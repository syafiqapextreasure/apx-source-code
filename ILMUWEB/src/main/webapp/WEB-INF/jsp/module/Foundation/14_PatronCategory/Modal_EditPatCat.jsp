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

<%
	String GL07CATE = request.getParameter("GL07CATE");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getPatCat(GL07CATE);
	System.out.println(e);
%>

<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Edit Patron Category</h4>
</div>
	
<!-- <form role="form" class="form-horizontal" method="post"> -->
<form role="form" id="branchForm" class="form-horizontal" action="${pageContext.request.contextPath}/UpdatePatCate"  method="post">
	<div class="modal-body" style="height:500px;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-12">
			
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 "><strong>Patron Category</strong></label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07CATE" name="GL07CATE" maxlength="3" onkeyup="toUppercase()" value="<%=e.getGL07CATE()%>" readonly >
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
                        <input type="text" class="form-control" id="GL07DESC" name="GL07DESC" onkeyup="toUppercase()" value="<%=e.getGL07DESC()%>" required>
                      </div>
                    </div>
                    
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Maximum Loan</strong></label>
                      <%if(e.getGL07ELIG()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07ELIG" name="GL07ELIG" maxlength="3" onkeyup="toUppercase()" value=" " required >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07ELIG" name="GL07ELIG" onkeyup="toUppercase()"  value="<%=e.getGL07ELIG()%>" required>
                      </div>
                     <%} %>
                    
                      <label for="inputPassword3" class="col-sm-3 "><strong>Maximum Fines</strong></label>
                      <%if(e.getGL07MAXFINE()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07MAXFINE" name="GL07MAXFINE" maxlength="3" onkeyup="toUppercase()" value=" " required >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07MAXFINE" name="GL07MAXFINE" onkeyup="toUppercase()"  value="<%=e.getGL07MAXFINE()%>" required>
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Fines Limit</strong></label>
                      <%if(e.getGL07FINELIMIT()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07FINELIMIT" name="GL07FINELIMIT" maxlength="3" onkeyup="toUppercase()" value=" " required >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07FINELIMIT" name="GL07FINELIMIT" onkeyup="toUppercase()"  value="<%=e.getGL07FINELIMIT()%>" required>
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Maximum ILL Outgoing Request Allowed</strong></label>
                      <%if(e.getGL07ILLOUT()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07ILLOUT" name="GL07ILLOUT" maxlength="3" onkeyup="toUppercase()" value=" " required >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07ILLOUT" name="GL07ILLOUT" onkeyup="toUppercase()"  value="<%=e.getGL07ILLOUT()%>" required>
                      </div>
                      <%} %>
                    
                      <label for="inputPassword3" class="col-sm-3 "><strong>Maximum Reservation Allowed</strong></label>
                      <%if(e.getGL07MAXRESV()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07MAXRESV" name="GL07MAXRESV" maxlength="3" onkeyup="toUppercase()" value=" " required >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07MAXRESV" name="GL07MAXRESV" onkeyup="toUppercase()" value="<%=e.getGL07MAXRESV()%>" required>
                      </div>
                      <%} %>
                    </div>
                    
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Renew Fee</strong></label>
                      <%if(e.getGL07RENEWFEE()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07RENEWFEE" name="GL07RENEWFEE"  onkeyup="toUppercase()" value=" " required >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07RENEWFEE" name="GL07RENEWFEE" onkeyup="toUppercase()"  value="<%=e.getGL07RENEWFEE()%>" required>
                      </div>
                     <%} %>
                    
                      <label for="inputPassword3" class="col-sm-3 "><strong>Renew Grace Period</strong></label>
                      <%if(e.getGL07RENEWGRC()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07RENEWGRC" name="GL07RENEWGRC" maxlength="3" onkeyup="toUppercase()" value=" " required >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07RENEWGRC" name="GL07RENEWGRC"  maxlength="3" onkeyup="toUppercase()"  value="<%=e.getGL07RENEWGRC()%>" >
                      </div>
                      <%} %>
                    </div>
                    
                    
                    
                    
                     <div class="form-group" id ="selectRadio">
        			 <label for="inputEmail3" class="col-sm-3 "><strong>Allow Overdue</strong></label>
                     <div class="radio">
                     
                    <input type="hidden" name="Overdue" value="<%=e.getGL07ALLOWOVD()%>">
                     <label class="col-sm-1 ">
                     <input type="radio" class="minimal" name="GL07ALLOWOVD" id="GL07ALLOWOVD" value="Y"> Yes
        				<!-- <input type="radio" class="minimal" name="GL07ALLOWOVD" id="GL07ALLOWOVD" value="Y"> Yes
        				</label>
                     
      					<label class="col-sm-1 ">
      					<input type="radio" class="minimal" name="GL07ALLOWOVD" id="GL07ALLOWOVD" value="Y" checked > Yes
        				</label>
        				
        				<label class="col-sm-1 ">
        				<input type="radio" class="minimal" name="GL07ALLOWOVD" id="GL07ALLOWOVD"> No -->
        			</label>
        			<label class="col-sm-1 ">
        				<input type="radio" class="minimal" name="GL07ALLOWOVD" id="GL07ALLOWOVD" value="N"> No
        		    </label>

    				 </div>
    				 </div>
    			
			
			
        			 <div class="form-group" id ="selectRadio">
        			 <label for="inputEmail3" class="col-sm-3 "><strong>Allow Reserve</strong></label>
        			 <input type="hidden" name="Reserve" value="<%=e.getGL07ALLOWRSV()%>">
                     <div class="radio">
      					<label class="col-sm-1 ">
      					<input type="radio" class="minimal" name="GL07ALLOWRSV" id="GL07ALLOWRSV" value="Y"> Yes
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
      						<%if(e.getGL07MAXACCT().equals("-1")){ %>
      						<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=subplan id="subplan">Subscription Plan
      						<!-- onclick="updatePatCat()" -->
      						</label>
      						<%}else{ %>
      						<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=subplan id="subplan" checked >Subscription Plan
      						<!-- onclick="updatePatCat()" -->
      						</label>
      						<%} %>
      						</div>  
      					</div>
      					
      					<div class="form-group" >
      						<label for="inputPassword3" class="col-sm-4">Maximum Patron Account Allowed</label>
                      		<%if(e.getGL07MAXACCT().equals("-1")){ %>
                      			<div class="col-sm-2 col-md-2">
                        			<input type="text" class="form-control" id="GL07MAXACCT" name="GL07MAXACCT" maxlength="3" onkeyup="toUppercase()" value=" " disabled >
                      			</div>
                      		<%}else{ %>
                      			<div class="col-sm-2">
                        			<input type="text" class="form-control" id="GL07MAXACCT" name="GL07MAXACCT" value="<%=e.getGL07MAXACCT()%>" required>
                      			</div>
                      		<%} %>
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
                     	<%if (e.getGL07POPDB().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07POPDB" id="GL07POPDB" value="Y" checked > Popular Update Database
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07POPDB" id="GL07POPDB" > Popular Update Database
        					</label>
        				<%} %>
    				 	
    				 	<%if (e.getGL07RATER().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07RATER" id="GL07RATER" value="Y" checked > Review
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07RATER" id="GL07RATER" > Review
        					</label>
        				<%} %>
    				 	</div>
    					</div> 
    					
    					<div class="form-group" id ="selectCheckbox">
                     	<div class="checkbox">
                     	<%if (e.getGL07EMAIL().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07EMAIL" id="GL07EMAIL" value="Y" checked > Send E-Mail
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07EMAIL" id="GL07EMAIL" > Send E-Mail
        					</label>
        				<%} %>
    				 	
    				 	<%if (e.getGL07MODEM().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07MODEM" id="GL07MODEM" value="Y" checked > Internet Login
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07MODEM" id="GL07MODEM" > Internet Login
        					</label>
        				<%} %>
    				 	</div>
    					</div> 
    					
    					<div class="form-group" id ="selectCheckbox">
                     	<div class="checkbox">
                     	<%if (e.getGL07SCHAR().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07SCHAR" id="GL07SCHAR" value="Y" checked > Self Charging
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07SCHAR" id="GL07SCHAR" > Self Charging
        					</label>
        				<%} %>
    				 	
    				 	<%if (e.getGL07BRFORC().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07BRFORC" id="GL07BRFORC" value="Y" checked > Override Charging Restriction
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07BRFORC" id="GL07BRFORC" > Override Charging Restriction
        					</label>
        				<%} %>
    				 	</div>
    					</div> 
    					
    					<div class="form-group" id ="selectCheckbox">
                     	<div class="checkbox">
                     	<%if (e.getGL07ARTXN().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07ARTXN" id="GL07ARTXN" value="Y" checked > Archive Circulation Txn
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07ARTXN" id="GL07ARTXN" > Archive Circulation Txn
        					</label>
        				<%} %>
    				 	<%if (e.getGL07DCFORC().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07DCFORC" id="GL07DCFORC" value="Y" checked > Override Fines - Charges
        					</label>
        				<%}else{%>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07DCFORC" id="GL07DCFORC" > Override Fines - Charges
        					</label>
        				
        				<%} %>
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
		<!-- <button type="submit" class="btn btn-primary" id="" onclick="updatePatCat()">Save</button> -->
		<button type="submit" class="btn btn-primary" id="btn-save">Save</button>
		 <a href="template?MODULE=Foundation/14_PatronCategory&ACTION=PatCategory.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
            </a>
	</div>			
	</form>
	

	