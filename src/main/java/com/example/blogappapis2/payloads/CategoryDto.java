package com.example.blogappapis2.payloads;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	
	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4,message="min size of message is 4")
	private String categoryTitle;
	
	@NotBlank
	private String categoryDescription;
	
	
}

