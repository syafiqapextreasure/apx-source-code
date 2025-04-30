<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>

<head>

 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Authority/Global_Merge.js"></script>			
</head>
<script>
  	$(document).ready(function () {
  		$("[title]").tooltip();
	$('#globalchgtbl').DataTable({
	    responsive: true,
	    "pagingType": "full_numbers",
	    stateSave: true,
	});

});
	</script>
		
<body>

<div class="panel-group" id="accordion">
 <div class="panel panel-default">
    <div class="panel-heading" style="height:50px">
  		<div class="btn-group pull-right">
  		 <button type="button" class="btn btn-info" id="merge" title="Start Merge">
			<span class="glyphicon glyphicon-resize-small"></span>
		</button>
		<button type="button" class="btn btn-info" id="cancel" title="refresh">
			<span class="glyphicon glyphicon-refresh"></span>
		</button>
  		</div>
    </div>
   
    <div id="patrondet" class="panel-collapse collapse in">
      <div class="panel-body" id="patrDet">
		 <div class="row">
		  <div class="col-sm-6 mb-3 mb-md-0">

		    <div class="panel panel-default">
		     <div class="panel-heading clearfix" >
			    <div class="panel-title pull-left">
			    	<h4>Record to merge from</h4>
			    </div>
			    <div class="btn-group pull-right">
			    <button type="button" class="btn btn-info" id="retrievefrom" data-action="fromTerm">
					<span class="glyphicon glyphicon-search"></span> 
				</button>
			    </div>
		    </div>
		 	 <div class="panel-body">
		 	 	<table class="table table-bordered" id="listable">
		 	 		<thead>
		 	 			<tr>
			 	 			<th>Term</th>
			 	 			<th>Action</th>
			 	 		</tr>
		 	 		</thead>
		 	 		<tbody id="fromTerm">
		 	 		</tbody>
		 	 	</table>
		 	 </div>
			</div>
		</div>

  <div class="col-sm-6">
   <div class="panel panel-default">
   <div class="panel-heading clearfix" >
	 <div class="panel-title pull-left">
		<h4>Record to merge to</h4>
	</div>
	<div class="btn-group pull-right">
		<button type="button" class="btn btn-info" id="retrieveto" data-action="toterm">
		   <span class="glyphicon glyphicon-search"></span> 
		</button>
	 </div>
	</div>
	<input type="hidden" value="" id="topointer"><input type="hidden" value="" id="totag">
	<div class="panel-body" id="toterm" style="height:40%"></div>
	</div>
  </div>
	</div>
  </div>
    </div>
    </div>
  </div>
  
<div class="modal fade retrieval" id="retrieval" tabindex="-1" role="dialog" aria-labelledby="retrieval"  aria-hidden="true" data-backdrop="static"> 
				    <div class="modal-dialog" style="width:80%;overflow:auto">
						  <div class="modal-content" id="retrievalcontent">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				


</body>

</html>	