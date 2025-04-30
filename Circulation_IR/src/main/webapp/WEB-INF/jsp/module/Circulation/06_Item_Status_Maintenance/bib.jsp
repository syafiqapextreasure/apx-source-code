<%-- <%@page import="com.ilmu.cataloging.template.*"%> --%>
<%@page import="com.ilmu.circulation.Item_Status_Maintenance.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Selection List</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.16/css/jquery.dataTables.min.css" />
 
<script>


</script>
</head>
<body>
	<%
		String ControlNo = request.getParameter("ControlNo");
	%>
      
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<center></b><h4 class="modal-title">Linear Display for Control No : <%=ControlNo%></h4></center>
	</div>
	
	<div class="container"></div>
        <div class="modal-body" id="" >
			<form role="form" class="form-horizontal">  <!--onsubmit="return send_vendor_info()" -->
			
				<div class="clearfix"></div>
				<div class="form-group" id="left">
					<div  class="col-lg-12" style="height:50%;">
     					<table class="table table-striped">
     						<thead>
						      <tr>
						        <th>Description </th>
						        <th>Bibliographic Details</th>
						      </tr>
						    </thead>
						    <tbody>
						      <tr>
						        <td>INTERNATIONAL STANDARD BOOK NUMBER (ISBN)</td>
						        <td id="1"></td>
						      </tr>
						      <tr>
						        <td>LOCAL CALL NUMBER</td>
						        <td id="2"></td>
						      </tr>
						      <tr>
						        <td>MAIN ENTRY - PERSONAL NAME</td>
						        <td id="3"></td>
						      </tr>
						      <tr>
						        <td>TITLE AND STATEMENT OF RESPONSIBILITY AREA</td>
						        <td id="4"></td>
						      </tr>
						      <tr>
						        <td>PUBLICATION AND DISTRIBUTION AREA</td>
						        <td id="5"></td>
						      </tr>
						      <tr>
						        <td>PHYSICAL DESCRIPTION AREA</td>
						        <td id="6"></td>
						      </tr>
						      <tr>
						        <td>SERIES STATEMENT (UNTRACED OR TRACED DIFFERENTLY)</td>
						        <td id="7"></td>
						      </tr>
						      <tr>
						        <td>TOPICAL HEADINGS</td>
						        <td id="8"></td>
						      </tr>
						   </tbody>
     					</table>
					</div>
				</div>
			</form>
	</div>
	
	<div class="modal-footer">
        <!-- <button type="button" class="btn btn-info" id="btnOK" data-dismiss="modal">OK</button> -->
		<input type="button" name="Close" value="Close" class="btn btn-info" data-dismiss="modal">
	</div>
						
</body>
</html>