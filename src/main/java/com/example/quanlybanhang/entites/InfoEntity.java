package com.example.quanlybanhang.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "type")
public class InfoEntity extends BaseEntity {

	@Column(name = "chipset")
	private String chipSet;
	
	@Column(name = "company", length = 20)
	private String company;
	
	@Column(name = "pinphone")
	private Integer pinPhone;
	
	@Column(name = "ramphone")
	private Integer ramPhone;
	
	@Column(name = "romphone")
	private Integer romPhone;
	
	@Column(name = "scrennsize")
	private Float screenSize;
	
	@Column(name = "systemversion", length = 40)
	private String systemVersion;
	
	@Column(name = "weight")
	private Float weight;
	
	@OneToOne(mappedBy = "info")
	private ItemsEntity item;
	
	@OneToOne(mappedBy = "info")
	private ProductNewEntity productNewEntit;
}
