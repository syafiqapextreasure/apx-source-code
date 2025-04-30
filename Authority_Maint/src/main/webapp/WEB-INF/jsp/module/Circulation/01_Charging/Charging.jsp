<!DOCTYPE html>
<html lang="en">
  <%@page import="com.ilmu.circulation.Charging.*,java.util.*,com.ilmu.global.*,
				java.util.*"%>

<head>

 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Circulation.js"></script>					
  <title>Charging</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="text/javascript">
/*   $(window).bind('load', function() {
	   window.stop()
	});
 function loadPage(){
  var a= "Handler_AccLvl?progID=AARM0150";
  $.get(a,function(data){
	  alert(data);
	  if(data.trim()==false){
		  document.write('<style type="text/css">body {visibility: visible;}</style>');
		  swal('Info!','Access Level not allowed', '' );
	  }
  })
 } */
  function access(GL14PATR) {
	
	 // var b = document.getElementById("GL14PATR").value;

	 if(GL14PATR!=null && GL14PATR!=""){
	  var a= "${pageContext.servletContext.contextPath }/PhotoServlet?GL14PATR="+GL14PATR;
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
 */
  
  
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
	         
	                swal(result);
	          }
	    });
	}
  
  $(document).ready(function(){
	  var table =  $('#table-charging').DataTable({
	       responsive: true
	   });
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
  	.charging_info {
    padding-top: 20px;
    /*margin: 10px 0 20px 0;*/
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
  </style>

</head>

		
<body>

<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading">
      	<div class="form-check form-check-inline">
			<label class="form-check-label">
			    <input class="form-check-input" type="radio" name="actiontype" id="inlineRadiocharge" value="charge" checked> Charge &nbsp;
			 </label>  
			 <label class="form-check-label">
			    <input class="form-check-input" type="radio" name="actiontype" id="inlineRadiodischarge" value="discharge"> Discharge
			  </label>
			  <label class="form-check-label">
			    <input class="form-check-input" type="radio" name="actiontype" id="inlineRadiorenewal" value="renewal"> Renewal
			  </label>
		</div>
<!-- 		<a class="btn btn-primary" id='enableSelect' title="Borrower's Info"><span class="glyphicon glyphicon-info-sign" style="color:white"></span></a>
		<a class="btn btn-primary" id='retrieveLink' data-toggle='modal' data-target='#titleSearch' href='RetrieveTitleModal?action=7' title="Fine"><span class="glyphicon glyphicon-usd" style="color:white"></span></a> -->
        <a class="accordion-toggle" data-toggle="collapse" href="#patrondet">
          <i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
        </a>
      
    </div>
    <div id="patrondet" class="panel-collapse collapse in">
      <div class="panel-body" id="patrDet">
        <div style="float:left">
		  
		  <img src="${pageContext.request.contextPath}/resources/image/user_default.png" id = "imgtest3" class="image--cover">
		  </div>
      <div class="row">
            <div class=" col-md-7 col-lg-7 "> 
                <table class="table accession table-condensed" style='font-size:10pt'>
					<col width="50%">
						<tbody >
							<tr>
								<td colspan = "2">
								
									<button class="btn btn-primary" id='patrstatus' title="Circulation Details" data-toggle="modal" 
									data-target="#patrDetailsModal" disabled><span class="glyphicon glyphicon glyphicon-book" style="color:white"></span></button>
									<button class="btn btn-primary" id='patrDetails' title="Patron Status Enquiry" data-toggle="modal" 
									data-target="#patrDetailsModal" disabled><span class="glyphicon glyphicon-user" style="color:white"></span></button>
									<!-- <a class="btn btn-primary" id='patrstatus' title="Patron's Status Enquiry" data-toggle="modal" 
									data-target="#patrStatusModal"><span class="glyphicon icon-user-add" style="color:white"></span></a> -->
									<button class="btn btn-primary" id='patrLateReturn' title="Late Return History" data-toggle="modal" 
									data-target="#patrLateReturnHis" disabled><i class="fa fa-exchange" aria-hidden="true"></i></button>
																	     <!-- Removed temporarily -->
									<!-- <button class="btn btn-primary" id='override' title="Override Date" data-toggle="modal" 
									data-target="#overrideModal"><i class="fa fa-exchange" aria-hidden="true" style="color:white"></i></button> -->
									
									<button class="btn btn-primary" id='receipting' title="Receipting" disabled><span class="glyphicon glyphicon glyphicon-credit-card" style="color:white"></span></button>
									<button id="refresh" class="btn btn-primary" title= "Refresh"><span class="glyphicon glyphicon-refresh" style="color:white"></span></button>
								</td>
							</tr>
							<tr>
								<td>	
									<label>Patron ID: </label> 
									<span class="chargeaction">
										<input class="form-control patrid GL14PATR" name ="patr" id="GL14PATR"  type="text" style="display: inline;width:50%"  maxlength="12">
									</span>	
									<span class="dischargeaction GL14PATR" style="display:none">
									</span>
									<button type="button" class="btn btn-primary btn-sm search" id="btn_add" data-toggle="modal" 
												data-target="#searchPatron" title="Search Patron" ><span class="glyphicon glyphicon-search" style="color:white"></span></button>	
								</td>
								
								
								<td>
									<label>Status: </label>
								<span class="GL14STAT" ></span>
								</td>
								
							 </tr>
							  <tr>
								<td>
									<label>Name:</label><span class="GL14NAME"></span>
								</td>
								 <td>
									<label>Membership Date:</label><span class="GL14MEMDATE"></span>
								</td>
							  </tr>
							  <tr>
								<td>
									<label>Patron Category:</label><span class="GL14CATE"></span>
								</td>
								 <td>
									<label>Expiring Date:</label><span class="GL14EXPDATE"></span>
								</td>
								
							  </tr>
							  <tr>
							   <td>
									<label>Branch:</label><span class="GL14BRANCH"></span>
								</td>
							  </tr>
						</tbody>
				</table>
             </div>
             <div class="col-md-3 col-lg-3 charging_info" style="border:1px solid #ddd;">
                <div class="row">
	               	<div class="form-group">
	               	    <div class="col-md-8 col-lg-8">
	               			<label>Item onloan </label>
	                	</div>		
	                	<div class="col-md-4 col-lg-4">
	                		<label>:</label>
	                		<label id="onloan">0</label>
	                	</div>
	                </div>
                </div>
                <div class="row">
	               	<div class="form-group">
	               	  	<div class="col-md-8 col-lg-8">
	               			<label>Item overdue </label>
	               		</div>
	              		<div class="col-md-4 col-lg-4">
		               		<label>:</label>
		               		<label id="itemoverdue">0</label>
	               		</div>
	               	</div>
                </div>
                <div class="row">
	               	<div class="form-group">
		               	<div class="col-md-8 col-lg-8">
		               		<label>Item reserved</label>
		               	</div>
		               	<div class="col-md-4 col-lg-4">
		               		<label>:</label>
		               		<label id="itemreserved">0</label>
	               		</div>
	               	</div>
                </div>
             <!--    <div class="row">
	               	<div class="form-group">
		               	<div class="col-md-8 col-lg-8">
		               		<label>Item onhold</label>
		               	</div>
		               	<div class="col-md-4 col-lg-4">
		               		<label>:</label>
		               		<label id="itemonhold">0</label>
		               	</div>
	               	</div>
                </div> -->
                <div class="row">
	               	<div class="form-group">
		               	<div class="col-md-8 col-lg-8">
		               		<label>Outstanding Fines</label>
		               	</div>
		               	<div class="col-md-4 col-lg-4">
		               		<label>:</label>
		               		<label id="fines">0.00</label>
		               	</div>
		               		<!-- <div class="col-md-8 col-lg-8">
		               		<label>Total Fines Charged</label>
		               	</div>
		               	<div class="col-md-4 col-lg-4">
		               		<label>:</label>
		               		<label id="totalfines">0.00</label>
		               	</div> -->
	               	</div>
                </div>
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
				
					<div class="col-sm-2 col-md-2">
						<label>Accession No.:</label>
					</div>
					<div class="col-sm-3">
						 <input type="text" class="form-control" id="CT03DOCNO"   name='name' maxlength="10" value="" onkeydown="getAccession(event)" onchange="formvalidation(this)" >
					
					</div>
					<div class="col-sm-5">
						 <!--   <input type="button" class="btn btn-dull" id="newsubmit" value="..." />  -->
						   <button  id='charging' class="btn btn-primary"  onclick='charging()' title="Charge" ><span class="glyphicon glyphicon-import" style="color:white"></span></button>
						   <button  id='discharging' class="btn btn-primary"  data-toggle='modal' onclick='discharging()' title="Discharge"><span class="glyphicon glyphicon-export" style="color:white"></span></button>
						   <button  id='renewal2' class="btn btn-primary renewal2"  onclick="renew()" title="Renew"><span class="glyphicon glyphicon glyphicon-registration-mark" style="color:white"></span></button>
						   <button class="btn btn-primary" id='printSlip' title="Print Slip" onclick="printSlip()"><span class="glyphicon glyphicon-print" style="color:white"></span></button>
						   &nbsp;
						   	<label><input type="checkbox" id="fast" class="fast" value= "fast">&nbsp;Fast Counter Service</label>
					 	<label>&nbsp;<input type="checkbox" id="printSlip" class="printSlip" value= "printSlip">&nbsp;Print Slip</label>
						   
					 </div>
<!-- 
					 <div class="col-sm-3">
					 	<label><input type="checkbox" id="fast" class="fast" value= "fast">&nbsp;Fast Charging</label>
					 	<label>&nbsp;<input type="checkbox" id="printSlip" class="printSlip" value= "printSlip">&nbsp;Print Slip</label>
					</div> -->
				</div>
			
      </div>
</div>
<br/>
<div class="row">
<div class="col-md-12">		
				<div class="form-group">
				
					<div class="col-sm-2 col-md-2">
						<label>Title:</label>
					</div>
					<div class="col-sm-8" id="title" style="font-size:10pt">
					<!-- 	<textarea id="title" style="font-size:10pt"></textarea> -->
					
					</div>
				<!-- 	<div class="col-sm-5">
						   <input type="button" class="btn btn-dull" id="newsubmit" value="..." /> 
						   <button  id='charging' class="btn btn-primary"  onclick='charging()' title="Charge" ><span class="glyphicon glyphicon-import" style="color:white"></span></button>
						   <button  id='discharging' class="btn btn-primary"  data-toggle='modal' onclick='discharging()' title="Discharge"><span class="glyphicon glyphicon-export" style="color:white"></span></button>
						   <button  id='renewal2' class="btn btn-primary renewal2"  onclick="renew()" title="Renew"><span class="glyphicon glyphicon glyphicon-registration-mark" style="color:white"></span></button>
						   <button class="btn btn-primary" id='printSlip' title="Print Slip" onclick="printSlip()"><span class="glyphicon glyphicon-print" style="color:white"></span></button>
						   &nbsp;
						   	<label><input type="checkbox" id="fast" class="fast" value= "fast">&nbsp;Fast Counter Service</label>
					 	<label>&nbsp;<input type="checkbox" id="printSlip" class="printSlip" value= "printSlip">&nbsp;Print Slip</label>
						   
					 </div> -->
<!-- 
					 <div class="col-sm-3">
					 	<label><input type="checkbox" id="fast" class="fast" value= "fast">&nbsp;Fast Charging</label>
					 	<label>&nbsp;<input type="checkbox" id="printSlip" class="printSlip" value= "printSlip">&nbsp;Print Slip</label>
					</div> -->
				</div>
			
      </div>

</div>
  <br/>    
	  <div class="panel-body">
       <table class="table table-bordered " id="table-charging" style="float:left;font-size:10pt">	
			<thead>
				<tr>
					<th data-sortable="true">Accession No.</th>
					<!-- <th data-sortable="true">Title</th> -->
					<th data-sortable="true">Charge Date</th>
					<th data-sortable="true">Charge Time</th>
					<th data-sortable="true">Due Date</th>
					<th data-sortable="true">Due Time</th>
					<th data-sortable="true">Status</th>
					<th class="ndate" data-sortable="true" style="display:none">New Date</th>
					<th class="ntime" data-sortable="true" style="display:none">New Time</th> 
					<th class="late" data-sortable="true" style="display:none">Late By</th>
					<th class="fine" data-sortable="true" style="display:none">Fines</th> 
					<th class="returndate" data-sortable="true" style="display:none">Returned Date</th>
					<th class="returntime" data-sortable="true" style="display:none">Returned Time</th> 
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
      </div>
       
    </div>
  </div>
</div>
<div class="modal fade" id="patrStatusModal" tabindex="-1" role="dialog" aria-labelledby="paraZ39"  aria-hidden="true" data-backdrop="static"> 
				    <div class="modal-dialog" style="max-width:80%;overflow:auto">
						  <div class="modal-content" id="Patrtable">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
<div class="modal fade" id="overrideModal" tabindex="-1" role="dialog" aria-labelledby="overrideModal"  aria-hidden="true" data-backdrop="static"> 
				    <div class="modal-dialog" style="max-width:80%;height:100%;overflow:auto">
						  <div class="modal-content overrideTable">
						  		<div class='override'></div>
						  </div>
					</div>
				</div>
				
<div class="modal fade" id="patrDetailsModal" tabindex="-1" role="dialog" aria-labelledby="patrDetailsModal"  aria-hidden="true" data-backdrop="static"> 
	<div class="modal-dialog" style="max-width:80%;overflow:auto">
		  <div class="modal-content" id="patrDetailsTable">
			  <!-- Remote content load here -->
		  </div>
	</div>
</div>

<div class="modal fade" id="patrLateReturnHis" tabindex="-1" role="dialog" aria-labelledby="patrLateReturnHis"  aria-hidden="true" data-backdrop="static"> 
	<div class="modal-dialog" style="width:80%;overflow:auto">
		  <div class="modal-content" id="lateReturnTable">
			  <!-- Remote content load here -->
		  </div>
	</div>
</div>

<div class="modal fade" id="searchPatron" tabindex="-1" role="dialog" aria-labelledby="searchPatron"  aria-hidden="true" data-backdrop="static"> 
	<div class="modal-dialog" style="width:80%;">
		    <div class="modal-content" id="searchPatrContent">
			  <!-- Remote content load here -->
		  </div>
	</div>
</div>

</body>

</html>	