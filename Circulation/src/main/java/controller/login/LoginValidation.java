/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.RequestDispatcher
 *  javax.servlet.ServletContext
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  javax.servlet.http.HttpSession
 */
package controller.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginValidation
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block20: {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            System.out.println("test");
            ServletContext context = this.getServletContext();
            String db_driver = context.getInitParameter("driver");
            String db_url = context.getInitParameter("url");
            String db_user = context.getInitParameter("user");
            String db_password = context.getInitParameter("password");
            Connection con = null;
            Statement stmt = null;
            ResultSet rset = null;
            String user = null;
            int marker = 0;
            String error_login = "Nama pengguna atau kata laluan salah";
            String error_duplicate_user = "Terdapat dua nama pengguna yang sama";
            String v_main_page = "";
            try {
                String user_kod = request.getParameter("id").trim();
                String user_password = request.getParameter("password").trim();
                user_password = user_password.toUpperCase();
                String logintime = request.getParameter("logintime");
                if (user_kod == null && user_password == null) {
                    user_kod = "";
                    user_password = "";
                }
                try {
                    Class.forName(db_driver);
                }
                catch (ClassNotFoundException cnfe) {
                    out.println("driver not found :" + cnfe.getMessage());
                }
                try {
                    char c;
                    con = DriverManager.getConnection(db_url, db_user, db_password);
                    String db_passwd = "";
                    String real_passwd = "";
                    int v_user_no = 0;
                    String query = "select user_kod from kawalan where user_kod='" + user_kod + "' and menu1_kod='9' and menu2_kod='9' and menu3_kod='9'";
                    stmt = con.createStatement();
                    rset = stmt.executeQuery(query);
                    while (rset.next()) {
                        ++marker;
                    }
                    query = "SELECT USER_KOD, USER_NAME, USER_PASSWORD FROM PENGGUNA WHERE USER_KOD = '" + user_kod + "'";
                    stmt = con.createStatement();
                    rset = stmt.executeQuery(query);
                    if (rset.next()) {
                        db_passwd = rset.getString("USER_PASSWORD");
                        user = rset.getString("USER_NAME");
                    }
                    int i = 0;
                    while (i < user_kod.length()) {
                        c = user_kod.charAt(i);
                        v_user_no += c;
                        ++i;
                    }
                    v_user_no = v_user_no % 20 + 113;
                    i = 0;
                    while (i < user_password.length()) {
                        c = user_password.charAt(i);
                        real_passwd = String.valueOf(real_passwd) + (char)(c + v_user_no);
                        ++i;
                    }
                    System.out.println(real_passwd);
                    System.out.println(db_passwd);
                    if (marker == 1) {
                        if (db_passwd.equals(real_passwd)) {
                            query = "SELECT A.MENU_NAMA, A.MENU_ID_NAMA, A.MENU_PROG, A.MENU_IKON ";
                            query = String.valueOf(query) + "FROM DO_TB_MENU A, DO_TB_KAWALAN_MENU B ";
                            query = String.valueOf(query) + "WHERE A.MENU_GROUP_ID = B.MENU_GROUP_ID ";
                            query = String.valueOf(query) + "AND A.MENU_ID = B.MENU_ID ";
                            query = String.valueOf(query) + "AND B.USER_KOD =  '" + user_kod + "'";
                            query = String.valueOf(query) + "ORDER BY A.MENU_GROUP_ID, A.MENU_ID ";
                            stmt = con.createStatement();
                            rset = stmt.executeQuery(query);
                            v_main_page = rset.next() ? rset.getString("MENU_PROG") : "DontHaveAccess.ppat";
                            System.out.println("success");
                            HttpSession session = request.getSession();
                            session.setAttribute("user", (Object)user);
                            session.setAttribute("logintime", (Object)logintime);
                            session.setAttribute("user_kod", (Object)user_kod);
                            RequestDispatcher dispatch = request.getRequestDispatcher(v_main_page);
                            dispatch.forward((ServletRequest)request, (ServletResponse)response);
                        } else {
                            request.setAttribute("error", (Object)"Nama pengguna atau kata laluan salah");
                            RequestDispatcher dispatch2 = request.getRequestDispatcher("login2.jsp");
                            dispatch2.forward((ServletRequest)request, (ServletResponse)response);
                        }
                        break block20;
                    }
                    if (marker > 1) {
                        request.setAttribute("error", (Object)"Terdapat dua nama pengguna yang sama");
                        RequestDispatcher dispatch2 = request.getRequestDispatcher("error.jsp");
                        dispatch2.forward((ServletRequest)request, (ServletResponse)response);
                        break block20;
                    }
                    request.setAttribute("error", (Object)"Nama pengguna atau kata laluan salah");
                    RequestDispatcher dispatch2 = request.getRequestDispatcher("login2.jsp");
                    dispatch2.forward((ServletRequest)request, (ServletResponse)response);
                }
                catch (SQLException sqle) {
                    out.println(sqle.getMessage());
                }
            }
            finally {
                out.close();
                try {
                    con.close();
                    stmt.close();
                    rset.close();
                }
                catch (SQLException sqle2) {
                    out.println(sqle2.getMessage());
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }
}
