<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ page language="java" 
contentType="text/html; 
charset=utf-8" 
pageEncoding="utf-8"
import="java.sql.*, com.ilmu.global.*"
%>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Release for Circulation</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Release_For_CI.js"></script>					
<script>

</script>
</head>
<body>

<div class="panel panel-default" id="form_parent">
		<div class="panel-heading">
			<b>Release For Circulation</b>
		</div>
		<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent" data-target="#searchDiv" id="searchTitle"></a>
					</h4>
				</div>
				<div id="searchDiv" class="panel-collapse collapse in">
					<div class="panel-body">
						
							<div class="container">
							<form role="form" class="form-horizontal" id="ReleaseForm" name="ReleaseForm">
							<table class="table table-hover table-condensed table-striped table-fixed" style="border-collapse:collapse;color:black;width:100%">
	
								<tr>
									<td>
										Accession No.:
									</td>
									<td>
										<input type="text" class="form-control format-vendorCode validate-char accMatNo" id="accMatNo" name="accMatNo" maxlength="10" onblur="duplicateValue()" onkeyup="duplicateValue()">
									</td>
									<td>
										<button type="button" class="btn btn-default selectPopup"  data-toggle="modal" data-target="#accModal" href="ListOfAcc">...</button>
										<span class="errorMessage" style="color:red"></span>
									</td>
									<td>
										 <button type="button" class="btn btn-primary" id="releaseBtn">
											Release
										</button>
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<td colspan="5">
										<span class="accTitle"></span>
									</td>
								</tr>
								<tr>
									<td><label><input type="checkbox" class="fastRelease" id="fastRelease" value="">&nbsp;Fast Release&nbsp;</label></td>
									<td>&nbsp;</td>
									<td><label><input type="checkbox" class="reserve" id="reserve" value="">&nbsp;Reserve Item On Release&nbsp;</label></td>
									<td>&nbsp;</td>
									<td><label><input type="checkbox" class="print" id="print" value="" disabled="disabled">&nbsp;Print Reservation Notice&nbsp;</label></td>
								</tr>
							</table>
						</form>
						<div style="max-height:100%;overflow-y:scroll">
								<table id="BibRcrd" class="table table-hover table-condensed table-striped table-fixed" data-url="data1.json" data-height="299" data-sort-name="name" data-sort-order="desc" style="border-collapse:collapse;color:black;width:100%">
									<tr>
										<th>
											Accession No.
										</th>
										<th>
											Status
										</th>
										<th>
											Title
										</th>
										<th>
											Call No.
										</th>
									</tr>
									<tbody id="releaseList">
										<!-- <td id="accNo"></td>
										<td id="status"></td>
										<td id="accTitl"></td>
										<td id="accCallNo"></td> -->
									</tbody>
								</table>
							
								</div>
						</div>
							<input type="hidden" id="accMatNo_value" name="accMatNo_value">
							<input type="hidden" id="accTitl_value" name="accTitl_value">
							<input type="hidden" id="accCallNo_value" name="accCallNo_value">
							<input type="hidden" id="accNo_value" name="accNo_value">				</div>
				</div>
			</div>

		</div>
	</div>
	
	<div class="modal lg fade" id="accModal" role= "dialog">
	<div class="modal-dialog"  style="width:70%">
      <div class="modal-content">
    		
        </div>

      </div>
    </div>

 
</body>
</html>