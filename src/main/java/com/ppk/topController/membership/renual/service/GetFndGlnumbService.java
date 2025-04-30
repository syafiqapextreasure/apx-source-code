package com.ppk.topController.membership.renual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.Handler;

@Service
public class GetFndGlnumbService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private int GL98VALUE;
    private String GL98FUNC;

    // Constructor
    public GetFndGlnumbService() {
    }

    public int getGL98VALUE() {
        return GL98VALUE;
    }

    public String getGL98FUNC() {
        return GL98FUNC;
    }

    // Method to get GL98VALUE based on function name
    public GetFndGlnumbService getFndGlnumbVal(String function) {
        updateGlnumbVal(function);
        String query = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = ?";
        GetFndGlnumbService result = jdbcTemplate.queryForObject(query, new Object[]{function}, (rs, rowNum) -> {
            GetFndGlnumbService glnumb = new GetFndGlnumbService();
            glnumb.GL98VALUE = rs.getInt("GL98VALUE");
            return glnumb;
        });
        return result;
    }

    // Method to get GL98VALUE (without updating)
    public GetFndGlnumbService getGlnumb(String function) {
        String query = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = ?";
        GetFndGlnumbService result = jdbcTemplate.queryForObject(query, new Object[]{function}, (rs, rowNum) -> {
            GetFndGlnumbService glnumb = new GetFndGlnumbService();
            glnumb.GL98VALUE = rs.getInt("GL98VALUE");
            return glnumb;
        });
        return result;
    }

    // Method to update GL98VALUE for a specific function
    public void updateGlnumbVal(String function) {
        String query = "UPDATE GLNUMB SET GL98VALUE = GL98VALUE + 1 WHERE GL98FUNC = ?";
        jdbcTemplate.update(query, function);
    }

    // Method to get GL98VALUE and return it as an integer
    public int getGlnumb2(String function) {
        String query = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{function}, Integer.class);
    }

    // Method to update GL98VALUE (increment) for a function
    public boolean updatingGLNUMB(String functionname) {
        String query = "UPDATE GLNUMB SET GL98VALUE = GL98VALUE + 1 WHERE GL98FUNC = ?";
        int rowsAffected = jdbcTemplate.update(query, functionname);
        return rowsAffected > 0;
    }
    
    // Method to update Patron status for renewal
    public boolean updatePatrStatRenewal(String loginid) {
        // Define values to update
        Map<String, String> valueStr = new HashMap<>();
        Map<String, String> condition = new HashMap<>();

        condition.put("GL14PATR", loginid);
        valueStr.put("GL14STAT", "16");

        // Create the update query dynamically
        String query = createUpdateQuery("GLPATR", valueStr, condition);
               System.out.println(valueStr+" UPDATE GLPATR isis "+condition.get("GL14PATR"));
        // Run the query using JdbcTemplate
        int rowsAffected = jdbcTemplate.update(query, valueStr.get("GL14STAT"),condition.get("GL14PATR"));
        System.out.println("rowsAffected is "+rowsAffected);
        // Return true if at least one row was affected, otherwise false
        return rowsAffected > 0;
    }

    // Method to create the SQL update query dynamically
    private String createUpdateQuery(String tableName, Map<String, String> values, Map<String, String> conditions) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE " + tableName + " SET ");

        // Build SET part of the query
        values.forEach((key, value) -> {
            queryBuilder.append(key).append(" = ?, ");
        });

        // Remove trailing comma
        queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());

        // Add WHERE clause if conditions exist
        if (conditions != null && !conditions.isEmpty()) {
            queryBuilder.append(" WHERE ");
            conditions.forEach((key, value) -> {
                queryBuilder.append(key).append(" = ? AND ");
            });
            // Remove trailing "AND"
            queryBuilder.delete(queryBuilder.length() - 4, queryBuilder.length());
        }

        return queryBuilder.toString();
    }
    
 // Method to get Patron Category description
    public String getPatronCatnDesc(String partid) {
        String query = "SELECT GL14CATE, GL07DESC " +
                       "FROM GLPATR " +
                       "LEFT JOIN GLCATE ON GL07CATE = GL14CATE " +
                       "WHERE UPPER(GL14PATR) = UPPER(?)";

        // Execute query and map result to return format
        return jdbcTemplate.queryForObject(query, new Object[]{partid}, (rs, rowNum) -> {
            String Statcode =  rs.getString("GL14CATE");
            String Statdec = rs.getString("GL07DESC");
            return "=" + Statcode + " =" + Statdec;
        });
    }
    
 // Method to get the Patron category renewal code
    public String getFndPatronRenewFee(String cate) {
        String query = "SELECT GL07RENEWALCODE FROM GLCATE WHERE GL07CATE = ?";

        // Execute query and return the renewal code
        return jdbcTemplate.queryForObject(query, new Object[]{cate}, (rs, rowNum) -> {
            return rs.getString("GL07RENEWALCODE");
        });
    }
    
    public boolean insertRETRXN(int iCounter, String msCode, String fee, String patronid, String recordedBy, String year, String sgenerateBillno) {
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
        String query = createInsertQuery("RETRXN", valueStr, valueInt, null);

        // Running the query and returning success status
        return jdbcTemplate.update(query) > 0;
    }
    
    public static String getTodaySystemDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.now().format(formatter);
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
}
