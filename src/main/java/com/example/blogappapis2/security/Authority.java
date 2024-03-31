package com.example.blogappapis2.security;


import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{

	public Authority(String authority) {
		this.authority = authority;
	}

	private String authority;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}

