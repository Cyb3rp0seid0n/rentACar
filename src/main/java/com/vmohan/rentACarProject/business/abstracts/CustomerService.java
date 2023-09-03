package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.customer.ListCustomerDto;
import com.vmohan.rentACarProject.business.requests.customer.CreateCustomerRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

public interface CustomerService {
	
	DataResult<List<ListCustomerDto>> getAll();
	DataResult<ListCustomerDto> getById(int id);
	Result create(CreateCustomerRequest createCustomerRequest) throws BusinessException;
}
