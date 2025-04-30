$(document).ready(function() {
	
	var liferayLogin = $("#liferayLogin").val();
	$('input[type="text"]').attr('autocomplete', 'off');
	$("#criteria").focus();
	$("#datepickerCheckin").hide();
	$("#checkinCtrlno").attr('disabled', 'disabled');
	$("#checkIn").hide();
	//$("#checkacc").attr('maxlength','10');
	
	var today = new Date();  
	$('#input-startDate').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	//////////////////////////////TABLE CHECK IN///////////////////////////////
	$('#table-BindCheckIn').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	//////////////////////////////Clear All Modal /////////////////////////////	
	$('#modalAcessionDetail').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $('#formdataAcessionDetail').data('bootstrapValidator').resetForm();
	});
	
	//////////////////////////////ONCHANGE RADIO BUTTON////////////////////////
	$('input[name=radioOption]').change(function(){
		
		var value = $( 'input[name=radioOption]:checked' ).val();

		$("input[type='text'][name!=end]").val('');
		$("#datepickerCheckin").hide();
		$("#checkinCtrlno").attr('disabled', 'disabled');
		$("#checkIn").hide();
		
		if(value == "ctrlno"){
			$("#datepickerCheckin").hide();
			$("input[name=criteria]").show();
			$("input[name=criteria").focus();
			$("#checkinCtrlno").attr('disabled', false);
			$("input[name=criteria]").attr('maxlength','10');
		}else if(value == "datesent"){
			$("#datepickerCheckin").show();
			$("input[name=criteria]").hide();
		}else if(value == "year"){
			$("input[name=criteria]").attr('maxlength','4');
		}else if(value == "accno"){
			$("input[name=criteria]").attr('maxlength','10');
		}else if(value == "refno"){
			$("input[name=criteria]").attr('maxlength','10');
		}else{
			$("#datepickerCheckin").hide();
			$("input[name=criteria]").show();
			$("input[name=criteria").focus();
		}
		
	});
	
	//////////////////////////////TAB OR ENTER RETRIEVE////////////////////////
	$("#criteria").keydown(function(e){ 
		var reviewlist = $("#reviewListNo").val();
		
		var code = e.keyCode || e.which;
		if(code == '9' ||code == '13' ){
			setTimeout(function(){ 
				$('#bindCheckInReterive').click();
  		}, 1000);
			event.preventDefault();	
		}else if(code == '8'){
			$('#table-BindCheckIn').dataTable().fnClearTable();	
		}
	});
	
	//////////////////////////////RETRIEVE BUTTON CLICK////////////////////////
	$('#bindCheckInReterive').click(function(){
		
		$('#table-BindCheckIn').dataTable().fnClearTable();
		
		var input_criteria = $('#criteria').val();
		var search_type = $("input[name='radioOption']:checked").val();
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		
		$('#panel-result').collapse('show');
		$("#checkIn").show().prop('disabled', true);
		
		$('#table-BindCheckIn').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			language: {
			      "emptyTable": "No current record in this range"
			},
			//info: false,
			columnDefs: [
			             {
								"targets": [ 9, 10, 11, 12 ],
								"visible": false,
								"searchable": false,
							}
			],
		    ajax: {
		    	url: "resultCheckIn",
		        data : {input_criteria : input_criteria, search_type : search_type, 
		        	startSentDate : startSentDate, endSentDate : endSentDate},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){

		              return_data.push({
		            	'': '<input type="radio" name="id[]" value=" '+json[i].bindno+' ">',
		                'Binding No': json[i].bindno,
		                'Title' : json[i].title, 
		                'Volume/Issue, Copy' : "vol." +json[i].volfrom +"-"+json[i].volto+"/iss."+json[i].issfrom+"-"+json[i].issto+",C"+json[i].copy,
		                'Binder' : json[i].bindname,
		                'Officer' : json[i].officename,
		                'Date Sent' : json[i].dsent,
		                'Accession No' : json[i].accno,
		                'Type' : json[i].type,
		                'Control No' : json[i].ctrlno,
		                'Vol From' : json[i].volfrom,
		                'Vol To' : json[i].volto,
		                'Copy' : json[i].copy
		            })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': ''},
					{'data': 'Binding No'},
					{'data': 'Title'},
					{'data': 'Volume/Issue, Copy'},
					{'data': 'Binder'},
					{'data': 'Officer'},
					{'data': 'Date Sent', className: "text-center"},
					{'data': 'Accession No'},
					{'data': 'Type', className: "text-center"},
					{'data': 'Control No'},
					{'data': 'Vol From'},
					{'data': 'Vol To'},
					{'data': 'Copy'},
				],
		});
	});
	
	//////////////////////////////RADIO BUTTON CLICK AT TABLE//////////////////
	
	$('#table-BindCheckIn tbody').on('change', 'input[type="radio"]', function(){
    	
	       // If checkbox is not checked
	       if(!this.checked){
	    	   $("#checkIn").attr('disabled',true);
	          var el = $('#example-select-all').get(0);
	          // If "Select all" control is checked and has 'indeterminate' property
	          if(el && el.checked && ('indeterminate' in el)){
	             // Set visual state of "Select all" control 
	             // as 'indeterminate'
	             el.indeterminate = true;
	          }
	       }else{
	    	   $("#checkIn").attr('disabled',false);
	       }
	});
	
	//////////////////////////////CHECK IN CLICK///////////////////////////////
	
	$("#checkIn").click(function(){
		$('#table-BindCheckIn input:radio:checked').each(function () {
			  var bindno = this.value;
			  var type = $(this).closest('tr').find('td:eq(8)').text();
			  var accno = $(this).closest('tr').find('td:eq(7)').text();
			  var indexval  = $('#table-BindCheckIn').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
			  var data = $('#table-BindCheckIn').DataTable().row($(this).closest("tr")).data();
			  var ctrlno = data['Control No'];
			  var volfrom = data['Vol From'];
			  var volto = data['Vol To'];
			  var copy = data['Copy'];
			  
			  if(type == "M"){
				  $.get("CheckInMonograph",{
					  liferayLogin : liferayLogin,
					  bindno : bindno.trim(),
					  accno : accno
				  },
				  		function(message){
	     					var status = message.replace(/\s+/g, '');
	     					if (status == "ok") {
	     						//alert("Check in process has been completed");
	     						messageBox("138","","");
	     						$('#table-BindCheckIn').DataTable().row(indexval).remove().draw();
	     					}else{
	     						swal("Unsuccessfully");
	     					}
				  });
			  }
			  
			  if(type == "S"){
				  swal({
						text: "Do you wish to create Accession Detail for the checked-in issue? Or else Spine Lable won't be transfered",
				        showCancelButton: true,
				        confirmButtonColor: "#DD6B55",
				        confirmButtonText: "Yes",
				        cancelButtonText: "No"
				      }).then(
				        function(isConfirm) {
				          if (isConfirm) {
				        	  $('#modalAcessionDetail').modal('show');
				        	  $("#checkSMD, #checkicat, #checkloca, #checkcon, #checkcurrent").prop("selectedIndex",-1);	
				        	  $("#checkCtrlno").val(ctrlno);
				        	  $("#checkvol").val(volfrom +" - "+volto);
				        	  $("#checkcopy").val(copy);
				        	  
				        	  //////////////////////Max Length ////////////////////////
				        	  $("#checkacc").attr('maxlength','10');
				        	  $("#checkspine").attr('maxlength','60');
				        	  
				        	  //**********************************AREA FOR ON CHANGE CURRENCY *************************************//
				        		$("#checkcurrent").change(function(){

				        	        var currency = $("#checkcurrent option:selected").val();     

				        	        $.get('getCurrencyBankRate', {
				        	        	currency : currency
				        	        	}, function(responseJson) {
				        	    			if(responseJson != null){
				        	    				$.each(responseJson, function(key,value) {
				        	    					$("#checkerate").val(value['rate']);
				        	    					$("#checkerate").focus();
				        	    				});
				        	    			}
				        	        });
				        	        
				        	        setTimeout(function(){
				        	        	var rate = $("#checkerate").val();
				        	    		var foreign = $("#checkfprice").val();
				        	    		var totalLocalAmount = (rate * foreign).toFixed(2);
				        	    		$("#checklprice").val(totalLocalAmount);
				        			}, 1000); 
				        		});
				        		//******************************END AREA FOR ON CHANGE CURRENCY *************************************//
				        		
				        		//////////////////////////////FOREIGN AMOUNT///////////////////////////////
				        		$("#checkfprice").keyup(function(){
				        			var rate = $("#checkerate").val();
				        			var foreign = $("#checkfprice").val();
				        			var totalLocalAmount = (rate * foreign);

				        			if(rate==""){
				        				$("#checklprice").val("0");
				        			}else{
				        				$("#checklprice").val(parseFloat(totalLocalAmount).toFixed(2));
				        			}
				        		});
				        		
				        		//////////////////////////////LOCAL AMOUNT/////////////////////////////////
				        		$("#checklprice").keyup(function(){
				        			var rate = $("#checkerate").val();
				        			var local = $("#checklprice").val();
				        			var totalForeignAmount = (local / rate);
				        			if(rate==""){
				        				$("#checkfprice").val('');
				        			}else{
				        				$("#checkfprice").val(parseFloat(totalForeignAmount).toFixed(2));
				        			}
				        		});
				        	  
				        	  ///////for validation
				        	  $('#formdataAcessionDetail').bootstrapValidator({
				        			framework: 'bootstrap',
				        			 excluded: [':disabled'],
				        			 icon: {
				        	        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
				        	        //feedbackIcons: {
				        	            valid: 'glyphicon glyphicon-ok',
				        	            invalid: 'glyphicon glyphicon-remove',
				        	            validating: 'glyphicon glyphicon-refresh'
				        	        },
				        	        fields: {
				        	        	checkacc: {
				        	        		trigger: 'blur',
				        	                validators: {
				        	                    notEmpty: {
				        	                        message: 'Accession is required'
				        	                    },
				        	                    remote: {
				        	                    	type: "GET",
				        	                        url: 'CheckingAccession',
				        	                        data: function(validator) {
				        	                            return {
				        	                            	checkacc: validator.getFieldElements('checkacc').val(),
				        	                            };
				        	                        },
				        	                        message: 'Accession already exists. Please enter a Accession',
				        	                    }
				        	                }
				        	            },
				        	            checkSMD: {
				        	                validators: {
				        	                    notEmpty: {
				        	                        message: 'SMD is required'
				        	                    },
				        	                }
				        	            },
				        	            checkicat: {
				        	                validators: {
				        	                    notEmpty: {
				        	                        message: 'Item Category is required'
				        	                    },
				        	                }
				        	            },
				        	            checkloca: {
				        	                validators: {
				        	                    notEmpty: {
				        	                        message: 'Location is required'
				        	                    },
				        	                }
				        	            },
				        	            checkcon: {
				        	                validators: {
				        	                    notEmpty: {
				        	                        message: 'Condition is required'
				        	                    },
				        	                }
				        	            },
				        	            checkspine: {
				        	                validators: {
				        	                    notEmpty: {
				        	                        message: 'Spine is required'
				        	                    },
				        	                }
				        	            },
				        	        }
				        	    })
				        	     .on('success.form.bv', function (e) {
				        	    	 
				        	    	 // Prevent form submission
				        	         e.preventDefault();

				        	         // Get the form instance
				        	         var $form = $(e.target);

				        	         // Get the BootstrapValidator instance
				        	         var bv = $form.data('bootstrapValidator');
				        	         
				        	         var checkCtrlno = $("#checkCtrlno").val();
				        	         var checkacc = $("#checkacc").val();
				        	         var checkSMD = $("#checkSMD").val();
				        	         var checkicat = $("#checkicat").val();
				        	         var checkloca = $("#checkloca").val();
				        	         var checkcon = $("#checkcon").val();
				        	         var checkfprice = $("#checkfprice").val();
				        	         var checklprice = $("#checklprice").val();
				        	         var checkvol = $("#checkvol").val();
				        	         var checkcopy = $("#checkcopy").val();
				        	         var checkspine = $("#checkspine").val();
				        	     	 
				        	     	$.get("CheckInSerial",{
				         				liferayLogin : liferayLogin,
								  		bindno : bindno.trim(),
								  		checkCtrlno : checkCtrlno,
								  		checkacc : checkacc,
								  		checkSMD : checkSMD,
								  		checkicat : checkicat,
								  		checkloca : checkloca,
								  		checkcon : checkcon,
								  		checkfprice : checkfprice,
								  		checklprice : checklprice,
								  		checkvol : checkvol,
								  		checkcopy : checkcopy,
								  		checkspine : checkspine,
								  		createAcc : "YES"
				         				},
				         				
				         				function(message){
				         					var status = message.replace(/\s+/g, '');
				         					if (status == "ok") {
				         						messageBox("138","","");
				         						$('#table-BindCheckIn').DataTable().row(indexval).remove().draw();
				         						$("#closeModalAccDetail").click();
				         					}else{
				         						swal("Unsuccessfully");
				         					}
				         			});
				        	     	 
				        	     
				        		});
				        	  /////////////////////////
				        	  
				        	  /////for GENERATE ACCESSION//
				        	  $("#checkgenerate").click(function(){
				        			$.get('generateAccNO', {
				        			 	}, function(jsonResponse) {
				        			 		 $("#checkacc").val(jsonResponse);   
				        			 		 $("#checkacc").focus();
				        			 	});
				        	  });
				        	  /////////////////////////////
				        	  
				        	  ///contol input value for foreign-amount and local-amount n rate///////////
				        	  $("#checkfprice, #checklprice").on("keypress keyup blur",function (event) {
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
				        	 /////////////////////////////
				          }
					 	},
					 	function() {
					 		$('#modalAcessionDetail').modal('hide');
					 		$.get("CheckInSerial",{
								  liferayLogin : liferayLogin,
								  bindno : bindno.trim(),
								  createAcc : "NO"
							  },
							  		function(message){
				     					var status = message.replace(/\s+/g, '');
				     					if (status == "ok") {
				     						//alert("Check in process has been completed");
				     						messageBox("138","","");
				     						$('#table-BindCheckIn').DataTable().row(indexval).remove().draw();
				     					}else{
				     						swal("Unsuccessfully");
				     					}
							});	
		    	        }
					   ).fail(function(data){
					 	 swal("fail");
					   }).success(function(data){
					   });
			  }
			  
		});
		/*$('#table-BindCheckIn').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
    		var reqnumber = $(this).closest('tr').find('td:eq(1)').text();  
     		var indexval  = $('#table-BindCheckIn').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
     		//alert(reqnumber)
     		var obj = {};
  	    	obj.reqnumber = reqnumber;
  	    	obj.indexval = indexval;
  	    	orderno.push(obj);
		});*/
	});
	
});