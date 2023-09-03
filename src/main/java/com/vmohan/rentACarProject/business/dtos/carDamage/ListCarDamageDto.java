package com.vmohan.rentACarProject.business.dtos.carDamage;

import com.vmohan.rentACarProject.entities.concretes.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDamageDto {
	
	int id;
	String description;
	Car car;

}
