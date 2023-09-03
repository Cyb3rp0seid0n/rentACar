package com.vmohan.rentACarProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmohan.rentACarProject.entities.concretes.Brand;
import com.vmohan.rentACarProject.entities.concretes.Color;

@Repository
public interface ColorDao extends JpaRepository<Color, Integer>{
	
	Color getColorById(int id);
	
	List<Brand> getColorByName(String name);

}
