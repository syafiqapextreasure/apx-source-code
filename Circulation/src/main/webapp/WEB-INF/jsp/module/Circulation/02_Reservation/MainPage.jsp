<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ilmu.foundation.LocationCode.*,
				java.util.*,com.ilmu.global.*"%>
				<script>
// 				  $(window).bind('load', function() {
// 					   window.stop()
// 					});
				$(document).ready(function() {
				
				 $('#general,#accession').click(function(e) {

					 localStorage.setItem('activeTab', $(e.target).attr('href'));
					 location.reload();
					});
				});
				 
				</script>
<!--<div id="exTab1" class="container">	
<ul  class="nav nav-pills">
			<li class="active">
        <a  href="#1a" data-toggle="tab">Overview</a>
			</li>
			<li><a href="#2a" data-toggle="tab">Using nav-pills</a>
			</li>
			<li><a href="#3a" data-toggle="tab">Applying clearfix</a>
			</li>
  		<li><a href="#4a" data-toggle="tab">Background color</a>
			</li>
		</ul>

			<div class="tab-content clearfix">
			  <div class="tab-pane active" id="1a">
          <h3>Content's background color is the same for the tab</h3>
				</div>
				<div class="tab-pane" id="2a">
          <h3>We use the class nav-pills instead of nav-tabs which automatically creates a background color for the tab</h3>
				</div>
        <div class="tab-pane" id="3a">
          <h3>We applied clearfix to the tab-content to rid of the gap between the tab and the content</h3>
				</div>
          <div class="tab-pane" id="4a">
          <h3>We use css to change the background color of the content to be equal to the tab</h3>
				</div>
			</div>
  </div>-->


 <hr></hr>
<%--  <script type="text/javascript" src="${pageContext.request.contextPath}/plugin/jquery-2.1.4.min.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Reservation.js"></script>					
<div id="exTab2" class="container">	
<ul class="nav nav-tabs">
			<li class="active">
        <a  href="#1" class='tab' id='general' data-toggle="tab">General</a>
			</li>
			<li><a href="#2" class='tab' id='accession' data-toggle="tab">Accession Number</a>
			</li>
		</ul>

			<div class="tab-content ">
			  <div class="tab-pane active" id="1">
			  <br/>
					  <div class="form-group row" >
						    <label for="staticEmail" class="col-sm-2 col-form-label">Control No.:</label>
						    <div class="col-sm-5">
						      <input type="text" maxlength="10" class="form-control" id="CT03MATNO">
						      <span id="error" style="color:red"></span>
						    </div>
						     <div class="col-sm-1 col-md-1"><button type="button" class="btn btn-default selectPopup"  data-toggle="modal" data-target="#titleSearch" href="RetrieveTitleModal?action=8" title="Search">...</button></div>
						  </div>
						  <div class="changeValue">
						  <div class="form-group row" id="location">
						    <label for="inputPassword" class="col-sm-2 col-form-label">Location :</label>
						    <div class="col-sm-3">
								<select class="form-control" id="sel1">
									<option>Please Select </option>
								</select>
						    </div>
						<!-- <div class="col-sm-5">
								<select class="form-control" id="sel1">
									<option>Please Select </option>
								</select>
						    </div>-->
						  </div>
						  <div class="form-group row" id="accession">
						    <label for="inputPassword" class="col-sm-2 col-form-label">Accession No. :</label>
						    <div class="col-sm-5">
						        <select class="form-control selectCT03MATNO" id="">
									<option value="0">Please Select </option>
									
								</select>
						    </div>
						  </div>
						</div>
				</div>
				<div class="tab-pane" id="2">
         		     <br/>
					  <div class="form-group row">
						    <label for="staticEmail" class="col-sm-2 col-form-label">Accession No.:</label>
						    <div class="col-sm-5">
						      <input type="text" class="form-control" id="CT03DOCNO" >
						    </div>
						  </div>
				</div>

			</div>
  </div>

<div class="panel panel-default" id="itemInfo">
  <div class="panel-body" style="width:90%;margin:auto">
  	 <div class="form-group row">
		<label for="staticEmail" class="col-sm-3 col-form-label">Item Location :</label>
		    <div class="col-sm-5">
		    	<span></span>
		    </div>
		<label for="staticEmail" class="col-sm-2 col-form-label">Location :</label>
		    <div class="col-sm-5">
		    	<span></span>
		    </div>
	</div>
	<div class="form-group row">
		 <label for="staticEmail" class="col-sm-3 col-form-label">Title :</label>
		    <div class="col-sm-5">
		    	<span></span>
		    </div>
		<label for="staticEmail" class="col-sm-2 col-form-label">Call No. :</label>
		    <div class="col-sm-5">
		    	<span></span>
		    </div>
	</div>
  </div>
</div>

<%-- <div class="panel panel-default">
  <div class="panel-body" style="width:90%;margin:auto">
  	 <div class="form-group row">
		<label for="staticEmail" class="col-sm-3 col-form-label">Pickup Branch :</label>
		    <div class="col-sm-2">
		    	   <select class="form-control" id="sel1">
						<option>Please Select </option>
						<%
							List<LocationCriteria> location = null;
							location = LocationCriteria.getLocation();
				        	for (LocationCriteria i: location) {
						%>
						<option><%=i.getGL71BRNC() %></option>
						<%
				        	}
						%>
					</select>
			</div>
			<div class="col-sm-5">
						<select class="form-control" id="sel1">
									<option>Please Select </option>
									<%
										List<LocationCriteria> locationDesc = null;
										locationDesc = LocationCriteria.getLocation();
				          				for (LocationCriteria i: locationDesc) {
									%>
									<option><%=i.getGL71DESC() %></option>
									<%
				          				}
									%>
								</select>
		    </div>
	</div>
  </div>
</div>
 --%>
<div class="panel panel-default">
  <div class="panel-body" style="margin:auto">
  	 <div class="form-group resv_list">
	</div>
  </div>
</div>

	<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch" data-backdrop="static">
		<div class="modal-dialog" role="document" style="width:900px;">
			<div class="modal-content">
			<!-- Remote content load here -->
			</div>
		</div>
	</div>	
