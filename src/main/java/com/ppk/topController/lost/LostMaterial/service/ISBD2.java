package com.ppk.topController.lost.LostMaterial.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ISBD2 {

	@Autowired
	JdbcTemplate jdbcTemplate;
	ISBD2(){}
    // Use dependency injection to initialize JdbcTemplate
    public ISBD2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private String GL23PUNCT;
    private String GL23TAG;
    private String GL23SUBF;

    public ISBD2(String GL23PUNCT, String GL23TAG, String GL23SUBF) {
        this.jdbcTemplate = new JdbcTemplate();
		this.GL23PUNCT = GL23PUNCT;
        this.GL23TAG = GL23TAG;
        this.GL23SUBF = GL23SUBF;
    }

    public String getGL23PUNCT() {
        return GL23PUNCT;
    }

    public String getGL23TAG() {
        return GL23TAG;
    }

    public String getGL23SUBF() {
        return GL23SUBF;
    }

    /**
     * Retrieves a list of ISBD2 punctuation mappings based on the given `GL17TAG`.
     * @throws DataAccessException 
     */
    public List<ISBD2> getPunctuation(String GL17TAG) throws SQLException, DataAccessException {
    	String query = "SELECT GL23TAG, GL23SUBF, GL23PUNCT " +
                "FROM GLMSUBF, GLTAGP " +
                "WHERE GL23MARC = 'U' " +
                "AND GL17MARC = GL23MARC " +
                "AND GL17TAG = GL23TAG " +
                "AND GL17TAG = ?";

        return jdbcTemplate.query(query, new Object[]{GL17TAG}, rowMapper());
    }

    private static RowMapper<ISBD2> rowMapper() {
        return (rs, rowNum) -> {
            String punctuation = rs.getString("GL23PUNCT");
            if (punctuation != null && !punctuation.isEmpty()) {
                punctuation = asciiconvert(punctuation);
            } else {
                punctuation = " ";
            }
            return new ISBD2(punctuation, rs.getString("GL23TAG"), rs.getString("GL23SUBF"));
        };
    }

    /**
     * Converts a raw string into a formatted string using ISBD2 punctuation rules.
     */
    public static String getSubfield2(String raw, List<ISBD2> isbd2List) {
        StringBuilder result = new StringBuilder();
        if (raw != null && raw.contains("|")) {
            String[] rawArray = raw.split("\\|");
            for (int i = 1; i < rawArray.length; i++) {
                String subfield = rawArray[i].substring(0, 1);
                for (ISBD2 isbd2 : isbd2List) {
                    if (isbd2.getGL23SUBF().equals(subfield)) {
                        subfield = isbd2.getGL23PUNCT();
                        String data = subfield + rawArray[i].substring(1);
                        result.append(data);
                    }
                }
            }
        } else {
            result.append(raw);
        }
        return result.toString().trim();
    }

    /**
     * Converts octal strings into their ASCII character equivalents.
     */
    public static String asciiconvert(String punct) {
        StringBuilder result = new StringBuilder();
        int n = punct.length() / 3;
        for (int i = 0; i < n; i++) {
            String octal = punct.substring(i * 3, (i + 1) * 3);
            char cOctal = (char) Integer.parseInt(octal, 8);
            result.append(cOctal);
        }
        return result.toString();
    }
}
