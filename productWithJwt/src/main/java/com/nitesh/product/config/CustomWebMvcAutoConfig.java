package com.nitesh.product.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class CustomWebMvcAutoConfig implements WebMvcConfigurer {


    Path myExternalFilePath = Paths.get(System.getProperty("app.file.upload-dir", "uploads/")).toAbsolutePath();

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    	registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:"+myExternalFilePath.toString()+"/");
    }
    
}