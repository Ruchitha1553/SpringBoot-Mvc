package com.example.demo.model;

import org.springframework.web.bind.annotation.PostMapping;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
	
//	private String name;
//	private double price;
//	private int quantity;
//	private String brand;
//	private String madein;


	    @NotNull(message = "Name cannot be null")
	    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
	    private String name;

	    @Positive(message = "Price must be greater than zero")
	    private double price;

	    @Min(value = 0, message = "Quantity must be a positive integer or zero")
	    private int quantity;

	    @NotNull(message = "Brand cannot be null")
	    @Size(min = 1, max = 50, message = "Brand name must be between 1 and 50 characters")
	    private String brand;

	    @NotNull(message = "Madein cannot be null")
	    @Size(min = 2, max = 50, message = "Madein should be between 2 and 50 characters")
	    private String madein;

	   
	  
	    
	}


