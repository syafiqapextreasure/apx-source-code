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

import com.ilmu.foundation.global.Foundation;
import com.ilmu.foundation.global.GlobalSQLStatement;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/UpdatePatCate"})
public class UpdatePatCate
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Foundation emp = new Foundation();
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
            System.out.println("YYYY");
        } else {
            GL07ALLOWOVD = "N";
            System.out.println("NNN");
        }
        GL07ALLOWRSV = GL07ALLOWRSV.equals("Y") ? "Y" : "N";
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
        emp.setGL07CATE(request.getParameter("GL07CATE"));
        emp.setGL07DESC(request.getParameter("GL07DESC"));
        emp.setGL07ELIG(request.getParameter("GL07ELIG"));
        emp.setGL07MAXFINE(request.getParameter("GL07MAXFINE"));
        emp.setGL07FINELIMIT(request.getParameter("GL07FINELIMIT"));
        emp.setGL07ILLOUT(request.getParameter("GL07ILLOUT"));
        emp.setGL07MAXRESV(request.getParameter("GL07MAXRESV"));
        emp.setGL07ALLOWOVD(GL07ALLOWOVD);
        emp.setGL07ALLOWRSV(GL07ALLOWRSV);
        emp.setGL07MAXACCT(GL07MAXACCT);
        emp.setGL07POPDB(GL07POPDB);
        emp.setGL07RATER(GL07RATER);
        emp.setGL07EMAIL(GL07EMAIL);
        emp.setGL07MODEM(GL07MODEM);
        emp.setGL07SCHAR(GL07SCHAR);
        emp.setGL07BRFORC(GL07BRFORC);
        emp.setGL07ARTXN(GL07ARTXN);
        emp.setGL07DCFORC(GL07DCFORC);
        emp.setGL07RENEWFEE(GL07RENEWFEE);
        emp.setGL07RENEWGRC(GL07RENEWGRC);
        GlobalSQLStatement eb = new GlobalSQLStatement();
        eb.updatePatCat(emp);
        request.setAttribute("GL07CATE", (Object)GL07CATE);
        request.setAttribute("GL07DESC", (Object)GL07DESC);
        request.setAttribute("GL07ELIG", (Object)GL07ELIG);
        request.setAttribute("GL07MAXFINE", (Object)GL07MAXFINE);
        request.setAttribute("GL07FINELIMIT", (Object)GL07FINELIMIT);
        request.setAttribute("GL07ILLOUT", (Object)GL07ILLOUT);
        request.setAttribute("GL07MAXRESV", (Object)GL07MAXRESV);
        request.setAttribute("GL07ALLOWOVD", (Object)GL07ALLOWOVD);
        request.setAttribute("GL07ALLOWRSV", (Object)GL07ALLOWRSV);
        request.setAttribute("GL07MAXACCT", (Object)GL07MAXACCT);
        request.setAttribute("GL07POPDB", (Object)GL07POPDB);
        request.setAttribute("GL07RATER", (Object)GL07RATER);
        request.setAttribute("GL07EMAIL", (Object)GL07EMAIL);
        request.setAttribute("GL07MODEM", (Object)GL07MODEM);
        request.setAttribute("GL07SCHAR", (Object)GL07SCHAR);
        request.setAttribute("GL07BRFORC", (Object)GL07BRFORC);
        request.setAttribute("GL07ARTXN", (Object)GL07ARTXN);
        request.setAttribute("GL07DCFORC", (Object)GL07DCFORC);
        request.setAttribute("GL07RENEWFEE", (Object)GL07RENEWFEE);
        request.setAttribute("GL07RENEWGRC", (Object)GL07RENEWGRC);
        RequestDispatcher view = this.getServletContext().getRequestDispatcher("/template?MODULE=Foundation/14_PatronCategory&ACTION=PatCategory.jsp");
        view.forward((ServletRequest)request, (ServletResponse)response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}
