<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.ItemCategory.*, java.util.List" %>
<%@ page import="java.sql.*" %>
					
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/ItemCat.js"></script>
					
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
	String GL10ICAT = request.getParameter("GL10ICAT");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getItemCat(GL10ICAT);
	System.out.println(e);
%>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">View Item Category</h4>
</div>
	
<form role="form" class="form-horizontal" >
	<div class="modal-body" style="height:45%;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 "><strong>Item Category</strong></label>
                      <div class="col-sm-3 col-md-2">
                        <input type="text" class="form-control" id="GL10ICAT" name="GL10ICAT" maxlength="3" onkeypress="toUppercase()" value="<%=e.getGL10ICAT()%>" readonly>
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
                        <input type="text" class="form-control" id="GL10DESC" name="GL10DESC" onkeypress="toUppercase()" value="<%=e.getGL10DESC()%>" readonly>
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Display</label>
                      <div class="col-sm-7">
                      <%if (e.getGL10DISPLAY()== null){ %>
                        <input type="text" class="form-control" id="GL10DISPLAY" name="GL10DISPLAY" onkeypress="toUppercase()" value=" " readonly>
                      <%}else{ %>
                        <input type="text" class="form-control" id="GL10DISPLAY" name="GL10DISPLAY" onkeypress="toUppercase()" value="<%=e.getGL10DISPLAY()%>" readonly>
                      <%} %>
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Maximum Loan</label>
                      <div class="col-sm-7">
                      <%if (e.getGL10ELIG()== null){ %>
                        <input type="text" class="form-control" id="GL10ELIG" name="GL10ELIG" onkeypress="toUppercase()" value=" " readonly>
                      <%}else{ %>
                        <input type="text" class="form-control" id="GL10ELIG" name="GL10ELIG" onkeypress="toUppercase()" value="<%=e.getGL10ELIG()%>" readonly>
                      <%} %>
                      </div>
                    </div>
                    
                     <div class="form-group" id ="selectRadio">
                     <label for="inputPassword3" class="col-sm-3 ">Borrowing Period</label>
                     <div class="radio">
      					<%
      					System.out.println(e.getGL10UNIT());
                    	if (e.getGL10UNIT().equals("D")){ %>
      					<label class="col-sm-2 ">
      					<input type="radio" class="minimal" name="GL10UNIT" id="day" checked disabled> Day
        				</label>
        				
        				<label class="col-sm-2 ">
        				<input type="radio" class="minimal" name="GL10UNIT" id="hour" disabled> Hour
        				</label>
        				
        				<%}else{ %>
        				<label class="col-sm-2 ">
      					<input type="radio" class="minimal" name="GL10UNIT" id="day" disabled> Day
        				</label>
      					
      					<label class="col-sm-2 ">
        				<input type="radio" class="minimal" name="GL10UNIT" id="hour"  checked disabled> Hour
        				</label>
        				<%} %>
    				</div>
    				</div>
                    
                     <div class="form-group" id ="selectCheckbox">
                     
                     <div class="checkbox">
      					<%
      					System.out.println(e.getGL10RESERVEC());
      					if (e.getGL10RESERVEC().equals("Y")){ %>
      					<label class="col-sm-7 ">
        				<input type="checkbox" class="minimal" name=GL10RESERVEC id="GL10RESERVEC" checked disabled> Item Category for Reserve Collection
        				</label>
        				<%}else if(e.getGL10RESERVEC()== null){ %>
        				<label class="col-sm-7 ">
        				<input type="checkbox" class="minimal" name=GL10RESERVEC id="GL10RESERVEC" disabled> Item Category for Reserve Collection
        				</label>
        				<%}else{ %>
        				<label class="col-sm-7 ">
        				<input type="checkbox" class="minimal" name=GL10RESERVEC id="GL10RESERVEC" disabled> Item Category for Reserve Collection
        				</label>
        				<%} %>
    				</div>
    				
    				</div>
                    
                   
              
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		 <a href="template?MODULE=Foundation/01_BranchCode&ACTION=BranchCode.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Close</button>
         </a>
	</div>			
	</form>
	

	