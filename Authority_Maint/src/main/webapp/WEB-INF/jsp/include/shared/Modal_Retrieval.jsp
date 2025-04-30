<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>

<head>

 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Authority/Global_Change.js"></script>					
  <title>Charging</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/Search_Modal.js"></script>
</head>
<script>
  	$(document).ready(function () {
  		$("[title]").tooltip();
	$('#globalchgtbl').DataTable({
	    responsive: true,
	});

});
	</script>
		
<body>

<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading">
   <a class="accordion-toggle" data-toggle="collapse" href="#patrondet">
          <i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
        </a>
      
    </div>
    <div id="patrondet" class="panel-collapse collapse in">
      <div class="panel-body" id="patrDet">
      <div class="row">
            <div class=" col-md-2 col-lg-2 "> 
                <label>Query By :</label>
             </div>
             <div class="col-md-3 col-lg-3 charging_info">
					<%								
						List<Tag_Handler> TagInfo = null;
						TagInfo = Tag_Handler.getBibTags();
					%>
					<select class="form-control tags" id="select_tags" name="tags">
						<option value="0">Please select</option>
						<%
							for(Tag_Handler i: TagInfo){
						%>
						<option value="<%=i.getGL17TAG()%>" data-id="<%=i.getGL17GRID()%>"><%=i.getGL17TAG()%>-<%=i.getGL17DESC()%></option>
						<%										
							}
						%>
     				</select>
     				<%
     					String action = request.getParameter("action");
     				%>
     				<input type="hidden" id="action" value="<%=action%>">
             </div>
         </div>
         
          <div class="row">
            <div class=" col-md-2 col-lg-2 "> 
                <label>Enter Description :</label>
             </div>
             <div class="col-md-3 col-lg-3 charging_info">
					<input type="text" class="form-control criteria" name="criteria" id="criteria" placeholder="Enter Search Term">
             </div>
             <button type="button" class="btn btn-info" id="btn-submit-retrievemodal"onclick="getDetails(this);return send_title_info()" title="Search" disabled>
				<span class="glyphicon glyphicon-search"></span>
			</button>
			    <%
      	if(action.equals("fromTerm")){
      %>
       <button type="button" class="btn btn-info" id="getRecord" onclick="getRecord()" title="Select">
				<span class="glyphicon glyphicon-hand-up"></span>
			</button>
      <%
      	}
      %>
         </div>
      </div>
    </div>
  </div>
  </div>
  
  <div class="panel panel-default">
    <div id="accesioning" class="panel-collapse collapse in" >
      <div class="panel-body" id="searchcontent">
        <table class="table table-bordered" id="globalchgtbl">
				<col width="5%">
				<col width="10%">
				<col width="10%">
					<thead>
						<tr>
							<th data-sortable="true" style="text-align: center;">No.</th>
							<th data-sortable="true" style="text-align: center;">Term Type</th>
							<th style="text-align: center;">Hits</th>
							<th style="text-align: center;">Bibliography Details</th>
						</tr>
					</thead>
					 <tbody>
												
					</tbody>
				</table>
	</div>
  </div>
    </div>
    <div class="modal-footer">
    <button class='btn btn-sm' id='cancel' data-dismiss="modal">Cancel</button>
    </div>
</body>

</html>	