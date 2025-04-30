<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@ page import="com.ilmu.foundation.global.Foundation"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<title>Library Calendar</title>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<!-- css content -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/fullcalendar/fullcalendar.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/lib-calendar.css">
	


</head>

<body>
	<!-- Header content -->
	<!-- <section class="content-header">
		<h1>
			<i class="glyphicon glyphicon-calendar"></i> Library Calendar
		</h1>
	</section> -->

	<!-- Main content -->
	<section class="content">
		<!-- row -->
		<div class="row">
			<!-- calendar function -->
			<div class="col-md-3">
				<!-- select branch box-->
				<div class="box box-default">
					<!-- box-header -->
					<div class="box-header with-border">
						<h3 class="box-title">Branch</h3>
					</div>
					<!-- /.box-header -->

					<!-- box-body -->
					<div class="box-body">
						<div class="btn-group">
							<select class="form-control" id="branch"
								onchange="document.getElementById('branchDesc').selectedIndex = document.getElementById('branch').selectedIndex">
								<%
									GlobalSQLStatement brnc = new GlobalSQLStatement();
									List<Foundation> brnccode = brnc.getBranch();
									for (Foundation d : brnccode) {
								%>
								<option value="<%=d.getGL71BRNC()%>"><%=d.getGL71BRNC()%></option>
								<%
									}
								%>
							</select>
						</div>
						<div class="btn-group">
							<select class="form-control" id="branchDesc"
								onchange="document.getElementById('branch').selectedIndex = document.getElementById('branchDesc').selectedIndex">
								<%
									GlobalSQLStatement brncdesc = new GlobalSQLStatement();
									List<Foundation> brnclist = brncdesc.getBranch();
									for (Foundation f : brnclist) {
								%>
								<option value="<%=f.getGL71BRNC()%>"><%=f.getGL71DESC()%></option>
								<%
									}
								%>
							</select>
						</div>
						 <div class="checkbox">
							<label for="apply-all"> <input type="checkbox"
								id="apply-all"> Apply to all branch
							</label>
						</div>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.select branch box -->

				<!-- Holiday button box -->
				<div class="box box-default">
					<!-- box-header -->
					<div class="box-header with-border">
						<h3 class="box-title">Holiday</h3>
					</div>
					<!-- /.box-header -->
					<!-- box-body -->
					<div class="box-body">
						<div id="external-events">
							<div class="external-event bg-green" data-type="1">State</div>
							<div class="external-event bg-yellow" data-type="2">Federal</div>
							<div class="external-event bg-aqua" data-type="3">Term
								Break</div>
						</div>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.Holiday button box -->

				<!-- Weekends button box -->
				<div class="box box-default">
					<!-- box-header -->
					<div class="box-header with-border">
						<h3 class="box-title">Weekends</h3>
					</div>
					<!-- /.box-header -->
					<!-- box-body -->
					<div class="box-body">
						<div id="external-events">
							<div class="external-event bg-red" data-type="4">Weekend</div>
						</div>
						<br />
						<div class="form-inline">
							<div class="form-group">
								<select id="weekend" class="form-control">
									<option value="1">Sunday</option>
									<option value="2">Monday</option>
									<option value="3">Tuesday</option>
									<option value="4">Wednesday</option>
									<option value="5">Thursday</option>
									<option value="6">Friday</option>
									<option value="7">Saturday</option>
								</select>
								<button id="add-weekend" type="button"
									class="btn btn-primary btn-flat">Add</button>
								<button id="clear-weekend" type="button"
									class="btn btn-primary btn-flat">Clear</button>
							</div>
						</div>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.Weekends button box -->

				<!-- Event button box -->
				<div class="box box-default">
					<!-- box-header -->
					<div class="box-header with-border">
						<h3 class="box-title">Activity</h3>
					</div>
					<!-- /.box-header -->
					<!-- box-body -->
					<div class="box-body">
						<div class="btn-group">
							<div class="box-body">
								<div class="external-events">
									<div id="external-events-listing"></div>
								</div>
							</div>
						</div>
						<div class="input-group">
							<input id="new-event" type="text" class="form-control"
								placeholder="Event Title">
							<div class="input-group-btn">
								<button id="add-new-event" type="button"
									class="btn btn-primary btn-flat">Add</button>
							</div>
						</div>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.Holiday button box -->
			</div>
			<!-- /.calendar function -->

			<!-- fullcalendar content -->
			<div class="col-md-9">
				<!-- box -->
				<div class="box box-primary">
					<!-- box-body -->
					<div class="box-body no-padding">
						<!-- THE CALENDAR -->
						<div id="calendar"></div>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /. box -->
			</div>
			<!-- /.fullcalendar content -->
		</div>
		<!-- /.row -->
	</section>

	<!-- create holiday dialog -->
	<div id="createHolModal" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 id="myModalLabel2">Create Holiday</h3>
				</div>
				<div class="modal-body">
					<div style="padding: 20px 20px;">
						<form id="createHolForm" class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="descrLabel">Description:</label>
								<div class="controls col-sm-9">
									<textarea class="form-control" id="descr" name="descr"></textarea>
									<input type="hidden" id="date" /> <input type="hidden"
										id="type" /> <input type="hidden" id="color" /><input
										type="hidden" id="abbr" /><input type="hidden" id="calDate" />
								</div>
							</div>
							<div class="form-group" id="forEndDate">
								<div class="col-sm-2 control-label enddate"><label>End Date</label></div>
								<div class='controls col-sm-4'>
			                    	<div class="input-group date" id="endDate" name="endDate">
			  							<input type="text" class="form-control" id="endDateSembreak" name="endDateSembreak" autocomplete="off">
			  						 		<span class="input-group-addon">
			  								<i class="glyphicon glyphicon-calendar"></i></span>
									</div>
			        			</div>
							</div>
							
							
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" id="submitCreateButton">Save</button>
					<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
					
				</div>
			</div>
		</div>
	</div>
	<!-- /.create holiday dialog -->

	<!-- edit holiday dialog -->
	<div id="editCalendarModal" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 id="myModalLabel2">Edit</h3>
				</div>
				<div class="modal-body">
					<div style="padding: 20px 20px;">
						<form id="editCalendarForm" class="form-horizontal">
							<div class="control-group">
								<label class="col-sm-2 control-label" for="descrLabel">Description:</label>
								<div class="controls col-sm-9">
									<textarea class="form-control" id="descr" name="descr"></textarea>
									<input type="hidden" id="date" /> <input type="hidden"
										id="type" /> <input type="hidden" id="branch" /><input
										type="hidden" id="eventId" />
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary"
						id="deleteCalendarButton">Delete</button>
					<button type="submit" class="btn btn-primary" id="submitEditButton">Save</button>
					<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
				</div>
			</div>
		</div>
	</div>
	<!-- /.create holiday dialog -->

	<!-- JavaScript content -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js"></script>
	<!-- fullCalendar 2.2.0 -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/fullcalendar/fullcalendar.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/lib-calendar.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
</body>
</html>