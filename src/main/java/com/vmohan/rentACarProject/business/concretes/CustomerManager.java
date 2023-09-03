package com.vmohan.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.business.abstracts.CustomerService;
import com.vmohan.rentACarProject.business.constants.Messages;
import com.vmohan.rentACarProject.business.dtos.customer.ListCustomerDto;
import com.vmohan.rentACarProject.business.requests.customer.CreateCustomerRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.ErrorDataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessDataResult;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;
import com.vmohan.rentACarProject.dataAccess.abstracts.CustomerDao;
import com.vmohan.rentACarProject.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService {
	
	private CustomerDao customerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CustomerManager(CustomerDao customerDao, ModelMapperService modelMapperService) {
		
		this.customerDao = customerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListCustomerDto>> getAll() {
		
		var result = customerDao.findAll();
		
		List<ListCustomerDto> response = result.stream()
				.map(customer -> modelMapperService.forDto().map(customer, ListCustomerDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCustomerDto>>(response);
	}

	@Override
	public DataResult<ListCustomerDto> getById(int id) {
		
		Customer result = customerDao.getById(id);
		
		if(result == null) {
			return new ErrorDataResult<ListCustomerDto>("Car.NotFound");
		}
		
		ListCustomerDto response = modelMapperService.forDto().map(result, ListCustomerDto.class);		
		
		return new SuccessDataResult<ListCustomerDto>(response);
	}

	@Override
	public Result create(CreateCustomerRequest createCustomerRequest) throws BusinessException {
		
		Customer customer = modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
		
		customerDao.save(customer);
		
		return new SuccessResult(Messages.CustomerAdded);
	}

}
