<%@page import="java.util.List, com.ilmu.foundation.PatrMiscellaneous.SQLPatrMiscellaneous "%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime" %>

<%	
	String patrAttri = request.getParameter("patrAttri");
    System.out.println("patrAttri >  "+patrAttri);
    
    String desc = request.getParameter("desc");
    System.out.println("desc >  "+desc);

    boolean bSuccessful;
    
    SQLPatrMiscellaneous.updatingMisscellaneous(patrAttri, desc);

%>