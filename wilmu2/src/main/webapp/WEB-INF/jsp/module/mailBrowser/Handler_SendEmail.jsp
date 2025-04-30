<%@page import="com.kmlink.ilmu.mailBrowser.email.MailBrowser"%>

<%
	try {

		/* 		java.util.Enumeration keys = session.getAttributeNames(); */
		{

			System.out.println("send email");

			String mailNo = request.getParameter("mailNo");
			String receiverEmail = request.getParameter("email");

			String senderUsername = "library@unisza.edu.my";

			MailBrowser.sendEmail(Integer.parseInt(mailNo), receiverEmail);

			out.println("ok");

		}

	} catch (Exception e) {
		out.println("error");
	}
%>
