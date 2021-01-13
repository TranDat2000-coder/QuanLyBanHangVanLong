package com.example.quanlybanhang.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "news")
public class NewsEntity extends BaseEntity {
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "thumbnail")
	private String thumbnail;
	
	@Column(name = "shortdescription", columnDefinition = "TEXT")
	private String shortDescription;
	
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
}
