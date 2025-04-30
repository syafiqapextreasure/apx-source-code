/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.kmlink.ilmu.cataloging.Global;

import com.kmlink.ilmu.cataloging.Global.ISBD;
import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/ISBDView"})
public class ISBDConvert
extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder result;
        PrintWriter out;
        block16: {
            out = response.getWriter();
            String template = request.getParameter("template");
            String[] tag = request.getParameterValues("tag[]");
            String[] raw = request.getParameterValues("data[]");
            int total = Integer.parseInt(request.getParameter("total"));
            ArrayList tplList = new ArrayList();
            String query = " SELECT * FROM CTISBD WHERE CT17TPL='" + template + "' ORDER BY CT17SEQNO";
            System.out.println("System" + query);
            String isbd = null;
            result = new StringBuilder();
            try {
                try {
                    c = DBConnection.getConnection();
                    stmt = c.createStatement();
                    rs = stmt.executeQuery(query);
                    int number = 1;
                    int roman = 1;
                    while (rs.next()) {
                        int i = 0;
                        while (i < total) {
                            System.out.println("tag1" + rs.getString("CT17TAG") + Handler.removeSubfield(raw[i].trim()) + "sdsd");
                            if (rs.getString("CT17TAG").equals(tag[i].trim()) && Handler.removeSubfield(raw[i].trim()).length() != 0) {
                                List<ISBD> isbdpunc = ISBD.getPunctuation(rs.getString("CT17TAG"));
                                System.out.println("sdsdsd" + roman + number);
                                isbd = String.valueOf(Handler.convertTabNewLine(Handler.isNull(rs.getString("CT17MRGLEFT")))) + Handler.isNull(rs.getString("CT17PREPUNC")) + rs.getString("CT17TAG") + Handler.isNull(rs.getString("CT17POSTPUNC")) + Handler.convertTabNewLine(Handler.isNull(rs.getString("CT17MRGRIGHT")));
                                if (Handler.isNull(rs.getString("CT17PREPUNC")).equals("%n")) {
                                    isbd = isbd.replace("%n", String.valueOf(String.valueOf(number)) + ")");
                                    ++number;
                                }
                                if (Handler.isNull(rs.getString("CT17PREPUNC")).equals("%r")) {
                                    isbd = isbd.replace("%r", String.valueOf(String.valueOf(Handler.toRoman(roman))) + ")");
                                    ++roman;
                                }
                                System.out.println(isbd);
                                isbd = isbd.replace(tag[i].trim(), ISBD.getSubfield(raw[i], isbdpunc));
                                result.append(isbd);
                            }
                            ++i;
                        }
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        c.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block16;
                }
            }
            catch (Throwable throwable) {
                try {
                    c.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(result.toString());
        String html = "<div class='modal-header'><button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <h4 class='modal-title' id='myModalLabel'> ISBD View</h4></div><div class='modal-body'><pre wrap='hard' style='font-size:10pt;'>" + result.toString() + "</pre>";
        out.println(html);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
