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

class RecordsDaoImpl$4
implements RowMapper<Records> {
    RecordsDaoImpl$4() {
    }

    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
        Records dRange = new Records();
        dRange.setAccessionNo(rs.getString("AccessionNo"));
        dRange.setCopyNo(rs.getString("CopyNo"));
        dRange.setVolume(rs.getString("Volume"));
        dRange.setItemCategory(rs.getString("ItemCategory"));
        dRange.setItemCategoryDesc(rs.getString("ItemCategoryDesc"));
        dRange.setSMD(rs.getString("SMD"));
        dRange.setSMDdesc(rs.getString("SMDDesc"));
        dRange.setLocation(rs.getString("Location"));
        dRange.setLocationDesc(rs.getString("LocationDesc"));
        dRange.setBranch(rs.getString("Branch"));
        dRange.setAccessionDate(rs.getString("AccessionDate"));
        dRange.setCallNo(rs.getString("CallNo"));
        dRange.setClassNo(rs.getString("ClassNo"));
        dRange.setControlNo(rs.getString("ControlNo"));
        dRange.setTitle(rs.getString("Title"));
        return dRange;
    }
}
