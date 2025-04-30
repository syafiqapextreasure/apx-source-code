<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.ilmu.receipting.TransactionType.*, com.ilmu.global.DateTime,java.util.List" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Receipting / Miscellaneous</title>

</head>
<body>
	<div receipting-miscellaneous>
		<form id="receiptingMiscellaneousForm" class="form-horizontal ">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<i class="glyphicon glyphicon-plus-sign"></i> Miscellaneous
				Transaction
			</div>

			<div class="modal-body">

				<div class="row modal-size" style="height:40%">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">


						<div class="form-group">
							<label class="col-sm-3 control-label">Transaction Code &nbsp;<span class="red" style="color:red">*</span>:</label>
							<div class="col-sm-6">
								<select field-input class="form-control mandatory" id=Code name="Code"
									has-accession="100,104,105">
									<option value="0">Please Select</option>
									<%
										System.out.println("Count");
										List <TransactionTypeMaint> searchResult = TransactionTypeMaint.ListTranscType();
									
										String date = DateTime.getTodayDate();
										for(TransactionTypeMaint i: searchResult)
										{	
									%>
									
										<option value="<%=i.getGL38TXCD()%>"><%=i.getGL38TXCD()%>-<%=i.getGL38DESC()%></option>
									<%
										}
									%>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">Transaction Date &nbsp;:</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" disabled id="Date"
									value="<%=date%>">
							</div>
						</div>

						<div class="form-group">
							<label for="Amount" class="col-sm-3 control-label">
								Transaction Amount <span class="red" style="color:red">*</span>&nbsp;:
							</label>
							<div class="col-sm-3">
								<input field-input class="form-control numeric mandatory" type="text"
									id="MiscAmount" name="Amount">
							</div>
						</div>

						<div class="form-group">
							<label for="Reference" class="col-sm-3 control-label">
								Reference <span class="red"  style="color:red">*</span>&nbsp;:
							</label>
							<div class="col-sm-6">
								<input field-input class="form-control mandatory" type="text"
									id="Reference" name="Reference">
							</div>
						</div>

						<div class="form-group" group="AccessionNumber">
							<label for="AccessionNumber" class="col-sm-3 control-label">
								Accession Number &nbsp;:</label>
							<div class="col-sm-6">
								<input field-input class="form-control" type="text"
									id="AccessionNumber" name="AccessionNumber" maxlength="10">
							</div>
						</div>


					</div>
				</div>
			</div>

			<div class="modal-footer">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-info" onclick="addTransc()">Save</button>
						<button type="button" class="btn btn-info" data-dismiss="modal">
							<span aria-hidden="true">Cancel</span>
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
