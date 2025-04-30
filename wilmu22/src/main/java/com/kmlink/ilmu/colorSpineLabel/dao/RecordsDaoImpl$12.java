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

class RecordsDaoImpl$12
implements RowMapper<Records> {
    RecordsDaoImpl$12() {
    }

    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
        Records accession = new Records();
        accession.setAccessionNo(rs.getString("AccessionNo"));
        accession.setCopyNo(rs.getString("CopyNo"));
        accession.setVolume(rs.getString("Volume"));
        accession.setItemCategory(rs.getString("ItemCategory"));
        accession.setItemCategoryDesc(rs.getString("ItemCategoryDesc"));
        accession.setSMD(rs.getString("SMD"));
        accession.setSMDdesc(rs.getString("SMDDesc"));
        accession.setLocation(rs.getString("Location"));
        accession.setLocationDesc(rs.getString("LocationDesc"));
        accession.setBranch(rs.getString("Branch"));
        accession.setAccessionDate(rs.getString("AccessionDate"));
        accession.setControlNo(rs.getString("ControlNo"));
        accession.setCallNo(rs.getString("CallNo"));
        accession.setClassNo(rs.getString("ClassNo"));
        accession.setTitle(rs.getString("Title"));
        accession.setItemClass(rs.getString("ItemClass"));
        accession.setColourSequence(rs.getInt("ColourSequence"));
        accession.setColourCode(rs.getString("ColourCode"));
        return accession;
    }
}
