package com.vmohan.rentACarProject.business.dtos.rental;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListRentalDto {
    
	private int id;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private double additionalPrice;   
    private double totalPrice;
    private int customerId;
    private int carId;
    private int initialMileage;
    private int returnMileage;
	private int initialCityId;
	private int returnCityId;
}


