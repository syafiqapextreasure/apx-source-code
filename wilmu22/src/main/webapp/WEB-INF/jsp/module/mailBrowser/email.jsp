
<%@ page contentType="text/html; charset=UTF-8"%>
<%-- <%@ include file="/WEB-INF/jsp/module/mailBrowser/Handler_DeleteEmail.jsp" %> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<!-- <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"> -->

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">

<script src="https://unpkg.com/html-to-formatted-text/dist/index.umd.min.js"></script>

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/moment.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/mailBrowser/email.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/mailBrowser/validateMailBrowser.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/mailBrowser/ColReorderWithResize.js"></script>

<style>

/* @media only print {
    body {
        display: none;
    }
}
 */
#Email_table {
	table-layout: fixed;
}

#Email_table tr td {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	color: black;
}

th {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>

</head>

<body>

	<div class="container" style="margin-left: 20.000;">

		<div class="col-md-9">
			<form class="form-horizontal" id="form-mail">
				<div class="form-group" style="margin-top: 6px;">
					<div class="col-md-4" style="top: 6px;"><strong>Retrieve all mail
						generated from:</strong></div>
					<div class='col-md-3'>
						<div class="input-group date" id="startDate" style="left: -15;">
							<input type="text" class="form-control" name="dateFrom"
								id="dateFrom"> <span class="input-group-addon"> <i
								class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>
					</div>
					<div class="input-group"
						style="width: 5%; float: left; left: -8; top: 5px;">
						&nbsp;&nbsp;&nbsp; <span> <label>to</label> </span>
					</div>
					<div class='col-md-3'>
						<div class="input-group date" id="endDate">
							<input type="text" class="form-control" name="dateTo" id="dateTo">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-calendar"></i></span>
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-4" style="margin-top: 6px;"><label>Receiver:</label></div>
					<div class="input-group select" style="width: 27%; float: left;">
						<select class="form-control" id="receiver">
							<option value="Any">Any</option>
							<option value="Patron">Patron</option>
							<option value="Vendor">Vendor(Mail No marked with*)</option>
							<option value="Unavailable">Unavailable</option>
						</select>
					</div>
					<div class="input-group" style="width: 25%; float: left;">
						<button type="button" class="btn btn-primary refresh"
							style="margin-left: 20px; background-color: #1ab394"><label>Refresh Subject</label></button>
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-4"><label>Retrive sent mail:</label></div>
					<div class="input-group" style="width: 25%; float: left;">
						<input type="checkbox" class="form-check-input" id="sentMail">
					</div>
				</div>
			</form>
		</div>

		<div class="col-md-3" style="margin-top: 6px;">
			<div class="form-row">
				<div class="form-group">
					<label> Total subjects: </label> <span id="countSubject"></span>
				</div>
				<div class="form-group">
					<label>Total messages for selected subject: </label> <span
						id="countMail"></span>
				</div>
				<!-- 				<div class="form-group">
					<label>Branch: </label> <span>GB CAMPUS</span>
				</div> -->
			</div>
		</div>

		<div class="col-md-12">
			<form class="form-horizontal" id="form-mail_1">
				<div class="form-group">
					<div class="col-md-3" style="margin-top: 6px;"><label>Select mail
						subject:</label></div>
					<div class="input-group select">
						<select class="form-control" id="subject" style="width: 350px;"></select>
					</div>
				</div>
			</form>
			<!-- <button class="btn btn-primary" id='printSlip' title="Print Slip" onclick="printSlip()"><span class="glyphicon glyphicon-print" style="color:white"></span></button>
			 -->
		</div>

		<div class="col-md-12">
			<div class="form-group">
				<div class="list-group email"></div>
				<div class="table-responsive" style="overflow-x: auto;">
					<table id="Email_table" class="table table-bordered table-striped"
						style="overflow-x: auto">
						<thead>
							<tr>
								<th class="rowNumber">No</th>
								<th>Mail No</th>
								<th>Sender ID</th>
								<th>Send To</th>
								<th>Email</th>
								<th>Sent Date</th>
								<th>Action</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>

	</div>


	<div class="modal fade" id="modalEmail" tabindex="-1" role="dialog"
		aria-labelledby="modal" aria-hidden="true" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog" role="document" style="width: 75%">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalName" align="center">View
						Sent Mail</h5>
					<button type="button" id="closeModal" class="close"
						data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form method="post" role="form" class="form-horizontal"
						id="formdataMonograph">
						<div class="form-group">
							<div class="col-md-4">Mail No:</div>
							<div class="col-md-8">
								<span id="modalView_mailNo"></span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-4">Sent No:</div>
							<div class="col-md-8">
								<span id="modalView_receiver"></span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-4">Ref:</div>
							<div class="col-md-8">
								<span id="modalView_subject"></span>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12" id="htmlContent" contenteditable="true">
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<textarea readonly class="form-control" rows="10"
										id="modalView_message"></textarea>
								</div>
							</div>
						</div>

						<!-- END TAB CONTENT -->
						<div class="modal-footer">

							<button type="button" id="print" class="btn btn-primary save">Print</button>

							<a id="saveAs" href="" download="mail.rtf" class="btn btn-info"
								role="button">Save AS</a>

							<button type="button" id="close" class="btn btn-default"
								data-dismiss="modal">Close</button>

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>