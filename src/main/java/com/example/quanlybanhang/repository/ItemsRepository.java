package com.example.quanlybanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quanlybanhang.entites.ItemsEntity;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsEntity, Long> {

	ItemsEntity findOneById(Long id);
}
