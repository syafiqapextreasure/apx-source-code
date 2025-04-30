<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.TagParameter.*, java.util.List" %>
<%@ page import="java.sql.*" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/TagParameter.js"></script>	
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
	String GL17MARC = request.getParameter("GL17MARC");
	String GL17TAG = request.getParameter("GL17TAG");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getTagP(GL17MARC,GL17TAG);
	System.out.println(e);
%>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Edit Tag Parameter</h4>
</div>
	
<form role="form" class="form-horizontal" method="post">
	<div class="modal-body" style="height:500px;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-13">
			
					<div class="form-group">
						<label class="col-sm-1 "></label>
                  		<label class="col-sm-2 "><strong>MARC</strong></label>
                  		<div class="col-sm-2 col-md-2">
                  		<select class="form-control" id="GL17MARC" name="GL17MARC" onchange="document.getElementById('GL16DESC').selectedIndex
                            						= document.getElementById('GL17MARC').selectedIndex" disabled>
                      		<option value="0">Select</option>
                      		<%
                      			GlobalSQLStatement marccode = new GlobalSQLStatement();
								List<Foundation> marc = marccode.getMARC();
								for (Foundation list : marc) {
					 		%>
					  		<option value="<%=list.getGL16MARC()%>"><%=list.getGL16MARC()%></option>
					  		<%
								}
					  		%>
						</select>
                		</div>
               
                  		<div class="col-sm-6 col-md-6">
                  		<select class="form-control" id="GL16DESC" name="GL16DESC" onchange="document.getElementById('GL17MARC').selectedIndex
                            						= document.getElementById('GL16DESC').selectedIndex" disabled>
                      		<option value="0">Please Select</option>
                      		<%
								GlobalSQLStatement marcdesc = new GlobalSQLStatement();
								List<Foundation> marclist = marcdesc.getMARC();
								for (Foundation list : marclist) {
					  		%>
					  		<option value="<%=list.getGL16MARC()%>"><%=list.getGL16DESC()%></option>
					  		
					  		<%
								}
					  		%>
						</select>
                		</div>
                
                                
             		</div>
             
             		<div class="form-group">
             			<label class="col-sm-1 "></label>
                  		<label class="col-sm-2 "><strong>Tag</strong></label>
                  		<div class="col-sm-2 col-md-2">
                     		<input type="text" class="form-control" id="GL17TAG" name="GL17TAG" maxlength="3" value="<%=e.getGL17TAG()%>" readonly>
                  		</div>
             
                  		<label class="col-sm-2 control-label">Revised from Tag</label>
                  		<div class="col-sm-3">
                      		<input type="text" class="form-control" id="GL11POSCODE" name="GL11POSCODE" value="">
                  		</div>
             		</div>
             		<div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group">
                    	<label class="col-sm-1 "></label>
                  		<label for="inputPassword3" class="col-sm-2">Description</label>
                  		<div class="col-sm-8">
                     		<input type="text" class="form-control" id="GL17DESC" name="GL17DESC" value="<%=e.getGL17DESC()%>">
                  		</div>
             		</div>
                    
             		<div class="form-group">
             			<label class="col-sm-1 "></label>
                  		<label class="col-sm-2">Abbreviated Desc.</label>
                  		<div class="col-sm-8">
                      		<input type="text" class="form-control" id="GL17TRUC" name="GL17TRUC" value="<%=e.getGL17TRUC()%>">
                  		</div>
             		</div> 
             		
             		<!-- START CUSTOM TABS -->
          <h2 class="page-header"></h2>
          <div class="row">
            <div class="col-md-12">
              <!-- Custom Tabs -->
              <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                  <li class="active"><a href="#tab_1" data-toggle="tab"><strong>Parameters</strong></a></li>
                  <li><a href="#tab_2" data-toggle="tab"><strong>Indicator 1</strong></a></li>
                  <li><a href="#tab_3" data-toggle="tab"><strong>Indicator 2</strong></a></li>
                  <li><a href="#tab_4" data-toggle="tab"><strong>Subfields (a-j)</strong></a></li>
                  <li><a href="#tab_5" data-toggle="tab"><strong>Subfields (k-t)</strong></a></li>
                  <li><a href="#tab_6" data-toggle="tab"><strong>Subfields (u-z)</strong></a></li>
                  <li><a href="#tab_7" data-toggle="tab"><strong>Subfields (0-9)</strong></a></li>
                </ul>
                <div class="tab-content">
                  <div class="tab-pane active" id="tab_1">
                    <div class="form-group">
                  	<label class="col-sm-2 "><b>Index Table</b></label>
                  	<div class="col-sm-4">
                      <input type="text" class="form-control" id="GL17TABNAME" name="GL17TABNAME" value="<%=e.getGL17TABNAME()%>">
                  	</div>
                  	
                  	<div class="col-md-6">
                      	<div class="checkbox">
                      	<%if (e.getGL17REPEAT().equals("Y")){  %>
      						<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=GL17REPEAT id="GL17REPEAT" checked>Repeatable
      						</label>
      				  	<%}else{ %>
      				  		<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=GL17REPEAT id="GL17REPEAT">Repeatable
      						</label>
      				  	<%} %>
      				  	<%if (e.getGL17AUTFLAG().equals("Y")){  %>
      						<label class="col-sm-5 ">
      						<input type="checkbox" class="minimal" name=GL17AUTFLAG id="GL17AUTFLAG" checked>Authority Flag
      						</label>
      				  	<%}else{ %>
      				  		<label class="col-sm-5 ">
      						<input type="checkbox" class="minimal" name=GL17AUTFLAG id="GL17AUTFLAG">Authority Flag
      						</label>
      				  	<%} %>
    					</div>
    				</div>
             		</div> 
             		
             		<div class="form-group">
                  	<label class="col-sm-2 ">Acronym</label>
                  	<div class="col-sm-4">
                      <input type="text" class="form-control" id="GL17ACRO" name="GL17ACRO" value="<%=e.getGL17ACRO()%>">
                  	</div>
                  	<div class="col-md-6">
                      	<div class="checkbox">
                      	<%if (e.getGL17MANDA().equals("Y")){  %>
      						<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=GL17MANDA id="GL17MANDA" checked>Mandatory
      						</label>
      				  	<%}else{ %>
      				  		<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=GL17MANDA id="GL17MANDA">Mandatory
      						</label>
      				  	<%} %>
      				  	<%if (e.getGL17CPFLAG().equals("Y")){  %>
      						<label class="col-sm-5 ">
      						<input type="checkbox" class="minimal" name=GL17CPFLAG id="GL17CPFLAG" checked>Copy and Paste
      						</label>
      				  	<%}else{ %>
      				  		<label class="col-sm-5 ">
      						<input type="checkbox" class="minimal" name=GL17CPFLAG id="GL17CPFLAG">Copy and Paste
      						</label>
      				  	<%} %>
    					</div> 
    				</div>
             		</div> 
             		
             		<div class="form-group"> 
                  	<label class="col-sm-2 ">Authority Group</label>
                  	<div class="col-sm-4">
                      <input type="text" class="form-control" id="GL17GRID" name="GL17GRID" value="<%=e.getGL17GRID()%>">
                  	</div>
                  	<div class="col-md-6">
                      	<div class="checkbox">
                      	<%if (e.getGL17IDXFLAG().equals("Y")){  %>
      						<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=GL17IDXFLAG id="GL17IDXFLAG" checked>Index Flag
      						</label>
      				  	<%}else{ %>
      				  		<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=GL17IDXFLAG id="GL17IDXFLAG">Index Flag
      						</label>
      				  	<%} %>
      				  	<%if (e.getGL17PARAMIPS().equals("Y")){  %>
      						<label class="col-sm-5 ">
      						<input type="checkbox" class="minimal" name=GL17PARAMIPS id="GL17PARAMIPS" checked>PARAMIPS Link
      						</label>
      				  	<%}else{ %>
      				  		<label class="col-sm-5 ">
      						<input type="checkbox" class="minimal" name=GL17PARAMIPS id="GL17PARAMIPS">PARAMIPS Link
      						</label>
      				  	<%} %>
    					</div>
    				</div>
             		</div> 
             		
             		<div class="form-group">
                  	<label class="col-sm-2 ">Field Length</label>
                  	<div class="col-sm-4">
                      <input type="text" class="form-control" id="GL17LEN" name="GL17LEN" value="<%=e.getGL17LEN()%>">
                  	</div>
                  	<div class="col-md-6">
                      	<div class="checkbox">
                      	<%if (e.getGL17KEYWORD().equals("Y")){  %>
      						<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=GL17KEYWORD id="GL17KEYWORD" checked>Keyword
      						</label>
      				  	<%}else{ %>
      				  		<label class="col-sm-4 ">
      						<input type="checkbox" class="minimal" name=GL17KEYWORD id="GL17KEYWORD">Keyword
      						</label>
      				  	<%} %>
      				  	<%if (e.getGL17MEDIA().equals("Y")){  %>
      						<label class="col-sm-5 ">
      						<input type="checkbox" class="minimal" name=GL17MEDIA id="GL17MEDIA" checked>Multimedia Tag
      						</label>
      				  	<%}else{ %>
      				  		<label class="col-sm-5 ">
      						<input type="checkbox" class="minimal" name=GL17MEDIA id="GL17MEDIA">Multimedia Tag
      						</label>
      				  	<%} %>
    					</div>
    				</div>
             		</div> 
             		
             		<div class="form-group">
                  	<label class="col-sm-2 ">Index Language</label>
                  	<div class="col-sm-4">
                      <input type="text" class="form-control" id="GL17IDXLANG" name="GL17IDXLANG" value="<%=e.getGL17IDXLANG()%>">
                  	</div>
                  	<div class="col-md-6">
                      	<div class="checkbox">
                      	<%if (e.getGL17STOP().equals("Y")){  %>
      						<label class="col-sm-5 ">
      						<input type="checkbox" class="minimal" name=GL17STOP id="GL17STOP" checked>Duplicate Check
      						</label>
      				  	<%}else{ %>
      				  		<label class="col-sm-5 ">
      						<input type="checkbox" class="minimal" name=GL17STOP id="GL17STOP">Duplicate Check
      						</label>
      				  	<%} %>
    					</div>
    				</div>
             		</div> 
             		
             	  <div class="form-group">
                  <label class="col-sm-2 ">Default Indicator</label>
                  <label class="col-sm-2 control-label">Indicator 1 </label>
                  <div class="col-sm-1 col-md-1">
                  <select class="form-control" id="GL17INDI1" name="GL17INDI1">
                      
					  <option value="#">#</option>
					  <option value="1">1</option>
					  <option value="2">2</option>
					  <option value="3">3</option>
					  <option value="4">4</option>
					  <option value="5">5</option>
					  <option value="6">6</option>
					  <option value="7">7</option>
					  <option value="8">8</option>
					  <option value="9">9</option>
					  
				</select>
                </div>
               <label class="col-sm-2 control-label">Indicator 2 </label>
                  <div class="col-sm-1 col-md-1">
                  <select class="form-control" id="GL17INDI2" name="GL17INDI2">
                      
					  <option value="#">#</option>
					  <option value="1">1</option>
					  <option value="2">2</option>
					  <option value="3">3</option>
					  <option value="4">4</option>
					  <option value="5">5</option>
					  <option value="6">6</option>
					  <option value="7">7</option>
					  <option value="8">8</option>
					  <option value="9">9</option>
					  
				</select>
                </div>      
             </div>
             
             		<div class="form-group">
                  	<label class="col-sm-2 ">Default Value</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL17DEFAULT" name="GL17DEFAULT" value="<%=e.getGL17DEFAULT()%>">
                  	</div>
             		</div>
             		
             		<div class="form-group">
                  	<label class="col-sm-2 ">Remark</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL17REMARK" name="GL17REMARK" value="<%=e.getGL17REMARK()%>">
                  	</div>
             		</div>
             
             
                  </div><!-- /.tab-pane -->
                  
                  <!-- Tab 2 -->
                  
                  <div class="tab-pane" id="tab_2">
                 
                  <div class="form-group">
                  
                  	<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_1" id="0" value="0" onclick = "change()"> Undefined (#)
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_1" id="0" value="0" onclick = "change()"> 0
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_1" id="1" value="1" onclick = "change()"> 1
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_1" id="2" value="2" onclick = "change()"> 2
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_1" id="3" value="3" onclick = "change()"> 3
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_1" id="4" value="4" onclick = "change()"> 4
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_1" id="5" value="5" onclick = "change()"> 5
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_1" id="6" value="6" onclick = "change()"> 6
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<!-- <div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_1" id="6" value="6" onclick = "change()"> 6
        					</label>
                  	<div class="col-sm-9"></div> 
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div> -->
                  	
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_1" id="7" value="7" onclick = "change()"> 7
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_1" id="8" value="8" onclick = "change()"> 8
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_1" id="9" value="9" onclick = "change()"> 9
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  
                  </div><!-- /.tab-pane -->
                 
                  <!-- Tab 3 -->
                  <div class="tab-pane" id="tab_3">
                   <div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_2" id="Undefined" value="#" onclick = "change()"> Undefined (#)
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_2" id="0" value="0" onclick = "change()"> 0
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_2" id="1" value="1" onclick = "change()"> 1
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_2" id="2" value="2" onclick = "change()"> 2
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_2" id="3" value="3" onclick = "change()"> 3
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_2" id="4" value="4" onclick = "change()"> 4
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_2" id="5" value="5" onclick = "change()"> 5
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_2" id="6" value="6" onclick = "change()"> 6
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_2" id="7" value="7" onclick = "change()"> 7
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_2" id="8" value="8" onclick = "change()"> 8
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL18INDI_2" id="9" value="9" onclick = "change()"> 9
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  </div><!-- /.tab-pane -->
                  
                  <!-- Tab 4 -->
                  <div class="tab-pane" id="tab_4">
                   <div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> a
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> b
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> c
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> d
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> e
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> f
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> g
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> h
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> i
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> j
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  </div><!-- /.tab-pane -->
                  
                  <!-- Tab 5 -->
                  <div class="tab-pane" id="tab_5">
                   <div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> k
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> l
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> m
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> n
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> o
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> p
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> q
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> r
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> s
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> t
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  </div><!-- /.tab-pane -->
                  
                  <!-- Tab 6 -->
                  <div class="tab-pane" id="tab_6">
                   <div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> u
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> v
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> w
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> x
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> y
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> z
        					</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  </div><!-- /.tab-pane -->
                  
                  <!-- Tab 7 -->
                  <div class="tab-pane" id="tab_7">
                   <div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 0
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 1
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 2
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 3
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 4
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 5
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 6
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 7
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 8
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 9
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  </div><!-- /.tab-pane -->
                </div><!-- /.tab-content -->
              </div><!-- nav-tabs-custom -->
            </div><!-- /.col -->
            </div>
                    
                     
                    
                   
              
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		<button type="submit" class="btn btn-primary" id="btn-save">Save</button>
		<a href="template?MODULE=Foundation/08_TagParameter&ACTION=TagTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
         </a>
	</div>			
	</form>
	

	