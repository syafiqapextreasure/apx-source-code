 <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.print.*,java.awt.print.PrinterJob"%>
 <%
 /* String printerName = request.getParameter("printer");
 PrinterJob pj = PrinterJob.getPrinterJob();
 PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
 System.out.println("Number of printers configured: " + printServices.length);
 *//*  for (PrintService printer : printServices) {
     System.out.println("Printer: " + printer.getName());
     if (printer.getName().equals(printerName)) {

             pj.setPrintService(printer);

     }
 } */
 String printerName = request.getParameter("printer");

PrinterJob pj = PrinterJob.getPrinterJob();
    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
    System.out.println("Number of printers configured: " + printServices.length);
    for (PrintService printer : printServices) {
        System.out.println("Printer: " + printer.getName());
        if (printer.getName().equals(printerName)) {
        	System.out.println("s" + printer);
        	 pj.setPrintService(printer);
        }
    }

    %>
