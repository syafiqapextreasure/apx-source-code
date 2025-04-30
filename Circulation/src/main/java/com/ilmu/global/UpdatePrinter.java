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
package com.ilmu.global;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/UpdatePrinter"})
public class UpdatePrinter
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String defaultprinter = request.getParameter("default");
        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of printers configured: " + printServices.length);
        PrintService[] printServiceArray = printServices;
        int n = printServices.length;
        int n2 = 0;
        while (n2 < n) {
            PrintService printer = printServiceArray[n2];
            System.out.println("Printer: " + printer.getName());
            if (printer.getName().equals(defaultprinter)) {
                try {
                    System.out.println("Printeraaa: " + printer + "Print " + defaultprinter);
                    pj.setPrintService(printer);
                    pj.printDialog();
                }
                catch (PrinterException printerException) {
                    // empty catch block
                }
            }
            ++n2;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
