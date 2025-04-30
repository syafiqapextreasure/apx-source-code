<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.wilmu.global.*, java.util.List.*,java.util.*, com.wilmu.acquisition.requestctrl.data.*"%>
	
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

$("#close_modalorder").click(function(){
	 $("#closeModalOrder").click();
})
});
</script>
</head>
<%
	String getCriteria = request.getParameter("isbn");
%>

  <div class="modal-header">
				<button type="button" id="closeModalOrder" class="close" data-dismiss="modal"
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
													<table id="status" class="table table-bordered table-striped" style="width:100%">
														<thead>
														 	<tr>
														 		<th>ISBN</th>
																<th>Control No</th>
																<th>Title</th>
																<th>Author</th>
																<th>Vendor</th>
																<th>Request No</th>
																<th>Requestor</th>
																<th>Request Date</th>
														 	</tr>
													 	</thead>
													 	<tbody>
													 	<%
													 		List<GetSearchRequestCtrl> searchreqCTRL = null;
															
															searchreqCTRL = GetSearchRequestCtrl.getforReqCtrl("ISBN", getCriteria, "", 
																	"", null, "", null, "", null, "");
															for(GetSearchRequestCtrl reqCtrl: searchreqCTRL){  
																List<GetAcqRequestCtrl> requestDetails = null;
																requestDetails = GetAcqRequestCtrl.getRequestCtrlDetails(reqCtrl.getreqno());
																for(GetAcqRequestCtrl requests: requestDetails){ 
														%>
														<tr>
															<td><%=requests.getisbn()%></td>
															<td><%=requests.getctrlno()%></td>
															<td><%=requests.gettitle()%></td>
															<td><%=requests.getauthor()%></td>
															<td><%=requests.getvenddesc()%></td>
															<td><%=requests.getreqno()%></td>
															<td><%=requests.getregname()%></td>
															<td><%=requests.getdatereq()%></td>
														</tr>
														<%
																}}
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
						        <button type="button" id="close_modalorder" class="btn btn-default">Close</button>
					        
						</div>	
					</div>
					
					</form>
					</div>