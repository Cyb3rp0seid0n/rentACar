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

import com.vmohan.rentACarProject.business.abstracts.ColorService;
import com.vmohan.rentACarProject.business.dtos.color.ListColorDto;
import com.vmohan.rentACarProject.business.requests.color.CreateColorRequest;
import com.vmohan.rentACarProject.business.requests.color.DeleteColorRequest;
import com.vmohan.rentACarProject.business.requests.color.UpdateColorRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;

@RestController
@RequestMapping("/api/colors")

public class ColorsController {
	
	private ColorService colorService;
	
	@Autowired
	public ColorsController(ColorService colorService) {
		
		this.colorService = colorService;
	}
	
	@GetMapping("/getAll")
	public List<ListColorDto> getAll(){
		
		return this.colorService.getAll();
	}
	
	@PostMapping("/create")
	public void add(@RequestBody CreateColorRequest createColorRequest) throws BusinessException{
		
		colorService.create(createColorRequest);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestBody DeleteColorRequest deleteColorRequest) {
		
		colorService.delete(deleteColorRequest);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody UpdateColorRequest updateColorRequest) {
		
		colorService.update(updateColorRequest);
	}
	
	@GetMapping("/get")
	public ListColorDto get(@RequestParam int id) throws BusinessException {
		
		return colorService.getById(id);
	}
}
