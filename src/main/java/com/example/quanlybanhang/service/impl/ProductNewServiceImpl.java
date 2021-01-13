package com.example.quanlybanhang.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.quanlybanhang.dto.InfoDTO;
import com.example.quanlybanhang.dto.ProductNewDTO;
import com.example.quanlybanhang.entites.InfoEntity;
import com.example.quanlybanhang.entites.ProductNewEntity;
import com.example.quanlybanhang.repository.InfoRepository;
import com.example.quanlybanhang.repository.ProductNewRepository;
import com.example.quanlybanhang.service.IProductNewService;

@Service
public class ProductNewServiceImpl implements IProductNewService {

	@Autowired
	private ProductNewRepository productNewRepository;
	
	@Autowired
	private InfoRepository infoRepository;
	
	private static final String Root = "src/main/resources/images/";
	
	@Override
	public List<ProductNewDTO> findAll() {
		List<ProductNewEntity> productNewEntity = productNewRepository.findAll();
		List<ProductNewDTO> productNewDTO = new ArrayList<>();
		for(ProductNewEntity product : productNewEntity) {
			ProductNewDTO producDTO = new ProductNewDTO();
			producDTO.setId(product.getId());
			producDTO.setPicture(product.getPicture());
			producDTO.setName(product.getName());
			producDTO.setBasePrice(product.getBasePrice());
			producDTO.setSalePrice(product.getSalePrice());
			producDTO.setDescription(product.getDescription());
			producDTO.setInfoId(product.getInfo().getId());
			productNewDTO.add(producDTO);
		}
		return productNewDTO;
	}
	
	@Override
	public ProductNewDTO saveProduct(ProductNewDTO productNewDTO, InfoDTO infoDTO, MultipartFile multipartFile) {
		try {
			System.out.print(productNewDTO.getId());
			ProductNewEntity productNewEntity = new ProductNewEntity();
			InfoEntity infoEntity = infoRepository.findOneById(infoDTO.getId());
			if(productNewDTO.getId() != null) {
				productNewEntity = productNewRepository.findOneById(productNewDTO.getId());
				productNewEntity.setName(productNewDTO.getName());
				productNewEntity.setBasePrice(productNewDTO.getBasePrice());
				productNewEntity.setSalePrice(productNewDTO.getSalePrice());
				productNewEntity.setDescription(productNewDTO.getDescription());
				productNewEntity.setInfo(infoEntity);
			}else {
				String filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
				String rootPath = Root;
				Path path = Paths.get(rootPath + filename);
				productNewEntity.setPicture(filename);
				productNewEntity.setName(productNewDTO.getName());
				productNewEntity.setBasePrice(productNewDTO.getBasePrice());
				productNewEntity.setSalePrice(productNewDTO.getSalePrice());
				productNewEntity.setDescription(productNewDTO.getDescription());
				productNewEntity.setInfo(infoEntity);
				Files.copy(multipartFile.getInputStream(), path);
			}
			productNewRepository.save(productNewEntity);
			return new ProductNewDTO(productNewEntity.getName(),
								productNewEntity.getPicture(),
								productNewEntity.getBasePrice(),
								productNewEntity.getSalePrice(),
								productNewEntity.getDescription());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ProductNewDTO findById(Long id) {
		ProductNewEntity entity = productNewRepository.findOneById(id);
		ProductNewDTO dto = new ProductNewDTO();
		dto.setId(entity.getId());
		dto.setPicture(entity.getPicture());
		dto.setName(entity.getName());
		dto.setBasePrice(entity.getBasePrice());
		dto.setSalePrice(entity.getSalePrice());
		dto.setDescription(entity.getDescription());
		return dto;
	}

	@Override
	public void delete(long[] ids) {
		for(long product : ids) {
			productNewRepository.deleteById(product);
		}
		
	}


}
