package com.vmohan.rentACarProject.business.requests.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

	private double dailyPrice;
	private int modelYear;
	private String description;
	private int brandId;
	private int colorId;
	private int mileage;
	
}
