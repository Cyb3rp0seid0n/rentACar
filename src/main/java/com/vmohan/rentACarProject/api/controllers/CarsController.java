package com.vmohan.rentACarProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmohan.rentACarProject.business.abstracts.CarService;
import com.vmohan.rentACarProject.business.dtos.car.ListCarDto;
import com.vmohan.rentACarProject.business.requests.car.CreateCarRequest;
import com.vmohan.rentACarProject.business.requests.car.DeleteCarRequest;
import com.vmohan.rentACarProject.business.requests.car.UpdateCarRequest;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

	private CarService carService;

	@Autowired
	public CarsController(CarService carService) {
		
		this.carService = carService;
	}

	@PostMapping("/create")
	public Result add(@RequestBody CreateCarRequest createCarRequest) {
		
		return carService.create(createCarRequest);
	}
	
	@GetMapping("/get")
	public DataResult<ListCarDto> get(@RequestParam int id) {
		
		return carService.getById(id);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateCarRequest updateCarRequest) {
		
		return carService.update(updateCarRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) {
		
		return carService.delete(deleteCarRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<ListCarDto>> getAll() {
		
		return carService.getAll();
	}
	
	@GetMapping("/getAllPaged")
	DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
		
		return carService.getAllPaged(pageNo, pageSize);
		
	}
	
	@GetMapping("/getAllSorted")
	DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction) {
		
		return this.carService.getAllSorted(direction);
		
	}
	
	@GetMapping("/getAllByDailyPriceLessThanEqual")
	DataResult<List<ListCarDto>> getAllByDailyPriceLessThanEqual(double dailyPrice) {
		
		return this.carService.getAllByDailyPriceLessThanEqual(dailyPrice);
	}
	
}
