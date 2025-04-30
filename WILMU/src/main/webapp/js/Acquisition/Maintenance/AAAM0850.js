/**
 * Javascript for Main_Page
 * 
 */

function messageBox(code, criteria, label){
	//alert("ssssw");
		$.get("Global?type=Handler&name=Error_Page",{GL79ERRCODE : code,
			criteria : criteria,
		 	label : label},function(result){
				swal(result);
			
		});
} 
$(document).ready(function() {
	
	//////datatable
	$('#reqCtrl').DataTable({
			responsive: true,
			scrollx: true,
			info: false,
			paging: true,
			ordering: false,
			searching:false,
			info: true,
			lengthChange: false,
			destory: true
	});
	
	///maxlength
	$("#input-ctrlno").attr('maxlength','10');
	$("#requestorId").attr('maxlength','12');
	$("#hidePseudo").hide();
	/////////set Date Requested
	var today = new Date();  
	//$('.reqdate').val(moment(today).format("DD/MM/YYYY"));

	//****************************************** AREA FOR MODAL *****************************************//
	 
	//////modal add, edit, view
	$('#modalreqCtrl').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('mode'); // Extract info from data-* attributes
		var rowid = button.data('rowid');
		$(".reqno, .reqdate").empty();
		//$("#div-reqname, #div-reqdept, #reqStatus, #div-vendorName").empty();
		$("#acqMode, #currency").prop("selectedIndex",0);

		switch(recipient){
			case state = 1:
				//$("#labelStatus").html("Requeset Status");
				addDisplay();
				modal.find('.modal-title').text("Add Request Control");
				$("#checkboxpseudo").attr('disabled',true);
				$("#input-excRate").attr('disabled',true);
				$('.reqdate').val(moment(today).format("DD/MM/YYYY"));
				$(".reqStatus").val("New Request");
				
				break;
			case state = 2:
				//$("#labelStatus").html("Requeset Status");
				modal.find('.modal-title').text("Edit Request Control");
			//$(".reqStatus").val("New Request");
				addDisplay();
					$.get('getDisplayReqCtrl?action=view', {
			  			rowid : rowid
					 	}, function(responseJson) {
						if(responseJson != null){
							$.each(responseJson, function(key,value) {
								$(".reqno").val(value['reqno']);
								$(".reqdate").val(value['datereq']);
								$("#requestorId").val(value['reqID']);
								$("#div-reqname").text(value['regName']);
								$("#div-reqdept").val(value['deptdesc']);
								$("#acqMode").val(value['mode']);
								$("#input-ISBN").val(value['isbn']);
								$("#input-title").val(value['title']);
								$("#input-author").val(value['author']);
								$("#input-pub").val(value['pub']);
								$("#input-edition").val(value['edition']);
								$("#input-set").val(value['sets']);
								$("#input-lcNo").val(value['lcno']);
								$("#input-copies").val(value['copies']);
								$("#input-copies").val(value['copies']);
								$("#remarks").val(value['uremarks']);
								$("#input-ctrlno").val(value['ctrlno']);
								$(".reqStatus").val(value['statdesc']);
								$("#currency").val(value['forex']);
								$("#input-excRate").val(value['rate']);
								$("#input-estima").val(value['fprice']);
								$("#input-lprice").val(value['price']);
								$("#input-vendorCode").val(value['vend']);
								$("#div-vendorName").text(value['venddesc']);
								$("#remarksofficeuse").val(value['lremarks']);
								
								var pseudo = value['pseudo'];
								if(pseudo == "Y"){
									$("#checkboxpseudo").prop('checked', true);
								}else{
									$("#checkboxpseudo").prop('checked', false);
								}
							});
						}
					});
				break;
			case state = 3:
				//$("#labelStatus").html("Requeset Status");
				modal.find('.modal-title').text("View Request Control");
				viewDisplay();
				$.get('getDisplayReqCtrl?action=view', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							$(".reqno").val(value['reqno']);
							$(".reqdate").val(value['datereq']);
							$("#requestorId").val(value['reqID']);
							$("#div-reqname").text(value['regName']);
							$("#div-reqdept").val(value['deptdesc']);
							$("#acqMode").val(value['mode']);
							$("#input-ISBN").val(value['isbn']);
							$("#input-title").val(value['title']);
							$("#input-author").val(value['author']);
							$("#input-pub").val(value['pub']);
							$("#input-edition").val(value['edition']);
							$("#input-set").val(value['sets']);
							$("#input-lcNo").val(value['lcno']);
							$("#input-copies").val(value['copies']);
							$("#input-copies").val(value['copies']);
							$("#remarks").val(value['uremarks']);
							$("#input-ctrlno").val(value['ctrlno']);
							$(".reqStatus").val(value['statdesc']);
							$("#currency").val(value['forex']);
							$("#input-excRate").val(value['rate']);
							$("#input-estima").val(value['fprice']);
							$("#input-lprice").val(value['price']);
							$("#input-vendorCode").val(value['vend']);
							$("#div-vendorName").text(value['venddesc']);
							$("#remarksofficeuse").val(value['lremarks']);
							
							var pseudo = value['pseudo'];
							if(pseudo == "Y"){
								$("#checkboxpseudo").prop('checked', true);
							}else{
								$("#checkboxpseudo").prop('checked', false);
							}
						});
					}
				});
				break;
		}
	});
	
	//clear all input fields in bootstrap modal when close modal #modalAdd
	$('#modalreqCtrl').on('hidden.bs.modal', function () {
		$(this).find('form').trigger('reset');
		$('#formdatareqCtrl').data('bootstrapValidator').resetForm();
		//$('#formdatareqCtrl a:first').tab('show');
			//$(this).removeData('bs.modal');
			//$('#formdata').bootstrapValidator('resetForm', true);
	});
	
	//**************************************END AREA FOR MODAL *****************************************//
	
	//////////////////////////FUNCTION //
	
	function viewDisplay(){
		$(".reqStatus").val("New Request");
		$("#formdatareqCtrl input").attr('disabled','disabled');
		$("#formdatareqCtrl textarea").attr('disabled','disabled');
		$("#formdatareqCtrl input[type=checkbox]").attr('disabled', true);
		$('#acqMode, #currency').prop('disabled', 'disabled');
		$("#searchpatr, #searchvendor").attr('disabled', 'disabled');
		$('#remarks, #remarksofficeuse').prop('disabled', true);
		$("#save, #cancel").hide();
  		$("#close").show();   
	}
	
	function addDisplay(){
		//$("#formdatareqCtrl input").attr('disabled',false);
		$("#formdatareqCtrl input").attr('disabled',false);
		$("#formdatareqCtrl textarea").attr('disabled',false);
		$(".reqno, .reqdate, .reqStatus,#div-reqdept,#input-excRate").attr('disabled','disabled');
		$("#formdatareqCtrl input[type=checkbox]").attr('disabled', false);
		$('#acqMode, #currency').prop('disabled', false);
		$("#searchpatr, #searchvendor").attr('disabled', false);
		$('#remarks, #remarksofficeuse').prop('disabled', false);
		$("#save, #cancel").show();
  		$("#close").hide();
  		$("#input-vendorCode").css("border", "");
  		$("#requestorId").css("border", "");
  		$("#div-reqname, #div-vendorName").text("");
	}
	
	/////key in number only
	$("#input-set, #input-copies").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });
	
	////contol input value for input-estima and input-lprice
	$("#input-estima, #input-lprice").on("keypress keyup blur",function (event) {
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
	
	////////for currency
	$("#currency").change(function(){

        var currency = $("#currency option:selected").val();     

        $.get('Global?type=Handler&name=GetPrate', {
        	currency : currency
        	}, function(data) {
        		$("#input-excRate").val(data.trim());  
        		var rate = $("#input-excRate").val();
        		var foreign = $("#input-estima").val();
        		var totalLocalAmount = (rate * foreign).toFixed(2);
        		if(rate==""){
        			//$("#local-amount").val(foreign);
        			$("#input-lprice").val("0");
        		}else{
        			$("#input-lprice").val(totalLocalAmount);
        			//$("#local-amount").focus();
        		}
        });
	});
	
	//////Estimated Foreign Price key in
	$("#input-estima").keyup(function(){
		var rate = $("#input-excRate").val();
		var foreign = $("#input-estima").val();
		var totalLocalAmount = (rate * foreign).toFixed(2);

		if(rate==""){
			//$("#local-amount").val(foreign);
			$("#input-lprice").val("0");
		}else{
			$("#input-lprice").val(totalLocalAmount);
			//$("#local-amount").focus();
		}
	});
	
	//////Local Price key in
	$("#input-lprice").keyup(function(){
		var rate = $("#input-excRate").val();
		var local = $("#input-lprice").val();
		var totalForeignAmount = (local / rate).toFixed(2);
		if(rate==""){
			$("#input-estima").val('');
		}else{
			$("#input-estima").val(totalForeignAmount);
		}
	});
	
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
			$("#save").attr('disabled',false);
			$("#input-vendorCode").css("border", "");
		}
	});
	
	
	///////Requestor ID
	$("#requestorId").blur(function(e){
		var reqid = $("#requestorId").val().trim();
		$.get('CheckRequestor', {
			patronID : reqid
	 	}, function(responseJson) {
		if(responseJson != null){
			if(responseJson==''){
				messageBox("036","",""); 
			}
			$.each(responseJson, function(key,value) {
				if(moment(today).format("YYYY-MM-DD") > moment(value['expdate']).format("YYYY-MM-DD")){
					messageBox("032","","");
					$(".save").hide();
			 		$("#requestorId").css("border", "solid red");
				}else if(value['CHARGING'] == 'N'){
					messageBox("118",value['STATDESC'], "@action");
					$(".save").hide();
			 		$("#requestorId").css("border", "solid red");
				}else{
					$.get('getPatronDept', {
						code : reqid
					 	}, function(responseJson) {
					 	if(responseJson != null){
					 	
							$.each(responseJson, function(key,value) {
								$("#div-reqname").text(value['Name']);
								$("#div-reqdept").val(value['Department']);
								$(".save").show();
								$("#requestorId").css("border", "");
								$("#cancel").show();
							});
						}
					});
				}
			});
		}
		});
	});
	
	//clear Requestor name and department when vendor code keydown backspace
	$("#requestorId").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#div-reqname, #div-reqdept").empty();
			$(".save").show();
			$("#requestorId").css("border", "");
		}
	});
	
	////ISBN
	$("#input-ISBN").blur(function(){
        $.get('CheckIsbn', {
        	isbn : $("#input-ISBN").val()
	 	}, function(responseJson) {
		if(responseJson != null){
			$.each(responseJson, function(key,value) {
				if(value['count'] >= 1){
					messageBox("165","", "");
				}else{
					$.get('CheckCatIsbn', {
			        	isbn : $("#input-ISBN").val()
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							if(value['count'] >= 1){
								messageBox("167","", "");
							}else{
								
							}
						});
					}
					});
				}
			});
		}
		});
    });

	
	
	$('#formdatareqCtrl').bootstrapValidator({
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
        	requestorId: {
        		trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'Requestor ID is required'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9 ]+$/,
                        message: 'No special characters allowed'
                    },
                    remote: {
                        type: "GET",
                        url: 'checkReqId',
                        data: function(validator) {
                            return {
                            	requestorId: validator.getFieldElements('requestorId').val(),
                            };
                        },
                        message: 'Requestor ID Not exist!',
                    }
                }
            },	
        	inputtitle: {
            	trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'Title is required'
                    }
                }
            },
            inputcopies: {
            	trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'No of copies is required'
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
     	 
     	 var inputForm = $form.serialize();
     	 var reqno = $(".reqno").val();
    	 var reqdate = moment($(".reqdate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
    	 var requestor = $('input[name="requestorId"]').val();   
    	 var mode = $('#acqMode').val(); 
    	 var isbn = $('input[name="inputISBN"]').val(); 
    	 var titleinput = $('#input-title').val();
    	 var author = $('input[name="inputauthor"]').val();
    	 var publication = $('input[name="inputpub"]').val();
    	 var edition = $('input[name="inputedition"]').val();
    	 var ctrlno = $('input[name="inputctrlno"]').val();
    	 var set = $('input[name="inputset"]').val();
    	 if(set == ""){
    		 set = 0;
    	 }
		 var lcno = $('input[name="inputlcNo"]').val();
		 var copies = $('input[name="inputcopies"]').val(); 
		 var remarks = $('#remarks').val();
		 if(remarks === undefined){
			 remarks = "";
		 }
		 
		var status = $("#reqStatus").val(); 
		 var currency = $('#currency').val();
		 var excRate = $('input[name="inputexcRate"]').val();
		 var fprice = $('input[name="inputestima"]').val();
		 var lprice = $('input[name="inputlprice"]').val();
		 var vend = $('input[name="inputVendorCode"]').val();
		 var remarksofficeuse = $('#remarksofficeuse').val();
		 if(remarksofficeuse === undefined){
			 remarksofficeuse = "";
		 }

    	 var createpseudo  =  "";

    	 if($("#checkboxpseudo").prop('checked') == true){
    		createpseudo = "Y";
		 }else{
			createpseudo = "N";
		 }
		 
		 var t = $('#reqCtrl').DataTable({
				responsive: true,
				scrollx: true,
				info: false,
				paging: true,
				ordering: false,
				destroy: true,
				searching:false,
				info: true,
				lengthChange: false,
				drawCallback: function( settings ) {
					$('#reqCtrl tbody tr').each(function() {
				          var Cell = $(this).find('td:eq(5)');
				          debugger;
				          if((Cell.text().trim() == "USER REQUEST") || (Cell.text().trim() == "REQUESTED")){
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
		 	case state = 'Add Request Control':
		 		var date =$(".reqdate").val();
		 		
		 		$('#formdatareqCtrl').find('input, select, textarea').not("#requestorId, #acqMode").val('');
		     	$('#formdatareqCtrl').data('bootstrapValidator').resetForm();
		     	$(".reqdate").val(date);
		     	$('#checkboxpseudo').prop('checked',false);
		    	$("#checkboxpseudo").attr('disabled',true);
		    	$("#div-vendorName").empty();
		    	 
		    	var checkboxreqIDval = "";
		    	var checkboxModeval = "";
		    	
		    	if($("#checkboxreqID").prop('checked') == true){
	
				    checkboxreqIDval = "Y";
				 }else{
					checkboxreqIDval = "N";
					$("#div-reqname, #div-reqdept").empty();
					$("#requestorId").val("");
				 }
				 
				 if($("#checkboxMode").prop('checked') == true){
					    checkboxModeval = "Y";
				 }else{
						checkboxModeval = "N";
						$("#acqMode").val("");
						$("#acqMode").prop("selectedIndex",-1);
				 }
				 $(".reqStatus").val("New request");
				 $.post("insertRequestCtrl", { 
					 	requestor : requestor,
					 	mode : mode,
					 	isbn : isbn,
					 	titleinput : titleinput,
					 	author : author,
					 	publication : publication,
					 	edition : edition,
					 	set : set,
					 	lcno : lcno,
					 	reqdate : reqdate,
					 	copies : copies,
					 	remarks : remarks,
					 	ctrlno : ctrlno,
					 	currency : currency,
					 	excRate : excRate,
					 	fprice : fprice,
					 	lprice : lprice,
					 	vend : vend,
					 	remarksofficeuse : remarksofficeuse,
					 	createpseudo : createpseudo
					 }, 
					 function(data,status){
						 messageBox("005"," Add Request Control","@action");
						 
						 //DISPLAY LATEST RECORD
						 $.get("getDisplayReqCtrl?action=add",function(json){
			    			 var return_data = new Array();
			    	           
			    	            for(var i=0;i< json.length; i++){
			    	            	t.row.add( [
			    	            	            json[i].reqno,
			    	            	            json[i].regName,
			    	    			            json[i].isbn,
			    	    			            json[i].title,
			    	    			            json[i].datereq,
			    	    			            json[i].statdesc,
			    	    			            '<button id="View" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalreqCtrl" data-mode="3" title="View Request Control" data-rowid="'+json[i].reqno+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-sm" data-toggle="modal" data-target="#modalreqCtrl" data-mode="2" title="Edit Request Control" data-rowid="'+json[i].reqno+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-sm DeleteReq" data-reqno="'+json[i].reqno+'" onclick="deleteRequest(this)"><span class="glyphicon glyphicon-trash" title="Delete Request" ></span></button>'
			    	    			        ] ).draw( false );
			    	            }
			    		 });
					 }
				     ).fail(function(data){
				    	 swal("fail");
				     }).success(function(data){
				     });
		 		break;
		 	case state = 'Edit Request Control':
		 		$.post("updateRequestCtrl", { 
		 			reqno : reqno,
		 			requestor : requestor,
				 	mode : mode,
				 	isbn : isbn,
				 	titleinput : titleinput,
				 	author : author,
				 	publication : publication,
				 	edition : edition,
				 	set : set,
				 	lcno : lcno,
				 	copies : copies,
				 	remarks : remarks,
				 	ctrlno : ctrlno,
				 	currency : currency,
				 	excRate : excRate,
				 	fprice : fprice,
				 	lprice : lprice,
				 	vend : vend,
				 	remarksofficeuse : remarksofficeuse,
				 	createpseudo : createpseudo
	 			}, 
		 	    function(data,status){
		 	    	messageBox("005"," Edit Request Control","@action");
		 	    	$("#closeModalAdd").click();
		 	    	$('#reqCtrl').dataTable().fnClearTable();
		 	    	
		 	    	$.get("getDisplayReqCtrl?action=edit",{reqno : reqno},function(json){
		    			 var return_data = new Array();
		    	           
		    			 for(var i=0;i< json.length; i++){
		    	            	t.row.add( [
											json[i].reqno,
											json[i].regName,
											json[i].isbn,
											json[i].title,
											json[i].datereq,
											json[i].statdesc,
		    	    			            '<button id="View" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalreqCtrl" data-mode="3" title="View Request Control" data-rowid="'+json[i].reqno+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-sm" data-toggle="modal" data-target="#modalreqCtrl" data-mode="2" title="Edit Request Control" data-rowid="'+json[i].reqno+'"><span class="glyphicon glyphicon-pencil"></span></button><button id="Delete" class="btn btn-dull btn-sm DeleteReq" data-reqno="'+json[i].reqno+'" onclick="deleteRequest(this)"><span class="glyphicon glyphicon-trash" title="Delete Request" ></span></button>'
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


