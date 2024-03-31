package com.example.blogappapis2.services;



import java.util.List;

import com.example.blogappapis2.payloads.UserDto;



public interface UserService {
    
	UserDto registerUser(UserDto userDto);
	UserDto createUser(UserDto user); 
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers(); 
	
	void deleteUser(Integer userId);
	
}

//here UserDto is return type of this methods it means it returns the fields from UserDto//

