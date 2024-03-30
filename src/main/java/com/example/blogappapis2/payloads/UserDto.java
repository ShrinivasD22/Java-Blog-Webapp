package com.example.blogappapis2.payloads;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter; 
import lombok.NoArgsConstructor;
import lombok.Setter; 

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
  
	private int id;
	
	
	
	@NotBlank
	private String name;
	
	@Email 
	private String email;
	
	
	@NotBlank
	@Size(min=3,max=10,message="Password must be min of 3 chars and max of 10 chars!!!")

	private String password; 
	
   
    @NotBlank
	private String about; 
	
}
