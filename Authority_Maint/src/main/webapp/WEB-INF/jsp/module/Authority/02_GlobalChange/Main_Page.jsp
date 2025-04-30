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
	    "pagingType": "full_numbers"
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
					<input type="hidden" id="action" value="newdata">
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
             </div>
         </div>
         
          <div class="row">
            <div class=" col-md-2 col-lg-2 "> 
                <label>Enter Description :</label>
             </div>
             <div class="col-md-3 col-lg-3 charging_info">
					<input type="text" class="form-control criteria" name="criteria" id="criteria" placeholder="Enter Search Term">
             </div>
             <button type="button" class="btn btn-info" id="btn-submit-retrievemodal"onclick="getDetails(this);return send_title_info()" disabled>
				<span class="glyphicon glyphicon-search"></span> 
			</button>
					<button type="button" class="btn btn-info" id="refresh" title="refresh" onclick="refresh()">
				<span class="glyphicon glyphicon-refresh"></span>
			</button>
         </div>
      </div>
    </div>
  </div>
  </div>
  <div class="panel panel-default">
  
    <div id="accesioning" class="panel-collapse collapse in" >
      <div class="panel-body" id="searchcontent">
      <div id="loader"></div>
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
    <div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading">
  		Edit Bibliography Details
    </div>
    <div id="patrondet" class="panel-collapse collapse in">
      <div class="panel-body" id="patrDet">
      <div class="row">
            <div class=" col-md-10 col-lg-10 "> 
              <textarea cols="50" rows="7" class="form-control" id="changercrd">
              </textarea>
              <input type="hidden" id="pointer" value="">
                <input type="hidden" id="term" value="">
             </div>
             <div class=" col-md-2 col-lg-2 "> 
             <button type="button" class="btn btn-info" id="viewtag" title="Tag">
				<span class="glyphicon glyphicon-eye-open"></span>
			</button>
			<br/>
			  <button type="button" class="btn btn-info" id="accept" >
				<span class="glyphicon glyphicon-ok-circle"></span>
			</button>
			<br/>
			  <button type="button" class="btn btn-info" id="delete" title="Delete" disabled>
				<span class="glyphicon glyphicon-trash"></span>
			</button>
			<br/>

             </div>
            
         </div>

      </div>
    </div>
  </div>
  </div>
<div class="modal fade" id="modaltag" tabindex="-1" role="dialog" aria-labelledby="modaltag"  aria-hidden="true" data-backdrop="static"> 
				    <div class="modal-dialog" style="max-width:80%;overflow:auto">
						  <div class="modal-content" id="tagcontent">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				


</body>

</html>	