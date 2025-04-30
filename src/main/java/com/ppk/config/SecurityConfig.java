//package com.ppk.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//	@Autowired
//	private AuthenticationSuccessHandler authenticationSuccessHandler;
//	
////	@Autowired
////	@Lazy
////	private AuthFailureHandlerImpl authenticationFailureHandler;
////	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
////	@Bean
////	public UserDetailsService userDetailsService() {
////		return new UserDetailsServiceImpl();
////	}
//
////	@Bean
////	public DaoAuthenticationProvider authenticationProvider() {
////		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
////		authenticationProvider.setUserDetailsService(userDetailsService());
////		authenticationProvider.setPasswordEncoder(passwordEncoder());
////		return authenticationProvider;
////	}
//
//	@Bean
//	public void filterChain(HttpSecurity http) throws Exception
//	{
//		
//		 http
//         .authorizeRequests() // Use this method instead of authorizeRequests()
//             .anyRequest().permitAll() // Allow all requests without authentication
//             .and()
//         .formLogin().disable() // Disable the default login form
//         .httpBasic().disable() // Disable HTTP Basic Authentication
//         .csrf().disable(); // Optionally disable CSRF protection (use with caution)
// }
//}
////		http.csrf(csrf->csrf.disable()).cors(cors->cors.disable())
//////				.authorizeHttpRequests(req->req.requestMatchers("/user/**").hasRole("USER")
////						.authorizeHttpRequests(req->req.antMatchers("/user/**").permitAll()
////				.antMatchers("/admin/**").hasRole("ADMIN")
////				.antMatchers("/error").permitAll()
////				.antMatchers("/**").permitAll())
////				.formLogin(form->form.loginPage("/register")
////						.loginProcessingUrl("/login")
//////						.defaultSuccessUrl("/")
////						.failureHandler(authenticationFailureHandler)
////						.successHandler(authenticationSuccessHandler))
////				.logout(logout->logout.permitAll());
////		
////		return http.build();
////	}
//	
////	 protected void configure(HttpSecurity http) throws Exception {
////	        http
////	            .authorizeRequests()
////	                .anyRequest().permitAll() // Allow all requests without authentication
////	                .and()
////	            .httpBasic().disable() // Disable HTTP Basic Authentication
////	            .formLogin().disable() // Disable the default login form
////	            .csrf().disable(); // Optionally disable CSRF protection (use with caution)
////	    }
//
////}
//
