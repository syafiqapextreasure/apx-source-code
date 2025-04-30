<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/enquiry/SerialsHolding.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script> 
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
   <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>

	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/jquery.treegrid.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.treegrid.css">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-treeview.min.css">
    <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-treeview.min.js"></script>
    
	<style type="text/css">	
		.center {
			text-align: center;
		}	
		
		.right {
			text-align: right;
		}
		
		#red {
			color: red;
		}	
		
		fieldset.scheduler-border {
		    border: 1px groove #ddd !important;
		    padding: 0 1.4em 1.4em 1.4em !important;
		    margin: 0 0 1.5em 0 !important;
		    -webkit-box-shadow:  0px 0px 0px 0px #000;
		            box-shadow:  0px 0px 0px 0px #000;
		}

	    legend.scheduler-border {
	        font-size: 1.2em !important;
	        font-weight: bold !important;
	        text-align: left !important;
	        width:auto;
	        padding:0 10px;
	        border-bottom:none;
	    }	
	    
	    #modalWeed {
		    overflow-y: scroll;
		}
		
		.fundtable {
		   margin: auto;
		   width: 50% !important; 
		}
		
		tr{
		    border-top: hidden;
		}
		
		#treeview {
	height: 400px;
}

.options {
    padding: 20px;
    position: absolute;
    bottom: 0;
    right: 0;
    width: 260px;
    top: 0;
    background-color: #f5f5f5;
}

.caption {
    font-size: 18px;
    font-weight: 500;
}

.option {
    margin-top: 10px;
}

.option > .dx-selectbox {
    display: inline-block;
    vertical-align: middle;
    max-width: 350px;
    width: 100%;
    margin-top: 5px;
}
		
	</style>
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title pull-left"></h3>
						<!-- <button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchWeed'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="Weeding"></i></button> -->
						<div class="clearfix"></div>
					</div>
					
					<div class="panel-body" id="display_Fund">
						<form class="form-horizontal" name="scanFund" id="scanFund">
							
							<fieldset class="scheduler-border">
							<legend class="scheduler-border"></legend>
								<div class="row">
									<div class="form-group">
										<div class="col-sm-2">
											<label><input type="radio" name="radioOption" value="issn">&nbsp ISSN</label>
										</div>
										<div class="col-sm-3">
											<input type="text" class="form-control" id="textisn" name="textisn">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-2">
											<label><input type="radio" name="radioOption" value="ctrlno">&nbsp Control No</label>
										</div>
										<div class="col-sm-3">
											<div class="input-group">
													<input type="text" class="form-control" id="textctrlno" name="textctrlno">
														<a href="Modal_Vendor" id="searchCtrl" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#modal_vendorSearch">
														<span class="glyphicon glyphicon-option-horizontal"></span></a>
											</div>
										</div>
									</div>
								</div>
								
								<div class="btn-group pull-right ">	
									<div class="col-md-1"><button type="button" id="serHolReterive" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button></div>
								</div>
							</fieldset>
							
							<fieldset class="scheduler-border">
							<legend class="scheduler-border"></legend>
								<div class="row">	
								
									<div class="form-group">
										<div class="col-sm-1"><label>Title</label></div>
										<div class="col-sm-9">
											<input type="text" class="form-control title" disabled>
										</div>
									</div>
								
															

							        <div class="col-sm-4">
							          <div id="treeview12" class=""></div>
							        </div>
							        
							         <div id="treeview"></div>
        
								</div>
							</fieldset>
							</form>						
						
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
		


</body>
</html>