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

import com.vmohan.rentACarProject.business.abstracts.CarMaintenanceService;
import com.vmohan.rentACarProject.business.dtos.carMaintenance.ListCarMaintenanceDto;
import com.vmohan.rentACarProject.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.vmohan.rentACarProject.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

@RestController
@RequestMapping("/api/carMaintenances")
public class CarMaintenancesController {
	

	private CarMaintenanceService carMaintenanceService;
	
	@Autowired
	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		
		this.carMaintenanceService = carMaintenanceService;
	}
	
	@PostMapping("/create")
	public Result add(@RequestBody CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException{
		
		return carMaintenanceService.create(createCarMaintenanceRequest);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		
		return carMaintenanceService.update(updateCarMaintenanceRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		
		return carMaintenanceService.getAll();
	}
	
	@GetMapping("/getAllByCarId")
	public DataResult<List<ListCarMaintenanceDto>> getAllByCarId(@RequestParam int id) {
		
		return carMaintenanceService.getAllByCarId(id);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestParam int id) {
		
		return carMaintenanceService.delete(id);
	}
}
