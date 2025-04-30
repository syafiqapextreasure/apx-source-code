<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.VendorCode.*, java.util.List" %>
<%@ page import="java.sql.*" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/VendorCode.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Delete.js"></script>
<%-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> --%>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>

<%
	String GL39CODE = request.getParameter("GL39CODE");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getVendor(GL39CODE);
	System.out.println(e);
%>

<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">View Vendor Code</h4>
</div>
	
<form role="form" class="form-horizontal" method="post">
	<div class="modal-body" style="height:500px;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-12">
			
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 "><strong>Vendor Code</strong></label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL39CODE" name="GL39CODE" maxlength="3" onkeyup="toUppercase()" value="<%=e.getGL39CODE()%>" readonly>
                      </div>
                      
                      <div class="checkbox">
                      <%if (e.getGL39BINDER().equals("Y")){  %>
      						<label class="col-sm-2 ">
      						<input type="checkbox" class="minimal" name=GL39BINDER id="GL39BINDER" checked disabled>Binder
      						</label>
      				  <%}else{ %>
      				  		<label class="col-sm-2 ">
      						<input type="checkbox" class="minimal" name=GL39BINDER id="GL39BINDER" disabled>Binder
      						</label>
      				  <%} %>
      				  <%if (e.getGL39SUPPLIER().equals("Y")){  %>
      						<label class="col-sm-2 ">
      						<input type="checkbox" class="minimal" name=GL39SUPPLIER id="GL39SUPPLIER" checked disabled>Vendor
      						</label>
      				  <%}else{ %>
      						<label class="col-sm-2 ">
      						<input type="checkbox" class="minimal" name=GL39SUPPLIER id="GL39SUPPLIER" disabled>Vendor
      						</label>
      			      <%} %>
      				  <%if (e.getGL39PUB().equals("Y")){  %>
      						<label class="col-sm-2 ">
      						<input type="checkbox" class="minimal" name=GL39PUB id="GL39PUB" checked disabled>Publisher
      						</label>
      				  <%}else{ %>
      						<label class="col-sm-2 ">
      						<input type="checkbox" class="minimal" name=GL39PUB id="GL39PUB" disabled>Publisher
      						</label>
      				  <%} %>
      				  </div>  
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-4 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Vendor Name</strong></label>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39NAME" name="GL39NAME" onkeyup="toUppercase()" value="<%=e.getGL39NAME()%>" readonly>
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Person In Charge</label>
                      <%if (e.getGL39PERINC()==null){  %>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39PERINC" name="GL39PERINC" onkeyup="toUppercase()" value=" " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39PERINC" name="GL39PERINC" onkeyup="toUppercase()" value="<%=e.getGL39PERINC()%>" readonly>
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Designation</label>
                      <%if (e.getGL39DESIG()==null){  %>
                      <div class="col-sm-5">
                        <input type="text" class="form-control" id="GL39DESIG" name="GL39DESIG" onkeyup="toUppercase()" value=" " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-5">
                        <input type="text" class="form-control" id="GL39DESIG" name="GL39DESIG" onkeyup="toUppercase()" value="<%=e.getGL39DESIG()%>" readonly>
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Address</label>
                      <%if (e.getGL39ADD1()==null){  %>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39ADD1" name="GL39ADD1"  value= "  " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39ADD1" name="GL39ADD1"  value="<%=e.getGL39ADD1()%>" readonly>
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "></label>
                      <%if (e.getGL39ADD2()==null){  %>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39ADD2" name="GL39ADD2" value=" " readonly >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39ADD2" name="GL39ADD2" value="<%=e.getGL39ADD2()%>" readonly >
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "></label>
                      <%if (e.getGL39ADD3()==null){  %>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39ADD3" name="GL39ADD3" value=" " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39ADD3" name="GL39ADD3" value="<%=e.getGL39ADD3()%>" readonly>
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Poscode</label>
                      <%if (e.getGL39PCODE()==null){  %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39PCODE" name="GL39PCODE" maxlength="5" value=" " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39PCODE" name="GL39PCODE" maxlength="5" value="<%=e.getGL39PCODE()%>" readonly>
                      </div>
                      <%} %>
                    
                      <label for="inputPassword3" class="col-sm-2 ">IP Address</label>
                      <%if (e.getGL39IPADD()==null){  %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39IPADD" name="GL39IPADD" value=" " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39IPADD" name="GL39IPADD" value="<%=e.getGL39IPADD()%>" readonly>
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Telephone No.</label>
                      <%if (e.getGL39TELNO()==null){  %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39TELNO" name="GL39TELNO" value=" " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39TELNO" name="GL39TELNO" value="<%=e.getGL39TELNO()%>" readonly>
                      </div>
                      <%} %>
                    
                      <label for="inputPassword3" class="col-sm-2 ">Fax/Telex No.</label>
                      <%if (e.getGL39FAX()==null){  %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39FAX" name="GL39FAX" value=" " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39FAX" name="GL39FAX" value="<%=e.getGL39FAX()%>" readonly>
                      </div>
                      <%} %>
                    </div>
                    
                     <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Contract No.</label>
                      <%if (e.getGL39CONTNO()==null){  %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39CONTNO" name="GL39CONTNO" value=" " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39CONTNO" name="GL39CONTNO" value="<%=e.getGL39CONTNO()%>" readonly>
                      </div>
                      <%} %>
                      </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Contract Begin</label>
                      <%if (e.getGL39CBDATE()==null || e.getGL39CBDATE()=="-"){  %>
                      <div class='col-sm-3'>
                      		<div class="input-group date">
  						 		<input type="text" class="form-control" id="GL39CBDATE" name="GL39CBDATE" value="" readonly>
  						 		<span class="input-group-addon">
  								<i class="glyphicon glyphicon-calendar"></i></span>
							</div>
        			  </div>
        			  <%}else{ %>
        			  <div class='col-sm-3'>
                      		<div class="input-group date">
  						 		<input type="text" class="form-control" id="GL39CBDATE" name="GL39CBDATE" value="<%=e.getGL39CBDATE()%>" readonly>
  						 		<span class="input-group-addon">
  								<i class="glyphicon glyphicon-calendar"></i></span>
							</div>
        			  </div>
        			  <%} %>
                    
                      
                      <label for="inputPassword3" class="col-sm-2 ">Contract End</label>
                      <%if (e.getGL39CEDATE()==null || e.getGL39CEDATE()=="-"){  %>
                      <div class='col-sm-3'>
                      		<div class="input-group date">
  						 		<input type="text" class="form-control" id="GL39CEDATE" name="GL39CEDATE" value=" " readonly>
  						 		<span class="input-group-addon">
  								<i class="glyphicon glyphicon-calendar"></i></span>
							</div>
        			  </div>
        			  <%}else{ %>
        			  <div class='col-sm-3'>
                      		<div class="input-group date">
  						 		<input type="text" class="form-control" id="GL39CEDATE" name="GL39CEDATE" value="<%=e.getGL39CEDATE()%>" readonly>
  						 		<span class="input-group-addon">
  								<i class="glyphicon glyphicon-calendar"></i></span>
							</div>
        			  </div>
        			  <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Username</label>
                      <%if (e.getGL39USERNAME()==null){  %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39USERNAME" name="GL39USERNAME" value=" " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39USERNAME" name="GL39USERNAME" value="<%=e.getGL39USERNAME()%>" readonly>
                      </div>
                      <%} %>
                    
                      <label for="inputPassword3" class="col-sm-2 ">Password</label>
                      <%if (e.getGL39PASSWORD()==null){  %>
                      <div class="col-sm-3">
                        <input type="password" class="form-control" id="GL39PASSWORD" name="GL39PASSWORD" value=" " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-3">
                        <input type="password" class="form-control" id="GL39PASSWORD" name="GL39PASSWORD" value="<%=e.getGL39PASSWORD()%>" readonly>
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Bank Account No.</label>
                      <%if (e.getGL39ACCNO()==null){  %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39ACCNO" name="GL39ACCNO" value=" " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39ACCNO" name="GL39ACCNO" value="<%=e.getGL39ACCNO()%>" readonly>
                      </div>
                      <%} %>
                    
                      <label for="inputPassword3" class="col-sm-2 ">Bank</label>
                      <%if (e.getGL39BANK()==null){  %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39BANK" name="GL39BANK" value=" " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-3">
                        <input type="text" class="form-control" id="GL39BANK" name="GL39BANK" value="<%=e.getGL39BANK()%>" readonly>
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Email</label>
                      <%if (e.getGL39EMAIL()==null){  %>
                      <div class="col-sm-5">
                        <input type="email" class="form-control" id="GL39EMAIL" name="GL39EMAIL" placeholder="testing@yahoo.com" value="  " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-5">
                        <input type="email" class="form-control" id="GL39EMAIL" name="GL39EMAIL" placeholder="testing@yahoo.com" value="<%=e.getGL39EMAIL()%>" readonly>
                      </div>
                      <%} %>
                    
                      <div class="checkbox">
                      <%if (e.getGL39PUB().equals("Y")){  %>
      					<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=GL39INDI id="GL39INDI" checked disabled>Active
      					</label>
      					<%}else{ %>
      					<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=GL39INDI id="GL39INDI">Active
      					</label>
      					<%} %>
      				  </div>  
      				</div>
      				
      				<div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Remarks</label>
                      <%if (e.getGL39REMARK()==null){  %>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39REMARK" name="GL39REMARK" value=" " readonly>
                      </div>
                      <%}else{ %>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" id="GL39REMARK" name="GL39REMARK" value="<%=e.getGL39REMARK()%>" readonly>
                      </div>
                      <%} %>
                    </div>
                    
                     
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		
		 <a href="template?MODULE=Foundation/07_VendorCode&ACTION=VendorTable.jsp">
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
	