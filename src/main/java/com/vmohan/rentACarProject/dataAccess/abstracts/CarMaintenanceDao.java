package com.vmohan.rentACarProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmohan.rentACarProject.entities.concretes.CarMaintenance;

@Repository
public interface CarMaintenanceDao extends JpaRepository <CarMaintenance, Integer> {

	List<CarMaintenance> getAllByCarId(int carId);
	
	CarMaintenance findByCarIdAndReturnDateIsNull(int carId);
}
