package com.example.quanlybanhang.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quanlybanhang.service.impl.InfoServiceImpl;
import com.example.quanlybanhang.service.impl.ItemsServiceImpl;

@Controller(value = "itemsAPIOfWeb")
public class ItemsAPI {
	
	@Autowired
	private ItemsServiceImpl itemsServiceImpl;
	
	@Autowired
	private InfoServiceImpl infoServiceImpl;

	@GetMapping(value = "/danh-sach-san-pham")
	private String listProduct(Model model) {
		model.addAttribute("itemslist", itemsServiceImpl.findAll());
		return "/itemslist";
	}
	
	@GetMapping(value = "/san-pham")
	private String showItem(Model model, @RequestParam(value = "id")Long id,
										@RequestParam(value = "infoid")Long infoId) {
		model.addAttribute("items", itemsServiceImpl.findById(id));
		model.addAttribute("info", infoServiceImpl.findById(infoId));
		return "/item";
	}
}
