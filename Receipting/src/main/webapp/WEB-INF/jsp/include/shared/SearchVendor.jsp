
	<%@ page import="com.ilmu.global.Vendor, java.util.List, com.ilmu.cataloging.Bibliography.*"%>
	
	<%
	String action = request.getParameter("action");
	String criteria = request.getParameter("criteria");
	
	if(action.equals("keyupVendor")){
		if(criteria!=""){
		List<Vendor> vendorResult = Vendor.searchVendorByCode(criteria);
		for(Vendor i: vendorResult){
			out.println(i.getName());
		}
		}else{
			out.println("");
		}
	}else if(action.equals("keyupCtrlNo")){
		System.out.println(criteria);
		List<Bibliography> titleResult = Bibliography.CT_SearchByAccno(criteria);
		// System.out.println(titleResult.length); 
		if (titleResult.isEmpty()){
			out.println("Error");
		}else{
			for(Bibliography i: titleResult){
				System.out.println("Title" + i.getTitle());
				out.println(i.getTitle());
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
	 
			<tr class="clickable-row1" data-value="<%= i.getCode() %>" id="vendor" data-dismiss="modal">
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