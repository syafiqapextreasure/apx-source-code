	<%@ page import="com.ilmu.foundation.LocationCode.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	
	
	System.out.println("Masuk Handler Add ItemCat");
	
	 
	 String GL05LOCA = request.getParameter("GL05LOCA");
	 String GL05DESC = request.getParameter("GL05DESC");
   	 String GL05SUBJECT = request.getParameter("GL05SUBJECT");
   	 String GL05IPADD = request.getParameter("GL05IPADD");
   	 String GL05MATCAP = request.getParameter("GL05MATCAP");
   	 String GL05NOSERVER = request.getParameter("GL05NOSERVER");
   	 String GL05NOTER = request.getParameter("GL05NOTER");
   	 String GL05NOPC = request.getParameter("GL05NOPC");
   	 String GL05LNPRT = request.getParameter("GL05LNPRT");
   	 String GL05LJPRT = request.getParameter("GL05LJPRT");
   	 String GL05DMPRT = request.getParameter("GL05DMPRT");
   	 String GL05MODEM = request.getParameter("GL05MODEM");
   	 String GL05MMEDIA = request.getParameter("GL05MMEDIA");
   	 String GL05CDROM = request.getParameter("GL05CDROM");
   	 String GL05SDI = request.getParameter("GL05SDI");
   	 String GL05SDDS = request.getParameter("GL05SDDS");
   	 String GL05IRL = request.getParameter("GL05IRL");
   	 String GL05JARING = request.getParameter("GL05JARING");
   	 String GL05NST = request.getParameter("GL05NST");
   	 String GL05LAYOUT = request.getParameter("GL05LAYOUT");
   	 String GL05DISPLAY = request.getParameter("GL05DISPLAY");
   	 String GL05BRNC = request.getParameter("GL05BRNC");
   	 
     if(request.getParameter("GL05SUBJECT").isEmpty() || request.getParameter("GL05DISPLAY").isEmpty() || request.getParameter("GL05LAYOUT").isEmpty() || request.getParameter("GL05MATCAP").isEmpty() || request.getParameter("GL05IPADD").isEmpty()){
    	GL05SUBJECT = " ";
    	GL05DISPLAY = " ";
    	GL05LAYOUT = " ";
    	GL05MATCAP = "0";
    	GL05IPADD = " ";
   	 } 
    

   	 SQLStatement.AddLocation(GL05LOCA, GL05DESC, GL05SUBJECT, GL05IPADD, GL05MATCAP, GL05NOSERVER, GL05NOTER, GL05NOPC, GL05LNPRT, GL05LJPRT, GL05DMPRT, GL05MODEM, GL05MMEDIA, GL05CDROM, GL05SDI, GL05SDDS, GL05IRL, GL05JARING, GL05NST, GL05LAYOUT, GL05DISPLAY, GL05BRNC);
	
	%>