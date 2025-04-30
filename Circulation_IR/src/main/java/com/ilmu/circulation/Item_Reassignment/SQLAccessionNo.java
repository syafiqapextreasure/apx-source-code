/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.Item_Reassignment;

import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLAccessionNo {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String ACCESSION07 = null;
    private String MATNO = null;
    private String CALLNUMBER = null;
    private String TITLE = null;
    private String SICAT = null;
    private String PICAT = null;
    private String DTSTART07 = null;
    private String DTEND07 = null;
    private String AUTHORISED07 = null;
    private String SICATDESC = null;
    private String PICATDESC = null;
    private String DOCNO = null;
    private String ICAT = null;
    private String DESC10 = null;
    private String LOCA03 = null;
    private String DESC05 = null;
    private String BRNC71 = null;
    private String DESC71 = null;
    private String ACCESSION13 = null;
    private String SLOCA = null;
    private String SLOCADESC = null;
    private String ORIBRANCH = null;
    private String PLOCA = null;
    private String ReassignedBRANCH = null;
    private String PLOCADESC = null;
    private String DTSTART13 = null;
    private String DTEND13 = null;
    private String AUTHORISED13 = null;
    private String DESC36 = null;
    private String DESSICAT = null;
    private String DESPICAT = null;
    private String CRDATE = null;
    private String CREBY = null;
    private String NAMEAUTHORISED = null;
    private String NAMECREBY = null;
    private String STAT = null;
    private String DESC08 = null;
    private String CATE = null;
    private String DESC07 = null;
    private String GRID = null;
    private String NAME02 = null;
    private String EXPDATE = null;
    private String IMG = null;
    private String BRNC14 = null;
    private String loginmane = null;

    public SQLAccessionNo(String CI07ACCESSION, String CT03MATNO, String CALLNUMBER, String TITLE, String CI07SICAT, String SICATDESC, String CI07PICAT, String PICATDESC, String CI07DTSTART, String CI07DTEND, String CI07AUTHORISED, String loginmane) {
        this.ACCESSION07 = CI07ACCESSION;
        this.MATNO = CT03MATNO;
        this.CALLNUMBER = CALLNUMBER;
        this.TITLE = TITLE;
        this.SICAT = CI07SICAT;
        this.SICATDESC = SICATDESC;
        this.PICAT = CI07PICAT;
        this.PICATDESC = PICATDESC;
        this.DTSTART07 = CI07DTSTART;
        this.DTEND07 = CI07DTEND;
        this.AUTHORISED07 = CI07AUTHORISED;
        this.loginmane = loginmane;
    }

    public SQLAccessionNo(String CT03DOCNO, String CT03MATNO, String CT03ICAT, String GL10DESC, String CALLNUMBER, String TITLE) {
        this.DOCNO = CT03DOCNO;
        this.MATNO = CT03MATNO;
        this.ICAT = CT03ICAT;
        this.DESC10 = GL10DESC;
        this.CALLNUMBER = CALLNUMBER;
        this.TITLE = TITLE;
    }

    public SQLAccessionNo(String CT03DOCNO, String CT03MATNO, String CT03LOCA, String GL05DESC, String GL71BRNC, String GL71DESC, String CALLNUMBER, String TITLE) {
        this.DOCNO = CT03DOCNO;
        this.MATNO = CT03MATNO;
        this.LOCA03 = CT03LOCA;
        this.DESC05 = GL05DESC;
        this.BRNC71 = GL71BRNC;
        this.DESC71 = GL71DESC;
        this.CALLNUMBER = CALLNUMBER;
        this.TITLE = TITLE;
    }

    public SQLAccessionNo(String CI13ACCESSION, String CT03MATNO, String CI13SLOCA, String SLOCADESC, String ORIBRANCH, String CI13PLOCA, String ReassignedBRANCH, String PLOCADESC, String CI13DTSTART, String CI13DTEND, String CI13AUTHORISED, String CALLNUMBER, String TITLE) {
        this.ACCESSION13 = CI13ACCESSION;
        this.MATNO = CT03MATNO;
        this.SLOCA = CI13SLOCA;
        this.SLOCADESC = SLOCADESC;
        this.ORIBRANCH = ORIBRANCH;
        this.PLOCA = CI13PLOCA;
        this.ReassignedBRANCH = ReassignedBRANCH;
        this.PLOCADESC = PLOCADESC;
        this.DTSTART13 = CI13DTSTART;
        this.DTEND13 = CI13DTEND;
        this.AUTHORISED13 = CI13AUTHORISED;
        this.CALLNUMBER = CALLNUMBER;
        this.TITLE = TITLE;
    }

    public SQLAccessionNo(String CT03DOCNO, String CT03MATNO, String GL10DESC, String GL36DESC) {
        this.DOCNO = CT03DOCNO;
        this.MATNO = CT03MATNO;
        this.DESC10 = GL10DESC;
        this.DESC36 = GL36DESC;
    }

    public SQLAccessionNo(String CI07ACCESSION, String CI07SICAT, String DESSICAT, String CI07PICAT, String DESPICAT, String CI07DTSTART, String CI07DTEND, String CI07AUTHORISED, String NAMEAUTHORISED, String CI07CRDATE, String CI07CREBY, String NAMECREBY, String GL14STAT, String GL08DESC, String GL14CATE, String GL07DESC, String GL14GRID, String GL02NAME, String GL14EXPDATE, String GL14IMG, String GL14BRNC, String GL71DESC) {
        this.ACCESSION07 = CI07ACCESSION;
        this.SICAT = CI07SICAT;
        this.DESSICAT = DESSICAT;
        this.PICAT = CI07PICAT;
        this.DESPICAT = DESPICAT;
        this.DTSTART07 = CI07DTSTART;
        this.DTEND07 = CI07DTEND;
        this.AUTHORISED07 = CI07AUTHORISED;
        this.NAMEAUTHORISED = NAMEAUTHORISED;
        this.CRDATE = CI07CRDATE;
        this.CREBY = CI07CREBY;
        this.NAMECREBY = NAMECREBY;
        this.STAT = GL14STAT;
        this.DESC08 = GL08DESC;
        this.CATE = GL14CATE;
        this.DESC07 = GL07DESC;
        this.GRID = GL14GRID;
        this.NAME02 = GL02NAME;
        this.EXPDATE = GL14EXPDATE;
        this.IMG = GL14IMG;
        this.BRNC14 = GL14BRNC;
        this.DESC71 = GL71DESC;
    }

    public String getACCESSION07() {
        return Handler.ifIsNull(this.ACCESSION07);
    }

    public String getMATNO() {
        return Handler.ifIsNull(this.MATNO);
    }

    public String getCALLNUMBER() {
        return Handler.ifIsNull(this.CALLNUMBER);
    }

    public String getTITLE() {
        return Handler.ifIsNull(this.TITLE);
    }

    public String getSICAT() {
        return Handler.ifIsNull(this.SICAT);
    }

    public String getPICAT() {
        return Handler.ifIsNull(this.PICAT);
    }

    public String getDTSTART07() {
        return SQLAccessionNo.dateConverter(this.DTSTART07);
    }

    public String getDTEND07() {
        return SQLAccessionNo.dateConverter(this.DTEND07);
    }

    public String getAUTHORISED07() {
        return Handler.ifIsNull(this.AUTHORISED07);
    }

    public String getSICATDESC() {
        return Handler.ifIsNull(this.SICATDESC);
    }

    public String getPICATDESC() {
        return Handler.ifIsNull(this.PICATDESC);
    }

    public String getDOCNO() {
        return Handler.ifIsNull(this.DOCNO);
    }

    public String getICAT() {
        return Handler.ifIsNull(this.ICAT);
    }

    public String getDESC10() {
        return Handler.ifIsNull(this.DESC10);
    }

    public String getLOCA03() {
        return Handler.ifIsNull(this.LOCA03);
    }

    public String getDESC05() {
        return Handler.ifIsNull(this.DESC05);
    }

    public String getBRNC71() {
        return Handler.ifIsNull(this.BRNC71);
    }

    public String getDESC71() {
        return Handler.ifIsNull(this.DESC71);
    }

    public String getACCESSION13() {
        return Handler.ifIsNull(this.ACCESSION13);
    }

    public String getSLOCA() {
        return Handler.ifIsNull(this.SLOCA);
    }

    public String getSLOCADESC() {
        return Handler.ifIsNull(this.SLOCADESC);
    }

    public String getORIBRANCH() {
        return Handler.ifIsNull(this.ORIBRANCH);
    }

    public String getPLOCA() {
        return Handler.ifIsNull(this.PLOCA);
    }

    public String getReassignedBRANCH() {
        return Handler.ifIsNull(this.ReassignedBRANCH);
    }

    public String getPLOCADESC() {
        return Handler.ifIsNull(this.PLOCADESC);
    }

    public String getDTSTART13() {
        return Handler.ifIsNull(this.DTSTART13);
    }

    public String getDTEND13() {
        return Handler.ifIsNull(this.DTEND13);
    }

    public String getAUTHORISED13() {
        return Handler.ifIsNull(this.AUTHORISED13);
    }

    public String getDESC36() {
        return Handler.ifIsNull(this.DESC36);
    }

    public String getDESPICAT() {
        return Handler.ifIsNull(this.DESPICAT);
    }

    public String getCRDATE() {
        return SQLAccessionNo.dateConverter(this.CRDATE);
    }

    public String getCREBY() {
        return Handler.ifIsNull(this.CREBY);
    }

    public String getDESSICAT() {
        return Handler.ifIsNull(this.DESSICAT);
    }

    public String getNAMEAUTHORISED() {
        return Handler.ifIsNull(this.NAMEAUTHORISED);
    }

    public String getNAMECREBY() {
        return Handler.ifIsNull(this.NAMECREBY);
    }

    public String getSTAT() {
        return Handler.ifIsNull(this.STAT);
    }

    public String getDESC08() {
        return Handler.ifIsNull(this.DESC08);
    }

    public String getCATE() {
        return Handler.ifIsNull(this.CATE);
    }

    public String getDESC07() {
        return Handler.ifIsNull(this.DESC07);
    }

    public String getGRID() {
        return Handler.ifIsNull(this.GRID);
    }

    public String getNAME02() {
        return Handler.ifIsNull(this.NAME02);
    }

    public String getEXPDATE() {
        return Handler.ifIsNull(this.EXPDATE);
    }

    public String getIMG() {
        return Handler.ifIsNull(this.IMG);
    }

    public String getBRNC14() {
        return Handler.ifIsNull(this.BRNC14);
    }

    public static List<SQLAccessionNo> getCitran(String CT03DOCNO, String id) {
        ArrayList<SQLAccessionNo> list = new ArrayList<SQLAccessionNo>();
        Connection conn = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        conn = DBConnection.getConnection();
        String ACCESSIONNO = "";
        try {
            String queryACCESSION = "SELECT * FROM CITRAN WHERE CI07ACCESSION = '" + CT03DOCNO + "'";
            System.out.println(queryACCESSION);
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(queryACCESSION);
            System.out.println(String.valueOf(ACCESSIONNO) + "null");
            if (!rs.next()) {
                System.out.println("ACCESSION == NULL");
                List<SQLAccessionNo> list2 = SQLAccessionNo.getAccessionCITRAN(CT03DOCNO);
                return list2;
            }
            try {
                System.out.println("ACCESSION NOT NULL");
                String query = "SELECT itran.CI07ACCESSION, docm.CT03MATNO, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090')) AS CALLNUMBER, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245')) AS TITLE, itran.CI07SICAT, (SELECT GL10DESC FROM GLICAT WHERE CI07SICAT = GL10ICAT) AS SICATDESC, itran.CI07PICAT,(SELECT GL10DESC FROM GLICAT WHERE CI07PICAT = GL10ICAT) AS PICATDESC, itran.CI07DTSTART, itran.CI07DTEND, itran.CI07AUTHORISED, (SELECT CT05POINTER FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245')) AS  POINTERTITLE, (SELECT CT05POINTER FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090')) AS POINTERCALLNUMBER,(SELECT GL14NAME FROM GLPATR WHERE GL14PATR = '" + id + "') AS loginmane " + "FROM CITRAN itran, GLICAT icat, CTDOCM docm " + "WHERE itran.CI07SICAT = icat.GL10ICAT " + "AND itran.CI07ACCESSION = docm.CT03DOCNO " + "AND CI07ACCESSION = '" + CT03DOCNO + "'";
                System.out.println(query);
                try {
                    stmt1 = conn.createStatement();
                    rs = stmt1.executeQuery(query);
                    while (rs.next()) {
                        SQLAccessionNo citran_getDetail = new SQLAccessionNo(rs.getString("CI07ACCESSION"), rs.getString("CT03MATNO"), ISBD.convert(Integer.parseInt(Handler.ifIntIsNull(rs.getString("POINTERCALLNUMBER"))), Handler.ifIsNull(rs.getString("CALLNUMBER"))), ISBD.convert(Integer.parseInt(Handler.ifIntIsNull(rs.getString("POINTERTITLE"))), Handler.ifIsNull(rs.getString("TITLE"))), rs.getString("CI07SICAT"), rs.getString("SICATDESC"), rs.getString("CI07PICAT"), rs.getString("PICATDESC"), rs.getString("CI07DTSTART"), rs.getString("CI07DTEND"), rs.getString("CI07AUTHORISED"), rs.getString("loginmane"));
                        list.add(citran_getDetail);
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
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
        return list;
    }

    public static List<SQLAccessionNo> getAccessionCITRAN(String CT03DOCNO) {
        ArrayList<SQLAccessionNo> list = new ArrayList<SQLAccessionNo>();
        String query = "SELECT docm.CT03DOCNO, docm.CT03MATNO, docm.CT03ICAT, icat.GL10DESC, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG= '090')) AS CALLNUMBER, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG= '245')) AS TITLE, (SELECT CT05POINTER FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090')) AS POINTERCALLNUMBER, (SELECT CT05POINTER FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245')) AS  POINTERTITLE FROM CTDOCM docm, GLICAT icat WHERE docm.CT03ICAT = icat.GL10ICAT AND CT03STAT = 'A' AND CT03DOCNO = '" + CT03DOCNO + "'";
        System.out.println(query);
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            List<ISBD> title = ISBD.getPunctuation("245");
            while (rs.next()) {
                SQLAccessionNo p_getDetail = new SQLAccessionNo(rs.getString("CT03DOCNO"), rs.getString("CT03MATNO"), rs.getString("CT03ICAT"), rs.getString("GL10DESC"), Handler.rawToDisplayFormat(rs.getString("CALLNUMBER")), Handler.rawToDisplayFormat(rs.getString("TITLE")));
                list.add(p_getDetail);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SQLAccessionNo> getCITLOCA(String CT03DOCNO) {
        ArrayList<SQLAccessionNo> list = new ArrayList<SQLAccessionNo>();
        Connection conn = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        conn = DBConnection.getConnection();
        String CI13ACCESSION = "";
        try {
            String queryACCESSION = "SELECT * FROM CITLOCA WHERE CI13ACCESSION = '" + CT03DOCNO + "'";
            System.out.println(queryACCESSION);
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(queryACCESSION);
            if (!rs.next()) {
                System.out.println("ACCESSION == NULL");
                return SQLAccessionNo.getAccession2BranchCITRAN(CT03DOCNO);
            }
            System.out.println("ACCESSION NOT NULL");
            String query = "SELECT tloca.CI13ACCESSION, docm.CT03MATNO, tloca.CI13SLOCA, (SELECT GL05DESC FROM GLLOCA WHERE GL05LOCA = CI13SLOCA) AS SLOCADESC, (SELECT GL71DESC FROM GLLOCA, GLBRNC WHERE  GL05LOCA = CI13SLOCA AND GL71BRNC=GL05LOCA) AS ORIBRANCH, tloca.CI13PLOCA, (SELECT GL71DESC FROM GLLOCA, GLBRNC WHERE  GL05LOCA = CI13PLOCA AND GL71BRNC=GL05LOCA) AS ReassignedBRANCH, (SELECT GL05DESC FROM GLLOCA WHERE GL05LOCA = CI13PLOCA) AS PLOCADESC, tloca.CI13DTSTART, tloca.CI13DTEND, tloca.CI13AUTHORISED, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG= '090')) AS CALLNUMBER, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245')) AS TITLE, (SELECT CT05POINTER FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090')) AS POINTERCALLNUMBER, (SELECT CT05POINTER FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245')) AS  POINTERTITLE FROM CITLOCA tloca, CTDOCM docm WHERE tloca.CI13ACCESSION = docm.CT03DOCNO AND CI13ACCESSION = '" + CT03DOCNO + "'";
            System.out.println(query);
            try {
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    SQLAccessionNo citran_getDetail = new SQLAccessionNo(rs.getString("CI13ACCESSION"), rs.getString("CT03MATNO"), rs.getString("CI13SLOCA"), rs.getString("SLOCADESC"), rs.getString("ORIBRANCH"), rs.getString("CI13PLOCA"), rs.getString("ReassignedBRANCH"), rs.getString("PLOCADESC"), rs.getString("CI13DTSTART"), rs.getString("CI13DTEND"), rs.getString("CI13AUTHORISED"), ISBD.convert(Integer.parseInt(Handler.ifIntIsNull(rs.getString("POINTERCALLNUMBER"))), Handler.ifIsNull(rs.getString("CALLNUMBER"))), ISBD.convert(Integer.parseInt(Handler.ifIntIsNull(rs.getString("POINTERTITLE"))), Handler.ifIsNull(rs.getString("TITLE"))));
                    list.add(citran_getDetail);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SQLAccessionNo> getAccession2BranchCITRAN(String CT03DOCNO) {
        ArrayList<SQLAccessionNo> list = new ArrayList<SQLAccessionNo>();
        String query = "SELECT docm.CT03DOCNO, docm.CT03MATNO, docm.CT03LOCA, loca.GL05DESC, brcn.GL71BRNC, brcn.GL71DESC, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG= '090')) AS CALLNUMBER , (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG= '245')) AS TITLE, (SELECT CT05POINTER FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090')) AS POINTERCALLNUMBER, (SELECT CT05POINTER FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245')) AS  POINTERTITLE FROM CTDOCM docm, GLLOCA loca, GLBRNC brcn WHERE docm.CT03LOCA = loca.GL05LOCA AND loca.GL05BRNC=brcn.GL71BRNC AND CT03STAT = 'A' AND docm.CT03DOCNO = '" + CT03DOCNO + "'";
        System.out.println(query);
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                SQLAccessionNo p_getDetail = new SQLAccessionNo(rs.getString("CT03DOCNO"), rs.getString("CT03MATNO"), rs.getString("CT03LOCA"), rs.getString("GL05DESC"), rs.getString("GL71BRNC"), rs.getString("GL71DESC"), ISBD.convert(Integer.parseInt(Handler.ifIntIsNull(rs.getString("POINTERCALLNUMBER"))), Handler.ifIsNull(rs.getString("CALLNUMBER"))), ISBD.convert(Integer.parseInt(Handler.ifIntIsNull(rs.getString("POINTERTITLE"))), Handler.ifIsNull(rs.getString("TITLE"))));
                list.add(p_getDetail);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SQLAccessionNo> getAllAcc() {
        ArrayList<SQLAccessionNo> acclist = new ArrayList<SQLAccessionNo>();
        String query = "SELECT CT03DOCNO, CT03MATNO, GL10DESC, GL36DESC FROM CTDOCM, GLICAT, GLDOCS WHERE GL10ICAT=CT03ICAT AND GL36STAT=CT03STAT AND CT03STAT='A'";
        System.out.println(query);
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                SQLAccessionNo p_getacclist = new SQLAccessionNo(rs.getString("CT03DOCNO"), rs.getString("CT03MATNO"), rs.getString("GL10DESC"), rs.getString("GL36DESC"));
                acclist.add(p_getacclist);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return acclist;
    }

    public static List<SQLAccessionNo> getAllAccBYButton(String action) {
        ArrayList<SQLAccessionNo> acclistBtn = new ArrayList<SQLAccessionNo>();
        if (action.equals("branch")) {
            return SQLAccessionNo.getAllCITLOCA();
        }
        if (action.equals("category")) {
            return SQLAccessionNo.getAllCITRAN();
        }
        return acclistBtn;
    }

    public static List<SQLAccessionNo> getAllCITRAN() {
        ArrayList<SQLAccessionNo> acclistCITRAN = new ArrayList<SQLAccessionNo>();
        String query = "SELECT CI07ACCESSION, CI07SICAT, (SELECT GL10DESC FROM GLICAT WHERE GL10ICAT=CI07SICAT) AS DESSICAT, CI07PICAT, (SELECT GL10DESC FROM GLICAT WHERE GL10ICAT=CI07PICAT) AS DESPICAT, CI07DTSTART, CI07DTEND, CI07AUTHORISED, (SELECT GL14NAME FROM GLPATR WHERE CI07AUTHORISED = GL14PATR ) AS NAMEAUTHORISED, CI07CRDATE, CI07CREBY, (SELECT GL14NAME FROM GLPATR WHERE UPPER(GL14PATR) = UPPER(LTRIM(RTRIM(CI07CREBY)))) AS NAMECREBY, p.GL14STAT, s.GL08DESC, p.GL14CATE, c.GL07DESC, p.GL14GRID, d.GL02NAME, p.GL14EXPDATE, p.GL14IMG, p.GL14BRNC, b.GL71DESC FROM CITRAN LEFT JOIN GLPATR p ON CI07AUTHORISED = GL14PATR LEFT JOIN GLSTAT s ON p.GL14STAT=s.GL08STAT LEFT JOIN GLCATE c ON p.GL14CATE=c.GL07CATE LEFT JOIN GLGRMA d ON p.GL14GRID=d.GL02GRP LEFT JOIN GLBRNC b ON p.GL14BRNC=b.GL71BRNC";
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                SQLAccessionNo p_getCITRANlist = new SQLAccessionNo(rs.getString("CI07ACCESSION"), rs.getString("CI07SICAT"), rs.getString("DESSICAT"), rs.getString("CI07PICAT"), rs.getString("DESPICAT"), rs.getString("CI07DTSTART"), rs.getString("CI07DTEND"), rs.getString("CI07AUTHORISED"), Handler.ifIsNull(rs.getString("NAMEAUTHORISED")), rs.getString("CI07CRDATE"), rs.getString("CI07CREBY"), Handler.ifIsNull(rs.getString("NAMECREBY")), Handler.ifIsNull(rs.getString("GL14STAT")), Handler.ifIsNull(rs.getString("GL08DESC")), Handler.ifIsNull(rs.getString("GL14CATE")), Handler.ifIsNull(rs.getString("GL07DESC")), Handler.ifIsNull(rs.getString("GL14GRID")), Handler.ifIsNull(rs.getString("GL02NAME")), Handler.ifIsNull(rs.getString("GL14EXPDATE")), Handler.ifIsNull(rs.getString("GL14IMG")), Handler.ifIsNull(rs.getString("GL14BRNC")), Handler.ifIsNull(rs.getString("GL71DESC")));
                acclistCITRAN.add(p_getCITRANlist);
                System.out.println(rs.getString("CI07DTSTART"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return acclistCITRAN;
    }

    public static List<SQLAccessionNo> getAllCITLOCA() {
        ArrayList<SQLAccessionNo> acclistCILOCA = new ArrayList<SQLAccessionNo>();
        String query = "SELECT CI13ACCESSION, CI13SLOCA, (SELECT GL05DESC FROM GLLOCA WHERE GL05LOCA=CI13SLOCA) AS DESSLOCA, CI13PLOCA, (SELECT GL05DESC FROM GLLOCA WHERE GL05LOCA=CI13PLOCA) AS DESPLOCA, CI13DTSTART, CI13DTEND, CI13AUTHORISED, (SELECT GL14NAME FROM GLPATR WHERE  GL14PATR = CI13AUTHORISED ) AS NAMEAUTHORISED, CI13CRDATE, CI13CREBY, (SELECT GL14NAME FROM GLPATR WHERE UPPER(GL14PATR) = UPPER(LTRIM(RTRIM(CI13CREBY)))) AS NAMECREBY, p.GL14STAT, s.GL08DESC, p.GL14CATE, c.GL07DESC, p.GL14GRID, d.GL02NAME, p.GL14EXPDATE, p.GL14IMG, p.GL14BRNC, b.GL71DESC FROM CITLOCA, GLPATR p, GLSTAT s, GLCATE c, GLGRMA d, GLBRNC b WHERE CI13AUTHORISED = GL14PATR AND p.GL14STAT=s.GL08STAT AND p.GL14CATE=c.GL07CATE AND p.GL14GRID=d.GL02GRP AND p.GL14BRNC=b.GL71BRNC";
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                SQLAccessionNo p_getCITRANlist = new SQLAccessionNo(rs.getString("CI13ACCESSION"), rs.getString("CI13SLOCA"), rs.getString("DESSLOCA"), rs.getString("CI13PLOCA"), rs.getString("DESPLOCA"), rs.getString("CI13DTSTART"), rs.getString("CI13DTEND"), rs.getString("CI13AUTHORISED"), rs.getString("NAMEAUTHORISED"), rs.getString("CI13CRDATE"), rs.getString("CI13CREBY"), rs.getString("NAMECREBY"), rs.getString("GL14STAT"), rs.getString("GL08DESC"), rs.getString("GL14CATE"), rs.getString("GL07DESC"), rs.getString("GL14GRID"), rs.getString("GL02NAME"), rs.getString("GL14EXPDATE"), rs.getString("GL14IMG"), rs.getString("GL14BRNC"), rs.getString("GL71DESC"));
                System.out.println("nameeee :" + rs.getString("NAMEAUTHORISED"));
                acclistCILOCA.add(p_getCITRANlist);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return acclistCILOCA;
    }

    public static String dateConverter(String oldDate) {
        StringBuilder result = new StringBuilder();
        if (oldDate == null) {
            result.append(" ");
        } else {
            result.append(DateTime.DBToDisplayFormat(oldDate));
        }
        return result.toString();
    }
}
