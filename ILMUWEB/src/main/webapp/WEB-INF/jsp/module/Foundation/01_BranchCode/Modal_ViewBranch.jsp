<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.BranchCode.*, java.util.List" %>
<%@ page import="java.sql.*" %>


					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Add.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Delete.js"></script>
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
	String GL71BRNC = request.getParameter("GL71BRNC");
	String CODE = request.getParameter("CODE");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getBrnchCode(GL71BRNC, CODE);
	System.out.println(e);
%>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close" onClick="window.location.reload()"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">View Branch Code</h4>
</div>
	
<form role="form" class="form-horizontal" >
	<div class="modal-body" style="height:500px;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
			
            
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 "><strong>Branch Code</strong></label>
                      <div class="col-sm-3 col-md-2">
                        <input type="text" class="form-control" id="GL71BRNC" name="GL71BRNC" value="<%=e.getGL71BRNC()%>" readonly>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label for="inputPassword3" class="col-sm-3 ">Description</label>
                      <div class="col-sm-7">
                        <input type="text" class="form-control" id="GL71DESC" name="GL71DESC" value="<%=e.getGL71DESC()%>" readonly>
                      </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 ">Display</label>
                        <div class="col-sm-7">
                             <input type="text" class="form-control" id="GL71DISPLAY" name="GL71DISPLAY" value="<%=e.getGL71DISPLAY()%>" readonly>
                        </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-3 ">Address</label>
                         <div class="col-sm-7">
                              <input type="text" class="form-control" id="GL71ADD1" name="GL71ADD1" value="<%=e.getGL71ADD1()%>" readonly>
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-3 "></label>
                         <div class="col-sm-7">
                              <input type="text" class="form-control" id="GL71ADD2" name="GL71ADD2" value="<%=e.getGL71ADD2()%>" readonly>
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-3 "></label>
                         <div class="col-sm-7">
                             <input type="text" class="form-control" id="GL71ADD3" name="GL71ADD3" value="<%=e.getGL71ADD3()%>" readonly>
                         </div>
                    </div>
                                        
                    <!-- Town Code -->
             	<div class="form-group">
                  	<label class="col-sm-3 ">Town</label>
                  	<div class="col-sm-2 col-md-2">
                  	<select class="form-control" id="GL71TOWN" name="GL71TOWN" onchange="document.getElementById('GL15DESC').selectedIndex
                            						= document.getElementById('GL71TOWN').selectedIndex" disabled>
                      	<option value="<%=e.getGL71TOWN() %>"><%=e.getGL71TOWN() %></option>
                      	<%-- <%
							GlobalSQLStatement towncode = new GlobalSQLStatement();
							List<Foundation> town = towncode.getTown();
							for (Foundation a : town) {
					  	%>
					  	<option value="<%=a.getGL15TOWN()%>"><%=a.getGL15TOWN()%></option>
					  	<%
							}
					  	%> --%>
					</select>
                	</div>
               
                  	<div class="col-sm-3 col-md-5">
                  	<select class="form-control" id="GL15DESC" name="GL15DESC" onchange="document.getElementById('GL71TOWN').selectedIndex
                            						= document.getElementById('GL15DESC').selectedIndex" disabled>
                      	<option value="<%=e.getGL71TOWN() %>"><%=e.getDESCRIPTION() %></option>
                      	<%
                      		GlobalSQLStatement towndesc = new GlobalSQLStatement();
							List<Foundation> townlist = towndesc.getTown();
							for (Foundation b : townlist) {
					  	%>
					  	<option value="<%=b.getGL15TOWN()%>"><%=b.getGL15DESC()%></option>
					  	<%
							}
					 	%>
					</select>
                	</div>
             		</div>
										
					<div class="form-group">
                         <label class="col-sm-3 ">Postcode</label>
                         <div class="col-sm-3 col-md-2">
                              <input type="text" class="form-control" id="number" name="GL71POSCODE" value="<%=e.getGL71POSCODE()%>" readonly>
                         </div>
                    </div>
                    
                    
                    <div class="form-group">
                         <label class="col-sm-3 ">Phone No</label>
                         <div class="col-sm-4 col-md-4">
                              <input type="text" class="form-control" id="phoneno" name="phoneno" value="<%=e.getHtel()%>" readonly>
                         </div>
                    </div>
                    
                    
                    <div class="form-group">
                         <label class="col-sm-3 ">Email</label>
                         <div class="col-sm-6 col-md-6">
                              <input type="text" class="form-control" id="email" name="email" value="<%=e.getIPADD()%>" readonly>
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
	

	