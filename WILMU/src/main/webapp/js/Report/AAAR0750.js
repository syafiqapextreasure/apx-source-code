$(document).ready(function() {
	function messageBox(code, criteria, label){
			$.get("Global?type=Handler&name=Error_Page",{GL79ERRCODE : code,
				criteria : criteria,
			 	label : label},function(result){
					swal(result);
			});
	}
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		return results[1] || 0;
	}
	
	$('.module').val(decodeURIComponent($.urlParam('value')));
	
	$.get('ResultGlflag2', {
		 	id : "CURRENCYFORMAT",
		 	}, function(responseJson) {
			$("#setupCurrency").val(responseJson);
	 });

	$(".div1vend").removeClass( "col-sm-3 col-md-3" ).addClass( "col-sm-2 col-md-2" );
	$(".div-vendorName1").removeClass( "col-sm-3 col-md-3" ).addClass( "col-sm-2 col-md-2" );

	$('#input-vendorCode').prop( "disabled", false );
	$('#Reterive').prop('disabled', true);
	$('input[name=radioOption1]').attr("disabled",true);
	$('input[name=radioOption2]').attr("disabled",true);
	$('input[name=radioOption3]').attr("disabled",true);
	$('#input-startDoc, #input-endDoc').prop( "disabled", true );
	$('#input-startDate, #input-endDate').prop( "disabled", true );
	
	
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	}).on('changeDate', function(e) {
        // Revalidate the date field
		$('#Reterive').prop('disabled', false);
		  var minDate = new Date(selected.date);
                    minDate.setDate(minDate.getDate() + 1);
                    $('#input-endDate').datepicker('setStartDate', minDate);
    });
	
	///////////////////// input-endDate set Current Date  ////////////////////
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	}).on('changeDate', function(e) {
        // Revalidate the date field
		$('#Reterive').prop('disabled', false);
		  var minDate = new Date(selected.date);
                    minDate.setDate(minDate.getDate() - 1);
                    $('#input-startDate').datepicker('setEndDate', minDate);
    });
	
	//////table setup
	$('#reportTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	///////////////////////////////ON CHANGE TYPE//////////////////////////////
	$('input[name=radioOption1]').change(function(){
		var value = $( 'input[name=radioOption1]:checked' ).val();
		if(value == "doctRange"){ 
			$('input[name=radioOption2]').attr("disabled",false);
			$('input[name=radioOption3]').attr("disabled",true);
			$('#input-startDoc, #input-endDoc').prop( "disabled", false );
			$('#input-startDate, #input-endDate').prop( "disabled", true );

			$('#input-startDate').val("");
			$('input[name=radioOption3]').prop('checked', false);
			$("#Reterive").attr('disabled', 'disabled');
			
			$("input[name='radioOption2']").click(function () {
		    	if ($("input[name='radioOption2']").is(":checked")) {
		    		$('#input-startDoc, #input-endDoc').prop( "disabled", false );
					$('#input-startDate, #input-endDate').prop( "disabled", true );
					$("#input-startDoc").focus();
					$("#input-startDoc").blur(function() {
					    if(($("#input-startDoc").val().length >= 1) && $("#input-endDoc").val().length >= 1){
					    	$('#Reterive').prop('disabled', false);
					    } else {
					    	$('#Reterive').prop('disabled', true);
					    }
					});
					
					$("#input-endDoc").blur(function() {
					    if(($("#input-startDoc").val().length >= 1) && $("#input-endDoc").val().length >= 1){
					    	$('#Reterive').prop('disabled', false);
					    } else {
					    	$('#Reterive').prop('disabled', true);
					    }
					});
		    	}
		    });
			
		}else if(value=="dateRange"){
			$('input[name=radioOption2]').attr("disabled",true);
			$('input[name=radioOption3]').attr("disabled",false);
			$('#input-startDoc, #input-endDoc').prop( "disabled", true );
			$('#input-startDoc, #input-endDoc').val("");
			$('#input-startDate, #input-endDate').prop( "disabled", false );
			$('input[name=radioOption2]').prop('checked', false);
			$("#Reterive").attr('disabled', 'disabled');
			
			$("input[name='radioOption3']").click(function () {
		    	if ($("input[name='radioOption3']").is(":checked")) {
		    		$('#input-startDate, #input-endDate').prop( "disabled", false );
					$('#input-startDoc, #input-endDoc').prop( "disabled", true );
					$("#input-startDate").focus();
					$("#input-startDate").blur(function() {
					    if((($("#input-startDate").val().length >= 1) && $("#input-endDate").val().length >= 1) || $("#input-startDate").val().length >= 1){
					    	$('#Reterive').prop('disabled', false);
					    } else {
					    	$('#Reterive').prop('disabled', true);
					    }
					});

					$("#input-endDate").blur(function() {
					    if(($("#input-startDate").val().length >= 1) && $("#input-endDate").val().length >= 1){
					    	$('#Reterive').prop('disabled', false);
					    } else {
					    	$('#Reterive').prop('disabled', true);
					    }
					});
		    	}
		    });
		}
	});
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////vendor /////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	///on click searc patron 
	$('.vendorCode').on('click',function(){
		var url = "Modal_Vendor?action=All";

		$.get(url,function(data){
			$("#modal_vendorSearchContent").html(data);
		});
	});
	
	///keyup
	$("#input-vendorCode").on("keypress keyup blur",function (e) {
		var id = $("#input-vendorCode").val();

		$(".div-vendorName").empty();
		////display vendor name
		$.get('ResultVendorName', {
        	id : id,
			action : "All"
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-vendorName").text(value['vendorName']);
					$("#Reterive").attr('disabled', false);
					$('input[name=radioOption1]').attr("disabled",false);
				});
			}
		});
	});
	
	
	//clear title when paron id keydown backspace
	$("#input-vendorCode").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-vendorname").empty();
			$("#Reterive").attr('disabled', true);
			$('#reportTable').dataTable().fnClearTable();
		}
		if(code == 13) {
	      e.preventDefault();
	      return false;
	    }
	});
	
	
	$("#input-startDoc, #input-endDoc").focusout(function(){
	    if(parseFloat($("#input-startDoc").val()) > parseFloat($("#input-endDoc").val())){
			$("#input-startDoc, #input-endDoc").css("border", "solid red");
	        $("#Reterive").prop('disabled',true);
			messageBox("172","","");
	    } else {
	        $(".error").css("display","none");
			$("#input-startDoc, #input-endDoc").css("border", "");
	        $("#Reterive").prop('disabled',false);        
	    }
	});
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Payment Reterive//////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		$('#reportTable').dataTable().fnClearTable();
		var currencyFormat = $("#setupCurrency").val();
		var moduleval = $(".module").val();
		
		var vendor = $("#input-vendorCode").val();
		var radioOption1 = $( 'input[name=radioOption1]:checked' ).val();
		var radioOption2 = $( 'input[name=radioOption2]:checked' ).val();
		var radioOption3 = $( 'input[name=radioOption3]:checked' ).val();
		var inputstartDoc = $("#input-startDoc").val();
		var inputendDoc = $("#input-endDoc").val();

		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate, eDate;
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
			sDate = '';
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = "from " +$("#input-startDate").val();
		}
		
		var endSentDate = $("#input-endDate").val();
		if(endSentDate == ''){
			endSentDate = '';
			eDate = '';
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate =  " until " +$("#input-endDate").val();
		}

		$('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_PaymentHistory',
					        title: 'Payment History Report ' +sDate +eDate,              
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_PaymentHistory',
							charset: "utf-8",
							bom : "true",
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								//columns: ':visible',
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
								
								doc.styles.tableHeader.alignment = 'left';
								
								 var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][6].alignment = 'right';
						           };
											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Payment History Report '+sDate +eDate+ ' \n',bold:true,fontSize:13,alignment: 'center'},
						                        ],
						                        margin: [0, 0, 0, 12],
						                    });
					
											doc['footer']=(function(page, pages) {
												return {
													columns: [
														{
															alignment: 'left',
															text: ['\t\t\t\t  ', moment(today).format("DD/MM/YYYY") +'\t' +moment(today).format("hh:mm:ss A")]
														},
														{
															alignment: 'right',
															text: ['page ', { text: page.toString() },	' of ',	{ text: pages.toString() }]
														}
													],
													margin: 20
												}
											});
									}, 
					    },
					
               			{
		                	extend: 'csv',
		                	filename: 'WILMU_PaymentHistory',
		                	title: 'Payment History Report ',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										return "Payment History Report " +sDate + eDate +"\n\n" + doc;
									 }
             			},
            ],
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
		    ajax: {
		    	url: "ResultPaymentHistory",
		        data : {module : moduleval,
					vendor : vendor, radioOption1 : radioOption1, radioOption2 : radioOption2,
		        	radioOption3 : radioOption3, inputstartDoc : inputstartDoc, inputendDoc : inputendDoc,
		        	startDate : startSentDate, endDate : endSentDate},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            for(var i=0;i< json.length; i++){
		              return_data.push({
		                'No': (i+1),
		                'Invoice No' : json[i].InvoiceNo,
		                'Invoice Date' : json[i].InvoiceDate,
		                'Order No' : json[i].OrderNo,
		                'Vendor' : json[i].Vendor,
						'Payment Date' : json[i].VoucherDate,
						'Amount' : json[i].Amount,
						'Cheque No' : json[i].ChequeNo,
						'Cheque Date' : json[i].ChequeDate,
						'Voucher No' : json[i].VoucherNo,
						'Voucher Date' : json[i].VoucherDate,
						'Pay Ref No' : json[i].RefNo,
						'Pay Ref Date' : json[i].RefDate,
		            })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Invoice No',visible: false},
					{'data': 'Invoice Date', type: 'date-uk', targets: 0},
					{'data': 'Order No'},
					{'data': 'Vendor'},
					{'data': 'Payment Date', type: 'date-uk', targets: 0},
					{'data': 'Amount', className: "text-right", render: $.fn.dataTable.render.number(',', '.', currencyFormat, '')},
					{'data': 'Cheque No'},
					{'data': 'Cheque Date'},
					{'data': 'Voucher No'},
					{'data': 'Voucher Date', type: 'date-uk', targets: 0},
					{'data': 'Pay Ref No'},
					{'data': 'Pay Ref Date', type: 'date-uk', targets: 0},
				],
				drawCallback: function ( settings ) {
		            var api = this.api();
		            var rows = api.rows( {page:'current'} ).nodes();
		            var last=null;
		            api.column(1, {page:'current'} ).data().each( function ( group, i ) {
		                if ( last !== group ) {
		                    $(rows).eq( i ).before(
		                        '<tr class="group"><td colspan="12">'+group+'</td></tr>'
		                    );
		                    last = group;
		                }
		            } );
		        },
				footerCallback: function ( row, data, start, end, display ) {
		            var api = this.api(), data;
					var numFormat = $.fn.dataTable.render.number( '\,', '.', currencyFormat, 'RM' ).display;
		 
		            // Remove the formatting to get integer data for summation
		            var intVal = function ( i ) {
		                return typeof i === 'string' ?
		                    i.replace(/[\$,]/g, '')*1 :
		                    typeof i === 'number' ?
		                        i : 0;
		            };
		 
					///charge
		            // Total over all pages
		            total = api
		                .column( 6 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
		 
		            // Update footer
		           $( api.column( 6 ).footer() ).html(
		                numFormat(total)
		            );
		        }
		});
	});
});