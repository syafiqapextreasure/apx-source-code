<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ilmu.serial.Renewal.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<%
/* String action = request.getParameter("action");
String criteria = request.getParameter("criteria");
String vendor = request.getParameter("vendor");
String value = request.getParameter("value");
String value1 = request.getParameter("value1");
List<Ordering> ordering = null;


if(action.equals("vendor")){
	ordering = Ordering.getOrderingList("AC03VEND", criteria, vendor, value, value1);
}
 */
//for(Ordering i : ordering){
%>

<%-- <tr>
	<td><input type = "checkbox" class="checkAll" id = "checkAll"></td>
	<td><%=i.getAC03ORDER()%></td>
	<td><%=i.getAC03CRDATE()%></td>
	<td><%=i.getISBN()%></td>
	<td><%=i.getTITLE()%></td>
	<td><%=i.getQuantity()%></td>
	<td><%=i.getAC03FOREX() %> <%=i.getAC03FPRICE() %></td>
	<td><%=i.getAC03FOREX() %> <%=i.getTotal() %></td>
	<td><%=i.getLocalTotal() %></td>
	
</tr> --%>

<%
//}
%>

<%
String criteria = request.getParameter("criteria");
String vendor = request.getParameter("vendor");
String value = request.getParameter("value");
String value1 = request.getParameter("value1");
String ordermode = request.getParameter("ordermode");
List<Renewal> renewal = Renewal.LoadRecordset(criteria,  vendor, value, value1, ordermode);

for(Renewal i : renewal){
	System.out.println("Renewal" + i.getSE07ORDER());
	System.out.println("Order" + i.getSE07ORDER());
	
%>

<tr>
<td>
		<%
		if(i.getSE07STATUS().equals("44")){
		%>
		<input onchange="getValue()" name= "orderlist" type = "checkbox" class="orderlist" id = "orderlist" value="<%=i.getSE07ORDER()%>*" data-total="<%=i.getTotal() %>">
		<%
		}else{
		%>
		<input onchange="getValue()" name= "orderlist" type = "checkbox" class="orderlist" id = "orderlist" value="<%=i.getSE07ORDER()%>" data-total="<%=i.getTotal() %>">
		<%
		}
		%>
	</td> 
	<td>
		<%=i.getSE07ORDER()%>
		
		<%
		if(i.getSE07STATUS().equals("44")){
		%>
		<span style="color:red">*</span>
		<%
		}
		%>
		<%-- <%
		if(i.getSE03STATUS().equals("40")){
		%>
		<span style="color:red">*</span>
		<%
		}
		%> --%>
	</td>
	
	<%-- <td><%=i.getSE07CRDATE()%></td> --%>
	<%
		if(i.getISBN()==null){
	%>
	<td></td>
	<%
		}else{
	%>
	<td><%=i.getISBN()%></td>
	<%
		}
	%>
	<td><%=i.getTITLE()%></td>
	<td><%=i.getQuantity()%></td>
	<td><%=i.getSE07FOREX() %> <%=i.getSE07FPRICE() %></td>
	<td><%=i.getSE07FOREX() %> <%=i.getTotal() %></td>
	<td><%=i.getSE07LPRICE() %></td>
	<td></td>
	
</tr> 

<%
}
%>