<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.ItemCategory.*, java.util.List" %>
<%@ page import="java.sql.*" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/LocationCode.js"></script>	
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->



<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> --%>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>

<script>

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
    	return false;
    }
    return true;
}
</script>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Add Location Code</h4>
</div>
	
<!-- <form role="form" class="form-horizontal" method="post"> -->
<form role="form" id="locaForm" class="form-horizontal" action="${pageContext.request.contextPath}/addLoca" method="post">
	<div class="modal-body" style="height:500px;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
			<div class="form-group">
                  <label class="col-sm-3 "><strong>Branch</strong></label>
                  <div class="col-sm-3 col-md-3">
                  <select class="form-control" id="GL05BRNC" name="GL05BRNC" onchange="document.getElementById('GL71DESC').selectedIndex
                            						= document.getElementById('GL05BRNC').selectedIndex" required>
                      <option value="">Please Select</option>
                      <%
							GlobalSQLStatement brnchcode = new GlobalSQLStatement();
							List<Foundation> brnch = brnchcode.getBranch();
							for (Foundation list : brnch) {
					  %>
					  <option value="<%=list.getGL71BRNC()%>"><%=list.getGL71BRNC()%></option>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-6 col-md-6">
                  <select class="form-control" id="GL71DESC" name="GL71DESC" onchange="document.getElementById('GL05BRNC').selectedIndex
                            						= document.getElementById('GL71DESC').selectedIndex" required>
                      <option value="">Please Select</option>
                      <%
                      GlobalSQLStatement brnchdesc = new GlobalSQLStatement();
							List<Foundation> brnchlist = brnchdesc.getBranch();
							for (Foundation list : brnchlist) {
					  %>
					  <option value="<%=list.getGL71DESC()%>"><%=list.getGL71DESC()%></option>
					  <%
						}
					  %>
				</select>
                </div>
                
                                
             </div>
			
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 "><strong>Location</strong></label>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL05LOCA" name="GL05LOCA" maxlength="4" onkeyup="toUppercase()" required>
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
                      <div class="col-sm-9">
                        <input type="text" class="form-control" id="GL05DESC" name="GL05DESC" onkeyup="toUppercase()" required>
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Display</label>
                      <div class="col-sm-9">
                        <input type="text" class="form-control" id="GL05DISPLAY" name="GL05DISPLAY" onkeyup="toUppercase()" >
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Specialization</label>
                      <div class="col-sm-9">
                        <input type="text" class="form-control" id="GL05SUBJECT" name="GL05SUBJECT" onkeyup="toUppercase()" >
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-3 ">Library Collection</label>
                      <div class="col-sm-5 col-md-3">
                        <input type="text" class="form-control" id="GL05MATCAP" name="GL05MATCAP" placeholder="titles" onkeypress="return isNumber(event)">
                      </div>
                      
                      <label class="col-sm-3 ">Layout Image Name</label>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL05LAYOUT" name="GL05LAYOUT">
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-3 ">Internet Address</label>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL05IPADD" name="GL05IPADD">
                      </div>
                    </div>
                    
                    <div class="panel-group">
    				<div class="panel panel-default">
      				<div class="panel-heading">
					<h3 class="panel-title">Resources Available</h3>
					</div>
					<div class="panel-body">
      					<div class="form-group">
                      	<label class="col-sm-3 control-label">No. of Servers</label>
                      	<div class="col-sm-5 col-md-3">
                        	<input type="text" class="form-control" id="GL05NOSERVER" name="GL05NOSERVER" value="0">
                      	</div>
                      	<label class="col-sm-3 control-label">No. of Line Printers</label>
                      	<div class="col-sm-5 col-md-3">
                        	<input type="text" class="form-control" id="GL05LNPRT" name="GL05LNPRT" value="0">
                      	</div>
                      	</div>
                      	
                      	<div class="form-group">
                      	<label class="col-sm-3 control-label">No. of Terminals</label>
                      	<div class="col-sm-5 col-md-3">
                        	<input type="text" class="form-control" id="GL05NOTER" name="GL05NOTER"  value="0">
                      	</div>
                      	<label for="inputEmail3" class="col-sm-3 control-label">No. of Laserjet Printers</label>
                      	<div class="col-sm-5 col-md-3">
                        	<input type="text" class="form-control" id="GL05LJPRT" name="GL05LJPRT" value="0">
                      	</div>
                      	</div>
                      	
                      	<div class="form-group">
                      	<label class="col-sm-3 control-label">No. of PC's</label>
                      	<div class="col-sm-5 col-md-3">
                        	<input type="text" class="form-control" id="GL05NOPC" name="GL05NOPC" value="0">
                      	</div>
                      	<label class="col-sm-3 control-label">No. of Dot Matrix Printers</label>
                      	<div class="col-sm-5 col-md-3">
                        	<input type="text" class="form-control" id="GL05DMPRT" name="GL05DMPRT" value="0">
                      	</div>
                      	</div>
                      	
                      	<div class="form-group">
                      	<label class="col-sm-3 control-label">No. of Modems</label>
                      	<div class="col-sm-5 col-md-3">
                        	<input type="text" class="form-control" id="GL05MODEM" name="GL05MODEM" value="0">
                      	</div>
                      	</div>
                    </div>
    				</div>
    				</div>
                    
                     <div class="panel-group">
    				<div class="panel panel-default">
      				<div class="panel-heading">
					<h3 class="panel-title">Facilities Offered</h3>
					</div>
					<div class="panel-body">
      					<div class="form-group">
                      	 <div class="checkbox">
      						<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05MMEDIA" id="GL05MMEDIA" value="Y"> Multimedia
      						</label>
      						
      						<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05SDI" id="GL05SDI" value="Y"> SDI Services
      						</label>
      						
      						<label class="col-sm-4 ">
        					<input type="checkbox" class="minimal" name="GL05SDDS" id="GL05SDDS" value="Y"> Special Document Service
      						</label>
      					 </div>
      					 
    					 <div class="checkbox">
      						<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05JARING" id="GL05JARING" value="Y"> Jaring
      						</label>
      						
      						<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05CDROM" id="GL05CDROM" value="Y"> CD-ROM Network
      						</label>
      						
      						<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05IRL" id="GL05IRL" value="Y"> Inter Library Loan
      						</label>
    					 </div>
    					 
    					 <div class="checkbox">
      						<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05NST" id="GL05NST" value="Y"> NST / Beriteks
      						</label>
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
		<!-- <button type="submit" class="btn btn-primary" id="btn-add" style="width:80px;" onclick="addLocation()">Save</button> -->
		<input type="submit" class="btn btn-primary" value="Save" id="btn-add" style="width:80px;">
		 <a href="template?MODULE=Foundation/06_LocationCode&ACTION=LocaTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
            </a>
	</div>			
	</form>
	

	