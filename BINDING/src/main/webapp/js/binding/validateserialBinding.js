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

	$('#formdataSerials').bootstrapValidator({
		framework: 'bootstrap',
		 excluded: [':disabled, :hidden'],
		 icon: {
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        //feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	controlNo: {
                validators: {
                    notEmpty: {
                        message: 'Control Number is required'
                    }
                }
            },
            officer: {
            	trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'Officer is required'
                    }
                }
            },
            binder: {
                validators: {
                    notEmpty: {
                        message: 'Binder is required'
                    }
                }
            },
            currency: {
                validators: {
                    notEmpty: {
                        message: 'Currency is required'
                    },
                    /*numeric: {
                        message: 'The Currency must be a number',
                        thousandsSeparator: '',
                        decimalSeparator: '.'                        
                    }*/
                }
            },
            dsent: {
                validators: {
                    notEmpty: {
                        message: 'The Date Sent is required'
                    },date: {
                        format: 'DD/MM/YYYY',
                        message: 'The value is not a valid date'
                    }
                }
            },
            dexpect: {
                validators: {
                    notEmpty: {
                        message: 'The Date Expected is required'
                    },date: {
                        format: 'DD/MM/YYYY',
                        message: 'The value is not a valid date'
                    }
                }
            },
            
           /*ordernoMast: {
                validators: {
                    notEmpty: {
                        message: 'ordernoMast is required'
                    }
                }
            },*/
           /*volFrom: {
                validators: {
                    notEmpty: {
                        message: 'Volume From is required'
                    }
                }
            },*/
            /*volFrom: {
                validators: {
                    notEmpty: {
                        enabled: false   // or false
                    }
                }
            }*/
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
     	var bindingNo = $(".bindingNo").text();
     	var issn = $("#issn").val();
     	var ctrlno = $("#controlNo").val();
     	var officer = $("#officer").val();
     	var binder = $("#binder").val().toUpperCase();
     	var currency = $("#currencySerial").val();
     	var erate = $("#erate").val();
     	var year = $("#year").val();
     	var fcost = $("#fcost").val();
     	var lcost = $("#lcost").val();
     	var dsent  = moment($("#dsent").val(), 'DD/MM/YYYY').format("YYYYMMDD");
     	
     	var dexpect  = moment($("#dexpect").val(), 'DD/MM/YYYY').format("YYYYMMDD");
     	var remarks = $("#remarks").val();
     	var isbn = $("#isbn").text();
     	var typeBind = $("#typeBind").val();
     	var status = $("input[name='statusRadio']:checked").val();
     	var orispineTitle = $("#spineTitle").val();
     	var spineTitle = orispineTitle.replace(/[']/gi, "''");
     	var callno = $("#callNumber").text();
     	var cColor = $("#cColor").val();
     	var lColor = $("#lColor").val();
     	var daterec = moment($(".daterec").text(), 'DD/MM/YYYY').format("YYYYMMDD");
    	
     	var recby = $(".recby").text();
    	var oriCover = $('#oriCover:checked').val();
    	if(oriCover == "Y"){
    		oriCover = "Y";
    	}else{
    		oriCover = "N";
    	}
    	
    	var advert = $('#advert:checked').val();
    	if(advert == "Y"){
    		advert = "Y";
    	}else{
    		advert = "N";
    	}
    	
    	var titlePage = $('#titlePage:checked').val();
    	if(titlePage == "Y"){
    		titlePage = "Y";
    	}else{
    		titlePage = "N";
    	}

    	var supp = $('#supp:checked').val();
    	if(supp == "Y"){
    		supp = "Y";
    	}else{
    		supp = "N";
    	}
    	
    	var indexInc = $('#indexInc:checked').val();
    	if(indexInc == "Y"){
    		indexInc = "Y";
    	}else{
    		indexInc = "N";
    	}
    	
    	var contentsPage = $('#contentsPage:checked').val();
    	if(contentsPage == "Y"){
    		contentsPage = "Y";
    	}else{
    		contentsPage = "N";
    	}
    	
    	var volFrom = $("#volFrom").val();
    	var volTo = $("#volTo").val();
    	var issueFrom = $("#issueFrom").val();
    	var issueTo = $("#issueTo").val();
    	var pages = $("#pages").val();
    	var issueselected = $("#issueselected").val();
    	var copyno = $("#copyno").val();
    	var ordernoMast = $("#ordernoMast").val();
    	
    	var getforretain = $('input[name="Retain"]:checkbox:checked').map(function(){
			return $(this).val();
			});
    	
    	/*alert("1. volFrom" + volFrom);
    	alert("2. volTo" + volTo);
    	alert("3. issueFrom" + issueFrom);
    	alert("4. issueTo" + issueTo);
    	alert("5. issueselected" + issueselected);
    	alert("6. copyno" + copyno);
    	alert("7. ordernoMast" + ordernoMast);*/
    	
    	//////////////////////SET TABLE ///////////////////////////////////////////
    	var t = $('#Serials_table').DataTable({
     		destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			columnDefs    : [
					{'data': 'No'},
					{'data': 'Reference No'},
					{'data': 'Title'},
					{'data': 'Date Sent', className: "text-center"},
					{'data': 'Date Expected', className: "text-center"},
					{'data': 'Volume'},
					{'data': 'Issue'},
					{'data': 'Status'},
					{'data': 'Action'},
			],
			drawCallback: function( settings ) {
				$('#Serials_table tbody tr').each(function() {
			          var Cell = $(this).find('td:eq(7)');
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
    	///////////////////////////////////////////////////////////////////////////
    	
    	///////////////////// Set Current Date  ///////////////////////////////////
    	var today = new Date(); 
    	var liferayLogin = $("#liferayLogin").val();
    	var year = moment().format('YYYY');
     	///////////////////////////////////////////////////////////////////////////

     	switch(title){
     
	     	case state = 'Add New Binding Record':
	     		
	     		if(volFrom == ""){
	     			swal("Please insert mandatory value");
	     		}else{
	     			$('#formdataSerials').data('bootstrapValidator').resetForm();
	     			$(".bindingNo, .refrenceNo, .stausView, .callNumber, .isbn, .div-bindername").empty();
	     			$("#editissuevolDes, #issn").val("");
			  		$('.daterec').text(moment(today).format("DD/MM/YYYY"));
			  		$(".recby").text(liferayLogin);
			  		$("#year").val(year);
			  		$('#dsent').val(moment(today).format("DD/MM/YYYY"));
			  		
			  		
			  		if(getforretain.get().length == '0'){
			  			$(".div-officername").empty();
			  			$('#formdataSerials').trigger('reset');
			  			$("#officer").val(liferayLogin);
			  			$("#currencySerial, #typeBind").prop("selectedIndex",-1);	
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
			  		}else{
			  			$("#typeBind").prop("selectedIndex",-1);
			  			$("#issn, #controlNo, #editissuevolDes, #fcost, #lcost").val("");
			  			$("#spineTitle, #cColor, #lColor").val("");
			  			$("input[name=statusRadio][value='S']").prop("checked",true);
			  			$("#oriCover").prop('checked', false);
			  			$("#advert").prop('checked', false);
			  			$("#titlePage").prop('checked', false);
			  			$("#supp").prop('checked', false);
			  			$("#indexInc").prop('checked', false);
			  			$("#contentsPage").prop('checked', false);
			  			
			  			var element;
		            	$('input:checkbox[name=Retain]').each(function() {    
		           			    if(!$(this).is(':checked')){
		           			    	var element = "#" + $(this).val();
		           			    	
		           			    	//alert(element);
		           			    	
		           			    	if(element=="#input-officer"){
		           			    		$('input[type=text][name=officer]').val('');
		           			    		$(".div-officername").empty();
		           			    	}
		           			    	
		           			    	if(element=="#input-binder"){
		           			    		$('input[type=text][name=binder]').val('');
		           			    		$(".div-bindername").empty();
		           			    	}
		           			    	
		 	           			    if(element=="#input-currency"){
		 	           			    	$("#currency").prop('selectedIndex',-1);
		 	           			    	$('input[type=text][name=erate]').val('');
		           			    	}
	 	           			    
			 	           			if(element=="#input-dateSent"){
			 	           				$('input[type=text][name=dsent]').val('');
		           			    	}else{
		           			    		$('#dsent').val(moment(today).format("DD/MM/YYYY"));
		           			    	}
		 	           			
				 	           		if(element=="#input-dateExpected"){
				 	           		  $('#dexpect').val("").datepicker("update");
			 	           				/*$('#dexpect').datepicker('setDate', null);
			 	           				$('input[type=text][name=dexpect]').val('');*/
		           			    	}
			 	           		
					 	           	if(element=="#input-Remarks"){
		           			    		$('#remarks').val('');
		           			    	}
					 	           	
					 	           if(element=="#input-pages"){
		           			    		$('#pages').val('');
		           			    	}
		           			    }
		           		});
			  		}
			  		
			
			  		var tablellength = t.data().length;
			  		
			  		$.get("Handler_AddSer",{
	 		  			issn : issn,
	 		  			ctrlno : ctrlno,
		 		  		officer : officer,
		 		  		binder : binder,
		 		  		currency : currency, 
		 		  		erate : erate,
		 		  		year : year,
		 		  		fcost : fcost,
		 		  		lcost : lcost,
		 		  		dsent : dsent,
	 		     	
		 		  		dexpect : dexpect,
		 		  		remarks : remarks,
		 		  		isbn : isbn, 
		 		  		typeBind : typeBind,
		 		  		status : status,
		 		  		spineTitle : spineTitle,
		 		  		callno : callno,
		 		  		cColor : cColor,
		 		  		lColor : lColor,
		 		  		daterec : daterec,
	 		    	
		 		  		recby : recby,
		 		  		oriCover : oriCover, 
		 		  		advert : advert,
		 		  		titlePage : titlePage,
		 		  		supp : supp,
		 		  		indexInc : indexInc,
		 		  		contentsPage : contentsPage,
	 		    	
		 		  		volFrom : volFrom,
		 		  		volTo : volTo,
		 		  		issueFrom : issueFrom,
		 		  		issueTo : issueTo,
		 		  		pages : pages,
		 		  		issueselected : issueselected,
		 		  		copyno : copyno,
		 		  		ordernoMast : ordernoMast,
						action : "ADD",
	     				},
	     				function(message){
	     					var status = message.replace(/\s+/g, '');
	     					
	     					if (status != "") {
	     						$.get("resultSearchSerial",
	     						{input_criteria : message.trim(), search_type : "bindno"}
	     						,function(json){
	     			    			 var return_data = new Array();
	     			    			 	messageBox("005","Add","@action");
	     			    	            for(var i=0;i< json.length; i++){

	     			    	            	var no = parseInt(tablellength);
	     			    	            	no = (no+1);
	     			    	            	
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
							        		}
	     			    	            	
	     			    	            	t.row.add( [
	     			    	            	        no.toString(),
	     			    	            	        json[i].refno,
	     			    	            	        json[i].title, 
	     			    	            	        json[i].datesent,
	     			    	            	        json[i].dateexpected,
	     			    	            	        json[i].volfrom +" - "+ json[i].volto,
	     			    	            	        json[i].issfrom +"("+ json[i].labelissfrom +") - "+ json[i].issto +"("+json[i].lableissto +")",
	     			    	            	        stat,
	     			    	            	        '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalSerials" data-whatever="View Binding Record" title="View Binding Record" data-rowid="'+json[i].refno+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalSerials" data-whatever="Edit Binding Record" title="Edit Binding Record" data-rowid="'+json[i].refno+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].refno+'"><span class="glyphicon glyphicon-trash" title="Delete Binding" ></span></button>',
	     										] ).draw( false );
	     			    	            }
	     			    		 });
	     					}else{
	     						swal("Unsuccessfully");
	     					}
	     			});
	     		}
	     		/*setTimeout(function(){ 
					if(total >= 1){
						$("#save").attr('disabled', false);
					}else{
						$("#save").attr('disabled', 'disabled');
					}
					
				}, 1000);*/
	     		
	     		/*$('#formdataSerials').data('bootstrapValidator').resetForm();
 				$('#formdataSerials').trigger('reset');
 				$("#currencySerial, #typeBind").prop("selectedIndex",-1);	
 				$("#officer").val(liferayLogin);
 				$("#year").val(year);
 		  		$('#dsent').val(moment(today).format("DD/MM/YYYY"));
 		  		$(".bindingNo, .refrenceNo, .stausView, .div-bindername, .callNumber, .div-officername, .isbn").empty();
 		  		$("#volFrom, #volTo, #issueFrom, #issueTo, #issueselected, #copyno").val("");
 		 		$(".issueFrom, .issueTo").empty();
 		 		$('.Retrieve, .Previous').prop('disabled', 'disabled');
 		  		
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
		  		}, 1000);*/
	     		
 		  		
 		  		/**/
	 			break;
	 		case state = 'Edit Binding Record':

	 			$.get("Handler_EditSer",{
	 				bindingNo : bindingNo,
 		  			officer : officer, 
 		  			binder : binder,
 		  			currency : currency, 
 		  			erate : erate,
 		  			year : year,
 		  			fcost : fcost,
 		  			lcost : lcost,
 		  			dsent : dsent,
 		  			dexpect : dexpect,
 		  			pages : pages,
 		  			remarks : remarks,
 		  			typeBind : typeBind,
 		  			status : status,
 		  			spineTitle : spineTitle,
 		  			cColor :  cColor,
 		  			lColor : lColor,
 		  			oriCover : oriCover,
 		  			advert : advert,
 		  			titlePage : titlePage,
 		  			supp : supp,
 		  			indexInc : indexInc, 
 		  			contentsPage : contentsPage,
 		  			liferayLogin : liferayLogin,
					action : "Edit",
     				},
     				function(message){
     					var status = message.replace(/\s+/g, '');
     					if (status == "ok") {
     						$('#Serials_table').dataTable().fnClearTable();
     						$.get("resultSearchSerial",
     						{input_criteria : bindingNo, search_type : "bindno"}
     						,function(json){
     			    			 var return_data = new Array();
     			    			 	messageBox("005","Edit","@action");
     			    			 	$("#closeModalAdd").click();
     			    	            for(var i=0;i< json.length; i++){
     			    	            	
     			    	            	var no = parseInt(i);
     			    	            	no = (no+1);    			    	            	
 
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
     					        		}
     			    	            	
     			    	            	t.row.add( [
     			    	            	        no.toString(),
     			    	            	        json[i].refno,
     			    			                json[i].title, 
     			    			                json[i].datesent,
     			    			                json[i].dateexpected,
     			    			                json[i].volfrom +" - "+ json[i].volto,
     			    			                json[i].issfrom +"("+ json[i].labelissfrom +") - "+ json[i].issto +"("+json[i].lableissto +")",
     			    			                stat,
     			    			                '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalSerials" data-whatever="View Binding Record" title="View Binding Record" data-rowid="'+json[i].refno+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalSerials" data-whatever="Edit Binding Record" title="Edit Binding Record" data-rowid="'+json[i].refno+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].refno+'"><span class="glyphicon glyphicon-trash" title="Delete Binding" ></span></button>',
     										] ).draw( false );
     			    	            }
     			    		 });
     					}else{
     						swal("Unsuccessfully");
     					}
     			});
	 			break;
     	}
	});
	
	
	/////////////////////////DATE	
	$('#dateSent').on('changeDate show', function(e) {
        // Revalidate the date when user change it
        $('#formdataSerials').bootstrapValidator('revalidateField', 'dsent');
	});
	
	$('#dateExpected').on('changeDate show', function(e) {
	        // Revalidate the date when user change it
	        $('#formdataSerials').bootstrapValidator('revalidateField', 'dexpect');
	});
	
	// Disable validator
	$("#formdataSerials")
	    .bootstrapValidator('enableFieldValidators', ordernoMast, 'notEmpty', false);
	
	/*$('#formdataSerials').bootstrapValidator('enableFieldValidators',
			  'volFrom', false, 'notEmpty');*/
	
});