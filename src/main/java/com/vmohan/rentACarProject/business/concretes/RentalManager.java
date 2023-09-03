package com.vmohan.rentACarProject.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.business.abstracts.AdditionalServiceItemService;
import com.vmohan.rentACarProject.business.abstracts.CarMaintenanceService;
import com.vmohan.rentACarProject.business.abstracts.CarService;
import com.vmohan.rentACarProject.business.abstracts.OrderedAdditionalServiceService;
import com.vmohan.rentACarProject.business.abstracts.RentalService;
import com.vmohan.rentACarProject.business.constants.Messages;
import com.vmohan.rentACarProject.business.dtos.orderedAdditionalService.ListOrderedAdditionalServiceDto;
import com.vmohan.rentACarProject.business.dtos.rental.ListRentalDto;
import com.vmohan.rentACarProject.business.requests.rental.CreateRentalRequest;
import com.vmohan.rentACarProject.business.requests.rental.DeleteRentalRequest;
import com.vmohan.rentACarProject.business.requests.rental.UpdateRentalRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.ErrorDataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessDataResult;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;
import com.vmohan.rentACarProject.dataAccess.abstracts.RentalDao;
import com.vmohan.rentACarProject.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private CarService carService;
	private ModelMapperService modelMapperService;
	private AdditionalServiceItemService additionalServiceItemService;
	private OrderedAdditionalServiceService orderedAdditionalServiceService;
	private CarMaintenanceService carMaintenanceService;
		
	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService,
			@Lazy CarMaintenanceService carMaintenanceService, CarService carService, 
			OrderedAdditionalServiceService orderedAdditionalServiceService, 
			AdditionalServiceItemService additionalServiceItemService) {
		
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carMaintenanceService = carMaintenanceService;
		this.carService = carService;
		this.orderedAdditionalServiceService = orderedAdditionalServiceService;
		this.additionalServiceItemService = additionalServiceItemService;
	}

	@Override
	public Result createForCorporateCustomer(CreateRentalRequest createRentalRequest) throws BusinessException {

		carMaintenanceService.isCarInMaintenance(createRentalRequest.getCarId());
		isCarRented(createRentalRequest.getCarId());   								
		
	    Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
	    rentalDao.save(rental);
	    
	    return new SuccessResult(Messages.RentalAdded);
	}

	@Override
	public Result createForIndividualCustomer(CreateRentalRequest createRentalRequest) throws BusinessException {
		
		carMaintenanceService.isCarInMaintenance(createRentalRequest.getCarId());
		isCarRented(createRentalRequest.getCarId());
		
	    Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
	    rentalDao.save(rental);
	    
	    return new SuccessResult(Messages.RentalAdded);
	}
	

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		
		Rental rental = modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		rentalDao.delete(rental);
		
		return new SuccessResult(Messages.RentalDeleted);
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		
		carMaintenanceService.isCarInMaintenance(updateRentalRequest.getCarId());
		
		Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		
		LocalDate estimatedDate = getById(updateRentalRequest.getId()).getData().getReturnDate();
			
		rental.setTotalPrice(rentalCalculation(rental));
		
		updateReturnMileage(rental);
		
		rentalDao.save(rental);
		
		return new SuccessResult(Messages.CarUpdated);
	}

	@Override
	public DataResult<List<ListRentalDto>> getAll() {
		
		var result = rentalDao.findAll();
		List<ListRentalDto> response = result.stream()
				.map(rental -> modelMapperService.forDto().map(rental, ListRentalDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	@Override
	public DataResult<ListRentalDto> getById(int id) {

		Rental result = rentalDao.getById(id);
		
		if(result == null) {
			return new ErrorDataResult<ListRentalDto>(Messages.RentalNotFound);
		}
		
		ListRentalDto response = modelMapperService.forDto().map(result, ListRentalDto.class);
		
		return new SuccessDataResult<ListRentalDto>(response);
	}

	private double rentalCalculation(Rental rental) {
		
		double totalPrice = 0;
		
		List<ListOrderedAdditionalServiceDto> orderedAdditionalServiceDtos = orderedAdditionalServiceService.findAllByRentalId(rental.getId()).getData();
		
		if(orderedAdditionalServiceDtos.size() > 0) {
			for(ListOrderedAdditionalServiceDto orderedAdditionalServiceDto : orderedAdditionalServiceDtos) {
				totalPrice += additionalServiceItemService.findById(orderedAdditionalServiceDto.getAdditionalServiceItemId()).getData().getPrice(); 
			}	
		}
		
		if(rental.getInitialCity().getId() != rental.getReturnCity().getId())
			totalPrice += 750;

		long days = ChronoUnit.DAYS.between(rental.getRentDate(), rental.getReturnDate());
		
		if(days == 0)
			days = 1;
		
		totalPrice += days * carService.getById(rental.getCar().getId()).getData().getCarDailyPrice();
		
		
		return totalPrice;
	}
	
	@Override
	public Result isCarRented(int id) throws BusinessException {
		
		if(rentalDao.findByCarIdAndReturnDateIsNull(id) != null) {
			throw new BusinessException(Messages.ThisCarIsRental);
		}
		else
			return new SuccessResult();
	}
	
	private void updateReturnMileage(Rental rental) {
		
		carService.getById(rental.getCar().getId()).getData().setMileage(rental.getReturnMileage());
	}
	
	private void LastSubmissionCheck(LocalDate estimatedDate, Rental rental) {
		
		long days = ChronoUnit.DAYS.between(estimatedDate, rental.getReturnDate());
		
		if(days > 0)
			rental.setTotalPrice(rental.getTotalPrice() * 2);
	}

}
