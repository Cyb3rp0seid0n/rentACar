package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.creditCardDetails.ListCreditCardDetailsDto;
import com.vmohan.rentACarProject.business.requests.creditCardDetails.CreateCreditCardDetailsRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

public interface CreditCardDetailsService {
	
	DataResult<List<ListCreditCardDetailsDto>> getAll();
	DataResult<ListCreditCardDetailsDto> getById(int id);
	DataResult<List<ListCreditCardDetailsDto>> getAllByCustomerId(int customerId);
	
	Result create(CreateCreditCardDetailsRequest cardDetailsRequest) throws BusinessException;

}
