<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.SMD.*, java.util.List" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/SMD.js"></script>
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
	String GL47SMD = request.getParameter("GL47SMD");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getSMDCode(GL47SMD);
	System.out.println(e);
%>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Edit Specific Material Designation (SMD)</h4>
</div>
	
<form role="form" id="smdForm" class="form-horizontal" action="${pageContext.request.contextPath}/UpdateSMD"  method="post">
	<div class="modal-body" style="height:30%;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
			
            
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 "><strong>SMD</strong></label>
                      <div class="col-sm-3 col-md-2">
                        <input type="text" class="form-control" id="GL47SMD" name="GL47SMD" value="<%=e.getGL47SMD()%>" disabled>
                      </div>
                      <input type="hidden" id="SMD" name="SMD" class="form-control" value="<%=e.getGL47SMD()%>">
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label for="inputPassword3" class="col-sm-3 "><strong>Description</strong></label>
                      <div class="col-sm-7">
                        <input type="text" class="form-control" id="GL47DESC" name="GL47DESC" value="<%=e.getGL47DESC()%>" onkeyup="toUppercase()">
                      </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 ">Display</label>
                        <div class="col-sm-7">
                             <%	if(e.getGL47DISPLAY()== null){%>
                        	 <input type="text" class="form-control" id="GL47DISPLAY" name="GL47DISPLAY" value=" " >
                        	 <%}else{%>
                             <input type="text" class="form-control" id="GL47DISPLAY" name="GL47DISPLAY" onkeyup="toUppercase()" value="<%=e.getGL47DISPLAY()%>" >
                        	<%}%>
                        </div>
                    </div>
                    
                   
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
	<button type="submit" class="btn btn-primary"  >Save</button>
	   <a href="template?MODULE=Foundation/15_SMD&ACTION=SMDTable.jsp">
           <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal" >Cancel</button>
       </a>
	</div>			
	</form>
	

	