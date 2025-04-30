/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.kmlink.ilmu.tagParameter;

import com.kmlink.ilmu.tagParameter.TagParameter;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/DeleteTag"})
public class DeleteTag
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in delete tag");
        String marc = request.getParameter("marc");
        String tagId = request.getParameter("tagNo");
        String desc = request.getParameter("desc");
        String abbreDesc = request.getParameter("abbrDesc");
        String indexTable = request.getParameter("indexTable");
        System.out.println("marc : " + marc);
        System.out.println("tagId : " + tagId);
        System.out.println("description : " + desc);
        System.out.println("Abbreviated description : " + abbreDesc);
        System.out.println("tableName : " + indexTable);
        try {
            TagParameter.deleteTagParameter(marc, tagId, desc, abbreDesc, indexTable);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
