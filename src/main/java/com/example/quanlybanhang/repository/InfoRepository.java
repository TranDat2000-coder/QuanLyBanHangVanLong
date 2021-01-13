package com.example.quanlybanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quanlybanhang.entites.InfoEntity;

@Repository
public interface InfoRepository extends JpaRepository<InfoEntity, Long>{

	public InfoEntity findOneById(Long id);
}
