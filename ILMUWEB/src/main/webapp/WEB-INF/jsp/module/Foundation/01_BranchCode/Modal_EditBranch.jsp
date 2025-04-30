<%@ page import="com.ilmu.cataloging.Acc_Maint.*, com.ilmu.foundation.global.*,com.ilmu.foundation.BranchCode.*, java.util.List" %>
<%@ page import="java.sql.*" %>


					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/BranchCode.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Delete.js"></script>	
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 -->
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
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Edit Branch Code</h4>
</div>
	
<!-- <form role="form" class="form-horizontal"   method="post"> -->
<form role="form" id="branchForm" class="form-horizontal" action="${pageContext.request.contextPath}/UpdateBranchCode"  method="post">
	<div class="modal-body" style="height:500px;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
			
            
                    <div class="form-group">
                      <label for="GL71BRNC" class="col-sm-3 "><strong>Branch Code</strong></label>
                      <div class="col-sm-3 col-md-2">
                        <input type="text" class="form-control" id="GL71BRNC" name="GL71BRNC" value="<%=e.getGL71BRNC()%>" disabled>
                      </div>
                      <input type="hidden" id="Branch" name="Branch" class="form-control" value="<%=e.getGL71BRNC()%>">
                    </div>
                    
                    <div class="form-group">
                      <label for="inputPassword3" class="col-sm-3 ">Description</label>
                      <div class="col-sm-7">
                        <input type="text" class="form-control" id="GL71DESC" name="GL71DESC" value="<%=e.getGL71DESC()%>" onkeyup="toUppercase()">
                      </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 ">Display</label>
                        <div class="col-sm-7">
                             <input type="text" class="form-control" id="GL71DISPLAY" name="GL71DISPLAY" value="<%=e.getGL71DISPLAY()%>"  onkeyup="toUppercase()">
                        </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-3 ">Address</label>
                         <div class="col-sm-7">
                              <input type="text" class="form-control" id="GL71ADD1" name="GL71ADD1" value="<%=e.getGL71ADD1()%>" >
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-3 "></label>
                         <div class="col-sm-7">
                              <input type="text" class="form-control" id="GL71ADD2" name="GL71ADD2" value="<%=e.getGL71ADD2()%>" >
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-3 "></label>
                         <div class="col-sm-7">
                             <input type="text" class="form-control" id="GL71ADD3" name="GL71ADD3" value="<%=e.getGL71ADD3()%>" >
                         </div>
                    </div>
                                        
                    <!-- Town Code -->
             	<div class="form-group">
                  	<label class="col-sm-3 ">Town</label>
                  	<div class="col-sm-2 col-md-2">
                  	<select class="form-control" id="GL71TOWN" name="GL71TOWN" onchange="document.getElementById('GL15DESC').selectedIndex
                            						= document.getElementById('GL71TOWN').selectedIndex">
                      	<option value="<%=e.getGL71TOWN()%>"><%=e.getGL71TOWN()%></option>
                      	<%
							GlobalSQLStatement towncode = new GlobalSQLStatement();
							List<Foundation> town = towncode.getTownNotIn(e.getGL71TOWN());
							for (Foundation a : town) {
					  	%>
					  	<option value="<%=a.getCODE()%>"><%=a.getCODE()%></option>
					  	<%
							}
					  	%>
					</select>
                	</div>
               
                  	<div class="col-sm-3 col-md-5">
                  	<select class="form-control" id="GL15DESC" name="GL15DESC" onchange="document.getElementById('GL71TOWN').selectedIndex
                            						= document.getElementById('GL15DESC').selectedIndex">
                      	<option value="0"><%=e.getDESCRIPTION()%></option>
                      	<%
                      		GlobalSQLStatement towndesc = new GlobalSQLStatement();
							List<Foundation> townlist = towndesc.getTownNotIn(e.getGL71TOWN());
							for (Foundation b : townlist) {
					  	%>
					  	<option value="<%=b.getDESCRIPTION()%>"><%=b.getDESCRIPTION()%></option>
					  	<%
							}
					 	%>
					</select>
                	</div>
             		</div>
										
					<div class="form-group">
                         <label class="col-sm-3 ">Postcode</label>
                         <div class="col-sm-3 col-md-2">
                              <input type="text" class="form-control" id="GL71POSCODE" maxlength="5" name="GL71POSCODE" value="<%=e.getGL71POSCODE()%>" >
                         </div>
                    </div>
                    
                    
                    <div class="form-group">
                         <label class="col-sm-3 ">Phone No</label>
                         <div class="col-sm-4 col-md-4">
                              <input type="text" class="form-control" id="phonenoedit" name="phonenoedit" value="<%=e.getHtel()%>">
                         </div>
                    </div>
                    
                    
                    <div class="form-group">
                         <label class="col-sm-3 ">Email</label>
                         <div class="col-sm-6 col-md-6">
                              <input type="text" class="form-control" id="emailedit" name="emailedit" value="<%=e.getIPADD()%>">
                         </div>
                    </div>
              
       		
	
		
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
	<!-- <button type="submit" class="btn btn-primary" id="btn-save" onclick="updateBranch()">Save</button> -->
	<button type="submit" class="btn btn-primary" id="btn-save">Save</button>
		 <a href="template?MODULE=Foundation/01_BranchCode&ACTION=BranchCode.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
            </a>
	</div>			
	</form>
	

	