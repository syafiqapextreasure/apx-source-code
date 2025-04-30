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
	
	$('#formdataMonograph').bootstrapValidator({
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
        	accessionNo: {
                validators: {
                    notEmpty: {
                        message: 'Accession No is required'
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
            	trigger: 'blur, focusout',
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
     	
     	var accno = $("#accessionNo").val();
     	var ctrlno = $("#controlNo").val();
     	var officer = $("#officer").val();
     	var binder = $("#binder").val().toUpperCase();;
     	var currency = $("#currency").val();
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
    	
    	//getcheked chekbox
		var getforretain = $('input[name="Retain"]:checkbox:checked').map(function(){
		        			return $(this).val();
			    			});
    	
    	/*var oldAmount  = $("#inputAmountFundAccount").val(); 
    	 var Amount = oldAmount.replace(/,/g,'');*/
    	
    	//////////////////////SET TABLE ///////////////////////////////////////////
    	var t = $('#Monograph_table').DataTable({
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
    	
    	var data = t.rows().data();
     
    /*alert( 'The table has '+data.length+' records' );
    alert( "xdd" + ((data.length)+1));*/
    	///////////////////////////////////////////////////////////////////////////
    	
    	///////////////////// Set Current Date  ///////////////////////////////////
    	var today = new Date(); 
    	var liferayLogin = $("#liferayLogin").val();
    	var year = moment().format('YYYY');
     	///////////////////////////////////////////////////////////////////////////
    	
     	switch(title){
	     	case state = 'Add New Binding Record':
	     		$('#formdataMonograph').data('bootstrapValidator').resetForm();
	     		$(".bindingNo, .refrenceNo, .stausView, .callNumber, .isbn").empty();
	     		$("#editissuevolDes").val("");
 				//$(".bindingNo, .refrenceNo, .stausView, .div-bindername, .callNumber, .div-officername, .isbn").empty();
 		  		$('.daterec').text(moment(today).format("DD/MM/YYYY"));
 		  		$(".recby").text(liferayLogin);
 		  		$("#year").val(year);
 		  		$('#dsent').val(moment(today).format("DD/MM/YYYY"));
 		  		
 		  		if(getforretain.get().length == '0'){
 		  			$(".div-officername, .div-bindername").empty();
 		  			$('#formdataMonograph').trigger('reset');
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
 		  		}else{
 		  			$("#typeBind").prop("selectedIndex",-1);
 		  			$("#accessionNo, #controlNo, #editissuevolDes, #fcost, #lcost").val("");
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
		 	           				$('input[type=text][name=dexpect]').val('');
	           			    	}
			 	           		
				 	           	if(element=="#input-Remarks"){
	           			    		$('#remarks').val('');
	           			    	}
 	           			    }
 	           		});
 		  		}
 		  		
 		  		var tablellength = t.data().length;
 		  		
 		  		$.get("Handler_AddMonograph",{
 		  			accno : accno, 
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
 		  			cColor :  cColor,
 		  			lColor : lColor,
 		  			daterec : daterec,
 		  			recby : recby,
 		  			oriCover : oriCover,
 		  			advert : advert,
 		  			titlePage : titlePage,
 		  			supp : supp,
 		  			indexInc : indexInc, 
 		  			contentsPage : contentsPage,
					action : "ADD",
     				},
     				function(message){
     					var status = message.replace(/\s+/g, '');
     					if (status != "") {
     						$.get("resultSearchMonograph",
     						{input_criteria : message.trim(), search_type : "bindno", inputSentDate : ""}
     						,function(json){
     			    			 var return_data = new Array();
     			    			 	messageBox("005","Add","@action");
     			    	            for(var i=0;i< json.length; i++){

     			    	            	/*var no = parseInt(i);
     			    	            	no = (no+1);*/
     			    	            	//(data.length)+1)
     			    	            	//16102019
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
     			    	  		                stat,
     			    	  		             '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalMonograph" data-whatever="View Binding Record" title="View Binding Record" data-rowid="'+json[i].refno+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalMonograph" data-whatever="Edit Binding Record" title="Edit Binding Record" data-rowid="'+json[i].refno+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].refno+'"><span class="glyphicon glyphicon-trash" title="Delete Binding" ></span></button>',
     										] ).draw( false );
     			    	            }
     			    		 });
     					}else{
     						swal("Unsuccessfully");
     					}
     			});
	 			break;
	 		case state = 'Edit Binding Record':
	 			$.get("Handler_EditMonograph",{
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
     						$('#Monograph_table').dataTable().fnClearTable();
     						$.get("resultSearchMonograph",
     						{input_criteria : bindingNo, search_type : "bindno", inputSentDate : ""}
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
     			    	  		                stat,
     			    	  		             '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalMonograph" data-whatever="View Binding Record" title="View Binding Record" data-rowid="'+json[i].refno+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalMonograph" data-whatever="Edit Binding Record" title="Edit Binding Record" data-rowid="'+json[i].refno+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].refno+'"><span class="glyphicon glyphicon-trash" title="Delete Binding" ></span></button>',
     										] ).draw( false );
     			    	            }
     			    		 });
     						
     						 t.on( 'order.dt search.dt', function () {
     					        t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
     					            cell.innerHTML = i+1;
     					        } );
     					    } ).draw();
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
        $('#formdataMonograph').bootstrapValidator('revalidateField', 'dsent');
	});
	
	$('#dateExpected').on('changeDate show', function(e) {
	        // Revalidate the date when user change it
	        $('#formdataMonograph').bootstrapValidator('revalidateField', 'dexpect');
	});
	
});