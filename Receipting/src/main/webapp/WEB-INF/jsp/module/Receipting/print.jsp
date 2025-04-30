<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="com.ilmu.receipting.Receipting.*, java.util.List, com.ilmu.global.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>W-ILMU / Receipting / Print Receipt</title>

<style>
@media print {
	@page {
		margin: 0;
	}
	body {
		margin: 0.5cm;
	}
	.no-print {
		display: none !important;
		height: 0;
	}
	.no-print, .no-print * {
		display: none !important;
		height: 0;
	}
}

@page {
	/*size: auto;*/
	margin: 0mm;
}

html {
	width: 350px;
}

body {
	font-size: 12px;
	font-family: sans-serif;
}

.ta-right {
	text-align: right;
}

.ta-center {
	text-align: center;
}

.padd-right-5 {
	padding-right: 5px;
}

.margin-top-15 {
	margin-top: 15px;
}

.margin-btm-15 {
	margin-bottom: 15px;
}

.float-left {
	float: left;
}

.float-right {
	float: right;
}

.clear {
	clear: both;
}

table td {
	font-weight: normal;
}

table.transaction-table {
	width: 100%;
	margin-top: 15px;
	margin-bottom: 15px;
	border-collapse: collapse;
}

table.transaction-table thead {
	border-top: 1px dashed #333;
	border-bottom: 1px dashed #333;
}

table.transaction-table thead tr th {
	text-align: left;
	padding: 5px;
	font-weight: normal;
}

table.transaction-table tfoot {
	border-top: 1px dashed #333;
	border-bottom: 1px dashed #333;
}

table.gst-table {
	width: 100%;
}

table.gst-table thead tr th {
	text-align: left;
	padding: 5px;
	font-weight: normal;
}
</style>

<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="ta-center padding-20 no-print">
		<button action="Print" title="Print" class="btn btn-primary" onclick="window.print();return false;" >
			Print <i class="glyphicon glyphicon-print"></i>
		</button>
	</div>

	<div class="padding-20">
		<div class="ta-center">
			<% 
			String patronid = request.getParameter("patronid");
			String receiptno = request.getParameter("receiptno");
			String patrname = PatronService.getPatronName(request.getParameter("patronid"));
			String offname =  PatronService.getPatronName("sysadmin");
			String patrmemdate = PatronService.getPatronExpDate(request.getParameter("patronid")); 
			List <ReceiptingService> searchResult = ReceiptingService.getContactsOnly();
			List <ReceiptingService> receipt = ReceiptingService.ListReceiptData(receiptno);
			
				for(ReceiptingService i: searchResult)
				{	

			%>
			<div class="ta-center"><%=i.getPatrHtel() %></div>
			<div class="ta-center"><%=i.getPatrName() %></div>
			<div class="ta-center"><%=i.getPatrCourse() %></div>
			<div class="ta-center"><%=i.getPatrAdd1() %></div>
			<div class="ta-center"><%=i.getPatrAdd2() %></div>
			<div class="ta-center"><%=i.getPatrAdd3() %></div>
			<%
					}
			%>
		</div>

		<div class="margin-btm-15 ta-center">
			<%-- <div class="ta-center">GST ID : ${libraryGstId}</div> --%>
			<div class="ta-center">PAYMENT RECEIPT</div>
		</div>

		<div class="margin-btm-15">

			<div class="float-left">Date: <%=DateTime.getTodayDate() %></div>
			<div class="float-right">Time: </div>
			<div class="clear"></div>
		</div>

		<div class="margin-btm-15">
			<div class="float-left">ID: <%=patronid %></div>
			
			<%
				if(request.getParameter("receiptno")==null){
			%>
			<div class="float-right">Receipt No.:  <%=request.getParameter("receiptno") %></div>
			<%
				}else{
			%>
			<div class="float-right">Receipt No.:  <%=request.getParameter("receiptno") %></div>
			<%
				}
			%>
			<div class="clear"></div>
		</div>

		<div><%=patrname %></div>

		<div>Membership Expiry Date: <%=patrmemdate %></div>

		<div>

			<table class="transaction-table">
				<thead>
					<tr>
						<th>CODE</th>
						<th>ITEM ACCESSION/TITLE</th>
						<th class="ta-right">AMOUNT</th>
					</tr>
				</thead>
				<tbody>
					<%	double total = 0.00;
						for(ReceiptingService i: receipt)
						{	
						total +=i.getReceiptAmount();
					%>
					<tr>
						<td> <%=i.getRE01CODE() %></td>
						<td> <%=i.getRE01DOCNO() %>/<%=i.getCT05SRAW() %></td>
						<td> <%=i.getReceiptAmount() %></td>
					</tr>
					<%
						}
					%>
					<%-- <c:forEach items="${transactionList}" var="item">
						<tr>
							<td>${item.code}</td>
							<td><c:choose>
									<c:when test="${item.reference != null}">
										${item.reference}
								</c:when>
								</c:choose> <c:choose>
									<c:when test="${item.accessionNumber != null}">
										${item.accessionNumber}
								</c:when>
								</c:choose> <c:choose>
									<c:when test="${item.title != null}">
										<span class="padd-right-5"></span>/<span class="padd-right-5"></span>${item.title}
								</c:when>
								</c:choose></td>
							<td class="ta-right">${item.amount}</td>
						</tr>
					</c:forEach> --%>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">Rounding Adjust</td>
						<td class="ta-right">0.00</td>
					</tr>
					<tr>
						<td colspan="2">Total</td>
						<td class="ta-right"><%=total %></td>
					</tr>
				</tfoot>
			</table>

		</div>

		<div class="margin-btm-15">
			<table>
				<tr>
					<td>Received</td>
					<td>: <%=total%></td>
				</tr>
				<tr>
					<td>Change</td>
					<td>: 0.00</td>
				</tr>
			</table>
		</div>

		<div class="margin-btm-15">
			<table>
				<tr>
					<td>Officer</td>
					<td>: <%=offname %></td>
				</tr>
				<tr>
					<td>Location</td>
					<td>: ${location}</td>
				</tr>
			</table>
		</div>

		<div class="margin-btm-15">
			<table class="gst-table">
				<thead>
					<tr>
					<!-- 	<th>GST Summary</th> -->
						<th>Amount(RM)</th>
						<!-- <th>Tax(RM)</th> -->
						<th>Total(RM)</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=total%></td>
						<td><%=total %></td>
					</tr>
				<%-- 	<c:forEach items="${gstList}" var="item">
						<tr>
							<td>${item.summary}</td>
							<td>${item.amount}</td>
							<td>${item.tax}</td>
							<td>${item.total}</td>
						</tr>
					</c:forEach> --%>

				</tbody>
			</table>
		</div>

		<div>THANK YOU</div>
	</div>

	<div class="ta-center padding-20 no-print">
		<button action="Print" title="Print" class="btn btn-primary">
			Print <i class="glyphicon glyphicon-print"></i>
		</button>
	</div>
</body>
</html>

