/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.tagParameter;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import com.kmlink.ilmu.tagParameter.Subfields;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagParameter {
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;
    private String marc;
    private String tag;
    private String description;
    private String abbreDesc;
    private String indexTable;
    private String acronym;
    private String authorityGroup;
    private String fieldLength;
    private String indexLength;
    private String defaultIndicator1;
    private String defaultIndicator2;
    private String defaultValue;
    private String remark;
    private String dateRecorded;
    private String recordedBy;
    private String repeatable;
    private String mandatory;
    private String indexFlag;
    private String keyword;
    private String duplicateCheck;
    private String authorityCheck;
    private String copyPaste;
    private String paraLink;
    private String multimediaTag;

    public String getMarc() {
        return this.marc;
    }

    public void setMarc(String marc) {
        this.marc = marc;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbbreDesc() {
        return this.abbreDesc;
    }

    public void setAbbreDesc(String abbreDesc) {
        this.abbreDesc = abbreDesc;
    }

    public String getIndexTable() {
        return this.indexTable;
    }

    public void setIndexTable(String indexTable) {
        this.indexTable = indexTable;
    }

    public String getAcronym() {
        return this.acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getAuthorityGroup() {
        return this.authorityGroup;
    }

    public void setAuthorityGroup(String authorityGroup) {
        this.authorityGroup = authorityGroup;
    }

    public String getFieldLength() {
        return this.fieldLength;
    }

    public void setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
    }

    public String getIndexLength() {
        return this.indexLength;
    }

    public void setIndexLength(String indexLength) {
        this.indexLength = indexLength;
    }

    public String getDefaultIndicator1() {
        return this.defaultIndicator1;
    }

    public void setDefaultIndicator1(String defaultIndicator1) {
        this.defaultIndicator1 = defaultIndicator1;
    }

    public String getDefaultIndicator2() {
        return this.defaultIndicator2;
    }

    public void setDefaultIndicator2(String defaultIndicator2) {
        this.defaultIndicator2 = defaultIndicator2;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDateRecorded() {
        return this.dateRecorded;
    }

    public void setDateRecorded(String dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public String getRecordedBy() {
        return this.recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getRepeatable() {
        return this.repeatable;
    }

    public void setRepeatable(String repeatable) {
        this.repeatable = repeatable;
    }

    public String getMandatory() {
        return this.mandatory;
    }

    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    public String getIndexFlag() {
        return this.indexFlag;
    }

    public void setIndexFlag(String indexFlag) {
        this.indexFlag = indexFlag;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDuplicateCheck() {
        return this.duplicateCheck;
    }

    public void setDuplicateCheck(String duplicateCheck) {
        this.duplicateCheck = duplicateCheck;
    }

    public String getAuthorityCheck() {
        return this.authorityCheck;
    }

    public void setAuthorityCheck(String authorityCheck) {
        this.authorityCheck = authorityCheck;
    }

    public String getCopyPaste() {
        return this.copyPaste;
    }

    public void setCopyPaste(String copyPaste) {
        this.copyPaste = copyPaste;
    }

    public String getParaLink() {
        return this.paraLink;
    }

    public void setParaLink(String paraLink) {
        this.paraLink = paraLink;
    }

    public String getMultimediaTag() {
        return this.multimediaTag;
    }

    public void setMultimediaTag(String multimediaTag) {
        this.multimediaTag = multimediaTag;
    }

    public TagParameter(String marc, String tag, String description) {
        this.marc = marc;
        this.tag = tag;
        this.description = description;
    }

    public TagParameter(String marc, String tag, String description, String abbreDesc, String indexTable, String acronym, String authorityGroup, String fieldLength, String indexLength, String defaultIndicator1, String defaultIndicator2, String defaultValue, String remark, String dateRecorded, String recordedBy, String repeatable, String mandatory, String indexFlag, String keyword, String duplicateCheck, String authorityCheck, String copyPaste, String paraLink, String multimediaTag) {
        this.marc = marc;
        this.tag = tag;
        this.description = description;
        this.abbreDesc = abbreDesc;
        this.indexTable = indexTable;
        this.acronym = acronym;
        this.authorityGroup = authorityGroup;
        this.fieldLength = fieldLength;
        this.indexLength = indexLength;
        this.defaultIndicator1 = defaultIndicator1;
        this.defaultIndicator2 = defaultIndicator2;
        this.defaultValue = defaultValue;
        this.remark = remark;
        this.dateRecorded = dateRecorded;
        this.recordedBy = recordedBy;
        this.repeatable = repeatable;
        this.mandatory = mandatory;
        this.indexFlag = indexFlag;
        this.keyword = keyword;
        this.duplicateCheck = duplicateCheck;
        this.authorityCheck = authorityCheck;
        this.copyPaste = copyPaste;
        this.paraLink = paraLink;
        this.multimediaTag = multimediaTag;
    }

    public TagParameter(String indexTable, String acronym, String authorityGroup, String fieldLength, String indexLength, String defaultIndicator1, String defaultIndicator2, String defaultValue, String remark, String dateRecorded, String recordedBy, String repeatable, String mandatory, String indexFlag, String keyword, String duplicateCheck, String authorityCheck, String copyPaste, String paraLink, String multimediaTag) {
        this.indexTable = indexTable;
        this.acronym = acronym;
        this.authorityGroup = authorityGroup;
        this.fieldLength = fieldLength;
        this.indexLength = indexLength;
        this.defaultIndicator1 = defaultIndicator1;
        this.defaultIndicator2 = defaultIndicator2;
        this.defaultValue = defaultValue;
        this.remark = remark;
        this.dateRecorded = dateRecorded;
        this.recordedBy = recordedBy;
        this.repeatable = repeatable;
        this.mandatory = mandatory;
        this.indexFlag = indexFlag;
        this.keyword = keyword;
        this.duplicateCheck = duplicateCheck;
        this.authorityCheck = authorityCheck;
        this.copyPaste = copyPaste;
        this.paraLink = paraLink;
        this.multimediaTag = multimediaTag;
    }

    public static List<TagParameter> loadAllTagParameter() {
        String query = "SELECT * FROM GLTAGP WHERE GL17MARC='U'";
        ArrayList<TagParameter> loadTagParameter = new ArrayList<TagParameter>();
        try {
            try {
                conn = DBConnection.getConnection();
                pstmt = conn.prepareStatement(query);
                rs = pstmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    loadTagParameter.add(new TagParameter(rs.getString("GL17MARC"), rs.getString("GL17TAG"), rs.getString("GL17DESC"), rs.getString("GL17TRUC"), rs.getString("GL17TABNAME"), rs.getString("GL17ACRO"), rs.getString("GL17GRID"), rs.getString("GL17LEN"), rs.getString("GL17IDXLANG"), rs.getString("GL17INDI1"), rs.getString("GL17INDI2"), rs.getString("GL17DEFAULT"), rs.getString("GL17REMARK"), rs.getString("GL17DATEREC"), rs.getString("GL17RECBY"), rs.getString("GL17REPEAT"), rs.getString("GL17MANDA"), rs.getString("GL17IDXFLAG"), rs.getString("GL17KEYWORD"), rs.getString("GL17KEYWORD"), rs.getString("GL17AUTFLAG"), rs.getString("GL17CPFLAG"), rs.getString("GL17PARAMIPS"), rs.getString("GL17MEDIA")));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return loadTagParameter;
    }

    public void TagContainer(TagParameter tagParameter, List<Subfields> getSubfield) {
    }

    public static List<TagParameter> viewTag(String tagId) {
        String query = "SELECT * FROM GLTAGP WHERE GL17TAG=? AND GL17MARC='U'";
        ArrayList<TagParameter> tagDetails = new ArrayList<TagParameter>();
        try {
            try {
                conn = DBConnection.getConnection();
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, tagId);
                rs = pstmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    tagDetails.add(new TagParameter(rs.getString("GL17TABNAME"), rs.getString("GL17ACRO"), rs.getString("GL17GRID"), rs.getString("GL17LEN"), rs.getString("GL17IDXLANG"), rs.getString("GL17INDI1"), rs.getString("GL17INDI2"), rs.getString("GL17DEFAULT"), rs.getString("GL17REMARK"), rs.getString("GL17DATEREC"), rs.getString("GL17RECBY"), rs.getString("GL17REPEAT"), rs.getString("GL17MANDA"), rs.getString("GL17IDXFLAG"), rs.getString("GL17KEYWORD"), rs.getString("GL17KEYWORD"), rs.getString("GL17AUTFLAG"), rs.getString("GL17CPFLAG"), rs.getString("GL17PARAMIPS"), rs.getString("GL17MEDIA")));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tagDetails;
    }

    public static void editTagParameter(String marc, String tagId, String desc, String abbreDesc, String acronym, String authorityGroup, String fieldLength, String indexLanguage, String indicator1, String indicator2, String defaultValue, String remark, String repeatable, String mandatory, String indexFlag, String keyword, String duplicateCheck, String authorityFlag, String copyPaste, String paramLink, String multimediaTag, String ind1Undefine, String ind1Input0, String ind1Input1, String ind1Input2, String ind1Input3, String ind1Input4, String ind1Input5, String ind1Input6, String ind1Input7, String ind1Input8, String ind1Input9, String ind2Undefine, String ind2Input0, String ind2Input1, String ind2Input2, String ind2Input3, String ind2Input4, String ind2Input5, String ind2Input6, String ind2Input7, String ind2Input8, String ind2Input9, String sfA, String sfB, String sfC, String sfD, String sfE, String sfF, String sfG, String sfH, String sfI, String sfJ, String sfK, String sfL, String sfM, String sfN, String sfO, String sfP, String sfQ, String sfR, String sfS, String sfT, String sfU, String sfV, String sfW, String sfX, String sfY, String sfZ, String sf0, String sf1, String sf2, String sf3, String sf4, String sf5, String sf6, String sf7, String sf8, String sf9) {
        block12: {
            System.out.println("marc : " + marc);
            System.out.println("tagId : " + tagId);
            System.out.println("description : " + desc);
            System.out.println("Abbreviated description : " + abbreDesc);
            System.out.println("acronym : " + acronym);
            System.out.println("fieldLength : " + fieldLength);
            System.out.println("indexLanguage : " + indexLanguage);
            System.out.println("indicator1 : " + indicator1);
            System.out.println("defaultValue : " + defaultValue);
            System.out.println("remark : " + remark);
            System.out.println("repeatable : " + repeatable);
            System.out.println("mandatory\t : " + mandatory);
            System.out.println("indexFlag : " + indexFlag);
            System.out.println("keyword : " + keyword);
            System.out.println("duplicateCheck : " + duplicateCheck);
            System.out.println("authorityFlag : " + authorityFlag);
            System.out.println("copyPaste : " + copyPaste);
            System.out.println("paramLink : " + paramLink);
            System.out.println("multimediaTag : " + multimediaTag);
            int gl17Length = 0;
            if (!fieldLength.equals("")) {
                gl17Length = Integer.parseInt(fieldLength);
            }
            String queryUpdate = "UPDATE GLTAGP SET GL17DESC='" + desc + "' , GL17TRUC = '" + abbreDesc + "',GL17ACRO='" + acronym + "' , GL17LEN = " + gl17Length + ", GL17IDXLANG = '" + indexLanguage + "', GL17DEFAULT = '" + defaultValue + "', GL17REMARK= '" + remark + "'," + " GL17REPEAT= '" + repeatable + "', GL17MANDA= '" + mandatory + "', GL17IDXFLAG= '" + indexFlag + "' , GL17KEYWORD= '" + keyword + "'" + ", GL17AUTFLAG= '" + authorityFlag + "' , GL17CPFLAG= '" + copyPaste + "' , GL17PARAMIPS= '" + paramLink + "'," + "GL17MEDIA= '" + multimediaTag + "' WHERE GL17MARC='" + marc + "' AND GL17TAG='" + tagId + "'";
            try {
                try {
                    conn = DBConnection.getConnection();
                    System.out.println("query: " + queryUpdate);
                    pstmt = conn.prepareStatement(queryUpdate);
                    pstmt.executeUpdate();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block12;
                }
            }
            catch (Throwable throwable) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        TagParameter.insertIndicators(marc, tagId, ind1Undefine, ind1Input0, ind1Input1, ind1Input2, ind1Input3, ind1Input4, ind1Input5, ind1Input6, ind1Input7, ind1Input8, ind1Input9, ind2Undefine, ind2Input0, ind2Input1, ind2Input2, ind2Input3, ind2Input4, ind2Input5, ind2Input6, ind2Input7, ind2Input8, ind2Input9);
        TagParameter.insertSubfield(marc, tagId, sfA, sfB, sfC, sfD, sfE, sfF, sfG, sfH, sfI, sfJ, sfK, sfL, sfM, sfN, sfO, sfP, sfQ, sfR, sfS, sfT, sfU, sfV, sfW, sfX, sfY, sfZ, sf0, sf1, sf2, sf3, sf4, sf5, sf6, sf7, sf8, sf9);
    }

    public static void insertIndicators(String marc, String tagId, String ind1Undefine, String ind1Input0, String ind1Input1, String ind1Input2, String ind1Input3, String ind1Input4, String ind1Input5, String ind1Input6, String ind1Input7, String ind1Input8, String ind1Input9, String ind2Undefine, String ind2Input0, String ind2Input1, String ind2Input2, String ind2Input3, String ind2Input4, String ind2Input5, String ind2Input6, String ind2Input7, String ind2Input8, String ind2Input9) {
        String deleteQueryInd = "DELETE FROM GLMINDI WHERE GL18MARC='" + marc + "' AND GL18TAG='" + tagId + "'";
        String queryInsertInd = "INSERT INTO GLMINDI (GL18MARC, GL18TAG, GL18INDILVL, GL18INDI, GL18DESC1, GL18DESC2) VALUES (?,?,?,?,?,?)";
        try {
            try {
                conn = DBConnection.getConnection();
                PreparedStatement stmtDeleteInd = null;
                stmtDeleteInd = conn.prepareStatement(deleteQueryInd);
                stmtDeleteInd.executeUpdate();
                PreparedStatement stmtInsertInd = null;
                stmtInsertInd = conn.prepareStatement(queryInsertInd);
                if (ind1Input0 != null && !ind1Input0.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "1");
                    stmtInsertInd.setString(4, "0");
                    stmtInsertInd.setString(5, ind1Input0);
                    stmtInsertInd.setString(6, ind1Input0);
                    stmtInsertInd.executeUpdate();
                }
                if (ind1Input1 != null && !ind1Input1.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "1");
                    stmtInsertInd.setString(4, "1");
                    stmtInsertInd.setString(5, ind1Input1);
                    stmtInsertInd.setString(6, ind1Input1);
                    stmtInsertInd.executeUpdate();
                }
                if (ind1Input2 != null && !ind1Input2.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "1");
                    stmtInsertInd.setString(4, "2");
                    stmtInsertInd.setString(5, ind1Input2);
                    stmtInsertInd.setString(6, ind1Input2);
                    stmtInsertInd.executeUpdate();
                }
                if (ind1Input3 != null && !ind1Input3.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "1");
                    stmtInsertInd.setString(4, "3");
                    stmtInsertInd.setString(5, ind1Input3);
                    stmtInsertInd.setString(6, ind1Input3);
                    stmtInsertInd.executeUpdate();
                }
                if (ind1Input4 != null && !ind1Input4.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "1");
                    stmtInsertInd.setString(4, "4");
                    stmtInsertInd.setString(5, ind1Input4);
                    stmtInsertInd.setString(6, ind1Input4);
                    stmtInsertInd.executeUpdate();
                }
                if (ind1Input5 != null && !ind1Input5.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "1");
                    stmtInsertInd.setString(4, "5");
                    stmtInsertInd.setString(5, ind1Input5);
                    stmtInsertInd.setString(6, ind1Input5);
                    stmtInsertInd.executeUpdate();
                }
                if (ind1Input6 != null && !ind1Input6.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "1");
                    stmtInsertInd.setString(4, "6");
                    stmtInsertInd.setString(5, ind1Input6);
                    stmtInsertInd.setString(6, ind1Input6);
                    stmtInsertInd.executeUpdate();
                }
                if (ind1Input7 != null && !ind1Input7.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "1");
                    stmtInsertInd.setString(4, "7");
                    stmtInsertInd.setString(5, ind1Input7);
                    stmtInsertInd.setString(6, ind1Input7);
                    stmtInsertInd.executeUpdate();
                }
                if (ind1Input8 != null && !ind1Input8.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "1");
                    stmtInsertInd.setString(4, "8");
                    stmtInsertInd.setString(5, ind1Input8);
                    stmtInsertInd.setString(6, ind1Input8);
                    stmtInsertInd.executeUpdate();
                }
                if (ind1Input9 != null && !ind1Input9.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "1");
                    stmtInsertInd.setString(4, "9");
                    stmtInsertInd.setString(5, ind1Input9);
                    stmtInsertInd.setString(6, ind1Input9);
                    stmtInsertInd.executeUpdate();
                }
                if (ind1Undefine != null && !ind1Undefine.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "1");
                    stmtInsertInd.setString(4, "#");
                    stmtInsertInd.setString(5, ind1Undefine);
                    stmtInsertInd.setString(6, ind1Undefine);
                    stmtInsertInd.executeUpdate();
                }
                if (ind2Input0 != null && !ind2Input0.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "2");
                    stmtInsertInd.setString(4, "0");
                    stmtInsertInd.setString(5, ind2Input0);
                    stmtInsertInd.setString(6, ind2Input0);
                    stmtInsertInd.executeUpdate();
                }
                if (ind2Input1 != null && !ind2Input1.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "2");
                    stmtInsertInd.setString(4, "1");
                    stmtInsertInd.setString(5, ind2Input1);
                    stmtInsertInd.setString(6, ind2Input1);
                    stmtInsertInd.executeUpdate();
                }
                if (ind2Input2 != null && !ind2Input2.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "2");
                    stmtInsertInd.setString(4, "2");
                    stmtInsertInd.setString(5, ind2Input2);
                    stmtInsertInd.setString(6, ind2Input2);
                    stmtInsertInd.executeUpdate();
                }
                if (ind2Input3 != null && !ind2Input3.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "2");
                    stmtInsertInd.setString(4, "3");
                    stmtInsertInd.setString(5, ind2Input3);
                    stmtInsertInd.setString(6, ind2Input3);
                    stmtInsertInd.executeUpdate();
                }
                if (ind2Input4 != null && !ind2Input4.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "2");
                    stmtInsertInd.setString(4, "4");
                    stmtInsertInd.setString(5, ind2Input4);
                    stmtInsertInd.setString(6, ind2Input4);
                    stmtInsertInd.executeUpdate();
                }
                if (ind2Input5 != null && !ind2Input5.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "2");
                    stmtInsertInd.setString(4, "5");
                    stmtInsertInd.setString(5, ind2Input5);
                    stmtInsertInd.setString(6, ind2Input5);
                    stmtInsertInd.executeUpdate();
                }
                if (ind2Input6 != null && !ind2Input6.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "2");
                    stmtInsertInd.setString(4, "6");
                    stmtInsertInd.setString(5, ind2Input6);
                    stmtInsertInd.setString(6, ind2Input6);
                    stmtInsertInd.executeUpdate();
                }
                if (ind2Input7 != null && !ind2Input7.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "2");
                    stmtInsertInd.setString(4, "7");
                    stmtInsertInd.setString(5, ind2Input7);
                    stmtInsertInd.setString(6, ind2Input7);
                    stmtInsertInd.executeUpdate();
                }
                if (ind2Input8 != null && !ind2Input8.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "2");
                    stmtInsertInd.setString(4, "8");
                    stmtInsertInd.setString(5, ind2Input8);
                    stmtInsertInd.setString(6, ind2Input8);
                    stmtInsertInd.executeUpdate();
                }
                if (ind2Input9 != null && !ind2Input9.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "2");
                    stmtInsertInd.setString(4, "9");
                    stmtInsertInd.setString(5, ind2Input9);
                    stmtInsertInd.setString(6, ind2Input9);
                    stmtInsertInd.executeUpdate();
                }
                if (ind2Undefine != null && !ind2Undefine.equals("")) {
                    stmtInsertInd.setString(1, marc);
                    stmtInsertInd.setString(2, tagId);
                    stmtInsertInd.setString(3, "2");
                    stmtInsertInd.setString(4, "#");
                    stmtInsertInd.setString(5, ind2Undefine);
                    stmtInsertInd.setString(6, ind2Undefine);
                    stmtInsertInd.executeUpdate();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertSubfield(String marc, String tagId, String sfA, String sfB, String sfC, String sfD, String sfE, String sfF, String sfG, String sfH, String sfI, String sfJ, String sfK, String sfL, String sfM, String sfN, String sfO, String sfP, String sfQ, String sfR, String sfS, String sfT, String sfU, String sfV, String sfW, String sfX, String sfY, String sfZ, String sf0, String sf1, String sf2, String sf3, String sf4, String sf5, String sf6, String sf7, String sf8, String sf9) {
        String deleteQuerySF = "DELETE FROM GLMSUBF WHERE GL23MARC='" + marc + "' AND GL23TAG='" + tagId + "'";
        String queryInsertSF = "INSERT INTO GLMSUBF (GL23MARC, GL23TAG, GL23SUBF, GL23DESC) VALUES (?,?,?,?)";
        try {
            try {
                conn = DBConnection.getConnection();
                PreparedStatement stmtDeleteSF = null;
                stmtDeleteSF = conn.prepareStatement(deleteQuerySF);
                stmtDeleteSF.executeUpdate();
                PreparedStatement stmtInsertSF = null;
                stmtInsertSF = conn.prepareStatement(queryInsertSF);
                if (sf0 != null && !sf0.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "0");
                    stmtInsertSF.setString(4, sf0);
                    stmtInsertSF.executeUpdate();
                }
                if (sf1 != null && !sf1.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "1");
                    stmtInsertSF.setString(4, sf1);
                    stmtInsertSF.executeUpdate();
                }
                if (sf2 != null && !sf2.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "2");
                    stmtInsertSF.setString(4, sf2);
                    stmtInsertSF.executeUpdate();
                }
                if (sf3 != null && !sf3.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "3");
                    stmtInsertSF.setString(4, sf3);
                    stmtInsertSF.executeUpdate();
                }
                if (sf4 != null && !sf4.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "4");
                    stmtInsertSF.setString(4, sf4);
                    stmtInsertSF.executeUpdate();
                }
                if (sf5 != null && !sf5.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "5");
                    stmtInsertSF.setString(4, sf5);
                    stmtInsertSF.executeUpdate();
                }
                if (sf6 != null && !sf6.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "6");
                    stmtInsertSF.setString(4, sf6);
                    stmtInsertSF.executeUpdate();
                }
                if (sf7 != null && !sf7.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "7");
                    stmtInsertSF.setString(4, sf7);
                    stmtInsertSF.executeUpdate();
                }
                if (sf8 != null && !sf8.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "8");
                    stmtInsertSF.setString(4, sf8);
                    stmtInsertSF.executeUpdate();
                }
                if (sf9 != null && !sf9.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "9");
                    stmtInsertSF.setString(4, sf9);
                    stmtInsertSF.executeUpdate();
                }
                if (sfA != null && !sfA.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "a");
                    stmtInsertSF.setString(4, sfA);
                    stmtInsertSF.executeUpdate();
                }
                if (sfB != null && !sfB.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "b");
                    stmtInsertSF.setString(4, sfB);
                    stmtInsertSF.executeUpdate();
                }
                if (sfC != null && !sfC.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "c");
                    stmtInsertSF.setString(4, sfC);
                    stmtInsertSF.executeUpdate();
                }
                if (sfD != null && !sfD.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "d");
                    stmtInsertSF.setString(4, sfD);
                    stmtInsertSF.executeUpdate();
                }
                if (sfE != null && !sfE.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "e");
                    stmtInsertSF.setString(4, sfE);
                    stmtInsertSF.executeUpdate();
                }
                if (sfF != null && !sfF.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "f");
                    stmtInsertSF.setString(4, sfF);
                    stmtInsertSF.executeUpdate();
                }
                if (sfG != null && !sfG.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "g");
                    stmtInsertSF.setString(4, sfG);
                    stmtInsertSF.executeUpdate();
                }
                if (sfH != null && !sfH.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "h");
                    stmtInsertSF.setString(4, sfH);
                    stmtInsertSF.executeUpdate();
                }
                if (sfI != null && !sfI.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "i");
                    stmtInsertSF.setString(4, sfI);
                    stmtInsertSF.executeUpdate();
                }
                if (sfJ != null && !sfJ.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "j");
                    stmtInsertSF.setString(4, sfJ);
                    stmtInsertSF.executeUpdate();
                }
                if (sfK != null && !sfK.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "k");
                    stmtInsertSF.setString(4, sfK);
                    stmtInsertSF.executeUpdate();
                }
                if (sfL != null && !sfL.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "l");
                    stmtInsertSF.setString(4, sfL);
                    stmtInsertSF.executeUpdate();
                }
                if (sfM != null && !sfM.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "m");
                    stmtInsertSF.setString(4, sfM);
                    stmtInsertSF.executeUpdate();
                }
                if (sfN != null && !sfN.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "n");
                    stmtInsertSF.setString(4, sfN);
                    stmtInsertSF.executeUpdate();
                }
                if (sfO != null && !sfO.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "o");
                    stmtInsertSF.setString(4, sfP);
                    stmtInsertSF.executeUpdate();
                }
                if (sfP != null && !sfP.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "p");
                    stmtInsertSF.setString(4, sfP);
                    stmtInsertSF.executeUpdate();
                }
                if (sfQ != null && !sfQ.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "q");
                    stmtInsertSF.setString(4, sfQ);
                    stmtInsertSF.executeUpdate();
                }
                if (sfR != null && !sfR.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "r");
                    stmtInsertSF.setString(4, sfR);
                    stmtInsertSF.executeUpdate();
                }
                if (sfS != null && !sfS.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "s");
                    stmtInsertSF.setString(4, sfS);
                    stmtInsertSF.executeUpdate();
                }
                if (sfT != null && !sfT.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "t");
                    stmtInsertSF.setString(4, sfT);
                    stmtInsertSF.executeUpdate();
                }
                if (sfU != null && !sfU.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "u");
                    stmtInsertSF.setString(4, sfU);
                    stmtInsertSF.executeUpdate();
                }
                if (sfV != null && !sfV.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "v");
                    stmtInsertSF.setString(4, sfV);
                    stmtInsertSF.executeUpdate();
                }
                if (sfW != null && !sfW.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "w");
                    stmtInsertSF.setString(4, sfW);
                    stmtInsertSF.executeUpdate();
                }
                if (sfX != null && !sfX.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "x");
                    stmtInsertSF.setString(4, sfX);
                    stmtInsertSF.executeUpdate();
                }
                if (sfY != null && !sfY.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "y");
                    stmtInsertSF.setString(4, sfY);
                    stmtInsertSF.executeUpdate();
                }
                if (sfZ != null && !sfZ.equals("")) {
                    stmtInsertSF.setString(1, marc);
                    stmtInsertSF.setString(2, tagId);
                    stmtInsertSF.setString(3, "z");
                    stmtInsertSF.setString(4, sfZ);
                    stmtInsertSF.executeUpdate();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addNewTagParameter(String marc, String tagId, String desc, String abbreDesc, String indexTable, String acronym, String authorityGroup, String fieldLength, String indexLanguage, Short indicator1, Short indicator2, String defaultValue, String remark, String repeatable, String mandatory, String indexFlag, String keyword, String duplicateCheck, String authorityFlag, String copyPaste, String paramLink, String multimediaTag, String ind1Undefine, String ind1Input0, String ind1Input1, String ind1Input2, String ind1Input3, String ind1Input4, String ind1Input5, String ind1Input6, String ind1Input7, String ind1Input8, String ind1Input9, String ind2Undefine, String ind2Input0, String ind2Input1, String ind2Input2, String ind2Input3, String ind2Input4, String ind2Input5, String ind2Input6, String ind2Input7, String ind2Input8, String ind2Input9, String sfA, String sfB, String sfC, String sfD, String sfE, String sfF, String sfG, String sfH, String sfI, String sfJ, String sfK, String sfL, String sfM, String sfN, String sfO, String sfP, String sfQ, String sfR, String sfS, String sfT, String sfU, String sfV, String sfW, String sfX, String sfY, String sfZ, String sf0, String sf1, String sf2, String sf3, String sf4, String sf5, String sf6, String sf7, String sf8, String sf9) throws SQLException {
        block13: {
            String insertQuery = "INSERT INTO GLTAGP (GL17MARC, GL17TAG, GL17DESC, GL17TRUC, GL17TABNAME, GL17ACRO, GL17LEN, GL17IDXLANG, GL17INDI1, GL17INDI2, GL17DEFAULT, GL17REMARK, GL17REPEAT, GL17MANDA, GL17IDXFLAG, GL17KEYWORD, GL17AUTFLAG, GL17CPFLAG, GL17PARAMIPS, GL17MEDIA) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            conn = DBConnection.getConnection();
            try {
                try {
                    pstmt = conn.prepareStatement(insertQuery);
                    pstmt.setString(1, marc);
                    pstmt.setString(2, tagId);
                    pstmt.setString(3, desc);
                    pstmt.setString(4, abbreDesc);
                    pstmt.setString(5, indexTable);
                    pstmt.setString(6, acronym);
                    if (!fieldLength.isEmpty()) {
                        pstmt.setShort(7, Short.valueOf(fieldLength));
                    } else {
                        pstmt.setNull(7, 4);
                    }
                    pstmt.setString(8, indexLanguage);
                    pstmt.setShort(9, indicator1);
                    pstmt.setShort(10, indicator2);
                    pstmt.setString(11, defaultValue);
                    pstmt.setString(12, remark);
                    pstmt.setString(13, mandatory);
                    pstmt.setString(14, indexFlag);
                    pstmt.setString(15, keyword);
                    pstmt.setString(16, duplicateCheck);
                    pstmt.setString(17, authorityFlag);
                    pstmt.setString(18, copyPaste);
                    pstmt.setString(19, paramLink);
                    pstmt.setString(20, multimediaTag);
                    pstmt.toString();
                    pstmt.executeUpdate();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block13;
                }
            }
            catch (Throwable throwable) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        TagParameter.insertIndicators(marc, tagId, ind1Undefine, ind1Input0, ind1Input1, ind1Input2, ind1Input3, ind1Input4, ind1Input5, ind1Input6, ind1Input7, ind1Input8, ind1Input9, ind2Undefine, ind2Input0, ind2Input1, ind2Input2, ind2Input3, ind2Input4, ind2Input5, ind2Input6, ind2Input7, ind2Input8, ind2Input9);
        TagParameter.insertSubfield(marc, tagId, sfA, sfB, sfC, sfD, sfE, sfF, sfG, sfH, sfI, sfJ, sfK, sfL, sfM, sfN, sfO, sfP, sfQ, sfR, sfS, sfT, sfU, sfV, sfW, sfX, sfY, sfZ, sf0, sf1, sf2, sf3, sf4, sf5, sf6, sf7, sf8, sf9);
    }

    public static void deleteTagParameter(String marc, String tagNo, String desc, String abbreDesc, String indexTable) throws SQLException {
        String deleteQuery = "DELETE FROM GLTAGP WHERE GL17MARC= '" + marc + "' AND GL17TAG ='" + tagNo + "' AND GL17DESC ='" + desc + "' AND GL17TRUC ='" + abbreDesc + "' AND GL17TABNAME= '" + indexTable + "'";
        conn = DBConnection.getConnection();
        try {
            try {
                pstmt = conn.prepareStatement(deleteQuery);
                pstmt.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> getMARCid() {
        ArrayList<String> MARCHid = new ArrayList<String>();
        String query = "SELECT GL16MARC FROM GLMARC ";
        Connection conn = null;
        try {
            try {
                conn = DBConnection.getConnection();
                pstmt = conn.prepareStatement(query);
                rs = pstmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    MARCHid.add(rs.getString("GL16MARC"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return MARCHid;
    }

    public static List<String> getMARCdesc() {
        ArrayList<String> MARCHdesc = new ArrayList<String>();
        String query = "SELECT GL16DESC FROM GLMARC ";
        try {
            try {
                conn = DBConnection.getConnection();
                pstmt = conn.prepareStatement(query);
                rs = pstmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    MARCHdesc.add(rs.getString("GL16DESC"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return MARCHdesc;
    }

    public static List<String> getFileterPatronCategoryId() {
        ArrayList<String> patron = new ArrayList<String>();
        String query = "SELECT GL07CATE FROM GLCATE ";
        try {
            try {
                conn = DBConnection.getConnection();
                pstmt = conn.prepareStatement(query);
                rs = pstmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                System.out.println();
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    patron.add(rs.getString("GL07CATE"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return patron;
    }

    public static List<String> getFileterPatronCategoryDesc() {
        ArrayList<String> patron = new ArrayList<String>();
        String query = "SELECT GL07DESC FROM GLCATE ";
        try {
            try {
                conn = DBConnection.getConnection();
                pstmt = conn.prepareStatement(query);
                rs = pstmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    patron.add(rs.getString("GL07DESC"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return patron;
    }

    public static boolean deleteTag(String tagNo) throws SQLException, UnknownHostException {
        boolean bSuccessful = false;
        String query = "DELETE FROM GLTAGP WHERE GL17MARC='U' AND GL17TAG = '" + tagNo + "' ";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                pstmt = conn.prepareStatement(query);
                pstmt.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bSuccessful;
    }

    public static List<TagParameter> FindTag(String marcId, String marcDesc, String selectedRadio, String userInput) {
        ArrayList<TagParameter> tagList = new ArrayList<TagParameter>();
        System.out.println("tag id: " + marcId);
        System.out.println("tag desc: " + marcDesc);
        System.out.println("select radio: " + selectedRadio);
        System.out.println("user input: " + userInput);
        String query = null;
        query = selectedRadio.equals("tagId") ? "SELECT * FROM GLTAGP WHERE GL17TAG='" + userInput + "'" : "SELECT * FROM GLTAGP WHERE GL17DESC='" + userInput + "'  AND GL17MARC='" + marcId + "'";
        try {
            try {
                System.out.println("query: " + query);
                conn = DBConnection.getConnection();
                pstmt = conn.prepareStatement(query);
                rs = pstmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    tagList.add(new TagParameter(rs.getString("GL17MARC"), rs.getString("GL17TAG"), rs.getString("GL17DESC"), rs.getString("GL17TRUC"), rs.getString("GL17TABNAME"), rs.getString("GL17ACRO"), rs.getString("GL17GRID"), rs.getString("GL17LEN"), rs.getString("GL17IDXLANG"), rs.getString("GL17INDI1"), rs.getString("GL17INDI2"), rs.getString("GL17DEFAULT"), rs.getString("GL17REMARK"), rs.getString("GL17DATEREC"), rs.getString("GL17RECBY"), rs.getString("GL17REPEAT"), rs.getString("GL17MANDA"), rs.getString("GL17IDXFLAG"), rs.getString("GL17KEYWORD"), rs.getString("GL17KEYWORD"), rs.getString("GL17AUTFLAG"), rs.getString("GL17CPFLAG"), rs.getString("GL17PARAMIPS"), rs.getString("GL17MEDIA")));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tagList;
    }
}
