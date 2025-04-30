/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(value={"/UpdatePatronDetails"})
@MultipartConfig(maxFileSize=16177215L)
public class UpdatePatronDetails
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String message = null;
        String GL14PATR = request.getParameter("GL14PATR2");
        System.out.println("GL14PATR" + GL14PATR);
        String GL14NAME = request.getParameter("GL14NAME").toUpperCase();
        String GL14PASSWORD = request.getParameter("GL14PASW");
        System.out.println("GL14PASSWORDGL14PASSWORDGL14PASSWORD" + GL14PASSWORD);
        String GL14PASSWORD2 = request.getParameter("GL14PASSWORD");
        String GL14GRID = request.getParameter("GL14GRID");
        String GL14OLDIC = request.getParameter("GL14OLDIC");
        String GL14NEWIC = request.getParameter("GL14NEWIC");
        String GL14CATE = request.getParameter("GL14CATE");
        String GL14DEPT = request.getParameter("GL14DEPT");
        String GL14COURSE = request.getParameter("GL14COURSE");
        String GL14STAT = request.getParameter("GL14STAT");
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
        String GL14DOB = request.getParameter("GL14DOB");
        String GL14SEX = request.getParameter("GL14SEX");
        if (GL14SEX.equals("null")) {
            GL14SEX = "";
        }
        String GL14RACE = request.getParameter("GL14RACE");
        String GL14DESC = request.getParameter("GL14DESC");
        String GL14MEMDATE = request.getParameter("GL14MEMDATE");
        String GL14EXPDATE = request.getParameter("GL14EXPDATE");
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
        String GL14NAMETITLE = request.getParameter("GL14NAMETITLE");
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
        String GL14DEFMODE = request.getParameter("GL14DEFMODE");
        String GL14IMG = request.getParameter("GL14IMG");
        String loginid = request.getParameter("loginid");
        String GL14RMVD = request.getParameter("activate");
        String activateStatus = request.getParameter("activateStatus");
        if (GL14MEMFEE.equals("")) {
            GL14MEMFEE = "0.00";
        }
        if (GL14DEPOSIT.equals("")) {
            GL14DEPOSIT = "0.00";
        }
        if (activateStatus.equals("s")) {
            System.out.println(String.valueOf(activateStatus) + " is");
            GL14RMVD = GL14RMVD != null ? "N" : "Y";
        }
        if (activateStatus.equals("h")) {
            GL14RMVD = GL14RMVD != null ? null : null;
        }
        System.out.println("NEW GL14RMVD UPDATE : " + GL14RMVD);
        System.out.println(String.valueOf(GL14RELID) + "=GL14RELID");
        System.out.println(String.valueOf(GL14SEX) + " GL14SEX");
        InputStream inputStream = null;
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            System.out.println(String.valueOf(filePart.getName()) + " getName");
            System.out.println(String.valueOf(filePart.getSize()) + "getSize");
            System.out.println(String.valueOf(filePart.getContentType()) + " getContentType");
            inputStream = filePart.getInputStream();
            System.out.println(filePart + " filePart");
            System.out.println(inputStream + " inputStream");
            System.out.println(String.valueOf(GL14IMG) + " sktn");
        } else {
            System.out.println(String.valueOf(GL14IMG) + " dptE");
        }
        try {
            try {
                PreparedStatement statement;
                con = DBConnection.getConnection();
                if (filePart.getSize() != 0L) {
                    String sqlUpdateImg = "UPDATE GLPATR SET GL14IMG=? WHERE GL14PATR=?";
                    System.out.println(sqlUpdateImg);
                    statement = con.prepareStatement(sqlUpdateImg);
                    System.out.println(statement);
                    if (inputStream != null) {
                        statement.setBinaryStream(1, inputStream, inputStream.available());
                        System.out.println("a " + inputStream.available() + " != null");
                    } else {
                        statement.setString(1, GL14IMG);
                        System.out.println("q " + GL14IMG + " w  null");
                    }
                    statement.setString(2, GL14PATR);
                    statement.executeUpdate();
                }
                System.out.println(String.valueOf(GL14PASSWORD) + " GL14PASSWORD");
                System.out.println(String.valueOf(Encrypter.encrypt(GL14PASSWORD)) + " ENC");
                if (GL14PASSWORD.length() > 0) {
                    System.out.println("updatela");
                    String sqlUpdatePass = "UPDATE GLPATR SET GL14PASW=? WHERE GL14PATR=?";
                    System.out.println(sqlUpdatePass);
                    statement = con.prepareStatement(sqlUpdatePass);
                    System.out.println(statement);
                    statement.setString(1, Encrypter.encrypt(GL14PASSWORD));
                    statement.setString(2, GL14PATR);
                    System.out.println("sss");
                    statement.executeUpdate();
                } else {
                    System.out.println("xyhupdate");
                }
                System.out.println("123");
                String sql = "UPDATE GLPATR SET GL14NAME=?,GL14NAMETITLE=?,GL14GRID=?,GL14CATE=?,GL14STAT=?,GL14MEMDATE=?,GL14EXPDATE=?,GL14OLDIC=?, GL14NEWIC=?,GL14DEPT=?, GL14COURSE=?,GL14RELID=?, GL14ADD1=?, GL14ADD2=?, GL14ADD3=?, GL14CODE=?, GL14TOWN=?, GL14HTEL=?, GL14OTEL=?, GL14FAX=?, GL14IPADD=?, GL14DOB=?, GL14SEX=?, GL14RACE=?, GL14DESC=?,GL14MEMFEE=?, GL14DEPOSIT=?, GL14RECEIPT=?, GL14REMARK=?, GL14COLOR=?, GL14RELIGION=?, GL14EMPLOYEE=?, GL14DATEJOIN=?, GL14STAFFLEVEL=?, GL14REGISTER=?, GL14SUPERVISOR=?, GL14BRNC=?, GL14OFFADD1=?, GL14OFFADD2=?, GL14OFFADD3=?, GL14MAILFLAG=?, GL14OFFCODE=?, GL14OFFTOWN=?, GL14ADD21=?, GL14ADD22=?, GL14ADD23=?,GL14CODE2=?, GL14TOWN2=?, GL14HTEL2=?, GL14HTELX=?, GL14MTEL=?, GL14RMVD=? WHERE GL14PATR=?";
                System.out.println(sql);
                statement = con.prepareStatement(sql);
                System.out.println(statement);
                statement.setString(1, GL14NAME);
                System.out.println(String.valueOf(GL14NAME) + "=GL14NAME");
                statement.setString(2, GL14NAMETITLE);
                System.out.println(String.valueOf(GL14NAMETITLE) + "=GL14NAMETITLE");
                statement.setString(3, GL14GRID);
                System.out.println(String.valueOf(GL14GRID) + "=GL14GRID");
                statement.setString(4, GL14CATE);
                System.out.println(String.valueOf(GL14CATE) + "=GL14CATE");
                statement.setString(5, GL14STAT);
                System.out.println(String.valueOf(GL14STAT) + "=GL14STAT");
                statement.setString(6, DateFormatter.displayToDBFormat(GL14MEMDATE));
                System.out.println(String.valueOf(DateFormatter.displayToDBFormat(GL14MEMDATE)) + "=GL14MEMDATE");
                statement.setString(7, DateFormatter.displayToDBFormat(GL14EXPDATE));
                System.out.println(String.valueOf(DateFormatter.displayToDBFormat(GL14EXPDATE)) + "=GL14EXPDATE");
                statement.setString(8, GL14OLDIC);
                System.out.println(String.valueOf(GL14OLDIC) + "=GL14OLDIC");
                statement.setString(9, GL14NEWIC);
                System.out.println(String.valueOf(GL14NEWIC) + "=GL14NEWIC");
                statement.setString(10, GL14DEPT);
                System.out.println(String.valueOf(GL14DEPT) + "=GL14DEPT");
                statement.setString(11, GL14COURSE);
                System.out.println(String.valueOf(GL14COURSE) + "=GL14COURSE");
                if (GL14RELID.equals(" ")) {
                    statement.setString(12, null);
                } else {
                    statement.setString(12, GL14RELID);
                }
                System.out.println(String.valueOf(GL14RELID) + "=GL14RELID");
                statement.setString(13, GL14ADD1);
                System.out.println(String.valueOf(GL14ADD1) + "=GL14ADD1");
                statement.setString(14, GL14ADD2);
                System.out.println(String.valueOf(GL14ADD2) + "=GL14ADD2");
                statement.setString(15, GL14ADD3);
                System.out.println(String.valueOf(GL14ADD3) + "=GL14ADD3");
                statement.setString(16, GL14CODE);
                System.out.println(String.valueOf(GL14CODE) + "=GL14CODE");
                statement.setString(17, GL14TOWN);
                System.out.println(String.valueOf(GL14TOWN) + "=GL14TOWN");
                statement.setString(18, GL14HTEL);
                System.out.println(String.valueOf(GL14HTEL) + "=GL14HTEL");
                statement.setString(19, GL14OTEL);
                System.out.println(String.valueOf(GL14OTEL) + "=GL14OTEL");
                statement.setString(20, GL14FAX);
                System.out.println(String.valueOf(GL14FAX) + "=GL14FAX");
                statement.setString(21, GL14IPADD);
                System.out.println(String.valueOf(GL14IPADD) + "=GL14IPADD");
                if (GL14DOB.isEmpty()) {
                    statement.setString(22, null);
                    System.out.println(String.valueOf(GL14DOB) + "=GL14DOBNULL");
                } else {
                    statement.setString(22, DateFormatter.displayToDBFormat(GL14DOB));
                    System.out.println(String.valueOf(DateFormatter.displayToDBFormat(GL14DOB)) + "=GL14DOB");
                }
                statement.setString(23, GL14SEX);
                System.out.println(String.valueOf(GL14SEX) + "=GL14SEX");
                statement.setString(24, GL14RACE);
                System.out.println(String.valueOf(GL14RACE) + "=GL14RACE");
                statement.setString(25, GL14DESC);
                System.out.println(String.valueOf(GL14DESC) + "=GL14DESC");
                statement.setString(26, GL14MEMFEE);
                System.out.println(String.valueOf(GL14MEMFEE) + "=GL14MEMFEE");
                statement.setString(27, GL14DEPOSIT);
                System.out.println(String.valueOf(GL14DEPOSIT) + "=GL14DEPOSIT");
                statement.setString(28, GL14RECEIPT);
                System.out.println(String.valueOf(GL14RECEIPT) + "=GL14RECEIPT");
                statement.setString(29, GL14REMARK);
                System.out.println(String.valueOf(GL14REMARK) + "=GL14REMARK");
                statement.setString(30, GL14COLOR);
                System.out.println(String.valueOf(GL14COLOR) + "=GL14COLOR");
                statement.setString(31, GL14RELIGION);
                System.out.println(String.valueOf(GL14RELIGION) + "=GL14RELIGION");
                statement.setString(32, GL14EMPLOYEE);
                System.out.println(String.valueOf(GL14EMPLOYEE) + "=GL14EMPLOYEE");
                if (GL14DATEJOIN.isEmpty()) {
                    statement.setString(33, null);
                    System.out.println(String.valueOf(GL14DATEJOIN) + "=GL14DATEJOINNULL");
                } else {
                    statement.setString(33, DateFormatter.displayToDBFormat(GL14DATEJOIN));
                    System.out.println(String.valueOf(DateFormatter.displayToDBFormat(GL14DATEJOIN)) + "=GL14DATEJOIN");
                }
                statement.setString(34, GL14STAFFLEVEL);
                System.out.println(String.valueOf(GL14STAFFLEVEL) + "=GL14STAFFLEVEL");
                statement.setString(35, GL14REGISTER);
                System.out.println(String.valueOf(GL14REGISTER) + "=GL14REGISTER");
                statement.setString(36, GL14SUPERVISOR);
                System.out.println(String.valueOf(GL14SUPERVISOR) + "=GL14SUPERVISOR");
                statement.setString(37, GL14BRNC);
                System.out.println(String.valueOf(GL14BRNC) + "=GL14BRNC");
                statement.setString(38, GL14OFFADD1);
                System.out.println(String.valueOf(GL14OFFADD1) + "=GL14OFFADD1");
                statement.setString(39, GL14OFFADD2);
                System.out.println(String.valueOf(GL14OFFADD2) + "=GL14OFFADD2");
                statement.setString(40, GL14OFFADD3);
                System.out.println(String.valueOf(GL14OFFADD3) + "=GL14OFFADD3");
                if (GL14MAILFLAG.equals("null")) {
                    statement.setString(41, null);
                    System.out.println(String.valueOf(GL14MAILFLAG) + "=GL14MAILFLAGNULL");
                } else {
                    statement.setString(41, GL14MAILFLAG);
                    System.out.println(String.valueOf(GL14MAILFLAG) + "=GL14MAILFLAG");
                }
                statement.setString(42, GL14OFFCODE);
                System.out.println(String.valueOf(GL14OFFCODE) + "=GL14OFFCODE");
                statement.setString(43, GL14OFFTOWN);
                System.out.println(String.valueOf(GL14OFFTOWN) + "=GL14OFFTOWN");
                statement.setString(44, GL14ADD21);
                System.out.println(String.valueOf(GL14ADD21) + "=GL14ADD21");
                statement.setString(45, GL14ADD22);
                System.out.println(String.valueOf(GL14ADD22) + "=GL14ADD22");
                statement.setString(46, GL14ADD23);
                System.out.println(String.valueOf(GL14ADD23) + "=GL14ADD23");
                statement.setString(47, GL14CODE2);
                System.out.println(String.valueOf(GL14CODE2) + "=GL14CODE2");
                statement.setString(48, GL14TOWN2);
                System.out.println(String.valueOf(GL14TOWN2) + "=GL14TOWN2");
                statement.setString(49, GL14HTEL2);
                System.out.println(String.valueOf(GL14HTEL2) + "=GL14HTEL2");
                statement.setString(50, GL14HTELX);
                System.out.println(String.valueOf(GL14HTELX) + "=GL14HTELX");
                statement.setString(51, GL14MTEL);
                System.out.println(String.valueOf(GL14MTEL) + "=GL14MTEL");
                statement.setString(52, GL14RMVD);
                System.out.println(String.valueOf(GL14RMVD) + "=GL14RMVD");
                statement.setString(53, GL14PATR);
                System.out.println(String.valueOf(GL14PATR) + "=GL14PATR");
                int succ = statement.executeUpdate();
                if (succ == 1) {
                    request.setAttribute("msg", (Object)"Success Update");
                    String gsModule = "GL";
                    RecordTransaction.InsertAudit(gsModule, "GLU0001", String.valueOf(GL14PATR) + " ," + Encrypter.encrypt(GL14PASSWORD), loginid);
                }
                System.out.println("berjaya edit");
                System.out.println("berjaya edit" + request.getParameter("countrowmiscellaneousedit") + "dsfsfs");
                if (!request.getParameter("countrowmiscellaneousedit").equals("")) {
                    int countrowmiscellaneousedit = Integer.parseInt(request.getParameter("countrowmiscellaneousedit"));
                    System.out.println(String.valueOf(countrowmiscellaneousedit) + " countrowmiscellaneousedit");
                    if (countrowmiscellaneousedit > 0) {
                        int x = 1;
                        while (x <= countrowmiscellaneousedit) {
                            HashMap<String, String> valueStr;
                            String miscellaneousValueEdit = request.getParameter("miscellaneousValueEdit" + x);
                            System.out.println(String.valueOf(miscellaneousValueEdit) + " miscellaneousValueEdit" + x);
                            System.out.println("String Length :" + miscellaneousValueEdit.length());
                            String code = request.getParameter("code" + x);
                            System.out.println(String.valueOf(code) + " code" + x);
                            int total = 0;
                            System.out.println(String.valueOf(code) + "SAVE code" + x);
                            String getexist = "SELECT COUNT(*) AS TOTAL  FROM GLPATREX WHERE GL65PATR = '" + GL14PATR + "' AND GL65ATTRI='" + code + "'";
                            System.out.println("getexist" + getexist);
                            PreparedStatement statementgetexist = con.prepareStatement(getexist);
                            ResultSet rsgetexist = statementgetexist.executeQuery();
                            while (rsgetexist.next()) {
                                total = rsgetexist.getInt("TOTAL");
                            }
                            System.out.println("total" + total);
                            if (total >= 1) {
                                sql = "SELECT COUNT(*) AS TOTAL  FROM GLPATREX WHERE GL65PATR = '" + GL14PATR + "' AND GL65ATTRI='" + code + "'";
                                System.out.println("SQL total patrex" + sql);
                                valueStr = new HashMap();
                                HashMap<String, String> condition = new HashMap<String, String>();
                                condition.put("GL65PATR", GL14PATR);
                                condition.put("GL65ATTRI", code);
                                valueStr.put("GL65VALUE", miscellaneousValueEdit);
                                String query = QueryBuilder.createUpdateQuery("GLPATREX", valueStr, null, null, condition);
                                DBQuery.runQuery(query);
                            } else {
                                valueStr = new HashMap<String, String>();
                                valueStr.put("GL65PATR", GL14PATR);
                                valueStr.put("GL65ATTRI", code);
                                valueStr.put("GL65VALUE", miscellaneousValueEdit);
                                String query = QueryBuilder.createInsertQuery("GLPATREX", valueStr, null, null);
                                DBQuery.runQuery(query);
                            }
                            ++x;
                        }
                    }
                }
            }
            catch (SQLException ex) {
                message = "ERROR: " + ex.getMessage();
                ex.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException ex2) {
                    ex2.printStackTrace();
                }
                response.sendRedirect(String.valueOf(this.getServletContext().getContextPath()) + "/template?MODULE=Foundation/10_PatronDetails&ACTION=PatronTable.jsp");
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            response.sendRedirect(String.valueOf(this.getServletContext().getContextPath()) + "/template?MODULE=Foundation/10_PatronDetails&ACTION=PatronTable.jsp");
        }
    }
}
