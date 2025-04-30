<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.circulation.resv.*, com.ilmu.circulation.Charging.*" %>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Reservation.js"></script>	
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css"> 
<script>
$(document).ready(function(){
	var table = $('#resvTable').DataTable({
		"ordering": false
	});
    $('#div-result').DataTable();
});
</script>
<div class="modal-body">	
<p>
	<label><input type="checkbox" name="print" class="ackLetter" value="ackLetter">Print Acknowledgment Letter</label>
	&nbsp;&nbsp;&nbsp;<span><label><input type="checkbox" name= "print" class="cancellationLetter" value="cancellationLetter">Print Reservation Cancellation Letter</label></span>
</p>	
	<div id="exTab2" class="container" style="width:100%">	
			<ul class="nav nav-tabs">
				<li class="active">
	        		<a  href="#resvDetails" data-toggle="tab">Reservation Details</a>
				</li>
				<li><a href="#relatedItem" data-toggle="tab">Related Item Details</a>
				</li>
				<li class="btn-group pull-right">
					<div class="btn-group pull-right">
						<button type="button" class="btn btn-primary" id="btn_add" data-toggle="modal" 
												data-target="#searchPatron" href="Modal_PatrSearch?action=addResv"><span class="glyphicon glyphicon-plus" title="Add Reservation"></span></button>
					</div>
				</li>
			</ul>

			<div class="tab-content " style="height:60%;overflow-y:auto">
			  <div class="tab-pane active" id="resvDetails">
          			<table class="table table-bordered div-result" id="resvTable" style="font-size:10pt">
						<thead>
							<tr>
								<th>Patron ID</th>
								<th>Name</th>
								<th>Reserved On</th>
								<th>Notification</th>
								<th>Expiry Date</th>
								<th>Pickup Branch</th>
								<th>Status</th>
								<th>Accession No.</th>
								<th>Item Category</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<%
								List<Reservation> getListResv = null;
								String CI03MATNO = request.getParameter("CI03MATNO");
								String CI03DOCNO = request.getParameter("CI03DOCNO");
								getListResv = Reservation.getListResv(CI03DOCNO, CI03MATNO);
						/* 	if(action.equals("accession")){
								CI03DOCNO = request.getParameter("CI03DOCNO");
								getListResv = Reservation.getListResv(CI03DOCNO, action);
							}else if(action.equals("ctrlno")){{
								CI03DOCNO = request.getParameter("CI03MATNO");
								getListResv = Reservation.getListResv(CI03DOCNO, action);
							}else{
								
							}
								 */
								for (Reservation i: getListResv) {
							%>
							
							<tr>
								<td class='id'><%=i.getCI03PATR() %></td>
								<td><%=i.getGL14NAME() %></td>
								<td><%=i.getCI03RDATE() %></td>
								<td><%=i.getCI03NDATE() %></td>
								<td><%=i.getCI03DDATE() %></td>
								<td><%=i.getCI03BRNC() %></td>
								<%
									String status = "";
									if((i.getCI03NSTAT()).equals("X")){
										status = "Waiting in List";
									}else{
										status = "Awaiting for collection";
									}
								%>
								<td class='status'><%=status%></td>
								<td class='accession'><%=i.getCI03DOCNO()%></td>
								<td><%=i.getCI03ICAT() %></td>
								<td>

								     <button type="button"onclick="deleteReserv('<%=i.getCI03PATR() %>, <%=i.getCI03NSTAT() %>')" class="btn btn-dull btn-sm"><span class="glyphicon glyphicon-trash" title="Delete Reservation"></span></button>
								     
								     <!-- Removed temporarily -->
								<%--      <button type="button" class="btn btn-primary btn-sm" id="btn_sort">
									<span class="glyphicon glyphicon-sort" title="Change Priority" onclick="sortPriority(<%=status%>)"></span>
								</button> --%>
								</td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					
				</div>
				<div class="tab-pane" id="relatedItem">
					<table class="table table-bordered div-result" id="div-result" style="font-size:10pt">
						<thead>
							<tr>
								<th>No.</th>
								<th>Accession No.</th>
								<th>Due Date</th>
								<th>Due Time</th>
								<th>Status</th>
								<th>Patron ID</th>
								<th>Branch</th>
								<th>Location</th>
								<th>Item Category</th>
								<th>SMD</th>
							</tr>
						</thead>
						<tbody>
						<%
						
							List<Charging> getRelatedItems = null;
						
					/* 	if(action.equals("accession")){
							CI03DOCNO = request.getParameter("CI03DOCNO");
							getRelatedItems = Charging.relatedItem(CI03DOCNO, action);
						}else{
							CI03DOCNO = request.getParameter("CI03MATNO");
							getRelatedItems = Charging.relatedItem(CI03DOCNO, action);
						} */
								getRelatedItems = Charging.relatedItem(CI03DOCNO, CI03MATNO);
								int count = 1;
								for (Charging i: getRelatedItems) {
							%>
							
							<tr>
								<td><%=count++%></td>
								<td><%=i.getCT02DOCNO() %></td>
								<td><%=i.CT03DDATE() %></td>
								<td><%=i.CT03DTIME() %></td>
								<td><%=i.CT03STAT() %></td>
								<td><%=i.CT03PATR() %></td>
								<td><%=i.GL14BRNC() %></td>
								<td><%=i.CT03LOCA() %></td>
								<td><%=i.CT03ICAT()%></td>
								<td><%=i.CT03SMD() %></td>
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

<div class="modal fade" id="searchPatron" tabindex="-1" role="dialog" aria-labelledby="searchPatron"  aria-hidden="true"> 
		<div class="modal-dialog" style="min-width:60%;">
		  <div class="modal-content">
			  <!-- Remote content load here -->
		  </div>
	</div>
</div>