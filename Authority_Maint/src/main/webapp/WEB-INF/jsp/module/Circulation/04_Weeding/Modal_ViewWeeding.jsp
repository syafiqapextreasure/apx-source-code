 <%@page import="com.ilmu.circulation.Weeding.*,
				java.util.*,com.ilmu.global.*"%>


<style>
table{
font-size:12pt
}
</style>
<script>
$(document).ready(function() {
	
	$('#viewRecord').on('hidden.bs.modal', function(e)
	{ 
		$(this).removeData();
	}) ;
});
</script>
 <div class="modal-header">
     <button type="button" class="close" data-dismiss="modal" aria-label="Close">
   	 <span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">Weeding Details</h4>
  </div>
  <%
  	String accNo = request.getParameter("accNo");
 	 List<SQLStatusMaintenance> weedingenqury = new ArrayList<SQLStatusMaintenance>();
 		weedingenqury=SQLStatusMaintenance.getWeedingAccession(accNo);
  %>
  <div class="modal-body">
  	<div class="panel panel-default">
  		<div class="panel-body">	
  			<table>
  			<col width="15%">
  			<%
				for(SQLStatusMaintenance i: weedingenqury){
			%>
  				<tr>
  					<td><label>Accession No</label></td>
         			<td><label>:</label>&nbsp;<%=i.getCT03DOCNO() %></td>
  				</tr>
  				<tr>
  					<td><label>Title</label></td>
         			<td><label>:</label>&nbsp;<%=i.getTITLE() %></td>
  				</tr>
  				<tr>
  					<td><label>Reason</label></td>
         			<td><label>:</label>&nbsp;<%=i.getWE03REASON() %></td>
  				</tr>
  				<tr>
  					<td><label>Weed Date</label></td>
         			<td><label>:</label>&nbsp;<%=i.getCT03RFCDATE() %></td>
  				</tr>
  				<%
				}
  				%>
  			</table>
  		</div>
	</div>
	<div class="panel panel-default">
	 <div class="panel-heading">Accession Details</div>
  		<div class="panel-body">	
  		
  			<table>
  			<col width="15%">
  			<col width="35%">
  			<col width="20%">
  				<%
				for(SQLStatusMaintenance i: weedingenqury){
				%>
  				<tr>
  					<td><label>Status</label></td>
         			<td><label>:</label>&nbsp;<%=i.getGL36DESC() %></td>
         			<td><label>Circulated Hits</label></td>
         			<td><label>:</label>&nbsp;<%=i.getCT03CIRHITS() %></td>
  				</tr>
  				<tr>
  					<td><label>Item Category</label></td>
         			<td><label>:</label>&nbsp;<%=i.getGL10DESC() %></td>
         			<td><label>Claim Hits</label></td>
         			<td><label>:</label>&nbsp;<%=i.getCT03CLMHITS() %></td>
  				</tr>
  				<tr>
  					<td><label>Condition</label></td>
         			<td><label>:</label>&nbsp;<%=i.getGL06DESC() %></td>
         			<td><label>Last Activity Date</label></td>
         			<td><label>:</label>&nbsp;<%=i.getWE03LASTACT() %></td>
  				</tr>
  				<tr>
  					<td><label>Location</label></td>
         			<td><label>:</label>&nbsp;<%=i.getGL05DESC() %></td>
         			<td><label>Borrower ID</label></td>
         			<td><label>:</label>&nbsp;<%=i.getWE03PATR() %></td>
  				</tr>
  				<tr>
  					<td><label>Control No</label></td>
         			<td><label>:</label>&nbsp;<%=i.getCT03MATNO() %></td>
         			<td><label>Date Borrowed</label></td>
         			<td><label>:</label>&nbsp;<%=i.getWE03BDATE() %></td>
  				</tr>
  				<tr>
  					<td><label>Currency Code</label></td>
         			<td><label>:</label>&nbsp;<%=i.getGL24DESC() %></td>
         			<td><label>Date Borrowed</label></td>
         			<td><label>:</label>&nbsp;<%=i.getWE03BTIME() %></td>
  				</tr>
  				<tr>
  					<td><label>Foreign Cost</label></td>
         			<td><label>:</label>&nbsp;<%=i.getCT03FCOST() %></td>
         			<td><label>Date Due</label></td>
         			<td><label>:</label>&nbsp;<%=i.getWE03DDATE() %></td>
  				</tr>
  				<tr>
  					<td><label>Local Cost</label></td>
         			<td><label>:</label>&nbsp;<%=i.getCT03LCOST() %></td>
         			<td><label>Time Due</label></td>
         			<td><label>:</label>&nbsp;<%=i.getWE03DTIME() %></td>
  				</tr>
  				<%
				}
  				%>
  			</table>
  		</div>
	</div>
	<div class="panel panel-default">
	<div class="panel-heading">Bibliographic</div>
  		<div class="panel-body">	
  			<table>
  				<%
				for(SQLStatusMaintenance i: weedingenqury){
				%>
  				<tr>
  					<td><label>Author</label></td>
         			<td><label>:</label>&nbsp;<%=i.getAUTHOR() %></td>
  				</tr>
  				<tr>
  					<td><label>Call No</label></td>
         			<td><label>:</label>&nbsp;<%=i.getCALLNUMBER() %></td>
  				</tr>
  				<tr>
  					<td><label>Publisher</label></td>
         			<td><label>:</label>&nbsp;<%=i.getPUBLISHER() %></td>
  				</tr>
  				<tr>
  					<td><label>Edition</label></td>
         			<td><label>:</label>&nbsp;<%=i.getWE03EDITION() %></td>
  				</tr>
  				<tr>
  					<td><label>Collation</label></td>
         			<td><label>:</label>&nbsp;<%=i.getCOLLATION() %></td>
  				</tr>
  				<%
				}
  				%>
  			</table>
  		</div>
	</div>
  </div>
  <div class="modal-footer">
  <!-- <button class="btn btn-sm btn-info" id="searchWeed" data-dismiss="modal">Search</button>-->
  <button class="btn btn-sm btn-info" data-dismiss="modal">Close</button>
  </div>