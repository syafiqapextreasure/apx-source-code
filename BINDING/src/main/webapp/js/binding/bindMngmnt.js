$(document).ready(function() {

	$("#cancellation").hide();
	var liferayLogin = $("#liferayLogin").val();
	//////////////////////////////Max Length /////////////////////////////////
	$("#binder").attr('maxlength','4');
	$("#ctrlno").attr('maxlength','10');
	
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
	
	//////////////////////////////TABLE CHECK IN///////////////////////////////
	$('#table-BindingMngmt').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	//****************************************** AREA FOR keyup, keydown blur****************************//
	////////BINDER////////////
	//keyup
	$("#binder").keyup(function() {

		var binder = $("#binder").val();
		
		if(binder.length >= 1){
			$('#checkbox-binder').prop('checked', true);
		}else{
			$('#checkbox-binder').prop('checked', false);
		}
	});
	
	//blur
	$("#binder").blur(function(e){
		var binder = $("#binder").val();
		//alert("vendorCode " + vendorCode);
		$(".div-bindername").empty();
		////display vendor name
		$.get('GetBinderName', {
        	code : binder
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		$("#binder").val("");
		 		$("#binder").css("border", "solid red");
		 		$("#bindMngmntReterive").attr('disabled', 'disabled');
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-bindername").text(value['vendorName']);
					$("#binder").css("border", "");
					$("#bindMngmntReterive").attr('disabled', false);
				});
			}
		});
	});
	
	//backspace
	$("#binder").keydown(function(e){ 
		var code = e.keyCode || e.which;
		/*if(code == '9' ||code == '13' ){
			setTimeout(function(){ 
				$('#bindMngmntReterive').click();
			}, 1000);
			event.preventDefault();	
		}*/
		if(code == '8' ||code == '46'){
			$(".div-bindername").empty();
			$("#binder").css("border", "");
			$("#bindMngmntReterive").attr('disabled', false);
			$('#table-BindingMngmt').dataTable().fnClearTable();
		}
	});
	
	////////Control Number////
	$("#ctrlno").keyup(function() {

		var ctrlno = $("#ctrlno").val();
		
		if(ctrlno.length >= 1){
			$('#checkbox-controlNo').prop('checked', true);
		}else{
			$('#checkbox-controlNo').prop('checked', false);
		}
	});
	
	//backspace
	$("#ctrlno").keyup(function() {

		var binder = $("#ctrlno").val();
		
		if(binder.length >= 1){
			$('#checkbox-controlNo').prop('checked', true);
		}else{
			$('#checkbox-controlNo').prop('checked', false);
		}
	});
	
	
	////////Date Sent /////
	$("#input-startDate").bind("keyup focusout", function () {
		
		setTimeout(function(){
        	var startdate = $("#input-startDate").val();
        	if(startdate.length >= 1){
    			$('#checkbox-bindDate').prop('checked', true);
    		}else{
    			$('#checkbox-bindDate').prop('checked', false);
    		}
		}, 1000); 
	});
	
	//***************************************************************************************************//
	
	///////////////////////////////ON CHANGE TYPE//////////////////////////////
	$('input[name=statusRadio]').change(function(){
		
		$('#table-BindingMngmt').dataTable().fnClearTable();
		$('input:checkbox').prop('checked', false);
		$('input[type="text"]').val('');
		$(".div-bindername").empty();
		$("#cancellation").hide();
	});
	///////////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////////REFERSH//////////////////////////////////////
	$('#refresh').click(function(){
		$('#table-BindingMngmt').dataTable().fnClearTable();
		$('#panel-result').collapse('hide');
		$("#searchForm")[0].reset();
		$(".div-bindername").empty();
		//$(this).find('form').trigger('reset');
	});
	///////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////RETRIEVE BUTTON CLICK////////////////////////
	$('#bindMngmntReterive').click(function(){
		
		$('#table-BindingMngmt').dataTable().fnClearTable();
		
		var type = $("input[name='statusRadio']:checked").val();
		var binder = $("#binder").val();
		var ctrlno = $("#ctrlno").val();
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");

		var checkbinder = $('#checkbox-binder:checked').val();
    	if(checkbinder == "binderVal"){
    		checkbinder = "Y";
    	}else{
    		checkbinder = "N";
    	}
    	
    	var checkcontrolNo = $('#checkbox-controlNo:checked').val();
    	if(checkcontrolNo == "controlNoVal"){
    		checkcontrolNo = "Y";
    	}else{
    		checkcontrolNo = "N";
    	}
    	
    	var checkbindDate= $('#checkbox-bindDate:checked').val();
    	if(checkbindDate == "bindDateVal"){
    		checkbindDate = "Y";
    	}else{
    		checkbindDate = "N";
    	}
    	
    	$('#panel-result').collapse('show');
		$("#cancellation").show().prop('disabled', true);
		
		$('#table-BindingMngmt').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//pagingType: "full_numbers",
		    ajax: {
		    	url: "resultManagement",
		        data : {
		        		type : type, 
		        		binder : binder, 
		        		ctrlno : ctrlno, 
		        		startSentDate : startSentDate,
		        		endSentDate : endSentDate,
		        		checkbinder : checkbinder,
		        		checkcontrolNo : checkcontrolNo,
		        		checkbindDate : checkbindDate
		        },
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){
		            	
		            	var stat = json[i].status;

		            	switch(stat){
		        	  	case state = 'R':
		        	  		stat = "Returned";
		        	  		break;
		        	  	case state = 'S':
		        	  		stat = "Sent";
		        	  		break;
		        	  	case state = 'P':
		        	  		stat = "Pending";
		        	  		break;
		        	  	case state = 'C':
		        	  		stat = "Cancelled";
		        	  		break;
		        		}

		              return_data.push({
		            	'': '<input type="checkbox" id="example-select-all" name="id[]" value=" '+json[i].bindno+' ">',
		                'Binding No': json[i].bindno,
		                'Binder' : json[i].binder,
		                'Title' : json[i].title, 
		                'Call No' : json[i].callno,
		                'Accession' : json[i].accession,
		                'Status' : stat,
		                'Sent Date' : json[i].sdate,
		                'Expected Date' : json[i].expdate,
		                'Order No' : json[i].orderno,
		            })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': ''},
					{'data': 'Binding No'},
					{'data': 'Binder'},
					{'data': 'Title'},
					{'data': 'Call No'},
					{'data': 'Accession'},
					{'data': 'Status'},
					{'data': 'Sent Date', className: "text-center"},
					{'data': 'Expected Date', className: "text-center"},
					{'data': 'Order No', className: "text-center"},
				],
		});
	});
	///////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////WHEN CLICK CHECK BOX////////////
	/// 1) FOR SELECT ALL
	$('#example-select-all').on('click', function(){
		// Check/uncheck all checkboxes in the table
		var rows = $('#table-BindingMngmt').DataTable().rows({ 'search': 'applied' }).nodes();
		if(!this.checked){
	    	$('#example-select-all', rows).prop('checked', false);
	    	$("#cancellation").attr('disabled',true);
	   	}else{
	    	$('#example-select-all', rows).prop('checked', this.checked);
	    	$("#cancellation").attr('disabled',false);
	    }
	});
	
	// Handle click on checkbox to set state of "Select all" control
    $('#table-BindingMngmt tbody').on('change', '#example-select-all', function(){
    	
       // If checkbox is not checked
       if(!this.checked){
    	  //$(".saveRetrieve").attr('disabled',true);
          var el = $('#example-select-all').get(0);
          var totaltick = $('#table-BindingMngmt :input[type="checkbox"]:checked').length
          if(totaltick == 0){
        	  $("#cancellation").attr('disabled',true);  
          }else{
        	  $("#cancellation").attr('disabled',false);  
          }
          
          // If "Select all" control is checked and has 'indeterminate' property
          if(el && el.checked && ('indeterminate' in el)){
             // Set visual state of "Select all" control 
             // as 'indeterminate'
             el.indeterminate = true;
          }
       }else{
    	   $("#cancellation").attr('disabled',false);
       }
    });
    ///////////////////////////////////////////////////////////////////////////

    
    ///////////////////////////Save Retrieve click/////////////////////////////
	$('#cancellation').click(function(){
		
		var type = $("input[name='statusRadio']:checked").val();
		//var indexval  = $('#table-BindingMngmt').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
		
		////getvalue when checked row
		var output2 = [];
		$('#table-BindingMngmt').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
     		var indexval  = $('#table-BindingMngmt').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
			var bindnoobj = $(this).closest('tr').find('td:eq(1)').text();
			var accobj = $(this).closest('tr').find('td:eq(5)').text();
			var orderobj = $(this).closest('tr').find('td:eq(9)').text();

     		var obj = {};
     		obj.bindnoobj = bindnoobj;
     		obj.accobj = accobj;
     		obj.indexval = indexval;
     		obj.orderobj = orderobj;
     		output2.push(obj);
		});
		
		var total = output2.length;
		
		///bind no
		var bindnoArray = [];
	    for (var i = 0; i < output2.length; i++) {
	 		var code = output2[i]['bindnoobj'];
	 		bindnoArray.push(code);
	 	}
		
		///accession
		var accobjArray = [];
	    for (var i = 0; i < output2.length; i++) {
	 		var code = output2[i]['accobj'];
	 		accobjArray.push(code);
	 	}
	    
	    ///orderno
		var orderArray = [];
	    for (var i = 0; i < output2.length; i++) {
	 		var code = output2[i]['orderobj'];
	 		orderArray.push(code);
	 	}
		
		swal({
			text: "Cancel the selected record(s)?",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "Yes",
	        cancelButtonText: "No"
	      }).then(
	        function(isConfirm) {
	          if (isConfirm) {
	        	 if(type == "M"){
	        		 $.get("managementBinding",{
	         				liferayLogin : liferayLogin,
					  		bindno : bindnoArray.toString(),
					  		accno : accobjArray.toString(),
					  		total : total,
					  		type : "M"
	         				},
	         				
	         				function(message){
	         					var status = message.replace(/\s+/g, '');
	         					if (status == "ok") {
	         						setTimeout(function(){
	         							$('#table-BindingMngmt').dataTable().fnClearTable();
	         							$('#bindMngmntReterive').click();
	         						}, 1000); 
	         						//$('#table-BindingMngmt').DataTable().row(indexval).remove().draw();
	         					}else{
	         						swal("Unsuccessfully");
	         					}
	         		});
	        	 }
	        	 
	        	 if(type == "S"){
	        		 $.get("managementBinding",{
	         				liferayLogin : liferayLogin,
					  		bindno : bindnoArray.toString(),
					  		order : orderArray.toString(),
					  		total : total,
					  		type : "S"
	         				},
	         				
	         				function(message){
	         					var status = message.replace(/\s+/g, '');
	         					if (status == "ok") {
	         						setTimeout(function(){
	         							$('#table-BindingMngmt').dataTable().fnClearTable();
	         							$('#bindMngmntReterive').click();
	         						}, 1000); 
	         						//$('#table-BindingMngmt').DataTable().row(indexval).remove().draw();
	         					}else{
	         						swal("Unsuccessfully");
	         					}
	         		});
	        	 }
	          }
		 	},
		 	function() {
	        }
		   ).fail(function(data){
		 	 swal("fail");
		   }).success(function(data){
		});

	    
	});
	///////////////////////////////////////////////////////////////////////////
});