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
package com.kmlink.ilmu.circulation.Charging;

import com.kmlink.ilmu.circulation.Charging.GeneralUtility;
import com.kmlink.ilmu.circulation.Charging.Renewal;
import com.kmlink.ilmu.circulation.Global.ErrorMessage_Handler;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AccessionBatchRenew"})
public class AccessionBatchRenew
extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private static Connection con = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String output = "";
        int num = 1;
        try {
            String GL14PATR = request.getParameter("patronid");
            List<Renewal> loanItems = null;
            loanItems = Renewal.getLoanItems(GL14PATR);
            for (Renewal i : loanItems) {
                boolean renewalfull = false;
                Renewal rnw = null;
                rnw = new Renewal();
                renewalfull = rnw.ExecuteRenewal_V1(i.getCI02DOCNO(), GL14PATR);
                String errmessage = rnw.getErrMessage();
                BigDecimal msFines = rnw.calculatefines2(GL14PATR);
                String output1 = "";
                String renewDate = "";
                String renewTime = "";
                if (errmessage != null && !errmessage.isEmpty()) {
                    List<ErrorMessage_Handler> ErrorMessage = null;
                    ErrorMessage = ErrorMessage_Handler.getGL79ERRMSG(errmessage);
                    for (ErrorMessage_Handler j : ErrorMessage) {
                        renewDate = "";
                        renewTime = "";
                        output1 = "<td><span class='glyphicon glyphicon-info-sign' data-toggle='tooltip' title='" + j.getGL79ERRMSG() + "'></span></td>";
                    }
                } else {
                    renewDate = GeneralUtility.StrToDate(rnw.getmsRawDateDue());
                    renewTime = rnw.getmsRawTimeDue();
                    output1 = String.valueOf(output1) + "<td></td>";
                }
                System.out.println("i.getCT05SRAW()" + i.getCT05SRAW());
                output = String.valueOf(output) + "<tr><td><input type = 'checkbox' class='checkAlls' id = 'checkAll' name='renew'></td><td>" + num++ + "</td>" + "<td><span class='glyphicon glyphicon-book' data-toggle='tooltip' title='" + i.getCT05SRAW() + "'></span>&nbsp;" + i.getCI02DOCNO() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02CDATE()) + "</td>" + "<td>" + i.getCI02CTIME() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02DDATE()) + "</td>" + "<td>" + i.getCI02DTIME() + "</td>" + "<td>" + i.getCI02FLAG() + "</td>" + "<td>" + renewDate + "</td>" + "<td>" + renewTime + "</td>" + "<td>" + rnw.getLateBy() + "</td>" + "<td>" + msFines + "</td>";
                output = String.valueOf(output) + output1;
                output = String.valueOf(output) + "</tr>";
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF8");
            response.getWriter().println(output);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
