package com.codewithrocky.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithrocky.blog.entities.Category;
import com.codewithrocky.blog.entities.Post;
import com.codewithrocky.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {
    //custom queries
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
}
