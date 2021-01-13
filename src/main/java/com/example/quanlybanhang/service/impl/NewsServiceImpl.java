package com.example.quanlybanhang.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.quanlybanhang.dto.NewsDTO;
import com.example.quanlybanhang.entites.NewsEntity;
import com.example.quanlybanhang.repository.NewsRepository;
import com.example.quanlybanhang.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository newsRepository;
	

	private static final String Root = "src/main/resources/images/";
	
	@Override
	public List<NewsDTO> findAll() {
		List<NewsEntity> newsList = newsRepository.findAll();
		List<NewsDTO> newsDTO = new ArrayList<>();
		for (NewsEntity news : newsList) {
			NewsDTO newlist = new NewsDTO();
				newlist.setId(news.getId());
				newlist.setContent(news.getContent());
				newlist.setThumbnail(news.getThumbnail());
				newlist.setShortDesciption(news.getShortDescription());
				newlist.setTitle(news.getTitle());
				newsDTO.add(newlist);
		}
		return newsDTO;
	}

	@Override
	public NewsDTO saveNews(NewsDTO newsDTO, MultipartFile multipartFile) {
		try {
			System.out.print(newsDTO.getId());
			NewsEntity newsEntity = new NewsEntity();
			if(newsDTO.getId() != null) {
				newsEntity = newsRepository.findOneById(newsDTO.getId());
				newsEntity.setContent(newsDTO.getContent());
				newsEntity.setTitle(newsDTO.getTitle());
				newsEntity.setShortDescription(newsDTO.getShortDesciption());
				//newsEntity.setThumbnail(filename);
			} else {
				String filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
				String rootPath = Root;
				Path path = Paths.get(rootPath + filename);
				newsEntity.setContent(newsDTO.getContent());
				newsEntity.setTitle(newsDTO.getTitle());
				newsEntity.setShortDescription(newsDTO.getShortDesciption());
				newsEntity.setThumbnail(filename);
				Files.copy(multipartFile.getInputStream(), path);
			}
			newsRepository.save(newsEntity);
			return new NewsDTO(newsEntity.getTitle(), 
							   newsEntity.getThumbnail(),
							   newsEntity.getShortDescription(),							  
							   newsEntity.getContent());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public NewsDTO findById(Long id) {
		NewsEntity entity = newsRepository.findOneById(id);
		NewsDTO dto = new NewsDTO();
		dto.setThumbnail(entity.getThumbnail());
		dto.setTitle(entity.getTitle());
		dto.setShortDesciption(entity.getShortDescription());
		dto.setContent(entity.getContent());
		return dto;
	}

	@Override
	public void delete(long[] ids) {
		for(long item : ids) {
			newsRepository.deleteById(item);
		}
		
	}
	
	
}
