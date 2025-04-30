
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%>
<%@page import="com.ilmu.foundation.PatronDetails.RetrievePatron"%>
<%@ page import="com.ilmu.foundation.global.*, java.util.List, com.ilmu.foundation.PatronDetails.PatCriteria" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/PatronDetails.js"></script>					
<!-- Include Required Prerequisites -->

<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>  --%>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

#red {
	color: red;
}

</style>

<script>
$('#viewModal').on('hidden', function () {
	  document.location.reload();
	})
</script>

<style>
<%
List<PatCriteria> lists = PatCriteria.retrievePatrDetails();
for (PatCriteria i : lists) {
	if((i.getGL76MANDAT()).equals("Y")){
%>
	label[id="<%=i.getGL76LBLNAME()%>"]{
	font-weight:bold;
	}
	
	
<%
	}
}
%>
</style>

<%
	String GL14PATR = request.getParameter("GL14PATR");
	System.out.println(GL14PATR + " GL14PATR");
	String GL14NAMETITLE = request.getParameter("GL14NAMETITLE");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getPatDetail(GL14PATR);
	System.out.println(e + "cf");
	System.out.println("dgd" +e.getGL14RELID());
	System.out.println("dgd2" +e.getGL14TOWN());
	System.out.println("dgd3" +e.getGL14TOWN2());
%>

<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Edit Patron Details</h4>
	</div>
	<form role="form" id="patronForm" class="form-horizontal" action="${pageContext.request.contextPath}/UpdatePatronDetails" enctype="multipart/form-data" method="post">
	<div class="modal-body" style="height:70%;overflow:auto">
		<div class="panel-body">
			
					<div class="row">
	<div class="col-md-13">
	<!-- Custom Tabs -->
		<div class="nav-tabs-custom">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#1" data-toggle="tab" aria-expanded="false"><strong>Patron Details</strong></a></li>
				<li><a href="#2" data-toggle="tab"><strong>Personal</strong></a></li>
				<li><a href="#3" data-toggle="tab" aria-expanded="true"><strong>Professional</strong></a></li>
             	<li><a href="#4" data-toggle="tab"><strong>Residence</strong></a></li>
             	<li><a href="#5" data-toggle="tab" aria-expanded="true"><strong>Residence (2)</strong></a></li>
             	<li><a href="#6" data-toggle="tab"><strong>Office</strong></a></li>
             	<li><a href="#7" data-toggle="tab"><strong>Miscellanous</strong></a></li>
			</ul>
			
		<!-- TAB CONTENT -->
		<div class="tab-content">
		
		<!-- TAB ONE -->
			<div class="tab-pane active" id="1">
			<div class="box-body">
			<div class="row">
			
			<div class="col-sm-2 col-md-3">			
					 <%	
          				System.out.println(e.getGL14IMG() + " img");
					 	if((e.getGL14IMG())== null || (e.getGL14IMG()).equals("0") || (e.getGL14IMG()).equals("-1")){
          			 %>
          			
          			<div class="fileinput fileinput-new" data-provides="fileinput">
  						<div class="fileinput-new thumbnail" style="width: 150px; height: 150px;">
  						 	<img src="${pageContext.request.contextPath}/resources/image/avatar.jpg" style="width:150px">
    					</div>
  						<div class="fileinput-preview fileinput-exists thumbnail" style="width:auto"></div>
  						<div>
    						<span class="btn btn-default btn-file"><span class="fileinput-new">
    						<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>   Browse image</span>
    						<span class="fileinput-exists">Change</span>
    						<input type="file" name="photo"></span>
    						<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
  						</div>
					</div>
					
					<%}else{%>
					
					<div class="fileinput fileinput-new" data-provides="fileinput">
  						<div class="fileinput-new thumbnail" style="width: auto; height: 150px;">
  						 	<img src="${pageContext.servletContext.contextPath }/PhotoServlet?GL14PATR=<%=e.getGL14PATR()%>" style="width:auto" />
    					</div>
  						<div class="fileinput-preview fileinput-exists thumbnail" style="width:auto"></div>
  						<div>
    						<span class="btn btn-default btn-file"><span class="fileinput-new">
    						<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>   Browse image</span>
    						<span class="fileinput-exists">Change</span>
    						<input type="file" name="photo"></span>
    						<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
  						</div>
					</div>
					
					<%}%>
          			
			</div>
					
			<div class="col-md-9">	
				<div class="form-group">
                  	
                      <label class="col-sm-2 " id="lblPatron(0)"><b>Patron ID<span id="red">*</span></b></label>
                      <div class="col-md-4">
                        <input type="text" class="form-control" id="GL14PATR" name="GL14PATR"  value="<%=e.getGL14PATR()%>" disabled>
                     </div>
                     <input type="hidden" id="GL14PATR2" name="GL14PATR2" class="form-control" value="<%=e.getGL14PATR()%>">

                      <label class="col-sm-2 control-label" id="lblPatron(2)"><b>Password</b><span id="red">*</span></label>
                      <div class="col-sm-5 col-md-2">
                        <input type="password" class="form-control" id="GL14PASW" name="GL14PASW" value="<%=e.getGL14PASW()%>">
                      </div>
                      <!-- <div class="col-sm-3">  
                      <input id="checkBox" type="checkbox" onclick="showPassword()">
                      <label>Show Password</label>
                     </div> -->
                    </div>
                 
                 <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                 </div>
                 
                  <input type="hidden" id='loginid' name="loginid">
                 
                  <!-- Name Title -->
             		<div class="form-group">
                  	<label class="col-sm-2 " id="lblPatron(14)">Name Title</label>
                  	<div class="col-sm-1 col-md-2">
                  		<select class="form-control" id="plkupDetails(5)" name="GL14NAMETITLE" onchange="document.getElementById('name').selectedIndex
                            						= document.getElementById('plkupDetails(5)').selectedIndex">
                      	<%	if((e.getGL14NAMETITLE()) == null || (e.getGL14NAMETITLE()).isEmpty()) {%> 
                      	<!-- if((e.getGL14NAMETITLE()).equals("") ||(e.getGL14NAMETITLE()) == null || (e.getGL14NAMETITLE()).isEmpty()) { -->
                      	
                     	<option value="">Please select </option>
                     	<%
                      	GlobalSQLStatement title = new GlobalSQLStatement();
							List<Foundation> titlelist = title.getNameTitle();
							for (Foundation a : titlelist) {
					  	%>
					  	<option value="<%=a.getCODE()%>"><%=a.getDESCRIPTION()%></option>
					  	<%
							}
					  	%>
                     	<%}else{  %>
                     	
                     	<option value="<%=e.getGL14NAMETITLE()%>"><%=e.getDESCRIPTIONTITLE()%></option>
                      	<%
                      	
                      	GlobalSQLStatement title = new GlobalSQLStatement();
							List<Foundation> titlelist = title.getNameTitleNotIn(e.getGL14NAMETITLE());
							for (Foundation a : titlelist) {
					  	%>
					  	<option value="<%=a.getCODE()%>"><%=a.getDESCRIPTION()%></option>
					  	<%
							}
					  	%>
					  	<%
							}
					  	%>
						</select>
                	</div> 
                	
                		<label class="col-sm-2 " id="lblPatron(1)">Corporate ID/Parent ID</label>
                		<%
                      			String copId = "";
                      			if(e.getGL14RELID().equals("null") || (e.getGL14RELID())== null){
                      				copId = "";
                      			}else{
                      				copId = e.getGL14RELID();
                      			}
                      		%>
                      		<div class="col-sm-3 col-md-3">
                        		<input type="text" class="form-control" id="txtDetails(22)" name="GL14RELID" value="<%=copId%>">
                      		</div>
                      		
                      		<input type="hidden" id="activateStatusview" name="activateStatusview" value="<%=e.getGL14RMVD()%>">
                      		<div class="form-check" id="hideshowactivate"> <!--  id="hideshowactivate" -->
                      			<input type="checkbox" class="form-check-input" id="activate" name="activate" value="N">
  								<input type="hidden" id="activateStatus" name="activateStatus" value="h">
  								<label class="form-check-label" for="activate">Activate</label>
							</div>
                	
                	</div>
                
                	<div class="form-group">
                	<label class="col-sm-2" id="lblPatron(3)"><strong>Name<span id="red">*</span></strong></label>
                    <div class="col-sm-3 col-md-8">
                        <input type="text" class="form-control text-uppercase" id="GL14NAME" name="GL14NAME" value="<%=e.getGL14NAME()%>">
                    </div>
                      
                 
             		</div>
             		<!-- End Name Title -->	
             		
             		<div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="nameResponse" style="color:red"></div>
                      </div>
                    </div>
                    
                  	<!-- Group Id -->
                  <div class="form-group">
                  <label class="col-sm-2 " id="lblPatron(5)"><strong>Group ID<span id="red">*</span></strong></label>
                  <div class="col-sm-2 col-md-2">
                  <select class="form-control" id="GL14GRID" name="GL14GRID" onchange="document.getElementById('DESCEDIT').selectedIndex
                            						= document.getElementById('GL14GRID').selectedIndex" >
                      <%String id  = (String) session.getAttribute("screenname");
                     	System.out.println("logID" +id);	
                     	System.out.println("gggpppiiddd" +e.getGL14GRID());
                     	if((e.getGL14GRID())== null){%> 
                     <option value=""> </option>
                     <option value="<%=e.getGL14GRID()%>"><%=e.getGL14GRID()%></option>
                       <%
                       	
                       	GlobalSQLStatement group = new GlobalSQLStatement();
						//List<Foundation> grouplist = group.getGroupId();
						List<Foundation> grouplist = group.getGroupId("ilmuadmin");
							for (Foundation b : grouplist) {
					  %>
					  <option value="<%=b.getGL02GRP()%>"><%=b.getGL02GRP()%></option>
					  <%
						}
					  %> 
                     <%}else{%>
                     <option value="<%=e.getGL14GRID()%>"><%=e.getGL14GRID()%></option>
                       <%
                       		GlobalSQLStatement group = new GlobalSQLStatement();
                            List<Foundation> grouplist = group.getGroupIdNotIN(id, e.getGL14GRID());
							//List<Foundation> grouplist = group.getGroupIdNotIn(e.getGL14GRID());
							for (Foundation b : grouplist) {
					  %>
					  <option value="<%=b.getGL02GRP()%>"><%=b.getGL02GRP()%></option>
					  <%
						}
					  %> 
					  <%}%> 
				</select>
                </div>
               
                  <div class="col-sm-6 col-md-6">
                  <select class="form-control" id="DESCEDIT" name="DESCEDIT" onchange="document.getElementById('GL14GRID').selectedIndex
                            						= document.getElementById('DESCEDIT').selectedIndex" >
                      <option value="<%=e.getGL14GRID()%>"><%=e.getGL02NAME()%></option>
                     <%
                     GlobalSQLStatement grpName = new GlobalSQLStatement();
							List<Foundation> name = grpName.getGroupIdNotIN(id, e.getGL14GRID());
							for (Foundation c : name) {
					  %>
					  <option value="<%=c.getGL02GRP()%>"><%=c.getGL02NAME()%></option>
					  <%
						}
					  %> 
				</select>
                </div>              
             </div>
             <!-- End Group Id -->
             
             <!-- Category -->
                  <div class="form-group">
                  <label class="col-sm-2 " id="lblPatron(6)"><strong>Category<span id="red">*</span></strong></label>
                  <div class="col-sm-5 col-md-2">
                  <select class="form-control" id="plkupDetails(1)" name="GL14CATE" onchange="document.getElementById('cateDESCEdit').selectedIndex
                            						= document.getElementById('plkupDetails(1)').selectedIndex" >
                      <%	if((e.getGL14CATE())== null){%> 
                     <option value=""> </option>
                      <%
                       GlobalSQLStatement cate = new GlobalSQLStatement();
							List<Foundation> catelist = cate.getCate();
							for (Foundation d : catelist) {
					  %>
					  <option value="<%=d.getGL07CATE()%>"><%=d.getGL07CATE()%></option>
					  <%
						}
					  %>
                     <%}else{%>
                     <option value="<%=e.getGL14CATE()%>"><%=e.getGL14CATE()%></option>
                       <%
                       GlobalSQLStatement cate = new GlobalSQLStatement();
							List<Foundation> catelist = cate.getCateNotIn(e.getGL14CATE());
							for (Foundation d : catelist) {
					  %>
					  <option value="<%=d.getGL07CATE()%>"><%=d.getGL07CATE()%></option>
					  <%
						}
					  %> 
					  <%
						}
					  %>
				</select>
                </div>
               
                <div class="col-sm-5 col-md-6">
                <select class="form-control" id="cateDESCEdit" name="cateDESCEdit" onchange="document.getElementById('plkupDetails(1)').selectedIndex
                            						= document.getElementById('cateDESCEdit').selectedIndex" >
                      <option value=""><%=e.getGL07DESC()%></option>
                     <%
                     GlobalSQLStatement cateName = new GlobalSQLStatement();
							List<Foundation> cateNamelist = cateName.getCateNotIn(e.getGL14CATE());
							for (Foundation f : cateNamelist) {
					  %>
					  <option value="<%=f.getGL07CATE()%>"><%=f.getGL07DESC()%></option>
					  <%
						}
					  %> 
				</select>
                </div>              
             </div>
             <!-- End Category -->
             
             <!-- Patron Status -->
                  <div class="form-group">
                  <label class="col-sm-2 " id="lblPatron(7)"><strong>Patron Status<span id="red">*</span></strong></label>
                  <div class="col-sm-5 col-md-2">
                  <select class="form-control" id="plkupDetails(2)" name="GL14STAT" onchange="document.getElementById('statDESCEdit').selectedIndex
                            						= document.getElementById('plkupDetails(2)').selectedIndex" >
                      <%	if((e.getGL14STAT())== null){%> 
                     <option value=""> </option>
                     <%
                       GlobalSQLStatement status = new GlobalSQLStatement();
							List<Foundation> statuslist = status.getStat();
							for (Foundation g : statuslist) {
					  %>
					  <option value="<%=g.getGL08STAT()%>"><%=g.getGL08STAT()%></option>
					  <%
						}
					  %>
                     <%}else{%>
                     <option value="<%=e.getGL14STAT()%>"><%=e.getGL14STAT()%></option>
                       <%
                       GlobalSQLStatement status = new GlobalSQLStatement();
							List<Foundation> statuslist = status.getStatNotIn(e.getGL14STAT());
							for (Foundation g : statuslist) {
					  %>
					  <option value="<%=g.getGL08STAT()%>"><%=g.getGL08STAT()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-6">
                  <select class="form-control" id="statDESCEdit" name="statDESCEdit" onchange="document.getElementById('plkupDetails(2)').selectedIndex
                            						= document.getElementById('statDESCEdit').selectedIndex" >
                      
                      <option value=""><%=e.getGL08DESC()%></option>
                       <%
                       GlobalSQLStatement stat = new GlobalSQLStatement();
							List<Foundation> statusDesc = stat.getStatNotIn(e.getGL14STAT());
							for (Foundation h : statusDesc) {
					  %>
					  <option value="<%=h.getGL08STAT()%>"><%=h.getGL08DESC()%></option>
					  <%
						}
					  %> 
				</select>
                </div>              
             </div>
             <!-- End Patron Status -->
             
            <!-- Registered Branch -->
                  	<div class="form-group">
                  	<label class="col-sm-2 " id="lblPatron(8)"><strong>Registered Branch<span id="red">*</span></strong></label>
                  	<div class="col-sm-5 col-md-2">
                  	<select class="form-control" id="plkupDetails(3)" name="GL14BRNC" onchange="document.getElementById('regDESCEdit').selectedIndex
                            						= document.getElementById('plkupDetails(3)').selectedIndex" required>
                      <%	if((e.getGL14BRNC())== null){%> 
                     <option value=""> </option>
                      <%
                      	GlobalSQLStatement branch = new GlobalSQLStatement();
						List<Foundation> branchcodelist = branch.getBranch();
						for (Foundation i : branchcodelist) {
					  %>
					  <option value="<%=i.getGL71BRNC()%>"><%=i.getGL71BRNC()%></option>
					  <%
						}
					  %>
                     <%}else{%>
                     <option value="<%=e.getGL14BRNC()%>"><%=e.getGL14BRNC()%></option>
                       <%
                      		GlobalSQLStatement branch = new GlobalSQLStatement();
							List<Foundation> branchcodelist = branch.getBranchNotIn(e.getGL14BRNC());
							for (Foundation i : branchcodelist) {
					   %>
					  <option value="<%=i.getGL71BRNC()%>"><%=i.getGL71BRNC()%></option>
					  <%
						}
					  %> 
					  <%
						}
					  %>
					  
					</select>
                	</div>
               
                  	<div class="col-sm-5 col-md-6">
                  	<select class="form-control" id="regDESCEdit" name="regDESCEdit" onchange="document.getElementById('plkupDetails(3)').selectedIndex
                            						= document.getElementById('regDESCEdit').selectedIndex">
                      <option value=""><%=e.getGL71DESC()%></option>
                      <%
                      GlobalSQLStatement branchDesc = new GlobalSQLStatement();
							List<Foundation> branchlist = branchDesc.getBranchNotIn(e.getGL14BRNC());
							for (Foundation i : branchlist) {
					  %>
					  <option value="<%=i.getGL71DESC()%>"><%=i.getGL71DESC()%></option>
					  <%
						}
					  %> 
					  
					</select>
                	</div>              
             		</div>
             		<!-- End Register Branch -->
             
             		
                    
             		<div class="form-group">
                      <label class="col-sm-2 " id="lblPatron(11)"><b>Date Enrolled<span id="red">*</span></b></label>
                      <div class='col-sm-4'>
                      	<div class="input-group date">
                      	<%	if((e.getGL14MEMDATE())== null){%> 
                     		<input type="text" class="form-control" id="txtDetails19" name="GL14MEMDATE" value="<%=e.getGL14MEMDATE()%>" >
                     	<%}else{%>
  						 	<input type="text" class="form-control" id="txtDetails19" name="GL14MEMDATE" value="<%=e.getGL14MEMDATE()%>" >
  						<%}%>
  						<span class="input-group-addon">
  							<i class="glyphicon glyphicon-calendar"></i></span>
						</div>
        			  </div>
        			  
                      <!-- <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="txtDetails19" name="GL14MEMDATE" >
                      </div> -->
                      
                      <label class="col-sm-2 control-label" id="lblPatron(12)"><b>Expiry Date<span id="red">*</span></b></label>
                      
                      <div class='col-sm-4'>
                      	<div class="input-group date" id="GL14EXPDATEDIV">
                      	<%	if((e.getGL14EXPDATE())== null){%> 
                     		<input type="text" class="form-control" id="txtDetails20" name="GL14EXPDATE" value="<%=e.getGL14EXPDATE()%>" >
                     	<%}else{%>
  						 	<input type="text" class="form-control" id="txtDetails20" name="GL14EXPDATE" value="<%=e.getGL14EXPDATE()%>" >
  						 	<%}%>
  						 	<span class="input-group-addon">
  							<i class="glyphicon glyphicon-calendar"></i></span>
						</div>
        			  </div>
        
                      
              </div>
							
			</div><!-- /.col -->
            </div><!-- /.row -->
		    </div><!-- /.box body -->
			</div><!-- /.End Tab 1 -->
			
			
			<!-- TAB TWO -->	 
		   <div class="tab-pane" id="2">
		   <div class="box-body">
           <div class="row">
                  	
           <div class="col-sm-1 col-md-6">
                  	
                  	
                 <div class="form-group">
                  <label class="col-sm-3" id="lblPatron(13)">Race</label>
                  <div class="col-sm-3 col-md-3">
                  <select class="form-control" id="plkupDetails(4)" name="GL14RACE" onchange="document.getElementById('raceDescEdit').selectedIndex
                            						= document.getElementById('plkupDetails(4)').selectedIndex" >
                      <%	if((e.getGL14RACE())== null || (e.getGL14RACE()).isEmpty()) {%> 
                     <option value=""> </option>
                     <%
                      GlobalSQLStatement racecode = new GlobalSQLStatement();
							List<Foundation> racelist = racecode.getRace();
							for (Foundation k : racelist) {
					  %>
					  <option value="<%=k.getCODE()%>"><%=k.getCODE()%></option>
					  <%
						}
					  %>
                     <%}else{%>
                     <option value="<%=e.getGL14RACE()%>"><%=e.getGL14RACE()%></option>
                      <%
                      GlobalSQLStatement racecode = new GlobalSQLStatement();
							List<Foundation> racelist = racecode.getRaceNotIn(e.getGL14RACE());
							for (Foundation k : racelist) {
					  %>
					  <option value="<%=k.getCODE()%>"><%=k.getCODE()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-4 col-md-4">
                  <select class="form-control" id="raceDescEdit" name="raceDescEdit" onchange="document.getElementById('plkupDetails(4)').selectedIndex
                            						= document.getElementById('raceDescEdit').selectedIndex" >
                      <%	if((e.getGL14RACE())== null || (e.getGL14RACE()).isEmpty()){%> 
                     <option value=""> </option>
                     <%
                      GlobalSQLStatement racedesc = new GlobalSQLStatement();
							List<Foundation> racedesclist = racedesc.getRaceNotIn(e.getGL14RACE());
							for (Foundation l : racedesclist) {
					  %>
					  <option value="<%=l.getCODE()%>"><%=l.getDESCRIPTION()%></option>
					  <%
						}
					  %>
                     <%}else{%>
                     <option value="<%=e.getGL14RACE()%>"><%=e.getDESCRIPTIONRACE()%></option>
                      <%
                      GlobalSQLStatement racedesc = new GlobalSQLStatement();
							List<Foundation> racedesclist = racedesc.getRaceNotIn(e.getGL14RACE());
							for (Foundation l : racedesclist) {
					  %>
					  <option value="<%=l.getCODE()%>"><%=l.getDESCRIPTION()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div>    
                </div><!-- End Race -->
                
                <!-- Religion -->
                 <div class="form-group">
                
                <label class="col-sm-3" id="lblPatron(16)">Religion</label>
                  <div class="col-sm-3 col-md-3">
                  <select class="form-control" id="plkupDetails(7)" name="GL14RELIGION" onchange="document.getElementById('religionEdit').selectedIndex
                            						= document.getElementById('plkupDetails(7)').selectedIndex" >
                      <% if((e.getGL14RELIGION())== null || (e.getGL14RELIGION()).isEmpty()){%> 
                     <option value=""> </option>
                      <%
                      GlobalSQLStatement reli = new GlobalSQLStatement();
							List<Foundation> relicodelist = reli.getReli();
							for (Foundation m : relicodelist) {
					  %>
					  <option value="<%=m.getCODE()%>"><%=m.getCODE()%></option>
					  <%
						}
					  %>
                     <%}else{%>
                      <option value="<%=e.getGL14RELIGION()%>"><%=e.getGL14RELIGION()%></option>
                      <%
                      GlobalSQLStatement reli = new GlobalSQLStatement();
						List<Foundation> relicodelist = reli.getReliNotIn(e.getGL14RELIGION());
						for (Foundation m : relicodelist) {
					  %>
					  <option value="<%=m.getCODE()%>"><%=m.getCODE()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-4 col-md-4">
                  <select class="form-control" id="religionEdit" name="religionEdit" onchange="document.getElementById('plkupDetails(7)').selectedIndex
                            						= document.getElementById('religionEdit').selectedIndex" >
                      <%	if((e.getGL14RELIGION())== null || (e.getGL14RELIGION()).isEmpty()){%> 
                     <option value=" "> </option>
                     <%
                      GlobalSQLStatement religion = new GlobalSQLStatement();
							List<Foundation> religionlist = religion.getReli();
							for (Foundation n : religionlist) {
					  %>
					  <option value="<%=n.getCODE()%>"><%=n.getDESCRIPTION()%></option>
					  <%
						}
					  %>
                     <%}else{%>
                     <option value="<%=e.getGL14RELIGION()%>"><%=e.getDESCRIPTIONRELIGION()%></option>
                      <%
                      GlobalSQLStatement religion = new GlobalSQLStatement();
							List<Foundation> religionlist = religion.getReliNotIn(e.getGL14RELIGION());
							for (Foundation n : religionlist) {
					  %>
					  <option value="<%=n.getCODE()%>"><%=n.getDESCRIPTION()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div>           
                 
             </div>
             <!-- End Religion -->

             	<div class="form-group">
             	
             	<label class="col-sm-3">New IC</label>
                         <div class="col-sm-2 col-md-6">
                           <input type="text" class="form-control" id="GL14NEWIC" name="GL14NEWIC" value="<%=e.getGL14NEWIC()%>" onblur="IcLength()">
                      </div>
                      </div>
             	
                <div class="form-group">
                      <label class="col-sm-3 "></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="icResponse" style="color:red"></div>
                      </div>
                    </div>
                      
                <div class="form-group">
                       <label class="col-sm-3 " id="lblPatron(24)">Old IC</label>
                      <div class="col-sm-7 col-md-7">
                      <%	if((e.getGL14OLDIC())== null){%>
                      <input type="text" class="form-control" id="txtDetails(8)" name="GL14OLDIC" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(8)" name="GL14OLDIC" value="<%=e.getGL14OLDIC()%>">
                      <%}%>
                      </div>
                </div>
                
                <div class="form-group">
                <label class="col-sm-3 " id="lblPatron(27)">IC Color</label>
                      <div class="col-sm-5 col-md-5">
                      <%	if((e.getGL14COLOR())== null){%>
                      <input type="text" class="form-control" id="txtDetails(23)" name="GL14COLOR" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(23)" name="GL14COLOR" value="<%=e.getGL14COLOR()%>">
                      	<%}%>
                      </div>
                 </div>
                   
                      
             
             </div><!-- col -->
             
             <div class="col-sm-1 col-md-6">

             		   <div class="row">
             		   <div class="form-group">
                         <label class="col-sm-3 " id="emailAdd">E-Mail Address<span id="red">*</span></label>
                         <div class="col-sm-2 col-md-7">
                           <input type="email" class="form-control" id="GL14IPADD" name="GL14IPADD" value="<%=e.getGL14IPADD()%>">
                         </div>
                      </div>
                      </div>
             		   
             	   
                   <div class="row">
                   <div class="form-group">
                      <label class="col-sm-3 " id="lblPatron(25)">Gender</label>
                      <input type="hidden" name="GL14SEX" value="<%=e.getGL14SEX()%>">
                      	<label class="radio-inline">
	    					<input type="radio" id="optGender(0)" name="GL14SEX" value="M">
	    					Male
  						</label>
  						<label class="radio-inline">
	    					<input type="radio" id="optGender(1)" name="GL14SEX" value="F">
	    					Female
  						</label>
			  	 	</div>
			  	 	</div>
			  	 	<div class="row">
			  	 	
                    
                    <div class="form-group">
                      <label class="col-sm-3 ">Date of Birth</label>
                      <div class='col-sm-5'>
                      <%if((e.getGL14DOB())== null || (e.getGL14DOB())== "-"){%>
                      <div class="input-group date">
  						 	<input type="text" class="form-control" id="GL14DOB" name="GL14DOB" value="" >
  						 	<span class="input-group-addon">
  							<i class="glyphicon glyphicon-calendar"></i></span>
						</div>
						<%}else{ %>
                      	<div class="input-group date">
  						 	<input type="text" class="form-control" id="GL14DOB" name="GL14DOB" value="<%=e.getGL14DOB()%>" onchange="CompareDOB()">
  						 	<span class="input-group-addon">
  							<i class="glyphicon glyphicon-calendar"></i></span>
						</div>
						<%} %>
        			  </div>
        			  </div>
                    </div>
                    
                    <div class="row">
                    <div class="form-group">     
                      <label class="col-sm-3 " id="lblPatron(28)">Age</label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="txtDetails(45)" name="GL14AGE">
                      </div>
                   </div> 
                   </div>
                   
                   <div class="row"> 
                    <div class="form-group" id="PassportNo">
                      <label class="col-sm-3 " id="lblPatron(1)">Passport No</label>
                     <div class="col-sm-3 col-md-6">
                        <input type="text" class="form-control" id="txtDetails(22)" name="">
                      </div>
                 	</div>
                 	</div>
                 	
                 	<div class="row">
                 	<div class="form-group">
                      <label class="col-sm-3" id="lblPatron(23)">Deposit</label>
                       <div class="col-sm-2 col-md-4">
                      <%	if((e.getGL14DEPOSIT())== null){%>
                      <input type="text" class="form-control" id="txtDetails(17)" name="GL14DEPOSIT" value="" >
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(17)" name="GL14DEPOSIT" value="<%=e.getGL14DEPOSIT()%>" >
                      	<%}%>
                      </div>
                    </div>
                    </div>
                    
                    <div class="row">
                    <div class="form-group">
                      <label class="col-sm-3" id="lblPatron(20)">Annually Membership Fee</label>
                      <div class="col-sm-3 col-md-4">
                      <%	if((e.getGL14MEMFEE())== null){%>
                      <input type="text" class="form-control" id="txtDetails(16)" name="GL14MEMFEE" value="" >
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(16)" name="GL14MEMFEE" value="<%=e.getGL14MEMFEE()%>" >
                      <%}%>
                      </div>
                    </div>
                    </div>
                      
                    
                    
                    <div class="row">
                    <div class="form-group">
                      <label class="col-sm-3" id="lblPatron(26)">Receipt No.</label>
                      <div class="col-sm-2 col-md-4">
                      <%	if((e.getGL14RECEIPT())== null){%>
                      <input type="text" class="form-control" id="txtDetails(18)" name="GL14RECEIPT" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(18)" name="GL14RECEIPT" value="<%=e.getGL14RECEIPT()%>">
                      <%}%>
                      </div>
                    </div>
                    </div>
                    
             
             </div><!-- col -->
             </div><!-- row -->
             </div><!-- box body -->
					
			</div><!-- /.End Tab 2 -->
				
				
		<!-- TAB THREE -->	
			 <div class="tab-pane" id="3">
			 
			 <div class="box-body">
       
       				<div class="form-group">
                      <label class="col-sm-2 " id="lblPatron(29)">Employee No.</label>
                      <div class="col-sm-1 col-md-2">
                      <%	if((e.getGL14EMPLOYEE())== null){%>
                      <input type="text" class="form-control" id="txtDetails(26)" name="GL14EMPLOYEE" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(26)" name="GL14EMPLOYEE" value="<%=e.getGL14EMPLOYEE()%>">
                     <%}%>
                      </div>
                      
                      
                      <label class="col-sm-2 " id="lblPatron(30)">Date Employed</label>
                      <div class="col-sm-1 col-md-2">
                      <%	if((e.getGL14DATEJOIN())== null || (e.getGL14DATEJOIN())== "-"){%>
                      <div class="input-group date">
  						 	<input type="text" class="form-control" id="GL14DATEJOIN" name="GL14DATEJOIN" value="">
  						 	<span class="input-group-addon">
  							<i class="glyphicon glyphicon-calendar"></i></span>
						</div>
						<%}else{ %>
                      	<div class="input-group date">
  						 	<input type="text" class="form-control" id="GL14DATEJOIN" name="GL14DATEJOIN" value="<%=e.getGL14DATEJOIN()%>" >
  						 	<span class="input-group-addon">
  							<i class="glyphicon glyphicon-calendar"></i></span>
						</div>
						<%} %>
        			  </div>

                      <label class="col-sm-2 " id="lblPatron(31)">Company No.</label>
                      <div class="col-sm-1 col-md-2">
                      <%	if((e.getGL14REGISTER())== null){%>
                      <input type="text" class="form-control" id="txtDetails(29)" name="GL14REGISTER" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(29)" name="GL14REGISTER" value="<%=e.getGL14REGISTER()%>">
                      <%}%>
                      </div>
                    </div>
                  
             <!-- Designation -->
                  <div class="form-group">
                  <label class="col-sm-2 " id="lblPatron(32)">Designation</label>
                  <div class="col-sm-5 col-md-2">
                   <select class="form-control" id="plkupDetails(8)" name="GL14DESC" onchange="document.getElementById('designationEdit').selectedIndex
                            						= document.getElementById('plkupDetails(8)').selectedIndex" >
                      <% if((e.getGL14DESC())== null || (e.getGL14DESC()).isEmpty()){%>
                      <option value=""> </option>
                      <%
                      GlobalSQLStatement desgname = new GlobalSQLStatement();
							List<Foundation> desgnamelist = desgname.getDesg();
							for (Foundation p : desgnamelist) {
					  %>
					  <option value="<%=p.getCODE()%>"><%=p.getCODE()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL14DESC()%>"><%=e.getGL14DESC()%></option>
                      <%
                      GlobalSQLStatement desgname = new GlobalSQLStatement();
							List<Foundation> desgnamelist = desgname.getDesgNotIn(e.getGL14DESC());
							for (Foundation p : desgnamelist) {
					  %>
					  <option value="<%=p.getCODE()%>"><%=p.getCODE()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="designationEdit" name="designationEdit" onchange="document.getElementById('plkupDetails(8)').selectedIndex
                            						= document.getElementById('designationEdit').selectedIndex" >
                      <% if((e.getGL14DESC())== null || (e.getGL14DESC()).isEmpty()){%>
                      <option value=""> </option>
                      <%
                      GlobalSQLStatement desgcode = new GlobalSQLStatement();
							List<Foundation> desgcodelist = desgcode.getDesg();
							for (Foundation o : desgcodelist) {
					  %>
					  <option value="<%=o.getCODE()%>"><%=o.getDESCRIPTION()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL14DESC()%>"><%=e.getDESCRIPTIONDESC()%></option>
                      <%
                      GlobalSQLStatement desgcode = new GlobalSQLStatement();
							List<Foundation> desgcodelist = desgcode.getDesgNotIn(e.getGL14DESC());
							for (Foundation o : desgcodelist) {
					  %>
					  <option value="<%=o.getCODE()%>"><%=o.getDESCRIPTION()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                 
                </div> 
                
                <label class="col-sm-2 " id="lblPatron(33)">Supervisor</label>
                      <div class="col-sm-2 col-md-2">
                      <%	if((e.getGL14SUPERVISOR())== null){%>
                      <input type="text" class="form-control" id="txtDetails(25)" name="GL14SUPERVISOR" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(25)" name="GL14SUPERVISOR" value="<%=e.getGL14SUPERVISOR()%>">
                      <%
						}
					  %>
					  </div>      
             </div>
             <!-- End Designation -->
             
             <!-- Department -->
                  <div class="form-group">
                  <label class="col-sm-2 " id="lblPatron(34)">Department</label>
                  <div class="col-sm-5 col-md-2">
                  <select class="form-control" id="plkupDetails(9)" name="GL14DEPT" onchange="document.getElementById('deptEdit').selectedIndex
                            						= document.getElementById('plkupDetails(9)').selectedIndex" >
                      <%	if((e.getGL14DEPT())== null || (e.getGL14DEPT()).isEmpty()){%>
                      <option value=""> </option>
                      <%
                      
                      GlobalSQLStatement deptcode = new GlobalSQLStatement();
							List<Foundation> deptcodelist = deptcode.getDept();
							for (Foundation q : deptcodelist) {
					  %>
					  <option value="<%=q.getGL11DEPT()%>"><%=q.getGL11DEPT()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL14DEPT()%>"><%=e.getGL14DEPT()%></option>
                      <%
                      
                      GlobalSQLStatement deptcode = new GlobalSQLStatement();
							List<Foundation> deptcodelist = deptcode.getDeptNotIn(e.getGL14DEPT());
							for (Foundation q : deptcodelist) {
					  %>
					  <option value="<%=q.getGL11DEPT()%>"><%=q.getGL11DEPT()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="deptEdit" name="deptEdit" onchange="document.getElementById('plkupDetails(9)').selectedIndex
                            						= document.getElementById('deptEdit').selectedIndex" >
                      <%	if((e.getGL14DEPT())== null || (e.getGL14DEPT()).isEmpty()){%>
                      <option value=""> </option>
                      
                      <%
                      GlobalSQLStatement deptname = new GlobalSQLStatement();
						List<Foundation> deptnamelist = deptname.getDept();
						for (Foundation r : deptnamelist) {
					  %>
					  <option value="<%=r.getGL11NAME()%>"><%=r.getGL11NAME()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL14DEPT()%>"><%=e.getGL11NAME()%></option>
                      <%
                      GlobalSQLStatement deptname = new GlobalSQLStatement();
						List<Foundation> deptnamelist = deptname.getDeptNotIn(e.getGL14DEPT());
						for (Foundation r : deptnamelist) {
					  %>
					  <option value="<%=r.getGL11NAME()%>"><%=r.getGL11NAME()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div> 
                
             </div>
             <!-- End Department -->
             
             <!-- Start Course -->
             <div class="form-group">
               <label class="col-sm-2" id="lblPatron(15)">Course</label>
                  <div class="col-sm-1 col-md-2">
                  <select class="form-control" id="plkupDetails(6)" name="GL14COURSE" onchange="document.getElementById('courseDescEdit').selectedIndex
                            						= document.getElementById('plkupDetails(6)').selectedIndex" >
                      <%	if((e.getGL14COURSE())== null || (e.getGL14COURSE()).isEmpty()){%>
                      <option value=""> </option>
                      <%
                      GlobalSQLStatement coursecode = new GlobalSQLStatement();
							List<Foundation> coursecodelist = coursecode.getCourse();
							for (Foundation s : coursecodelist) {
					  %>
					  <option value="<%=s.getGL12COURSE()%>"><%=s.getGL12COURSE()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL14COURSE()%>"><%=e.getGL14COURSE()%></option>
                      <%
                      GlobalSQLStatement coursecode = new GlobalSQLStatement();
							List<Foundation> coursecodelist = coursecode.getCourseNotIn(e.getGL14COURSE());
							for (Foundation s : coursecodelist) {
					  %>
					  <option value="<%=s.getGL12COURSE()%>"><%=s.getGL12COURSE()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-2 col-md-4">
                  <select class="form-control" id="courseDescEdit" name="courseDescEdit" onchange="document.getElementById('plkupDetails(6)').selectedIndex
                            						= document.getElementById('courseDescEdit').selectedIndex" >
                      <%if((e.getGL14COURSE())== null || (e.getGL14COURSE()).isEmpty()){%>
                      <option value=""> </option>
                      <%
                      GlobalSQLStatement coursedesc = new GlobalSQLStatement();
							List<Foundation> coursedesclist = coursedesc.getCourse();
							for (Foundation t : coursedesclist) {
					  %>
					  <option value="<%=t.getGL12COURSE()%>"><%=t.getGL12DESC()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL14COURSE()%>"><%=e.getGL12DESC()%></option>
                      <%
                      GlobalSQLStatement coursedesc = new GlobalSQLStatement();
							List<Foundation> coursedesclist = coursedesc.getCourseNotIn(e.getGL14COURSE());
							for (Foundation t : coursedesclist) {
					  %>
					  <option value="<%=t.getGL12COURSE()%>"><%=t.getGL12DESC()%></option>
					  <%
						}}
					  %>
					 
				</select>
                </div>   
                
                <label class="col-sm-2 " id="lblPatron(35)">Level</label>
                      <div class="col-sm-2 col-md-2">
                      <%	if((e.getGL14STAFFLEVEL())== null){%>
                      <input type="text" class="form-control" id="txtDetails(27)" name="GL14STAFFLEVEL" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(27)" name="GL14STAFFLEVEL" value="<%=e.getGL14STAFFLEVEL()%>">
                      <%
						}
					  %>
					  </div>        
                </div>
                <!-- End Course -->
             
           <div class="form-group">
               <label class="col-sm-2 " id="lblPatron(36)">Remarks</label>
               <div class="col-sm-2 col-md-10">
               <%	if((e.getGL14REMARK())== null || (e.getGL14REMARK()).isEmpty()){%>
                        <input type="text" class="form-control" id="txtDetails(44)" name="GL14REMARK" value="">
                        <%}else{%>
                  <textarea class="form-control" id="txtDetails(44)" name="GL14REMARK"><%=e.getGL14REMARK()%></textarea>
                <%
						}
					  %>
					  </div>
            </div> 
       
       		</div><!-- /.BOX BODY -->
				
		</div>
		<!-- /.End Tab 3 -->
		
		<!-- TAB FOUR -->
		<div class="tab-pane" id="4">
		<div class="box-body">
       				<input type="hidden" id="GL14MAILFLAG" name="GL14MAILFLAG" class="form-control" value="<%=e.getGL14MAILFLAG()%>" onblur="getGL14MAILFLAG()">
       				<div class="form-group">
                      <label class="col-sm-2 control-label" id="lblPatron(18)">Mailing Address</label>
                      <div class="col-sm-1 col-md-10">
                        <input type="checkbox" class="minimal" name="chkMailFlag(0)" id="GL14MAILFLAG" value="R">
                      </div>
                    </div>
       
       				<div class="form-group">
                      <label class="col-sm-2 control-label" id="lblPatron(37)">Address</label>
                      <div class="col-sm-1 col-md-9">
                      <%	if((e.getGL14ADD1())== null){%>
                        <input type="text" class="form-control" id="txtDetails(4)" name="GL14ADD1" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(4)" name="GL14ADD1" value="<%=e.getGL14ADD1()%>">
                      <%}%>
                      </div>
                    </div> 
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                      <%	if((e.getGL14ADD2())== null){%>
                        <input type="text" class="form-control" id="txtDetails(5)" name="GL14ADD2" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(5)" name="GL14ADD2"value="<%=e.getGL14ADD2()%>">
                       <%}%>
                       </div>
                     </div>
                     <div class="form-group"> 
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                      <%	if((e.getGL14ADD3())== null){%>
                        <input type="text" class="form-control" id="txtDetails(6)" name="GL14ADD3" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(6)" name="GL14ADD3" value="<%=e.getGL14ADD3()%>">
                      <%}%>
                      </div>
                    </div>
                    
                    <div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(38)">Postcode</label>
                      <div class="col-sm-1 col-md-2">
                      <%	if((e.getGL14CODE())== null){%>
                        <input type="text" class="form-control" id="txtDetails(10)" name="GL14CODE" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(10)" name="GL14CODE" value="<%=e.getGL14CODE()%>">
                      <%}%>
                      </div>
                    
                  
             <!-- State/Town -->
                  <div class="form-group">
                  <label class="col-sm-1 control-label" id="lblPatron(39)">State/Town</label>
                  <div class="col-sm-2 col-md-2">
                  <select class="form-control" id="plkupDetails(10)" name="GL14TOWN" onchange="document.getElementById('townDescEdit').selectedIndex
                            						= document.getElementById('plkupDetails(10)').selectedIndex" >
                      <%	if((e.getGL14TOWN())== null || (e.getGL14TOWN()).isEmpty()){%>
                      <option value=""> </option>
                      <%
                      GlobalSQLStatement code = new GlobalSQLStatement();
							List<Foundation> codelist = code.getTown();
							for (Foundation u : codelist) {
					  %>
					  <option value="<%=u.getCODE()%>"><%=u.getCODE()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL14TOWN()%>"><%=e.getGL14TOWN()%></option>
                      <%
                      GlobalSQLStatement code = new GlobalSQLStatement();
							List<Foundation> codelist = code.getTownNotIn(e.getGL14TOWN());
							for (Foundation u : codelist) {
					  %>
					  <option value="<%=u.getCODE()%>"><%=u.getCODE()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="townDescEdit" name="townDescEdit" onchange="document.getElementById('plkupDetails(10)').selectedIndex
                            						= document.getElementById('townDescEdit').selectedIndex" >
                      <%	if((e.getGL14TOWN())== null || (e.getGL14TOWN()).isEmpty()){%>
                      <option value=""> </option>
                      <%
                      		GlobalSQLStatement town = new GlobalSQLStatement();
							List<Foundation> townlist = town.getTown();
							for (Foundation r : townlist) {
					  %>
					  <option value="<%=r.getCODE()%>"><%=r.getDESCRIPTION()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL14TOWN()%>"><%=e.getDESCRIPTIONTOWNR1()%></option>
                      <%
                      		GlobalSQLStatement town = new GlobalSQLStatement();
							List<Foundation> townlist = town.getTownNotIn(e.getGL14TOWN());
							for (Foundation r : townlist) {
					  %>
					  <option value="<%=r.getCODE()%>"><%=r.getDESCRIPTION()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div> 
                     
             </div>
             <!-- End State/Town -->
             </div>
             
             		<div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(40)">Home Tel. No.</label>
                      <div class="col-sm-1 col-md-2">
                      <% if((e.getGL14HTEL())== null){%>
                        <input type="text" class="form-control" id="txtDetails(11)" name="GL14HTEL" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(11)" name="GL14HTEL" value="<%=e.getGL14HTEL()%>">
                      <%
						}
					  %>
					  </div>
             		 
                      <label class="col-sm-1 control-label" id="lblPatron(41)">Tel.No.(2)</label>
                      <div class="col-md-2">
                      <% if((e.getGL14HTELX())== null){%>
                        <input type="text" class="form-control" id="txtDetails(41)" name="GL14HTELX" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(41)" name="GL14HTELX" value="<%=e.getGL14HTELX()%>">
                      <%
						}
					  %>
					  </div>
                    
                      <label class="col-sm-2 control-label" id="lblPatron(42)">Mobile No.</label>
                      <div class="col-md-2">
                      <% if((e.getGL14MTEL())== null){%>
                        <input type="text" class="form-control" id="txtDetails(42)" name="GL14MTEL" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(42)" name="GL14MTEL" value="<%=e.getGL14MTEL()%>">
                      <%
						}
					  %>
					  </div>
                    </div>
           </div><!-- /.End Box Body-->
       	   </div><!-- /.tab-pane -->
		<!-- /.End Tab 4 -->
		
		<!-- TAB FIVE -->
		<div class="tab-pane" id="5">
		<div class="box-body">
       
       				<div class="form-group">
                      <label class="col-sm-2 control-label" id="lblPatron(54)">Mailing Address</label>
                      <div class="col-sm-1 col-md-10">
                        <input type="checkbox" class="minimal" name="chkMailFlag(2)" id="GL14MAILFLAG" value="S">
                      </div>
                    </div>
       
       				<div class="form-group">
                      <label class="col-sm-2 control-label" id="lblPatron(43)">Address</label>
                      <div class="col-sm-1 col-md-9">
                      <% if((e.getGL14ADD21())== null){%>
                        <input type="text" class="form-control" id="txtDetails(34)" name="GL14ADD21" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(34)" name="GL14ADD21" value="<%=e.getGL14ADD21()%>">
                      <%
						}
					  %>
					  </div>
                    </div> 
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                      <% if((e.getGL14ADD22())== null){%>
                        <input type="text" class="form-control" id="txtDetails(35)" name="GL14ADD22" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(35)" name="GL14ADD22" value="<%=e.getGL14ADD22()%>">
                      <%
						}
					  %>
					  </div>
                     </div>
                     <div class="form-group"> 
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                      <% if((e.getGL14ADD23())== null){%>
                        <input type="text" class="form-control" id="txtDetails(36)" name="GL14ADD23" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(36)" name="GL14ADD23" value="<%=e.getGL14ADD23()%>">
                      <%
						}
					  %>
					  </div>
                    </div>
                    
                    <div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(44)">Postcode</label>
                      <div class="col-sm-1 col-md-2">
                      <% if((e.getGL14CODE2())== null){%>
                        <input type="text" class="form-control" id="txtDetails(37)" name="GL14CODE2" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(37)" name="GL14CODE2" value="<%=e.getGL14CODE2()%>">
                      <%
						}
					  %>
					  </div>
                    
                  
             <!-- State/Town -->
                  <div class="form-group">
                  <label class="col-sm-1 control-label" id="lblPatron(45)">State/Town</label>
                  <div class="col-sm-2 col-md-2">
                  <select class="form-control" id="plkupDetails(11)" name="GL14TOWN2" onchange="document.getElementById('town2Edit').selectedIndex
                            						= document.getElementById('plkupDetails(11)').selectedIndex" >
                      <% if((e.getGL14TOWN2())== null || (e.getGL14TOWN2()).isEmpty()){%>
                      <option value=""> </option>
                      <%
                      GlobalSQLStatement code2 = new GlobalSQLStatement();
							List<Foundation> codelist2 = code2.getTown();
							for (Foundation r : codelist2) {
					  %>
					  <option value="<%=r.getCODE()%>"><%=r.getCODE()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL14TOWN2()%>"><%=e.getGL14TOWN2()%></option>
                      <%
                      GlobalSQLStatement code2 = new GlobalSQLStatement();
							List<Foundation> codelist2 = code2.getTownNotIn(e.getGL14TOWN2());
							for (Foundation r : codelist2) {
					  %>
					  <option value="<%=r.getCODE()%>"><%=r.getCODE()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="town2Edit" name="town2Edit" onchange="document.getElementById('plkupDetails(11)').selectedIndex
                            						= document.getElementById('town2Edit').selectedIndex" >
                      <% if((e.getGL14TOWN2())== null || (e.getGL14TOWN2()).isEmpty()){%>
                      <option value=""> </option>
                      <%
                      GlobalSQLStatement town2 = new GlobalSQLStatement();
							List<Foundation> townlist2 = town2.getTown();
							for (Foundation r : townlist2) {
					  %>
					  <option value="<%=r.getCODE()%>"><%=r.getDESCRIPTION()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL14TOWN2()%>"><%=e.getDESCRIPTIONTOWNR2()%></option>
                      <%
                      GlobalSQLStatement town2 = new GlobalSQLStatement();
							List<Foundation> townlist2 = town2.getTownNotIn(e.getGL14TOWN2());
							for (Foundation r : townlist2) {
					  %>
					  <option value="<%=r.getCODE()%>"><%=r.getDESCRIPTION()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div> 
                     
             </div>
             <!-- End State/Town -->
             </div>
             
             		<div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(46)">Home Tel. No.</label>
                      <div class="col-sm-1 col-md-2">
                      <% if((e.getGL14HTEL2())== null){%>
                        <input type="text" class="form-control" id="txtDetails(38)" name="GL14HTEL2" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(38)" name="GL14HTEL2" value="<%=e.getGL14HTEL2()%>">
                      <%
						}
					  %>
					  </div>
                    </div>
             
       	</div><!-- /.box body-->
		</div><!-- /.tab-pane -->
		<!-- /.End Tab 5 -->
		
		
		<!-- TAB SIX-->
		<div class="tab-pane" id="6">
       	<div class="box-body">
       
       				<div class="form-group">
                      <label class="col-sm-2 control-label" id="lblPatron(55)">Mailing Address</label>
                      <div class="col-sm-1 col-md-10">
                        <input type="checkbox" class="minimal" name="chkMailFlag(1)" id="GL14MAILFLAG" value="O">
                      </div>
                    </div>
       
       				<div class="form-group">
                      <label class="col-sm-2 control-label" id="lblPatron(47)">Address</label>
                      <div class="col-sm-1 col-md-9">
                      <% if((e.getGL14OFFADD1())== null){%>
                        <input type="text" class="form-control" id="txtDetails(30)" name="GL14OFFADD1" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(30)" name="GL14OFFADD1" value="<%=e.getGL14OFFADD1()%>">
                      <%
						}
					  %>
					  </div>
                    </div> 
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                      <% if((e.getGL14OFFADD2())== null){%>
                        <input type="text" class="form-control" id="txtDetails(31)" name="GL14OFFADD2" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(31)" name="GL14OFFADD2" value="<%=e.getGL14OFFADD2()%>">
                      <%
						}
					  %>
					  </div>
                     </div>
                     <div class="form-group"> 
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                      <% if((e.getGL14OFFADD3())== null){%>
                        <input type="text" class="form-control" id="txtDetails(32)" name="GL14OFFADD3" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(32)" name="GL14OFFADD3" value="<%=e.getGL14OFFADD3()%>">
                      <%
						}
					  %>
					  </div>
                    </div>
                    
                    <div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(48)">Postcode</label>
                      <div class="col-sm-1 col-md-2">
                      <% if((e.getGL14OFFCODE())== null){%>
                        <input type="text" class="form-control" id="txtDetails(33)" name="GL14OFFCODE" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(33)" name="GL14OFFCODE" value="<%=e.getGL14OFFCODE()%>">
                     <%
						}
					  %>
					   </div>
                    
                  
             <!-- State/Town -->
                  <div class="form-group">
                  <label class="col-sm-1 control-label" id="lblPatron(49)">State/Town</label>
                  <div class="col-sm-2 col-md-2">
                  <select class="form-control" id="plkupDetails(12)" name="GL14OFFTOWN" onchange="document.getElementById('offTownEdit').selectedIndex
                            						= document.getElementById('plkupDetails(12)').selectedIndex" >
                     <%if((e.getGL14OFFTOWN())== null || (e.getGL14OFFTOWN()).isEmpty()){%>
                      <option value=""> </option>
                      <%
                      GlobalSQLStatement code3 = new GlobalSQLStatement();
							List<Foundation> codelist3 = code3.getTown();
							for (Foundation r : codelist3) { 
							
					  %>
					  <option value="<%=r.getCODE()%>"><%=r.getDESCRIPTION()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL14OFFTOWN()%>"><%=e.getGL14OFFTOWN()%></option>
                      <%
                      GlobalSQLStatement code3 = new GlobalSQLStatement();
							List<Foundation> codelist3 = code3.getTownNotIn(e.getGL14OFFTOWN());
							for (Foundation r : codelist3) {
					  %>
					  <option value="<%=r.getCODE()%>"><%=r.getCODE()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="offTownEdit" name="offTownEdit" onchange="document.getElementById('plkupDetails(12)').selectedIndex
                            						= document.getElementById('offTownEdit').selectedIndex" >
                      <% if((e.getGL14OFFTOWN())== null || (e.getGL14OFFTOWN()).isEmpty()){%>
                      <option value=""> </option>
                      <%
                      GlobalSQLStatement town3 = new GlobalSQLStatement();
							List<Foundation> townlist3 = town3.getTown();
							for (Foundation r : townlist3) {
					  %>
					  <option value="<%=r.getCODE()%>"><%=r.getDESCRIPTION()%></option>
					  <%
						}
					  %>
                      <%}else{ 
                    	  System.out.println(e.getDESCRIPTIONTOWNROFF() +"e.getDESCRIPTIONTOWNROFF()");
                    	  System.out.println(e.getGL14OFFTOWN() +"e.getGL14OFFTOWN()");
                    	  %>
                      	  
                      <option value="<%=e.getGL14OFFTOWN()%>"><%=e.getDESCRIPTIONTOWNROFF()%></option>
                      <%
                      GlobalSQLStatement town3 = new GlobalSQLStatement();
							List<Foundation> townlist3 = town3.getTownNotIn(e.getGL14OFFTOWN());
							for (Foundation r : townlist3) {
					  %>
					  <option value="<%=r.getCODE()%>"><%=r.getDESCRIPTION()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
				</select>
                </div> 
                     
             </div>
             <!-- End State/Town -->
             </div>
             
             		<div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(50)">Office Tel. No.</label>
                      <div class="col-sm-1 col-md-2">
                      <% if((e.getGL14OTEL())== null){%>
                        <input type="text" class="form-control" id="txtDetails(12)" name="GL14OTEL" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(12)" name="GL14OTEL" value="<%=e.getGL14OTEL()%>">
                      <%
						}
					  %>
					  </div>
             		 
                      <label class="col-sm-1 control-label" id="lblPatron(51)">Office Fax</label>
                      <div class="col-md-2">
                      <% if((e.getGL14FAX())== null){%>
                        <input type="text" class="form-control" id="txtDetails(13)" name="GL14FAX" value="">
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(13)" name="GL14FAX" value="<%=e.getGL14FAX()%>">
                      <%
						}
					  %>
					  
					   
					  </div>
                    </div>
             
       </div><!-- /.box body -->
       </div><!-- /.tab pane -->
       <!-- /.End Tab 6 -->
       
       <!-- TAB SEVEN-->
       <div class="tab-pane" id="7">
       	<div class="box-body">
       		Patron Miscellaneous Attributes
       		<input type="hidden" class="form-control" id="countrowmiscellaneousedit" name="countrowmiscellaneousedit">
       		<br>
       		
       		<table id="miscellaneousedit" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>No</th>
						<th>Description</th>
						<th>Value</th>
					</tr>
				</thead>
			</table>
       	</div>
       </div>
       <!-- TAB SEVEN-->
		
		</div><!-- /.END TAB CONTENT -->
		
		</div><!-- /.END CUSTOM TAB -->
		
		</div><!-- /.END COLUMN 13 -->
		 
	 </div><!-- /.END ROW -->
	
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		<div class="form-group">
									<div class="col-sm-2"><label><b>Date</b></label></div>
										<div class='col-sm-1' ><%=e.getdaterec()%>
										</div>
									<div class="col-sm-3"></div>
									<div class="col-sm-2"><label><b>Officer</b></label></div>
										<div class='col-sm-1'><%=e.getrecby()%>
										</div>
								</div>
	
	
		<a href="viewNote" id="viewNote" class="btn btn-primary" data-toggle="modal" data-target="#modalViewNote">Patron Note</a>
		<button type="submit" id="update" class="btn btn-primary sendButton" >Save</button>
		 <a href="template?MODULE=Foundation/10_PatronDetails&ACTION=PatronTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
         </a>
	</div>			
	</form>
	
	<!-- MODAL WHEN CLICK NOTE-->
		<div class="modal fade modalViewNote" id="modalViewNote" tabindex="-1" role="dialog" aria-labelledby="modalViewNote" aria-hidden="true" data-keyboard="false"> 
			<div class="modal-dialog" role="document" style="width:90%;overflow:auto">
				    <div class="modal-content" id="modal_vendorViewNoteContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
	
	 <script type="text/javascript">
  $('.input-group.date').datepicker({
	    format: "dd/mm/yyyy",
	    todayBtn: true,
	    autoclose: true,
	    todayHighlight: true
	});
  </script>  
	