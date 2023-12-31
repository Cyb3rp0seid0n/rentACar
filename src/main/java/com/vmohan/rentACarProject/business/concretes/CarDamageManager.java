package com.vmohan.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.business.abstracts.CarDamageService;
import com.vmohan.rentACarProject.business.dtos.carDamage.ListCarDamageDto;
import com.vmohan.rentACarProject.business.requests.carDamage.CreateCarDamageRequest;
import com.vmohan.rentACarProject.business.requests.carDamage.UpdateCarDamageRequest;
import com.vmohan.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessDataResult;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;
import com.vmohan.rentACarProject.dataAccess.abstracts.CarDamageDao;
import com.vmohan.rentACarProject.entities.concretes.CarDamage;

@Service
public class CarDamageManager implements CarDamageService {

	private CarDamageDao carDamageDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService) {
		
		this.carDamageDao = carDamageDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListCarDamageDto>> getAll() {

		var result = this.carDamageDao.findAll();

		List<ListCarDamageDto> response = result.stream()
				.map(carDamage -> modelMapperService.forDto().map(carDamage, ListCarDamageDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarDamageDto>>(response);
	}

	@Override
	public DataResult<List<ListCarDamageDto>> getAllByCarId(int id) {

		List<ListCarDamageDto> carDamageList = carDamageDao.getAllByCarId(id);

		List<ListCarDamageDto> response = carDamageList.stream()
				.map(carDamage -> modelMapperService.forDto().map(carDamage, ListCarDamageDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarDamageDto>>(response);
	}

	@Override
	public Result create(CreateCarDamageRequest createCarDamageRequest) {

		CarDamage carDamage = modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
		carDamageDao.save(carDamage);
		
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateCarDamageRequest updateCarDamageRequest) {

		CarDamage carDamage = modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
		carDamageDao.save(carDamage);
		
		return new SuccessResult();
	}

	@Override
	public Result delete(int id) {
		
		carDamageDao.deleteById(id);
		return new SuccessResult("CarMaintenance.Deleted");
	}

}
