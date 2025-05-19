package com.damda.ideaconcert.damda.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("images/**").addResourceLocations("file:images/");
        registry.addResourceHandler("upload/**").addResourceLocations("file:upload/");
        registry.addResourceHandler("license/**").addResourceLocations("file:license/");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*")
                .allowedOrigins("*","https://localhost:9000","",
                        "https://hidamda.com","https://www.hidamda.com","https://222.239.218.109:9000");
    }
}
