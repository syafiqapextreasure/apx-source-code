<%@ page import="com.ilmu.foundation.global.*, com.ilmu.foundation.DepartmentCode.*, java.util.List" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/GeneralSubject.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Checking.js"></script>

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
		<h4 class="modal-title" id="myModalLabel" align="center">View General Subject</h4>
</div>
	
<form role="form" class="form-horizontal">
	<div class="modal-body" style="height:500px;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
			<div class="form-group">
                  <label for="inputEmail3" class="col-sm-3 control-label">Department Code</label>
                  <div class="col-sm-5 col-md-3">
                     <input type="text" class="form-control" id="GL11DEPT" name="GL11DEPT" value="" readonly>
                  </div>
             </div>
                    
             <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label">Department Name</label>
                  <div class="col-sm-7">
                      <input type="text" class="form-control" id="GL11NAME" name="GL11NAME" value="" readonly>
                  </div>
             </div>
             
             <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label">Address</label>
                  <div class="col-sm-7">
                      <input type="text" class="form-control" id="GL11ADD1" name="GL11ADD1" value="" readonly>
                  </div>
             </div>
             <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label"></label>
                  <div class="col-sm-7">
                      <input type="text" class="form-control" id="GL11ADD2" name="GL11ADD2" value="" readonly>
                  </div>
             </div>
             <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label"></label>
                  <div class="col-sm-7">
                      <input type="text" class="form-control" id="GL11ADD3" name="GL11ADD3" value="" readonly>
                  </div>
             </div>
             
             <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label">Postcode</label>
                  <div class="col-sm-3">
                      <input type="text" class="form-control" id="GL11POSCODE" name="GL11POSCODE" value="" readonly>
                  </div>
             </div>
            
                   
                    
                   
                    
		
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		 <a href="template?MODULE=Foundation/09_GeneralSubject&ACTION=SubjTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal" onClick="window.location.reload()">Close</button>
            </a>
	</div>			
	</form>
	

	