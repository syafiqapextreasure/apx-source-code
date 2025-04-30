<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Circulation/Report/AARR0450.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading"></div>
			<div class="panel-body" id="display_cirAct">
				<form class="form-horizontal" name="cirActForm" id="cirActForm">
					<input type="hidden" class="patrSelection" value="m">
					<jsp:include page="../../../PatronID.jsp"></jsp:include>
					<div class="btn-group pull-right">
						<div class="col-md-1">
							<button type="button" id="Reterive" class="btn btn-primary"
								title="Retrieve">Retrieve</button>
						</div>
					</div>
				</form>

				<br> <br>
				<table id="reportTable" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th class="removeHeaderForTable">Items</th>
							<th style="text-align: right" class="removeHeaderForTable">Value</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<!-- <th>1</th> -->
							<th>Patron Id</th>
							<td style="text-align: left" id="patronId"></td>
						</tr>
						<tr>
							<!-- <th>1</th> -->
							<th>Patron Name</th>
							<td style="text-align: left" id="patronName"></td>
						</tr>
						<tr>
							<!-- <th>1</th> -->
							<th>Group Id</th>
							<td style="text-align: left" id="groupId"></td>
						</tr>
						<tr>
							<!-- <th>2</th> -->
							<th>Category</th>
							<td style="text-align: left" id="category"></td>
						</tr>
						<tr>
							<!-- <th>3</th> -->
							<th>Status</th>
							<td style="text-align: left" id="status"></td>
						</tr>
						<tr>
							<!-- <th>4</th> -->
							<th>Lost Item</th>
							<td style="text-align: left" id="lostItem"></td>
						</tr>
						<tr>
							<!-- <th>5</th> -->
							<th>Collection Fines</th>
							<td style="text-align: left" id="collectionFine"></td>
						</tr>
						<tr>
							<!-- <th>6</th> -->
							<th>No of Suspension</th>
							<td style="text-align: left" id="noOfSuspension"></td>
						</tr>
						<tr>
							<!-- <th>7</th> -->
							<th>Membership Date</th>
							<td style="text-align: left" id="membershipDate"></td>
						</tr>
						<tr>
							<!-- <th>8</th> -->
							<th>Expiry Date</th>
							<td style="text-align: left" id="expiryDate"></td>
						</tr>
						<tr>
							<!-- <th>9</th> -->
							<th>Borrow To Date</th>
							<td style="text-align: left" id="borrowToDate"></td>
						</tr>
						<tr>
							<!-- <th>10</th> -->
							<th>Borrow To Year</th>
							<td style="text-align: left" id="borrowToYear"></td>
						</tr>
						<tr>
							<!-- <th>11</th> -->
							<th>Last Borrow</th>
							<td style="text-align: left" id="lastBorrow"></td>
						</tr>
						<tr>
							<!-- <th>12</th> -->
							<th>Last Return</th>
							<td style="text-align: left" id="lastReturn"></td>
						</tr>
						<tr>
							<!-- <th>13</th> -->
							<th>Branch</th>
							<td style="text-align: left" id="branch"></td>
						</tr>
						<tr>
							<!-- <th>14</th> -->
							<th>Outstanding Fines (RM)</th>
							<td style="text-align: left" id="outstandingFines"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>