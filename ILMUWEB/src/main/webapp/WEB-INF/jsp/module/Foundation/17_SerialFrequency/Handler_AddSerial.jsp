	<%@ page import="com.ilmu.foundation.SerialFrequency.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	
	
	System.out.println("Masuk Handler Add Serial");
	
	 String GL49FREQ = request.getParameter("GL49FREQ");
   	 String GL49DESC = request.getParameter("GL49DESC");
   	 String GL49TIME = request.getParameter("GL49TIME");
   	 String GL49ALERT = request.getParameter("GL49ALERT");
   	 String GL49PTYPE = request.getParameter("GL49PTYPE");
	
   	SQLStatement.AddSerial(GL49FREQ, GL49DESC, GL49TIME, GL49ALERT, GL49PTYPE);
	
	%>