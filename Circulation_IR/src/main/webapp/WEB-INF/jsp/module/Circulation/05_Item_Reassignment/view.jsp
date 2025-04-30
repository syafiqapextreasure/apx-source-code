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
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Item_Reassignment.js"></script>	
	
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Release_For_CI.js"></script> --%>					

<script>
/* $('#accessionNo').DataTable({
    responsive: true,
}); */
	var table = $('#accessionNo').DataTable({
        "columnDefs": [
                       {
                           "targets": [ 7 ],
                           "visible": false,
                           "searchable": false
                       },
                       {
                           "targets": [ 8 ],
                           "visible": false,
                           "searchable": false
                       },
                       {
                           "targets": [ 9 ],
                           "visible": false,
                           "searchable": false
                       },
                        {
                           "targets": [ 10 ],
                           "visible": false,
                           "searchable": false
                       },
                       {
                           "targets": [ 11 ],
                           "visible": false,
                           "searchable": false
                       },
                       {
                           "targets": [ 12 ],
                           "visible": false,
                           "searchable": false
                       },
                       /* {
                           "targets": [ 3 ],
                           "visible": false
                       } */
                   ]
               } );
	var action2 = $("#action").val();
	/* $('tr td:nth-child(7)').hide(); */
	
	$('#accessionNo tbody').on( 'click', 'tr', function () {
		var data = table.row( this ).data();
		//alert(data[0]);
		$("#ACCNO").val(data[0]);
	    if ( $(this).hasClass('selected') ) {
	        $(this).removeClass('selected');
	    }
	    else {
	        table.$('tr.selected').removeClass('selected');
	        $(this).addClass('selected');
	    }
	    
	});

	$('#accessionNo tbody').on('dblclick', 'tr', function () {
		
		//if(action2 == 'category'){
			var data = table.row( this ).data();
	    	$("#GL14PATR").val(data[7]);
	    	$(".Name").text(data[8]);
	    	$('.PatronCategory').text(data[10]);
	    	$('.Status').text(data[9]);
	    	$('.GroupId').text(data[11]);
	    	$('.ExpiringDate').text(moment(data[12]).format("DD/MM/YYYY"));
	    	$("#AccessionNo").prop("readonly", false);
	    	$("#AccessionNo").val(data[0]); 
	    	// $("#GL14PATR").trigger("keypress",[9]); 
	    	/* $("#GL14PATR").focusout(function(){
	    		alert($(this).val().length>1);
	    			if($(this).val().length>1){
	    				alert("more");
	    			} 
	    	}); */
	    	setTimeout(function(){
	    		$("#AccessionNo").focus();
	    		 
	    		/*  $("#GL14PATR").keypress(function (e) {
	    			   var code = e.keyCode || e.which;
	    			    if(code == 9)
	    			       alert("Hurray!!!");
	    			    
	    		 });
	    		$("#GL14PATR").trigger("keypress",[9]);  */
	    		
	    		 //alert("out"); 
		    }, 1500);
	    	
	        $("#viewDetail .close").click();
	       	//alert($("#action").val());
	       	if($("#action").val() == 'branch'){
	       		$("#locaInfo, #locaInfo2").show();
	    		$("#itemInfo3, #reassign, #unassign").hide();
	    		$("#rStops").attr("disabled", true);
	    		$('#PLOCA13').prop('disabled', 'disabled');
	    		$("#PLOCA132").hide();
	       	}
	        $("#after").empty();
		//}
	    
	  	//alert( 'You clicked on '+data[0]+'\'s row' );
	    	/* var data = table.row( this ).data();
	    	alert(data);
	        $("#viewDetail .close").click(); */
    
	}); 
	
	$( ".close" ).click(function() {
		 $("#after").empty();
		 //$("#searchView").removeData(data);
		 //$('#viewDetail').removeData('bs.modal')
	});
	
	/////new Tab
	$('#accStatus').click(function() {
		var AccessionNo = $("#ACCNO").val();
		 var url = "/Circulation_IR/template?MODULE=Circulation/06_Item_Status_Maintenance&ACTION=Item_Status_Maintenance.jsp";
		// $("#AccessionNo").val(AccessionNo);
		 //alert($("#AccessionNo").val(AccessionNo) + " testing");
		 window.open(url+ '&AccessionNo='+ AccessionNo, '_blank');
		 $("#AccessionNo").val(AccessionNo);
		 
  	});
	

	if(action2 == "branch"){
		 $("#accessionNo thead tr th").eq(1).html('Original Location');
		 $("#accessionNo thead tr th").eq(2).html('Reassigned Location');
	}
</script>
</head>
<body>

   <%
     	String action = request.getParameter("action");
     %>
     
      <input type="hidden" id="action" value="<%=action%>">
      <input type="hidden" id="ACCNO" value="">
      
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
							<th>Original Item Cat</th>
							<th>Reassigned Item Cat</th>
							<th>Reassignment Starts</th>
							<th>Reassignment Stops</th>
							<th>Reassigned On</th>  
							<th>Reassigned By</th>
							<th>Patron ID</th>
							<th>Patron Name</th>
							<th>Status</th>
							<th>PatronCategory</th>
							<th>GroupId</th>
							<th>ExpiringDate</th>
						</tr>
					</thead>
					 <tbody style="overflow-y: scroll;height: 200px;">
					 
					 	<% 
					 		List<SQLAccessionNo> getANBUTTON = null;
					 		getANBUTTON = SQLAccessionNo.getAllAccBYButton(action);
					 		for (SQLAccessionNo i: getANBUTTON) {
					 			System.out.println(i.getNAMECREBY() + " ppppp");
						%>
							
							<tr>
								<td><%=i.getACCESSION07()%></td>
								<td><%=i.getDESSICAT()%></td>
								<td><%=i.getDESPICAT()%></td>
								<td><%=i.getDTSTART07()%></td>
								<td><%=i.getDTEND07()%></td>
								<td><%=i.getCRDATE()%></td>
								<td><%=i.getCREBY()%> , <%=i.getNAMECREBY()%></td>
								<td><%=i.getAUTHORISED07()%></td>
								<td><%=i.getNAMEAUTHORISED()%></td>
								<td><%=i.getDESC08()%></td>
								<td><%=i.getDESC07()%></td>
								<td><%=i.getNAME02()%></td>
								<td><%=i.getEXPDATE()%></td>
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
        	<button  id='accStatus' class="btn btn-primary"><span class="glyphicon glyphicon-list-alt"  title="Accession Status" style="color:white"></span></button>
        	<button  id='close' class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove"  title="Close" style="color:white"></span></button>
        	<!-- <button type="button" class="btn btn-info" id="btn_submit" onclick="updateAccRcrd()" data-dismiss="modal">
											OK
			</button>
			<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/> -->
			
        </div>
						
</body>
</html>