package com.example.blogappapis2.services.serviceimpl;



import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blogappapis2.config.AppConstants;
import com.example.blogappapis2.entities.Role;
import com.example.blogappapis2.entities.User;
import com.example.blogappapis2.exceptions.ResourceNotFoundException;
import com.example.blogappapis2.exceptions.UserAlreadyExistsException;
import com.example.blogappapis2.payloads.UserDto;
import com.example.blogappapis2.repositories.RoleRepo;
import com.example.blogappapis2.repositories.UserRepo;
import com.example.blogappapis2.services.UserService;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
private UserRepo userRepo; 

	@Autowired
private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;	

//private AppConstant appconstant;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUser(userDto);
	    user.setPassword(encoder.encode(userDto.getPassword()));
		this.userRepo.save(user); 
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
			
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout()); 
		
		User updatedUser=this.userRepo.save(user);
		UserDto userDto1=this.userToDto(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));	
		this.userRepo.delete(user);
	} 
	
	
	private User dtoToUser(UserDto userDto) {
		User user= this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
	}
	
	public UserDto userToDto(User user) {
	    UserDto userDto = this.modelMapper.map(user, UserDto.class);
//	    userDto.setId(user.getId());
//	    userDto.setName(user.getName());
//	    userDto.setEmail(user.getEmail());
//	    userDto.setAbout(user.getAbout());
//	    userDto.setPassword(user.getPassword());
	    return userDto;
	}

		@Override
	public UserDto registerUser(UserDto userDto) {

		
		
		// Check if the user with the given email already exists
	    if (userRepo.existsByEmail(userDto.getEmail())) {
	        throw new UserAlreadyExistsException(userDto.getEmail());
	    }
	    
	    
		User user = this.dtoToUser(userDto); 
		
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		// roles
	
		Role role = this.roleRepo.getById(AppConstants.NORAMAL_USER);
		user.getRoles().add(role);
		
		User newUser = this.userRepo.save(user);
		return this.userToDto(newUser);
	}




	
}


