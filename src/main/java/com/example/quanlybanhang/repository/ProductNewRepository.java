package com.example.quanlybanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quanlybanhang.entites.ProductNewEntity;

@Repository
public interface ProductNewRepository extends JpaRepository<ProductNewEntity, Long> {

	ProductNewEntity findOneById(Long id);
}
