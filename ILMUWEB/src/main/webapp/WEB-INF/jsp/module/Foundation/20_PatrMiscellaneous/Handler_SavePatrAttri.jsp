<%@page import="java.util.List, com.ilmu.foundation.PatrMiscellaneous.SQLPatrMiscellaneous "%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime" %>

<%	
	String patrAttri = request.getParameter("patrAttri");
    System.out.println("patrAttri >  "+patrAttri);
    
    String desc = request.getParameter("desc");
    System.out.println("desc >  "+desc);
    
    String daterec = request.getParameter("daterec");
    System.out.println("daterec >  "+daterec);
    
    String recby = request.getParameter("recby");
    System.out.println("recby >  "+recby);
	
    boolean bSuccessful;
    
    SQLPatrMiscellaneous.CreateMisscellaneous(patrAttri, desc, daterec, recby);

%>