package com.example.quanlybanhang.converter;

import org.springframework.stereotype.Component;

import com.example.quanlybanhang.dto.InfoDTO;
import com.example.quanlybanhang.entites.InfoEntity;

@Component
public class InfoConverter {

	public InfoDTO toDTO(InfoEntity entity) {
		InfoDTO dto = new InfoDTO();
		dto.setId(entity.getId());
		dto.setCompany(entity.getCompany());
		dto.setWeight(entity.getWeight());
		dto.setRomPhone(entity.getRomPhone());
		dto.setRamPhone(entity.getRamPhone());
		dto.setScreenSize(entity.getScreenSize());
		dto.setSystemVersion(entity.getSystemVersion());
		dto.setChipSet(entity.getChipSet());
		dto.setPinPhone(entity.getPinPhone());
		return dto;
	}
	
	public InfoEntity toEntity(InfoDTO dto) {
		InfoEntity entity = new InfoEntity();
		entity.setCompany(dto.getCompany());
		entity.setWeight(dto.getWeight());
		entity.setRomPhone(dto.getRomPhone());
		entity.setRamPhone(dto.getRamPhone());
		entity.setScreenSize(dto.getScreenSize());
		entity.setSystemVersion(dto.getSystemVersion());
		entity.setChipSet(dto.getChipSet());
		entity.setPinPhone(dto.getPinPhone());
		return entity;
	}
	
	public InfoEntity toEntity(InfoDTO dto, InfoEntity entity) {
		entity.setId(dto.getId());
		entity.setCompany(dto.getCompany());
		entity.setWeight(dto.getWeight());
		entity.setRomPhone(dto.getRomPhone());
		entity.setRamPhone(dto.getRamPhone());
		entity.setScreenSize(dto.getScreenSize());
		entity.setSystemVersion(dto.getSystemVersion());
		entity.setChipSet(dto.getChipSet());
		entity.setPinPhone(dto.getPinPhone());
		return entity;
	}
}
