<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/UserProfileFomPortal.js"></script>

	<style>
	     fieldset.scheduler-border {
		    border: 1px groove #ddd !important;
		    padding: 0 1.4em 1.4em 1.4em !important;
		    margin: 0 0 1.5em 0 !important;
		    -webkit-box-shadow:  0px 0px 0px 0px #000;
		            box-shadow:  0px 0px 0px 0px #000;
		}

	    legend.scheduler-border {
	        font-size: 1.2em !important;
	        font-weight: bold !important;
	        text-align: left !important;
	        width:auto;
	        padding:0 10px;
	        border-bottom:none;
	    }
     </style>
	
	
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">

					</div>

					<div class="panel-body" id="display_userprofile">
					
							<form class="form-horizontal" name="userprofile" id="userprofile">

								<fieldset class="scheduler-border">
								<legend class="scheduler-border">Patron Details</legend>
									<div class="row">
									
										
				            			<input type="hidden" class="patrSelection" value = "m">
										<jsp:include page="../../../module/PatronID.jsp"></jsp:include>
		 
									
									</div>
								</fieldset>
								
								<div class="form-group statedropdown">
												<select class="form-control" id="negeriUpd" name="negeriUpd">
													<jsp:include page="../../../include/shared/Selection/Select_Fndcode.jsp">
														<jsp:param name="fcode" value="M" /> 
													</jsp:include> 
												</select>
						</div>
							</form>	
							
							
							<div class="btn-group pull-right ">	
								<div class="col-md-1"><button type="button" id="updUserProfile" class="btn btn-primary" title="Update"> Update</button></div>
							</div>
						
						<br><br>
						
						
						
						<table class="table table-bordered table-striped display compact" width="100%">
						  <thead style="background-color:rgba(52, 73, 94, 0.94);color:#ecf0f1">
						    <tr>
						      <th scope="col" style="width:20%">#</th>
						      <th scope="col" style="width:40%">Portal PPJ</th>
						      <th scope="col" style="width:40%">WILMU</th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr>
						      <th scope="row">Name</th>
						      <td class="portalname"></td>
						      <td class="wilmuname"></td>
						    </tr>
						    <tr>
						      <th scope="row">Email</th>
						      <td class="portalemail"></td>
						      <td class="wilmuemail"></td>
						    </tr>
						     <tr>
						      <th scope="row">IC No</th>
						      <td class="portalic"></td>
						      <td class="wilmuic"></td>
						    </tr>
						    <tr>
						      <th scope="row">DOB</th>
						      <td class="portaldob"></td>
						      <td class="wilmudob"></td>
						    </tr>
						    <tr>
						      <th scope="row">Gender</th>
						      <td class="portalgender"></td>
						      <td class="wilmugender"></td>
						    </tr>
						    <tr>
						      <th scope="row">Address</th>
						      <td class="portaladd1"></td>
						      <td class="wilmuadd1"></td>
						    </tr>
						    <tr>
						      <th scope="row"></th>
						      <td class="portaladd2"></td>
						      <td class="wilmuadd2"></td>
						    </tr>
						    <tr>
						      <th scope="row"></th>
						      <td class="portaladd3"></td>
						      <td class="wilmuadd3"></td>
						    </tr>
						    <tr>
						      <th scope="row">Postcode</th>
						      <td class="portalpostcode"></td>
						      <td class="wilmupostcode"></td>
						    </tr>
						    <tr>
						      <th scope="row">State</th>
						      <td class="portalstate"></td>
						      <td class="wilmustate"></td>
						    </tr>
						    <tr>
						      <th scope="row">Country</th>
						      <td class="portalcountry"></td>
						      <td class="wilmucountry"></td>
						    </tr>
						    <tr>
						      <th scope="row">Mobile Phone No</th>
						      <td class="portalphoneno"></td>
						      <td class="wilmuphoneno"></td>
						    </tr>
						    <tr>
						      <th scope="row">Home Phone No</th>
						      <td class="portalhomeno"></td>
						      <td class="wilmuhomeno"></td>
						    </tr>
						  </tbody>
						</table>
						

					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
		
		
	<%-- 	<div class="modal fade" id="modalreviewitem" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" style="width: 70%;">
				<div class="modal-content">
					<jsp:include page="Modal_ReviewBahanRosak.jsp"></jsp:include> 
				</div>
			</div>
		</div> --%>
		

		
		
</body>
</html>