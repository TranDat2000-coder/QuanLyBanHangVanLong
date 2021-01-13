package com.example.quanlybanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quanlybanhang.entites.NewsEntity;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
	
	NewsEntity findOneById(Long id);
}
