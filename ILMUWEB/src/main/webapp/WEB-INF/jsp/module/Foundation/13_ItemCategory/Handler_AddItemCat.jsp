	<%@ page import="com.ilmu.foundation.ItemCategory.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	
	
	System.out.println("Masuk Handler Add ItemCat");
	
	 
	 String GL10ICAT = request.getParameter("GL10ICAT");
	 System.out.println(GL10ICAT);
   	 String GL10DESC = request.getParameter("GL10DESC");
   	 System.out.println(GL10DESC);
   	 String GL10DISPLAY = request.getParameter("GL10DISPLAY");
   	 System.out.println(GL10DISPLAY);
   	 String GL10ELIG = request.getParameter("GL10ELIG");
   	 System.out.println(GL10ELIG);
   	 String GL10UNIT = request.getParameter("GL10UNIT");
   	 System.out.println(GL10UNIT);
   	 String GL10RESERVEC = request.getParameter("GL10RESERVEC");
   	 System.out.println(GL10RESERVEC);
	
   	 SQLStatement.AddItemCat(GL10ICAT, GL10DESC, GL10DISPLAY, GL10ELIG, GL10UNIT, GL10RESERVEC);
	
	%>