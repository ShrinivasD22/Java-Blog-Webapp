package com.example.blogappapis2.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogappapis2.entities.Comment;



public interface CommentRepo extends JpaRepository<Comment,Integer> {

}

