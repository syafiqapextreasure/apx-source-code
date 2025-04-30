$(document).ready(function() {
	
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	
	$("#plkupLendingLib").prop("selectedIndex",-1);	
	$("#preview, #return").attr('disabled', true);
	
	$('#txtDateReturned').val(moment(today).format("DD/MM/YYYY"));
	$('#txtDateReturned').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
		startDate:  moment(today).format("DD/MM/YYYY")
	})/*.on('changeDate', function(e) {
        // Revalidate the date field
		//$('#borrowerReterive').prop('disabled', false);
    })*/;
	
	//////table setup
	$('#returnIll').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	//////////////////////////////////////////////////////////////////////////
	/////////////////////////////btn  Reterive////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#returnReterive').click(function(){
		
		$('#returnIll').dataTable().fnClearTable();
		
		var lendingLib = $('#plkupLendingLib').val();
		
		$('#returnIll').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
		    ajax: {
		    	url: "ResultReturn",
		    	data : {lendid : lendingLib},
		        type: "GET",
		        dataSrc: function (json) {
		        	
		        	if(json==""){
		        		$("#plkupLendingLib").prop("selectedIndex",-1);
		        		$(".lblName, .lblLibAdd1, .lblLibAdd2, .lblLibAdd3").empty();
		        		//$("#preview").attr('disabled', true);
		        	}else{
		        		//$("#preview").attr('disabled', false);
		        		$.get('LibraryDetails', {
		                	code : lendingLib
		        		 	}, function(responseJson) {
		        			if(responseJson != null){
		        				$.each(responseJson, function(key,value) {
		        					$(".lblName").text(value['lblName']);
		        					$(".lblLibAdd1").text(value['lblLibAdd1']);
		        					$(".lblLibAdd2").text(value['lblLibAdd2']);
		        					$(".lblLibAdd3").text(value['lblLibAdd3']);
		        				});
		        			}
		        		});
		        	}
		        	
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){

		              return_data.push({
		            	'' : '<input type="checkbox" id="example-select-all" name="id[]" value="'+json[i].sReferenceNo+'">',
		            	'Reference No' : json[i].sReferenceNo,  
		            	'Control No' : json[i].sControlNo,
		                'Title' : json[i].sTitle, 
		                'Copies' : json[i].iCopies,
		                'Requested Date' : json[i].sRequesteddate,
		                'Expected Date' : json[i].sExpectedDate,
		                'Received Date' : json[i].sReceivedDate,
		              })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
		     	    {'data': ''},
					{'data': 'Reference No'},
					{'data': 'Control No', className: "text-center"},
					{'data': 'Title'},
					{'data': 'Copies', className: "text-right"},
					{'data': 'Requested Date', className: "text-right"},
					{'data': 'Expected Date', className: "text-center"},
					{'data': 'Received Date', className: "text-center"},

				],
			});
	});
	
	/////////////////preview click
	$("#preview").click(function(){
		
		var plkupLendingLib = $("#plkupLendingLib").val();
		
		var output2 = [];
		$('#returnIll').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
     		var indexval  = $('#returnIll').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
			var refno = $(this).closest('tr').find('td:eq(1)').text();
			var ctrlno = $(this).closest('tr').find('td:eq(2)').text();
			var reqDate = $(this).closest('tr').find('td:eq(5)').text();

     		var obj = {};
     		obj.indexval = indexval;
     		obj.refno = refno;
     		obj.ctrlno = ctrlno;
     		obj.reqDate = reqDate;
     		output2.push(obj);
		});
		
		var total = output2.length;
		
		///refno
		var refnoArray = [];
	    for (var i = 0; i < output2.length; i++) {
	 		var code = output2[i]['refno'];
	 		refnoArray.push(code);
	 	}
	    
	    ///ctrlno
		var ctrlnoArray = [];
	    for (var i = 0; i < output2.length; i++) {
	 		var code = output2[i]['ctrlno'];
	 		ctrlnoArray.push(code);
	 	}
	    
	    ///reqDate
		var reqDateArray = [];
	    for (var i = 0; i < output2.length; i++) {
	 		var code = output2[i][''];
	 		reqDateArray.push(code);
	 	}
	    
	    for (var i = 0; i < output2.length; i++) {
	    	
	    	/*alert("liferayLogin" +liferayLogin);
	    	alert("refnoArray[i]" +refnoArray[i]);
	    	alert("ctrlnoArray[i]" +ctrlnoArray[i]);
	    	alert("plkupLendingLib[i]" +plkupLendingLib[i]);
	    	alert("reqDateArray[i]" +reqDateArray[i]);*/
	    	
		 	//alert(ControlNoArray[i]);
		 	$.post("GeneratePreviewDocument", {
				action : "EL05",
			 	liferayLogin : liferayLogin,
			 	refno : refnoArray[i],
			 	sControlNo : ctrlnoArray[i], 
			 	sRequestor : plkupLendingLib,
			 	action2 : "preview",
			 	reqDate : reqDateArray[i],
			 	
			}, function(result){
	
					 var w = window.open('about:blank');
					 	w.document.open();
					 	w.document.write(result);
					 	w.document.close();
					 	//w.print();
			});
		}
		
		
		/*$('#returnIll input:radio:checked').each(function () {
			var refno = this.value;
			var ctrlno = $(this).closest('tr').find('td:eq(2)').text();
			var reqDate = $(this).closest('tr').find('td:eq(5)').text();
			var indexval  = $('#returnIll').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
			
			$.post("GeneratePreviewDocument", {
					action : "EL05",
				 	liferayLogin : liferayLogin,
				 	refno : refno.trim(),
				 	sControlNo : ctrlno, 
				 	sRequestor : plkupLendingLib,
				 	action2 : "preview",
				 	reqDate : reqDate,
				 	
			}, function(result){
	
					 var w = window.open('about:blank');
					 	w.document.open();
					 	w.document.write(result);
					 	w.document.close();
					 	//w.print();
			});
		});*/
		
	});
	
///////////////////////////////////////////WHEN CLICK CHECK BOX////////////
	/// 1) FOR SELECT ALL
	$('#example-select-all').on('click', function(){
		// Check/uncheck all checkboxes in the table
		var rows = $('#returnIll').DataTable().rows({ 'search': 'applied' }).nodes();
		if(!this.checked){
	    	$('#example-select-all', rows).prop('checked', false);
	    	$("#return, #preview").attr('disabled',true);
	   	}else{
	    	$('#example-select-all', rows).prop('checked', this.checked);
	    	$("#return, #preview").attr('disabled',false);
	    }
	});
	
	// Handle click on checkbox to set state of "Select all" control
    $('#returnIll tbody').on('change', '#example-select-all', function(){
    	
       // If checkbox is not checked
       if(!this.checked){
    	  //$(".saveRetrieve").attr('disabled',true);
          var el = $('#example-select-all').get(0);
          var totaltick = $('#returnIll :input[type="checkbox"]:checked').length
          if(totaltick == 0){
        	  $("#return, #preview").attr('disabled',true);  
          }else{
        	  $("#return, #preview").attr('disabled',false);  
          }
          
          // If "Select all" control is checked and has 'indeterminate' property
          if(el && el.checked && ('indeterminate' in el)){
             // Set visual state of "Select all" control 
             // as 'indeterminate'
             el.indeterminate = true;
          }
       }else{
    	   $("#return, #preview").attr('disabled',false);
       }
    });
    ///////////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////////return click
	$("#return").click(function(){
		
		/*alert("sda");
		alert( $('input[name=print]:checked').val());*/
		
		var checkPrint = $('input[name=print]:checked').val();
		//alert(checkPrint)
		
		var output2 = [];
		$('#returnIll').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
     		var indexval  = $('#returnIll').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
			var refno = $(this).closest('tr').find('td:eq(1)').text();
			var sControlNo = $(this).closest('tr').find('td:eq(2)').text();
			var iCopies = $(this).closest('tr').find('td:eq(4)').text();
			var reqDate = $(this).closest('tr').find('td:eq(5)').text();

     		var obj = {};
     		obj.indexval = indexval;
     		obj.refno = refno;
     		obj.sControlNo = sControlNo;
     		obj.iCopies = iCopies;
     		obj.reqDate = reqDate;
     		output2.push(obj);
		});
		
		var total = output2.length;
		
		///refno
		var refnoArray = [];
	    for (var i = 0; i < output2.length; i++) {
	 		var code = output2[i]['refno'];
	 		refnoArray.push(code);
	 	}
	    
	    //alert(refnoArray.toString() +"ppp");
	    
	    ////ControlNo
	    var ControlNoArray = [];
	    for (var i = 0; i < output2.length; i++) {
	 		var code = output2[i]['sControlNo'];
	 		ControlNoArray.push(code);
	 	}
	    //alert(ControlNoArray.toString() +"ppp");
	    ///iCopies
	    var iCopiesArray = [];
	    for (var i = 0; i < output2.length; i++) {
	 		var code = output2[i]['iCopies'];
	 		iCopiesArray.push(code);
	 	}
	    
	    ///reqDate
		var reqDateArray = [];
	    for (var i = 0; i < output2.length; i++) {
	 		var code = output2[i][''];
	 		reqDateArray.push(code);
	 	}
	    
	    $.get("clickreturnILL",{
				sReferenceNo : refnoArray.toString(),
				sControlNo : ControlNoArray.toString(),
				iCopies : iCopiesArray.toString(),
				total : total,
				liferayLogin : liferayLogin
		},
				
				function(message){
					var status = message.replace(/\s+/g, '');
					//alert(status +"status");
					
					if (status == "true") {
						setTimeout(function(){
							$('#returnIll').dataTable().fnClearTable();
							$('#returnReterive').click();
							messageBox("005","Return","@action");
						}, 1000); 
						
						 if(checkPrint == "print"){
								//alert("dfgd");
								 var plkupLendingLib = $('#plkupLendingLib').val();
							
								 for (var i = 0; i < total; i++) {

										$.post("GeneratePreviewDocument", {
											action : "EL05",
										 	liferayLogin : liferayLogin,
										 	refno : refnoArray[i],
										 	sControlNo : ControlNoArray[i], 
										 	sRequestor : plkupLendingLib,
										 	action2 : "print",
										 	reqDate : reqDateArray[i],
										 	
									}, function(result){

											 var w = window.open('about:blank');
											 	w.document.open();
											 	w.document.write(result);
											 	w.document.close();
											 	w.print();
									});
								}
								
							}
						
					}else{
						swal("Unsuccessfully");
					}
		});
		
		
	});
	
});