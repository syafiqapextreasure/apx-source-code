<%@page import="com.ilmu.foundation.VendorFeedback.VendorFeedback "%>

	<%
	try {
		String code = request.getParameter("code");
		System.out.println("DELETE Vendor Feedback");
		
		/*  */
		if(VendorFeedback.CheckIfExistACFEED(code) && VendorFeedback.CheckIfExistSEFEED(code)){
			VendorFeedback.deleteVendorFeedback(code);
			out.println("ok");
		}
		//System.out.println(controlNo);
		/* if(FundAccountChart.CheckIfExist(fundcode)){
			System.out.println("rr");
			FundAccountChart.deleteFundAccountChart(fundcode);

			out.println("ok");
		} */
	} catch (Exception e) {
		out.println("error");
	}

	%>
