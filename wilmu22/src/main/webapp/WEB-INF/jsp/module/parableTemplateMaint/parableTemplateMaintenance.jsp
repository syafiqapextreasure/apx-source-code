<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
	
 <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/parableTemplateMaint/validateParableTemplateMaintenance.js"></script>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/moment.min.js"></script>

<title>Insert title here</title>

<style>

/* .hidden {display:none}   */
.attributeProperties_table{diplay:none}
/* #attrProperties {display:none;} */

#attributeProperties_table{
    display: none;
}

#parableTemplateMaintenance_table{
    display: none;
}

#styleTable{
    display: none;
}

#styleCommon_table{
    display: none;
}

#barcode_table{
    display: none;
}

#title_table{
    display: none;
}

#styleSheetLable_table{
    display: none;
}

#editSheet_table{
    display: none;
}

#editLabel_table{
    display: none;
}

#qrcode {
  width:160px;
  height:160px;
  margin-top:15px;
}

</style>

	<script>
		
		$(function(){
			$('#previewBtn').click(function(){
			 
				var news = $('#Barcode').length;
				var accNo = [];
				
			})
		})
		
	</script>
	
</head>
<body>

<div class="container" style="margin-left: 20.000;">
<!-- target="printForm" -->
 		<form method="post" id ="form1" action ="previewParableTempt" target="printForm">
				<div class="col-md-12">
					<div class="col-md-2">
						<div class="form-group ">
							<div style="margin-top: 10px;margin-left: 60px;">Branch</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group ">
							<div class="input-group select">
								<select
									style="padding-top: 2px; padding-bottom: 2px; top: 3px; bottom: 2px; height: 30px; left: 8px; width: 250px;"
									data-id="1" class="labelBlack form-control patronCategoryDesc"
									id="selectBranch" name="selectBranch"
									class="form-control nearSelcttxt"></select>
							</div>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group ">
							<div style="margin-top: 10px;">Select Template</div>
						</div>
					</div>
					<div class="col-md-3" style="padding-left: 0px;">
						<div class="form-group ">
							<div class="input-group select">
								<select
									style="padding-top: 2px; padding-bottom: 2px; top: 3px; bottom: 2px; height: 30px; left: 8px; width: 250px;"
									data-id="1" class="labelBlack form-control patronCategoryDesc"
									id="selectTemplate" name="selectTemplate" disabled="disabled"
									class="form-control nearSelcttxt"></select>
							</div>
						</div>
					</div>
				</div>
	 		</form>
		<div class="col-md-12">
			<div class="col-md-6">
				<div class="form-group">
						<div class="table-responsive" style="overflow-x: auto;">
							<table id="parableTemplateMaintenance_table" class="table table-bordered table-striped"
								style="overflow-x: auto">
								<thead>
									<tr>
										<th>SEQ No</th>
										<th>Attributes</th>
										<th>Action</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
		<form method="post" id ="form1" action ="previewParableTempt" target="printForm">		
				<div class="col-md-6">
 					<div class="form-group">
						<div class="table-responsive" style="overflow-x: auto;">
							<table id="styleTable" name="styleTable" class="table table-bordered table-striped"
								style="overflow-x: auto">
								<thead>
									<tr>
										<th>Style Name</th>
										<th>Value</th>
									</tr>
								</thead>
							</table>
						</div>
					</div> 
<!--   		 	<input type="text" name="inputValue" id="inputTest"> -->
<%--  					<div <%= request.getAttribute("styleTable")%>></div>  --%>
				</div>
 		</form>
				</div>
				
				<div class="col-md-12">
 					<div class="container">
								<span class="pull-right">
									<button type="button" id="loadAttributeBtn"
										class="btn btn-primary" style="background-color: #1ab394">Load Attribute</button>
   									<button type="button" id="previewBtn"
										class="btn btn-primary" style="background-color: #1ab394">Preview</button>
							<!-- 		<input type="submit" onclick="submitForms()" style="background-color: #1ab394"> -->
<!-- 			 	     <input type="submit" form="form1" class="btn btn-primary" style="background-color: #1ab394"> -->
 				 <!-- 			<input type="button" value="submit" id="submit2form"/> -->
									<button type="button" id="saveBtn"
										class="btn btn-primary" style="background-color: #1ab394">Save</button>	
			<!-- 						<button type="button" id="close" class="btn btn-primary"
										data-dismiss="modal" >Close</button> -->
								</span>
					</div> 
				</div>
			</div>


</body>


</html>

