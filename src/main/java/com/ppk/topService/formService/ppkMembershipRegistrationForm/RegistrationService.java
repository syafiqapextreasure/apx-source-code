package com.ppk.topService.formService.ppkMembershipRegistrationForm;

import java.io.IOException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.ppk.topController.membership.renual.service.GetFndGlnumbService;
import com.ppk.topEntity.formsEntity.dto.Dependent;
import com.ppk.topEntity.formsEntity.registration.Encrypter;

@Service
public class RegistrationService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	GetFndGlnumbService getFndGlnumbService;

	public void handleRegistration(String namaAhli, String inputDOB, String alamat, String poskod, String negeri,
			String phone, MultipartFile picImage, MultipartFile paspekerja, String statusUser,
			MultipartFile picImageVal, String bandar, String TanggunganIdPenggunaTanggungan,
			String Tanggungan_Id_Pengguna_Portal_PPJ, String branchLocation, String noKP, String citizen, String Nation,
			String IsFamiltyMember,String emailAddress, String updatedCat) throws SQLException, IOException {
		UUID ic = UUID.randomUUID();
		// Determine category and status
		// String cate = determineCategory(finalcate);
		String statusis = "15";
		String negeriSuratIs = "";
		String raceIs = "";
//        if (!negeriSurat.equals("null")) {
//            negeriSuratIs = negeriSurat;
//        }
//
//        if (!race.equals("null")) {
//            raceIs = race;
//        }
//
//        String finalcateIs = "";
//
//        if (finalcate.equals("pasangan")) {
//            finalcateIs = idPsgn;
//        } else if (finalcate.equals("Kanak-kanak") || finalcate.equals("parent")) {
//            finalcateIs = idparent;
//        } else {
//            idparent = null;
//        }
		String icIs2 = "";
		String icIs = "";
		if (ic.toString().length() == 12) {

			icIs = noKP.toString();//ic.toString();
			icIs2 = null;
		} else {
			icIs = null;
			icIs2 =noKP.toString(); //ic.toString();
		}
		String GL14PATRONID = "";
//		if (!radioPasanganStaff.equals("null") && radioPasanganStaff.equals("Y")) {
//
//			GL14PATRONID = idPsgn;
//		}

		String radioPasanganStaffIs = "";
//		if (!radioPasanganStaff.equals("null") && radioPasanganStaff.equals("Y")) {
//			radioPasanganStaffIs = idPsgn;
//		}
		UUID uuid = UUID.randomUUID();
		System.out.println("Uniue id is " + uuid);
		String GL14PASWIs = Encrypter.encrypt(uuid.toString().substring(uuid.toString().length() - 4));
		System.out.println("GL14PASWIs is " + GL14PASWIs);
		InputStream inputStream = null;
		InputStream inputStream2 = null;
		InputStream inputStream3 = null;
		InputStream inputStream4 = null;
		InputStream inputStream5 = null;
		inputStream2 = paspekerja.getInputStream();

		if (picImage != null) {
			System.out.println(String.valueOf(picImage.getName()) + " getName");
			System.out.println(String.valueOf(picImage.getSize()) + " getSize");
			System.out.println(String.valueOf(picImage.getContentType()) + " getContentType");
			inputStream = picImage.getInputStream();
			System.out.println(inputStream + " inputStreamfilePartImgae");
		}
		if (paspekerja != null) {
			System.out.println(String.valueOf(paspekerja.getName()) + " getName");
			System.out.println(String.valueOf(paspekerja.getSize()) + " getSize");
			System.out.println(String.valueOf(paspekerja.getContentType()) + " getContentType");
			inputStream2 = paspekerja.getInputStream();
			System.out.println(inputStream2 + " inputStreamfilePartpaspekerja");
		}
		/*
		 * if (kadOKU != null) { System.out.println(String.valueOf(kadOKU.getName()) +
		 * " getName"); System.out.println(String.valueOf(kadOKU.getSize()) +
		 * " getSize"); System.out.println(String.valueOf(kadOKU.getContentType()) +
		 * " getContentType"); inputStream3 = kadOKU.getInputStream();
		 * System.out.println(inputStream3 + " inputStreamfilePartkadOKU"); }
		 */
//		if (paspekerjapasangan != null) {
//			System.out.println(String.valueOf(paspekerjapasangan.getName()) + " getName");
//			System.out.println(String.valueOf(paspekerjapasangan.getSize()) + " getSize");
//			System.out.println(String.valueOf(paspekerjapasangan.getContentType()) + " getContentType");
//			inputStream4 = paspekerjapasangan.getInputStream();
//			System.out.println(inputStream4 + " inputStreamfilePartpaspekerjapasangan");
//		}
//		if (paspekerjaparent != null) {
//			System.out.println(String.valueOf(paspekerjaparent.getName()) + " getName");
//			System.out.println(String.valueOf(paspekerjaparent.getSize()) + " getSize");
//			System.out.println(String.valueOf(paspekerjaparent.getContentType()) + " getContentType");
//			inputStream5 = paspekerjaparent.getInputStream();
//			System.out.println(inputStream5 + " inputStreamfilePartpaspekerjaparent");
//		}

//		if (finalcate.equals("staff")) {
//			cate = "10";
//			statusis = "15";
//		} else if (finalcate.equals("pasangan")) {
//			cate = "12";
//			statusis = "15";
//		} else if (finalcate.equals("parent")) {
//			cate = "13";
//			statusis = "15";
//		} else if (finalcate.equals("oku Kanak-kanak")) {
//			cate = "16";
//			statusis = "15";
//		} else if (finalcate.equals("oku Remaja")) {
//			cate = "15";
//			statusis = "15";
//		} else if (finalcate.equals("oku Dewasa")) {
//			cate = "14";
//			statusis = "15";
//		} else if (finalcate.equals("Warga Emas")) {
//			cate = "17";
//			statusis = "15";
//		} else if (finalcate.equals("Kanak-kanak")) {
//			cate = "06";
//			statusis = "15";
//		} else if (finalcate.equals("Remaja")) {
//			cate = "05";
//			statusis = "15";
//		} else if (finalcate.equals("Dewasa")) {
//			cate = "04";
//			statusis = "15";
//		}
//		System.out.println("cate >  " + cate);
//		if (jantina.equals("P")) {
//			jantina = "F";
//		} else if (jantina.equals("L")) {
//			jantina = "M";
//		},
//		
		// GL14IPADD,GL14DOB,GL14ADD1,GL14CODE,GL14OFFTOWN,GL14MTEL,GL14TOWN,Tanggungan_Id_Pengguna_Tanggungan,Tanggungan_Id_Pengguna_Portal_PPJ,Lokasi_PPK_Pilihan,No_KP,Warganegara,Nation,Adakah_ahli_keluarga_anda_merupakan
		// GL14NEWIC, GL14OLDIC, GL14DEPOSIT, GL14MEMFEE, GL14PATRONID, GL14PASW
		// Prepare SQL insert queries
		String sqlInsert = "INSERT INTO GLPATR (GL14IPADD,GL14DOB, GL14BRNC, GL14CATE, GL14NAME, GL14PATR, "
				+ "GL14ADD1, GL14ADD2, GL14ADD3, GL14CODE, GL14TOWN, GL14OFFADD1, GL14OFFADD2, GL14OFFADD3, "
				+ "GL14OFFCODE, GL14OFFTOWN, GL14MTEL, GL14RACE, GL14STAT, GL14GRID, GL14RMVD, GL14DATEREC, "
				+ "GL14RECBY, GL14IMG, GL14RELID, GL14NEWIC, GL14OLDIC, GL14DEPOSIT, GL14MEMFEE, GL14PATRONID, "
				+ "GL14PASW,Tanggungan_Id_Pengguna_Tanggungan,Tanggungan_Id_Pengguna_Portal_PPJ,Lokasi_PPK_Pilihan,No_KP,Warganegara,Nation,Adakah_ahli_keluarga_anda_merupakan) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";

		// Insert into the database using jdbcTemplate  ic.toString().substring(20)
		System.out.println("emailAddress is "+emailAddress);
		int resumt = jdbcTemplate.update(sqlInsert, emailAddress, inputDOB, branchLocation, updatedCat, namaAhli, noKP,
				alamat, null, null, poskod, negeri, null, null, null, null, negeriSuratIs, phone, raceIs, statusis,
				"99", "Y", "20240112", "EREGISTER", null, null, noKP, noKP, 0.0000, 0.0000, GL14PATRONID, GL14PASWIs,
				TanggunganIdPenggunaTanggungan, Tanggungan_Id_Pengguna_Portal_PPJ, branchLocation, noKP, citizen,
				Nation, IsFamiltyMember);
		System.out.println(resumt+"Tanggungan_Id_Pengguna_Portal_PPJ is "+TanggunganIdPenggunaTanggungan+ Tanggungan_Id_Pengguna_Portal_PPJ+ branchLocation+ noKP+ citizen+
				Nation+IsFamiltyMember);

		if (resumt > 0) {
			// RecordTransaction.InsertAudit(gsModule, "GLI0003", ic, "onlineform");

			insertAudit(radioPasanganStaffIs, GL14PASWIs, sqlInsert, statusUser);
			String filecount = getPatronFileCount(ic.toString());
//			int ifilecount = Integer.parseInt(filecount);
//			System.out.println("ifilecount is " + ifilecount);
//			if (ifilecount > 0)
//				deletePatrfile(filecount); 
			if (paspekerja.getContentType() != null && inputStream2 != null) {
				System.out.println("here is new update " + ic);
				int succ2 = saveFile(ic.toString(), "001", picImage);
				System.out.println("succ2 here is new update " + succ2);

				if (succ2 > 0)
					// RecordTransaction.InsertAudit(gsModule, "GLI0004", ic, "onlineform");
					insertAudit(radioPasanganStaffIs, GL14PASWIs, sqlInsert, statusUser);
			} 
//			else if (kadOKU.getContentType() != null && inputStream3 != null) {
//
//				int succ3 = saveFile(ic.toString(), "002", picImage);
//
//				if (succ3 > 0)
//					// RecordTransaction.InsertAudit(gsModule, "GLI0004", ic, "onlineform");
//					insertAudit(radioPasanganStaffIs, GL14PASWIs, sqlInsert, statusUser);
//			} 
			else if (inputStream4 != null) {

				int succ4 = saveFile(ic.toString(), "003", picImage);

				if (succ4 > 0)
					// RecordTransaction.InsertAudit(gsModule, "GLI0004", ic, "onlineform");
					insertAudit(radioPasanganStaffIs, GL14PASWIs, sqlInsert, statusUser);
			} else if (inputStream5 != null) {

				int succ5 = saveFile(ic.toString(), "004", picImage);

				if (succ5 > 0)
					// RecordTransaction.InsertAudit(gsModule, "GLI0004", ic, "onlineform");
					insertAudit(radioPasanganStaffIs, GL14PASWIs, sqlInsert, statusUser);
			}
//			if (finalcate.equals("Kanak-kanak") && !emailParent.equals("")) {
//				Map<String, String> valueStr = new HashMap<>();
//				valueStr.put("GL90PATRID", ic.toString());
//				valueStr.put("GL90PARENTID", idparent);
//				valueStr.put("GL90NAME", namaPenjaga);
//				valueStr.put("GL90EMAIL", emailParent);
//				valueStr.put("GL90ADD", alamatSuratParent);
//				valueStr.put("GL90ADD2", alamatSuratParent2);
//				valueStr.put("GL90ADD3", alamatSuratParent3);
//				valueStr.put("GL90CODE", poskodSuratParent);
//				valueStr.put("GL90TOWN", negeriSuratParent);
//				valueStr.put("GL90PHONE", phoneParent);
//				 
//				insertParentDetails(icIs, emailParent, namaPenjaga);
//			}
		}

		// // Handle file uploads
		// if (picImage != null) {
		// saveFile(ic.length()+1, picImage, "001");
		// }
		// if (paspekerja != null) {
		// saveFile(ic.length()+1, paspekerja, "002");
		// }
		// if (kadOKU != null) {
		// saveFile(ic.length()+1, kadOKU, "003");
		// }
		// if (paspekerjapasangan != null) {
		// saveFile(ic.length()+1, paspekerjapasangan, "004");
		// }
		// if (paspekerjaparent != null) {
		// saveFile(ic.length()+1, paspekerjaparent, "005");
		// }
		//
		// // Insert Parent details if applicable
		// if ("Kanak-kanak".equals(finalcate) && emailParent != null) {
		// insertParentDetails(ic, emailParent, namaPenjaga);
		// }
	}

	private String determineCategory(String finalcate) {
		switch (finalcate) {
		case "staff":
			return "10";
		case "pasangan":
			return "12";
		case "parent":
			return "13";
		case "oku Kanak-kanak":
			return "16";
		case "oku Remaja":
			return "15";
		case "oku Dewasa":
			return "14";
		case "Warga Emas":
			return "17";
		case "Kanak-kanak":
			return "06";
		case "Remaja":
			return "05";
		case "Dewasa":
			return "04";
		default:
			return "00"; // Default case
		}
	}

	private int saveFile(String ic, String fileType, MultipartFile file) throws SQLException, IOException {
		if (file != null && !file.isEmpty()) {
			String sqlFileInsert = "INSERT INTO GLPATRFILE(GL89PATR, GL89ATRR, GL89IMAGE) VALUES (?, ?, ?)";
			try (InputStream inputStream = file.getInputStream()) {
				return jdbcTemplate.update(sqlFileInsert, ic.toString().subSequence(25, ic.length()), fileType, inputStream);
			}
		}
		return 0;
	}

	private void insertParentDetails(String ic, String emailParent, String namaPenjaga) {
		Map<String, String> values = new HashMap<>();
		values.put("GL90PATRID", ic);
		values.put("GL90EMAIL", emailParent);
		values.put("GL90NAME", namaPenjaga);
		String sqlInsertParent = "INSERT INTO GLPATREXTPARENT (GL90PATRID, GL90EMAIL, GL90NAME) VALUES (?, ?, ?)";
		jdbcTemplate.update(sqlInsertParent, values.get("GL90PATRID"), values.get("GL90EMAIL"), values.get("GL90NAME"));
	}

	public String insertAudit(String sModule, String vsCode, String vsRefNo, String user) throws UnknownHostException {
		Map<String, String> valueStr = new HashMap<>();
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
		LocalDateTime now = LocalDateTime.now();

		// Populate values for the audit record
		valueStr.put(sModule + "68ACTCODE", vsCode);
		valueStr.put(sModule + "68PATRONID", user);
		valueStr.put(sModule + "68DATE", date.format(now));
		valueStr.put(sModule + "68TIME", time.format(now));
		valueStr.put(sModule + "68REFER", vsRefNo);
		valueStr.put(sModule + "68TEMNID", "127.0.0.1");

		// Build the SQL insert query dynamically
		String sql = createInsertQuery(sModule + "AUDIT", valueStr);

		try {
			// Execute the insert query
			int rowsAffected = jdbcTemplate.update(sql);
			if (rowsAffected > 0) {
				System.out.println("Audit record inserted successfully.");
				return "Success";
			} else {
				System.out.println("Failed to insert audit record.");
				return "Failure";
			}
		} catch (Exception e) {
			// System.out.println("Error inserting audit record", e);
			return "Failure";
		}
	}

	// Helper method to create the insert SQL query string
	private String createInsertQuery(String tableName, Map<String, String> values) {
		StringBuilder columns = new StringBuilder();
		StringBuilder placeholders = new StringBuilder();
		values.forEach((key, value) -> {
			if (columns.length() > 0) {
				columns.append(", ");
				placeholders.append(", ");
			}
			columns.append(key);
			placeholders.append("'").append(value).append("'");
		});

		return String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns.toString(), placeholders.toString());
	}

	public boolean deletePatrfile(String partid) {
		// Prepare the condition map
        Map<String, String> condition = Collections.singletonMap("GL89PATR", partid);

		// Build the delete query
		String query = createDeleteQuery("GLPATRFILE", condition);
		System.out.println("Generated SQL query: " + query);

		// Execute the query and return the result
		int rowsAffected = jdbcTemplate.update(query);
		return rowsAffected > 0;
	}

	private String createDeleteQuery(String table, Map<String, String> conditionMap) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("DELETE FROM ").append(table).append(" WHERE ");

		// Iterate over the conditions and append to the query
		for (Map.Entry<String, String> entry : conditionMap.entrySet()) {
			queryBuilder.append(entry.getKey()).append(" = '").append(entry.getValue()).append("'");

			// If there's another condition, add "AND"
			if (conditionMap.size() > 1) {
				queryBuilder.append(" AND ");
			}
		}

		return queryBuilder.toString();
	}

	public String getPatronFileCount(String patrid) {
		// SQL query with parameterized input (avoids SQL injection)
		String sql = "SELECT COUNT(*) AS TotalFile FROM GLPATRFILE WHERE UPPER(GL89PATR) = UPPER(?)";

		// Execute the query and retrieve the result as a String
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] { patrid }, String.class);
		} catch (Exception e) {
			// Handle exception (you can log it or rethrow a custom exception as needed)
			e.printStackTrace();
			return null;
		}
	}
	
	
	 
	 // Method to insert a single DependentReg record into the database
    public void insertDependentReg(Dependent dependentReg) {
        String sql = "INSERT INTO depedentReg (loginId, idPengguna, nokPTanggungan, hubungan, statusOKU, harga) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";



        jdbcTemplate.update(sql,
				dependentReg
						.getLoginId(),
                dependentReg.getIdPengguna(),
                dependentReg.getNokPTanggungan(),
                dependentReg.getHubungan(),
                dependentReg.getStatusOKU(),
                dependentReg.getHarga());
    }

    // Method to insert multiple DependentReg records in a batch
	@Transactional
	public void insertDependentRegs(List<Dependent> dependentRegs, String parentLoginId, String billNumber) {
		// Get parent details
		String parentSql = "SELECT GL14BRNC, GL14IPADD FROM GLPATR WHERE GL14PATR = ?";
		Map<String, Object> parentDetails = jdbcTemplate.queryForMap(parentSql, parentLoginId);

		String branchLocation = (String) parentDetails.get("GL14BRNC");
		String parentEmail = (String) parentDetails.get("GL14IPADD");

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		String currentDate = LocalDate.now().format(dateFormat);
		String expiryDate = LocalDate.now().plusYears(1).format(dateFormat);

		for (Dependent dependent : dependentRegs) {
			try {
				String icNumber = dependent.getNokPTanggungan();

				// 1. Extract birthdate from IC (first 6 digits: YYMMDD)
				String birthYearPrefix = icNumber.substring(0, 2); // YY
				String birthMonth = icNumber.substring(2, 4); // MM
				String birthDay = icNumber.substring(4, 6); // DD

				// 2. Convert to YYYY format (handling 1900s/2000s)
				int currentYearShort = LocalDate.now().getYear() % 100;
				int birthYear = Integer.parseInt(birthYearPrefix);
				birthYear = (birthYear <= currentYearShort) ? 2000 + birthYear : 1900 + birthYear;

				// 3. Format complete birthdate (YYYYMMDD)
				String formattedDOB = String.format("%04d%s%s", birthYear, birthMonth, birthDay);

				// 4. Calculate age from GL14DOB
				LocalDate dob = LocalDate.parse(formattedDOB, dateFormat);
				int age = Period.between(dob, LocalDate.now()).getYears();

				// 5. Determine category
				String category = determineCategory(age, dependent.getStatusOKU());

				// 6. Insert with properly formatted GL14DOB
				String sql = "INSERT INTO GLPATR " +
						"(GL14PATR, GL14NAME, GL14NEWIC, GL14OLDIC, GL14RELID, " +
						"GL14STAT, GL14DATEREC, GL14CATE, GL14BRNC, GL14GRID, " +
						"GL14RMVD, GL14RECBY, GL14PATRONID, GL14IPADD, " +
						"GL14MEMDATE, GL14EXPDATE, GL14DOB) " +
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				int result = jdbcTemplate.update(sql,
						icNumber,                          // GL14PATR
						dependent.getIdPengguna(),           // GL14NAME
						icNumber,                           // GL14NEWIC
						icNumber,                           // GL14OLDIC
						mapRelationshipCode(dependent.getHubungan()), // GL14RELID
						"15",                               // GL14STAT
						currentDate,                        // GL14DATEREC
						category,                           // GL14CATE (calculated)
						branchLocation,                     // GL14BRNC
						"99",                               // GL14GRID
						"N",                                // GL14RMVD
						"EREGISTER",                        // GL14RECBY
						parentLoginId,                      // GL14PATRONID
						parentEmail,                        // GL14IPADD
						currentDate,                        // GL14MEMDATE
						expiryDate,                         // GL14EXPDATE
						formattedDOB                        // GL14DOB (YYYYMMDD)
				);

				System.out.printf("Inserted %s (Age: %d) as category %s%n",
						dependent.getIdPengguna(), age, category);

			} catch (Exception e) {
				System.err.println("Failed to insert dependent: " + e.getMessage());
				throw new RuntimeException("Dependent registration failed", e);
			}
		}

		// Payment processing remains same
		BigDecimal total = dependentRegs.stream()
				.map(Dependent::getHarga)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		int iCounter = getFndGlnumbService.getGlnumb2("TRXNO");
		getFndGlnumbService.insertRETRXN(iCounter+1, null, total.toString(), parentLoginId,
				"_ADMIN", String.valueOf(new Date().getYear()), billNumber);
	}

	// Category determination using GL14DOB-based age
	private String determineCategory(int age, String okuStatus) {
		boolean isOKU = "Ya".equalsIgnoreCase(okuStatus);

		if (age < 5) return isOKU ? "16" : "00";  // Infant
		if (age <= 12) return isOKU ? "16" : "06"; // Child
		if (age <= 20) return isOKU ? "15" : "05"; // Teen
		if (age <= 54) return isOKU ? "14" : "04"; // Adult
		return "17"; // Senior (55+)
	}
	// Relationship mapping helper
	private String mapRelationshipCode(String hubungan) {
		String result;
		switch (hubungan.toLowerCase()) {
			case "anak":
				result = "01"; // Child
				break;
			case "pasangan":
				result = "02"; // Spouse
				break;
			case "ibu":
			case "bapa":
				result = "03"; // Parent
				break;
			default:
				result = "99"; // Other
				break;
		}
		return result;
	}


	// Helper method to map status
	private String mapStatus(String statusOKU) {
		return "tidak".equalsIgnoreCase(statusOKU) ? "N" : "Y";
	}
    
    // Method to insert multiple DependentReg records in a batch
    public List<Dependent> getDependentRegs(String loginId) {
    	String sql = "SELECT * FROM depedentreg WHERE loginId = ?";

    	// Query and map the result to a list of Dependent objects
    	List<Dependent> dependents = jdbcTemplate.query(sql, new Object[]{loginId}, new RowMapper<Dependent>() {
    	    @Override
    	    public Dependent mapRow(ResultSet rs, int rowNum) throws SQLException {
    	        Dependent dependent = new Dependent();
    	        dependent.setIdPengguna(rs.getString("idPengguna"));
    	        dependent.setNokPTanggungan(rs.getString("nokPTanggungan"));
    	        dependent.setHubungan(rs.getString("hubungan"));
    	        dependent.setStatusOKU(rs.getString("statusOKU"));
    	//        dependent.setHarga(rs.getBigDecimal("harga"));
    	        dependent.setLoginId(rs.getString("loginId"));
    	        // Set other fields as needed
    	        return dependent;
    	    }
    	});
		return dependents;
    }
    public String getApiResponse() {
        String url = "https://api.ppj.gov.my/api/call/5?fid=52&token=bhnod0tidzd4czfrueduttcwoujwnm9sufphzuo5vww4zw1py2vlng==&ref=mytest19845@gmail.com";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        return response.getBody();
    }
    
    public boolean isNoKPRegistered(String noKP) {
	   // String sql = "SELECT COUNT(*) FROM GLPATR WHERE GL14NEWIC OR GL14OLDIC = ?";
	    String sql = "SELECT COUNT(*) FROM GLPATR WHERE GL14PATR = ? OR GL14NEWIC = ? OR GL14OLDIC = ?";

	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class,noKP.trim(), noKP.trim(),noKP.trim());
	    
//	    System.err.println("Checking No. KP: " + noKP + " - Count: " + count);

	    return count != null && count > 0;
	}

	
	public boolean isNoKPRegisteredDepedent(String noKP) {
	    String sql = "SELECT COUNT(*) FROM depedentreg WHERE nokPTanggungan = ?";

	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, noKP.trim());
	    
//	    System.err.println("Checking No.  dep KP: " + noKP + " -dep Count: " + count);

	    return count != null && count > 0;
	}
	
}
