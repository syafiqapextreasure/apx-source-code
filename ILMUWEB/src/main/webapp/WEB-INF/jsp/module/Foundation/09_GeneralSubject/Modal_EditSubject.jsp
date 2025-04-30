<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.GeneralSubject.*, java.util.List" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/GeneralSubject.js"></script>
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
	String GL54SUBJSTA = request.getParameter("GL54SUBJSTA");
	System.out.println(GL54SUBJSTA + " GL54SUBJSTA");
	String GL54SUBJEND = request.getParameter("GL54SUBJEND");
	System.out.println(GL54SUBJEND + " GL54SUBJEND");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getGenSubj(GL54SUBJSTA, GL54SUBJEND);
	System.out.println(e);
%>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Edit General Subject</h4>
</div>
	
<form role="form" class="form-horizontal" action="${pageContext.request.contextPath}/UpdateGeneralSubj"  method="post">
	<div class="modal-body" style="height:40%;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
			
            
                    <div class="form-group">
                      <label class="col-sm-3 "><strong>Subject Start</strong></label>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL54SUBJSTA" name="GL54SUBJSTA" value="<%=e.getGL54SUBJSTA()%>"  disabled>
                        <input type="hidden" id="Start" name="Start" class="form-control" value="<%=e.getGL54SUBJSTA()%>">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 "><strong>Subject End</strong></label>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL54SUBJEND" name="GL54SUBJEND" value="<%=e.getGL54SUBJEND()%>" disabled>
                        <input type="hidden" id="End" name="End" class="form-control" value="<%=e.getGL54SUBJEND()%>">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse2" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label  class="col-sm-3 "><strong>Join Character</strong></label>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL54MARK" name="GL54MARK" value="<%=e.getGL54MARK()%>" maxlength="1" >
                      </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 "><strong>Description</strong></label>
                        <div class="col-sm-7">
                             <input type="text" class="form-control" id="GL54DESC" name="GL54DESC" onkeyup="toUppercase()" value="<%=e.getGL54DESC()%>" >
                        </div>
                    </div>
                    
                    
		
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		<!-- <button type="submit" class="btn btn-primary" id="" onclick="updateGenSubj()">Save</button> -->
		<button type="submit" class="btn btn-primary" id="btn-save">Save</button>
		 <a href="template?MODULE=Foundation/09_GeneralSubject&ACTION=SubjTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
         </a>
	</div>			
	</form>
	

	