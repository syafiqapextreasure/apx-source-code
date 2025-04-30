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

class RecordsDaoImpl$6
implements RowMapper<Records> {
    RecordsDaoImpl$6() {
    }

    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
        Records itemCat = new Records();
        itemCat.setAccessionNo(rs.getString("AccessionNo"));
        itemCat.setCopyNo(rs.getString("CopyNo"));
        itemCat.setVolume(rs.getString("Volume"));
        itemCat.setItemCategory(rs.getString("ItemCategory"));
        itemCat.setItemCategoryDesc(rs.getString("ItemCategoryDesc"));
        itemCat.setSMD(rs.getString("SMD"));
        itemCat.setSMDdesc(rs.getString("SMDDesc"));
        itemCat.setLocation(rs.getString("Location"));
        itemCat.setLocationDesc(rs.getString("LocationDesc"));
        itemCat.setBranch(rs.getString("Branch"));
        itemCat.setAccessionDate(rs.getString("AccessionDate"));
        itemCat.setCallNo(rs.getString("CallNo"));
        itemCat.setClassNo(rs.getString("ClassNo"));
        itemCat.setControlNo(rs.getString("ControlNo"));
        itemCat.setTitle(rs.getString("Title"));
        return itemCat;
    }
}
