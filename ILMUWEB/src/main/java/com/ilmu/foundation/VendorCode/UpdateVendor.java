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
package com.ilmu.foundation.VendorCode;

import com.ilmu.foundation.global.Foundation;
import com.ilmu.foundation.global.GlobalSQLStatement;
import com.ilmu.global.DateFormatter;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/UpdateVendor"})
public class UpdateVendor
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Foundation emp = new Foundation();
        System.out.println("testinggggggg");
        String GL39CODE = request.getParameter("Vendor");
        String GL39NAME = request.getParameter("GL39NAME");
        String GL39ADD1 = request.getParameter("GL39ADD1");
        String GL39ADD2 = request.getParameter("GL39ADD2");
        String GL39ADD3 = request.getParameter("GL39ADD3");
        String GL39TELNO = request.getParameter("GL39TELNO");
        String GL39FAX = request.getParameter("GL39FAX");
        String GL39PERINC = request.getParameter("GL39PERINC");
        String GL39DESIG = request.getParameter("GL39DESIG");
        String GL39CONTNO = request.getParameter("GL39CONTNO");
        String GL39CBDATE = request.getParameter("GL39CBDATE");
        System.out.println(String.valueOf(GL39CBDATE) + "GL39CBDATE");
        String GL39CEDATE = request.getParameter("GL39CEDATE");
        System.out.println(String.valueOf(GL39CEDATE) + "GL39CEDATE");
        String GL39REMARK = request.getParameter("GL39REMARK");
        String GL39ACCNO = request.getParameter("GL39ACCNO");
        String GL39PCODE = request.getParameter("GL39PCODE");
        String GL39IPADD = request.getParameter("GL39IPADD");
        String GL39BINDER = request.getParameter("GL39BINDER");
        String GL39SUPPLIER = request.getParameter("GL39SUPPLIER");
        String GL39PUB = request.getParameter("GL39PUB");
        String GL39INDI = request.getParameter("GL39INDI");
        String GL39USERNAME = request.getParameter("GL39USERNAME");
        String GL39PASSWORD = request.getParameter("GL39PASSWORD");
        String GL39EMAIL = request.getParameter("GL39EMAIL");
        String GL39BANK = request.getParameter("GL39BANK");
        if (GL39SUPPLIER != null) {
            GL39SUPPLIER = "Y";
            System.out.println("GL39SUPPLIER null");
        } else {
            System.out.println("GL39SUPPLIER null1");
            GL39SUPPLIER = "N";
        }
        if (GL39BINDER != null) {
            GL39BINDER = "Y";
            System.out.println("GL39BINDER null");
        } else {
            System.out.println("GL39BINDER null1");
            GL39BINDER = "N";
        }
        if (GL39PUB != null) {
            GL39PUB = "Y";
            System.out.println("GL39PUB null");
        } else {
            System.out.println("GL39PUB null1");
            GL39PUB = "N";
        }
        if (GL39CBDATE == "") {
            GL39CBDATE = "";
            System.out.println("GL39CBDATE null");
        } else {
            GL39CBDATE = DateFormatter.displayToDBFormat(GL39CBDATE);
            System.out.println("GL39CBDATE.isEmpty(");
        }
        if (GL39CEDATE == "") {
            GL39CEDATE = "";
            System.out.println("GL39CEDATE null");
        } else {
            GL39CEDATE = DateFormatter.displayToDBFormat(GL39CEDATE);
            System.out.println("GL39CEDATE.isEmpty(");
        }
        if (GL39INDI != null) {
            GL39INDI = "Y";
            System.out.println("GL39INDI null");
        } else {
            System.out.println("GL39INDI null1");
            GL39INDI = "N";
        }
        emp.setGL39CODE(request.getParameter("Vendor"));
        emp.setGL39NAME(request.getParameter("GL39NAME"));
        emp.setGL39ADD1(request.getParameter("GL39ADD1"));
        emp.setGL39ADD2(request.getParameter("GL39ADD2"));
        emp.setGL39ADD3(request.getParameter("GL39ADD3"));
        emp.setGL39TELNO(request.getParameter("GL39TELNO"));
        emp.setGL39FAX(request.getParameter("GL39FAX"));
        emp.setGL39PERINC(request.getParameter("GL39PERINC"));
        emp.setGL39DESIG(request.getParameter("GL39DESIG"));
        emp.setGL39CONTNO(request.getParameter("GL39CONTNO"));
        emp.setGL39CBDATE(GL39CBDATE);
        emp.setGL39CEDATE(GL39CEDATE);
        emp.setGL39REMARK(request.getParameter("GL39REMARK"));
        emp.setGL39ACCNO(request.getParameter("GL39ACCNO"));
        emp.setGL39PCODE(request.getParameter("GL39PCODE"));
        emp.setGL39IPADD(request.getParameter("GL39IPADD"));
        emp.setGL39BINDER(GL39BINDER);
        emp.setGL39SUPPLIER(GL39SUPPLIER);
        emp.setGL39PUB(GL39PUB);
        emp.setGL39INDI(request.getParameter("GL39INDI"));
        emp.setGL39USERNAME(request.getParameter("GL39USERNAME"));
        emp.setGL39PASSWORD(request.getParameter("GL39PASSWORD"));
        emp.setGL39EMAIL(request.getParameter("GL39EMAIL"));
        emp.setGL39BANK(request.getParameter("GL39BANK"));
        GlobalSQLStatement eb = new GlobalSQLStatement();
        eb.updateVendor(emp);
        request.setAttribute("GL39CODE", (Object)GL39CODE);
        request.setAttribute("GL39NAME", (Object)GL39NAME);
        request.setAttribute("GL39ADD1", (Object)GL39ADD1);
        request.setAttribute("GL39ADD2", (Object)GL39ADD2);
        request.setAttribute("GL39ADD3", (Object)GL39ADD3);
        request.setAttribute("GL39TELNO", (Object)GL39TELNO);
        request.setAttribute("GL39FAX", (Object)GL39FAX);
        request.setAttribute("GL39PERINC", (Object)GL39PERINC);
        request.setAttribute("GL39DESIG", (Object)GL39DESIG);
        request.setAttribute("GL39CONTNO", (Object)GL39CONTNO);
        request.setAttribute("GL39CBDATE", (Object)GL39CBDATE);
        request.setAttribute("GL39CEDATE", (Object)GL39CEDATE);
        request.setAttribute("GL39REMARK", (Object)GL39REMARK);
        request.setAttribute("GL39ACCNO", (Object)GL39ACCNO);
        request.setAttribute("GL39PCODE", (Object)GL39PCODE);
        request.setAttribute("GL39IPADD", (Object)GL39IPADD);
        request.setAttribute("GL39BINDER", (Object)GL39BINDER);
        request.setAttribute("GL39SUPPLIER", (Object)GL39SUPPLIER);
        request.setAttribute("GL39PUB", (Object)GL39PUB);
        request.setAttribute("GL39INDI", (Object)GL39INDI);
        request.setAttribute("GL39USERNAME", (Object)GL39USERNAME);
        request.setAttribute("GL39PASSWORD", (Object)GL39PASSWORD);
        request.setAttribute("GL39EMAIL", (Object)GL39EMAIL);
        request.setAttribute("GL39BANK", (Object)GL39BANK);
        request.setAttribute("msg", (Object)"Success Update");
        String url = "/template?MODULE=Foundation/07_VendorCode&ACTION=VendorTable.jsp";
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
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
