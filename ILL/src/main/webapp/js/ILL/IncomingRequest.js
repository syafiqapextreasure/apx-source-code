$(document).ready(function() {
	
	/*$.validate({
	    modules : 'sanitize'
	});*/
	
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
	
	//////////////////////////////set cal view/////////////////////////////////
	$("input[name='txtRequestedDate']").datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
		startDate:  moment(today).format("DD/MM/YYYY"),
	}).on('changeDate', function(e) {
        // Revalidate the date field
		 var minDate = $("input[name='txtRequestedDate']").val();
		// Destroy 
		  $("input[name='txtSentDate']").datepicker('destroy');
		  
		  $("input[name='txtSentDate']").datepicker({
				format: "dd/mm/yyyy",
				todayBtn: true,
				autoclose: true,
				todayHighlight: true,
				startDate:  minDate,
				//endDate:'+12m',
		  });
    });
	
	$("input[name='txtRequestedDate']").datepicker( 'setDate', moment(today).format("DD/MM/YYYY") );
	
	$("input[name='txtSentDate']").datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
		startDate: moment(today).format("DD/MM/YYYY"),
		//endDate:'+12m',
	});
	
	///////////////////// Set Monograph_table  ////////////////////////////////
	$('#incomReq_table').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	///////////////////// Set Current Date  ///////////////////////////////////
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	
	///////////////////// Set text autocomplete off////////////////////////////
	$('input[type="text"]').attr('autocomplete', 'off');
	
	////////////////////////////// Max Length /////////////////////////////////
	$("#txtMaterial").attr('maxlength','10');
	$("#txtPatron").attr('maxlength','12');
	$("#txtContact").attr('maxlength','50');
	
	//////////////////////////////Clear All Modal /////////////////////////////
	$('#searchInRequest').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $("#input-criteria").val("");
	});
	
	$('#modalincomReq').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $('#formdataIncomReq').data('bootstrapValidator').resetForm();
	    $('#txtAccession').empty();
	});
	
	//////////////////////////////when search modal open///////////////////////
	$('#searchInRequest').on('shown.bs.modal', function () {
		
		$("input").prop("disabled", false);
		$("input[type=radio]").attr('disabled', false);
		$("#input-criteria").focus();
		
		$("input[name=input-criteria]").attr('maxlength','10');
		
		$('input[name=searchSelection]').change(function(){
			 var value = $( 'input[name=searchSelection]:checked' ).val(); 
			 
			$("input[name=input-criteria]").val('');
			if(value == "ctrlNo"){
				$("#input-criteria").focus();
				$("input[name=input-criteria]").attr('maxlength','10');
		    }else if(value == "reqID"){
		    	$("#input-criteria").focus();
		    	$("input[name=input-criteria]").attr('maxlength','12');
		    }
			
		});
	});
	
	//***************************************AREA FOR SEARCH ********************************************//
	$('#search').click(function(){
		$('#incomReq_table').dataTable().fnClearTable();
		
		var input_criteria = $('#input-criteria').val();
		var search_type = $("input[name='searchSelection']:checked").val();
		
		$('#incomReq_table').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//info: false,
		    ajax: {
		    	url: "resultSearch",
		        data : {input_criteria : input_criteria, search_type : search_type},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){
		            	var stat = json[i].Status;
		            	switch(stat){
		        	  	case state = 'N':
		        	  		stat = "New Request";
		        	  		break;
		        	  	case state = 'R':
		        	  		stat = "Returned";
		        	  		break;
		        	  	case state = 'P':
		        	  		stat = "Charged";
		        	  		break;
		        		}
		              return_data.push({
		            	    'No': (i+1),
			                'Request No': json[i].RequestNo,
			                'Control No' : json[i].Material, 
			                'Accession No' : json[i].Accession,
			                'Title' : json[i].Title,
			                'Requestor Name' : json[i].Name,
			                'Request Date' : json[i].RequestedDate,
			                'Status' : stat,
			                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalincomReq" data-whatever="View Incoming Request" title="View Incoming Request" data-rowid="'+json[i].RequestNo+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalincomReq" data-whatever="Edit Incoming Request" title="Edit Incoming Request" data-rowid="'+json[i].RequestNo+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].RequestNo+'" data-id2="'+json[i].Accession+'"><span class="glyphicon glyphicon-trash" title="Delete Incoming Request" ></span></button>',
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Request No'},
					{'data': 'Control No'},
					{'data': 'Accession No'},
					{'data': 'Title'},
					{'data': 'Requestor Name'},
					{'data': 'Request Date'},
					{'data': 'Status'},
					{'data': 'Action'},
				],
				drawCallback: function( settings ) {
					$('#incomReq_table tbody tr').each(function() {
				          var Cell = $(this).find('td:eq(3)');
				          //alert("dss"+Cell.text() +"dsd");
				          debugger;
				          if((Cell.text() == ' ') || (Cell.text() == '')){
				        	  $(this).find('#Edit').prop("disabled", false);
				        	  $(this).find('#Delete').prop("disabled", false);
				          }else{
				        	  $(this).find('#Edit').prop("disabled", true);
				        	  $(this).find('#Delete').prop("disabled", true);
				          }
				          
				     });
			    }
		});

		$("#closesearch").click();  
	});
	///$("#Delete").prop('disabled', true);
	//****************************************** AREA FOR MODAL *****************************************//
	$('#modalincomReq').on('show.bs.modal', function (event) {
		
		
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');		

		modal.find('.modal-title').text(recipient);
		
		switch(recipient){
		
			case state = 'Add Incoming Request':
				enable();
				$("input[name='txtRequestedDate']").datepicker('setDate', moment(today).format("DD/MM/YYYY"));
				$("input[name='txtSentDate']").datepicker('setDate', null);
				$("input[name='txtRequestedDate']").val(moment(today).format("DD/MM/YYYY"));
				$('.daterec').text(moment(today).format("DD/MM/YYYY"));
		  		$(".recby").text(liferayLogin);
		  		$("#previewbtn").attr('disabled', true);
		  		$(".lblRequestNo").text("New Request Number");
				break;
			case state = 'Edit Incoming Request':
				enable();
			    $("#previewbtn").attr('disabled', true);
			    $("#searchMaterial").attr('disabled', true);
			    $("#txtMaterial").attr('disabled', true);
			    $("#txtAccession").attr('disabled', false);
				$.get('GetEditView', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							var status = value['Status'];
	
							switch(status){
							case state = 'N':
			        	  		stat = "New Request";
			        	  		break;
			        	  	case state = 'R':
			        	  		stat = "Returned";
			        	  		break;
			        	  	case state = 'P':
			        	  		stat = "Charged";
			        	  		break;
							}
							
							var cService = value['Service'];
							var cHandling = value['Handling'];
							var cDiscount = value['Discount'];
							
							/*var cPercentage = ((parseFloat(cService) + parseFloat(cHandling)) * (cDiscount / 100));
					        var cAmount = (parseFloat(cService) + parseFloat(cHandling) - parseFloat(cPercentage)).toFixed(2);*/
							
							$(".lblRequestNo").text(value['RequestNo']);
							$(".lblStatus").text(stat);
							$("#txtMaterial").val(value['Material']);
							$("#lblTitle").val(value['Title']);
							$("#txtAccession").val(value['Accession']);
							$("#txtService").val(cService.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,'));
							$("input[name='txtRequestedDate']").val(value['RequestedDate']);
							$("#txtHandling").val(cHandling.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,'));
							$("input[name='txtSentDate']").val(value['SentDate']);
							$("#txtSentDate").val(value['SentDate']);
							$("#txtDiscount").val(cDiscount.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,'));
							$(".lblAmount").text(value['Amount'].toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,'));

							$("#txtPatron").val(value['Patron']);
							$(".lblName").text(value['Name']);
							$(".lblAdd1").text(value['Add1']);
							$(".lblAdd2").text(value['Add2']);
							$(".lblAdd3").text(value['Add3']);
							$(".lblPostCode").text(value['PostCode']);
							$(".lblTelNo").text(value['TelNo']);
							$("#txtContact").val(value['Contact']);
							$(".daterec").text(value['DateCreated']);
							$(".recby").text(value['CreatedBy']);
							
							//alert("sds"+value['Accession']+"dsfs");
							$("#txtAccession").prop("selectedIndex",-1);
							setTimeout(function(){
								$('#txtAccession').empty(); 
				 				$("#txtAccession").append("<option value=''></option>");
								
				 				//$("#txtAccession").prop("disabled", false);
								/*alert(value['Accession'] +"dsfs");
								if(value['Accession'] != ""){
									$('option:gt(0)', '#txtAccession').remove();
								}*/
									
				 				$.get('GetAccession', {
						        	code : value['Material']
								 	}, function(responseJson) {
									if(responseJson != null){
										$.each(responseJson, function(key,value) {
											var stat = value['Status'];
											
											switch(stat){
									        case state = 'A':
									        	stat = "Available";
										  		break;
									        case state = 'G':
									        	stat = "ILL Request";
										  		break;
										    }
											//$("#div-vendorName").text(value['vendorName']);
											$("#txtAccession").append("<option value='"+value['Accession']+"'>" + value['Accession'] + " - " + stat + "</option>");
										});
									}
								});
				 			},800); 
						});
					}
				});
				break;
			case state = 'View Incoming Request':
				disable();
				$.get('GetEditView', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							var status = value['Status'];
	
							switch(status){
							case state = 'N':
			        	  		stat = "New Request";
			        	  		break;
			        	  	case state = 'R':
			        	  		stat = "Returned";
			        	  		break;
			        	  	case state = 'P':
			        	  		stat = "Charged";
			        	  		break;
							}
							
							var cService = value['Service'];
							var cHandling = value['Handling'];
							var cDiscount = value['Discount'];
							
							/*var cPercentage = ((parseFloat(cService) + parseFloat(cHandling)) * (cDiscount / 100));
					        var cAmount = (parseFloat(cService) + parseFloat(cHandling) - parseFloat(cPercentage)).toFixed(2);*/
							
							$(".lblRequestNo").text(value['RequestNo']);
							$(".lblStatus").text(stat);
							$("#txtMaterial").val(value['Material']);
							$("#lblTitle").val(value['Title']);
							
							
							$("#txtAccession").prop("selectedIndex",-1);
							setTimeout(function(){
								$("#txtAccession").append("<option value='"+value['Accession']+"'>" + value['Accession'] +  "</option>");
				 			},800); 
							
							
							$("#txtService").val(cService.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,'));
							$("input[name='txtRequestedDate']").val(value['RequestedDate']);
							$("#txtHandling").val(cHandling.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,'));
							$("input[name='txtSentDate']").val(value['SentDate']);
							$("#txtSentDate").val(value['SentDate']);
							$("#txtDiscount").val(cDiscount.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,'));
							$(".lblAmount").text(value['Amount'].toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,'));
							//$("#lblAmount").val(cAmount);
							$("#txtPatron").val(value['Patron']);
							$(".lblName").text(value['Name']);
							$(".lblAdd1").text(value['Add1']);
							$(".lblAdd2").text(value['Add2']);
							$(".lblAdd3").text(value['Add3']);
							$(".lblPostCode").text(value['PostCode']);
							$(".lblTelNo").text(value['TelNo']);
							$("#txtContact").val(value['Contact']);
							$(".daterec").text(value['DateCreated']);
							$(".recby").text(value['CreatedBy']);
						});
					}
				});
			break;
		}
	});
	
	//***************************************AREA FOR FUNCTION ******************************************//
	
	//////////////////////////////Disable//////////////////////////////////////
	function disable(){
		$("input").attr('disabled','disabled');
  		$("#save, #cancel, #previewbtn").hide();
  		$("textarea").prop('disabled', true);
  		$("#searchMaterial, #searchPatron").attr('disabled', 'disabled');
  		$('#txtAccession').prop('disabled', true);
	}
	
	///////////////////////////Enable/////////////////////////////////////////
	function enable(){
		$("input").prop("disabled", false);
  		$("#save, #cancel, #previewbtn").show();
  		$("#close").hide();
  		$("#searchMaterial, #searchPatron").attr('disabled', false);
  		$(".lblAmount, .lblRequestNo, .lblStatus").empty();
  		$(".lblName, .lblAdd1, .lblAdd2, .lblAdd3").empty();
  		$(".lblPostCode, .lblTelNo").empty();
  		$(".daterec, .recby").empty();
  		//$('#txtAccession').prop('disabled', false);
	}
	
	function clearInput(){
		$("#txtPatron, #txtContact").val("");
		$(".lblName, .lblAdd1, .lblAdd2, .lblAdd3").empty();
  		$(".lblPostCode, .lblTelNo").empty();
	}
	
	///////////////////////////Total Amount/////////////////////////////////////////
	function GetTotalAmount(Service, Handling, Discount){
		
		var Percentage;
		var Amount;
		
		var Percentage = ((parseFloat(Service) + parseFloat(Handling)) * (Discount / 100));
        var Amount = (parseFloat(Service) + parseFloat(Handling) - parseFloat(Percentage)).toFixed(2);
        
        $(".lblAmount").text(Amount);
	}
	
	////contol input value for Service Charges AND Handling Charges
	$("#txtService, #txtHandling").on("keypress keyup blur",function (event) {
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
	
	/////key in number only Discount
	$("#txtDiscount").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });
	
	//////Service Charges key up
	$("#txtService, #txtHandling, #txtDiscount").keyup(function(){

		var Service = $("#txtService").val();
		var Handling = $("#txtHandling").val();
		var Discount = $("#txtDiscount").val();
		
		if(Service==""){
			Service = "0";
		}
		
		if(Handling==""){
			Handling = "0";
		}
		
		if(Discount==""){
			Discount = "0";
		}
		
		GetTotalAmount(Service, Handling, Discount);
	});

	//****************************************** AREA FOR keyup, keydown blur****************************//
	//////////////////////////////CONTROL NUMBER blur////////////////////////////////
	$("#txtMaterial").blur(function(e){
		var material = $("#txtMaterial").val();
		var patron = $("#txtPatron").val();
		var contact = $("#txtContact").val();

		if((material.length>0) && (patron.length>0) && (contact.length>0)){
			$("#previewbtn").attr('disabled', false);
		}else{
			$("#previewbtn").attr('disabled', true);
		}
		
		$.get('CheckMatExist', {
        	code : material
		 	}, function(responseJson) {
		 		
		 		if(responseJson == 0){
		 			messageBox("126","","");
		 			$("#txtMaterial").val("");
		 			 $('#formdataIncomReq').data('bootstrapValidator').resetForm();
		 		}else{
		 			
		 			$.get('GetTitle', {
	 					code : material
	 		        }, function(responseJson) {
	 		    		$("#lblTitle").val(responseJson);		
	 		        });
		 			
		 			//$("#txtAccession").append("<option value=''></option>");
		 			
		 			setTimeout(function(){
		 				$("#txtAccession").prop("disabled", false);
		 				$('#txtAccession').empty(); 
		 				//$("#txtAccession").append("<option value=''></option>");
		 				$.get('GetAccession', {
				        	code : material
						 	}, function(responseJson) {
							if(responseJson != null){
								$.each(responseJson, function(key,value) {
									var stat = value['Status'];
									
									switch(stat){
							        case state = 'A':
							        	stat = "Available";
								  		break;
							        case state = 'G':
							        	stat = "ILL Request";
								  		break;
								    }
									//$("#div-vendorName").text(value['vendorName']);
									$("#txtAccession").append("<option value='"+value['Accession']+"'>" + value['Accession'] + " - " + stat + "</option>");
								});
							}
						});
		 			},800); 
		 			
		 		}
		});
	});
	
	//clear title when CNTRL NO keydown backspace
	$("#txtMaterial").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#lblTitle").val("");
			$('#txtAccession').empty(); 
			$("#txtAccession").append("<option value=''></option>");  
		}
	});
	
	/////////////////////////function select
	
	
	
	//////////////////////////////Patron blur////////////////////////////////
	/*$("#txtPatron").blur(function(e){
		
		var material = $("#txtMaterial").val();
		var patron = $("#txtPatron").val();
		var contact = $("#txtContact").val();

		if((material.length>0) && (patron.length>0) && (contact.length>0)){
			$("#previewbtn").attr('disabled', false);
		}else{
			$("#previewbtn").attr('disabled', true);
		}
		
		
		$.get("Handler_Patron",
					{code : $("#txtPatron").val(),
        	accno : $("#txtAccession").val(),
        	ctrlno : $("#txtMaterial").val(),}
					,function(json){
						var msg = json.trim();
						
						//alert("msg" +msg +"lll");
						if(msg == "Invalid Requestor ID"){
							messageBox("154","",""); 
							clearInput();
						}
						
						if(msg == "Invalid Group Category"){
							messageBox("151","",""); 
							clearInput();
						}
						
						if(msg == "Patron does not have CHARGE status"){
							messageBox("152","",""); 
							clearInput();
						}
						
						//21082020
						if(msg == "Valid" || msg == ""){
							setTimeout(function(){
								$.get('GetCheckPatronExist', {
						        	code : $("#txtPatron").val(),
						        	accno : $("#txtAccession").val(),
						        	ctrlno : $("#txtMaterial").val(),
								 	}, function(responseJson) {
								 		
								 		if(responseJson==''){
								 			messageBox("036","",""); 
								 			$("#txtPatron, #txtContact").val("");
								 		}else{
								 			$.each(responseJson, function(key,value) {
								 				
								 				$(".lblName").text(value['Name']);
												$(".lblAdd1").text(value['Add1']);
												$(".lblAdd2").text(value['Add2']);
												$(".lblAdd3").text(value['Add3']);
												$(".lblPostCode").text(value['PostCode']);
												$(".lblTelNo").text(value['TelNo']);
												
								 			});
								 		}
								});
				 			},800); 
						}
						
						if(msg == "membership had expired"){
							messageBox("032","",""); 
							clearInput();
						}
						
						if(msg == "membership will not be activated until"){
							setTimeout(function(){
								$.get('GetCheckPatronExist', {
						        	code : $("#txtPatron").val(),
						        	accno : $("#txtAccession").val(),
						        	ctrlno : $("#txtMaterial").val(),
								 	}, function(responseJson) {
								 		if(responseJson==''){
								 		}else{
								 			$.each(responseJson, function(key,value) {
								 				var memdate = value['PatronMembershipDate'];
								 				messageBox("153",memdate,"@date");
												clearInput();
								 			});
								 		}
								});
				 			},500); 
						
						}
						
						if(msg == "Patron has exceeded the eligibility to charge/borrow items"){
							messageBox("155","",""); 
							clearInput();
						}
						
						if(msg == "The borrower has requested this item by Control Number"){
							messageBox("156","",""); 
							clearInput();
						}
						
						if(msg == "The borrower is not eligible to charge/borrow this item"){
							messageBox("157","",""); 
							clearInput();
						}
						
		    			
		});*/
		
			//////////////////////////////Patron blur////////////////////////////////
$("#txtPatron").blur(function(e){
    
    var material = $("#txtMaterial").val();
    var patron = $("#txtPatron").val();
    var contact = $("#txtContact").val();
    
    if (patron.length === 0) {
        $('#formdataIncomReq').data('bootstrapValidator').resetForm();
    }

    if((material.length>0) && (patron.length>0) && (contact.length>0)){
        $("#previewbtn").attr('disabled', false);
    }else{
        $("#previewbtn").attr('disabled', true);
    }
    
    $.get("Handler_Patron",
        {
            code: $("#txtPatron").val(),
            accno: $("#txtAccession").val(),
            ctrlno: $("#txtMaterial").val()
        },
        function(json){
            var msg = json.trim();
            
            // Handle different messages
            switch(msg) {
                case "Invalid Requestor ID":
                    messageBox("154","","");
                    $('#formdataIncomReq').data('bootstrapValidator').resetForm();
                    clearInput();
                    break;
                case "Invalid Group Category":
                	$('#formdataIncomReq').data('bootstrapValidator').resetForm();
                     messageBox("151","","");
                    clearInput();
                    break;
                case "Patron does not have CHARGE status":
                    messageBox("152","","");
                    $('#formdataIncomReq').data('bootstrapValidator').resetForm();
                    clearInput();
                    break;
                case "Valid":
                case "":
                    setTimeout(function(){
                        $.get('GetCheckPatronExist', {
                            code: $("#txtPatron").val(),
                            accno: $("#txtAccession").val(),
                            ctrlno: $("#txtMaterial").val(),
                        }, function(responseJson) {
                            if(responseJson == ''){
                                // messageBox("036","","");
                                $("#txtPatron, #txtContact").val("");
                            } else {
                                $.each(responseJson, function(key, value) {
								//alert(value['Name']);
                                    $(".lblName").text(value['Name']);
                                    $(".lblAdd1").text(value['Add1']);
                                    $(".lblAdd2").text(value['Add2']);
                                    $(".lblAdd3").text(value['Add3']);
                                    $(".lblPostCode").text(value['PostCode']);
                                    $(".lblTelNo").text(value['TelNo']);
                                });
                            }
                        });
                    }, 800); 
                    break;
                case "membership had expired":
                    messageBox("032","","");
                    $('#formdataIncomReq').data('bootstrapValidator').resetForm();
                    clearInput();
                    break;
                case "membership will not be activated until":
                    setTimeout(function(){
                        $.get('GetCheckPatronExist', {
                            code: $("#txtPatron").val(),
                            accno: $("#txtAccession").val(),
                            ctrlno: $("#txtMaterial").val(),
                        }, function(responseJson) {
                            if(responseJson != ''){
                                $.each(responseJson, function(key, value) {
                                    var memdate = value['PatronMembershipDate'];
                                    $('#formdataIncomReq').data('bootstrapValidator').resetForm();
                                    messageBox("153", memdate, "@date");
                                    clearInput();
                                });
                            }
                        });
                    }, 500); 
                    break;
                case "Patron has exceeded the eligibility to charge/borrow items":
                	$('#formdataIncomReq').data('bootstrapValidator').resetForm();
                     messageBox("155","","");
                    clearInput();
                    break;
                case "The borrower has requested this item by Control Number":
                	$('#formdataIncomReq').data('bootstrapValidator').resetForm();
                     messageBox("156","","");
                    clearInput();
                    break;
                case "The borrower is not eligible to charge/borrow this item":
                	$('#formdataIncomReq').data('bootstrapValidator').resetForm();
                    messageBox("157","","");
                    clearInput();
                    break;
            }
        });
		
		/*$.get('GetCheckPatronExist', {
        	code : $("#txtPatron").val(),
        	accno : $("#txtAccession").val(),
        	ctrlno : $("#txtMaterial").val(),
		 	}, function(responseJson) {
		 		
		 		if(responseJson==''){
		 			messageBox("036","",""); 
		 			$("#txtPatron, #txtContact").val("");
		 		}else{
		 			$.each(responseJson, function(key,value) {
		 				var cateGroup = value['checkGroup'];
						var cate = value['PatronCategory']; 
						
						if(cateGroup != cate){
							messageBox("151","",""); 
							$("#txtPatron, #txtContact").val("");
						}else{
							var expdate = value['PatronExpiryDate'];
							var memdate = value['PatronMembershipDate'];
							
							if(moment(moment(today, 'DD-MM-YYYY')).format('YYYY-MM-DD') > moment(moment(expdate, 'DD-MM-YYYY')).format('YYYY-MM-DD')){
								messageBox("005","Add","@action");
								$("#txtPatron, #txtContact").val("");
							}else{
								if(value['PatronCharge'] != 'Y'){
									messageBox("152","",""); 
									$("#txtPatron, #txtContact").val("");
								}else{
									$(".lblName").text(value['Name']);
									$(".lblAdd1").text(value['Add1']);
									$(".lblAdd2").text(value['Add2']);
									$(".lblAdd3").text(value['Add3']);
									$(".lblPostCode").text(value['PostCode']);
									$(".lblTelNo").text(value['TelNo']);
								}
							}
							
							if(moment(moment(today, 'DD-MM-YYYY')).format('YYYY-MM-DD') < moment(moment(memdate, 'DD-MM-YYYY')).format('YYYY-MM-DD')){
								///messageBox("032","",""); 
								messageBox("153",memdate,"@date");
								$("#txtPatron, #txtContact").val("");
							}

						}
						
						
		 			});
		 		}
		});*/
		
	});
	
	//////////////////////////////CONTROL NUMBER blur////////////////////////////////
	$("#txtContact").blur(function(e){
		var material = $("#txtMaterial").val();
		var patron = $("#txtPatron").val();
		var contact = $("#txtContact").val();

		if((material.length>0) && (patron.length>0) && (contact.length>0)){
			$("#previewbtn").attr('disabled', false);
		}else{
			$("#previewbtn").attr('disabled', true);
		}
	});
	
	//////////////////////////////PREVIEW BUTTON CLICK////////////////////////////////
	$("#previewbtn").click(function(e){

		var sControlNo = $("#txtMaterial").val(); 
	    var sRequestor = $("#txtPatron").val();
	    var RequestedDate = $("input[name='txtRequestedDate']").val();
	    var requestno = $(".lblRequestNo").text();
	    var sAccessionNo = $("#txtAccession").val();
	    
		$.post("GeneratePreviewDocument", {sControlNo: sControlNo,
				sRequestor : sRequestor,
			 	liferayLogin : liferayLogin,
			 	action : "EL01",
			 	requestno : requestno,
			 	RequestedDate : RequestedDate,
			 	sAccessionNo : sAccessionNo,
			 	action2 : "preview"
		}, function(result){

				 var w = window.open('about:blank');
				 	w.document.open();
				 	w.document.write(result);
				 	w.document.close();
				 	//w.print();
		});
	});
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	
	///////////////////////////////////////delete////////////////////////////////////////////
	$('#incomReq_table tbody').on( 'click', '#Delete', function () {
		var deleteId = $(this).attr('data-id');
		var accno = $(this).attr('data-id2');
		//alert(deleteFundAccountChar);

		var index = $('#incomReq_table').DataTable().rows({ search: 'applied'})
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
					url : "Handler_Delete",
					data: { code: deleteId,
							loginid : liferayLogin,
							accno : accno},
						success : function(result) {
							var status = result.replace(/\s+/g, '');
							if (status == "ok") {
								messageBox("005","Deleted","@action");
								/*swal('Successfully Deleted!',
									'The record has been successfully removed.');*/
								$('.swal2-confirm').click(
									function() {
										$('#incomReq_table').DataTable().row(index).remove().draw();
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
	
	
	
});