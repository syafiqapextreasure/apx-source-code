<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.PatronCategory.*, java.util.List" %>
<%@ page import="java.sql.*" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/PatronCategory.js"></script>	
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script> -->



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
		<h4 class="modal-title" id="myModalLabel" align="center">Add Patron Category</h4>
</div>
	
<!-- <form role="form" class="form-horizontal" method="post"> -->
<form role="form" id="patCatForm" class="form-horizontal" action="${pageContext.request.contextPath}/AddPatCate" method="post">
	<div class="modal-body" style="height:500px;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-12">
			
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 "><strong>Patron Category</strong></label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07CATE" name="GL07CATE" onkeyup="toUppercase()" required>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-4 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Description</strong></label>
                      <div class="col-sm-7">
                        <input type="text" class="form-control" id="GL07DESC" name="GL07DESC" onkeyup="toUppercase()" required>
                      </div>
                    </div>
                    
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Maximum Loan</strong></label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07ELIG" name="GL07ELIG" onkeyup="toUppercase()" value="0" required>
                      </div>
                    
                      <label for="inputPassword3" class="col-sm-3 "><strong>Maximum Fines</strong></label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07MAXFINE" name="GL07MAXFINE" onkeyup="toUppercase()" value="0" required>
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Fines Limit</strong></label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07FINELIMIT" name="GL07FINELIMIT" onkeyup="toUppercase()" value="0" required>
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Maximum ILL Outgoing Request Allowed</strong></label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07ILLOUT" name="GL07ILLOUT" onkeyup="toUppercase()"value="0"  required>
                      </div>
                    
                      <label for="inputPassword3" class="col-sm-3 "><strong>Maximum Reservation Allowed</strong></label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07MAXRESV" name="GL07MAXRESV" onkeyup="toUppercase()" value="0" required>
                      </div>
                    </div>
                    
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Renew Fee</strong></label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07RENEWFEE" name="GL07RENEWFEE" value="0"  required>
                      </div>
                    
                      <label for="inputPassword3" class="col-sm-3 "><strong>Renew Grace Period</strong></label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07RENEWGRC" name="GL07RENEWGRC" maxlength="3"   value="0" required>
                      </div>
                    </div>
                    
                    
                     <div class="form-group" id ="selectRadio">
        			 <label for="inputEmail3" class="col-sm-3 "><strong>Allow Overdue</strong></label>
                     <div class="radio">
                     
      					<label class="col-sm-1 ">
      					<input type="radio" class="minimal" name="GL07ALLOWOVD" id="GL07ALLOWOVD" value="Y" checked > Yes
        				</label>
        				
      					<label class="col-sm-1 ">
        				<input type="radio" class="minimal" name="GL07ALLOWOVD" id="GL07ALLOWOVD" value="N" > No
        				</label>
        			
    				 </div>
    				 </div>
    			
			
			
        			 <div class="form-group" id ="selectRadio">
        			 <label for="inputEmail3" class="col-sm-3 "><strong>Allow Reserve</strong></label>
                     <div class="radio">
      					<label class="col-sm-1 ">
      					<input type="radio" class="minimal" name="GL07ALLOWRSV" id="GL07ALLOWRSV" value="Y" checked > Yes
        				</label>
        				
      					<label class="col-sm-1 ">
        				<input type="radio" class="minimal" name="GL07ALLOWRSV" id="GL07ALLOWRSV"  value="N"> No
        				</label>
        			 </div>
    				 </div>
    				 
    				 
    			<div class="panel-group" id="accordion">
  					<div class="panel panel-default">
    				<div class="panel-heading">
      					<h4 class="panel-title">
        					<a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Subscription Plan</a>
      					</h4>
    				</div>
    				<div id="collapse1" class="panel-collapse collapse in">
      					<div class="panel-body">
      					
      					<div class="form-group" >
      						<div class="checkbox">
      						<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=subplan id="subplan">Subscription Plan
      						<!-- onclick="addPatCate()" -->
      						</label>
      						</div>  
      					</div>
      					
      					<div class="form-group" >
      						<label for="inputPassword3" class="col-sm-4">Maximum Patron Account Allowed</label>
                      		<div class="col-sm-2">
                        		<input type="text" class="form-control" id="GL07MAXACCT" name="GL07MAXACCT" disabled>
                      		</div>
                      	</div>
                        </div>
    				</div>
  					</div>
  					
  					<div class="panel panel-default">
    				<div class="panel-heading">
      					<h4 class="panel-title">
        					<a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
        					Category Profile</a>
      					</h4>
    				</div>
    				<div id="collapse2" class="panel-collapse collapse">
      					<div class="panel-body">
      					<div class="form-group" id ="selectCheckbox">
                     	<div class="checkbox">
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07POPDB" id="GL07POPDB" value="Y"> Popular Update Database
        					</label>
    				 	
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07RATER" id="GL07RATER" value="Y"> Review
        					</label>
    				 	</div>
    					</div> 
    					
    					<div class="form-group" id ="selectCheckbox">
                     	<div class="checkbox">
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07EMAIL" id="GL07EMAIL" value="Y"> Send E-Mail
        					</label>
    				 	
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07MODEM" id="GL07MODEM" value="Y"> Internet Login
        					</label>
    				 	</div>
    					</div> 
    					
    					<div class="form-group" id ="selectCheckbox">
                     	<div class="checkbox">
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07SCHAR" id="GL07SCHAR" value="Y"> Self Charging
        					</label>
    				 	
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07BRFORC" id="GL07BRFORC" value="Y"> Override Charging Restriction
        					</label>
    				 	</div>
    					</div> 
    					
    					<div class="form-group" id ="selectCheckbox">
                     	<div class="checkbox">
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07ARTXN" id="GL07ARTXN"> Archive Circulation Txn
        					</label>
    				 	
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07DCFORC" id="GL07DCFORC"> Override Fines - Charges
        					</label>
    				 	</div>
    					</div> 
    					</div>
    				</div>
  					</div>
 
				</div>
                    
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		<!-- <button type="submit" class="btn btn-primary" id="btn-add" style="width:80px;" onclick="addPatCate()">Add</button> -->
		 <input type="submit" class="btn btn-primary" value="Save" id="btn-add" style="width:80px;">
		 <a href="template?MODULE=Foundation/14_PatronCategory&ACTION=PatCategory.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
            </a>
	</div>			
	</form>
	

	