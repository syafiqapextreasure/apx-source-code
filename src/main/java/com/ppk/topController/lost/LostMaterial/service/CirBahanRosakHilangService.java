package com.ppk.topController.lost.LostMaterial.service;

import org.springframework.beans.factory.annotation.Autowired;
//import com.wilmu.cataloging.global.GetBibData2;
//import com.wilmu.cataloging.global.ISBD2;
//import com.wilmu.utilities.DateTimeFormatter;
//import com.wilmu.utilities.Handler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.liferay.portal.kernel.search.ParseException;
import com.ppk.lost.material.model.CirBahanRosakHilangtblPinjaman;
import com.ppk.topController.lost.LostMaterial.util.DateTimeFormatter;
import com.ppk.topController.lost.LostMaterial.util.StringUtils;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CirBahanRosakHilangService {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    GetBibData2 GetBibData2;
    
    @Autowired
    com.ppk.topController.lost.LostMaterial.service.ISBD2 ISBD2;

    public CirBahanRosakHilangService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CirBahanRosakHilangtblPinjaman> getBahanRosakHilangByPatronId(String patrid) throws SQLException, InterruptedException {
    	String sql = "SELECT CI02DOCNO, CI02MATNO, CI02CDATE, CI02DDATE " +
                "FROM CICIRC " +
                "LEFT JOIN CTDOCM ON CT03DOCNO = CI02DOCNO " +
                "WHERE CI02PATR = ? " +
                "AND CT03STAT = 'C' AND CI02FLAG = 'C'";

        List<CirBahanRosakHilangtblPinjaman> list = jdbcTemplate.query(sql, new Object[]{patrid}, rowMapper());
System.out.println("list is "+new Gson().toJson(list));
        if (!list.isEmpty()) {
            List<String> titleQuery =new ArrayList<String>(); //list.stream().map(CirBahanRosakHilangtblPinjaman::getNoPerolehan).collect(Collectors.toList());
            for(int i=0; i< list.size(); i++) {
            	titleQuery.add(GetBibData2.searchByTitle(list.get(i).getMapNumber(), "245"));
            }
            
           
            
            
            System.out.println("be for titleisbd is "+titleQuery);
            List<ISBD2> titleisbd = ISBD2.getPunctuation("245");
            //System.out.println("titleisbd is "+titleisbd.get(0).getGL23SUBF());
            List<GetBibData2> aTitle = GetBibData2.getCTBibData2(titleQuery, titleisbd);
            System.out.println("aTitle is "+new Gson().toJson(aTitle));
            for (int i = 0; i < list.size(); i++) {
            	System.out.println("aTitle.get(i).getData is "+aTitle.get(i).getData());
                list.get(i).setJudul(aTitle.get(i).getData());
            }
        }

        return list;
    }

    private RowMapper<CirBahanRosakHilangtblPinjaman> rowMapper() {
        return (ResultSet rs, int rowNum) -> {
        	
        	
            // Extract values from the ResultSet
            String docNo =  rs.getString("CI02DOCNO");
 
            String cdate = null;
			try {
				cdate = DBToDisplayFormat(rs.getString("CI02CDATE"));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String ddate = null;
			try {
				ddate = DBToDisplayFormat(rs.getString("CI02DDATE"));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String matNumber =  rs.getString("CI02MATNO");
            
            
            // Print the values for debugging
            System.out.println("matNumber: " + matNumber);
            System.out.println("CI02CDATE: " + cdate);
            System.out.println("CI02DDATE: " + ddate);
            
            // Return the object with the extracted values
            return new CirBahanRosakHilangtblPinjaman(docNo, "", cdate, ddate,matNumber);
        };
    }
    
    public String getCtdocmLcost(String docno) {
        String sql = "SELECT CT03LCOST FROM CTDOCM WHERE CT03DOCNO = ?";
        System.out.println("query CirGetCtdocmLcost: " + sql);

        try {
            // Query the database and fetch the result
            return jdbcTemplate.queryForObject(
                sql,
                new Object[]{docno},
                (rs, rowNum) -> rs.getString("CT03LCOST")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of an error
        }
    }
    
    public String getMenuLcost(String accno, String patronid) {
        String sql = 
            "SELECT GL27TIMESCOST " +
            "FROM GLELIG " +
            "WHERE GL27CATE = (" +
            "    SELECT GL14CATE FROM GLPATR WHERE UPPER(GL14PATR) = UPPER(?)" +
            ") " +
            "AND GL27ICAT = (" +
            "    SELECT CT03ICAT FROM CTDOCM WHERE UPPER(CT03DOCNO) = UPPER(?)" +
            ") " +
            "AND GL27SMD = (" +
            "    SELECT CT03SMD FROM CTDOCM WHERE UPPER(CT03DOCNO) = UPPER(?)" +
            ") " +
            "AND GL27BRNC = (" +
            "    SELECT GL14BRNC FROM GLPATR WHERE UPPER(GL14PATR) = UPPER(?)" +
            ")";

        System.out.println("Query FndGetMenuLcost: " + sql);

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{patronid, accno, accno, patronid}, new RowMapperImpl());
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of an error
        }
    }

    // Custom RowMapper for Java 1.8
    private static class RowMapperImpl implements org.springframework.jdbc.core.RowMapper<String> {
        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString("GL27TIMESCOST");
        }
    }
    
    public String biggetLcostMenu() {
        String sql = "SELECT GL28RCOST FROM GLLIBR WHERE GL28RCOST IS NOT NULL";
        System.out.println("Query FndGetMenuLcost: " + sql);

        try {
            return jdbcTemplate.queryForObject(sql, new RowMapperImplL());
        } catch (Exception e) {
            e.printStackTrace();
            return "0"; // Default value in case of error
        }
    }

    // Custom RowMapper for Java 1.8
    private static class RowMapperImplL implements org.springframework.jdbc.core.RowMapper<String> {
        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            String rcost = rs.getString("GL28RCOST");
            return rcost == null || rcost.isEmpty() ? "0" : rcost;
        }
    }
    
    public static String DBToDisplayFormat(String dbDate) throws java.text.ParseException {
        if (dbDate == null || dbDate.isEmpty())
          return " "; 
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date convertedCurrentDate = sdf.parse(dbDate);
          SimpleDateFormat newFormatter = new SimpleDateFormat("dd/MM/yyyy");
          date = newFormatter.format(convertedCurrentDate); 
        return date;
      }
   }

