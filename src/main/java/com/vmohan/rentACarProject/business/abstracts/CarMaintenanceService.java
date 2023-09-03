package com.vmohan.rentACarProject.business.abstracts;

import java.util.List;

import com.vmohan.rentACarProject.business.dtos.carMaintenance.ListCarMaintenanceDto;
import com.vmohan.rentACarProject.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.vmohan.rentACarProject.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.utilities.results.DataResult;
import com.vmohan.rentACarProject.core.utilities.results.Result;

public interface CarMaintenanceService {
	
	DataResult<List<ListCarMaintenanceDto>> getAll();
	DataResult<List<ListCarMaintenanceDto>> getAllByCarId(int id);
	Result create(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException;
	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);
	Result delete(int id);
	Result isCarInMaintenance(int carId) throws BusinessException;
	// DataResult<CarMaintenanceDto> getById(int carMaintenanceId);

}
