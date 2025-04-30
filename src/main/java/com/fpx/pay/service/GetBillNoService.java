package com.fpx.pay.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.fpx.pay.model.BillDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class GetBillNoService {

    private final JdbcTemplate jdbcTemplate;

    // Constructor Injection
    public GetBillNoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<BillDetailsDTO> getBillNoReBill(String orderNo) {
        String sql = "SELECT RE02BILLNO, RE02PATR, RE02STATUS, RE02USEREMAIL, RE02USERMOBILE, GL14EXPDATE " +
                     "FROM REBILL " +
                     "LEFT JOIN GLPATR ON GL14PATR = RE02PATR " +
                     "WHERE RE02ORDERNO = ?";

        try {
            return jdbcTemplate.query(sql, new Object[]{orderNo}, new BillRowMapper()).stream().findFirst();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // RowMapper to map result set to DTO
    private static class BillRowMapper implements RowMapper<BillDetailsDTO> {
        @Override
        public BillDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BillDetailsDTO(
                rs.getString("RE02BILLNO"),
                rs.getString("RE02PATR"),
                rs.getString("RE02STATUS"),
                rs.getString("RE02USEREMAIL"),
                rs.getString("RE02USERMOBILE"),
                rs.getString("GL14EXPDATE")
            );
        }
    }
}
