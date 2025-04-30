$(document).ready(function() {
	
	$("#lblPatronID").focus();
	$("#lblPatronID").attr('maxlength','12');
	$("#refresh").prop('disabled', true);
	
	//////table setup
	var tablePatronHistory = $('#table-PatronHistory').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		bInfo : false,
		bPaginate: false,
	});
	
	//////////////////////////////////////////////////////////////////////////////////
	/////////////////////////PATRON TAB OR ENTER//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	function clearOrEmpty(){
		$(".lblName").empty();
		$(".lblMemberDate").empty();
		$(".lblExpiry").empty();
		$(".lblCategory").empty();
		$(".lblStatus").empty();
		$("#refresh").prop('disabled', true);
	}
	
	$("#lblPatronID").keydown(function(e){ 
		var PatronID = $("#lblPatronID").val();
		
		var code = e.keyCode || e.which;
		if(code == '9' ||code == '13' ){
			
			$.get('getLoadPatronDetail', {
	        	id : PatronID
			 	}, function(responseJson) {
				if(responseJson != null){
					if(responseJson==''){
						messageBox("036","",""); 
						$("#lblPatronID").val("");
						setTimeout(function(){
					        $('#lblPatronID')[0].focus();
					    }, 3000);
						clearOrEmpty();
					}else{
	
						$("#refresh").prop('disabled', false);
						
						$.each(responseJson, function(key,value) {
							$(".lblName").text(value['Name']);
							$(".lblMemberDate").text(value['MemberDate']);
							$(".lblExpiry").text(value['Expiry']);
							$(".lblCategory").text(value['Category']);
							$(".lblStatus").text(value['Status']);
						});

						$('#table-PatronHistory').DataTable( {
							destroy: true,
							searching: true,
							bLengthChange: false,
							autoWidth: false,
							responsive: true,
							processing: true,
							ordering: false,
							//order: [[ 1, 'asc' ], [ 0, 'asc' ]],
							//info: false,
						    ajax: {
						    	url: "ResultPatronHistory",
						    	data : {id : PatronID},
								//dataType: 'json',
						        type: "GET",
						        dataSrc: function (json) {
						            var return_data = new Array();

						            for(var i=0;i< json.length; i++){
						            	
						              return_data.push({
						            	'Accession No' : json[i].sAccNo,
						            	'Borrow Date/Time': json[i].sBorrowDate +" "+ json[i].sBorrowTime,
						                'Due Date/Time': json[i].sDueDate + " "+ json[i].sDueTime,
						                'Return Date/time' : json[i].sDateReturned + " "+json[i].sTimeReturned,
										'Status' : json[i].sStatus,
										'Item Status' : json[i].sItemStatus,
										'No of Renewal' : json[i].sRenewal,
										'Item Category' : json[i].sItemCat,
										'Charged Officer' : json[i].sChargingOfficer,
										'Discharged Officer' : json[i].sDischargingOfficer,
										'Title' : json[i].sTitle,
										'Renew Date/Time' : json[i].sRenewDate +" "+ json[i].sRenewTime,
										'Renew Officer' : json[i].sRenewOfficer,	
						              })
						            }
						            return return_data;
						          },
						     },//This is the end of the AJAX object from the example above
						     	columns    : [
						     	    {'data': 'Accession No'},
									{'data': 'Borrow Date/Time'},
									{'data': 'Due Date/Time'},
									{'data': 'Return Date/time'},
									{'data': 'Status'},
									{'data': 'Item Status'},	
									{'data': 'No of Renewal'},	
									{'data': 'Item Category'},	
									{'data': 'Charged Officer'},	
									{'data': 'Discharged Officer'},
									{'data': 'Title'},
									{'data': 'Renew Date/Time'},
									{'data': 'Renew Officer'},	
								],
						}); 
					}
				}
			});			
			event.preventDefault();	
		}else if(code == '8'){
			 clearOrEmpty();
		}
	});
	
	////////refersh
	$('#refresh').click(function(){		
		
		 $('#table-PatronHistory').dataTable().fnClearTable();
	
    	 $('#table-PatronHistory').DataTable().ajax.reload();
	});
		
	
	
});