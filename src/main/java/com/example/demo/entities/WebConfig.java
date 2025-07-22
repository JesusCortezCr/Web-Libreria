package com.example.demo.entities;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Ajusta la ruta absoluta de acuerdo a tu sistema operativo
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
    
}
