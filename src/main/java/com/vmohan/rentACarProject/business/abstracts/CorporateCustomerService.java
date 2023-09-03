package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.corporateCustomer.ListCorporateCustomerDto;
import com.vmohan.rentACarProject.business.requests.corporateCustomer.CreateCorporateCustomerRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

public interface CorporateCustomerService {
	
	DataResult<List<ListCorporateCustomerDto>> getAll();
	DataResult<ListCorporateCustomerDto> getById(int id);
	Result create(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException;

}
