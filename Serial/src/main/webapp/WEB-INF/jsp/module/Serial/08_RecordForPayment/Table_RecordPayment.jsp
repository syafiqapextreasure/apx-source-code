<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ilmu.serial.RecordForPayment.*, com.ilmu.global.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>


<%
String criteria = request.getParameter("criteria");
String textValue = request.getParameter("textValue");
List<RecordForPayment> ordering = RecordForPayment.GetOrderNo(criteria, textValue);
double totalLPrice = 0.00;
double totalFPrice = 0.00;
int count = 1;
for(RecordForPayment i : ordering){
	totalLPrice = totalLPrice+Double.parseDouble(i.getSE07AMT());
	totalFPrice = totalFPrice+Double.parseDouble(i.getSE07FPRICE());
%>

<tr class="record">

	<td><%=i.getSE07SUPPLIER()%></td>
	<td><%=i.getSE07REFERNO()%></td>
	<td><%=i.getSE07INVNO()%></td>
	<td><%=Handler.decimalConversion(String.valueOf(i.getSE07AMT())) %></td>
	<td class="foreignPrice"><%=Handler.decimalConversion(String.valueOf(i.getSE07AMT())) %></td>
	<td class="currency"><%=i.getSE07FOREX()%></td>
	<td class="voucherno"></td>
	<td class="voucherdate"></td>
	<td class="chequeno"></td>
	<td class="chequedate"></td>
<%
	}
%>
	
</tr> 
<%-- 
<tr>

	<td colspan="7" style="text-align:right"><label>Total</label></td>

	<td><label><%=Handler.decimalConversion(String.valueOf(totalLPrice))%></label></td>
	<td class="foreignPrice" ><label><%=Handler.decimalConversion(String.valueOf(totalFPrice)) %></label></td>

</tr> --%>