package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.invoice.ListInvoiceDto;
import com.vmohan.rentACarProject.business.requests.invoice.CreateInvoiceRequest;
import com.vmohan.rentACarProject.business.requests.invoice.DeleteInvoiceRequest;
import com.vmohan.rentACarProject.business.requests.invoice.UpdateInvoiceRequest;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

public interface InvoiceService {
	
	DataResult<List<ListInvoiceDto>> getAll();
	Result create(CreateInvoiceRequest createInvoiceRequest);
	Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
	Result update(UpdateInvoiceRequest updateInvoiceRequest);
}
