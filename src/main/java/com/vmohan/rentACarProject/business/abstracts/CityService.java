package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.city.ListCityDto;
import com.vmohan.rentACarProject.business.requests.city.CreateCityRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

public interface CityService {

	DataResult<List<ListCityDto>> getAll();
	DataResult<ListCityDto> getById(int id);
	Result create(CreateCityRequest createCityRequest) throws BusinessException;
}
