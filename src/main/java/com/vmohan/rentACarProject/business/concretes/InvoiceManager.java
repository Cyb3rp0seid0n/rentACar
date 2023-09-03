package com.vmohan.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.business.abstracts.InvoiceService;
import com.vmohan.rentACarProject.business.dtos.invoice.ListInvoiceDto;
import com.vmohan.rentACarProject.business.requests.invoice.CreateInvoiceRequest;
import com.vmohan.rentACarProject.business.requests.invoice.DeleteInvoiceRequest;
import com.vmohan.rentACarProject.business.requests.invoice.UpdateInvoiceRequest;
import com.vmohan.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessDataResult;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;
import com.vmohan.rentACarProject.dataAccess.abstracts.InvoiceDao;
import com.vmohan.rentACarProject.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService{
	
	private InvoiceDao invoiceDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService) {
		
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getAll() {
		
		var result = invoiceDao.findAll();
		
		List<ListInvoiceDto> response = result.stream()
				.map(brand -> modelMapperService.forDto().map(brand, ListInvoiceDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListInvoiceDto>>(response);
	}

	@Override
	public Result create(CreateInvoiceRequest createInvoiceRequest) {
		
		Invoice invoice = modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		
		invoiceDao.save(invoice);
		
		return new SuccessResult("Invoice.Created");
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		
		Invoice invoice = modelMapperService.forRequest().map(deleteInvoiceRequest, Invoice.class);
		
		invoiceDao.delete(invoice);
		
		return new SuccessResult("Invoice.Deleted");
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		
		Invoice invoice = modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
		
		invoiceDao.save(invoice);
		
		return new SuccessResult("Invoice.Updated");
	}

}
