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
package com.kmlink.ilmu.tagParameter;

import com.google.gson.Gson;
import com.kmlink.ilmu.tagParameter.TagParameter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/EditTag"})
public class EditTag
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in edit tag parameter");
        String marc = request.getParameter("marc");
        String tagId = request.getParameter("tagNo");
        String desc = request.getParameter("desc");
        String abbreDesc = request.getParameter("abbrDesc");
        String indexTable = request.getParameter("indexTable");
        String acronym = request.getParameter("acronym");
        String authorityGroup = request.getParameter("authorityGroup");
        String fieldLength = request.getParameter("fieldLength");
        String indexLanguage = request.getParameter("indexLanguage");
        String ind1 = request.getParameter("indicator1");
        String ind2 = request.getParameter("indicator2");
        String defaultValue = request.getParameter("defaultValue");
        String remark = request.getParameter("remark");
        String repeatable = request.getParameter("repeatable");
        String mandatory = request.getParameter("mandatory");
        String indexFlag = request.getParameter("indexFlag");
        String keyword = request.getParameter("keyword");
        String duplicateCheck = request.getParameter("duplicateCheck");
        String authorityFlag = request.getParameter("authorityFlag");
        String copyPaste = request.getParameter("copyPaste");
        String paramLink = request.getParameter("paramLink");
        String multimediaTag = request.getParameter("multimediaTag");
        String ind1Undefine = request.getParameter("ind1Undefine");
        String ind1Input0 = request.getParameter("ind1Input0");
        String ind1Input1 = request.getParameter("ind1Input1");
        String ind1Input2 = request.getParameter("ind1Input2");
        String ind1Input3 = request.getParameter("ind1Input3");
        String ind1Input4 = request.getParameter("ind1Input4");
        String ind1Input5 = request.getParameter("ind1Input5");
        String ind1Input6 = request.getParameter("ind1Input6");
        String ind1Input7 = request.getParameter("ind1Input7");
        String ind1Input8 = request.getParameter("ind1Input8");
        String ind1Input9 = request.getParameter("ind1Input9");
        String ind2Undefine = request.getParameter("ind2Undefine");
        String ind2Input0 = request.getParameter("ind2Input0");
        String ind2Input1 = request.getParameter("ind2Input1");
        String ind2Input2 = request.getParameter("ind2Input2");
        String ind2Input3 = request.getParameter("ind2Input3");
        String ind2Input4 = request.getParameter("ind2Input4");
        String ind2Input5 = request.getParameter("ind2Input5");
        String ind2Input6 = request.getParameter("ind2Input6");
        String ind2Input7 = request.getParameter("ind2Input7");
        String ind2Input8 = request.getParameter("ind2Input8");
        String ind2Input9 = request.getParameter("ind2Input9");
        String sfA = request.getParameter("sfA");
        String sfB = request.getParameter("sfB");
        String sfC = request.getParameter("sfC");
        String sfD = request.getParameter("sfD");
        String sfE = request.getParameter("sfE");
        String sfF = request.getParameter("sfF");
        String sfG = request.getParameter("sfG");
        String sfH = request.getParameter("sfH");
        String sfI = request.getParameter("sfI");
        String sfJ = request.getParameter("sfJ");
        String sfK = request.getParameter("sfK");
        String sfL = request.getParameter("sfL");
        String sfM = request.getParameter("sfM");
        String sfN = request.getParameter("sfN");
        String sfO = request.getParameter("sfO");
        String sfP = request.getParameter("sfP");
        String sfQ = request.getParameter("sfQ");
        String sfR = request.getParameter("sfR");
        String sfS = request.getParameter("sfS");
        String sfT = request.getParameter("sfT");
        String sfU = request.getParameter("sfU");
        String sfV = request.getParameter("sfV");
        String sfW = request.getParameter("sfW");
        String sfX = request.getParameter("sfX");
        String sfY = request.getParameter("sfY");
        String sfZ = request.getParameter("sfZ");
        String sf0 = request.getParameter("sf0");
        String sf1 = request.getParameter("sf1");
        String sf2 = request.getParameter("sf2");
        String sf3 = request.getParameter("sf3");
        String sf4 = request.getParameter("sf4");
        String sf5 = request.getParameter("sf5");
        String sf6 = request.getParameter("sf6");
        String sf7 = request.getParameter("sf7");
        String sf8 = request.getParameter("sf8");
        String sf9 = request.getParameter("sf9");
        System.out.println("marc : " + marc);
        System.out.println("tagId : " + tagId);
        System.out.println("description : " + desc);
        System.out.println("Abbreviated description : " + abbreDesc);
        System.out.println("tableName : " + indexTable);
        System.out.println("fieldLength : " + fieldLength);
        System.out.println("indexLanguage : " + indexLanguage);
        System.out.println("defaultValue : " + defaultValue);
        System.out.println("remark : " + remark);
        System.out.println("repeatable : " + repeatable);
        System.out.println("mandatory : " + mandatory);
        System.out.println("indexFlag : " + indexFlag);
        System.out.println("keyword : " + keyword);
        System.out.println("duplicateCheck : " + duplicateCheck);
        System.out.println("authorityFlag : " + authorityFlag);
        System.out.println("copyPaste : " + copyPaste);
        System.out.println("paramLink : " + paramLink);
        System.out.println("multimediaTag : " + multimediaTag);
        repeatable = repeatable.equals("true") ? "Y" : "N";
        mandatory = mandatory.equals("true") ? "Y" : "N";
        indexFlag = indexFlag.equals("true") ? "Y" : "N";
        keyword = keyword.equals("true") ? "Y" : "N";
        duplicateCheck = duplicateCheck.equals("true") ? "Y" : "N";
        authorityFlag = authorityFlag.equals("true") ? "Y" : "N";
        copyPaste = copyPaste.equals("true") ? "Y" : "N";
        paramLink = paramLink.equals("true") ? "Y" : "N";
        multimediaTag = multimediaTag.equals("true") ? "Y" : "N";
        TagParameter.editTagParameter(marc, tagId, desc, abbreDesc, acronym, authorityGroup, fieldLength, indexLanguage, ind1, ind2, defaultValue, remark, repeatable, mandatory, indexFlag, keyword, duplicateCheck, authorityFlag, copyPaste, paramLink, multimediaTag, ind1Undefine, ind1Input0, ind1Input1, ind1Input2, ind1Input3, ind1Input4, ind1Input5, ind1Input6, ind1Input7, ind1Input8, ind1Input9, ind2Undefine, ind2Input0, ind2Input1, ind2Input2, ind2Input3, ind2Input4, ind2Input5, ind2Input6, ind2Input7, ind2Input8, ind2Input9, sfA, sfB, sfC, sfD, sfE, sfF, sfG, sfH, sfI, sfJ, sfK, sfL, sfM, sfN, sfO, sfP, sfQ, sfR, sfS, sfT, sfU, sfV, sfW, sfX, sfY, sfZ, sf0, sf1, sf2, sf3, sf4, sf5, sf6, sf7, sf8, sf9);
        Gson gson = new Gson();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
