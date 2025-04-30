<%@ page import="com.ilmu.receipting.Receipting.*, java.util.List" %>

	<!-- Bootstrap table -->
	 <script type="text/javascript" src="<%= request.getContextPath() %>/js/Receipting/Receipting.js"></script>				
	<script>
	$('#searchbypaid').DataTable({
	    responsive: true, 
	    "ordering": false
	});
	
	</script>
	<table class="table table-bordered" id="searchbypaid" style="font-size:11pt">
		<thead>
			<tr>
				<th data-sortable="true">Trxn#</th>
				<th data-sortable="true">Date</th>
				<th data-sortable="true">Code</th>
				<th data-sortable="true">Officer</th>
				<th data-sortable="true">Reference</th>
				<th data-sortable="true">Accession No.</th>
				<th data-sortable="true">Paid Amount</th>
				<th data-sortable="true">Override</th>
<!-- 				<th data-sortable="true">Action</th>
				<th></th> -->
			</tr>
		</thead>
		<tbody>
			<% 
			String patronId = request.getParameter("patronid");
				System.out.println("Patron" + patronId);
			List <ReceiptingService> searchResult = ReceiptingService.ListPaidPayment(patronId);
			 String trxn = "";
				for(ReceiptingService i: searchResult)
				{	System.out.println("Patron" + i.getReceiptAmount());
				 String numberAsString = String.format ("%.2f", i.getReceiptAmount());
				
			%>
			<tr>
				<td><%=i.getRE01TXNO() %></td>
				<td><%=i.getRE01DATE() %></td>
				<td><%=i.getRE01CODE() %></td>
				<td><%=i.getRE01OFFID() %></td>
				<td><%=i.getRE01REFER() %></td>
				<td><%=i.getRE01DOCNO() %></td>
				
				<%
					System.out.println("Journal" + trxn);
					if((i.getGL05BRNC()).equals("R")){
				%>
				<td><%=numberAsString %></td>
				<td><%=0.00 %></td>
				<%
					}
				%>
				
				<%
					if((i.getGL05BRNC()).equals("J")){
				%>
				<td><%=0.00 %></td>
				<td><%=numberAsString %></td>
				
				<%
					}
				%>
			</tr>
			<%
					trxn = i.getRE01TXNO();
				}				
			%>
	<%-- 		
			<% 
				System.out.println("Patron" + patronId);
			List <ReceiptingService> searchOverride = ReceiptingService.ListOverridePayment(patronId);
			
				for(ReceiptingService i: searchOverride)
				{	System.out.println("Patron" + i.getReceiptAmount());
				 String numberAsString = String.format ("%.2f", i.getReceiptAmount());
			%>
			<tr>
				<td><%=i.getRE01TXNO() %></td>
				<td><%=i.getRE01DATE() %></td>
				<td><%=i.getRE01CODE() %></td>
				<td><%=i.getRE01OFFID() %></td>
				<td><%=i.getRE01REFER() %></td>
				<td><%=i.getRE01DOCNO() %></td>
				<td>0.00</td>
				<td><%=numberAsString %></td>
			</tr>
			<%
				}				
			%> --%>
		</tbody>
	</table>
	
		<div class="modal fade" id="overrideModal" role="dialog" >
				    <div class="modal-dialog" style="width:900px;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
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
	
	
	