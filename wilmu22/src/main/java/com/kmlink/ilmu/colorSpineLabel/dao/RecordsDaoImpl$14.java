/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.colorSpineLabel.dao;

import com.kmlink.ilmu.colorSpineLabel.model.Records;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class RecordsDaoImpl$14
implements RowMapper<Records> {
    RecordsDaoImpl$14() {
    }

    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
        Records colour = new Records();
        colour.setColourCode(rs.getString("ColourCode"));
        return colour;
    }
}
