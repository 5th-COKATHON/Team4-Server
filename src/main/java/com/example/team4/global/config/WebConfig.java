package com.example.team4.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
			.allowedHeaders("*")
			.allowedOrigins(
				"http://localhost:8080",
				"http://localhost:5173",
				"http://team4-front.s3-website.ap-northeast-2.amazonaws.com",
				"http://api.cotato-team4.kro.kr/"
			)
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
	}
}
