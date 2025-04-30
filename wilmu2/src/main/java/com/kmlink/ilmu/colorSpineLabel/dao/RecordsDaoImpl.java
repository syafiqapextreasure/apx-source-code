/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.JdbcTemplate
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.colorSpineLabel.dao;

import com.kmlink.ilmu.colorSpineLabel.dao.RecordsDao;
import com.kmlink.ilmu.colorSpineLabel.model.Records;
import com.kmlink.ilmu.shared.global.Handler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class RecordsDaoImpl
implements RecordsDao {
    private JdbcTemplate jdbcTemplate;

    public RecordsDaoImpl(DataSource dataSoruce) {
        this.jdbcTemplate = new JdbcTemplate(dataSoruce);
    }

    @Override
    public List<Records> retrieveByAccessionNo(String accessionNo, String selectBranch) {
        List byAccessionNo = this.jdbcTemplate.query("SELECT D1.CT03DOCNO AS 'AccessionNo', \r\nD4.GL05DISPLAY AS 'LocaDisplay', \r\nD7.CT05SCONV AS 'CallNo', \r\nSUBSTRING(REPLACE(REPLACE(REPLACE(REPLACE( REPLACE(SUBSTRING(D7.CT05SRAW,3,8), '|b',' '),'|d',''), '|2', ' '),'.',''),' ',''),1,4) AS SPINE, \r\nSUBSTRING(LEFT(D7.CT05SRAW,LOCATE('|b',D7.CT05SRAW) - 1),3,20) AS CLASSLABEL, \r\nSUBSTRING_INDEX(D7.CT05SRAW,'|b',-1) AS CUTTERLABEL, \r\nD1.CT03COPYNO AS 'CopyNo', \r\nD1.CT03VOLUME AS 'Volume', \r\nD1.CT03ICAT AS 'ItemCategory', \r\nD2.GL10DESC AS 'ItemCategoryDesc', \r\nD1.CT03SMD AS 'SMD', \r\nD3.GL47DESC AS 'SMDDesc', \r\nD1.CT03LOCA AS 'Location', \r\nD4.GL05DESC AS 'LocationDesc', \r\nD4.GL05BRNC AS 'Branch', \r\nD5.GL71DESC AS 'BranchDesc', \r\nD1.CT03CRDATE AS 'AccessionDate', \r\nD1.CT03FOREX AS 'Currency', \r\nD1.CT03FCOST AS 'ForeignCost', \r\nD1.CT03LCOST AS 'LocalCost', \r\nD2.GL10DISPLAY AS 'SpineItemCategory', \r\nD3.GL47DISPLAY AS 'SpineSMD', \r\nD1.CT03PARTNO AS 'PartNo', \r\nD1.CT03SPINE AS 'UserDefineSpine', \r\nD7.CT05SCONV AS 'ClassNo', \r\nD2.GL10DISPLAY AS 'ClassNoL1', SUBSTRING(D7.CT05SRAW, POSITION('|b' IN D7.CT05SRAW) + 2, 3) AS 'ClassNoL2', D1.CT03MATNO AS 'ControlNo', \r\nD9.CT05SCONV AS 'Title' \r\nFROM CTDOCM D1 \r\nLEFT JOIN GLICAT D2 ON (D1.CT03ICAT = D2.GL10ICAT) \r\nLEFT JOIN GLSMD D3 ON (D1.CT03SMD = D3.GL47SMD) \r\nLEFT JOIN GLLOCA D4 ON (D1.CT03LOCA = D4.GL05LOCA) \r\nLEFT JOIN GLBRNC D5 ON (D4.GL05BRNC = D5.GL71BRNC) \r\nLEFT JOIN CTPONT D6 ON (D1.CT03MATNO = D6.CT06MATNO AND \r\nD6.CT06TAG = '090') \r\nINNER JOIN CTCALL D7 ON (D6.CT06POINTER = D7.CT05POINTER \r\nAND D7.CT05SCONV LIKE '%' ) \r\nLEFT JOIN CTPONT D8 ON (D1.CT03MATNO = D8.CT06MATNO AND \r\nD8.CT06TAG = '245') \r\nINNER JOIN CTTITL D9 ON (D8.CT06POINTER = D9.CT05POINTER) \r\nWHERE D1.CT03DOCNO = ? AND D1.CT03LOCA LIKE ?", new Object[]{accessionNo, selectBranch}, (RowMapper)new RowMapper<Records>(){

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
        });
        return byAccessionNo;
    }

    @Override
    public List<Records> retrieveByAccessionRange(String accessionFrom, String accessionTo, String selectBranch) {
        List accessionRange = this.jdbcTemplate.query("SELECT D1.CT03DOCNO AS 'AccessionNo', \r\nD4.GL05DISPLAY AS 'LocaDisplay', \r\nD7.CT05SCONV AS 'CallNo', \r\nSUBSTRING(REPLACE(REPLACE(REPLACE(REPLACE( REPLACE(SUBSTRING(D7.CT05SRAW,3,8), '|b',' '),'|d',''), '|2', ' '),'.',''),' ',''),1,4) AS SPINE, \r\nSUBSTRING(LEFT(D7.CT05SRAW,LOCATE('|b',D7.CT05SRAW) - 1),3,20) AS CLASSLABEL, \r\nSUBSTRING_INDEX(D7.CT05SRAW,'|b',-1) AS CUTTERLABEL, \r\nD2.GL10DISPLAY AS 'SpineItemCategory', \r\nD1.CT03COPYNO AS 'CopyNo', \r\nD1.CT03VOLUME AS 'Volume', \r\nD1.CT03ICAT AS 'ItemCategory', \r\nD2.GL10DESC AS 'ItemCategoryDesc', \r\nD1.CT03SMD AS 'SMD', \r\nD3.GL47DESC AS 'SMDDesc', \r\nD1.CT03LOCA AS 'Location', \r\nD4.GL05DESC AS 'LocationDesc', \r\nD4.GL05BRNC AS 'Branch', \r\nD5.GL71DESC AS 'BranchDesc', \r\nD1.CT03CRDATE AS 'AccessionDate', \r\nD1.CT03FOREX AS 'Currency', \r\nD1.CT03FCOST AS 'ForeignCost', \r\nD1.CT03LCOST AS 'LocalCost', \r\nD3.GL47DISPLAY AS 'SpineSMD', \r\nD1.CT03PARTNO AS 'PartNo', \r\nD1.CT03SPINE AS 'UserDefineSpine', \r\nD7.CT05SCONV AS 'ClassNo', \r\nD2.GL10DISPLAY AS 'ClassNoL1', SUBSTRING(D7.CT05SRAW, POSITION('|b' IN D7.CT05SRAW) + 2, 3) AS 'ClassNoL2', D1.CT03MATNO AS 'ControlNo', \r\nD9.CT05SCONV AS 'Title' \r\nFROM CTDOCM D1 \r\nLEFT JOIN GLICAT D2 ON (D1.CT03ICAT = D2.GL10ICAT) \r\nLEFT JOIN GLSMD D3 ON (D1.CT03SMD = D3.GL47SMD) \r\nLEFT JOIN GLLOCA D4 ON (D1.CT03LOCA = D4.GL05LOCA) \r\nLEFT JOIN GLBRNC D5 ON (D4.GL05BRNC = D5.GL71BRNC) \r\nLEFT JOIN CTPONT D6 ON (D1.CT03MATNO = D6.CT06MATNO AND \r\nD6.CT06TAG = '090') \r\nINNER JOIN CTCALL D7 ON (D6.CT06POINTER = D7.CT05POINTER \r\nAND D7.CT05SCONV LIKE '%' ) \r\nLEFT JOIN CTPONT D8 ON (D1.CT03MATNO = D8.CT06MATNO AND \r\nD8.CT06TAG = '245') \r\nINNER JOIN CTTITL D9 ON (D8.CT06POINTER = D9.CT05POINTER) \r\nWHERE D1.CT03DOCNO BETWEEN ? AND ? AND D1.CT03LOCA LIKE ?", new Object[]{accessionFrom, accessionTo, selectBranch}, (RowMapper)new RowMapper<Records>(){

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
        });
        return accessionRange;
    }

    @Override
    public List<Records> retrieveByCallNo(String callNo, String selectBranch) {
        List byCallNo = this.jdbcTemplate.query("SELECT D1.CT03DOCNO AS 'AccessionNo', \r\nD1.CT03COPYNO AS 'CopyNo', \r\nD1.CT03VOLUME AS 'Volume', \r\nD1.CT03ICAT AS 'ItemCategory', \r\nD2.GL10DESC AS 'ItemCategoryDesc', \r\nD1.CT03SMD AS 'SMD', \r\nD3.GL47DESC AS 'SMDDesc', \r\nD1.CT03LOCA AS 'Location', \r\nD4.GL05DESC AS 'LocationDesc', \r\nD4.GL05BRNC AS 'Branch', \r\nD5.GL71DESC AS 'BranchDesc', \r\nD1.CT03CRDATE AS 'AccessionDate', \r\nD1.CT03FOREX AS 'Currency', \r\nD1.CT03FCOST AS 'ForeignCost', \r\nD1.CT03LCOST AS 'LocalCost', \r\nD2.GL10DISPLAY AS 'SpineItemCategory', \r\nD3.GL47DISPLAY AS 'SpineSMD', \r\nD1.CT03PARTNO AS 'PartNo', \r\nD1.CT03SPINE AS 'UserDefineSpine', \r\nD7.CT05SCONV AS 'CallNo', \r\nD7.CT05SCONV AS 'ClassNo', \r\nD1.CT03MATNO AS 'ControlNo', \r\nD9.CT05SCONV AS 'Title' \r\nFROM CTDOCM D1 \r\nLEFT JOIN GLICAT D2 ON (D1.CT03ICAT = D2.GL10ICAT) \r\nLEFT JOIN GLSMD D3 ON (D1.CT03SMD = D3.GL47SMD) \r\nLEFT JOIN GLLOCA D4 ON (D1.CT03LOCA = D4.GL05LOCA) \r\nLEFT JOIN GLBRNC D5 ON (D4.GL05BRNC = D5.GL71BRNC) \r\nLEFT JOIN CTPONT D6 ON (D1.CT03MATNO = D6.CT06MATNO AND \r\nD6.CT06TAG = '090') \r\nLEFT JOIN CTCALL D7 ON (D6.CT06POINTER = D7.CT05POINTER \r\nAND D7.CT05SCONV LIKE ? ) \r\nLEFT JOIN CTPONT D8 ON (D1.CT03MATNO = D8.CT06MATNO AND \r\nD8.CT06TAG = '245') \r\nINNER JOIN CTTITL D9 ON (D8.CT06POINTER = D9.CT05POINTER) WHERE D1.CT03LOCA LIKE ? \r\n", new Object[]{callNo, selectBranch}, (RowMapper)new RowMapper<Records>(){

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
        });
        return byCallNo;
    }

    @Override
    public List<Records> retrieveByAccessionDateRange(String accessionDateFrom, String accessionDateTo, String selectBranch) {
        List accessionDateRange = this.jdbcTemplate.query("SELECT D1.CT03DOCNO AS 'AccessionNo', \r\nD1.CT03COPYNO AS 'CopyNo', \r\nD1.CT03VOLUME AS 'Volume', \r\nD1.CT03ICAT AS 'ItemCategory', \r\nD2.GL10DESC AS 'ItemCategoryDesc', \r\nD1.CT03SMD AS 'SMD', \r\nD3.GL47DESC AS 'SMDDesc', \r\nD1.CT03LOCA AS 'Location', \r\nD4.GL05DESC AS 'LocationDesc', \r\nD4.GL05BRNC AS 'Branch', \r\nD5.GL71DESC AS 'BranchDesc', \r\nD1.CT03CRDATE AS 'AccessionDate', \r\nD1.CT03FOREX AS 'Currency', \r\nD1.CT03FCOST AS 'ForeignCost', \r\nD1.CT03LCOST AS 'LocalCost', \r\nD2.GL10DISPLAY AS 'SpineItemCategory', \r\nD3.GL47DISPLAY AS 'SpineSMD', \r\nD1.CT03PARTNO AS 'PartNo', \r\nD1.CT03SPINE AS 'UserDefineSpine', \r\nD7.CT05SCONV AS 'CallNo', \r\nD7.CT05SCONV AS 'ClassNo', \r\nD1.CT03MATNO AS 'ControlNo', \r\nD9.CT05SCONV AS 'Title' \r\nFROM CTDOCM D1 \r\nLEFT JOIN GLICAT D2 ON (D1.CT03ICAT = D2.GL10ICAT) \r\nLEFT JOIN GLSMD D3 ON (D1.CT03SMD = D3.GL47SMD) \r\nLEFT JOIN GLLOCA D4 ON (D1.CT03LOCA = D4.GL05LOCA) \r\nLEFT JOIN GLBRNC D5 ON (D4.GL05BRNC = D5.GL71BRNC) \r\nLEFT JOIN CTPONT D6 ON (D1.CT03MATNO = D6.CT06MATNO AND \r\nD6.CT06TAG = '090') \r\nLEFT JOIN CTCALL D7 ON (D6.CT06POINTER = D7.CT05POINTER) \r\nLEFT JOIN CTPONT D8 ON (D1.CT03MATNO = D8.CT06MATNO AND \r\nD8.CT06TAG = '245') \r\nINNER JOIN CTTITL D9 ON (D8.CT06POINTER = D9.CT05POINTER) \r\nWHERE D1.CT03CRDATE BETWEEN ? AND ? AND D1.CT03LOCA LIKE ? ", new Object[]{accessionDateFrom, accessionDateTo, selectBranch}, (RowMapper)new RowMapper<Records>(){

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
        });
        return accessionDateRange;
    }

    @Override
    public List<Records> retrieveByControlNo(String controlNo, String selectBranch) {
        List byControlNo = this.jdbcTemplate.query("SELECT D1.CT03DOCNO AS 'AccessionNo', \r\nD1.CT03COPYNO AS 'CopyNo', \r\nD1.CT03VOLUME AS 'Volume', \r\nD1.CT03ICAT AS 'ItemCategory', \r\nD2.GL10DESC AS 'ItemCategoryDesc', \r\nD1.CT03SMD AS 'SMD', \r\nD3.GL47DESC AS 'SMDDesc', \r\nD1.CT03LOCA AS 'Location', \r\nD4.GL05DESC AS 'LocationDesc', \r\nD4.GL05BRNC AS 'Branch', \r\nD5.GL71DESC AS 'BranchDesc', \r\nD1.CT03CRDATE AS 'AccessionDate', \r\nD1.CT03FOREX AS 'Currency', \r\nD1.CT03FCOST AS 'ForeignCost', \r\nD1.CT03LCOST AS 'LocalCost', \r\nD2.GL10DISPLAY AS 'SpineItemCategory', \r\nD3.GL47DISPLAY AS 'SpineSMD', \r\nD1.CT03PARTNO AS 'PartNo', \r\nD1.CT03SPINE AS 'UserDefineSpine', \r\nD7.CT05SCONV AS 'CallNo', \r\nD7.CT05SCONV AS 'ClassNo', \r\nD1.CT03MATNO AS 'ControlNo', \r\nD9.CT05SCONV AS 'Title' \r\nFROM CTDOCM D1 \r\nLEFT JOIN GLICAT D2 ON (D1.CT03ICAT = D2.GL10ICAT) \r\nLEFT JOIN GLSMD D3 ON (D1.CT03SMD = D3.GL47SMD) \r\nLEFT JOIN GLLOCA D4 ON (D1.CT03LOCA = D4.GL05LOCA) \r\nLEFT JOIN GLBRNC D5 ON (D4.GL05BRNC = D5.GL71BRNC) \r\nLEFT JOIN CTPONT D6 ON (D1.CT03MATNO = D6.CT06MATNO AND \r\nD6.CT06TAG = '090') \r\nLEFT JOIN CTCALL D7 ON (D6.CT06POINTER = D7.CT05POINTER) \r\nLEFT JOIN CTPONT D8 ON (D1.CT03MATNO = D8.CT06MATNO AND \r\nD8.CT06TAG = '245') \r\nINNER JOIN CTTITL D9 ON (D8.CT06POINTER = D9.CT05POINTER) \r\nWHERE D1.CT03MATNO  = ? AND D1.CT03LOCA LIKE ?", new Object[]{controlNo, selectBranch}, (RowMapper)new RowMapper<Records>(){

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
        });
        return byControlNo;
    }

    @Override
    public List<Records> retrieveByItemCategory(String itemCategory, String selectBranch) {
        List byItemCategory = this.jdbcTemplate.query("SELECT D1.CT03DOCNO AS 'AccessionNo', \r\nD1.CT03COPYNO AS 'CopyNo', \r\nD1.CT03VOLUME AS 'Volume', \r\nD1.CT03ICAT AS 'ItemCategory', \r\nD2.GL10DESC AS 'ItemCategoryDesc', \r\nD1.CT03SMD AS 'SMD', \r\nD3.GL47DESC AS 'SMDDesc', \r\nD1.CT03LOCA AS 'Location', \r\nD4.GL05DESC AS 'LocationDesc', \r\nD4.GL05BRNC AS 'Branch', \r\nD5.GL71DESC AS 'BranchDesc', \r\nD1.CT03CRDATE AS 'AccessionDate', \r\nD1.CT03FOREX AS 'Currency', \r\nD1.CT03FCOST AS 'ForeignCost', \r\nD1.CT03LCOST AS 'LocalCost', \r\nD2.GL10DISPLAY AS 'SpineItemCategory', \r\nD3.GL47DISPLAY AS 'SpineSMD', \r\nD1.CT03PARTNO AS 'PartNo', \r\nD1.CT03SPINE AS 'UserDefineSpine', \r\nD7.CT05SCONV AS 'CallNo', \r\nD7.CT05SCONV AS 'ClassNo', \r\nD1.CT03MATNO AS 'ControlNo', \r\nD9.CT05SCONV AS 'Title' \r\nFROM CTDOCM D1 \r\nLEFT JOIN GLICAT D2 ON (D1.CT03ICAT = D2.GL10ICAT) \r\nLEFT JOIN GLSMD D3 ON (D1.CT03SMD = D3.GL47SMD) \r\nLEFT JOIN GLLOCA D4 ON (D1.CT03LOCA = D4.GL05LOCA) \r\nLEFT JOIN GLBRNC D5 ON (D4.GL05BRNC = D5.GL71BRNC) \r\nLEFT JOIN CTPONT D6 ON (D1.CT03MATNO = D6.CT06MATNO AND \r\nD6.CT06TAG = '090') \r\nLEFT JOIN CTCALL D7 ON (D6.CT06POINTER = D7.CT05POINTER) \r\nLEFT JOIN CTPONT D8 ON (D1.CT03MATNO = D8.CT06MATNO AND \r\nD8.CT06TAG = '245') \r\nINNER JOIN CTTITL D9 ON (D8.CT06POINTER = D9.CT05POINTER) \r\nWHERE D1.CT03ICAT  = ? AND D1.CT03LOCA LIKE ? ", new Object[]{itemCategory, selectBranch}, (RowMapper)new RowMapper<Records>(){

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
        });
        return byItemCategory;
    }

    @Override
    public List<Records> retrieveBySMD(String smd, String selectBranch) {
        List bySMD = this.jdbcTemplate.query("SELECT D1.CT03DOCNO AS 'AccessionNo', \r\nD1.CT03COPYNO AS 'CopyNo', \r\nD1.CT03VOLUME AS 'Volume', \r\nD1.CT03ICAT AS 'ItemCategory', \r\nD2.GL10DESC AS 'ItemCategoryDesc', \r\nD1.CT03SMD AS 'SMD', \r\nD3.GL47DESC AS 'SMDDesc', \r\nD1.CT03LOCA AS 'Location', \r\nD4.GL05DESC AS 'LocationDesc', \r\nD4.GL05BRNC AS 'Branch', \r\nD5.GL71DESC AS 'BranchDesc', \r\nD1.CT03CRDATE AS 'AccessionDate', \r\nD1.CT03FOREX AS 'Currency', \r\nD1.CT03FCOST AS 'ForeignCost', \r\nD1.CT03LCOST AS 'LocalCost', \r\nD2.GL10DISPLAY AS 'SpineItemCategory', \r\nD3.GL47DISPLAY AS 'SpineSMD', \r\nD1.CT03PARTNO AS 'PartNo', \r\nD1.CT03SPINE AS 'UserDefineSpine', \r\nD7.CT05SCONV AS 'CallNo', \r\nD10.SP02CCODE AS 'ItemClass', \r\nD7.CT05SCONV AS 'ClassNo', \r\nD1.CT03MATNO AS 'ControlNo', \r\nD9.CT05SCONV AS 'Title' \r\nFROM CTDOCM D1 \r\nLEFT JOIN GLICAT D2 ON (D1.CT03ICAT = D2.GL10ICAT) \r\nLEFT JOIN GLSMD D3 ON (D1.CT03SMD = D3.GL47SMD) \r\nLEFT JOIN GLLOCA D4 ON (D1.CT03LOCA = D4.GL05LOCA) \r\nLEFT JOIN GLBRNC D5 ON (D4.GL05BRNC = D5.GL71BRNC) \r\nLEFT JOIN CTPONT D6 ON (D1.CT03MATNO = D6.CT06MATNO AND \r\nD6.CT06TAG = '090') \r\nLEFT JOIN CTCALL D7 ON (D6.CT06POINTER = D7.CT05POINTER) \r\nLEFT JOIN CTPONT D8 ON (D1.CT03MATNO = D8.CT06MATNO AND \r\nD8.CT06TAG = '245') \r\nLEFT JOIN SPCHARCOLOR D10 ON (D2.GL10DESC = D10.SP02CDESC) \r\nINNER JOIN CTTITL D9 ON (D8.CT06POINTER = D9.CT05POINTER) \r\nWHERE D1.CT03SMD  = ? AND D1.CT03LOCA LIKE ? ", new Object[]{smd, selectBranch}, (RowMapper)new RowMapper<Records>(){

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
        });
        return bySMD;
    }

    @Override
    public List<Records> retrieveByTitle(String title, String selectBranch) {
        List byTitle = this.jdbcTemplate.query("SELECT D1.CT03DOCNO AS 'AccessionNo', \r\nD1.CT03COPYNO AS 'CopyNo', \r\nD1.CT03VOLUME AS 'Volume', \r\nD1.CT03ICAT AS 'ItemCategory', \r\nD2.GL10DESC AS 'ItemCategoryDesc', \r\nD1.CT03SMD AS 'SMD', \r\nD3.GL47DESC AS 'SMDDesc', \r\nD1.CT03LOCA AS 'Location', \r\nD4.GL05DESC AS 'LocationDesc', \r\nD4.GL05BRNC AS 'Branch', \r\nD5.GL71DESC AS 'BranchDesc', \r\nD1.CT03CRDATE AS 'AccessionDate', \r\nD1.CT03FOREX AS 'Currency', \r\nD1.CT03FCOST AS 'ForeignCost', \r\nD1.CT03LCOST AS 'LocalCost', \r\nD2.GL10DISPLAY AS 'SpineItemCategory', \r\nD3.GL47DISPLAY AS 'SpineSMD', \r\nD1.CT03PARTNO AS 'PartNo', \r\nD1.CT03SPINE AS 'UserDefineSpine', \r\nD7.CT05SCONV AS 'CallNo', \r\nD7.CT05SCONV AS 'ClassNo', \r\nD1.CT03MATNO AS 'ControlNo', \r\nD9.CT05SCONV AS 'Title' \r\nFROM CTDOCM D1 \r\nLEFT JOIN GLICAT D2 ON (D1.CT03ICAT = D2.GL10ICAT) \r\nLEFT JOIN GLSMD D3 ON (D1.CT03SMD = D3.GL47SMD) \r\nLEFT JOIN GLLOCA D4 ON (D1.CT03LOCA = D4.GL05LOCA) \r\nLEFT JOIN GLBRNC D5 ON (D4.GL05BRNC = D5.GL71BRNC) \r\nLEFT JOIN CTPONT D6 ON (D1.CT03MATNO = D6.CT06MATNO AND \r\nD6.CT06TAG = '090') \r\nLEFT JOIN CTCALL D7 ON (D6.CT06POINTER = D7.CT05POINTER)\r\nLEFT JOIN CTPONT D8 ON (D1.CT03MATNO = D8.CT06MATNO AND \r\nD8.CT06TAG = '245') \r\nINNER JOIN CTTITL D9 ON (D8.CT06POINTER = D9.CT05POINTER  \r\nAND D9.CT05SCONV LIKE CONCAT(?,'%') ) WHERE D1.CT03LOCA LIKE ? ", new Object[]{title, selectBranch}, (RowMapper)new RowMapper<Records>(){

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
        });
        return byTitle;
    }

    @Override
    public List<Records> pickListItemCategory() {
        List itemCategoryList = this.jdbcTemplate.query("SELECT GL10ICAT AS 'ItemCategory', GL10DESC as 'Description' FROM GLICAT, CTDOCM WHERE CT03ICAT = GL10ICAT GROUP BY GL10ICAT, GL10DESC ORDER BY GL10DESC  ", (RowMapper)new RowMapper<Records>(){

            public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
                Records itemCategory = new Records();
                itemCategory.setItemCategory(rs.getString("ItemCategory"));
                itemCategory.setItemCategoryDesc(rs.getString("Description"));
                return itemCategory;
            }
        });
        return itemCategoryList;
    }

    @Override
    public List<Records> pickListSMD() {
        List smdList = this.jdbcTemplate.query("SELECT GL47SMD AS 'SMD', GL47DESC as 'Description' FROM GLSMD, CTDOCM WHERE CT03SMD = GL47SMD GROUP BY GL47SMD, GL47DESC ORDER BY GL47DESC ", (RowMapper)new RowMapper<Records>(){

            public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
                Records smdlist = new Records();
                smdlist.setSMD(rs.getString("SMD"));
                smdlist.setSMDdesc(rs.getString("Description"));
                return smdlist;
            }
        });
        return smdList;
    }

    @Override
    public List<Records> filterByBranch() {
        List branchList = this.jdbcTemplate.query("SELECT  GL71BRNC AS 'Branch', \r\nGL71DESC as 'Branch Description', \r\nGL05LOCA AS 'Branch Sub Location', \r\nGL05DESC as 'Location Description' \r\nFROM GLLOCA, GLBRNC \r\nWHERE GL05BRNC = GL71BRNC \r\nGROUP BY GL71BRNC, GL71DESC, GL05LOCA, GL05DESC \r\nORDER BY GL71DESC ", (RowMapper)new RowMapper<Records>(){

            public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
                Records branch = new Records();
                branch.setBranch(rs.getString("Branch"));
                branch.setBranchDesc(rs.getString("Branch Description"));
                return branch;
            }
        });
        return branchList;
    }

    @Override
    public List<Records> retrieveForLabel(String accessionNo, String selectBranch) {
        List forLabel = this.jdbcTemplate.query("SELECT D1.CT03DOCNO AS 'AccessionNo',\r\nD1.CT03COPYNO AS 'CopyNo', \r\nD1.CT03VOLUME AS 'Volume', \r\nD1.CT03ICAT AS 'ItemCategory', \r\nD2.GL10DESC AS 'ItemCategoryDesc', \r\nD1.CT03SMD AS 'SMD', \r\nD3.GL47DESC AS 'SMDDesc', \r\nD1.CT03LOCA AS 'Location', \r\nD4.GL05DESC AS 'LocationDesc', \r\nD4.GL05BRNC AS 'Branch', \r\nD5.GL71DESC AS 'BranchDesc', \r\nD1.CT03CRDATE AS 'AccessionDate', \r\nD1.CT03FOREX AS 'Currency', \r\nD1.CT03FCOST AS 'ForeignCost', \r\nD1.CT03LCOST AS 'LocalCost', \r\nD2.GL10DISPLAY AS 'SpineItemCategory', \r\nD3.GL47DISPLAY AS 'SpineSMD', \r\nD1.CT03PARTNO AS 'PartNo', \r\nD1.CT03SPINE AS 'UserDefineSpine', \r\nD7.CT05SRAW AS 'CallNo', \r\nD7.CT05SCONV AS 'ClassNo', \r\nD1.CT03MATNO AS 'ControlNo', \r\nD10.SP02COLSEQ AS 'ColourSequence', \r\nD11.SP01COLCODE AS 'ColourCode', \r\nD10.SP02CCODE AS 'ItemClass', \r\nD9.CT05SRAW AS 'Title' \r\nFROM CTDOCM D1 \r\nLEFT JOIN GLICAT D2 ON (D1.CT03ICAT = D2.GL10ICAT) \r\nLEFT JOIN GLSMD D3 ON (D1.CT03SMD = D3.GL47SMD) \r\nLEFT JOIN GLLOCA D4 ON (D1.CT03LOCA = D4.GL05LOCA) \r\nLEFT JOIN GLBRNC D5 ON (D4.GL05BRNC = D5.GL71BRNC) \r\nLEFT JOIN SPCHARCOLOR D10 ON (D2.GL10DESC = D10.SP02CDESC) \r\nINNER JOIN SPCOLOR D11 ON (D11.SP01COLSEQ = D10.SP02COLSEQ)\r\nLEFT JOIN CTPONT D6 ON (D1.CT03MATNO = D6.CT06MATNO AND D6.CT06TAG = '090') \r\nLEFT JOIN CTCALL D7 ON (D6.CT06POINTER = D7.CT05POINTER AND D7.CT05SCONV LIKE '%' ) \r\nLEFT JOIN CTPONT D8 ON (D1.CT03MATNO = D8.CT06MATNO AND D8.CT06TAG = '245') \r\nINNER JOIN CTTITL D9 ON (D8.CT06POINTER = D9.CT05POINTER) \r\nWHERE D1.CT03DOCNO = ? AND D1.CT03LOCA LIKE ? ", new Object[]{accessionNo, selectBranch}, (RowMapper)new RowMapper<Records>(){

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
        });
        return forLabel;
    }

    @Override
    public List<Records> findColour(String colourCode) {
        List colourList = this.jdbcTemplate.query("SELECT SP02CCODE AS 'ColCode', SP02COLSEQ AS 'ColourSequence', SP01COLCODE AS 'ColourCode' \r\nFROM SPCHARCOLOR \r\nINNER JOIN spcolor ON spcharcolor.SP02COLSEQ=spcolor.SP01COLSEQ \r\nWHERE spcharcolor.SP02CTYPE = 'CLASS' AND spcharcolor.SP02CCODE = ? ", new Object[]{colourCode}, (RowMapper)new RowMapper<Records>(){

            public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
                Records colour = new Records();
                colour.setColourCode2(rs.getString("ColourCode"));
                return colour;
            }
        });
        return colourList;
    }

    @Override
    public List<Records> getAllColour(String colourCode) {
        List colourListAll = this.jdbcTemplate.query("SELECT  SP01COLSEQ AS 'ColourSequence',\r\nSP01COLCODE as 'ColourCode',\r\nSP02CTYPE AS 'Type',\r\nSP02CCODE as 'Code'\r\nFROM SPCOLOR, SPCHARCOLOR \r\nWHERE SP01COLSEQ = SP02COLSEQ \r\nAND SP02CCODE = ? ", new Object[]{colourCode}, (RowMapper)new RowMapper<Records>(){

            public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
                Records colour = new Records();
                colour.setColourCode(rs.getString("ColourCode"));
                return colour;
            }
        });
        return colourListAll;
    }

    @Override
    public List<Records> findItemClass(String itemClass) {
        List itemClassList = this.jdbcTemplate.query("\tSELECT GLICAT.GL10ICAT AS 'ItemCategory', \r\n\tSPCHARCOLOR.SP02CCODE AS 'ItemClass',\r\n\tGLICAT.GL10DESC as 'ItemCategoryDesc',\r\n\tSPCHARCOLOR.SP02COLSEQ AS 'ColourSequence',\r\n\tSPCOLOR.SP01COLCODE AS 'ColourCode'\r\n\tFROM GLICAT\r\n\tINNER JOIN SPCHARCOLOR ON (GLICAT.GL10DESC = SPCHARCOLOR.SP02CDESC) \r\n\tINNER JOIN SPCOLOR ON (SPCHARCOLOR.SP02COLSEQ=SPCOLOR.SP01COLSEQ) \r\n\tWHERE GLICAT.GL10ICAT=?", new Object[]{itemClass}, (RowMapper)new RowMapper<Records>(){

            public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
                Records itemclass = new Records();
                itemclass.setItemClass(rs.getString("ItemClass"));
                itemclass.setColourCode2(rs.getString("ColourCode"));
                return itemclass;
            }
        });
        return itemClassList;
    }

    @Override
    public List<Records> findCutterColour(String colourCode) {
        List colourList = this.jdbcTemplate.query("SELECT SP02CCODE AS 'ColCode', SP02COLSEQ AS 'ColourSequence', SP01COLCODE AS 'ColourCode' \r\nFROM SPCHARCOLOR \r\nINNER JOIN spcolor ON spcharcolor.SP02COLSEQ=spcolor.SP01COLSEQ \r\nWHERE spcharcolor.SP02CTYPE = 'CUTTER' AND spcharcolor.SP02CCODE = ? ", new Object[]{colourCode}, (RowMapper)new RowMapper<Records>(){

            public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
                Records colour = new Records();
                colour.setColourCode2(rs.getString("ColourCode"));
                return colour;
            }
        });
        return colourList;
    }
}
