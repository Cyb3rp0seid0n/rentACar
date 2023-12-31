package com.vmohan.rentACarProject.business.dtos.carMaintenance;

import java.time.LocalDate;

import com.vmohan.rentACarProject.entities.concretes.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarMaintenanceDto {
	
	private int id;
	private String description;
	private LocalDate returnDate;
	private Car car;
}
