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
package com.ilmu.foundation.LocationCode;

import com.ilmu.foundation.LocationCode.SQLStatement;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/addLoca"})
public class addLoca
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object con = null;
        Object message = null;
        String GL05LOCA = request.getParameter("GL05LOCA");
        System.out.println(String.valueOf(GL05LOCA) + " GL05LOCA");
        String GL05DESC = request.getParameter("GL05DESC");
        System.out.println(String.valueOf(GL05DESC) + " GL05DESC");
        String GL05SUBJECT = request.getParameter("GL05SUBJECT");
        System.out.println(String.valueOf(GL05SUBJECT) + " GL05SUBJECT");
        String GL05IPADD = request.getParameter("GL05IPADD");
        System.out.println(String.valueOf(GL05IPADD) + " GL05IPADD");
        String GL05MATCAP = request.getParameter("GL05MATCAP");
        System.out.println(String.valueOf(GL05MATCAP) + " GL05MATCAP");
        String GL05NOSERVER = request.getParameter("GL05NOSERVER");
        System.out.println(String.valueOf(GL05NOSERVER) + " GL05NOSERVER");
        String GL05NOTER = request.getParameter("GL05NOTER");
        System.out.println(String.valueOf(GL05NOTER) + " GL05NOTER");
        String GL05NOPC = request.getParameter("GL05NOPC");
        System.out.println(String.valueOf(GL05NOPC) + " GL05NOPC");
        String GL05LNPRT = request.getParameter("GL05LNPRT");
        System.out.println(String.valueOf(GL05LNPRT) + " GL05LNPRT");
        String GL05LJPRT = request.getParameter("GL05LJPRT");
        System.out.println(String.valueOf(GL05LJPRT) + " GL05LJPRT");
        String GL05DMPRT = request.getParameter("GL05DMPRT");
        System.out.println(String.valueOf(GL05DMPRT) + " GL05DMPRT");
        String GL05MODEM = request.getParameter("GL05MODEM");
        System.out.println(String.valueOf(GL05MODEM) + " GL05MODEM");
        String GL05MMEDIA = request.getParameter("GL05MMEDIA");
        System.out.println(String.valueOf(GL05MMEDIA) + " GL05MMEDIA");
        String GL05CDROM = request.getParameter("GL05CDROM");
        System.out.println(String.valueOf(GL05CDROM) + " GL05CDROM");
        String GL05SDI = request.getParameter("GL05SDI");
        System.out.println(String.valueOf(GL05SDI) + " GL05SDI");
        String GL05SDDS = request.getParameter("GL05SDDS");
        System.out.println(String.valueOf(GL05SDDS) + " GL05SDDS");
        String GL05IRL = request.getParameter("GL05IRL");
        System.out.println(String.valueOf(GL05IRL) + " GL05IRL");
        String GL05JARING = request.getParameter("GL05JARING");
        System.out.println(String.valueOf(GL05JARING) + " GL05JARING");
        String GL05NST = request.getParameter("GL05NST");
        System.out.println(String.valueOf(GL05NST) + " GL05NST");
        String GL05LAYOUT = request.getParameter("GL05LAYOUT");
        System.out.println(String.valueOf(GL05LAYOUT) + " GL05LAYOUT");
        String GL05DISPLAY = request.getParameter("GL05DISPLAY");
        System.out.println(String.valueOf(GL05DISPLAY) + " GL05DISPLAY");
        String GL05BRNC = request.getParameter("GL05BRNC");
        System.out.println(String.valueOf(GL05BRNC) + " GL05BRNC");
        GL05MMEDIA = GL05MMEDIA != null ? "Y" : "N";
        GL05SDI = GL05SDI != null ? "Y" : "N";
        GL05SDDS = GL05SDDS != null ? "Y" : "N";
        GL05JARING = GL05JARING != null ? "Y" : "N";
        GL05CDROM = GL05CDROM != null ? "Y" : "N";
        GL05IRL = GL05IRL != null ? "Y" : "N";
        GL05NST = GL05NST != null ? "Y" : "N";
        if (request.getParameter("GL05SUBJECT").isEmpty() || request.getParameter("GL05DISPLAY").isEmpty() || request.getParameter("GL05LAYOUT").isEmpty() || request.getParameter("GL05MATCAP").isEmpty() || request.getParameter("GL05IPADD").isEmpty()) {
            GL05SUBJECT = " ";
            GL05DISPLAY = " ";
            GL05LAYOUT = " ";
            GL05MATCAP = "0";
            GL05IPADD = " ";
        }
        SQLStatement.AddLocation(GL05LOCA, GL05DESC, GL05SUBJECT, GL05IPADD, GL05MATCAP, GL05NOSERVER, GL05NOTER, GL05NOPC, GL05LNPRT, GL05LJPRT, GL05DMPRT, GL05MODEM, GL05MMEDIA, GL05CDROM, GL05SDI, GL05SDDS, GL05IRL, GL05JARING, GL05NST, GL05LAYOUT, GL05DISPLAY, GL05BRNC);
        RequestDispatcher rd = request.getRequestDispatcher("/template?MODULE=Foundation/06_LocationCode&ACTION=LocaTable.jsp");
        rd.forward((ServletRequest)request, (ServletResponse)response);
    }
}
