package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.orderedAdditionalService.ListOrderedAdditionalServiceDto;
import com.vmohan.rentACarProject.business.requests.orderedAdditionalService.CreateOrderedAdditionalServiceRequest;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

public interface OrderedAdditionalServiceService {

	DataResult<List<ListOrderedAdditionalServiceDto>> findAllByRentalId(int rentalId);
	
	Result add(CreateOrderedAdditionalServiceRequest createAdditionalServiceRequest);
	
	// update 
	// delete
	
}
