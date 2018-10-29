package io.kosovo21.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Enable CORS and Resource(for Swagger) 
 * 
 * @author muhammad.ozon
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
		;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
	        .addResourceHandler("swagger-ui.html")
	        .addResourceLocations("classpath:/META-INF/resources/");
	    registry
	        .addResourceHandler("/webjars/**")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
