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

class ListDaoImpl$3
implements RowMapper<Records> {
    ListDaoImpl$3() {
    }

    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
        Records itemClass = new Records();
        itemClass.setColourCode(rs.getString("SP01COLCODE"));
        itemClass.setPpatCode(rs.getString("SP02CCODE"));
        itemClass.setColourDescription(rs.getString("SP02CDESC"));
        itemClass.setColourSequence(rs.getInt("SP02COLSEQ"));
        return itemClass;
    }
}
