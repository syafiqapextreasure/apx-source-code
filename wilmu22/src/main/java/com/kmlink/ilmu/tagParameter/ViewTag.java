/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.reflect.TypeToken
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.kmlink.ilmu.tagParameter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.kmlink.ilmu.tagParameter.Indicator1;
import com.kmlink.ilmu.tagParameter.Indicator2;
import com.kmlink.ilmu.tagParameter.Subfields;
import com.kmlink.ilmu.tagParameter.TagContainer;
import com.kmlink.ilmu.tagParameter.TagParameter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/ViewTag"})
public class ViewTag
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tagId = request.getParameter("tagNo");
        List<TagParameter> tagParameter = TagParameter.viewTag(tagId);
        try {
            List<Indicator1> indicator1 = Indicator1.getIndicator1(tagId);
            List<Indicator2> indicator2 = Indicator2.getIndicator2(tagId);
            List<Subfields> subfields = Subfields.getSubfield(tagId);
            TagContainer tagContainer = new TagContainer(tagParameter, indicator1, indicator2, subfields);
            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree((Object)tagContainer, new TypeToken<TagContainer>(){}.getType());
            response.setCharacterEncoding("UTF8");
            response.setContentType("application/json");
            response.getWriter().print(element);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
