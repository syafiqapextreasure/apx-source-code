$(document).ready(function() {
	
	$("#lblPatronID").focus();
	$("#lblPatronID").attr('maxlength','12');
	$("#ViewOnloan, #ViewReservation, #ViewOverdue").hide();
	$('#PatronStatusReserve').hide();
	$('input[name=req]').prop("disabled",true);
	
	$("input[name=req][value=acqreq]").prop('checked', true);
	
	//////table setup
	var tablePatrStatus = $('#PatronStatus').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		bInfo : false,
		bPaginate: false,
	});
	
	var tableRequestStatus = $('#RequestStatus').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		bInfo : false,
		bPaginate: false,
	});
	
	var tableRelated = $('#Related').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		bInfo : false,
		bPaginate: false,
	});
	
	var Miscellaneous = $('#Miscellaneous').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		bInfo : false,
		bPaginate: false,
	});
	
	//////////////////////////////////////////////////////////////////////////////////
	/////////////////////////PATRON KEY DOWN//////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	function clearOrEmpty(){
		$(".tblHeader").empty();
		$('#PatronStatus').dataTable().fnClearTable();
		$('#PatronStatusReserve').dataTable().fnClearTable();
		$('#RequestStatus').dataTable().fnClearTable();
		$('#Related').dataTable().fnClearTable();
		
		$("input[name=req][value=acqreq]").prop('checked', true);
		$('#PatronStatusReserve').hide();
		$('#PatronStatus').show();
		$('input[name=req]').prop("disabled",true);
		
		$(".lblName, #txtAddress, .lblDateEnrolled, .lblExpiryDate, .lblContactNo, .lblIPAddress").empty();
		
		////tab 1
		$("#ViewOnloan, #ViewReservation, #ViewOverdue").hide();
		$("label[for='tblHeader']").empty();
		/*$('#PatronStatus_info, #PatronStatus_paginate').hide();
		$('#PatronStatusReserve_info, #PatronStatusReserve_paginate').hide();*/

		////tab3
		$(".lblOnloanItems, .lblReservedItems, .lblOverdueItems, .lblItemsChargedTD, .lblItemsChargedYTD").empty();
		$(".lblLateReturnsTD, .lblLateReturnsYTD, .lblLastCircTrxDate, .lblDateLastReturned, .lblNumLostItems").empty();
		$(".lblFinesOutstanding, .lblNumSuspension, .lblGroupID, .lblDepartment, .lblStatus").empty();
		$(".lblCategory, .lblDOB, .lblGender, .lblFinesPaid").empty();
		
		$(".lblRelatedID, .lblRelatedName").empty();
	}
	
	$("#lblPatronID").keydown(function(e){ 
		var PatronID = $("#lblPatronID").val();
		
		var code = e.keyCode || e.which;
		if(code == '9' ||code == '13' ){
			
			$.get('getPatronDetail', {
	        	id : PatronID
			 	}, function(responseJson) {
				if(responseJson != null){
					if(responseJson==''){
						messageBox("036","",""); 
						$("#ViewOnloan, #ViewReservation, #ViewOverdue").hide();
						$("#lblPatronID").val("");
						setTimeout(function(){
					        $('#lblPatronID')[0].focus();
					    }, 3000);
						clearOrEmpty();
					}else{
						$('input[name=req]').prop("disabled",false);
						
						$.each(responseJson, function(key,value) {
							$("#ViewOnloan, #ViewReservation, #ViewOverdue").show();
							$(".lblName").text(value['Name']);
							$("#txtAddress").text(value['Address']);
							$(".lblDateEnrolled").text(value['DateEnrolled']);
							$(".lblExpiryDate").text(value['ExpiryDate']);
							$(".lblContactNo").text(value['ContactNo']);
							$(".lblIPAddress").text(value['IPAddress']);
							$(".lblOnloanItems").text(value['Onloan']);
							$(".lblReservedItems").text(value['Reserved']);
							$(".lblOverdueItems").text(value['Overdue']);
							$(".lblItemsChargedTD").text(value['ItemsChargedTD']);
							$(".lblItemsChargedYTD").text(value['ItemsChargedYTD']);
							$(".lblLateReturnsTD").text(value['LateReturnsTD']);
							$(".lblLateReturnsYTD").text(value['LateReturnsYTD']);
							$(".lblLastCircTrxDate").text(value['LastCircTrxDate']);
							$(".lblDateLastReturned").text(value['LastReturned']);
							$(".lblNumLostItems").text(value['LostItems']);
							$(".lblFinesOutstanding").text("RM " +value['outstanding']);
							$(".lblNumSuspension").text(value['Suspension']);
							$(".lblGroupID").text(value['GroupID']);
							$(".lblDepartment").text(value['Department']);
							$(".lblStatus").text(value['Status']);
							$(".lblCategory").text(value['Category']);
							$(".lblDOB").text(value['DOB']);
							$(".lblGender").text(value['Gender']);
							$(".lblFinesPaid").text("RM " +value['FinesPaid']);
							
							$(".lblRelatedID").text(value['RelatedID']);
							$(".lblRelatedName").text(value['RelatedName']);
						});
						
						////tab Request Status
						var reqStat = $('input[name=req]:checked').val();
						
						$('#RequestStatus').DataTable( {
							destroy: true,
							searching: false,
							bLengthChange: false,
							autoWidth: false,
							responsive: true,
							processing: true,
							ordering: false,
							//order: [[ 1, 'asc' ], [ 0, 'asc' ]],
							//info: false,
						    ajax: {
						    	url: "ResultDisplayRequestStatus",
						    	data : {id : PatronID, reqStat : reqStat},
						        type: "GET",
						        dataSrc: function (json) {
						            var return_data = new Array();

						            for(var i=0;i< json.length; i++){
						            	
						              return_data.push({
						            	'Date Request' : json[i].Col1,
						            	'Request No': json[i].Col2,
						                'Title': json[i].Col3,
						                'Request Status' : json[i].Col4,
						              })
						            }
						            return return_data;
						          },
						     },//This is the end of the AJAX object from the example above
						     	columns    : [
						     	    {'data': 'Date Request'},
									{'data': 'Request No'},
									{'data': 'Title'},
									{'data': 'Request Status'},
								],
						}); 
						
						//////related patron
						$('#Related').DataTable( {
							destroy: true,
							searching: false,
							bLengthChange: false,
							autoWidth: false,
							responsive: true,
							processing: true,
							ordering: false,
							//order: [[ 1, 'asc' ], [ 0, 'asc' ]],
							//info: false,
						    ajax: {
						    	url: "ResultDisplayRelated",
						    	data : {id : PatronID},
						        type: "GET",
						        dataSrc: function (json) {
						            var return_data = new Array();

						            for(var i=0;i< json.length; i++){
						            	
						              return_data.push({
						            	'No' : (i+1),
						            	'Patron ID': json[i].Col1,
						            	'Name': json[i].Col2,
						                'Status': json[i].Col3,
						                'Relation' : json[i].Col4,
						              })
						            }
						            return return_data;
						          },
						     },//This is the end of the AJAX object from the example above
						     	columns    : [
						     	    {'data': 'No'},
									{'data': 'Patron ID'},
									{'data': 'Name'},
									{'data': 'Status'},
									{'data': 'Relation'},
								],
						}); 
						
						
						//////Miscellaneous
						$('#Miscellaneous').DataTable( {
							destroy: true,
							searching: false,
							bLengthChange: false,
							autoWidth: false,
							responsive: true,
							processing: true,
							ordering: false,
							//order: [[ 1, 'asc' ], [ 0, 'asc' ]],
							//info: false,
						    ajax: {
						    	url: "ResultDisplayMiscellaneous",
						    	data : {id : PatronID},
						        type: "GET",
						        dataSrc: function (json) {
						            var return_data = new Array();

						            for(var i=0;i< json.length; i++){
						            	
						              return_data.push({
						            	'No' : (i+1),
						            	'Description': json[i].Description,
						            	'Value': json[i].Value,
						              })
						            }
						            return return_data;
						          },
						     },//This is the end of the AJAX object from the example above
						     	columns    : [
						     	    {'data': 'No'},
									{'data': 'Description'},
									{'data': 'Value'},
								],
						}); 
					}
				}
			});
			
			///Display Pic
			var a= "PhotoServlet?id="+PatronID;
			$.get(a,function(data){
				 if(data!=null && data!=""){
					 $("#imgtest3").attr("src", a);
				 }else{
					 var pathArray = window.location.pathname.split('/');
					 var newPathname = "";
					 newPathname += "/";
					 newPathname += pathArray[1];
					 $("#imgtest3").attr("src", newPathname+"/resources/image/user_default.png");
				 }
			});
			
			     
			
			event.preventDefault();	
		}else if(code == '8'){
			 var pathArray = window.location.pathname.split('/');
			 var newPathname = "";
			 newPathname += "/";
			 newPathname += pathArray[1];
			 $("#imgtest3").attr("src", newPathname+"/resources/image/user_default.png");
			 
			 clearOrEmpty();
			//$('#review').dataTable().fnClearTable();	
		}
	});
	
	////////View button click
	$('#ViewReservation').click(function(){
		
		$('#PatronStatus').hide();
		$('#PatronStatusReserve').show();
		
		$('#PatronStatus_info, #PatronStatus_paginate').hide();
		
		$("label[for='tblHeader']").text("Reservation Detail").css({"font-weight": "bold","text-decoration": "underline", "text-align" : "center", "font-size" : "x-large"});
		
		$('#PatronStatusReserve').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
		    ajax: {
		    	url: "resultDisplayReserved",
		    	data : {id : $("#lblPatronID").val()},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){

		              return_data.push({
		            	'Control No' : json[i].ControlNo,  
		                'Call No' : json[i].CallNo, 
		                'Title' : json[i].Title,
		                'Status' : json[i].Status,
		                'Date Reserved' : json[i].DateReserved,
		                
		              })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
		     	    {'data': 'Control No', className: "text-right"},
					{'data': 'Call No'},
					{'data': 'Title'},
					{'data': 'Status'},
					{'data': 'Date Reserved', className: "text-right"},

				],
			});
		});
	
	////////view button click
	$('#ViewOnloan, #ViewOverdue').click(function(e){
		$('#PatronStatus').show();
		$('#PatronStatusReserve').hide();
		
		$('#PatronStatusReserve_info, #PatronStatusReserve_paginate').hide();

		var getButtonIdClick = e.currentTarget.id;
		
		$("label[for='tblHeader']").text(getButtonIdClick).css({"font-weight": "bold","text-decoration": "underline", "text-align" : "center", "font-size" : "x-large"});
		
		$('#PatronStatus').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			ordering: false,
		    ajax: {
		    	url: "resultDisplayOnloanOverDue",
		    	data : {id : $("#lblPatronID").val(),
		    		button : getButtonIdClick},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){

		              return_data.push({
		            	  'Accession No' : json[i].AccessionNo,  
			              'Call No' : json[i].CallNo, 
			              'Title' : json[i].Title,
			              'Date Due' : json[i].DateDue,
			              'Time Due' : json[i].TimeDue,
			              'Control No' : json[i].ControlNo,
		                
		              })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
		     	    {'data': 'Accession No'},
					{'data': 'Call No'},
					{'data': 'Title'},
					{'data': 'Date Due'},
					{'data': 'Time Due'},
					{'data': 'Control No'},
				],
			});		
	});
	
	
	/////ONCHANGE
	////////////change radio button
	$('input[name=req]').change(function(){

		var reqStat = $('input[name=req]:checked').val();

		$('#RequestStatus').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			ordering: false,
			//order: [[ 1, "asc" ], [ 0, "desc" ]],
			//info: false,
		    ajax: {
		    	url: "ResultDisplayRequestStatus",
		    	data : {id : $("#lblPatronID").val(), reqStat : reqStat},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){
		            	
		              return_data.push({
		            	'Date Request' : json[i].Col1,
		            	'Request No': json[i].Col2,
		                'Title': json[i].Col3,
		                'Request Status' : json[i].Col4,
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
		     	    {'data': 'Date Request'},
					{'data': 'Request No'},
					{'data': 'Title'},
					{'data': 'Request Status'},
				],
		}); 
	});
});