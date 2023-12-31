package com.vmohan.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.business.abstracts.CreditCardDetailsService;
import com.vmohan.rentACarProject.business.constants.Messages;
import com.vmohan.rentACarProject.business.dtos.creditCardDetails.ListCreditCardDetailsDto;
import com.vmohan.rentACarProject.business.requests.creditCardDetails.CreateCreditCardDetailsRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.ErrorDataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessDataResult;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;
import com.vmohan.rentACarProject.dataAccess.abstracts.CreditCardDetailsDao;
import com.vmohan.rentACarProject.entities.concretes.CreditCardDetails;


@Service
public class CreditCardDetailsManager implements CreditCardDetailsService {

	CreditCardDetailsDao creditCardDetailsDao;
	ModelMapperService modelMapperService;
	
	@Autowired
	public CreditCardDetailsManager(CreditCardDetailsDao cardDetailsDao, ModelMapperService mapperService) {
		
		this.creditCardDetailsDao = cardDetailsDao;
		this.modelMapperService = mapperService;
	}

	@Override
	public DataResult<List<ListCreditCardDetailsDto>> getAll() {
		
		var result = creditCardDetailsDao.findAll();
		
		List<ListCreditCardDetailsDto> response = result.stream()
				.map(creditCardDetails -> modelMapperService.forDto().map(creditCardDetails, ListCreditCardDetailsDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCreditCardDetailsDto>>(response);
	}

	@Override
	public DataResult<ListCreditCardDetailsDto> getById(int id) {

		CreditCardDetails result = creditCardDetailsDao.getById(id);
		
		if(result == null) {
			
			return new ErrorDataResult<ListCreditCardDetailsDto>("Car.NotFound");
		}
		
		ListCreditCardDetailsDto response = modelMapperService.forDto().map(result, ListCreditCardDetailsDto.class);
		
		return new SuccessDataResult<ListCreditCardDetailsDto>(response);
	}

	@Override
	public Result create(CreateCreditCardDetailsRequest createCreditCardDetailsRequest) throws BusinessException {
		
		CreditCardDetails creditCardDetails = modelMapperService.forRequest().map(createCreditCardDetailsRequest, CreditCardDetails.class);
		
		creditCardDetailsDao.save(creditCardDetails);
		
		return new SuccessResult(Messages.CreditCardAdded);
	}

	@Override
	public DataResult<List<ListCreditCardDetailsDto>> getAllByCustomerId(int customerId) {

		var result = creditCardDetailsDao.findAllByCustomerId(customerId);
		
		List<ListCreditCardDetailsDto> response = result.stream()
				.map(creditCardDetails -> modelMapperService.forDto().map(creditCardDetails, ListCreditCardDetailsDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCreditCardDetailsDto>>(response);
	}

}
