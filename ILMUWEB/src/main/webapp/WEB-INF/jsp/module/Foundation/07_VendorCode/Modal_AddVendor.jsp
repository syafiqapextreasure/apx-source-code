<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.VendorCode.*, java.util.List" %>
<%@ page import="java.sql.*" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/VendorCode.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Delete.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> --%>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Add Vendor Code</h4>
</div>
	
<!-- <form role="form" class="form-horizontal" method="post"> -->
<form role="form" id="vendorForm" class="form-horizontal" action="${pageContext.request.contextPath}/AddVendor" method="post">
	<div class="modal-body" style="height:500px;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-12">
			
                    <div class="form-group">
                      <label for="GL39CODE" class="col-sm-3 "><strong>Vendor Code</strong></label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL39CODE" name="GL39CODE" onkeyup="toUppercase()">
                      
                      </div>
                      
                      <div class="col-sm-7 col-md-7">
                      <div class="checkbox">
      						<label class="col-sm-2 ">
      						<input type="checkbox" class="minimal" name=GL39BINDER id="GL39BINDER" value="Y">Binder
      						</label>
      				  
      						<label class="col-sm-2 ">
      						<input type="checkbox" class="minimal" name=GL39SUPPLIER id="GL39SUPPLIER" value="Y">Vendor
      						</label>
      				   
      		
      						<label class="col-sm-2 ">
      						<input type="checkbox" class="minimal" name=GL39PUB id="GL39PUB" value="Y">Publisher
      						</label>
      				  </div> 
      				  </div> 
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-4 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Vendor Name</strong></label>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39NAME" name="GL39NAME" onkeyup="toUppercase()">
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Person In Charge</label>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39PERINC" name="GL39PERINC" onkeyup="toUppercase()" >
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Designation</label>
                      <div class="col-sm-5">
                        <input type="text" class="form-control" id="GL39DESIG" name="GL39DESIG" onkeyup="toUppercase()" >
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Address</label>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39ADD1" name="GL39ADD1"  >
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "></label>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39ADD2" name="GL39ADD2"  >
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "></label>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39ADD3" name="GL39ADD3" >
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Poscode</label>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39PCODE" name="GL39PCODE" maxlength="5">
                      </div>
                    
                      <label for="inputPassword3" class="col-sm-2 ">IP Address</label>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39IPADD" name="GL39IPADD" >
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Telephone No.</label>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39TELNO" name="GL39TELNO" >
                      </div>
                    
                      <label for="inputPassword3" class="col-sm-2 ">Fax/Telex No.</label>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39FAX" name="GL39FAX" >
                      </div>
                    </div>
                    
                     <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Contract No.</label>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39CONTNO" name="GL39CONTNO" >
                      </div>
                      </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Contract Begin</label>
                      <div class='col-sm-3'>
                      		<div class="input-group date">
  						 		<input type="text" class="form-control" id="GL39CBDATE" name="GL39CBDATE" onchange="CompareDate()">
  						 		<span class="input-group-addon">
  								<i class="glyphicon glyphicon-calendar"></i></span>
							</div>
        			  </div>
                    
                      <label for="inputPassword3" class="col-sm-2 ">Contract End</label>
                      <div class='col-sm-3'>
                      		<div class="input-group date">
  						 		<input type="text" class="form-control" id="GL39CEDATE" name="GL39CEDATE" onchange="CompareDate()">
  						 		<span class="input-group-addon">
  								<i class="glyphicon glyphicon-calendar"></i></span>
							</div>
        			  </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Username</label>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39USERNAME" name="GL39USERNAME">
                      </div>
                    
                      <label for="inputPassword3" class="col-sm-2 ">Password</label>
                      <div class="col-sm-3">
                        <input type="password" class="form-control" id="GL39PASSWORD" name="GL39PASSWORD" >
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Bank Account No.</label>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39ACCNO" name="GL39ACCNO" >
                      </div>
                    
                      <label for="inputPassword3" class="col-sm-2 ">Bank</label>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39BANK" name="GL39BANK" >
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Email</label>
                      <div class="col-sm-5">
                        <input type="email" class="form-control" id="GL39EMAIL" name="GL39EMAIL" placeholder="testing@yahoo.com">
                      </div>
                    
                      <div class="checkbox">
      					<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=GL39INDI id="GL39INDI" value="Y">Active
      					</label>
      				  </div>  
      				</div>
      				
      				<div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Remarks</label>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39REMARK" name="GL39REMARK" >
                      </div>
                    </div>
                    
                     
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		<!-- <button type="submit" class="btn btn-primary" id="btn-add" style="width:80px;" onclick="addVendor()">Save</button> -->
		<input type="submit" class="btn btn-primary" value="Save" id="btn-add" style="width:80px;">
		<a href="template?MODULE=Foundation/07_VendorCode&ACTION=VendorTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
            </a>
	</div>			
	</form>
	
<script type="text/javascript">
  $('.input-group.date').datepicker({
	    format: "dd/mm/yyyy",
	    todayBtn: true,
	    autoclose: true,
	    todayHighlight: true
	    
	    
	});
  </script> 
	