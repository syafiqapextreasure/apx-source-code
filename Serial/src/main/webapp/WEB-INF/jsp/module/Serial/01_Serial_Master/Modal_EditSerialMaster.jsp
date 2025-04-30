	<%@ page import="java.util.List, com.ilmu.serial.serial_master.*, com.ilmu.global.*" %>
	
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Serial/Serial_Master.js"></script>		
	<% Serial_Master currControlNo = Serial_Master.SE01_getDetailsByControlNo(request.getParameter("controlNo")); %>
	<% Serial_Master selectListCode = Serial_Master.SE01_getCodeByControlNo(request.getParameter("controlNo")); %>
	
	<style>
	.combine-row {
    	height:60px;
	}	
	</style>
	<script>
	$(document).ready(function(){
		init();
		
		
		//updateCurrency();
		
		//Validation double text field
		$('.validate-double').keypress(function(eve) {
			if ((eve.which != 46 || $(this).val().indexOf('.') != -1) && (eve.which < 48 || eve.which > 57) || (eve.which == 46 && $(this).caret().start == 0) ) {
				eve.preventDefault();
			}
			     
			// this part is when left part of number is deleted and leaves a . in the leftmost position. For example, 33.25, then 33 is deleted
			$('.filterme').keyup(function(eve) {
				if($(this).val().indexOf('.') == 0) {    
					$(this).val($(this).val().substring(1));
				}
			});
		});
	});
	
	// Update vendor name div with vendor name with the option to clear the current field
   	function updateVendorNameWithClear() {
   		// Clear the vendor name first
   		document.getElementById('div-vendorName').innerHTML = "";
   		updateVendorName();
   	}

   	// Update vendor name div with vendor name
   	function updateVendorName() {
   		var vendor_details = $(".CT03VEND").val();
   		$.get("SearchVendor",{criteria:vendor_details,action:"keyupVendor", type: "vendor"},function(data_vendor){

   			$(".div-vendorName").html(data_vendor)
   			//document.getElementById('div-vendorName').innerHTML = data_vendor;
   		   });	
   	}
	
   	function updateBinderNameWithClear() {
   		
   		document.getElementById('div-binderName').innerHTML = "";
   		updateBinderName();
   	}

   	function updateBinderName() {
   		var vendor_details = $(".binder").val();
   		$.get("SearchVendor",{criteria:vendor_details,action:"keyupVendor", type: "binder"},function(data_vendor){

   			$(".div-binderName").html(data_vendor)
   			//document.getElementById('div-vendorName').innerHTML = data_vendor;
   		   });	
   	}
   	
   	function updatePublisherNameWithClear() {
   		
   		document.getElementById('div-publisherName').innerHTML = "";
   		updatePublisherName();
   	}

   	function updatePublisherName() {
   		var vendor_details = $(".publisher").val();
   		$.get("SearchVendor",{criteria:vendor_details,action:"keyupVendor", type: "publisher"},function(data_vendor){

   			$(".div-publisherName").html(data_vendor)
   			//document.getElementById('div-vendorName').innerHTML = data_vendor;
   		   });	
   	}
   	
   	function updateRequestorNameWithClear() {
   		document.getElementById('div-requestorName').innerHTML = "";
   		updateRequestorName();
   	}

   	function updateRequestorName() {
   		if(document.getElementById('requestor').value.length < 3)
   			return;
   		
   		var url = "../../../include/shared/GetRequestorName.jsp?requestorCode=" + document.getElementById('requestor').value;
   		
   		AJAXUpdate(url, 'div-requestorName');
   	}
   	
   	function updateCurrency(){
   		
   		var url = "../../../include/shared_currency/CurrencyRate.jsp?currency=" + document.getElementById('currency').value;

   		$.ajax({
   			url: url,
   			success: function(result) {
   				$("#pubRate").val(result);
   			}
   		});
	}
	
 	// Mini jQuery plugin that formats to two decimal places
    (function($) {
        $.fn.decimalFormat = function() {
            this.each( function( i ) {
                $(this).change( function( e ){
                    if( isNaN( parseFloat( this.value ) ) ) return;
                    this.value = parseFloat(this.value).toFixed(2);
                });
            });
            return this;
        }
    })( jQuery );

    // Apply the currencyFormat behaviour to elements with 'decimal' as their class
    $( function() {
        $('.decimal').decimalFormat();
    });
   	
    // Calculation
   	var foreignPrice = document.getElementById('fPrice');
	var localPrice = document.getElementById('lPrice');
	var publisherRate = document.getElementById('pubRate');
	
   	function updateLocalPrice(){
   		localPrice.value = (foreignPrice.value * publisherRate.value).toFixed(2);
   	}
   	
   	function updateForeignPrice(){
   		foreignPrice.value = (localPrice.value / publisherRate.value).toFixed(2);
   	}
   	
   	function updateBoth(){
   		localPrice.value = (foreignPrice.value * publisherRate.value).toFixed(2);
   	}
   	
   	function init()
   	{
   		var currency = "<%out.print(currControlNo.getCurrency());%>";
   			AJAXUpdate("../component/Select_CurrencyList.jsp?selected="+currency, "currency");
   		var bibliographicSource = "<%out.print(selectListCode.getBibliographic());%>";
   			AJAXUpdate("../component/Select_BibliographicList.jsp?selected="+bibliographicSource, "bibliographicSource");
   		var smd = "<%out.print(selectListCode.getSMD());%>";
   			AJAXUpdate("../component/Select_SMDList.jsp?selected="+smd, "smd");
   		var language = "<%out.print(selectListCode.getLanguage());%>";
   			AJAXUpdate("../component/Select_LanguageList.jsp?selected="+language, "language");
   		var department = "<%out.print(selectListCode.getDepartment());%>";
   			AJAXUpdate("../component/Select_DepartmentList.jsp?selected="+department, "department");
   		var frequency = "<%out.print(selectListCode.getFrequency());%>";
   			AJAXUpdate("../component/Select_FrequencyList.jsp?selected="+frequency, "frequency");
   		var serialMode = "<%out.print(selectListCode.getSerialMode());%>";
   			AJAXUpdate("../component/Select_SerialModeList.jsp?selected="+serialMode, "serialMode");
   	}
   	
   	
   	$('#btn-update').click(function(){
		   	 var mandatory = $(".mandatory").val();
			 var selection = $('#currency :selected').val();
		
		    if (mandatory == ""||selection=="0") {
		    	swal("", "Please insert mandatory fields", "");
		    }else{
		    	var url = "Handler_EditPeriodicalsMaster.jsp?controlNo=" + $('#controlNo').val() +
				"&smd=" + $('#smd').val() +
				"&language=" + $('#language').val() +
				"&department=" + $('#department').val() +
				"&frequency=" + $('#frequency').val() +
				"&vendor=" + $('.vendor').val() +
				"&binder=" + $('.binder').val() +
				"&publisher=" + $('.publisher').val() +
				"&bibliographicSource=" + $('#bibliographicSource').val() +
				"&renewalAlert=" + $('#renewalAlert').val() +
				"&serialMode=" + $('#serialMode').val() +
				"&requestor=" + $('#requestor').val() +
				"&currency=" + $('.currency:selected').val() +
				"&pubRate=" + $('#pubRate').val() +
				"&fPrice=" + $('#fPrice').val() +
				"&lPrice=" + $('#lPrice').val() ;
	   		url += "&cumIndex=";
				if ($('#cumIndex').is(':checked')) {url += "Y"}else{url += "N"};
			url += "&contPage=";
				if ($('#contPage').is(':checked')) {url += "Y"}else{url += "N"};
			url += "&stndOrder=";
				if ($('#stndOrder').is(':checked')) {url += "Y"}else{url += "N"};
			url += "&bindTreatment=";
				if ($('#bindTreatment').is(':checked')) {url += "Y"}else{url += "N"};
			url += "&subjHeading=";
				if ($('#subjHeading').is(':checked')) {url += "Y"}else{url += "N"};
			url += "&irsIndexing=";
				if ($('#irsIndexing').is(':checked')) {url += "Y"}else{url += "N"};
			url += "&ttlPage=";
				if ($('#ttlPage').is(':checked')) {url += "Y"}else{url += "N"};
			url += "&routing=";
				if ($('#routing').is(':checked')) {url += "Y"}else{url += "N"};

		$.ajax({
			url: url,
			success: function(result) {
				var updateRecord = result.trim();
				if(updateRecord == "ok"){
					swal("", "Update successful.","");
					$('#editPeriodicalsMaster').modal('hide');
					showAll();
				}else{
					showPopupMsg("Please fill up the necessary details.","warning");
				}
			}
		});
		    }
   		
   	});
	</script>
		
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Edit Serial Master</h4>
	</div>
	<div class="modal-body">
		<div class="panel-body">
			<form role="form" class="form-horizontal">
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Control No <span style="color:red">*</span></label>
					</div>
					<div class="col-sm-3">
						<input type="text" class="form-control" readonly maxlength="10" id="controlNo" name="controlNo"
							value="<% if(currControlNo != null){out.println(currControlNo.getControlNo());} %>">
						<div class="clearfix"></div>
						<!-- <button type="button" class="btn btn-default" disabled>Bibliography</button> -->
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" disabled>...</button>  
					</div>
					<div class="col-sm-6">
						<div><% if(currControlNo != null){out.println(currControlNo.getTitle());} %></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>SMD</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" id="smd" name="smd">
						</select>
					</div>
					
					<div class="col-sm-2">
						<label>Currency <span style="color:red">*</span></label>
					</div>
					<div class="col-sm-4">
						<select class="form-control currency" id="currency" name="currency" required>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Language</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" id="language" name="language">
						</select>
					</div>
					
					<div class="col-sm-2">
						<label>Publisher Rate</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control validate-double CT03RATE" id="pubRate" name="pubRate" onkeyup="updateBoth()"
							value="<% if(currControlNo != null){out.println(currControlNo.getExchRate());} %>">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Department</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" id="department" name="department">
						</select>
					</div>
					
					<div class="col-sm-2">
						<label>Foreign Price</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control decimal validate-double foreignCost" id="fPrice" name="fPrice" onkeyup="updateLocalPrice()"
							value="<% if(currControlNo != null){out.println(Handler.format2Decimal(currControlNo.getFPrice()));} %>">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Frequency</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" id="frequency" name="frequency">
						</select>
					</div>
					
					<div class="col-sm-2">
						<label>Local Price <span style="color:red">*</span></label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control decimal validate-double localCost" id="lPrice" name="lPrice" onkeyup="updateForeignPrice()" required
							value="<% if(currControlNo != null){out.println(Handler.format2Decimal(currControlNo.getLPrice()));} %>">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Vendor <span style="color:red">*</span></label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control CT03VEND" id="vendor" name="vendor" required onblur="updateVendorName()"
							onkeyup="updateVendorNameWithClear()" value="<% if(currControlNo != null){out.println(currControlNo.getVendorCode());} %>">					
					</div>			  
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" id="btn_vendorSearch" data-toggle="modal" 
							data-target="#vendorSearch" href="../../../include/shared/SearchVendorModal.jsp">...</button>
					</div>
					<div class="col-sm-4">
						<div id="div-vendorName" class="div-vendorName"><% if(currControlNo != null){out.println(currControlNo.getVendorName());} %></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Binder</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control binder" id="binder" name="binder" onblur="updateBinderName()"
							onkeyup="updateBinderNameWithClear()" value="<% if(currControlNo.getBinder() == null){out.println("");}else{out.println(currControlNo.getBinder());} %>">				
					</div>			  
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" id="btn_binderSearch" data-toggle="modal" 
							data-target="#vendorSearch" href="Modal_Vendor?action=binder">...</button>
					</div>
					<div class="col-sm-4">
						<div class="div-binderName"><% if(currControlNo.getBinderName() == null){out.println("");}else{out.println(currControlNo.getBinderName());} %></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Publisher</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control publisher" id="publisher" name="publisher" onblur="updatePublisherName()"
							onkeyup="updatePublisherNameWithClear()" value="<% if(currControlNo.getPublisher() == null){out.println("");}else{out.println(currControlNo.getPublisher());}%>">						
					</div>			  
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" id="btn_publisherSearch" data-toggle="modal" 
							data-target="#publisherSearch" href="../../../include/shared/SearchPublisherModal.jsp">...</button>
					</div>
					<div class="col-sm-4">
						<div class="div-publisherName"><% if(currControlNo.getPublisherName() == null){out.println("");}else{out.println(currControlNo.getPublisherName());} %></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Bibliographic Source</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" id="bibliographicSource" name="bibliographicSource">
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Renewal Alert</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="renewalAlert" name="renewalAlert" maxlength="3"
							value="<% if(currControlNo.getRenewal() != null){out.println(currControlNo.getRenewal());} %>">
					</div>
					<div class="col-sm-2"></div>
					<div class="col-sm-6">
						<div class="combine-row">
							<fieldset class="amount-border">
								<div class="checkbox">
									<label style="width:150px;"><input type="checkbox" value="Y" id="cumIndex" class="checkboxValue"
										<% if(currControlNo.getCumIndx().equals("Y")){out.println("checked");} %>>Cumulative Index</label>
									<label><input type="checkbox" value="Y" id="contPage" class="checkboxValue"
										<% if(currControlNo.getContPg().equals("Y")){out.println("checked");} %>>Contents Page</label>
								</div>
								<div class="checkbox">
								  	<label style="width:150px;"><input type="checkbox" value="Y" id="stndOrder" class="checkboxValue"
								  		<% if(currControlNo.getStdOrd().equals("Y")){out.println("checked");} %>>Standing Order</label>
								  	<label><input type="checkbox" value="Y" id="bindTreatment" class="checkboxValue"
								  		<% if(currControlNo.getBindtr().equals("Y")){out.println("checked");} %>>Binding Treatment</label>
								</div>
								<div class="checkbox">
								  	<label style="width:150px;"><input type="checkbox" value="Y" id="subjHeading" class="checkboxValue"
								  		<% if(currControlNo.getSubjHead().equals("Y")){out.println("checked");} %>>Subject Heading</label>
								  	<label><input type="checkbox" value="Y" id="irsIndexing" class="checkboxValue"
								  		<% if(currControlNo.getIrsIdx().equals("Y")){out.println("checked");} %>>IRS Indexing</label>
								</div>
								<div class="checkbox">
								  	<label style="width:150px;"><input type="checkbox" value="Y" id="ttlPage" class="checkboxValue"
								  		<% if(currControlNo.getTitlPg().equals("Y")){out.println("checked");} %>>Title Page</label>
								  	<label><input type="checkbox" value="Y" id="routing" class="checkboxValue"
								  		<% if(currControlNo.getRoute().equals("Y")){out.println("checked");} %>>Routing</label>
								</div>
							</fieldset>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Serial Mode</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" id="serialMode" name="serialMode">
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Reference No</label>
					</div>
					<div class="col-sm-3">
						<input type="text" class="form-control" readonly
							value="<% if(currControlNo.getRefNo() == null){out.println("");}else{out.println(currControlNo.getRefNo());} %>">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Requestor</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="requestor" name="requestor" onblur="updateRequestorName()"
							onkeyup="updateRequestorNameWithClear()"
							value="<% if(currControlNo.getRequestor() == null){out.println("");}else{out.println(currControlNo.getRequestor());} %>">					
					</div>			  
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" id="btn_requestorSearch" data-toggle="modal" 
							data-target="#requestorSearch" href="../../../include/shared/SearchRequestorModal.jsp">...</button>
					</div>
					<div class="col-sm-4">
						<div id="div-requestorName"><% if(currControlNo.getRequestorName() == null){out.println("");}else{out.println(currControlNo.getRequestorName());} %></div>
					</div>
				</div>
				
			</form>
		</div>
	</div>
	
	<div class="modal-footer">
		<input type="button" value="Update" class="btn btn-info" id="btn-update"/>
		<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
	</div>