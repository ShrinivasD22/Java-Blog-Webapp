package com.example.blogappapis2.exceptions;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyExistsException extends RuntimeException{

	String username;

	public UserAlreadyExistsException(String username) {
		super(String.format("user is already existed with username %s ", username));
		this.username = username;
	}
	
}
