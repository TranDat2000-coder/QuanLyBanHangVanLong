package com.example.quanlybanhang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoDTO {

	private Long id;
	
	private String company;
	
	private Float weight;
	
	private Integer romPhone;
	
	private Integer ramPhone;
	
	private Float screenSize;
	
	private String systemVersion;
	
	private String chipSet;

	private Integer pinPhone;
	
	private Long itemId;
	
	public InfoDTO(String company, Float weight, Integer romPhone, Integer ramPhone, Float screenSize, 
					String systemVersion, String chipSet, Integer pinPhone) {
		super();
		this.company = company;
		this.weight = weight;
		this.romPhone = romPhone;
		this.ramPhone = ramPhone;
		this.screenSize = screenSize;
		this.systemVersion = systemVersion;
		this.chipSet = chipSet;
		this.pinPhone = pinPhone;
	}
}
