$(document).ready(function() {
	
	///get ill accno max length
	//var accmaxlength;
	$(".acclength").hide();
	
	$.get('GetIllLegth', {
	 	}, function(responseJson) {
	 		$(".acclength").text(responseJson);
	});
	
	
	$(".SMD, .ItemCatgory, .Loca, .Con").prop("selectedIndex",-1);
	
	
	$("#cboReferenceNo").prop("selectedIndex",-1);
	$("#save, #preview, #generate").attr('disabled', true);
	
	$(".lblRequestorId").hide();
	var liferayLogin = $("#liferayLogin").val();
	
	var today = new Date(); 
	$('#txtReceivedDate').val(moment(today).format("DD/MM/YYYY"));
	$('#txtReceivedDate, #txtDueDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
		//startDate:  moment(today).format("DD/MM/YYYY")
	});

	
	/////key in number only
	$("#txtCopies").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });
	
	
	$('#refno').DataTable( {
		destroy: true,
		searching: true,
		bLengthChange: false,
		autoWidth: true,
		responsive: true,
		processing: true,
		//info: false,
	    ajax: {
	    	url: "ResultRefNo",
	        type: "GET",
	        dataSrc: function (json) {
	            var return_data = new Array();

	            for(var i=0;i< json.length; i++){

	              return_data.push({
	            	'' : '<input type="radio" name="id[]" value="'+json[i].sReferenceNo+'">',
	                'Reference Number' : json[i].sReferenceNo, 
	                'Title' : json[i].sTitle,
	              })
	            }
				
	            return return_data;
	          },
	     },//This is the end of the AJAX object from the example above
	     	columns    : [
				{'data': ''},
				{'data': 'Reference Number'},
				{'data': 'Title'},
			],
	});
	
	$('#cboReferenceNo').on('change', function() {
	   
	    var refno = $(this).find(":selected").val();
	    
	    setTimeout(function(){
	    	$.get('GetOutgoingValue', {
	        	code : refno
			 	}, function(responseJson) {
			 		if(responseJson == ""){
			 			messageBox("158","","");
		 		 	}
			 		
			 		if(responseJson != null){
			 			$.each(responseJson, function(key,value) {
			 				var dateReceive = value['sReceiveDate'];
			 				var duedate = value['sDueDate'];
			 				var copy = value['iCopy'];
			
			 				if(dateReceive=="-"){
			 					$("#preview").attr('disabled', false);
			 					if(duedate == "-"){
			 					}else{
			 						$("#txtDueDate").val(value['sDueDate']);
			 					}
			 					if(copy == " "){
			 					}else{
			 						$("#txtCopies").val(value['iCopy']);
			 					}
			 					$(".lblTitle").text(value['sTitle']);
			 					$(".lblCallNo").text(value['sCallNo']);
			 					$(".lblControlNo").text(value['sCtrlNo']);
			 					$(".lblRequestor").text(value['sRequestor']);
			 					$(".lblRequestorId").text(value['sreqId']);
			 					$(".lblLendingLib").text(value['sLendingLib']);
			 					$(".lblRequestedDate").text(value['sReqDate']);
			 					$(".lblExpectedDate").text(value['sExptDate']);
			 					//$("#txtReceivedDate").val(value['sReceiveDate']);
			 					//$("#txtDueDate").val(value['sDueDate']);
			 					//$("#txtCopies").val(value['iCopy']);
		 					}else{
		 						messageBox("159","","");
		 					}
			 			});
			 		}
			});
		},800);
  
	});
	
	
	/////////////////////////////
	$('#detailRefNo').on('show.bs.modal', function (event) {
		$('#refno').DataTable().ajax.reload();
		$("#refnoOK").attr('disabled', true);
	});
	
	$('#refno tbody').on('change', 'input[type="radio"]', function(){
    	
	       // If checkbox is not checked
	       if(!this.checked){
	    	   $("#refnoOK").attr('disabled',true);
	          var el = $('#example-select-all').get(0);
	          // If "Select all" control is checked and has 'indeterminate' property
	          if(el && el.checked && ('indeterminate' in el)){
	             // Set visual state of "Select all" control 
	             // as 'indeterminate'
	             el.indeterminate = true;
	          }
	       }else{
	    	   $("#refnoOK").attr('disabled',false);
	       }
	});
	
	$("#refnoOK").click(function(){
		$('#refno input:radio:checked').each(function () {
			var sReferenceNo = this.value;
			$("#illreceiveForm #cboReferenceNo").val(sReferenceNo);
			$("#closerefno").click();
			
			setTimeout(function(){
		    	$.get('GetOutgoingValue', {
		        	code : sReferenceNo
				 	}, function(responseJson) {
				 		if(responseJson == ""){
				 			messageBox("158","","");
			 		 	}
				 		
				 		if(responseJson != null){
				 			$.each(responseJson, function(key,value) {
				 				var dateReceive = value['sReceiveDate'];
				 				var duedate = value['sDueDate'];
				 				var copy = value['iCopy'];

				 				if(dateReceive=="-"){
				 					if(duedate == "-"){
				 					}else{
				 						$("#txtDueDate").val(value['sDueDate']);
				 					}
				 					if(copy == " "){
				 					}else{
				 						$("#txtCopies").val(value['iCopy']);
				 					}
				 					$("#preview").attr('disabled', false);
				 					$(".lblTitle").text(value['sTitle']);
				 					$(".lblCallNo").text(value['sCallNo']);
				 					$(".lblControlNo").text(value['sCtrlNo']);
				 					$(".lblRequestor").text(value['sRequestor']);
				 					$(".lblRequestorId").text(value['sreqId']);
				 					$(".lblLendingLib").text(value['sLendingLib']);
				 					$(".lblRequestedDate").text(value['sReqDate']);
				 					$(".lblExpectedDate").text(value['sExptDate']);
				 					//$("#txtReceivedDate").val(value['sReceiveDate']);
				 					//$("#txtCopies").val(value['iCopy']);
			 					}else{
			 						messageBox("159","","");
			 					}
				 			});
				 		}				 		
				});
			},800);
		});
	});
	
	////preview click
	
	$("#preview").click(function(){
		
		var duedate = $("#txtDueDate").val();
		var reqdate = $(".lblRequestedDate").text();
		var ctrlno = $(".lblControlNo").text();
		var refno = $("#cboReferenceNo").val();
		var reqid = $(".lblRequestorId").text();
		
		$.post("GeneratePreviewDocument", {
			action : "EL03",
		 	liferayLogin : liferayLogin,
		 	refno : refno,
		 	reqDate : reqdate,
		 	sControlNo : ctrlno, 
		 	sRequestor : reqid,
		 	action2 : "preview",
		 	duedate : duedate
		 	
		}, function(result){

				 var w = window.open('about:blank');
				 	w.document.open();
				 	w.document.write(result);
				 	w.document.close();
				 	//w.print();
		});
		
	});
	
	
	var table = $('#receiveIll').DataTable( {
        scrollX:        true,
        scrollCollapse: true,
        paging:         false,
        info: false,
        autoWidth:false,
        fixedColumns: true,
        searching: false,
        ordering: false,
        responsive: true,
    });
	
	///////////////////////////////hide TBODY///////////////////////////////////////////
	$('#receiveIll').find('tbody').hide();
	
	
	///////////////////////////////////////CREATE TABLE ROW FOR COPY RECEIVED/////////////////////////////////
	$("#txtCopies").keydown(function(e){ 

		setTimeout(function(){
			var qtyRec = $("#txtCopies").val();

			$("#generate, #save").attr('disabled', false);

			if(qtyRec == '0'){
				$("#generate, #save").attr('disabled', true);
			}else{
				$("#generate, #save").attr('disabled', false);
				generateTableCOPY();
			}

			$('#receiveIll').find('tbody').show();  // --> SHOW TBODY


		}, 1000); 
	});
	
	///////////////////////////// function generate table for COPY ////////////////////////////////////////////
	function generateTableCOPY(){
		
		var numberROW = $("#txtCopies").val();
		$(".accno").attr('maxlength',$(".acclength").text());
		$('#receiveIll').find('tbody').show();  // --> SHOW TBODY
		//
		
		var totalTr=($("#receiveIll tr").length)-1;
		
		if(numberROW > totalTr){
	           for(var i=0;i<numberROW-totalTr;i++){	        	   
	               var lastTR=$("#receiveIll tr:last").html();
	               
	               var prevID = $("#receiveIll input.accno").attr('id');
	               var acc = prevID.split("_");
	               $("#receiveIll").append("<tr>");
	               $("#receiveIll tr:last").html(lastTR);
	             
	               	///display row number
	               	$('#receiveIll tbody tr').each(function(idx){
	    	       		
	               		$(".SMD, .ItemCatgory, .Loca, .Con").prop("selectedIndex",-1);
	   			   		$(this).children().first().html(idx + 1 + "<input type='hidden' class='count' value='"+idx+"'>");
	   				});
	           }
	           
	       }else{
	            for(var i=0;i<totalTr-qtyRec;i++){
	                $("#receiveIll tr:last").remove();
	            }
	       }

	       
	       /// for check user input ACCESSION value
	       $('.accno').blur(function () {
	    	      	   
	    	   var id = $(this).closest("#receiveIll tbody tr").find('.accno').val();
	    	   var getNOCOPY  =$(this).closest("#receiveIll tbody tr").find('#NOCOPY').text();
	    	   var NOCOPYindex = parseInt(getNOCOPY)-1;
	    	   $.get('checkAccno',{
				   id : id
					}, function(responseJson) {
				 		
				 		if(responseJson != null){
				 			$.each(responseJson, function(key,value) {
				 				//alert("fs" +value['Name']);
								if(value['Name'] >= '1'){
									$('#receiveIll tbody tr:eq('+NOCOPYindex+') input.accno').val('');//.css("background-color", 'red');
									//alert("Accession already exits");
									//messageBox("124","",""); 
									swal("Accession already exits")
								}else if(value['Name'] == '0'){
									//alert("Accession can use");
								}
				 			});
				 		}
			   });
	       });
	}
	
	////////////////////////ONCHANGE FOR SMD FIRST ROW /////////////////////////////////////
	$('#SMD').on('change', function() {
		var getSMDVALUE = $(this).find(":selected").val();
		$("#SMD option[value='"+getSMDVALUE+"']").attr('selected', 'selected');
	});
	
	////////////////////////ONCHANGE FOR Item Catgory FIRST ROW /////////////////////////////
	$('#ItemCatgory').on('change', function() {
		var getItemCatgoryVALUE = $(this).find(":selected").val();
		$("#ItemCatgory option[value='"+getItemCatgoryVALUE+"']").attr('selected', 'selected');
	});
	
	////////////////////////ONCHANGE FOR Location FIRST ROW /////////////////////////////////
	$('#Loca').on('change', function() {
		var getLocaVALUE = $(this).find(":selected").val();
		$("#Loca option[value='"+getLocaVALUE+"']").attr('selected', 'selected');
	});
	
	////////////////////////ONCHANGE FOR Condition FIRST ROW ///////////////////////////////
	$('#Con').on('change', function() {
		var getConVALUE = $(this).find(":selected").val();
		$("#Con option[value='"+getConVALUE+"']").attr('selected', 'selected');
	});
	
	
	////////////////////////WHEN GENERATE CLICK //////////////////////////////////////////
	$("#generate").click(function(){
		//alert("generate");
		var rowCount = $('#receiveIll tr').length-1;

		$.get('generateAccNO', {
			rowCount : rowCount
		 	}, function(jsonResponse) {
		 		var table = $("#receiveIll");
		 		 $.each(jsonResponse, function(index, value) {
		 			 /*alert("value = " + value);
		 			 alert("index = " + index);*/
		 			 
		 			 if(rowCount == 1){
		 				//alert('1');
		 				$("#Accession").val(value);
		 			 }else{
		 				//alert('2');
		 				$('#receiveIll tbody tr').each(function(indexs) {
			 				if(index == $(this).find("td .count").val()){
			 					$(this).children().eq(1).html("<input type='text' class='form-control accno' id='Accession' value='"+value+"'>");
			 				}
						});
		 			 }
		 		 });     
		 	});
	});
	
	
	////////////////////////WHEN SAVE CLICK ///////////////////////////////////////////////
	$("#save").click(function(){
		
		// check for empty value
		if(!checkEmptyValue()) {
			// Do not proceed if empty values detected
			//alert('Empty values detected!');
			return false;
		}
		
		var refno = $('#cboReferenceNo').val();
		var ctrlno = $(".lblControlNo").text();
		
		var reqId = $(".lblRequestorId").text();
		var recDate = $("#txtReceivedDate").val();
		var dueDate = $("#txtDueDate").val();

		var reqdate = $(".lblRequestedDate").text();
		
		 var output = [];
		    $("#receiveIll tbody tr").each(function() {
		        var obj = {};
		        obj.Accession = $("#Accession", this).val();
		        obj.SMD = $("#SMD", this).val();
		        obj.ItemCatgory = $("#ItemCatgory", this).val();
		        obj.Loca = $("#Loca", this).val();
		        obj.Con = $("#Con", this).val();
		        
		       
		        if(obj.Accession == ''){
					messageBox("108","Accession","@field");
					return false;
		        }else if(obj.SMD == ''){
					messageBox("108","SMD","@field");
				}else if(obj.ItemCatgory == ''){
					messageBox("108","Item Catgory","@field");
				}else if(obj.Loca == ''){
					messageBox("108","Location","@field");
				}else if(obj.Con == ''){
					messageBox("108","Condition","@field");
				}else{
					output.push(obj);
				}
		    });
		    
		    var total = output.length; 
		   
		    ///Accession
			var AccessionArray = [];
		    for (var i = 0; i < output.length; i++) {
		 		var code = output[i]['Accession'];
		 		AccessionArray.push(code);
		 	}
		    
		    ///Loaca
			var LocaArray = [];
		    for (var i = 0; i < output.length; i++) {
		 		var code = output[i]['Loca'];
		 		LocaArray.push(code);
		 	}
		    
		    ///icat
			var ItemCatgoryArray = [];
		    for (var i = 0; i < output.length; i++) {
		 		var code = output[i]['ItemCatgory'];
		 		ItemCatgoryArray.push(code);
		 	}
		    
		    ///icate
			var ItemCatgoryArray = [];
		    for (var i = 0; i < output.length; i++) {
		 		var code = output[i]['ItemCatgory'];
		 		ItemCatgoryArray.push(code);
		 	}
		    
		    //con
		    var ConArray = [];
		    for (var i = 0; i < output.length; i++) {
		 		var code = output[i]['Con'];
		 		ConArray.push(code);
		 	}
		    
		    //smd
		    var SMDArray = [];
		    for (var i = 0; i < output.length; i++) {
		 		var code = output[i]['SMD'];
		 		SMDArray.push(code);
		 	}
		    
		    
		    getValueForSave(total, AccessionArray.toString(), LocaArray.toString(), ItemCatgoryArray.toString(), 
		    		ConArray.toString(),SMDArray.toString(), refno, ctrlno, reqId, recDate, dueDate, reqdate);
		    
 
		    return false;
	});
	
	//////////////////////////////Check empty value///////////////////////////////
	function checkEmptyValue() {
		var result = true;

	    
	    $('#receiveIll tr').each(function(row, tr){
	    	if ($(tr).find('td:eq(1) #Accession').val() === '') {
	    		$(tr).find('td:eq(1) #Accession').focus();
	    		messageBox("108","Accession","@field");
	    		result = false;
	    		return false;
	    	}else if ($(tr).find('td:eq(5) #SMD').val() === null) {
	    		$(tr).find('td:eq(5) #SMD').focus();
	    		messageBox("108","SMD","@field");
	    		result = false;
	    		return false;
	    	}else if ($(tr).find('td:eq(3) #ItemCatgory').val() === null) {
	    		$(tr).find('td:eq(3) #ItemCatgory').focus();
	    		messageBox("108","Item Catgory","@field");
	    		result = false;
	    		return false;
	    	}else if ($(tr).find('td:eq(2) #Loca').val() === null) {
	    		$(tr).find('td:eq(2) #Loca').focus();
	    		messageBox("108","Location","@field");
	    		result = false;
	    		return false;
	    	}else if ($(tr).find('td:eq(4) #Con').val() === null) {
	    		$(tr).find('td:eq(4) #Con').focus();
	    		messageBox("108","Condition","@field");
	    		result = false;
	    		return false;
	    	}		
	    });

	    return result;
	}
	
	
	//////////////////////////////function getValue for save /////////////////////
	function getValueForSave(total, AccessionArray, LocaArray, ItemCatgoryArray, 
			ConArray,SMDArray, refno, ctrlno, reqId, recDate, dueDate, reqdate){

		
		if(dueDate == ''){
			dueDate = '';
		}else{
			dueDate = moment(dueDate, 'DD/MM/YYYY').format("YYYYMMDD")
		}
		

		$.ajax({
			method: "POST",
			url: "saveReceived",
			data: {total: total, 
				accno : AccessionArray.toString(),
				loca : LocaArray.toString(),
				icate : ItemCatgoryArray.toString(),
				con : ConArray.toString(),
				smd : SMDArray.toString(), 
				refno: refno,
				ctrlno: ctrlno,
				reqId:reqId,
				recDate: moment(recDate, 'DD/MM/YYYY').format("YYYYMMDD"),
				dueDate: dueDate,
				liferayLogin : liferayLogin },
		}).done(function( msg ) {
			
			if(msg=="Done"){
				$.post("GeneratePreviewDocument", {
					action : "EL03",
					liferayLogin : liferayLogin,
					refno : refno,
					reqDate : reqdate,
					sControlNo : ctrlno, 
					sRequestor : reqId,
					action2 : "print",
					duedate : dueDate

				}, function(result){

					var w = window.open('about:blank');
					w.document.open();
					w.document.write(result);
					w.document.close();
					w.print();
				});

				//////CLEAR
				$("#cboReferenceNo option[value='"+refno+"']").remove();
				$("#cboReferenceNo").prop("selectedIndex",-1);
				$(".lblCallNo, .lblControlNo, .lblTitle, .lblRequestor, .lblRequestorId, .lblLendingLib, .lblRequestedDate, .lblExpectedDate").empty();
				$('#txtReceivedDate').val(moment(today).format("DD/MM/YYYY"));
				$('#txtDueDate, #txtCopies').val("");
				$("#save, #preview, #generate").attr('disabled', true);
				var firstElement = $('tbody > tr').first();
				table.rows(firstElement.nextAll('tr')).remove().draw();
				///table.clear();
				$(".accno").val('');
				$(".SMD, .ItemCatgory, .Loca, .Con").prop("selectedIndex",-1);
				$('#receiveIll').find('tbody').hide();
			}

		});
	}
	
});