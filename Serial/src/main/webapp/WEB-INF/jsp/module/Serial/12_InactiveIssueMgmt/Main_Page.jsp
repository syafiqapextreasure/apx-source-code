<%@page import="com.ilmu.cataloging.Template_Maint.Template_Maintenance"%>
<%@page import="com.ilmu.cataloging.Template_Maint.Cataloging"%>
<%@page import="com.ilmu.global.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serial/Inactive_IssueMgmt.js"></script>					
			
	<script>
  	$(document).ready(function () {
  		
  		$("[title]").tooltip();
		
/* 		$(".checkAll").change(function () {
		    $("input:checkbox").prop('checked', $(this).prop("checked"));
		    if ($("input[class=spineChk]:checked").length > 0) {
				$("#resubmit").removeAttr("disabled");
				$("#btn_add").removeAttr("disabled");
			}else{
				$("#resubmit").attr("disabled", "disabled");
				$("#btn_add").attr("disabled", "disabled");
			}
		}); */
		
		$('#input-vendorCode').focusout(function(){
			$.get("SearchVendor",{action:"keyupVendor", criteria:$(this).val()},function(data){
				$("#divvendorName").text(data.trim());
			});
		});
	});
	</script>
	<script type="text/javascript">
            $(function () {
            	$("#orderDate1").datepicker({
            		format: 'dd/mm/yyyy'
            	});
            	$("#orderDate2").datepicker({
            		format: 'dd/mm/yyyy'
            	});
            	$( "#orderDate2" ).datepicker( "option", "dateFormat", "dd/mm/yyyy" );
            	   $('#orderDate2').datepicker('setDate', new Date());
            });
            
            function purgeRecord(){
            	alert("sdds");
            	var orderno = [];
            	var copyno = [];
            	var issueno = [];
            	swal({   
            		text: 'Are you sure to perform this deletion?',
            		showCancelButton: true,
            		confirmButtonColor: '#3085d6', 
            		cancelButtonColor: '#d33',
            		confirmButtonText: 'Yes',
            		cancelButtonText: 'No',
            		confirmButtonClass: 'confirm-class',
            		cancelButtonClass: 'cancel-class',
            		closeOnConfirm: false,
            		closeOnCancel: true 
            		}).then(function() {
            					$('#inactiveList').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
            	
            						orderno.push( $(this).closest('tr').find('td:eq(1)').text());
            						copyno.push( $(this).closest('tr').find('td:eq(3)').text());
            						issueno.push( $(this).closest('tr').find('td:eq(8)').text());
            				        //alert(dataArr);
            					 });
            					
            						$.get("Handler_DeleteRecord",{orderno:orderno, copyno:copyno, issueno:issueno, total:orderno.length},function(data){
            							if(data.trim()=="Successful"){
            								swal("", "Successfully removed", "");
            								retrieveRecord();
            								//swal("", "Reference No exist", "")
            								// alertMessage("", "", "116","", "");
            							}else{
            								
            								swal("", "Record fail to delete", "");
            							}
            						});
            		},function(dismiss) {

            		})
            }

        </script>

</head>
<body>
	
	<%
		String username = CurrentUser.getCurrUser();
	%>
	  <div class="panel-group" id="testing" style=" position: relative;overflow:hidden;">
        <div class="panel panel-default">
            	<div class="panel-body">
            	<form id="inactiveForm">
						<div class="form-group row">
							<label class="col-sm-2">
								Issue Removed Date From
							</label>
							<div class="col-sm-5 searchInput">
								<div class="input-group">
									<input type="text" class="form-control" maxlength="10" id="orderDate1" readonly="readonly"/>
									<span class="input-group-addon">to</span>
									<input type="text" class="form-control" maxlength="10" id="orderDate2" readonly="readonly"/>
								</div>
							</div>
						</div>
							<div class="form-group row">
							   <label class="col-sm-2">
							       <input type="checkbox" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="orderno">
							    	Order No
								     </label>	
						      <div class="col-sm-5 searchInput">
								<div class="input-group">
									<input type="text" class="form-control" maxlength="10" id="orderno1" />
									<span class="input-group-addon">to</span>
									<input type="text" class="form-control" maxlength="10" id="orderno2" />
								</div>
							</div>	    
							</div>
							<div class="form-group row">
						    <label class="col-sm-2">
					        <input type="checkbox" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="controlno">
						      	Control No
					      </label>	
					     <div class="col-sm-5 searchInput">
								<div class="input-group">
									<input type="text" class="form-control" maxlength="10" id="controlno1" />
									<span class="input-group-addon">to</span>
									<input type="text" class="form-control" maxlength="10" id="controlno2" />
								</div>
							</div>	    
						</div>
					<div class="pull-right ">	
						<button type="button" class="btn btn-primary" id="btn_add" href="" data-placement="top" title="Retrieve" onclick="retrieveRecord()"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button>
						<button type="button" class="btn btn-primary" id="refresh" href="" data-placement="top" title="Refresh" onclick="refresh()"><span class="glyphicon glyphicon-refresh" style="color:white"></span></button>
						<button type="button" class="btn btn-primary" id="purge" data-placement="top" title="Purge" onclick="purgeRecord()" disabled><span class="glyphicon glyphicon-trash" style="color:white"></span></button>
					</div>
					</form>
					</div>	
			</div>
				<div class="panel panel-default">
             <div id="search" class="panel-collapse collapse in">
			<div class="panel-body" id="cancel_orderList">
				<table class="table table-bordered " id="inactiveList" style="font-size:10pt">
					<thead>
						<tr>
							<th><input type = "checkbox" class="checkAll" id = "checkAll"></th>
							<th data-sortable="true" style="text-align: center;">Order No.</th>
							<th data-sortable="true" style="text-align: center;">Control No.</th>
							<th data-sortable="true" style="text-align: center;">Copy</th>
							<th data-sortable="true" style="text-align: center;">Volume</th>
							<th data-sortable="true" style="text-align: center;">Issue Label</th>
							<th data-sortable="true" style="text-align: center;">Expected Date</th>
							<th data-sortable="true" style="text-align: center;">Removed Date</th>
							<th data-sortable="true" style='display:none'></th>
						</tr>
					</thead>
					 <tbody id="inactivedata">
		
													
					</tbody>
				</table>
			</div>
		</div>
        </div>
    </div>

</body>
    
</html>
            