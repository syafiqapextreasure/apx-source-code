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
package com.ilmu.global.serial;

import com.ilmu.global.Config;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@WebServlet(value={"/GetCardexPattern"})
public class GetCardexPattern
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        String pattern = request.getParameter("pattern");
        int patternID = GetCardexPattern.getPatternID(pattern);
        System.out.println("Pattern" + patternID);
        printWriter.println(patternID);
    }

    public static int getPatternID(String pattern) {
        ArrayList patternList = new ArrayList();
        String path = Config.get("ISSUEPATTERN");
        File xmlFile = new File(path);
        int patternID = 0;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Pattern");
            int i = 0;
            while (i < nList.getLength()) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == 1) {
                    Element e = (Element)nNode;
                    ArrayList list = new ArrayList();
                    int x = 0;
                    while (x < e.getElementsByTagName("Item").getLength()) {
                        System.out.println(pattern);
                        if (e.getElementsByTagName("Item").item(x).getTextContent().contains(pattern)) {
                            patternID = Integer.parseInt(e.getAttribute("id"));
                        }
                        ++x;
                    }
                }
                ++i;
            }
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return patternID;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
