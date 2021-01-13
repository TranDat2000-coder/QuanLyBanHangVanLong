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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.quanlybanhang.dto.InfoDTO;
import com.example.quanlybanhang.dto.ItemsDTO;
import com.example.quanlybanhang.dto.NewsDTO;
import com.example.quanlybanhang.repository.InfoRepository;
import com.example.quanlybanhang.repository.ItemsRepository;
import com.example.quanlybanhang.service.IInfoService;
import com.example.quanlybanhang.service.IItemsService;


@Controller(value = "newAPIOfAmin")
public class ItemsAPI {
	
	@Autowired
	private IItemsService iItemsService;
	
	@Autowired
	private IInfoService iInfoService;
	
	@Autowired
	private ItemsRepository itemsRepository;
	
	@Autowired
	private InfoRepository infoRepository;
	
	@GetMapping(value = "/admin/danhsach/products")
	private String listProducts(Model model) {
		model.addAttribute("itemList", iItemsService.findAll());
		return "admin/itemslist";
	}
	
	@GetMapping(value = "/admin/products")
	private String products(Model model, @RequestParam(value = "id", required = false) Long id,
										 @RequestParam(value = "info", required = false) Long info) {
		ItemsDTO itemDTO = new ItemsDTO();
		InfoDTO infoDTO = new InfoDTO();
		if (id != null && info != null) {
			itemDTO = iItemsService.findById(id);
			infoDTO = iInfoService.findById(info);	
		}
		model.addAttribute("items", itemDTO);
		model.addAttribute("infoitems", infoDTO);
		return "admin/items";
	}
	
	@PostMapping(value = "/admin/products")
	private String products(@ModelAttribute ItemsDTO itemsDTO,
							@ModelAttribute InfoDTO infoDTO,
							@RequestParam(value = "id", required = false) Long id,
							@RequestParam(value = "info", required = false) Long info,
							@RequestParam(value = "file", required = false) MultipartFile file) {
		if (id != null && info != null ) {
			itemsDTO.setId(id);
			infoDTO.setId(info);
			iItemsService.saveItems(itemsDTO, infoDTO, file);
			return "redirect:/admin/danhsach/products?fsuccess";
		} else {
			InfoDTO infopost = iInfoService.saveInfo(infoDTO);
			infoDTO.setId(infopost.getId());
			iItemsService.saveItems(itemsDTO, infopost, file);
			return "redirect:/admin/danhsach/products?fsuccess";
		}
	}
	
	@PutMapping(value = "/admin/products/{id}")
	private String products(@RequestParam(value = "id", required = false) Long id,
							@RequestParam(value = "info", required = false) Long info,
							@RequestParam(value = "file", required = false) MultipartFile file,
							@ModelAttribute ItemsDTO itemsDTO, 
							@ModelAttribute InfoDTO infoDTO) {
		itemsDTO.setId(id);
		infoDTO.setId(info);
		iItemsService.saveItems(itemsDTO, infoDTO, file);
		return "redirect:/admin/danhsach/products?fsuccess";
	}
	
	@RequestMapping("/productDelete/{id}")
	public String deleteProduct(@PathVariable Long id, Model model) {
		itemsRepository.deleteById(id);
		model.addAttribute("itemlist", itemsRepository.findAll());
		return "redirect:/admin/danhsach/products?fsuccess";
	}
}
