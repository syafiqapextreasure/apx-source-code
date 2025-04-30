$(document).ready(function() {
	
	//////datatable
	$('#OdrMaint2').DataTable({
			//responsive: true,
			scrollx: true,
			info: false,
			paging: true,
			ordering: false,
			searching:false,
			info: true,
			lengthChange: false,
			destory: true
	 });
	 
	 var t = $('#requestor').DataTable({
			//responsive: true,
			scrollx: true,
			info: false,
			paging: false,
			ordering: false,
			searching:false,
			//info: true,
			lengthChange: false,
			destory: true,
			bLengthChange: false,
			oLanguage: {"sZeroRecords": "", "sEmptyTable": ""}
	 });
	 
	 /*var table = $('#addRequestorTable').DataTable({
			responsive: true,
			scrollx: true,
			info: false,
			paging: true,
			ordering: false,
			searching:false,
			info: true,
			lengthChange: false,
			destroy: true,
			bLengthChange: false,
			
	 });*/
	 
	 /////input max length
	 $("#input-contorlNo").attr('maxlength','10');
	 $("#isbn").attr('maxlength','20');
	 
	 //$('#checkboxRequestor').attr('checked', true);
	 
	 
	//****************************************** AREA FOR MODAL *****************************************//
	 
	 //////modal add, edit, view
	 var stored;
	 $('#modalOdrMaint').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');
		
		modal.find('.modal-title').text(recipient);
		
		switch(recipient){
			case state = 'Add New Order':
				//$("#addRequestorTable").find("tr:gt(0)").remove();
				addDisplay();
				$(".addrow").prop("disabled",false);
				$("#orderhideorView").hide();
				$("#ordermode, #source, #currency, #subj").prop("selectedIndex",-1);
				$("#div-vendorName, #div-ctrolNo, #namePatr, #dept, #datereq").empty();
				/*var firstElement = $('tbody > tr').first();
			    table.rows(firstElement.nextAll('tr')).remove().draw();*/
			    $("#addRequestorRow").attr('disabled', true);
			    $(".btn-danger").attr('disabled', true);
			    $('input[name=quantity][value=copies]').attr('checked', true);
	        	$('input[name=quantity][value=set]').attr('checked', false);
	        	$('#set, #copiesSet').attr('readonly', true);
	    		$('#copies').attr('readonly', false);
	    		///$('#requestor').fdataTable().fnClearTable();
	    		stored = [];
		  		break;
			case state = 'Edit Record Order':
				$(".addrow").prop("disabled",false);
				$('#requestor').dataTable().fnClearTable();
				addDisplay();
				stored = [];
				$("#ordermode, #source, #currency, #subj").prop("selectedIndex",-1);
				$("#div-vendorName, #div-ctrolNo, #namePatr, #dept").empty();
				$("#orderhideorView").show();
				$("#orderno").attr('disabled', 'disabled');

				//$('.editHide').hide();
				////// getOrdermaintenance Particulars
				$.get('getOrdermaintenanceAcq1', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {

							////display vendor name
							$.get('getVendorName', {
					        	code : value['vendor']
							 	}, function(responseJson) {
								if(responseJson != null){
									$.each(responseJson, function(key,value) {
										$("#div-vendorName").text(value['vendorName']);
									});
								}
							});
							
							/// when COPIES ////

							if(value['orderSet'] == '0' || value['orderSet'] == undefined){
								$('input[name=quantity][value=copies]').attr('checked', true);
					        	$('input[name=quantity][value=set]').attr('checked', false);
								$("#copies").val(value['ordercopies']);
								$("#set").val('');
								$("#copiesSet").val('');
								$('#set, #copiesSet').attr('readonly', true);
								$('#copies').attr('readonly', false);
							}else{
								$('input[name=quantity][value=set]').attr('checked', true); 
								$('input[name=quantity][value=copies]').attr('checked', false); 
								$("#set").val(value['orderSet']);
								$("#copiesSet").val(value['ordercopies']);
								$("#copies").val('');
								$('#set, #copiesSet').attr('readonly', false);
								$('#copies').attr('readonly', true);
							}
							
							$("#orderno").val(value['orderNumber']);
							$("#input-contorlNo").val(value['ctrlno']);
							$("#div-ctrolNo").html(value['title']);
							$("#input-vendorCode").val(value['vendor']);
							$("#ordermode").val(value['ordermode']);
							$("#source").val(value['source']);
							$("#currency").val(value['currency']);
							$("#subj").val(value['subj']);
							$("#div-exchange-Rate").val(value['exchangeRate']);
							$("#foreign-amount").val(value['fprice']);
							$("#local-amount").val(value['lprice']);
							
							$("label[for='created']").text("Created on " +value['daaterec'] + " by " +value['officer']);
						});
					}
				});
				
				//alert(rowid)
				////get for requestor  
				$('#requestor').DataTable( {
					destroy: true,
					searching: false,
					bLengthChange: false,
					paging: false,
					info: false,
					oLanguage: {"sZeroRecords": "", "sEmptyTable": ""},
				    ajax: {
				        url: "getOrdermaintenance3Acq?rowid="+rowid,
				        type: "GET",
				        dataSrc: function (json) {
				            var return_data = new Array();
				           
				            for(var i=0;i< json.length; i++){
				              return_data.push({
				                'Request No': '<div class="reqNo">'+json[i].reqno+'</div>',  //json[i].reqno,
				                'Requestor' : '<div class="requestorId">'+json[i].requestor+'</div>',  //json[i].requestor, 
				                'Name' : json[i].name,
				                'Department' : json[i].department,
				                'Date Requested' : '<div class="datereq">'+json[i].dateRequestor+'</div>',  //json[i].dateRequestor,
				                'Action': '<input type="button" class="ibtnDel btn btn-md btn-danger" value="Delete">'
				                	
				                	/*'<td><div class="reqNo"></div></td>'+
	   	            				'<td><div class="requestorId">'+getReqestor+'</div></td>'+
	   	            				'<td>'+json[i].patrname+'</td>'+
	   	            				'<td>'+json[i].patrdept+'</td>'+
	   	            				'<td><div class="datereq">'+formattedtoday+'</td>'+
	   	            				'<td><input type="button" class="ibtnDel btn btn-md btn-danger" value="Delete"></td>'+*/
				              })
				            }
				            return return_data;
				          },
				     },//This is the end of the AJAX object from the example above
				     	columns    : [
							{'data': 'Request No'},
							{'data': 'Requestor'},
							{'data': 'Name'},
							{'data': 'Department'},
							{'data': 'Date Requested'},
							{'data': 'Action'}
						],
				});
				/*$.get("getOrdermaintenance3",{rowid : rowid},function(json){
	    			 var return_data = new Array();
	    	           
	    	            for(var i=0;i< json.length; i++){
	    	            	t.row.add( [
	    	            	            '<div class="reqNo">'+json[i].reqno+'',
	    	            	            '<div class="requestorId">'+json[i].requestor+'',
	    	            	            json[i].name,
	    	            	            json[i].department,
	    	    			            '<div class="datereq">'+json[i].dateRequestor+'',
	    	    			            '<input type="button" class="ibtnDel btn btn-md btn-danger" value="Delete">'
	    	    			        ] ).draw( false );
	    	            }
	    		 });*/
	  			break;
			case state = 'View Record Order':
				//$('#requestor').dataTable().fnClearTable();
				viewDisplay();
				$("#div-vendorName, #div-ctrolNo, #namePatr, #dept").empty();
				$("#ordermode, #source, #currency, #subj").prop("selectedIndex",-1);
				
				$(".addrow").prop("disabled",true);
				
				////// getOrdermaintenance Particulars n Status part 1
				$.get('getOrdermaintenanceAcq1', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							//////////////xhidekn lagi ....
							////display vendor name
							$.get('getVendorName', {
					        	code : value['vendor']
							 	}, function(responseJson) {
								if(responseJson != null){
									$.each(responseJson, function(key,value) {
										$("#div-vendorName").text(value['vendorName']);
									});
								}
							});
							
							//alert(value['orderSet'] +" orderset");
							/// when COPIES ////
							if(value['orderSet'] == '0' || value['orderSet'] == undefined){
								$('input[name=quantity][value=copies]').attr('checked', true);
					        	$('input[name=quantity][value=set]').attr('checked', false);
								$("#copies").val(value['ordercopies']);
								$("#set").val('');
								$("#copiesSet").val('');
							}else{
								$('input[name=quantity][value=set]').attr('checked', true); 
								$('input[name=quantity][value=copies]').attr('checked', false); 
								$("#set").val(value['orderSet']);
								$("#copiesSet").val(value['ordercopies']);
								$("#copies").val('');
							}
							
							$("#orderno").val(value['orderNumber']);
							$("#input-contorlNo").val(value['ctrlno']);
							$("#div-ctrolNo").text(value['title']);
							$("#input-vendorCode").val(value['vendor']);
							$("#ordermode").val(value['ordermode']);
							$("#source").val(value['source']);
							$("#currency").val(value['currency']);
							$("#subj").val(value['subj']);
							$("#div-exchange-Rate").val(value['exchangeRate']);
							$("#foreign-amount").val(value['fprice']);
							$("#local-amount").val(value['lprice']);
							
							
							$("#div-status").text(value['currentStatusDesc']);
							$("#div-refNo").val(value['refno']);
							$("#orderdate").val(value['oderDate']);
							$("#expectedDate").val(value['expectedDate']);
							$("#claims1").val(value['claim1']);
							$("#claims2").val(value['claim2']);
							$("#claims3").val(value['claim3']);
							$("label[for='created']").text("Created on " +value['daaterec'] + " by " +value['officer']);
						});
					}
				});
				
				////// getOrdermaintenance Status part 2
				$('#status').DataTable( {
					destroy: true,
					searching: false,
					bLengthChange: false,
				    ajax: {
				        url: "getOrdermaintenance2Acq?rowid="+rowid,
				        type: "GET",
				        dataSrc: function (json) {
				            var return_data = new Array();
				           
				            for(var i=0;i< json.length; i++){
				              return_data.push({
				                'Accession Number': json[i].accno,
				                'Branch' : json[i].branch, 
				                'Location' : json[i].locadesc,
				                'Item Category' : json[i].itemCategory,
				                'Condition' : json[i].condition, 
				                'SMD' : json[i].smd
				              })
				            }
				            return return_data;
				          },
				     },//This is the end of the AJAX object from the example above
				     	columns    : [
							{'data': 'Accession Number'},
							{'data': 'Branch'},
							{'data': 'Location'},
							{'data': 'Item Category'},
							{'data': 'Condition'},
							{'data': 'SMD'}
						]
				});
				
				////get for requestor  
				$('#requestor').DataTable( {
					destroy: true,
					searching: false,
					bLengthChange: false,
				    ajax: {
				        url: "getOrdermaintenance3Acq?rowid="+rowid,
				        type: "GET",
				        dataSrc: function (json) {
				            var return_data = new Array();
				           
				            for(var i=0;i< json.length; i++){
				              return_data.push({
				                'Request No': json[i].reqno,
				                'Requestor' : json[i].requestor, 
				                'Name' : json[i].name,
				                'Department' : json[i].department,
				                'Date Requested' : json[i].dateRequestor,
				                '': ''
				              })
				            }
				            return return_data;
				          },
				     },//This is the end of the AJAX object from the example above
				     	columns    : [
							{'data': 'Request No'},
							{'data': 'Requestor'},
							{'data': 'Name'},
							{'data': 'Department'},
							{'data': 'Date Requested'},
							{'data': ''}
						],
						columnDefs: [
							{
						     	"targets": [ 5 ],
						     	"visible": false,
						     	"searchable": false
							},
						]
				});
				
				////get for feedback
				$('#feedback').DataTable( {
					destroy: true,
					searching: false,
					bLengthChange: false,
				    ajax: {
				        url: "getOrdermaintenance4Acq?rowid="+rowid,
				        type: "GET",
				        dataSrc: function (json) {
				            var return_data = new Array();
				           
				            for(var i=0;i< json.length; i++){
				              return_data.push({
				            	  'Date': json[i].date,
					              'Feedback' : json[i].feedback, 
					              'Officer' : json[i].officer
				              })
				            }
				            return return_data;
				          },
				     },//This is the end of the AJAX object from the example above
				     	columns    : [
							{'data': 'Date'},
							{'data': 'Feedback'},
							{'data': 'Officer'}
						]
				});
				
				////get for errorInSupply
				$('#errorInSupply').DataTable( {
					destroy: true,
					searching: false,
					bLengthChange: false,
				    ajax: {
				        url: "getOrdermaintenance5Acq?rowid="+rowid,
				        type: "GET",
				        dataSrc: function (json) {
				            var return_data = new Array();
				           
				            for(var i=0;i< json.length; i++){
				              return_data.push({
				                'Date': json[i].date,
				                'Feedback' : json[i].feedback, 
				                'Officer' : json[i].officer
				              })
				            }
				            return return_data;
				          },
				     },//This is the end of the AJAX object from the example above
				     	columns    : [
							{'data': 'Date'},
							{'data': 'Feedback'},
							{'data': 'Officer'}
						]
				});
				
				break;
		}
		
	 });
	 
	//clear all input fields in bootstrap modal when close modal #modalAdd
	$('#modalOdrMaint').on('hidden.bs.modal', function () {
		$(this).find('form').trigger('reset');
		$('#formdataOrderMain').data('bootstrapValidator').resetForm();
		$('#formdataOrderMain a:first').tab('show');
		$('#requestor').dataTable().fnClearTable();
			//$(this).removeData('bs.modal');
			//$('#formdata').bootstrapValidator('resetForm', true);
	});
	 
	//**************************************END AREA FOR MODAL *****************************************//
	 
	//***************************************AREA FOR FUNCTION *****************************************//
	/// hide form bar
	function addDisplay(){
		$('#StatusTab, #FeedbackTab, #ErrorTab').hide();
		$("input").attr('disabled',false);
		$("input[type=radio]").attr('disabled', false);
		$('#ordermode, #source, #currency, #subj').prop('disabled', false);
		$("#deatilRequest").show();
		//$("#viewReqestor").hide();
		//$("#addRequestor").show();
		$("#save, #cancel").show();
  		$("#close").hide();
  		$("#searchCtrlNo, #searchvendor, #searchpatr").attr('disabled', false);
  		
		//$('#addRequestor, #removeRequestor, #clearRequestor').attr('disabled', false);
	}
	
	//show form bar
	function viewDisplay(){
		$('#StatusTab, #FeedbackTab, #ErrorTab').show();
		$("#formdataOrderMain input").attr('disabled','disabled');
		$("#formdataOrderMain input[type=radio]").attr('disabled', true);
		$('#ordermode, #source, #currency, #subj').prop('disabled', 'disabled');
		$("#deatilRequest").hide();
		//$("#viewReqestor").show();
		//$("#addRequestor").hide();
		$("#save, #cancel").hide();
  		$("#close").show();
  		$("#searchCtrlNo, #searchvendor, #searchpatr").attr('disabled', 'disabled');
  		$("#orderno").attr('disabled', 'disabled');
  		$("#orderhideorView").show();
		//$('#addRequestor, #removeRequestor, #clearRequestor').attr('disabled', 'disabled');
		////... -> xbuat lagi
  		
	}
	
	//***********************************END AREA FOR FUNCTION *****************************************//
	
	//**********************************AREA FOR ON CHANGE CURRENCY*************************************//
	////on change currency for select to get Currency Rate
	$("#currency").change(function(){

        var currency = $("#currency option:selected").val();     

        $.get('getCurrencyBankRateAcq', {
        	currency : currency
        	}, function(responseJson) {
    			if(responseJson != null){
    				$.each(responseJson, function(key,value) {
    					$("#div-exchange-Rate").val(value['rate']);
    					$("#div-exchange-Rate").focus();
    				});
    			}
    			
        });
        
        setTimeout(function(){
        	var rate = $("#div-exchange-Rate").val();
    		var foreign = $("#foreign-amount").val();
    		
    		var totalLocalAmount = (rate * foreign).toFixed(2);

    		$("#local-amount").val(totalLocalAmount);

		}, 1000); 
	});
	//******************************END AREA FOR DROPDOWN MENU *****************************************//
	
	//**********************************AREA FOR HIDE SELECT VALUE *************************************//
	$("#ordermode, #source, #currency, #subj").prop("selectedIndex",-1);
	//******************************END AREA FOR HIDE SELECT VALUE *************************************//
	
	//************************************ AREA FOR GET TODAY DATE *************************************//
	var today = new Date();           
    var formattedtoday = moment(today).format("DD/MM/YYYY");
    
	//********************************END AREA FOR GET TODAY DATE *************************************//
	
	//**************************END AREA FOR KEYUP PATRON ID AT REQUESTOR *****************************//
    $('.addrow').on( 'click', function () {
		 var getReqestor = $(".reqId").val().toUpperCase().trim();
		 //getReqestor.toUpperCase();
		 //alert("getReqestor : " +getReqestor);
		 
		 $.get('getRequestorAcq', {
			 patronID : getReqestor
	 	}, function(responseJson) {
		if(responseJson != null){
			if(responseJson==''){
				messageBox("036","",""); 
				$(".reqId").val("");
				setTimeout(function(){
			        $('.reqId')[0].focus();
			    }, 2000);	
				
			}
			$.each(responseJson, function(key,value) {

				var EXPDATE =  moment(value['expdate']).format("DD/MM/YYYY");
				var today2 = moment(today2).format("DD/MM/YYYY");
				
				if(moment(today).format("YYYY-MM-DD") > moment(value['expdate']).format("YYYY-MM-DD")){
					
					messageBox("032","","");
					$(".reqId").val("");
					setTimeout(function(){
						 $('.reqId')[0].focus();
				    }, 1000);
					
				}else if(value['CHARGING'] == 'N'){

					messageBox("118",value['STATDESC'], "@action");
					//alert("not allow")
					$(".reqId").val("");
					setTimeout(function(){
						$('.reqId')[0].focus();
				    }, 1000);
				}else{
					//alert("else");
					
			           var inputs  =   $("div[class='requestorId']"); //var inputs  =   $("div[class='requestorId']");
			           //alert("inputs : " +inputs); 
			           
			           var values = $("div[class='requestorId']").map(function (idx, ele) {
			        	   return $(ele).val();
			        	}).get();
			           //alert("values : " +values); 
			           var exist = true;
			           $.each(inputs,function(k,v){ 
			        	   /*alert("k" +k);
			        	   alert("v" +v);*/
			               var getVal  =   $(".reqId").val().toUpperCase().trim();
			               //alert("getVal" +getVal);

			               //alert(stored.indexOf(getVal) + 'indexOf');
			               if(stored.indexOf(getVal) != -1 ){
			               	///alert("Patron already have")
			            	   messageBox("117","","");
			                   $(".reqId").val("").trim();
			               		$(".reqId").focus();
			     
			                   exist = false;
			               }

			               //alert("text " +stored.push(getVal));
			           });
			        
			        if(exist == true){
			        	
			        	//alert("add");
				       	 $.get("getPatrNameDept",{code : getReqestor},function(json){
				   			 var return_data = new Array();
				   			 		stored.push(getReqestor);
				   			 		//alert(stored.toString() + "test");
				   			 		//alert(formattedtoday);
				   	            for(var i=0;i< json.length; i++){
				   	            	var html = 	'<tr>' + 
				   	            				'<td><div class="reqNo"></div></td>'+
				   	            				'<td><div class="requestorId">'+getReqestor+'</div></td>'+
				   	            				'<td>'+json[i].patrname+'</td>'+
				   	            				'<td>'+json[i].patrdept+'</td>'+
				   	            				'<td><div class="datereq">'+formattedtoday+'</td>'+
				   	            				'<td><input type="button" class="ibtnDel btn btn-md btn-danger" value="Delete"></td>'+
				   	            				'</tr>';
				   	            	//alert(html);
				   	    			            /*'<td><div class="requestorId">'+getReqestor+ 
				   	    			            json[i].patrname+
				   	    			            json[i].patrdept+
				   	    			            '<td><div class="datereq">'+formattedtoday+
				   	    			            '<input type="button" class="ibtnDel btn btn-md btn-danger" value="Delete">';*/
				   	            	$("#requestor").append(html);
				   	            	/*t.row.add( [
				   	            	            '<div class="reqNo">',
				   	    			            '<div class="requestorId">'+getReqestor, 
				   	    			            json[i].patrname,
				   	    			            json[i].patrdept,
				   	    			            '<div class="datereq">'+formattedtoday,
				   	    			            '<input type="button" class="ibtnDel btn btn-md btn-danger" value="Delete">'
				   	    			        ] ).draw( false );*/
				   	            }
				   		 });
				       	 $("#requestorId").val("");
			        }
				}
			});
		}
		});
		 
		 

		   /**/

	 });
	 
	 $('#requestor tbody').on( 'click', '.ibtnDel', function () {
		$('#requestor').DataTable().row( $(this).parents('tr') ).remove().draw();	
	 });
    
	 //******************************AREA FOR KEYUP PATRON ID AT REQUESTOR *****************************//

    
    //****************************************** AREA FOR keyup, keydown*******************************//
    /////vendor code
	$("#input-vendorCode").focusout(function(e){
		var vendorCode = $("#input-vendorCode").val();
		//alert("vendorCode " + vendorCode);
		$("#div-vendorName").empty();
		////display vendor name
		$.get('GetVendorName', {
        	code : vendorCode
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
				
				//alert("dfs"+value['contBegin']+"lds");

					var contbegin = moment(value['contBegin']).format("YYYY-MM-DD");
					var contEnd = moment(value['contEnd']).format("YYYY-MM-DD");
						
					var currentdate = moment(today).format("YYYY-MM-DD");

					if( ((value['contBegin']) == "" && (value['contEnd']) == "") || ((value['contBegin']) == " " && (value['contEnd']) == " ")){
						$("#div-vendorName").text(value['vendorName']);
					}else{
						//alert("STARTTTTTT" + moment(contbegin).isBefore(currentdate));
						//alert("ENDDDD" + moment(contEnd).isAfter(currentdate));
						
						var checkStart = moment(contbegin).isBefore(currentdate);
						var checkEnd = moment(contEnd).isAfter(currentdate);
						
						if(checkStart == true){
							
							if(checkEnd == true){
								$("#div-vendorName").text(value['vendorName']);
							}else{
								$("#input-vendorCode").val("");
								$("#input-vendorCode").focus();
								messageBox("169",moment(value['contEnd']).format("DD/MM/YYYY"),"@date");
								//alert("Vendor Contract End At "+moment(value['contEnd']).format("DD/MM/YYYY"));
							}
						}else{
							$("#input-vendorCode").val("");
							$("#input-vendorCode").focus();
							$( "#input-vendorCode" ).removeClass( ".has-success .form-control" );
							messageBox("170",moment(value['contBegin']).format("DD/MM/YYYY"),"@date");
							//alert("Vendor Contract Start At "+moment(value['contBegin']).format("DD/MM/YYYY"));
						}
					}
					
					/*moment('2020-01-20').isAfter('2020-01-21'); // false
						moment('2020-01-20').isAfter('2020-01-19'); // true
						
						moment('2020-01-20').isBefore('2020-01-21'); // true
						moment('2020-01-20').isBefore('2020-01-19'); // false*/

				});
			}
		});
	});
	
	
	//clear vendor name when vendor code keydown backspace
	$("#input-vendorCode").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#div-vendorName").empty();
		}
	});
	
	/////CNTRL NO
	$("#input-contorlNo").keyup(function(e){
		var ctrlNo = $("#input-contorlNo").val();
		//alert("vendorCode " + vendorCode);
		$("#div-ctrolNo").empty();
		////display vendor name
		$.get('getCtrlNoTitle', {
        	code : ctrlNo
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$("#div-ctrolNo").text(value['title2']);
				});
			}
		});
	});
	
	
	//clear title when CNTRL NO keydown backspace
	$("#input-contorlNo").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#div-ctrolNo").empty();
		}
	});
	
	////contol input value for foreign-amount and local-amount
	$("#foreign-amount, #local-amount").on("keypress keyup blur",function (event) {
        //this.value = this.value.replace(/[^0-9\.]/g,'');
		 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
	         if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
	             event.preventDefault();
	         }
	         if(($(this).val().indexOf('.') != -1) && ($(this).val().substring($(this).val().indexOf('.'),$(this).val().indexOf('.').length).length>4 )){
			     /*if (event.keyCode !== 8 && event.keyCode !== 46 ){ //exception
			         event.preventDefault();
			     }*/
	         }
    });
	
	/////key in number only
	$("#set, #copiesSet, #copies").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });
	
	//////foreign amount key in
	$("#foreign-amount").keyup(function(){
		var rate = $("#div-exchange-Rate").val();
		var foreign = $("#foreign-amount").val();
		var totalLocalAmount = (rate * foreign);

		if(rate==""){
			//$("#local-amount").val(foreign);
			$("#local-amount").val("0");
		}else{
			$("#local-amount").val(parseFloat(totalLocalAmount).toFixed(4));
			//$("#local-amount").focus();
		}
	});
	
	//////local amount key in
	$("#local-amount").keyup(function(){
		var rate = $("#div-exchange-Rate").val();
		var local = $("#local-amount").val();
		var totalForeignAmount = (local / rate);
		/*alert("rate " +rate);
		alert("totalForeignAmount" +totalForeignAmount);*/
		if(rate==""){
			//$("#foreign-amount").val(local);
			$("#foreign-amount").val('');
		}else{
			$("#foreign-amount").val(parseFloat(totalForeignAmount).toFixed(4));
			//$("#foreign-amount").focus();
		}
	});
	
	//***************************************END AREA FOR keyup, keydown*********************************//
	
	//***************************************AREA FOR Radio Button **************************************//
	
	////on change Quantity for radio button
	$('input[name=quantity]').change(function(){
		var addSelection = $( 'input[name=quantity]:checked' ).val();
		//alert("addSelection"+addSelection);
		
		switch(addSelection){
        case state = 'set':
        	$('#set, #copiesSet').attr('readonly', false);
        	$('#copies').attr('readonly', true);
        	$("#copies").val('');
			var data = $('#formdataOrderMain').data('bootstrapValidator');
                    data.resetField('copies');
					data.resetField('copiesSet');
					data.resetField('copies');
	  		break;
        case state = 'copies':
        	$('#set, #copiesSet').attr('readonly', true);
    		$('#copies').attr('readonly', false);
    		$('#set, #copiesSet').val('');
			//$(this).find('form').trigger('reset');
			 var data = $('#formdataOrderMain').data('bootstrapValidator');
                    data.resetField('copies');
					data.resetField('copiesSet');
					data.resetField('copies');

	  		break;
	    }
		
	});
	
	/////uppercase
	$('#input-vendorCode').keyup(function() {
		$(this).val($(this).val().toUpperCase());
	});
	
	////ISBN
	$("#isbn").blur(function(){
        //alert("This input field has lost its focus.");
        //alert($("#isbn").val())
        $.get('getDuplicateIsbn', {
        	isbn : $("#isbn").val()
	 	}, function(responseJson) {
		if(responseJson != null){
			/*if(responseJson==''){				
			}*/
			$.each(responseJson, function(key,value) {
				if(value['count'] >= 1){
					//alert("duplicate");
					//swal("This ISBN is exist");
					messageBox('119',"", "");
				}/*else{
					alert("not");
				}*/
			});
		}
		});
    });
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	//$('#viewNote').click(function(){
	$(document).on('click', '#viewNote', function(event){
		// check for empty value
		if(!checkEmptyValue()) {
			// Do not proceed if empty values detected
			swal('Empty values detected!');

			$('#modalViewNote').modal('hide');
			return false;
		}else{
			//$('#modalViewNote').modal('show'); 
			//$('#viewNote').prop('data-target','#modalViewNote');
			 event.preventDefault();

		     $('#modalViewNote').modal('show');

			var getOrderno = $(".orderno2").val();	
			
			///alert(getOrderno+"getOrderno");
			
			
			var modalName = $("#modalName").text();
			//alert("fdf"+modalName);
			///21102020
			
			if( (modalName == 'Add New Order') && (getOrderno.length == 0)){
				//alert("dsfsfs");
				$.get("Handler_Getorderno",{
				},
				function(message){
					var status = message.replace(/\s+/g, '');
					if (status != "") {
						$("#orderhideorView").show();
						$("#orderno").attr('disabled', 'disabled');
						//alert("ccc"+message.trim());
						
						//$("#passval").val(message.trim());
						
						setTimeout(function(){
							$("#orderno").val(message.trim());
							$(".orderno2").val(message.trim());
							$("#passval").val(message.trim());
						}, 800); 
					}else{
						swal("Unsuccessfully");
					}
				});
			}else{
				setTimeout(function(){
					$("#passval").val($("#orderno").val());
				}, 1000);
			}
		}
		/*if(getOrderno == ""){
			alert("addorderno");
			$.get("Handler_Getorderno",{
			},
			function(message){
				var status = message.replace(/\s+/g, '');
				if (status != "") {
					$("#orderhideorView").show();
					$("#orderno").attr('disabled', 'disabled');
					$("#orderno").val(message.trim());
					//$("#passval").val(message.trim());
					setTimeout(function(){
						$("#passval").val(message.trim());
					}, 1000); 
				}else{
					swal("Unsuccessfully");
				}
			});
		}else{
			setTimeout(function(){
				$("#passval").val(getOrderno);
			}, 1000); 
			
		}*/
		
		//$("#passval").val(getOrderno);
		
		/*$.get("Handler_Getorderno",{
			},
			function(message){
				var status = message.replace(/\s+/g, '');
				if (status != "") {
					$("#orderhideorView").show();
					$("#orderno").attr('disabled', 'disabled');
					$("#orderno").val(message.trim());
				}else{
					swal("Unsuccessfully");
				}
		});*/
		
	});
	
	//////////////////////////////Check empty value///////////////////////////////
	function checkEmptyValue() {
		var result = true;
		
		var radioValue = $("input[name='quantity']:checked").val();
		
		if ($('#input-contorlNo').val() === '') {
    		$('#input-contorlNo').focus();
    		result = false;
    		return false;
    	}else if ($('#input-vendorCode').val() === '') {
    		$('#input-vendorCode').focus();
    		result = false;
    		return false;
    	}else if ($('#ordermode').val() === null) {
    		$('#ordermode').focus();
    		result = false;
    		return false;
    	}else if ($('#source').val() === null) {
    		$('#source').focus();
    		result = false;
    		return false;
    	}else if ($('#currency').val() === null) {
    		$('#currency').focus();
    		result = false;
    		return false;
    	}else if ($('#div-exchange-Rate').val() === '') {
    		$('#div-exchange-Rate').focus();
    		result = false;
    		return false;
    	}else if ($('#foreign-amount').val() === '') {
    		$('#foreign-amount').focus();
    		result = false;
    		return false;
    	}else if ($('#local-amount').val() === '') {
    		$('#local-amount').focus();
    		result = false;
    		return false;
    	}else if(radioValue == "set"){
    		if ($('#set').val() === '') {
        		$('#set').focus();
        		result = false;
        		return false;
    		}else if ($('#copiesSet').val() === '') {
        		$('#copiesSet').focus();
        		result = false;
        		return false;
    		}	
    	}else if(radioValue == "copies"){
    		if ($('#copies').val() === '') {
        		$('#copies').focus();
        		result = false;
        		return false;
    		}	
    	}
	    return result;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	

});