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

class RecordsDaoImpl$8
implements RowMapper<Records> {
    RecordsDaoImpl$8() {
    }

    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
        Records title = new Records();
        title.setAccessionNo(rs.getString("AccessionNo"));
        title.setCopyNo(rs.getString("CopyNo"));
        title.setVolume(rs.getString("Volume"));
        title.setItemCategory(rs.getString("ItemCategory"));
        title.setItemCategoryDesc(rs.getString("ItemCategoryDesc"));
        title.setSMD(rs.getString("SMD"));
        title.setSMDdesc(rs.getString("SMDDesc"));
        title.setLocation(rs.getString("Location"));
        title.setLocationDesc(rs.getString("LocationDesc"));
        title.setBranch(rs.getString("Branch"));
        title.setAccessionDate(rs.getString("AccessionDate"));
        title.setCallNo(rs.getString("CallNo"));
        title.setClassNo(rs.getString("ClassNo"));
        title.setControlNo(rs.getString("ControlNo"));
        title.setTitle(rs.getString("Title"));
        return title;
    }
}
