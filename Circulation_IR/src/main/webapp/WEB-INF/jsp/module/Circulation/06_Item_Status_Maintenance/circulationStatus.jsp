<%-- <%@page import="com.ilmu.cataloging.template.*"%> --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ilmu.circulation.Item_Status_Maintenance.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Selection List</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.16/css/jquery.dataTables.min.css" />

 
<script>
	var table = $('#circ').DataTable({
		//scrollx: true,
		responsive: true,
		scrollx: true,
		info: false,
		paging: false,
		search:false,
		order: [[ 2, "desc" ]]
	});

</script>
</head>
<body>
	<%
		String GL14PATR = request.getParameter("GL14PATR");
		System.out.println(GL14PATR + "jkklj");
		List<SQLStatusMaintenance> listcalldtl = SQLStatusMaintenance.getCIRCDETAILSPatr(GL14PATR); 
		for (SQLStatusMaintenance i: listcalldtl) {
		
	%>
      
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h4 class="modal-title">Circulation Details</h4>
	</div>
	
	<div class="container"></div>
        <div class="modal-body">
			<form>
			  <div class="form-group row">
			    <label for="patr" class="col-sm-2 col-form-label">Patron ID:</label>
			    <div class="col-sm-3">
			      <span class="patr"><%=i.getPATR14()%></span>
			    </div>
			    <label for="name" class="col-sm-2 col-form-label">Name:</label>
			    <div class="col-sm-5">
			      <span class="name"><%=i.getNAME()%></span>
			    </div>
			  </div>
			 
			  <div class="form-group row">
			    <label for="mdate" class="col-sm-2 col-form-label">Membership Date:</label>
			    <div class="col-sm-3">
			      <span class="mdate"><%=i.getMEMDATE()%></span>
			    </div>
			    <label for="edate" class="col-sm-2 col-form-label">Expiry Date:</label>
			    <div class="col-sm-3">
			      <span class="edate"><%=i.getEXPDATE()%></span> 
			     <%--  moment(<%=i.getEXPDATE()%>).format("DD/MM/YYYY") --%>
			    </div>
			  </div>
			   <%
				}
				List<SQLStatusMaintenance> listcallLL = SQLStatusMaintenance.getCIRCDETAILSLL(GL14PATR); 
				for (SQLStatusMaintenance j: listcallLL) {
			  %>
			  <div class="form-group row">
			    <label for="loan" class="col-sm-2 col-form-label">No. of items on loan:</label>
			    <div class="col-sm-3">
			      <span class="loan"><%=j.getLOAN()%></span>
			    </div>
			    <label for="lost" class="col-sm-2 col-form-label">No. of lost items:</label>
			    <div class="col-sm-3">
			      <span class="lost"><%=j.getLOST()%></span>
			    </div>
			  </div>
			  <%
			  	}
			  %>
			</form>
			  
			<table class="table table-responsive" id="circ">
				<thead>
           			<tr> 
           				<th>Accession No.</th>
           				<th>Title</th>
           				<th>Borrow Date</th>
           				<th>Due Date</th>
           				<th>Status</th>
           			</tr>
           			
           		</thead>
           		  <%
				  	List<SQLStatusMaintenance> listcallTable = SQLStatusMaintenance.getCIRCDETAILTABLE(GL14PATR); 
					for (SQLStatusMaintenance k: listcallTable) {
				  %>
				  <tr>
				  		<td><%=k.getDOCNO()%></td>
				  		<td><%=k.getTITLE()%></td>
				  		<td><%=k.getBDATE()%></td>
				  		<td><%=k.getDDATE03()%></td>
				  		<td><%=k.getDESC36()%></td>
				  </tr>
				  <%
				  	}
				  %>
           		<tbody>
           		</tbody>
			</table>
	</div>
	
	<div class="modal-footer">
		<input type="button" name="Close" value="Close" class="btn btn-info" data-dismiss="modal">
	</div>
						
</body>
</html>