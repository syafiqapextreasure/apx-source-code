<%@ page contentType="text/html; charset=UTF-8" %>
	<%@ page import="com.ilmu.global.Vendor, java.util.List, com.ilmu.cataloging.Bibliography.*"%>
	
	<%
	String action = request.getParameter("action");
	String criteria = request.getParameter("criteria");
	String type = request.getParameter("type");
	
	System.out.println("typetypetype" +type);
	
	if(action.equals("keyupVendor")){
		if(criteria!=""){
		List<Vendor> vendorResult = null;
		if(type.equals("vendor")){
			vendorResult = Vendor.searchVendorByCode(criteria);
		}else if(type.equals("binder")){
			vendorResult = Vendor.searchBinderByCode(criteria);
		}else if(type.equals("publisher")){
			vendorResult = Vendor.searchPublisherByCode(criteria);
		}
		for(Vendor i: vendorResult){
			out.println(i.getName());
		}
		}else{
			out.println("");
		}
	}else if(action.equals("keyupVendorType")){
		if(criteria!=""){
			List<Vendor> vendorResult = null;
			if(type.equals("vendor")){
				vendorResult = Vendor.searchVendorByCode2(criteria);
			}else if(type.equals("binder")){
				vendorResult = Vendor.searchBinderByCode(criteria);
			}else if(type.equals("publisher")){
				vendorResult = Vendor.searchPublisherByCode(criteria);
			}
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
				<th></th>
			</tr>
		</thead>
		<tbody>
		<% 
		String searchType = request.getParameter("search_type");
		System.out.println("99999"+searchType + criteria);
		List<Vendor> vendorResult = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}else
		{
			if(type.equals("vendor")){
				if(searchType.equals("vendorCode"))
					vendorResult = Vendor.searchVendorByCode(criteria);
				else if (searchType.equals("vendorName"))
					vendorResult = Vendor.searchVendorByName(criteria);
			}else if(type.equals("binder")){
				if(searchType.equals("vendorCode"))
					vendorResult = Vendor.searchBinderByCode(criteria);
				else if (searchType.equals("vendorName"))
					vendorResult = Vendor.searchBinderByName(criteria);
			}else if(type.equals("publisher")){
				if(searchType.equals("vendorCode"))
					vendorResult = Vendor.searchPublisherByCode(criteria);
				else if (searchType.equals("vendorName"))
					vendorResult = Vendor.searchPublisherByName(criteria);
			}
			
		
		%>
		<%
			for(Vendor i: vendorResult){
		%>
	 
			<tr class="clickable-row1" data-value="<%= i.getCode() %>" id="vendor" data-dismiss="modal">
				<td class="vendorCode"><input type="hidden" class="type" value="<%=type%>"><%= i.getCode() %></td>
				<td class="vendorName"><%= i.getName() %></td>
				<td>
					<button title="" class="btn btn-sm btn-default clickable-row1" data-value="<%= i.getCode() %>" data-original-title="Select" data-dismiss="modal">
						<i class="fa fa-check"></i> Select
					</button>
				</td>
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