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

class RecordsDaoImpl$7
implements RowMapper<Records> {
    RecordsDaoImpl$7() {
    }

    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
        Records smd = new Records();
        smd.setAccessionNo(rs.getString("AccessionNo"));
        smd.setCopyNo(rs.getString("CopyNo"));
        smd.setVolume(rs.getString("Volume"));
        smd.setItemCategory(rs.getString("ItemCategory"));
        smd.setItemCategoryDesc(rs.getString("ItemCategoryDesc"));
        smd.setSMD(rs.getString("SMD"));
        smd.setSMDdesc(rs.getString("SMDDesc"));
        smd.setLocation(rs.getString("Location"));
        smd.setLocationDesc(rs.getString("LocationDesc"));
        smd.setBranch(rs.getString("Branch"));
        smd.setAccessionDate(rs.getString("AccessionDate"));
        smd.setCallNo(rs.getString("CallNo"));
        smd.setItemClass(rs.getString("ItemClass"));
        smd.setClassNo(rs.getString("ClassNo"));
        smd.setControlNo(rs.getString("ControlNo"));
        smd.setTitle(rs.getString("Title"));
        return smd;
    }
}
