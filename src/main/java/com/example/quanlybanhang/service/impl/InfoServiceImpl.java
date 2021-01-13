package com.example.quanlybanhang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quanlybanhang.converter.InfoConverter;
import com.example.quanlybanhang.dto.InfoDTO;
import com.example.quanlybanhang.entites.InfoEntity;
import com.example.quanlybanhang.repository.InfoRepository;
import com.example.quanlybanhang.service.IInfoService;

@Service
public class InfoServiceImpl implements IInfoService {

	@Autowired 
	private InfoRepository infoRepository;
	
	@Autowired
	private InfoConverter infoConverter;
	
	@Override
	public InfoDTO findById(Long id) {
		InfoEntity infoEntity = infoRepository.findOneById(id);
		return infoConverter.toDTO(infoEntity);
	}

	@Override
	public InfoDTO saveInfo(InfoDTO infoDTO) {
		try {
			InfoEntity infoEntity = new InfoEntity();
			if(infoDTO.getId() != null) {
				InfoEntity oldInfo = infoRepository.findOneById(infoDTO.getId());
				infoEntity = infoConverter.toEntity(infoDTO, oldInfo);
			}else {
				infoEntity = infoConverter.toEntity(infoDTO);
			}
			return infoConverter.toDTO(infoRepository.save(infoEntity));
		} catch (Exception e) {
			return null;
		}
	}


}
