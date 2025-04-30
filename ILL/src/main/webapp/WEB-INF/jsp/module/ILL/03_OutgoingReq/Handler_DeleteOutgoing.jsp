<%@page import="com.ilmu.ill.outgoingreq.OutgoingReq, com.ilmu.global.RecordTransaction "%>

	<%
	try {
		String refno = request.getParameter("refno");
		String loginid = request.getParameter("loginid");
		
		OutgoingReq.DeleteCurrentRecord(refno);
		
		String gsModule = "CI";
		RecordTransaction.InsertAudit(gsModule, "CID0005", refno, loginid); 

		out.println("ok");

	} catch (Exception e) {
		out.println("error");
	}
	%>
