package com.vmohan.rentACarProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vmohan.rentACarProject.business.abstracts.RentalService;
import com.vmohan.rentACarProject.business.dtos.rental.ListRentalDto;
import com.vmohan.rentACarProject.business.requests.rental.CreateRentalRequest;
import com.vmohan.rentACarProject.business.requests.rental.DeleteRentalRequest;
import com.vmohan.rentACarProject.business.requests.rental.UpdateRentalRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
	
	private RentalService rentalService;
	
	@Autowired
	public RentalsController(RentalService rentalService) {
		
		this.rentalService = rentalService;
	}
	
	@PostMapping("/createCorporateCustomer")
	Result createCorporateCustomer(@RequestBody @Valid CreateRentalRequest createRentalRequest) throws BusinessException {
		
		return rentalService.createForCorporateCustomer(createRentalRequest);
	}
	
	@PostMapping("/createIndividualCustomer")
	Result createIndividualCustomer(@RequestBody @Valid CreateRentalRequest createRentalRequest) throws BusinessException {
		
		return rentalService.createForIndividualCustomer(createRentalRequest);
	}
	
	@DeleteMapping("/delete")
	Result delete(DeleteRentalRequest deleteCarRequest) {
		
		return rentalService.delete(deleteCarRequest);		
	}

	@PutMapping("/update")
	Result update(UpdateRentalRequest updateCarRequest) {
		
		return rentalService.update(updateCarRequest);
	}

	@GetMapping("/getAll")
	DataResult<List<ListRentalDto>> getAll() {
		
		return rentalService.getAll();
	}

	@GetMapping("/get")
	DataResult<ListRentalDto> getById(int id) {
		
		return rentalService.getById(id);
	}

	@GetMapping("/isRented")
	Result isCarRented(int carId) throws BusinessException {
		
		return rentalService.isCarRented(carId);
	}
	
}
