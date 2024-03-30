package com.example.blogappapis2.services.serviceimpl;





import java.util.Date;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.blogappapis2.entities.Category;
import com.example.blogappapis2.entities.Post;
import com.example.blogappapis2.entities.User;
import com.example.blogappapis2.exceptions.ResourceNotFoundException;
import com.example.blogappapis2.payloads.PostDto;
import com.example.blogappapis2.payloads.PostResponse;
import com.example.blogappapis2.repositories.CategoryRepo;
import com.example.blogappapis2.repositories.PostRepo;
import com.example.blogappapis2.repositories.UserRepo;
import com.example.blogappapis2.services.PostService;




@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper; 
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		
		Post post=this.modelMapper.map(postDto,Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category); 
		
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
	post.setTitle(postDto.getTitle());
	post.setContent(postDto.getContent());
	post.setImageName(postDto.getImageName());
	
	Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class) ;
	}


	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir){
		
//		Sort sort=null;
//		if(sortDir.equalsIgnoreCase("asc")) {
//			sort=Sort.by(sortBy).ascending();
//		}else {
//			sort=Sort.by(sortBy).descending();
//		}
		
		
		//insted of if else condition we can use turnary operator here
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable p =PageRequest.of(pageNumber, pageSize,sort);
		
		
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allpost=pagePost.getContent();
		
		List<PostDto> postDtos = allpost.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		return this.modelMapper.map(post, PostDto.class);
	} 

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
	    // Retrieve the category from the repository
	    Category cat = this.categoryRepo.findById(categoryId)
	            .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

	    // Retrieve posts associated with the category
	    List<Post> posts = this.postRepo.findByCategory(cat);

	    // Map each Post entity to PostDto using ModelMapper
	    List<PostDto> postDtos = posts.stream()
	            .map(post -> this.modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());

	    return postDtos; 
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
	    // Retrieve the user from the repository
	    User user = this.userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

	    // Retrieve posts associated with the user
	    List<Post> posts = this.postRepo.findByUser(user);

	    // Map each Post entity to PostDto using ModelMapper
	    List<PostDto> postDtos = posts.stream()
	            .map(post -> this.modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());

	    return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts=this.postRepo.findByTitleContaining(keyword); 
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList()); 
		return postDtos;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub 
		Post post =this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
		this.postRepo.delete(post);
	}



}

