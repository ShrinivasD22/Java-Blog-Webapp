package com.example.blogappapis2.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogappapis2.entities.User;



public interface UserRepo extends JpaRepository<User,Integer> {

	

}
