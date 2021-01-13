package com.example.quanlybanhang.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "banner")
public class BannerEntity extends BaseEntity {

	@Column(name = "code")
	private Integer code;
	
	@Column(name = "name",length = 100)
	private String name;
	
	@Column(name = "url")
	private String url;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
