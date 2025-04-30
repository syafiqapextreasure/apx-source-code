	<%@ page import="com.ilmu.foundation.PatronStatus.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	
	
	System.out.println("Masuk Handler Add Status");
	
	 
	 String GL08STAT = request.getParameter("GL08STAT");
	 System.out.println(GL08STAT);
   	 String GL08DESC = request.getParameter("GL08DESC");
   	 System.out.println(GL08DESC);
   	 
	 String GL08ACTION1 = request.getParameter("GL08ACTION1");
	 String GL08ACTION2 = request.getParameter("GL08ACTION2");
	 String GL08ACTION3 = request.getParameter("GL08ACTION3");
	 String GL08ACTION4 = request.getParameter("GL08ACTION4");
	 String GL08ACTION5 = request.getParameter("GL08ACTION5");
	 String GL08ACTION6 = request.getParameter("GL08ACTION6");
	 String GL08ACTION7 = request.getParameter("GL08ACTION7");
	 String GL08ACTION8 = request.getParameter("GL08ACTION8");
	 String GL08ACTION9 = request.getParameter("GL08ACTION9");
	 String GL08ACTION10 = request.getParameter("GL08ACTION10");
	 String GL08ACTION11 = request.getParameter("GL08ACTION11");
	 String GL08ACTION12 = request.getParameter("GL08ACTION12");
	 String GL08ACTION13 = request.getParameter("GL08ACTION13");
	 String GL08ACTION14 = request.getParameter("GL08ACTION14");
	 String GL08ACTION15 = request.getParameter("GL08ACTION15");
	 String GL08ACTION16 = request.getParameter("GL08ACTION16");
	 String GL08ACTION17 = request.getParameter("GL08ACTION17");
	 String GL08ACTION18 = request.getParameter("GL08ACTION18");
	 String GL08ACTION19 = request.getParameter("GL08ACTION19");
	 String GL08ACTION20 = request.getParameter("GL08ACTION20");
	 
   	 
	 //SQLStatement.AddStatus(GL08STAT, GL08DESC, GL08ACTION)
	 SQLStatement.AddStatus(GL08STAT, GL08DESC, GL08ACTION1, GL08ACTION2, GL08ACTION3, GL08ACTION4, GL08ACTION5, GL08ACTION6, GL08ACTION7, GL08ACTION8, GL08ACTION9, GL08ACTION10, GL08ACTION11, GL08ACTION12, GL08ACTION13, GL08ACTION14, GL08ACTION15, GL08ACTION16, GL08ACTION17, GL08ACTION18, GL08ACTION19, GL08ACTION20);
	
	
	%>