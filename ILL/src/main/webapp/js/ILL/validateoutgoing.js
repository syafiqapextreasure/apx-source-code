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

	$('#formdataOutReq').bootstrapValidator({
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
			txtMaterialNo: {
				trigger: 'blur, focusout',
				validators: {
					notEmpty: {
						message: 'Control No is required'
					}
				}
			},
			cboDocumentType: {
				validators: {
					notEmpty: {
						message: 'Document Type is required'
					},
				}
			},
			txtLibraryID: {
				trigger: 'blur, focusout',
				validators: {
					notEmpty: {
						message: 'Library ID is required'
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

		//var sRequestNo = $(".lblRequestNo").text();

		var refno  = $(".lblReferenceNumber").text();
		var cntrlno = $("#txtMaterialNo").val();
		var accno = $("#txtAccession").val();
		var doctype = $("#cboDocumentType").val();
		var vol = $("#txtVolume").val();
		var iss = $("#txtIssue").val();
		var pageNo = $("#txtPageNumber").val();
		var dateReq = $("#txtRequestedDate").val();

		if(dateReq == '' || dateReq == ' '){
			dateReq = '';
		}else{
			dateReq = moment(dateReq, 'DD/MM/YYYY').format("YYYYMMDD")
		}


		var expDate = $("#txtExpectedDate").val();

		if(expDate == '' || expDate == ' '){
			expDate = '';
		}else{
			expDate = moment(expDate, 'DD/MM/YYYY').format("YYYYMMDD")
		}

		//alert(dueDate+"dueDate222");
		var dueDate = $("#txtDueDate").val();

		if(dueDate == '' || dueDate == ' ' ||dueDate == '-'){
			dueDate = '';
		}else{
			dueDate = moment(dueDate, 'DD/MM/YYYY').format("YYYYMMDD")
		}
		
		//alert(dueDate+"dueDate");

		var libId = $("#txtLibraryID").val();
		var contactperson = $("#txtContactPerson").val();
		var patr = $("#txtPatron").val();
		var daterec = moment($(".daterec").text(), 'DD/MM/YYYY').format("YYYYMMDD");
		var recby = $(".recby").text();
		
		var doctypevalue = $("#cboDocumentType option:selected").text();


		//////////////////////SET TABLE ///////////////////////////////////////////
		var t = $('#Outgoing_table').DataTable({
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			
			columnDefs    : [ 
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
			                 order: [[ 1, 'asc' ]],
			                 columnDefs: [
			          					{
			          						"targets": [ 7 ],
			          						"visible": false,
			          						"searchable": false
			          					},
			          				],
			                 drawCallback: function( settings ) {
			                	 $('#Outgoing_table tbody tr').each(function() {
			                		 var Cell = $(this).find('td:eq(2)');
			                		 //alert("dsd8888"+Cell.text()+"dd");
			                		 debugger;
			                		 if((Cell.text() == ' ')){
							        	  $(this).find('#Edit').prop("disabled", false);
							        	  $(this).find('#Delete').prop("disabled", false);
							          }else{
							        	  $(this).find('#Edit').prop("disabled", true);
							        	  $(this).find('#Delete').prop("disabled", true);
							          }
			                	 });
			                 }
		});
		
		var totalrow = t.row().count();
		
		//alert(totalrow+1 +"fdfsdfsddfs");

		///////////////////////////////////////////////////////////////////////////

		///////////////////// Set Current Date  ///////////////////////////////////
		var today = new Date(); 
		var liferayLogin = $("#liferayLogin").val();

		function printLending(cntrlno, sRequestor, liferayLogin, action, sContactPerson, action2,
				refno, reqDate, doctype, sVolume, sIssue, sPageNumber, duedate, button){
			swal({
				text: "Do you wish to print letter to the Lending Library?",
				showCancelButton: true,
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "Yes",
				cancelButtonText: "No"
			}).then(
					function(isConfirm) {
						if (isConfirm) {
							setTimeout(function(){
								$.post("GeneratePreviewDocument", {cntrlno: cntrlno,
									sRequestor : sRequestor,
									liferayLogin : liferayLogin,
									action : "EL02",
									sContactPerson : sContactPerson,
									action2 : "print",
									refno : refno,
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
								printnotification(cntrlno, sRequestor, liferayLogin, action, sContactPerson, action2,
										refno, reqDate, doctype, sVolume, sIssue, sPageNumber, duedate, button);
							},800); 
						}
					},
					function() {
						setTimeout(function(){
							printnotification(cntrlno, sRequestor, liferayLogin, action, sContactPerson, action2,
									refno, reqDate, doctype, sVolume, sIssue, sPageNumber, duedate, button);
						},800);
					}
			);
		}

		function printnotification(cntrlno, sRequestor, liferayLogin, action, sContactPerson, action2,
				refno, reqDate, doctype, sVolume, sIssue, sPageNumber, duedate, button){
			swal({
				text: "Do you wish to print notification letter to the requestor?",
				showCancelButton: true,
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "Yes",
				cancelButtonText: "No"
			}).then(
					function(isConfirm) {
						if (isConfirm) {
							$.post("GeneratePreviewDocument", {
								cntrlno: cntrlno,
								sRequestor : sRequestor,
								liferayLogin : liferayLogin,
								action : "EL06",
								sContactPerson : sContactPerson,
								refno : refno,
								reqDate : reqDate,
								doctype : doctype,
								sVolume : sVolume,
								sIssue : sIssue,
								sPageNumber : sPageNumber,
								duedate : duedate
							}, function(result){

								var w = window.open('about:blank');
								w.document.open();
								w.document.write(result);
								w.document.close();
								//w.print();
							});

							//alert("refnorefnorefnorefno" +refno);
							if(button == "add"){
								$.get("ResultSearchOutgoing",
										{input_criteria : refno, search_type : "refno"}
								,function(json){
									var return_data = new Array();
									//messageBox("005","Add","@action");
									swal("ILL Refernce Number is " +refno);
									for(var i=0;i< json.length; i++){
										t.row.add( [
										            totalrow+1,
										            json[i].referenceNumber,
										            json[i].materialNo, 
										            json[i].title,
										            json[i].libName,
										            json[i].requestorName,
										            json[i].requestedDate,
										            json[i].candelete,
										            '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalOutReq" data-whatever="View Outgoing Request" title="View Outgoing Request" data-rowid="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalOutReq" data-whatever="Edit Outgoing Request" title="Edit Outgoing Request" data-rowid="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-trash" title="Delete Outgoing Request" ></span></button>',
										            ] ).draw( false );
									}
								});
							}else if(button == "edit"){
								messageBox("005","Edit Outgoing Request","@action");
								$.get("ResultSearchOutgoing",
										{input_criteria : refno, search_type : "refno"}
								,function(json){
									var return_data = new Array();
									//messageBox("005","Add","@action");
									swal("ILL Refernce Number is " +refno);
									for(var i=0;i< json.length; i++){
										var no = parseInt(i);
     			    	            	no = (no+1); 
     			    	            	
										t.row.add( [
										            no.toString(),
										            json[i].referenceNumber,
										            json[i].materialNo, 
										            json[i].title,
										            json[i].libName,
										            json[i].requestorName,
										            json[i].requestedDate,
										            json[i].candelete,
										            '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalOutReq" data-whatever="View Outgoing Request" title="View Outgoing Request" data-rowid="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalOutReq" data-whatever="Edit Outgoing Request" title="Edit Outgoing Request" data-rowid="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-trash" title="Delete Outgoing Request" ></span></button>',
										            ] ).draw( false );
									}
								});
							}
							
						}


					},
					function() {
						if(button == "add"){
							$.get("ResultSearchOutgoing",
									{input_criteria : refno, search_type : "refno"}
							,function(json){
								var return_data = new Array();
								//messageBox("005","Add","@action");
								swal("ILL Refernce Number is " +refno);
								for(var i=0;i< json.length; i++){
									t.row.add( [
									            totalrow+1,
									            json[i].referenceNumber,
									            json[i].materialNo, 
									            json[i].title,
									            json[i].libName,
									            json[i].requestorName,
									            json[i].requestedDate,
									            json[i].candelete,
									            '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalOutReq" data-whatever="View Outgoing Request" title="View Outgoing Request" data-rowid="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalOutReq" data-whatever="Edit Outgoing Request" title="Edit Outgoing Request" data-rowid="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-trash" title="Delete Outgoing Request" ></span></button>',
									            ] ).draw( false );
								}
							});
						}else if(button == "edit"){
							messageBox("005","Edit Outgoing Request","@action");
							$.get("ResultSearchOutgoing",
									{input_criteria : refno, search_type : "refno"}
							,function(json){
								var return_data = new Array();
								//messageBox("005","Add","@action");
								swal("ILL Refernce Number is " +refno);
								for(var i=0;i< json.length; i++){
									var no = parseInt(i);
 			    	            	no = (no+1); 
 			    	            	
									t.row.add( [
									            no.toString(),
									            json[i].referenceNumber,
									            json[i].materialNo, 
									            json[i].title,
									            json[i].libName,
									            json[i].requestorName,
									            json[i].requestedDate,
									            json[i].candelete,
									            '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalOutReq" data-whatever="View Outgoing Request" title="View Outgoing Request" data-rowid="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalOutReq" data-whatever="Edit Outgoing Request" title="Edit Outgoing Request" data-rowid="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].referenceNumber+'"><span class="glyphicon glyphicon-trash" title="Delete Outgoing Request" ></span></button>',
									            ] ).draw( false );
								}
							});
						}
					}
			);
		}

		///////////////////////////////////////////////////////////////////////////
		switch(title){
		case state = 'Add Outgoing Request':
			//printLending();
			$('#formdataOutReq').data('bootstrapValidator').resetForm();
			$('#txtRequestedDate').val(moment(today).format("DD/MM/YYYY"));
			$("#txtMaterialNo, #controlNo, #txtVolume, #txtIssue, #txtPageNumber").val("");
			$("#txtAccession").prop("disabled", false);
	
			$("#txtAccession").append("<option value=''></option>"); 
			$("#cboDocumentType").prop("selectedIndex",-1);
			$('#txtExpectedDate, #txtLibraryID, #txtPatron, #txtContactPerson, #txtDueDate').val("");
			$('.lblTitle, .lblLibName, .lblLibAdd1, .lblLibAdd2, .lblLibAdd3, .lblLibTelNo').empty(); 
			$('.lblRequestorName, .lblReqAdd1, .lblReqAdd2, .lblReqAdd3').empty();
			
			$.post("AddOutgoing",{

				cntrlno : cntrlno,
				accno : accno,
				doctype : doctype,
				vol : vol,
				iss :iss,
				pageNo : pageNo,
				dateReq : dateReq, 
				expDate : expDate,
				dueDate : dueDate,
				libId : libId,
				contactperson : contactperson, 
				patr : patr,
				daterec : daterec,
				recby : recby
 				},
 				function(message){
 					var status = message.replace(/\s+/g, '');

 					$(".lblReferenceNumber").text(message);

 					if (status != "") {

 						printLending(cntrlno, libId, recby, "EL02", contactperson, "print", message.trim(), dateReq,
 								doctype, vol, iss, pageNo, dueDate, "add");
 					}else{
 						swal("Unsuccessfully");
 					}
 			});
		
		break;
		case state = 'Edit Outgoing Request':
			
			$.post("EditOutgoing",{
				refno : refno,
				cntrlno : cntrlno,
				accno : accno,
				doctype : doctype,
				vol : vol,
				iss :iss,
				pageNo : pageNo,
				dateReq : dateReq, 
				expDate : expDate,
				dueDate : dueDate,
				libId : libId,
				contactperson : contactperson, 
				patr : patr,
				loginid : $("#liferayLogin").val()
 				},
 				function(message){
 					var status = message.replace(/\s+/g, '');
 					//alert(status +"status");
 					if (status == "Success") {

 						printLending(cntrlno, libId, recby, "EL02", contactperson, "print", refno, dateReq,
 								doctype, vol, iss, pageNo, dueDate, "edit");
 					}else{
 						swal("Unsuccessfully");
 					}
 			});
			
			$("#closeModalAdd").click();
			break;
		}
	});

});