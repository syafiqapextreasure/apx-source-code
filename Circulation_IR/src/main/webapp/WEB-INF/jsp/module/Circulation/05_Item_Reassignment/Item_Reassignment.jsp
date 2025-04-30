<!DOCTYPE html>
<html lang="en">
  <%@page import="com.ilmu.circulation.Charging.*,java.util.*,com.ilmu.global.*,
				java.util.*, com.ilmu.foundation.global.SQLStatement, com.ilmu.circulation.Item_Reassignment.*"%>

<head>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Item_Reassignment.js"></script>	
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepaginator/1.1.0/bootstrap-datepaginator.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script> -->
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> --%>
<!-- <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> -->
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.0/css/bootstrap-datepicker3.css" integrity="sha256-kS81UmreG6WJA7BRAVmBSkuc0YldflRXJw66wiAF9a8=" crossorigin="anonymous" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.0/js/bootstrap-datepicker.js" integrity="sha256-4/8vOpKmglUDBGTR6LAubR1L6/0f9kvKVfWdNWcxEV8=" crossorigin="anonymous"></script>


				
  <title>Item Reassignment</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <script type="text/javascript">
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
  

/*   function formvalidation()
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
   
  } */
  
/*   function messageBox(code, criteria, label){
	    var url = "Error_Page?GL79ERRCODE="+code+"" +
	    "&criteria=" + criteria + "&label="+label+"";
	    //alert(url);
	    $.ajax({
	          url: url,
	          success: function(result) {
	         
	                swal(result);
	          }
	    });
	} */
  
 /*  $(document).ready(function(){
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
 */

 
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
	/*   margin: 20px; */

	  object-fit: cover;
	  object-position: center right;
	}
  	/* 	.charging_info {
	    padding-top: 20px;
	    /*margin: 10px 0 20px 0;
	    background-color: rgba(214, 224, 226, 0.2);
	    border-top-width: 0;
	    border-bottom-width: 2px;
	    -webkit-border-radius: 3px;
	    -moz-border-radius: 3px;
	    border-radius: 3px;
	    -webkit-box-shadow: none;
	    -moz-box-shadow: none;
	    box-shadow: none;
	    -webkit-box-sizing: border-box;
	    -moz-box-sizing: border-box;
	    box-sizing: border-box;
	}
 */  </style>
  
</head>

		
<body>

<%
//session.setAttribute("pat2",request.getParameter("patr"));
%>


<div class="panel-group" id="accordion">
	<div class="panel panel-default">
    	<div class="panel-heading">
      		<div class="form-check form-check-inline">
				<label class="form-check-label">
			    	<input class="form-check-input" type="radio" name="actiontype" id="inlineRadioCategory" value="category" checked> Item Category &nbsp;
			 	</label>  
			 	<label class="form-check-label">
			    	<input class="form-check-input" type="radio" name="actiontype" id="inlineRadioBranch" value="branch"> Location
			  	</label>
			</div>
        	<a class="accordion-toggle" data-toggle="collapse" href="#patrondet">
          		<i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
        	</a>
    	</div>
    	
		<div id="patrondet" class="panel-collapse collapse in">
    		<div class="panel-body" id="patrDet">
        		<div style="float:left">
					<img src="${pageContext.request.contextPath}/resources/image/user_default.png" id ="imgtest3" class="image--cover">
		  		</div>
		  			  		
				<div class="row">      
            		<div class=" col-md-9 col-lg-10 "> 
            			<form>
		  					<div class="form-group row">
								<label for="GL14PATR" class="col-sm-2 col-form-label">Patron ID</label>
									<div class="col-sm-5">
								      	<input class="form-control patrid" name="patr" id="GL14PATR"  type="text" style="display: inline;width:50%"  onchange="access(this)" maxlength="12">
								      	<button type="button" class="btn btn-primary btn-sm search" id="btn_add" data-toggle="modal" 
												data-target="#searchPatron"><span class="glyphicon glyphicon-search" style="color:white"></span></button>	
								    </div>
								   <!--  <div class="col-sm-1">
								    </div> -->
								<label for="GL14STAT" class="col-sm-2 col-form-label">Status</label>
									<div class="col-sm-3">
								    	<span class="Status"></span>
									</div>
							</div>
							<div class="form-group row">
								<label for="GL14NAME" class="col-sm-2 col-form-label">Name</label>
									<div class="col-sm-5">
								      	<span class="Name"></span>
								    </div>
								<label for="GL14EXPDATE" class="col-sm-2 col-form-label">Expiring Date</label>
									<div class="col-sm-3">
								    	<span class="ExpiringDate"></span>
								 	</div>
							</div>
							<div class="form-group row">
								<label for="GL14CATE" class="col-sm-2 col-form-label">Patron Category</label>
									<div class="col-sm-5">
								      	<span class="PatronCategory"></span>
								    </div>
								<label for="GL14GRID" class="col-sm-2 col-form-label">Group Id</label>
									<div class="col-sm-3">
								    	<span class="GroupId"></span>
								 	</div>
							</div>
						
								<%
									List<SQLItem_Reassignement> desc = SQLItem_Reassignement.getReassignGrace(); 
									for (SQLItem_Reassignement i: desc) {
								%>
									<input type="hidden" id="addDate" value="<%=i.getVALUE9()%>">
            					<%
									}
            					%>
		  				</form>
             		</div>
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
				<div class="form-group">	
				
					<!-- <div class="col-sm-3 col-md-3">
						<label>Accession No.:</label>
					</div>
					<div class="col-sm-3">
						 <input type="text" class="form-control" id="CT03DOCNO"   name='name' maxlength="10" value="" onchange="formvalidation(this)">
					
					</div>
					<div class="col-sm-2">
						   <input type="button" class="btn btn-dull" id="newsubmit" value="..." /> 
						   <button  id='charging' class="btn btn-primary"  onclick='charging()' ><span class="glyphicon glyphicon-import" style="color:white"></span></button>
						   <button  id='discharging' class="btn btn-primary"  data-toggle='modal' ><span class="glyphicon glyphicon-export" style="color:white"></span></button>
						   <button  id='renewal2' class="btn btn-primary renewal2"  ><span class="glyphicon glyphicon glyphicon-registration-mark" style="color:white"></span></button>
						   
					 </div> -->
								 
					 <div class=" col-md-10 col-lg-12"> 
                		<table class="table accession table-condensed" style='font-size:10pt'>
							<col width="60%">
								<tbody >
									<tr>
										<td colspan="2" align="left">
											<button  id='refresh' class="btn btn-primary"><span class="fa fa-refresh" style="color:white" title="Refresh"></span></button>
						   					<button  id='unassign' class="btn btn-primary"><span class="fa fa-square-o" style="color:white" title="Unassign"></span></button>
						   					<button  id='reassign' class="btn btn-primary"><span class="fa fa-clone" style="color:white" title="Reassign"></span></button>
						   					<!-- <button  id='view' class="btn btn-primary"><span class="fa fa-eye" style="color:white" title="View"></span></button> -->
						   					<button type="button" class="btn btn-primary" id="view" data-toggle="modal" 
												data-target="#viewDetail"><span class="fa fa-eye" style="color:white" title="View"></span></button>
											<!-- <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#viewDetail">Open Modal</button> -->
										</td>
									</tr>
								</tbody>
						</table>
						<div class="form-group row">
						    <label class="col-sm-3 col-form-label"  for="AccessionNo">Accession No :</label>
						    <div class="col-sm-3">
						    	<div class="input-group">

							    	<input type="text" maxlength="10" class="form-control" id="AccessionNo">
							    	<!-- <span class="input-group-addon">..</span> -->
								    <!-- <button class="btn btn-default" type="button" data-placement="right">...</button> -->
								    <a href="searchAN" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#showModelSearchAN"><span class="glyphicon glyphicon-option-horizontal"></span></a>
						    	</div>
						    </div>
						    <label for="CallNo" class="col-sm-2 col-form-label">Call No:</label>
						    <div class="col-sm-2" id="CallNo"></div>
						</div>	
						<div class="form-group row">
						    <label for="Title" class="col-sm-3 col-form-label">Title/Author:</label>
						    <div class="col-sm-9" id="Title"></div>
						</div>	
						<div class="form-group row" id="itemInfo3">
						    <label for="oriItemCat" class="col-sm-3 col-form-label">Original Item Cat:</label>
						    <div id="oriItemCat"></div>
						    <!-- <div class="col-sm-3" id="CT03ICAT">
						    	<input type="text" class="form-control" id="CT03ICAT" name="CT03ICAT">
						    </div> -->
						    <div class="col-sm-3" id="DESC10"></div>
						    <label class="col-sm-3 col-form-label">Reassigned Item Cat:</label>
						    	<div class="col-sm-3">
						    		<%		
						    			String oriItemCat = request.getParameter("oriItemCat");
						    			System.out.println(oriItemCat+"klj");
										List<SQLItem_Reassignement> ICatInfo = null;
										ICatInfo = SQLItem_Reassignement.getICat();
									%>
										<select class="form-control" id="icat" name="icat">
										<option value="">Please select</option>
										<%
										for(SQLItem_Reassignement i: ICatInfo){
										%>
										<option value="<%=i.getICAT()%>"><%=i.getDESC10()%></option>

										<%										
											}
										%>
										</select>
						    	</div>
						    	<div class="col-sm-3" id="icat2">
						    		<input type="text" class="form-control" id="icat2input" readonly>
						    	</div>
						</div>	
						<div class="form-group row" id="locaInfo">
						    <label for="" class="col-sm-3 col-form-label">Original Location:</label>
						    <div class="col-sm-3" id="DESC05"></div>
						    <div id="LOCA03"></div>
						    
						    <label class="col-sm-3 col-form-label">Reassigned Location:</label>
						    	<div class="col-sm-3">
						    		<%								
										List<SQLItem_Reassignement> locaBranchInfo = null;
										ICatInfo = SQLItem_Reassignement.getLOCABRANCH();
									%>
										<select class="form-control" id="PLOCA13" name="PLOCA13">
										<option value="">Please select</option>
										<%
										for(SQLItem_Reassignement i: ICatInfo){
										%>
										<option value="<%=i.getLOCA()%>"><%=i.getDESC05()%></option>
											<%=i.getBRNC05()%><%=i.getDESC71()%>
										<%										
											}
										%>
										</select>
						    	</div>
						    	<div class="col-sm-3" id="PLOCA132">
						    		<input type="text" class="form-control" id="PLOCA132input" readonly>
						    	</div>
						</div>	
						<div class="form-group row" id="locaInfo2">
						    <label for="" class="col-sm-3 col-form-label">Original Branch:</label>
						    <div class="col-sm-3" id="DESC71"></div>
						    <div class="col-sm-3" id="BRNC71"></div>
						    
						    <label for="" class="col-sm-3 col-form-label">Reassigned Branch:</label>
						    	<div class="col-sm-3" id="ReassignedBranch">
						    	</div>
						</div>	
						<div class="form-group row" >
						    <label for="Starts" class="col-sm-3 col-form-label">Reassignment Begin:</label>
						    	<div class="col-sm-3" id="Starts"></div>
						    
						    <label for="" class="col-sm-3 col-form-label">Reassignment End</label>
						    	<div class="col-sm-3">
						    		<div class="input-group date" id="dateStop">
							    		<input type="text" class="form-control" id="rStops" name="rStops">
									    	<span class="input-group-addon">
	  											<i class="glyphicon glyphicon-calendar"></i>
	  										</span>
						    		</div>
						    	</div>
						</div>
						<div class="form-group row" >
						    <label for="" class="col-sm-3 col-form-label">Reassigned On:</label>
						    	<div class="col-sm-6" id="rOn"></div>
						</div>	
						
						<div class="form-group row">
							<label for="" class="col-sm-3 col-form-label"></label>
						    	<div class="col-sm-7" id="after"></div>
						</div>
						<div class="form-group row">	
							<div class="col-sm-3" id="oriItemCat"></div>	
						</div>	
					<!-- The Modal -->	
					</div>
      			</div>
			</div>

			<div class="row">
				<div class="col-md-12" id="title">	
				</div>
			</div>
      
	  		<div class="panel-body">
		       <!-- <table class="table table-bordered " id="table-charging">	
					<thead>
						<tr>
							<th data-sortable="true">Accession No.</th>
							<th data-sortable="true">Title</th>
							<th data-sortable="true">Charge/Renewal Date</th>
							<th data-sortable="true">Charge/Renewal Time</th>
							<th data-sortable="true">Due Date</th>
							<th data-sortable="true">Due Time</th>
							<th data-sortable="true">Status</th>
							<th class="hide" data-sortable="true" style="display:none">Late By</th>
							<th class="hide" data-sortable="true" style="display:none">Fines</th> 
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table> -->
      		</div>
       
    		</div>
  		</div>
	</div>
	
	<!-------------- DIALOG --------------->
	<!-- <div id="dialog" title="Basic dialog">
    	<p>Dialog box</p>
	</div> -->
</div>

	
	
	
	
	<!-- MODAL WHEN CLICK PATR Details -->
<!-- 	<div class="modal fade" id="patrDetailsModal" tabindex="-1" role="dialog" aria-labelledby="patrDetailsModal"  aria-hidden="true"> 
		<div class="modal-dialog" style="max-width:80%;overflow:auto">
		  	<div class="modal-content" id="patrDetailsTable">
			  Remote content load here
		  	</div>
		</div>
	</div> -->
	
	<!-- MODAL WHEN CLICK Accession No Details -->
	<div class="modal lg fade" id="showModelSearchAN" role= "dialog" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog"  style="width:70%">
      <div class="modal-content">
    		
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
	
	
	<!-- MODAL WHEN CLICK View -->
    <div class="modal fade" id="viewDetail" tabindex="-1" role="dialog" aria-labelledby="viewDetail"  aria-hidden="true" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:80%;">
			    <div class="modal-content" id="searchView">
				  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
	
	
  <!-- Modal -->
<!--   <div class="modal fade" id="viewDetail" role="dialog">
    <div class="modal-dialog">
      
    </div>
  </div> -->

</body>
</html>

	