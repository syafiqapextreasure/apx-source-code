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

	 /////input max length
	 $("#input-contorlNo").attr('maxlength','10');
	 $("#isbn").attr('maxlength','20');

	 
	 
	 	$('#requestor tbody').on( 'click', '#ibtnDel', function () {
	
	 		 $(this).closest('tr').remove();
		
	} );
	//****************************************** AREA FOR MODAL *****************************************//
	 
	 //////modal add, edit, view
	 var stored;
	 $('#modalOdrMaint').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('mode'); // Extract info from data-* attributes
		var rowid = button.data('rowid');
		
		switch(recipient){
			case state = 1:
				//$("#addRequestorTable").find("tr:gt(0)").remove();
				addDisplay();
				modal.find('.modal-title').text("Add New Order");
				$(".addrow").prop("disabled",false);
				$("#orderhideorView").hide();
				$("#acqMode, #source, #currency, #subj").prop("selectedIndex",0);
				$("#div-vendorName, #title, #namePatr, #dept, #datereq").empty();
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
			case state = 2:
				modal.find('.modal-title').text("Edit Order");
				$(".addrow").prop("disabled",false);
				$('#requestor').dataTable().fnClearTable();
				addDisplay();
				stored = [];
				$("#ordermode, #source, #currency, #subj").prop("selectedIndex",-1);
				$("#div-vendorName, #title, #namePatr, #dept").empty();
				$("#orderhideorView").show();
				$("#orderno").attr('disabled', 'disabled');

				//$('.editHide').hide();
				////// getOrdermaintenance Particulars
				$.get('displayOrderMaint?action=2', {
					orderNo : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {

							////display vendor name
							$.get('Global?type=Handler&name=GetVendorName', {
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
							$("#title").html(value['title']);
							$("#input-vendorCode").val(value['vendor']);
							$("#acqMode").val(value['ordermode']);
							$("#source").val(value['source']);
							$("#currency").val(value['currency']);
							$("#subj").val(value['subj']);
							$("#div-exchange-Rate").val(value['exchangeRate']);
							$("#foreign-amount").val(value['fprice']);
							$("#local-amount").val(value['lprice']);
							$("#div-vendorName").text(value['descVendor']);
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
				    	 url: "displayOrderMaint?action=3&type=requestor&rowid="+rowid,
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
				                'Action': '<input type="button" class="ibtnDelete btn btn-md btn-danger" id="ibtnDel" value="Delete">'
				                	
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
			case state = 3:
				//$('#requestor').dataTable().fnClearTable();
				viewDisplay();
				modal.find('.modal-title').text("View Order");
				$("#div-vendorName, #title, #namePatr, #dept").empty();
				$("#ordermode, #source, #currency, #subj").prop("selectedIndex",0);
				
				$(".addrow").prop("disabled",true);
				
				////// getOrdermaintenance Particulars n Status part 1
				$.get('displayOrderMaint?action=3&type=all', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							//////////////xhidekn lagi ....
							////display vendor name
							$.get('Global?type=Handler&name=GetVendorName', {
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
							$("#title").text(value['title']);
							$("#input-vendorCode").val(value['vendor']);
							$("#acqMode").val(value['ordermode']);
							$("#source").val(value['source']);
							$("#currency").val(value['currency']);
							$("#subj").val(value['subj']);
							$("#div-exchange-Rate").val(value['exchangeRate']);
							$("#foreign-amount").val(value['fprice']);
							$("#local-amount").val(value['lprice']);
							$("#div-vendorName").text(value['descVendor']);
							
							
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
				
				var table = $('#status').DataTable();
				table.destroy();
				////// getOrdermaintenance Status part 2
				$('#status').DataTable( {
					retrieve:true,
					destroy: true,
					searching: false,
				    ajax: {
				        url: "displayOrderMaint?action=3&type=status&rowid="+rowid,
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
				        url: "displayOrderMaint?action=3&type=requestor&rowid="+rowid,
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
				        url: "displayOrderMaint?action=3&type=feedback&rowid="+rowid,
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
				        url: "displayOrderMaint?action=3&type=errorinsupply&rowid="+rowid,
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
		$('#acqMode, #source, #currency, #subj').prop('disabled', false);
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
		$('#acqMode, #source, #currency, #subj').prop('disabled', 'disabled');
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

        $.get('Global?type=Handler&name=GetPrate', {
        	currency : currency
        	}, function(data) {
        		$("#div-exchange-Rate").val(data.trim());  
        		var rate = $("#div-exchange-Rate").val();
        		var foreign = $("#foreign-amount").val();
        		var totalLocalAmount = (rate * foreign).toFixed(2);
        		if(rate==""){
        			//$("#local-amount").val(foreign);
        			$("#local-amount").val("0");
        		}else{
        			$("#local-amount").val(totalLocalAmount);
        			//$("#local-amount").focus();
        		}
        });
  
	});
	//******************************END AREA FOR DROPDOWN MENU *****************************************//
	
	
	//************************************ AREA FOR GET TODAY DATE *************************************//
	var today = new Date();           
    var formattedtoday = moment(today).format("DD/MM/YYYY");
    
	//********************************END AREA FOR GET TODAY DATE *************************************//
	
	//**************************END AREA FOR KEYUP PATRON ID AT REQUESTOR *****************************//
    $('.addrow').on( 'click', function () {
    
		 var getReqestor = $(".reqId").val().toUpperCase().trim();
		 //getReqestor.toUpperCase();
		 //alert("getReqestor : " +getReqestor);

		 $.get('CheckRequestor', {
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

			
					
			           var inputs  =   $("div[class='requestorId']"); //var inputs  =   $("div[class='requestorId']");
			           //alert("inputs : " +inputs); 
			           
			           var values = $("div[class='requestorId']").map(function (idx, ele) {
			        	   return $(ele).val();
			        	}).get();
			           //alert("values : " +values); 
			           var exist = true;
			  
			        if(exist == true){
			        	
			        	//alert(");
				       	 $.get("getPatronDept",{code : getReqestor},function(json){
				       
				   			 var return_data = new Array();
				   			 		stored.push(getReqestor);
				   			 		//alert(stored.toString() + "test");
				   			 		//alert(formattedtoday);
				   	            for(var i=0;i< json.length; i++){
				   	            	var html = 	'<tr>' + 
				   	            				'<td><div class="reqNo"></div></td>'+
				   	            				'<td><div class="requestorId">'+getReqestor+'</div></td>'+
				   	            				'<td>'+json[i].Name+'</td>'+
				   	            				'<td>'+json[i].Department+'</td>'+
				   	            				'<td><div class="datereq">'+formattedtoday+'</td>'+
				   	            				'<td><input type="button" class="ibtnDelete btn btn-md btn-danger" id="ibtnDel" value="Delete"></td>'+
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
			
			});
		}
		});
		 
		 

		   /**/

	 });
	 
/*	 $('#requestor tbody').on( 'click', '.ibtnDel', function () {
		$('#requestor').DataTable().row( $(this).parents('tr') ).remove().draw();	
	 });
    */
	 //******************************AREA FOR KEYUP PATRON ID AT REQUESTOR *****************************//

    
    //****************************************** AREA FOR keyup, keydown*******************************//
		/////vendor code
		$("#input-vendorCode").blur(function(e){

			var vendorCode = $("#input-vendorCode").val().toUpperCase();

			$('#input-vendorCode').val($('#input-vendorCode').val().toUpperCase());
			
			$("#div-vendorName").empty();
			if(vendorCode == ""){
				$("#save").attr('disabled',false);
				$("#input-vendorCode").css("border", "");
			}else{
			$.get('Global?type=Handler&name=GetVendorName', {
	        	code : vendorCode
			 	}, function(data) {
			 		$("#div-vendorName").text(data.trim());
			});
			}
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
		$("#title").empty();
		////display vendor name
		$.get('getCtrlNoTitle', {
        	code : ctrlNo
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$("#title").text(value['title2']);
				});
			}
		});
	});
	
	
	//clear title when CNTRL NO keydown backspace
	$("#input-contorlNo").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#title").empty();
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
		   $.get('CheckIsbn', {
	        	isbn : $("#isbn").val()
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					if(value['count'] >= 1){
						swal({
							text: "This ISBN is exist in Request Control. Would like to view this record?",
					        showCancelButton: true,
					        confirmButtonColor: "#DD6B55",
					        confirmButtonText: "Yes",
					        cancelButtonText: "No"
					      }).then(
					        function(isConfirm) {
					        		  $("#ordermodal").modal("show");
					        			$.get('Global?type=Modal&name=Modal_OrderRecord', {
					        				isbn : $("#isbn").val()
									 	}, function(data) {
									 		$("#ordermodalContent").html(data);
					        			});
					        		  
					        }
					      );
					}
				});
			}
			});
		
    });
	
	$('#ordermodal').on('hidden.bs.modal', function () {
		checkcatisbn();
	});
	
	

	function checkcatisbn(){

		var control = $("#input-contorlNo").val();
		
	  	  if(control.trim()==""){
	  		  swal("", "Please key in valid Control No to view related record.", "");
	  	  }else{
	  		$.get('CheckCatIsbn', {
	        	isbn : $("#isbn").val()
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					if(value['count'] >= 1){
						swal({
							text: "This ISBN is exist in Catalog. Would like to view this record?",
					        showCancelButton: true,
					        confirmButtonColor: "#DD6B55",
					        confirmButtonText: "Yes",
					        cancelButtonText: "No"
					      }).then(
					        function(isConfirm) {
					        	 $("#bibmodal").modal("show");
					        	 alert($("#input-contorlNo").val());
				        			$.get('Global?type=Modal&name=Modal_BibOrder', {
				        				ctrlno : $("#input-contorlNo").val()
								 	}, function(data) {
								 		$("#orderbibmodalContent").html(data);
				        			});
				        			
					        }
					      );
					}
				});
			}
			});
	  	  }
	}
	
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
    	}else if ($('#acqMode').val() === null) {
    		$('#acqMode').focus();
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
	

	function messageBox(code, criteria, label){
		
			$.get("Global?type=Handler&name=Error_Page",{GL79ERRCODE : code,
				criteria : criteria,
			 	label : label},function(result){
					swal(result);
				
			});
	} 

});


function matnoFocusOut(matno){
	var matno = $(matno).val();
	
	$.get("Global?type=Table&name=Table_BibSearch",{criteria:matno,actiontype:"keyupCtrlNo"},function(title){

		if(title.trim()=="Error"){
			$("#title").html("<font style='color:red'>Invalid control no.</font>");
			 $("#add-submit").prop('disabled', true);
		}else{
			$("#title").html(title);
			 $("#add-submit").prop('disabled', false);
		}
	   });	
}

function matnoKeydown(e,matno){
	var code = e.keyCode || e.which;
     if(code == '13' || code == '9'){
			var matno = $(matno).val();
	
			$.get("Global?type=Table&name=Table_BibSearch",{criteria:matno,actiontype:"keyupCtrlNo"},function(title){

				if(title.trim()=="Error"){
					$("#title").html("<font style='color:red'>Invalid control no.</font>");
					 $("#add-submit").prop('disabled', true);
				}else{
					$("#title").html(title);
					 $("#add-submit").prop('disabled', false);
				}
			   });	
     }
}