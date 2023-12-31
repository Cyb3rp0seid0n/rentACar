package com.vmohan.rentACarProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmohan.rentACarProject.business.abstracts.CorporateCustomerService;
import com.vmohan.rentACarProject.business.dtos.corporateCustomer.ListCorporateCustomerDto;
import com.vmohan.rentACarProject.business.requests.corporateCustomer.CreateCorporateCustomerRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;


@RestController
@RequestMapping("/api/corporateCustomers")
public class CorporateCustomersController {
	
	private CorporateCustomerService corporateCustomerService;
	
	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		
		this.corporateCustomerService = corporateCustomerService;
	}
	
	@GetMapping("/getAll")
	DataResult<List<ListCorporateCustomerDto>> getAll() {
		
		return corporateCustomerService.getAll();
		
	}
	@GetMapping("/get")
	DataResult<ListCorporateCustomerDto> getById(@RequestParam int id) {
		
		return corporateCustomerService.getById(id);
	}
	@PostMapping("/create")
	Result create(@RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException {
		
		return corporateCustomerService.create(createCorporateCustomerRequest);
	}
}
