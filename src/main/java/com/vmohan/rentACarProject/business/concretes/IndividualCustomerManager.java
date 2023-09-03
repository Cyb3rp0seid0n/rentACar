package com.vmohan.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.business.abstracts.IndividualCustomerService;
import com.vmohan.rentACarProject.business.constants.Messages;
import com.vmohan.rentACarProject.business.dtos.individualCustomer.ListIndividualCustomerDto;
import com.vmohan.rentACarProject.business.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.ErrorDataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessDataResult;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;
import com.vmohan.rentACarProject.dataAccess.abstracts.IndividualCustomerDao;
import com.vmohan.rentACarProject.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
	
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao customerDao, ModelMapperService modelMapperService) {
		
		this.individualCustomerDao = customerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListIndividualCustomerDto>> getAll() {
		
		var result = individualCustomerDao.findAll();
		
		List<ListIndividualCustomerDto> response = result.stream()
				.map(customer -> modelMapperService.forDto().map(customer, ListIndividualCustomerDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListIndividualCustomerDto>>(response);
	}

	@Override
	public DataResult<ListIndividualCustomerDto> getById(int id) {
		
		IndividualCustomer result = individualCustomerDao.getById(id);
		
		if(result == null) {
			return new ErrorDataResult<ListIndividualCustomerDto>("Car.NotFound");
		}
		
		ListIndividualCustomerDto response = modelMapperService.forDto().map(result, ListIndividualCustomerDto.class);		
		
		return new SuccessDataResult<ListIndividualCustomerDto>(response);
	}

	@Override
	public Result create(CreateIndividualCustomerRequest createCustomerRequest) throws BusinessException {
		
		IndividualCustomer customer = modelMapperService.forRequest().map(createCustomerRequest, IndividualCustomer.class);
		
		individualCustomerDao.save(customer);
		
		return new SuccessResult(Messages.CustomerAdded);
	}

}
