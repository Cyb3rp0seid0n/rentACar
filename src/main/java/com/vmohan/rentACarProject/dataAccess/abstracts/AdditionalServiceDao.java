package com.vmohan.rentACarProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmohan.rentACarProject.entities.concretes.OrderedAdditionalService;

@Repository
public interface AdditionalServiceDao extends JpaRepository<OrderedAdditionalService, Integer>{
	
	List<OrderedAdditionalService> findAllByRentalId(int RentalId);

}

