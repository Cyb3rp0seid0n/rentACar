package com.vmohan.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.business.abstracts.CarMaintenanceService;
import com.vmohan.rentACarProject.business.abstracts.RentalService;
import com.vmohan.rentACarProject.business.constants.Messages;
import com.vmohan.rentACarProject.business.dtos.carMaintenance.ListCarMaintenanceDto;
import com.vmohan.rentACarProject.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.vmohan.rentACarProject.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;

import com.vmohan.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.ErrorResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessDataResult;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;
import com.vmohan.rentACarProject.dataAccess.abstracts.CarMaintenanceDao;
import com.vmohan.rentACarProject.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService{
	
	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService,
			@Lazy RentalService rentalService) {
		
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
	}

	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		
		var result = this.carMaintenanceDao.findAll();
		
		List<ListCarMaintenanceDto> response = result.stream()
				.map(carMaintenance -> modelMapperService.forDto().map(carMaintenanceDao, ListCarMaintenanceDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response);
	}

	@Override
    public DataResult<List<ListCarMaintenanceDto>> getAllByCarId(int id) {
		
        List<CarMaintenance> carMaintenanceList = carMaintenanceDao.getAllByCarId(id);
        
        List<ListCarMaintenanceDto> response = carMaintenanceList.stream()
                .map(carMaintenance -> modelMapperService.forDto().map(carMaintenance, ListCarMaintenanceDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarMaintenanceDto>>(response);
    }
	@Override
	public Result create(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException{
		
		rentalService.isCarRented(createCarMaintenanceRequest.getCarId());
		isCarInMaintenance(createCarMaintenanceRequest.getCarId());
		
		CarMaintenance carMaintenance = modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
		carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.CarMaintenanceAdded);
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		
		
		CarMaintenance carMaintenance = modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
		carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.CarMaintenanceUpdated);
	}

	@Override
    public Result delete(int id) {
		
        if (carMaintenanceDao.existsById(id)) {
        	
        	carMaintenanceDao.deleteById(id);
            return new SuccessResult(Messages.CarMaintenanceDeleted);
            
        } else
            return new ErrorResult(Messages.notFound);
    }

	@Override
	public Result isCarInMaintenance(int carId) throws BusinessException {
		
		if(carMaintenanceDao.findByCarIdAndReturnDateIsNull(carId) != null)
			throw new BusinessException(Messages.CarIsInMaintenance);
		
		else
			return new SuccessResult();
	}
}
