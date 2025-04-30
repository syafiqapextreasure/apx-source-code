$(document).ready(function() {
	
	$("#Fund").prop("selectedIndex",-1);
	$("#fundReterive").prop('disabled', true);
	
	$('#Fund_table').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	$("#Fund").change(function() {
		$("#fundReterive").prop('disabled', false);
		$( ".AllocationVal, .IncreaseVal, .cTotalAlloc, .CommitmentVal").empty();
		$( ".PaymentVal, .cBalAlloc").empty();
	});
	
	function addCommas(num) {
	    var str = num.toString().split('.');
	    if (str[0].length >= 4) {
	        //add comma every 3 digits befor decimal
	        str[0] = str[0].replace(/(\d)(?=(\d{3})+$)/g, '$1,');
	    }
	  
	    return str.join('.');
	}
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn FUND Reterive//////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#fundReterive').click(function(){
		$( ".AllocationVal, .IncreaseVal, .cTotalAlloc, .CommitmentVal").empty();
		$( ".PaymentVal, .cBalAlloc").empty();
		
		var fund = $("#Fund").val();
		
		$.get('GetFundDeatail', {
			fund : fund,
		 	}, function(responseJson) {
			if(responseJson != null){
				//alert("freq " +value['sFrequency']);
				$.each(responseJson, function(key,value) {

					$(".AllocationVal").text(addCommas(value['Allo']));
					$(".IncreaseVal").text(addCommas(value['Incr']));
					$(".DecreaseVal").text(addCommas(value['Decr']));
					$(".cTotalAlloc").text(addCommas(value['TotalAlloc']));
					$(".CommitmentVal").text(addCommas(value['Comm']));
					$(".PaymentVal").text(addCommas(value['Pay']));
					$(".cBalAlloc").text(addCommas(value['BalAlloc']));
				});
			}
		});
		
	});
	
	
	//****************************************** AREA FOR MODAL *****************************************//
	$('#modalFund').on('show.bs.modal', function (event) {
		
		var fund = $("#Fund").val();
		
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');

		modal.find('.modal-title').text(recipient + fund);
		
		$('#Fund_table').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//info: false,
		    ajax: {
		    	url: "resultFundSpecifc",
		        data : {fund : fund, rowid : rowid},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){
		            	
		              return_data.push({
		            	'Trx#' : json[i].Trx,
		            	'Date': json[i].Date,
		                'Trx Code': json[i].TrxCode,
		                'Increase' : json[i].Incr, 
		                'Decreae' : json[i].Decr,
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
		     	    {'data': 'Trx#'},
					{'data': 'Date', className: "text-center"},
					{'data': 'Trx Code', className: "text-center"},
					{'data': 'Increase', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': 'Decreae', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
				],
				 footerCallback: function ( row, data, start, end, display ) {
					 	
					 	var numFormat = $.fn.dataTable.render.number(',', '.', 2, '').display;
					 
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			            
			            total2 = api.column( 3 ).data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			            // Total over this page
			            pageTotal2 = api
			                .column( 3, { page: 'current'} )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			            // Update footer
			            /*$( api.column( 3 ).footer() ).html(
			               pageTotal2.toFixed(2) +' ('+ total2.toFixed(2) +')'
			            );*/
			            $( api.column( 3 ).footer() ).html(numFormat(total2.toFixed(2)));
			 
			            // Total over all pages
			            total = api
			                .column( 4 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			            // Total over this page
			            pageTotal = api
			                .column( 4, { page: 'current'} )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			            // Update footer
			            /*$( api.column( 4 ).footer() ).html(
			                pageTotal.toFixed(2) +' ('+ total.toFixed(2) +')'
			            );*/
			            $( api.column( 4 ).footer() ).html(numFormat(total.toFixed(2)));
			        }
		});
		
		
	});
});