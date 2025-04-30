
	<%@ page import="com.ilmu.global.Vendor, java.util.List, com.ilmu.cataloging.Bibliography.*"%>

	<%
	String action = request.getParameter("action");
	String criteria = request.getParameter("criteria");
	if(action.equals("keyupVendor")){
		System.out.println(criteria);
		List<Vendor> vendorResult = Vendor.searchVendorByCode(criteria);
		for(Vendor i: vendorResult){
			out.println(i.getName());
		}
	}else if(action.equals("keyupCtrlNo")){
		System.out.println(criteria);
		List<Bibliography> titleResult = Bibliography.CT_SearchByAccno(criteria);
		for(Bibliography i: titleResult){

	out.println(i.getTitle());
		}
	}else{
	%>
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
		List<Vendor> vendorResult = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}else
		{
			if(searchType.equals("vendorCode"))
				vendorResult = Vendor.searchVendorByCode(criteria);
			else if (searchType.equals("vendorName"))
				vendorResult = Vendor.searchVendorByName(criteria);
		%>
		<%
			for(Vendor i: vendorResult){
		%>
	 
			<tr class="clickable-row1" data-value="<%= i.getCode() %>">
				<td class="vendorCode"><%= i.getCode() %></td>
				<td class="vendorName"><%= i.getName() %></td>
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