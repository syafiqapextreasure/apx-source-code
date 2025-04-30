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

@WebServlet(value={"/UpdateLocation"})
public class UpdateLocation
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Foundation emp = new Foundation();
        String GL05LOCA = request.getParameter("Location");
        String GL05DESC = request.getParameter("GL05DESC");
        String GL05SUBJECT = request.getParameter("GL05SUBJECT");
        String GL05IPADD = request.getParameter("GL05IPADD");
        String GL05MATCAP = request.getParameter("GL05MATCAP");
        String GL05NOSERVER = request.getParameter("GL05NOSERVER");
        String GL05NOTER = request.getParameter("GL05NOTER");
        String GL05NOPC = request.getParameter("GL05NOPC");
        String GL05LNPRT = request.getParameter("GL05LNPRT");
        String GL05LJPRT = request.getParameter("GL05LJPRT");
        String GL05DMPRT = request.getParameter("GL05DMPRT");
        String GL05MODEM = request.getParameter("GL05MODEM");
        String GL05MMEDIA = request.getParameter("GL05MMEDIA");
        System.out.println(String.valueOf(GL05MMEDIA) + "GL05MMEDIA");
        String GL05CDROM = request.getParameter("GL05CDROM");
        String GL05SDI = request.getParameter("GL05SDI");
        String GL05SDDS = request.getParameter("GL05SDDS");
        String GL05IRL = request.getParameter("GL05IRL");
        String GL05JARING = request.getParameter("GL05JARING");
        String GL05NST = request.getParameter("GL05NST");
        String GL05LAYOUT = request.getParameter("GL05LAYOUT");
        String GL05DISPLAY = request.getParameter("GL05DISPLAY");
        String GL05BRNC = request.getParameter("Branch");
        if (GL05MMEDIA != null) {
            GL05MMEDIA = "Y";
            System.out.println("YES");
        } else {
            GL05MMEDIA = "N";
            System.out.println("No");
        }
        GL05SDI = GL05SDI != null ? "Y" : "N";
        GL05SDDS = GL05SDDS != null ? "Y" : "N";
        GL05JARING = GL05JARING != null ? "Y" : "N";
        GL05CDROM = GL05CDROM != null ? "Y" : "N";
        GL05IRL = GL05IRL != null ? "Y" : "N";
        GL05NST = GL05NST != null ? "Y" : "N";
        emp.setGL05LOCA(request.getParameter("Location"));
        emp.setGL05DESC(request.getParameter("GL05DESC"));
        emp.setGL05SUBJECT(request.getParameter("GL05SUBJECT"));
        emp.setGL05IPADD(request.getParameter("GL05IPADD"));
        emp.setGL05MATCAP(request.getParameter("GL05MATCAP"));
        emp.setGL05NOSERVER(request.getParameter("GL05NOSERVER"));
        emp.setGL05NOTER(request.getParameter("GL05NOTER"));
        emp.setGL05NOPC(request.getParameter("GL05NOPC"));
        emp.setGL05LNPRT(request.getParameter("GL05LNPRT"));
        emp.setGL05LJPRT(request.getParameter("GL05LJPRT"));
        emp.setGL05DMPRT(request.getParameter("GL05DMPRT"));
        emp.setGL05MODEM(request.getParameter("GL05MODEM"));
        emp.setGL05MMEDIA(GL05MMEDIA);
        emp.setGL05CDROM(GL05CDROM);
        emp.setGL05SDI(GL05SDI);
        emp.setGL05SDDS(GL05SDDS);
        emp.setGL05IRL(GL05IRL);
        emp.setGL05JARING(GL05JARING);
        emp.setGL05NST(GL05NST);
        emp.setGL05LAYOUT(request.getParameter("GL05LAYOUT"));
        emp.setGL05DISPLAY(request.getParameter("GL05DISPLAY"));
        emp.setGL05BRNC(request.getParameter("Branch"));
        GlobalSQLStatement eb = new GlobalSQLStatement();
        eb.updateLoca(emp);
        request.setAttribute("GL05LOCA", (Object)GL05LOCA);
        request.setAttribute("GL05DESC", (Object)GL05DESC);
        request.setAttribute("GL05SUBJECT", (Object)GL05SUBJECT);
        request.setAttribute("GL05IPADD", (Object)GL05IPADD);
        request.setAttribute("GL05MATCAP", (Object)GL05MATCAP);
        request.setAttribute("GL05NOSERVER", (Object)GL05NOSERVER);
        request.setAttribute("GL05NOTER", (Object)GL05NOTER);
        request.setAttribute("GL05NOPC", (Object)GL05NOPC);
        request.setAttribute("GL05LNPRT", (Object)GL05LNPRT);
        request.setAttribute("GL05LJPRT", (Object)GL05LJPRT);
        request.setAttribute("GL05DMPRT", (Object)GL05DMPRT);
        request.setAttribute("GL05MODEM", (Object)GL05MODEM);
        request.setAttribute("GL05MMEDIA", (Object)GL05MMEDIA);
        request.setAttribute("GL05CDROM", (Object)GL05CDROM);
        request.setAttribute("GL05SDI", (Object)GL05SDI);
        request.setAttribute("GL05SDDS", (Object)GL05SDDS);
        request.setAttribute("GL05IRL", (Object)GL05IRL);
        request.setAttribute("GL05JARING", (Object)GL05JARING);
        request.setAttribute("GL05NST", (Object)GL05NST);
        request.setAttribute("GL05LAYOUT", (Object)GL05LAYOUT);
        request.setAttribute("GL05DISPLAY", (Object)GL05DISPLAY);
        request.setAttribute("GL05BRNC", (Object)GL05BRNC);
        RequestDispatcher view = this.getServletContext().getRequestDispatcher("/template?MODULE=Foundation/06_LocationCode&ACTION=LocaTable.jsp");
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
