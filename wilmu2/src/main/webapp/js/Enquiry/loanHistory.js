$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	$('#txtControl').attr('maxlength', 10);
	$('#txtAccessionNo').attr('maxlength', 12);
	
	$('input:radio[name="radioSearchCateria"][value="ctrlNo"]').attr('checked',true);
	$("#txtAccessionNo").prop('disabled', true);
	
	//////table setup
	$('#loanHistoryTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	
	//////////////radio button on change
	$('input[type=radio][name=radioSearchCateria]').on('change', function() {
		switch ($(this).val()) {
		    case 'ctrlNo':
				$("#txtControl").prop('disabled', false);
				$("#txtAccessionNo").prop('disabled', true);
				$('.searchCtrlNo').prop('disabled', false);
				$('#loanHistoryTable').dataTable().fnClearTable();
				$("#txtTitle").empty();
				$("#txtAccessionNo, #txtControl").val('');
		      break;
		    case 'accno':
				$("#txtControl").prop('disabled', true);
				$("#txtAccessionNo").prop('disabled', false);
				$('.searchCtrlNo').prop('disabled', true);
				$('#loanHistoryTable').dataTable().fnClearTable();
				$("#txtTitle").empty();
				$("#txtAccessionNo, #txtControl").val('');
		      break;
		  }
	});
	
	///////tab or enter
	//control number
	$("#txtControl, #txtAccessionNo").keydown(function(e){ 
		var searchInput; 
		var type;
		
		var checked = $('input[name="radioSearchCateria"]:checked').val();
		
		switch (checked) {
		    case 'ctrlNo':
				type = "controlnumber";
				searchInput = $("#txtControl").val();
		      break;
		    case 'accno':
				type = "accessionnumber";
				searchInput = $("#txtAccessionNo").val();
		      break;
		}

		var code = e.keyCode || e.which;
		if(code == '9' ||code == '13' ){
			
			$.get('ResultLoanHistoryGetTitle', {
	        	searchInput : searchInput,
				type : type, ///accessionNumber
			 	}, function(responseJson) {
				if(responseJson != null){
					if(responseJson==''){
						$("#txtTitle").empty();
						$("#txtAccessionNo, #txtControl").val('');
						$('#loanHistoryTable').dataTable().fnClearTable();
						
							if(type == "controlnumber"){
								messageBox("020","",""); 
							}else if(type == "accessionnumber"){
								messageBox("126","",""); 
							}	
					}else{
						
						$.each(responseJson, function(key,value) {
							
							var title = value['sTitle'];
							
							if(title.charAt(0)=='*'){
								title = title.slice(1);
								$('#txtTitle').css('font-style', 'italic');
							}else{
								$('#txtTitle').css('font-style', 'normal');
							}
							
							
							if(type == "controlnumber"){
								$("#txtTitle").text(title);
							}else if(type == "accessionnumber"){
								$("#txtTitle").text(title);
								$("#txtAccessionNo").text(value['sControlNumber']);
							}	
							
							setTimeout(function(){	
								tableDispaly(searchInput, type); 
	    					}, 1000);
						});
						
						
					}
				}
			});

			
			event.preventDefault();	
		}else if(code == '8'){
			
			if(type == "controlnumber"){
				$("#txtTitle").empty();
				$("#txtAccessionNo, #txtControl").val('');
				$('#loanHistoryTable').dataTable().fnClearTable();
				$('input:radio[name="radioSearchCateria"][value="ctrlNo"]').attr('checked',true);
				$("#txtControl").prop('disabled', false);
				$("#txtAccessionNo").prop('disabled', true);
			}else if(type == "accessionnumber"){
				$("#txtTitle").empty();
				$("#txtAccessionNo, #txtControl").val('');
				$('#loanHistoryTable').dataTable().fnClearTable();
				$('input:radio[name="radioSearchCateria"][value="accno"]').attr('checked',true);
				$("#txtAccessionNo").prop('disabled', false);
				$("#txtControl").prop('disabled', true);
			}
			
			
		}
	});  
	
	
	///accessionnumber
	
	
	
	///////////////////////tabble display
	function tableDispaly(searchInput, type){
		
		if(type == "controlnumber"){
			$('#loanHistoryTable').DataTable().column( 1 ).visible( true );
		}else if(type == "accessionnumber"){
			$('#loanHistoryTable').DataTable().column( 1 ).visible( false );
		}
		
		
		
		var t = $('#loanHistoryTable').DataTable( {
					destroy: true,
					searching: false,
					bLengthChange: false,
					autoWidth: false,
					responsive: true,
					processing: true,
					//ordering: false,
						ajax: {
							url: "ResultLoanTable",
							data : {searchInput : searchInput,
								type : type
							},
							type: "GET",
							dataSrc: function (json) {
								var return_data = new Array();
				
									for(var i=0;i< json.length; i++){
										            	
										return_data.push({
											'No' : '',
											'Accession No': json[i].sAccno,
										    'Patron ID': json[i].sPatronName +" - "+json[i].sPatron,
										    'Borrow Date': json[i].sBorrow,
										    'Due Date': json[i].sDue,
										    'Return Date' : json[i].sReturn,
											'Charge Officer': json[i].sChargeOfficer,
										    'Discharge Officer' : json[i].sDischargeOfficer,
										})
									}
									return return_data;
							 },
						},//This is the end of the AJAX object from the example above
						columns    : [
							{'data': 'No'},
							{'data': 'Accession No'},
							{'data': 'Patron ID'},
							{'data': 'Borrow Date'},
							{'data': 'Due Date'},
							{'data': 'Return Date'},
							{'data': 'Charge Officer'},
							{'data': 'Discharge Officer'},
						],
				}); 
								
	t.on('order.dt search.dt', function () {
		t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
			cell.innerHTML = i+1;
			t.cell(cell).invalidate('dom');
		});
	}).draw(); 
		
	}
	
	
	
	
	
	
		
									
	
	
	                                   
	
	
});