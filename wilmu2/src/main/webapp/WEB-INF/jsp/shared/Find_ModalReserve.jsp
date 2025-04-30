<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%-- <%@page import="com.kmlink.ilmu.reserveCollection.cataloging.Bibliography.*"%> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/reserve/reserve.js"></script>
 
  <div class="modal-header">
     <button type="button" class="close closePatrModal" aria-label="Close">
   	 <span aria-hidden="true">&times;</span></button>
           <h4 class="modal-title">Search Criteria</h4>
  </div>
<div class="modal-body" style="height: 190px;">
	<div class="padding-15" id="searchCriteria">
			<div class="col-md-12">
				<div class="col-md-3">
					<div class="form-group ">
						<div class="radio">
							<label><input type="radio" name="retrieveRadios"
								id="optradio" value="unprint" checked="checked">Course Code </label>
<!-- 							<label><input type="radio" name="retrieveRadios"
								id="optradio" value="unprint" checked="checked">Subject </label> -->
							<label><input type="radio" name="retrieveRadios"
								id="optradio" value="patron">Semester Code </label>
							<label><input type="radio" name="retrieveRadios"
								id="optradio" value="patron">Instructor Id </label>
							<label><input type="radio" name="retrieveRadios"
								id="optradio" value="patron">Control No </label>
						</div>
					</div>
				</div>
				<div class="col-md-3" style="padding-right: 0px;padding-left: 0px;" >
					<input type="text" name="inputCriteria" id="inputCriteria" style="height: 136px;">
				</div>
				<div class="col-md-6">
					<button type="button" class="btn btn-default" id="findBtn">
						<span aria-hidden="true"> <i class="fa fa-check"></i> Find
						</span>
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span aria-hidden="true"> <i class="fa fa-close"></i> Close
						</span>
					</button>
				</div>
			</div>
	</div>
</div>
	


<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Search Criteria 
</body>
</html> --%>