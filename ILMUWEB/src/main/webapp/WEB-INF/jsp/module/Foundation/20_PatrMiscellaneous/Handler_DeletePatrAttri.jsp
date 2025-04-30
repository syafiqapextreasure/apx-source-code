<%@page import="java.util.List, com.ilmu.foundation.PatrMiscellaneous.SQLPatrMiscellaneous "%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime" %>

<%	
	String patrAttri = request.getParameter("deleteid");
    System.out.println("patrAttri >  "+patrAttri);
    
    SQLPatrMiscellaneous.deleteMisscellaneous(patrAttri);

%>