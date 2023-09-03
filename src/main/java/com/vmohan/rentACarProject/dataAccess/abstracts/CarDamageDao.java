package com.vmohan.rentACarProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmohan.rentACarProject.business.dtos.carDamage.ListCarDamageDto;
import com.vmohan.rentACarProject.entities.concretes.CarDamage;

@Repository
public interface CarDamageDao extends JpaRepository<CarDamage, Integer>{
	
	List<ListCarDamageDto> getAllByCarId(int carId);
}
