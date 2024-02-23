package com.codewithrocky.blog.services;

import java.util.List;

import com.codewithrocky.blog.entities.User;
import com.codewithrocky.blog.payloads.UserDto;

public interface UserService {
    
	
	UserDto createUser(UserDto user); 
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers(); 
	
	void deleteUser(Integer userId);
	
}

//here UserDto is return type of this methods it means it returns the fields from UserDto//
