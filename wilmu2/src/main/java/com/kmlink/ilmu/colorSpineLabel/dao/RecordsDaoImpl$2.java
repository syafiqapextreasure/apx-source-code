/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.colorSpineLabel.dao;

import com.kmlink.ilmu.colorSpineLabel.model.Records;
import com.kmlink.ilmu.shared.global.Handler;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class RecordsDaoImpl$2
implements RowMapper<Records> {
    RecordsDaoImpl$2() {
    }

    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
        String callno = Handler.removeSubf1(rs.getString("CallNo"));
        String classlabel = "";
        String cutterlabel = "";
        if (Handler.isNumeric(callno = callno.substring(0, 3))) {
            classlabel = rs.getString("CLASSLABEL");
            cutterlabel = rs.getString("CUTTERLABEL");
        } else if (!Handler.isNumeric(callno)) {
            classlabel = rs.getString("ClassNoL1");
            cutterlabel = rs.getString("ClassNoL2");
        }
        Records accessionRange = new Records();
        accessionRange.setAccessionNo(rs.getString("AccessionNo"));
        accessionRange.setCopyNo(rs.getString("CopyNo"));
        accessionRange.setVolume(rs.getString("Volume"));
        accessionRange.setItemCategory(rs.getString("ItemCategory"));
        accessionRange.setItemCategoryDesc(rs.getString("ItemCategoryDesc"));
        accessionRange.setSMD(rs.getString("SMD"));
        accessionRange.setSMDdesc(rs.getString("SMDDesc"));
        accessionRange.setLocation(rs.getString("LocaDisplay"));
        accessionRange.setLocationDesc(rs.getString("LocationDesc"));
        accessionRange.setBranch(rs.getString("Branch"));
        accessionRange.setAccessionDate(rs.getString("AccessionDate"));
        accessionRange.setControlNo(rs.getString("ControlNo"));
        accessionRange.setClassNo(rs.getString("ClassNo"));
        accessionRange.setCallNo(rs.getString("CallNo"));
        accessionRange.setTitle(rs.getString("Title"));
        accessionRange.setSpineRow(rs.getString("SPINE"));
        accessionRange.setClassLabel(classlabel);
        accessionRange.setCutterLabel(cutterlabel);
        return accessionRange;
    }
}
