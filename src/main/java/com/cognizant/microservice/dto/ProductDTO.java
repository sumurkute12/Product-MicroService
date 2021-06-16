package com.cognizant.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO 
{
	private int id;
	private String name;
	private double price;
	private String description;
	private String imageName;
	private float rating;
	private int userRated;
}
