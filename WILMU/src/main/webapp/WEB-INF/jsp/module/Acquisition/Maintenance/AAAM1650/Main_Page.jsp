<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<html>
<head>

</head>
<body>
	<!-- START MAIN CONTENT -->
		<div class="box box-default">
			<div class="panel panel-default">
				<div class="panel-heading">
					
					<h3 class="panel-title pull-left"></h3>
					<button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchCDNote' href='Modal_SearchCreditDebit'><i class="glyphicon glyphicon-search" title="Search"></i></button>
        			<button type="button" class="btn btn-primary pull-right" id="addNewCDNote" data-toggle='modal' data-target="#modalAdd" data-whatever="Add New Record"> 
        			<i class="glyphicon glyphicon-plus" title="Add New Credit/Debit Note"></i></button>
					<div class="clearfix"></div>
				</div>
				
				
				
				<div class="panel-body" id="display_CDNote">
					
					<!-- <input type="text" id="msgAdd" value=""> -->

					<table id="cdNote2" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>Note No </th>
						 		<th>Vendor </th>
						 		<th>Note Type </th>
						 		<th>Status </th>
						 		<th>Received Date </th>
						 		<th>Invoice Number </th>
						 		<th>Used Date </th>
						 		<th>Currency </th>
						 		<th>Foreign Amount </th>
						 		<th>Local Amount </th>
						 		<th>Action </th>
						 	</tr>
						 </thead>
						<%-- <tbody>
			<% 
				String searchType = request.getParameter("searchType");
				System.out.println("searchType = " +searchType);
				String criteria = request.getParameter("criteria");
				System.out.println("criteria = " +criteria);
				
				List<CreditDebit> searchResult = null;
				if(criteria == null)
				{
					out.println();
				}
				else
				{
				if(searchType.equals("vendorCode")){
					searchResult = CreditDebit.searchByVendorCode(criteria);
				}else if(searchType.equals("noteNo")){
					searchResult = CreditDebit.searchByNoteNo(criteria);
				}else if(searchType.equals("receivedDate")){
					searchResult = CreditDebit.searchByReceivedDate(criteria);
				}else if(searchType.equals("usedDate")) {
					searchResult = CreditDebit.searchByUsedDate(criteria);
				}
				
				for(CreditDebit getSearch: searchResult){
			%>
				<tr>
					<td><%=getSearch.getNoteNo()%></td>
					<td><%=getSearch.getVendor()%></td>
					<td><%=getSearch.getNoteType()%></td>
					<td id="Status"><%=getSearch.getStatus()%></td>
					<td><%=getSearch.getReceivedDate()%></td> 
					<td><%=getSearch.getInvoiceNumber()%></td>
					<td><%=getSearch.getUsedDate()%></td>
					<td><%=getSearch.getCurrency()%></td>
					<td><%=getSearch.getForeignAmount()%></td>
					<td><%=getSearch.getLocalAmount()%></td>
					
					
					%>
					<td class="last">
						<!-- <input type="hidden" value="A" name="fndcategories"/> -->
						<button id="View" class="btn btn-primary btn-xs" data-toggle='modal' data-target="#modalAdd" data-whatever="View Record" title="View Credit/Debit Note" data-rowid="<%=getSearch.getNoteNo()%>"><span class="glyphicon glyphicon-eye-open"></span></button>
						<button id="Edit" class="btn btn-info btn-xs" data-toggle='modal' data-target="#modalAdd" data-whatever="Edit Record" title="Edit Credit/Debit Note" data-rowid="<%=getSearch.getNoteNo()%>"><span class="glyphicon glyphicon-pencil"></span></button>
		   				<button id="Delete" class="btn btn-dull btn-xs DeleteCD" data-NoteNo="<%=getSearch.getNoteNo()%>" data-Status="<%=getSearch.getStatus()%>"><span class="glyphicon glyphicon-trash" title="Delete Credit/Debit Note" ></span></button>
   					</td>
				</tr>
				<%
					} 
					}
				%> 
			</tbody> --%>
					</table>
				</div>
			</div>
		</div>	
	<!-- END MAIN CONTENT -->

	
		<!-- Start Modal Add, Edit, View -->
			<div class="modal fade" id="modalAdd" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			  <div class="modal-dialog" role="document" style="width:55%;overflow:auto">
			    <div class="modal-content">
					<!-- Modal content-->	
					      <div class="modal-header">
					        <h5 class="modal-title" id="modalName" align="center">form</h5>
					        <button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body">
					        <form role="form" class="form-horizontal" id="formdata" method="post">
					        	<input type="hidden" name="userid">
					          <div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3">
									<label>Note Type</label> 
								</div>
								<div class="col-sm-7">
								<div class="form-group">
						            <div class="col-md-9">
						                <label class="radio-inline">
						                    <input id="noteType" name="noteType" type="radio" value="C">
						                    Credit Note
						                </label>
						                <label class="radio-inline">
						                    <input id="noteType" name="noteType" type="radio" value="D">
						                    Debit Note
						                </label>
						            </div>
						        </div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3">
									<label for="noteNo">Credit Note No</label>
								</div>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="note_no" name="note_no">
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"><label>Vendor</label></div>
								<div class="col-sm-6">
						    	<div class="input-group">

							    	<input type="text" class="form-control" id="input-vendorCode" name="inputVendorCode">
								    <a href="Modal_Vendor" id="searchvendor" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#modal_vendorSearch">
								    <span class="glyphicon glyphicon-option-horizontal"></span></a>
						    	</div>
								</div>	
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"><label></label></div>
								<div class='col-sm-8'>
									<div id="div-vendorName"></div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"><label>Received Date</label></div>
								 <div class='col-sm-4'>
			                      	<div class="input-group date" id="receivedDate">
			  						 	<input type="text" class="form-control" id="received_date" name="received_date">
			  						 	<span class="input-group-addon">
			  							<i class="glyphicon glyphicon-calendar"></i></span>
									</div>
			        			</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"><label>Currency</label></div>
								<div class="col-sm-6">
									<select class="form-control" id="currency" name="currency">
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"><label>Currency Rate</label></div>
								<div class="col-sm-3">
									<span id="exchangeRate"></span>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"><label>Amount</label></div>
								<div class="col-sm-7">
									<div class="form-group">
											<div class="col-sm-1"><input type="radio" name="amount" value="foreignAmount" checked></div>
											<div class="col-sm-2"><label>Foreign</label></div>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="foreign-amount" name="foreignAmount">						
											</div>	
										</div>
										<div class="form-group">
											<div class="col-sm-1"><input type="radio" name="amount" value="localAmount"></div>
											<div class="col-sm-2"><label>Local</label></div>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="local-amount" name="localAmount" readonly>						
											</div>
										</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"><label>Remark</label></div>
								<div class="col-sm-7">
									<textarea class="form-control" id="remark" name="remark" rows="3"></textarea>
								</div>
							</div>
							
							<div class="form-group pull-right">
					            <label><input type="checkbox" class="form-check-input" id="use" name="use" value="use">&nbsp; Use Immediately</label>
					        </div>
					            
							<div class="form-group modal-footer">
							<br>
						      	<button type="submit" id="save" class="btn btn-primary" onClick="testing()">Save</button>
						        <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						        <button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
					        
					      	</div>
					        </form>
					      </div>
					      <!-- <div class="modal-footer">
					      	<button type="submit" id="save" class="btn btn-primary">Save</button>
					        <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					        <button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
					        
					      </div> -->
			<!-- End Modal content -->
			</div>
		  </div>
		</div>
	<!-- End Modal Add -->
	
	<!-- Modal for search credit and debit -->
	<div class="modal fade" id="searchCDNote" tabindex="-1" role="dialog" aria-labelledby="searchCDNote" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
			<!-- Remote content load here -->
			</div>
		</div>
	</div>	
	<!-- Modal END -->
	
	<!-- MODAL WHEN CLICK Search button-vendorSearch -->
    <div class="modal fade" id="modal_vendorSearch" tabindex="-1" role="dialog" aria-labelledby="modal_vendorSearch" aria-hidden="true"> 
		<div class="modal-dialog" style="width:50%;">
			    <div class="modal-content" id="modal_vendorSearchContent">
				  <!-- Remote content load here  -->
			  	</div>
		</div>
	</div>
	
	<!-- MODAL CREADIT DEBIT  -->
	<div class="modal fade" id="modalAssignInvoice" tabindex="-1" role="dialog" aria-labelledby="ModalAssignInvoice" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" role="document" style="width:70%">
			<div class="modal-content">
				<!-- Modal content-->
				<div class="modal-header">
					<h5 class="modal-title" id="modalNameAssign" align="center">Assign Invoice Number and Fund</h5>
					    <button type="button" id="closeAssignInvoice" class="close" data-dismiss="modal" aria-label="Close">
					        <span aria-hidden="true">&times;</span>
					       </button>
				</div>
				<div class="modal-body">
					<form role="form" class="form-horizontal" id="formdataAssignInvoice" method="post">
						<br>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3">
									<label>Invoice Number</label>
								</div>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="assignInvoNo" name="assignInvoNo">
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"><label>Fund</label></div>
								<div class="col-sm-4">
									<select class="form-control" id="assignFund" name="assignFund">

									</select>
								</div>
							</div>
								
							<div class="modal-footer">
							    <button type="submit" id="saveAssignInvoice" class="btn btn-primary" data-dismiss="modal">Ok</button>
								<button type="button" id="cancelAssignInvoice" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					      	</div>
					</form>
				</div>
				<!-- END Modal content-->
			</div>
		</div>
	</div>
	<!-- END MODAL CREADIT DEBIT  -->
	
	<!-- <script type="text/javascript">
		$('.input-group.date').datepicker({
			format: "dd/mm/yyyy",
		    todayBtn: true,
		    autoclose: true,
		    todayHighlight: true
		});
  </script>  --> 
</body>
<%
/* } */
%>
</html>