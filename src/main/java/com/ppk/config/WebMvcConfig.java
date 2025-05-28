package com.ppk.config;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            // Ensure directories exist
            Path uploadsDir = Paths.get("uploads");
            if (!Files.exists(uploadsDir)) {
                Files.createDirectories(uploadsDir);
                System.out.println("Created directory: " + uploadsDir.toAbsolutePath());
            }
            
            Path imagesDir = Paths.get("uploads/images");
            if (!Files.exists(imagesDir)) {
                Files.createDirectories(imagesDir);
                System.out.println("Created directory: " + imagesDir.toAbsolutePath());
            }
            
            Path roomImagesDir = Paths.get("uploads/room-images");
            if (!Files.exists(roomImagesDir)) {
                Files.createDirectories(roomImagesDir);
                System.out.println("Created directory: " + roomImagesDir.toAbsolutePath());
            }
            
            // Get absolute paths
            String uploadsDirAbs = uploadsDir.toAbsolutePath().toString();
            String imagesDirAbs = imagesDir.toAbsolutePath().toString();
            String roomImagesDirAbs = roomImagesDir.toAbsolutePath().toString();
            
            // Map without context path
            registry.addResourceHandler("/uploads/**")
                    .addResourceLocations("file:" + uploadsDirAbs + File.separator);
            
            registry.addResourceHandler("/images/**")
                    .addResourceLocations("file:" + imagesDirAbs + File.separator);
            
            registry.addResourceHandler("/room-images/**")
                    .addResourceLocations("file:" + roomImagesDirAbs + File.separator);
            
            // Map with context path
            registry.addResourceHandler("/eforms/uploads/**")
                    .addResourceLocations("file:" + uploadsDirAbs + File.separator);
            
            registry.addResourceHandler("/eforms/images/**")
                    .addResourceLocations("file:" + imagesDirAbs + File.separator);
            
            registry.addResourceHandler("/eforms/room-images/**")
                    .addResourceLocations("file:" + roomImagesDirAbs + File.separator);
            
            // Log the configured paths
            System.out.println("Uploads directory configured: " + uploadsDirAbs);
            System.out.println("Images directory configured: " + imagesDirAbs);
            System.out.println("Room images directory configured: " + roomImagesDirAbs);
            
        } catch (Exception e) {
            System.err.println("Error configuring resource handlers: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 