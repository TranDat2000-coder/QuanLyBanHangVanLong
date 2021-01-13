package com.example.quanlybanhang.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.quanlybanhang.dto.InfoDTO;
import com.example.quanlybanhang.dto.ProductNewDTO;
import com.example.quanlybanhang.repository.InfoRepository;
import com.example.quanlybanhang.repository.ProductNewRepository;
import com.example.quanlybanhang.service.IInfoService;
import com.example.quanlybanhang.service.IProductNewService;

@Controller
public class ProductNewAdminAPI {

	@Autowired
	private IProductNewService iProductNewService;
	
	@Autowired
	private IInfoService iInfoService;
	
	@Autowired
	private ProductNewRepository productNewRepository;
	
	@Autowired
	private InfoRepository infoRepository;
	
	@GetMapping(value = "/admin/danhsach/productnew")
	private String productsNew(Model model) {
		model.addAttribute("productList", iProductNewService.findAll());
		return "admin/productnew";
	}
	
	@GetMapping(value = "/admin/productnew")
	private String productsNew(Model model, @RequestParam(value = "id", required = false) Long id,
											@RequestParam(value = "info", required = false) Long info) {
		ProductNewDTO productNewDTO = new ProductNewDTO();
		InfoDTO infoDTO = new InfoDTO();
		if(id != null && info != null) {
			productNewDTO = iProductNewService.findById(id);
			infoDTO = iInfoService.findById(info);
		}
		model.addAttribute("product", productNewDTO);
		model.addAttribute("infoproduct", infoDTO);
		return "admin/product";
	}
	
	@PostMapping(value = "/admin/productnew")
	private String productsNew(@ModelAttribute ProductNewDTO productNewDTO,
								@ModelAttribute InfoDTO infoDTO,
								@RequestParam(value = "id", required = false) Long id,
								@RequestParam(value = "info", required = false) Long info,
								@RequestParam(value = "file", required = false) MultipartFile file) {
		if (id != null && info != null) {
			productNewDTO.setId(id);
			infoDTO.setId(info);
			iProductNewService.saveProduct(productNewDTO, infoDTO, file);
			return "redirect:/admin/danhsach/productnew?fsuccess";
		}else {
			InfoDTO infopost = iInfoService.saveInfo(infoDTO);
			infoDTO.setId(infopost.getId());
			iProductNewService.saveProduct(productNewDTO, infoDTO, file);
			return "redirect:/admin/danhsach/productnew?fsuccess";
		}
	}
	
	@PutMapping(value = "/admin/productnew/{id}")
	private String productsNew( @RequestParam(value = "id", required = false) Long id,
								@RequestParam(value = "info", required = false) Long info,
								@RequestParam(value = "file", required = false) MultipartFile file,
								@ModelAttribute ProductNewDTO productNewDTO,
								@ModelAttribute InfoDTO infoDTO) {
		productNewDTO.setId(id);
		infoDTO.setId(info);
		iProductNewService.saveProduct(productNewDTO, infoDTO, file);
		return "redirect:/admin/danhsach/productnew?fsuccess";
	}
	
	@RequestMapping("/product-Delete/{id}")
	public String deleteProduct(@PathVariable Long id, Model model) {
		productNewRepository.deleteById(id);
		model.addAttribute("productList", productNewRepository.findAll());
		return "redirect:/admin/danhsach/productnew?fsuccess";
	}
}
