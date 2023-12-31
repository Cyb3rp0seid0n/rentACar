package com.vmohan.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.business.abstracts.CarService;
import com.vmohan.rentACarProject.business.constants.Messages;
import com.vmohan.rentACarProject.business.dtos.car.ListCarDto;
import com.vmohan.rentACarProject.business.requests.car.CreateCarRequest;
import com.vmohan.rentACarProject.business.requests.car.DeleteCarRequest;
import com.vmohan.rentACarProject.business.requests.car.UpdateCarRequest;
import com.vmohan.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.ErrorDataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessDataResult;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;
import com.vmohan.rentACarProject.dataAccess.abstracts.CarDao;
import com.vmohan.rentACarProject.entities.concretes.Car;

@Service
public class CarManager implements CarService {
	
	private CarDao carDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListCarDto>> getAll() {
		
		var result = carDao.findAll();
		
		List<ListCarDto> response = result.stream()
				.map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public Result create(CreateCarRequest createCarRequest) {
		
		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		carDao.save(car);
		
		return new SuccessResult(Messages.CarAdded);
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		
		Car car = modelMapperService.forRequest().map(deleteCarRequest, Car.class);
		carDao.delete(car);
		
		return new SuccessResult(Messages.CarDeleted);	
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		
		Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
		carDao.save(car);
		
		return new SuccessResult(Messages.CarUpdated);
	}

	@Override
	public DataResult<ListCarDto> getById(int id) {
		
		Car result = carDao.getCarById(id);
		
		if(result == null) {
			
			return new ErrorDataResult<ListCarDto>("Car.NotFound");
		}
		
		ListCarDto response = modelMapperService.forDto().map(result, ListCarDto.class);
		
		return new SuccessDataResult<ListCarDto>(response);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		List<Car> result = carDao.findAll(pageable).getContent();
		
		List<ListCarDto> response = result.stream()
				.map(car -> modelMapperService.forDto()
				.map(car, ListCarDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDto>>(response);
	}


	@Override
	public DataResult<List<ListCarDto>> getAllByDailyPriceLessThanEqual(double dailyPrice) {
		
		
		List<Car> result = carDao.getAllByDailyPriceLessThanEqual(dailyPrice);
		
		List<ListCarDto> response = result.stream()
				.map(car -> modelMapperService.forDto()
				.map(car, ListCarDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDto>>(response);
	}

	
	@Override
	public DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction) {
		
		Sort sort = Sort.by(direction, "dailyPrice");
		
		List<Car> result = carDao.findAll(sort);
		
		List<ListCarDto> response = result.stream()
				.map(car -> modelMapperService.forDto()
				.map(car, ListCarDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<>(response);
	}



}
