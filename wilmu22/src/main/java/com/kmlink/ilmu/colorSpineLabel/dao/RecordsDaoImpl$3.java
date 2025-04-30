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

class RecordsDaoImpl$3
implements RowMapper<Records> {
    RecordsDaoImpl$3() {
    }

    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
        Records callNo = new Records();
        callNo.setAccessionNo(rs.getString("AccessionNo"));
        callNo.setCopyNo(rs.getString("CopyNo"));
        callNo.setVolume(rs.getString("Volume"));
        callNo.setItemCategory(rs.getString("ItemCategory"));
        callNo.setItemCategoryDesc(rs.getString("ItemCategoryDesc"));
        callNo.setSMD(rs.getString("SMD"));
        callNo.setSMDdesc(rs.getString("SMDDesc"));
        callNo.setLocation(rs.getString("Location"));
        callNo.setLocationDesc(rs.getString("LocationDesc"));
        callNo.setBranch(rs.getString("Branch"));
        callNo.setAccessionDate(rs.getString("AccessionDate"));
        callNo.setCallNo(rs.getString("CallNo"));
        callNo.setClassNo(rs.getString("ClassNo"));
        callNo.setControlNo(rs.getString("ControlNo"));
        callNo.setTitle(rs.getString("Title"));
        return callNo;
    }
}
