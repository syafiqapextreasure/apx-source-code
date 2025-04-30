<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.LocationCode.*, java.util.List" %>
<%@ page import="java.sql.*" %>
					
					
<!-- Include Required Prerequisites -->	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/LocationCode.js"></script>	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

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

<%
	String GL05LOCA = request.getParameter("GL05LOCA");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getLoca(GL05LOCA);
	System.out.println(e);
%>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Edit Location Code</h4>
</div>
	
<!-- <form role="form" class="form-horizontal" method="post"> -->
<form role="form" id="locaForm" class="form-horizontal" action="${pageContext.request.contextPath}/UpdateLocation" method="post">
	<div class="modal-body" style="height:500px;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
			<div class="form-group">
                  <label class="col-sm-3 "><strong>Branch</strong></label>
                  <div class="col-sm-3 col-md-3">
                  <select class="form-control" id="GL05BRNC" name="GL05BRNC" onchange="document.getElementById('GL71DESC').selectedIndex
                            						= document.getElementById('GL05BRNC').selectedIndex" disabled>
                      <option value="<%=e.getGL05BRNC()%>"><%=e.getGL05BRNC()%></option>
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
				 <input type="hidden" id="Branch" name="Branch" class="form-control" value="<%=e.getGL05BRNC()%>">
                </div>
               
                  <div class="col-sm-6 col-md-6">
                  <select class="form-control" id="GL71DESC" name="GL71DESC" onchange="document.getElementById('GL05BRNC').selectedIndex
                            						= document.getElementById('GL71DESC').selectedIndex" disabled>
                      <option value="<%=e.getGL05BRNC()%>"><%=e.getGL71DESC()%></option>
                      <%
							GlobalSQLStatement brnchdesc = new GlobalSQLStatement();
							List<Foundation> brnchlist = brnchcode.getBranch();
							for (Foundation list : brnchlist) {
					  %>
					  <option value="<%=list.getGL71BRNC()%>"><%=list.getGL71DESC()%></option>
					  <%
						}
					  %>
				</select>
                </div>
                
                                
             </div>
			
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 "><strong>Location</strong></label>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL05LOCA" name="GL05LOCA" maxlength="4" onkeyup="toUppercase()" value="<%=e.getGL05LOCA()%>" disabled>
                      </div>
                      <input type="hidden" id="Location" name="Location" class="form-control" value="<%=e.getGL05LOCA()%>">
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
                        <input type="text" class="form-control" id="GL05DESC" name="GL05DESC" onkeyup="toUppercase()" value="<%=e.getGL05DESC()%>" >
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Display</label>
                      <%if(e.getGL05DISPLAY()== null){ %>
                      <div class="col-sm-9">
                        <input type="text" class="form-control" id="GL05DISPLAY" name="GL05DISPLAY" onkeyup="toUppercase()" value=" " >
                      </div>
                      <%}else {%>
                      <div class="col-sm-9">
                        <input type="text" class="form-control" id="GL05DISPLAY" name="GL05DISPLAY" onkeyup="toUppercase()" value="<%=e.getGL05DISPLAY()%>" >
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Specialization</label>
                      <div class="col-sm-9">
                        <input type="text" class="form-control" id="GL05SUBJECT" name="GL05SUBJECT" onkeyup="toUppercase()" value="<%=e.getGL05SUBJECT()%>" >
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-3 ">Library Collection</label>
                      <div class="col-sm-5 col-md-3">
                        <input type="text" class="form-control" id="GL05MATCAP" name="GL05MATCAP" value="0" placeholder="titles" onkeypress="return isNumber(event)" value="<%=e.getGL05MATCAP()%>" >
                      </div>
                      
                      <label class="col-sm-3 ">Layout Image Name</label>
                      <%if(e.getGL05LAYOUT()== null){ %>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL05LAYOUT" name="GL05LAYOUT" value=" " >
                      </div>
                      <%}else {%>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL05LAYOUT" name="GL05LAYOUT" value="<%=e.getGL05LAYOUT()%>" >
                      </div>
                      <%} %>
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-3 ">Internet Address</label>
                      <%if(e.getGL05IPADD()== null){ %>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL05IPADD" name="GL05IPADD" value=" " >
                      </div>
                      <%}else{ %>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL05IPADD" name="GL05IPADD" value="<%=e.getGL05IPADD()%>" >
                      </div>
                      <%} %>
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
                        	<input type="text" class="form-control" id="GL05NOSERVER" name="GL05NOSERVER" value="<%=e.getGL05NOSERVER()%>" >
                      	</div>
                      	<label class="col-sm-3 control-label">No. of Line Printers</label>
                      	<div class="col-sm-5 col-md-3">
                        	<input type="text" class="form-control" id="GL05LNPRT" name="GL05LNPRT" value="<%=e.getGL05LNPRT()%>" >
                      	</div>
                      	</div>
                      	
                      	<div class="form-group">
                      	<label class="col-sm-3 control-label">No. of Terminals</label>
                      	<div class="col-sm-5 col-md-3">
                        	<input type="text" class="form-control" id="GL05NOTER" name="GL05NOTER" value="<%=e.getGL05NOTER()%>" >
                      	</div>
                      	<label for="inputEmail3" class="col-sm-3 control-label">No. of Laserjet Printers</label>
                      	<div class="col-sm-5 col-md-3"> 
                        	<input type="text" class="form-control" id="GL05LJPRT" name="GL05LJPRT" value="<%=e.getGL05LJPRT()%>" >
                      	</div>
                      	</div>
                      	
                      	<div class="form-group">
                      	<label class="col-sm-3 control-label">No. of PC's</label>
                      	<div class="col-sm-5 col-md-3">
                        	<input type="text" class="form-control" id="GL05NOPC" name="GL05NOPC" value="<%=e.getGL05NOPC()%>" >
                      	</div>
                      	<label class="col-sm-3 control-label">No. of Dot Matrix Printers</label>
                      	<div class="col-sm-5 col-md-3">
                        	<input type="text" class="form-control" id="GL05DMPRT" name="GL05DMPRT" value="<%=e.getGL05DMPRT()%>" >
                      	</div>
                      	</div>
                      	
                      	<div class="form-group">
                      	<label class="col-sm-3 control-label">No. of Modems</label>
                      	<div class="col-sm-5 col-md-3">
                        	<input type="text" class="form-control" id="GL05MODEM" name="GL05MODEM" value="<%=e.getGL05MODEM()%>" >
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
                      	 <%if (e.getGL05MMEDIA().equals("Y")){ %>
      						<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05MMEDIA" id="GL05MMEDIA" value="Y" checked > Multimedia
      						</label>
      					 <%}else{ %>
      					 	<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05MMEDIA" id="GL05MMEDIA" > Multimedia
      						</label>
      					 <%} %>
      					 
      					 <%if (e.getGL05SDI().equals("Y")){ %>
      						<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05SDI" id="GL05SDI" value="Y" checked > SDI Services
      						</label>
      					 <%}else{ %>
      					 	<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05SDI" id="GL05SDI" > SDI Services
      						</label>
      					 <%} %>
      					 <%if (e.getGL05SDDS().equals("Y")){ %>
      						<label class="col-sm-4 ">
        					<input type="checkbox" class="minimal" name="GL05SDDS" id="GL05SDDS" value="Y" checked > Special Document Service
      						</label>
      					 <%}else{ %>
      					 	<label class="col-sm-4 ">
        					<input type="checkbox" class="minimal" name="GL05SDDS" id="GL05SDDS" > Special Document Service
      						</label>
      					 <%} %>
      					 </div>
      					 
    					 <div class="checkbox">
    					 <%if (e.getGL05JARING().equals("Y")){ %>
      						<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05JARING" id="GL05JARING" value="Y" checked > Jaring
      						</label>
      					 <%}else{ %>
      					 	<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05JARING" id="GL05JARING"> Jaring
      						</label>
      					 <%} %>
      					 
      					 <%if (e.getGL05CDROM().equals("Y")){ %>
      						<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05CDROM" id="GL05CDROM" value="Y" checked > CD-ROM Network
      						</label>
      					 <%}else{ %>
      					 	<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05CDROM" id="GL05CDROM" > CD-ROM Network
      						</label>
      					 <%} %>
      					 <%if (e.getGL05IRL().equals("Y")){ %>
      						<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05IRL" id="GL05IRL" value="Y" checked > Inter Library Loan
      						</label>
      					 <%}else{ %>
      					 	<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="GL05IRL" id="GL05IRL" > Inter Library Loan
      						</label>
      					 <%} %>
    					 </div>
    					 
    					 <div class="checkbox">
    					 <%if (e.getGL05NST().equals("Y")){ %>
      						<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="facilities" id="GL05NST" value="Y" checked > NST / Beriteks
      						</label>
      					 <%}else{ %>
      					 	<label class="col-sm-3 ">
        					<input type="checkbox" class="minimal" name="facilities" id="GL05NST" > NST / Beriteks
      						</label>
      					 <%} %>
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
	<!-- <button type="submit" class="btn btn-primary" id="btn-add" onclick="updateLocation()">Save</button> -->
	<button type="submit" class="btn btn-primary" id="btn-save">Save</button>
		 <a href="template?MODULE=Foundation/06_LocationCode&ACTION=LocaTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
         </a>
	</div>			
	</form>
	

	