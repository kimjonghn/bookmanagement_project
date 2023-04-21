package com.toyproject.bookmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) { //
		registry.addMapping("/**") //    /auth , /book
			.allowedMethods("*")	//
			.allowedMethods("*");	//
//			.allowedOrigins("http://localhost:3000");	//react와 연결
	}
}
