	<%@ page import="com.ilmu.foundation.global.*, java.util.List"%>

	
	<script>
	// Code making table row clickable
	// Have to place here otherwise the code won't work
	$(document).ready(function() {

		$('#title_result').bootstrapTable();
		
		$('.clickable-row').click(function() {
			var controlNoInput = $(this).find(".controlNoInput").attr('data-value');
			// Hide the modal
			$('#titleSearch').modal('hide');
			$("#result").collapse("show");
			//replaceLoader("#div-result");
			$.get("jsp/include/sharedV1/Table_CTResultMaster.jsp",{controlNoInput : controlNoInput},function(data){
				$("#div-result").html(data);
				$("#txt-searchAll").val("no");
			});	
		});
	});
	</script>
	

	
	<table class="table table-hover"  data-toggle="table" id="vendor_result" data-click-to-select="true" >
		<thead>
			<tr>
				<th data-sortable="true">Title</th>
				<th data-sortable="true">Control Number</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String searchType = request.getParameter("search_type");
		String criteria = request.getParameter("criteria");
		String cate_type = request.getParameter("cate_type");
		String cate_id = request.getParameter("cate_id");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("stopDate");
		List<PatronSearch> vendorResult = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}else
		{

				vendorResult = PatronSearch.CT_getListByDate(startDate, endDate,criteria);
		%>
		<%
			for(PatronSearch i: vendorResult){
		%>
	 
			<tr class="clickable-row" data-dismiss="modal" data-value="<%= i.getId()%>">
				<td><%= i.getCT05SRAW() %></td>
				<td class="controlNoInput" data-value="<%= i.getCT02MATNO() %>" data-dismiss="modal"><%= i.getCT02MATNO() %></td>
			</tr>
		<%
			}
		}
		%>
		</tbody>
	</table>