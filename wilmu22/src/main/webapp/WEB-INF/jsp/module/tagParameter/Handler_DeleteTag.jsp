<%@page import="com.kmlink.ilmu.tagParameter.TagParameter"%>

	<%
	try {
		String tagNo = request.getParameter("TagNo");
		
		TagParameter.deleteTag(tagNo);
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
