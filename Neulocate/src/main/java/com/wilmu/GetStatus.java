/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.wilmu.NeuLocate.stocktake.StockTake
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.wilmu;

import com.wilmu.NeuLocate.stocktake.StockTake;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/GetStatus1"})
public class GetStatus
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String barcode = request.getParameter("barcodelist");
        String invalid = request.getParameter("invalid");
        String missing = request.getParameter("missing");
        String startcallno = request.getParameter("startcallno");
        String endcallno = request.getParameter("endcallno");
        String loca = request.getParameter("locationArray");
        String smd = request.getParameter("smdArray");
        String branch = request.getParameter("branchArray");
        String item = request.getParameter("itemcateArray");
        List getTotalStat = StockTake.getTotalStat((String)barcode, (String)startcallno, (String)endcallno, (String)item, (String)smd, (String)branch, (String)loca);
        String html = "";
        PrintWriter pw = null;
        html = String.valueOf(html) + "<div class='panel panel-default' style='width:50%;float:left'> <div class='panel-body'>";
        for (StockTake j : getTotalStat) {
            html = String.valueOf(html) + "<div class='row'><div class='col-sm-5 col-md-5' style='width:150px'>" + j.getDESC() + "</div>" + "<div class='col-sm-1 col-md-1'><span class='badge badge-pill badge-light'>" + j.getCODE() + "</span></div>" + "</div>";
        }
        html = String.valueOf(html) + "</div> </div><div class='panel panel-default' style='width:50%;float:right'> <div class='panel-body'>";
        html = String.valueOf(html) + "<div class='row'><div class='col-sm-5 col-md-5' style='width:150px'>Missing from shelf</div><div class='col-sm-1 col-md-1'><span class='badge badge-pill badge-light'>" + missing + "</span></div>" + "</div>";
        html = String.valueOf(html) + "<div class='row'><div class='col-sm-5 col-md-5' style='width:150px'>Wrong shelf</div><div class='col-sm-1 col-md-1'><span class='badge badge-pill badge-light'>" + invalid + "</span></div>" + "</div>";
        html = String.valueOf(html) + "</div> </div>";
        pw = response.getWriter();
        pw.println(html);
        pw.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
