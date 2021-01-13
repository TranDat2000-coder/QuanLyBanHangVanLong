package com.example.quanlybanhang.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.quanlybanhang.dto.InfoDTO;
import com.example.quanlybanhang.dto.ProductNewDTO;

public interface IProductNewService {

	List<ProductNewDTO> findAll();
	
	ProductNewDTO saveProduct(ProductNewDTO productNewDTO, InfoDTO infoDTO, MultipartFile multipartFile);
	
	ProductNewDTO findById(Long id);
	
	void delete(long[] ids);
}
