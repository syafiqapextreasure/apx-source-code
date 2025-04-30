/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.RequestDispatcher
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.annotation.MultipartConfig
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  javax.servlet.http.Part
 */
package com.ilmu.foundation.PatronDetails;

import com.ilmu.foundation.global.Encrypter;
import com.ilmu.global.DateFormatter;
import com.ilmu.global.RecordTransaction;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(value={"/AddPatronDetails"})
@MultipartConfig(maxFileSize=16177215L)
public class AddPatronDetails
extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "C:\\";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String message = null;
        String GL14PATR = request.getParameter("GL14PATR");
        System.out.println(String.valueOf(GL14PATR) + " GL14PATR ADD");
        String GL14NAME = request.getParameter("GL14NAME").toUpperCase();
        System.out.println(String.valueOf(GL14NAME) + " GL14NAME ADD");
        GL14NAME = GL14NAME.replaceAll("'", "''");
        System.out.println(String.valueOf(GL14NAME) + " NEW GL14NAME ADD");
        String GL14PASW = request.getParameter("GL14PASW");
        System.out.println(String.valueOf(GL14PASW) + " GL14PASW ADD");
        String GL14NAMETITLE = request.getParameter("GL14NAMETITLE");
        String GL14GRID = request.getParameter("GL14GRID");
        String GL14CATE = request.getParameter("GL14CATE");
        String GL14STAT = request.getParameter("GL14STAT");
        String GL14MEMDATE = request.getParameter("GL14MEMDATE");
        System.out.println(String.valueOf(GL14MEMDATE) + " GL14MEMDATE ADD");
        String GL14EXPDATE = request.getParameter("GL14EXPDATE");
        System.out.println(String.valueOf(GL14EXPDATE) + " GL14EXPDATE ADD");
        String GL14OLDIC = request.getParameter("GL14OLDIC");
        String GL14NEWIC = request.getParameter("GL14NEWIC");
        String GL14DEPT = request.getParameter("GL14DEPT");
        String GL14COURSE = request.getParameter("GL14COURSE");
        String GL14RELID = request.getParameter("GL14RELID");
        String GL14ADD1 = request.getParameter("GL14ADD1");
        String GL14ADD2 = request.getParameter("GL14ADD2");
        String GL14ADD3 = request.getParameter("GL14ADD3");
        String GL14CODE = request.getParameter("GL14CODE");
        String GL14TOWN = request.getParameter("GL14TOWN");
        String GL14HTEL = request.getParameter("GL14HTEL");
        String GL14OTEL = request.getParameter("GL14OTEL");
        String GL14FAX = request.getParameter("GL14FAX");
        String GL14IPADD = request.getParameter("GL14IPADD");
        String GL14DOB2 = request.getParameter("GL14DOB2");
        String GL14SEX = request.getParameter("GL14SEX");
        String GL14RACE = request.getParameter("GL14RACE");
        String GL14DESC = request.getParameter("GL14DESC");
        String GL14MEMFEE = request.getParameter("GL14MEMFEE");
        String GL14DEPOSIT = request.getParameter("GL14DEPOSIT");
        String GL14RECEIPT = request.getParameter("GL14RECEIPT");
        String GL14FINEOUT = request.getParameter("GL14FINEOUT");
        String GL14FINECOL = request.getParameter("GL14FINECOL");
        String GL14LOSTBOK = request.getParameter("GL14LOSTBOK");
        String GL14SUSPEND = request.getParameter("GL14SUSPEND");
        String GL14BORDATE = request.getParameter("GL14BORDATE");
        String GL14BORYEAR = request.getParameter("GL14BORYEAR");
        String GL14LTDATE = request.getParameter("GL14LTDATE");
        String GL14LTYEAR = request.getParameter("GL14LTYEAR");
        String GL14LASTBOR = request.getParameter("GL14LASTBOR");
        String GL14LASTRET = request.getParameter("GL14LASTRET");
        String GL14LOGIN = request.getParameter("GL14LOGIN");
        String GL14REMARK = request.getParameter("GL14REMARK");
        String GL14USID = request.getParameter("GL14USID");
        String GL14DUEF = request.getParameter("GL14DUEF");
        String GL14COLOR = request.getParameter("GL14COLOR");
        String GL14RELIGION = request.getParameter("GL14RELIGION");
        String GL14EMPLOYEE = request.getParameter("GL14EMPLOYEE");
        String GL14DATEJOIN = request.getParameter("GL14DATEJOIN");
        String GL14STAFFLEVEL = request.getParameter("GL14STAFFLEVEL");
        String GL14REGISTER = request.getParameter("GL14REGISTER");
        String GL14SUPERVISOR = request.getParameter("GL14SUPERVISOR");
        String GL14BRNC = request.getParameter("GL14BRNC");
        String GL14OFFADD1 = request.getParameter("GL14OFFADD1");
        String GL14OFFADD2 = request.getParameter("GL14OFFADD2");
        String GL14OFFADD3 = request.getParameter("GL14OFFADD3");
        String GL14MAILFLAG = request.getParameter("GL14MAILFLAG");
        String GL14OFFCODE = request.getParameter("GL14OFFCODE");
        String GL14OFFTOWN = request.getParameter("GL14OFFTOWN");
        String GL14BPRINT = request.getParameter("GL14BPRINT");
        String GL14ADD21 = request.getParameter("GL14ADD21");
        String GL14ADD22 = request.getParameter("GL14ADD22");
        String GL14ADD23 = request.getParameter("GL14ADD23");
        String GL14CODE2 = request.getParameter("GL14CODE2");
        String GL14TOWN2 = request.getParameter("GL14TOWN2");
        String GL14HTEL2 = request.getParameter("GL14HTEL2");
        String GL14HTELX = request.getParameter("GL14HTELX");
        String GL14SECURE = request.getParameter("GL14SECURE");
        String GL14PBAR = request.getParameter("GL14PBAR");
        String GL14SNOTICE = request.getParameter("GL14SNOTICE");
        String GL14MTEL = request.getParameter("GL14MTEL");
        String GL14RMVD = request.getParameter("activate");
        String activateStatus = request.getParameter("activateStatus");
        System.out.println(String.valueOf(GL14RMVD) + " : GL14RMVD ADD");
        System.out.println(String.valueOf(activateStatus) + " : activateStatus ADD");
        String GL14DEFMODE = request.getParameter("GL14DEFMODE");
        String newPart = request.getParameter("newPart");
        System.out.println(String.valueOf(newPart) + " newPart ADD");
        String GL14IMG = request.getParameter("photo");
        System.out.println(String.valueOf(GL14IMG) + " GL14IMG ADD");
        System.out.println(String.valueOf(GL14SEX) + " GL14SEX ADD");
        String loginid = request.getParameter("loginid");
        System.out.println(String.valueOf(loginid) + " loginid ADD");
        String daterecpatradd = request.getParameter("daterecpatradd");
        System.out.println(String.valueOf(daterecpatradd) + " daterecpatradd ADD");
        Part filePart = request.getPart("photo");
        System.out.println("filePart = " + filePart);
        InputStream inputStream = null;
        if (filePart != null) {
            System.out.println(String.valueOf(filePart.getName()) + " getName");
            System.out.println(String.valueOf(filePart.getSize()) + " getSize");
            System.out.println(String.valueOf(filePart.getContentType()) + " getContentType");
            inputStream = filePart.getInputStream();
            System.out.println(inputStream + " inputStream");
        }
        try {
            try {
                con = DBConnection.getConnection();
                if (GL14DOB2.isEmpty()) {
                    GL14DOB2 = "";
                    System.out.println(" GL14DOB2 IS EMPTY");
                } else {
                    GL14DOB2 = DateFormatter.displayToDBFormat(GL14DOB2);
                }
                if (GL14DATEJOIN.isEmpty()) {
                    GL14DATEJOIN = "";
                    System.out.println(" GL14DATEJOIN IS EMPTY");
                } else {
                    GL14DATEJOIN = DateFormatter.displayToDBFormat(GL14DATEJOIN);
                }
                GL14MEMDATE = DateFormatter.displayToDBFormat(GL14MEMDATE);
                GL14EXPDATE = DateFormatter.displayToDBFormat(GL14EXPDATE);
                if (GL14PASW.isEmpty()) {
                    GL14PASW = " ";
                    System.out.println(" GL14PASW IS EMPTY");
                } else {
                    GL14PASW = Encrypter.encrypt(GL14PASW);
                }
                if (activateStatus.equals("s")) {
                    System.out.println(String.valueOf(activateStatus) + " is");
                    GL14RMVD = GL14RMVD != null ? "'N'" : "'Y'";
                }
                if (activateStatus.equals("h")) {
                    GL14RMVD = GL14RMVD != null ? null : null;
                }
                if (GL14MEMFEE.equals("")) {
                    GL14MEMFEE = "0.00";
                }
                if (GL14DEPOSIT.equals("")) {
                    GL14DEPOSIT = "0.00";
                }
                System.out.println("NEW GL14RMVD : " + GL14RMVD);
                String sql = "INSERT INTO GLPATR (GL14PATR,GL14NAME,GL14PASW,GL14NAMETITLE,GL14GRID,GL14CATE,GL14STAT,GL14MEMDATE,GL14EXPDATE,GL14IMG,GL14OLDIC, GL14NEWIC,GL14DEPT, GL14COURSE,GL14RELID, GL14ADD1, GL14ADD2, GL14ADD3, GL14CODE, GL14TOWN, GL14HTEL, GL14OTEL, GL14FAX, GL14IPADD, GL14DOB, GL14SEX, GL14RACE, GL14DESC,GL14MEMFEE, GL14DEPOSIT, GL14RECEIPT,GL14REMARK, GL14COLOR, GL14RELIGION, GL14EMPLOYEE, GL14DATEJOIN, GL14STAFFLEVEL, GL14REGISTER, GL14SUPERVISOR, GL14BRNC, GL14OFFADD1, GL14OFFADD2, GL14OFFADD3, GL14MAILFLAG, GL14OFFCODE, GL14OFFTOWN, GL14ADD21, GL14ADD22, GL14ADD23,GL14CODE2, GL14TOWN2, GL14HTEL2, GL14HTELX, GL14MTEL, GL14RMVD, GL14DATEREC, GL14RECBY) VALUES ('" + GL14PATR + "', '" + GL14NAME + "', '" + GL14PASW + "', " + "'" + GL14NAMETITLE + "', '" + GL14GRID + "', '" + GL14CATE + "', '" + GL14STAT + "', " + "'" + GL14MEMDATE + "', '" + GL14EXPDATE + "', ?, '" + GL14OLDIC + "', " + "'" + GL14NEWIC + "', '" + GL14DEPT + "', '" + GL14COURSE + "', '" + GL14RELID + "', " + "'" + GL14ADD1 + "', '" + GL14ADD2 + "', '" + GL14ADD3 + "', '" + GL14CODE + "', '" + GL14TOWN + "', " + "'" + GL14HTEL + "', '" + GL14OTEL + "', '" + GL14FAX + "', '" + GL14IPADD + "', '" + GL14DOB2 + "', " + "'" + GL14SEX + "', '" + GL14RACE + "', '" + GL14DESC + "', '" + GL14MEMFEE + "', '" + GL14DEPOSIT + "', " + "'" + GL14RECEIPT + "', '" + GL14REMARK + "', '" + GL14COLOR + "', '" + GL14RELIGION + "', '" + GL14EMPLOYEE + "', " + "'" + GL14DATEJOIN + "', '" + GL14STAFFLEVEL + "', '" + GL14REGISTER + "', " + "'" + GL14SUPERVISOR + "', '" + GL14BRNC + "', '" + GL14OFFADD1 + "', '" + GL14OFFADD2 + "', '" + GL14OFFADD3 + "', " + "'" + GL14MAILFLAG + "', '" + GL14OFFCODE + "', '" + GL14OFFTOWN + "', '" + GL14ADD21 + "', " + "'" + GL14ADD22 + "', '" + GL14ADD23 + "', '" + GL14CODE2 + "', '" + GL14TOWN2 + "', '" + GL14HTEL2 + "'," + "'" + GL14HTELX + "', '" + GL14MTEL + "', " + GL14RMVD + ", '" + daterecpatradd + "', '" + loginid + "')";
                System.out.println("INSERT INTO GLPATR (GL14PATR,GL14NAME,GL14PASW,GL14NAMETITLE,GL14GRID,GL14CATE,GL14STAT,GL14MEMDATE,GL14EXPDATE,GL14IMG,GL14OLDIC, GL14NEWIC,GL14DEPT, GL14COURSE,GL14RELID, GL14ADD1, GL14ADD2, GL14ADD3, GL14CODE, GL14TOWN, GL14HTEL, GL14OTEL, GL14FAX, GL14IPADD, GL14DOB, GL14SEX, GL14RACE, GL14DESC,GL14MEMFEE, GL14DEPOSIT, GL14RECEIPT,GL14REMARK, GL14COLOR, GL14RELIGION, GL14EMPLOYEE, GL14DATEJOIN, GL14STAFFLEVEL, GL14REGISTER, GL14SUPERVISOR, GL14BRNC, GL14OFFADD1, GL14OFFADD2, GL14OFFADD3, GL14MAILFLAG, GL14OFFCODE, GL14OFFTOWN, GL14ADD21, GL14ADD22, GL14ADD23,GL14CODE2, GL14TOWN2, GL14HTEL2, GL14HTELX, GL14MTEL, GL14RMVD, GL14DATEREC, GL14RECBY) VALUES ('" + GL14PATR + "', '" + GL14NAME + "', '" + GL14PASW + "', " + "'" + GL14NAMETITLE + "', '" + GL14GRID + "', '" + GL14CATE + "', '" + GL14STAT + "', " + "'" + GL14MEMDATE + "', '" + GL14EXPDATE + "', ?, '" + GL14OLDIC + "', " + "'" + GL14NEWIC + "', '" + GL14DEPT + "', '" + GL14COURSE + "', '" + GL14RELID + "', " + "'" + GL14ADD1 + "', '" + GL14ADD2 + "', '" + GL14ADD3 + "', '" + GL14CODE + "', '" + GL14TOWN + "', " + "'" + GL14HTEL + "', '" + GL14OTEL + "', '" + GL14FAX + "', '" + GL14IPADD + "', '" + GL14DOB2 + "', " + "'" + GL14SEX + "', '" + GL14RACE + "', '" + GL14DESC + "', '" + GL14MEMFEE + "', '" + GL14DEPOSIT + "', " + "'" + GL14RECEIPT + "', '" + GL14REMARK + "', '" + GL14COLOR + "', '" + GL14RELIGION + "', '" + GL14EMPLOYEE + "', " + "'" + GL14DATEJOIN + "', '" + GL14STAFFLEVEL + "', '" + GL14REGISTER + "', " + "'" + GL14SUPERVISOR + "', '" + GL14BRNC + "', '" + GL14OFFADD1 + "', '" + GL14OFFADD2 + "', '" + GL14OFFADD3 + "', " + "'" + GL14MAILFLAG + "', '" + GL14OFFCODE + "', '" + GL14OFFTOWN + "', '" + GL14ADD21 + "', " + "'" + GL14ADD22 + "', '" + GL14ADD23 + "', '" + GL14CODE2 + "', '" + GL14TOWN2 + "', '" + GL14HTEL2 + "'," + "'" + GL14HTELX + "', '" + GL14MTEL + "', " + GL14RMVD + ", '" + daterecpatradd + "', '" + loginid + "')");
                PreparedStatement statement = con.prepareStatement(sql);
                if (inputStream != null) {
                    statement.setBinaryStream(1, inputStream, inputStream.available());
                }
                System.out.println("TAMAT");
                System.out.println("TAMA uuuuT");
                int succ = statement.executeUpdate();
                if (succ > 0) {
                    request.setAttribute("msg", (Object)"Success");
                    String gsModule = "GL";
                    RecordTransaction.InsertAudit(gsModule, "GLI0001", String.valueOf(GL14PATR) + " ," + Encrypter.encrypt(request.getParameter("GL14PASW")) + " ," + GL14IPADD, loginid);
                }
                int countrowmiscellaneousadd = Integer.parseInt(request.getParameter("countrowmiscellaneousadd"));
                System.out.println(String.valueOf(countrowmiscellaneousadd) + " countrowmiscellaneousadd");
                if (countrowmiscellaneousadd > 0) {
                    int x = 1;
                    while (x <= countrowmiscellaneousadd) {
                        String miscellaneousValue = request.getParameter("miscellaneousValue" + x);
                        System.out.println(String.valueOf(miscellaneousValue) + " miscellaneousValue" + x);
                        System.out.println("String Length :" + miscellaneousValue.length());
                        String code = request.getParameter("code" + x);
                        System.out.println(String.valueOf(code) + " code" + x);
                        if (miscellaneousValue.length() >= 1) {
                            System.out.println(String.valueOf(code) + "SAVE code" + x);
                            HashMap<String, String> valueStr = new HashMap<String, String>();
                            valueStr.put("GL65PATR", GL14PATR);
                            valueStr.put("GL65ATTRI", code);
                            valueStr.put("GL65VALUE", miscellaneousValue);
                            String query = QueryBuilder.createInsertQuery("GLPATREX", valueStr, null, null);
                            DBQuery.runQuery(query);
                        }
                        ++x;
                    }
                }
                System.out.println("--------------------------");
                con.close();
            }
            catch (SQLException ex) {
                message = "ERROR: " + ex.getMessage();
                ex.printStackTrace();
                if (con != null) {
                    try {
                        con.close();
                    }
                    catch (SQLException ex2) {
                        ex2.printStackTrace();
                    }
                }
                String url = "/template?MODULE=Foundation/10_PatronDetails&ACTION=PatronTable.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward((ServletRequest)request, (ServletResponse)response);
            }
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            String url = "/template?MODULE=Foundation/10_PatronDetails&ACTION=PatronTable.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward((ServletRequest)request, (ServletResponse)response);
        }
    }
}
