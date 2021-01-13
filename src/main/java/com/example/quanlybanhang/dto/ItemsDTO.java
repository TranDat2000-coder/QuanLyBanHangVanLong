package com.example.quanlybanhang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsDTO {

	private Long id;
	
	private String thumbNail;
	
	private String name;
	
	private Integer basePrice;
	
	private Integer salePrice;
	
	private String description;
	
	private Long infoId;
	
	private Long companyId;
	
	public ItemsDTO(String thumbNail, String name, Integer basePrice, Integer salePrice, String description) {
		super();
		this.thumbNail = thumbNail;
		this.name = name;
		this.basePrice = basePrice;
		this.salePrice = salePrice;
		this.description = description;
	}
}
