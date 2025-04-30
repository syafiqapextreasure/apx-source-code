$(document).ready(function() {
	
	/*$.validate({
	    modules : 'sanitize'
	});
	*/
	//////////////////////////////set cal view/////////////////////////////////
	$('#dsent').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	$('#dexpect').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
		//clearBtn : true,
	});
	
	$('#dsent').datepicker( 'setDate', moment(today).format("DD/MM/YYYY") );
	///////////////////// Set Monograph_table  ////////////////////////////////
	$('#Monograph_table').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	///////////////////// Set Current Date  ///////////////////////////////////
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	var year = moment().format('YYYY');
	
	///////////////////// Set text autocomplete off////////////////////////////
	$('input[type="text"]').attr('autocomplete', 'off');
	
	////////////////////////////// Max Length /////////////////////////////////
	$("#accessionNo").attr('maxlength','10');
	$("#officer").attr('maxlength','12');
	$("#controlNo").attr('maxlength','10');
	$("#editissuevolDes, #spineTitle").attr('maxlength','255');
	$("#binder, #year").attr('maxlength','4');
	$("#remarks").attr('maxlength','50');
	$("#cColor, #lColor").attr('maxlength','20');
	
	//////////////////////////////Clear All Modal /////////////////////////////
	$('#searchMonograph').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	});
	
	$('#modalMonograph').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $('#formdataMonograph').data('bootstrapValidator').resetForm();
	    /*$("#dexpect").datepicker
	    (
	        {
	            beforeShow: function (input, inst)
	            {
	                setTimeout
	                (
	                    function () 
	                    { 
	                        $('.ui-datepicker-clear').bind('click', function() { $(input).val(''); });
	                    }, 
	                    0
	                );
	            }
	        }
	    );*/
		
	    //$("#dsent, #dexpect").datepicker("refresh");
	    //$('#dsent').datepicker('setDate', null);
	    //$('#dexpect').datepicker('setDate', null);
	    //$('#dsent').datepicker({ dateFormat: 'dd/mm/yyyy' });
	});
	
	/*$('#searchMonograph').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	});*/
	
	//////////////////////////////when search modal open///////////////////////
	$('#searchMonograph').on('shown.bs.modal', function () {
		$("input").prop("disabled", false);
		$("input[type=radio]").attr('disabled', false);
		$("#input-criteriaMono").focus();
		
		$("#datepickerSentDate").hide();
		$("input[name=input-criteriaMono]").attr('maxlength','10');
		/*$('#input-sentDate').val(moment(today).format("DD/MM/YYYY"));
		$('#input-sentDate').datepicker({
			format: "dd/mm/yyyy",
			todayBtn: true,
			autoclose: true,
			todayHighlight: true,
		});*/
		/////13102019
		$('#endSentDate').val(moment(today).format("DD/MM/YYYY"));
		$('#input-sentDate, #endSentDate').datepicker({
			format: "dd/mm/yyyy",
			todayBtn: true,
			autoclose: true,
			todayHighlight: true,
		});
		/////
		
		$('input[name=searchSelectionMono]').change(function(){
		    var value = $( 'input[name=searchSelectionMono]:checked' ).val();
		    
		    $("input[name=input-criteriaMono]").val('');
		    $("#datepickerSentDate").hide();
			$("input[name=input-criteriaMono]").show();
		    //alert(value);
		    
		    if(value == "sentDate"){
		    	$("#datepickerSentDate").show();
				$("input[name=input-criteriaMono]").hide();
		    }else if(value == "ctrlNo"){
		    	$("#datepickerSentDate").hide();
				$("input[name=input-criteriaMono]").show();
		    	$("input[name=input-criteriaMono]").attr('maxlength','10');
		    }else if(value == "binderCode"){
		    	$("#datepickerSentDate").hide();
				$("input[name=input-criteriaMono]").show();
		    	$("input[name=input-criteriaMono]").attr('maxlength','4');
		    }else{
		    	$("#datepickerSentDate").hide();
				$("input[name=input-criteriaMono]").show();
				$("input[name=input-criteriaMono]").focus();
				$("input[name=input-criteriaMono]").attr('maxlength','12');
		    }
		});
	});
	
	//****************************************** AREA FOR MODAL *****************************************//
	$('#modalMonograph').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');

		modal.find('.modal-title').text(recipient);
		
		switch(recipient){
	  	case state = 'Add New Binding Record':
	  		enable();
	  		$("#accessionNo").prop("disabled", false); 
			$("#searchAccsion").attr('disabled', false);
	  		$(".bindingNo, .refrenceNo, .stausView, .div-bindername, .callNumber, .div-officername, .isbn").empty();
	  		$('.daterec').text(moment(today).format("DD/MM/YYYY"));
	  		$(".recby").text(liferayLogin);
	  		$("#officer").val(liferayLogin);
	  		$("#currency, #typeBind").prop("selectedIndex",-1);	
	  		$("#year").val(year);
	  		$('#dsent').val(moment(today).format("DD/MM/YYYY"));
	  		
	  		setTimeout(function(){ 
		  			$.get('GetoffiName', {
		  	        	code : liferayLogin
		  			 	}, function(responseJson) {
		  				if(responseJson != null){
		  					$.each(responseJson, function(key,value) {
		  						$(".div-officername").text(value['patrName']);
		  					});
		  				}
		  		});
	  		}, 1000);
	  		
	  		

	  		break;
	  	case state = 'Edit Binding Record':
	  		enable();
	  		$("#accessionNo").prop("disabled", true); 
			$("#searchAccsion").attr('disabled', 'disabled');
  			$.get('GetEditViewMonograph', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						var status = value['status'];;
						
						switch(status){
						case state = 'R':
							$("input[name=statusRadio][value='R']").prop("checked",true);
		        	  		break;
		        	  	case state = 'S':
		        	  		$("input[name=statusRadio][value='S']").prop("checked",true);
		        	  		break;
		        	  	case state = 'P':
		        	  		$("input[name=statusRadio][value='P']").prop("checked",true);
		        	  		break;
						}
						
						$(".bindingNo").text(value['bindNo']);
						$(".refrenceNo").text(value['refno']);
						$("#accessionNo").val(value['accno']);
						$("#controlNo").val(value['ctrlno']);
						$("#editissuevolDes").val(value['title']);
						$("#officer").val(value['officer']);
						$(".div-officername").text(value['name']);
						$("#binder").val(value['binder']);
						$(".div-bindername").text(value['bindName']);
						$("#currency").val(value['currency']);
						$("#erate").val(value['rate']);
						$("#year").val(value['year']);
						$("#fcost").val(value['fcost']);
						$("#lcost").val(value['lcost']);
						$("#dsent").val(value['datesent']);
						$("#dexpect").val(value['dateexpected']);
						$("#remarks").val(value['remarks']);
						$(".isbn").text(value['issn']);
						$("#typeBind").val(value['type']);
						$("#spineTitle").val(value['spine']);
						$(".callNumber").text(value['callno']);
						$("#cColor").val(value['cover']);
						$("#lColor").val(value['lettering']);
						
						var original = value['original'];
						
						switch(original){
					  		case state = 'Y':
					  			 $("#oriCover").prop('checked', true);
					  		break;
					  		case state = 'N':
					  			 $("#oriCover").prop('checked', false);
					  		break;
						}
						
						var adver = value['adver'];
						
						switch(adver){
					  		case state = 'Y':
					  			 $("#advert").prop('checked', true);
					  		break;
					  		case state = 'N':
					  			 $("#advert").prop('checked', false);
					  		break;
						}
						
						var titlePage = value['titlePage'];
						
						switch(titlePage){
					  		case state = 'Y':
					  			 $("#titlePage").prop('checked', true);
					  		break;
					  		case state = 'N':
					  			 $("#titlePage").prop('checked', false);
					  		break;
						}
						
						var supplement = value['supplement'];
						
						switch(supplement){
					  		case state = 'Y':
					  			 $("#supp").prop('checked', true);
					  		break;
					  		case state = 'N':
					  			 $("#supp").prop('checked', false);
					  		break;
						}
						
						var index = value['index'];
						
						switch(index){
					  		case state = 'Y':
					  			 $("#indexInc").prop('checked', true);
					  		break;
					  		case state = 'N':
					  			 $("#indexInc").prop('checked', false);
					  		break;
						}
						
						var contents = value['contents'];
						
						switch(contents){
					  		case state = 'Y':
					  			 $("#contentsPage").prop('checked', true);
					  		break;
					  		case state = 'N':
					  			 $("#contentsPage").prop('checked', false);
					  		break;
						}
						
						$(".daterec").text(value['crdate']);
						$(".recby").text(value['offid']);
					});
				}
			});
	  		break;
	  	case state = 'View Binding Record':
	  		disable();
		  	$.get('GetEditViewMonograph', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						var status = value['status'];;
						
						switch(status){
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
						
						$(".bindingNo").text(value['bindNo']);
						$(".refrenceNo").text(value['refno']);
						$(".stausView").text("Status :" +stat).css({'font-weight': 'bold', 'font-style': 'italic', 'font-size': '20px'});
						$("#accessionNo").val(value['accno']);
						$("#controlNo").val(value['ctrlno']);
						$("#editissuevolDes").val(value['title']);
						$("#officer").val(value['officer']);
						$(".div-officername").text(value['name']);
						$("#binder").val(value['binder']);
						$(".div-bindername").text(value['bindName']);
						$("#currency").val(value['currency']);
						$("#erate").val(value['rate']);
						$("#year").val(value['year']);
						$("#fcost").val(value['fcost']);
						$("#lcost").val(value['lcost']);
						$("#dsent").val(value['datesent']);
						$("#dexpect").val(value['dateexpected']);
						$("#remarks").val(value['remarks']);
						$(".isbn").text(value['issn']);
						$("#typeBind").val(value['type']);
						$("#spineTitle").val(value['spine']);
						$(".callNumber").text(value['callno']);
						$("#cColor").val(value['cover']);
						$("#lColor").val(value['lettering']);
						
						var original = value['original'];
						
						switch(original){
					  		case state = 'Y':
					  			 $("#oriCover").prop('checked', true);
					  		break;
					  		case state = 'N':
					  			 $("#oriCover").prop('checked', false);
					  		break;
						}
						
						var adver = value['adver'];
						
						switch(adver){
					  		case state = 'Y':
					  			 $("#advert").prop('checked', true);
					  		break;
					  		case state = 'N':
					  			 $("#advert").prop('checked', false);
					  		break;
						}
						
						var titlePage = value['titlePage'];
						
						switch(titlePage){
					  		case state = 'Y':
					  			 $("#titlePage").prop('checked', true);
					  		break;
					  		case state = 'N':
					  			 $("#titlePage").prop('checked', false);
					  		break;
						}
						
						var supplement = value['supplement'];
						
						switch(supplement){
					  		case state = 'Y':
					  			 $("#supp").prop('checked', true);
					  		break;
					  		case state = 'N':
					  			 $("#supp").prop('checked', false);
					  		break;
						}
						
						var index = value['index'];
						
						switch(index){
					  		case state = 'Y':
					  			 $("#indexInc").prop('checked', true);
					  		break;
					  		case state = 'N':
					  			 $("#indexInc").prop('checked', false);
					  		break;
						}
						
						var contents = value['contents'];
						
						switch(contents){
					  		case state = 'Y':
					  			 $("#contentsPage").prop('checked', true);
					  		break;
					  		case state = 'N':
					  			 $("#contentsPage").prop('checked', false);
					  		break;
						}
						
						$(".daterec").text(value['crdate']);
						$(".recby").text(value['offid']);
					});
				}
			});
	  		break;
		}
	});
	//***************************************END AREA FOR MODAL *****************************************//
	
	//**********************************AREA FOR ON CHANGE CURRENCY *************************************//
	$("#currency").change(function(){

        var currency = $("#currency option:selected").val();     

        $.get('getCurrencyBankRate', {
        	currency : currency
        	}, function(responseJson) {
    			if(responseJson != null){
    				$.each(responseJson, function(key,value) {
    					$("#erate").val(value['rate']);
    					$("#erate").focus();
    				});
    			}
    			
        });
        
        setTimeout(function(){
        	var rate = $("#erate").val();
    		var foreign = $("#fcost").val();
    		
    		var totalLocalAmount = (rate * foreign).toFixed(2);

    		$("#lcost").val(totalLocalAmount);

		}, 1000); 
	});
	//******************************END AREA FOR ON CHANGE CURRENCY *************************************//
	
	
	//****************************************** AREA FOR keyup, keydown blur****************************//
	//////////////////////////////ACCSION blur////////////////////////////////
	$("#accessionNo").blur(function(e){
		var accessionNo = $("#accessionNo").val();

		$("#controlNo, #editissuevolDes, #spineTitle").val("");
 		$(".callNumber").empty();
		////display officer name
		$.get('GetAccession', {
        	code : accessionNo
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		messageBox("143","","");
		 		$("#accessionNo, #controlNo, #editissuevolDes, #spineTitle").val("");
		 		$(".callNumber").empty();
		 		$('#formdataMonograph').data('bootstrapValidator').resetForm();
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$("#controlNo").val(value['ctrlno']);
					$("#editissuevolDes").val(value['title']);
					$(".isbn").text(value['issn']);
					$("#spineTitle").val(value['title']);
					$(".callNumber").text(value['callno']);
					//$(".div-officername").text(value['patrName']);
				});
			}
		});
	});
	
	//////////////////////////////ACC BACKSPACE/////////////////////////////
	$("#accessionNo").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#controlNo, #editissuevolDes, #spineTitle").val("");
			$(".isbn, .callNumber").empty();
		}
	});
	
	//////////////////////////////OFFIC blur//////////////////////////////////
	$("#officer").blur(function(e){
		var officer = $("#officer").val();

		$(".div-officername").empty();
		////display officer name
		$.get('GetoffiName', {
        	code : officer
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		messageBox("145","","");
		 		$("#officer").val("");
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-officername").text(value['patrName']);
				});
			}
		});
	});
	
	//////////////////////////////OFFIC BACKSPACE/////////////////////////////
	$("#officer").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-officername").empty();
		}
	});
	
	//////////////////////////////BINDER blur/////////////////////////////////
	/*$("#binder").blur(function(e){
		var binder = $("#binder").val();
		//alert("vendorCode " + vendorCode);
		$(".div-bindername").empty();
		////display vendor name
		$.get('GetBinderName', {
        	code : binder
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		messageBox("144","","");
		 		$("#binder").val("");
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-bindername").text(value['vendorName']);
				});
			}
		});
	});*/
	
	$("#binder").blur(function(e) {
    var binder = $("#binder").val();

    // Check if the binder is empty
    if (binder === "") {
        // Optionally, clear the .div-bindername content if needed
        $(".div-bindername").empty();
    } else {
        // Clear any previous error message
        // (assuming messageBox clears the message when called with empty parameters)
        /////////////messageBox("144", "", "");

        // Display vendor name
        $.get('GetBinderName', {
            code: binder
        }, function(responseJson) {
            /*if (responseJson != null) {
                // Assuming .div-bindername should display all vendor names, you should append them
                $(".div-bindername").empty(); // Clear previous names
                $.each(responseJson, function(key, value) {
                    $(".div-bindername").append(value['vendorName'] + "<br>");
                });
            }else{
				messageBox("144", "", "");
			}*/
			if (responseJson != null) {
		        if (Object.keys(responseJson).length === 0) {
		            // The responseJson is not null but doesn't have any values
		            // You can display a message or perform another action here
		            messageBox("144", "", "");
		            $("#binder").val("");
		            $(".div-bindername").empty();
		            // Get a reference to the Bootstrap Validator instance for your form
					var validator = $('#formdataMonograph').data('bootstrapValidator');
					// Reset the specific input field by its name or ID (replace 'myInputField' with your field's name or ID)
					validator.resetField('binder');
		        } else {
		            // Assuming .div-bindername should display all vendor names, you should append them
		            $(".div-bindername").empty(); // Clear previous names
		            $.each(responseJson, function(key, value) {
		                $(".div-bindername").append(value['vendorName'] + "<br>");
		            });
		        }
		    } else {
		        // Handle the case where responseJson is null
		       
		    }
        });
    }
});

	
	
	//////////////////////////////BINDER BACKSPACE/////////////////////////////
	$("#binder").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-bindername").empty();
		}
	});
	
	//////////////////////////////FOREIGN AMOUNT///////////////////////////////
	$("#fcost").keyup(function(){
		var rate = $("#erate").val();
		var foreign = $("#fcost").val();
		var totalLocalAmount = (rate * foreign);

		if(rate==""){
			$("#lcost").val("0");
		}else{
			$("#lcost").val(parseFloat(totalLocalAmount).toFixed(2));
		}
	});
	
	//////////////////////////////LOCAL AMOUNT/////////////////////////////////
	$("#lcost").keyup(function(){
		var rate = $("#erate").val();
		var local = $("#lcost").val();
		var totalForeignAmount = (local / rate);
		if(rate==""){
			$("#fcost").val('');
		}else{
			$("#fcost").val(parseFloat(totalForeignAmount).toFixed(2));
		}
	});
	
	//***************************************END AREA FOR keyup, keydown*********************************//
	
	//***************************************AREA FOR FUNCTION ******************************************//
	//////////////////////////////Disable//////////////////////////////////////
	function disable(){
		$("input").attr('disabled','disabled');
  		$("#save, #cancel").hide();
  		$("#close").show();
  		$('#currency, #typeBind').prop('disabled', 'disabled');
  		$("textarea").prop('disabled', true);
  		$("#searchAccsion, #searchOffi, #searchBinder").attr('disabled', 'disabled');
  		$(".vhideStat").hide();
	}
	
	//////////////////////////////Enable///////////////////////////////////////
	function enable(){
		$("input").prop("disabled", false);
  		$("#save, #cancel").show();
  		$("#close").hide();
  		$('#currency, #typeBind').prop('disabled', false);
  		$("textarea").prop('disabled', false);
  		$("#searchOffi, #searchBinder").attr('disabled', false);
  		$(".vhideStat").show();
  		$("#controlNo").prop("disabled", true); 
		$("#editissuevolDes").prop("disabled", true);
		$("input[name=statusRadio][value='R']").prop("disabled",true);
	}
	
	///contol input value for foreign-amount and local-amount n rate///////////
	$("#fcost, #lcost, #erate").on("keypress keyup blur",function (event) {
        //this.value = this.value.replace(/[^0-9\.]/g,'');
		 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
	         if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
	             event.preventDefault();
	         }
	         if(($(this).val().indexOf('.') != -1) && ($(this).val().substring($(this).val().indexOf('.'),$(this).val().indexOf('.').length).length>4 )){
			     if (event.keyCode !== 8 && event.keyCode !== 46 ){ //exception
			         event.preventDefault();
			     }
	         }
    });
	
	//////////////Rate key in number only//////////////////////////////////////
	$("#year").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });
	
	//***********************************END AREA FOR FUNCTION ******************************************//
	
	
	//***************************************AREA FOR SEARCH ********************************************//
	$('#search').click(function(){
		$('#Monograph_table').dataTable().fnClearTable();
		
		var input_criteria = $('#input-criteriaMono').val();
		var search_type = $("input[name='searchSelectionMono']:checked").val();
		var inputSentDate = moment($("#input-sentDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endinputSentDate = moment($("#endSentDate").val(), 'DD/MM/YYYY').format("YYYYMMDD"); 
		
		$('#Monograph_table').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//info: false,
		    ajax: {
		    	url: "resultSearchMonograph",
		        data : {input_criteria : input_criteria, search_type : search_type, inputSentDate : inputSentDate,
		        	endinputSentDate : endinputSentDate},
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
		            	'No': (i+1),
		                'Reference No': json[i].refno,
		                'Title' : json[i].title, 
		                'Date Sent' : json[i].datesent,
		                'Date Expected' : json[i].dateexpected,
		                'Status' : stat,
		                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalMonograph" data-whatever="View Binding Record" title="View Binding Record" data-rowid="'+json[i].refno+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalMonograph" data-whatever="Edit Binding Record" title="Edit Binding Record" data-rowid="'+json[i].refno+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].refno+'"><span class="glyphicon glyphicon-trash" title="Delete Binding" ></span></button>',
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Reference No'},
					{'data': 'Title'},
					{'data': 'Date Sent', className: "text-center"},
					{'data': 'Date Expected', className: "text-center"},
					{'data': 'Status'},
					{'data': 'Action'},
				],
				drawCallback: function( settings ) {
					$('#Monograph_table tbody tr').each(function() {
				          var Cell = $(this).find('td:eq(5)');
				          //alert(Cell.text());
				          debugger;
				          if((Cell.text() == 'Pending')){
				        	  $(this).find('#Edit').prop("disabled", false);
				        	  $(this).find('#Delete').prop("disabled", false);
				          }else{
				        	  $(this).find('#Edit').prop("disabled", true);
				        	  $(this).find('#Delete').prop("disabled", true);
				          }
				        });
			    }
		});

		$("#closesearchMonograph").click();  

	});
	//***********************************END AREA FOR SEARCH ********************************************//
	
	//***************************************AREA FOR DELETE ********************************************//
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
	
	$('#Monograph_table tbody').on( 'click', '#Delete', function () {
		var deleteMono = $(this).attr('data-id');
		//alert(deleteFundAccountChar);

		var index = $('#Monograph_table').DataTable().rows({ search: 'applied'})
		.nodes().to$().index($(this).closest('tr'));
		//alert(index);
		
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
					url : "Handler_DeleteMono",
					data: { refno: deleteMono,
							loginid : liferayLogin},
						success : function(result) {
							var status = result.replace(/\s+/g, '');
							if (status == "ok") {
								messageBox("005","Deleted","@action");
								/*swal('Successfully Deleted!',
									'The record has been successfully removed.');*/
								$('.swal2-confirm').click(
									function() {
										$('#Monograph_table').DataTable().row(index).remove().draw();
										//location.reload();
								});
							}else{
	     						swal("Unsuccessfully");
	     					}/*else {
								messageBox("131","","@action");
								swal(
									'Not Deleted','This record cannot be deleted.');
							}*/
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