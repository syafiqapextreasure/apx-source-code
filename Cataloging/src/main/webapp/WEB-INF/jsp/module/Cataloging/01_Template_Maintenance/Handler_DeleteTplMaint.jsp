<%@page import="com.ilmu.cataloging.Template_Maint.Template_Maintenance"%>

	<%
	try {
		String tplID = request.getParameter("tplID");
		//System.out.println(controlNo);
		if(Template_Maintenance.Tpl_isDeletable(tplID))
		{
			System.out.println("rr");
			Template_Maintenance.CT_deleteTplMaint(tplID);

		out.println("ok");
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
