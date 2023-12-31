package com.vmohan.rentACarProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmohan.rentACarProject.entities.concretes.CreditCardDetails;

@Repository
public interface CreditCardDetailsDao extends JpaRepository<CreditCardDetails, Integer> {

	List<CreditCardDetails> findAllByCustomerId(int customerId);
}
