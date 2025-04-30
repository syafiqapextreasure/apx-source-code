/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.ill.IncomingRequest;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.RecordTransaction;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.ill.IncomingRequest.DocValidate;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IncomingRequest {
    private String RequestNo;
    private String Material;
    private String Accession;
    private String Title;
    private String Name;
    private String RequestedDate;
    private String Status;
    private String Service;
    private String Handling;
    private String SentDate;
    private String Discount;
    private String Patron;
    private String Add1;
    private String Add2;
    private String Add3;
    private String PostCode;
    private String TelNo;
    private String Contact;
    private String DateCreated;
    private String CreatedBy;
    private String Amount;
    private String PatronCategory;
    private String PatronExpiryDate;
    private String Flag;
    private String PatronMembershipDate;
    private String RelatedID;
    private String checkGroup;
    private String PatronCharge;

    public String getRequestNo() {
        return Handler.ifIsNull(this.RequestNo);
    }

    public String getMaterial() {
        return Handler.ifIsNull(this.Material);
    }

    public String getAccession() {
        return Handler.ifIsNull(this.Accession);
    }

    public String getTitle() {
        return Handler.ifIsNull(this.Title);
    }

    public String getstatus() {
        return Handler.ifIsNull(this.Name);
    }

    public String getRequestedDate() {
        return Handler.ifIsNull(this.RequestedDate);
    }

    public String getStatus() {
        return Handler.ifIsNull(this.Status);
    }

    public String getService() {
        return Handler.ifIsNull(this.Service);
    }

    public String getHandling() {
        return Handler.ifIsNull(this.Handling);
    }

    public String getSentDate() {
        return Handler.ifIsNull(this.SentDate);
    }

    public String getDiscount() {
        return Handler.ifIsNull(this.Discount);
    }

    public String getPatron() {
        return Handler.ifIsNull(this.Patron);
    }

    public String getAdd1() {
        return Handler.ifIsNull(this.Add1);
    }

    public String getAdd2() {
        return Handler.ifIsNull(this.Add2);
    }

    public String getAdd3() {
        return Handler.ifIsNull(this.Add3);
    }

    public String getPostCode() {
        return Handler.ifIsNull(this.PostCode);
    }

    public String getTelNo() {
        return Handler.ifIsNull(this.TelNo);
    }

    public String getContact() {
        return Handler.ifIsNull(this.Contact);
    }

    public String getDateCreated() {
        return Handler.ifIsNull(this.DateCreated);
    }

    public String getCreatedBy() {
        return Handler.ifIsNull(this.CreatedBy);
    }

    public String getAmount() {
        return Handler.ifIsNull(this.Amount);
    }

    public String getPatronCategory() {
        return Handler.ifIsNull(this.PatronCategory);
    }

    public String getPatronExpiryDate() {
        return Handler.ifIsNull(this.PatronExpiryDate);
    }

    public String getFlag() {
        return Handler.ifIsNull(this.Flag);
    }

    public String getPatronMembershipDate() {
        return Handler.ifIsNull(this.PatronMembershipDate);
    }

    public String getRelatedID() {
        return Handler.ifIsNull(this.RelatedID);
    }

    public String getcheckGroup() {
        return Handler.ifIsNull(this.checkGroup);
    }

    public String getPatronCharge() {
        return Handler.ifIsNull(this.PatronCharge);
    }

    public IncomingRequest(String RequestNo, String Material, String Accession, String Title, String Name, String RequestedDate, String Status) {
        this.RequestNo = RequestNo;
        this.Material = Material;
        this.Accession = Accession;
        this.Title = Title;
        this.Name = Name;
        this.RequestedDate = RequestedDate;
        this.Status = Status;
    }

    public IncomingRequest(String RequestNo, String Status, String Material, String Title, String Accession, String RequestedDate, String SentDate, String Service, String Handling, String Discount, String Patron, String Name, String Add1, String Add2, String Add3, String TelNo, String PostCode, String Contact, String DateCreated, String CreatedBy, String Amount) {
        this.RequestNo = RequestNo;
        this.Status = Status;
        this.Material = Material;
        this.Title = Title;
        this.Accession = Accession;
        this.RequestedDate = RequestedDate;
        this.SentDate = SentDate;
        this.Service = Service;
        this.Handling = Handling;
        this.Discount = Discount;
        this.Patron = Patron;
        this.Name = Name;
        this.Add1 = Add1;
        this.Add2 = Add2;
        this.Add3 = Add3;
        this.TelNo = TelNo;
        this.PostCode = PostCode;
        this.Contact = Contact;
        this.DateCreated = DateCreated;
        this.CreatedBy = CreatedBy;
        this.Amount = Amount;
    }

    public IncomingRequest(String Accession, String Status) {
        this.Accession = Accession;
        this.Status = Status;
    }

    public IncomingRequest(String PatronCategory, String Name, String PatronExpiryDate, String Flag, String Add1, String Add2, String Add3, String Telno, String PostCode, String PatronMembershipDate, String Status, String RelatedID, String checkGroup, String PatronCharge) {
        this.PatronCategory = PatronCategory;
        this.Name = Name;
        this.PatronExpiryDate = PatronExpiryDate;
        this.Flag = Flag;
        this.Add1 = Add1;
        this.Add2 = Add2;
        this.Add3 = Add3;
        this.TelNo = Telno;
        this.PostCode = PostCode;
        this.PatronMembershipDate = PatronMembershipDate;
        this.Status = Status;
        this.RelatedID = RelatedID;
        this.checkGroup = checkGroup;
        this.PatronCharge = PatronCharge;
    }

    public static List<IncomingRequest> search(String input_criteria, String search_type) {
        ArrayList<IncomingRequest> list = new ArrayList<IncomingRequest>();
        String query = "SELECT CI01REQUEST, CI01MATNO, CI01ACCESSION, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI01MATNO AND CT06TAG = '245') AS TITLE, GL14NAME, CI01DTREQUEST, CI01STAT FROM CIINCO LEFT JOIN GLPATR ON GL14PATR = CI01REQUESTOR WHERE ";
        if (search_type.equals("ctrlNo")) {
            query = String.valueOf(query) + "UPPER(CI01MATNO) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("reqID")) {
            query = String.valueOf(query) + "UPPER(CI01REQUESTOR) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("reqNo")) {
            query = String.valueOf(query) + "UPPER(CI01REQUEST) = UPPER('" + input_criteria + "')";
        }
        System.out.println("query search : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                while (rs.next()) {
                    IncomingRequest loadtabledetail = new IncomingRequest(Handler.ifIsNull(rs.getString("CI01REQUEST")), Handler.ifIsNull(rs.getString("CI01MATNO")), Handler.ifIsNull(rs.getString("CI01ACCESSION")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI01DTREQUEST"))), Handler.ifIsNull(rs.getString("CI01STAT")));
                    list.add(loadtabledetail);
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
        return list;
    }

    public static List<IncomingRequest> editView(String id) {
        ArrayList<IncomingRequest> list = new ArrayList<IncomingRequest>();
        String query = "SELECT CI01REQUEST, CI01STAT, CI01MATNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI01MATNO AND CT06TAG = '245') AS TITLE, CI01ACCESSION, CI01DTREQUEST, CI01DTSENT, CI01SERVICE, CI01HANDLING, CI01DISCOUNT, CI01REQUESTOR, GL14NAME,GL14ADD1,GL14ADD2,GL14ADD3, GL14OTEL,GL14CODE, CI01CONTACT, CI01CRDATE, CI01CREBY, CI01AMOUNT FROM CIINCO LEFT JOIN GLPATR ON GL14PATR = CI01REQUESTOR WHERE CI01REQUEST = '" + id + "'";
        System.out.println("query editView : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                while (rs.next()) {
                    IncomingRequest loadtabledetail = new IncomingRequest(Handler.ifIsNull(rs.getString("CI01REQUEST")), Handler.ifIsNull(rs.getString("CI01STAT")), Handler.ifIsNull(rs.getString("CI01MATNO")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), Handler.ifIsNull(rs.getString("CI01ACCESSION")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI01DTREQUEST"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI01DTSENT"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("CI01SERVICE"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("CI01HANDLING"))), Handler.ifIsNull(rs.getString("CI01DISCOUNT")), Handler.ifIsNull(rs.getString("CI01REQUESTOR")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("GL14ADD1")), Handler.ifIsNull(rs.getString("GL14ADD2")), Handler.ifIsNull(rs.getString("GL14ADD3")), Handler.ifIsNull(rs.getString("GL14OTEL")), Handler.ifIsNull(rs.getString("GL14CODE")), Handler.ifIsNull(rs.getString("CI01CONTACT")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI01CRDATE"))), Handler.ifIsNull(rs.getString("CI01CREBY")), Handler.ifIsNull(Handler.decimalConversion(rs.getString("CI01AMOUNT"))));
                    list.add(loadtabledetail);
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
        return list;
    }

    public static int CheckMaterialExist(String value) {
        String sSQLStmt = "SELECT COUNT(*) AS COUNT FROM CTMATM WHERE CT02MATNO = '" + value + "'";
        System.out.println("SQL CheckMaterialExist" + sSQLStmt);
        int getCount = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    getCount = rs.getInt("COUNT");
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
        return getCount;
    }

    public static String ConvertRawData(String ctrlNo) throws SQLException {
        String sSQLStmt = "SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = '" + ctrlNo + "' AND CT06TAG = '245'";
        System.out.println("SQL ConvertRawData" + sSQLStmt);
        String title = null;
        Connection conn = null;
        List<ISBD> titleISBD = ISBD.getPunctuation("245");
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    title = Handler.getSubfield(Handler.ifIsNull(rs.getString("CT05SRAW")), titleISBD);
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
        return title;
    }

    public static List<IncomingRequest> LoadAccession(String vsControl) {
        ArrayList<IncomingRequest> list = new ArrayList<IncomingRequest>();
        String query = "SELECT CT03DOCNO, CT03STAT FROM CTDOCM WHERE CT03MATNO = '" + vsControl + "' " + "AND CT03STAT = 'A' ";
        System.out.println("query LoadAccession : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    IncomingRequest loadtabledetail = new IncomingRequest(Handler.ifIsNull(rs.getString("CT03DOCNO")), Handler.ifIsNull(rs.getString("CT03STAT")));
                    list.add(loadtabledetail);
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
        return list;
    }

    public static String LoadILLPatronCat() throws SQLException {
        String sSQLStmt = "SELECT GL99VALUE FROM GLFLAG2 WHERE UPPER(GL99FUNC) = UPPER('ILLPATRONCAT')";
        System.out.println("SQL LoadILLPatronCat" + sSQLStmt);
        String value = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    value = Handler.ifIsNull(rs.getString("GL99VALUE"));
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
        return value;
    }

    public static String CanPatronCharge(String id) throws SQLException {
        String sSQLStmt = "Select GL08ACTION1 From GLSTAT Where GL08STAT = '" + id + "'";
        System.out.println("SQL CanPatronCharge" + sSQLStmt);
        String value = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    value = Handler.ifIsNull(rs.getString("GL08ACTION1"));
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
        return value;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static List<IncomingRequest> CheckPatronExist(String id, String accno, String ctrlno) {
        ArrayList<IncomingRequest> list = new ArrayList<IncomingRequest>();
        String query = "SELECT * FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + id + "')";
        System.out.println("query CheckPatronExist : " + query);
        Connection conn = null;
        String checkGroup = null;
        try {
            try {
                conn = DBConnection.getConnection();
                Throwable throwable = null;
                Object var8_10 = null;
                try {
                    Statement stmt = conn.createStatement();
                    try {
                        try (ResultSet rs = stmt.executeQuery(query);){
                            if (!rs.next()) {
                                System.out.println("CheckPatronExist == NULL");
                            } else {
                                System.out.println("CheckPatronExist == NULL nottt");
                                String sPatronCategory = Handler.ifIsNull(rs.getString("GL14CATE"));
                                String name = Handler.ifIsNull(rs.getString("GL14NAME"));
                                String sPatronExpiryDate = Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL14EXPDATE")));
                                String flag = Handler.ifIsNull(rs.getString("GL14MAILFLAG"));
                                String add1 = Handler.ifIsNull(rs.getString("GL14OFFADD1"));
                                String add2 = Handler.ifIsNull(rs.getString("GL14OFFADD2"));
                                String add3 = Handler.ifIsNull(rs.getString("GL14OFFADD3"));
                                String telNo = Handler.ifIsNull(rs.getString("GL14OTEL"));
                                String postCode = Handler.ifIsNull(rs.getString("GL14OFFCODE"));
                                String sPatronMembershipDate = Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL14MEMDATE")));
                                String sPatronStatus = Handler.ifIsNull(rs.getString("GL14STAT"));
                                String sRelatedID = Handler.ifIsNull(rs.getString("GL14RELID"));
                                checkGroup = IncomingRequest.LoadILLPatronCat();
                                String CanPatronCharge = null;
                                if (sPatronStatus != null) {
                                    CanPatronCharge = IncomingRequest.CanPatronCharge(sPatronStatus);
                                }
                                DocValidate chr = new DocValidate();
                                chr.DocValidateMain(id, accno, ctrlno);
                                String output = chr.getErrMessage();
                                System.out.println("dfsfsfsdfsdfds" + output);
                                IncomingRequest loadtabledetail = new IncomingRequest(sPatronCategory, name, sPatronExpiryDate, flag, add1, add2, add3, telNo, postCode, sPatronMembershipDate, sPatronStatus, sRelatedID, checkGroup, CanPatronCharge);
                                list.add(loadtabledetail);
                            }
                        }
                        if (stmt == null) return list;
                    }
                    catch (Throwable throwable2) {
                        if (throwable == null) {
                            throwable = throwable2;
                        } else if (throwable != throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        if (stmt == null) throw throwable;
                        stmt.close();
                        throw throwable;
                    }
                    stmt.close();
                    return list;
                }
                catch (Throwable throwable3) {
                    if (throwable == null) {
                        throwable = throwable3;
                        throw throwable;
                    } else {
                        if (throwable == throwable3) throw throwable;
                        throwable.addSuppressed(throwable3);
                    }
                    throw throwable;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                if (conn == null) return list;
                try {
                    conn.close();
                    return list;
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            return list;
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean AddNewRecord(String sRequestNo, String sControlNo, String sAccession, String sRequestor, String contact, String requestedDate, String sentDate, String cService, String cHandling, String cDiscount, String cAmount, String gsUserId, String noConvert) throws UnknownHostException {
        boolean sucess = false;
        sucess = IncomingRequest.saveIncoReq(sRequestNo, sControlNo, sAccession, sRequestor, contact, requestedDate, sentDate, cService, cHandling, cDiscount, cAmount, gsUserId, noConvert);
        if (!sAccession.equals("")) {
            IncomingRequest.EditCTDOCM(sControlNo, sAccession, sRequestor, "G");
        }
        String ref = String.valueOf(noConvert) + ", " + sControlNo;
        String gsModule = "CI";
        RecordTransaction.InsertAudit(gsModule, "CII0007", ref, gsUserId);
        return sucess;
    }

    public static boolean saveIncoReq(String sRequestNo, String sControlNo, String sAccession, String sRequestor, String contact, String requestedDate, String sentDate, String cService, String cHandling, String cDiscount, String cAmount, String gsUserId, String noConvert) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        valueStr.put("CI01REQUEST", noConvert);
        valueStr.put("CI01MATNO", sControlNo);
        valueStr.put("CI01ACCESSION", sAccession);
        valueStr.put("CI01REQUESTOR", sRequestor);
        valueStr.put("CI01CONTACT", contact);
        valueStr.put("CI01DTREQUEST", requestedDate);
        valueStr.put("CI01DTSENT", sentDate);
        valueStr.put("CI01SERVICE", cService);
        valueStr.put("CI01HANDLING", cHandling);
        valueStr.put("CI01DISCOUNT", cDiscount);
        valueStr.put("CI01CRDATE", DateTime.getTodaySystemDate());
        valueStr.put("CI01CREBY", gsUserId);
        valueStr.put("CI01STAT", "N");
        valueStr.put("CI01AMOUNT", cAmount);
        String query = QueryBuilder.createInsertQuery("CIINCO", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean EditCTDOCM(String vsControlNo, String vsAccession, String vsPatronID, String status) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CT03MATNO", vsControlNo);
        condition.put("CT03DOCNO", vsAccession);
        valueStr.put("CT03STAT", status);
        valueStr.put("CT03PATR", vsPatronID);
        valueStr.put("CT03LASTACT", DateTime.getTodaySystemDate());
        String query = QueryBuilder.createUpdateQuery("CTDOCM", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean deleteIncomMstr(String logingid, String id, String accno) throws SQLException, UnknownHostException {
        boolean bSuccessful = false;
        bSuccessful = IncomingRequest.deleteIncom(id);
        String sControlNo = IncomingRequest.getMatno(id);
        IncomingRequest.EditCTDOCM(sControlNo, accno, logingid, "A");
        String ref = String.valueOf(id) + ", " + accno;
        String gsModule = "CI";
        RecordTransaction.InsertAudit(gsModule, "CID0004", ref, logingid);
        return bSuccessful;
    }

    public static boolean deleteIncom(String id) {
        String query = "DELETE FROM CIINCO WHERE CI01REQUEST = '" + id + "'";
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                Object stmt = null;
                Object rs = null;
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(query);
                delete.execute();
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
        return true;
    }

    public static String getMatno(String id) throws SQLException {
        String sSQLStmt = "SELECT CI01MATNO FROM CIINCO WHERE CI01REQUEST = '" + id + "'";
        System.out.println("SQL getmatno" + sSQLStmt);
        String value = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    value = Handler.ifIsNull(rs.getString("CI01MATNO"));
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
        return value;
    }

    public static String getAccno(String id) throws SQLException {
        String sSQLStmt = "SELECT CI01ACCESSION FROM CIINCO WHERE CI01REQUEST = '" + id + "'";
        System.out.println("SQL getmatno" + sSQLStmt);
        String value = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    value = Handler.ifIsNull(rs.getString("CI01ACCESSION"));
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
        return value;
    }

    public static boolean EditRecord(String sRequestNo, String sControlNo, String sAccession, String sRequestor, String contact, String requestedDate, String sentDate, String cService, String cHandling, String cDiscount, String cAmount, String gsUserId) throws UnknownHostException, SQLException {
        boolean sucess = false;
        if (!sAccession.equals("")) {
            sucess = IncomingRequest.updatingIncom(sRequestNo, sAccession, sRequestor, contact, requestedDate, sentDate, cService, cHandling, cDiscount, cAmount);
        } else {
            String oldAccno = IncomingRequest.getAccno(sRequestNo);
            if (sAccession != oldAccno) {
                IncomingRequest.EditCTDOCM(sControlNo, oldAccno, sRequestor, "A");
                IncomingRequest.EditCTDOCM(sControlNo, sAccession, sRequestor, "G");
                sucess = IncomingRequest.updatingIncom(sRequestNo, sAccession, sRequestor, contact, requestedDate, sentDate, cService, cHandling, cDiscount, cAmount);
            } else {
                IncomingRequest.EditCTDOCM(sControlNo, sAccession, sRequestor, "G");
                sucess = IncomingRequest.updatingIncom(sRequestNo, sAccession, sRequestor, contact, requestedDate, sentDate, cService, cHandling, cDiscount, cAmount);
            }
        }
        String ref = String.valueOf(sRequestNo) + ", " + sControlNo;
        String gsModule = "CI";
        RecordTransaction.InsertAudit(gsModule, "CIU0001", ref, gsUserId);
        return sucess;
    }

    public static boolean updatingIncom(String sRequestNo, String sAccession, String sRequestor, String contact, String requestedDate, String sentDate, String cService, String cHandling, String cDiscount, String cAmount) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CI01REQUEST", sRequestNo);
        valueStr.put("CI01ACCESSION", sAccession);
        valueStr.put("CI01REQUESTOR", sRequestor);
        valueStr.put("CI01CONTACT", contact);
        valueStr.put("CI01DTREQUEST", requestedDate);
        valueStr.put("CI01DTSENT", sentDate);
        valueStr.put("CI01SERVICE", cService);
        valueStr.put("CI01HANDLING", cHandling);
        valueStr.put("CI01DISCOUNT", cDiscount);
        valueStr.put("CI01AMOUNT", cAmount);
        String query = QueryBuilder.createUpdateQuery("CIINCO", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
