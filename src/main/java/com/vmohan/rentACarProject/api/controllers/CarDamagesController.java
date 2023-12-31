package com.vmohan.rentACarProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmohan.rentACarProject.business.abstracts.CarDamageService;
import com.vmohan.rentACarProject.business.dtos.carDamage.ListCarDamageDto;
import com.vmohan.rentACarProject.business.requests.carDamage.CreateCarDamageRequest;
import com.vmohan.rentACarProject.business.requests.carDamage.UpdateCarDamageRequest;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

@RestController
@RequestMapping("/api/carDamages")
public class CarDamagesController {

	private CarDamageService carDamageService;

	@Autowired
	public CarDamagesController(CarDamageService carDamageService) {
		
		this.carDamageService = carDamageService;
	}

	@PostMapping("/create")
	public Result add(@RequestBody CreateCarDamageRequest createCarDamageRequest) {
		
		return carDamageService.create(createCarDamageRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestBody UpdateCarDamageRequest updateCarDamageRequest) {
		
		return carDamageService.update(updateCarDamageRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<ListCarDamageDto>> getAll() {
		
		return carDamageService.getAll();
	}

	@GetMapping("/getAllByCarId")
	public DataResult<List<ListCarDamageDto>> getAllByCarId(@RequestParam int id) {
		
		return carDamageService.getAllByCarId(id);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestParam int id) {
		
		return carDamageService.delete(id);
	}
}