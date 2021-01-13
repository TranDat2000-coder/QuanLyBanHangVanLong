package com.example.quanlybanhang.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.quanlybanhang.dto.NewsDTO;
import com.example.quanlybanhang.repository.NewsRepository;
import com.example.quanlybanhang.service.NewsService;

@Controller
public class NewsAPI {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private NewsRepository newsRepository;
	
	@GetMapping(value = "/admin/danhsach/tin-tuc")
	private String listNews(Model model) {
		model.addAttribute("newlist", newsService.findAll());
		return "admin/newlist";
	}
	
	@GetMapping(value = "/admin/tin-tuc")
	private String news(Model model, @RequestParam(value = "id", required = false) Long id) {
		NewsDTO news = new NewsDTO();
		if (id != null) {
			news = newsService.findById(id);
		}
		model.addAttribute("news", news);
		return "admin/news";
	}
	
	@PostMapping(value = "/admin/tin-tuc")
	private String news(@ModelAttribute NewsDTO newsDTO, 
						@RequestParam(value = "file", required = false) MultipartFile file) {
		newsService.saveNews(newsDTO, file);
		return "redirect:/admin/danhsach/tin-tuc?success";
	}
	
	@PutMapping(value = "/admin/tin-tuc/{id}")
	private String news(@RequestParam(value = "id", required = false) Long id,
						@RequestParam(value = "file", required = false) MultipartFile file,
						@ModelAttribute NewsDTO newsDTO) {
		newsDTO.setId(id);
		newsService.saveNews(newsDTO, file);
		return "redirect:/admin/danhsach/tin-tuc?fsuccess";
	}
	
	@RequestMapping("/newDelete/{id}")
	public String deleteNews(@PathVariable Long id, Model model) {
		newsRepository.deleteById(id);
		model.addAttribute("newlist", newsRepository.findAll());
		return "redirect:/admin/danhsach/tin-tuc?fsuccess";
		
	}

}
