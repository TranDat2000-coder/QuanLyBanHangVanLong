package com.example.quanlybanhang.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.quanlybanhang.dto.NewsDTO;

public interface NewsService {
	
	List<NewsDTO> findAll();

	NewsDTO saveNews(NewsDTO newsDTO, MultipartFile multipartFile);
	
	NewsDTO findById(Long id);

	void delete(long[] ids);
}
