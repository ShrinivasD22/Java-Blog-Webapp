package com.example.blogappapis2;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.blogappapis2.config.AppConstants;
import com.example.blogappapis2.entities.Role;
import com.example.blogappapis2.repositories.RoleRepo;


@EnableAutoConfiguration
@EnableAsync
@SpringBootApplication
public class BlogAppApis2Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApis2Application.class, args);
	}

	 
	
	private PasswordEncoder passwordEncoder;
	
	private RoleRepo roleRepo;

	    @Autowired
	    public BlogAppApis2Application(PasswordEncoder passwordEncoder,RoleRepo roleRepo) {
	        this.passwordEncoder = passwordEncoder;
	        this.roleRepo = roleRepo;
	    }
	    
	    @Bean
	    public ModelMapper modelMapper()
	    {
	    	return new ModelMapper(); 
	    }
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub  
		

//		System.out.println(this.passwordEncoder.encode("9168651535"));

		try {
			Role role1 = new Role();
			Role role2 = new Role();
			
			role1.setId(AppConstants.ADMIN_USER);
			role2.setName("ADMIN_USER");
			
			role2.setId(AppConstants.NORAMAL_USER);
			role2.setName("NORAMAL_USER");
			
			

			
			List<Role> roles = List.of(role1,role2);
			
			List<Role> result = this.roleRepo.saveAll(roles);
			result.forEach(r ->{
				System.out.println(r.getName());
			});
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}  
	
	
	@Bean
    public FilterRegistrationBean<CorsFilter> coresFilter() {
    	
    	
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	
    	CorsConfiguration corsConfiguration = new CorsConfiguration();
    	
    	corsConfiguration.setAllowCredentials(true);
    	corsConfiguration.addAllowedOriginPattern("*");
    	corsConfiguration.addAllowedHeader("Authorization");
    	corsConfiguration.addAllowedHeader("Content-type");
    	corsConfiguration.addAllowedHeader("Accept");
    	corsConfiguration.addAllowedHeader("POST");
    	corsConfiguration.addAllowedHeader("GET");
    	corsConfiguration.addAllowedHeader("DELETE");
    	corsConfiguration.addAllowedHeader("PUT");
    	corsConfiguration.addAllowedHeader("OPTIONS");
    	corsConfiguration.setMaxAge(3600L);
    	
    	source.registerCorsConfiguration("/**", corsConfiguration);
    	
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
		
    	return bean;
    }
	
	
	
	
}
