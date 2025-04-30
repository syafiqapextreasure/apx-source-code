<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.SerialFrequency.*, java.util.List" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/SerialFreq.js"></script>
<!-- Include Required Prerequisites -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> --%>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>

<%
	String GL49FREQ = request.getParameter("GL49FREQ");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getSerial(GL49FREQ);
	System.out.println(e);
%>

<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">View Serial Frequency</h4>
</div>
	
<form role="form" class="form-horizontal" method="post">
	<div class="modal-body" style="height:40%;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 "><b>Frequency Code</b></label>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL49FREQ" name="GL49FREQ" maxlength="3" onkeyup="toUppercase()" value="<%=e.getGL49FREQ()%>" required readonly>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 ">Description</label>
                        <div class="col-sm-8">
                             <input type="text" class="form-control" id="GL49DESC" name="GL49DESC" value="<%=e.getGL49DESC()%>" readonly >
                        </div>
                    </div>
                    
                    <div class="form-group">
                      	<label class="col-sm-3 "><b>Time Span</b></label>
                      	<div class="col-sm-3 col-md-3">
                        	<input type="text" class="form-control" id="GL49TIME" name="GL49TIME" value="<%=e.getGL49TIME()%>" required readonly>
                      	</div>
                    </div>
                    
                    <div class="form-group">
                      	<label  class="col-sm-3 "><b>Renewal Alert</b></label>
                      	<div class="col-sm-3 col-md-3">
                        	<input type="text" class="form-control" id="GL49ALERT" name="GL49ALERT" value="<%=e.getGL49ALERT()%>" required readonly>
                      	</div>
                    </div>
                    
                    <div class="form-group">
                  	<label class="col-sm-3 "><b>Period Type</b></label>
                  	<div class="col-sm-3 col-md-3">
                  	<select class="form-control" id="GL49PTYPE" name="GL49PTYPE" required disabled>
                  	<%if (e.getGL49PTYPE().equals("YYYY")){ %>
                      	<option value="YYYY">Year</option>
                    <%}else if(e.getGL49PTYPE().equals("M")){ %>
                        <option value="M">Month</option>
                      	<option value="Q">Quater</option>
                    <%}else if(e.getGL49PTYPE().equals("WW")){ %>
                      	<option value="WW">Week</option>
                    <%}else if(e.getGL49PTYPE().equals("D")){ %>
                      	<option value="D">Day</option>
                    <%} %>
					</select>
                	</div>
               		</div>
                   
                    
		
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		 <a href="template?MODULE=Foundation/09_GeneralSubject&ACTION=SubjTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Close</button>
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
	