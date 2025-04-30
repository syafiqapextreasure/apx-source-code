	<%@ page import="com.ilmu.foundation.CurrencyCode.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	
	
	System.out.println("Masuk Handler Add Currency");
	
	 String GL24FOREX = request.getParameter("GL24FOREX");
   	 String GL24DESC = request.getParameter("GL24DESC");
   	 String GL24PRATE = request.getParameter("GL24PRATE");
   	 String GL24BRATE = request.getParameter("GL24BRATE");
   	 String GL24PDATE = request.getParameter("GL24PDATE");
   	 String GL24BDATE = request.getParameter("GL24BDATE");
	
   	SQLStatement.AddCurrency(GL24FOREX, GL24DESC, GL24PRATE, GL24BRATE, GL24PDATE, GL24BDATE);
	
	%>