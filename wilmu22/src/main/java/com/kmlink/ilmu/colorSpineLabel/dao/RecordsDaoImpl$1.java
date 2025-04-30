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

class RecordsDaoImpl$1
implements RowMapper<Records> {
    RecordsDaoImpl$1() {
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
        Records accessionNo = new Records();
        accessionNo.setAccessionNo(rs.getString("AccessionNo"));
        accessionNo.setCopyNo(rs.getString("CopyNo"));
        accessionNo.setVolume(rs.getString("Volume"));
        accessionNo.setItemCategory(rs.getString("ItemCategory"));
        accessionNo.setItemCategoryDesc(rs.getString("ItemCategoryDesc"));
        accessionNo.setSpineItemCategory(rs.getString("SpineItemCategory"));
        accessionNo.setSMD(rs.getString("SMD"));
        accessionNo.setSMDdesc(rs.getString("SMDDesc"));
        accessionNo.setLocation(rs.getString("Location"));
        accessionNo.setLocationDisplay(rs.getString("LocaDisplay"));
        accessionNo.setLocationDesc(rs.getString("LocationDesc"));
        accessionNo.setBranch(rs.getString("Branch"));
        accessionNo.setAccessionDate(rs.getString("AccessionDate"));
        accessionNo.setControlNo(rs.getString("ControlNo"));
        accessionNo.setCallNo(Handler.removeSubf1(rs.getString("CallNo")));
        accessionNo.setClassNo(rs.getString("ClassNo"));
        accessionNo.setTitle(rs.getString("Title"));
        accessionNo.setSpineRow(rs.getString("SPINE"));
        accessionNo.setClassLabel(classlabel);
        accessionNo.setCutterLabel(cutterlabel);
        return accessionNo;
    }
}
