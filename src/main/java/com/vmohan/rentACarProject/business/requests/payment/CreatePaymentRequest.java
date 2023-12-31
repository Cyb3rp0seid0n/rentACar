package com.vmohan.rentACarProject.business.requests.payment;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.vmohan.rentACarProject.business.requests.creditCardDetails.CreateCreditCardDetailsRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {

	@NotNull
	private LocalDate paymentDate;
	@NotNull
	private int rentalId;
	private CreateCreditCardDetailsRequest createCreditCardDetailsRequest;
	private double amount;
	private boolean saveCreditCard;
}
