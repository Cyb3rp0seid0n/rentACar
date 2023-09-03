package com.vmohan.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.business.abstracts.AdditionalServiceItemService;
import com.vmohan.rentACarProject.business.dtos.additionalServiceItem.ListAdditionalServiceItemDto;
import com.vmohan.rentACarProject.business.requests.additionalServiceItem.CreateAdditionalServiceItemRequest;
import com.vmohan.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.ErrorDataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessDataResult;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;
import com.vmohan.rentACarProject.dataAccess.abstracts.AdditionalServiceItemDao;
import com.vmohan.rentACarProject.entities.concretes.AdditionalServiceItem;

@Service
public class AdditionalServiceItemManager implements AdditionalServiceItemService {

	private ModelMapperService modelMapperService;
	private AdditionalServiceItemDao additionalServiceItemDao;

	@Autowired
	public AdditionalServiceItemManager(ModelMapperService modelMapperService,
			AdditionalServiceItemDao additionalServiceItemDao) {

		this.modelMapperService = modelMapperService;
		this.additionalServiceItemDao = additionalServiceItemDao;
	}

	@Override
	public Result add(CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest) {
		
		AdditionalServiceItem additionalServiceItem = modelMapperService.forRequest().map(createAdditionalServiceItemRequest, AdditionalServiceItem.class);
		additionalServiceItemDao.save(additionalServiceItem);
		
		return new SuccessResult();
	}

	@Override
	public DataResult<ListAdditionalServiceItemDto> findById(int id) {
		
		if(additionalServiceItemDao.existsById(id)) {
			
			AdditionalServiceItem item = additionalServiceItemDao.findById(id).get();
			ListAdditionalServiceItemDto response = modelMapperService.forDto().map(item, ListAdditionalServiceItemDto.class);
			
			return new SuccessDataResult<ListAdditionalServiceItemDto>(response);
		}
		else 
			return new ErrorDataResult<ListAdditionalServiceItemDto>();
	}

	@Override
	public DataResult<List<ListAdditionalServiceItemDto>> getAll() {
		
		var result = additionalServiceItemDao.findAll();
		
		List<ListAdditionalServiceItemDto> response = result.stream()
				.map(additionalServiceItem -> modelMapperService.forDto().map(additionalServiceItem, ListAdditionalServiceItemDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListAdditionalServiceItemDto>>(response);
	}

	
	
}
