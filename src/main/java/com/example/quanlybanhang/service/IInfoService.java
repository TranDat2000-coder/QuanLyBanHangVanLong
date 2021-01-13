package com.example.quanlybanhang.service;

import com.example.quanlybanhang.dto.InfoDTO;

public interface IInfoService {
	
	public InfoDTO findById(Long id);
	
	public InfoDTO saveInfo(InfoDTO infoDTO);
	
	
}
