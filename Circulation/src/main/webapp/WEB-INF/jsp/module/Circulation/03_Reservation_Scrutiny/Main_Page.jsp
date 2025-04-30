<%@ page import="com.ilmu.circulation.resv.*, com.ilmu.circulation.Charging.*" %>
	
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css"> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Reservation.js"></script>	
<script>
$(document).ready(function(){
    $('#resv_scrutiny').DataTable();
});
</script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Reservation.js"></script>			 --%>
		 	<div class="panel panel-default">
			  <div class="panel-body" style="margin:auto">
		 			 <div class="form-group row">
						    <label for="staticEmail" class="col-sm-2 col-form-label"><input type="radio" name="optradio" value="accession" checked>Accession No. :</label>
						    <div class="col-sm-3">
						      <input type="text" class="form-control" id="accession" >
						    </div> 
						    <label for="staticEmail" style="padding-left:5%;padding-right:-15%" class="col-sm-3 col-form-label"><input type="radio" name="optradio" value="days">Number of days after notification date :</label>
						    <div class="col-sm-1">
						    <% 
						    	//Reservation resv = new Reservation();
						    %>
						      <input type="text" class="form-control" id="CI02NDATE" maxlength="3" value="<%=Reservation.grace()%>" disabled>
						    </div>
						    <div class="col-sm-3" style="padding-left:10%">
						   	 <button id="retrieveRecord" class="btn btn-primary"><span class="glyphicon glyphicon-log-in" style="color:white" title="Retrieve"></span></button>
						   	<button id="refresh" class="btn btn-primary" title="Refresh"><span class="glyphicon glyphicon-refresh" style="color:white"></span></button>
						   </div>
						  </div>	
         		     <br/>
					<%--   <div class="form-group row">
						    <label for="staticEmail" class="col-sm-2 col-form-label"><input type="radio" name="optradio" value="days">Number of days after notification date :</label>
						    <div class="col-sm-1">
						    <% 
						    	//Reservation resv = new Reservation();
						    %>
						      <input type="text" class="form-control" id="CI02NDATE" maxlength="3" value="<%=Reservation.grace()%>" disabled>
						    </div>
						     <div class="col-sm-4" style="margin-left:20%">
						   	 <button id="retrieveRecord" class="btn btn-primary"><span class="glyphicon glyphicon-log-in" style="color:white" title="Retrieve"></span></button>
						   	<button id="refresh" class="btn btn-primary" title="Refresh"><span class="glyphicon glyphicon-refresh" style="color:white"></span></button>
						   </div>
						  </div> --%>
					<!-- 	   <div class="form-group row" style="margin-left:70%"> 
						   <div class="col-sm-4" >
						   	 <button id="retrieveRecord" class="btn btn-primary"><span class="glyphicon glyphicon-log-in" style="color:white" title="Retrieve"></span></button>
						   	<button id="refresh" class="btn btn-primary" title="Refresh"><span class="glyphicon glyphicon-refresh" style="color:white"></span></button>
						   </div>
						  </div> -->
			</div>
			</div>
			<div class="panel panel-default">
			  <div class="panel-body" style="margin:auto">
				  <p>
				  	<label><input type="checkbox" name= "print" class="cancellationLetter" value="cancellationLetter">Print Reservation Cancellation Letter</label>
				  </p>
				  <br/>
			  	 <div class="form-group resvscrutiny_list">
			  	 	<table class="table table-bordered div-result" id="resv_scrutiny" style="font-size:10pt">
						<thead>
							<tr>
								<th>No.</th>
								<th>Notification Date</th>
								<th>Patron ID</th>
								<th>Patron Name</th>
								<th>Title</th>
								<th>Priority</th>
								<th>Control No.</th>
								<th>Accession No.</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			  </div>
			</div>

<!-- 	<div class="modal-footer">
		<button type="button" class="btn btn-info" id="add-submit">
			<span class="glyphicon glyphicon-save"></span> Add
		</button>
		<input type="button" name="cancel" value="Close" class="btn btn-info" data-dismiss="modal"/>
	</div>	
 -->

<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->