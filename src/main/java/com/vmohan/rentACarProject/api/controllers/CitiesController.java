package com.vmohan.rentACarProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmohan.rentACarProject.business.abstracts.CityService;
import com.vmohan.rentACarProject.business.dtos.city.ListCityDto;
import com.vmohan.rentACarProject.business.requests.city.CreateCityRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

	private CityService cityService;

	@Autowired
	public CitiesController(CityService cityService) {

		this.cityService = cityService;
	}

	@GetMapping("/getAll")
	DataResult<List<ListCityDto>> getAll() {
		
		return cityService.getAll();
	}

	@GetMapping("/get")
	DataResult<ListCityDto> getById(@RequestParam int id) {
		
		return cityService.getById(id);
	}

	@PostMapping("/create")
	Result create(@RequestBody CreateCityRequest createCityRequest) throws BusinessException {
		
		return cityService.create(createCityRequest);
	}

}
