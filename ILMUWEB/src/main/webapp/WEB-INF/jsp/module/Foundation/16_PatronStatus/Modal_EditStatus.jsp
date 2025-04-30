<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.PatronStatus.*, java.util.List" %>
<%@ page import="java.sql.*" %>


					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/PatronStatus.js"></script>	
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
	String GL08STAT = request.getParameter("GL08STAT");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getPatStatus(GL08STAT);
	System.out.println(e);
%>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Edit Patron Status</h4>
</div>
	
<!-- <form role="form" class="form-horizontal" method="post"> -->
<form role="form" id="statusForm" class="form-horizontal" action="${pageContext.request.contextPath}/UpdatePatronStatus"  method="post">
	<div class="modal-body" style="height:45%;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 "><strong>Patron Status</strong></label>
                      <div class="col-sm-3 col-md-2">
                        <input type="text" class="form-control" id="GL08STAT" name="GL08STAT" value="<%=e.getGL08STAT()%>" disabled>
                      </div>
                      <input type="hidden" id="Status" name="Status" class="form-control" value="<%=e.getGL08STAT()%>">
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
                        <input type="text" class="form-control" id="GL08DESC" name="GL08DESC" value="<%=e.getGL08DESC()%>" required onkeyup="toUppercase()">
                      </div>
                    </div>
                    
                     <div class="form-group" id ="selectCheckbox">
                     <div class="checkbox">
                     	
      					<label class="col-sm-4 ">
      					<input type="checkbox" class="minimal" name=GL08ACTION1 id="GL08ACTION1" value="Y"  <%if (e.getGL08ACTION1().equals("Y")){ %> checked <%}%> > CHARGING
        				</label>
      					
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION2" id="GL08ACTION2" value="Y" <%if (e.getGL08ACTION2().equals("Y")){ %> checked <%}%> > DISCHARGING
        				</label>
      					
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION3" id="GL08ACTION3" <%if (e.getGL08ACTION3().equals("Y")){ %> value="Y" checked <%}%> > RENEWAL
        				</label>
    		
    				 </div>
    				 
    				 
    				 <div class="checkbox">
    				 	
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION4" id="GL08ACTION4" <%if (e.getGL08ACTION4().equals("Y")){ %> value="Y" checked <%}%> > RESERVATION
        				</label>
      					
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION5" id="GL08ACTION5" <%if (e.getGL08ACTION5().equals("Y")){ %> value="Y" checked <%}%> > REVIEW
        				</label>
      					
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION6" id="GL08ACTION6" <%if (e.getGL08ACTION6().equals("Y")){ %>  value="Y" checked <%}%> > MESSAGE
        				</label>
    				 </div>
    				 
    				 <div class="checkbox">
    				 
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION7" id="GL08ACTION7" <%if (e.getGL08ACTION7().equals("Y")){ %> value="Y" checked <%}%> > MODEM CONNECTION
        				</label>
      					
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION8" id="GL08ACTION8" <%if (e.getGL08ACTION8().equals("Y")){ %> value="Y" checked <%}%> > RECALLS
        				</label>
      					
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION9" id="GL08ACTION9" <%if (e.getGL08ACTION9().equals("Y")){ %> value="Y" checked <%}%> > NOT ASSIGNED
        				</label>
    				 </div>
    				 
    				 <div class="checkbox">
    				 
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION10" id="GL08ACTION10" <%if (e.getGL08ACTION10().equals("Y")){ %> value="Y" checked <%}%> > ILMU LOGIN
        				</label>
      					
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION11" id="GL08ACTION11" <%if (e.getGL08ACTION11().equals("Y")){ %> value="Y" checked <%}%> > NOT ASSIGNED
        				</label>
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION12" id="GL08ACTION12" <%if (e.getGL08ACTION12().equals("Y")){ %> value="Y" checked <%}%> > NOT ASSIGNED
        				</label>
    				</div>
    				
    				<div class="checkbox">
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION13" id="GL08ACTION13" <%if (e.getGL08ACTION13().equals("Y")){ %> value="Y" checked <%}%> > NOT ASSIGNED
        				</label>
      					
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION14" id="GL08ACTION14" <%if (e.getGL08ACTION14().equals("Y")){ %> value="Y" checked <%}%> > NOT ASSIGNED
        				</label>
      					
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION15" id="GL08ACTION15" <%if (e.getGL08ACTION15().equals("Y")){ %> value="Y" checked <%}%> > NOT ASSIGNED
        				</label>
    				</div>
    				
    				<div class="checkbox">
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION16" id="GL08ACTION16" <%if (e.getGL08ACTION16().equals("Y")){ %> value="Y" checked <%}%> > NOT ASSIGNED
        				</label>
      					

      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION17" id="GL08ACTION17" <%if (e.getGL08ACTION17().equals("Y")){ %> value="Y" checked <%}%> > NOT ASSIGNED
        				</label>
      					
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION18" id="GL08ACTION18" <%if (e.getGL08ACTION18().equals("Y")){ %> value="Y" checked <%}%> > NOT ASSIGNED
        				</label>
      				</div>
      				
    				<div class="checkbox">
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION19" id="GL08ACTION19" <%if (e.getGL08ACTION19().equals("Y")){ %> value="Y" checked <%}%> > NOT ASSIGNED
        				</label>
        				
      					
      					<label class="col-sm-4 ">
        				<input type="checkbox" class="minimal" name="GL08ACTION20" id="GL08ACTION20" <%if (e.getGL08ACTION20().equals("Y")){ %> value="Y" checked <%}%> > NOT ASSIGNED
        				</label>
    				</div>
    				</div>
                    
                   
              
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
	<!-- <button type="submit" class="btn btn-primary" id="btn-add" onclick="update()">Save</button> -->
	<button type="submit" class="btn btn-primary"  >Save</button>
		 <a href="template?MODULE=Foundation/16_PatronStatus&ACTION=StatusTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
         </a>
	</div>			
	</form>
	

	