package com.vmohan.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.business.abstracts.OrderedAdditionalServiceService;
import com.vmohan.rentACarProject.business.abstracts.RentalService;
import com.vmohan.rentACarProject.business.dtos.orderedAdditionalService.ListOrderedAdditionalServiceDto;
import com.vmohan.rentACarProject.business.requests.orderedAdditionalService.CreateOrderedAdditionalServiceRequest;
import com.vmohan.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessDataResult;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;
import com.vmohan.rentACarProject.dataAccess.abstracts.AdditionalServiceDao;
import com.vmohan.rentACarProject.entities.concretes.OrderedAdditionalService;
@Service
public class OrderedAdditionalServiceManager implements OrderedAdditionalServiceService{
	
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
		
	@Autowired
	public OrderedAdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService,
			@Lazy RentalService rentalService) {

		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
	}
	
	@Override
	public Result add(CreateOrderedAdditionalServiceRequest createAdditionalServiceRequest) {
		
		OrderedAdditionalService additionalService = modelMapperService.forRequest().map(createAdditionalServiceRequest, OrderedAdditionalService.class);
		additionalService.setId(0);
		
		additionalServiceDao.save(additionalService);
		
		return new SuccessResult("sss");
	}

	@Override
	public DataResult<List<ListOrderedAdditionalServiceDto>> findAllByRentalId(int rentalId) {
		
		List<OrderedAdditionalService> additionalServiceList = additionalServiceDao.findAllByRentalId(rentalId);
		List<ListOrderedAdditionalServiceDto> response = additionalServiceList.stream().map(
				additionalService -> modelMapperService.forDto().map(additionalService, ListOrderedAdditionalServiceDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListOrderedAdditionalServiceDto>>(response);
	}
	
	/*private Result checkIfRentalExists(int rentalId) {
		if (!rentalService.findBy(rentalId).isSuccess()) {
			return new ErrorResult(Messages.rentalIsNotFound);
		} else
			return new SuccessResult();
	}*/

	

}
