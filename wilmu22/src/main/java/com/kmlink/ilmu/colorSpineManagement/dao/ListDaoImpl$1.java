/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.colorSpineManagement.dao;

import com.kmlink.ilmu.colorSpineManagement.model.Records;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class ListDaoImpl$1
implements RowMapper<Records> {
    ListDaoImpl$1() {
    }

    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
        Records colour = new Records();
        colour.setColourCode(rs.getString("SP01COLCODE"));
        colour.setColourSequence(rs.getInt("SP01COLSEQ"));
        return colour;
    }
}
