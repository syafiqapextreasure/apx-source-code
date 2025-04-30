<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList, com.wilmu.foundation.global.Vendor" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/vendorSearch.js"></script>	

<% 
String searchCriteria = request.getParameter("searchCriteria");
System.out.println(searchCriteria + " searchCriteria");

String criteria = request.getParameter("criteria");
System.out.println(criteria + " criteria");

String action = request.getParameter("action");
System.out.println(action + " action");

List<Vendor> vendorList = new ArrayList<Vendor>();
//List<Vendor> searchVendorByName = null;

		if(searchCriteria.equals("vendorCode") && criteria != null) {
			vendorList = Vendor.searchListByVendorCode(criteria, action);
			System.out.println("pass vendorCode");
		}else if(searchCriteria.equals("vendorName") && criteria != null) {
			vendorList = Vendor.searchListByVendorName(criteria, action);
			System.out.println("pass vendorName");
		}
%>

<%
	if(vendorList.size() == 0) {
		out.print("No results is found based on search criteria!");
	} else {
%>
<table class="table table-hover" id="tableVendor">
	<thead>
		<tr>
			<th data-sortable="true">Vendor Code</th>
			<th data-sortable="true">Vendor Name</th>
			<th data-sortable="true">ACTION</th>
		</tr>
	</thead>
	<% 
		for(Vendor v : vendorList) {
	%>
		<tr class="clickable-row" data-value="<%= v.getVendCode() %>" data-value2="<%= v.getVendorName() %>">
			<td><%= v.getVendCode() %></td>
			<td><%= v.getVendorName() %></td>
			<td><button type="button"  id="btnSelect" class="btnSelectVend"  data-value="<%=v.getVendCode()%>"
			data-value2="<%= v.getVendorName() %>"
			class="btn btn-sm btn-default" data-original-title="Select">
			<i class="fa fa-check"></i> Select</button></td>
		</tr>
	<%
		}
	%>
</table>
<%
	}

%>


