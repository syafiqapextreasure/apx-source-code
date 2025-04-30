/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.JdbcTemplate
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.colorSpineManagement.dao;

import com.kmlink.ilmu.colorSpineManagement.dao.ListDao;
import com.kmlink.ilmu.colorSpineManagement.model.Records;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ListDaoImpl
implements ListDao {
    private JdbcTemplate jdbcTemplate;

    public ListDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Records> colourSequence() {
        List colourSequence = this.jdbcTemplate.query("SELECT SP01COLCODE, SP01COLSEQ FROM SPCOLOR; ", (RowMapper)new RowMapper<Records>(){

            public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
                Records colour = new Records();
                colour.setColourCode(rs.getString("SP01COLCODE"));
                colour.setColourSequence(rs.getInt("SP01COLSEQ"));
                return colour;
            }
        });
        return colourSequence;
    }

    @Override
    public List<Records> locationList() {
        List locationList = this.jdbcTemplate.query("SELECT spcharcolor.SP02CCODE,spcharcolor.SP02CDESC,spcharcolor.SP02COLSEQ, spcolor.SP01COLCODE FROM SPCHARCOLOR INNER JOIN spcolor ON spcharcolor.SP02COLSEQ=spcolor.SP01COLSEQ WHERE spcharcolor.SP02CTYPE = 'LOCATION'; ", (RowMapper)new RowMapper<Records>(){

            public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
                Records location = new Records();
                location.setColourCode(rs.getString("SP01COLCODE"));
                location.setPpatCode(rs.getString("SP02CCODE"));
                location.setColourDescription(rs.getString("SP02CDESC"));
                location.setColourSequence(rs.getInt("SP02COLSEQ"));
                return location;
            }
        });
        return locationList;
    }

    @Override
    public List<Records> itemClassList() {
        List itemClassList = this.jdbcTemplate.query("SELECT spcharcolor.SP02CCODE,spcharcolor.SP02CDESC,spcharcolor.SP02COLSEQ, spcolor.SP01COLCODE FROM SPCHARCOLOR INNER JOIN spcolor ON spcharcolor.SP02COLSEQ=spcolor.SP01COLSEQ WHERE spcharcolor.SP02CTYPE = 'ITEM-CLASS'; ", (RowMapper)new RowMapper<Records>(){

            public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
                Records itemClass = new Records();
                itemClass.setColourCode(rs.getString("SP01COLCODE"));
                itemClass.setPpatCode(rs.getString("SP02CCODE"));
                itemClass.setColourDescription(rs.getString("SP02CDESC"));
                itemClass.setColourSequence(rs.getInt("SP02COLSEQ"));
                return itemClass;
            }
        });
        return itemClassList;
    }

    @Override
    public List<Records> findRecordById(int colorSequence) {
        List colourList = this.jdbcTemplate.query("SELECT * FROM SPCOLOR where SP01COLSEQ=?", new Object[]{colorSequence}, (RowMapper)new RowMapper<Records>(){

            public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
                Records colour = new Records();
                colour.setColourSequence(rs.getInt("SP01COLSEQ"));
                colour.setColourCode(rs.getString("SP01COLCODE"));
                return colour;
            }
        });
        return colourList;
    }

    @Override
    public int update(Records colour) {
        String sql = "update  SPCOLOR set SP01COLCODE=? where SP01COLSEQ=?";
        try {
            int counter = this.jdbcTemplate.update(sql, new Object[]{colour.getColourCode(), colour.getColourSequence()});
            return counter;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int colourSequence) {
        String sql = "delete from SPCOLOR where SP01COLSEQ=?";
        try {
            int counter = this.jdbcTemplate.update(sql, new Object[]{colourSequence});
            return counter;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int create(Records colour) {
        String sql = "insert into SPCOLOR(SP01COLSEQ,SP01COLCODE) values(?,?)";
        try {
            int counter = this.jdbcTemplate.update(sql, new Object[]{colour.getColourSequence(), colour.getColourCode()});
            return counter;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
