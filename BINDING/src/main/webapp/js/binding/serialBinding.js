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
	
	///////////////////// Set Current Date  ///////////////////////////////////
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	var year = moment().format('YYYY');
	
	$("#save").attr('disabled', 'disabled');
	
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
		startDate : today,
		 //minDate: 0,
		//clearBtn : true,
	});
	
	$('#dsent').datepicker( 'setDate', moment(today).format("DD/MM/YYYY") );
	
	///////////////////// Set Monograph_table  ////////////////////////////////
	$('#Serials_table').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	
	
	///////////////////// Set text autocomplete off////////////////////////////
	$('input[type="text"]').attr('autocomplete', 'off');
	
	//////////////////////////////Max Length /////////////////////////////////
	//$("#accessionNo").attr('maxlength','10');
	$("#officer").attr('maxlength','12');
	$("#controlNo").attr('maxlength','10');
	$("#editissuevolDes, #spineTitle").attr('maxlength','255');
	$("#binder, #year").attr('maxlength','4');
	$("#remarks, #pages").attr('maxlength','50');
	$("#cColor, #lColor").attr('maxlength','20');
	
	//////////////////////////////Clear All Modal /////////////////////////////
	$('#searchSerials').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	});
	
	$('#modalSerials').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $('#formdataSerials').data('bootstrapValidator').resetForm();
	});
	
	$('#modalRetrieve').on('hidden.bs.modal', function () {
	    $(this).off('hide.bs.modal');
	    	$('#table-order').dataTable().fnClearTable();
	    	$('#table-order').dataTable().fnDestroy();
			$('#table-issue').dataTable().fnClearTable();
			$('#table-issue').dataTable().fnDestroy();
	});
	
	//////////////////////////////when search modal open///////////////////////
	$('#searchSerials').on('shown.bs.modal', function () {
		$("input").prop("disabled", false);
		$("input[type=radio]").attr('disabled', false);
		$("#input-criteriaSer").focus();
		
		$("input[name=input-criteriaSer]").attr('maxlength','10');
		
		$('input[name=searchSelectionSerial]').change(function(){
			$("#input-criteriaSer").val("");
		    var value = $( 'input[name=searchSelectionSerial]:checked' ).val();
		    $("input[name=input-searchSelectionSerial]").val('');
		    $("input[name=input-criteriaSer]").focus();

		    if(value == "ctrlNo"){
		    	$("input[name=input-criteriaSer]").attr('maxlength','10');
		    }else{
		    	$("input[name=input-criteriaSer]").removeAttr('maxLength');
		    }
		    
		});	
	});
	
	
	//****************************************** AREA FOR MODAL *****************************************//
	$('#modalSerials').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');

		modal.find('.modal-title').text(recipient);	
		
		switch(recipient){
	  	case state = 'Add New Binding Record':
	  		enable();
		  	$('.daterec').text(moment(today).format("DD/MM/YYYY"));
	  		$(".recby").text(liferayLogin);
	  		$("#officer").val(liferayLogin);
	  		$("#currencySerial, #typeBind").prop("selectedIndex",-1);	
	  		$("#year").val(year);
	  		$('#dsent').val(moment(today).format("DD/MM/YYYY"));
	  		
	  		$(".bindingNo, .refrenceNo, .div-bindername").empty();
	  		$(".Retrieve, .Previous").hide();
	  		$("#searchISSN").attr('disabled', false);
	  		$(".stausView").hide();
	  		$(".Retrieve, .Previous").show();
	  		//$(".vhideStat, .Retrieve, .Previous, .issselect").hide();
	  		
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
	  		$(".Retrieve, .Previous, .issselect").hide();
	  		$("#issn, #controlNo").prop("disabled", true); 
			$("#searchISSN").attr('disabled', 'disabled');
			$.get('GetEditViewSer', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						var status = value['status'];
						
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
						
						$(".bindingNo").text(value['bindno']);
						$(".refrenceNo").text(value['refno']);
						$("#issn").val(value['issn']);
						$("#controlNo").val(value['ctrlno']);
						$("#editissuevolDes").val(value['title']);
						$("#officer").val(value['officer']);
						$(".div-officername").text(value['name']);
						$("#binder").val(value['binder']);
						$(".div-bindername").text(value['bindName']);
						$("#currencySerial").val(value['currency']);
						$("#erate").val(value['rate']);
						$("#year").val(value['year']);
						$("#fcost").val(value['fcost']);
						$("#lcost").val(value['lcost']);
						$("#dsent").val(value['datesent']);
						$("#dexpect").val(value['dateexpected']);
						$("#pages").val(value['page']);
						$("#remarks").val(value['remarks']);
						$("#volFrom").val(value['volfrom']);
						$("#volTo").val(value['volto']);
						$("#issueFrom").val(value['issfrom']);
						$(".issfromissbl").text(value['labelissfrom']);
						$("#issueTo").val(value['issto']);
						$(".isstoissbl").text(value['lableissto']);
						$("#copyno").val(value['copy']);
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
		  	$.get('GetEditViewSer', {
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
						
						$(".bindingNo").text(value['bindno']);
						$(".refrenceNo").text(value['refno']);
						$(".stausView").text("Status :" +stat).css({'font-weight': 'bold', 'font-style': 'italic', 'font-size': '20px'});
						$("#issn").val(value['issn']);
						$("#controlNo").val(value['ctrlno']);
						$("#editissuevolDes").val(value['title']);
						$("#officer").val(value['officer']);
						$(".div-officername").text(value['name']);
						$("#binder").val(value['binder']);
						$(".div-bindername").text(value['bindName']);
						$("#currencySerial").val(value['currency']);
						$("#erate").val(value['rate']);
						$("#year").val(value['year']);
						$("#fcost").val(value['fcost']);
						$("#lcost").val(value['lcost']);
						$("#dsent").val(value['datesent']);
						$("#dexpect").val(value['dateexpected']);
						$("#pages").val(value['page']);
						$("#remarks").val(value['remarks']);
						$("#volFrom").val(value['volfrom']);
						$("#volTo").val(value['volto']);
						$("#issueFrom").val(value['issfrom']);
						$(".issfromissbl").text(value['labelissfrom']);
						$("#issueTo").val(value['issto']);
						$(".isstoissbl").text(value['lableissto']);
						$("#copyno").val(value['copy']);
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
	$("#currencySerial").change(function(){

        var currency = $("#currencySerial option:selected").val();     

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
	/*$("#accessionNo").blur(function(e){
		var accessionNo = $("#accessionNo").val();

		$("#controlNo, #editissuevolDes, #spineTitle").val("");
 		$(".callNumber").empty();
		////display officer name
		$.get('GetAccession', {
        	code : accessionNo
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		$("#accessionNo, #controlNo, #editissuevolDes, #spineTitle").val("");
		 		$(".callNumber").empty();
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
	});*/
	
	//////////////////////////CONTROL NO blur//////////////////////////////////
	$("#controlNo").blur(function(e){
		var controlNo = $("#controlNo").val();

		$("#editissuevolDes, #spineTitle, #binder").val("");
		$(".callNumber, .div-bindername").empty();

		$.get('GetCtrlNoBySerial', {
        	code : controlNo
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		//alert("Invalid control no for serials record");
		 		$("#controlNo, #editissuevolDes, #spineTitle, #binder").val("");
		 		$(".callNumber, .div-bindername").empty();
		 		$(".Retrieve, .Previous").attr('disabled', 'disabled');
		 		messageBox("146","","");
		 		$('#formdataSerials').data('bootstrapValidator').resetForm();
		 	}
			if(responseJson != null){
				$(".Retrieve, .Previous").attr('disabled', false);
				$.each(responseJson, function(key,value) {
					$("#editissuevolDes").val(value['title']);
					$(".isbn").text(value['issn']);
					$("#spineTitle").val(value['title']);
					$(".callNumber").text(value['callno']);
					$("#issn").val(value['issn']);
					$("#binder").val(value['binder']);
					$(".div-bindername").text(value['bindName']);
				});
			}
		});
	});
	
	//////////////////////////CONTROL NO blur//////////////////////////////////
	$("#controlNo").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".Retrieve").attr('disabled', 'disabled');
			$("#accessionNo, #editissuevolDes, #spineTitle, #binder").val("");
	 		$(".callNumber, .div-bindername").empty();
	 		$("#volFrom, #volTo, #issueFrom, #issueTo, #issueselected, #copyno").val("");
	 		$(".issueFrom, .issueTo").empty();
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
	
	$("#binder").blur(function(e){
		var binder = $("#binder").val();
		//alert("binder " + binder);
		$(".div-bindername").empty();
		////display vendor name
		$.get('GetBinderName', {
        	code : binder
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		messageBox("144","","");
		 		$("#binder").val("");
		 		var validator = $('#formdataSerials').data('bootstrapValidator');
					// Reset the specific input field by its name or ID (replace 'myInputField' with your field's name or ID)
					validator.resetField('binder');
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-bindername").text(value['vendorName']);
				});
			}
		});
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
  		$('#currencySerial, #typeBind').prop('disabled', 'disabled');
  		$("textarea").prop('disabled', true);
  		$("#searchISSN, #searchOffi, #searchBinder").attr('disabled', 'disabled');
  		$(".vhideStat, .Retrieve, .Previous, .issselect").hide();  
  		$(".stausView").show();
  		
	}
	//////////////////////////////Enable///////////////////////////////////////
	function enable(){
		$("input").prop("disabled", false);
  		$("#save, #cancel").show();
  		$("#close").hide();
  		$('#currencySerial, #typeBind').prop('disabled', false);
  		$("textarea").prop('disabled', false);
  		$("#searchOffi, #searchBinder").attr('disabled', false);
  		$(".vhideStat").show();
  		$("#volFrom, #volTo, #issueFrom, #issueTo, #issueselected, #copyno").prop("disabled", true); 
		$("#editissuevolDes").prop("disabled", true);
		$("input[name=statusRadio][value='R']").prop("disabled",true);
		$('.Retrieve, .Previous').prop('disabled', 'disabled');
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
	
	//********************************************** Retrieve********************************************//
	$('.Retrieve').click(function(e){
		
		var controlNo = $("#controlNo").val();
		
			$('#modalRetrieve').modal('show');
			
			$('#modalNameRetrieve').text("Control Number " +controlNo);
			$("#saveRetrieve").attr('disabled', true);
			var tableOrder = $('#table-order').DataTable( {
				destroy: true,
				searching: false,
				bLengthChange: false,
				autoWidth: false,
				responsive: true,
				paging: true,
				select: true,
				//info: false,
			    ajax: {
			    	url: "resultTableOrder",
			        data : {ctrlno : controlNo},
			        type: "GET",
			        dataSrc: function (json) {
			            var return_data = new Array();
			            for(var i=0;i< json.length; i++){			              
			              return_data.push({
			                'OrderNo': json[i].orderno,
			                'CopyNo' : json[i].copy, 
			                'Volume From' : json[i].volfrom,
			                'Volume To' : json[i].volto,
			                'Information' : json[i].info +" issue(s) received",
			              })
			            }
			            return return_data;
			          },
			     },//This is the end of the AJAX object from the example above
			     	columns    : [
						{'data': 'OrderNo', className: "text-center"},
						{'data': 'CopyNo'},
						{'data': 'Volume From'},
						{'data': 'Volume To'},
						{'data': 'Information'},
					],
			});
			
			setTimeout(function(){ 
				var rowCount = tableOrder.column( 0 ).data().length;
				if(rowCount==0){
					messageBox("140","","");
					$("#closeModalRetrieve").click();
				}else{
					///////on click row			
					tableOrder.ajax.reload( function (json) {
					    $('#table-order tbody tr:eq(0)').click();
					} );
				}
			}, 1000);

			
			

			$('#table-order tbody').on( 'click', 'tr', function () {

				//tableOrder.fnClearTable();
				
				/*var index = $('#table-order').DataTable().rows({ search: 'applied'})
				.nodes().to$().index($(this).closest('tr'));*/
				
				 var tr = $(this).closest("tr");
		            //var rowindex = tr.index();
		            //Get row based on index
		            
		            // Get row based on tr instead.
		            var rowData = $("#table-order").DataTable().row(tr).data();
		            var orderNo = rowData.OrderNo;
		            var copyNo = rowData.CopyNo;
		            
				/*var data = tableOrder.row( this ).data();
				
				var orderNo = data["OrderNo"];
				var copyNo = data["Copy No"];*/
				
				/*alert("orderNo" +orderNo);
				alert("copyNo" +copyNo);
				alert("dfgds" +tableOrder.column( 2 ).data());*/
				
		            $(".orderIssue").text(orderNo);
		            $(".orderCopyno").text(copyNo);
				//$("#ordernoMast").val(orderNo);
					
			     $("#table-order  tbody tr").removeClass('row_selected');        
			     $(this).addClass('row_selected');
				
				/*if ( tr.hasClass('row_selected') ) {
					tr.removeClass('row_selected');
		        }
		        else {
		        	tableOrder.$('tr.row_selected').removeClass('row_selected');
		        	tr.addClass('row_selected');
		        }*/
			     
			     $('#table-issue').DataTable( {
						destroy: true,
						searching: false,
						bLengthChange: false,
						autoWidth: false,
						responsive: true,
						//info: false,
					    ajax: {
					    	url: "resultTableIssue",
					        data : {orderno : orderNo},
					        type: "GET",
					        dataSrc: function (json) {
					            var return_data = new Array();
					            for(var i=0;i< json.length; i++){	
					              return_data.push({
					                'Copy': '<input type="checkbox" id="example-select-all" name="id[]" value=" '+json[i].issno+' ">' +json[i].copy,
					                'Volume' : json[i].vol, 
					                'Issue Label' : json[i].isslabel,
					                'Issue No' : json[i].issno,
					                'Vol Desc' : json[i].voldesc,
					                'Iss Desc' : json[i].issdesc,
					                'Date Received' : json[i].dateReceived,
					              })
					            }
					            return return_data;
					          },
					     },//This is the end of the AJAX object from the example above
					     	columns    : [
					     	    {'data': 'Copy'},
								{'data': 'Volume', className: "text-center"},
								{'data': 'Issue Label', className: "text-center"},
								{'data': 'Issue No', className: "text-center"},
								{'data': 'Vol Desc'},
								{'data': 'Iss Desc'},
								{'data': 'Date Received', className: "text-center"},
							],
				});
			     
			    ///////////////////////////Save Retrieve click/////////////////////////////
				$('.saveRetrieve').click(function(){
					$("#closeModalRetrieve").click();
					
					$("#ordernoMast").val(orderNo);
					$("#copyno").val(copyNo);
					
					////getvalue when checked row
					var output2 = [];
					$('#table-issue').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
			     		/*var indexval  = $('#table-issue').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
			     		alert(indexval);*/
						var volobj = $(this).closest('tr').find('td:eq(1)').text();
						var issobj = $(this).closest('tr').find('td:eq(2)').text();
			     		var issnoobj = $(this).closest('tr').find('td:eq(3)').text();

			     		var obj = {};
			     		obj.issnoobj = issnoobj;
			     		obj.volobj = volobj;
			     		obj.issobj = issobj;
			     		output2.push(obj);
					});
					
					var total = output2.length;
					
					/*setTimeout(function(){ 
						if(total >= 1){
							$("#save").attr('disabled', false);
						}else{
							$("#save").attr('disabled', 'disabled');
						}
						
					}, 1000);*/
					
					if(total > 1 ){
						///volfrom
					    $("#volFrom").val(output2[0]['volobj']);
					    
					    //volto
					    $("#volTo").val(output2[total-1]['volobj']);
					    
					    //issfrom
					    $("#issueFrom").val(output2[0]['issnoobj']);
					    
					    //issfrom iabel
					    $(".issfromissbl").text("("+output2[0]['issobj']+")");
					    
					    //issto
					    $("#issueTo").val(output2[total-1]['issnoobj']);
					    
					   //issto iabel
					    $(".isstoissbl").text("("+output2[total-1]['issobj']+")");
					}else{
						///volfrom
					    $("#volFrom").val(output2[0]['volobj']);
					    
					    //volto
					    $("#volTo").val(output2[0]['volobj']);
					    
					    //issfrom
					    $("#issueFrom").val(output2[0]['issnoobj']);
					    
					    //issfrom iabel
					    $(".issfromissbl").text("("+output2[0]['issobj']+")");
					    
					    //issto
					    $("#issueTo").val(output2[0]['issnoobj']);
					    
					   //issto iabel
					    $(".isstoissbl").text("("+output2[0]['issobj']+")");
					}
					
					
					
					///issno
					var issnoArray = [];
				    for (var i = 0; i < output2.length; i++) {
				 		var code = output2[i]['issnoobj'];
				 		//alert(code)
				 	 	/*var obj = {};
				  		obj.code = code;*/
				 		issnoArray.push(code);
				 	}
				    
				    $("#issueselected").val(issnoArray.toString());
				});
				///////////////////////////////////////////////////////////////////////////
			});
			
			///////////////////////////////////////////WHEN CLICK CHECK BOX////////////
			/// 1) FOR SELECT ALL
			$('#example-select-all').on('click', function(){
				// Check/uncheck all checkboxes in the table
				var rows = $('#table-issue').DataTable().rows({ 'search': 'applied' }).nodes();
				if(!this.checked){
			    	$('#example-select-all', rows).prop('checked', false);
			    	$(".saveRetrieve").attr('disabled',true);
			   	}else{
			    	$('#example-select-all', rows).prop('checked', this.checked);
			    	$(".saveRetrieve").attr('disabled',false);
			    }
			});
			
			// Handle click on checkbox to set state of "Select all" control
		    $('#table-issue tbody').on('change', '#example-select-all', function(){
		    	
		       // If checkbox is not checked
		       if(!this.checked){
		    	  //$(".saveRetrieve").attr('disabled',true);
		          var el = $('#example-select-all').get(0);
		          var totaltick = $('#table-issue :input[type="checkbox"]:checked').length
		          if(totaltick == 0){
		        	  $(".saveRetrieve").attr('disabled',true);  
		          }else{
		        	  $(".saveRetrieve").attr('disabled',false);  
		          }
		          
		          // If "Select all" control is checked and has 'indeterminate' property
		          if(el && el.checked && ('indeterminate' in el)){
		             // Set visual state of "Select all" control 
		             // as 'indeterminate'
		             el.indeterminate = true;
		          }
		       }else{
		    	   $(".saveRetrieve").attr('disabled',false);
		       }
		    });
		    ///////////////////////////////////////////////////////////////////////////
		    
		    

	});
	
	//****************************************** END Retrieve** *****************************************//
	
	//********************************************** Previous********************************************//
	$('.Previous').click(function(e){

		var controlNo = $("#controlNo").val();
		
		$.get('GetPrevious', {
        	ctrlno : controlNo
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		messageBox("139","","");
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
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
				});
			}
		});
	});
	//****************************************** END Previous** *****************************************//
	
	//***************************************AREA FOR SEARCH ********************************************//
	$('#search').click(function(){
		$('#Serials_table').dataTable().fnClearTable();
		
		var input_criteria = $('#input-criteriaSer').val();
		var search_type = $("input[name='searchSelectionSerial']:checked").val();
		//var inputSentDate = moment($("#input-sentDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		
		$('#Serials_table').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//info: false,
		    ajax: {
		    	url: "resultSearchSerial",
		        data : {input_criteria : input_criteria, search_type : search_type},
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
		                'Volume' : json[i].volfrom +" - "+ json[i].volto,
		                'Issue' : json[i].issfrom +"("+ json[i].labelissfrom +") - "+ json[i].issto +"("+json[i].lableissto +")",
		                'Status' : stat,
		                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalSerials" data-whatever="View Binding Record" title="View Binding Record" data-rowid="'+json[i].refno+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalSerials" data-whatever="Edit Binding Record" title="Edit Binding Record" data-rowid="'+json[i].refno+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].refno+'"><span class="glyphicon glyphicon-trash" title="Delete Binding" ></span></button>',
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

		$("#closesearchSerial").click();  

	});
	//***********************************END AREA FOR SEARCH ********************************************//
	
	//***************************************AREA FOR DELETE ********************************************//
	/*function messageBox(code, criteria, label){
		var url = "Error_Message?GL79ERRCODE="+code+"" + 
			"&criteria=" + criteria + "&label="+label+"";
		$.ajax({
				url: url,
				success: function(result) {
					//swal('',result, 'info' );
					swal(result);
				}
			});
	}*/
	
	$('#Serials_table tbody').on( 'click', '#Delete', function () {
		var deleteSer = $(this).attr('data-id');
		//alert(deleteFundAccountChar);

		var index = $('#Serials_table').DataTable().rows({ search: 'applied'})
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
					url : "Handler_DeleteSer",
					data: { refno: deleteSer,
							loginid : liferayLogin},
						success : function(result) {
							var status = result.replace(/\s+/g, '');
							if (status == "ok") {
								messageBox("005","Deleted","@action");
								/*swal('Successfully Deleted!',
									'The record has been successfully removed.');*/
								$('.swal2-confirm').click(
									function() {
										$('#Serials_table').DataTable().row(index).remove().draw();
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
	//***********************************END AREA FOR DELETE ********************************************//
	
});