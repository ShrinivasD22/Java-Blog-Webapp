package com.example.blogappapis2.services;

import com.example.blogappapis2.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	void deleteComment(Integer commentId);
	
}

