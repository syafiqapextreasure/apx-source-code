	<%@ page import="com.ilmu.foundation.GeneralSubject.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	
	
	System.out.println("Masuk Handler Add ItemCat");
	
	 
	 String GL54SUBJSTA = request.getParameter("GL54SUBJSTA");
	 String GL54SUBJEND = request.getParameter("GL54SUBJEND");
	 String GL54MARK = request.getParameter("GL54MARK");
	 String GL54DESC = request.getParameter("GL54DESC");
	
	 SQLStatement.AddGenSubj(GL54SUBJSTA, GL54SUBJEND, GL54MARK, GL54DESC);
	
	%>