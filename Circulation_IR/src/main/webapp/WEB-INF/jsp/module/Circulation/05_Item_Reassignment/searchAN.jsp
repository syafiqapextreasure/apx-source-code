<%-- <%@page import="com.ilmu.cataloging.template.*"%> --%>
<%@page import="com.ilmu.circulation.Item_Reassignment.*"%>
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
	
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Release_For_CI.js"></script> --%>					

<script>
/* $('#accessionNo').DataTable({
    responsive: true,
}); */
var table = $('#accessionNo').DataTable();

$('#accessionNo tbody').on( 'click', 'tr', function () {
    if ( $(this).hasClass('selected') ) {
        $(this).removeClass('selected');
    }
    else {
        table.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
} );

 $('#accessionNo tbody').on('click', 'tr', function () {
    var data = table.row( this ).data();
    //alert( 'You clicked on '+data[0]+'\'s row' );
    $("#showModelSearchAN .close").click();
    ///alert(data[0]);
    $("#AccessionNo").val(data[0]);
    $("#AccessionNo").focus();
   // alert("out");
   // $("#CT03DOCNO").trigger("focusout");
    //$("#CT03DOCNO").trigger( 'keypress', [{preventDefault:function(){},keyCode:9}] );
    ///$("#CT03DOCNO").next('.input-group-addon').focus();
    //System.out.println("yeahhh");
} ); 

</script>
</head>
<body>
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title">Selection List</h4>
        </div><div class="container"></div>
        <div class="modal-body" id="accSlctList" >
							<form role="form" class="form-horizontal">  <!--onsubmit="return send_vendor_info()" -->
								<div class="form-group">
								</div>
								<div class="clearfix"></div>
								<div class="form-group" id="left">
										<div  class="col-lg-12" style="height:80%;overflow: scroll;">
										<table border="1" class="table table-condensed accessionNo" id="accessionNo" style="background-color:#ffffff;border-collapse:collapse;color:black;height:300px; ">
					<thead>
						<tr>
							<th>Accession No</th>
							<th>Control No</th>
							<th>Item Category</th>
							<th>Status</th>
						</tr>
					</thead>
					 <tbody style="overflow-y: scroll;height: 200px;">
						<%
							/* SQLAccessionNo eb = new SQLAccessionNo();
							List<SQLAccessionNo> list = eb.getAllAcc();
							//List<Cataloging> list1 = eb1.chkExist();
						
							for (SQLAccessionNo e : list) { */
							
							List<SQLAccessionNo> getAllAcc = null;
							getAllAcc = SQLAccessionNo.getAllAcc();
							for (SQLAccessionNo i: getAllAcc) {
						%>
							<tr class='accValues' value="<%=i.getDOCNO()%>">
							<!-- <tr> -->
								<td><%=i.getDOCNO()%></td>
								<td><%=i.getMATNO()%></td>	
								<td><%=i.getDESC10()%></td>	
								<td><%=i.getDESC36()%></td>	
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