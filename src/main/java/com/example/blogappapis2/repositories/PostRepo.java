package com.example.blogappapis2.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogappapis2.entities.Category;
import com.example.blogappapis2.entities.Post;
import com.example.blogappapis2.entities.User; 



public interface PostRepo extends JpaRepository<Post,Integer> {
    //custom queries
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
}

