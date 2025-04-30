<%@ page contentType="text/html; charset=UTF-8" %>

<%@page import="com.wilmu.acquisition.receiving.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Maintenance/AAAM1350/AAAM1350.js"></script>	
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	
	
	
	
		
	<title>Receiving</title>
	
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	
  	<style>
	select.smd{
		width:80px;
	}
	select.location{
		width:80px;
	}
	select.icat{
		width:80px;
	}
	select.condition{
		width:80px;
	}

  		/* #bootstrapSelectForm .selectContainer .form-control-feedback {
    		/* Adjust feedback icon position */
		    right: -15px;
		} */
		
		/* table {
		    border-collapse: collapse;
		    border-spacing: 0;
		    max-width: 755px;
		    max-width: 100%;
		    width: 755px;
		}
		
		table.dataTable {
		    margin: 0 !important;
		} */
		
		table {
  margin: 15px 0;
  border: 1px solid black;
  table-layout: fixed;
  width: 100%; /* must have this set */
}
		
		/* table.dataTable,
		table.dataTable th,
		table.dataTable td {
		  -webkit-box-sizing: content-box;
		  -moz-box-sizing: content-box;
		  box-sizing: content-box;
		} */

		/*  #tab-content {
		  padding:10px;
		  border-left:1px solid #DDD;
		  border-bottom:1px solid #DDD;
		  border-right:1px solid #DDD;
		}  */
  	</style>
</head>

<body>
	<%
//session.setAttribute("pat2",request.getParameter("patr"));
%>
<div class="panel-group">
  <div class="panel panel-default">
    <div class="panel-heading">

        <a class="accordion-toggle" data-toggle="collapse" href="#statusMaint">
        </a>
    </div>
    <div class="container-fluid">

    <div id="detail" class="panel-collapse collapse in">
      	<div class="panel-body">
      		<div class="row">
       			<div class="col-md-12 col-lg-12">
       				<div class="form-group row">
						<label class="col-sm-2 col-form-label" for="OrderNO">Order No :</label>
							<div class="col-sm-3">
						    	<div class="input-group">
							    	<input type="text" maxlength="10" class="form-control" id="OrderNO">
							    	 <a class="input-group-addon btn btn-primary" id="getListReceving" data-toggle="modal" data-target="#searchrecv" href='Acquisition?type=Maintenance&module=AAAM1350&name=Modal_SearchReceiving'><span class="glyphicon glyphicon-search"></span></a>
								   <!--  <a class="input-group-addon btn btn-primary" id="getListReceving" data-toggle="modal" data-target="#getListRecevingDetail"><span class="glyphicon glyphicon-search"></span></a> -->
								   <!-- <button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchOdrMaint' href='Modal_SearchOdrMaint'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="orderMaint"></i></button> -->
						    		<!-- <button type="button" class="btn btn-primary" id="getListReceving" data-toggle="modal" data-target="getListReceving"><span class="fa fa-info-circle" style="color:white" title="Bib Details"></span></button> -->
												<!-- <button type="button" class="btn btn-primary" id="getListReceving" data-toggle="modal" 
												data-target="#getListRecevingDetail"><span class="fa fa-eye" style="color:white" title="View"></span></button> -->
						    	</div>
						    </div>
					</div>
					
					<div class="form-group row">
							<span class="vendCode"></span>
							
							<div class="form-group row">
								<label for="ISBN" class="col-sm-2 col-form-label">ISBN</label>
									<div class="col-sm-3">
										<span class="ISBN"></span>
									</div>
								<label for="ControlNo" class="col-sm-2 col-form-label">Control No</label>
									<div class="col-sm-2">
									 	<span class="ControlNo"></span>
									</div>
								<!-- <label for="CallNo" class="col-sm-2 col-form-label">Call No</label>
									<div class="col-sm-2">
										<span class="CallNo"></span>
									</div> -->
							</div>
							
							<div class="form-group row">
								<label for="Title" class="col-sm-2 col-form-label">Title</label>
									<div class="col-sm-3">
										<span class="Title"></span>
									</div>
								<label for="CallNo" class="col-sm-2 col-form-label">Call No</label>
									<div class="col-sm-2">
										<span class="CallNo"></span>
									</div>
							</div>
							<br>
							<div class="form-group row">
								    	<label for="ReferenceNo" class="col-sm-2 col-form-label">Reference No</label>
										    <div class="col-sm-3">
										      	<span class="ReferenceNo"></span>
										    </div>
								    	<label for="Vendor" class="col-sm-2 col-form-label">Vendor</label>
									    	<div class="col-sm-3">
									      		<span class="VendorR"></span>
									    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="Mode" class="col-sm-2 col-form-label">Mode</label>
										    <div class="col-sm-3">
										      	<span class="Mode"></span>
										    </div>
								    	<label for="Status" class="col-sm-2 col-form-label">Status</label>
									    	<div class="col-sm-3">
									      		<span class="Status"></span>
									    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="OrderDate" class="col-sm-2 col-form-label">Order Date</label>
										    <div class="col-sm-3">
										      	<span class="OrderDate"></span>
										    </div>
								    	<label for="ExpectedDate" class="col-sm-2 col-form-label">Expected Date</label>
									    	<div class="col-sm-3">
									      		<span class="ExpectedDate"></span>
									    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="Set" class="col-sm-2 col-form-label">Set</label>
										    <div class="col-sm-3">
										      	<span class="Set"></span>
										    </div>
								    	<label for="Copies" class="col-sm-2 col-form-label">Copies</label>
									    	<div class="col-sm-3">
									      		<span class="Copies"></span>
									    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="Received" class="col-sm-2 col-form-label">Received</label>
										    <div class="col-sm-3">
										      	<span class="Received"></span>
										    </div>
								    	<label for="RecordedBy" class="col-sm-2 col-form-label">Recorded By</label>
									    	<div class="col-sm-3">
									      		<span class="RecordedBy"></span>
									    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="Currency" class="col-sm-2 col-form-label">Currency</label>
										    <div class="col-sm-3">
										      	<span class="Currency"></span>
										    </div>
								    	<label for="PubRate" class="col-sm-2 col-form-label">Pub. Rate</label>
									    	<div class="col-sm-3">
									      		<span class="PubRate"></span>
									    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="FPrice" class="col-sm-2 col-form-label">F. Price</label>
										    <div class="col-sm-3">
										      	<span class="FPrice"></span>
										    </div>
								    	<label for="LocalPrice" class="col-sm-2 col-form-label">Local Price</label>
									    	<div class="col-sm-3">
									      		<span class="LocalPrice"></span>
									    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="TotalFPrice" class="col-sm-2 col-form-label">Total F. Price</label>
										    <div class="col-sm-3">
										      	<span class="TotalFPrice"></span>
										    </div>
								    	<label for="TotalLocalPrice" class="col-sm-2 col-form-label">Total Local Price</label>
									    	<div class="col-sm-3">
									      		<span class="TotalLocalPrice"></span>
									    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="InvoiceNo" class="col-sm-2 col-form-label">Invoice No</label>
										    <div class="col-sm-3">
										      	<span class="InvoiceNo"></span>
										    </div>
								    	<label for="InvoiceDate" class="col-sm-2 col-form-label">Invoice Date</label>
									    	<div class="col-sm-3">
									      		<span class="InvoiceDate"></span>
									    	</div>
								  	</div>
								  	
								  	<br>
								  	<div class="form-group row" id="copy">
								  		<label for="CopyReceived" class="col-sm-2 col-form-label">Copy Received</label>
										    <div class="col-sm-1">
										      	<input type="text" class="form-control" id="CopyReceived">
										    </div>
										<label for="DateReceived" class="col-sm-2 col-form-label">Date Received</label>
											<div class="col-sm-2">
									    		<div class="input-group date" id="DateReceived">
										    		<input type="text" class="form-control" id="DateRec" name="DateRec">
												    	<span class="input-group-addon">
				  											<i class="glyphicon glyphicon-calendar"></i>
				  										</span>
									    		</div>
						    				</div>
								  	</div>
								  	
								  	<br>
								  	<div class="form-group row" id="set">
								  		<label for="SetReceived" class="col-sm-2 col-form-label">Set Received</label>
										    <div class="col-sm-1">
										      	<input type="text" class="form-control" id="SetReceived">
										    </div>
										<label for="CopyReceivedPerSet" class="col-sm-2 col-form-label">Copies Received Per Set</label>
										    <div class="col-sm-1">
										      	<input type="text" class="form-control" id="CopyReceivedPerSet">
										    </div>
										<label for="DateReceivedSet" class="col-sm-2 col-form-label">Date Received</label>
											<div class="col-sm-2">
									    		<div class="input-group date" id="DateReceivedSet">
										    		<input type="text" class="form-control" id="DateRecSet" name="DateRecSet">
												    	<span class="input-group-addon">
				  											<i class="glyphicon glyphicon-calendar"></i>
				  										</span>
									    		</div>
						    				</div>
								  	</div>
								  									  	
								  	<div class="form-group row">
								  		<div class="col-sm-5">
								  			<button  id='generate' class="btn btn-primary"><span class="fa fa-credit-card" style="color:white" title="Generate Accession No"></span></button>
								  			<button  id='save' class="btn btn-primary"><span class="glyphicon glyphicon-save" style="color:white" title="Save"></span></button>
								  			<!-- <button  id='note' class="btn btn-primary"><span class="fa fa-fw fa-file-text-o" style="color:white" title="View Note"></span></button> -->
						   				    <button  id='discard' class="btn btn-primary"><span class="fa fa-times" style="color:white" title="Discard"></span></button>
								  		</div>
								  		
								  	</div>
								  	
								  	<div class="form-check" align="center">
									    <input type="checkbox" class="form-check-input" id="requestor" name="checkReceiving" value="requestor">
									    <label class="form-check-label" for="requestor">Print receival letter to requestor</label>

									    <input type="checkbox" class="form-check-input" id="vendor" name="checkReceiving" value="vendor">
									    <label class="form-check-label" for="vendor">Print receival letter to vendor</label>

									    <input type="checkbox" class="form-check-input" id="reservation" name="checkReceiving" value="reservation">
									    <label class="form-check-label" for="reservation">Place reservation for requestor</label>
									</div>
								  	<div class="row" id="tab-content">
           							<table class="table table-hover table-responsive" id="table-receivingDetail"  style="width:100%">
           					
           								<thead>
           									<tr> 
           										<th></th>
           										<th>Accession</th>
           										<th>SMD</th>
           										<th>Item Category</th>
           										<th>Location</th>
           										<th>Condition</th>
           										<th>Foreign Price</th>
           										<th>Local Price</th>
           										<th>Volume</th>
           										<th>Copy No</th>
           									</tr>
           								</thead>
           								<tbody>
	           								<tr>
	           									<td id="NOCOPY"> 1</td>
	           									<td><input type="text" maxlength="10" class="form-control accno" id="Accession" size="10"></td>
	           									<td>
	           										<jsp:include page="../../../../include/shared/Selection/Select_SMD.jsp"></jsp:include>
	
					      						</td>
	           									<td>
	           										<jsp:include page="../../../../include/shared/Selection/Select_ItemCate.jsp"></jsp:include> 
	           									
	           									</td>
	           									<td>
	           										<jsp:include page="../../../../include/shared/Selection/Select_Loca.jsp"></jsp:include>
	           									</td>
	           									<td>
	           											<jsp:include page="../../../../include/shared/Selection/Select_Condition.jsp"></jsp:include>

	           									</td>
	           									<td><input type="text" class="form-control foreign" size="7"></td>
	           									<td><input type="text" class="form-control local" size="7"></td>
	           									<td><input type="text" maxlength="80" class="form-control" id="Volume" size="5"></td>
	           									<td><input type="text" maxlength="20" class="form-control" id="Copy" size="5"></td>
	           								</tr> 
           									
           								</tbody>
           							</table>
								</div>
								
								<input type="hidden" id="retainSMD">
								<input type="hidden" id="retainitemcategory">
								<input type="hidden" id="retainlocation">
								<input type="hidden" id="retaincondition">
								
								<div class="form-group row">
								  		<label class="col-sm-2 col-form-label">Retain Value</label>
								  		<div class="col-sm-5">
									  		<div class="form-check">
											    <input type="checkbox" class="form-check-input" id="smdcheck" name="Retain" value="smdcheck">
											    <label class="form-check-label" for="smdcheck">SMD</label>
		
											    <input type="checkbox" class="form-check-input" id="itemcategorycheck" name="Retain" value="itemcategorycheck">
											    <label class="form-check-label" for="itemcategorysmdcheck">Item Category</label>
		
											    <input type="checkbox" class="form-check-input" id="locationcheck" name="Retain" value="locationcheck">
											    <label class="form-check-label" for="locationcheck">Location</label>
											    
											    <input type="checkbox" class="form-check-input" id="conditioncheck" name="Retain" value="conditioncheck">
											    <label class="form-check-label" for="conditioncheck">Condition</label>
											</div>
								  		</div>
								  	</div>
					</div>
					<!-- <input type="text" class="form-control test" id="test"> -->
       			</div>   		
     		</div>
      	</div>
    </div>
    
    <!-- MODAL WHEN CLICK Search getListReceving -->
    <!-- <div class="modal fade" id="getListRecevingDetail" tabindex="-1" role="dialog" aria-labelledby="getListRecevingDetail" aria-hidden="true"> 
		<div class="modal-dialog" style="width:80%;">
			    <div class="modal-content" id="getListRecevingContent">
				  Remote content load here
			  	</div>
		</div>
	</div> -->
	
	<!-- Modal for search -->
		<div class="modal fade" id="searchrecv" tabindex="-1" role="dialog" aria-labelledby="searchrecv" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:65%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>	
    
    </div>
  </div>
  </div>
</body>

</html>