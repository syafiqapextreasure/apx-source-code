package com.ppk.topController.homeController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// Handle root paths - both with and without context path
	@GetMapping({"/", "/eforms", "/eforms/"})
	public String getHomePage() {
		return "index";
	}
	
	@GetMapping("/home")
	public String getHomePageAlt() {
		return "index";
	}

	@GetMapping({"/signin", "/eforms/signin"})
	public String login() {
		return "login";
	}
	
	@GetMapping({"/login", "/eforms/login"})
	public String loginPage(@RequestParam(required = false) String redirectUrl, Model model) {
		if (redirectUrl != null && !redirectUrl.isEmpty()) {
			model.addAttribute("redirectUrl", redirectUrl);
		}
		return "login";
	}
	
	@GetMapping("/error")
	public String errorpage() {
		return "errorPage";
	}
	
	@GetMapping({"/register", "/eforms/register"})
	public String registerPage(@RequestParam(required = false) String redirectUrl, Model model) {
		if (redirectUrl != null && !redirectUrl.isEmpty()) {
			model.addAttribute("redirectUrl", redirectUrl);
		}
		return "register";
	}
	
	@PostMapping({"/saveUser", "/eforms/saveUser"})
	public String saveUser(@ModelAttribute UserDtls user, 
						  @RequestParam(value = "img", required = false) MultipartFile file,
						  @RequestParam(required = false) String redirectUrl,
						  HttpSession session) throws IOException {
		try {
			// Set default role
			user.setRole("ROLE_USER");
			
			// Encrypt password
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			// Handle profile image
			if (file != null && !file.isEmpty()) {
				String imageName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
				user.setProfileImage(imageName);
				
				// Create profiles directory if it doesn't exist
				File uploadsDir = new File("uploads");
				if (!uploadsDir.exists()) {
					uploadsDir.mkdir();
				}
				
				File profilesDir = new File("uploads/profiles");
				if (!profilesDir.exists()) {
					profilesDir.mkdir();
				}
				
				// Save image to directory
				Path path = Paths.get(profilesDir.getAbsolutePath() + File.separator + imageName);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			} else {
				user.setProfileImage("default.jpg");
			}
			
			// Save user to database
			// Implementation would go here - for now we'll just simulate success
			
			session.setAttribute("succMsg", "Registration successful! Please login.");
			
			// If redirect URL was provided, redirect back to login with that URL
			if (redirectUrl != null && !redirectUrl.isEmpty()) {
				return "redirect:/login?redirectUrl=" + redirectUrl;
			}
			
			return "redirect:/login";
			
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Something went wrong: " + e.getMessage());
			return "redirect:/register";
		}
	}
	
	@PostMapping({"/login", "/eforms/login"})
	public String processLogin(@RequestParam String username, 
	                          @RequestParam String password,
	                          @RequestParam(required = false) String redirectUrl,
	                          HttpSession session,
	                          Model model) {
	    // In a real application, you would authenticate the user here
	    // For testing purposes, we'll simulate success
	    session.setAttribute("user", username);
	    
	    if (redirectUrl != null && !redirectUrl.isEmpty()) {
	        // Fix double eforms issue by removing one /eforms prefix if there's duplication
	        if (redirectUrl.startsWith("/eforms/eforms/")) {
	            redirectUrl = redirectUrl.replace("/eforms/eforms/", "/eforms/");
	        }
	        return "redirect:" + redirectUrl;
	    }
	    
	    return "redirect:/";
	}
	
	// Simple user class for registration
	public static class UserDtls {
		private String name;
		private String email;
		private String password;
		private String address;
		private String city;
		private String state;
		private String pincode;
		private String mobileNumber;
		private String role;
		private String profileImage;
		
		// Getters and setters
		public String getName() { return name; }
		public void setName(String name) { this.name = name; }
		
		public String getEmail() { return email; }
		public void setEmail(String email) { this.email = email; }
		
		public String getPassword() { return password; }
		public void setPassword(String password) { this.password = password; }
		
		public String getAddress() { return address; }
		public void setAddress(String address) { this.address = address; }
		
		public String getCity() { return city; }
		public void setCity(String city) { this.city = city; }
		
		public String getState() { return state; }
		public void setState(String state) { this.state = state; }
		
		public String getPincode() { return pincode; }
		public void setPincode(String pincode) { this.pincode = pincode; }
		
		public String getMobileNumber() { return mobileNumber; }
		public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
		
		public String getRole() { return role; }
		public void setRole(String role) { this.role = role; }
		
		public String getProfileImage() { return profileImage; }
		public void setProfileImage(String profileImage) { this.profileImage = profileImage; }
	}
}
