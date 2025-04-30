<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
  <%@page import="com.ilmu.circulation.Charging.*,java.util.*,com.ilmu.global.*,
				java.util.*,
				com.ilmu.circulation.Weeding.*"%>
<html lang="en">
<head>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Weeding.js"></script>	

<!-- <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> -->
				
  <title>Weeding</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="text/javascript">
   
  function access() {

	  var b = document.getElementById("GL14PATR").value;
	  var a= "${pageContext.servletContext.contextPath }/PhotoServlet?GL14PATR="+b;
	  $.get(a,function(data){
		 if(data!=null && data!=""){
			 document.getElementById("imgtest3").src=a;
		 }
		});
	 
     
		  
		  
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
			    <input class="form-check-input" type="radio" name="actiontype" id="inlineRadiocharge" value="maintenance" checked>Maintenance &nbsp;
			 </label>  
			 <!-- <label class="form-check-label">
			    <input class="form-check-input" type="radio" name="actiontype" id="inlineRadiodischarge" value="enquiry"> Accession Status Enquiry &nbsp;
			  </label> -->
			  <label class="form-check-label">
			    <input class="form-check-input" type="radio" name="actiontype" id="inlineRadiorenewal" value="enquiry"> Enquiry
			  </label>
		</div>
<!-- 		<a class="btn btn-primary" id='enableSelect' title="Borrower's Info"><span class="glyphicon glyphicon-info-sign" style="color:white"></span></a>
		<a class="btn btn-primary" id='retrieveLink' data-toggle='modal' data-target='#titleSearch' href='RetrieveTitleModal?action=7' title="Fine"><span class="glyphicon glyphicon-usd" style="color:white"></span></a> -->
        <a class="accordion-toggle" data-toggle="collapse" href="#statusMaint">
          <i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
        </a>
    </div>
    <div id="statusCombineDetail" class="panel-collapse collapse in">
      	<div class="panel-body" id="statusCombine">
      		<div class="row">
       			<div class="col-md-12 col-lg-12">
       				<div class="form-group row">
						<label class="col-sm-2 col-form-label" for="CT03DOCNO">Accession No :</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="CT03DOCNO">
							    	<!-- <button type="button" class="btn btn-primary btn-sm search" id="searchAN2" data-toggle="modal" 
												data-target="#searchAN2"><span class="glyphicon glyphicon-search" style="color:white"></span></button> -->
							</div>
						    <div class="col-sm-2">
						    	 <a  href='RetrieveTitleModal?action=15' class="btn btn-primary" data-toggle="modal" data-target="#searchWeed" title="Search"><span class="glyphicon glyphicon-search"></span></a>
						    	 <button type="button" class="btn btn-primary" id="modifybtn"><span class="glyphicon glyphicon-trash" style="color:white" title="Weed"></span></button>
						    </div>
						    
						 <!--    <div align="left">
						    	<button  id='modifybtn' class="btn btn-primary"><span class="fa fa-exchange fa-2x" style="color:white" title="Modify Item Status"></span></button>
						    	<button type="button" class="btn btn-primary" id="modifybtn"><span class="glyphicon glyphicon-trash" style="color:white" title="Weed"></span></button>
						    </div> -->
					</div>
					<div class="form-group row">
					<div class="col-sm-12"><label>Title :</label><span class="Title"></span></div>
					</div>
					
					<div class="nav-tabs-custom">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#item" data-toggle="tab" aria-expanded="false"><strong>Item Details</strong></a></li>
							<li><a href="#circulation" data-toggle="tab"><strong>Bibliographic</strong></a></li>
							<li><a href="#related" data-toggle="tab" aria-expanded="true"><strong>Related Copies</strong></a></li>
			             	<!-- <li><a href="#eligibility" data-toggle="tab"><strong>Eligibility</strong></a></li> -->
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
								      	<label>:&nbsp;</label><span class="Status"></span>
								    </div>
								    <label for="Currency" class="col-sm-2 col-form-label">Currency Code</label>
								    	<div class="col-sm-4">
								      		<label>:&nbsp;</label><span class="Currency"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="ItemCategory" class="col-sm-2 col-form-label">Item Category</label>
								    <div class="col-sm-4">
								      	<label>:&nbsp;</label><span class="ItemCategory"></span>
								    </div>
								    <label for="ForeignCost" class="col-sm-2 col-form-label">Foreign Cost</label>
								    	<div class="col-sm-4">
								      		<label>:&nbsp;</label><span class="ForeignCost"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="SMD" class="col-sm-2 col-form-label">SMD</label>
								    <div class="col-sm-4">
								      	<label>:&nbsp;</label><span class="SMD"></span>
								    </div>
								    <label for="LocalCost" class="col-sm-2 col-form-label">Local Cost</label>
								    	<div class="col-sm-4">
								      		<label>:&nbsp;</label><span class="LocalCost"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="Condition" class="col-sm-2 col-form-label">Condition</label>
								    <div class="col-sm-4">
								      	<label>:</label><span class="Condition"></span>
								    </div>
								    <label for="CirculatedHits" class="col-sm-2 col-form-label">Circulated Hits</label>
								    	<div class="col-sm-4">
								      		<label>:&nbsp;</label><span class="CirculatedHits"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="CopyNo" class="col-sm-2 col-form-label">Copy No</label>
								    <div class="col-sm-4">
								      	<label>:&nbsp;</label><span class="CopyNo"></span>
								    </div>
								    <label for="Location" class="col-sm-2 col-form-label">Location</label>
								    	<div class="col-sm-4">
								      		<label>:&nbsp;</label><span class="Location"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="Volume" class="col-sm-2 col-form-label">Volume</label>
								    <div class="col-sm-4">
								      	<label>:</label><span class="Volume"></span>
								    </div>
								    <label for="Branch" class="col-sm-2 col-form-label">Branch</label>
								    	<div class="col-sm-4">
								      		<label>:&nbsp;</label><span class="Branch"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="ControlNo" class="col-sm-2 col-form-label">Control No</label>
								    <div class="col-sm-4">
								      	<label>:&nbsp;</label><span class="ControlNo"></span>
								    </div>
								    <label for="CreatedDate" class="col-sm-2 col-form-label">Created Date</label>
								    	<div class="col-sm-4">
								      		<label>:&nbsp;</label><span class="CreatedDate"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    <label for="ReleasedDate" class="col-sm-2 col-form-label">Released Date</label>
								    	<div class="col-sm-4">
								      		<label>:&nbsp;</label><span class="ReleasedDate"></span>
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
           						  <div class="form-group row">
								    	<label for="Title" class="col-sm-2 col-form-label">Title</label>
								    <div class="col-sm-10">
								      	<label>:&nbsp;</label><span class="Title"></span>
								    </div>
								  </div>
								    <div class="form-group row">
								    	 <label for="CallNumber" class="col-sm-2 col-form-label">Call Number</label>
								   		 <div class="col-sm-4">
								      		<label>:&nbsp;</label><span class="CallNumber"></span>
								    	</div>
								  </div>
								   <div class="form-group row">
								    	 <label for="CallNumber" class="col-sm-2 col-form-label">Author</label>
								   		 <div class="col-sm-4">
								      		<label>:&nbsp;</label><span class="Author"></span>
								    	</div>
								  </div>
								  <div class="form-group row">
								    	<label for="Publisher" class="col-sm-2 col-form-label">Publisher</label>
								    <div class="col-sm-4">
								      	<label>:&nbsp;</label><span class="Publisher"></span>
								    </div>
								   </div>
								   <div class="form-group row">
								    	<label for="ISBN" class="col-sm-2 col-form-label">Edition</label>
								    <div class="col-sm-4">
								      	<label>:&nbsp;</label><span class="ISBN"></span>
								    </div>
								</div><!-- END row circulation-->
								<div class="form-group row">
								    	<label for="ISBN" class="col-sm-2 col-form-label">Collation</label>
								    <div class="col-sm-4">
								      	<label>:&nbsp;</label><span class="Collation"></span>
								    </div>
								</div><!-- END row circulation-->
             				</div><!-- END box body circulation-->
             				</div><!-- END box body circulation-->
						</div><!-- /.End Tab circulation -->
						
						<!-- TAB 3 -->	 
					   	<div class="tab-pane" id="related">
					   		<div class="box-body">
           						<div class="row">
           							<table class="table table-responsive" id="table-related">	
           								<thead>
           									<tr> 
           										<th data-sortable="true">Accession No.</th>
           										<th data-sortable="true">Copy No.</th>
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
					   	<!-- <div class="tab-pane" id="eligibility">
					   		<div class="box-body">
           						<div class="row">
           							<table class="table table-responsive" id="table-eligibility">	
           								<thead>
           									<tr> 
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
								</div>END row eligibility
             				</div>END box body eligibility
						</div>/.End Tab eligibility -->

						</div><!-- /.END TAB CONTENT -->
		
					</div><!-- /.END CUSTOM TAB -->
					  <div class="form-group">
					    <label for="exampleTextarea">Reasons</label>
					    <textarea class="form-control" id="reason" rows="3"></textarea>
					  </div>
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
    		<p>
    			<button class="btn btn-primary" id='searchWeedEnquiry' title="Search Criteria" data-toggle="modal" 
									data-target="#searchWeed" style='float:right;margin-right:20px'><span class="glyphicon glyphicon glyphicon-search" style="color:white"></span></button>
			</p>
			<br/>
			<br/>
			<div class="weddingList">
    		<table class="table table-responsive" id="weeding_enquiry">	
           		<thead>
           			<tr> 
           				<th data-sortable="true">No.</th>
           				<th data-sortable="true">Accession No.</th>
           				<th data-sortable="true">Title</th>
           				<th data-sortable="true">Weed Date</th>
           			</tr>
           		</thead>
           		<tbody>
           		</tbody>
           	</table>
           	</div>
      		</div>
		</div>
	</div>

</div>
  


	<!-- MODAL WHEN CLICK Modify Item Status-->
    <div class="modal fade" id="modifyDetail" tabindex="-1" role="dialog" aria-labelledby="modifyDetail" aria-hidden="true" data-backdrop="static"> 
		<div class="modal-dialog" style="width:60%;">
			    <div class="modal-content" id="modifyContent">
				  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
	
	<!-- MODAL WHEN CLICK BIB DETAILS-->
    <div class="modal fade" id="bibDetail" tabindex="-1" role="dialog" aria-labelledby="bibDetail" aria-hidden="true" data-backdrop="static"> 
		<div class="modal-dialog" style="width:70%;">
			    <div class="modal-content" id="bibContent">
				  <!-- Remote content load here -->
			  	</div>
		</div>
	</div>
	
	<!-- MODAL WHEN CLICK Circulation Modal-->
    <div class="modal fade" id="searchWeed" tabindex="-1" role="dialog" aria-labelledby="circulationModal" aria-hidden="true" data-backdrop="static"> 
		<div class="modal-dialog" style="width:70%;">
			    <div class="modal-content" id="searchWeedContent">
				  <!-- Remote content load here -->
			  	</div>
		</div>
	</div>
	
		<!-- MODAL WHEN CLICK Circulation Modal-->
    <div class="modal fade" id="displayBO" tabindex="-1" role="dialog" aria-labelledby="circulationModal" aria-hidden="true" data-backdrop="static"> 
		<div class="modal-dialog" style="width:70%;">
			    <div class="modal-content" id="BORecord">
				  <!-- Remote content load here -->
			  	</div>
		</div>
	</div>
	
	 		<!-- Modal HTML View Accession Maint-->  
				<div class="modal fade" id="viewRecord" role="dialog" data-backdrop="static">
				    <div class="modal-dialog" style="width:1000px;height:900px;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
			    <!-- Modal END -->
	

</body>

</html>

	