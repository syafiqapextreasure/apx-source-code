<%@page import="com.ilmu.ill.IncomingRequest.IncomingRequest "%>

	<%
	try {
		String code = request.getParameter("code");
		String loginid = request.getParameter("loginid");
		String accno = request.getParameter("accno");
		
		boolean sucess = false;
		
		sucess = IncomingRequest.deleteIncomMstr(loginid, code, accno);
		out.println("ok");
		
		/* Monograph.deletemonoGraph(refno, loginid);
		out.println("ok"); */
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
