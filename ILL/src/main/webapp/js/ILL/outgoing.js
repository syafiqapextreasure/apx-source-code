$(document).ready(function() {
	
	
	
	///////////////////// Set Current Date  ///////////////////////////////////
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();

	
	///////////////////// Set ReqCtrl_table  ////////////////////////////////
	$('#Outgoing_table').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	//////////////////////////////Max Length /////////////////////////////////
	$("#txtMaterialNo, #txtVolume, #txtIssue, #txtPageNumber").attr('maxlength','10');
	$("#searchPatron, #txtPatron").attr('maxlength','12');
	$("#txtContactPerson").attr('maxlength','50');
	
	///////////////////// Set text autocomplete off////////////////////////////
	$('input[type="text"]').attr('autocomplete', 'off');
	
	//////////////////////////////Clear All Modal /////////////////////////////
	$('#searchOutgoing').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $("input").prop("disabled", false);
	});
	
	$('#modalOutReq').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $('#formdataOutReq').data('bootstrapValidator').resetForm();
	    $('.lblReferenceNumber, .lblTitle, .lblLibName, .lblLibAdd1, .lblLibAdd2, .lblLibAdd3, .lblLibTelNo').empty(); 
		$('.lblRequestorName, .lblReqAdd1, .lblReqAdd2, .lblReqAdd3').empty();
		$('.daterec, .recby').empty();
	});
	
	
	//////////////////////////////when search modal open///////////////////////
	$('#searchReqCtrl').on('shown.bs.modal', function () {
		
		
		$("input[type=radio]").attr('disabled', false);
		$("#input-criteria").focus();
		
		$("input[name=input-criteria]").attr('maxlength','12');
	});
	
	////////////////////////////
	
	$('.searchoutgoingill').click(function(){
		$("#input-criteria").prop("disabled", false);
		$("#search-type").attr('disabled', false);
		 $(".searcoutcancel").removeAttr('disabled');

	});
	////////////////////////////
	
	//***************************************AREA FOR SEARCH ********************************************//
	$('#search').click(function(){
		
		$('#Outgoing_table').dataTable().fnClearTable();
		
		var input_criteria = $('#input-criteria').val();
		var search_type = $("input[name='searchSelection']:checked").val(); 
		
		$('#Outgoing_table').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//info: false,
		    ajax: {
		    	url: "ResultSearchOutgoing",
		        data : {input_criteria : input_criteria, search_type : search_type},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){
		              return_data.push({
		            	'No': (i+1),
		                'ILL Ref No': json[i].referenceNumber,
		                'Control No' : json[i].materialNo, 
		                'Title' : json[i].title,
		                'Lending Library' : json[i].libName,
		                'Requestor Name' : json[i].requestorName,
		                'Request Date' : json[i].requestedDate,
		                'msg' : json[i].candelete,
		                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalOutReq" data-whatever="View Outgoing Request" title="View Outgoing Request" data-rowid="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalOutReq" data-whatever="Edit Outgoing Request" title="Edit Outgoing Request" data-rowid="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-trash" title="Delete Outgoing Request" ></span></button>',
		              })
		            }
		            return return_data;
		            
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'ILL Ref No'},
					{'data': 'Control No'},
					{'data': 'Title'},
					{'data': 'Lending Library'},
					{'data': 'Requestor Name'},
					{'data': 'Request Date'},
					{'data': 'msg'},
					{'data': 'Action'},
				],
				columnDefs: [
					{
						"targets": [ 7 ],
						"visible": false,
						"searchable": false
					},
				],
				drawCallback: function( settings ) {
					var tdItems=[];
					
		
					
					$('#Outgoing_table tbody tr').each(function() {
				          var Cell = $(this).find('td:eq(2)');
				          //////var Cell2 = $(this).find('td:eq(7)');
				          var Cell2 = $(this).find('td:eq(7)');
  
				          //alert("dsd"+Cell2.text()+"dd");
				          //alert($("#msg").val() );
				          //var currentRow = $(this).closest("tr");

						    //var data = $(this).row($(this)).data();
/*				          var data = $(this).find('td').data()["msg"]; 
						  alert(data);*/
						 
						// alert( 'Table\'s column visibility are set to: '+$('#Outgoing_table').dataTable().columns().visible().join(', ') );

						 
						
				         debugger;
				        /* if((Cell.text() == ' ')){
				        	  $(this).find('#Edit').prop("disabled", false);
				        	  $(this).find('#Delete').prop("disabled", false);
				          }else{
				        	  $(this).find('#Edit').prop("disabled", true);
				        	  $(this).find('#Delete').prop("disabled", true);
				          }*/
				          
				          if((Cell.text() == ' ')){
				        	  if((Cell2.text() == 'Record cannot be deleted.')){
				        		  $(this).find('#Edit').prop("disabled", false);
					        	  $(this).find('#Delete').prop("disabled", true);
				        	  }else{
				        		  $(this).find('#Edit').prop("disabled", false);
					        	  $(this).find('#Delete').prop("disabled", false);
				        	  }
				        	  
				          }else{
				        	  $(this).find('#Edit').prop("disabled", true);
				        	  $(this).find('#Delete').prop("disabled", true);
				          }
				          
				     });
			    }
		});

		$("#closesearchOutgoing").click();  

	});
	//***********************************END AREA FOR SEARCH ********************************************//
	
	
	//****************************************** AREA FOR MODAL *****************************************//
	$('#modalOutReq').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');

		modal.find('.modal-title').text(recipient);
		
		$("#cboDocumentType").prop("selectedIndex",-1);
		
		switch(recipient){
	  	case state = 'Add Outgoing Request':
	  			enable();
	  			$(".lblReferenceNumber").empty();
	  			$("#txtVolume, #txtIssue, #txtPageNumber").prop("disabled", true); 
	  			$('.daterec').text(moment(today).format("DD/MM/YYYY"));
		  		$(".recby").text(liferayLogin);
		  		$("#previewbtn").attr('disabled', true);
		  		$("#searchMaterial, #searchLibID, #searchReqID").attr('disabled', false);
		  		$('#txtRequestedDate').val(moment(today).format("DD/MM/YYYY"));
		  		$('#txtRequestedDate, #txtExpectedDate, #txtDueDate').datepicker({
		  			format: "dd/mm/yyyy",
		  			todayBtn: true,
		  			autoclose: true,
		  			todayHighlight: true,
		  		});
		  		
		  		
	  		break;
	  	case state = 'Edit Outgoing Request':
	  		enable();
	  		$("#previewbtn").attr('disabled', true);
	  	
		  	$.get('GetEditViewOutgoing', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						var doctype = value['doctype'];
						
						$(".lblReferenceNumber").text(value['referenceNumber']);
						$("#txtMaterialNo").val(value['materialNo']);
						$(".lblTitle").text(value['title']);
						$("#txtAccession").val(value['accno']);
						$("#cboDocumentType").val(doctype);
						$("#txtVolume").val(value['vol']);
						$("#txtIssue").val(value['iss']);
						$("#txtPageNumber").val(value['pageno']);
						$("#txtRequestedDate").val(value['requestedDate']);
						$("#txtExpectedDate").val(value['sExpDate']);
						$("#txtLibraryID").val(value['libId']);
						$("#txtDueDate").val(value['dtdue']);
						$("#txtPatron").val(value['requestorId']);
						$(".daterec").text(value['crdate']);
						$(".recby").text(value['creby']);
						
						if(doctype=='01' || doctype == '02'){
							$("#txtVolume, #txtIssue, #txtPageNumber").prop("disabled", true); 
						}else if(doctype=='03' || doctype == '04'){
							$("#txtVolume, #txtIssue, #txtPageNumber").prop("disabled", false); 
						}
						

						$.get('GetCheckLibExist', {
							code : value['requestorId'],
						}, function(responseJson) {
							$.each(responseJson, function(key,value) {

								$(".lblRequestorName").text(value['lblLibName']);
								$(".lblReqAdd1").text(value['lblLibAdd1']);
								$(".lblReqAdd2").text(value['lblLibAdd12']);
								$(".lblReqAdd3").text(value['lblLibAdd13']);
							});
						});
						
						$.get('GetCheckLibExist', {
				        	code : value['libId'],
						 	}, function(responseJson) {
						 			$.each(responseJson, function(key,value) {
						 				
						 				$(".lblLibName").text(value['lblLibName']);
										$(".lblLibAdd1").text(value['lblLibAdd1']);
										$(".lblLibAdd2").text(value['lblLibAdd12']);
										$(".lblLibAdd3").text(value['lblLibAdd13']);
										$(".lblLibTelNo").text(value['lblLibTelNo']);
										
						 			});
						});
						
						
						$('#txtRequestedDate, #txtExpectedDate, #txtDueDate').datepicker({
				  			format: "dd/mm/yyyy",
				  			todayBtn: true,
				  			autoclose: true,
				  			//todayHighlight: true,
				  		});
					});
				}
			});
	  		break;
	  	case state = 'View Outgoing Request':
	  			disable();
	  	
			  	$.get('GetEditViewOutgoing', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							$(".lblReferenceNumber").text(value['referenceNumber']);
							$("#txtMaterialNo").val(value['materialNo']);
							$(".lblTitle").text(value['title']);
							$("#txtAccession").val(value['accno']);
							$("#cboDocumentType").val(value['doctype']);
							$("#txtVolume").val(value['vol']);
							$("#txtIssue").val(value['iss']);
							$("#txtPageNumber").val(value['pageno']);
							$("#txtRequestedDate").val(value['requestedDate']);
							$("#txtExpectedDate").val(value['sExpDate']);
							$("#txtLibraryID").val(value['libId']);
							$("#txtDueDate").val(value['dtdue']);
							$("#txtPatron").val(value['requestorId']);
							$(".daterec").text(value['crdate']);
							$(".recby").text(value['creby']);

							$.get('GetCheckLibExist', {
								code : value['requestorId'],
							}, function(responseJson) {
								$.each(responseJson, function(key,value) {

									$(".lblRequestorName").text(value['lblLibName']);
									$(".lblReqAdd1").text(value['lblLibAdd1']);
									$(".lblReqAdd2").text(value['lblLibAdd12']);
									$(".lblReqAdd3").text(value['lblLibAdd13']);
								});
							});
							
							$.get('GetCheckLibExist', {
					        	code : value['libId'],
							 	}, function(responseJson) {
							 			$.each(responseJson, function(key,value) {
							 				
							 				$(".lblLibName").text(value['lblLibName']);
											$(".lblLibAdd1").text(value['lblLibAdd1']);
											$(".lblLibAdd2").text(value['lblLibAdd12']);
											$(".lblLibAdd3").text(value['lblLibAdd13']);
											$(".lblLibTelNo").text(value['lblLibTelNo']);
											
							 			});
							});
						});
					}
				});
	  		break;
		}
	});
	//***************************************END AREA FOR MODAL *****************************************//
	
	function disable(){
		$("input").attr('disabled','disabled');
  		$("#save, #cancel, #previewbtn").hide();
  		$("#close").show();
  		$('#cboDocumentType, #txtAccession').prop('disabled', true);
  		$("#searchMaterial, #searchLibID, #searchReqID").attr('disabled', true);
	}
	
	//////////////////////////////Enable///////////////////////////////////////
	function enable(){
		$("input").prop("disabled", false);
  		$("#save, #cancel").show();
  		$("#close").hide();
  		$('#cboDocumentType, #txtAccession').prop('disabled', false);
  		$("#searchMaterial, #searchLibID, #searchReqID").attr('disabled', false);
	}

	//****************************************** AREA FOR keyup, keydown blur****************************//
	//////////////////////////////CONTROL NUMBER blur////////////////////////////////
	$("#txtMaterialNo").blur(function(e){
		var material = $("#txtMaterialNo").val();

		var reqid = $("#txtPatron").val();
		
		var doctype = $("#cboDocumentType").val();
		var libID = $("#txtLibraryID").val();

		if((reqid.length>0) && (material.length>0) && (doctype.length>0) && (libID.length>0)){
			$("#previewbtn").attr('disabled', false);
		}else{
			$("#previewbtn").attr('disabled', true);
		}
		$.get('CheckMatExist', {
        	code : material
		 	}, function(responseJson) {
		 		
		 		if(responseJson == 0){
		 			messageBox("126","","");
		 			$("#txtMaterialNo").val("");
		 			$(".lblTitle").empty();
		 		}else{
		 			
		 			$.get('GetTitle', {
	 					code : material
	 		        }, function(responseJson) {
	 		    		$(".lblTitle").text(responseJson);		
	 		        });
		 			
		 			//$("#txtAccession").append("<option value=''></option>");
		 			
		 			setTimeout(function(){
		 				$("#txtAccession").prop("disabled", false);
		 				/*$('#txtAccession').empty(); 
		 				$("#txtAccession").append("<option value=''></option>");*/
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
	$("#txtMaterialNo").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".lblTitle").empty();
			$('#txtAccession').empty(); 
			$("#txtAccession").append("<option value=''></option>");  
		}
	});
	
	/////////////////////LIB ID
	$("#txtLibraryID").blur(function(e){
		
		var libID = $("#txtLibraryID").val();
		
		var reqid = $("#txtPatron").val();
		var material = $("#txtMaterialNo").val();
		var doctype = $("#cboDocumentType").val();

		if((reqid.length>0) && (material.length>0) && (doctype.length>0) && (libID.length>0)){
			$("#previewbtn").attr('disabled', false);
		}else{
			$("#previewbtn").attr('disabled', true);
		}
		
		setTimeout(function(){
			$.get('GetCheckLibExist', {
	        	code : libID,
			 	}, function(responseJson) {
			 		
			 		if(responseJson==''){
			 			messageBox("162","",""); 
			 			$("#txtLibraryID").val("");
			 			$(".lblLibName, .lblLibAdd1, .lblLibAdd2, .lblLibAdd3, .lblLibTelNo").empty();
			 		}else{
			 			$.each(responseJson, function(key,value) {
			 				
			 				var cateGroup = value['sLLBorrowerCategory'];
							var cate = value['sPatronCat'];
							var expdate = value['sExpDate'];
							
							if(cateGroup != cate){
								messageBox("151","",""); 
								$("#txtLibraryID").val("");
								$(".lblLibName, .lblLibAdd1, .lblLibAdd2, .lblLibAdd3, .lblLibTelNo").empty();
								
								
							}else{
								if(moment(moment(today, 'DD-MM-YYYY')).format('YYYY-MM-DD') > moment(moment(expdate, 'DD-MM-YYYY')).format('YYYY-MM-DD')){
									messageBox("163","","");
									$("#txtPatron").val("");
									$(".lblLibName, .lblLibAdd1, .lblLibAdd2, .lblLibAdd3, .lblLibTelNo").empty();
								}else{
											$(".lblLibName").text(value['lblLibName']);
											$(".lblLibAdd1").text(value['lblLibAdd1']);
											$(".lblLibAdd2").text(value['lblLibAdd12']);
											$(".lblLibAdd3").text(value['lblLibAdd13']);
											$(".lblLibTelNo").text(value['lblLibTelNo']);
								}
							}
								
							
							
			 			});
			 		}
			});
			},800);
		
	});
	
	
	/////////////////////LIB ID
	$("#txtPatron").blur(function(e){
		
		var reqid = $("#txtPatron").val();
		
		var material = $("#txtMaterialNo").val();
		var doctype = $("#cboDocumentType").val();
		var libID = $("#txtLibraryID").val();

		if((reqid.length>0) && (material.length>0) && (doctype.length>0) && (libID.length>0)){
			$("#previewbtn").attr('disabled', false);
		}else{
			$("#previewbtn").attr('disabled', true);
		}
		
		setTimeout(function(){
			$.get('CheckExceed', {
	        	code : reqid,
			 	}, function(responseJson) {
			 		
			 		
			 		if(responseJson==true){
			 			messageBox("164","","");
			 			$("#txtPatron").val("");
			 			$(".lblRequestorName, .lblReqAdd1, .lblReqAdd2, .lblReqAdd3").empty();
			 		}else{
			 			setTimeout(function(){
			 				$.get('GetCheckLibExist', {
			 		        	code : reqid,
			 				 	}, function(responseJson) {
			 				 		
			 				 		if(responseJson==''){
			 				 			messageBox("162","",""); 
			 				 			$("#txtPatron").val("");
			 				 			$(".lblRequestorName, .lblReqAdd1, .lblReqAdd2, .lblReqAdd3").empty();
			 				 		}else{
			 				 			$.each(responseJson, function(key,value) {
			 				 				
			 								var expdate = value['sExpDate'];
			 									
			 								if(moment(moment(today, 'DD-MM-YYYY')).format('YYYY-MM-DD') > moment(moment(expdate, 'DD-MM-YYYY')).format('YYYY-MM-DD')){
			 										messageBox("163","","");
			 										$("#txtPatron").val("");
			 										$(".lblRequestorName, .lblReqAdd1, .lblReqAdd2, .lblReqAdd3").empty();
			 								}else{
			 											$(".lblRequestorName").text(value['lblLibName']);
			 											$(".lblReqAdd1").text(value['lblLibAdd1']);
			 											$(".lblReqAdd2").text(value['lblLibAdd12']);
			 											$(".lblReqAdd3").text(value['lblLibAdd13']);
			 								}
			 								
			 				 			});
			 				 		}
			 				});
			 				},800);
			 		}
			 		
			});
			},800);
		
	});
	
	
	$( "#cboDocumentType" ).change(function() {
		
		var value = $(this).val(); 
		
		if(value=='01' || value == '02'){
			$("#txtVolume, #txtIssue, #txtPageNumber").prop("disabled", true); 
		}else if(value=='03' || value == '04'){
			$("#txtVolume, #txtIssue, #txtPageNumber").prop("disabled", false); 
		}
		
		var reqid = $("#txtPatron").val();
		
		var material = $("#txtMaterialNo").val();
		var doctype = $("#cboDocumentType").val();
		var libID = $("#txtLibraryID").val();

		if((reqid.length>0) && (material.length>0) && (doctype.length>0) && (libID.length>0)){
			$("#previewbtn").attr('disabled', false);
		}else{
			$("#previewbtn").attr('disabled', true);
		}
		
		
	});
	
	//////////////////////////////PREVIEW BUTTON CLICK////////////////////////////////
	$("#previewbtn").click(function(e){
		
		 var sControlNo = $("#txtMaterialNo").val();
		 var sRequestor = $("#txtLibraryID").val();
		 var sContactPerson = $("#txtContact").val();
		 var doctype = $("#cboDocumentType option:selected").text();
		 
		 var sVolume = $("#txtVolume").val();
		 var sIssue = $("#txtIssue").val();
		 var sPageNumber = $("#txtPageNumber").val();
		 
		 var reqDate = $("#txtRequestedDate").val();
			
		$.post("GeneratePreviewDocument", {sControlNo: sControlNo,
				sRequestor : sRequestor,
			 	liferayLogin : liferayLogin,
			 	action : "EL02",
			 	sContactPerson : sContactPerson,
			 	action2 : "preview",
			 	refno : "",
			 	reqDate : reqDate,
			 	doctype : doctype,
			 	sVolume : sVolume,
			 	sIssue : sIssue,
			 	sPageNumber : sPageNumber
			 	
		}, function(result){

				 var w = window.open('about:blank');
				 	w.document.open();
				 	w.document.write(result);
				 	w.document.close();
				 	//w.print();
		});
	});
	
	//***************************************AREA FOR DELETE ********************************************//
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
	
	$('#Outgoing_table').on( 'click', '#Delete', function () {

		
		//e.preventDefault();  

	    var currentRow = $(this).closest("tr");

	    var data = $('#Outgoing_table').DataTable().row(currentRow).data();

	    /*alert(data["msg"])
	    alert($(this).attr('data-id'));*/
	    
	    if(data["msg"] == "Record cannot be deleted."){
	    	swal("Record cannot be deleted.")
	    }else{
	    	var deleteId= $(this).attr('data-id');
	    	var index = $('#Outgoing_table').DataTable().rows({ search: 'applied'})
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
						url : "Handler_DeleteOutgoing",
						data: { refno: deleteId,
								loginid : liferayLogin},
							success : function(result) {
								var status = result.replace(/\s+/g, '');
								if (status == "ok") {
									messageBox("005","Deleted","@action");
									$('.swal2-confirm').click(
										function() {
											$('#Outgoing_table').DataTable().row(index).remove().draw();
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
	    }

		/*var deleteId= $(this).attr('data-id');
		//alert(deleteFundAccountChar);

		var index = $('#Outgoing_table').DataTable().rows({ search: 'applied'})
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
					url : "Handler_DeleteOutgoing",
					data: { refno: deleteId,
							loginid : liferayLogin},
						success : function(result) {
							var status = result.replace(/\s+/g, '');
							if (status == "ok") {
								messageBox("005","Deleted","@action");
								$('.swal2-confirm').click(
									function() {
										$('#Outgoing_table').DataTable().row(index).remove().draw();
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
		})*/
	});
	//***********************************END AREA FOR DELETE ********************************************//

});