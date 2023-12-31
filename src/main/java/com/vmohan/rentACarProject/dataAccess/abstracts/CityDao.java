package com.vmohan.rentACarProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmohan.rentACarProject.entities.concretes.City;

@Repository
public interface CityDao extends JpaRepository<City, Integer> {

}
