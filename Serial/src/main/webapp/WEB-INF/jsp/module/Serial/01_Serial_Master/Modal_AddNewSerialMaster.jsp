	<%@ page contentType="text/html; charset=UTF-8" %>
	<%@ page import="java.util.List, com.ilmu.global.*, com.ilmu.serial.serial_master.*" %>
	
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Serial/Serial_Master.js"></script>		
	<script>
	$(document).ready(function(){
		init();
		updateCurrency();
		
		$("#requestor").blur(function(){
			alert("er");
		})
		
		//Validation interger text field
		$(".validate-number").keypress(function (e) {
			//if the letter is not digit then display error and don't type anything
			if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
				return false;
			}
		});
		
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
   		// Do not perform search when input character length less than 3
   		if(document.getElementById('vendor').value.length < 4)
   			return;
   		$.get("SearchVendor",{criteria:document.getElementById('vendor').value,action:"keyupVendor",module: "vendor"},function(data_vendor){
			document.getElementById('div-vendorName').innerHTML = data_vendor.trim();
	   });	
   	}
	
	function updateTitleNameWithClear() {
   		
   		document.getElementById('div-title').innerHTML = "";
   		updateTitleName();
   	}

   	function updateTitleName() {
   		if(document.getElementById('controlNo').value.length < 10)
   			return;
   		
   		var url = "../../../include/shared/GetTitleName.jsp?controlNo=" + document.getElementById('controlNo').value;
   		
   		AJAXUpdate(url, 'div-title');
   	}
   	
   	function updateBinderNameWithClear() {
   		
   		document.getElementById('div-binderName').innerHTML = "";
   		updateBinderName();
   	}
   	
   	function updatePublisherNameWithClear() {
   		
   		document.getElementById('div-publisherName').innerHTML = "";
   		updatePublisherName();
   	}
   	
	function updateRequestorNameWithClear() {
   		document.getElementById('div-requestorName').innerHTML = "";
   		updateRequestorName();
   	}

   /* 	function updateRequestorName() {
   	
   		if(document.getElementById('requestor').value.length < 1)
   			return;
   		$.get("SearchRequestor",{criteria:document.getElementById('requestor').value,search_type:"reqID",action:"blur"},function(data){
   			//alert(data.trim() + "dataaaa");
   			$("#div-requestorName").html(data);
			
	});	
   	} */
   	
   	function updateCurrency(){
   		if($( "#currency option:selected" ).val()=="0"){
   			$("#pubRate").val(0.00);
   		}else{
   		var pub = $( "#currency option:selected" ).data("pub");
   		$("#pubRate").val(pub);
   		}
   		updateLocalCost();
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
		AJAXUpdate("../component/Select_CurrencyList.jsp", "currency");
		AJAXUpdate("../component/Select_BibliographicList.jsp", "bibliographicSource");
		AJAXUpdate("../component/Select_SMDList.jsp", "smd");
		AJAXUpdate("../component/Select_LanguageList.jsp", "language");
		AJAXUpdate("../component/Select_DepartmentList.jsp", "department");
		AJAXUpdate("../component/Select_FrequencyList.jsp", "frequency");
		AJAXUpdate("../component/Select_SerialModeList.jsp", "serialMode");
   	}
   	
    function setValue(myVal) {
alert("errre");
        document.getElementById('ctrlNo').value = myVal;

    }

    
   	$('#BibRcrd').click(function(){
   		
   		//var host = "http://" + window.location.hostname + "/web/guest/receipt-maintenance";
		/*popup = window.open(host+"?patrid="+ GL14PATR, '');*/
		//popup = window.open(host + '?patrid='+ GL14PATR, '_blank')
/*    		var url = "http://dev.paradigm.com.my/web/guest/bibliographic-organization";
   		window.open(url+"?action=serialMstr",'_blank'); */
   		window.open("http://dev.paradigm.com.my/web/guest/bibliographic-organization?action=serialMstr","",
   		"width=550,height=170,left=150,top=200,toolbar=1,status=1,");
   		//alert( window.location.hostname);
   	});
	$('#btn-submit').click(function(){
		
		 var mandatory = $(".mandatory").val();
		 var selection = $('#currency :selected').val();
		 
		   if (mandatory == ""||selection=="0") {
		    	swal("", "Please insert mandatory fields", "");
		    }else{
		   		var controlNumber = $(".CT03MATNO").val();
		   		var vendorCode = $("#vendor").val();
		   		var publisherRate = $("#pubRate").val();
		   		var foreignPrice = $("#fPrice").val();
		   		var localPrice = $("#lPrice").val();
		   		var currency = $('#currency').val();
		   		
		   		var url = "Handler_AddNewSerialMaster?controlNo=" + $('.CT03MATNO').val() +
		   				"&smd=" + $('#smd').val() +
		   				"&language=" + $('#language').val() +
		   				"&department=" + $('#department').val() +
		   				"&frequency=" + $('#frequency').val() +
		   				"&vendor=" + $('#vendor').val() +
		   				"&binder=" + $('#binder').val() +
		   				"&publisher=" + $('#publisher').val() +
		   				"&bibliographicSource=" + $('#bibliographicSource').val() +
		   				//"&renewalAlert=" + $('#renewalAlert').val() +
		   				"&serialMode=" + $('#serialMode').val() +
		   				"&requestor=" + $('#requestor').val() +
		   				"&currency=" + $('#currency').val() +
		   				"&pubRate=" + $('#pubRate').val() +
		   				"&fPrice=" + $('#fPrice').val() +
		   				"&lPrice=" + $('#lPrice').val();
		   				
		   				url += "&renewalAlert=";
		   					if($('#renewalAlert').val() == "") {url += "0"}else{url += $('#renewalAlert').val()};	
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
		   					
		   					alert(url);
		   					if(currency == "-" || controlNumber == "" || vendorCode ==  "" || publisherRate == "" || foreignPrice == "" || localPrice == ""){
		   						swal('',"Please fill up the necessary details.","");
		   					}else
		   					{
					   			$.ajax({
					   	   			url: url,
					   	   			success: function(result) {
					   	   				var isDuplicate = result.trim();
					   	   				alert(isDuplicate);
					   	   				if(isDuplicate == "error"){
					   	   					swal("","Duplicate control number","");
					   	   					$("#addNewSerialMaster").modal("show");
					   	   					//showPopupMsg("Duplicate control number.","warning");
					   	   				}else{
					   	   					swal("","Added successfully","");
					   	   				$("#addNewSerialMaster").modal("hide");
					   	   					showAll();
					   	   				}
					   	   			}
					   	   		});		
		   					}
		    }
   	});


  /*  	function isControlNoExist(){
 
   		var controlNumber = $("#controlNo").val();
   		
   		var url = "Handler_AddNewSerialMaster?controlNo=" + $('#controlNo').val();
   		alert(url);
   		$.ajax({
			url: url,
			success: function(result) {
				var isDuplicate = result.trim();
				alert(isDuplicate);
				if(isDuplicate == "error"){
					swal('',"Duplicate control number.","");
				}
			}
	   	});
   	} */
	</script>
	<style>
	.combine-row {
    	height:60px;
	}	
	</style>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Add New Serial Master</h4>
	</div>
	<div class="modal-body">
		<div class="panel-body">
			<form role="form" class="form-horizontal" name="serialMstrform">
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Control No<span style="color:red">*</span></label>
					</div>
					<div class="col-sm-3">
						<input type="text" class="form-control validate-number format-controlNo mandatory CT03MATNO" name="controlNo" id="controlNo CT03MATNO" required maxlength="10"
							onblur="isControlNoExist()" onkeyup="updateTitleNameWithClear();">
						<div class="clearfix"></div>
						<!-- <button type="button" class="btn btn-default" disabled>Bibliography</button> -->
					</div>
					<div class="col-sm-2">
					   <div class="col-sm-1 col-md-1"><button type="button" class="btn btn-default selectPopup"  data-toggle="modal" data-target="#titleSearch" href="RetrieveTitleModal?action=8">...</button></div>
							<!-- <button type="button" class="btn btn-primary" id="BibRcrd" title="Bibliography"><i class="fa fa-book" aria-hidden="true"></i></button> -->
					</div>
					<div class="col-sm-5">
						<div id="div-title" class="title"></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>SMD</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" id="smd" name="smd">
								<option value="0">Please Select</option>
								<%
									List<Serial_Components> smd = Serial_Components.getListSMD();
									for (Serial_Components e : smd) {
										
								%>
									<option value="<%=e.getCode()%>"><%=e.getDesc()%></option>
								<%
									}
								%>
						</select>
					</div>
					
					<div class="col-sm-2">
						<label>Currency<span style="color:red">*</span></label>
					</div>
					<div class="col-sm-4">
						<select class="form-control mandatory currency" id="currency" name="currency" required>
								<option value="0">Please Select</option>
								<%
									List<Serial_Components> currency = Serial_Components.geCurrencytList();
									for (Serial_Components e : currency) {
								%>
									<option  value="<%=e.getCode()%>" data-pub="<%=e.getPubRate()%>" data-bank="<%=e.getBankRate()%>"><%=e.getDesc()%></option>
								<%
									}
								%>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Language</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" id="language" name="language" required>
								<option value="0">Please Select</option>
								<%
									List<Serial_Components> language = Serial_Components.getListLanguage();
									for (Serial_Components e : language) {
								%>
									<option value="<%=e.getCode()%>"><%=e.getDesc()%></option>
								<%
									}
								%>
						</select>
					</div>
					
					<div class="col-sm-2">
						<label>Publisher Rate</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control validate-double CT03RATE" id="pubRate" name="pubRate" onkeyup="updateBoth()">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Department</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" id="department" name="department">
								<option value="0">Please Select</option>
								<%
									List<Serial_Components> department = Serial_Components.getListDepartment();
									for (Serial_Components e : department) {
								%>
											<option value="<%=e.getCode()%>"><%=e.getDesc()%></option>
								<%
									}
								%>
						</select>
					</div>
					
					<div class="col-sm-2">
						<label>Foreign Price</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control decimal validate-double foreignCost" id="fPrice" name="fPrice" onkeyup="updateLocalPrice()">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Frequency</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" id="frequency" name="frequency">
								<option value="0">Please Select</option>
								<%
									List<Serial_Components> frequency = Serial_Components.getListFrequency();
									for (Serial_Components e : frequency) {
								%>
									<option value="<%=e.getCode()%>"><%=e.getDesc()%></option>
								<%
									}
								%>
						</select>
					</div>
					
					<div class="col-sm-2">
						<label>Local Price<span style="color:red">*</span></label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control decimal mandatory validate-double localCost" id="lPrice" name="lPrice" onkeyup="updateForeignPrice()" required>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Vendor<span style="color:red">*</span></label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control mandatory CT03VEND" id="vendor" name="vendor" onblur="updateVendorName()"
							onkeyup="updateVendorNameWithClear()" required>						
					</div>			  
					<div class="col-sm-1">
						<button type="button" class="btn btn-default selectPopup" data-toggle="modal" data-target="#vendorModal" href="Modal_Vendor?action=vendor" >...</button>
							<!-- <button type="button" class="btn btn-default" id="btn_binderSearch" data-toggle="modal" 
							data-target="#vendorSearch" href="Modal_Vendor?action=vendor">...</button> -->
					</div>
					<div class="col-sm-4">
						<div id="div-vendorName" class="div-vendorName"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label>Binder</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control binder" id="binder" name="binder" onblur="updateBinderName()"
							onkeyup="updateBinderNameWithClear()">						
					</div>			  
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" id="btn_binderSearch" data-toggle="modal" 
							data-target="#vendorModal" href="Modal_Vendor?action=binder">...</button>
					</div>
					<div class="col-sm-4">
						<div class="div-binderName"></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Publisher</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control publisher" id="publisher" name="publisher" onblur="updatePublisherName()"
							onkeyup="updatePublisherNameWithClear()">							
					</div>			  
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" id="btn_publisherSearch" data-toggle="modal" 
							data-target="#vendorModal" href="Modal_Vendor?action=publisher">...</button>
					</div>
					<div class="col-sm-4">
						<div class="div-publisherName"></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Bibliographic Source</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" id="bibliographicSource" name="bibliographicSource">
								<option value="0">Please Select</option>
								<%
									List<Serial_Components> bo = Serial_Components.getListBibliographicSource();
									for (Serial_Components e : bo) {
								%>
									<option value="<%=e.getCode()%>"><%=e.getDesc()%></option>
								<%
									}
								%>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label>Renewal Alert</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="renewalAlert" name="renewalAlert" maxlength="3">
					</div>
					<div class="col-sm-2"></div>
					<div class="col-sm-6">
						<div class="combine-row">
							<fieldset class="amount-border">
								<div class="checkbox">
									<label style="width:150px;"><input type="checkbox" value="Y" id="cumIndex" class="checkboxValue">Cumulative Index</label>
									<label><input type="checkbox" value="Y" id="contPage" class="checkboxValue">Contents Page</label>
								</div>
								<div class="checkbox">
								  	<label style="width:150px;"><input type="checkbox" value="Y" id="stndOrder" class="checkboxValue">Standing Order</label>
								  	<label><input type="checkbox" value="Y" id="bindTreatment" class="checkboxValue">Binding Treatment</label>
								</div>
								<div class="checkbox">
								  	<label style="width:150px;"><input type="checkbox" value="Y" id="subjHeading" class="checkboxValue">Subject Heading</label>
								  	<label><input type="checkbox" value="Y" id="irsIndexing" class="checkboxValue">IRS Indexing</label>
								</div>
								<div class="checkbox">
								  	<label style="width:150px;"><input type="checkbox" value="Y" id="ttlPage" class="checkboxValue">Title Page</label>
								  	<label><input type="checkbox" value="Y" id="routing" class="checkboxValue">Routing</label>
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
								<option value="0">Please Select</option>
								<%
									List<Serial_Components> serial = Serial_Components.getListSerialMode();
									for (Serial_Components e : serial) {
								%>
									<option value="<%=e.getCode()%>"><%=e.getDesc()%></option>
								<%
									}
								%>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Reference No</label>
					</div>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="referenceNo" readonly>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Requestor</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="requestor" name="requestor" onblur="updateRequestorName()"
							onkeyup="updateRequestorNameWithClear()">						
					</div>			  
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" id="btn_requestorSearch" data-toggle="modal" 
							data-target="#requestorSearch" href="SearchRequestorModal">...</button>
					</div>
					<div class="col-sm-4">
						<div id="div-requestorName"></div>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<div class="modal-footer">
	<button type="button" class="btn btn-info" id="btn-submit" >
			<span class="glyphicon glyphicon-save"></span> Add
		</button>
	<!-- <input type="button" name="addSerial" value="Add" class="btn btn-info" id="btn-submit"/> -->
		<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
	</div>
	