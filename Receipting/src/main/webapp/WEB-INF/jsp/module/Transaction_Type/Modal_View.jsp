<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.ilmu.receipting.Receipting.*, java.util.List, com.ilmu.receipting.TransactionType.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Receipting / Transaction Type / ${operation}</title>

</head>
<body>
	<form id="transactionTypeForm" class="form-horizontal "
		validation-url="<spring:url value="/receipting/get-client-validation/transaction-type" />"
		submit-url="<spring:url value="/receipting/transaction-type-change/submit-ajax" />"
		operation="${operation}">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<i class="glyphicon glyphicon-pencil"></i> View Transaction
			Type
		</div>

		<div class="modal-body">

			<div class="row modal-size" modal-size>
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<%
					String code = request.getParameter("code");
					TransactionTypeMaint updateAccMaint = TransactionTypeMaint.GetTranscType(code); 
					%>

					<div class="form-group">
						<label for="Code" class="col-sm-2 control-label">Transaction
							Code <span class="red">*</span>
						</label>
						<div class="col-sm-4">
							<input type="text" value="<%=updateAccMaint.getGL38TXCD() %>" disabled>
						</div>
					</div>

					<div class="form-group">
						<label for="Description" class="col-sm-2 control-label">Description
							<span class="red">*</span>
						</label>
						<div class="col-sm-6">
							<input type="text" value="<%=updateAccMaint.getGL38DESC() %>" disabled>
						</div>
					</div>

					<div class="form-group">
						<label for="TransactionPriority" class="col-sm-2 control-label">Transaction
							Priority <span class="red">*</span>
						</label>
						<div class="col-sm-2">


							<c:choose>
								<c:when test="${operation.equals('View')}">
									<input class="form-control" type="text" disabled
										value="${transactionType.priority}">
								</c:when>
								<c:otherwise>
									<input field-input field-integer type="text"
										class="numeric form-control" id="Priority" name="Priority"
										placeholder="Transaction Priority"
										init-value="${transactionType.priority}"
										value="${transactionType.priority}">
								</c:otherwise>
							</c:choose>

						</div>
					</div>

					<div class="form-group">
						<div>
							<label for="PrintColumn" class="col-sm-2 control-label">Print
								Column</label>
							<div class="col-sm-4">
								<c:choose>
									<c:when test="${operation.equals('View')}">
										<input class="form-control" type="text" disabled
											value="${transactionType.printColumn}">
									</c:when>
									<c:otherwise>
										<input field-input field-integer type="text"
											class="numeric form-control" id="PrintColumn"
											name="PrintColumn" placeholder="Print Column"
											init-value="${transactionType.printColumn}"
											value="${transactionType.printColumn}">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div>
							<label for="GroupColumn" class="col-sm-2 control-label">Group
								Column</label>
							<div class="col-sm-4">
								<c:choose>
									<c:when test="${operation.equals('View')}">
										<input class="form-control" type="text" disabled
											value="${transactionType.groupColumn}">
									</c:when>
									<c:otherwise>
										<input field-input field-integer type="text"
											class="numeric form-control" id="GroupColumn"
											name="GroupColumn" placeholder="Group Column"
											init-value="${transactionType.groupColumn}"
											value="${transactionType.groupColumn}">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div>
							<label for="DebitAccountCode" class="col-sm-2 control-label">Debit
								Account Code</label>
							<div class="col-sm-4">
								<c:choose>
									<c:when test="${operation.equals('View')}">
										<input class="form-control" type="text" disabled
											value="${transactionType.debitAccountCode}">
									</c:when>
									<c:otherwise>
										<input field-input type="text" class="form-control"
											id="DebitAccountCode" name="DebitAccountCode"
											placeholder="Debit Account Code"
											init-value="${transactionType.debitAccountCode}"
											value="${transactionType.debitAccountCode}">
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<div>
							<label for="CreditAccountCode" class="col-sm-2 control-label">Credit
								Account Code</label>
							<div class="col-sm-4">
								<c:choose>
									<c:when test="${operation.equals('View')}">
										<input class="form-control" type="text" disabled
											value="${transactionType.creditAccountCode}">
									</c:when>
									<c:otherwise>
										<input field-input type="text" class="form-control"
											id="CreditAccountCode" name="CreditAccountCode"
											placeholder="Credit Account Code"
											init-value="${transactionType.creditAccountCode}"
											value="${transactionType.creditAccountCode}">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="TransactionMode" class="col-sm-2 control-label">Transaction
							Mode <span class="red">*</span>
						</label>
						<div class="col-sm-4">
							<c:choose>
								<c:when test="${operation.equals('View')}">
									<input class="form-control" type="text" disabled
										value="${transactionType.modeDescription}">
								</c:when>
								<c:otherwise>
									<select field-input class="form-control" id="Mode" name="Mode"
										init-value="${transactionType.mode}">
										<option value="C">Credit</option>
										<option value="D">Debit</option>
									</select>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="form-group">
						<label for="TransactionType" class="col-sm-2 control-label">Transaction
							Type <span class="red">*</span>
						</label>
						<div class="col-sm-4">
							<c:choose>
								<c:when test="${operation.equals('View')}">
									<input class="form-control" type="text" disabled
										value="${transactionType.typeDescription}">
								</c:when>
								<c:otherwise>
									<select field-input class="form-control" id="Type" name="Type"
										init-value="${transactionType.type}">
										<option value="D">Due</option>
										<option value="J">Journal</option>
										<option value="P">Payment</option>
										<option value="R">Receipt</option>
									</select>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="form-group">
						<label for="DefaultAmount" class="col-sm-2 control-label">Default
							Amount (RM)</label>
						<div class="col-sm-4">
							<c:choose>
								<c:when test="${operation.equals('View')}">
									<input class="form-control" type="text" disabled
										value="${transactionType.defaultAmount}">
								</c:when>
								<c:otherwise>
									<input field-input type="text" class="numeric form-control"
										id="DefaultAmount" name="DefaultAmount"
										placeholder="Default Amount (RM)"
										init-value="${transactionType.defaultAmount}"
										value="${transactionType.defaultAmount}">
								</c:otherwise>
							</c:choose>
						</div>

						<label for="CalculateGst" class="col-sm-2 control-label">Calculate
							GST</label>
						<div class="col-sm-4">
							<c:choose>
								<c:when test="${operation.equals('View')}">
									<div class="checkbox">
										<label> <input field-input type="checkbox" disabled
											id="CalculateGst" name="CalculateGst"
											checked="${transactionType.calculateGst}">
										</label>
									</div>
								</c:when>
								<c:otherwise>
									<div class="checkbox">
										<label> <input field-input type="checkbox"
											id="CalculateGst" name="CalculateGst"
											init-value="${transactionType.calculateGst}">
										</label>
									</div>
								</c:otherwise>
							</c:choose>

						</div>
					</div>

					<div class="form-group">
						<label for="DisplayFlag" class="col-sm-2 control-label">Show</label>
						<div class="col-sm-4">
							<c:choose>
								<c:when test="${operation.equals('View')}">

									<div class="checkbox">
										<label> <input field-input type="checkbox" disabled
											id="DisplayFlag" name="DisplayFlag"
											checked="${transactionType.displayFlag}"> Display
											Flag
										</label>
									</div>
								</c:when>
								<c:otherwise>
									<div class="checkbox">
										<label> <input field-input type="checkbox"
											id="DisplayFlag" name="DisplayFlag"
											init-value="${transactionType.displayFlag}"> Display
											Flag
										</label>
									</div>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${operation.equals('View')}">
									<div class="checkbox">
										<label> <input field-input type="checkbox" disabled
											id="ReceiptAppearance" name="ReceiptAppearance"
											checked="${transactionType.receiptAppearance}">
											Receipt Appearance
										</label>
									</div>
								</c:when>
								<c:otherwise>
									<div class="checkbox">
										<label> <input field-input type="checkbox"
											id="ReceiptAppearance" name="ReceiptAppearance"
											init-value="${transactionType.receiptAppearance}">
											Receipt Appearance
										</label>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="form-group">
						<label for="Branch" class="col-sm-2 control-label">Branch
							<i title="Impose the transaction income from this to.."
							class="fa fa-question-circle-o"></i> <span class="red">*</span>
						</label>
						<div class="col-sm-4">


							<c:choose>
								<c:when test="${operation.equals('View')}">
									<input class="form-control" type="text" disabled
										value="${transactionType.branchDescription}">
								</c:when>
								<c:otherwise>
									<select field-input class="form-control" id="Branch"
										name="Branch" init-value="${transactionType.branch}">
										<option value="I">Item Branch</option>
										<option value="P">Patron Branch</option>
										<option value="O">Officer Branch</option>
									</select>
								</c:otherwise>
							</c:choose>
						</div>
					</div>



				</div>
			</div>
		</div>

		<div class="modal-footer">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<c:choose>
						<c:when test="${operation.equals('View')}">
							<button type="button" class="btn btn-danger" data-dismiss="modal">
								<span aria-hidden="true">Close</span>
							</button>
						</c:when>
						<c:otherwise>

							<button type="submit" class="btn btn-primary">Submit</button>
							<button type="button" class="btn btn-danger" data-dismiss="modal">
								<span aria-hidden="true">Cancel</span>
							</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
