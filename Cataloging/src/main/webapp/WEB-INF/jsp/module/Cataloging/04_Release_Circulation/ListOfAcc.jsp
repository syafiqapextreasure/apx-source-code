<%@page import="com.ilmu.cataloging.template.*"%>
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
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Release_For_CI.js"></script>					

<script>
$('#accList').DataTable({
    responsive: true
});


</script>
</head>
<body>
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title">Selection List</h4>
        </div><div class="container"></div>
        <div class="modal-body" id="accSlctList" >
							<form role="form" class="form-horizontal" onsubmit="return send_vendor_info()">
								<div class="form-group">
								<!-- 	<div class="col-sm-6 col-md-6">
										<label>Search By Text</label> 
										<input type="text" class="form-control" name="criteria" id="criteria" placeholder="Vendor Code">
									</div>
									<div class="col-sm-6 col-md-6">
										<label>Sort By</label> 
										<select class="form-control" id="search-type" name="search-type" onchange="updatePlaceholder()">
											<option value="vendorCode">Accession Number</option>
											<option value="vendorName">Item Category</option>
										</select>
									</div> -->
								</div>
								<div class="clearfix"></div>
								<div class="form-group" id="left">
										<div  class="col-lg-12" style="height:80%;overflow: scroll;">
										<table border="1" class="table table-condensed accList" id="accList" style="background-color:#ffffff;border-collapse:collapse;color:black;height:300px; ">
					<thead>
						<tr>
							<th>Accession No</th>
							<th>Item Category</th>
						</tr>
					</thead>
					 <tbody style="overflow-y: scroll;height: 200px;">
						<%
							SQLStatement eb = new SQLStatement();
							List<Cataloging> list = eb.getAllAcc();
							//List<Cataloging> list1 = eb1.chkExist();
						
							for (Cataloging e : list) {
						%>
						<tr class='accValues' value="<%=e.getDocNo()%>">
							<td><%=e.getDocNo()%></td>

							<td><%=e.getAccItemCat()%></td>
							
								
						</tr>
						<%
							}
						%>
													
					</tbody>
				</table>
					</div>
				</div>
				  <div id="accTitle">
				  </div>
						<!-- 		<div class="form-group">
									<div class="col-sm-4 col-md-4"></div>
									<div class="col-sm-8 col-md-8">
										<button type="button" class="btn btn-info" id="btn_submit" onclick="updateAccRcrd()" data-dismiss="modal">
											Release
										</button>
										<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
									</div>
								</div> -->
							</form>
							  </div>
        <div class="modal-footer">
        <button type="button" class="btn btn-info" id="btn_submit" onclick="updateAccRcrd()" data-dismiss="modal">
											OK
										</button>
										<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
        </div>
						
</body>
</html>