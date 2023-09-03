package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.individualCustomer.ListIndividualCustomerDto;
import com.vmohan.rentACarProject.business.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

public interface IndividualCustomerService {
	
	DataResult<List<ListIndividualCustomerDto>> getAll();
	DataResult<ListIndividualCustomerDto> getById(int id);
	Result create(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException;
}
