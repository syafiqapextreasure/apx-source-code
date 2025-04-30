<%@page import="com.ilmu.binding.maintenance.Serial "%>

	<%
	try {
		String refno = request.getParameter("refno");
		String loginid = request.getParameter("loginid");
		
		Serial.deleteSer(refno, loginid);
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
