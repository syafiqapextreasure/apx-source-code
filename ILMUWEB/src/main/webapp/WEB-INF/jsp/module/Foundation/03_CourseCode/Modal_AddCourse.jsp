<%@ page import="com.ilmu.cataloging.Acc_Maint.*, com.ilmu.foundation.global.*,com.ilmu.foundation.CourseCode.*, java.util.List" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/CourseCode.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Delete.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Checking.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/plugin/jquery-2.1.4.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Checking.js"></script>


<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> --%>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script> -->

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>




<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Add Course Code</h4>
</div>
	
<!-- <form id="courseCodeForm" class="form-horizontal"  method="post"> -->
<form role="form" id="courseCodeForm" class="form-horizontal" action="${pageContext.request.contextPath}/AddCourseCode" method="post">
	<div class="modal-body" style="height:40%;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
            
                    <div class="form-group">
                    	<label for="GL12COURSE" class="col-sm-3 "><strong>Course Code</strong></label>
                      	<div class="col-sm-5 col-md-3">
                         	<input type="text" class="form-control" id="GL12COURSE" name="GL12COURSE" required onkeyup="toUppercase()">
                      	</div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group">
                  		<label for="GL12DESC" class="col-sm-3 ">Description</label>
                  		<div class="col-sm-7">
                      		<input type="text" class="form-control" id="GL12DESC" name="GL12DESC" required onkeyup="toUppercase()">
                  		</div>
             		</div>
                    
                    <div class="form-group">
                  		<label class="col-sm-3 tutor">Tutor</label>
                  		<div class="col-sm-5 col-md-3">
                       		<!-- <input type="text" class="form-control" id="patronId" name="GL12TUTOR"> -->
                       		<input type="text" class="form-control" id="patronId" name="GL12TUTOR" >
                  		</div>
                  		<button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="Modal_PatronSearch">...</button>
                  		<div class="col-sm-2 col-md-5">
                       	<input type="text" class="form-control" id="patronName" name="patronName" readonly>
                  		</div>
             		</div> 
          
          			<input type="hidden" id="hidden-json" name="hidden-json"/>
                    
                 
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		<!-- <button type="submit" class="btn btn-primary" id="Add" style="width:80px;" onclick="addCourse()">Save</button> -->
		 <input type="submit" class="btn btn-primary" value="Save" id="btn-add" style="width:80px;">
		 <a href="template?MODULE=Foundation/03_CourseCode&ACTION=CourseTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
            </a>
	</div>			
	</form>
	

	
	