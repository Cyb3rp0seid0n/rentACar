package com.vmohan.rentACarProject.entities.concretes;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "additional_service_items")
public class AdditionalServiceItem { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") 
	private int id; 
	
	@Column(name="name") 
	private String name;
	
	@Column(name="price") 
	private double price;
	
	@OneToMany(mappedBy = "additionalServiceItem")
	private List<OrderedAdditionalService> additionalServices;
	
	
	
}
