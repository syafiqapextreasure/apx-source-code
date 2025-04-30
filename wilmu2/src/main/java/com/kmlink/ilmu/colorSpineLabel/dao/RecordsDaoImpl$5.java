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

class RecordsDaoImpl$5
implements RowMapper<Records> {
    RecordsDaoImpl$5() {
    }

    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
        Records controlNo = new Records();
        controlNo.setAccessionNo(rs.getString("AccessionNo"));
        controlNo.setCopyNo(rs.getString("CopyNo"));
        controlNo.setVolume(rs.getString("Volume"));
        controlNo.setItemCategory(rs.getString("ItemCategory"));
        controlNo.setItemCategoryDesc(rs.getString("ItemCategoryDesc"));
        controlNo.setSMD(rs.getString("SMD"));
        controlNo.setSMDdesc(rs.getString("SMDDesc"));
        controlNo.setLocation(rs.getString("Location"));
        controlNo.setLocationDesc(rs.getString("LocationDesc"));
        controlNo.setBranch(rs.getString("Branch"));
        controlNo.setAccessionDate(rs.getString("AccessionDate"));
        controlNo.setCallNo(rs.getString("CallNo"));
        controlNo.setClassNo(rs.getString("ClassNo"));
        controlNo.setControlNo(rs.getString("ControlNo"));
        controlNo.setTitle(rs.getString("Title"));
        return controlNo;
    }
}
