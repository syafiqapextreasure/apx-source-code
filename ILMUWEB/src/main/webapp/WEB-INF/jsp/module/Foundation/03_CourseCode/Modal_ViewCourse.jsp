<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.CourseCode.*, java.util.List" %>
<%@ page import="java.sql.*" %>


					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/CourseCode.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Add.js"></script>	
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Checking.js"></script>

<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> --%>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>

<%
	String GL12COURSE = request.getParameter("GL12COURSE");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getCourse(GL12COURSE);
	System.out.println(e);
%>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">View Course Code</h4>
</div>
	
<form role="form" class="form-horizontal" >
	<div class="modal-body" style="height:40%;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
			<div class="form-group">
                    	<label for="inputEmail3" class="col-sm-3 "><strong>Course Code</strong></label>
                      	<div class="col-sm-5 col-md-2">
                         	<input type="text" class="form-control" id="GL12COURSE" name="GL12COURSE" value="<%=e.getGL12COURSE()%>" readonly>
                      	</div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group">
                  		<label for="inputPassword3" class="col-sm-3 ">Description</label>
                  		<div class="col-sm-7">
                      		<input type="text" class="form-control" id="GL12DESC" name="GL12DESC" value="<%=e.getGL12DESC()%>" readonly>
                  		</div>
             		</div>
                    
                    <%-- <%if(e.getGL12TUTOR()== null){ %> --%>
             		<div class="form-group">
                  		<label class="col-sm-3 ">Tutor</label>
                  		<div class="col-sm-5 col-md-2">
                       		<!-- <input type="text" class="form-control" id="patronId" name="GL12TUTOR"> -->
                       		<input type="text" class="form-control" id="patronId" name="GL12TUTOR" value="<%=e.getGL12TUTOR()%>" readonly>
                  		</div>
                  		<button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="Modal_PatronSearch" disabled>...</button>
                  		<div class="col-sm-2 col-md-5">
                  			<%
			             	 	 	System.out.println(e.getGL12TUTOR() +" getGL12TUTOR");
		             	 	   		String patronName;
		             	 	 		if(e.getGL12TUTOR().trim().isEmpty()){
		             	 	 			patronName = "";
		             	 	 		}else{
				                  		GlobalSQLStatement name = new GlobalSQLStatement();
			                  			Foundation patrName = name.getPatron(e.getGL12TUTOR());
			                  			System.out.println(patrName.getGL14NAME() +" patrName.getGL14NAME()");
			                  			patronName = patrName.getGL14NAME();
		             	 	 		}
		             	 	 		System.out.println(patronName +" patronName");
							   %>
                       	<input type="text" class="form-control" id="patronName" name="patronName" value="<%=patronName%>" readonly>
                  		</div>
             		</div> 
          
          			<input type="hidden" id="hidden-json" name="hidden-json"/>
          			
          			<%-- <%}else{ %>
          			
          			
                    <div class="form-group">
                  		<label class="col-sm-3 ">Tutor</label>
                  		<div class="col-sm-5 col-md-2">
                       		<!-- <input type="text" class="form-control" id="patronId" name="GL12TUTOR"> -->
                       		<input type="text" class="form-control" id="patronId" name="GL12TUTOR" value="<%=e.getGL12TUTOR()%>" readonly >
                  		</div>
                  		<button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="Modal_PatronSearch" disabled>...</button>
                  		<div class="col-sm-2 col-md-5">
                       	<input type="text" class="form-control" id="patronName" name="patronName" readonly>
                  		</div>
             		</div> 
             		  
          			<input type="hidden" id="hidden-json" name="hidden-json"/>
			
			<%} %> --%>
			
			             		 <div class="form-group">
                  		<label for="dateRec" class="col-sm-3 ">Date Recorded</label>
                  		<div class="col-sm-3">
                  			<input type="text" class="form-control" id="dateRec" name="dateRec" value="<%=e.getGL12DATERE()%>" readonly>
                  		</div>
             		</div>
             		
             		 <div class="form-group">
                  		<label for="recBy" class="col-sm-3 ">Recorded By</label>
                  		<div class="col-sm-3">
                  		<input type="text" class="form-control" id="dateRec" name="dateRec" value="<%=e.getGL12RECBY()%>" readonly>
                  		</div>
             		</div>
            
                   
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		 <a href="template?MODULE=Foundation/01_BranchCode&ACTION=BranchCode.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal" onClick="window.location.reload()">Close</button>
         </a>
	</div>			
	</form>
	

	