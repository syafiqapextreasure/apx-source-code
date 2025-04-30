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
		<h4 class="modal-title" id="myModalLabel" align="center">View Patron Category</h4>
</div>
	
<form role="form" class="form-horizontal" method="post">
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
                        <input type="text" class="form-control" id="GL07DESC" name="GL07DESC" onkeyup="toUppercase()" value="<%=e.getGL07DESC()%>" readonly>
                      </div>
                    </div>
                    
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Maximum Loan</strong></label>
                      <%if(e.getGL07ELIG()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07ELIG" name="GL07ELIG" maxlength="3" onkeyup="toUppercase()" value=" " readonly >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07ELIG" name="GL07ELIG" onkeyup="toUppercase()"  value="<%=e.getGL07ELIG()%>" readonly>
                      </div>
                     <%} %>
                    
                      <label for="inputPassword3" class="col-sm-3 "><strong>Maximum Fines</strong></label>
                      <%if(e.getGL07MAXFINE()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07MAXFINE" name="GL07MAXFINE" maxlength="3" onkeyup="toUppercase()" value=" " readonly >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07MAXFINE" name="GL07MAXFINE" onkeyup="toUppercase()"  value="<%=e.getGL07MAXFINE()%>" readonly>
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Fines Limit</strong></label>
                      <%if(e.getGL07FINELIMIT()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07FINELIMIT" name="GL07FINELIMIT" maxlength="3" onkeyup="toUppercase()" value=" " readonly >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07FINELIMIT" name="GL07FINELIMIT" onkeyup="toUppercase()"  value="<%=e.getGL07FINELIMIT()%>" readonly>
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Maximum ILL Outgoing Request Allowed</strong></label>
                      <%if(e.getGL07ILLOUT()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07ILLOUT" name="GL07ILLOUT" maxlength="3" onkeyup="toUppercase()" value=" " readonly >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07ILLOUT" name="GL07ILLOUT" onkeyup="toUppercase()"  value="<%=e.getGL07ILLOUT()%>" readonly>
                      </div>
                      <%} %>
                    
                      <label for="inputPassword3" class="col-sm-3 "><strong>Maximum Reservation Allowed</strong></label>
                      <%
                      System.out.println("e.getGL07MAXRESV()e.getGL07MAXRESV()e.getGL07MAXRESV() -->" +e.getGL07MAXRESV());
                      if(e.getGL07MAXRESV()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07MAXRESV" name="GL07MAXRESV" maxlength="3" onkeyup="toUppercase()" value=" " readonly >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07MAXRESV" name="GL07MAXRESV" onkeyup="toUppercase()" value="<%=e.getGL07MAXRESV()%>" readonly>
                      </div>
                      <%} %>
                    </div>
                    
                    
                    
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Renew Fee</strong></label>
                      <%if(e.getGL07RENEWFEE()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07RENEWFEE" name="GL07RENEWFEE"  onkeyup="toUppercase()" value=" " readonly >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07RENEWFEE" name="GL07RENEWFEE" onkeyup="toUppercase()"  value="<%=e.getGL07RENEWFEE()%>" readonly>
                      </div>
                     <%} %>
                    
                      <label for="inputPassword3" class="col-sm-3 "><strong>Renew Grace Period</strong></label>
                      <%if(e.getGL07RENEWGRC()==null){ %>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL07RENEWGRC" name="GL07RENEWGRC" maxlength="3" onkeyup="toUppercase()" value=" " readonly >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="GL07RENEWGRC" name="GL07RENEWGRC" onkeyup="toUppercase()"  value="<%=e.getGL07RENEWGRC()%>" readonly>
                      </div>
                      <%} %>
                    </div>
                    
                    
                    
                    
                    
                     <div class="form-group" id ="selectRadio">
        			 <label for="inputEmail3" class="col-sm-3 "><strong>Allow Overdue</strong></label>
                     <div class="radio">
                     
                    <input type="hidden" name="Overdue" value="<%=e.getGL07ALLOWOVD()%>">
                     <label class="col-sm-1 ">
                     <input type="radio" class="minimal" name="GL07ALLOWOVD" id="GL07ALLOWOVD" value="Y" disabled> Yes
        			
        			</label>
        			<label class="col-sm-1 ">
        				<input type="radio" class="minimal" name="GL07ALLOWOVD" id="GL07ALLOWOVD" value="N" disabled> No
        		    </label>

    				 </div>
    				 </div>
    			
			
			
        			 <div class="form-group" id ="selectRadio">
        			 <label for="inputEmail3" class="col-sm-3 "><strong>Allow Reserve</strong></label>
        			 <input type="hidden" name="Reserve" value="<%=e.getGL07ALLOWRSV()%>">
                     <div class="radio">
      					<label class="col-sm-1 ">
      					<input type="radio" class="minimal" name="GL07ALLOWRSV" id="GL07ALLOWRSV" value="Y" disabled> Yes
        				</label>
        				
      					<label class="col-sm-1 ">
        				<input type="radio" class="minimal" name="GL07ALLOWRSV" id="GL07ALLOWRSV"  value="N" disabled> No
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
      						<input type="checkbox" class="minimal" name=subplan id="subplan" disabled>Subscription Plan
      						</label>
      						<%}else{ %>
      						<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=subplan id="subplan" checked disabled>Subscription Plan
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
        						<input type="checkbox" class="minimal" name="GL07POPDB" id="GL07POPDB" checked disabled> Popular Update Database
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07POPDB" id="GL07POPDB" disabled> Popular Update Database
        					</label>
        				<%} %>
    				 	
    				 	<%if (e.getGL07RATER().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07RATER" id="GL07RATER" checked disabled> Review
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07RATER" id="GL07RATER" disabled> Review
        					</label>
        				<%} %>
    				 	</div>
    					</div> 
    					
    					<div class="form-group" id ="selectCheckbox">
                     	<div class="checkbox">
                     	<%if (e.getGL07EMAIL().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07EMAIL" id="GL07EMAIL" checked disabled> Send E-Mail
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07EMAIL" id="GL07EMAIL" disabled> Send E-Mail
        					</label>
        				<%} %>
    				 	
    				 	<%if (e.getGL07MODEM().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07MODEM" id="GL07MODEM" checked disabled> Internet Login
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07MODEM" id="GL07MODEM" disabled> Internet Login
        					</label>
        				<%} %>
    				 	</div>
    					</div> 
    					
    					<div class="form-group" id ="selectCheckbox">
                     	<div class="checkbox">
                     	<%if (e.getGL07SCHAR().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07SCHAR" id="GL07SCHAR" checked disabled> Self Charging
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07SCHAR" id="GL07SCHAR" disabled> Self Charging
        					</label>
        				<%} %>
    				 	
    				 	<%if (e.getGL07BRFORC().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07BRFORC" id="GL07BRFORC" checked disabled> Override Charging Restriction
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07BRFORC" id="GL07BRFORC" disabled> Override Charging Restriction
        					</label>
        				<%} %>
    				 	</div>
    					</div> 
    					
    					<div class="form-group" id ="selectCheckbox">
                     	<div class="checkbox">
                     	<%if (e.getGL07ARTXN().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07ARTXN" id="GL07ARTXN" checked disabled> Archive Circulation Txn
        					</label>
        				<%}else{ %>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07ARTXN" id="GL07ARTXN" disabled> Archive Circulation Txn
        					</label>
        				<%} %>
    				 	<%if (e.getGL07DCFORC().equals("Y")){ %>
      						<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07DCFORC" id="GL07DCFORC" checked disabled> Override Fines - Charges
        					</label>
        				<%}else{%>
        					<label class="col-sm-4 ">
        						<input type="checkbox" class="minimal" name="GL07DCFORC" id="GL07DCFORC" disabled> Override Fines - Charges
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
		
		 <a href="template?MODULE=Foundation/10_PatronDetails&ACTION=PatronTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Close</button>
            </a>
	</div>			
	</form>
	

	