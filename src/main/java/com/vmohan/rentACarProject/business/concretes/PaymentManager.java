package com.vmohan.rentACarProject.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.business.abstracts.AdditionalServiceItemService;
import com.vmohan.rentACarProject.business.abstracts.CarService;
import com.vmohan.rentACarProject.business.abstracts.CreditCardDetailsService;
import com.vmohan.rentACarProject.business.abstracts.OrderedAdditionalServiceService;
import com.vmohan.rentACarProject.business.abstracts.PaymentService;
import com.vmohan.rentACarProject.business.abstracts.RentalService;
import com.vmohan.rentACarProject.business.dtos.orderedAdditionalService.ListOrderedAdditionalServiceDto;
import com.vmohan.rentACarProject.business.dtos.payment.ListPaymentDto;
import com.vmohan.rentACarProject.business.dtos.rental.ListRentalDto;
import com.vmohan.rentACarProject.business.requests.creditCardDetails.CreateCreditCardDetailsRequest;
import com.vmohan.rentACarProject.business.requests.payment.CreatePaymentRequest;
import com.vmohan.rentACarProject.business.requests.payment.DeletePaymentRequest;
import com.vmohan.rentACarProject.core.adapters.abstracts.BankAdapterService;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessDataResult;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;
import com.vmohan.rentACarProject.dataAccess.abstracts.PaymentDao;
import com.vmohan.rentACarProject.entities.concretes.Payment;

@Service

public class PaymentManager implements PaymentService {

	private ModelMapperService modelMapperService;
	private PaymentDao paymentDao;
	private RentalService rentalService;
	private BankAdapterService bankAdapterService;
	private CarService carService;
	private AdditionalServiceItemService additionalServiceItemService;
	private OrderedAdditionalServiceService orderedAdditionalServiceService;
	private CreditCardDetailsService creditCardDetailsService;

	@Autowired
	public PaymentManager(ModelMapperService modelMapperService, PaymentDao paymentDao, RentalService rentalService,
			BankAdapterService bankAdapterService, CarService carService,
			AdditionalServiceItemService additionalServiceItemService,
			OrderedAdditionalServiceService orderedAdditionalServiceService,
			CreditCardDetailsService creditCardDetailsService) {
		
		this.modelMapperService = modelMapperService;
		this.paymentDao = paymentDao;
		this.rentalService = rentalService;
		this.bankAdapterService = bankAdapterService;
		this.orderedAdditionalServiceService = orderedAdditionalServiceService;
		this.carService = carService;
		this.additionalServiceItemService = additionalServiceItemService;
		this.creditCardDetailsService = creditCardDetailsService;
	}

	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) {

		// this.rentalService.checkRentCarExists(createPaymentRequest.getRentalId());
		checkPaymentRentalId(createPaymentRequest.getRentalId());
		
		if(createPaymentRequest.isSaveCreditCard())
			saveCreditCard(createPaymentRequest.getCreateCreditCardDetailsRequest());		
		
		ListRentalDto rental = rentalService.getById(createPaymentRequest.getRentalId()).getData();

		double totalPrice = rentalCalculation(rental);

		bankAdapterService.checkIfLimitIsEnough(createPaymentRequest.getCreateCreditCardDetailsRequest().getCardNumber(), 
				Integer.toString(createPaymentRequest.getCreateCreditCardDetailsRequest().getExpirationDate().getYear()),
				createPaymentRequest.getCreateCreditCardDetailsRequest().getExpirationDate().getMonth().toString(), 
				Integer.toString(createPaymentRequest.getCreateCreditCardDetailsRequest().getCVV()), totalPrice);

		Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

		payment.setTotalPayment(totalPrice);

		payment.setId(0);
		
		paymentDao.save(payment);

		return new SuccessResult();
	}
	

	@Override
	public Result delete(DeletePaymentRequest deletePaymentRequest) {

		checkPaymentExists(deletePaymentRequest.getId());

		Payment payment = modelMapperService.forRequest().map(deletePaymentRequest, Payment.class);
		paymentDao.deleteById(payment.getId());

		return new SuccessResult();
	}

	@Override
	public DataResult<List<ListPaymentDto>> getAll() {

		List<Payment> result = paymentDao.findAll();
		List<ListPaymentDto> response = result.stream()
				.map(payment -> modelMapperService.forDto().map(payment, ListPaymentDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListPaymentDto>>(response);
	}

	@Override
	public DataResult<List<ListPaymentDto>> getAllPaged(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		List<Payment> result = paymentDao.findAll(pageable).getContent();
		List<ListPaymentDto> response = result.stream()
				.map(payment -> modelMapperService.forDto().map(payment, ListPaymentDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListPaymentDto>>(response);
	}

	@Override
	public DataResult<ListPaymentDto> getByRentalId(int rentalId) {

		// this.rentalService.checkRentCarExists(rentalId);

		var result = paymentDao.getAllByRental_Id(rentalId);
		ListPaymentDto response = modelMapperService.forDto().map(result, ListPaymentDto.class);

		return new SuccessDataResult<ListPaymentDto>(response);
	}

	@Override
	public boolean checkPaymentRentalId(int rentalId) {

		var result = paymentDao.getAllByRental_Id(rentalId);
		
		if (result != null) {
			throw new BusinessException("Daha önce ödemesi alınmıştır.");
		}
		return true;
	}

	private boolean checkPaymentExists(int paymentId) {

		var result = paymentDao.existsById(paymentId);
		
		if (result) {
			return true;
		}
		throw new BusinessException("Payment için geçersiz Id..!!!!");
	}

	private double rentalCalculation(ListRentalDto rental) {

		double totalPrice = 0;

		List<ListOrderedAdditionalServiceDto> orderedAdditionalServiceDtos = orderedAdditionalServiceService
				.findAllByRentalId(rental.getId()).getData();

		if (orderedAdditionalServiceDtos.size() > 0) {
			for (ListOrderedAdditionalServiceDto orderedAdditionalServiceDto : orderedAdditionalServiceDtos) {
				totalPrice += additionalServiceItemService
						.findById(orderedAdditionalServiceDto.getAdditionalServiceItemId()).getData().getPrice();
			}
		}
		
		if (rental.getInitialCityId() != rental.getReturnCityId())
			totalPrice += 750;

		long days = ChronoUnit.DAYS.between(rental.getRentDate(), rental.getReturnDate());

		if (days == 0)
			days = 1;

		totalPrice += days * carService.getById(rental.getCarId()).getData().getCarDailyPrice();

		return totalPrice;
	}

	private Result saveCreditCard(CreateCreditCardDetailsRequest createCreditCardDetailsRequest) {
		
		return creditCardDetailsService.create(createCreditCardDetailsRequest);
	}
}
