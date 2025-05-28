package com.ppk.topController;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageController {

    /**
     * Serves room images from the uploads/room-images directory
     */
    @GetMapping({"/room-images/{filename:.+}", "/eforms/room-images/{filename:.+}"})
    @ResponseBody
    public ResponseEntity<Resource> serveRoomImage(@PathVariable String filename) {
        try {
            // Create the full path to the image file
            Path imagePath = Paths.get("uploads/room-images", filename);
            File imageFile = imagePath.toFile();
            
            // Check if file exists
            if (!imageFile.exists()) {
                // For debugging only
                System.out.println("Image not found: " + imagePath.toAbsolutePath());
                return ResponseEntity.notFound().build();
            }
            
            // Determine content type
            String contentType = Files.probeContentType(imagePath);
            if (contentType == null) {
                contentType = MediaType.IMAGE_JPEG_VALUE; // Default to JPEG
            }
            
            // Log that we're serving this image
            System.out.println("Serving image: " + imagePath.toAbsolutePath());
            
            // Return the file as a resource
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(new FileSystemResource(imageFile));
        } catch (IOException e) {
            System.err.println("Error serving image: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Serves images from the uploads/images directory
     */
    @GetMapping({"/images/{filename:.+}", "/eforms/images/{filename:.+}"})
    @ResponseBody
    public ResponseEntity<Resource> serveGeneralImage(@PathVariable String filename) {
        try {
            // Create the full path to the image file
            Path imagePath = Paths.get("uploads/images", filename);
            File imageFile = imagePath.toFile();
            
            // Check if file exists
            if (!imageFile.exists()) {
                // For debugging only
                System.out.println("Image not found: " + imagePath.toAbsolutePath());
                
                // Check if this is a room_p*.jpg image pattern - serve a default image instead
                if (filename.startsWith("room_p") && filename.endsWith(".jpg")) {
                    System.out.println("Using default placeholder image for: " + filename);
                    // Use a default room image from the static resources
                    Path defaultImagePath = Paths.get("src/main/resources/static/img/room2.jpg");
                    File defaultImageFile = defaultImagePath.toFile();
                    
                    if (defaultImageFile.exists()) {
                        return ResponseEntity.ok()
                               .contentType(MediaType.IMAGE_JPEG)
                               .body(new FileSystemResource(defaultImageFile));
                    }
                }
                return ResponseEntity.notFound().build();
            }
            
            // Determine content type
            String contentType = Files.probeContentType(imagePath);
            if (contentType == null) {
                contentType = MediaType.IMAGE_JPEG_VALUE; // Default to JPEG
            }
            
            // Return the file as a resource
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(new FileSystemResource(imageFile));
        } catch (IOException e) {
            System.err.println("Error serving image: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
} 