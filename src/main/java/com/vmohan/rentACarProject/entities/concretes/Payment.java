package com.vmohan.rentACarProject.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payments")

public class Payment {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "total_payment")
	private double totalPayment;
	
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	
	@ManyToOne
	@JoinColumn(name = "rental_id")	
	private Rental rental;
	
	//card bilgisi
	
}
