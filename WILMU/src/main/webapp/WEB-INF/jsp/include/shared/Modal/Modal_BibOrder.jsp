<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.wilmu.global.*, java.util.List.*,java.util.*, com.wilmu.acquisition.ordermaint.data.*"%>
	
<head>
<style>
.form-group{
margin-bottom : 5px;
}

table thead{
    background: rgba(52, 73, 94, 0.94);
    color: #ecf0f1;
}
</style>
<script>
$(document).ready(function() {


$("#close_modalbiborder").click(function(){
	 $("#closeModalBibOrder").click();
})
});
</script>
</head>
<%
	String ctrlno = request.getParameter("ctrlno");
%>

  <div class="modal-header">
				<button type="button" id="closeModalBibOrder" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
     
	<div class="modal-body">
					<form role="form" class="form-horizontal" id="formdataOrderMain" >
					
					<div class="modal-body" id="detail">
						
							
								<div class="row">
									<div class="col-xs-12">
										<div class="panel panel-default">
											<div class="panel-heading"></div>
											<div class="panel-body">
												<div id="receivingListingContainer">
													<table id="table" class="table table-bordered table-striped" style="width:100%">
														<thead>
														 	<tr>
														 		<th>Order No</th>
																<th>Control No</th>
																<th>Title</th>
																<th>Vendor</th>
																<th>Order Status</th>
																<th>Reference</th>
																<th>Order Date</th>
														 	</tr>
													 	</thead>
													 	<tbody>
													 	<%
													 	
													
																List<GetAcqBibliographic> details = GetAcqBibliographic.getBibOrder(ctrlno);
																for(GetAcqBibliographic requests: details){ 
														%>
														<tr>
															<td><%=requests.getAccno()%></td>
															<td><%=requests.getLocation()%></td>
															<td><%=requests.getSmd()%></td>
															<td><%=requests.getLocadesc()%></td>
															<td><%=requests.getBranch()%></td>
															<td><%=requests.getItemCategory()%></td>
															<td><%=requests.getCondition()%></td>
														</tr>
														<%
																}
														%>
													 	</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
									<!-- </form> -->
								</div>
						
						
						<!-- END TAB CONTENT -->
						<div class="form-group modal-footer">
						        <button type="button" id="close_modalbiborder" class="btn btn-default">Close</button>
					        
						</div>	
					</div>
					
					</form>
					</div>