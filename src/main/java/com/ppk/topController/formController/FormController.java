package com.ppk.topController.formController;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.ppk.lost.material.model.CirBahanRosakHilangtblPinjaman;
import com.ppk.topController.lost.LostMaterial.service.CirBahanRosakHilangService;
import com.ppk.topController.membership.renual.entity.RenewalMembershipData;
import com.ppk.topController.membership.renual.service.GetFndGlnumbService;
import com.ppk.topController.membership.renual.service.RenewFeeService;
import com.ppk.topEntity.APITokenResponse;
import com.ppk.topEntity.PaymentAccessPayload;
import com.ppk.topEntity.errorEntity.ReportCreationException;
import com.ppk.topEntity.errorEntity.UserNotFoundException;
import com.ppk.topEntity.formsEntity.BorrowDetails;
import com.ppk.topEntity.formsEntity.DependentDetails;
import com.ppk.topEntity.formsEntity.GetFndBranch;
import com.ppk.topEntity.formsEntity.LostBorrowedMaterial;
import com.ppk.topEntity.formsEntity.LostDamagedReport;
import com.ppk.topEntity.formsEntity.LostMaterialForm;
import com.ppk.topEntity.formsEntity.MaterialProcurementProposalForm;
import com.ppk.topEntity.formsEntity.dto.Dependent;
import com.ppk.topEntity.formsEntity.dto.DropDownValueDto;
import com.ppk.topEntity.formsEntity.dto.EquipmentForm;
import com.ppk.topEntity.roomEntity.BookingSummary;
import com.ppk.topEntity.roomEntity.EquipmentDetails;
import com.ppk.topEntity.roomEntity.PersonalInformation;
import com.ppk.topEntity.roomEntity.RoomBookingDetails;
import com.ppk.topEntity.roomEntity.RoomReservationFormEntity;
import com.ppk.topService.formService.ppkMembershipRegistrationForm.RegistrationService;
import com.ppk.topServiceImpl.formServiceImpl.LostDamagedReportServiceImpl;
import com.ppk.topServiceImpl.formServiceImpl.MaterialProcurementProposalFormServiceImpl;
import com.ppk.topServiceImpl.formServiceImpl.RoomReservationService;
import com.ppk.topServiceImpl.formServiceImpl.roomServiceImpl.RoomServiceImpl;
import com.ppk.topServiceImpl.userServiceImpl.PpkMembershipRegistrationFormImpl;
import com.ppk.utilities.LogUtil;
import com.ppk.utilities.Utilities;
import com.ppk.topServiceImpl.emailServiceImpl.EmailService;
 
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Period;

@Controller
public class FormController {

	private static Logger logger = LogUtil.getLogger(FormController.class);

	@Autowired
	private MaterialProcurementProposalFormServiceImpl materialProcurementProposalFormServiceImpl;

	@Autowired
	private LostDamagedReportServiceImpl lostDamagedReportService;

	@Autowired
	private PpkMembershipRegistrationFormImpl membershipRegistrationFormImpl;

	@Autowired
	private RoomServiceImpl roomReservationService;

	@Autowired
	private LostDamagedReportServiceImpl damagedReportServiceImpl;
	@Autowired
	private RoomServiceImpl roomServiceImpl;

	@Autowired
	private RenewFeeService renewFeeService;

	@Autowired
	private CirBahanRosakHilangService service;
	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private com.ppk.topService.DependentService dependentService;

	@Autowired
	GetFndGlnumbService getFndGlnumbService;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private com.ppk.topService.formService.roomService.RoomBookingTransactionService roomBookingTransactionService;

	@Autowired
	private EmailService emailService;

	// for drop down data membership registration form
	@GetMapping("/location")
	public String getLocationWithCode(org.springframework.ui.Model model) {
		logger.info("Received request from getLocation() Controller");
		try {
			List<GetFndBranch> getFndBranchs = membershipRegistrationFormImpl.getFndBranchCodeandDesc();
			if (getFndBranchs == null || getFndBranchs.isEmpty()) {
				logger.warn("No branch locations found");
			} else {
				logger.info("Successfully retrieved {} branch locations", getFndBranchs.size());
			}
			model.addAttribute("branchLocations", getFndBranchs);
			return "branchLocations";
		} catch (Exception e) {
			logger.error("Error retrieving branch locations: {}", e.getMessage(), e);
			model.addAttribute("errorMessage", "An error occurred while retrieving branch locations.");
			return "error";
		}
	}

	// This form is used to save Material Proposal Form
//	@PostMapping("/save")
//	public String saveFormToDb(@ModelAttribute MaterialProcurementProposalForm formData,
//			RedirectAttributes redirectAttributes, org.springframework.ui.Model model) {
//		logger.info("Received request to save proposal form: {}", formData);
//		try {
//			System.err.println(formData);
//			MaterialProcurementProposalForm savedData = this.materialProcurementProposalFormServiceImpl
//					.saveMaterialProposalForm(formData);
//			logger.info("Proposal form saved successfully with ID: {}", savedData.getId());
//
//			// Add success message
//			redirectAttributes.addFlashAttribute("successMessage", "Data saved successfully!");
//			return "redirect:/user/recommendation-form"; // Redirect to the same form page
//		} catch (Exception e) {
//			logger.error("Error saving proposal form: {}", e.getMessage(), e);
//			model.addAttribute("errorMessage", "An error occurred while saving the proposal form. Please try again.");
//			return "redirect:/user/recommendation-form"; // Return the same form page with an error
//		}
//	}

	@PostMapping("/save")
	public String saveFormToDb(@ModelAttribute MaterialProcurementProposalForm formData,
			RedirectAttributes redirectAttributes, org.springframework.ui.Model model) {
		try {
			MaterialProcurementProposalForm savedData = materialProcurementProposalFormServiceImpl
					.saveMaterialProposalForm(formData);
			
			// Send email notification to staff
			boolean emailSent = emailService.sendMaterialSuggestionNotification(savedData);
			
			// Log the result of email sending
			if (emailSent) {
				logger.info("Email notification sent successfully for material suggestion ID: {}", savedData.getId());
			} else {
				logger.warn("Failed to send email notification for material suggestion ID: {}", savedData.getId());
			}

			// Add a success message using flash attributes
			redirectAttributes.addFlashAttribute("successMessage", "Data saved successfully!");
			return "redirect:/recommendation-form"; // Redirect to reload the form

		} catch (Exception e) {
			// Log the error
			logger.error("Error saving material suggestion form: {}", e.getMessage(), e);
			
			// Add an error message to the model
			model.addAttribute("errorMessage",
					"An unexpected error occurred while saving the proposal form. Please try again.");
			return "user/form/recommendationf"; // Return to the form with an error message
		}
	}

	// This endpoint retrieves material procurement proposal data by email ID
	@GetMapping("/getFormDataByEmail/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
		try {
			logger.info("Request received to fetch data for email: {}", email);

			MaterialProcurementProposalForm user = materialProcurementProposalFormServiceImpl.getDataByEmailId(email);

			logger.info("Data successfully retrieved for email: {}", email);
			return ResponseEntity.ok(user);

		} catch (UserNotFoundException ex) {
			logger.warn("User not found for email: {}", email);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for email: {}" + email);

		} catch (Exception ex) {
			logger.error("An unexpected error occurred while retrieving data for email: {}. Error: {}", email,
					ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An unexpected error occurred. Please try again later.");
		}
	}

	// LOST MATERIAL COMPLAINT FORM / BORANG ADUAN BAHAN HILANG

//	@GetMapping("/listBorrowedMaterial") public ResponseEntity<List<LostBorrowedMaterial>> showAllBorrowedMaterialsLost() {
//		try { 
//			List<LostBorrowedMaterial> borrowedMaterials = damagedReportServiceImpl.getBorrowedMaterials(); 
//			if (borrowedMaterials.isEmpty()) { 
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
//				} return new ResponseEntity<>(borrowedMaterials, HttpStatus.OK); 
//				} catch (Exception e) { 
//					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
//					} 
//		}

	@GetMapping("/listBorrowedMaterial")
	public String showAllBorrowedMaterialsLost(org.springframework.ui.Model model) {
		List<LostBorrowedMaterial> borrowedMaterials = damagedReportServiceImpl.getBorrowedMaterials();

		model.addAttribute("borrowedMaterials", borrowedMaterials);
		return "user/form/lostmf";
	}

	// All form UI started hear
//	@GetMapping("/lost-material-form")
//	public String getLostFormUI(org.springframework.ui.Model model) {
//		model.addAttribute("form", new LostDamagedReport());
//		List<LostBorrowedMaterial> borrowedMaterials = damagedReportServiceImpl.getBorrowedMaterials();
//		System.err.println(borrowedMaterials);
//		model.addAttribute("borrowedMaterials", borrowedMaterials);
//		return "user/form/lostmf";
//	}

	@GetMapping("/lost-material-form")
	public String getLostJustRederUI(org.springframework.ui.Model model) throws SQLException, InterruptedException {

		List<LostBorrowedMaterial> borrowedMaterials = damagedReportServiceImpl.getBorrowedMaterials();
		model.addAttribute("borrowedMaterials", borrowedMaterials);
		return "user/form/lostmf";
	}

	// Handle form submission
	@PostMapping("/submit")
	public String submitForm(@RequestParam String patronId, @RequestParam String[] acquisitionNumbers, // Array for
																										// multiple
																										// checkboxes
			@RequestParam String borrowDate, @RequestParam String returnDate, @RequestParam String paymentMethod,
			@RequestParam double totalPayment, RedirectAttributes redirectAttributes) {

		try {
			// Save each selected material
			for (String acquisitionNumber : acquisitionNumbers) {
				LostMaterialForm form = new LostMaterialForm();
				form.setPatronId(patronId);
				form.setAcquisitionNumber(acquisitionNumber);
				form.setBorrowDate(borrowDate);
				form.setReturnDate(returnDate);
				form.setPaymentMethod(paymentMethod);
				form.setTotalPayment(totalPayment);

				damagedReportServiceImpl.saveFormData(form);
			}

			redirectAttributes.addFlashAttribute("message", "Form submitted successfully!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Error submitting form: " + e.getMessage());
		}

		return "redirect:/lost-material-form"; // Redirect to the form page
	}

	@GetMapping("/lost-material-form-service")
	@ResponseBody
	public List<CirBahanRosakHilangtblPinjaman> getLostFormUI(org.springframework.ui.Model model,
			@RequestParam String idlogin) throws SQLException, InterruptedException {
		List<CirBahanRosakHilangtblPinjaman> list = service.getBahanRosakHilangByPatronId(idlogin);
		// model.addAttribute("borrowedMaterials", list);
		return list;
	}

	@PostMapping("/submitlostForm")
	public String savedBorrowDetails(@ModelAttribute BorrowDetails borrowDetails) {
		System.out.println(new Gson().toJson(borrowDetails));
		System.err.println(borrowDetails);
		return "user/form/lostmf";
	}

//	@PostMapping("/lost-save")
//	public String saveBorrowDetails(@ModelAttribute BorrowDetails borrowDetails) {
//	    try {
//	        // Validate the input
////	        validateBorrowDetails(borrowDetails);
//
//	        // Calculate the total price
//	        double totalPrice = lostDamagedReportService.calculateTotalPrice(borrowDetails);
//	        System.out.println("Calculated Total Price: RM " + totalPrice);
//
//	        // Save the details (placeholder for actual save logic)
//	        // borrowDetailsRepository.save(borrowDetails);
//
//	        // Set total price in borrow details for display or further processing
//	        borrowDetails.setPaymentAmount(totalPrice);
//	        System.out.println("Borrow details saved successfully: " + borrowDetails);
//
//	        // Return view
//	        return "user/form/lostmf";
//	    } catch (Exception e) {
//	        System.err.println("Error processing borrow details: " + e.getMessage());
//	        return "error";
//	    }
//	}

	@PostMapping("/lost-save")
	@ResponseBody // This will ensure a JSON response is sent back
	public Map<String, Object> saveLostMaterialForm(@ModelAttribute BorrowDetails borrowDetails) {

		BorrowDetails savedBorrowDetails = lostDamagedReportService.saveBorrowDetails(borrowDetails);

		Map<String, Object> response = new HashMap<>();
		response.put("paymentAmount", savedBorrowDetails.getPaymentAmount());
		response.put("priceDetails", savedBorrowDetails.getPriceDetails());

		return response;
	}

//	private double calculateTotalPrice(BorrowDetails borrowDetails) {
//	    double totalPrice = 0.0;
//
//	    for (LostBorrowedMaterial book : borrowDetails.getBooks()) {
//	        double lateFee = calculateLateFee(borrowDetails.getBorrowDate(), borrowDetails.getReturnDate());
//	        totalPrice += book.getBookPrice() + lateFee + 10; // Add RM10 processing fee
//	    }
//
//	    return totalPrice;
//	}

	@GetMapping("/recommendation-form")
	public String getRecommendationForm() {
		return "user/form/recommendationf";
	}

	@GetMapping("/membership-registration")
	public String getMembershipRegistration(org.springframework.ui.Model model) {
		List<GetFndBranch> getFndBranchs = membershipRegistrationFormImpl.getFndBranchCodeandDesc();
		List<DropDownValueDto> getDropValue = membershipRegistrationFormImpl.getDropValueForRegistration();
		model.addAttribute("dropValue", getDropValue);

		model.addAttribute("branchLocations", getFndBranchs); // Ensure attribute is set correctly
		return "user/form/membership_registration";
	}

	@GetMapping({"/membership-renewal-service", "/eforms/membership-renewal-service"})
	public String getMembershipRenewalService(Model model, HttpServletRequest request) {
		String petronId = request.getParameter("username");
		String demoMode = request.getParameter("demo");
		LocalDate expDate = null;
		List<com.ppk.topController.membership.renual.entity.RenewalMembershipData> dbData = renewFeeService
				.getMembershipData(petronId);
		// User user = getLiferayUserDetails(request);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		// NOTE: This code uses mock data for testing purposes when database returns no results
		// The mock data will be displayed in the form but is not connected to any production database
		// In a production environment, this would be replaced with actual data from the membership database

		// Ensure dbData is not null or empty, create an instance manually if needed
		RenewalMembershipData membershipData = new RenewalMembershipData();
		
		// For testing: Check if dbData is empty and create dummy data
		if (dbData == null || dbData.isEmpty()) {
			// Determine if we should use a "demo" mode with variable data for presentation
			boolean isDemo = "true".equalsIgnoreCase(demoMode);
			
			// Create base dummy data
			membershipData.setPatronID(petronId != null ? petronId : "default");
			membershipData.setName("Test User");
			
			// Vary data based on demonstration mode
			if (isDemo) {
				// Create random expiry date to demonstrate "about to expire" scenario
				LocalDate today = LocalDate.now();
				LocalDate randomEnrollDate = today.minusYears(1 + (int)(Math.random() * 3));
				LocalDate randomExpireDate = today.plusDays((int)(Math.random() * 60) - 30); // -30 to +30 days from today
				
				// Format dates according to the expected format
				String enrollDateStr = randomEnrollDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
				String expireDateStr = randomExpireDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
				
				// Set random data for demo
				membershipData.setDateEnrolled(enrollDateStr);
				membershipData.setExpiryDate(expireDateStr);
				membershipData.setLastReturnDate(today.minusDays((int)(Math.random() * 30)).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
				
				// Randomize status based on expiry date
				if (randomExpireDate.isBefore(today)) {
					membershipData.setStatus("Expired");
				} else if (randomExpireDate.isBefore(today.plusDays(15))) {
					membershipData.setStatus("About to Expire");
				} else {
					membershipData.setStatus("Active");
				}
				
				// Formatted dates for display
				model.addAttribute("formattedDateEnrolled", randomEnrollDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				model.addAttribute("expireDate", randomExpireDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				model.addAttribute("returnDate", today.minusDays((int)(Math.random() * 30)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				
				// Add demo badge
				model.addAttribute("isDemo", true);
				model.addAttribute("demoType", (int)(Math.random() * 3)); // 0, 1, or 2 for different demo scenarios
			} else {
				// Standard fixed testing data
				membershipData.setDateEnrolled("20000101");
				membershipData.setExpiryDate("20990101");
				membershipData.setLastReturnDate("20990101");
				membershipData.setStatus("Active");
				
				// Add the dummy data to model
				model.addAttribute("formattedDateEnrolled", "2000-01-01");
				model.addAttribute("expireDate", "2099-01-01");
				model.addAttribute("returnDate", "2099-01-01");
			}
			
			// Add membership data to model
			model.addAttribute("membershipData", membershipData);
			
			// Add empty dependent list
			model.addAttribute("dependentDetails", new ArrayList<>());
			
			return "user/form/membership_renewal";
		}

		if (!dbData.isEmpty()) {
			// User data found
			membershipData = dbData.get(0);
			
			// Format dates
			String enrollDate = membershipData.getDateEnrolled();
			if (enrollDate != null && !enrollDate.isEmpty() && enrollDate.length() >= 8) {
				expDate = LocalDate.parse(enrollDate, formatter);
				model.addAttribute("formattedDateEnrolled", expDate.toString());
		} else {
			model.addAttribute("formattedDateEnrolled", "");
			}

			String expireDate = membershipData.getExpiryDate();
			if (expireDate != null && !expireDate.isEmpty() && expireDate.length() >= 8) {
				expDate = LocalDate.parse(expireDate, formatter);
				model.addAttribute("expireDate", expDate.toString());

				// Calculate date difference for eligibility check
				LocalDate today = LocalDate.now();
				Period period = Period.between(today, expDate);
				long monthsUntilExpiry = ChronoUnit.MONTHS.between(today, expDate);
				
				// Skip eligibility check for testing
				/*
				if (monthsUntilExpiry > 1) {
					return "user/form/renualdateYetnotcame";
				}
				*/
			} else {
				model.addAttribute("expireDate", "");
			}

			String returnDate = membershipData.getLastReturnDate();
			if (returnDate != null && !returnDate.isEmpty() && returnDate.length() >= 8) {
				expDate = LocalDate.parse(returnDate, formatter);
				model.addAttribute("returnDate", expDate.toString());
			} else {
				model.addAttribute("returnDate", "");
			}
			
			model.addAttribute("membershipData", membershipData);
		}

		// Load dependent info
		List<DependentDetails> dependentDetails = null;
		if (petronId != null && !petronId.isEmpty()) {
			dependentDetails = dependentService.getDependentsByUserId(petronId);
			model.addAttribute("dependentDetails", dependentDetails);
		} else {
			model.addAttribute("dependentDetails", new ArrayList<>());
		}

		return "user/form/membership_renewal";
	}

	@PostMapping({"/membership-renewal-service", "/eforms/membership-renewal-service"})
	public String postMembershipRenewalService(@RequestParam Map<String, String> formData, Model model, HttpServletRequest request) {
		// Reuse same code from GET method
		return getMembershipRenewalService(model, request);
	}

	@GetMapping({"/membership-renewal", "/eforms/membership-renewal"})
	public String getMembershipRenewalForm(HttpServletRequest request) {
		logger.info("[GET] /eforms/membership-renewal - Form loaded by {}", request.getRemoteAddr());
		return "user/form/membership_renewal";
	}

	@PostMapping("/eforms/membership-renewal")
	public String submitMembershipRenewal(
	    @RequestParam Map<String, String> params,
	    org.springframework.ui.Model model, HttpServletRequest request) {
	    logger.info("[POST] /eforms/membership-renewal - Submission from {}", request.getRemoteAddr());
	    logger.info("[POST] /eforms/membership-renewal - Params: {}", params);
	    // Log the form data for now
	    System.out.println("Membership renewal submitted: " + params);
	    model.addAttribute("message", "Renewal submitted! (Test only)");
	    model.addAttribute("params", params); // Pass params to the view
	    logger.info("[POST] /eforms/membership-renewal - Returning membership_renewal_success view");
	    return "user/form/membership_renewal_success";
	}

//    Room Booking started

	@GetMapping({"/room-booking", "/eforms/room-booking"})
	public String getRoomBookingForm(org.springframework.ui.Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<RoomReservationFormEntity> rooms = roomServiceImpl.getAllRooms();
		model.addAttribute("rooms", rooms);
			return "user/rooms/room-list";
		} catch (Exception e) {
			logger.error("Error loading room booking form: {}", e.getMessage(), e);
			model.addAttribute("errorMessage", "Failed to load room booking form. Please try again.");
			return "errorPage";
		}
	}

	@GetMapping({"/room-booking-details/{id}", "/eforms/room-booking-details/{id}"})
	public String getRoomBookingDetails(@PathVariable Long id, org.springframework.ui.Model model,
			HttpSession session) {
		
		RoomReservationFormEntity room = roomServiceImpl.getRoomById(id);
		// Image path should be correct from DB.
		session.setAttribute("roomDetails", room);
		model.addAttribute("room", room);
		model.addAttribute("roomBookingDetails", new RoomBookingDetails());
		session.setAttribute("id", id);
		return "user/rooms/room-booking1";
	}

//	@PostMapping("/save-room-booking")
//	public String saveRoomBookingDetails(@ModelAttribute RoomBookingDetails roomBookingDetails,
//			org.springframework.ui.Model model, HttpSession session) {
//		System.err.println("Save room booking inside ");
//		try {
//			// Retrieve the roomId from session to pass to the redirect URL
//			Long roomId = (Long) session.getAttribute("id");
//
//			// Check for overlapping bookings before saving
//			boolean isRoomBooked = roomReservationService.isRoomAlreadyBooked(roomBookingDetails.getRoomId(),
//					roomBookingDetails.getStartDate(), roomBookingDetails.getEndDate(), roomBookingDetails.getTime());
//
//			if (isRoomBooked) {
//				// Inform the user about the booking conflict
//				model.addAttribute("errorMessage", "The room is already booked for the specified date and time.");
//				return "user/form/lostmf"; // Or the same booking form page to display the error
//			}
//
//			// Save the room booking details if no overlap is found
//			RoomBookingDetails savedRoomBookingDetails = roomReservationService.saveRoomDetails(roomBookingDetails);
//			session.setAttribute("roomDetailsI", savedRoomBookingDetails);
//			// After saving, redirect to the equipment page with the roomId
//			return "redirect:/room-booking-equipment/" + roomId.toString();
//
//		} catch (Exception e) {
//			// Handle any exceptions (like database issues, validation errors, etc.)
//			logger.error("Error while saving room booking details: {}", e.getMessage());
//			model.addAttribute("errorMessage", "An error occurred while saving your booking. Please try again.");
//			return "user/form/lostmf"; // Or the page where you want to show the error
//		}
//	}

	@PostMapping({"/save-room-booking", "/eforms/save-room-booking"})
	@ResponseBody
	public Object saveRoomBookingDetails(@ModelAttribute RoomBookingDetails roomBookingDetails,
			org.springframework.ui.Model model, HttpSession session, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response) {
		Long roomId = (Long) session.getAttribute("id");
		try {
			roomBookingDetails.setRoomId(roomId);
			RoomBookingDetails savedBookingDetails = roomServiceImpl.saveRoomDetails(roomBookingDetails);
			session.setAttribute("roomDetailsI", savedBookingDetails);
			
			// Check if it's an AJAX request
			String requestedWith = request.getHeader("X-Requested-With");
			if ("XMLHttpRequest".equals(requestedWith)) {
				// For AJAX requests, return JSON response
				Map<String, Object> jsonResponse = new HashMap<>();
				jsonResponse.put("success", true);
				jsonResponse.put("redirectUrl", "/eforms/room-booking-equipment/" + roomId.toString());
				response.setContentType("application/json");
				return jsonResponse;
			}
			
			// For traditional form submissions
			return "redirect:/eforms/room-booking-equipment/" + roomId.toString();
		} catch (Exception e) {
			logger.error("Error while saving room booking details: {}", e.getMessage());
			
			// Check if it's an AJAX request
			String requestedWith = request.getHeader("X-Requested-With");
			if ("XMLHttpRequest".equals(requestedWith)) {
				// For AJAX requests, return JSON error response
				Map<String, Object> jsonResponse = new HashMap<>();
				jsonResponse.put("success", false);
				jsonResponse.put("error", "T"
						+ "arikh dan masa yang dipilih telah ditempah. Sila pilih tarikh/masa lain.");
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return jsonResponse;
			}
			
			// For traditional form submissions
			redirectAttributes.addFlashAttribute("errorMessage", "Tarikh dan masa yang dipilih telah ditempah. Sila pilih tarikh/massa lain.");
			return "redirect:/eforms/room-booking-details/" + roomId;
		}
	}

//	@GetMapping("/room-booking-equipment/{id}")
//	public String getRoomBookingQquipment(@PathVariable Long id, org.springframework.ui.Model model) {
//        RoomReservationFormEntity room = roomServiceImpl.getRoomBEquipById(id);
//       model.addAttribute("room", room);
//		return "user/rooms/room-equipment";
//	}

	// room booking equipment
	@GetMapping({"/room-booking-equipment/{id}", "/eforms/room-booking-equipment/{id}"})
	public String getRoomBookingEquipment(@PathVariable Long id, org.springframework.ui.Model model, HttpSession session) {
		try {
		RoomReservationFormEntity room = roomServiceImpl.getRoomBEquipById(id);
		model.addAttribute("room", room);

			// Debug log for room type
			logger.info("Room ID: {}, Room Type: {}, Room Name: {}", id, room.getRoomType(), room.getLabelName());

			// Check if the room type is "Bilik Mesyuarat" or if the name contains "Bilik Mesyuarat"
			if ((room.getRoomType() != null && room.getRoomType().equals("Bilik Mesyuarat")) 
					|| (room.getLabelName() != null && room.getLabelName().contains("Bilik Mesyuarat"))) {
				logger.info("Room type is Bilik Mesyuarat, skipping equipment selection and redirecting to summary");
				
				// Get booking ID from session for direct redirect to summary
				RoomBookingDetails bookingDetails = (RoomBookingDetails) session.getAttribute("roomDetailsI");
				if (bookingDetails != null && bookingDetails.getId() != null) {
					// We have a booking ID, redirect directly to summary
					Long bookingId = bookingDetails.getId();
					logger.info("Redirecting Mesyuarat room directly to summary with booking ID: {}", bookingId);
					return "redirect:/room-booking-summary/" + bookingId;
				}
				
				// No redirect if no booking ID, show the equipment page but indicate unavailability
				model.addAttribute("equipmentAvailable", false);
				model.addAttribute("roomId", id);
				return "user/rooms/room-equipment";
			}
			
			// Only show equipment for "Bilik Serbaguna"
			logger.info("Room type is Bilik Serbaguna, showing equipment selection");
			List<EquipmentDetails> equipmentList = Arrays.asList(
				new EquipmentDetails(null, "Kerusi", 0, 1.00, false),
				new EquipmentDetails(null, "Meja Petak", 0, 3.50, false),
				new EquipmentDetails(null, "LCD Projector / Skrin Layer", 0, 50.00, false)
			);

		EquipmentForm equipmentForm = new EquipmentForm();
		equipmentForm.setEquipmentList(equipmentList);

			model.addAttribute("equipmentForm", equipmentForm);
		model.addAttribute("roomId", id);
			model.addAttribute("equipmentAvailable", true); // Equipment is available for Serbaguna

		return "user/rooms/room-equipment";
		} catch (Exception e) {
			logger.error("Error in room equipment selection: {}", e.getMessage(), e);
			model.addAttribute("errorMessage", "Error loading equipment options");
			return "errorPage";
		}
	}

	@PostMapping({"/save-room-booking-equipment/{id}", "/eforms/save-room-booking-equipment/{id}"})
	public String saveRoomBookingEquipment(@PathVariable Long id, @ModelAttribute EquipmentForm equipmentForm,
			org.springframework.ui.Model model, HttpSession session, RedirectAttributes redirectAttributes) {

		try {
			// Enhanced logging for debugging
			logger.info("Processing equipment form with roomId (path variable): {}", id);
			logger.info("Equipment form data: {}", equipmentForm);
			logger.info("Equipment list size: {}",
					equipmentForm.getEquipmentList() != null ? equipmentForm.getEquipmentList().size() : "null");

			RoomBookingDetails bookingDetailsFromSession = (RoomBookingDetails) session.getAttribute("roomDetailsI");
			if (bookingDetailsFromSession == null || bookingDetailsFromSession.getId() == null) {
				logger.error("Critical: RoomBookingDetails not found in session or has no ID when saving equipment.");
				redirectAttributes.addFlashAttribute("errorMessage", "Sesi tempahan tidak dijumpai. Sila cuba lagi.");
				return "redirect:/room-booking"; // Or a more appropriate error/start page
			}
			Long actualBookingId = bookingDetailsFromSession.getId();
			logger.info("Actual Booking ID from session for linking equipment: {}", actualBookingId);

			// Get room type from RoomReservationFormEntity
			RoomReservationFormEntity room = roomServiceImpl.getRoomById(bookingDetailsFromSession.getRoomId());
			if (room != null && room.getRoomType() != null && room.getRoomType().equals("Bilik Serbaguna")) {
				if (equipmentForm.getEquipmentList() != null) {
					for (EquipmentDetails equipment : equipmentForm.getEquipmentList()) {
						equipment.setSelected(true);
					}
				}
			}

			if (equipmentForm.getEquipmentList() != null) {
				for (EquipmentDetails equipment : equipmentForm.getEquipmentList()) {
					logger.info("Equipment item: name={}, selected={}, quantity={}, price={}",
							equipment.getEquipmentName(), equipment.isSelected(), equipment.getQuantity(),
							equipment.getPrice());
				}
			}

			// Delegate the saving operation to the service with the actual booking ID
			roomServiceImpl.saveSelectedEquipment(equipmentForm.getEquipmentList(), actualBookingId);

			// Log session attributes
			logger.info("Setting session attribute 'eqipList'");
			session.setAttribute("eqipList", equipmentForm.getEquipmentList()); // Keep storing the form's list for summary display

			// Log redirect destination
			// The path variable {id} here refers to the room's ID, not the booking's ID, used for page navigation.
			String redirectUrl = "/room-booking-summary/" + actualBookingId;
			logger.info("Redirecting to: {}", redirectUrl);

			// Redirect to the summary page with the room ID (for displaying room info, not for equipment saving logic)
			return "redirect:" + redirectUrl;
		} catch (Exception e) {
			// Log and handle errors
			logger.error("Error while saving equipment details: {}", e.getMessage(), e);
			model.addAttribute("errorMessage", "An error occurred while processing your request. Please try again.");
			return "errorPage"; // Updated to match the actual error page path
		}
	}

	@PostMapping("/save-equipment-selection")
	public String saveEquipmentSelection(@ModelAttribute("equipmentList") List<EquipmentDetails> equipmentList,
			HttpSession session, Model model) {
		try {
			// Store equipment list in session for final transaction
			session.setAttribute("selectedEquipment", equipmentList);
			return "redirect:/personal-information";
		} catch (Exception e) {
			logger.error("Error while processing equipment selection: {}", e.getMessage());
			model.addAttribute("errorMessage", "Failed to process equipment selection. Please try again.");
			return "user/form/error";
		}
	}

	@PostMapping("/complete-booking")
	public String completeBooking(@ModelAttribute PersonalInformation personalInfo, HttpSession session, Model model,
			HttpServletResponse response) {
		try {
			// Retrieve all booking information from session
			RoomBookingDetails bookingDetails = (RoomBookingDetails) session.getAttribute("roomDetailsI");
			@SuppressWarnings("unchecked")
			List<EquipmentDetails> equipmentList = (List<EquipmentDetails>) session.getAttribute("selectedEquipment");

			// Process the complete booking transaction
			RoomBookingDetails completedBooking = roomBookingTransactionService.processCompleteBooking(bookingDetails,
					equipmentList, personalInfo);

			// Clear session attributes
			session.removeAttribute("roomDetailsI");
			session.removeAttribute("selectedEquipment");

			// Add booking confirmation to model
			model.addAttribute("bookingConfirmation", completedBooking);

			// After saving booking, always redirect using the real booking ID
			String redirectUrl = "/room-booking-summary/" + completedBooking.getId();
			logger.info("Redirecting to: {}", redirectUrl);
			return "redirect:" + redirectUrl;

		} catch (Exception e) {
			logger.error("Error while completing booking: {}", e.getMessage());
			model.addAttribute("errorMessage", "Failed to complete the booking. Please try again.");
			return "user/form/error";
		}
	}

	@PostMapping("/confirm-booking")
	public String saveAllRoomBookingDetails(@ModelAttribute PersonalInformation personalInfo, HttpSession httpSession,
			RedirectAttributes redirectAttributes, HttpServletResponse response) {
        
		Long actualBookingId = null;
		if (httpSession.getAttribute("roomDetailsI") != null) {
			RoomBookingDetails bookingDetails = (RoomBookingDetails) httpSession.getAttribute("roomDetailsI");
			actualBookingId = bookingDetails.getId();
		}
		
        logger.info("Processing personal information submission: {}", personalInfo);
        logger.info("Actual Booking ID from session for final confirmation steps: {}", actualBookingId);
        
        // If actualBookingId is null, redirect to room booking list with error
        if (actualBookingId == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Session expired or booking details not found. Please try again.");
            return "redirect:/eforms/room-booking";
        }
        
		try {
			// Save or update the personal information and link to booking
			
			
			personalInfo.setBookingId(actualBookingId);
			PersonalInformation savedPersonalInformation = roomServiceImpl.savedUserDetails(personalInfo);

			if (savedPersonalInformation != null) {
			    Double totalPriceSession = (Double) httpSession.getAttribute("totalPrice");
			    if (totalPriceSession == null) totalPriceSession = 0.0;
			    
				Cookie cookie = new Cookie("petronId", savedPersonalInformation.getId().toString());
			    cookie.setMaxAge(60 * 60); // 1 hour
			    cookie.setPath("/");
			    response.addCookie(cookie);
				
				Utilities u = new Utilities(null);
			  /*  PaymentAccessPayload payload = u.paymentProcessModel(totalPriceSession.toString(),
			   		actualBookingId.toString());
			    ResponseEntity<APITokenResponse> apiResponse = getToken(payload);
			    
			    // Try to save transaction record, but don't fail if it already exists
				try {
					int iCounter = getFndGlnumbService.getGlnumb2("TRXNO");
					//System.out.println("actualBookingId.toString()================"+actualBookingId.toString());
					getFndGlnumbService.insertRETRXN(iCounter+1, null, totalPriceSession.toString(), actualBookingId.toString(), 
							"_ADMIN", String.valueOf(new java.util.Date().getYear()), payload.getPayload().getOrderNo());
				} catch (org.springframework.dao.DuplicateKeyException e) {
					// Transaction already exists, no need to save again
					logger.warn("Transaction record already exists, continuing with booking process");
				}
				*/
				
				// Clear session attributes related to booking after successful processing
				httpSession.removeAttribute("roomDetailsI");
                httpSession.removeAttribute("eqipList");
                httpSession.removeAttribute("roomDetails");
                httpSession.removeAttribute("totalPrice");
                httpSession.removeAttribute("id");
                
                // Update booking with customer name
                RoomBookingDetails booking = roomServiceImpl.getRoomBookingById(actualBookingId);
                booking.setCustomerName(personalInfo.getName());
                PaymentAccessPayload  apiResponse=null;
                // Check if payment gateway is available
                if (apiResponse != null) {
                    // Payment gateway is available, redirect to payment
                   // return "redirect:/pfxp/redirect-to-payment?token=" + apiResponse.getBody().getToken();
                } 
                else {
                    // Payment gateway is not available, mark as pending and success
                    booking.setStatus("Pending Payment (Offline)");
                    roomServiceImpl.saveBooking(booking);
                    
                    // Add success message for offline payment
                    redirectAttributes.addFlashAttribute("successMessage","Tempahan anda telah berjaya! Sila selesaikan pembayaran di kaunter kami.");
                   
                   
                    
                    return "redirect:/admin/admin-dashboard/";
                }
			}

			logger.warn("Failed to save personal information for booking ID: {}", actualBookingId);
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to save your information. Please try again.");
			return "redirect:/room-booking-summary/" + httpSession.getAttribute("id"); // Redirect back to summary with room ID
		}
		catch (Exception e) {
            logger.error("Error while confirming booking (saveAllRoomBookingDetails): {}", e.getMessage(), e);
            
            // Handle errors gracefully
            redirectAttributes.addFlashAttribute("errorMessage", "Error processing your booking. Please try again or contact support.");
            
            if (httpSession.getAttribute("id") != null) {
                return "redirect:/eforms/room-booking-summary/" + httpSession.getAttribute("id");
            } else {
                return "redirect:/eforms/room-booking-error";
            }
        }
	}

	@GetMapping("/room-filter")
	public String getRoomFilterPage(org.springframework.ui.Model model) {
		// Get all room types (can be fetched from database or hardcoded for now)
		List<String> roomTypes = Arrays.asList("Bilik Mesyuarat", "Bilik Serbaguna");
		model.addAttribute("roomTypes", roomTypes);

		// Get all available rooms
		List<RoomReservationFormEntity> rooms = roomServiceImpl.getAllAvailableRooms();
		model.addAttribute("rooms", rooms);

		return "user/rooms/room-filter";
	}

	@GetMapping("/filter-rooms")
	public String filterRooms(@RequestParam(required = false) String roomType,
			@RequestParam(required = false) String date, @RequestParam(required = false) String time,
			org.springframework.ui.Model model) {

		try {
			// Convert parameters as needed
			String labelName = null;
			if (roomType != null && !roomType.equals("Jenis Bilik")) {
				labelName = roomType.equals("Bilik Mesyuarat") ? "P11 Bilik Mesyuarat" : "Bilik Serbaguna";
			}

			// Filter rooms based on parameters
			List<RoomReservationFormEntity> filteredRooms;
			if (labelName != null || date != null || time != null) { // Check if any filter criteria is present
				// We'll pass date and time. The service method will need to be updated.
				// For now, capacity and price are passed as null.
				filteredRooms = roomServiceImpl.getFilteredRooms(labelName, null, null); // Temporarily changed, date/time were passed as capacity/price
			}
			else {
				filteredRooms = roomServiceImpl.getAllAvailableRooms();
			}

			// Add the filtered rooms to the model
			model.addAttribute("rooms", filteredRooms);

			// Pass the filter parameters back to maintain form state
			model.addAttribute("selectedRoomType", roomType);
			model.addAttribute("selectedDate", date);
			model.addAttribute("selectedTime", time);

			// Add room types for the dropdown
			List<String> roomTypes = Arrays.asList("Bilik Mesyuarat", "Bilik Serbaguna");
			model.addAttribute("roomTypes", roomTypes);

			return "user/rooms/room-filter";
		} catch (Exception e) {
			// Handle exceptions
			model.addAttribute("errorMessage", "Error while filtering rooms: " + e.getMessage());

			// Add room types for the dropdown
			List<String> roomTypes = Arrays.asList("Bilik Mesyuarat", "Bilik Serbaguna");
			model.addAttribute("roomTypes", roomTypes);

			return "user/rooms/room-filter";
		}
	}

	@GetMapping("/api/booked-dates/{roomId}")
	@ResponseBody
	public List<Map<String, Object>> getBookedDatesForRoom(@PathVariable Long roomId) {
		List<Map<String, Object>> bookedDates = new ArrayList<>();
		
		try {
			// Get actual bookings from the repository
			List<RoomBookingDetails> bookings = roomServiceImpl.getBookingsForRoom(roomId);
			
			DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE; // Standard date format
			
			for (RoomBookingDetails booking : bookings) {
				Map<String, Object> dateInfo = new HashMap<>();
				dateInfo.put("startDate", booking.getStartDate().format(dateFormatter));
				dateInfo.put("endDate", booking.getEndDate().format(dateFormatter));
				dateInfo.put("time", booking.getTime());
				bookedDates.add(dateInfo);
			}
		} catch (Exception e) {
			logger.error("Error fetching booked dates for room ID {}: {}", roomId, e.getMessage());
			// Return empty list in case of error
		}

		logger.info("Found {} booked date/time slots for room ID {}", bookedDates.size(), roomId);
		return bookedDates;
	}

	@GetMapping("/all-form")
	public String getHomeUi() {
		return "index";
	}

	@PostMapping("/registration")
	@ResponseBody
	public String registerUser() {
		return "form submitted";
	}

	public User getLiferayUserDetails(HttpServletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		if (themeDisplay != null) {
			return themeDisplay.getUser();
		}

		return null;
	}

	@GetMapping("/getLcost")
	@ResponseBody
	public String getLcost(org.springframework.ui.Model model, @RequestParam String accno,
			@RequestParam String patronid) {
		String costAmount = service.getCtdocmLcost(accno);
		;
		BigDecimal bigCirGetCtdocmCosty = new BigDecimal(costAmount);
		BigDecimal bookCost = BigDecimal.ZERO;
		if (bigCirGetCtdocmCosty.compareTo(BigDecimal.ZERO) > 0) {
			bookCost = bigCirGetCtdocmCosty;
		} else {

			String getTimesCost = service.getMenuLcost(accno, patronid);
			String getLcostMenu = service.biggetLcostMenu();

			BigDecimal biggetLcostMenu = new BigDecimal(getLcostMenu);
			BigDecimal biggetTimesCost = new BigDecimal(getTimesCost);
			bookCost = biggetLcostMenu.multiply(biggetTimesCost);
		}
		model.addAttribute("costAmpount", bookCost);
		return bookCost.toString();

	}

	@GetMapping("/getMenuLcost")
	public String getMenuLcost(@RequestParam String accno, @RequestParam String patronid) {
		return service.getMenuLcost(accno, patronid);
	}

	@GetMapping({"/room-booking-summary/{id}", "/eforms/room-booking-summary/{id}"})
	public String getRoomBookingSummary(@PathVariable Long id, org.springframework.ui.Model model) {
		try {
			// Log the request for debugging
			logger.info("Accessing room booking summary for booking ID: {}", id);
			
			// Fetch booking details from DB
			RoomBookingDetails bookingDetails = null;
			try {
			    bookingDetails = roomServiceImpl.getRoomBookingById(id);
			} catch (Exception e) {
			    logger.error("Error fetching booking: {}", e.getMessage());
			    model.addAttribute("errorMessage", "Tempahan dengan ID " + id + " tidak dijumpai.");
			    return "errorPage";
			}
			
			if (bookingDetails == null) {
				logger.error("Booking with ID {} not found", id);
				model.addAttribute("errorMessage", "Tempahan dengan ID " + id + " tidak dijumpai.");
				return "errorPage";
			}

			// Fetch room details from DB
			RoomReservationFormEntity room = roomServiceImpl.getRoomById(bookingDetails.getRoomId());
			if (room == null) {
				logger.error("Room with ID {} not found for booking {}", bookingDetails.getRoomId(), id);
				model.addAttribute("errorMessage", "Maklumat bilik tidak dijumpai.");
				return "errorPage";
			}

			// Fetch equipment details from DB
			List<EquipmentDetails> equipmentList = roomServiceImpl.getEquipmentByBookingId(id);
			logger.info("Found {} equipment items for booking ID: {}", equipmentList.size(), id);
			
			// Calculate equipment cost
			double equipCost = 0.0;
			for (EquipmentDetails equip : equipmentList) {
				if (equip.getQuantity() != null && equip.getPrice() != null) {
					equipCost += equip.getPrice().doubleValue() * equip.getQuantity();
					logger.info("Equipment: {} x {} = RM{}", equip.getEquipmentName(), equip.getQuantity(), equip.getPrice().doubleValue() * equip.getQuantity());
				}
			}

			// Calculate room cost
			double roomCost = room.getPrice() != null ? room.getPrice().doubleValue() : 0.0;
			double totalPrice = roomCost + equipCost;

			// Pass all details to the view
			model.addAttribute("room", room);
			model.addAttribute("currentRoom", room);
			model.addAttribute("selectedEquip", equipmentList);
			model.addAttribute("currentBookingDet", bookingDetails);
			model.addAttribute("totalPrice", totalPrice);
			model.addAttribute("equipCost", equipCost);
			model.addAttribute("roomCost", roomCost);

			return "user/rooms/room-summary";
		} catch (Exception e) {
			logger.error("Error in getRoomBookingSummary for ID {}: {}", id, e.getMessage(), e);
			model.addAttribute("errorMessage", "Ralat telah berlaku semasa memproses ringkasan tempahan anda. Sila cuba lagi.");
			model.addAttribute("backUrl", "/eforms/room-booking");
			return "errorPage";
		}
	}
	public ResponseEntity<APITokenResponse> getToken(PaymentAccessPayload payload) {
		String url = "https://ppkdev.ppj.gov.my:8080/pfxp/pay-api/pay/access";
		try {
		    return restTemplate.postForEntity(url,payload.getPayload(), APITokenResponse.class);
		} catch (Exception e) {
		    logger.warn("Payment gateway connection failed: {}", e.getMessage());
		    return null; // Return null to indicate connection failure
		}
	}

	@GetMapping("/lost-material-direct")
	public String getLostMaterialDirectForm(org.springframework.ui.Model model) {
		try {
			logger.info("Fetching borrowed materials for lost-material-direct form");
			List<LostBorrowedMaterial> borrowedMaterials = damagedReportServiceImpl.getBorrowedMaterials();
			logger.info("Found {} borrowed materials to display", borrowedMaterials.size());
			model.addAttribute("borrowedMaterials", borrowedMaterials);
			return "forms/lost-material-direct";
		} catch (Exception e) {
			logger.error("Error loading lost-material-direct form: {}", e.getMessage(), e);
			model.addAttribute("errorMessage", "Error loading materials. Please try again.");
			return "error";
		}
	}

	@GetMapping({"/booking-success", "/eforms/booking-success"})
	public String bookingSuccess(Model model, RedirectAttributes redirectAttributes) {
		// Show success page with any flash messages that were set
		return "user/rooms/booking-success";
	}

	@GetMapping({"/room-booking-error", "/eforms/room-booking-error"})
	public String roomBookingError(Model model, RedirectAttributes redirectAttributes) {
		// Show custom error page specific to room booking errors
		if (!model.containsAttribute("errorMessage")) {
			model.addAttribute("errorMessage", "Ralat telah berlaku semasa memproses tempahan anda. Sila cuba lagi.");
		}
		return "user/rooms/room-booking-error";
	}
}
