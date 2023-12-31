package com.vmohan.rentACarProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmohan.rentACarProject.business.abstracts.CustomerService;
import com.vmohan.rentACarProject.business.dtos.customer.ListCustomerDto;
import com.vmohan.rentACarProject.business.requests.customer.CreateCustomerRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {
	
	private CustomerService customerService;
	
	@Autowired
	public CustomersController(CustomerService customerService) {
		
		this.customerService = customerService;
	}
	
	@GetMapping("/getAll")
	DataResult<List<ListCustomerDto>> getAll() {
		
		return customerService.getAll();
		
	}
	@GetMapping("/get")
	DataResult<ListCustomerDto> getById(@RequestParam int id) {
		
		return customerService.getById(id);
		
	}
	@PostMapping("/create")
	Result create(@RequestBody CreateCustomerRequest createCustomerRequest) throws BusinessException {
		
		return customerService.create(createCustomerRequest);
	}

}
