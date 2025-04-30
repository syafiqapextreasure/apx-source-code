<!DOCTYPE html>
<html lang="en">
<head>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Renewal.js"></script>					
  <title>Charging</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>
  	.charging_info {
    padding-top: 20px;
    margin: 10px 0 20px 0;
    background-color: rgba(214, 224, 226, 0.2);
    border-top-width: 0;
    border-bottom-width: 2px;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
    -webkit-box-shadow: none;
    -moz-box-shadow: none;
    box-shadow: none;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
  </style>
</head>
<body>
	  <div class="panel panel-default">
            <div class="panel-heading">
					<a class="btn btn-primary" id='enableSelect' title="Borrower's Info"><span class="glyphicon glyphicon-info-sign" style="color:white"></span></a>
					<a class="btn btn-primary" id='retrieveLink' data-toggle='modal' data-target='#titleSearch' href='RetrieveTitleModal?action=7' title="Fine"><span class="glyphicon glyphicon-usd" style="color:white"></span></a>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-2 col-lg-2 " align="center"> 
                	<img alt="User Pic" style="border: 2px solid black;" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png" class="img-circle img-responsive"> 
                	</div>
                <div class=" col-md-6 col-lg-6 "> 
                  <table class="table accession">
                    <tbody>
                      <tr>
                        <td>
                        	<label>Patron ID:</label>
                        </td>
                        <td><input class="form-control" id="GL14PATR" type="text"></td>
                      </tr>
                      <tr>
                        <td>
                        	<label>Name:</label>
                        </td>
                        <td class="GL14NAME"></td>
                      </tr>
                      <tr>
                        <td>
                        	<label>Patron Category:</label>
                        </td>
                        <td class="GL14CATE"></td>
                      </tr>
                       <tr>
                        <td>
                        	<label>Status:</label>
                        </td>
                        <td class="GL14STAT"></td>
                      </tr>
                      <tr>
                        <td>
                        	<label>Membership Date:</label>
                        </td>
                        <td class="GL14MEMDATE"></td>
                      </tr>
                      <tr>
                        <td>
                        	<label>Expiring Date:</label>
                        </td>
                        <td class="GL14EXPDATE"></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="col-md-4 col-lg-4 charging_info" style="border:1px solid #ddd;">
                	<div class="row">
                	<div class="form-group">
                		<div class="col-md-4">
                		<label>Item onloan</label>
                		</div>
                		<div class="col-md-2">
                		<label>: 4</label>
                		</div>
                	</div>
                	</div>
                	<div class="row">
                	<div class="form-group">
                		<div class="col-md-4">
                		<label>Item overdue</label>
                		</div>
                		<div class="col-md-2">
                		<label>: 3</label>
                		</div>
                	</div>
                	</div>
                	<div class="row">
                	<div class="form-group">
                		<div class="col-md-4">
                		<label>Item reserved</label>
                		</div>
                		<div class="col-md-2">
                		<label>: 2</label>
                		</div>
                	</div>
                	</div>
                	<div class="row">
                	<div class="form-group">
                		<div class="col-md-4">
                		<label>Item onhold</label>
                		</div>
                		<div class="col-md-2">
                		<label>: 1</label>
                		</div>
                	</div>
                	</div>
                	<div class="row">
                	<div class="form-group">
                		<div class="col-md-4">
                		<label>Fines</label>
                		</div>
                		<div class="col-md-2">
                		<label>: 8</label>
                		</div>
                	</div>
                	</div>
                </div>
              </div>
            </div>
            </div>
			 <div class="panel panel-default">
			    <div class="panel-body">
				  <div class="row">
				    <div class="col-md-12">		
						<div class="form-group">
							<div class="col-sm-3 col-md-3">
								<label>Accession No.:</label>
							</div>
							<div class="col-sm-3">
								 <input type="text" class="form-control" id="CT03DOCNO" maxlength="10" value="" max-length="10">
							</div>
							<div class="col-sm-2">
								<input type="button" class="btn btn-dull" id="button-generateAccession" onclick="generateAcc()" value="..." >
							 </div>
							 <div class="col-sm-2">
									<a class="btn btn-primary" id='retrieveLink' data-toggle='modal' data-target='#titleSearch' href='RetrieveTitleModal?action=7' title="Charge"><span class="glyphicon glyphicon-export" style="color:white"></span></a>
							 </div>
						</div>
					</div>
					</div>
				  </div>
			  	</div>
			  	<div class="panel panel-default">
				    <div class="panel-body">
				    	<table class="table table-bordered" id="table-charging">	
						<thead>
							<tr>
								<th data-sortable="true">Accession No.</th>
								<th data-sortable="true">Title</th>
								<th data-sortable="true">Charge Date</th>
								<th data-sortable="true">Charge Time</th>
								<th data-sortable="true">Due Date</th>
								<th data-sortable="true">Due Time</th>
								<th data-sortable="true">Status</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
					  </div>
			  	</div>
</body>
</html>

