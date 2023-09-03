package com.vmohan.rentACarProject.entities.concretes;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ordered_additional_services")
public class OrderedAdditionalService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 	
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "additional_service_item_id") 
	private AdditionalServiceItem additionalServiceItem;	
	
	@ManyToOne
	@JoinColumn(name = "rental_id")  
	private Rental rental; 
	
}
