package com.ppk.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Define the root paths
        String uploadsDirPath = System.getProperty("user.dir") + "/uploads/";
        String roomImagesDirPath = System.getProperty("user.dir") + "/uploads/room-images/";
        
        // Log the absolute paths for debugging
        System.out.println("Uploads directory path: " + uploadsDirPath);
        System.out.println("Room images directory path: " + roomImagesDirPath);
        
        // Ensure the uploads directory exists
        try {
            Path uploadsDir = Paths.get("uploads");
            if (!Files.exists(uploadsDir)) {
                Files.createDirectories(uploadsDir);
                System.out.println("Created directory: " + uploadsDir.toAbsolutePath());
            }
            
            Path roomImagesDir = Paths.get("uploads/room-images");
            if (!Files.exists(roomImagesDir)) {
                Files.createDirectories(roomImagesDir);
                System.out.println("Created directory: " + roomImagesDir.toAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("Error creating uploads directories: " + e.getMessage());
        }
        
        // Map file paths for room images (use both relative and absolute for better compatibility)
        registry.addResourceHandler("/eforms/images/room-images/**")
                .addResourceLocations("file:uploads/room-images/", "file:" + roomImagesDirPath);
        
        // Backup mapping - directly map the room file pattern
        registry.addResourceHandler("/eforms/images/room-images/*.png")
                .addResourceLocations("file:uploads/room-images/", "file:" + roomImagesDirPath);
        
        // Map direct room filenames (without path prefix)
        registry.addResourceHandler("/*.png")
                .addResourceLocations("file:uploads/room-images/", "file:" + roomImagesDirPath);
        
        // Serve from static/images directory
        registry.addResourceHandler("/eforms/img/**")
                .addResourceLocations("classpath:/static/img/");
        
        // Map static resources for CSS, JS etc.
        registry.addResourceHandler("/eforms/**/*.css")
                .addResourceLocations("classpath:/static/");
                
        registry.addResourceHandler("/eforms/static/**")
                .addResourceLocations("classpath:/static/");
                
        // Add a default mapping for all static resources
        registry.addResourceHandler("/eforms/**")
                .addResourceLocations("classpath:/static/");
    }
} 