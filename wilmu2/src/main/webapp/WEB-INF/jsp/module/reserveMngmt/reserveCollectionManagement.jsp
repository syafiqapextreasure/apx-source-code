<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"rel="stylesheet"> -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/base/js/global.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/moment.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/reserveMngmt/js/validateReserveManagement.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/reserveMngmt/js/ColReorderWithResize.js"></script>

<style>
#RCtable {
	table-layout: fixed;
}

#RCtable tr td {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	color: black;
}

th {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	color: black;
}
</style>	
	
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="col-md-12" style="margin-top: 10px">
			<div class="col-md-2">
 					<div class="form-group ">
						<div class="radio">
							<label><input type="radio" name="selectedRadio"
								id="userDefineRB" checked="checked">User define</label>
						</div>
						<div class="radio">
							<label><input type="radio" name="selectedRadio"
								id="fetchListRB" >Fetch List</label>
						</div>
					</div> 
			</div>
			<div class='col-md-6'>
				<div class='col-md-12'>
					<div class='col-md-5'>
  						<div class="input-group date" id="startDate" style="left: -15;">
 							<input type="text" class="form-control" name="dateFrom" id="dateFrom"> 
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span> 						
						</div>  
					 	<!-- <input type="text" id="dateFrom"> -->
					</div>
					<div class="input-group"
						style="width: 5%; float: left; top: 5px; margin-top: -15;">
						&nbsp;&nbsp;&nbsp; <span> to </span>
					</div>
					<div class='col-md-5'>
 						<div class="input-group date" id="endDate">
							<input type="text" class="form-control" name="dateTo" id="dateTo">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-calendar"></i></span>
						</div>  
					 <!-- 	<input type="text" id="dateTo"> -->
					</div>
				</div>
				<div class="col-md-12" style="margin-right: -40; margin-top:10px;">
					<div class="input-group select">
						<select class="labelBlack form-control subjectId" id="fetchListDD"
							data-id="1" disabled
							style="border-style: solid; border-width: thin; padding-top: 2px; padding-bottom: 0px; top: 2px; bottom: 2px; height: 30px; left: 0px; width: 180px; border-bottom-width: 0px; border-top-width: 0px; padding-left: -; margin-left: -;"></select>
					</div>
				</div>
			</div>


			<div class="col-md-3">
				<div>
					<button type="button" class="btn btn-primary" id="newBtn" style="margin-bottom: 5px;">New</button>
				</div>
				<div>
					<button type="button" class="btn btn-primary" id="retrieveBtn"
					style="margin-bottom: 5px;" disabled="disabled">Retrieve</button>
				</div>
			</div>
		</div>

		<div class="col-md-12" style="margin-bottom: 5px;">
			<table class="table table-hover table-responsive" id="RCtable">
				<thead>
					<tr>
						<th data-sortable="true">Course</th>
						<th data-sortable="true">Semester</th>
						<th data-sortable="true">Subject</th>
						<th data-sortable="true">Instructor</th>
						<th data-sortable="true">Control No</th>
						<th data-sortable="true">Title</th>
						<th data-sortable="true">Start Date</th>
						<th data-sortable="true">End Date</th>
						<th data-sortable="true">Reserve No</th>
						<th data-sortable="true">Action</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>

	</div>
</body>
</html>


<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.min.css" rel="stylesheet" type="text/css" /> 

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">

<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script> 

<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/reserveCollectionManagement/ColReorderWithResize.js"></script>
	
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>

<style>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/reserveCollectionManagement/validateReserveCollectionManagement.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/general/global.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugin/moment.min.js"></script>
	
	
#RCtable {
	table-layout: fixed;
}

#RCtable tr td {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	color: black;
}

th {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	color: black;
}
</style>

<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="col-md-12" style="margin-top: 10px">
			<div class="col-md-2">
				<div class="form-group ">
					<div class="radio">
						<label><input type="radio" name="selectedRadio"
							id="userDefineRB" checked="checked">User define</label>
					</div>
					<div class="radio">
						<label><input type="radio" name="selectedRadio"
							id="fetchListRB">Fetch List</label>
					</div>
				</div>
			</div>
			<div class='col-md-6'>
				<div class='col-md-12'>
					<div class='col-md-5'>
						<div class="input-group date" id="startDate" style="left: -15;">
							<input type="text" class="form-control" name="dateFrom"
								id="dateFrom"> <span class="input-group-addon"> <i
								class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>

				<!-- 		<input type="text" id="datepicker_start">  -->
					</div>
					<div class="input-group"
						style="width: 5%; float: left; top: 5px; margin-top: -15;">
						&nbsp;&nbsp;&nbsp; <span> to </span>
					</div>
					<div class='col-md-5'>
					<div class="input-group date" id="endDate">
							<input type="text" class="form-control" name="dateTo" id="dateTo">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-calendar"></i></span>
						</div>
					<!-- 	 <input type="text" id="datepicker_end">  -->
					</div>
				</div>
				<div class="col-md-12" style="margin-right: -40; margin-top: 10px;">
					<div class="input-group select">
						<select class="labelBlack form-control subjectId" id="fetchListDD"
							data-id="1" disabled
							style="border-style: solid; border-width: thin; padding-top: 2px; padding-bottom: 0px; top: 2px; bottom: 2px; height: 30px; left: 0px; width: 180px; border-bottom-width: 0px; border-top-width: 0px; padding-left: -; margin-left: -;"></select>
					</div>
				</div>
			</div>


			<div class="col-md-3">
				<div>
					<button type="button" class="btn btn-primary" id="newBtn"
						style="margin-bottom: 5px;">New</button>
				</div>
				<div>
					<button type="button" class="btn btn-primary" id="retrieveBtn"
						style="margin-bottom: 5px;" disabled="disabled">Retrieve</button>
				</div>
				<div>
					<button type="button" class="btn btn-primary" id="closeBtn">Close</button>
				</div>
			</div>
		</div>

		<div class="col-md-12" style="margin-bottom: 5px;">
			<table class="table table-hover table-responsive" id="RCtable">
				<thead>
					<tr>
						<th data-sortable="true">Course</th>
						<th data-sortable="true">Semester</th>
						<th data-sortable="true">Subject</th>
						<th data-sortable="true">Instructor</th>
						<th data-sortable="true">Control No.</th>
						<th data-sortable="true">Title</th>
						<th data-sortable="true">Start Date</th>
						<th data-sortable="true">End Date</th>
						<th data-sortable="true">Reserve No</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>

	</div>
</body>
</html> --%>