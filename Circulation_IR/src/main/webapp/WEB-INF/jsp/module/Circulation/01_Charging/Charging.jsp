<!DOCTYPE html>

  <%@page import="com.ilmu.circulation.Charging.*,java.util.*,com.ilmu.global.*,
				java.util.*"%>
<html lang="en">
<head>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Circulation.js"></script>					
  <title>Charging</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="text/javascript">
  
  function access() {

	  var b = document.getElementById("GL14PATR").value;
	  var a= "${pageContext.servletContext.contextPath }/PhotoServlet?GL14PATR="+b;
      document.getElementById("imgtest3").src=a;
     
		  
		  
  }
  function formvalidation()
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


 
  var contextPath='<%=request.getContextPath()%>';
  console.log(contextPath);


  function clearVars() {
	    document.getElementById('GL14STAT').value='';
	    document.getElementById('GL14NAME').value='';
	    document.getElementById('GL14MEMDATE').value='';
	 
	   }
  
  </script>
  
  
  
  <style>
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

<%
//session.setAttribute("pat2",request.getParameter("patr"));
%>
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
      </h4>
    </div>
    <div id="patrondet" class="panel-collapse collapse in">
      <div class="panel-body" id="patrDet">
      <div class="row">
            <div class="col-md-1 col-lg-1 " align="center"> 
            
            <img   id = "imgtest3" alt="User Pic" style="border: 2px solid black;" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png" class="img-circle img-responsive"> 
             	
            </div>
       
            <div class=" col-md-8 col-lg-8 "> 
                <table class="table accession table-condensed">
					<col width="60%">
						<tbody >
							<tr>
								<td colspan = "2">
								
									<a class="btn btn-primary" id='patrstatus' title="Patron's Status Enquiry" data-toggle="modal" 
											data-target="#patrStatusModal"><span class="glyphicon glyphicon-info-sign" style="color:white"></span></a>
											<a class="btn btn-primary" id='patrDetails' title="Patron Details" data-toggle="modal" 
											data-target="#patrDetailsModal"><span class="glyphicon glyphicon-user" style="color:white"></span></a>
											<!-- <a class="btn btn-primary" id='patrstatus' title="Patron's Status Enquiry" data-toggle="modal" 
											data-target="#patrStatusModal"><span class="glyphicon icon-user-add" style="color:white"></span></a> -->
											<a class="btn btn-primary" id='patrLateReturn' title="Late Return History" data-toggle="modal" 
											data-target="#patrLateReturnHis"><span class="glyphicon glyphicon-info-sign" style="color:white"></span></a>
								</td>
							</tr>
							<tr>
								<td>	
									<label>Patron ID: </label> 
									<span class="chargeaction">
										<input class="form-control GL14PATR" name ="patr" id="GL14PATR"  type="text" style="display: inline;width:50%"  onchange="access(this)">
									</span>	
									<span class="dischargeaction GL14PATR" style="display:none">
									</span>
												
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
	                		<label id="onloan"></label>
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
		               		<label id="itemoverdue"></label>
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
		               		<label id="itemreserved"></label>
	               		</div>
	               	</div>
                </div>
                <div class="row">
	               	<div class="form-group">
		               	<div class="col-md-8 col-lg-8">
		               		<label>Item onhold</label>
		               	</div>
		               	<div class="col-md-4 col-lg-4">
		               		<label>:</label>
		               		<label id="itemonhold"></label>
		               	</div>
	               	</div>
                </div>
                <div class="row">
	               	<div class="form-group">
		               	<div class="col-md-8 col-lg-8">
		               		<label>Outstanding Fines</label>
		               	</div>
		               	<div class="col-md-4 col-lg-4">
		               		<label>:</label>
		               		<label id="fines"></label>
		               	</div>
		               		<div class="col-md-8 col-lg-8">
		               		<label>Total Fines Charged</label>
		               	</div>
		               	<div class="col-md-4 col-lg-4">
		               		<label>:</label>
		               		<label id="totalfines"></label>
		               	</div>
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
				
					<div class="col-sm-3 col-md-3">
						<label>Accession No.:</label>
					</div>
					<div class="col-sm-3">
						 <input type="text" class="form-control" id="CT03DOCNO"   name='name' maxlength="10" value="" onchange="formvalidation(this)">
					
					</div>
					<div class="col-sm-2">
						 <!--   <input type="button" class="btn btn-dull" id="newsubmit" value="..." />  -->
						   <button  id='charging' class="btn btn-primary"  onclick='charging()' ><span class="glyphicon glyphicon-import" style="color:white"></span></button>
						   <button  id='discharging' class="btn btn-primary"  data-toggle='modal' ><span class="glyphicon glyphicon-export" style="color:white"></span></button>
						   <button  id='renewal2' class="btn btn-primary renewal2"  ><span class="glyphicon glyphicon glyphicon-registration-mark" style="color:white"></span></button>
						   
					 </div>
					 <div class="col-sm-2">
					 	<label><input type="checkbox" id="printSlip" class="printSlip" value= "printSlip">&nbsp;Print Slip</label>
					</div>
					<!-- The Modal -->
					<!-- 	<div id="myModal" class="modal">
						
						
						Modal content
						  <div  id="myModal2" class="modal-content"> 
						  
						  <span class="close">&times;</span>
						  
						  </div>
						  	  
						 
						</div>
						
						<script>
							// Get the modal
							var modal = document.getElementById('myModal');
							
							// Get the button that opens the modal
							var btn = document.getElementById("newsubmit");
							
							// Get the <span> element that closes the modal
							var span = document.getElementsByClassName("close")[0];
							
							
							
							// When the user clicks on <span> (x), close the modal
							span.onclick = function() {
							    modal.style.display = "none";
							}
							
							// When the user clicks anywhere outside of the modal, close it
							window.onclick = function(event) {
							    if (event.target == modal) {
							        modal.style.display = "none";
							    }
							}
							</script> -->
 		 
			<!-- 		 <div class="col-sm-2">
							
							
    					 <button class="renewal2"  type="button" id="renewal2">Renewal New</button>
    					
        			
				</div> 	 
					  -->
					
			</div>
			
      </div>
</div>
<div class="row">
	<div class="col-md-12" id="title">	
	</div>
</div>
      
	  <div class="panel-body">
       <table class="table table-bordered" id="table-charging">	
			<thead>
				<tr>
					<th data-sortable="true">Accession No.</th>
					<th data-sortable="true">Title</th>
					<th data-sortable="true">Charge/Renewal Date</th>
					<th data-sortable="true">Charge/Renewal Time</th>
					<th data-sortable="true">Due Date</th>
					<th data-sortable="true">Due Time</th>
					<th data-sortable="true">Status</th>
<!-- 					<th data-sortable="true">Late By</th>
					<th data-sortable="true">Fines</th> -->
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
      </div>
       
    </div>
  </div>
</div>
<div class="modal fade" id="patrStatusModal" tabindex="-1" role="dialog" aria-labelledby="paraZ39"  aria-hidden="true"> 
				    <div class="modal-dialog" style="width:1200px;">
						  <div class="modal-content" id="Patrtable">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
<div class="modal fade" id="patrDetailsModal" tabindex="-1" role="dialog" aria-labelledby="patrDetailsModal"  aria-hidden="true"> 
	<div class="modal-dialog" style="width:1200px;">
		  <div class="modal-content" id="patrDetailsTable">
			  <!-- Remote content load here -->
		  </div>
	</div>
</div>

<div class="modal fade" id="patrLateReturnHis" tabindex="-1" role="dialog" aria-labelledby="patrLateReturnHis"  aria-hidden="true"> 
	<div class="modal-dialog" style="width:1200px;">
		  <div class="modal-content" id="lateReturnTable">
			  <!-- Remote content load here -->
		  </div>
	</div>
</div>

</body>

</html>

	