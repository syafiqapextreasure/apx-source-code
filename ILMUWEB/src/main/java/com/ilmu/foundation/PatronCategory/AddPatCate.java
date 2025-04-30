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
package com.ilmu.foundation.PatronCategory;

import com.ilmu.foundation.PatronCategory.SQLStatement;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AddPatCate"})
public class AddPatCate
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object con = null;
        Object message = null;
        String GL07CATE = request.getParameter("GL07CATE");
        String GL07DESC = request.getParameter("GL07DESC");
        String GL07ELIG = request.getParameter("GL07ELIG");
        String GL07MAXFINE = request.getParameter("GL07MAXFINE");
        String GL07FINELIMIT = request.getParameter("GL07FINELIMIT");
        String GL07ILLOUT = request.getParameter("GL07ILLOUT");
        String GL07MAXRESV = request.getParameter("GL07MAXRESV");
        String GL07ALLOWOVD = request.getParameter("GL07ALLOWOVD");
        System.out.println(String.valueOf(GL07ALLOWOVD) + " GL07ALLOWOVD");
        String GL07ALLOWRSV = request.getParameter("GL07ALLOWRSV");
        System.out.println(String.valueOf(GL07ALLOWRSV) + " GL07ALLOWRSV");
        String GL07MAXACCT = request.getParameter("GL07MAXACCT");
        System.out.println(String.valueOf(GL07MAXACCT) + " GL07MAXACCT");
        String GL07POPDB = request.getParameter("GL07POPDB");
        String GL07RATER = request.getParameter("GL07RATER");
        String GL07EMAIL = request.getParameter("GL07EMAIL");
        String GL07MODEM = request.getParameter("GL07MODEM");
        String GL07SCHAR = request.getParameter("GL07SCHAR");
        String GL07BRFORC = request.getParameter("GL07BRFORC");
        String GL07ARTXN = request.getParameter("GL07ARTXN");
        String GL07DCFORC = request.getParameter("GL07DCFORC");
        String GL07RENEWFEE = request.getParameter("GL07RENEWFEE");
        String GL07RENEWGRC = request.getParameter("GL07RENEWGRC");
        String subplan = request.getParameter("subplan");
        System.out.println(String.valueOf(subplan) + " subplan");
        if (GL07ALLOWOVD.equals("Y")) {
            GL07ALLOWOVD = "Y";
            System.out.println("Value Y");
        } else {
            GL07ALLOWOVD = "N";
            System.out.println("Value N");
        }
        if (GL07ALLOWRSV.equals("Y")) {
            GL07ALLOWRSV = "Y";
            System.out.println("Value Y");
        } else {
            GL07ALLOWRSV = "N";
            System.out.println("Value N");
        }
        GL07POPDB = GL07POPDB != null ? "Y" : "N";
        GL07RATER = GL07RATER != null ? "Y" : "N";
        GL07EMAIL = GL07EMAIL != null ? "Y" : "N";
        GL07MODEM = GL07MODEM != null ? "Y" : "N";
        GL07SCHAR = GL07SCHAR != null ? "Y" : "N";
        GL07BRFORC = GL07BRFORC != null ? "Y" : "N";
        GL07ARTXN = GL07ARTXN != null ? "Y" : "N";
        GL07DCFORC = GL07DCFORC != null ? "Y" : "N";
        if (subplan != null) {
            System.out.println("no e");
        } else {
            GL07MAXACCT = "-1";
            System.out.println("yes2");
        }
        System.out.println(String.valueOf(GL07MAXACCT) + " GL07MAXACCT");
        SQLStatement.AddPatCate(GL07CATE, GL07DESC, GL07ELIG, GL07MAXFINE, GL07FINELIMIT, GL07ILLOUT, GL07MAXRESV, GL07ALLOWOVD, GL07ALLOWRSV, GL07MAXACCT, GL07POPDB, GL07RATER, GL07EMAIL, GL07MODEM, GL07SCHAR, GL07BRFORC, GL07ARTXN, GL07DCFORC, GL07RENEWFEE, GL07RENEWGRC);
        RequestDispatcher rd = request.getRequestDispatcher("/template?MODULE=Foundation/14_PatronCategory&ACTION=PatCategory.jsp");
        rd.forward((ServletRequest)request, (ServletResponse)response);
    }
}
