<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.DepartmentCode.*, java.util.List" %>
					
<!-- Include Required Prerequisites -->
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/GeneralSubject.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/DepartmentCode.js"></script>
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
	String GL11DEPT = request.getParameter("GL11DEPT");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getDeptCode(GL11DEPT);
	System.out.println(e);
%>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">View Department Code</h4>
</div>
	
<form role="form" class="form-horizontal">
	<div class="modal-body" style="height:500px;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
			
            
                    <div class="form-group">
                      <label class="col-sm-3 "><strong>Department Code</strong></label>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL11DEPT" name="GL11DEPT" value="<%=e.getGL11DEPT()%>"  readonly>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-3 "><strong>Department Name</strong></label>
                      <div class="col-sm-8 col-md-8">
                        <input type="text" class="form-control" id="GL11NAME" name="GL11NAME" value="<%=e.getGL11NAME()%>" readonly>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label  class="col-sm-3 "><strong>Address</strong></label>
                      <div class="col-sm-8 col-md-8">
                        <input type="text" class="form-control" id="GL11ADD1" name="GL11ADD1" value="<%=e.getGL11ADD1()%>" maxlength="4" readonly>
                      </div>
                    </div>
                    
                    <div class="form-group">
                  		<label for="inputPassword3" class="col-sm-3 control-label"></label>
                  		<div class="col-sm-8 col-md-8">
                      		<input type="text" class="form-control" id="GL11ADD2" name="GL11ADD2" value="<%=e.getGL11ADD2()%>" readonly>
                  		</div>
             		</div>
             		
             		<div class="form-group">
                  		<label for="inputPassword3" class="col-sm-3 control-label"></label>
                  		<div class="col-sm-8 col-md-8">
                      		<input type="text" class="form-control" id="GL11ADD3" name="GL11ADD3" value="<%=e.getGL11ADD3()%>" readonly>
                  		</div>
             		</div>
                    
                    <div class="form-group">
                  		<label for="inputPassword3" class="col-sm-3 ">Postcode</label>
                  		<div class="col-sm-3">
                      		<input type="text" class="form-control" id="GL11POSCODE" name="GL11POSCODE" value="<%=e.getGL11POSCODE()%>" readonly>
                  		</div>
             		</div>
             		
             		<!-- Town Code -->
             	<div class="form-group">
                  	<label class="col-sm-3 ">Town</label>
                  	<div class="col-sm-2 col-md-2">
                  	<select class="form-control" id="GL11TOWN" name="GL11TOWN" onchange="document.getElementById('GL15DESC').selectedIndex
                            						= document.getElementById('GL11TOWN').selectedIndex" disabled>
                      	<option value="<%=e.getGL11TOWN() %>"><%=e.getGL11TOWN() %></option>
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
                  	<select class="form-control" id="GL15DESC" name="GL15DESC" onchange="document.getElementById('GL11TOWN').selectedIndex
                            						= document.getElementById('GL15DESC').selectedIndex" disabled>
                      	<option value="<%=e.getDESCRIPTION() %>"><%=e.getDESCRIPTION() %></option>
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
                  		<label for="inputPassword3" class="col-sm-3 ">Telephone Number</label>
                  		<div class="col-sm-3">
                      		<input type="text" class="form-control" id="GL11TEL" name="GL11TEL" value="<%=e.getGL11TEL()%>" readonly>
                  		</div>
             		</div>
             		
             		<div class="form-group">
                  		<label class="col-sm-3 ">Head of Department</label>
                  		<div class="col-sm-5 col-md-3">
                       		<!-- <input type="text" class="form-control" id="patronId" name="GL12TUTOR"> -->
                       		<input type="text" class="form-control" id="GL11HEAD" name="GL11HEAD" value="<%=e.getGL11HEAD()%>" readonly>
                  		</div>
                  		<button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="Modal_PatronSearch" disabled>...</button>
                  		<div class="col-sm-2 col-md-5">
                       	<input type="text" class="form-control" id="patronName" name="patronName" value="<%=e.getHEADNAME()%>" readonly>
                  		</div>
             		</div> 
             		
             		<div class="form-group">
                  		<label for="inputPassword3" class="col-sm-3 ">Number of Staff</label>
                  		<div class="col-sm-3">
                      		<input type="text" class="form-control" id="GL11STAFF" name="GL11STAFF" value="<%=e.getGL11STAFF()%>" readonly>
                  		</div>
             		</div>
             		
             		<div class="form-group">
                  		<label for="inputPassword3" class="col-sm-3 ">Fax Number</label>
                  		<div class="col-sm-3">
                      		<input type="text" class="form-control" id="GL11FAX" name="GL11FAX" value="<%=e.getGL11FAX()%>" readonly>
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
	

	