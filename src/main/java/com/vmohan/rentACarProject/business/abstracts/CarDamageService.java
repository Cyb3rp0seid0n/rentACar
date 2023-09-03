package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.carDamage.ListCarDamageDto;
import com.vmohan.rentACarProject.business.requests.carDamage.CreateCarDamageRequest;
import com.vmohan.rentACarProject.business.requests.carDamage.UpdateCarDamageRequest;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

public interface CarDamageService {

    DataResult<List<ListCarDamageDto>> getAll();
    DataResult<List<ListCarDamageDto>> getAllByCarId(int id);
    Result create(CreateCarDamageRequest createCarDamageRequest);
    Result update(UpdateCarDamageRequest updateCarDamageRequest);
    Result delete(int id);
}
