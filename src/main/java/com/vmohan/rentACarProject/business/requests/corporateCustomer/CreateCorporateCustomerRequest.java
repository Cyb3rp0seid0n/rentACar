package com.vmohan.rentACarProject.business.requests.corporateCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {
	
	private String email;
	private String password;
	private String name;
	private int taxNumber;
}
