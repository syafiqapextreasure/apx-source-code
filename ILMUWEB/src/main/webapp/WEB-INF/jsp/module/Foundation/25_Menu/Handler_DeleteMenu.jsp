<%@page import="com.ilmu.foundation.Menu.Menu "%>

	<%
		try {
			String module = request.getParameter("module");
			String name = request.getParameter("name");
			String idx = request.getParameter("idx");

				Menu.deleteMenu(module, name, idx);
				out.println("ok");

		} catch (Exception e) {
			out.println("error");
		}

	%>
