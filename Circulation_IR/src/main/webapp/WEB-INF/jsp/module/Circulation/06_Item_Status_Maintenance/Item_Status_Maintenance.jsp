<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>

  <%@page import="com.ilmu.circulation.Charging.*,java.util.*,com.ilmu.global.*,
				java.util.*,com.ilmu.circulation.Item_Status_Maintenance.*,
				com.ilmu.circulation.Item_Reassignment.*"%>
<html lang="en">
<head>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Iteam_Status_Maintenance.js"></script>	
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<!-- <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> -->
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
<!--  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.16/css/jquery.dataTables.min.css" /> -->

				
  <title>Item Status Maintenance</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="text/javascript">
   
 /*  function access() {

	  var b = document.getElementById("GL14PATR").value;
	  var a= "${pageContext.servletContext.contextPath }/PhotoServlet?GL14PATR="+b;
	  $.get(a,function(data){
		 if(data!=null && data!=""){
			 document.getElementById("imgtest3").src=a;
		 }
		}); 
  } */
  function access() {
		
		 var b = document.getElementById("GL14PATR").value;

		 if(b!=null && b!=""){
		  var a= "${pageContext.servletContext.contextPath }/PhotoServlet?GL14PATR="+b;

		  $.get(a,function(data){
			 if(data!=null && data!=""){
				 document.getElementById("imgtest3").src=a;
			 }
			});
		 }else{
			 document.getElementById("imgtest3").src="${pageContext.servletContext.contextPath }/resources/image/user_default.png";
		 }	  
			  
	  }
  <%--function formvalidation()
  {
	  var b = document.getElementById("CT03DOCNO").value;
	  if(b.length<10)
		  alert("Accession Number length is less than 10");
	
}

  
  
  function myfunction2() {

	  var b = document.getElementById("CT03DOCNO").value;
	 if(!b)
		 {
		 messageBox("033","","");
		 	
		 }
   
  }
  
  function messageBox(code, criteria, label){
	    var url = "Error_Page?GL79ERRCODE="+code+"" +
	    "&criteria=" + criteria + "&label="+label+"";
	    //alert(url);
	    $.ajax({
	          url: url,
	          success: function(result) {
	         
	                swal('Info!',result, 'warning' );
	          }
	    });
	}
  
  $(document).ready(function(){
    $("#newsubmit").click(function(e){

    	//var myLineBreak = myTextareaVal.replace(/\r\n|\r|\n/g,"\n")
      $.ajax({
          type: "POST",
          url: "AccessionServlet",
          data: { name: $("#CT03DOCNO").val()},

          
          success:function(result){
        	 
        	 // alert(result);
        	  modal.style.display = "block";
        	  $("#myModal2").append('<p> Accession Details</p>');
              $("#myModal2").text(result);
      
     	   
              
            
          }
      });
    });
  });

   --%>
 
  var contextPath='<%=request.getContextPath()%>';
  console.log(contextPath);


	function clearVars() {
	    document.getElementById('GL14STAT').value='';
	    document.getElementById('GL14NAME').value='';
	    document.getElementById('GL14MEMDATE').value='';
	   }

  </script>
  
  <style>
  	 .wrapper {
	  padding: 20px;
	}
	
	.image--cover {
	  width: 120px;
	  height: 120px;
	  border-radius: 50%;
	  object-fit: cover;
	  object-position: center right;
	}
  </style> 
</head>

		
<body>

<%
//session.setAttribute("pat2",request.getParameter("patr"));
%>
<div class="panel-group">
  <div class="panel panel-default">
    <div class="panel-heading">
      	<div class="form-check form-check-inline">
			<label class="form-check-label">
			    <input class="form-check-input" type="radio" name="actiontype" id="inlineRadiocharge" value="itemStatus" checked>Item Status &nbsp;
			 </label>  
			 <!-- <label class="form-check-label">
			    <input class="form-check-input" type="radio" name="actiontype" id="inlineRadiodischarge" value="enquiry"> Accession Status Enquiry &nbsp;
			  </label> -->
			  <label class="form-check-label">
			    <input class="form-check-input" type="radio" name="actiontype" id="inlineRadiorenewal" value="recall"> Item Recall
			  </label>
		</div>
<!-- 		<a class="btn btn-primary" id='enableSelect' title="Borrower's Info"><span class="glyphicon glyphicon-info-sign" style="color:white"></span></a>
		<a class="btn btn-primary" id='retrieveLink' data-toggle='modal' data-target='#titleSearch' href='RetrieveTitleModal?action=8' title="Fine"><span class="glyphicon glyphicon-usd" style="color:white"></span></a> -->
        <a class="accordion-toggle" data-toggle="collapse" href="#statusMaint">
          <i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
        </a>
    </div>
    <div id="statusCombineDetail" class="panel-collapse collapse in">
      	<div class="panel-body" id="statusCombine">
      		<div class="row">
       			<div class="col-md-12 col-lg-12">
       				<div class="form-group row">
						<label class="col-sm-2 col-form-label" for="AccessionNO">Accession No :</label>
							<div class="col-sm-2">
						    	<div class="input-group">
						    	<%
						    	String accession = request.getParameter("AccessionNo");
						    	System.out.println("Accession" + accession);
						    		if(accession!=null){
						    	%>
						    	<input type="text" maxlength="10" class="form-control" id="AccessionNO" value="<%=accession%>">
						    	<%
						    		}else{
						    	%>
							    	<input type="text" maxlength="10" class="form-control" id="AccessionNO">
							    	
							    <%
						    		}
							    %>
							    	<!-- <button type="button" class="btn btn-primary btn-sm search" id="searchAN2" data-toggle="modal" 
												data-target="#searchAN2"><span class="glyphicon glyphicon-search" style="color:white"></span></button> -->
								    <a href="RetrieveTitleModal?action=15" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#titleSearch"><span class="glyphicon glyphicon-search"></span></a>
						    		<!-- <div><button type="button" id="searchcontrolNoBtn" class="btn btn-primary"  data-toggle="modal" data-target="#titleSearch" href="RetrieveTitleModal?action=8"><span class="glyphicon glyphicon-search"></span></button> -->
						    	</div>
						    	
						    </div>
						    
						    <div align="left">
						    	<!-- <button  id='modifybtn' class="btn btn-primary"><span class="fa fa-exchange fa-2x" style="color:white" title="Modify Item Status"></span></button> -->
						    	<button type="button" class="btn btn-primary" id="modifybtn" data-toggle="modal" 
												data-target="#modifyDetail"><span class="fa fa-pencil" style="color:white" title="Modify Item Status"></span></button>
								<!-- <button type="button" class="btn btn-primary" id="bib" data-toggle="modal" 
												data-target="#bibDetail"><span class="fa fa-info-circle" style="color:white" title="Bib Details"></span></button> -->
						    </div>
					</div>
					
					<div class="nav-tabs-custom">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#item" data-toggle="tab" aria-expanded="false"><strong>Item Details</strong></a></li>
							<li><a href="#circulation" data-toggle="tab"><strong>Circulation History</strong></a></li>
							<li><a href="#related" data-toggle="tab" aria-expanded="true"><strong>Related Copies</strong></a></li>
			             	<li><a href="#eligibility" data-toggle="tab"><strong>Eligibility</strong></a></li>
						</ul>
			
						<!-- TAB CONTENT -->
						<div class="tab-content">
		
						<!-- TAB 1 -->
						<div class="tab-pane active" id="item">
							<div class="box-body">
								<form>
								  	<div class="form-group row">
								    	<label for="Status" class="col-sm-2 col-form-label">Status</label>
								    <div class="col-sm-4">
								      	<span class="Status"></span>
								    </div>
								    <label for="Currency" class="col-sm-2 col-form-label">Currency Code</label>
								    	<div class="col-sm-4">
								      		<span class="Currency"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row" id="forConly">
								    	<label for="borrowedByC" class="col-sm-2 col-form-label">Borrowed By</label>
										    <div class="col-sm-4">
										      	<span class="borrowedByC"></span>
										    </div>
								    	<label for="dueDate" class="col-sm-2 col-form-label">Due Date</label>
									    	<div class="col-sm-4">
									      		<span class="dueDate"></span>
									    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="ItemCategory" class="col-sm-2 col-form-label">Item Category</label>
								    <div class="col-sm-4">
								      	<span class="ItemCategory"></span>
								    </div>
								    <label for="ForeignCost" class="col-sm-2 col-form-label">Foreign Cost</label>
								    	<div class="col-sm-4">
								      		<span class="ForeignCost"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="SMD" class="col-sm-2 col-form-label">SMD</label>
								    <div class="col-sm-4">
								      	<span class="SMD"></span>
								    </div>
								    <label for="LocalCost" class="col-sm-2 col-form-label">Local Cost</label>
								    	<div class="col-sm-4">
								      		<span class="LocalCost"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="Condition" class="col-sm-2 col-form-label">Condition</label>
								    <div class="col-sm-4">
								      	<span class="Condition"></span>
								    </div>
								    <label for="CirculatedHits" class="col-sm-2 col-form-label">Circulated Hits</label>
								    	<div class="col-sm-4">
								      		<span class="CirculatedHits"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="CopyNo" class="col-sm-2 col-form-label">Copy No</label>
								    <div class="col-sm-4">
								      	<span class="CopyNo"></span>
								    </div>
								    <label for="Location" class="col-sm-2 col-form-label">Location</label>
								    	<div class="col-sm-4">
								      		<span class="Location"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="Volume" class="col-sm-2 col-form-label">Volume</label>
								    <div class="col-sm-4">
								      	<span class="Volume"></span>
								    </div>
								    <label for="Branch" class="col-sm-2 col-form-label">Branch</label>
								    	<div class="col-sm-4">
								      		<span class="Branch"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="ControlNo" class="col-sm-2 col-form-label">Control No</label>
								    <div class="col-sm-4">
								      	<span class="ControlNo"></span>
								    </div>
								    <label for="CreatedDate" class="col-sm-2 col-form-label">Created Date</label>
								    	<div class="col-sm-4">
								      		<span class="CreatedDate"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="ISBN" class="col-sm-2 col-form-label">ISBN/ISSN</label>
								    <div class="col-sm-4">
								      	<span class="ISBN"></span>
								    </div>
								    <label for="ReleasedDate" class="col-sm-2 col-form-label">Released Date</label>
								    	<div class="col-sm-4">
								      		<span class="ReleasedDate"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="Publisher" class="col-sm-2 col-form-label">Publisher</label>
								    <div class="col-sm-4">
								      	<span class="Publisher"></span>
								    </div>
								    <label for="CallNumber" class="col-sm-2 col-form-label">Call Number</label>
								    	<div class="col-sm-4">
								      		<span class="CallNumber"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="Title" class="col-sm-2 col-form-label">Title</label>
								    <div class="col-sm-10">
								      	<span class="Title"></span>
								    </div>
								  	</div>
								</form>
						
								<!-- <div class="row">
            					</div>End row item -->
					    	</div><!--/End box body item -->
						</div><!-- /.End Tab item -->
			
			
						<!-- TAB 2 -->	 
					   	<div class="tab-pane" id="circulation">
					   		<div class="box-body">					   		
           						<div class="row">
           						
           						<%-- <input type="text" id="CT03DOCNOCir" value="<%=CT03DOCNO%>"> --%>
           							<table class="table table-hover table-responsive" id="table-circulation">	
           								<thead>
           									<tr>
           										<th data-sortable="true">No</th>
           										<th data-sortable="true">Borrower</th>
           										<th data-sortable="true">Charge Date</th>
           										<th data-sortable="true">Charge Time</th>  
           										<th data-sortable="true">Charge Staff</th> 
           										<th data-sortable="true">Due Date</th>  
           										<th data-sortable="true">Due Time</th> 
           										<th data-sortable="true">Return Date</th>
           										<th data-sortable="true">Return Time</th>
           										<th data-sortable="true">Return Staff</th>
           										<th data-sortable="true">Renew Date</th>
           										<th data-sortable="true">Renew</th>
           										<th data-sortable="true">Renew Time</th>
           										<th data-sortable="true">Renew Staff</th>
           										
           										<th data-sortable="true">Status</th>
           										<th data-sortable="true">View Detail</th>
           									</tr>
           									<!-- <tr>
           										<th data-sortable="true">No</th>
           										<th data-sortable="true">Borrower</th>
           										<th data-sortable="true">Charge Date</th>
           										<th data-sortable="true">Charge Time</th>  
           										<th data-sortable="true">Officer</th>  
           										<th data-sortable="true">Due Date</th>  
           										<th data-sortable="true">Due Time</th> 
           										<th data-sortable="true">Return Date</th>
           										<th data-sortable="true">Return Time</th>
           										<th data-sortable="true">Officer</th>
           										<th data-sortable="true">Renew Date</th>
           										<th data-sortable="true">Renew</th>
           										<th data-sortable="true">Renew Officer</th>
           										<th data-sortable="true">Renew Time</th>
           										<th data-sortable="true">Status</th>
           										<th data-sortable="true">View Detail</th>
           									</tr> -->
           								</thead>
           								<tbody>
           								</tbody>
           							</table>
								</div><!-- END row circulation-->
             				</div><!-- END box body circulation-->
						</div><!-- /.End Tab circulation -->
						
						<!-- TAB 3 -->	 
					   	<div class="tab-pane" id="related">
					   		<div class="box-body">
           						<div class="row">
           							<table class="table table-hover table-responsive" id="table-related">	
           								<thead>
           									<tr> 
           										<th data-sortable="true">No</th>
           										<th data-sortable="true">Accession No</th>
           										<th data-sortable="true">Copy No</th>
           										<th data-sortable="true">Status</th>
           										<th data-sortable="true">Branch</th>
           										<th data-sortable="true">Location</th>
           										<th data-sortable="true">Item Category</th>
           										<th data-sortable="true">SMD</th>
           										<th data-sortable="true">Volume</th>
           									</tr>
           								</thead>
           								<tbody>
           								</tbody>
           							</table>
								</div><!-- END row related-->
             				</div><!-- END box body related-->
						</div><!-- /.End Tab related -->
						
						<!-- TAB 4 -->	 
					   	<div class="tab-pane" id="eligibility">
					   		<div class="box-body">
           						<div class="row">
           							<table class="table table-hover table-responsive" id="table-eligibility">	
           								<thead>
           									<tr> 
           										<th data-sortable="true">No</th>
           										<th data-sortable="true">Patron Category</th>
           										<th data-sortable="true">Item Catgory</th>
           										<th data-sortable="true">SMD</th>
           										<th data-sortable="true">Branch</th>
           										<th data-sortable="true">Loan Period</th>
           										<th data-sortable="true">Eligibility</th>
           										<th data-sortable="true">Period Type</th>
           										<th data-sortable="true">Renew</th>
           									</tr>
           								</thead>
           								<tbody>
           								</tbody>
           							</table>
								</div><!-- END row eligibility-->
             				</div><!-- END box body eligibility-->
						</div><!-- /.End Tab eligibility -->

						</div><!-- /.END TAB CONTENT -->
		
					</div><!-- /.END CUSTOM TAB -->
       			</div>   		
     		</div>
      	</div>
    </div>
  </div>
  </div>
  
  <div class="panel-group" id="itemrecall">
	<div class="panel panel-default">
    	<div class="panel-heading">
      		<div class="form-check form-check-inline">
			</div>
        	<a class="accordion-toggle" data-toggle="collapse" href="#recall">
          		<i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
        	</a>
    	</div>
    	
		<div id="patrondet" class="panel-collapse collapse in">
    		<div class="panel-body" id="recall">
        		<div style="float:left">
					<img src="${pageContext.request.contextPath}/resources/image/user_default.png" id ="imgtest3" class="image--cover">
		  		</div>
		  		
				<div class="row">      
            		<div class=" col-md-7 col-lg-4 "> 
                		<table class="table accession table-condensed" style='font-size:10pt'>
						<col width="50%">
						<tbody >
							<tr>
								<td colspan = "2">
									<button class="btn btn-primary" id='circulationBtn' title="Circulation Details" data-toggle="modal" 
									data-target="#circulationModal" disabled><span class="glyphicon glyphicon glyphicon-book" style="color:white"></span></button>
								
									<!-- <a class="btn btn-primary" id='circulationBtn' title="Circulation Details" data-toggle="modal" 
											data-target="#circulationModal"><span class="glyphicon glyphicon glyphicon-book" style="color:white"></span></a> -->
									
									<button class="btn btn-primary" id='patrstatusBtn' title="Patron Status" data-toggle="modal" 
									data-target="#patrStatusModal" disabled><i class="glyphicon glyphicon-user" aria-hidden="true"></i></button>	
									
									<!-- <a class="btn btn-primary" id='patrstatusBtn' title="Patron Status" data-toggle="modal" 
											data-target="#patrStatusModal"><span class="glyphicon glyphicon-user" style="color:white"></span></a> -->
									
									<button class="btn btn-primary" id='patrLateReturn' title="Late Return History" data-toggle="modal" 
									data-target="#patrLateReturnHis" disabled><i class="glyphicon glyphicon-info-sign" aria-hidden="true"></i></button>	
									<!-- <a class="btn btn-primary" id='patrLateReturn' title="Late Return History" data-toggle="modal" 
											data-target="#patrLateReturnHis"><span class="glyphicon glyphicon-info-sign" style="color:white"></span></a> -->
									
									<button class="btn btn-primary" id='receipting' title="Receipting" disabled><span class="glyphicon glyphicon glyphicon-credit-card" style="color:white"></span></button>
									<!-- <a class="btn btn-primary" id='receipting' title="Receipting" data-toggle="modal" 
											data-target="#patrLateReturnHis"><span class="glyphicon glyphicon-credit-card" style="color:white"></span></a> -->
								</td>
							</tr>
							<tr>
								<td>	
								<!-- <a id="urlreceipt" href="https://wilmudev.kmlink.com.my:8444/web/guest/maintenance1"></a> -->
									<label>Patron ID: </label> 
									<span>
										<input type="text" class="form-control" name ="GL14PATR" id="GL14PATR"  style="display: inline;width:50%"  onchange="access(this)" maxlength="12">
									</span>	
									<button type="button" class="btn btn-primary btn-sm search" id="btn_add" data-toggle="modal" 
												data-target="#searchPatron"><span class="glyphicon glyphicon-search" style="color:white"></span></button>		
								</td>
							 </tr>
							 <tr>
								<td>
									<label>Name:</label> <span class="NAME"></span>
								</td>
							  </tr>
						</tbody>
						</table>
             		</div>
				</div>
      		</div>
		</div>
	</div>
	
	<div class="panel panel-default">
    <div class="panel-heading" style="height:6%">
      <h4 class="panel-title">
        <a class="accordion-toggle" data-toggle="collapse" href="#accesioning">
          <i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
        </a>
      </h4>
    </div>
    <div id="accesioning" class="panel-collapse collapse in" >
      <div class="panel-body">
        <div class="row">
		   <div class="col-md-12">		
		   		<div class="form-group row">
		   			<div class="col-sm-2 col-md-2">
		   				<button type="button" class="btn btn-primary" id="recallBtn" data-toggle="modal" 
												data-target="#recallDetail"><span class="fa fa-phone-square" style="color:white" title="Recall"></span></button>
		   			</div>
		   		</div>
				<div class="form-group row">
					<div class="col-sm-2 col-md-2">
						<label>Control No:</label>
					</div>
					<div class="col-sm-3">
						 <input type="text" class="form-control" id="CT03MATNO" name='CT03MATNO' maxlength="10" value="" onchange="formvalidation(this)">
						
					</div>
					<div class="col-sm-1">
						<!-- <button type="button" class="btn btn-primary btn-sm search" id="searchcontrolNoBtn" data-toggle="modal" 
						data-target="#titleSearch"><span class="glyphicon glyphicon-search" style="color:white" href="RetrieveTitleModal?action=8"></span></button> -->
						<div><button type="button" id="searchcontrolNoBtn" class="btn btn-primary"  data-toggle="modal" data-target="#titleSearch" href="RetrieveTitleModal?action=8"><span class="glyphicon glyphicon-search"></span></button>
							<!-- <div class="col-sm-5">
								<div class="title" id="title"></div>
							</div> -->
						</div>
						<!-- <a class="btn btn-primary" id='retrieveLink' data-toggle='modal' data-target='#titleSearch' href='RetrieveTitleModal?action=8' title="Fine"><span class="glyphicon glyphicon-usd" style="color:white"></span></a> -->
					</div>
					<div class="col-sm-2 col-md-2">
						<label>Accession No:</label>
					</div>
					<div class="col-sm-3">
						 <!-- <input type="text" class="form-control" id="accessionNo" name='accessionNo' maxlength="10" value="" onchange="formvalidation(this)"> -->
						<select class="form-control" id="accessionNo">
						    <option>Please Select</option>
						  </select>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-2 col-md-2">
						<label>Recall Branch:</label>
					</div>
					<div class="col-sm-3">
						
						<select class="form-control" id="recallBranch">
						    <!-- <option>Please select</option> -->
						<%								
							List<SQLItem_Reassignement> locaBranchInfo = null;
							locaBranchInfo = SQLItem_Reassignement.getRecallBRANCH();
						
							for(SQLItem_Reassignement i: locaBranchInfo){
						%>
							<option value="<%=i.getICAT()%>"><%=i.getDESC10()%></option>
							<%-- <%=i.getBRNC05()%><%=i.getDESC71()%> --%>
						<%										
							}
						%>
						  </select>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-2 col-md-2">
						<label>Title:</label>
					</div>
					<div class="col-sm-10">
						<span class="title" ></span>
						<!-- <input type="text" class="form-control" id="title" name='title'> -->
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-2 col-md-2">
						<label>Borrowed By:</label>
					</div>
					<div class="col-sm-10">
						<span class="borrowedBy" ></span>
					</div>
				</div>
				<!-- <div class="form-group row">
					<div class="col-sm-2 col-md-2">
						<label>Recall Status:</label>
					</div>
					<div class="col-sm-10">
						<span class="recallStatus" ></span>
					</div>
				</div> -->
				<div class="form-group row">
					<div class="col-sm-2 col-md-2">
						<label>Item Recalled:</label>
					</div>
					<div class="col-sm-10">
						<!-- <input type="text" class="form-control" id="title" name='title'> -->
					</div>
				</div>
				
      </div>
	</div>
       
    </div>
  </div>
</div>
</div>
  


	<!-- MODAL WHEN CLICK Modify Item Status-->
    <div class="modal fade" id="modifyDetail" tabindex="-1" role="dialog" aria-labelledby="modifyDetail" aria-hidden="true"> 
		<div class="modal-dialog" style="width:60%;">
			    <div class="modal-content" id="modifyContent">
				  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
	
	<!-- MODAL WHEN CLICK BIB DETAILS-->
    <div class="modal fade" id="bibDetail" tabindex="-1" role="dialog" aria-labelledby="bibDetail" aria-hidden="true"> 
		<div class="modal-dialog" style="width:70%;">
			    <div class="modal-content" id="bibContent">
				  <!-- Remote content load here -->
			  	</div>
		</div>
	</div>
	
	<!-- MODAL WHEN CLICK Circulation Modal-->
    <div class="modal fade" id="circulationModal" tabindex="-1" role="dialog" aria-labelledby="circulationModal" aria-hidden="true"> 
		<div class="modal-dialog" style="width:70%;">
			    <div class="modal-content" id="circulationContent">
				  <!-- Remote content load here -->
			  	</div>
		</div>
	</div>

	<!-- MODAL WHEN CLICK PATR STATUS Modal-->
    <div class="modal fade" id="patrStatusModal" tabindex="-1" role="dialog" aria-labelledby="patrStatusModal" aria-hidden="true"> 
		<div class="modal-dialog" style="width:70%;">
			    <div class="modal-content" id="patrStatusContent">
				  <!-- Remote content load here -->
			  	</div>
		</div>
	</div>
	
	<!-- MODAL WHEN CLICK patrLateReturnHis Modal-->
		<div class="modal fade" id="patrLateReturnHis" tabindex="-1" role="dialog" aria-labelledby="patrLateReturnHis"  aria-hidden="true"> 
		<div class="modal-dialog" style="width:80%;overflow:auto">
			  <div class="modal-content" id="lateReturnTable">
				 <!--  Remote content load here -->
			  </div>
		</div>
	</div>

	
	
	<!-- Modal FOR VIEW MODE DETAIL CIR -->
  <div class="modal fade" id="detailCirculationModal" role="dialog">
    <div class="modal-dialog modal-lg" style="width:80%;">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Circulation Detail</h4>
        </div>
        <div class="modal-body">
       		<form class="table table-striped">
				<div class="form-group row">
					<label for="chargeDate" class="col-sm-2 col-form-label">Charge Date</label>
						<div class="col-sm-2">
							<span class="chargeDate"></span>
						</div>
					<label for="chargeTime" class="col-sm-2 col-form-label">Charge Time</label>
						<div class="col-sm-2">
							<span class="chargeTime"></span>
						</div>
					<label for="chargeStaff" class="col-sm-2 col-form-label">Charge Staff</label>
						<div class="col-sm-2">
							<span class="chargeStaff"></span>
						</div>
				</div>
				<div class="form-group row">
					<label for="returnDate" class="col-sm-2 col-form-label">Return Date</label>
						<div class="col-sm-2">
							<span class="returnDate"></span>
						</div>
					<label for="returnTime" class="col-sm-2 col-form-label">Return Time</label>
						<div class="col-sm-2">
							<span class="returnTime"></span>
						</div>
					<label for="returnStaff" class="col-sm-2 col-form-label">Return Staff</label>
						<div class="col-sm-2">
							<span class="returnStaff"></span>
						</div>
				</div>
				<div class="form-group row">
					<label for="renewDate" class="col-sm-2 col-form-label">Renew Date</label>
						<div class="col-sm-2">
							<span class="renewDate"></span>
						</div>
					<label for="renewTime" class="col-sm-2 col-form-label">Renew Time</label>
						<div class="col-sm-2">
							<span class="renewTime"></span>
						</div>
					<label for="renewStaff" class="col-sm-2 col-form-label">Renew Staff</label>
						<div class="col-sm-2">
							<span class="renewStaff"></span>
						</div>
				</div>
			</form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- MODAL WHEN CLICK Search Patron -->
    <div class="modal fade" id="searchPatron" tabindex="-1" role="dialog" aria-labelledby="searchPatron"  aria-hidden="true" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:80%;">
			    <div class="modal-content" id="searchPatrContent">
				  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
	
	<!-- MODAL WHEN CLICK Search showModelSearchAN2 -->
    <div class="modal fade" id="showModelSearchAN2" tabindex="-1" role="dialog" aria-labelledby="showModelSearchAN2"  aria-hidden="true" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:80%;">
			    <div class="modal-content" id="searchPatrContent">
				  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
	
	
	<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" role="document" style="width:900px;">
			<div class="modal-content">
			<!-- Remote content load here -->
			</div>
		</div>
	</div>	
	
	

	

</body>

</html>

	