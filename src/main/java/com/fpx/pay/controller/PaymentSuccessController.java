package com.fpx.pay.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentSuccessController {
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
    @GetMapping("/payment/indirectpayment")
    public String showSuccessPage(
            @RequestParam(required = false) String txnId,
            @RequestParam(required = false) String orderNo,
            @RequestParam(required = false) String amount,
            @RequestParam(required = false) String txnTime,
            @RequestParam(required = false) String status,
            Model model, HttpServletRequest request) {
        
        model.addAttribute("txnId", txnId);
        model.addAttribute("orderNo", orderNo);
        model.addAttribute("amount", amount);
        model.addAttribute("txnTime", txnTime);
        model.addAttribute("status", status != null ? status : "SUCCESS");
        int nextIndex = getNextCount();
       String userId="";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
                if (cookie.getName().equals("patronId")) {
                	userId= cookie.getValue();
                }
            }
        }
      boolean  bSuccessful =  insertRETRXN(nextIndex, null, amount, userId,
    		  userId + "_ADMIN", txnTime, orderNo,txnId);
        return "payment-success";
    }
    public static String getTodaySystemDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.now().format(formatter);
    }
    
    public boolean insertRETRXN(int iCounter, String msCode, String fee, String patronid, String recordedBy, String year, String sgenerateBillno,String txnId) {
        // Creating maps for string and integer values
        Map<String, String> valueStr = new HashMap<>();
        Map<String, Integer> valueInt = new HashMap<>();

        // Setting values for the insert query
        valueInt.put("RE01TXNO", iCounter);
        valueStr.put("RE01CODE", msCode);
        valueStr.put("RE01DATE", getTodaySystemDate());
        valueStr.put("RE01AMT", fee);
        valueStr.put("RE01PDAMT", "0.00");
        valueStr.put("RE01STAT", "0");
        valueStr.put("RE01UPDREF", "0");
        valueStr.put("RE01PATR", patronid);
        valueStr.put("RE01REFER", getTodaySystemDate()+ ":" + year);
        valueStr.put("RE01OFFID", recordedBy);
        valueInt.put("RE01CICOUNTER", 0);
        valueStr.put("RE01BILLNO", sgenerateBillno);

        // Using a helper method to build an SQL insert query
       // String query = createInsertQuery("RETRXN", valueStr, valueInt, null);
       String query = updateTableQuery("RETRXN", valueStr, valueInt, null,patronid, txnId,sgenerateBillno);

        // Running the query and returning success status
        return jdbcTemplate.update(query) > 0;
    }
    
    public int getNextCount() {
        String sql = "SELECT COUNT(*) FROM my_table";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null ? count : 0) + 1;
    }
    public static String createInsertQuery(String tableName, Map<String, String> valueStr, Map<String, Integer> valueInt, Map<String, Object> valueOther) {
        StringJoiner columns = new StringJoiner(", ");
        StringJoiner values = new StringJoiner(", ");

        // Adding String values (valueStr map)
        for (Map.Entry<String, String> entry : valueStr.entrySet()) {
            columns.add(entry.getKey());
            values.add("'" + entry.getValue() + "'");  // Wrapping string values in quotes
        }

        // Adding Integer values (valueInt map)
        for (Map.Entry<String, Integer> entry : valueInt.entrySet()) {
            columns.add(entry.getKey());
            values.add(String.valueOf(entry.getValue()));  // No quotes for integers
        }

        // If there are other values, we can handle them similarly (valueOther map)
        if (valueOther != null) {
            for (Map.Entry<String, Object> entry : valueOther.entrySet()) {
                columns.add(entry.getKey());
                values.add("'" + entry.getValue().toString() + "'");  // Assuming values are strings or simple objects
            }
        }

        // Constructing the INSERT SQL query
        return String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns.toString(), values.toString());
    }
    public String updateTableQuery(String tableName, Map<String, String> valueStr, Map<String, Integer> valueInt, Map<String, Object> valueOther,String patronId,String referenceId, String billNumber) {
    	       StringJoiner columns = new StringJoiner(", ");
            StringJoiner values = new StringJoiner(", ");

            // Adding String values (valueStr map)
            for (Map.Entry<String, String> entry : valueStr.entrySet()) {
                columns.add(entry.getKey());
                values.add("'" + entry.getValue() + "'");  // Wrapping string values in quotes
            }

            // Adding Integer values (valueInt map)
            for (Map.Entry<String, Integer> entry : valueInt.entrySet()) {
                columns.add(entry.getKey());
                values.add(String.valueOf(entry.getValue()));  // No quotes for integers
            }

            // If there are other values, we can handle them similarly (valueOther map)
            if (valueOther != null) {
                for (Map.Entry<String, Object> entry : valueOther.entrySet()) {
                    columns.add(entry.getKey());
                    values.add("'" + entry.getValue().toString() + "'");  // Assuming values are strings or simple objects
                }
            }
			return String.format("UPDATE"+tableName+"set RE01REFER="+referenceId +"where RE01BILLNO="+billNumber);

    }
    
} 