package com.example.blogappapis2.payloads;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class JwtAuthRequest {

	
	private String username;
	private String password;
}
