package com.ppk.topController.lost.LostMaterial.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ppk.topController.lost.LostMaterial.util.QueryParams;
import com.ppk.topController.lost.LostMaterial.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GetBibData2 {

	@Autowired
    private JdbcTemplate jdbcTemplate;
@Autowired
	ISBD2 ISBD2;
    private String data;
    private String tag;
    private int pointer;
    private int hits;
    GetBibData2(){}
    GetBibData2(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;}
    public GetBibData2(String data, int pointer, int hits, String tag) {
        this.jdbcTemplate = new JdbcTemplate();
		this.data = data;
        this.pointer = pointer;
        this.hits = hits;
        this.tag = tag;
    }

    public GetBibData2(String data, String tag) {
        this.jdbcTemplate = new JdbcTemplate();
		this.data = data;
        this.tag = tag;
    }

    public String getData() {
        return data;
    }

    public String getTag() {
        return tag;
    }

    public int getPointer() {
        return pointer;
    }

    public int getHits() {
        return hits;
    }

    /**
     * Searches data by title.
     */
    public String searchByTitle(String matno, String tag) {
        return getCTBibSql("001", "CTTITL", matno, tag);
    }

    /**
     * Searches data by author name.
     */
    public String searchByName(String matno, String tag) {
        return getCTBibSql("002", "CTAUTH", matno, tag);
    }

    /**
     * Searches data by subject.
     */
    public String searchBySubject(String matno, String tag) {
        return getCTBibSql("003", "CTSUBJ", matno, tag);
    }

    // Additional search methods (searchByPubl, searchBySeries, etc.) are implemented similarly...

    /**
     * Constructs a SQL query based on the database type.
     */
    public static String getCTBibSql(String grid, String tableName, String matno, String tag) {
     //   String query = "";
        String query = "SELECT CT05POINTER, CT06TAG, CT05SRAW, CT05HITS, CT05RAW AS CT05RAW "
                + "FROM CTTITL "
                + "INNER JOIN CTPONT ON CTTITL.CT05POINTER = CTPONT.CT06POINTER "
                + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC = GLTAGP.GL17MARC "
                + "WHERE GL17GRID = '"+grid+"' AND CT05HITS IS NOT NULL AND CT06MATNO = '"+matno+"' AND CT06TAG = '"+tag+"' "
                + "GROUP BY CT05POINTER, CT06TAG, CT05SRAW, CT05HITS, CT05RAW ";
//                + "UNION ALL "
//                + "SELECT CT05POINTER, CT06TAG, CT05SRAW, CT05HITS, CT05RAW AS CT05RAW "
//                + "FROM CTTITL "
//                + "INNER JOIN CTPONT ON CTTITL.CT05POINTER = CTPONT.CT06POINTER "
//                + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC = GLTAGP.GL17MARC "
//                + "WHERE GL17GRID = '001' AND CT05HITS IS NOT NULL AND CT06MATNO = '0000010788' AND CT06TAG = '245' "
//                + "GROUP BY CT05POINTER, CT06TAG, CT05SRAW, CT05HITS, CT05RAW "
//                + "UNION ALL "
//                + "SELECT CT05POINTER, CT06TAG, CT05SRAW, CT05HITS, CT05RAW AS CT05RAW "
//                + "FROM CTTITL "
//                + "INNER JOIN CTPONT ON CTTITL.CT05POINTER = CTPONT.CT06POINTER "
//                + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC = GLTAGP.GL17MARC "
//                + "WHERE GL17GRID = '001' AND CT05HITS IS NOT NULL AND CT06MATNO = '0000010785' AND CT06TAG = '245' "
//                + "GROUP BY CT05POINTER, CT06TAG, CT05SRAW, CT05HITS, CT05RAW "
//                + "ORDER BY CT05SRAW;";

//          query = "SELECT CT05POINTER, CT06TAG, CT05SRAW, CT05HITS, CAST(CT05RAW AS varchar) CT05RAW FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + 
//            "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + 
//            "WHERE GL17GRID = '" + grid + "' AND CT05HITS IS NOT NULL AND CT06MATNO='" + matno + "' AND CT06TAG = '" + tag + "' " + 
//            "GROUP BY CT05POINTER, CT06TAG, CT05SRAW, CT05HITS, CAST(CT05RAW AS varchar) ORDER BY CT05SRAW";
//        } else if (dbtype.getDBType().equals("oracle")) {
//          query = "SELECT CT05POINTER, CT06TAG, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + 
//            "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + 
//            "WHERE GL17GRID = '" + grid + "' AND CT05HITS IS NOT NULL AND CT06MATNO='" + matno + "' AND CT06TAG = '" + tag + "' " + 
//            "GROUP BY CT05POINTER, CT06TAG, CT05SRAW, CT05HITS, CT05RAW ORDER BY CT05SRAW";
//        } else if (dbtype.getDBType().equals("mysql")) {
//          query = "SELECT CT05POINTER, CT06TAG, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + 
//            "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + 
//            "WHERE GL17GRID = '" + grid + "' AND CT05HITS IS NOT NULL AND CT06MATNO='" + matno + "' AND CT06TAG = '" + tag + "' " + 
//            "GROUP BY CT05POINTER, CT06TAG, CT05SRAW, CT05HITS, CT05RAW ORDER BY CT05SRAW";
//        } 
        return query;
      }

    /**
     * Determines the raw field to use based on the database type.
     */
    private String getRawFieldByDBType() {
        // Example of dynamically fetching DB type. Replace with your DB detection logic.
        String dbType = "mysql"; // Replace with actual DB type detection logic.
        if ("mssql".equalsIgnoreCase(dbType)) {
            return "CAST(CT05RAW AS varchar)";
        } else if ("oracle".equalsIgnoreCase(dbType)) {
            return "CT05RAW";
        } else {
            return "CT05RAW"; // Default to MySQL.
        }
    }

    /**
     * Retrieves bibliographic data from multiple SQL queries and processes it with ISBD2 rules.
     */
    
    public List<GetBibData2> getCTBibData2(List<String> sqlQueries2, List<ISBD2> isbd2) {
    	// Assuming you have a list of valid SQL queries
    	List<GetBibData2> list=new ArrayList<GetBibData2>();
    	 

     
     

    	//for (String gl17Grid : gl17GridValues) {
    	    for (String query : sqlQueries2) {
    	        try {
    	            // Execute the query with parameters
    	            List<GetBibData2> results = jdbcTemplate.query(query, new RowMapper<GetBibData2>() {
    	                @Override
    	                public GetBibData2 mapRow(ResultSet rs, int rowNum) throws SQLException {
    	                    String data;
    	                    if (rs.getString("CT05RAW") != null) {
    	                        data = rs.getString("CT05SRAW") + rs.getString("CT05RAW");
    	                    } else {
    	                        data = rs.getString("CT05SRAW");
    	                    }

    	                    // Transform the data
    	                    String processedData = ISBD2.getSubfield2(StringUtils.checkNull(data), isbd2);
System.out.println("processedData is "+processedData);
    	                    // Create and return the object
    	                    return new GetBibData2(
    	                            processedData,
    	                            rs.getInt("CT05POINTER"),
    	                            rs.getInt("CT05HITS"),
    	                            rs.getString("CT06TAG")
    	                    );
    	                }
    	            });

    	            // Add results to the main list
    	            if (results.isEmpty()) {
    	                list.add(new GetBibData2("", 0, 0, ""));
    	            } else {
    	                list.addAll(results);
    	            }
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	            // Add an empty object in case of query failure
    	            list.add(new GetBibData2("", 0, 0, ""));
    	        }
    	    }
    //	}

    	return list;

    }

    
    
//    public List<GetBibData2> getCTBibData2(List<String> sqlQueries, List<ISBD2> isbd2) {
//        List<GetBibData2> resultList = new ArrayList<>();
//        
//        for (String query : sqlQueries) {
//        	System.out.println("sqlQueries is "+query);
//            jdbcTemplate.query(query, rs -> {
//                String data = rs.getString("CT05SRAW");
//                if (rs.getString("CT05RAW") != null) {
//                    data += rs.getString("CT05RAW");
//                }
//                String processedData = ISBD2.getSubfield2(StringUtils.checkNull(data),isbd2);
//                resultList.add(new GetBibData2(processedData, rs.getInt("CT05POINTER"), rs.getInt("CT05HITS"), rs.getString("CT06TAG")));
//            });
//        }
//        return resultList;
//    }

    /**
     * Retrieves bibliographic data from CTWORK table.
     * @throws DataAccessException 
     */
    public List<GetBibData2> getCtworkBibData(String tag, String matno) throws SQLException, DataAccessException {
    	String query = "SELECT CT04TAG, CT04RAW " +
                "FROM CTWORK " +
                "WHERE CT04TAG IN (" + tag + ") AND CT04MATNO = ?";

        List<ISBD2> isbd2List = ISBD2.getPunctuation(tag);

        return jdbcTemplate.query(query, new Object[]{matno}, (rs, rowNum) -> {
            String rawData = rs.getString("CT04RAW");
            String processedData = ISBD2.getSubfield2(StringUtils.checkNull(rawData), isbd2List);
            return new GetBibData2(processedData, rs.getString("CT04TAG"));
        });
    }

    /**
     * Retrieves the material status from the CTMATM table.
     */
    public String getCtmatmStat(String matno) {
        String query = "SELECT CT02STAT FROM CTMATM WHERE CT02MATNO = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{matno}, String.class);
    }
}
