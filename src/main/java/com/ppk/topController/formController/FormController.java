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
 
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	GetFndGlnumbService getFndGlnumbService;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private com.ppk.topService.formService.roomService.RoomBookingTransactionService roomBookingTransactionService;

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

			// Add a success message using flash attributes
			redirectAttributes.addFlashAttribute("successMessage", "Data saved successfully!");
			return "redirect:/recommendation-form"; // Redirect to reload the form

		} catch (Exception e) {
			// Add an error message to the model (since redirectAttributes do not work for
			// the same request)
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

	@PostMapping("/membership-renewal-service")
	public String getMembershipRenewal(Model model, HttpServletRequest request) {
		String petronId = request.getParameter("username");
		LocalDate expDate = null;
		List<com.ppk.topController.membership.renual.entity.RenewalMembershipData> dbData = renewFeeService
				.getMembershipData(petronId);
		// User user = getLiferayUserDetails(request);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		// Ensure dbData is not null or empty, create an instance manually if needed
		RenewalMembershipData membershipData = new RenewalMembershipData();
		if (dbData != null && !dbData.isEmpty()) {
			membershipData = dbData.get(0);
		}
		List<Dependent> dependentDetails = new ArrayList<Dependent>();
		if (membershipData != null) {
			// Set default values if null
			String dateEnrolledStr = (membershipData.getDateEnrolled() != null) ? membershipData.getDateEnrolled()
					: "20000101";
			String expiryDateStr = (membershipData.getExpiryDate() != null) ? membershipData.getExpiryDate()
					: "20990101";
			String returnDateStr = (membershipData.getLastReturnDate() != null) ? membershipData.getLastReturnDate()
					: "20990101";
			// Parse dates safely
			LocalDate dateEnrolled = LocalDate.parse(dateEnrolledStr, formatter);
			LocalDate expiryDate = LocalDate.parse(expiryDateStr, formatter);
			LocalDate returnDate = LocalDate.parse(returnDateStr, formatter);

			// Add attributes to the model
			model.addAttribute("formattedDateEnrolled", dateEnrolled);
			model.addAttribute("expireDate", expiryDate);
			model.addAttribute("returnDate", returnDate);
			model.addAttribute("dbUserData", dbData);
			expDate = expiryDate;

			// Fetch dependent details safely
			dependentDetails = registrationService.getDependentRegs(petronId);
			if (dependentDetails == null) {
				dependentDetails = new ArrayList<>(); // Ensure we have a non-null list
			}
			model.addAttribute("dependentDetails", dependentDetails);

			model.addAttribute("dependentDetails", dependentDetails);
			model.addAttribute("membershipData", membershipData);
		} else {
			dependentDetails = new ArrayList<Dependent>();
			model.addAttribute("formattedDateEnrolled", "");
			model.addAttribute("expireDate", "");
			model.addAttribute("returnDate", "");
			model.addAttribute("dbUserData", "");
			model.addAttribute("membershipData", null);
			model.addAttribute("dependentDetails", dependentDetails);
		}

		LocalDate currentDate = LocalDate.now();

// Calculate the date that is one month ahead from the current date
		LocalDate oneMonthFromNow = currentDate.plusMonths(1);

// Check if the expiry date is less than or equal to one month from now
		String display = "user/form/membership_renewal";
		if (expDate != null && (expDate.isBefore(oneMonthFromNow) || expDate.isEqual(oneMonthFromNow))) {
			display = "user/form/membership_renewal";
		} else {
			display = "user/form/renualdateYetnotcame";
		}

		return display;
	}

	@GetMapping("/membership-renewal")
	public String getMembershipRenewalui(HttpServletRequest request) {
		return "user/form/justrender-renual";
	}

//    Room Booking started

	@GetMapping("/room-booking")
	public String getRoomBookingForm(org.springframework.ui.Model model) {
		// Fetch the list of rooms from the service
		List<RoomReservationFormEntity> rooms = roomReservationService.getAllRooms();

		// Add the list of rooms to the model
		model.addAttribute("rooms", rooms);

		// Return the view name
		return "user/rooms/room-list"; // The Thymeleaf template to render
	}

	@GetMapping("/room-booking-details/{id}")
	public String getRoomBookingDetails(@PathVariable Long id, org.springframework.ui.Model model,
			HttpSession session) {
		RoomReservationFormEntity room = roomServiceImpl.getRoomById(id);
		session.setAttribute("roomDetails", room);
		model.addAttribute("room", room);
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

	@PostMapping("/save-room-booking")
	public String saveRoomBookingDetails(@ModelAttribute RoomBookingDetails roomBookingDetails,
			org.springframework.ui.Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		Long roomId = (Long) session.getAttribute("id");
		try {
			// Retrieve the roomId from session

			roomBookingDetails.setRoomId(roomId);

			// Save the initial booking details and store in session
			RoomBookingDetails savedBookingDetails = roomServiceImpl.saveRoomDetails(roomBookingDetails);
			session.setAttribute("roomDetailsI", savedBookingDetails);

			// After saving, redirect to the equipment page
			return "redirect:/room-booking-equipment/" + roomId.toString();
		} catch (Exception e) {
			logger.error("Error while saving room booking details: {}", e.getMessage());
			redirectAttributes.addFlashAttribute("roomAlreadyBooked",
					"The room is already booked for the selected date and time.");

			model.addAttribute("errorMessage", "An error occurred while saving your booking. Please try again.");
			return "redirect:/room-booking-details/" + roomId.toString();
		}
	}

//	@GetMapping("/room-booking-equipment/{id}")
//	public String getRoomBookingQquipment(@PathVariable Long id, org.springframework.ui.Model model) {
//        RoomReservationFormEntity room = roomServiceImpl.getRoomBEquipById(id);
//       model.addAttribute("room", room);
//		return "user/rooms/room-equipment";
//	}

	// room booking equipment
	@GetMapping("/room-booking-equipment/{id}")
	public String getRoomBookingEquipment(@PathVariable Long id, org.springframework.ui.Model model) {
		RoomReservationFormEntity room = roomServiceImpl.getRoomBEquipById(id);
		model.addAttribute("room", room);

		List<EquipmentDetails> equipmentList = Arrays.asList(new EquipmentDetails(null, "Kerusi", 0, 1.00, false),
				new EquipmentDetails(null, "Meja Petak", 0, 3.50, false),
				// new EquipmentDetails(null, "Komputer", 0, 3.00, false),
				new EquipmentDetails(null, "LCD Projector / Skrin Layer", 0, 50.00, false));

		EquipmentForm equipmentForm = new EquipmentForm();
		equipmentForm.setEquipmentList(equipmentList);

		model.addAttribute("equipmentForm", equipmentForm); // Add the entire form object
		model.addAttribute("roomId", id);

		return "user/rooms/room-equipment";
	}

	@PostMapping("/save-room-booking-equipment/{id}")
	public String saveRoomBookingEquipment(@PathVariable Long id, @ModelAttribute EquipmentForm equipmentForm,
			org.springframework.ui.Model model, HttpSession session, RedirectAttributes redirectAttributes) {

		try {
			// Enhanced logging for debugging
			logger.info("Processing equipment form with roomId: {}", id);
			logger.info("Equipment form data: {}", equipmentForm);
			logger.info("Equipment list size: {}",
					equipmentForm.getEquipmentList() != null ? equipmentForm.getEquipmentList().size() : "null");

			if (equipmentForm.getEquipmentList() != null) {
				for (EquipmentDetails equipment : equipmentForm.getEquipmentList()) {
					logger.info("Equipment item: name={}, selected={}, quantity={}, price={}",
							equipment.getEquipmentName(), equipment.isSelected(), equipment.getQuantity(),
							equipment.getPrice());
				}
			}

			// Delegate the saving operation to the service
			roomServiceImpl.saveSelectedEquipment(equipmentForm.getEquipmentList());

			// Log session attributes
			logger.info("Setting session attribute 'eqipList'");
			session.setAttribute("eqipList", equipmentForm.getEquipmentList());

			// Log redirect destination
			String redirectUrl = "/room-booking-summary/" + id;
			logger.info("Redirecting to: {}", redirectUrl);

			// Redirect to the summary page with the room ID
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

			return "user/rooms/booking-confirmation";

		} catch (Exception e) {
			logger.error("Error while completing booking: {}", e.getMessage());
			model.addAttribute("errorMessage", "Failed to complete the booking. Please try again.");
			return "user/form/error";
		}
	}

	@PostMapping("/confirm-booking")
	public String saveAllRoomBookingDetails(@ModelAttribute PersonalInformation personalInformation,
			RedirectAttributes redirectAttributes, HttpSession httpSession, HttpServletResponse response ) {
		try {
			logger.info("Processing personal information submission: {}", personalInformation);

			// Set bookingId to null to avoid database column issue
			personalInformation.setBookingId(null);

			// Save personal information
			PersonalInformation savedPersonalInformation = this.roomServiceImpl.savedUserDetails(personalInformation);

			
			if (savedPersonalInformation != null) {
			Double totalPriceSession=httpSession.getAttribute("totalPrice") !=null?(Double)	httpSession.getAttribute("totalPrice"):0;
				Cookie cookie = new Cookie("petronId", personalInformation.getId().toString());
			    cookie.setMaxAge(60 * 60); // 1 hour
			    cookie.setPath("/");
			    response.addCookie(cookie);
				Utilities u = new Utilities(null);
			    PaymentAccessPayload payload = u.paymentProcessModel(totalPriceSession.toString(),
			    		personalInformation.getId().toString());
			    ResponseEntity<APITokenResponse> apiResponse = getToken(payload);
				 int iCounter = getFndGlnumbService.getGlnumb2("TRXNO");
		      		getFndGlnumbService.insertRETRXN(iCounter+1, null, totalPriceSession.toString(), personalInformation.getId().toString(),
		      				"_ADMIN", String.valueOf(new java.util.Date().getYear()), payload.getPayload().getOrderNo());
				
				// Return a success response for AJAX
		      	return "redirect:/pfxp/redirect-to-payment?token=" + apiResponse.getBody().getToken();
			//	redirectAttributes.addFlashAttribute("successMessage", "Data saved successfully!");
			//	return "redirect:/room-booking";
			}

			// Handle unsuccessful save
			logger.warn("Failed to save personal information");
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to save your information. Please try again.");
			return "redirect:/room-booking";
		} catch (Exception e) {
			// Log the exception details
			logger.error("Error while saving personal information: {}", e.getMessage(), e);
			redirectAttributes.addFlashAttribute("errorMessage", "An error occurred: " + e.getMessage());
			return "redirect:/room-booking";
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
			if (labelName != null) {
				filteredRooms = roomServiceImpl.getFilteredRooms(labelName, null, null);
			} else {
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
		// Get all bookings for the specific room
		List<RoomBookingDetails> bookings = roomServiceImpl.getBookingsForRoom(roomId);

		// Convert to a format suitable for frontend (dates and times that are booked)
		List<Map<String, Object>> bookedDates = new ArrayList<>();

		for (RoomBookingDetails booking : bookings) {
			Map<String, Object> dateInfo = new HashMap<>();
			dateInfo.put("startDate", booking.getStartDate().toString());
			dateInfo.put("endDate", booking.getEndDate().toString());
			dateInfo.put("time", booking.getTime());
			bookedDates.add(dateInfo);
		}

		return bookedDates;
	}

	// Login for UI
	@GetMapping("/login")
	public String loginUi() {
		return "login";
	}

	@GetMapping("/all-form")
	public String getHomeUi() {
		return "index";
	}

	@GetMapping("/")
	public String getErrorPage() {
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

	@GetMapping("/room-booking-summary/{id}")
	public String getRoomBookingSummary(@PathVariable Long id, org.springframework.ui.Model model,
			HttpSession session) {
		try {
			logger.info("Processing room booking summary for room ID: {}", id);

			BookingSummary bookingSummary = new BookingSummary();

			// Get room from database
			RoomReservationFormEntity room = roomServiceImpl.getRoomBEquipById(id);
			if (room == null) {
				logger.error("Room with ID {} not found", id);
				model.addAttribute("errorMessage", "Room not found");
				return "errorPage";
			}
			model.addAttribute("room", room);

			// Get room details from session
			RoomReservationFormEntity currentRoomDetails = (RoomReservationFormEntity) session
					.getAttribute("roomDetails");
			model.addAttribute("currentRoom", currentRoomDetails);

			// Get equipment list from session
			@SuppressWarnings("unchecked")
			List<EquipmentDetails> equipDet = (List<EquipmentDetails>) session.getAttribute("eqipList");
			if (equipDet == null) {
				logger.warn("Equipment list is null in session");
				equipDet = new ArrayList<>();
			}
			model.addAttribute("selectedEquip", equipDet);

			// Get booking details from session
			RoomBookingDetails bookingDetails = (RoomBookingDetails) session.getAttribute("roomDetailsI");
			model.addAttribute("currentBookingDet", bookingDetails);

			// Calculate costs
			bookingSummary.setRoomCost(currentRoomDetails != null ? currentRoomDetails.getPrice() : 0);
			double equipCost = 0;
			for (EquipmentDetails equip : equipDet) {
				equipCost += equip.getPrice() * equip.getQuantity();
			}
			bookingSummary.setEquipmentCost(equipCost);

			double totalPrice = equipCost + (currentRoomDetails != null ? currentRoomDetails.getPrice() : 0);
			model.addAttribute("totalPrice", totalPrice);

			// Store totalPrice in HTTP session
			session.setAttribute("totalPrice", totalPrice);

			bookingSummary.setEquipmentCost(equipCost);
			model.addAttribute("totalPrice",
					equipCost + (currentRoomDetails != null ? currentRoomDetails.getPrice() : 0));

			logger.info("Successfully processed room booking summary. Rendering room-summary page");
			return "user/rooms/room-summary";
		} catch (Exception e) {
			logger.error("Error processing room booking summary: {}", e.getMessage(), e);
			model.addAttribute("errorMessage",
					"An error occurred while processing your booking summary. Please try again.");
			return "errorPage";
		}
	}
	public ResponseEntity<APITokenResponse> getToken(PaymentAccessPayload payload) {
		String url = "https://ppkdev.ppj.gov.my:8080/pfxp/pay-api/pay/access";
		return restTemplate.postForEntity(url,payload.getPayload(), APITokenResponse.class);
	}
}
