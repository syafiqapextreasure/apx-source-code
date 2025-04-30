/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parableTemplateMaint;

import com.kmlink.ilmu.parableTemplateMaint.Style;
import java.util.List;

public class StyleTable {
    private String passHTMLtableToServelt;

    public String getPassHTMLtableToServelt() {
        return this.passHTMLtableToServelt;
    }

    public void setPassHTMLtableToServelt(String passHTMLtableToServelt) {
        this.passHTMLtableToServelt = passHTMLtableToServelt;
    }

    public StyleTable(String passHTMLtableToServelt) {
        this.passHTMLtableToServelt = passHTMLtableToServelt;
    }

    public StyleTable() {
    }

    public void creatHTMLtable(List<Style> arrayOfString) {
        String htmlTable = "Interview LineUp <table border ='1'><thead><th>Style Name</th><th>Value</th></thead> ";
        int i = 0;
        while (i < arrayOfString.size()) {
            htmlTable = htmlTable.concat("<tr><td>" + arrayOfString.get(i).getName() + "</td>" + "<td>" + arrayOfString.get(i).getValue() + "</td> " + "</tr> ");
            ++i;
        }
        this.passHTMLtableToServelt = htmlTable = htmlTable.concat("</table>");
    }
}
