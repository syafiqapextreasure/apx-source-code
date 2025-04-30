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

import com.ilmu.foundation.VendorCode.SQLStatement;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AddVendor"})
public class AddVendor
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object con = null;
        Object message = null;
        String GL39CODE = request.getParameter("GL39CODE");
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
        System.out.println(String.valueOf(GL39CBDATE) + " GL39CBDATE");
        String GL39CEDATE = request.getParameter("GL39CEDATE");
        System.out.println(String.valueOf(GL39CEDATE) + " GL39CEDATE");
        String GL39REMARK = request.getParameter("GL39REMARK");
        String GL39ACCNO = request.getParameter("GL39ACCNO");
        String GL39PCODE = request.getParameter("GL39PCODE");
        String GL39IPADD = request.getParameter("GL39IPADD");
        String GL39BINDER = request.getParameter("GL39BINDER");
        System.out.println(String.valueOf(GL39BINDER) + " GL39BINDER");
        String GL39SUPPLIER = request.getParameter("GL39SUPPLIER");
        System.out.println(String.valueOf(GL39SUPPLIER) + "GL39SUPPLIER");
        String GL39PUB = request.getParameter("GL39PUB");
        System.out.println(String.valueOf(GL39PUB) + " GL39PUB");
        String GL39INDI = request.getParameter("GL39INDI");
        System.out.println(String.valueOf(GL39INDI) + " GL39INDI");
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
        if (GL39INDI != null) {
            GL39INDI = "Y";
            System.out.println("GL39INDI null");
        } else {
            System.out.println("GL39INDI null1");
            GL39INDI = "N";
        }
        SQLStatement.AddVendor(GL39CODE, GL39NAME, GL39ADD1, GL39ADD2, GL39ADD3, GL39TELNO, GL39FAX, GL39PERINC, GL39DESIG, GL39CONTNO, GL39CBDATE, GL39CEDATE, GL39REMARK, GL39ACCNO, GL39PCODE, GL39IPADD, GL39BINDER, GL39SUPPLIER, GL39PUB, GL39INDI, GL39USERNAME, GL39PASSWORD, GL39EMAIL, GL39BANK);
        request.setAttribute("msg", (Object)"Success");
        String url = "/template?MODULE=Foundation/07_VendorCode&ACTION=VendorTable.jsp";
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
}
