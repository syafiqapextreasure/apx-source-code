<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ilmu.serial.InactiveIssueMgmt.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>


			<%
			List<Inactive_IssueMgmt> list = null;
		
				String date1 = request.getParameter("date1");
				String date2 = request.getParameter("date2");
				String criteria = request.getParameter("criteria");
				String ordernoFrom = request.getParameter("ordernoFrom");
				String ordernoTo = request.getParameter("ordernoTo");
				String ctrlnoFrom = request.getParameter("ctrlnoFrom");
				String ctrlTo = request.getParameter("ctrlTo");
	
				list = Inactive_IssueMgmt.LoadRecordset(date1, date2, criteria, ordernoFrom, ordernoTo, ctrlnoFrom,ctrlTo);
		
				for (Inactive_IssueMgmt i: list) {
			%>
		<tr>
			<td><input onchange='getResubmit()' class='spineChk' value="<%=i.getSE06ORDER() %>" type='checkbox' name = 'selectedValue[]'/></td>
			<td><%=i.getSE06ORDER() %></td>
			<td><%=i.getSE06CTRLNO() %></td>
			<td><%=i.getSE06COPIES() %></td>
			<td><%=i.getSE06VOL() %></td>
			<td><%=i.getSE06ISSULBL() %></td>
			<%-- <td><%=i.getAC03ORDATE() %></td> --%>
			<td><%=i.getSE06EXPDATE() %></td>
			<td><%=i.getSE06REMOVEDATE() %></td>
			<td style='display:none'><%=i.getSE06ISSUNO() %></td> 
		</tr>
			<%
				}
			%>