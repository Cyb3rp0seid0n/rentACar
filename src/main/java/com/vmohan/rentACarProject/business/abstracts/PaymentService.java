package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.payment.ListPaymentDto;
import com.vmohan.rentACarProject.business.requests.payment.CreatePaymentRequest;
import com.vmohan.rentACarProject.business.requests.payment.DeletePaymentRequest;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;


public interface PaymentService {

	Result add(CreatePaymentRequest createPaymentRequest);
	Result delete(DeletePaymentRequest deletePaymentRequest);

	DataResult<List<ListPaymentDto>> getAll();
	DataResult<List<ListPaymentDto>> getAllPaged(int pageNo, int pageSize);
	DataResult<ListPaymentDto> getByRentalId(int rentalId);
	
	boolean checkPaymentRentalId(int rentalId);
}
