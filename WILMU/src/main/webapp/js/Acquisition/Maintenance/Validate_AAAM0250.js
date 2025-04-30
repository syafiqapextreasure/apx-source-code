$(document).ready(function(){

	/////Call error message page
	function messageBox(code, criteria, label){
	      var url = "Error_Page?GL79ERRCODE="+code+"" +
	      "&criteria=" + criteria + "&label="+label+"";
	      //alert(url);
	      $.ajax({
	            url: url,
	            success: function(result) {
	                 //swal(result); //, 'warning' 
	            	setTimeout(function(){
	            		swal(result);
	        	     },700);
	            }
	      });
	}
	
	$('#formdataOrderMain').bootstrapValidator({
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
        	inputControlNo: {
        		trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'The Contol Number is required'
                    },
                    remote: {
                        type: "GET",
                        url: 'checkingctrlno',
                        data: function(validator) {
                            return {
                            	inputControlNo: validator.getFieldElements('inputControlNo').val(),
                            };
                        },
                        message: 'Not exist!',
                    }
                }
            },
        	inputVendorCode: {
        		trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'The Vendor is required'
                    },
                    remote: {
                        type: "GET",
                        url: 'checkingVendorCodeAcq',
                        data: function(validator) {
                            return {
                            	inputVendorCode: validator.getFieldElements('inputVendorCode').val(),
                            };
                        },
                        message: 'Vendor Not exist!',
                    }
                }
            },
        	ordermode: {
                validators: {
                    notEmpty: {
                        message: 'Please select order mode'
                    }
                }
            },
            source: {
                validators: {
                    notEmpty: {
                        message: 'Please select source'
                    }
                }
            },
            currency: {
                validators: {
                    notEmpty: {
                        message: 'Please select currency'
                    }
                }
            },
            exchangeRate: {
            	trigger: 'focus',
                validators: {
                    notEmpty: {
                        message: 'The Exchange Rate is required'
                    }
                }
            },
            foreignAmount: {
            	trigger: 'blur, focus, keyup',
                validators: {
                    notEmpty: {
                        message: 'The Foreign Price is required'
                    }
                }
            },
            localAmount: {
            	trigger: 'blur, focusout',
                validators: {
                    notEmpty: {
                        message: 'The Local Price is required'
                    }
                }
            },
            set: {
                validators: {
                	callback: {
                        message: 'You must enter at least one field Quantity',
                        callback: function(value, validator, $field) {
                        	
                        	/*if($("input[name=set]").val() != "" && $("input[name=copiesSet]").val() == "") {
                        		return false;
                        	}*/

                        	if($("input[name=set]").val() != "" && $("input[name=copiesSet]").val() != "") {
                        		return true;
                        	}
                        	
                        	
                        	//alert("set");
                            if ($("input[name=set]").val()) {
                                // Update the status of callback validator for all fields
                                validator.updateStatus('set', validator.STATUS_VALID, 'callback');
                                validator.updateStatus('copies', validator.STATUS_VALID, 'callback');
                                return true;
                            }
                            return false;
                        }
                    },
                }
            },
            copiesSet: {
                validators: {
                	callback: {
                        message: 'You must enter at least one field Quantity',
                        callback: function(value, validator, $field) {
                        	///alert("copiesSET");
                        	if ($("input[name=set]").val() != "" && $("input[name=copiesSet]").val() != "") {
                        		validator.updateStatus('set', validator.STATUS_VALID, 'callback');
                        		validator.updateStatus('copies', validator.STATUS_VALID, 'callback');
                        		return true;
                        	}
                        	
                        	if ($("input[name=set]").val() == "" && $("input[name=copiesSet]").val() == ""
                        		&& $("input[name=copies]").val() != "") {
                        		validator.updateStatus('set', validator.STATUS_VALID, 'callback');
                                validator.updateStatus('copies', validator.STATUS_VALID, 'callback');
                        		return true;
                        	}
                        	return false;
                        }
                    },
                }
            },
            /*copiesSet: {
                validators: {
                	callback: {
                        message: 'You must enter at least one field Quantity',
                        callback: function(value, validator, $field) {
                        	
                        	alert($("input[name=copiesSet]").val() +"ff");
                        	alert($("input[name=copies]").val() == "" && $("input[name=copiesSet]").val() != "" +"ccc");
                        	if($("input[name=copiesSet]").val() == ""){
                        		return false;
                        	}
                        	alert("copiesSET");
                        	if ($("input[name=set]").val() != "" && $("input[name=copiesSet]").val() != "") {
                        		return true;
                        	}

                            if ($("input[name=copiesSet]").val()) {
                                // Update the status of callback validator for all fields
                                validator.updateStatus('set', validator.STATUS_VALID, 'callback');
                                validator.updateStatus('copiesSet', validator.STATUS_VALID, 'callback');
                                return true;
                            }
                            return false;
                        }
                    },
                }
            },*/
            copies: {
                validators: {
                	callback: {
                        message: 'You must enter at least one field Quantity',
                        callback: function(value, validator, $field) {
                        	//alert("copies");
                        	
                        	/*if ($("input[name=copies]").val() != "" && $("input[name=copiesSet]").val() == "" 
                        		&& $("input[name=set]").val() == "") {
                        		return true;
                        	}*/

                            if ($("input[name=copies]").val()) {
                                // Update the status of callback validator for all fields
                                validator.updateStatus('copies', validator.STATUS_VALID, 'callback');
                                validator.updateStatus('set', validator.STATUS_VALID, 'callback');
                                validator.updateStatus('copiesSet', validator.STATUS_VALID, 'callback');
                                return true;
                            }
                            return false;
                        }
                    },
                }
            },
            /*requestorId: {
                validators: {
                    different: {
                        field: 'requestorId',
                        message: 'The Requestor cannot be the same as each other'
                    }
                }
            },*/
        }
    })
     .on('success.form.bv', function (e) {
    	 
    	// Prevent form submission
         e.preventDefault();

         // Get the form instance
         var $form = $(e.target);

         // Get the BootstrapValidator instance
         var bv = $form.data('bootstrapValidator');
         
         //get title
     	 var title = $("#modalName").text();
     	 alert(title);
     	 var inputForm = $form.serialize();
     	 var isbn = $('input[name="isbn"]').val();
     	 var inputControlNo = $('input[name="inputControlNo"]').val();
     	 var inputVendorCode = $('input[name="inputVendorCode"]').val();
     	 var ordermode = $( "#acqMode" ).val();
     	 var source = $( "#source" ).val();
     	 var currency = $( "#currency" ).val();
     	 var exchangeRate = $('input[name="exchangeRate"]').val();
     	 var foreignAmount = $('input[name="foreignAmount"]').val();
     	 var localAmount = $('input[name="localAmount"]').val();
     	 var set = $('input[name="set"]').val();
     	 var copiesSet = $('input[name="copiesSet"]').val();
     	 var copies = $('input[name="copies"]').val();
     	 var userid = $("#liferayLogin").val();
     	 var subj = $( "#subj" ).val();
     	 
     	 var output = [];
		    $("#requestor tbody tr").each(function() {
		        var obj = {};
		        obj.requestorId = $(".requestorId", this).text();
		        obj.datereq = $(".datereq", this).text();

		        output.push(obj);
		       
		    });
		
		// alert(JSON.stringify(output) + " output"); $form.serialize(),JSON.stringify(output)
		//getcheked chekbox
		var getforretain = $('input[name="Retain"]:checkbox:checked').map(function(){
		        			return $(this).val();
			    			});
		      				//alert(getforretain.get());
		
		var table = $('#requestor').DataTable();
		
		var t = $('#OdrMaint2').DataTable({
			//responsive: true,
			scrollx: true,
			info: false,
			paging: true,
			ordering: false,
			destroy: true,
			searching:false,
			info: true,
			lengthChange: false,
			/*columnDefs: [
				{
			    	"targets": [ 5 ],
			       	"visible": false,
			        "searchable": false,
			    }
			],*/
			drawCallback: function( settings ) {
				$('#OdrMaint2 tbody tr').each(function() {
			          var Cell = $(this).find('td:eq(4)');
			          //alert(Cell.text());
			          debugger;
			          if((Cell.text() == 'APPROVED FOR ORDER') || (Cell.text() == 'READY TO ORDER') || (Cell.text() == 'PROFORMA INVOICE RECEIVED')){
			        	  $(this).find('#Edit').prop("disabled", false);
			        	  $(this).find('#Delete').prop("disabled", false);
			          }else{
			        	  $(this).find('#Edit').prop("disabled", true);
			        	  $(this).find('#Delete').prop("disabled", true);
			          }
			        });
		    }
 			});
		
		 switch(title){
		 	case state = "Add New Order":
		 		var orderno = $('input[name="orderno"]').val();
		 	
		 		$.post("insertOrderMaint", { 
		 				isbn : isbn,
		 				inputControlNo : inputControlNo,
		 				inputVendorCode : inputVendorCode,
		 				ordermode : ordermode,
		 				source : source,
		 				currency : currency,
		 				exchangeRate : exchangeRate,
		 				foreignAmount : foreignAmount,
		 				localAmount : localAmount,
		 				set : set,
		 				copiesSet: copiesSet,
		 				copies : copies,
		 				requestor: JSON.stringify(output),
		 				userid : userid,
		 				orderno : orderno,
		 				subj : subj,
		 			}, 
		 	        function(data,status){
		 	        	messageBox("005","Add Order","@action");
		 	        	
		 	        	$.get("displayOrderMaint",{action : "1"},function(json){
			    			 var return_data = new Array();
			    	           
			    			////add 08072019
			      	    	$("#orderhideorView").show();
			    			$("#orderno").attr('disabled', 'disabled');
			    			$("#orderno").val(json[0].orderNumber);
							$(".orderno2").val('');
							
			    			 
			    	            for(var i=0;i< json.length; i++){
			    	            	t.row.add( [
			    	            	            json[i].orderNumber,
			    	            	            json[i].ctrlno,
			    	    			            json[i].title,
			    	    			            json[i].descVendor,
			    	    			            json[i].currentStatusDesc,
			    	    			            json[i].refno,
			    	    			            json[i].oderDate,
			    	    			            json[i].amount,
			    	    			            '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalOdrMaint" data-whatever="View Record Order" title="View Order" data-rowid="'+json[i].orderNumber+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalOdrMaint" data-whatever="Edit Record Order" title="Edit Order" data-rowid="'+json[i].orderNumber+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs DeleteOrder" data-OrderNo="'+json[i].orderNumber+'"><span class="glyphicon glyphicon-trash" title="Delete Oder" ></span></button>'
			    	    			        ] ).draw( false );
			    	            }
			    		 });
		 	        	
		 	        	$('#formdataOrderMain').data('bootstrapValidator').resetForm();
      			        $('#formdataOrderMain a:first').tab('show');
      			        ///$('#requestor').dataTable().fnClearTable();
      			        $("#div-ctrolNo, #namePatr, #dept, #datereq").empty();
      			        $("#addRequestorRow").attr('disabled', true);
      			        ///////$(".btn-danger").attr('disabled', true);
      			        $('input[name=quantity][value=copies]').attr('checked', true);
	      	        	$('input[name=quantity][value=set]').attr('checked', false);
	      	        	$('#set, #copiesSet').attr('readonly', true);
	      	    		$('#copies').attr('readonly', false);
	      	    		
	      	    		
	      	    		
	      	    		var quantityChecked = $('input[name=quantity]:checked').val();
	      	    		if(quantityChecked == "set"){
	      	    			$('input[name=quantity][value=set]').attr('checked', false);
	      	    			$('input[name=quantity][value=copies]').attr('checked', true);
	      	    			$('#set, #copiesSet').attr('readonly', false);
		      	    		$('#copies').attr('readonly', true);
	      	    		}
	      	    		
	      	    		if($("#checkboxRequestor").prop('checked') == true){
	    	    		    //alert("qwerty");
	    	    		}else{
	    	    			//alert("nottt");
	    	    			$('#requestor').dataTable().fnClearTable();
	    	    		}
	      	    		
	      	    		///$('#requestor').dataTable().fnClearTable();
	      	    		
		 	        	
      			        if(getforretain.get().length == '0'){
		 	        		$("#modalOdrMaint").find('form').trigger('reset');
	      			        $("#ordermode, #source, #currency, #subj").prop("selectedIndex",-1);
	      			        $("#div-vendorName").empty();
		 	            }else{
		 	            	$('input[type=text][name=isbn]').val('');
		 	            	$('input[type=text][name=inputControlNo]').val('');
		 	            	$('input[type=text][name=foreignAmount]').val('');
		 	            	$('input[type=text][name=localAmount]').val('');
		 	            	$('input[type=text][name=quantity]').val('');
		 	            	$('input[type=text][name=set]').val('');
		 	            	$('input[type=text][name=copiesSet]').val('');
		 	            	$('input[type=text][name=copies]').val('');
		 	            	var element;
		 	            	$('input:checkbox[name=Retain]').each(function() {    
		 	           			    if(!$(this).is(':checked')){
		 	           			    	var element = "#" + $(this).val();
		 	           			    	///alert(element);
				 	           			if(element!="#input-vendorCode"){
			 	           			    	$('input[type=text][name!=inputVendorCode]').val('');
				 	   					}else{
				 	   						$('input[type=text]').val('');
				 	   						$("#div-vendorName").empty();
				 	   					}
				 	           			
					 	           		if(element!="#currency"){
					 	           			$("#div-exchange-Rate" ).val(exchangeRate);
				 	   					}else{
				 	   						$("#div-exchange-Rate" ).val('');
				 	   					}
				 	           			
				 	           			if($(element).is("select")) {
				 	           				$(element).prop('selectedIndex',-1);
				 	           				
				 	           				/*if($(element)!= "#currency"){
				 	           					$("#div-exchange-Rate" ).val(exchangeRate);
				 	           				}else{
				 	           					$("#div-exchange-Rate" ).val('');
				 	           				}*/
		 	           					}
		 	           			    }
		 	           		});
		 	            }
		 	        }
	    		).fail(function(data){
	    		}).success(function(data){
	    		});
		 		break;
		 	case state = "Edit Order":
		 		$("#orderno").attr('disabled', false);
		 		var orderno = $('input[name="orderno"]').val();
		 		
		 		var output2 = [];
			    $("#requestor tbody tr").each(function() {
			        var obj = {};
			        //obj.requestorNo = $(".reqNo", this.text());
			        obj.reqNo = $(".reqNo", this).text();
			        obj.reqId = $(".requestorId", this).text();//obj.reqId = $(".requestorId", this).val(); //reqId
			        obj.datereq = $(".datereq", this).text();
			       
			        output2.push(obj);
			       
			    });
		 		///alert("ff" +JSON.stringify(output2));
			 	$.post("editOrderMaint", { 
			 		orderno : orderno,
	 				isbn : isbn,
	 				inputControlNo : inputControlNo,
	 				inputVendorCode : inputVendorCode,
	 				ordermode : ordermode,
	 				source : source,
	 				currency : currency,
	 				exchangeRate : exchangeRate,
	 				foreignAmount : foreignAmount,
	 				localAmount : localAmount,
	 				set : set,
	 				copiesSet: copiesSet,
	 				copies : copies,
	 				requestor: JSON.stringify(output2),
	 				subj : subj,
	 			}, 
		 	    function(data,status){
		 	    	messageBox("005","Edit Order","@action");
		 	    	$("#closeModalAdd").click();
		 	    	$('#OdrMaint2').dataTable().fnClearTable();
		 	    	
		 	    	$.get("displayOrderMaint",{orderNo : orderno, action : "2"},function(json){
		    			 var return_data = new Array();
		    	           
		    	            for(var i=0;i< json.length; i++){
		    	            	t.row.add( [
		    	            	            json[i].orderNumber,
		    	            	            json[i].ctrlno,
		    	    			            json[i].title,
		    	    			            json[i].descVendor,
		    	    			            json[i].currentStatusDesc,
		    	    			            //json[i].currentStatus,
		    	    			            json[i].refno,
		    	    			            json[i].oderDate,
		    	    			            json[i].amount,
		    	    			            '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalOdrMaint" data-whatever="View Record Order" title="View Order" data-rowid="'+json[i].orderNumber+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalOdrMaint" data-whatever="Edit Record Order" title="Edit Order" data-rowid="'+json[i].orderNumber+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs DeleteOrder" data-OrderNo="'+json[i].orderNumber+'"><span class="glyphicon glyphicon-trash" title="Delete Oder" ></span></button>'
		    	    			        ] ).draw( false );
		    	            }
		    		 });
		 	    	
		 	    }).fail(function(data){
					swal("fail");
				})
		 		break;
		 }
	});
});