/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.ilmu.global;

import com.google.gson.Gson;
import com.ilmu.global.GlobalValidation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/GetGlobalValidation"})
public class GetGlobalValidation
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.print("getGlobalSetting " + action);
        if (action.equals("maxL")) {
            int accLenght = 0;
            int accLenghtMin = 0;
            String chkAcc = "N";
            accLenght = GlobalValidation.getAccessionMaxLength();
            accLenghtMin = GlobalValidation.getAccessionMinLength();
            chkAcc = GlobalValidation.chkAccLength();
            System.out.print("flag Accession Lenght " + accLenght);
            System.out.print("flag Accession Lenght " + accLenghtMin);
            response.setCharacterEncoding("UTF8");
            response.setContentType("application/json");
            MyData data = new MyData(accLenght, chkAcc, accLenghtMin);
            System.out.print("flag Accession Lenght data " + data);
            Gson gson = new Gson();
            String json = gson.toJson((Object)data);
            response.getWriter().print(json);
        } else {
            System.out.print("add here ");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public class MyData {
        private int accLength;
        private int accLenghtMin;
        private String chkAcc;

        public MyData(int accLength, String chkAcc, int accLenghtMin) {
            this.accLength = accLength;
            this.chkAcc = chkAcc;
            this.accLenghtMin = accLenghtMin;
        }
    }
}
