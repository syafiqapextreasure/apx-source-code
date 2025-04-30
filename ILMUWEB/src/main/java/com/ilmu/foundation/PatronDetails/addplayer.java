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
package com.ilmu.foundation.PatronDetails;

import com.ilmu.global.connection.DBConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(value={"/addplayer"})
@MultipartConfig
public class addplayer
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con = null;
        Object fileInputStream = null;
        Object blob = null;
        try {
            try {
                Part p = request.getPart("playername");
                Scanner scanner = new Scanner(p.getInputStream());
                String playername = scanner.nextLine();
                Part photo = request.getPart("playerphoto");
                Scanner scanner2 = new Scanner(photo.getInputStream());
                String playername2 = scanner2.nextLine();
                System.out.println(playername2);
                con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("insert into GLPATR (GL14PATR, GLIMG2) values(?,?)");
                ps.setString(1, playername);
                File fBlob = new File("C:\\Users\\User\\Downloads\\small-red-apple-hi.png");
                FileInputStream is = new FileInputStream(fBlob);
                ps.setBinaryStream(2, (InputStream)is, (int)fBlob.length());
                ps.executeUpdate();
                con.commit();
                con.close();
                out.println("Added Player Successfully. <p> <a href='listplayers'>List Players </a>");
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                out.close();
            }
        }
        finally {
            out.close();
        }
    }
}
