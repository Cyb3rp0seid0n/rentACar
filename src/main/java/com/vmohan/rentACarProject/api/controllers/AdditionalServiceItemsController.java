package com.vmohan.rentACarProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmohan.rentACarProject.business.abstracts.AdditionalServiceItemService;
import com.vmohan.rentACarProject.business.dtos.additionalServiceItem.ListAdditionalServiceItemDto;
import com.vmohan.rentACarProject.business.requests.additionalServiceItem.CreateAdditionalServiceItemRequest;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;


@RestController
@RequestMapping("/api/additionalServiceItems")
public class AdditionalServiceItemsController {

	private AdditionalServiceItemService additionalServiceItemService;
	
	@Autowired
	public AdditionalServiceItemsController(AdditionalServiceItemService additionalServiceItemService) {
		
		this.additionalServiceItemService = additionalServiceItemService;
	}
	
	@PostMapping("/create")
	Result add(@RequestBody CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest) {
		
		return additionalServiceItemService.add(createAdditionalServiceItemRequest);
	}
	
	@GetMapping("/get")
	DataResult<ListAdditionalServiceItemDto> findById(@RequestParam int id) {
		
		return additionalServiceItemService.findById(id);
	}
	
	@GetMapping("/getAll")
	DataResult<List<ListAdditionalServiceItemDto>> getAll() {
		
		return additionalServiceItemService.getAll();
	}
}
