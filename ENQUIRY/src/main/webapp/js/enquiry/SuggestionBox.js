$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///////////////////// input-endDate set Current Date  ////////////////////
	
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	//////table setup
	$('#suggBox').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		columnDefs: [
						{
								width: "30%",
						    targets: 5
						 },
						 {
								width: "30%",
						    targets: 6
						 },
						 {
						 	targets: [ 1,4 ],
						    visible: false,
						    searchable: false
						 },
		            ],
	});
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn SuggBOX Reterive//////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#suggBoxReterive').click(function(){
		
		$('#suggBox').dataTable().fnClearTable();
		var startSentDate = $("#input-startDate").val()
		if(startSentDate == ""){
			startSentDate = null;
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		}
	 
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		
		$('#suggBox').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//info: false,
			columnDefs: [
			             {
			     			width: "30%",
			                targets: 5
			             },
			             {
				     		width: "30%",
				            targets: 6
				         },
				         {
				         	targets: [ 1,4 ],
				            visible: false,
				            searchable: false
				         },
			            ],
		    ajax: {
		    	url: "resultsuggBox",
		    	data : {startSentDate : startSentDate, endSentDate : endSentDate},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'Patron ID' : json[i].patr, 
		                'Patron' : json[i].patrName, 
		                'Date' : json[i].date,
		                'Time' : json[i].time,
		                'Suggestion' : json[i].suggestion,
		                'Reply Status' : json[i].replyStatus,
		                'Action' : '<button id="Reply" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalReply" data-whatever="Reply Suggestion" title="Reply" data-rowid="'+json[i].patr+'" data-rowid2="'+json[i].date+'" data-rowid3="'+json[i].time+'"><span class="glyphicon glyphicon-share-alt"></span></button> <button id="Delete" class="btn btn-dull btn-xs DeleteOrder" data-rowid="'+json[i].patr+'" data-rowid2="'+json[i].date+'" data-rowid3="'+json[i].time+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
		              })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Patron ID'},
					{'data': 'Patron'},
					{'data': 'Date'},
					{'data': 'Time'},
					{'data': 'Suggestion'},
					{'data': 'Reply Status'},
					{'data': 'Action'}
				],
		});
		
	});
	
	//****************************************** AREA FOR MODAL *****************************************//
	$('#modalReply').on('show.bs.modal', function (event) {
		
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');
		var rowid2 = button.data('rowid2');
		var rowid3 = button.data('rowid3');
		
		rowid2 = moment(rowid2, "DD/MM/YYYY").format('YYYYMMDD')

		modal.find('.modal-title').text(recipient);	
		
		switch(recipient){
		case state = 'Reply Suggestion':

  			$.get('GetReplySuggBox', {
	  			rowid : rowid,
	  			rowid2 : rowid2,
	  			rowid3 : rowid3,
	  			
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						$(".lblDate").text(value['date']);
						$(".lblTime").text(moment(value['time'], 'HHmmss').format('HH:mm:ss A'));
						$(".lblPatronID").text(value['patr']);
						$(".lblPatronName").text(value['patrName']);
						$("#txtSuggest").val(value['suggestion']);
						$("#txtReply").val(value['replyStatus']);
					});
				}
			});
	  		break;
		}
	});
	
	//***************************************AREA FOR DELETE ********************************************//
	$('#suggBox tbody').on( 'click', '#Delete', function () {
		
		var patrId = $(this).attr('data-rowid');
		var suggDate = $(this).attr('data-rowid2');
		var suggTime = $(this).attr('data-rowid3');
		
		suggDate = moment(suggDate, "DD/MM/YYYY").format('YYYYMMDD')
		//alert(deleteFundAccountChar);

		var index = $('#suggBox').DataTable().rows({ search: 'applied'})
		.nodes().to$().index($(this).closest('tr'));
		alert(index);
		
		swal({
			text: "Are you sure want to delete?",
			showCancelButton : true,
			confirmButtonColor : '#3085d6',
			cancelButtonColor : '#d33',
			confirmButtonText : 'Yes',
			cancelButtonText : 'No',
			confirmButtonClass : 'confirm-class',
			cancelButtonClass : 'cancel-class',
			closeOnConfirm : false,
			closeOnCancel : false
		}).then(
			function() {
				$.ajax({
					url : "Handler_SuggBox",
					data: { patrId: patrId,
							suggDate : suggDate,
							suggTime : suggTime},
						success : function(result) {
							var status = result.replace(/\s+/g, '');
							if (status == "ok") {
								messageBox("005","Deleted","@action");
								swal('Successfully Deleted!',
									'The record has been successfully removed.');
								$('.swal2-confirm').click(
									function() {
										$('#suggBox').DataTable().row(index).remove().draw();
										//location.reload();
								});
							}else{
	     						swal("Unsuccessfully");
	     					}
						}
					});
			}, function(dismiss) {
				if (dismiss == 'cancel') {
					swal('', 'Cancelled');
				}
		})
	});
	//***********************************END AREA FOR DELETE ********************************************//
	
	
});