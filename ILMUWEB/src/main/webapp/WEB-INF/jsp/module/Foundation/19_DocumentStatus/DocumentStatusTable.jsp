<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.ilmu.foundation.CurrencyCode.SQLStatement"%>
<%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%>
<%@page import="java.util.List,com.ilmu.global.*"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/DocumentStatus.js"></script>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/validateDocumentStatus.js"></script>	 --%>
</head>
<body>
	<div class="box box-default">
    <div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title pull-left"></h3>
        <button type="button" class="btn btn-primary pull-right" id="add" data-toggle='modal' data-target="#modalDocStatus" data-whatever="Add">
        <i class="glyphicon glyphicon-plus" title="Add"></i></button>
		<div class="clearfix"></div>
    </div>
    <div class="panel-body" id="display_DocStatus">
    
	<table id="docStatus" class="table table-bordered table-striped">
    	<thead>
 			<tr>
				<th>Status</th>
         		<th>Description</th>
         		<th>Action</th>
     		</tr>
   		</thead>
       	<tbody>
   		</tbody>
	</table>           
    </div>
	</div>
	</div> 

	<!-- Start Modal Add, Edit, View -->
			<div class="modal fade" id="modalDocStatus" tabindex="-1" role="dialog" aria-labelledby="modalDocStatus" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			  <div class="modal-dialog" role="document" style="width:55%;overflow:auto">
			    <div class="modal-content">
					<!-- Modal content-->	
					      <div class="modal-header">
					        <h5 class="modal-title" id="modalName" align="center">form</h5>
					        <button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body">
					        <form role="form" class="form-horizontal" id="formdata" method="post">
					        	<input type="hidden" name="userid" <%-- value="<%= username %>"" --%>>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3">
									<label for="docStatus">Document Status </label>
								</div>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="documentStatus" name="documentStatus">
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3">
									<label for="desc">Description </label>
								</div>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="desc" name="desc">
								</div>
							</div>
							
							<div class="form-group chgto1">
								<div class="col-sm-1"></div>
								<div class="col-sm-3">
									<label for="chgto">Change To </label>
								</div>
								<div class="col-sm-6">
									<span class="chgto"></span>
									<input type="hidden" id="chgto3" name="chgto3">
									<!-- <input type="text" class="form-control" id="chgto" name="chgto" readonly> -->
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"></div>
								<div class="col-sm-6"  id="test">
									<span class="chgValTick"></span>
								</div>
							</div>
							
							<!-- <div class="form-check">
								<div class="col-sm-4"></div>
								<div class="col-sm-6">
									<span class="chgVal"></span>
								</div>
									
							</div> -->
							
							<div class="form-group modal-footer">
						      	<button type="submit" id="save" class="btn btn-primary" onClick="testing()">Save</button>
						        <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						        <button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
					        
					      	</div>
					        </form>
					      </div>
			<!-- End Modal content -->
			</div>
		  </div>
		</div>
	<!-- End Modal Add, Edit, View -->
</body>
</html>