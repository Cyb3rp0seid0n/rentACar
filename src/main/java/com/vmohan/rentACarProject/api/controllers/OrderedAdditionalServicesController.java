package com.vmohan.rentACarProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmohan.rentACarProject.business.abstracts.OrderedAdditionalServiceService;
import com.vmohan.rentACarProject.business.dtos.orderedAdditionalService.ListOrderedAdditionalServiceDto;
import com.vmohan.rentACarProject.business.requests.orderedAdditionalService.CreateOrderedAdditionalServiceRequest;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

@RestController
@RequestMapping("/api/orderedAdditionalService")
public class OrderedAdditionalServicesController {

	OrderedAdditionalServiceService orderedAdditionalServiceService;

	@Autowired
	public OrderedAdditionalServicesController(OrderedAdditionalServiceService orderedAdditionalServiceService) {
		
		this.orderedAdditionalServiceService = orderedAdditionalServiceService;
	}
	
	@GetMapping("/getAll")
	DataResult<List<ListOrderedAdditionalServiceDto>> findAllByRentalId(@RequestParam int rentalId) {
		
		return orderedAdditionalServiceService.findAllByRentalId(rentalId);
	}
	
	@PostMapping("/create")
	Result add(@RequestBody CreateOrderedAdditionalServiceRequest createAdditionalServiceRequest) {
		
		return orderedAdditionalServiceService.add(createAdditionalServiceRequest);
	}
}
