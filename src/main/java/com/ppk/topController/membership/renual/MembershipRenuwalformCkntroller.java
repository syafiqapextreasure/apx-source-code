package com.ppk.topController.membership.renual;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.jdbc.core.JdbcTemplate;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.ppk.topController.membership.renual.entity.RenewFee;
import com.ppk.topController.membership.renual.entity.RenewalMembershipData;
import com.ppk.topController.membership.renual.service.GetFndGlnumbService;
import com.ppk.topController.membership.renual.service.RenewFeeService;
import com.ppk.topEntity.formsEntity.registration.Encrypter;

@RestController
public class MembershipRenuwalformCkntroller {
	private static final Logger logger = LoggerFactory.getLogger(MembershipRenuwalformCkntroller.class);
	@Autowired
	private RenewFeeService renewFeeService;

	@Autowired
	GetFndGlnumbService getFndGlnumbService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/getRenewFee")
	public List<RenewFee> getRenewFee(@RequestParam String patronId,HttpServletRequest request) {
		User user = getLiferayUserDetails(request);
		logger.error(patronId+"---user is --"+user);
		logger.info(patronId+"---user is --"+user);
		
		// Get data from the service
		List<RenewFee> result = renewFeeService.getRenewalFeeByPatronId(patronId);
		
		// Check if result is empty and provide default values for testing
		if (result == null || result.isEmpty()) {
			// Create a dummy RenewFee object for testing
			result = new ArrayList<>();
			result.add(new RenewFee("1.00", "1"));
			logger.info("No fee data found for patron ID: " + patronId + ". Using default values.");
		}
		
		return result;
	}

	@GetMapping("/membership")
	public List<RenewalMembershipData> getMembershipData(@RequestParam String id,HttpServletRequest request) {
		User user=	getLiferayUserDetails(request);
		//logger.error(id+"---user is --"+user.getScreenName());
		logger.info(id+"---user is --"+user);
		return renewFeeService.getMembershipData(id);
	}

	@PostMapping("/handleRenewalMembership")
	public ResponseEntity<Map<String, Object>> handleRenewalMembership(
	        @RequestParam Map<String, String> params,
	        HttpServletRequest request, 
	        HttpServletResponse response) {
	    
	    Map<String, Object> responseMap = new HashMap<>();
	    
	    try {
	        // Extract parameters
	        String patronId = params.get("patronId");
	        String expDate = params.get("expDate");
	        String newExpDate = params.get("newExpDate");
	        String recordedBy = params.get("recordedBy");
	        String fee = params.get("fee");
	        String year = params.get("year");
	        
	        System.out.println("Form request for renewal " + patronId + " expDate " + expDate + 
	                           " newExpDate " + newExpDate + " recordedBy " + recordedBy + " fee " + fee);
	        
	        // For testing, we'll simulate a successful renewal
	        responseMap.put("message", "Success");
	        responseMap.put("patronId", patronId);
	        responseMap.put("newExpiryDate", newExpDate);
	        
	        // Add cookie for the session
	        Cookie cookie = new Cookie("patronId", patronId);
	        cookie.setMaxAge(60 * 60); // 1 hour
	        cookie.setPath("/");
	        response.addCookie(cookie);
	        
	        return ResponseEntity.ok(responseMap);
	        
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        responseMap.put("message", "Error");
	        responseMap.put("error", ex.getMessage());
	        return ResponseEntity.status(500).body(responseMap);
	    }
	}

	@GetMapping("/payment-status-test")
	public String testPaymentStatus(@RequestParam(required = false) String patronId, Model model) {
		if (patronId != null && !patronId.isEmpty()) {
			try {
				// Execute a test update and capture results
				String beforeStatus = getPatronStatusDescription(patronId);
				
				// Update the status
				boolean updateResult = getFndGlnumbService.updatePatrStatRenewal(patronId);
				
				// Get status after update
				String afterStatus = getPatronStatusDescription(patronId);
				
				// Add status info to model
				model.addAttribute("patronId", patronId);
				model.addAttribute("beforeStatus", beforeStatus);
				model.addAttribute("afterStatus", afterStatus);
				model.addAttribute("updateResult", updateResult);
				model.addAttribute("updateTime", new java.util.Date());
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
			}
		}
		
		return "membership/status-test";
	}

	private String getPatronStatusDescription(String patronId) {
		try {
			// This executes a query but only returns the result, doesn't modify anything
			String query = "SELECT GL08DESC FROM GLSTAT JOIN GLPATR ON GL08STAT = GL14STAT WHERE GL14PATR = ?";
			return jdbcTemplate.queryForObject(query, String.class, patronId);
		} catch (Exception e) {
			return "Unknown (Error: " + e.getMessage() + ")";
		}
	}

	public User getLiferayUserDetails(HttpServletRequest request) {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        if (themeDisplay != null) {
        	System.out.println("themeDisplay.getUser details who has loggedin "+themeDisplay.getUser());
            return themeDisplay.getUser();
        }

        return null;
    }
}
