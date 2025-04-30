<%@ page import="com.ilmu.receipting.Receipting.*, java.util.List, com.ilmu.global.*" %>

	<!-- Bootstrap table -->
 	 <script type="text/javascript" src="<%= request.getContextPath() %>/js/Receipting/Receipting.js"></script>	
	<script>
	$('#searchbyaccno').DataTable({
	    responsive: true
	});
	
	$("#checkAll").change(function(){
		 var rows = $('#searchbyaccno').DataTable().rows({ 'search': 'applied' }).nodes();
	
				 
				 if(!this.checked){
			    	   $('input[type="checkbox"]', rows).prop('checked', false);
			    	   $("#selectedTransaction").html("")
			    	   calculateTotal();
			       }else{
			    	   $('input[type="checkbox"]', rows).prop('checked', this.checked);
			       }
				 
				 $('#searchbyaccno').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
					 var trxn =  $(this).closest('tr').find('td:eq(0)').text();
						var code =  $(this).closest('tr').find('td:eq(2)').text();
						var due =  $(this).closest('tr').find('td:eq(6)').text();
						var distribution =  $(this).closest('tr').find('td:eq(6)').text();
						var docno =  $(this).closest('tr').find('td:eq(5)').text().trim();
						
						$("#selectedTransaction").html("");
							$.get("DistributionModal",{action:"AddSelectedTrxn", trxn:trxn, due:due,distribution:distribution,code:code,docno:docno},function(data){
								$("#selectedTransaction").append(data);
								calculateTotal();
							});
			    });
	});
	
	</script>
	<table class="table table-bordered" id="searchbyaccno" style="font-size:11pt">
		<thead>
			<tr>
				<th data-sortable="true">Trxn#</th>
				<th data-sortable="true">Date</th>
				<th data-sortable="true">Code</th>
				<th data-sortable="true">Officer</th>
				<th data-sortable="true">Reference</th>
				<th data-sortable="true">Accession No.</th>
				<th data-sortable="true">Due</th>
				<th data-sortable="true">Action</th>
				<th><input type="checkbox" id="checkAll"></th>
			</tr>
		</thead>
		<tbody>
			<% 
			String patronId = request.getParameter("patronid");
			List <ReceiptingService> searchResult = ReceiptingService.ListPendingPayment(patronId);
			
				for(ReceiptingService i: searchResult){	
				 String numberAsString = String.format ("%.2f", i.getReceiptAmount());
					if(i.getReceiptAmount()>0){
			%>
			<tr>
				<td><%=i.getRE01TXNO() %></td>
				<td><%=i.getRE01DATE() %></td>
				<td><%=i.getRE01CODE() %></td>
				<td><%=i.getRE01OFFID() %></td>
				<td><%=i.getRE01REFER() %></td>
				<td>
					<%
						if(!i.getRE01DOCNO().trim().isEmpty()){
					%>
					<span class="glyphicon glyphicon-book" title="<%=Handler.removeSubfield(i.getCT05SRAW()) %>"></span>
					<%=i.getRE01DOCNO() %>
					<%
						}else {
					%>
<!-- 						&nbsp; -->
					<%		
						}
					%>
					
				</td>
				<td><%=numberAsString %></td>
				<td>
					<%
						if(i.getoverrideExist()==true){
							String code = i.getRE01CODE().substring(0,2);
					%>
						<%-- <a class="btn btn-success btn-sm override"  data-toggle="modal" data-target="#overrideModal" title="" action="override" 
						href="override?trxn=<%=i.getRE01TXNO() %>&due=<%=numberAsString%>&code=<%=code%>3&docno=<%=i.getRE01DOCNO() %>" ><i class="glyphicon glyphicon-copy"></i></a> --%>
					<a class="btn btn-success btn-sm override"  data-toggle="modal" data-target="#overrideModal" data-backdrop="static"
						href="override?trxn=<%=i.getRE01TXNO() %>&due=<%=numberAsString%>&code=<%=code%>3&docno=<%=i.getRE01DOCNO() %>" ><i class="glyphicon glyphicon-copy"></i></a>
					
					<%
						}
					%>	
				</td> 
				<td>
					<!-- <input type="checkbox" id="selectTrxn" onchange="selectTrxn(this)" name="receipting" checked> -->
					<input type="checkbox" id="selectTrxn" onchange="selectTrxn(this)" name="receipting">
				</td>
			</tr>
			<%
					}
				}
				
			%>
		</tbody>
	</table>
	
		<div class="modal fade" id="overrideModal" role="dialog" >
				    <div class="modal-dialog" style="width:900px;">
						  <div class="modal-content">
						  </div>
					</div>
				</div>
			
<!-- 				 
			<div class="modal hide" id="overrideModal" tabindex="-1" role="dialog" aria-labelledby="overrideModal" aria-hidden="true" data-backdrop="static">
			    <div class="modal-dialog" style="width:900px;" role="document">
					  <div class="modal-content">
					  </div>
				</div>
			</div>
			 -->	
						<!-- Modal HTML View Accession Maint-->  
				<div class="modal fade" id="viewAccMaintenance" role="dialog"  data-backdrop="static">
				    <div class="modal-dialog" style="width:1000px;height:900px;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
			    <!-- Modal END -->
			    
			    	<!-- Modal HTML View Accession Maint-->  
				<div class="modal fade" id="editAccMaintenance" role="dialog"  data-backdrop="static">
				    <div class="modal-dialog" style="width:1000px;height:900px;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
			    <!-- Modal END -->
			    
			    	<div class="modal fade" id="distributionmodal" role="dialog" >
				    <div class="modal-dialog" style="width:900px;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
				 <div class="modal fade" id="userLogIn" role="dialog" >
				    <div class="modal-dialog" style="width:900px;">
						  <div class="modal-content panelLogIn">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
	
	
	