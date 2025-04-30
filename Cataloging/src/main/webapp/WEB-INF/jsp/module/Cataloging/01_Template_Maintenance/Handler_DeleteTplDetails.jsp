<%@page import="com.ilmu.cataloging.Template_Maint.Template_Maintenance,
				com.ilmu.cataloging.Template_Maint.Tag_Handler"%>
<%@page import="com.ilmu.cataloging.Template_Maint.Cataloging"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>

<%
try {
	String CTMSTR = request.getParameter("CTMSTR");
	//Delete if the master already have details
	Template_Maintenance.CT_DeleteTplInfo(CTMSTR);
System.out.println("ok");
	out.println("ok");
	
} catch (Exception e) {
	System.out.println("error");
	out.println("error");
}

%>