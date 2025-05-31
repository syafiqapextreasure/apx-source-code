package com.ppk.topController.formController;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ppk.topController.membership.renual.service.GetFndGlnumbService;
import com.ppk.topEntity.APITokenResponse;
import com.ppk.topEntity.PaymentAccessPayload;
import com.ppk.topEntity.formsEntity.dto.Dependent;
import com.ppk.topService.formService.ppkMembershipRegistrationForm.RegistrationService;
import com.ppk.utilities.LoginUserDetail;
import com.ppk.utilities.SSOApiResponse;
import com.ppk.utilities.Utilities;

@Controller
public class RegistrationController {

	//    private static final String No_KP = null;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	GetFndGlnumbService getFndGlnumbService;

	@PostMapping("/submitForm")
	public String handleRegistration(@RequestParam("namaAhli") String namaAhli, @RequestParam("dob") String inputDOB,
									 @RequestParam("alamatSuratParent") String alamat, @RequestParam("poskodSuratParent") String poskod,
									 @RequestParam("negeri") String negeri, @RequestParam("phone") String phone,
									 @RequestParam("picImage") MultipartFile picImage, @RequestParam("paspekerjas") MultipartFile paspekerja, // @RequestParam("kadOKU")
									 // MultipartFile
									 // kadOKU,
									 @RequestParam(value = "statusUser", required = false) String statusUser,
									 @RequestParam(value = "picImage", required = false) MultipartFile picImageVal,
									 @RequestParam(value = "bandar", required = false) String bandar,
									 @RequestParam(value = "Tanggungan_Id_Pengguna_Tanggungan", required = false) String TanggunganIdPenggunaTanggungan,
									 @RequestParam(value = "Tanggungan_Id_Pengguna_Portal_PPJ", required = false) String Tanggungan_Id_Pengguna_Portal_PPJ,
									 @RequestParam(value = "branchLocation", required = false) String branchLocation,
									 @RequestParam(value = "No_KP", required = false) String noKP,
									 @RequestParam(value = "agecate", required = false) String citizen,
									 @RequestParam(value = "passport", required = false) String Nation,
									 @RequestParam(value = "Adakah_ahli_keluarga_anda_merupakan", required = false) String AdakahAhliKeluarga,
									 @RequestParam(value = "radioPasanganStaff", required = false) String radioPasanganStaff,
									 @RequestParam(value = "selectedItems", required = false) String selectedItemsJson,
									 @RequestParam(value = "emailAddress", required = false) String emailAddress,
									 @RequestParam(value = "updatedCat", required = false) String updatedCat,

									 Model model, RedirectAttributes redirectAttributes, HttpServletResponse response) {

		/* ==================== */
//    		@RequestParam("paspekerja") MultipartFile paspekerja,
//    		@RequestParam("radioPasanganStaff") String radioPasanganStaff,
//    		@RequestParam("paspekerja") MultipartFile paspekerjaFile,
//    		@RequestParam("Adakah_ahli_keluarga_anda_merupakan") String AdakahAhliKeluarga,
//    		@RequestParam("negeri") String negeri,
//    		@RequestParam("bandar") String bandar,
//    		@RequestParam("poskodSuratParent") String poskodSuratParent,
//    		@RequestParam("alamatSuratParent") String alamatSuratParent,a
//    		@RequestParam("picImage") MultipartFile picImage,
//    		@RequestParam("statusUser") String statusUser,
//    		@RequestParam("hubungan") String hubungan,
//    		@RequestParam("No_KP") String noKP,
//    		@RequestParam("Tanggungan_Id_Pengguna_Tanggungan") String TanggunganIdPenggunaTanggungan,
//    		@RequestParam("passport") String passport,
//    		@RequestParam("agecate") String agecate,
//    		@RequestParam("phone") String phone,
//    		@RequestParam("dob") String inputDOB,
//    		@RequestParam("name") String namaAhli,
//    		@RequestParam("branchLocation") String branchLocation,
//    		@RequestParam("Tanggungan_Id_Pengguna_Portal_PPJ") String Tanggungan_Id_Pengguna_Portal_PPJ,
//
		// Model model) {

		System.err.println("Submit form is called inputDOB" + emailAddress);
		List<Dependent> selectedItems = new ArrayList<Dependent>();
		Utilities u = new Utilities(null);
		try {
			// Use Jackson to convert the JSON string into a List of Item objects
			ObjectMapper objectMapper = new ObjectMapper();

			selectedItems = selectedItemsJson != null && !"".equals(selectedItemsJson)
					? objectMapper.readValue(selectedItemsJson, new TypeReference<List<Dependent>>() {
			})

					: selectedItems;
			System.out.println("Parsed " + selectedItems.size() + " dependents");
			System.out.println("Received selectedItemsJson: " + selectedItemsJson);

			// Add detailed logging of parsed data
			if(selectedItemsJson != null && !selectedItemsJson.isEmpty()) {
				System.out.println("Parsed dependents:");
				selectedItems.forEach(dep -> System.out.println(
						"Name: " + dep.getIdPengguna() +
								", IC: " + dep.getNokPTanggungan() +
								", Relation: " + dep.getHubungan()
				));
			}

			// Do something with the selected items (e.g., save to the database)
			System.out.println(new Gson().toJson(selectedItems) + " updatedCat Received items: ");

			// Check file size
			if (picImage.getSize() > 2 * 1024 * 1024) { // 2MB
				model.addAttribute("errorMessage", "File size exceeds the maximum limit of 2MB.");
				return "user/form/membership_registration"; // Redirect back to the form
			}
			// registrationService.handleRegistration(namaAhli, inputDOB, alamat, poskod,
			// negeri, phone, picImage, kadOKU, statusUser, picImageVal, bandar,
			// TanggunganIdPenggunaTanggungan, Tanggungan_Id_Pengguna_Portal_PPJ,
			// branchLocation, negeri, phone, noKP, TanggunganIdPenggunaTanggungan,
			// picImage, citizen, Nation, paspekerja, paspekerjaFile, passport, inputDOB,
			// Nation, alamatSuratParent, alamatSuratParent, alamatSuratParent,
			// alamatSuratParent, poskodSuratParent, negeri, alamatSuratParent, namaAhli,
			// branchLocation, Tanggungan_Id_Pengguna_Portal_PPJ, radioPasanganStaff,
			// alamatSuratParent, alamatSuratParent, alamatSuratParent, poskodSuratParent,
			// alamatSuratParent, phone, statusUser, agecate);

			String noKP2 = noKP != null && !"".equals(noKP) && noKP.contains(",") ? noKP.split(",")[0] : noKP;
			String inputDOB2 = inputDOB != null && !"".equals(inputDOB) ? inputDOB.replace("-", "") : inputDOB;

			if (!registrationService.isNoKPRegistered(noKP2)) {
				registrationService.handleRegistration(namaAhli, inputDOB2, alamat, poskod, negeri, phone, picImage,
						paspekerja, statusUser, picImageVal, bandar, TanggunganIdPenggunaTanggungan,
						Tanggungan_Id_Pengguna_Portal_PPJ, branchLocation, noKP2, citizen, Nation, AdakahAhliKeluarga,
						emailAddress, updatedCat);
			}
			try {
				if (selectedItems.size() > 0) {
					BigDecimal total = selectedItems.stream().map(Dependent::getHarga).reduce(BigDecimal.ZERO,
							BigDecimal::add);

					PaymentAccessPayload payload = u.paymentProcessModel(total.toString(),
							selectedItems.get(0).getLoginId());

					// Add debug logging before insertion
					System.out.println("Before dependent insertion - Parent ID: " + Tanggungan_Id_Pengguna_Portal_PPJ);
					System.out.println("Dependents to insert: " + selectedItems);

					registrationService.insertDependentRegs(selectedItems, Tanggungan_Id_Pengguna_Portal_PPJ,
							payload.getPayload().getOrderNo());

					// Verification step
					List<Dependent> insertedDependents = registrationService.getDependentRegs(selectedItems.get(0).getLoginId());
					System.out.println("Verified inserted dependents: " + insertedDependents.size());
				}
			} catch (Exception ex) {
				System.err.println("Error in dependent processing: " + ex.getMessage());
				ex.printStackTrace();
			}
			// Add success message

			if (selectedItems.size() > 0) {
				BigDecimal total = selectedItems.stream().map(Dependent::getHarga).reduce(BigDecimal.ZERO,
						BigDecimal::add);
				PaymentAccessPayload payload = u.paymentProcessModel(total.toString(),
						selectedItems.get(0).getLoginId());
			  //	ResponseEntity<APITokenResponse> apiResponse = getToken(payload);

				Cookie cookie = new Cookie("petronId", selectedItems.get(0).getLoginId());
				cookie.setMaxAge(60 * 60); // 1 hour
				cookie.setPath("/");
				response.addCookie(cookie);
				//return "redirect:/pfxp/redirect-to-payment?token=" + apiResponse.getBody().getToken();
			}

			redirectAttributes.addFlashAttribute("message", "Success");
			return "redirect:/membership-registration"; // Redirect to a Thymeleaf template named 'success.html'
		} catch (Exception e) {
			e.printStackTrace();

			// Add error message to the model
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			return "error"; // Redirect to a Thymeleaf template named 'error.html'
		}
	}

	@GetMapping("/registrationForm")
	public String showRegistrationForm() {
		return "registration"; // Maps to a Thymeleaf template named 'registration.html'
	}

	@GetMapping("/getDependentRegs")
	public List<Dependent> getDependentRegs(@RequestParam String loginId) {
		return registrationService.getDependentRegs(loginId); // Maps to a Thymeleaf template named 'registration.html'
	}

	@GetMapping("/getUserDetails")
	@ResponseBody
	public LoginUserDetail loginUserDetail(@RequestParam String emailAddress) {
		System.out.println("emailAddress is " + emailAddress);
		String url = "jdbc:mysql://10.32.0.44:3306/equipcms-ppkp"; //staging
		//	String url = "jdbc:mysql://10.10.32.154:3306/equipcmsppkp"; // prod
		String user = "ilmuweb"; // Change to your username
		String password = "ilmuweb"; // Change to your password
		LoginUserDetail loginUserDetail = new LoginUserDetail();
		// JDBC Connection
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement()) {

			System.out.println("Connected to the database!");

			// Execute SQL Query
			String query = "SELECT * FROM contact_ where emailAddress=" + "'" + emailAddress + "'"; // Change to your
			// table name
			ResultSet rs = stmt.executeQuery(query);

			// Process Result
			while (rs.next()) {
				loginUserDetail.setFirstName(rs.getString("firstName"));
				loginUserDetail.setLastName(rs.getString("lastName"));
				loginUserDetail.setEmailAddress(rs.getString("emailAddress"));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				String dateEnrolledStr = (rs.getString("birthday") != null)
						? rs.getString("birthday").replace("-", "").split(" ")[0]
						: "20000101";
				LocalDate dateEnrolled = LocalDate.parse(dateEnrolledStr, formatter);
				loginUserDetail.setDOB(dateEnrolled);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginUserDetail;
	}

	@GetMapping("/getSSOUserDetails")
	@ResponseBody
	public SSOApiResponse getSSODetails(@RequestParam String emailAddress)
			throws JsonMappingException, JsonProcessingException {
		String url = "https://api.ppj.gov.my/api/call/5?fid=52&token=bhnod0tidzd4czfrueduttcwoujwnm9sufphzuo5vww4zw1py2vlng==&ref="
				+ emailAddress;
		//RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		SSOApiResponse apiResponse = objectMapper.readValue(response.getBody(), SSOApiResponse.class);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String dateEnrolledStr = (apiResponse.getUser().getDOBBirth() != null)
				? apiResponse.getUser().getDOBBirth().replace("-", "")
				: "20000101";
		LocalDate dateEnrolled = LocalDate.parse(dateEnrolledStr, formatter);
		System.out.println("Status: " + apiResponse.getStatus());
		System.out.println("User Name: " + apiResponse.getUser().getName());
		System.out.println("User Email: " + apiResponse.getUser().getEmail());
		System.out.println("City: " + apiResponse.getUser().getCity());

		System.out.println("User State: " + apiResponse.getUser().getState());
		System.out.println("Bander: " + apiResponse.getUser().getBANDAR());
		apiResponse.getUser().setDOB(dateEnrolled);

		return apiResponse;
	}

	@GetMapping("/checkDuplicateNoKP")
	public ResponseEntity<Map<String, Object>> checkDuplicateNoKP(@RequestParam String noKP) {
		boolean isDuplicate = registrationService.isNoKPRegistered(noKP);
		System.out.println("isDuplicate is " + isDuplicate);

		Map<String, Object> response = new HashMap<>();
		if (isDuplicate) {
			response.put("duplicate", true);
			response.put("message",
					"Maklumat ahli anda telah didaftarkan dalam sistem kami. Anda tidak perlu melakukan pendaftaran semula");
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(response);
		} else {
			response.put("duplicate", false);
			response.put("message", "No. KP Tanggungan is available.");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@GetMapping("/checkDuplicateNoKPDep")
	public ResponseEntity<Map<String, Object>> checkDuplicateNoKPDepedent(@RequestParam String noKP) {
		boolean isDuplicate = registrationService.isNoKPRegisteredDepedent(noKP);

		Map<String, Object> response = new HashMap<>();
		if (isDuplicate) {
			response.put("duplicate", true);
			response.put("message",
					"Maklumat ahli anda telah didaftarkan dalam sistem kami. Anda tidak perlu melakukan pendaftaran semula");
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(response);
		} else {
			response.put("duplicate", false);
			response.put("message", "No. KP Tanggungan is available.");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@GetMapping("/FindCodeDescription")
	@ResponseBody
	public String stateCodesecription(@RequestParam String code) {
		String description = "";
		String url = "jdbc:mysql://10.10.0.112:3306/wilmuppkp"; // prod
		String user = "ilmuweb"; // Change to your username
		String password = "ilmuweb"; // Change to your password

		try (Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement()) {

			System.out.println("Connected to the database!");

			// Execute SQL Query
			String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE CODE =" + code; // Change to your
			// table name
			ResultSet rs = stmt.executeQuery(query);

			// Process Result
			while (rs.next()) {
				description = rs.getString("DESCRIPTION");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return description;
	}

	public ResponseEntity<APITokenResponse> getToken(PaymentAccessPayload payload) {
		String url = "https://ppkdev.ppj.gov.my:8080/pfxp/pay-api/pay/access";
		return restTemplate.postForEntity(url,payload.getPayload(), APITokenResponse.class);
	}
}
