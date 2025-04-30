<%-- 	<%@ page import="com.ilmu.circulation.Charging.*, java.util.List"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css"> 
	
	<script>

	function getResvDetails(CI03PATR){
	  /*   var CI03PATR = $(this).attr('value'); */
	    
	    var CT03MATNO = $("#CT03MATNO").val();
		 var CI03DOCNO = "";
		
		 if(CT03MATNO.length!=0 && CT03MATNO!=null){
			 CI03DOCNO = $(".selectCT03MATNO").find(':selected').val();
		 }else{
			 CT03MATNO = "0";
			 CI03DOCNO = $("#CT03DOCNO").val();
		 }
		
		$.get("Handler_AddResv",{CI03PATR:CI03PATR, CI03DOCNO:CI03DOCNO, CI03MATNO:CT03MATNO},function(result){
			var message = (result.trim()).split(",");
				if(result.trim()=="ok"){
					setTimeout(function(){swal("Successful", "Reservation successfully added"); }, 1000);
					 
						var url = "List_Resv?CI03DOCNO="+CI03DOCNO + "&CI03MATNO=" + CT03MATNO;
						$.get(url,function(data)
								{
									$(".resv_list").html(data);
								});
				}else if(result.trim()=="050"){
					messageBox("050","","");
				}else if(result.trim()=="032"){
					messageBox("032","","");
				}else if(result.trim()=="060"){
					messageBox("060","","");
				}else if(result.trim()=="077"){
					messageBox("077","","");
				}else if(result.trim()=="078"){
					messageBox("078","","");
				}else if(result.trim()=="079"){
					messageBox("079","","");
				}else if(message[0]=="076"){
					messageBox("076",message[1].trim(), "@no");
				}else if(message[0].trim()=="075"){
					messageBox("075",message[1].trim(),"@id");
				}
		   });
	}
/* 	function getResvDetails(){
		var CI03PATR = $("#GL14PATR").val();
		var CI03DOCNO = $("#CT03DOCNO").val();
		$.get("Handler_AddResv",{CI03PATR:CI03PATR, CI03DOCNO:CI03DOCNO},function(result){
			
				if(result=="ok"){
					var url = "List_Resv?action=accession&CI03DOCNO="+CI03DOCNO;
					$.get(url,function(data)
							{
								$(".resv_list").html(data);
							});
				}
		   });
	} */
	/* $(document).ready(function() {

		$('#title_result').bootstrapTable();
		
		
	}); */
	$(document).ready(function(){
	    $('#vendor_result').DataTable();
	});
	</script>
	

	
	<table class="table table-hover table"  data-toggle="table" id="vendor_result" data-click-to-select="true" >
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
		<tbody>
		<% 
		String searchType = request.getParameter("search_type");
		String criteria = request.getParameter("criteria");
		String cate_type = request.getParameter("cate_type");
		String cate_id = request.getParameter("cate_id");

		List<Patron> vendorResult = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}else
		{

				vendorResult = Patron.retrievePatron(criteria, searchType, cate_id);
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
					<button title="" onclick="getResvDetails('<%= i.getGL66DESC()%>')" class="btn btn-sm btn-default" data-original-title="Select">
						<i class="fa fa-check"></i> Select
					</button>
				</td>
			</tr>
		<%
			}
		}
		%>
		</tbody>
	</table> --%>