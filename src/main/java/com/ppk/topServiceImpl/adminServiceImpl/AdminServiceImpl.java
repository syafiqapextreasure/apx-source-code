//package com.ppk.topServiceImpl.adminServiceImpl;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Service;
//
//import com.ppk.topEntity.formsEntity.dto.AdminBookList;
//import com.ppk.topEntity.formsEntity.registration.MemebershipRequestModel;
//import com.ppk.topEntity.formsEntity.registration.UserRequestModel;
//@Service
//public class AdminServiceImpl {
//
//    // Injecting JdbcTemplate
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    
//    public List<AdminBookList> findAllDocuments() {
//        String sql = "SELECT CT03DOCNO, CT03LOCA, CT03ICAT FROM portal_dump.ctdocm";
//        return jdbcTemplate.query(sql, new DocumentRowMapper());
//    } 
//
//    //total over dues
//    public Double calculateTotalFeeOverdue() {
//        String sql = "SELECT SUM(CT03FCOST + CT03LCOST) AS TotalFeeOverdue " +
//                     "FROM portal_dump.ctdocm " +
//                     "WHERE CT03DDATE < CURDATE()";
//        return jdbcTemplate.queryForObject(sql, Double.class);
//    }
//    public List<MemebershipRequestModel> getAllRequestModel() {
//        // SQL query to fetch all records from the portal_dump.glpatr table
//        String sql = "SELECT GL14PATR, GL14STAT, GL14CATE " +
//                     "FROM glpatr " +
//                     "WHERE GL14STAT = 1";
//
//        // Using JdbcTemplate to query the database and map the result to a list of MemebershipRequestModel
//        return jdbcTemplate.query(sql, new RowMapper<MemebershipRequestModel>() {
//            @Override
//            public MemebershipRequestModel mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
//                MemebershipRequestModel model = new MemebershipRequestModel();
//
//                // Extract values from the result set and set them in the model object
//                model.setpId(rs.getString("GL14PATR")); // Extract GL14PATR
//                model.setStatus(rs.getString("GL14STAT")); // Extract GL14STAT
//                model.setBookIssue(rs.getString("GL14CATE")); // Extract GL14CATE
//
//                return model;
//            }
//        });
//    }
//    
//    
//    public int getTotalRegisteredMembers() {
//        String sql = "SELECT COUNT(*) FROM glpatr";
//        
//        // Query for the count of records in the table
//        return jdbcTemplate.queryForObject(sql, Integer.class);
//    }
//    
//    
//    // Method to get the total number of borrowed members
//    public int getTotalBorrowedMembers() {
//        String sql = "SELECT COUNT(*) FROM glpatr WHERE GL14LASTBOR IS NOT NULL";
//        
//        // Query for the count of borrowed members (those who have a value in GL14LASTBOR)
//        return jdbcTemplate.queryForObject(sql, Integer.class);
//    }
//}
//
//class DocumentRowMapper implements RowMapper<AdminBookList> {
//    @Override
//    public AdminBookList mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return new AdminBookList(
//                rs.getString("CT03DOCNO"),
//                rs.getString("CT03LOCA"),
//                rs.getString("CT03ICAT")
//        );
//    }
//}


package com.ppk.topServiceImpl.adminServiceImpl;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.ppk.topEntity.formsEntity.dto.AdminBookList;
import com.ppk.topEntity.formsEntity.registration.MemebershipRequestModel;
import com.ppk.topEntity.formsEntity.registration.UserRequestModel;
@Service
public class AdminServiceImpl {

    // Injecting JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<AdminBookList> findAllDocuments() {
        String sql = "SELECT CT03DOCNO, CT03LOCA, CT03ICAT FROM ctdocm";
        return jdbcTemplate.query(sql, new DocumentRowMapper());
    }

    //total over dues
    public Double calculateTotalFeeOverdue() {
        String sql = "SELECT SUM(CT03FCOST + CT03LCOST) AS TotalFeeOverdue " +
                     "FROM ctdocm " +
                     "WHERE CT03DDATE < CURDATE()";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }
    public List<MemebershipRequestModel> getAllRequestModel() {
        // SQL query to fetch all records from the portal_dump.glpatr table where GL14STAT = 1
        String sql = "SELECT GL14PATR, GL14CATE, GL14STAT FROM glpatr WHERE GL14STAT = 1";

        // Using JdbcTemplate to query the database and map the result to a list of MemebershipRequestModel
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            // Create a new MemebershipRequestModel object
            MemebershipRequestModel model = new MemebershipRequestModel();

            // Extract values from the result set and set them in the model object
            model.setpId(rs.getString("GL14PATR"));       // Set Patron ID (GL14PATR)
            model.setStatus(rs.getString("GL14STAT"));    // Set Status (GL14STAT)
            model.setBookIssue(rs.getString("GL14CATE")); // Set Book Issue Category (GL14CATE)

            return model; // Return the populated model
        });
    }
    
    
    public int getTotalRegisteredMembers() {
        String sql = "SELECT COUNT(*) FROM glpatr";
        
        // Query for the count of records in the table
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    
    // Method to get the total number of borrowed members
    public int getTotalBorrowedMembers() {
        String sql = "SELECT COUNT(*) FROM glpatr WHERE GL14LASTBOR IS NOT NULL";
        
        // Query for the count of borrowed members (those who have a value in GL14LASTBOR)
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}

class DocumentRowMapper implements RowMapper<AdminBookList> {
    @Override
    public AdminBookList mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AdminBookList(
                rs.getString("CT03DOCNO"),
                rs.getString("CT03LOCA"),
                rs.getString("CT03ICAT")
        );
    }
}
