package com.example.blogappapis2.payloads;





import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter

public class CommentDto {
   	
	@NotBlank
	private int id; 
	
	@NotBlank
	private String content; 
	
 
	
}
