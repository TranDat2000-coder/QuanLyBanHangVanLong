package com.example.quanlybanhang.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "product_new")
public class ProductNewEntity extends BaseEntity {
	
	@Column(name = "picture")
	private String picture;
	
	@Column(name = "name", length = 40)
	private String name;
	
	@Column(name = "baseprice")
	private Integer basePrice;
	
	@Column(name = "saleprice")
	private Integer salePrice;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name="code")
	private Integer code;
	
	@OneToOne
	@JoinColumn(name = "infoid")
	private InfoEntity info;
	
}
