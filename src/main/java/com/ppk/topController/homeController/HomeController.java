//package com.ppk.topController.homeController;
//
//
//import java.io.File;
//
//
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
////import com.ppk.topServiceImpl.userServiceImpl.UserServiceImpl;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class HomeController {
////	@Autowired
////private UserServiceImpl  serviceImpl;
////	
//	@GetMapping("/")
//	public String getHomePage() {
//		return "index";
//	}
//
//	@GetMapping("/signin")
//	public String login() {
//		return "login";
//	}
//
//	@GetMapping("/register")
//	public String register() {
//		return "register";
//	}
//	
//	@GetMapping("/error")
//	public String errorpage() {
//		return "errorPage";
//	}
//	
//	
//	
//	
////	@PostMapping("/saveUser")
////	public String saveUser(@ModelAttribute com.ppk.topEntity.userEntity.UserDtls user, @RequestParam("img") MultipartFile file, HttpSession session)
////			throws IOException {
////
////		Boolean existsEmail = serviceImpl.existsEmail(user.getEmail());
////
////		if (existsEmail) {
////			session.setAttribute("errorMsg", "Email already exist");
////		} else {
////			String imageName = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
////			user.setProfileImage(imageName);
////			com.ppk.topEntity.userEntity.UserDtls saveUser = serviceImpl.saveUser(user);
////
////			if (!ObjectUtils.isEmpty(saveUser)) {
////				if (!file.isEmpty()) {
////					File saveFile = new ClassPathResource("static/img").getFile();
////
////					Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "profile_img" + File.separator
////							+ file.getOriginalFilename());
////
//////					System.out.println(path);
////					Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
////				}
////				session.setAttribute("succMsg", "Register successfully");
////			} else {
////				session.setAttribute("errorMsg", "something wrong on server");
////			}
////		}
////
////		return "redirect:/register";
////	}
//}
