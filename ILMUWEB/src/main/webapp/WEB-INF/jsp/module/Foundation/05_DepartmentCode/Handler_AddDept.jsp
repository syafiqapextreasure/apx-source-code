	<%@ page import="com.ilmu.foundation.DepartmentCode.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	
	
	System.out.println("Masuk Handler Add Dept");
	
	 String GL11DEPT = request.getParameter("GL11DEPT");
	 String GL11NAME = request.getParameter("GL11NAME");
	 String GL11ADD1 = request.getParameter("GL11ADD1");
	 System.out.println(GL11ADD1);
	 String GL11ADD2 = request.getParameter("GL11ADD2");
		
	 String GL11ADD3 = request.getParameter("GL11ADD3");
	 String GL11POSCODE = request.getParameter("GL11POSCODE");
	 String GL11TOWN = request.getParameter("GL11TOWN");
	 String GL11TEL = request.getParameter("GL11TEL");
		
	 String GL11HEAD = request.getParameter("GL11HEAD");
	 String GL11STAFF = request.getParameter("GL11STAFF");
	 String GL11FAX = request.getParameter("GL11FAX");
	
	 SQLStatement.AddDeptCode( GL11DEPT, GL11NAME, GL11ADD1, GL11ADD2, GL11ADD3, GL11POSCODE, GL11TOWN, GL11TEL, GL11HEAD, GL11STAFF, GL11FAX);
	
	%>