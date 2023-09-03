package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.rental.ListRentalDto;
import com.vmohan.rentACarProject.business.requests.rental.CreateRentalRequest;
import com.vmohan.rentACarProject.business.requests.rental.DeleteRentalRequest;
import com.vmohan.rentACarProject.business.requests.rental.UpdateRentalRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

public interface RentalService {
	
	Result createForCorporateCustomer(CreateRentalRequest createRentalRequest) throws BusinessException;  
	Result createForIndividualCustomer(CreateRentalRequest createRentalRequest) throws BusinessException;  	
	Result delete(DeleteRentalRequest deleteCarRequest);
	Result update(UpdateRentalRequest updateCarRequest);
	Result isCarRented(int carId) throws BusinessException;
	
	DataResult<List<ListRentalDto>> getAll();
	DataResult<ListRentalDto> getById(int id);

}
