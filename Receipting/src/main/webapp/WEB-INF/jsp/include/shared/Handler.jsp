	<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<%
String handler = request.getParameter("handler");
System.out.println(handler);
if(handler.equals("NewCT04MATNO")){
/* 	Glnumb matno = Glnumb.getGL98VALUE("CATBUFFERNO");
	out.println(Handler.concatMatno(String.valueOf(matno.getGL98VALUE()))); */
}else if(handler.equals("ExistCT04MATNO")){
	String CT04MATNO = request.getParameter("CT04MATNO");
	if(BO_Record.matno_exist(CT04MATNO)){
		//BO_Record.CTWORK_Record(CT04MATNO);
	}
}
else if(handler.equals("mandatoryTag")){
	/* String mandatoryTag = BO_Validation.mandatoryTag();
	out.println(mandatoryTag); */
}
/* else if (handler.equals("CTWORKDetails")){
	List<BO_Record> details = null;
	String CT04MATNO = request.getParameter("CT04MATNO");
	details = BO_Record.getCTWORK(CT04MATNO);
	System.out.println(CT04MATNO);
	for(BO_Record i: details)
	{
		
	System.out.println(i.getCT04STATUS()+ "," + i.getCT04CREBY() + "," + i.getCT04CRETIME() + "," + 
			i.getCT04CREDATE()+","+i.getCT04MODIBY()+","+i.getCT04MODITIME()+","+i.getCT04MODIDATE());
	}
} */
%>