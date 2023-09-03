package com.vmohan.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.business.abstracts.CorporateCustomerService;
import com.vmohan.rentACarProject.business.constants.Messages;
import com.vmohan.rentACarProject.business.dtos.corporateCustomer.ListCorporateCustomerDto;
import com.vmohan.rentACarProject.business.requests.corporateCustomer.CreateCorporateCustomerRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.ErrorDataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessDataResult;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;
import com.vmohan.rentACarProject.dataAccess.abstracts.CorporateCustomerDao;
import com.vmohan.rentACarProject.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
		
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListCorporateCustomerDto>> getAll() {

		var result = corporateCustomerDao.findAll();
		
		List<ListCorporateCustomerDto> response = result.stream()
				.map(corporateCustomer -> modelMapperService.forDto().map(corporateCustomer, ListCorporateCustomerDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCorporateCustomerDto>>(response);
	}

	@Override
	public DataResult<ListCorporateCustomerDto> getById(int id) {

		CorporateCustomer result = corporateCustomerDao.getById(id);
		
		if(result == null) {
			return new ErrorDataResult<ListCorporateCustomerDto>("Car.NotFound");
		}
		ListCorporateCustomerDto response = modelMapperService.forDto().map(result, ListCorporateCustomerDto.class);		
		
		return new SuccessDataResult<ListCorporateCustomerDto>(response);
	}

	@Override
	public Result create(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException {
		
		CorporateCustomer customer = modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);

		corporateCustomerDao.save(customer);
		
		return new SuccessResult(Messages.CustomerAdded);
	}

}
