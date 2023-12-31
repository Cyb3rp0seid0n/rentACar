package com.vmohan.rentACarProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vmohan.rentACarProject.business.abstracts.PaymentService;
import com.vmohan.rentACarProject.business.dtos.payment.ListPaymentDto;
import com.vmohan.rentACarProject.business.requests.payment.CreatePaymentRequest;
import com.vmohan.rentACarProject.business.requests.payment.DeletePaymentRequest;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;



@RestController
@RequestMapping("/api/payment")
public class PaymentsController {
	
	private PaymentService paymentService ;
	
	@Autowired	
	public PaymentsController(PaymentService paymentService) {		
		
		this.paymentService = paymentService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreatePaymentRequest createPaymentRequest)  {
		
		return paymentService.add(createPaymentRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeletePaymentRequest deletePaymentRequest)  {
		
		return paymentService.delete(deletePaymentRequest);
	}


	@GetMapping("/getAll")
	public DataResult<List<ListPaymentDto>> getAll() {
		
		return paymentService.getAll();
	}

	@PostMapping("/getAllPaged")
	public DataResult<List<ListPaymentDto>> getAllPaged(int pageNo, int pageSize) {
		
		return paymentService.getAllPaged(pageNo, pageSize);
	}

	@GetMapping("/getPaymentByRentalId")
	public DataResult<ListPaymentDto> getByRentalId(int rentalId)  {
		
		return paymentService.getByRentalId(rentalId);
	}

}
