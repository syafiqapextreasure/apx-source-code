package com.ppk.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthSucessHandlerImpl implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// Check if a redirect URL was provided in the login form
		String redirectUrl = request.getParameter("redirectUrl");
		
		if (redirectUrl != null && !redirectUrl.isEmpty()) {
			// Fix double eforms issue by removing one /eforms prefix if there's duplication
			if (redirectUrl.startsWith("/eforms/eforms/")) {
				redirectUrl = redirectUrl.replace("/eforms/eforms/", "/eforms/");
			}
			// Redirect to the originally requested form page
			response.sendRedirect(redirectUrl);
			return;
		}
		
		// Default role-based redirection if no specific redirect URL
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Set<String> roles = AuthorityUtils.authorityListToSet(authorities);
		
		if(roles.contains("ROLE_ADMIN")) {
			response.sendRedirect("/eforms/admin/admin-dashboard");
		} else {
			response.sendRedirect("/eforms/all-form");
		}
	}
}
