package com.vmohan.rentACarProject.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "rent_date")
    private LocalDate rentDate;
    
    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "additional_price")
    private double additionalPrice;
    
    @Column(name = "initial_mileage")
    private Integer initialMileage;
    
    @Column(name = "return_mileage")
    private Integer returnMileage;
    
    @Column(name = "total_price")
    private double totalPrice;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer; 

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    
    @OneToMany(mappedBy = "rental")
	private List<OrderedAdditionalService> orderedAdditionalService;
    
    @ManyToOne
    @JoinColumn(name = "initial_city_id")
    private City initialCity;
    
    @ManyToOne
    @JoinColumn(name = "return_city_id")
    private City returnCity;
   
	@OneToMany(mappedBy = "rental")
	private List<Payment> payment;
    
}