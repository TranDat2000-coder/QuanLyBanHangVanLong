package com.example.quanlybanhang.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quanlybanhang.service.NewsService;

@Controller(value = "newsAPIOfWeb")
public class NewsAPI {
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping(value = "/tin-tuc")
	private String listNews(Model model) {
		model.addAttribute("newlist", newsService.findAll());
		return "newlist";
	}
	
	@GetMapping(value = "/tin-tuc-one")
	private String news(Model model, @RequestParam(value = "id")Long id) {
		model.addAttribute("newlist", newsService.findById(id));
		return "/new";
	}
}
