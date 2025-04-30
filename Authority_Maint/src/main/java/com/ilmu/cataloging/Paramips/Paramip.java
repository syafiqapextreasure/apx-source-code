/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.MultipartConfig
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  javax.servlet.http.Part
 */
package com.ilmu.cataloging.Paramips;

import com.ilmu.cataloging.Paramips.ISOtoMARC;
import com.ilmu.cataloging.Paramips.Paramips;
import com.ilmu.global.Glnumb;
import com.ilmu.global.Handler;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(value={"/upload"})
@MultipartConfig(maxFileSize=16177215L)
public class Paramip
extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static List<Integer> noofRecord = null;

    private String getFileName(Part part) {
        String partHeader = part.getHeader("content-disposition");
        String[] stringArray = part.getHeader("content-disposition").split(";");
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String content = stringArray[n2];
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf(61) + 1).trim().replace("\"", "");
            }
            ++n2;
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String scriptType = request.getParameter("script");
        String bufferno = null;
        if (action.equals("1")) {
            String str;
            Part filePart = request.getPart("thefile");
            InputStream inputStream = filePart.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(filePart.getInputStream(), "UTF8"));
            System.out.println("Test111");
            StringBuilder builder = new StringBuilder();
            while ((str = in.readLine()) != null) {
                System.out.println(str);
                builder.append(str);
            }
            ByteArrayInputStream is = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));
            noofRecord = ISOtoMARC.convertToMarc(is);
            response.getWriter().write("converted");
        } else if (action.equals("2")) {
            ArrayList<String> bufferlist = new ArrayList<String>();
            String buffernos = null;
            StringBuilder result = new StringBuilder();
            System.out.println("Counter334" + noofRecord);
            for (Integer counter : noofRecord) {
                System.out.println("Counter32" + counter);
                List<ISOtoMARC> script = ISOtoMARC.getScript(scriptType);
                Glnumb BUFFERNO = Glnumb.getGL98VALUE("CATBUFFERNO");
                bufferno = Handler.concatMatno(String.valueOf(BUFFERNO.getGL98VALUE()));
                System.out.println("Buffer" + bufferno);
                bufferlist.add(bufferno);
                buffernos = bufferlist.toString();
                buffernos = buffernos.replaceAll("[\\[\\]]", "");
                List<Paramips> batchNo = Paramips.RetrieveMPScript(bufferno, counter);
                System.out.println("Test" + batchNo);
                for (Paramips batchno : batchNo) {
                    Paramips.Delete_CTWORK(batchno.getPARA01TPLNAME());
                    System.out.println(batchno.getPARA01TPLNAME());
                }
            }
            response.getWriter().write(String.valueOf(buffernos) + "&done&" + bufferlist.size());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
