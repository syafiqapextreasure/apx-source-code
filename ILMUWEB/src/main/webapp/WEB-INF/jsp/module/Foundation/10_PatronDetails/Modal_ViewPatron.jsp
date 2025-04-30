<%@ page import="com.ilmu.foundation.global.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%>
<%@page import="com.ilmu.foundation.PatronDetails.RetrievePatron"%>
<%@ page import="java.util.List" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/PatronDetails.js"></script>					
<!-- Include Required Prerequisites -->
<%-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> --%>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>

<script>
$('#viewModal').on('hidden', function () {
	  document.location.reload();
	})
</script>

<%
	String GL14PATR = request.getParameter("GL14PATR");
	System.out.println("apa nie lunch" +GL14PATR);
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getPatDetail(GL14PATR);
	System.out.println(e);
	System.out.println("apa nie" +e.getGL14PATR());
%>

<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">View Patron Details</h4>
	</div>
	<div class="modal-body" style="height:70%;overflow:auto">
		<div class="panel-body">
			<form role="form" class="form-horizontal" id="viewAccMaint">
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
			
					 <%	System.out.println("test" + e.getGL14IMG());
          			
					 if((e.getGL14IMG())== null || (e.getGL14IMG()).equals("0")|| (e.getGL14IMG()).equals("-1")){
          			
          			%>
          			
          			<div class="fileinput fileinput-new" data-provides="fileinput">
  						<div class="fileinput-new thumbnail" style="width: 150px; height: 150px;">
  						 	<img src="${pageContext.request.contextPath}/resources/image/avatar.jpg" style="width:150px">
    					</div>
  						<div class="fileinput-preview fileinput-exists thumbnail" style="width:auto"></div>
  						
					</div>
					
					<%}else{%>
			
					<div class="fileinput fileinput-new" data-provides="fileinput">
  						<div class="fileinput-new thumbnail" style="width: 150px; height: 150px;">
  						 	<img src="${pageContext.servletContext.contextPath }/PhotoServlet?GL14PATR=<%=e.getGL14PATR()%>" style="width:150px" />
    					</div>
  						<div class="fileinput-preview fileinput-exists thumbnail" style="width:auto"></div>
  						
					</div>
					
					<%}%>
          			
			</div>
					
			<div class="col-md-9">	
				<div class="form-group">
                  	
                      <label class="col-sm-2 " id="lblPatron(0)"><b>Patron ID</b></label>
                      <div class="col-md-4">
                      <%--  <input type="hidden" class="form-control" id="patridview" name="patridview"  value="<%=GL14PATR%>"> --%>
                        <input type="text" class="form-control" id="GL14PATR" name="GL14PATR"  value="<%=e.getGL14PATR()%>" readonly>
                     </div>
                      
                      <label class="col-sm-2 control-label" id="lblPatron(2)"><b>Password</b></label>
                      <div class="col-sm-5 col-md-2">
                        <input type="password" class="form-control" id="GL14PASW" name="GL14PASW" value="<%=e.getGL14PASW()%>" readonly>
                      </div>
                    </div>
                 
                 <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                 </div>
                 
                  <!-- Name Title -->
             	<div class="form-group">
                  	<label class="col-sm-2 " id="lblPatron(14)">Name Title</label>
	                  	<div class="col-sm-1 col-md-2">
	                  		<input type="text" class="form-control" id="plkupDetails(5)" name="GL14NAMETITLE"  value="<%=e.getGL14NAMETITLE()%>" readonly>
	                	</div> 
                	
                	<label class="col-sm-2 " id="lblPatron(1)">Corporate ID/Parent ID</label>
                      		<div class="col-sm-3 col-md-3">
                      		<%
                      			String copId = "";
                      			if(e.getGL14RELID().equals("null")){
                      				copId = "";
                      			}else{
                      				copId = e.getGL14RELID();
                      			}
                      		%>
                        		<input type="text" class="form-control" id="txtDetails(22)" name="GL14RELID" value="<%=copId%>" readonly>
                      		</div>
                      		
                      		<input type="hidden" id="activateStatusview" name="activateStatusview" value="<%=e.getGL14RMVD()%>">
                      		<div class="form-check" id="hideshowactivate">
                      			<input type="checkbox" class="form-check-input" id="activate" name="activate" value="N" disabled>
  								<input type="hidden" id="activateStatus" name="activateStatus">
  								<label class="form-check-label" for="activate">Activate</label>
							</div>
               	</div>
                      
             <div class="form-group">
                <label class="col-sm-2" id="lblPatron(3)"><strong>Name</strong></label>
                      <div class="col-sm-3 col-md-8">
                        <input type="text" class="form-control" id="GL14NAME" name="GL14NAME" value="<%=e.getGL14NAME()%>" readonly>
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
                  <label class="col-sm-2 " id="lblPatron(5)"><strong>Group ID</strong></label>
                  <div class="col-sm-2 col-md-2">
                  <input type="text" class="form-control" id="plkupDetails(0)" name="GL14GRID" value="<%=e.getGL14GRID()%>" readonly>
                </div>
               
                  <div class="col-sm-6 col-md-6">
                  	<input type="text" class="form-control" id="DESC" name="DESC" value="<%=e.getGL02NAME()%>" readonly>
                </div>              
             </div>
             <!-- End Group Id -->
             
             <!-- Category -->
                  <div class="form-group">
                  <label class="col-sm-2 " id="lblPatron(6)"><strong>Category</strong></label>
                  <div class="col-sm-5 col-md-2">
                  	<input type="text" class="form-control" id="GL14CATE" name="GL14CATE" value="<%=e.getGL14CATE()%>" readonly>
                </div>
               
                <div class="col-sm-5 col-md-6">
                	<input type="text" class="form-control" id="cateDESC" name="cateDESC" value="<%=e.getGL07DESC()%>" readonly>
                </div>              
             </div>
             <!-- End Category -->
             
             <!-- Patron Status -->
                  <div class="form-group">
                  <label class="col-sm-2 " id="lblPatron(7)"><strong>Patron Status</strong></label>
                  <div class="col-sm-5 col-md-2">
                  	<input type="text" class="form-control" id="GL14STAT" name="GL14STAT" value="<%=e.getGL14STAT()%>" readonly>
                </div>
               
                  <div class="col-sm-5 col-md-6">
                  	<input type="text" class="form-control" id="statDESC" name="statDESC" value="<%=e.getGL08DESC()%>" readonly>
                </div>              
             </div>
             <!-- End Patron Status -->
             
             <!-- Registered Branch -->
                  	<div class="form-group">
                  	<label class="col-sm-2 " id="lblPatron(8)"><strong>Registered Branch</strong></label>
                  	<div class="col-sm-5 col-md-2">
                  		<input type="text" class="form-control" id="GL14BRNC" name="GL14BRNC" value="<%=e.getGL14BRNC()%>" readonly>
                	</div>
               
                  	<div class="col-sm-5 col-md-6">
                  		<input type="text" class="form-control" id="regDESC" name="regDESC" value="<%=e.getGL71DESC()%>" readonly>
                	</div>              
             		</div>
             		<!-- End Register Branch -->
             
             		
                    
             		<div class="form-group">
                      <label class="col-sm-2 " id="lblPatron(11)"><b>Date Enrolled</b></label>
                      <div class='col-sm-4'>
                      	<div class="input-group date">
                      	<%	if((e.getGL14MEMDATE())== null){%> 
                     		<input type="text" class="form-control" id="txtDetails19" name="GL14MEMDATE" value="<%=e.getGL14MEMDATE()%>" readonly>
                     	<%}else{%>
  						 	<input type="text" class="form-control" id="txtDetails19" name="GL14MEMDATE" value="<%=e.getGL14MEMDATE()%>" readonly>
  						<%}%>
  						<span class="input-group-addon">
  							<i class="glyphicon glyphicon-calendar"></i></span>
						</div>
        			  </div>
                      
                      <label class="col-sm-2 control-label" id="lblPatron(12)"><b>Expiry Date</b></label>
                      
                      <div class='col-sm-4'>
                      	<div class="input-group date">
                      	<%	if((e.getGL14EXPDATE())== null){%> 
                     		<input type="text" class="form-control" id="txtDetails20" name="GL14EXPDATE" value="<%=e.getGL14EXPDATE()%>" readonly>
                     	<%}else{%>
  						 	<input type="text" class="form-control" id="txtDetails20" name="GL14EXPDATE" value="<%=e.getGL14EXPDATE()%>" readonly>
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
		   <div class="tab-pane" id="tab_2">
		   <div class="box-body">
           <div class="row">
                  	
           <div class="col-sm-1 col-md-6">
                  	
                  	
                 <div class="form-group">
                  <label class="col-sm-3" id="lblPatron(13)">Race</label>
                  <div class="col-sm-3 col-md-3">
                  	<input type="text" class="form-control" id="GL14RACE" name="GL14RACE" value="<%=e.getGL14RACE()%>" readonly>
                </div>
               
                  <div class="col-sm-4 col-md-4">
                  	<input type="text" class="form-control" id="raceDesc" name="raceDesc" value="<%=e.getDESCRIPTIONRACE()%>" readonly>
                </div>    
                </div><!-- End Race -->
                
                <!-- Religion -->
                 <div class="form-group">
                
                <label class="col-sm-3" id="lblPatron(16)">Religion</label>
                  <div class="col-sm-3 col-md-3">
                  	<input type="text" class="form-control" id="GL14RELIGION" name="GL14RELIGION" value="<%=e.getGL14RELIGION()%>" readonly>
                </div>
               
                  <div class="col-sm-4 col-md-4">
                  	<input type="text" class="form-control" id="religion" name="religion" value="<%=e.getDESCRIPTIONRELIGION()%>" readonly>
                </div>           
                 
             </div>
             <!-- End Religion -->
             
             	<div class="form-group">
             		
             		<label class="col-sm-3 " id="lblPatron(21)">New IC</label>
                      <div class="col-sm-7 col-md-7">
                      <%	if((e.getGL14NEWIC())== null){%>
                      <input type="text" class="form-control" id="GL14NEWIC" name="GL14NEWIC" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="GL14NEWIC" name="GL14NEWIC" value="<%=e.getGL14NEWIC()%>"  readonly>
                        <%}%>
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
                      <input type="text" class="form-control" id="txtDetails(8)" name="GL14OLDIC" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(8)" name="GL14OLDIC" value="<%=e.getGL14OLDIC()%>" readonly>
                      <%}%>
                      </div>
                </div>
                
                <div class="form-group">
                <label class="col-sm-3 " id="lblPatron(27)">IC Color</label>
                      <div class="col-sm-5 col-md-5">
                      <%	if((e.getGL14COLOR())== null){%>
                      <input type="text" class="form-control" id="txtDetails(23)" name="GL14COLOR" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(23)" name="GL14COLOR" value="<%=e.getGL14COLOR()%>" readonly>
                      	<%}%>
                      </div>
                 </div>

             </div><!-- col -->
             
             <div class="col-sm-1 col-md-6">
             	   <div class="row">
              	   <div class="form-group">
              	   	 <label class="col-sm-3 " id="emailAdd">E-Mail Address</label>
                         <div class="col-sm-2 col-md-7">
                           <input type="email" class="form-control" id="GL14IPADD" name="GL14IPADD" value="<%=e.getGL14IPADD()%>" readonly>
                         </div>
                   </div>
                   </div>
                   <div class="row">
                   <div class="form-group">
                      <label class="col-sm-3 " id="lblPatron(25)">Gender</label>
                      <input type="hidden" name="GL14SEX" value="<%=e.getGL14SEX()%>">
                      	<label class="radio-inline">
	    					<input type="radio" id="optGender(0)" name="GL14SEX" value="M" disabled>
	    					Male
  						</label>
  						<label class="radio-inline">
	    					<input type="radio" id="optGender(1)" name="GL14SEX" value="F" disabled>
	    					Female
  						</label>
			  	 	</div>
			  	 	</div>
			  	 	<div class="row">
                    
                    <div class="form-group">
                      <label class="col-sm-3 ">Date of Birth</label>
                      <div class='col-sm-4'>
                      <%if((e.getGL14DOB())== null || (e.getGL14DOB())== "-"){%>
                      <div class="input-group date">
  						 	<input type="text" class="form-control" id="GL14DOB" name="GL14DOB" value=" " readonly>
  						 	<span class="input-group-addon">
  							<i class="glyphicon glyphicon-calendar"></i></span>
						</div>
						<%}else{ %>
                      	<div class="input-group date">
  						 	<input type="text" class="form-control" id="GL14DOB" name="GL14DOB" value="<%=e.getGL14DOB()%>" readonly>
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
                        <input type="text" class="form-control" id="txtDetails(45)" name="GL14AGE" readonly>
                      </div>
                   </div> 
                   </div>
                   
                   <div class="row"> 
                    <div class="form-group" id="PassportNo">
                      <label class="col-sm-3 " id="lblPatron(1)">Passport No</label>
                     <div class="col-sm-3 col-md-6">
                        <input type="text" class="form-control" id="txtDetails(22)" name="" maxlength="12"  readonly>
                      </div>
                 	</div>
                 	</div>
                 	
                 	<div class="row">
                 	<div class="form-group">
                      <label class="col-sm-3" id="lblPatron(23)">Deposit</label>
                       <div class="col-sm-2 col-md-4">
                      <%	if((e.getGL14DEPOSIT())== null){%>
                      <input type="text" class="form-control" id="txtDetails(17)" name="GL14DEPOSIT" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(17)" name="GL14DEPOSIT" value="<%=e.getGL14DEPOSIT()%>" readonly>
                      	<%}%>
                      </div>
                    </div>
                    </div>
                    
                    <div class="row">
                    <div class="form-group">
                      <label class="col-sm-3" id="lblPatron(20)">Annually Membership Fee</label>
                      <div class="col-sm-3 col-md-4">
                      <%	if((e.getGL14MEMFEE())== null){%>
                      <input type="text" class="form-control" id="txtDetails(16)" name="GL14MEMFEE" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(16)" name="GL14MEMFEE" value="<%=e.getGL14MEMFEE()%>" readonly>
                      <%}%>
                      </div>
                    </div>
                    </div>
                    
                    <div class="row">
                    <div class="form-group">
                      <label class="col-sm-3" id="lblPatron(26)">Receipt No.</label>
                      <div class="col-sm-2 col-md-4">
                      <%	if((e.getGL14RECEIPT())== null){%>
                      <input type="text" class="form-control" id="txtDetails(18)" name="GL14RECEIPT" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(18)" name="GL14RECEIPT" value="<%=e.getGL14RECEIPT()%>" readonly>
                      <%}%>
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
                      <div class="col-sm-1 col-md-2">
                      <%	if((e.getGL14EMPLOYEE())== null){%>
                      <input type="text" class="form-control" id="txtDetails(26)" name="GL14EMPLOYEE" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(26)" name="GL14EMPLOYEE" value="<%=e.getGL14EMPLOYEE()%>" readonly>
                     <%}%>
                      </div>
                      
                      <label class="col-sm-2 " id="lblPatron(30)">Date Employed</label>
                      <div class="col-sm-1 col-md-2">
                      <%	if((e.getGL14DATEJOIN())== null || (e.getGL14DATEJOIN())== "-"){%>
                      <input type="text" class="form-control" id="txtDetails(28)" name="GL14DATEJOIN" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(28)" name="GL14DATEJOIN" value="<%=e.getGL14DATEJOIN()%>" readonly>
                      <%}%>
                      </div>
                      
                      <label class="col-sm-2 " id="lblPatron(31)">Company No.</label>
                      <div class="col-sm-1 col-md-2">
                      <%	if((e.getGL14REGISTER())== null){%>
                      <input type="text" class="form-control" id="txtDetails(29)" name="GL14REGISTER" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(29)" name="GL14REGISTER" value="<%=e.getGL14REGISTER()%>" readonly>
                      <%}%>
                      </div>
                    </div>
                  
             <!-- Designation -->
                  <div class="form-group">
                  <label class="col-sm-2 " id="lblPatron(32)">Designation</label>
                  <div class="col-sm-5 col-md-2">
                  	<input type="text" class="form-control" id="GL14DESC" name="GL14DESC" value="<%=e.getGL14DESC()%>" readonly>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="designation" name="designation" onchange="document.getElementById('plkupDetails(8)').selectedIndex
                            						= document.getElementById('designation').selectedIndex" disabled>
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
                      <input type="text" class="form-control" id="txtDetails(25)" name="GL14SUPERVISOR" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(25)" name="GL14SUPERVISOR" value="<%=e.getGL14SUPERVISOR()%>" readonly>
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
                  	<input type="text" class="form-control" id="GL14DEPT" name="GL14DEPT" value="<%=e.getGL14DEPT()%>" readonly>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  	<input type="text" class="form-control" id="dept" name="dept" value="<%=e.getGL11NAME()%>" readonly>
                </div> 
                   
             </div>
             <!-- End Department -->
             
             <!-- Start Course -->
             <div class="form-group">
               <label class="col-sm-2" id="lblPatron(15)">Course</label>
                  <div class="col-sm-1 col-md-2">
                  	<input type="text" class="form-control" id="GL14COURSE" name="GL14COURSE" value="<%=e.getGL14COURSE()%>" readonly>
                </div>
               
                  <div class="col-sm-2 col-md-4">
                  	<input type="text" class="form-control" id="courseDesc" name="courseDesc" value="<%=e.getGL12DESC()%>" readonly>
                 
                </div> 
                
                <label class="col-sm-2 " id="lblPatron(35)">Level</label>
                      <div class="col-sm-2 col-md-2">
                      <%	if((e.getGL14STAFFLEVEL())== null){%>
                      <input type="text" class="form-control" id="txtDetails(27)" name="GL14STAFFLEVEL" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(27)" name="GL14STAFFLEVEL" value="<%=e.getGL14STAFFLEVEL()%>" readonly >
                      <%
						}
					  %>
					  </div>         
                </div>
                <!-- End Course -->
             
           <div class="form-group">
               <label class="col-sm-2 " id="lblPatron(36)">Remarks</label>
               <div class="col-sm-2 col-md-10">
               <%	if((e.getGL14REMARK())== null){%>
                        <input type="text" class="form-control" id="txtDetails(44)" name="GL14REMARK" value=" " readonly>
                        <%}else{%>
                  <textarea class="form-control" id="txtDetails(44)" name="GL14REMARK" readonly><%=e.getGL14REMARK()%></textarea>
                <%
						}
					  %>
					  </div>
            </div> 
       
       		</div><!-- /.BOX BODY -->
				
		</div>
		<!-- /.End Tab 3 -->
		
		<!-- TAB FOUR -->
		<div class="tab-pane" id="tab_4">
		<div class="box-body">
       				<input type="hidden" id="GL14MAILFLAG" name="GL14MAILFLAG" class="form-control" value="<%=e.getGL14MAILFLAG()%>" onblur="getGL14MAILFLAG()">
       				<div class="form-group">
                      <label class="col-sm-2 control-label" id="lblPatron(18)">Mailing Address</label>
                      <div class="col-sm-1 col-md-10">
                        <input type="checkbox" class="minimal" name="chkMailFlag(0)" id="GL14MAILFLAG" value="R" disabled>
                      </div>
                    </div>
       
       				<div class="form-group">
                      <label class="col-sm-2 control-label" id="lblPatron(37)">Address</label>
                      <div class="col-sm-1 col-md-9">
                      <%	if((e.getGL14ADD1())== null){%>
                        <input type="text" class="form-control" id="txtDetails(4)" name="GL14ADD1" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(4)" name="GL14ADD1" value="<%=e.getGL14ADD1()%>" readonly>
                      <%}%>
                      </div>
                    </div> 
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                      <%	if((e.getGL14ADD2())== null){%>
                        <input type="text" class="form-control" id="txtDetails(5)" name="GL14ADD2" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(5)" name="GL14ADD2"value="<%=e.getGL14ADD2()%>"readonly>
                       <%}%>
                       </div>
                     </div>
                     <div class="form-group"> 
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                      <%	if((e.getGL14ADD3())== null){%>
                        <input type="text" class="form-control" id="txtDetails(6)" name="GL14ADD3" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(6)" name="GL14ADD3" value="<%=e.getGL14ADD3()%>" readonly>
                      <%}%>
                      </div>
                    </div>
                    
                    <div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(38)">Postcode</label>
                      <div class="col-sm-1 col-md-2">
                      <%	if((e.getGL14CODE())== null){%>
                        <input type="text" class="form-control" id="txtDetails(10)" name="GL14CODE" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(10)" name="GL14CODE" value="<%=e.getGL14CODE()%>" readonly>
                      <%}%>
                      </div>
                    
                  
             <!-- State/Town -->
                  <div class="form-group">
                  <label class="col-sm-1 control-label" id="lblPatron(39)">State/Town</label>
                  <div class="col-sm-2 col-md-2">
                  	<input type="text" class="form-control" id="GL14TOWN" name="GL14TOWN" value="<%=e.getGL14TOWN()%>" readonly>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  	<input type="text" class="form-control" id="townDesc" name="townDesc" value="<%=e.getDESCRIPTIONTOWNR1()%>" readonly>
                </div> 
                     
             </div>
             <!-- End State/Town -->
             </div>
             
             		<div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(40)">Home Tel. No.</label>
                      <div class="col-sm-1 col-md-2">
                      <% if((e.getGL14HTEL())== null){%>
                        <input type="text" class="form-control" id="txtDetails(11)" name="GL14HTEL" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(11)" name="GL14HTEL" value="<%=e.getGL14HTEL()%>" readonly>
                      <%
						}
					  %>
					  </div>
             		 
                      <label class="col-sm-1 control-label" id="lblPatron(41)">Tel.No.(2)</label>
                      <div class="col-md-2">
                      <% if((e.getGL14HTELX())== null){%>
                        <input type="text" class="form-control" id="txtDetails(41)" name="GL14HTELX" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(41)" name="GL14HTELX" value="<%=e.getGL14HTELX()%>" readonly>
                      <%
						}
					  %>
					  </div>
                    
                      <label class="col-sm-2 control-label" id="lblPatron(42)">Mobile No.</label>
                      <div class="col-md-2">
                      <% if((e.getGL14MTEL())== null){%>
                        <input type="text" class="form-control" id="txtDetails(42)" name="GL14MTEL" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(42)" name="GL14MTEL" value="<%=e.getGL14MTEL()%>" readonly>
                      <%
						}
					  %>
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
                        <input type="checkbox" class="minimal" name="chkMailFlag(2)" id="GL14MAILFLAG" value="S" disabled>
                      </div>
                    </div>
       
       				<div class="form-group">
                      <label class="col-sm-2 control-label" id="lblPatron(43)">Address</label>
                      <div class="col-sm-1 col-md-9">
                      <% if((e.getGL14ADD21())== null){%>
                        <input type="text" class="form-control" id="txtDetails(34)" name="GL14ADD21" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(34)" name="GL14ADD21" value="<%=e.getGL14ADD21()%>" readonly>
                      <%
						}
					  %>
					  </div>
                    </div> 
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                      <% if((e.getGL14ADD22())== null){%>
                        <input type="text" class="form-control" id="txtDetails(35)" name="GL14ADD22" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(35)" name="GL14ADD22" value="<%=e.getGL14ADD22()%>"readonly>
                      <%
						}
					  %>
					  </div>
                     </div>
                     <div class="form-group"> 
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                      <% if((e.getGL14ADD23())== null){%>
                        <input type="text" class="form-control" id="txtDetails(36)" name="GL14ADD23" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(36)" name="GL14ADD23" value="<%=e.getGL14ADD23()%>" readonly>
                      <%
						}
					  %>
					  </div>
                    </div>
                    
                    <div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(44)">Postcode</label>
                      <div class="col-sm-1 col-md-2">
                      <% if((e.getGL14CODE2())== null){%>
                        <input type="text" class="form-control" id="txtDetails(37)" name="GL14CODE2" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(37)" name="GL14CODE2" value="<%=e.getGL14CODE2()%>" readonly>
                      <%
						}
					  %>
					  </div>
                    
                  
             <!-- State/Town -->
                  <div class="form-group">
                  <label class="col-sm-1 control-label" id="lblPatron(45)">State/Town</label>
                  <div class="col-sm-2 col-md-2">
                  	<input type="text" class="form-control" id="GL14TOWN2" name="GL14TOWN2" value="<%=e.getGL14TOWN2()%>" readonly>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  	<input type="text" class="form-control" id="town2" name="town2" value="<%=e.getDESCRIPTIONTOWNR2()%>" readonly>
                </div> 
                     
             </div>
             <!-- End State/Town -->
             </div>
             
             		<div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(46)">Home Tel. No.</label>
                      <div class="col-sm-1 col-md-2">
                      <% if((e.getGL14HTEL2())== null){%>
                        <input type="text" class="form-control" id="txtDetails(38)" name="GL14HTEL2" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(38)" name="GL14HTEL2" value="<%=e.getGL14HTEL2()%>" readonly>
                      <%
						}
					  %>
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
                        <input type="checkbox" class="minimal" name="chkMailFlag(1)" id="GL14MAILFLAG" value="O" disabled>
                      </div>
                    </div>
       
       				<div class="form-group">
                      <label class="col-sm-2 control-label" id="lblPatron(47)">Address</label>
                      <div class="col-sm-1 col-md-9">
                      <% if((e.getGL14OFFADD1())== null){%>
                        <input type="text" class="form-control" id="txtDetails(30)" name="GL14OFFADD1" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(30)" name="GL14OFFADD1" value="<%=e.getGL14OFFADD1()%>" readonly>
                      <%
						}
					  %>
					  </div>
                    </div> 
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                      <% if((e.getGL14OFFADD2())== null){%>
                        <input type="text" class="form-control" id="txtDetails(31)" name="GL14OFFADD2" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(31)" name="GL14OFFADD2" value="<%=e.getGL14OFFADD2()%>" readonly>
                      <%
						}
					  %>
					  </div>
                     </div>
                     <div class="form-group"> 
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-1 col-md-9">
                      <% if((e.getGL14OFFADD3())== null){%>
                        <input type="text" class="form-control" id="txtDetails(32)" name="GL14OFFADD3" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(32)" name="GL14OFFADD3" value="<%=e.getGL14OFFADD3()%>" readonly>
                      <%
						}
					  %>
					  </div>
                    </div>
                    
                    <div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(48)">Postcode</label>
                      <div class="col-sm-1 col-md-2">
                      <% if((e.getGL14OFFCODE())== null){%>
                        <input type="text" class="form-control" id="txtDetails(33)" name="GL14OFFCODE" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(33)" name="GL14OFFCODE" value="<%=e.getGL14OFFCODE()%>" readonly>
                     <%
						}
					  %>
					   </div>
                    
                  
             <!-- State/Town -->
                  <div class="form-group">
                  <label class="col-sm-1 control-label" id="lblPatron(49)">State/Town</label>
                  <div class="col-sm-2 col-md-2">
                  	<input type="text" class="form-control" id="GL14OFFTOWN" name="GL14OFFTOWN" value="<%=e.getGL14OFFTOWN()%>" readonly>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  	<input type="text" class="form-control" id="offTown" name="offTown" value="<%=e.getDESCRIPTIONTOWNROFF()%>" readonly>
                </div> 
                     
             </div>
             <!-- End State/Town -->
             </div>
             
             		<div class="form-group"> 
                      <label class="col-sm-2 control-label" id="lblPatron(50)">Office Tel. No.</label>
                      <div class="col-sm-1 col-md-2">
                      <% if((e.getGL14OTEL())== null){%>
                        <input type="text" class="form-control" id="txtDetails(12)" name="GL14OTEL" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(12)" name="GL14OTEL" value="<%=e.getGL14OTEL()%>" readonly>
                      <%
						}
					  %>
					  </div>
             		 
                      <label class="col-sm-1 control-label" id="lblPatron(51)">Office Fax</label>
                      <div class="col-md-2">
                      <% if((e.getGL14FAX())== null){%>
                        <input type="text" class="form-control" id="txtDetails(13)" name="GL14FAX" value=" " readonly>
                        <%}else{%>
                        <input type="text" class="form-control" id="txtDetails(13)" name="GL14FAX" value="<%=e.getGL14FAX()%>" readonly>
                      <%
						}
					  %>
					   
					  </div>
                    </div>
             
       </div><!-- /.box body -->
       </div><!-- /.tab pane -->
       <!-- /.End Tab 6 -->
       
       <!-- TAB SEVEN-->
       <div class="tab-pane" id="tab_7">
       	<div class="box-body">
       		Patron Miscellaneous Attributes
       		<br>
       		
       		<table id="miscellaneousView" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th></th>
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
	</form>
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
		 <a href="template?MODULE=Foundation/10_PatronDetails&ACTION=PatronTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Close</button>
         </a>
	</div>			
	
	
	<!-- MODAL WHEN CLICK NOTE-->
		<div class="modal fade modalViewNote" id="modalViewNote" tabindex="-1" role="dialog" aria-labelledby="modalViewNote" aria-hidden="true" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" role="document" style="width:90%;overflow:auto">
				    <div class="modal-content" id="modal_vendorViewNoteContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
	
	 <script type="text/javascript">
  /* $('.input-group.date').datepicker({
	    format: "dd/mm/yyyy",
	    todayBtn: true,
	    autoclose: true,
	    todayHighlight: true
	}); */
  </script>  
	