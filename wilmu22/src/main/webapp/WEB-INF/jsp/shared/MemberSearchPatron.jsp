	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@ page import="com.kmlink.ilmu.membershipRenewal.*, java.util.List"%>
	<%@ page import="com.kmlink.ilmu.membershipRenewal.Patron"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/membershipRenewal/validateMembershipRenewal.js"></script>

<%-- 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	 --%>
	
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/moment.min.js"></script> --%>

 <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">
	 									     
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">

</head>
<body>

	<table class="table table-hover table"  data-toggle="table" id="patr_result" data-click-to-select="true" >
	<%
		String action = request.getParameter("action");
		if(!action.equals("discharge")){
	%>
		<thead>
			<tr>
				<td>
					ID
				</td>
				<td>
					Name
				</td>
				<td>
					Action
				</td>
			</tr>
		</thead>
		<%
		}
		%>
		<tbody>
		<% 
		String searchType = request.getParameter("search_type");
		String criteria = request.getParameter("criteria");
		String cate_type = request.getParameter("cate_type");
		String cate_id = request.getParameter("cate_id");
		String sort = request.getParameter("sort");
		
		String patrid="";

		List<Patron> vendorResult = null;
		List<Patron> retrieveItem = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}else
		{
			if(action.equals("discharge")){
				vendorResult = Patron.retrievePatron(criteria, searchType, cate_id, sort);
			//	retrieveItem = Patron.retrieveDischarging(criteria, searchType, cate_id);
			}else{
				System.out.println("HEREEE");
				vendorResult = Patron.retrievePatron(criteria, searchType, cate_id, sort);
			}
		%>

	<%
			System.out.println(action + "kkjj");
			if(action.equals("discharge")){
				for(Patron i: vendorResult){
		%>
				<tr>
					<td colspan="2">
						<%= i.getGL66DESC() %>,<%= i.getGL65VALUE() %>
						<input type="hidden" class="GL14PATR" value="<%= i.getGL66DESC() %>">
					</td>
				</tr>
		<%
				patrid = i.getGL66DESC() ;
				}for(Patron i: retrieveItem){
		%>
				<tr>
					<td><%= i.getGL66DESC() %></td>
					<td><%= i.getGL65VALUE() %></td>
					<td><button title="" onclick="getDischargeItem('<%=patrid %>,<%= i.getGL66DESC() %>')" class="btn btn-sm btn-default" data-original-title="Select" data-dismiss="modal">
						<i class="fa fa-check"></i> Select
					</button></td>
				</tr>
		<%
				}
			}else{
		%>
		<%
			for(Patron i: vendorResult){
		%>
			<tr value="<%= i.getGL66DESC()%>" class="patrid" data-dismiss="modal">
				<td>
					<%= i.getGL66DESC() %>
					<input id="GL14PATR" type="hidden" value="<%= i.getGL66DESC()%>">
				</td>
				<td><%= i.getGL65VALUE() %></td>
				<td>
					<%
						if(action.equals("addResv")){
					%>
					<button title="" onclick="getResvDetails('<%= i.getGL66DESC()%>')" class="btn btn-sm btn-default" data-original-title="Select" data-dismiss="modal">
						<i class="fa fa-check"></i> Select
					</button>
					<%
						}else if(action.equals("charge")){
					%>
					<button title="" onclick="appendData('<%= i.getGL66DESC()%>')" class="btn btn-sm btn-default" data-original-title="Select" data-dismiss="modal">
						<i class="fa fa-check"></i> Select
					</button>
					<%
						}else{
					%>
					<button title="" onclick="appendData('<%= i.getGL66DESC()%>')" class="btn btn-sm btn-default" data-original-title="Select" data-dismiss="modal">
						<i class="fa fa-check"></i> Select
					</button>
					<%
						}
					%>
				</td>
			</tr>
		<%
			}
			}
		}
		%>
		</tbody>
	</table>
	
	</body>
</html>