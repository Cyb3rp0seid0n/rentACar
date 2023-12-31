package com.vmohan.rentACarProject.business.requests.carMaintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {
	
	private String description; 
	private int carId;
}
