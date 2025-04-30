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
package com.kmlink.ilmu.parableTemplateMaint;

import com.kmlink.ilmu.parableTemplateMaint.ParableTemplateMaintenance;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/SaveStyleTitle"})
public class SaveStyleTitle
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String template = request.getParameter("selectedTemplate");
        String branch = request.getParameter("selectedBranch");
        String attrType = request.getParameter("attrType");
        String selectedAttrType = request.getParameter("selectedAttrType");
        String selectedPaperType = request.getParameter("selectedPaperType");
        String sheetSize = request.getParameter("sheetSize");
        String sheetWidth = request.getParameter("sheetWidth");
        String sheetHeight = request.getParameter("sheetHeight");
        String sheetTop = request.getParameter("sheetTop");
        String sheetLeft = request.getParameter("sheetLeft");
        String sheetRowPerSheet = request.getParameter("sheetRowPerSheet");
        String sheetColPerSheet = request.getParameter("sheetColPerSheet");
        String sheetPrint = request.getParameter("sheetSelectPrint");
        String sheetNotes = request.getParameter("sheetNotes");
        String sheetColumnName = request.getParameter("sheetColumnName");
        String sheetTemplateGroup = request.getParameter("sheetTemplateGroup");
        String sheetTableName = request.getParameter("sheetTableName");
        String sheetDecimal = request.getParameter("sheetDecimal");
        String labelWidth = request.getParameter("labelWidth");
        String labelHeight = request.getParameter("labelHeight");
        String labelRow = request.getParameter("labelRow");
        String labelCol = request.getParameter("labelCol");
        String sendSelectedStyle = request.getParameter("sendSelectedStyle");
        String styleName = request.getParameter("styleName");
        String styleDisplay = request.getParameter("styleDisplay");
        String styleTitleFontDeco = request.getParameter("styleTitleFontDeco");
        String styleTitleFontFamily = request.getParameter("styleTitleFontFamily");
        String styleTitleFontSize = request.getParameter("styleTitleFontSize");
        String styleTitleFontStyle = request.getParameter("styleTitleFontStyle");
        String styleTitleFontWeight = request.getParameter("styleTitleFontWeight");
        String styleTitleFormat = request.getParameter("styleTitleFormat");
        String styleTitleLeft = request.getParameter("styleTitleLeft");
        String styleLineHeight = request.getParameter("styleLineHeight");
        String styleOverflow = request.getParameter("styleOverflow");
        String styleTitlePrintout = request.getParameter("styleTitlePrintout");
        String styleTitleTextAlign = request.getParameter("styleTitleTextAlign");
        String styleTextOverflow = request.getParameter("styleTextOverflow");
        String styleTitleTop = request.getParameter("styleTitleTop");
        String styleTitleType = request.getParameter("styleTitleType");
        String styleTitleWidth = request.getParameter("styleTitleWidth");
        if (!((sheetSize.equals("0") || sheetWidth.equals("0") || sheetHeight.equals("0") || sheetTop.equals("0") || sheetLeft.equals("0")) && (labelWidth.equals("0") || labelHeight.equals("0") || labelRow.equals("0") || labelCol.equals("0")))) {
            ParableTemplateMaintenance.saveAttribute(template, branch, attrType, selectedAttrType, selectedPaperType, sheetSize, sheetWidth, sheetHeight, sheetTop, sheetLeft, sheetRowPerSheet, sheetColPerSheet, sheetPrint, sheetNotes, sheetColumnName, sheetTemplateGroup, sheetTableName, sheetDecimal, labelWidth, labelHeight, labelRow, labelCol);
        } else {
            ParableTemplateMaintenance.saveStyleTitle(template, branch, sendSelectedStyle, styleName, styleDisplay, styleTitleFontDeco, styleTitleFontFamily, styleTitleFontSize, styleTitleFontStyle, styleTitleFontWeight, styleTitleFormat, styleTitleLeft, styleLineHeight, styleOverflow, styleTitlePrintout, styleTitleTextAlign, styleTextOverflow, styleTitleTop, styleTitleType, styleTitleWidth);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
