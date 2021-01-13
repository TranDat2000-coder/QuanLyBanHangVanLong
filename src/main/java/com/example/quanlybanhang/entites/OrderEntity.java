package com.example.quanlybanhang.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Data
@Table(name = "orderphone")
public class OrderEntity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -2576670215015463100L;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "fullname")
	private String fullName;
	
	@Column(name = "note", columnDefinition = "TEXT")
	private String note;
	
	@Column(name = "phonenumber")
	private Integer phoneNumber;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "amount")
	private Integer amount;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "order_num", nullable = false)
    private int orderNum;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "order_item", joinColumns = @JoinColumn(name = "orderid"), 
									inverseJoinColumns = @JoinColumn(name = "itemid"))
	private List<ItemsEntity> items = new ArrayList<>();
}
