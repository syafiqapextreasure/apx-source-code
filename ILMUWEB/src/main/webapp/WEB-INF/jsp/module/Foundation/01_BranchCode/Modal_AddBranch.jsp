<%@ page import="com.ilmu.cataloging.Acc_Maint.*, com.ilmu.foundation.global.*,com.ilmu.foundation.BranchCode.*, java.util.List" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/BranchCode.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Add.js"></script>	

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Checking.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Delete.js"></script>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

/* .has-error .form-control-feedback {
    color: #E74C3C;
}
.has-success .form-control-feedback {
    color: #18BCA0;
} */

</style>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Add Branch Code</h4>
</div>
	
<!-- <form  id="branchForm" class="form-horizontal"> -->
	<form role="form" id="branchForm" class="form-horizontal" action="${pageContext.request.contextPath}/AddBranchCode" method="post">
	<div class="modal-body" style="height:500px;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
			
            
                    <div class="form-group">
                      <label class="control-label col-sm-3 ">Branch Code</label>
                      <div class="col-sm-3 col-md-2">
                        <input type="text" class="form-control" id="GL71BRNC" name="GL71BRNC" maxlength="4" onkeyup="toUppercase()">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group">
                    	<label class="control-label col-sm-3 ">Description</label>
                      <div class="col-sm-7">
                        <input type="text" class="form-control" id="GL71DESC" name="GL71DESC" onkeyup="toUppercase()">
                      </div>
                    </div>
                    
                    <div class="form-group">
                    	<label class="control-label col-sm-3 ">Display</label>
                        <div class="col-sm-7">
                             <input type="text" class="form-control" id="GL71DISPLAY" name="GL71DISPLAY" onkeyup="toUppercase()">
                        </div>
                    </div>
                    
                    <div class="form-group">
                    	<label class="control-label col-sm-3 ">Address</label>
                         <div class="col-sm-7">
                              <input type="text" class="form-control" id="GL71ADD1" name="GL71ADD1">
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-3 "></label>
                         <div class="col-sm-7">
                              <input type="text" class="form-control" id="GL71ADD2" name="GL71ADD2">
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-3 "></label>
                         <div class="col-sm-7">
                             <input type="text" class="form-control" id="GL71ADD3" name="GL71ADD3">
                         </div>
                    </div>
                                        
                    <!-- Town Code -->
             	<div class="form-group">
             		<label class="control-label col-sm-3 ">Town</label>
                  	<div class="col-sm-2 col-md-2">
                  	<select class="form-control" id="GL71TOWN" name="GL71TOWN" onchange="document.getElementById('GL15DESC').selectedIndex
                            						= document.getElementById('GL71TOWN').selectedIndex">
                      	<option value="0">Select</option>
                      	<%
							GlobalSQLStatement towncode = new GlobalSQLStatement();
							List<Foundation> town = towncode.getTown();
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
                      	<option value="0">Select Town</option>
                      	<%
                      		GlobalSQLStatement towndesc = new GlobalSQLStatement();
							List<Foundation> townlist = towndesc.getTown();
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
						<label class="control-label col-sm-3 ">Postcode</label>
                         <div class="col-sm-3 col-md-3">
                              <input type="text" class="form-control" id="GL71POSCODE" name="GL71POSCODE" maxlength="5">
                         </div>
                    </div>
                    
                    
                    <div class="form-group">
						<label class="control-label col-sm-3 ">Phone No</label>
                         <div class="col-sm-4 col-md-4">
                              <input type="text" class="form-control" id="phonenoadd" name="phonenoadd" >
                         </div>
                    </div>
                    
                    
                   <div class="form-group">
						<label class="control-label col-sm-3 ">Email</label>
                         <div class="col-sm-6 col-md-6">
                              <input type="text" class="form-control" id="emailadd" name="emailadd" >
                         </div>
                    </div>
                    
	
		
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		<!-- <button type="button" class="btn btn-primary" id="btn-add" style="width:80px;">Save</button> -->
		<input type="submit" class="btn btn-primary" value="Save" id="btn-add" style="width:80px;">
		 <a href="template?MODULE=Foundation/01_BranchCode&ACTION=BranchCode.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
            </a>
	</div>			
	</form>
	

	