package com.example.quanlybanhang.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.quanlybanhang.dto.InfoDTO;
import com.example.quanlybanhang.dto.ItemsDTO;
import com.example.quanlybanhang.entites.InfoEntity;
import com.example.quanlybanhang.entites.ItemsEntity;
import com.example.quanlybanhang.repository.InfoRepository;
import com.example.quanlybanhang.repository.ItemsRepository;
import com.example.quanlybanhang.service.IItemsService;

@Service
@Transactional
public class ItemsServiceImpl implements IItemsService {

	@Autowired
	private ItemsRepository itemsRepository;
	
	@Autowired
	private InfoRepository infoRepository;
	
	private static final String Root = "src/main/resources/images/";
	
	@Override
	public List<ItemsDTO> findAll(){
		List<ItemsEntity> itemsList = itemsRepository.findAll();
		List<ItemsDTO> itemsDTO = new ArrayList<>();
		for(ItemsEntity items : itemsList) {
			ItemsDTO itemList = new ItemsDTO();
			itemList.setId(items.getId());
			itemList.setThumbNail(items.getThumbNail());
			itemList.setName(items.getName());
			itemList.setBasePrice(items.getBasePrice());
			itemList.setSalePrice(items.getSalePrice());
			itemList.setDescription(items.getDescription());
			itemList.setInfoId(items.getInfo().getId());
			itemsDTO.add(itemList);
		}
		return itemsDTO;
	}
	

	@Override
	public ItemsDTO saveItems(ItemsDTO itemsDTO, InfoDTO infoDTO, MultipartFile multipartFile) {
		try {
			System.out.print(itemsDTO.getId());
			ItemsEntity itemsEntity = new ItemsEntity();
			InfoEntity infoEntity = infoRepository.findOneById(infoDTO.getId());
			if(itemsDTO.getId() !=  null) {
				itemsEntity = itemsRepository.findOneById(itemsDTO.getId());
				itemsEntity.setName(itemsDTO.getName());
				itemsEntity.setBasePrice(itemsDTO.getBasePrice());
				itemsEntity.setSalePrice(itemsDTO.getSalePrice());
				itemsEntity.setDescription(itemsDTO.getDescription());
				itemsEntity.setInfo(infoEntity);
			}else {
				String filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
				String rootPath = Root;
				Path path = Paths.get(rootPath + filename);
				itemsEntity.setThumbNail(filename);
				itemsEntity.setName(itemsDTO.getName());
				itemsEntity.setBasePrice(itemsDTO.getBasePrice());
				itemsEntity.setSalePrice(itemsDTO.getSalePrice());
				itemsEntity.setDescription(itemsDTO.getDescription());
				itemsEntity.setInfo(infoEntity);
				Files.copy(multipartFile.getInputStream(), path);
			}
			itemsRepository.save(itemsEntity);
			return new ItemsDTO(itemsEntity.getName(), 
					itemsEntity.getThumbNail(),
					itemsEntity.getBasePrice(),
					itemsEntity.getSalePrice(),
					itemsEntity.getDescription());
		} catch (Exception e) {
			return null;		
		}
	}
	
	@Override
	public ItemsDTO findById(Long id) {
		ItemsEntity entity = itemsRepository.findOneById(id);
		ItemsDTO dto = new ItemsDTO();
		dto.setId(entity.getId());
		dto.setThumbNail(entity.getThumbNail());
		dto.setName(entity.getName());
		dto.setBasePrice(entity.getBasePrice());
		dto.setSalePrice(entity.getSalePrice());
		dto.setDescription(entity.getDescription());
		return dto;
	}


	@Override
	public void delete(long[] ids) {
		for(long item : ids) {
			itemsRepository.deleteById(item);
		}
		
	}
}
