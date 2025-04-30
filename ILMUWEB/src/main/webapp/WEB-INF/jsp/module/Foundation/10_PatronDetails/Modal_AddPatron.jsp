<%@ page import="com.ilmu.foundation.global.*, java.util.List, com.ilmu.foundation.PatronDetails.PatCriteria" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/PatronDetails.js"></script>					
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>


<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

#tab_1 .checkbox {
    margin-bottom: 20px;
    position: relative;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    -o-user-select: none;
    user-select: none;
}
#tab_1 .checkbox.show:before {
    content: '\e013';
    color: #1fa67b;
    font-size: 17px;
    margin: 1px 0 0 3px;
    position: absolute;
    pointer-events: none;
    font-family: 'Glyphicons Halflings';
}
#tab_1 .checkbox .character-checkbox {
    width: 25px;
    height: 25px;
    cursor: pointer;
    border-radius: 3px;
    border: 1px solid #ccc;
    vertical-align: middle;
    display: inline-block;
}
#tab_1 .checkbox .label {
    color: #6d6d6d;
    font-size: 13px;
    font-weight: normal;
}

#red {
	color: red;
}

</style>
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

<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Add Patron Details</h4>
	</div>
	<form role="form" id="patronForm" class="form-horizontal" action="${pageContext.request.contextPath}/AddPatronDetails" enctype="multipart/form-data" method="post"
	 >
	<div class="modal-body" style="height:70%;overflow:auto">
	<div class="panel-body">
	
	<div class="row">
	<div class="col-md-13">
	<!-- Custom Tabs -->
		<div class="nav-tabs-custom">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#tab_1" data-toggle="tab" aria-expanded="false"><strong>Patron Details</strong></a></li>
				<li><a href="#tab_2" data-toggle="tab"><strong>Personal</strong></a></li>
				<li><a href="#tab_3" data-toggle="tab" aria-expanded="true"><strong>Professional</strong></a></li>
             	<li><a href="#tab_4" data-toggle="tab"><strong>Residence</strong></a></li>
             	<li><a href="#tab_5" data-toggle="tab" aria-expanded="true"><strong>Residence (2)</strong></a></li>
             	<li><a href="#tab_6" data-toggle="tab"><strong>Office</strong></a></li>
             	<li><a href="#tab_7" data-toggle="tab"><strong>Miscellanous</strong></a></li>
             	<li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
			</ul>
			
		<!-- TAB CONTENT -->
		<div class="tab-content">
		
		
		
		<!-- TAB ONE -->
			<div class="tab-pane active" id="tab_1">
			<div class="box-body">
			<div class="row">
			
			<div class="col-sm-2 col-md-3">
                  		<div class="fileinput fileinput-new" data-provides="fileinput">
  						<div class="fileinput-new thumbnail" style="width: 150px; height: 150px;">
    						<img src="${pageContext.request.contextPath}/resources/image/avatar.jpg" style="width:150px">
  						</div>
  						<div class="fileinput-preview fileinput-exists thumbnail" style="width:auto"></div>
  						<div>
    						<span class="btn btn-default btn-file"><span class="fileinput-new">
    						<span class="glyphicon glyphicon-folder-open" aria-hidden="true" ></span>   Browse image</span>
    						<span class="fileinput-exists">Change</span>
    						<input type="file" name="photo" id="GL14IMAGE"></span>
    						<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
  						</div>
					</div>
			</div>
					
			<div class="col-md-9">	
			<%
				List<PatCriteria> list = PatCriteria.retrievePatrDetails();
			
				for (PatCriteria i : list) {
		 
					if((i.getGL76LBLNAME()).equals("lblPatron(0)")){
			%>
			
				<div class="form-group">
                  	 <label class="col-sm-2 " id="<%=i.getGL76LBLNAME()%>"><strong><%=i.getGL76LBLCAPT() %></strong><span id="red">*</span></label>
					 <div class="col-sm-3">
						<input type="text" class="form-control" id="<%=i.getGL76INPUTNAME()%>" name="GL14PATR">
					</div>
					 <div class="col-sm-1">
					 	<a class="btn btn-primary" title="Generate ID"><span id="card" class="glyphicon glyphicon-credit-card"></span></a>
						<!-- <button class="btn btn-primary" title="Generate ID"><span id="card" class="glyphicon glyphicon-credit-card"></span></button> -->
                     </div>
              
                     <!-- <div id="ajaxResponse" style="color:red"></div> -->
                     
                      <%}%>
                 
                 <%if((i.getGL76LBLNAME()).equals("lblPatron(2)")){ %>
                  <label class="col-sm-2 control-label" id="<%=i.getGL76LBLNAME()%>"><strong><%=i.getGL76LBLCAPT()%></strong><span id="red">*</span></label>
                      <div class="col-sm-3 col-md-2">
                        <input type="password" class="form-control" id="GL14PASW" name="GL14PASW">
                      </div>
                      
                      <!-- <div class="col-sm-2">  
                      <input id="checkBox" type="checkbox" onclick="showPassword()">
                      <label>Show Password</label>
                     </div> -->

                 <%}} %>
                   
                 </div>
                
                 <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    
                 </div>
                 
                 <input type="hidden" id='loginid' name="loginid">
                 <input type="hidden" id='daterecpatradd' name="daterecpatradd">
                
                 <!-- Name Title -->
             		<div class="form-group">
                  		<label class="col-sm-2 " id="lblPatron(14)">Name Title</label> <!-- lblPatron(14) -->
		                  	<div class="col-sm-1 col-md-3">
		                  		<select class="form-control" id="plkupDetails(5)" name="GL14NAMETITLE" onchange="document.getElementById('name').selectedIndex
		                            						= document.getElementById('plkupDetails(5)').selectedIndex">
		                      	<option value="">Please Select</option>
		                      	<%
		                      	GlobalSQLStatement title = new GlobalSQLStatement();
									List<Foundation> titlelist = title.getNameTitle();
									for (Foundation e : titlelist) {
							  	%>
							  	<option value="<%=e.getCODE()%>"><%=e.getDESCRIPTION()%></option>
							  	<%
									}
							  	%>
								</select>
		                	</div> 
		                	
		              	<label class="col-sm-2 " id="lblPatron(1)">Corporate ID/Parent ID</label>
                      		<div class="col-sm-3 col-md-3">
                        		<input type="text" class="form-control" id="txtDetails(22)" name="GL14RELID" autocomplete="off">
                      		</div>
                      		
                      		<div class="form-check" id="hideshowactivate">
  								<input type="checkbox" class="form-check-input" id="activate" name="activate" value="N">
  								<input type="hidden" id="activateStatus" name="activateStatus" value="h">
  								<label class="form-check-label" for="activate">Activate</label>
							</div>
		              </div>
		              
		              <div class="form-group">
                
                		<label class="col-sm-2" id="lblPatron(3)"><strong>Name<span id="red">*</span></strong></label>
		                    <div class="col-sm-3 col-md-8">
		                        <input type="text" class="form-control text-uppercase" id="GL14NAME" name="GL14NAME">
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
                  	<div class="col-sm-5 col-md-3">
                  	<select class="form-control" id="plkupDetails(0)" name="GL14GRID" onchange="document.getElementById('DESC').selectedIndex
                            						= document.getElementById('plkupDetails(0)').selectedIndex">
                      <option value="">Please Select</option>
                       <%
                       
                       String id  = (String) session.getAttribute("screenname");
                       System.out.println("logIDadd" +id);
                       GlobalSQLStatement group = new GlobalSQLStatement();
						//List<Foundation> grouplist = group.getGroupId();
						List<Foundation> grouplist = group.getGroupId(id);
							for (Foundation e : grouplist) {
					  %>
					  <option value="<%=e.getGL02GRP()%>"><%=e.getGL02GRP()%></option>
					  <%
						}
					  %> 
					</select>
                	</div>
               
                  	<div class="col-sm-5 col-md-6">
                  	<select class="form-control" id="DESC" name="DESC" onchange="document.getElementById('plkupDetails(0)').selectedIndex
                            						= document.getElementById('DESC').selectedIndex">
                      <option value="">Please Select</option>
                     <%
                     GlobalSQLStatement grpName = new GlobalSQLStatement();
							//List<Foundation> name = grpName.getGroupId();
							List<Foundation> name = group.getGroupId(id);
							for (Foundation e : name) {
					  %>
					  <option value="<%=e.getGL02NAME()%>"><%=e.getGL02NAME()%></option>
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
                  	<div class="col-sm-5 col-md-3">
                  	<select class="form-control" id="plkupDetails(1)" name="GL14CATE" onchange="document.getElementById('cateDESC').selectedIndex
                            						= document.getElementById('plkupDetails(1)').selectedIndex">
                      <option value="">Please Select</option>
                       <%
                       GlobalSQLStatement cate = new GlobalSQLStatement();
							List<Foundation> catelist = cate.getCate();
							for (Foundation e : catelist) {
					  %>
					  <option value="<%=e.getGL07CATE()%>"><%=e.getGL07CATE()%></option>
					  <%
						}
					  %> 
					</select>
                	</div>
               
                	<div class="col-sm-5 col-md-6">
                	<select class="form-control" id="cateDESC" name="cateDESC" onchange="document.getElementById('plkupDetails(1)').selectedIndex
                            						= document.getElementById('cateDESC').selectedIndex">
                      <option value="">Please Select</option>
                     <%
                     GlobalSQLStatement cateName = new GlobalSQLStatement();
							List<Foundation> cateNamelist = cateName.getCate();
							for (Foundation e : cateNamelist) {
					  %>
					  <option value="<%=e.getGL07DESC()%>"><%=e.getGL07DESC()%></option>
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
                  	<div class="col-sm-5 col-md-3">
                  	<select class="form-control" id="plkupDetails(2)" name="GL14STAT" onchange="document.getElementById('statDESC').selectedIndex
                            						= document.getElementById('plkupDetails(2)').selectedIndex">
                      <option value="">Please Select</option>
                       <%
                       GlobalSQLStatement status = new GlobalSQLStatement();
							List<Foundation> statuslist = status.getStat();
							for (Foundation e : statuslist) {
					  %>
					  <option value="<%=e.getGL08STAT()%>"><%=e.getGL08STAT()%></option>
					  <%
						}
					  %>
					</select>
                	</div>
               
                  	<div class="col-sm-5 col-md-6">
                  	<select class="form-control" id="statDESC" name="statDESC" onchange="document.getElementById('plkupDetails(2)').selectedIndex
                            						= document.getElementById('statDESC').selectedIndex">
                      <option value="">Please Select</option>
                       <%
                       GlobalSQLStatement stat = new GlobalSQLStatement();
							List<Foundation> statusDesc = stat.getStat();
							for (Foundation e : statusDesc) {
					  %>
					  <option value="<%=e.getGL08DESC()%>"><%=e.getGL08DESC()%></option>
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
                  	<div class="col-sm-5 col-md-3">
                  	<select class="form-control" id="plkupDetails(3)" name="GL14BRNC" onchange="document.getElementById('regDESC').selectedIndex
                            						= document.getElementById('plkupDetails(3)').selectedIndex">
                      <option value="">Please Select</option>
                      <%
                      GlobalSQLStatement branch = new GlobalSQLStatement();
							List<Foundation> branchcodelist = branch.getBranch();
							for (Foundation e : branchcodelist) {
					  %>
					  <option value="<%=e.getGL71BRNC()%>"><%=e.getGL71BRNC()%></option>
					  <%
						}
					  %>
					</select>
                	</div>
               
                  	<div class="col-sm-5 col-md-6">
                  	<select class="form-control" id="regDESC" name="regDESC" onchange="document.getElementById('plkupDetails(3)').selectedIndex
                            						= document.getElementById('regDESC').selectedIndex">
                      <option value="">Please Select</option>
                      <%
                      GlobalSQLStatement branchDesc = new GlobalSQLStatement();
							List<Foundation> branchlist = branchDesc.getBranch();
							for (Foundation e : branchlist) {
					  %>
					  <option value="<%=e.getGL71DESC()%>"><%=e.getGL71DESC()%></option>
					  <%
						}
					  %>
					</select>
                	</div>              
             		</div>
             		<!-- End Register Branch -->
             
             		
                    
             		<div class="form-group">
             		 <%   // Display a date in day, month, year format
             			 Date date = Calendar.getInstance().getTime();
	                     DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	                     String today = formatter.format(date);
	                     System.out.println("Today : " + today); %>
                      <label class="col-sm-2 " id="lblPatron(11)"><b>Date Enrolled<span id="red">*</span></b></label>
                      <div class='col-sm-4'>
                      	<div class="input-group date" id="GL14MEMDATEDIV">
  						 	<input type="text" class="form-control" id="GL14MEMDATE" name="GL14MEMDATE" value="<%=today%>" onchange="CompareDate()">
  						 	<span class="input-group-addon">
  							<i class="glyphicon glyphicon-calendar"></i></span>
						</div>
        			  </div>
        			  
                      <label class="col-sm-2 control-label" id="lblPatron(12)"><b>Expiry Date<span id="red">*</span></b></label>
                      
                      <div class='col-sm-4'>
                      	<div class="input-group date" id="GL14EXPDATEDIV">
  						 	<input type="text" class="form-control" id="GL14EXPDATE" name="GL14EXPDATE" autocomplete="off">
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
		   <div class="tab-pane" id="tab_2">
		   <div class="box-body">
           <div class="row">
                  	
           <div class="col-sm-1 col-md-6">
                  
				
                  	
                  <!-- Race -->
                  <div class="form-group">
                  <label class="col-sm-3" id="lblPatron(13)">Race</label>
                  <div class="col-sm-3">
                  <select class="form-control" id="plkupDetails(4)" name="GL14RACE" onchange="document.getElementById('raceDesc').selectedIndex
                            						= document.getElementById('plkupDetails(4)').selectedIndex">
                      <option value="">Please Select</option>
                      <%
                      GlobalSQLStatement racecode = new GlobalSQLStatement();
							List<Foundation> racelist = racecode.getRace();
							for (Foundation e : racelist) {
					  %>
					  <option value="<%=e.getCODE()%>"><%=e.getCODE()%></option>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-2 col-md-4">
                  <select class="form-control" id="raceDesc" name="raceDesc" onchange="document.getElementById('plkupDetails(4)').selectedIndex
                            						= document.getElementById('raceDesc').selectedIndex">
                      <option value="">Please Select</option>
                      <%
                      GlobalSQLStatement racedesc = new GlobalSQLStatement();
							List<Foundation> racedesclist = racedesc.getRace();
							for (Foundation e : racedesclist) {
					  %>
					  <option value="<%=e.getDESCRIPTION()%>"><%=e.getDESCRIPTION()%></option>
					  <%
						}
					  %>
				</select>
                </div>    
                </div><!-- End Race -->
                <div class="form-group">
                
                <label class="col-sm-3" id="lblPatron(16)">Religion</label>
                  <div class="col-sm-3">
                  <select class="form-control" id="plkupDetails(7)" name="GL14RELIGION" onchange="document.getElementById('religion').selectedIndex
                            						= document.getElementById('plkupDetails(7)').selectedIndex">
                      <option value="">Please Select</option>
                      <%
                      GlobalSQLStatement reli = new GlobalSQLStatement();
							List<Foundation> relicodelist = reli.getReli();
							for (Foundation e : relicodelist) {
					  %>
					  <option value="<%=e.getCODE()%>"><%=e.getCODE()%></option>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-2 col-md-4">
                  <select class="form-control" id="religion" name="religion" onchange="document.getElementById('plkupDetails(7)').selectedIndex
                            						= document.getElementById('religion').selectedIndex">
                      <option value="">Please Select</option>
                      <%
                      GlobalSQLStatement religion = new GlobalSQLStatement();
							List<Foundation> religionlist = religion.getReli();
							for (Foundation e : religionlist) {
					  %>
					  <option value="<%=e.getDESCRIPTION()%>"><%=e.getDESCRIPTION()%></option>
					  <%
						}
					  %>
				</select>
                </div>           
                 
             </div>
             <!-- End Religion -->
             
            <%--  <%
				List<PatCriteria> list2 = PatCriteria.retrievePatrDetails();
			
				for (PatCriteria i : list2) {
		 
					
			%>
             <%if((i.getGL76LBLNAME()).equals("lblPatron(21)")&&(i.getGL76MANDAT()).equals("Y")){ %>
             	<div class="form-group">
             		
             		<label class="col-sm-3 " id="<%=i.getGL76LBLNAME()%>"><%=i.getGL76LBLCAPT()%><span id="red">*</span></label>
                      <div class="col-sm-2 col-md-6">
                        <input type="text" class="form-control" id="GL14NEWIC" name="GL14NEWIC" placeholder="Exp: 900420064356" onblur="IcLength()">
                      </div>
                </div>
                 <%}else if((i.getGL76LBLNAME()).equals("lblPatron(21)")&&(i.getGL76MANDAT()).equals("N")){ %>
             		 
                 	   <div class="form-group">
                         <label class="col-sm-3 " id="<%=i.getGL76LBLNAME()%>">New IC<span id="red">*</span></label>
                         <div class="col-sm-2 col-md-6">
                           <input type="text" class="form-control" id="GL14NEWIC" name="GL14NEWIC" placeholder="Exp: 900420064356" onblur="IcLength()">
                      </div>
                      </div>
                      
                      
            <%}} %> --%>
            	<div class="form-group">
                         <label class="col-sm-3 ">New IC</label>
                         <div class="col-sm-2 col-md-6">
                           <input type="text" class="form-control" id="GL14NEWIC" name="GL14NEWIC" placeholder="Exp: 900420064356" onblur="IcLength()">
                      </div>
                      </div>
            
                <div class="form-group">
                      <label class="col-sm-3 "></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="icResponse" style="color:red"></div>
                      </div>
                    </div>
               
			
			<div class="form-group">
                         <label class="col-sm-3 " id="oldIC">Old IC</label>
                         <div class="col-sm-2 col-md-6">
                           <input type="text" class="form-control" id="GL14OLDIC>" name="GL14OLDIC">
                      </div>
                      </div>
              <%-- <%
				List<PatCriteria> list4 = PatCriteria.retrievePatrDetails();
			
				for (PatCriteria i : list4) {
		 
					
			%> --%>
             <%-- <%if((i.getGL76LBLNAME()).equals("lblPatron(24)")&&(i.getGL76MANDAT()).equals("Y")){ %>       
                <div class="form-group">
                       <label class="col-sm-3 " id="<%=i.getGL76LBLNAME()%>"><%=i.getGL76LBLCAPT() %></label>
                      <div class="col-sm-2 col-md-6">
                        <input type="text" class="form-control" id="<%=i.getGL76INPUTNAME()%>" name="GL14OLDIC">
                      </div>
                </div>
                
                <%}else if((i.getGL76LBLNAME()).equals("lblPatron(24)")&&(i.getGL76MANDAT()).equals("N")){ %>
             		 
                 	   <div class="form-group">
                         <label class="col-sm-3 " id="<%=i.getGL76LBLNAME()%>">Old IC</label>
                         <div class="col-sm-2 col-md-6">
                           <input type="text" class="form-control" id="<%=i.getGL76INPUTNAME()%>" name="GL14OLDIC">
                      </div>
                      </div>
                      
                      
            <%}} %> --%>
                
                <div class="form-group">
                <label class="col-sm-3 " id="lblPatron(27)">IC Color</label>
                      <div class="col-sm-2 col-md-6">
                        <input type="text" class="form-control" id="txtDetails(23)" name="GL14COLOR">
                      </div>
                 </div>
                 
                 <!-- <div class="form-group">
                      <label class="col-sm-3 " id="lblPatron(1)">Corporate ID/Parent ID</label>
                      <div class="col-sm-3 col-md-6">
                        <input type="text" class="form-control" id="txtDetails(22)" name="GL14RELID">
                      </div>
                 </div>
                 
                 <div class="form-group">     
                      <label class="col-sm-3 " id="lblPatron(28)">Parent ID</label>
                      <div class="col-sm-2 col-md-6">
                        <input type="text" class="form-control" id="txtDetails(24)" name="GL14PARENTID" readonly>
                      </div>
                   </div>    -->
                      
             
             </div><!-- col -->
             <div class="col-sm-1 col-md-6">
            
              
			
			<div class="row">
                	   
                 	   <div class="form-group">
                         <label class="col-sm-3 " id="emailAdd">E-Mail Address<span id="red">*</span></label>
                         <div class="col-sm-2 col-md-7">
                           <input type="email" class="form-control" id="GL14IPADD" name="GL14IPADD" placeholder="Exp: name@example.com">
                         </div>
                      </div>
                      
                      </div>
                      
                     <%--  <%
				List<PatCriteria> list3 = PatCriteria.retrievePatrDetails();
			
				for (PatCriteria i : list3) {
		 
					
			%> --%>
             
             	   <%-- <% if((i.getGL76LBLNAME()).equals("lblPatron(19)")&&(i.getGL76MANDAT()).equals("Y")){ %>
             	   <div class="row">
             	   
              	   <div class="form-group">
                      <label class="col-sm-3 " id="<%=i.getGL76LBLNAME()%>"><%=i.getGL76LBLCAPT() %><span id="red">*</span></label>
                      <div class="col-sm-2 col-md-7">
                        <input type="email" class="form-control" id="<%=i.getGL76INPUTNAME()%>" name="GL14IPADD" placeholder="Exp: name@example.com">
                      </div>
                   </div>
                   
                   </div>
                   <%}else if(((i.getGL76LBLNAME()).equals("lblPatron(19)"))&&((i.getGL76MANDAT()).equals("N"))){ %>
             		  <div class="row">
                	   
                 	   <div class="form-group">
                         <label class="col-sm-3 " id="lblPatron(19)">E-Mail Address</label>
                         <div class="col-sm-2 col-md-7">
                           <input type="email" class="form-control" id="<%=i.getGL76INPUTNAME()%>" name="GL14IPADD" placeholder="Exp: name@example.com">
                         </div>
                      </div>
                      
                      </div>
             		   
             		   <%}} %> --%>
             	   
                   <div class="row">
                   <div class="form-group">
                      <label class="col-sm-3 " id="lblPatron(25)">Gender</label>
                      <div class="col-md-2 col-sm-1"> 
                      	<label>
    					<input type="radio" class="minimal" name="GL14SEX" id="optGender(0)" value="M" checked>
    					Male
  						</label>
  					  </div>
  					  <div class="col-md-3 col-sm-1">
  						<label>
    					<input type="radio" class="minimal" name="GL14SEX" id="optGender(1)" value="F">
    					Female
  						</label>
					  </div>
			  	 	</div>
			  	 	</div>
			  	 	<div class="row">
                    <div class="form-group">
                      <label class="col-sm-3 ">Date of Birth</label>
                    
                      <div class='col-sm-5'>
                      	<div class="input-group date" id="dateDOB">
  						 	<input type="text" class="form-control" id="GL14DOB" name="GL14DOB" onchange="CompareDOB()" disabled>
  						 	<input type="hidden" id="GL14DOB2" name="GL14DOB2" class="form-control">
  						 	<span class="input-group-addon" id="date">
  							<i class="glyphicon glyphicon-calendar "  ></i></span>
						</div>
        			  </div>
        			 
        			  </div>
                    </div>
                    
                    <div class="row">
                    <div class="form-group">     
                      <label class="col-sm-3 " id="lblPatron(28)">Age</label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL14AGE" name="GL14AGE">
                      </div>
                   </div> 
                   </div>
                   
                   <div class="row"> 
                    <div class="form-group" id="PassportNo">
                      <label class="col-sm-3 " id="lblPatron(1)">Passport No</label>
                      <div class="col-sm-3 col-md-6">
                        <input type="text" class="form-control" id="txtDetails(22)" name="passportNo">
                      </div>
                 	</div>
                 	</div>
                 	
                 	<div class="row">
                 	<div class="form-group">
                      <label class="col-sm-3" id="lblPatron(23)">Deposit</label>
                      <div class="col-sm-2 col-md-4">
                        <input type="text" class="form-control" id="txtDetails(17)" name="GL14DEPOSIT">
                      </div>
                    </div>
                    </div>
                    
                    <div class="row">
                    <div class="form-group">
                      <label class="col-sm-3" id="lblPatron(20)">Annually Membership Fee</label>
                      <div class="col-sm-3 col-md-4">
                        <input type="text" class="form-control" id="txtDetails(16)" name="GL14MEMFEE" >
                      </div>
                    </div>
                    </div>
                      
                     <!--  <label class="col-sm-2 " id="lblPatron(22)">Image Name</label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="lblPatron(22)" name="GL14IMAGE">
                      </div>
                    </div> -->
                    
                    <div class="row">
                    <div class="form-group">
                      <label class="col-sm-3" id="lblPatron(26)">Receipt No.</label>
                      <div class="col-sm-2 col-md-4">
                        <input type="text" class="form-control" id="txtDetails(18)" name="GL14RECEIPT">
                      </div>
                    </div>
                    </div>
                    
             
             </div><!-- col -->
             </div><!-- row -->
             </div><!-- box body -->
					
			</div><!-- /.End Tab 2 -->
				
				
		<!-- TAB THREE -->	
			 <div class="tab-pane" id="tab_3">
			 
			 <div class="box-body">
       
       				<div class="form-group">
                      <label class="col-sm-2 " id="lblPatron(29)">Employee No.</label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="txtDetails(26)" name="GL14EMPLOYEE">
                      </div>
                      
                      
                      <label class="col-sm-2 " id="lblPatron(30)">Date Employed</label>
                      <div class='col-sm-2'>
                      	<div class="input-group date">
  						 	<input type="text" class="form-control" id="GL14DATEJOIN" name="GL14DATEJOIN">
  						 	<span class="input-group-addon">
  							<i class="glyphicon glyphicon-calendar"></i></span>
						</div>
        			  </div>
        			  
                      
                      <label class="col-sm-2 " id="lblPatron(31)">Company No.</label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="txtDetails(29)" name="GL14REGISTER">
                      </div>
                    </div>
                  
             <!-- Designation -->
                  <div class="form-group">
                  <label class="col-sm-2 " id="lblPatron(32)">Designation</label>
                  <div class="col-sm-5 col-md-2">
                  <select class="form-control" id="plkupDetails(8)" name="GL14DESC" onchange="document.getElementById('designation').selectedIndex
                            						= document.getElementById('plkupDetails(8)').selectedIndex">
                      <option value=""></option>
                      <%
                      		GlobalSQLStatement desgcode = new GlobalSQLStatement();
							List<Foundation> desgcodelist = desgcode.getDesg();
							for (Foundation e : desgcodelist) {
					  %>
					  <option value="<%=e.getCODE()%>"><%=e.getCODE()%></option>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="designation" name="designation" onchange="document.getElementById('plkupDetails(8)').selectedIndex
                            						= document.getElementById('designation').selectedIndex">
                      <option value=""></option>
                      <%
                      GlobalSQLStatement desgname = new GlobalSQLStatement();
							List<Foundation> desgnamelist = desgname.getDesg();
							for (Foundation e : desgnamelist) {
					  %>
					  <option value="<%=e.getDESCRIPTION()%>"><%=e.getDESCRIPTION()%></option>
					  <%
						}
					  %>
				</select>
                </div> 
                
                <label class="col-sm-2 " id="lblPatron(33)">Supervisor</label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="txtDetails(25)" name="GL14SUPERVISOR">
                      </div>      
             </div>
             <!-- End Designation -->
             
             <!-- Department -->
                  <div class="form-group">
                  <label class="col-sm-2 " id="lblPatron(34)"><b>Department<span id="red">*</span></b></label>
                  <div class="col-sm-5 col-md-2">
                  <select class="form-control" id="plkupDetails(9)" name="GL14DEPT" onchange="document.getElementById('dept').selectedIndex
                            						= document.getElementById('plkupDetails(9)').selectedIndex">
                      <option value=""></option>
                      <%
                      GlobalSQLStatement deptcode = new GlobalSQLStatement();
							List<Foundation> deptcodelist = deptcode.getDept();
							for (Foundation e : deptcodelist) {
					  %>
					  <option value="<%=e.getGL11DEPT()%>"><%=e.getGL11DEPT()%></option>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="dept" name="dept" onchange="document.getElementById('plkupDetails(9)').selectedIndex
                            						= document.getElementById('dept').selectedIndex">
                      <option value=""></option>
                      <%
                      GlobalSQLStatement deptname = new GlobalSQLStatement();
							List<Foundation> deptnamelist = deptname.getDept();
							for (Foundation e : deptnamelist) {
					  %>
					  <option value="<%=e.getGL11NAME()%>"><%=e.getGL11NAME()%></option>
					  <%
						}
					  %>
				</select>
                </div> 
                
                <!-- <label class="col-sm-2 " id="lblPatron(35)">Level</label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="txtDetails(27)" name="GL14STAFFLEVEL">
                      </div>    -->   
             </div>
             <!-- End Department -->
             
             <!-- Start Course -->
             <div class="form-group">
               <label class="col-sm-2" id="lblPatron(15)">Course</label>
                  <div class="col-sm-1 col-md-2">
                  <select class="form-control" id="plkupDetails(6)" name="GL14COURSE" onchange="document.getElementById('courseDesc').selectedIndex
                            						= document.getElementById('plkupDetails(6)').selectedIndex">
                      <option value=""></option>
                      <%
                      GlobalSQLStatement coursecode = new GlobalSQLStatement();
							List<Foundation> coursecodelist = coursecode.getCourse();
							for (Foundation e : coursecodelist) {
					  %>
					  <option value="<%=e.getGL12COURSE()%>"><%=e.getGL12COURSE()%></option>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-2 col-md-4">
                  <select class="form-control" id="courseDesc" name="courseDesc" onchange="document.getElementById('plkupDetails(6)').selectedIndex
                            						= document.getElementById('courseDesc').selectedIndex">
                      <option value=""></option>
                      <%
                      GlobalSQLStatement coursedesc = new GlobalSQLStatement();
							List<Foundation> coursedesclist = coursedesc.getCourse();
							for (Foundation e : coursedesclist) {
					  %>
					  <option value="<%=e.getGL12DESC()%>"><%=e.getGL12DESC()%></option>
					  <%
						}
					  %>
				</select>
                </div>     
                
                <label class="col-sm-2 " id="lblPatron(35)">Level</label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="txtDetails(27)" name="GL14STAFFLEVEL">
                      </div>       
                </div>
                <!-- End Course -->
             
           <div class="form-group">
               <label class="col-sm-2 " id="lblPatron(36)">Remarks</label>
               <div class="col-sm-2 col-md-10">
                  <textarea class="form-control" id="txtDetails(44)" name="GL14REMARK" ></textarea>
               </div>
            </div> 
       
       		</div><!-- /.BOX BODY -->
				
		</div>
		<!-- /.End Tab 3 -->
		
		<!-- TAB FOUR -->
		<div class="tab-pane" id="tab_4">
		<div class="box-body">
       
       				<div class="form-group">
       				 <input type="hidden" id="GL14MAILFLAG" name="GL14MAILFLAG" class="form-control">
                      <label class="col-sm-2 control-label" id="lblPatron(18)">Mailing Address</label>
                      <div class="col-sm-1 col-md-10">
                        <input type="checkbox" class="minimal" name="chkMailFlag(0)" id="GL14MAILFLAG" value="R">
                      </div>
                    </div>
       
       				<div class="form-group">
                      <label class="col-sm-2 control-label" id="lblPatron(37)">Address</label>
                      <div class="col-sm-1 col-md-9">
                        <input type="text" class="form-control" id="txtDetails(4)" name="GL14ADD1">
                      </div>
                    </div> 
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                        <input type="text" class="form-control" id="txtDetails(5)" name="GL14ADD2">
                      </div>
                     </div>
                     <div class="form-group"> 
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                        <input type="text" class="form-control" id="txtDetails(6)" name="GL14ADD3">
                      </div>
                    </div>
                    
                    <div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(38)">Postcode</label>
                      <div class="col-sm-1 col-md-2">
                        <input type="text" class="form-control" id="txtDetails(10)" name="GL14CODE">
                      </div>
                    
                  
             <!-- State/Town -->
                  <div class="form-group">
                  <label class="col-sm-1 control-label" id="lblPatron(39)">State/Town</label>
                  <div class="col-sm-2 col-md-2">
                  <select class="form-control" id="plkupDetails(10)" name="GL14TOWN" onchange="document.getElementById('townDesc').selectedIndex
                            						= document.getElementById('plkupDetails(10)').selectedIndex">
                      <option value=""></option>
                      <%
                      GlobalSQLStatement code = new GlobalSQLStatement();
							List<Foundation> codelist = code.getTown();
							for (Foundation e : codelist) {
					  %>
					  <option value="<%=e.getCODE()%>"><%=e.getCODE()%></option>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="townDesc" name="townDesc" onchange="document.getElementById('plkupDetails(10)').selectedIndex
                            						= document.getElementById('townDesc').selectedIndex">
                      <option value=""></option>
                      <%
                      GlobalSQLStatement town = new GlobalSQLStatement();
							List<Foundation> townlist = town.getTown();
							for (Foundation e : townlist) {
					  %>
					  <option value="<%=e.getDESCRIPTION()%>"><%=e.getDESCRIPTION()%></option>
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
                        <input type="text" class="form-control" id="txtDetails(11)" name="GL14HTEL">
                      </div>
             		 
                      <label class="col-sm-1 control-label" id="lblPatron(41)">Tel.No.(2)</label>
                      <div class="col-md-2">
                        <input type="text" class="form-control" id="txtDetails(41)" name="GL14HTELX">
                      </div>
                    
                      <label class="col-sm-2 control-label" id="lblPatron(42)">Mobile No.</label>
                      <div class="col-md-2">
                        <input type="text" class="form-control" id="txtDetails(42)" name="GL14MTEL">
                      </div>
                    </div>
           </div><!-- /.End Box Body-->
       	   </div><!-- /.tab-pane -->
		<!-- /.End Tab 4 -->
		
		<!-- TAB FIVE -->
		<div class="tab-pane" id="tab_5">
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
                        <input type="text" class="form-control" id="txtDetails(34)" name="GL14ADD21">
                      </div>
                    </div> 
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                        <input type="text" class="form-control" id="txtDetails(35)" name="GL14ADD22">
                      </div>
                     </div>
                     <div class="form-group"> 
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                        <input type="text" class="form-control" id="txtDetails(36)" name="GL14ADD23">
                      </div>
                    </div>
                    
                    <div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(44)">Postcode</label>
                      <div class="col-sm-1 col-md-2">
                        <input type="text" class="form-control" id="txtDetails(37)" name="GL14CODE2">
                      </div>
                    
                  
             <!-- State/Town -->
                  <div class="form-group">
                  <label class="col-sm-1 control-label" id="lblPatron(45)">State/Town</label>
                  <div class="col-sm-2 col-md-2">
                  <select class="form-control" id="plkupDetails(11)" name="GL14TOWN2" onchange="document.getElementById('town2').selectedIndex
                            						= document.getElementById('plkupDetails(11)').selectedIndex">
                      <option value=""></option>
                      <%
                      GlobalSQLStatement code2 = new GlobalSQLStatement();
							List<Foundation> codelist2 = code2.getTown();
							for (Foundation e : codelist2) {
					  %>
					  <option value="<%=e.getCODE()%>"><%=e.getCODE()%></option>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="town2" name="town2" onchange="document.getElementById('plkupDetails(11)').selectedIndex
                            						= document.getElementById('town2').selectedIndex">
                      <option value=""></option>
                      <%
                      GlobalSQLStatement town2 = new GlobalSQLStatement();
							List<Foundation> townlist2 = town2.getTown();
							for (Foundation e : townlist2) {
					  %>
					  <option value="<%=e.getDESCRIPTION()%>"><%=e.getDESCRIPTION()%></option>
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
                        <input type="text" class="form-control" id="txtDetails(38)" name="GL14HTEL2">
                      </div>
                    </div>
             
       	</div><!-- /.box body-->
		</div><!-- /.tab-pane -->
		<!-- /.End Tab 5 -->
		
		
		<!-- TAB SIX-->
		<div class="tab-pane" id="tab_6">
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
                        <input type="text" class="form-control" id="txtDetails(30)" name="GL14OFFADD1">
                      </div>
                    </div> 
                    
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                        <input type="text" class="form-control" id="txtDetails(31)" name="GL14OFFADD2">
                      </div>
                     </div>
                     <div class="form-group"> 
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                        <input type="text" class="form-control" id="txtDetails(32)" name="GL14OFFADD3">
                      </div>
                    </div>
                    
                    <div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(48)">Postcode</label>
                      <div class="col-sm-1 col-md-2">
                        <input type="text" class="form-control" id="txtDetails(33)" name="GL14OFFCODE">
                      </div>
                    
                  
             <!-- State/Town -->
                  <div class="form-group">
                  <label class="col-sm-1 control-label" id="lblPatron(49)">State/Town</label>
                  <div class="col-sm-2 col-md-2">
                  <select class="form-control" id="plkupDetails(12)" name="GL14OFFTOWN" onchange="document.getElementById('offTown').selectedIndex
                            						= document.getElementById('plkupDetails(12)').selectedIndex">
                      <option value=""></option>
                      <%
                      GlobalSQLStatement code3 = new GlobalSQLStatement();
							List<Foundation> codelist3 = code3.getTown();
							for (Foundation e : codelist3) {
					  %>
					  <option value="<%=e.getCODE()%>"><%=e.getCODE()%></option>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="offTown" name="offTown" onchange="document.getElementById('plkupDetails(12)').selectedIndex
                            						= document.getElementById('offTown').selectedIndex">
                      <option value=""></option>
                      <%
                      GlobalSQLStatement town3 = new GlobalSQLStatement();
							List<Foundation> townlist3 = town3.getTown();
							for (Foundation e : townlist3) {
					  %>
					  <option value="<%=e.getDESCRIPTION()%>"><%=e.getDESCRIPTION()%></option>
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
                        <input type="text" class="form-control" id="txtDetails(12)" name="GL14OTEL">
                      </div>
             		 
                      <label class="col-sm-1 control-label" id="lblPatron(51)">Office Fax</label>
                      <div class="col-md-2">
                        <input type="text" class="form-control" id="txtDetails(13)" name="GL14FAX">
                      </div>
                    </div>
             
       </div><!-- /.box body -->
       </div><!-- /.tab pane -->
       <!-- /.End Tab 6 -->
       
       <!-- TAB SEVEN-->
       <div class="tab-pane" id="tab_7">
       	<div class="box-body">
       		Patron Miscellaneous Attributes
       		<input type="hidden" class="form-control" id="countrowmiscellaneousadd" name="countrowmiscellaneousadd">
       		<br>
       		
       		<table id="miscellaneousadd" class="table table-bordered table-striped">
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
										<div class='col-sm-1 daterecadd'>
										</div>
									<div class="col-sm-3"></div>
									<div class="col-sm-2"><label><b>Officer</b></label></div>
										<div class='col-sm-1 recbyadd'>
										</div>
								</div>
	
		<input type="submit" class="btn btn-primary sendButton" value="Save" id="btn-add" style="width:80px;">
		 <a href="template?MODULE=Foundation/10_PatronDetails&ACTION=PatronTable.jsp">
            <button type="button" id="cancel" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
            </a>
	</div>			
	</form>
	
	  <script type="text/javascript">
  $('.input-group.date').datepicker({
	    format: "dd/mm/yyyy",
	    todayBtn: true,
	    autoclose: true,
	    todayHighlight: true
	}).on('changeDate', function(e) {
        // Revalidate the date field
        $('#patronForm').bootstrapValidator('updateStatus', 'GL14EXPDATE', 'NOT_VALIDATED').bootstrapValidator('validateField', 'GL14EXPDATE');
    });
  

 
  </script>  