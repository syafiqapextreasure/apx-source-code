	<%@ page import="com.ilmu.cataloging.Acc_Maint.*, com.ilmu.foundation.*, java.util.List" %>
	
	<script>
	$(document).ready(function(){
		initDate();
		defaultDate();
		updateCurrency();
		
		$('input[type=radio][name=add-selection]').change(function() {
			//Compare radio button
	        if (this.value == 'foreignAmount') {
	        	disableAllInput();
	        	$("#foreign-amount").prop("readonly", false);
	        }
	        else if (this.value == 'localAmount') {
	        	disableAllInput();
	        	$("#local-amount").prop("readonly", false);
	        }
	    });
		
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
	
	function disableAllInput()
	{
		$("#foreign-amount").prop("readonly", true);
    	$("#local-amount").prop("readonly", true);
	}
	
	// Update note type placeholder
    function updateNoteTypePlaceholder() {
    	$("#note_no").attr("placeholder", $("#noteType option:selected").text());
    	$("#note_no").val("");
    }
    
  	// Update vendor name div with vendor name with the option to clear the current field
   	function updateVendorNameWithClear() {
   		// Clear the vendor name first
   		document.getElementById('div-vendorName').innerHTML = "";
   		updateVendorName();
   	}

   	// Update vendor name div with vendor name
   	function updateVendorName() {
   		// Do not perform search when input character length less than 3
   		if(document.getElementById('vendor').value.length < 3)
   			return;
   		
   		// Generate URL
   		var url = "../../../include/shared/GetVendorName.jsp?vendorCode=" + document.getElementById('vendor').value;
   		
   		// Execute AJAX Update
   		AJAXUpdate(url, 'div-vendorName');
   	}
    
 	// Update exchange rate with selected currency
   	function updateCurrency(){
		// Generate URL 
   		var url = "../../../include/shared_currency/CurrencyRate.jsp?currency=" + document.getElementById('currency').value;

   		// Execute AJAX Update
   		$.ajax({
   			// Title
   			url: url,
   			success: function(result) {
   				$("#exchangeRate").val(result);
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
   	var foreignAmount = document.getElementById('foreign-amount');
	var localAmount = document.getElementById('local-amount');
	var exchRate = document.getElementById('exchangeRate');
	
   	function updateLocalAmount(){
   		localAmount.value = (foreignAmount.value * exchRate.value).toFixed(2);
   	}
   	
   	function updateForeignAmount(){
   		foreignAmount.value = (localAmount.value / exchRate.value).toFixed(2);
   	}
   	
   	$('#btn-submit').click(function(){
   		var noteNo = $("#note_no").val();
   		var vendorCode = $("#vendor").val();
   		var foreignPrice = $("#foreign-amount").val();
   		var localPrice = $("#local-amount").val();
   		var currency = $('#currency').val();		
   		
   		var url = "Handler_AddNewRecord.jsp?noteType=" + $('#noteType').val() +
   				"&note_no=" + $('#note_no').val() +
   				"&vendor=" + $('#vendor').val() +
   				"&received_date=" + $('#received_date').val() +
   				"&currency=" + $('#currency').val() +
   				"&foreign-amount=" + $('#foreign-amount').val() +
   				"&local-amount=" + $('#local-amount').val() +
   				"&remark=" + $('#remark').val() ;
		
   		if(currency == "-" || noteNo == "" || vendorCode ==  "" || foreignPrice == "" || localPrice == "")
   			showPopupMsg("Please fill up the necessary details.","warning");
   		else
  		{
	   		$.ajax({
	   			url: url,
	   			success: function(result) {
	   				var isDuplicate = clearSpace(result);
	   				if(isDuplicate == "error"){
	   					showPopupMsg("Duplicate note number.","warning");
	   				}else{
	   					showPopupMsg("Insert successful.","success");
	   				}
	   			}
	   		});
  		}
   	});
	</script>
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Add New Record</h4>
	</div>
	<div class="modal-body">
		<div class="panel panel-default" id="form_parent">
			<div class="panel-group">
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#search_vendor" id="search">Search</a>
						</h4>
					</div>
					<div id="search_vendor" class="panel-collapse collapse in">
						<div class="panel-body">
							<form role="form" class="form-horizontal" id="current_form" name="current_form" onsubmit="return send_vendor_info()">
							
							<div class="form-group">
                				<label class="col-sm-3 control-label">Search By</label>
                				<div class="col-sm-5 col-md-5">
                				<select class="form-control" id="search-type" name="search-type" onchange="updatePlaceholder()">
  								<option value="select">Please Select</option>
 								<option value="patronId">Patron Id</option>
  								<option value="patronName">Name</option>
  								<option>New IC</option>
  								<option>Old IC</option>
								</select>
                				</div>
            				</div>
            				
            				<div class="clearfix"></div>
            				
            				<div class="form-group">
                				<label class="col-sm-3 control-label">Search Text</label>
                				<div class="col-sm-5 col-md-5">
                    				<input type="text" class="form-control" name="criteria" id="criteria" placeholder="Please Select">
                				</div>
            				</div>
            				
            				<div class="form-group">
                                <label class="col-sm-3 control-label">Filtered By</label>
                                <div class="col-sm-5 col-md-5">
                                     <select class="form-control" id="cate_type" name=" cate_type" onchange="getValue(this)">
                                     <%-- <option value="0">Please Select</option>
                                   <%
											SQLStatement cat = new SQLStatement();
											List<Foundation> tpllist = cat.getCategory();
											for (Foundation e : tpllist) {
										%>
										<option value="<%=e.getGL07CATE()%>"><%=e.getGL07DESC()%></option>
										<%
									}
								%> 
									 </select> --%>
                                </div>
                                <div class="col-sm-5 col-md-2">
                                      <input type="text" class="form-control" id="cate-id" name="cate-id"  readonly>
                                </div>
                                
                            </div>
                          <div class="form-group">
					<div class="col-sm-1"></div>
					<div class="col-sm-3"><label>Received Date</label></div>
					<div class="col-sm-3">
						<input class="datepicker form-control" type="text" id="received_date" name="received_date" readonly placeholder="Select Date" required>
					</div>
				</div>
				>
				</div>
						</div>
							<div class="form-group">
								<div class="col-sm-4 col-md-3"></div>
								<div class="col-sm-8 col-md-8">
								<button type="submit" class="btn btn-info" id="btn_submit" onclick="send()">
										<span class="glyphicon glyphicon-search"></span> Search
								</button>
								<input type="button" class="btn btn-default" style="width: 70px" value="Reset" onclick="resetForm()" id="btn-resetForm">
								<input type="button" name="cancel" value="Cancel" class="btn btn-default" data-dismiss="modal"/>
							</div>
							</div>
							</form>
						</div>
					</div>
				</div>
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#result_vendor" id="result">Result</a>
						</h4>
					</div>
					<div id="result_vendor" class="panel-collapse collapse">
						<div class="panel-body">
							<div id="display_vendor"  style="overflow:scroll; height:300px"></div>
						</div>
					</div>
				</div>
				
			</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-info" id="btn-submit">
			<span class="glyphicon glyphicon-save"></span> Save
		</button>
		<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
	</div>