package com.ppk.topController.adminController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// Simple Equipment controller to handle requests from admin dashboard
@RestController
@RequestMapping("/admin")
public class EquipmentController {
    
    // Get all equipment (returns empty list for now)
    @GetMapping("/equipment")
    @ResponseBody
    public List<Object> getAllEquipment() {
        // Return empty list for now
        return new ArrayList<>();
    }
    
    // Get equipment by ID
    @GetMapping("/equipment/{id}")
    @ResponseBody
    public Object getEquipmentById(@PathVariable Long id) {
        // Return empty object for now
        return new Object();
    }
} 