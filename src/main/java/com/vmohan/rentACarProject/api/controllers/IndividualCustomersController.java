package com.vmohan.rentACarProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmohan.rentACarProject.business.abstracts.IndividualCustomerService;
import com.vmohan.rentACarProject.business.dtos.individualCustomer.ListIndividualCustomerDto;
import com.vmohan.rentACarProject.business.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualCustomers")
public class IndividualCustomersController {
	
	private IndividualCustomerService individualCustomerService;
	
	@Autowired
	public IndividualCustomersController(IndividualCustomerService customerService) {
		
		this.individualCustomerService = customerService;
	}
	
	@GetMapping("/getAll")
	DataResult<List<ListIndividualCustomerDto>> getAll() {
		
		return individualCustomerService.getAll();
	}
	
	@GetMapping("/get")
	DataResult<ListIndividualCustomerDto> getById(@RequestParam int id) {
		
		return individualCustomerService.getById(id);
	}
	
	@PostMapping("/create")
	Result create(@RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException {
		
		return individualCustomerService.create(createIndividualCustomerRequest);
	}

}
