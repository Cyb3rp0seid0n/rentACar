package com.vmohan.rentACarProject.business.requests.carDamage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {
	
	private int id;
	private String description;
	private int carId;

}
