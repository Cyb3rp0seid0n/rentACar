package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.brand.ListBrandDto;
import com.vmohan.rentACarProject.business.requests.brand.CreateBrandRequest;
import com.vmohan.rentACarProject.business.requests.brand.DeleteBrandRequest;
import com.vmohan.rentACarProject.business.requests.brand.UpdateBrandRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;

public interface BrandService {

	List<ListBrandDto> getAll();
	ListBrandDto getById(int id) throws BusinessException;
	
	void create(CreateBrandRequest createBrandRequest) throws BusinessException;
	void delete(DeleteBrandRequest deleteBrandRequest);
	void update(UpdateBrandRequest updateBrandRequest);



}
