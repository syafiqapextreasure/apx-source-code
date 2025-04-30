$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	/*$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		return results[1] || 0;
	}
	
	$('.module').val(decodeURIComponent($.urlParam('value')));*/
	
	//$("#vendor").focus();
	$('#paymentReterive').prop('disabled', true);
	$('input[name=radioOption1]').attr("disabled",true);
	$('input[name=radioOption2]').attr("disabled",true);
	$('input[name=radioOption3]').attr("disabled",true);
	$('#input-startDoc, #input-endDoc').prop( "disabled", true );
	$('#input-startDate, #input-endDate').prop( "disabled", true );
	
	
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	}).on('changeDate', function(e) {
        // Revalidate the date field
		$('#paymentReterive').prop('disabled', false);
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
		$('#paymentReterive').prop('disabled', false);
    });
	
	//////table setup
	$('#table-PayHistory').DataTable( {
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
			$('#input-startDate').datepicker('setDate', null);
			$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
			$('#input-endDate').datepicker('setDate', moment(today).format("DD/MM/YYYY"));
			$('#input-startDate').val("");
			$('input[name=radioOption3]').prop('checked', false);
			$("#paymentReterive").attr('disabled', 'disabled');
			
			$("input[name='radioOption2']").click(function () {
		    	if ($("input[name='radioOption2']").is(":checked")) {
		    		
		    		$('#input-startDoc, #input-endDoc').prop( "disabled", false );
					$('#input-startDate, #input-endDate').prop( "disabled", true );
					$("#input-startDoc").focus();
					
					$("#input-startDoc").blur(function() {
					    if(($("#input-startDoc").val().length >= 1) && $("#input-endDoc").val().length >= 1){
					    	$('#paymentReterive').prop('disabled', false);
					    } else {
					    	$('#paymentReterive').prop('disabled', true);
					    }
					});
					
					$("#input-endDoc").blur(function() {
					    if(($("#input-startDoc").val().length >= 1) && $("#input-endDoc").val().length >= 1){
					    	$('#paymentReterive').prop('disabled', false);
					    } else {
					    	$('#paymentReterive').prop('disabled', true);
					    }
					});
					
		    	}
		    });
			
		}else if(value=="dateRange"){
			$('input[name=radioOption2]').attr("disabled",true);
			$('input[name=radioOption3]').attr("disabled",false);
			/*$('#input-startDoc, #input-endDoc').prop( "disabled", true );*/
			$('#input-startDoc, #input-endDoc').val("");
			/*$('#input-startDate, #input-endDate').prop( "disabled", false );*/
			$('input[name=radioOption2]').prop('checked', false);
			$("#paymentReterive").attr('disabled', 'disabled');
			
			$("input[name='radioOption3']").click(function () {
		    	if ($("input[name='radioOption3']").is(":checked")) {
		    		
		    		$('#input-startDate, #input-endDate').prop( "disabled", false );
					$('#input-startDoc, #input-endDoc').prop( "disabled", true );
					$("#input-startDate").focus();
					
					$("#input-startDate").blur(function() {
					    if((($("#input-startDate").val().length >= 1) && $("#input-endDate").val().length >= 1) || $("#input-startDate").val().length >= 1){
					    	$('#paymentReterive').prop('disabled', false);
					    } else {
					    	$('#paymentReterive').prop('disabled', true);
					    }
					});

					$("#input-endDate").blur(function() {
					    if(($("#input-startDate").val().length >= 1) && $("#input-endDate").val().length >= 1){
					    	$('#paymentReterive').prop('disabled', false);
					    } else {
					    	$('#paymentReterive').prop('disabled', true);
					    }
					});
					
		    	}
		    });
		}
	});
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
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
	$("#input-vendorCode").keyup(function(e){
		var id = $("#input-vendorCode").val();
		/*if(id.length==0){
			$('#Reterive').prop('disabled', true);
		}else{
			$('#Reterive').prop('disabled', false);
		}*/

		$(".div-vendorName").empty();
		////display vendor name
		$.get('GetVendorName', {
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
			/*if(responseJson == ""){
		 		$("#input-vendorCode").val("");
		 		$("#input-vendorCode").css("border", "solid red");
		 		$('input[name=radioOption1]').attr("disabled",true);
		 		$("#paymentReterive").attr('disabled', 'disabled');
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-vendorname").text(value['vendorName']);
					$("#input-vendorCode").css("border", "");
					$("#paymentReterive").attr('disabled', false);
					$('input[name=radioOption1]').attr("disabled",false);
				});
			}*/
		});
	});
	
	
	//clear title when paron id keydown backspace
	$("#input-vendorCode").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-vendorname").empty();
			$("#Reterive").attr('disabled', true);
			$('#table-PayHistory').dataTable().fnClearTable();
		}
		
		if(code == 13) {
	      e.preventDefault();
	      return false;
	    }
	});
	
	/*//blur
	$("#vendor").blur(function(e){
		var vendor = $("#vendor").val();
	
		$(".div-vendorname").empty();
		////display vendor name
		$.get('GetVendorName', {
        	code : vendor
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		$("#vendor").val("");
		 		$("#vendor").css("border", "solid red");
		 		$('input[name=radioOption1]').attr("disabled",true);
		 		$("#paymentReterive").attr('disabled', 'disabled');
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-vendorname").text(value['vendorName']);
					$("#vendor").css("border", "");
					$("#paymentReterive").attr('disabled', false);
					$('input[name=radioOption1]').attr("disabled",false);
				});
			}
		});
	});
	
	//backspace
	$("#vendor").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-vendorname").empty();
			$("#vendor").css("border", "");
			$("#paymentReterive").attr('disabled', false);
			$('#table-PayHistory').dataTable().fnClearTable();
		}
	});
	*/
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Payment Reterive//////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		$('#table-PayHistory').dataTable().fnClearTable();
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
			sDate = "";
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = $("#input-startDate").val();
		}
		
		var endSentDate = $("#input-endDate").val();
		if(endSentDate == ''){
			endSentDate = '';
			eDate = '';
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate =  $("#input-endDate").val();
		} 
		
		$('#table-PayHistory').DataTable( {
			dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_PaymentHistory',
					        title: 'Payment History Report from ' +sDate + " until " +eDate,

					        //messageTop: 'Minimum Borrowing = ' ,
					       /*	customize: function( xlsx ) {
						    	 var sheet = xlsx.xl.worksheets['sheet1.xml'];
						         // $('row:first c', sheet).attr( 's', '42' );
 								$('c[r=B3] t', sheet).text( 'Custom text' );
						     }*/
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_PaymentHistory',
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							//messageTop: 'Income Details by Transaction Type' ,
							//messageBottom: "lllllll",
							exportOptions: {
								//columns: ':visible',
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
								
								 /*var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][2].alignment = 'right';
						               	doc.content[1].table.body[i][3].alignment = 'right';
										doc.content[1].table.body[i][4].alignment = 'right';
						           };*/
								
								//doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
							//	doc.content[1].table.widths = [30,100,50,50,50];
							//doc.content[1].table.body[0][3].alignment = 'right';
							 //doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%']; 
						//doc.content[1].table.widths = [ '2%', '10%', '17%', '5%', '5%', '5%','5%','5%','5%','5%','5%','6%','5%','5%','5%','5%','5%'];  
							 //doc.content[1].table.body[0][5].alignment = 'right';
						
						


											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Payment History Report  from '+sDate+ ' until '+eDate+ ' \n',bold:true,fontSize:13,alignment: 'center'},
						                                   /////{ text: $("#libname").val() +'\n',bold:true,fontSize:13,alignment: 'center'},
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
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										///alert(csv_cell_array[1]);
										//alert(csv_cell_array[1]);
										
										return "Payment History Report from " +sDate + " until "+ eDate +"\n\n" + doc;
										//var split_csv = doc.split("\n");
										 
									 }
             			},
            ],
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//info: false,
		    ajax: {
		    	url: "resultPayHistory",
		        data : {vendor : vendor, radioOption1 : radioOption1, radioOption2 : radioOption2,
		        	radioOption3 : radioOption3, inputstartDoc : inputstartDoc, inputendDoc : inputendDoc,
		        	startDate : startSentDate, endDate : endSentDate},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'Invoice No' : json[i].Column1,
		                'Invoice Date' : json[i].Column2,
		                'Order No' : json[i].Column3,
		                'Vendor' : json[i].Column4,
						'Payment Date' : json[i].Column5,
						'Amount' : json[i].Column6,
						'Cheque No' : json[i].Column7,
						'Cheque Date' : json[i].Column8,
						'Voucher No' : json[i].Column9,
						'Voucher Date' : json[i].Column10,
						'Pay Ref No' : json[i].Column11,
						'Pay Ref Date' : json[i].Column12,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Invoice No'},
					{'data': 'Invoice Date'},
					{'data': 'Order No'},
					{'data': 'Vendor'},
					{'data': 'Payment Date'},
					{'data': 'Amount', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 4, '')},
					{'data': 'Cheque No'},
					{'data': 'Cheque Date'},
					{'data': 'Voucher No'},
					{'data': 'Voucher Date'},
					{'data': 'Pay Ref No'},
					{'data': 'Pay Ref Date'},
					
					
				],
				footerCallback: function ( row, data, start, end, display ) {
		            var api = this.api(), data;

					var numFormat = $.fn.dataTable.render.number( '\,', '.', 4, 'RM' ).display;
		 
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
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn REFERESH//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	/*$('#refresh').click(function(){
		$('#table-PayHistory').dataTable().fnClearTable();
		$('#panel-result').collapse('hide');
		$("#searchForm")[0].reset();
		$(".div-vendorname").empty();
		$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
		$('#input-endDate').datepicker('setDate', moment(today).format("DD/MM/YYYY"));
		//$(this).find('form').trigger('reset');
	});*/
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
});