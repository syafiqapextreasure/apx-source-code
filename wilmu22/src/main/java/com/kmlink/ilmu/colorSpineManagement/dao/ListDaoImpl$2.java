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

class ListDaoImpl$2
implements RowMapper<Records> {
    ListDaoImpl$2() {
    }

    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
        Records location = new Records();
        location.setColourCode(rs.getString("SP01COLCODE"));
        location.setPpatCode(rs.getString("SP02CCODE"));
        location.setColourDescription(rs.getString("SP02CDESC"));
        location.setColourSequence(rs.getInt("SP02COLSEQ"));
        return location;
    }
}
