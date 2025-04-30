	<%@ page import="com.ilmu.foundation.BranchCode.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	
	
	System.out.println("Masuk Handler Add Currency");
	
	 String GL71BRNC = request.getParameter("GL71BRNC");
	 String GL71DESC = request.getParameter("GL71DESC");
	 String GL71DISPLAY = request.getParameter("GL71DISPLAY");
	 String GL71ADD1 = request.getParameter("GL71ADD1");
	 String GL71ADD2 = request.getParameter("GL71ADD2");
	 String GL71ADD3 = request.getParameter("GL71ADD3");
	 String GL71POSCODE = request.getParameter("GL71POSCODE");
	 String GL71TOWN = request.getParameter("GL71TOWN");
	 String phonenoadd = request.getParameter("phonenoadd");
	 String emailadd = request.getParameter("emailadd");
	 
	 SQLStatement.AddBranch(GL71BRNC, GL71DESC, GL71DISPLAY, GL71ADD1, GL71ADD2, GL71ADD3, GL71TOWN, GL71POSCODE,
			 phonenoadd, emailadd);
	
	%>