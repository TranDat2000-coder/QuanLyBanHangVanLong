package com.example.quanlybanhang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {
	
	private Long id;
	
	private String title;
	
	
	private String thumbnail;
	
	private long[] ids;
	
	
	private String shortDesciption;
	
	
	private String content;


	public NewsDTO(String title, String thumbnail, String shortDesciption, String content) {
		super();
		this.title = title;
		this.thumbnail = thumbnail;
		this.shortDesciption = shortDesciption;
		this.content = content;
	}
	
	
}
