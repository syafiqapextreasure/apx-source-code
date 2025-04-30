$(document).ready(function(){
	
	 $("#Reterive").prop("disabled",true);
	 $("#inputreservid, #inputemailbooking").prop( "disabled", true );
	 $('#inputreservid').attr('maxlength', 12);
	 $('#inputemailbooking').attr('maxlength', 60);

	 //$("input[name=chkBoxSearchCateria][value=patrID]").prop('checked', true);
	 ///$('input:radio[name="rbRrcdSelection"]').prop('checked', true);
	
	/*$('.patronid').prop('disabled', true);
	$(".patronid").removeAttr('href');*/  
	

	$('#bilBayaranTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	
	///keyup
	$("#lblPatronID").on("keypress keyup blur",function (e) {

		

		var id = $("#lblPatronID").val();

		$(".lblName").empty();
		////display vendor name
		$.get('GetPatronName', {
        	id : id
		 	}, function(responseJson) {
			if(responseJson != null){



				$.each(responseJson, function(key,value) {
					
					
					var namelength = value['Name'].length;

					if(namelength > 0){
						$('#Reterive').prop('disabled', false);
					}else{
						$('#Reterive').prop('disabled', true);
					}

					$(".lblName").text(value['Name']);
					
				});

				
			}
		});
	});
	
	
	//clear paron id keydown backspace
	$("#lblPatronID").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#.lblName").empty();
			$('#Reterive').prop('disabled', true);
		}
		
		if(code == 13) {
		    e.preventDefault();
		    return false;
		 }
	});


	$('input[name=chkBoxSearchCateria]').change(function(){
		var value = $( 'input[name=chkBoxSearchCateria]:checked' ).val();

		
		if(value=='patrID'){
			alert("dfdgd");
			$("#inputreservid, #inputemailbooking").prop( "disabled", true );
			
		}else if(value=='bookingid'){
			$('#Reterive').prop('disabled', false);
			$('.patronid, #lblPatronID').prop('disabled', true);
			$(".patronid").removeAttr('href');
			$("#lblPatronID, #inputemailbooking").val("");
			$(".lblName").empty();
			$("#inputreservid").prop( "disabled", false );
			$("#inputemailbooking").prop( "disabled", true );
		}else if(value=='emailbook'){
			$('#Reterive').prop('disabled', false);
			$('.patronid, #lblPatronID').prop('disabled', true);
			$(".patronid").removeAttr('href');
			$("#lblPatronID, #inputreservid").val("");
			$(".lblName").empty();
			$("#inputreservid").prop( "disabled", true );
			$("#inputemailbooking").prop( "disabled", false );
		}
	});
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){

		var radiochoosen = $("input[name='chkBoxSearchCateria']:checked").val();

		var getinputval;
		var getradiovalue;

		if(radiochoosen == "patrID"){
			getinputval = $("#lblPatronID").val();
			getradiovalue = "patrID";
		}else if(radiochoosen == "bookingid"){
			getinputval = $("#inputreservid").val();
			getradiovalue = "bookingid";

		}else if(radiochoosen == "emailbook"){
			getinputval = $("#inputemailbooking").val();
			getradiovalue = "emailbook";

		}


		////LOAD TABLE 
		var ta = $('#bilBayaranTable').DataTable( {
			//dom: 'Bfrtip',
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "GetBilBayaran",
		        data : {getinputval : getinputval, getradiovalue : getradiovalue},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
			
			
					var noperolehan = "";
					var title = json[i].Tajuk;
			
					if(json[i].NoPerolehan != " "){
						 noperolehan = "<span class='glyphicon glyphicon-book' data-toggle='tooltip' data-placement='bottom' title='"+title+"'></span> "+json[i].NoPerolehan;
					}
					
			

		              return_data.push({
		                'Nombor Transaksi': json[i].NomborTransaksi,
		                'Tarikh' : json[i].Tarikh, 
	                	 'Kod' : json[i].Kod + " "+json[i].KodDesc,
							 'Rujukan' : json[i].Ref,
							 'No Perolehan' : noperolehan,
	                	 'Bayaran' : json[i].Bayaran,
							 'Tindakan' : '<input type="checkbox" id="selectTrxn" onchange="selectTrxn(this)" name="receipting" checked>'
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'Nombor Transaksi'},
					{'data': 'Tarikh'},
					{'data': 'Kod'},
					{'data': 'Rujukan'},
					{'data': 'No Perolehan'},
					{'data': 'Bayaran', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': 'Tindakan'},
				],
				 footerCallback: function ( row, data, start, end, display ) {
		            var api = this.api(), data;

					var numFormat = $.fn.dataTable.render.number( '\,', '.', 2, '' ).display
		 
		            // Remove the formatting to get integer data for summation
		            var intVal = function ( i ) {
		                return typeof i === 'string' ?
		                    i.replace(/[\$,]/g, '')*1 :
		                    typeof i === 'number' ?
		                        i : 0;
		            };
		 
					///charge
		            // Total over all pages
		            totalLocalPrice = api
		                .column( 5 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
		 

					if(totalLocalPrice == "0"){
						$("#PayBtn").prop("disabled",true);
					}else{
						$("#PayBtn").prop("disabled",false);
					}


		           $("#Amount").val(" "+numFormat(totalLocalPrice));
					
	
					
		        }
    	});

	});
	
	
	
	/**/
	 $("#bilBayaranTable").on("click", "td input[type=checkbox]", function () {
		
		 var checkedCount = $("#bilBayaranTable input:checked").length;
		    var creditAmount = 0
		
		    for (var i = 0; i < checkedCount; i++) {
		
		      var amount = $("#bilBayaranTable input:checked")[i].parentNode.parentNode.children[5].innerHTML


		
		      if (amount != "") {
		        creditAmount += parseFloat(amount);
				
		      } else {
		        creditAmount = 0;
				
		      }
		    }

			if(creditAmount == "0"){
				$("#PayBtn").prop("disabled",true);
			}else{
				$("#PayBtn").prop("disabled",false);
			}
		
		    $("#Amount").val(creditAmount.toFixed(2));
		
	});



		
		function leftPad(value, length) { 
		    return ('0'.repeat(length) + value).slice(-length); 
		}
		
		
		///////
		$("#PayBtn").on( 'click', function (event) {
			
			var output2 = [];
			$('#bilBayaranTable').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
	     		var indexval  = $('#tbilBayaranTable').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
				var NomborTransaksiobj = $(this).closest('tr').find('td:eq(0)').text();
	
	     		var obj = {};
	     		obj.NomborTransaksiobj = NomborTransaksiobj;
	     		obj.indexval = indexval;
	     		output2.push(obj);
			});
			
			
			var NomborTransaksiArray = [];
		    for (var i = 0; i < output2.length; i++) {
		 		var code = output2[i]['NomborTransaksiobj'];
		 		NomborTransaksiArray.push(code);
		 	}
		
						
			$.get('GetGLNumbVal', {
				}, function(responseJson) {
				
					$(".billnumber").text(leftPad(responseJson["GL98VALUE"], 10));
					
			});
			
			
			$.get('GetBilBayaranSumm', {
		        	notran : NomborTransaksiArray.toString(),
				 	}, function(responseJson) {
						if(responseJson==''){
					 	}else{					 	
							$.each(responseJson, function(key,value) {									
								$('<tr>').append(
						        $('<td>').text(value['NomborSAP']),
						        $('<td>').text(value['Bayaran'])).appendTo('.tableSAP')
							});
						}
			});
			
			swal({
				 //title: '<strong>Maklumat</strong>',
				 //icon: 'info',
				  html:
						'<div class="col-md-12" style="margin-bottom: 5px;">'+
                         ' <div class="col-md-3">'+
                            '<label>Bil No</label>'+
                            '</div>'+
                            '<div class="col-sm-4 billnumber">:'+
							'</div>'+
                        '</div>'+


						'<div class="col-md-12" style="margin-bottom: 5px;">'+
							'<div class="col-md-12" style="margin-bottom: 5px;">'+
								'<div class="col-md-7">'+
									'<table class="table table-bordered table-striped table-hover table-condensed tableSAP">'+
										'<thead style="background-color:rgba(52, 73, 94, 0.94);color:#ecf0f1">'+
											'<tr>'+
												'<th width=30%>No</th>'+
												'<th width=70%>Jumlah</th>'+
											'</tr>'+
										'</thead>'+
										'<tbody>'+
										'</tbody>'+
									'</table>'+
								'</div>'+
							'</div>'+
						'</div>',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Yes',
		        cancelButtonText: 'No',
		        confirmButtonClass: 'confirm-class',
		        cancelButtonClass: 'cancel-class',
		        closeOnConfirm: false,
		        closeOnCancel: false 
				}).then(function(isConfirm){

					if(isConfirm){
						alert("comfirm");
					}
								
					 // function when confirm button clicked
				}, function(dismiss){
						alert("dismiss");  
			});
			
		});
		
		
		


		
		
		
		
	
});