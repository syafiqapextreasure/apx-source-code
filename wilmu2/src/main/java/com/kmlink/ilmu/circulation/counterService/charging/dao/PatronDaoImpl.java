/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.JdbcTemplate
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.circulation.counterService.charging.dao;

import com.kmlink.ilmu.circulation.counterService.charging.dao.PatronDao;
import com.kmlink.ilmu.circulation.counterService.charging.model.Patron;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PatronDaoImpl
implements PatronDao {
    private JdbcTemplate jdbcTemplate;

    public PatronDaoImpl(DataSource dataSoruce) {
        this.jdbcTemplate = new JdbcTemplate(dataSoruce);
    }

    @Override
    public Patron findPatronById(String patronId) {
        Patron result = null;
        try {
            result = (Patron)this.jdbcTemplate.queryForObject("SELECT GL14NAME, \r\nGL14CATE, \r\nGL14BRNC, \r\nGL14STAT, \r\nGL14MEMDATE, \r\nGL14EXPDATE,\r\nGL14DEPT\nFROM GLPATR WHERE GL14PATR=?", new Object[]{patronId}, (RowMapper)new RowMapper<Patron>(){

                public Patron mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Patron patron = new Patron();
                    patron.setPatronName(rs.getString("GL14NAME"));
                    patron.setPatronCate(rs.getString("GL14CATE"));
                    patron.setPatronStatus(rs.getString("GL14STAT"));
                    patron.setPatronBranch(rs.getString("GL14BRNC"));
                    patron.setPatronMemDate(rs.getString("GL14MEMDATE"));
                    patron.setPatronExpDate(rs.getString("GL14EXPDATE"));
                    patron.setPatronDept(rs.getString("GL14DEPT"));
                    return patron;
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Patron retrievePatronDetailById(String patronId) {
        Patron result = (Patron)this.jdbcTemplate.queryForObject("SELECT GL14NAME, \r\nGL14CATE, \r\nGL14BRNC, \r\nGL14STAT, \r\nGL14MEMDATE, \r\nGL14EXPDATE\r\nFROM GLPATR WHERE GL14PATR=?", new Object[]{patronId}, (RowMapper)new RowMapper<Patron>(){

            public Patron mapRow(ResultSet rs, int rowNum) throws SQLException {
                Patron patron = new Patron();
                patron.setPatronName(rs.getString("GL14NAME"));
                patron.setPatronCate(rs.getString("GL14CATE"));
                patron.setPatronStatus(rs.getString("GL14STAT"));
                patron.setPatronBranchDesc(rs.getString("GL14BRNC"));
                patron.setPatronMemDate(rs.getString("GL14MEMDATE"));
                patron.setPatronExpDate(rs.getString("GL14EXPDATE"));
                return patron;
            }
        });
        return result;
    }
}
