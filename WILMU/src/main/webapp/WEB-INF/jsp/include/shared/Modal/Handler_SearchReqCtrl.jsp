<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.wilmu.global.*, java.util.List.*,java.util.*, com.wilmu.acquisition.requestctrl.data.*"%>
	

<html>
	<head>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/delete.js"></script>
		<!-- <script>
			$(document).ready(function(){
				
			});
		</script> -->
	</head>
	<body>
		<table id="reqCtrl" class="table table-bordered table-striped" style="width:100%">
			<col width="10%">
						<col width="15%">
						<col width="10%">
						<col width="20%">
						<col width="10%">
						<col width="5%">
						<col width="15%">
			<thead>
				<tr>
					<th>Request No</th>
					<th>Requestor</th>
					<th>ISBN</th>
					<th>Title</th>
					<th>Date Request</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<% 
					String getChecked = request.getParameter("getChecked");
					String getCriteria = request.getParameter("getCriteria");
					String inputStartDate = request.getParameter("inputStartDate");
					
					if(inputStartDate.equals("Invalid date")){
						inputStartDate = "";
					}
					
					String inputEndDate = request.getParameter("inputEndDate");
					
					if(inputEndDate.equals("Invalid date")){
						inputEndDate = "";
					}
					String stausYN = request.getParameter("stausYN");
					String searchstatus = request.getParameter("searchstatus");
					String deptYN = request.getParameter("deptYN");
					String searchdept = request.getParameter("searchdept");
					String branchYN = request.getParameter("branchYN");
					String searchbranch = request.getParameter("searchbranch");
					
					List<GetSearchRequestCtrl> searchreqCTRL = null;
					
					searchreqCTRL = GetSearchRequestCtrl.getforReqCtrl(getChecked, getCriteria, inputStartDate, 
							inputEndDate, stausYN, searchstatus, deptYN, searchdept, branchYN, searchbranch);
					for(GetSearchRequestCtrl reqCtrl: searchreqCTRL){  
				%>
				<tr>
					<td><%=reqCtrl.getreqno()%></td>
					<td><%=reqCtrl.getrequestor()%></td>
					<td><%=reqCtrl.getisbn()%></td>
					<td><%=reqCtrl.getTitle()%></td>
					<td><%=reqCtrl.getdatereq()%></td>
					<td><%=reqCtrl.getstatus()%></td>
					<td class="last">
						<button id="View" class="btn btn-primary btn-sm" data-toggle='modal' data-target="#modalreqCtrl" data-mode="3" title="Views Request Control" data-rowid="<%=reqCtrl.getreqno()%>"><span class="glyphicon glyphicon-eye-open"></span></button>
						<button id="Edit" class="btn btn-info btn-sm" data-toggle='modal' data-target="#modalreqCtrl" data-mode="2" title="Edits Request Control" data-rowid="<%=reqCtrl.getreqno()%>"><span class="glyphicon glyphicon-pencil"></span></button>
		   				<button id="Delete" class="btn btn-dull btn-sm delete" data-reqno="<%=reqCtrl.getreqno()%>" data-Status="<%=reqCtrl.getstatus()%>"><span class="glyphicon glyphicon-trash" title="Delete Request Control"></span></button>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
			
	</body>
</html>


