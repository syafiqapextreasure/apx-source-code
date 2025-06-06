package com.ppk.topController.membership.renual.service;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.ppk.topController.membership.renual.entity.RenewFee;
import com.ppk.topController.membership.renual.entity.RenewalMembershipData;
 

@Service
public class RenewFeeService {
	 @Autowired
	    private JdbcTemplate jdbcTemplate;

	    public List<RenewFee> getRenewalFeeByPatronId(String patronId) {
	        String sql = "SELECT GL07RENEWFEE, GL07RENEWGRC " +
	                     "FROM GLCATE " +
	                     "WHERE GL07CATE = (SELECT GL14CATE FROM GLPATR WHERE GL14PATR = ?)";

	        return jdbcTemplate.query(sql, new Object[]{patronId}, (rs, rowNum) -> {
	            String renewFee = rs.getString("GL07RENEWFEE");
	            String renewGrc = rs.getString("GL07RENEWGRC");
	            return new RenewFee(renewFee, renewGrc);
	        });
	    }
	    
	    public List<RenewalMembershipData> getMembershipData(String patronid) {
	        String sql = "SELECT \r\n"
	        		+ "    GL14PATR, \r\n"
	        		+ "    GL14NAME, \r\n"
	        		+ "    GL14IMG, \r\n"
	        		+ "    GL14ADD1, \r\n"
	        		+ "    GL14ADD2, \r\n"
	        		+ "    GL14ADD3, \r\n"
	        		+ "    GL14CODE, \r\n"
	        		+ "    DESCRIPTION, \r\n"
	        		+ "    GL14MEMDATE, \r\n"
	        		+ "    GL14EXPDATE, \r\n"
	        		+ "    GL14OTEL, \r\n"
	        		+ "    GL14MTEL, \r\n"
	        		+ "    GL14IPADD, \r\n"
	        		+ "    GL14SUSPEND, \r\n"
	        		+ "    GL02NAME, \r\n"
	        		+ "    GL14DEPT, \r\n"
	        		+ "    GL11NAME, \r\n"
	        		+ "    GL14STAT, \r\n"
	        		+ "    GL08DESC, \r\n"
	        		+ "    GL07DESC, \r\n"
	        		+ "    GL14DOB, \r\n"
	        		+ "    GL14SEX, \r\n"
	        		+ "    GL14RELID, \r\n"
	        		+ "    GL14PARENTID, \r\n"
	        		+ "    MAX(CI02CDATE) AS LastChargeDate, \r\n"
	        		+ "    MAX(CI02DIDATE) AS LastReturnDate, \r\n"
	        		+ "    MAX(CI02RDATE) AS LastRenewDate, \r\n"
	        		+ "    \r\n"
	        		+ "    -- Assuming these columns are from some logic or calculation\r\n"
	        		+ "    COUNT(CASE WHEN CI02FLAG = 'C' THEN 1 END) AS ItemsChargedTD,\r\n"
	        		+ "    COUNT(CASE WHEN CI02FLAG = 'D' AND CI02RDATE IS NULL THEN 1 END) AS LateReturnsTD,\r\n"
	        		+ "    COUNT(CASE WHEN CI02FLAG = 'L' THEN 1 END) AS LostItems,\r\n"
	        		+ "    COUNT(CASE WHEN YEAR(CI02CDATE) = YEAR(CURRENT_DATE) THEN 1 END) AS ItemsChargedYTD,\r\n"
	        		//+ "    SUM(CASE WHEN CI02FINESPAYED > 0 THEN CI02FINESPAYED ELSE 0 END) AS FinesPaid,\r\n"
	        		+ "    COUNT(CASE WHEN YEAR(CI02RDATE) = YEAR(CURRENT_DATE) THEN 1 END) AS LateReturnsYTD\r\n"
	        		+ "\r\n"
	        		+ "FROM \r\n"
	        		+ "    GLPATR \r\n"
	        		+ "LEFT JOIN \r\n"
	        		+ "    FNDCODE ON GL14CODE = FCODE AND CODE = 'M' \r\n"
	        		+ "LEFT JOIN \r\n"
	        		+ "    GLGRMA ON GL02GRP = GL14GRID \r\n"
	        		+ "LEFT JOIN \r\n"
	        		+ "    GLSTAT ON GL08STAT = GL14STAT \r\n"
	        		+ "LEFT JOIN \r\n"
	        		+ "    GLCATE ON GL07CATE = GL14CATE \r\n"
	        		+ "LEFT JOIN \r\n"
	        		+ "    CICIRC ON GL14PATR = CI02PATR \r\n"
	        		+ "LEFT JOIN \r\n"
	        		+ "    GLDEPT ON GL11DEPT = GL14DEPT \r\n"
	        		+ "WHERE \r\n"
	        		+ "    UPPER(GL14PATR) = UPPER(?) \r\n"
	        		+ "GROUP BY \r\n"
	        		+ "    GL14PATR, DESCRIPTION;\r\n"
	        		+ "";

	        return jdbcTemplate.query(sql, new Object[]{patronid}, new RowMapper<RenewalMembershipData>() {
	        //	@Override
	        	public RenewalMembershipData mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	    String patronID = rs.getString("GL14PATR");
	        	    String name = rs.getString("GL14NAME");
	        	    String address = rs.getString("GL14ADD1") + "\n" + rs.getString("GL14ADD2") + "\n" + rs.getString("GL14ADD3");
	        	    String dateEnrolled = rs.getString("GL14MEMDATE");
	        	    String expiryDate = rs.getString("GL14EXPDATE");
	        	    String contactNo = rs.getString("GL14MTEL");
	        	    String email = rs.getString("GL14IPADD");

	        	    // Handle additional fields from the query
	        	    int itemsChargedTD = rs.getInt("ItemsChargedTD");
	        	    String lastChargeDate = rs.getString("LastChargeDate");
	        	    int lateReturnsTD = rs.getInt("LateReturnsTD");
	        	    String lastReturnDate = rs.getString("LastReturnDate");
	        	    int lostItems = rs.getInt("LostItems");
	        	    String lastRenewDate = rs.getString("LastRenewDate");
	        	    String suspension = rs.getString("GL14SUSPEND");
	        	  //  BigDecimal outstanding = rs.getBigDecimal("Outstanding");
	        	    int itemsChargedYTD = rs.getInt("ItemsChargedYTD");
	        	   // String finesPaid = rs.getString("FinesPaid");
	        	    int lateReturnsYTD = rs.getInt("LateReturnsYTD");

	        	    String groupID = rs.getString("GL02NAME");
	        	    String department = rs.getString("GL11NAME");
	        	    String codeStatus = rs.getString("GL14STAT");
	        	    String status = rs.getString("GL08DESC");
	        	    String category = rs.getString("GL07DESC");
	        	    String dob = rs.getString("GL14DOB");
	        	    String gender =rs.getString("GL14SEX")!=null && rs.getString("GL14SEX").equalsIgnoreCase("F") ? "Female" : "Male";
                    System.out.println("result for the "+gender);
	        	    return new RenewalMembershipData(
	        	            patronID, name, address, dateEnrolled, expiryDate, contactNo, email,
	        	            itemsChargedTD, lastChargeDate, lateReturnsTD, lastReturnDate, 
	        	            lostItems, lastRenewDate, suspension, itemsChargedYTD,
	        	            lateReturnsYTD, groupID, department, codeStatus, status, 
	        	            category, dob, gender
	        	    );
	                
	                
//	                String patr = Handler.chkIsNull(rs.getString("GL14PATR"));
//	                String patrname = Handler.chkIsNull(rs.getString("GL14NAME"));
//	                String add1 = Handler.chkIsNull(rs.getString("GL14ADD1"));
//	                String add2 = Handler.chkIsNull(rs.getString("GL14ADD2"));
//	                String add3 = Handler.chkIsNull(rs.getString("GL14ADD3"));
//	                String code = Handler.chkIsNull(rs.getString("GL14CODE"));
//	                String town = Handler.chkIsNull(rs.getString("DESCRIPTION"));
//	                String address = String.valueOf(add1) + "\n" + add2 + "\n" + add3 + "\n" + code + " " + town;
//	                String telno = Handler.chkIsNull(rs.getString("GL14MTEL"));
//	                if (!"".equals(telno) && !" ".equals(telno))
//	                  telno = String.valueOf(telno) + " (O)"; 
//	                String Suspension = Handler.chkIsNull(rs.getString("GL14SUSPEND"));
//	                if (!"".equals(Suspension))
//	                  Suspension = "0"; 
//	                String gender = Handler.chkIsNull(rs.getString("GL14SEX"));
//	                if (gender.equalsIgnoreCase("F")) {
//	                  gender = "Female";
//	                } else if (gender.equalsIgnoreCase("M")) {
//	                  gender = "Male";
//	                } 
//	                if (!"".equals(patr)) {
//	                  borrowed = BorrowedToDate.GetBorrowedToDate(patr);
//	                  lateReturn = LateReturn.GetLateReturn(patr);
//	                  lostItem = LostItemCount.GetLostItemCount(patr);
//	                  outstanding = calculateOutstandingAmount.calculateOutstandingAmount(patronid);
//	                  borrowedToYear = BorrowedToYear.GetBorrowedToYear(patr);
//	                  finesPaid = FinesCollected.GetFinesCollected(patr);
//	                  lateReturnsYear = LateReturnToYear.GetLateReturnToYear(patr);
//	                } 
//	                RenewalMembershipData getMembership = new RenewalMembershipData(
//	                    patr, 
//	                    patrname, 
//	                    address, 
//	                    Handler.chkIsNull(DateTimeFormatter.DBToDisplayFormat(rs.getString("GL14MEMDATE"))), 
//	                    Handler.chkIsNull(DateTimeFormatter.DBToDisplayFormat(rs.getString("GL14EXPDATE"))), 
//	                    telno, 
//	                    Handler.chkIsNull(rs.getString("GL14IPADD")), 
//	                    borrowed, 
//	                    Handler.chkIsNull(DateTimeFormatter.DBToDisplayFormat(rs.getString("LastChargeDate"))), 
//	                    
//	                    lateReturn, 
//	                    Handler.chkIsNull(DateTimeFormatter.DBToDisplayFormat(rs.getString("LastReturnDate"))), 
//	                    
//	                    lostItem, 
//	                    Handler.chkIsNull(DateTimeFormatter.DBToDisplayFormat(rs.getString("LastRenewDate"))), 
//	                    
//	                    Suspension, 
//	                    outstanding, 
//	                    borrowedToYear, 
//	                    finesPaid, 
//	                    lateReturnsYear, 
//	                    Handler.chkIsNull(rs.getString("GL02NAME")), 
//	                    Handler.chkIsNull(rs.getString("GL11NAME")), 
//	                    Handler.chkIsNull(rs.getString("GL14STAT")), 
//	                    Handler.chkIsNull(rs.getString("GL08DESC")), 
//	                    Handler.chkIsNull(rs.getString("GL07DESC")), 
//	                    Handler.chkIsNull(DateTimeFormatter.DBToDisplayFormat(rs.getString("GL14DOB"))), 
//	                    gender);
//	                list.add(getMembership);
//	           
//	                
//	                
//	                

	        	  
	            }
	        });
	    }
	    
	   
	    // Method to get the patron's branch
	    public String getPatronBranch(String patronId) {
	        String sql = "SELECT GL14BRNC FROM GLPATR WHERE UPPER(GL14PATR) = UPPER(?)";
	        List<String> results = jdbcTemplate.queryForList(sql, String.class, patronId);
             System.out.println("results is "+results);
	        if (results.isEmpty()) {
	            return null; // or handle the case when no results are found
	        } else {
	        	System.out.println("results.get( is "+results.get(0));
	            return results.get(0); // return the first result
	        }
//	        try {
//	            return jdbcTemplate.queryForObject(sql, String.class, patronId);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            // Return null or handle it according to your needs
//	            return null;
//	        }
	    }
	    
	    // Method to update patron's expiration date
	    public boolean updatePatronExpdate(String loginId, String expDate) {
	        Map<String, String> valueStr = new HashMap<>();
	        Map<String, String> condition = new HashMap<>();
	        
	        // Set the update values and conditions
	        valueStr.put("GL14EXPDATE", expDate);
	        valueStr.put("GL14STAT", "1"); // Set status to "1" (Memuaskan) to indicate successful renewal
	        condition.put("GL14PATR", loginId);

	        // Log the update values for debugging
	        System.out.println("EXPIRY DATE UPDATE: Patron=" + loginId + ", New Expiry Date=" + expDate + ", New Status=1 (Memuaskan)");

	        // Construct the SQL query
	        String query = createUpdateQuery("GLPATR", valueStr, condition);
	        System.out.println("EXPIRY DATE SQL: " + query);
	        
	        // Execute the update query and return whether it was successful
	        try {
	            int rowsAffected = jdbcTemplate.update(query);
	            System.out.println("EXPIRY DATE RESULT: Rows affected=" + rowsAffected);
	            return rowsAffected > 0;
	        } catch (Exception e) {
	            System.err.println("EXPIRY DATE ERROR: " + e.getMessage());
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
 

	        // Helper method to build an update SQL query dynamically
	        public String createUpdateQuery(String tableName, Map<String, String> setValues, 
	                                                Map<String, String> whereConditions) {
	            StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
	            
	            // Build the SET clause
	            for (Map.Entry<String, String> entry : setValues.entrySet()) {
	                query.append(entry.getKey())
	                     .append(" = '")
	                     .append(entry.getValue())
	                     .append("', ");
	            }
	            query.delete(query.length() - 2, query.length());  // Remove last comma
	            
	            // Build the WHERE clause
	            if (whereConditions != null && !whereConditions.isEmpty()) {
	                query.append(" WHERE ");
	                for (Map.Entry<String, String> entry : whereConditions.entrySet()) {
	                    query.append(entry.getKey())
	                         .append(" = '")
	                         .append(entry.getValue())
	                         .append("' AND ");
	                }
	                query.delete(query.length() - 4, query.length());  // Remove last "AND"
	            }
	            
	            return query.toString();
	        }
	        
	     // Method to insert audit record
	        public void insertAudit(String module, String code, String refNo, String user) throws UnknownHostException {
	            Map<String, String> valueStr = new HashMap<>();
	            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
	            DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
	            LocalDateTime now = LocalDateTime.now();

	            // Populating valueStr map with audit information
	            valueStr.put(module + "68ACTCODE", code);
	            valueStr.put(module + "68PATRONID", user);
	            valueStr.put(module + "68DATE", date.format(now));
	            valueStr.put(module + "68TIME", time.format(now));
	            valueStr.put(module + "68REFER", refNo);
	            valueStr.put(module + "68TEMNID", getLocalIPAdd());

	            // Creating the insert SQL query dynamically
	            String insertQuery = createInsertQuery(module + "AUDIT", valueStr);

	            try {
	                // Running the SQL query using JdbcTemplate
	                jdbcTemplate.update(insertQuery);
	            } catch (Exception e) {
	                e.printStackTrace();
	                // Handle exception accordingly
	            }
	        }

	        // Helper method to get the local IP address (you can customize this logic)
	        private String getLocalIPAdd2() throws UnknownHostException {
	            InetAddress inetAddress = InetAddress.getLocalHost();
	            return inetAddress.getHostAddress();
	        }

	        // Helper method to create the SQL insert query dynamically
	        private String createInsertQuery(String tableName, Map<String, String> valueStr) {
	            StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
	            StringBuilder values = new StringBuilder("VALUES (");

	            for (Map.Entry<String, String> entry : valueStr.entrySet()) {
	                query.append(entry.getKey()).append(", ");
	                values.append("'").append(entry.getValue()).append("', ");
	            }

	            query.delete(query.length() - 2, query.length()); // Remove last comma
	            values.delete(values.length() - 2, values.length()); // Remove last comma

	            query.append(") ").append(values).append(")");
	            return query.toString();
	        }
	     // Method to insert data into GLPATA table
	        public boolean insertGLPATA(String patronId, String colmm, String expDate, String newExpDate, String recBy, String mode) throws UnknownHostException {
	            Map<String, String> valueStr = new HashMap<>();
	            valueStr.put("GL72PATR", patronId);
	            valueStr.put("GL72DATE", getTodaySystemDate());
	            valueStr.put("GL72TIME", getDisCurrTime());
	            valueStr.put("GL72COLM", colmm);
	            valueStr.put("GL72FVAL", expDate);
	            valueStr.put("GL72TVAL", newExpDate);
	            valueStr.put("GL72RECBY", recBy);
	            valueStr.put("GL72MODE", mode);
	            valueStr.put("GL72TEMNID", getLocalIPAdd2());

	            // Dynamically create SQL insert query
	            String insertQuery = createInsertQuery2("GLPATA", valueStr);

	            try {
	                // Execute the query using JdbcTemplate's update method
	                int rowsAffected = jdbcTemplate.update(insertQuery);
	                return rowsAffected > 0;  // Return true if at least one row is inserted
	            } catch (Exception e) {
	                e.printStackTrace();
	                return false;  // Return false if an exception occurs
	            }
	        }

	        // Helper method to get today's date in a specific format (yyyyMMdd)
	        private String getTodaySystemDate() {
	            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	        }

	        // Helper method to get the current time in a specific format (HHmmss)
	        private String getDisCurrTime() {
	            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
	        }

	        // Helper method to get the local IP address
	        private String getLocalIPAdd() throws UnknownHostException {
	            InetAddress inetAddress = InetAddress.getLocalHost();
	            return inetAddress.getHostAddress();
	        }

	        // Helper method to create the SQL insert query dynamically
	        private String createInsertQuery2(String tableName, Map<String, String> valueStr) {
	            StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
	            StringBuilder values = new StringBuilder("VALUES (");

	            // Build the column names and corresponding values
	            for (Map.Entry<String, String> entry : valueStr.entrySet()) {
	                query.append(entry.getKey()).append(", ");
	                values.append("'").append(entry.getValue()).append("', ");
	            }

	            // Remove the last comma and space from both the column names and values
	            query.delete(query.length() - 2, query.length());
	            values.delete(values.length() - 2, values.length());

	            // Complete the SQL query
	            query.append(") ").append(values).append(")");

	            return query.toString();
	        }
}
