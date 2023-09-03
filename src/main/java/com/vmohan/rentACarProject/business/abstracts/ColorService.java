package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.color.ListColorDto;
import com.vmohan.rentACarProject.business.requests.color.CreateColorRequest;
import com.vmohan.rentACarProject.business.requests.color.DeleteColorRequest;
import com.vmohan.rentACarProject.business.requests.color.UpdateColorRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;

public interface ColorService {

	List<ListColorDto> getAll();
	ListColorDto getById(int id) throws BusinessException;
	void create(CreateColorRequest createColorRequest) throws BusinessException;
	void delete(DeleteColorRequest deleteColorRequest);
	void update(UpdateColorRequest updateColorRequest);
}
