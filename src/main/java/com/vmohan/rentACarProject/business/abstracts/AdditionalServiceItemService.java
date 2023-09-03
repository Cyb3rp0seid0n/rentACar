package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.additionalServiceItem.ListAdditionalServiceItemDto;
import com.vmohan.rentACarProject.business.requests.additionalServiceItem.CreateAdditionalServiceItemRequest;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

public interface AdditionalServiceItemService {

	Result add(CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest);
	DataResult<ListAdditionalServiceItemDto> findById(int id);
	DataResult<List<ListAdditionalServiceItemDto>> getAll();
	
}
