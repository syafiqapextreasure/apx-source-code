<%@page import="com.ilmu.enquiry.suggBox.suggBox"%>

	<%
	try {
		String patrId = request.getParameter("patrId");
		String suggDate = request.getParameter("suggDate");
		String suggTime = request.getParameter("suggTime");		 
		
		suggBox.deleteSuggBox(patrId, suggDate, suggTime);
		out.println("ok");
		/* System.out.println("DELETE FundAccountChart");
		//System.out.println(controlNo);
		if(FundAccountChart.CheckIfExist(fundcode)){
			System.out.println("rr");
			FundAccountChart.deleteFundAccountChart(fundcode);

			out.println("ok");
		} */
	} catch (Exception e) {
		out.println("error");
	}
	%>
