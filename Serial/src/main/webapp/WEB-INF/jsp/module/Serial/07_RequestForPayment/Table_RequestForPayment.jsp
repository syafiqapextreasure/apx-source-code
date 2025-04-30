<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ilmu.serial.RequestForPayment.*, com.ilmu.global.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>


<%
String criteria = request.getParameter("criteria");
String textValue = request.getParameter("textValue");
String groupList = request.getParameter("groupList");
List<RequestForPayment> ordering = RequestForPayment.LoadTransactions(criteria, textValue, groupList);
System.out.println("Test" + ordering);
double totalLPrice = 0.00;
double totalFPrice = 0.00;
int count = 1;

if(!ordering.isEmpty()){
for(RequestForPayment i : ordering){
	totalLPrice = totalLPrice+Double.parseDouble(i.getSE07AMT());
%>

<tr class="record">

<%
	if(groupList.equals("Y")){
		totalFPrice = totalFPrice+Double.parseDouble(i.getSE07FPRICE());
%>
	<td><%=count++%></td>
	<td class="print"><%=i.getSE07DESC()%></td>
	<td><%=i.getSE07SUPPLIER()%></td>
	<td><%=i.getSE07INVNO()%></td>
	<td><%=Handler.decimalConversion(String.valueOf(i.getSE07AMT())) %></td>
	<td class="foreignPrice"><%=Handler.decimalConversion(String.valueOf(i.getSE07FPRICE())) %></td> 
	<td class="currency"><%=i.getSE07FOREX() %></td> 
	<td class="rate"><%=i.getSE07PRATE() %></td> 
<%
	}else{
		double foreignPrice = 0.00;
		String title = "";
				if((i.getSE07ORDER()).startsWith("DIS")||(i.getSE07ORDER()).startsWith("HC")||
						(i.getSE07ORDER()).startsWith("SC")){
					System.out.println("Order");
					foreignPrice = Double.parseDouble(i.getSE07FPRICE()) ;
					title = "";
				}else{
					foreignPrice = Double.parseDouble(i.getSE07FPRICE()) * Integer.parseInt(i.getSE07COPY());
					title = i.getSE07DESC();
				}
				
					totalFPrice = totalFPrice+foreignPrice;

%>
	<td><%=i.getSE07SUPPLIER()%></td>
	<td><%=i.getSE07PONO()%></td>
	<td><%=i.getSE07ORDER()%></td>
	<td><%=title%></td>
	<td><%=i.getSE07INVNO()%></td>
	<td><%=Handler.decimalConversion(String.valueOf(i.getSE07AMT())) %></td>
	<td class="foreignPrice"><%=Handler.decimalConversion(String.valueOf(foreignPrice)) %></td>
	<td class="currency"><%=i.getSE07FOREX() %></td>
	<td class="rate"><%=i.getSE07PRATE() %></td> 
<%
	}
%>
	
</tr> 

<%
}
%>
<tr>
<%
	if(groupList.equals("Y")){
%>
	<td colspan="4" style="text-align:right"><label>Total</label></td>
<%
	}else{
%>
	<td colspan="5" style="text-align:right"><label>Total</label></td>
<%
	}
%>
	<td><label><%=Handler.decimalConversion(String.valueOf(totalLPrice))%></label></td>
	

	<td colspan="2" class="foreignPrice"></td>
	<%-- <td class="foreignPrice" colspan="2"><label><%=Handler.decimalConversion(String.valueOf(totalFPrice)) %></label></td> --%>
</tr>
<%
}else{
%>
<tr><td colspan="6">No record displayed</td></tr>
<%
}
%>