package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.vmohan.rentACarProject.business.dtos.car.ListCarDto;
import com.vmohan.rentACarProject.business.requests.car.CreateCarRequest;
import com.vmohan.rentACarProject.business.requests.car.DeleteCarRequest;
import com.vmohan.rentACarProject.business.requests.car.UpdateCarRequest;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

public interface CarService {

	Result create(CreateCarRequest createCarRequest);
	Result delete(DeleteCarRequest deleteCarRequest);
	Result update(UpdateCarRequest updateCarRequest);

	DataResult<List<ListCarDto>> getAll();
	DataResult<ListCarDto> getById(int id);
	DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize);
	DataResult<List<ListCarDto>> getAllByDailyPriceLessThanEqual(double dailyPrice);
	DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction);
}
