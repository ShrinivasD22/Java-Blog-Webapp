package com.codewithrocky.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithrocky.blog.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {

	

}
