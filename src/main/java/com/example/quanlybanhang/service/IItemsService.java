package com.example.quanlybanhang.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.quanlybanhang.dto.InfoDTO;
import com.example.quanlybanhang.dto.ItemsDTO;


public interface IItemsService {

	List<ItemsDTO> findAll();
	
	ItemsDTO saveItems(ItemsDTO itemsDTO, InfoDTO infoDTO, MultipartFile multipartFile);
	
	ItemsDTO findById(Long id);
	
	void delete(long[] ids);
	
}
