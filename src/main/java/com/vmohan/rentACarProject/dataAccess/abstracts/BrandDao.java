package com.vmohan.rentACarProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmohan.rentACarProject.entities.concretes.Brand;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer>{
	
	Brand getBrandById(int id);
	
	List<Brand> getBrandByName(String name);
}
