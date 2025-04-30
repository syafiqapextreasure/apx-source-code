$(document).ready(function() {
	
	///////////////////// Set Current Date  ///////////////////////////////////
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	
	///////////////////// Set text autocomplete off////////////////////////////
	$('input[type="text"]').attr('autocomplete', 'off');
	
	//////////////////////////////Clear All Modal /////////////////////////////
	$('#searchWeed').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	});
	
	///////////////////// Set Weeding_table  ////////////////////////////////
	$('#Weeding_table').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		columnDefs: [
				{
					"targets": [ 0 ],
					"visible": false,
					"searchable": false,
				}
		],
	});
	
	//////////////////////////////when search modal open///////////////////////
	$('#searchWeed').on('shown.bs.modal', function () {
		$("input").prop("disabled", false);
		$("input[type=radio]").attr('disabled', false);
		$("#input-criteriaWeed").focus();
		
		$("input[name=input-criteriaWeed]").show();
		$("input[name=input-criteriaWeed]").attr('maxlength','10');
		
		$("#datepickerSentDate").hide();
		$("input[name=input-criteriaWeed]").attr('maxlength','10');

		$('#endSentDate').val(moment(today).format("DD/MM/YYYY"));
		$('#input-sentDate, #endSentDate').datepicker({
			format: "dd/mm/yyyy",
			todayBtn: true,
			autoclose: true,
			todayHighlight: true,
		});
		
		$('input[name=searchSelectionWeed]').change(function(){
		    var value = $('input[name=searchSelectionWeed]:checked' ).val();
		    
		    $("input[name=input-criteriaWeed]").val('');
		    $("#datepickerSentDate").hide();
			$("input[name=input-criteriaWeed]").show();
		    
		    if(value == "weedDate"){
		    	$("#datepickerSentDate").show();
				$("input[name=input-criteriaWeed]").hide();
		    }else if(value == "accNo"){
		    	$("#datepickerSentDate").hide();
				$("input[name=input-criteriaWeed]").show();
		    	$("input[name=input-criteriaWeed]").attr('maxlength','10');
		    }else{
		    	$("#datepickerSentDate").hide();
				$("input[name=input-criteriaWeed]").show();
				$("input[name=input-criteriaWeed]").focus();
				$("input[name=input-criteriaWeed]").removeAttr("maxlength");
		    }
		});
	});
	
	//////////////////////////////when click search ///////////////////////////
	$('#search').click(function(){
		$('#Weeding_table').dataTable().fnClearTable();
		
		var input_criteria = $('#input-criteriaWeed').val();
		var search_type = $("input[name='searchSelectionWeed']:checked").val();
		var inputSentDate = moment($("#input-sentDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endinputSentDate = moment($("#endSentDate").val(), 'DD/MM/YYYY').format("YYYYMMDD"); 
		
		$('#Weeding_table').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//info: false,
			columnDefs: [
				{
					"targets": [ 0 ],
					"visible": false,
					"searchable": false,
				}
			],
		    ajax: {
		    	url: "resultSearchWeed",
		        data : {input_criteria : input_criteria, search_type : search_type, inputSentDate : inputSentDate,
		        	endinputSentDate : endinputSentDate},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){
		            	
		              return_data.push({
		            	'Trxno' : json[i].sTrxno,
		            	'No': (i+1),
		                'Accession No': json[i].sAccno,
		                'Title' : json[i].sTitle, 
		                'Weed Date' : json[i].sWDate,
		                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalWeed" data-whatever="View Weeding" title="View Weeding" data-rowid="'+json[i].sTrxno+'"><span class="glyphicon glyphicon-eye-open"></span></button>',
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
		     	    {'data': 'Trxno'},
					{'data': 'No'},
					{'data': 'Accession No', className: "text-center"},
					{'data': 'Title'},
					{'data': 'Weed Date', className: "text-center"},
					{'data': 'Action'},
				],
		});
		
		$("#closesearchWeed").click();  
	});
	
	//****************************************** AREA FOR MODAL *****************************************//
	$('#modalWeed').on('show.bs.modal', function (event) {
		
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');

		modal.find('.modal-title').text(recipient);
		
		$( ".lblAccession, .lblReason, .LblWeedDate, .lblStatus, .lblItemCat").empty();
		$( ".lblCondition, .lblLocation, .lblMaterialNo, .lblForeignCost, .lblLocalCost").empty();
		$( ".lblCircHit, .lblClaimHits, .lblLastActDate, .lblBorrowerID, .lblDateBorrowed").empty();
		$( ".lblDateDue, .lblTImeDue, .lblAuthor, .lblCallNo, .lblPublisher").empty();
		$( ".lblEdition, .lblCollation, .lblCurrencyCode").empty();
		$( "#txtTitle" ).val("");
		
		switch(recipient){
		case state = 'View Weeding':

  			$.get('GetViewWeed', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						$(".lblAccession").text(value['sAccno']);
						$("#txtTitle").val(value['sTitle']);
						$(".lblReason").text(value['sReason']);
						$(".LblWeedDate").text(value['sWDate']);
						$(".lblStatus").text(value['sStatus']);
						$(".lblItemCat").text(value['iCate']);
						$(".lblCondition").text(value['sCond']);
						$(".lblLocation").text(value['sLoca']);
						$(".lblCurrencyCode").text(value['currency']);
						$(".lblMaterialNo").text(value['ctrlno']);
						$(".lblForeignCost").text(value['fcost']);
						$(".lblLocalCost").text(value['lcost']);
						$(".lblCircHit").text(value['CircHit']);
						$(".lblClaimHits").text(value['ClaimHits']);
						$(".lblLastActDate").text(value['LastActDate']);
						$(".lblBorrowerID").text(value['sBorrowerID']);
						$(".lblDateBorrowed").text(value['sDateBorrowed']);
						$(".lblDateDue").text(value['sDateDue']);
						$(".lblTImeDue").text(value['sTimeDue']);
						$(".lblAuthor").text(value['sAuthor']);
						$(".lblCallNo").text(value['sCallNo']);
						$(".lblPublisher").text(value['sPublisher']);
						$(".lblEdition").text(value['sEdition']);
						$(".lblCollation").text(value['sCollation']);
					});
				}
			});
	  		break;
		}
		
	});
	
});