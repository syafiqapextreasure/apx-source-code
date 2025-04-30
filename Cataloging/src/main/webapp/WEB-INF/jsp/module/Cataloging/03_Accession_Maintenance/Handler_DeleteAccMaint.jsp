	<%@ page import="com.ilmu.cataloging.Acc_Maint.*, com.ilmu.foundation.*, java.util.List" %>

	<%
	try {
		String accNo = request.getParameter("accNo");
		System.out.println(accNo);
		if(CTRetrieve.CT_isDeletable(accNo))
		{
		CTRetrieve.CT_deleteAccMaint(accNo);

		out.println("ok");
		}else{
			out.println("error");
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
