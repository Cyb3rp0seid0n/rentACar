package com.vmohan.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.business.abstracts.CityService;
import com.vmohan.rentACarProject.business.constants.Messages;
import com.vmohan.rentACarProject.business.dtos.city.ListCityDto;
import com.vmohan.rentACarProject.business.requests.city.CreateCityRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.ErrorDataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessDataResult;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;
import com.vmohan.rentACarProject.dataAccess.abstracts.CityDao;
import com.vmohan.rentACarProject.entities.concretes.City;

@Service
public class CityManager implements CityService{

	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	
	
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
		
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListCityDto>> getAll() {
		
		var result = cityDao.findAll();
		
		List<ListCityDto> response = result.stream()
				.map(city -> modelMapperService.forDto().map(city, ListCityDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCityDto>>(response);
	}

	@Override
	public DataResult<ListCityDto> getById(int id) {
		
		City result = cityDao.getById(id);
		
		if(result == null) {
			return new ErrorDataResult<ListCityDto>("City.NotFound");
		}
		
		ListCityDto response = modelMapperService.forDto().map(result, ListCityDto.class);		
		
		return new SuccessDataResult<ListCityDto>(response);
	}

	@Override
	public Result create(CreateCityRequest createCityRequest) throws BusinessException {
		
		City city = modelMapperService.forRequest().map(createCityRequest, City.class);
		
		cityDao.save(city);
		
		return new SuccessResult(Messages.CityAdded);
	}

}
