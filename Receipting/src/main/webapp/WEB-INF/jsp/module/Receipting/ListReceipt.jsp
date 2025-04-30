<%@ page import="com.ilmu.receipting.Receipting.*, java.util.List" %>

	<!-- Bootstrap table -->
	 <script type="text/javascript" src="<%= request.getContextPath() %>/js/Receipting/Receipting.js"></script>				
	<script>
	$('#searchbyreceipt').DataTable({
	    responsive: true,
	    "ordering": false
	});
	
	function printReceipt(r){
		var date = "";
		var receiptno = "";
		var patronid = $("#PatronId").val();
		var code = "";
		var accession = "";
		var payment="";
		var offid = "";
		var paymentMode="";
		var $row = $(r).closest("tr"),        
	    $tds = $row.find("td:nth-child(1)");
		$tds1 = $row.find("td:nth-child(1)");
		$tds2 = $row.find("td:nth-child(8)");
		$tds3 = $row.find("td:nth-child(6)");
		$tds4 = $row.find("td:nth-child(5)");
		$tds5 = $row.find("td:nth-child(4)");
		$tds6 = $row.find("td:nth-child(6)");
		
		$.each($tds4, function() {             
			payment = $(this).text();         
		})

		$.each($tds6, function() {             
			paymentMode = $(this).text();         
		})
	/* 
		$.each($tds, function() {             
			date = $(this).text();         
		})
		 */
		$.each($tds, function() {             
			receiptno = $(this).text(); 
		})
		
	/* 	$.each($tds2, function() {             
			code = $(this).text();         
		})
		
		$.each($tds3, function() {             
			accession = $(this).text();         
		})
		
		$.each($tds4, function() {             
			payment = $(this).text();         
		})
		
		$.each($tds5, function() {             
			offid = $(this).text();         
		})
			 */
			//alert("here");
			//var url = "printReceipt?receiptno=" + receiptno+ "&patronid=" + patronid;
			//14112019
			var url = "GeneratePreview?receiptno=" + receiptno+ 
					"&total=" + payment+
					"&received=" + payment+
					"&change=" + "0.00"+
					"&PayMode=" + paymentMode+
					"&patronid=" + patronid;

			window.open(url,"", "width=283,height=500,toolbar=0").print();
	}
/* $(".printreceipt").click(function(){
		alert("123");
		var date = "";
		var receiptno = "";
		var patronid = $("#PatronId").val();
		var code = "";
		var accession = "";
		var payment="";
		var offid = "";
		var $row = $(this).closest("tr"),        
	    $tds = $row.find("td:nth-child(2)");
		$tds1 = $row.find("td:nth-child(1)");
		$tds2 = $row.find("td:nth-child(8)");
		$tds3 = $row.find("td:nth-child(6)");
		$tds4 = $row.find("td:nth-child(5)");
		$tds5 = $row.find("td:nth-child(4)");
	
		$.each($tds, function() {             
			date = $(this).text();         
		})
		
		$.each($tds1, function() {             
			receiptno = $(this).text(); 
		})
		
		$.each($tds2, function() {             
			code = $(this).text();         
		})
		
		$.each($tds3, function() {             
			accession = $(this).text();         
		})
		
		$.each($tds4, function() {             
			payment = $(this).text();         
		})
		
		$.each($tds5, function() {             
			offid = $(this).text();         
		})
			
			var url = "printReceipt?date="+date+"&patronid="+patronid+"&receiptno=" + receiptno+"&code="+code+ "&accession="+accession
						+ "&payment="+payment+"&offid="+offid;

			window.open(url,"", "width=283,height=500,toolbar=0");
	}) */
	
	</script>
	<table class="table table-bordered" id="searchbyreceipt" style="font-size:11pt">
		<thead>
			<tr>
				<th data-sortable="true">ReceiptNo</th>
				<th data-sortable="true">Date</th>
				<th data-sortable="true">Time</th>
				<th data-sortable="true">Officer</th>
				<th data-sortable="true">Payment</th>
				<!-- <th data-sortable="true">Accession No.</th> -->
		<!-- 		<th data-sortable="true">Due</th> -->
				<th data-sortable="true">Payment Mode</th>
				<th data-sortable="true">Action</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<% 
			String patronId = request.getParameter("patronid");
			List <ReceiptingService> searchResult = ReceiptingService.ListReceiptNumbersByPatronId(patronId);
			
				for(ReceiptingService i: searchResult){
				 String numberAsString = String.format ("%.2f", i.getReceiptAmount());
					if(i.getReceiptAmount()>0){
			%>
			<tr>
				<td><%=i.getRE01REFER() %></td>
				<td><%=i.getRE01DATE() %></td>
				<td></td>
				<td><%=i.getRE01OFFID() %></td>
				<td><%=numberAsString %></td>
				<%-- <td><%=i.getRE01DOCNO() %></td> --%>
				<td><%=i.getPaymentMode() %></td>
				 <td>
					<button class="btn btn-success btn-sm printreceipt" title="Print" onclick="printReceipt(this)"><i class="glyphicon glyphicon-print"></i></button>
				</td> 
				<td style="display:none"><%=i.getRE01CODE() %>
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
	
	
	