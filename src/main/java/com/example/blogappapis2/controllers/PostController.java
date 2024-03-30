package com.example.blogappapis2.controllers;



import java.io.IOException;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.blogappapis2.config.AppConstants;
import com.example.blogappapis2.payloads.ApiResponse;
import com.example.blogappapis2.payloads.PostDto;
import com.example.blogappapis2.payloads.PostResponse;
import com.example.blogappapis2.services.FileService;
import com.example.blogappapis2.services.PostService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postservice; 
	
	@Autowired
	private FileService fileservice; 
	
	@Value("${project.image}")
	private String path;
	
	//create
	
 @PostMapping("/user/{userId}/category/{categoryId}/posts")
 public ResponseEntity<PostDto> createPost(
		 @RequestBody PostDto postDto,
		 @PathVariable Integer userId,
		 @PathVariable Integer categoryId	 
		 )
		 
		 
 {
	PostDto createPost= this.postservice.createPost(postDto,userId,categoryId);
	return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
 }
 
//get by user
 
 @GetMapping("/user/{userId}/posts")
 public ResponseEntity<List<PostDto>> getPostsByUser(
		 @PathVariable Integer userId
		 ){
	 List<PostDto> posts=this.postservice.getPostsByUser(userId);
	 
	 return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	 
 }
 
// get by category
 
 @GetMapping("/category/{categoryId}/posts")
 public ResponseEntity<List<PostDto>> getPostsByCategory(
		 @PathVariable Integer categoryId
		 ){
	 List<PostDto> posts=this.postservice.getPostsByCategory(categoryId);
	 
	 return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	 
 } 
 
 // get all posts 
 @GetMapping("/posts")
 public ResponseEntity<PostResponse> getAllPost(
		 @RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required =false)Integer pageNumber,
		 @RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize,
		 @RequestParam(value="sortBy",defaultValue =AppConstants.SORT_BY,required=false ) String sortBy,
		 @RequestParam(value="sortDir",defaultValue=AppConstants.SORT_DIR,required=false) String sortDir
		 ){ 
	 PostResponse postResponse = this.postservice.getAllPost(pageNumber, pageSize,sortBy,sortDir); 
	 return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
		 
 }
 
 // get post details by id   
 
 @GetMapping("/posts/{postId}")
 public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
 {
	 PostDto postDto=this.postservice.getPostById(postId);
	 return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	 
 }
 
 // delete post
 
 @DeleteMapping("/posts/{postId}")
 public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
	this.postservice.deletePost(postId);
	 return new ResponseEntity(new ApiResponse("post deleted successfully",true),HttpStatus.OK);
	 
 }
 
 //update post
 
 @PutMapping("/posts/{postId}")
 public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId ){
	 PostDto updatepost=this.postservice.updatePost(postDto,postId);
	 return new ResponseEntity<PostDto>(updatepost,HttpStatus.OK);
 }
 
 
 //search
 @GetMapping("/posts/search/{keywords}")
 
 //this repo ki findbytitle methode  me like ki query banegi
 
 public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
	List<PostDto> result=this.postservice.searchPosts(keywords);
	 
	 return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK); 	 
 }
 
 // post image upload
 
 @PostMapping("/posts/image/upload/{postId}")
 public ResponseEntity<PostDto> uploadPostImage( @RequestParam("image")MultipartFile image,
		 @PathVariable Integer postId ) throws IOException{
	 PostDto postDto=this.postservice.getPostById(postId);      
		String fileName=this.fileservice.uploadImage(path, image);
        // update this file in db for particular post
        postDto.setImageName(fileName);
        PostDto updatePost=this.postservice.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK); 
 }
 
 //methode to serve files
 
 @GetMapping(value= "posts/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
 public void downloadImage(
	@PathVariable("imageName") String imageName,
	HttpServletResponse response	 
		 ) throws IOException{
	 InputStream resource = this.fileservice.getResource(path, imageName);
	 response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	 StreamUtils.copy(resource,response.getOutputStream());
 }
 
 
 
}

