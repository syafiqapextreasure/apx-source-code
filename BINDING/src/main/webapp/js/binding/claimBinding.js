$(document).ready(function() {
	
	function messageBox(code, criteria, label){
		var url = "Error_Message?GL79ERRCODE="+code+"" + 
			"&criteria=" + criteria + "&label="+label+"";
		$.ajax({
				url: url,
				success: function(result) {
					//swal('',result, 'info' );
					swal(result);
				}
			});
	}
	//////////////////////////////////////////////////////////////////////////
	
	var liferayLogin = $("#liferayLogin").val();
	$("#criteria").focus();
	$("#datepicker").hide();
	$("#Claims").hide();
	$("#claimsReterive").attr('disabled', 'disabled');
	
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
	$('#table-BindClaim').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	///////////////////////////////ON CHANGE TYPE//////////////////////////////
	$('input[name=radioOption]').change(function(){
		var value = $( 'input[name=radioOption]:checked' ).val();
		
		if(value == "bindercode"){
			$("#datepicker").hide();
			$("input[name=criteria]").show();
			$("input[name=criteria").focus();
			//$("#Claims").attr('disabled', false);
		}else if(value=="dateClaims"){
			$("#datepicker").show();
			$("input[name=criteria]").hide();
			$("#claimsReterive").attr('disabled', false);
		}
	});
	
	$(".criteria").keyup(function() {

		var criteria = $(".criteria").val();
		
		if(criteria.length >= 1){
			$('#claimsReterive').attr('disabled', false);
		}else{
			$('#claimsReterive').attr('disabled', 'disabled');
		}
	});
	
	////////Date /////
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
	
	//////////////////////////////TAB OR ENTER RETRIEVE////////////////////////
	$("#criteria").keydown(function(e){ 
		var reviewlist = $("#reviewListNo").val();
		
		var code = e.keyCode || e.which;
		if(code == '9' ||code == '13' ){
			setTimeout(function(){ 
				$('#claimsReterive').click();
  		}, 1000);
			event.preventDefault();	
		}else if(code == '8'){
			$('#table-BindClaim').dataTable().fnClearTable();	
		}
	});
	
	//////////////////////////////RETRIEVE BUTTON CLICK////////////////////////
	$('#claimsReterive').click(function(){
		
		$('#table-BindClaim').dataTable().fnClearTable();
		
		var input_criteria = $('#criteria').val();
		var search_type = $("input[name='radioOption']:checked").val();
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		
		$('#panel-result').collapse('show');
		$("#Claims").show().prop('disabled', true);
		
		$('#table-BindClaim').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//info: false,
		    ajax: {
		    	url: "resultClaim",
		        data : {input_criteria : input_criteria, search_type : search_type, 
		        	startSentDate : startSentDate, endSentDate : endSentDate},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){

		              return_data.push({
		            	'': '<input type="checkbox" id="example-select-all" name="id[]" value=" '+json[i].bindno+' ">',
		                'Binding No': json[i].bindno,
		                'Reference No' : json[i].refno, 
		                'Title' : json[i].title,
		                'Binder' : json[i].binderName,
		                'Date Sent' : json[i].datesent,
		                'Date Expected' : json[i].dateexpected
		            })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': ''},
					{'data': 'Binding No'},
					{'data': 'Reference No'},
					{'data': 'Title'},
					{'data': 'Binder'},
					{'data': 'Date Sent', className: "text-center"},
					{'data': 'Date Expected', className: "text-center"},
				],
		});
	});
	
	///////////////////////////////////////////WHEN CLICK CHECK BOX////////////
	/// 1) FOR SELECT ALL
	$('#example-select-all').on('click', function(){
		// Check/uncheck all checkboxes in the table
		var rows = $('#table-BindClaim').DataTable().rows({ 'search': 'applied' }).nodes();
		if(!this.checked){
	    	$('#example-select-all', rows).prop('checked', false);
	    	$("#Claims").attr('disabled',true);
	   	}else{
	    	$('#example-select-all', rows).prop('checked', this.checked);
	    	$("#Claims").attr('disabled',false);
	    }
	});
	
	// Handle click on checkbox to set state of "Select all" control
    $('#table-BindClaim tbody').on('change', '#example-select-all', function(){
    	
       // If checkbox is not checked
       if(!this.checked){
    	  //$(".saveRetrieve").attr('disabled',true);
          var el = $('#example-select-all').get(0);
          var totaltick = $('#table-BindClaim :input[type="checkbox"]:checked').length
          if(totaltick == 0){
        	  $("#Claims").attr('disabled',true);  
          }else{
        	  $("#Claims").attr('disabled',false);  
          }
          
          // If "Select all" control is checked and has 'indeterminate' property
          if(el && el.checked && ('indeterminate' in el)){
             // Set visual state of "Select all" control 
             // as 'indeterminate'
             el.indeterminate = true;
          }
       }else{
    	   $("#Claims").attr('disabled',false);
       }
    });
    ///////////////////////////////////////////////////////////////////////////
    
    
    ///////////////////////////Save Retrieve click/////////////////////////////
	$('#Claims').click(function(){
		
		var type = $("input[name='statusRadio']:checked").val();
		var output2 = [];
		$('#table-BindClaim').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
     		var indexval  = $('#table-BindClaim').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
			var bindnoobj = $(this).closest('tr').find('td:eq(1)').text();

     		var obj = {};
     		obj.bindnoobj = bindnoobj;
     		obj.indexval = indexval;
     		output2.push(obj);
		});
		
		var total = output2.length;
		
		///bind no
		var bindnoArray = [];
	    for (var i = 0; i < output2.length; i++) {
	 		var code = output2[i]['bindnoobj'];
	 		bindnoArray.push(code);
	 	}

		$.get("claimBinding",{
				liferayLogin : liferayLogin,
				bindno : bindnoArray.toString(),
				total : total,
				},
				
				function(message){
					var status = message.replace(/\s+/g, '');
					if (status == "ok") {
						setTimeout(function(){
							$('#table-BindClaim').dataTable().fnClearTable();
							$('#claimsReterive').click();
							messageBox("142","","");
						}, 1000); 
						var action = "bindingClaim";
				 		var url = "GeneratePreviewDocument?bindno=" + bindnoArray.toString()
						+ "&liferayLogin=" + liferayLogin
						+ "&action=" + action;
						window.open(url).print();
						
					}else{
						swal("Unsuccessfully");
					}
		});
	    
	});
	///////////////////////////////////////////////////////////////////////////
	
});