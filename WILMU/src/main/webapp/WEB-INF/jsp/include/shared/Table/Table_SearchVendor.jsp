<%@ page contentType="text/html; charset=UTF-8" %>
	<%@ page import="com.wilmu.foundation.vendor.data.*, 
	java.util.List, com.ilmu.cataloging.Bibliography.*"%>
	<%
	String action = request.getParameter("action");
	String criteria = request.getParameter("criteria");
	
	if(action.equals("keyupVendor")){
		if(criteria!=""){
		List<GetFndVendor> vendorResult = GetFndVendor.searchVendorByCode(criteria);
		for(GetFndVendor i: vendorResult){
			out.println(i.getName());
		}
		}else{
			out.println("");
		}
	}else if(action.equals("keyupCtrlNo")){
		System.out.println(criteria);
		List<Bibliography> titleResult = Bibliography.CT_SearchByAccno(criteria, "");
		// System.out.println(titleResult.length); 
		if (titleResult.isEmpty()){
			out.println("Error");
		}else{
			for(Bibliography i: titleResult){
				System.out.println("Title" + i.getBufferno());
				out.println(i.getBufferno());
			}
		}
	}else{
	%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/Search_Modal.js"></script>		
	<table  style="cursor:pointer" class="table table-hover" data-toggle="table" id="vendor_result" data-click-to-select="true">
		<thead>
			<tr>
				<th data-sortable="true">Vendor Code</th>
				<th data-sortable="true">Vendor Name</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String searchType = request.getParameter("search_type");
		System.out.println(searchType + criteria);
		List<GetFndVendor> vendorResult = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}else
		{
			if(searchType.equals("vendorCode"))
				vendorResult = GetFndVendor.searchVendorByCode(criteria);
			else if (searchType.equals("vendorName"))
				vendorResult = GetFndVendor.searchVendorByName(criteria);
		%>
		<%
			for(GetFndVendor i: vendorResult){
		%>
	 
			<tr class="clickable-row1" data-value="<%= i.getVendCode() %>" id="vendor" >
				<td class="vendorCode"><%= i.getVendCode() %></td>
				<td class="vendorName"><%= i.getVendorName() %></td>
			</tr>
		<%
			}
		}
		%>
		</tbody>
	</table>
	<%
	}
	%>
