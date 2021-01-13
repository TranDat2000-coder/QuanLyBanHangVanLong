package com.example.quanlybanhang.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quanlybanhang.service.impl.InfoServiceImpl;
import com.example.quanlybanhang.service.impl.ProductNewServiceImpl;

@Controller
public class HomePage {
	
	@Autowired
	private ProductNewServiceImpl productNewServiceImpl;
	
	@Autowired
	private InfoServiceImpl infoServiceImpl;
	
	@GetMapping(value = "/trang-chu")
	private String homePages(Model model ) {
		model.addAttribute("productNew", productNewServiceImpl.findAll());
		return "/homepage";
	}
	
	@GetMapping(value = "/product-one")
	private String showProduct(Model model,
								@RequestParam(value = "id") Long id,
								@RequestParam(value = "infoid") Long infoid) {
		model.addAttribute("product", productNewServiceImpl.findById(id));
		model.addAttribute("info", infoServiceImpl.findById(infoid));
		return "/productnew";
	}
}
