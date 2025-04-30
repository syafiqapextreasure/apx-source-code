<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.ilmu.acquisition.crddbtNote.CreditDebit"%>
<%@ page import="java.util.*, com.ilmu.global.*" %>

<html>
<head>
	<script>
////////////CRDDBT JSP DATATABLE
$(document).ready(function(){
	$('#cdNote').DataTable({
		responsive: true,
		scrollx: true,
		info: false,
		paging: true,
		ordering: false,
		searching:false,
		info: true,
		lengthChange: false,
	});
	
	
	/* var $table = $('#table');
    $('#modalTable').on('shown.bs.modal', function () {
        $table.DataTable('resetView');
    }); */
    
    
	
});

/* 	$(function () {
		
/* 		
		//var $table = $('#table');


		$('#modalTable').on('shown.bs.modal', function () {
			$('#cdNote2').DataTable('resetView');
		}); */

      

</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/delete.js"></script>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/acquisition/crddbtNote.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/acquisition/test.js"></script> --%>
</head>
<body>
	<table id="cdNote" class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>Note No </th>
				<th>Vendor </th>
				<th>Note Type </th>
				<th>Status </th>
				<th>Received Date </th>
				<th>Invoice Number </th>
				<th>Used Date </th>
				<th>Currency </th>
				<th>Foreign Amount </th>
				<th>Local Amount </th>
				<th>Action </th>
			</tr>
		</thead>
		<tbody>
			<% 
				String searchType = request.getParameter("searchType");
				System.out.println("searchType = " +searchType);
				String criteria = request.getParameter("criteria");
				System.out.println("criteria = " +criteria);
				
				List<CreditDebit> searchResult = null;
				if(criteria == null)
				{
					out.println();
				}
				else
				{
				if(searchType.equals("vendorCode")){
					searchResult = CreditDebit.searchByVendorCode(criteria);
				}else if(searchType.equals("noteNo")){
					searchResult = CreditDebit.searchByNoteNo(criteria);
				}else if(searchType.equals("receivedDate")){
					searchResult = CreditDebit.searchByReceivedDate(criteria);
				}else if(searchType.equals("usedDate")) {
					searchResult = CreditDebit.searchByUsedDate(criteria);
				}
				
				for(CreditDebit getSearch: searchResult){
			%>
				<tr>
					<td><%=getSearch.getNoteNo()%></td>
					<td><%=getSearch.getVendor()%></td>
					<td><%=getSearch.getNoteType()%></td>
					<td id="Status"><%=getSearch.getStatus()%></td>
					<td><%=getSearch.getReceivedDate()%></td> 
					<td><%=getSearch.getInvoiceNumber()%></td>
					<td><%=getSearch.getUsedDate()%></td>
					<td><%=getSearch.getCurrency()%></td>
					<td><%=getSearch.getForeignAmount()%></td>
					<td><%=getSearch.getLocalAmount()%></td>
					
					<%-- 
					%> --%>
					<td class="last">
						<!-- <input type="hidden" value="A" name="fndcategories"/> -->
						<button id="View" class="btn btn-primary btn-xs" data-toggle='modal' data-target="#modalAdd" data-whatever="View Record" title="View Credit/Debit Note" data-rowid="<%=getSearch.getNoteNo()%>"><span class="glyphicon glyphicon-eye-open"></span></button>
						<button id="Edit" class="btn btn-info btn-xs" data-toggle='modal' data-target="#modalAdd" data-whatever="Edit Record" title="Edit Credit/Debit Note" data-rowid="<%=getSearch.getNoteNo()%>"><span class="glyphicon glyphicon-pencil"></span></button>
		   				<button id="Delete" class="btn btn-dull btn-xs DeleteCD" data-NoteNo="<%=getSearch.getNoteNo()%>" data-Status="<%=getSearch.getStatus()%>"><span class="glyphicon glyphicon-trash" title="Delete Credit/Debit Note" ></span></button>
   					</td>
				</tr>
				<%
					} 
					}
				%> 
			</tbody>
	</table>
	
	
</body>
</html>