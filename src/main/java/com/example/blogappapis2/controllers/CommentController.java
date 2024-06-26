package com.example.blogappapis2.controllers;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogappapis2.entities.Comment;
import com.example.blogappapis2.payloads.ApiResponse;
import com.example.blogappapis2.payloads.CommentDto;
import com.example.blogappapis2.services.CommentService;



@RestController
@RequestMapping("/api/")
public class CommentController{
    
	@Autowired
	private CommentService commentservice; 
	
    @PostMapping("/posts/{postId}/comments")	
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable Integer postId)
	{ 
		CommentDto createComment = this.commentservice.createComment(comment, postId);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}  
    
    
    //delete comment
    @DeleteMapping("/comments/{commentId}")	
 	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
 	{ 
 	    this.commentservice.deleteComment(commentId); 
 		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully !!",true),HttpStatus.CREATED);
 	}
    
    
}
