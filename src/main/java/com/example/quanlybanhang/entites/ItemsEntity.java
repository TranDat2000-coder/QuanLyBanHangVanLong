package com.example.quanlybanhang.entites;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import javassist.expr.NewArray;
import lombok.Data;

@Entity
@Data
@Table(name = "item")
public class ItemsEntity extends BaseEntity {
	
	@Column(name = "thumbnial")
	private String thumbNail;
	
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
	
	@ManyToMany(mappedBy = "items")
	private List<OrderEntity> orders = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "companyid")
	private CompanyEntity company;
	
	@OneToMany(mappedBy = "item")
	private List<CommentEntity> comments = new ArrayList<>();
	
}
