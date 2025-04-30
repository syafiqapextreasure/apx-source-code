/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.commons.fileupload.FileItem
 *  org.apache.commons.fileupload.FileItemFactory
 *  org.apache.commons.fileupload.disk.DiskFileItemFactory
 *  org.apache.commons.fileupload.servlet.ServletFileUpload
 */
package com.ilmu.foundation.Test;

import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(value={"/AddPhotoServlet"})
public class AddPhotoServlet
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload((FileItemFactory)factory);
            if (!ServletFileUpload.isMultipartContent((HttpServletRequest)request)) {
                System.out.println("sorry. No file uploaded");
                return;
            }
            List items = sfu.parseRequest(request);
            FileItem id = (FileItem)items.get(0);
            String photoid = id.getString();
            FileItem title = (FileItem)items.get(1);
            String phototitle = title.getString();
            FileItem file = (FileItem)items.get(2);
            Connection con = null;
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("insert into GLPATR (GL14PATR, GL14NAME, GL14IMG) values(?,?,?)");
            ps.setString(1, photoid);
            ps.setString(2, phototitle);
            ps.setBinaryStream(3, file.getInputStream(), (int)file.getSize());
            ps.executeUpdate();
            con.commit();
            con.close();
            out.println("Photo Added Successfully. <p> <a href='listphotos'>List Photos </a>");
        }
        catch (Exception ex) {
            out.println("Error --> " + ex.getMessage());
        }
    }
}
