package com.vmohan.rentACarProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmohan.rentACarProject.entities.concretes.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer>{
	
	Payment getAllByRental_Id(int rentalId); 
}
