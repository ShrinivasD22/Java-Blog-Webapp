package com.codewithrocky.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithrocky.blog.payloads.CategoryDto;
import com.codewithrocky.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
     
	@Autowired
	private CategoryService categoryService;
	
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto cateogDto)
	{
		CategoryDto createCategory = this.categoryService.createCategory(cateogDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	//update
	
	
	//delete
	
	
	//get
	
	
	//getall
}
