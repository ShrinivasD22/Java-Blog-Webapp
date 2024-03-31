package com.example.blogappapis2.config;



import org.springframework.beans.factory.annotation.Autowired;                

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.blogappapis2.security.CustomUserDetailService;
import com.example.blogappapis2.security.JwtAuthenticationEntrypoint;
import com.example.blogappapis2.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
    private JwtAuthenticationEntrypoint entryPoint;
	
    @Autowired
    private JwtAuthenticationFilter authFilter;
    
    @Autowired
    private CustomUserDetailService customUserDetailService;
    
    
    @Autowired
    public SecurityConfig(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }
    
    
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    } 

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	 http
         .csrf(AbstractHttpConfigurer::disable)          
         .authorizeHttpRequests(authorize -> authorize
                 .requestMatchers("/api/auth/**").permitAll()
                 .requestMatchers("/api/users/create").permitAll()
                 .anyRequest().authenticated()).exceptionHandling()
         .authenticationEntryPoint(this.entryPoint)
         .and()
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         
         http.addFilterBefore(this.authFilter, UsernamePasswordAuthenticationFilter.class);
         return http.build();
     }
    
    
  
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


	
}
