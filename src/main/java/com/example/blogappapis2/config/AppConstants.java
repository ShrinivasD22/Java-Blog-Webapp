package com.example.blogappapis2.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AppConstants {
    
	public static final String PAGE_NUMBER="0";
	public static final String PAGE_SIZE="10";
	public static final String SORT_BY="postId";
	public static final String SORT_DIR="asc"; 
	
	public final static int NORAMAL_USER = 501;
	public  final static int ADMIN_USER = 502;
	
}
