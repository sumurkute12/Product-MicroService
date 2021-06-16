package com.cognizant.microservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author Rishav,Amit,Siddhi,Swapnil
 *
 */
@Valid
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product 
{
	@Id
	private int id;
	private String name;
	private double price;
	private String description;
	@Column(name = "image_name")
	private String imageName;
//	@Range(min = 1,max = 5,message = "Rating should be between 1 to 5")
	private float rating;
	private int userRated;
}
