<%@page import="com.kmlink.ilmu.mailBrowser.email.MailBrowser"%>

<%
	try {
		String mailNo = request.getParameter("mailNo");

		MailBrowser.deleteEmail(mailNo);
		out.println("ok");

	} catch (Exception e) {
		out.println("error");
	}
%>
