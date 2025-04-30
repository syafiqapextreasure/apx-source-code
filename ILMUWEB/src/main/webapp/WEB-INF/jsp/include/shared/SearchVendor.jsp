	<!-- Java class-->
	<%@ page import="com.ilmu.global.Vendor, java.util.List"%>
	
	<!-- <script src="lib/bootstrap-table/bootstrap-table.js" type="text/javascript"></script> -->
	<link rel="stylesheet" href="lib/bootstrap-table/bootstrap-table.css" type="text/css"></link>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script>
$(document).ready(function(){
	$('.clickable-row').click(function() {
		// Hide the modal
	/* 
		$('#titleSearch').modal('hide');
		$('#vendorModal').modal('hide'); */

	document.getElementById('vendor').value = $(this).attr('data-value');
	//document.getElementById('CT03VEND').value = $(this).attr('data-value');
	document.getElementById('div-vendorName').innerHTML = $(this).find(".vendorName").text();
		
	});
});
</script>
	<script>
	// Code making table row clickable
	// Have to place here otherwise the code won't work
	$(document).ready(function() {
		
		$('#vendor_result').bootstrapTable();
		
		$('.clickable-row').click(function() {
			// Hide the modal
			//$('#vendorSearch').modal('hide');
			//$('#vendorModal').modal('hide');
			
			// Replace value
			//if($("#vendor").length>0)
			
		/* 		if(!(document.getElementById('vendor') === null)){
				document.getElementById('vendor').value = $(this).attr('data-value');
				}
			var tester = $(this).find(".vendorName").text();
			alert(tester);
				document.getElementById('div-vendorName').value = $(this).find(".vendorName").text();
			 */
				
			//else
				//document.getElementById('vendorCodeInput').value = $(this).data('value');
			// Set focus
// 			setTimeout(function() {
 				document.getElementById('vendor').focus()
// 				document.getElementById('vendorCodeInput').focus()
// 			}, 600);
			
			
		});
	});
	</script>
	
	<table class="table table-hover" data-toggle="table" id="vendor_result" data-click-to-select="true">
		<thead>
			<tr>
				<th data-sortable="true">Vendor Code</th>
				<th data-sortable="true">Vendor Name</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String searchType = request.getParameter("search_type");
		String criteria = request.getParameter("criteria");
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
	 
<%-- 			<tr class="clickable-row" data-value="<%= i.getVendorCode() %>"> --%>
<%-- 				<td><%= i.getVendorCode() %></td> --%>
<%-- 				<td><%= i.getVendorName() %></td> --%>
<!-- 			</tr> -->
		 
			
			<tr class="clickable-row" data-value="<%= i.getCode()%>">
				<td class="vendorCode"><%= i.getCode() %></td>
				<td class="vendorName"><%= i.getName() %></td>
			</tr>
		<%
			}
		}
		%>
		</tbody>
	</table>