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

import com.vmohan.rentACarProject.business.abstracts.BrandService;
import com.vmohan.rentACarProject.business.dtos.brand.ListBrandDto;
import com.vmohan.rentACarProject.business.requests.brand.CreateBrandRequest;
import com.vmohan.rentACarProject.business.requests.brand.DeleteBrandRequest;
import com.vmohan.rentACarProject.business.requests.brand.UpdateBrandRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;

@RestController
@RequestMapping("/api/brands")

public class BrandsController {

	private BrandService brandService;

	@Autowired
	public BrandsController(BrandService brandService) {
		
		this.brandService = brandService;
	}

	@GetMapping("/getAll")
	public List<ListBrandDto> getAll() {
		
		return brandService.getAll();
	}

	@PostMapping("/create")
	public void add(@RequestBody CreateBrandRequest createBrandRequest) throws BusinessException {
		
		brandService.create(createBrandRequest);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestBody DeleteBrandRequest deleteBrandRequest) {
		
		brandService.delete(deleteBrandRequest);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		
		brandService.update(updateBrandRequest);
	}

	@GetMapping("/get")
	public ListBrandDto get(@RequestParam int id) throws BusinessException {
		
		return brandService.getById(id);
	}
}