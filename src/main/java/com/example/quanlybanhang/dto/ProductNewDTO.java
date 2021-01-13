package com.example.quanlybanhang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductNewDTO {

	private Long id;
	
	private String picture;
	
	private String name;
	
	private Integer basePrice;
	
	private Integer salePrice;
	
	private String description;
	
	private Long infoId;
	
	public ProductNewDTO(String picture, String name, Integer basePrice, Integer salePrice, String description) {
		super();
		this.picture = picture;
		this.name = name;
		this.basePrice = basePrice;
		this.salePrice = salePrice;
		this.description = description;
	}
}
