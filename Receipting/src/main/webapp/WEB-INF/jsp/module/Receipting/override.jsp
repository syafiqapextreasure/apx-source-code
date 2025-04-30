<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ page import="java.text.DateFormat,java.text.SimpleDateFormat,java.util.Calendar,java.util.Date, com.ilmu.receipting.TransactionType.*, com.ilmu.global.DateTime,java.util.List" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Receipting / Override</title>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/plugin/jquery-2.1.1.js"></script> --%>
 <script type="text/javascript" src="<%= request.getContextPath() %>/js/Receipting/Receipting.js"></script>
 <%
	String due2 = request.getParameter("due");
%>
<script>
var getDue2 = <%=due2%>;
//alert(getDue2 + "getDue2");
var strNum = getDue2.toString(); // convert number to string
var decimalIndex = strNum.indexOf('.'); // find decimal index

if (decimalIndex === -1) {
	  // if there is no decimal point, add '.00' to the end of the string
	  strNum += '.00';
	} else {
	  // if there is a decimal point, check how many decimal places there are
	  var decimalPlaces = strNum.length - decimalIndex - 1;
	  if (decimalPlaces === 1) {
	    // if there is only one decimal place, add a '0' to the end of the string
	    strNum += '0';
	  } else if (decimalPlaces === 0) {
	    // if there are no decimal places, add '.00' to the end of the string
	    strNum += '.00';
	  }
	  // if there are already two decimal places, do nothing
	}

	//alert(strNum + "ohh yeah"); // output: 2.50

function save(){
	var code = $("#Code").val();
	var trxn = $("#Trxn").val();
	var date = $("#Date").val();
	var amt = $("#AmountOver").val();
	var patrid = $("#PatronId").val();
	var docno = $("#docno").val();
	var reference = $("#Reference").val();
	const regex = /^(0\.[1-9]\d*|[1-9]\d{0,3}(\.\d+)?|10000)$/;
//	alert(code);
	$('#searchbyaccno tbody tr').each(function () {
		var currtrxn =  $(this).closest('tr').find('td:eq(1)').text();
	
		if(trxn==currtrxn){
			 $(this).closest('tr').remove();
		}
	});
	
	if(reference == ""){
		swal("", "Please fill in mandatory field", "");
		 $('#AmountOver').val(strNum);
	}else{
		if(amt==""){
			swal("", "Please fill in mandatory field", "");
			 $('#AmountOver').val(strNum);
		}else if(amt <= 0){
			swal("", "You are not permitted to enter the value 0.00.", "");
			 $('#AmountOver').val(strNum);
		}else if(!regex.test(amt)){
			swal("", "Numerical and not more than 3 decimal place", "");
			 $('#AmountOver').val(strNum);
		}else{
			 $.get("AddTransaction",{action:"override", amt:amt, reference:reference, code:code, trxn:trxn, date:date,
									patrid:patrid, docno:docno},function(data){
										
					if(data.trim()=="success"){
										$("#overrideModal").modal("hide");
			
										$.get("ListPendingPayment",{patronid:patrid},function(data){
											$("#pendingtable").html(data);
										})
										$.get("ListPaidPayment",{patronid:patrid},function(data){
											$("#transaction-paid").html(data);
										})
										swal("", "Successfully override", "");
					}
		}) 
		}
	}
}

function resetModal() {
	  $('#AmountOver').val(strNum);
	}


</script>
</head>
<body>
		<%
			String trxn = request.getParameter("trxn");
			String code = request.getParameter("code");
			String docno = request.getParameter("docno");
			String due = request.getParameter("due");
			//String due = request.getParameter("due");
			 Date date = new Date();
		     String strDateFormat = "dd/MM/yyyy";
		     DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		     String formattedDate= dateFormat.format(date);
			//System.out.println(due + "dalam overid skrg ni");
		%>
	<div receipting-override>
		<form id="receiptingOverrideForm" class="form-horizontal ">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<i class="glyphicon glyphicon-copy"></i> Override Transaction
			</div>

			<div class="modal-body">

				<div class="row modal-size" style="height:40%">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">


						<div class="form-group">
							<label class="col-sm-3 control-label">Transaction Code &nbsp;:</label>
							<div class="col-sm-6">
								<input type="hidden" value="<%=docno%>" id="docno">
 								<input class="form-control" id="Code" type="text" disabled 
									name="Code"
									value="<%=code%>">
<%-- 								<select field-input class="form-control mandatory" id=Code name="Code"
									has-accession="100,104,105">
									<option value="0">Please Select</option>
									<%
										List <TransactionTypeMaint> searchResult = TransactionTypeMaint.ListTranscType();
									
										String date1 = DateTime.getTodayDate();
										for(TransactionTypeMaint i: searchResult)
										{	
									%>
									
										<option value="<%=i.getGL38TXCD()%>"><%=i.getGL38TXCD()%>-<%=i.getGL38DESC()%></option>
									<%
										}
									%>
								</select> --%>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">Transaction Date &nbsp;:</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" id="Date" disabled
									value="<%=formattedDate%>">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">Updates Transaction
								Number &nbsp;:</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" id="Trxn" disabled
									value="<%=trxn%>">
							</div>
						</div>

						<div class="form-group">
							<label for="Amount" class="col-sm-3 control-label">
								Transaction Amount <span style="color:red">*</span>&nbsp;:
							</label>
							<div class="col-sm-3">
								<input field-input class="form-control numeric" type="text"
									id="AmountOver" name="AmountOver" pattern="^\d{0,4}(\.\d{1,2})?$" title="Three letter country code" value="<%=due%>">
							</div>
						</div>

						<div class="form-group">
							<label for="Reference" class="col-sm-3 control-label">
								Reference <span style="color:red">*</span>&nbsp;:</label>
							<div class="col-sm-6">
								<input field-input class="form-control" type="text"
									id="Reference" name="Reference">
							</div>
						</div>


					</div>
				</div>
			</div>

			<div class="modal-footer">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<!-- <button type="button" class="btn btn-info" onclick="save()"  data-dismiss="modal" data-backdrop="false">Save</button> -->
						<button type="button" class="btn btn-info" onclick="save()" data-backdrop="false">Save</button>
						<button type="button" class="btn btn-info" data-dismiss="modal" onclick="resetModal()">
							<span aria-hidden="true">Cancel</span>
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
