package com.example.quanlybanhang.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity {
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "phonenumber")
	private Integer phoneNumber;
	
	@ManyToOne
	@JoinColumn(name = "itemid", nullable = false)
	private ItemsEntity item;
}
