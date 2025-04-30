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
package com.ilmu.foundation.PatronEligibility;

import com.ilmu.foundation.PatronEligibility.PatronEligibility;
import com.ilmu.foundation.PatronEligibility.SQLStatement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/CheckIndicator"})
public class CheckIndicator
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String[] patronCategories = request.getParameterValues("patCatList[]");
        String[] itemCategories = request.getParameterValues("iCatList[]");
        String[] smdCodes = request.getParameterValues("smdList[]");
        String[] branches = request.getParameterValues("branchList[]");
        int num = 0;
        String[] stringArray = patronCategories;
        int n = patronCategories.length;
        int n2 = 0;
        while (n2 < n) {
            String p = stringArray[n2];
            String[] stringArray2 = itemCategories;
            int n3 = itemCategories.length;
            int n4 = 0;
            while (n4 < n3) {
                String i = stringArray2[n4];
                String[] stringArray3 = smdCodes;
                int n5 = smdCodes.length;
                int n6 = 0;
                while (n6 < n5) {
                    String s = stringArray3[n6];
                    String[] stringArray4 = branches;
                    int n7 = branches.length;
                    int n8 = 0;
                    while (n8 < n7) {
                        String b = stringArray4[n8];
                        SQLStatement sql = new SQLStatement();
                        PatronEligibility pe = sql.getPatronEligibities(p.substring(0, p.indexOf(",")), i.substring(0, i.indexOf(",")), s.substring(0, s.indexOf(",")), b.substring(0, b.indexOf(",")));
                        String tag = pe.getIndicator() != "" ? "<tr class='red-inline'>" : "<tr>";
                        pw.print(tag);
                        pw.print("<td class='col10'>" + ++num + "</td>");
                        pw.print("<td class='col20' id = 'indPatronCat'>" + p + "</td>");
                        pw.print("<td class='col20' id = 'indICat'>" + i + "</td>");
                        pw.print("<td class='col20' id = 'indSmd'>" + s + "</td>");
                        pw.print("<td class='col20' id = 'indBranch'>" + b + "</td>");
                        pw.print("<td class='col10' id = 'indIcon'>" + pe.getIndicator() + "</td></tr>");
                        ++n8;
                    }
                    ++n6;
                }
                ++n4;
            }
            ++n2;
        }
        pw.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
