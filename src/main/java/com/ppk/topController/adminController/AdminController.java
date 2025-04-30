package com.ppk.topController.adminController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ppk.topEntity.formsEntity.MaterialProcurementProposalForm;
import com.ppk.topEntity.formsEntity.ProposalFormEntity;
import com.ppk.topEntity.formsEntity.dto.AdminBookList;
import com.ppk.topEntity.formsEntity.dto.MaterialProcurementProjection;
import com.ppk.topEntity.formsEntity.registration.MemebershipRequestModel;
import com.ppk.topServiceImpl.adminServiceImpl.AdminServiceImpl;
import com.ppk.topServiceImpl.formServiceImpl.LostDamagedReportServiceImpl;
import com.ppk.topServiceImpl.formServiceImpl.MaterialProcurementProposalFormServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private MaterialProcurementProposalFormServiceImpl fromServiceImpl;

	// Injecting AdminServiceImpl
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
    private LostDamagedReportServiceImpl lostMaterialFormService;

	// Endpoint to get all membership records with GL14STAT = 1
	@GetMapping("/memeberList")
	@ResponseBody
	public List<MemebershipRequestModel> getAllMembershipRequests() {
		return adminService.getAllRequestModel();
	}

	@GetMapping("/admin-dashboard")
	public String getAdminDashboard(Model model) {
		// Get the current date and time
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | EEEE, hh:mm a");

		// Format the current date and time
		String formattedDate = now.format(formatter);

		// Add the formatted date to the model
		model.addAttribute("currentDate", formattedDate);
		List<MaterialProcurementProposalForm> dbDataForAdmin = fromServiceImpl.getAllMaterialRecommendationAdmin();
		
		//book list 
		List<AdminBookList> bookListForAdmin = adminService.findAllDocuments();
		List<AdminBookList> adminFirstSixBookList = bookListForAdmin.stream().limit(6).collect(Collectors.toList()); // Limit the list to the first
      Double totalOverDues= adminService.calculateTotalFeeOverdue();
   model.addAttribute("totalOverDuesFees", totalOverDues);
//	 System.err.println(dbDataForAdmin +"admin");
//	System.err.println(dbDataForAdmin);	
		// Fetch all the membership request models
		List<MemebershipRequestModel> dbAllMemberList = adminService.getAllRequestModel();

		// Get the first 6 records
		List<MemebershipRequestModel> firstSixMembers = dbAllMemberList.stream().limit(6) // Limit the list to the first
																							// 6 records
				.collect(Collectors.toList());

		// get the 100 data
		List<MemebershipRequestModel> firsthundMembers = dbAllMemberList.stream().limit(200) // Limit the list to the
																								// first 6 records
				.collect(Collectors.toList());
		
		
		//get 100 book details
	  List<AdminBookList> topHundBookListAdmin= bookListForAdmin.stream().limit(200).collect(Collectors.toList());
		model.addAttribute("allDbListBookAdmin", topHundBookListAdmin);
	  model.addAttribute("alldbList", firsthundMembers);

		// get total memeber register
		Integer totalRegisterMember = adminService.getTotalRegisteredMembers();
		model.addAttribute("totalMember", totalRegisterMember);
		// Add the limited list to the model for book List
		model.addAttribute("adminBookList", adminFirstSixBookList);

		//lost form 
		 // Fetch data from the service
        List<Object[]> uniqueForms = lostMaterialFormService.getAllUniqueLostForms();

        System.err.println(uniqueForms);
        // Add the data to the model for Thymeleaf
        model.addAttribute("uniqueForms", uniqueForms);
		
		model.addAttribute("alldata", firstSixMembers);
		//get total borred memeber
	 Integer totalBorrowedMemeber=	adminService.getTotalBorrowedMembers();
		model.addAttribute("totalBorrowedMemeber", totalBorrowedMemeber);
		model.addAttribute("dbData", dbDataForAdmin);
		return "admin/admin-dashboard";
	}

	@GetMapping("/admin-dashboard/{id}")
	@ResponseBody
	public MaterialProcurementProposalForm getMaterialById(@PathVariable Long id) {
		return fromServiceImpl.getMaterialById(id).orElseThrow(() -> new RuntimeException("Material not found"));
	}
	
	 // Endpoint to get data by patron_id
    @GetMapping("/by-patron/{patronId}")
    @ResponseBody
    public List<Object[]> getLostFormRequestByPatronId(@PathVariable String patronId) {
        return lostMaterialFormService.getLostFormRequestByPatronId(patronId);
    }

}
