<%@page import="com.ilmu.cataloging.Template_Maint.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
	
	<%
	try {
		int CTTPLID = Integer.parseInt(request.getParameter("CTTPLID"));
		String CTTPLNAME = request.getParameter("CTTPLNAME");
		//String CT05SRAW = request.getParameter("CT05SRAW");
		int CTMSTR = Integer.parseInt(request.getParameter("CTMSTR"));
		String CTTPLTAG = request.getParameter("CTTPLTAG");
		//String frequency = request.getParameter("frequency");
		String CTTPLSUBF = request.getParameter("CTTPLSUBF");
		String CTTPLINDI1 = request.getParameter("CTTPLINDI1");
		String CTTPLINDI2 = request.getParameter("CTTPLINDI2");
		
		boolean update = Template_Maintenance.CT_editTplInfoMaint(CTTPLID, CTTPLNAME,CTTPLTAG,CTTPLINDI1,CTTPLINDI2,CTTPLSUBF,CTMSTR);
		
		if(update)
		{
			
			out.println("ok");
		}else{
			
			out.println("error");
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>