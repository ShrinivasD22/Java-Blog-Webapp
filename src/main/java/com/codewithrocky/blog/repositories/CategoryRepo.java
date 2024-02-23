package com.codewithrocky.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithrocky.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
