/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.RequestDispatcher
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.ilmu.cataloging.Release_Circulation;

import com.ilmu.cataloging.template.Cataloging;
import com.ilmu.cataloging.template.SQLStatement;
import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/ReleaseForCI"})
public class ReleaseForCI
extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SQLStatement releaseCI = new SQLStatement();
    private static Connection c = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ResultSet rs;
            String raw;
            ResultSet rs1;
            ResultSet rs2;
            Statement st1;
            Statement st;
            String query1;
            String query;
            System.out.println("Connected!");
            String accNo = request.getParameter("accNo");
            String accMatNo = request.getParameter("accMatNo");
            String accMatNo1 = request.getParameter("accMatNo1");
            String docNo = request.getParameter("docNo");
            String matNos = request.getParameter("matNos");
            ArrayList<String> al = null;
            ArrayList pid_list = new ArrayList();
            ArrayList<String> al1 = null;
            ArrayList pid_list1 = new ArrayList();
            if (accNo != null) {
                query = "  Select distinct CT05SRAW, CT03MATNO, CT03DOCNO from CTTITL,CTPONT, CTDOCM where CT06POINTER=CT05POINTER and CT03MATNO=CT06MATNO and CT03DOCNO='" + accNo + "'";
                query1 = "Select distinct CT05SRAW from CTCALL,CTPONT where CT06POINTER=CT05POINTER and CT06MATNO='" + accNo + "'";
                System.out.println("query " + query);
                System.out.println("query " + query1);
                c = DBConnection.getConnection();
                st = c.createStatement();
                st1 = c.createStatement();
                rs2 = st.executeQuery(query);
                rs1 = st1.executeQuery(query1);
                while (rs2.next()) {
                    al = new ArrayList();
                    raw = rs2.getString("CT05SRAW");
                    raw = raw.replace("|a", " ");
                    raw = raw.replace("|b", " ");
                    raw = raw.replace("|c", " /");
                    al.add(raw);
                    al.add(rs2.getString("CT03MATNO"));
                    al.add(rs2.getString("CT03DOCNO"));
                    System.out.println("ala :: " + al);
                    pid_list.add(al);
                }
                while (rs1.next()) {
                    al1 = new ArrayList<String>();
                    raw = rs1.getString("CT05SRAW");
                    raw = raw.replace("|a", " ");
                    raw = raw.replace("|b", " ");
                    raw = raw.replace("|c", " /");
                    al1.add(raw);
                    System.out.println("al :: " + al);
                    pid_list1.add(al1);
                }
                request.setAttribute("piList", pid_list);
                request.setAttribute("piList1", pid_list1);
                RequestDispatcher view = request.getRequestDispatcher("DisplayTitle");
                view.include((ServletRequest)request, (ServletResponse)response);
                c.close();
                System.out.println("Disconnected!");
            }
            response.setContentType("text/plain");
            if (matNos != null) {
                query = " Select CT03MATNO from CTDOCM where CT03DOCNO='" + matNos + "'";
                System.out.println("query " + query);
                c = DBConnection.getConnection();
                st = c.createStatement();
                st1 = c.createStatement();
                rs = st.executeQuery(query);
                while (rs.next()) {
                    al = new ArrayList<String>();
                    matNos = rs.getString("CT03MATNO");
                    al.add(rs.getString("CT03MATNO"));
                    System.out.println("alsss :: " + al);
                    pid_list.add(al);
                    response.getWriter().write(matNos);
                }
            }
            if (accMatNo != null) {
                query = " Select CT05SRAW, CT03MATNO, CT03DOCNO from CTTITL,CTPONT, CTDOCM where CT06POINTER=CT05POINTER and CT03MATNO=CT06MATNO and CT03DOCNO='" + accMatNo + "'";
                query1 = "Select CT05SRAW, CT03MATNO from CTCALL,CTPONT,CTDOCM where CT06POINTER=CT05POINTER and  CT03MATNO=CT06MATNO and CT03DOCNO='" + accMatNo + "'";
                System.out.println("query " + query);
                c = DBConnection.getConnection();
                st = c.createStatement();
                st1 = c.createStatement();
                rs2 = st.executeQuery(query);
                rs1 = st1.executeQuery(query1);
                System.out.println(accMatNo);
                while (rs2.next()) {
                    al = new ArrayList();
                    raw = rs2.getString("CT05SRAW");
                    raw = raw.replace("|a", " ");
                    raw = raw.replace("|b", " ");
                    accMatNo = raw = raw.replace("|c", " /");
                    System.out.println("al :: " + accMatNo);
                    pid_list.add(al);
                    response.getWriter().write(accMatNo);
                }
                while (rs1.next()) {
                    al1 = new ArrayList();
                    String raw1 = rs1.getString("CT05SRAW");
                    System.out.println(raw1);
                    raw1 = raw1.replace("|a", " ");
                    raw1 = raw1.replace("|b", " ");
                    accMatNo1 = raw1 = raw1.replace("|c", " /");
                    pid_list1.add(al1);
                    response.getWriter().write("<br>" + accMatNo1);
                    System.out.println("al1 :: " + accMatNo1);
                }
            }
            if (docNo != null) {
                String query12 = "Select CT03STAT from CTDOCM WHERE CT03DOCNO='" + docNo + "'";
                c = DBConnection.getConnection();
                st = c.createStatement();
                rs = st.executeQuery(query12);
                System.out.println("query " + query12);
                if (rs.next()) {
                    String status = rs.getString("CT03STAT");
                    if (status.equals("A")) {
                        response.getWriter().write(status);
                    } else {
                        this.doPost(request, response);
                    }
                }
                System.out.println("Disconnected!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cataloging ct = new Cataloging();
        String docNo = request.getParameter("docNo");
        String matNo = request.getParameter("matNo");
        if (docNo != null) {
            ct.setDocNo(request.getParameter("docNo"));
            System.out.println(docNo);
            this.releaseCI.updateRelease(ct);
            request.setAttribute("errorID", (Object)"005");
            RequestDispatcher rd = request.getRequestDispatcher("Error_Page");
            rd.forward((ServletRequest)request, (ServletResponse)response);
        }
        try {
            System.out.println("Connected!");
            if (matNo != null) {
                String query = "Select TOP 1 GL28MSGDELAY from GLLIBR";
                c = DBConnection.getConnection();
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    String delayDate = rs.getString("GL28MSGDELAY");
                    request.setAttribute("delayDate", (Object)delayDate);
                    Calendar cal = Calendar.getInstance();
                    int collDays = Integer.parseInt(delayDate);
                    cal.set(5, cal.get(5) + collDays);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    String date = sdf.format(cal.getTime());
                    ct.setDocNo(request.getParameter("docNo"));
                    ct.setAccNo(request.getParameter("matNo"));
                    ct.setDelayDate(date);
                    this.releaseCI.updateCIResv(ct);
                    request.setAttribute("matNo", (Object)matNo);
                    request.setAttribute("docNo", (Object)docNo);
                    request.setAttribute("date", (Object)date);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/modules/Cataloging/ReleaseForCI/Main_Page.jsp");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DoSomethingAlpha() {
        System.out.println("w");
    }

    public static void main(String[] args) {
        new ReleaseForCI().DoSomethingAlpha();
    }
}
