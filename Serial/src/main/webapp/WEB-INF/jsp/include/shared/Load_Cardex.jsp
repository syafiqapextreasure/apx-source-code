<%@ page import="com.ilmu.global.serial.IssuesPattern, java.util.List, com.ilmu.global.DateFormatter" %>
<%
String orderno = request.getParameter("orderno");
List<IssuesPattern> list = IssuesPattern.getCardexTable(orderno);

for(IssuesPattern i : list){
%>
<tr>
	<td><%=i.getSE06CPYNO() %></td>
	<td><%=i.getSE06VOLLBL() %></td>
	<td><%=i.getSE06ISSLBL() %></td>
	<td><%=i.getSE06EXPDATE() %></td>
</tr>
<%
}
%>