$(document).ready(function(){
	/////Call error message page
	function messageBox(code, criteria, label){
	      var url = "Error_Page?GL79ERRCODE="+code+"" +
	      "&criteria=" + criteria + "&label="+label+"";
	      //alert(url);
	      $.ajax({
	            url: url,
	            success: function(result) {
	                 swal(result); 
	            }
	      });
	}
	
	$('#formdataIncomReq').bootstrapValidator({
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
        	txtMaterial: {
            	trigger: 'blur, focusout',
                validators: {
                    notEmpty: {
                        message: 'Control No is required'
                    }
                }
            },
            txtPatron: {
            	trigger: 'blur, focusout',
                validators: {
                    notEmpty: {
                        message: 'Requestor ID is required'
                    }
                }
            },
            txtContact: {
            	trigger: 'blur, focusout',
                validators: {
                    notEmpty: {
                        message: 'Contact Person is required'
                    }
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
         
         //get title
     	 var title = $("#modalName").text();
     	 //
     	 
     	 var sRequestNo = $(".lblRequestNo").text();
     	 //alert("sRequestNo" + sRequestNo);
     	 var sControlNo = $("#txtMaterial").val();
     	 //alert("sControlNo" + sControlNo);
     	 var sAccessionNo = $("#txtAccession").val();
     	 //alert("sAccessionNo" + sAccessionNo);
     	 var sRequestor = $("#txtPatron").val();
     	 //alert("sRequestor" + sRequestor);
     	 var contact = $("#txtContact").val() ;
     	 //alert("contact" + contact);
     	 var requestedDate = moment($("input[name='txtRequestedDate']").val(), 'DD/MM/YYYY').format("YYYYMMDD"); 
     	 //alert("requestedDate" + requestedDate);
     	 var sentDate = $("input[name='txtSentDate']").val();
     	 //alert(sentDate +"sentDate1");
     	 /*if(sentDate != ""){
     		sentDate = moment($("input[name='txtSentDate']").val(), 'DD/MM/YYYY').format("YYYYMMDD");
     	 }*/
     	 
     	if(sentDate == '' || sentDate == ' '|| sentDate == '-'){
     		sentDate = '';
		}else{
			sentDate = moment(sentDate, 'DD/MM/YYYY').format("YYYYMMDD")
		}
     	//alert("sentDate" + sentDate);
     	 
     	 var cService = $("#txtService").val();
     	 if(cService == ""){
     		cService = "0";
     	 }
     	 //alert("cService" + cService);
     	 
     	 var cHandling = $("#txtHandling").val();
     	 if(cHandling == ""){
     		cHandling = "0";
     	 }
     	 //alert("cHandling" + cHandling);
     	 
     	 
     	 var cDiscount = $("#txtDiscount").val();
     	 if(cDiscount == ""){
     		cDiscount = "0";
     	 }
     	 //alert("cDiscount" + cDiscount);
     	 
     	 
     	 var cAmount = $(".lblAmount").text();
     	 if(cAmount == ""){
     		cAmount = "0";
     	 }
     	 //alert("cAmount" + cAmount);
     	 
     	 
     	 var gsUserId = $("#liferayLogin").val();
     	 //alert("gsUserId" + gsUserId);
     	 

    	//////////////////////SET TABLE ///////////////////////////////////////////
    	var t = $('#incomReq_table').DataTable({
     		destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			columnDefs    : [
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
     
    	///////////////////////////////////////////////////////////////////////////
    	
    	///////////////////// Set Current Date  ///////////////////////////////////
    	var today = new Date(); 
    	var liferayLogin = $("#liferayLogin").val();
    	
    	var totalrow = t.row().count();
    	//alert(totalrow);
    	
     	///////////////////////////////////////////////////////////////////////////
    	switch(title){
    		case state = 'Add Incoming Request':
    			$('#txtAccession').empty();
    			$('#formdataIncomReq').data('bootstrapValidator').resetForm();
    			$('#formdataIncomReq').trigger('reset');
    			$(".lblAmount, .lblRequestNo, .lblStatus").empty();
    			$(".lblName, .lblAdd1, .lblAdd2, .lblAdd3").empty();
    			$(".lblPostCode, .lblTelNo").empty();
    			$('.daterec').text(moment(today).format("DD/MM/YYYY"));
		  		$(".recby").text(liferayLogin);
		  		$("input[name='txtRequestedDate']").datepicker('setDate', moment(today).format("DD/MM/YYYY"));
				$("input[name='txtSentDate']").datepicker('setDate', null);
				$("input[name='txtRequestedDate']").val(moment(today).format("DD/MM/YYYY"));
				$("#previewbtn").attr('disabled', true);
				
				
				
				$.post("Handler_Add", {
						sRequestNo : sRequestNo,
						sControlNo : sControlNo,
						sAccession : sAccessionNo,
						sRequestor : sRequestor,
						contact : contact, 
						requestedDate : requestedDate, 
						sentDate : sentDate, 
						cService : cService, 
						cHandling : cHandling, 
						cDiscount : cDiscount, 
						cAmount : cAmount,
						gsUserId : gsUserId,
				}, function(result){
					
					//alert(result.trim())
					
					if(result.trim()!= "error"){
						
						
						swal({
							text: "Print Notification?",
							showCancelButton: true,
							confirmButtonColor: "#DD6B55",
							confirmButtonText: "Yes",
							cancelButtonText: "No"
						}).then(
								function(isConfirm) {
									if (isConfirm) {
										//alert("comfirmmmm");
										setTimeout(function(){
											$.post("GeneratePreviewDocument", {
												sControlNo: sControlNo,
												sRequestor : sRequestor,
											 	liferayLogin : gsUserId,
											 	action : "EL01",
											 	requestno : result.trim(),
											 	RequestedDate : $("input[name='txtRequestedDate']").val(),
											 	sAccessionNo : sAccessionNo,
											 	action2 : "print"
										}, function(result2){
					
												 var w = window.open('about:blank');
												 	w.document.open();
												 	w.document.write(result2);
												 	w.document.close();
												 	w.print();
										});
										},800); 
										
										setTimeout(function(){
											$.get("resultSearch",
							 						{input_criteria : result.trim(), search_type : "reqNo"}
							 						,function(json){
							 			    			 var return_data = new Array();
							 			    			 	//messageBox("005","Add","@action");
							 			    			 	//alert("pppp");
							 			    			messageBox("005","Add. Request No : "+ result.trim(),"@action");
							 			    	            for(var i=0;i< json.length; i++){
							 			    	            	//alert("ll" +parseInt(data.length));
							 			    	            	var no = t.rows().count(); // Get the current number of rows in the table
							 			    	            	//alert(no)
                												no = (no + 1); // Increment by 1
                												//alert(no)
						     									
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
							 			    	            	
							 			    	            	t.row.add( [
							 			    	            	            //(i+1),
							 			    	            	            no.toString(),
							 			    				                json[i].RequestNo,
							 			    				                json[i].Material, 
							 			    				                json[i].Accession,
							 			    				                json[i].Title,
							 			    				                json[i].Name,
							 			    				                json[i].RequestedDate,
							 			    				                stat,
							 			    				                '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalincomReq" data-whatever="View Incoming Request" title="View Incoming Request" data-rowid="'+json[i].RequestNo+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalincomReq" data-whatever="Edit Incoming Request" title="Edit Incoming Request" data-rowid="'+json[i].RequestNo+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].RequestNo+'"><span class="glyphicon glyphicon-trash" title="Delete Incoming Request" ></span></button>',
							 										] ).draw( false );
							 			    	            }
							 				});
										},800);
										
										
									}else{
										//alert("elaerer")
									}
								},
								function() {
									setTimeout(function(){
										$.get("resultSearch",
						 						{input_criteria : result.trim(), search_type : "reqNo"}
						 						,function(json){
						 			    			 var return_data = new Array();
						 			    			 	//messageBox("005","Add","@action");
						 			    			messageBox("005","Add. Request No : "+ result.trim(),"@action");
						 			    	            for(var i=0;i< json.length; i++){
							
															var no = t.rows().count(); // Get the current number of rows in the table
							 			    	            	//alert(no)
                												no = (no + 1); // Increment by 1
                												//alert(no)

						 			    	    
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
						 			    	            	
						 			    	            	t.row.add( [
						 			    	            	            //(i+1),
						 			    	            	            //totalrow+1,
						 			    	            	            no.toString(),
						 			    				                json[i].RequestNo,
						 			    				                json[i].Material, 
						 			    				                json[i].Accession,
						 			    				                json[i].Title,
						 			    				                json[i].Name,
						 			    				                json[i].RequestedDate,
						 			    				                stat,
						 			    				                '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalincomReq" data-whatever="View Incoming Request" title="View Incoming Request" data-rowid="'+json[i].RequestNo+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalincomReq" data-whatever="Edit Incoming Request" title="Edit Incoming Request" data-rowid="'+json[i].RequestNo+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].RequestNo+'"><span class="glyphicon glyphicon-trash" title="Delete Incoming Request" ></span></button>',
						 										] ).draw( false );
						 			    	            }
						 				});
									},800);
								}
						);
					}else{
						alert("error");
					}	
				});

    			break;
    		case state = 'Edit Incoming Request':
    			
    			$.get("Handler_Edit",{
    				sRequestNo : sRequestNo,
					sControlNo : sControlNo,
					sAccession : sAccessionNo,
					sRequestor : sRequestor,
					contact : contact, 
					requestedDate : requestedDate, 
					sentDate : sentDate, 
					cService : cService, 
					cHandling : cHandling, 
					cDiscount : cDiscount, 
					cAmount : cAmount,
					gsUserId : gsUserId,
     				},
     				function(message){
     					var status = message.replace(/\s+/g, '');
     					//alert("status" +status);
     					if (status == "true") {
     						$('#incomReq_table').dataTable().fnClearTable();
     						
     						
     						$.post("GeneratePreviewDocument", {
									sControlNo: sControlNo,
									sRequestor : sRequestor,
								 	liferayLogin : gsUserId,
								 	action : "EL01",
								 	requestno : sRequestNo,
								 	RequestedDate : $("input[name='txtRequestedDate']").val(),
								 	sAccessionNo : sAccessionNo,
								 	action2 : "print",
							}, function(result2){
		
									 var w = window.open('about:blank');
									 	w.document.open();
									 	w.document.write(result2);
									 	w.document.close();
									 	w.print();
							});
     						
     						setTimeout(function(){
     							$.get("resultSearch",
    		     						{input_criteria : sRequestNo, search_type : "reqNo"}
    		     						,function(json){print
    		     			    			 var return_data = new Array();
    		     			    			 	//messageBox("005","Add","@action");
    		     			    			 	$("#closeModalAdd").click();
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
    		     			    	            	
    		     			    	            	t.row.add( [
    		     			    	            	            (i+1),
    		     			    				                json[i].RequestNo,
    		     			    				                json[i].Material, 
    		     			    				                json[i].Accession,
    		     			    				                json[i].Title,
    		     			    				                json[i].Name,
    		     			    				                json[i].RequestedDate,
    		     			    				                stat,
    		     			    				                '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalincomReq" data-whatever="View Incoming Request" title="View Incoming Request" data-rowid="'+json[i].RequestNo+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalincomReq" data-whatever="Edit Incoming Request" title="Edit Incoming Request" data-rowid="'+json[i].RequestNo+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].RequestNo+'"><span class="glyphicon glyphicon-trash" title="Delete Incoming Request" ></span></button>',
    		     										] ).draw( false );
    		     			    	            }
    		     				});
     						});
     						/**/
     						
     						///generate document
    						
     					}else{
     						swal("Unsuccessfully");
     					}
     			});
	 			break;
    			
    	}
	});
	
	
	
});