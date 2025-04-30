<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.PatronStatus.*, java.util.List" %>
<%@ page import="java.sql.*" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/PatronStatus.js"></script>	
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->



<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Add Patron Status</h4>
</div>
	
<!-- <form role="form" class="form-horizontal" method="post"> -->
<form role="form" id="statusForm" class="form-horizontal" action="${pageContext.request.contextPath}/AddPatronStatus"  method="post">
	<div class="modal-body" style="height:45%;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 "><strong>Patron Status</strong></label>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL08STAT" name="GL08STAT" maxlength="3" onkeypress="toUppercase()" required>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Description</strong></label>
                      <div class="col-sm-7">
                        <input type="text" class="form-control" id="GL08DESC" name="GL08DESC" onkeypress="toUppercase()" required>
                      </div>
                    </div>
                    
                     <div class="form-group" id ="selectCheckbox">
                     <div class="checkbox">
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name=GL08ACTION1 id="GL08ACTION1" value="Y"> CHARGING
        				</label>
      					
      					<label class="col-sm-3 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION2" id="GL08ACTION2" value="Y"> DISCHARGING
        				</label>
      					
      					<label class="col-sm-3 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION3" id="GL08ACTION3" value="Y"> RENEWAL
        				</label>
    				 </div>
    				 
    				 <div class="checkbox">
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION4" id="GL08ACTION4" value="Y"> RESERVATION
        				</label>
      					
      					<label class="col-sm-3 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION5" id="GL08ACTION5" value="Y"> REVIEW
        				</label>
      					
      					<label class="col-sm-3 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION6" id="GL08ACTION6"  value="Y"> MESSAGE
        				</label>
    				 </div>
    				 
    				 <div class="checkbox">
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION7" id="GL08ACTION7" value="Y"> MODEM CONNECTION
        				</label>
      					
      					<label class="col-sm-3 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION8" id="GL08ACTION8" value="Y"> RECALLS
        				</label>
      					
      					<label class="col-sm-3 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION9" id="GL08ACTION9" value="Y"> NOT ASSIGNED
        				</label>
    				 </div>
    				 
    				 <div class="checkbox">
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION10" id="GL08ACTION10" value="Y"> ILMU LOGIN
        				</label>
      					
      					<label class="col-sm-3 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION11" id="GL08ACTION11" value="Y"> NOT ASSIGNED
        				</label>
      					
      					<label class="col-sm-3 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION12" id="GL08ACTION12" value="Y"> NOT ASSIGNED
        				</label>
    				</div>
    				
    				<div class="checkbox">
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION13" id="GL08ACTION13" value="Y"> NOT ASSIGNED
        				</label>
      					
      					<label class="col-sm-3 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION14" id="GL08ACTION14" value="Y"> NOT ASSIGNED
        				</label>
      					
      					<label class="col-sm-3 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION15" id="GL08ACTION15" value="Y"> NOT ASSIGNED
        				</label>
    				</div>
    				
    				<div class="checkbox">
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION16" id="GL08ACTION16" value="Y"> NOT ASSIGNED
        				</label>
      					
      					<label class="col-sm-3 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION17" id="GL08ACTION17" value="Y"> NOT ASSIGNED
        				</label>
      					
      					<label class="col-sm-3 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION18" id="GL08ACTION18" value="Y"> NOT ASSIGNED
        				</label>
      				</div>
      				
    				<div class="checkbox">
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION19" id="GL08ACTION19" value="Y"> NOT ASSIGNED
        				</label>
        				
      					<label class="col-sm-3 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION20" id="GL08ACTION20" value="Y"> NOT ASSIGNED
        				</label>
    				</div>
    				</div>
                    
                   
              
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		<!-- <button type="submit" class="btn btn-primary" id="btn-add" style="width:80px;" onclick="addStat()">Add</button> -->
		 <button type="submit" class="btn btn-primary" id="addBtn" style="width:80px;">Add</button>
		 <a href="template?MODULE=Foundation/10_PatronDetails&ACTION=PatronTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal" onClick="window.location.reload()">Cancel</button>
            </a>
	</div>			
	</form>
	

	