package com.example.blogappapis2.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogappapis2.entities.Category;



public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
