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
	
	
	$('#formdataReqCtrl').bootstrapValidator({
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
        	txtPatron: {
            	trigger: 'blur, focusout',
                validators: {
                    notEmpty: {
                        message: 'Requestor ID is required'
                    }
                }
            },
            txtLibraryID: {
            	trigger: 'blur, focusout',
                validators: {
                    notEmpty: {
                        message: 'Lending Library is required'
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
            txtTitle: {
            	//trigger: 'blur, focusout',
                validators: {
                    notEmpty: {
                        message: 'Title Person is required'
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
     	 var reqDate = moment($(".lblDateRequested").text(), 'DD/MM/YYYY').format("YYYYMMDD");
     	 var reqID = $("#txtPatron").val();
     	 var lendLib = $("#txtLibraryID").val();
     	 var doctype = $("#cboDocumentType").val();
     	 
     	 var dexpect = $("#dexpect").val();
	     	if(dexpect == '' || dexpect == ' '){
	     		dexpect = '';
			}else{
				dexpect = moment(dexpect, 'DD/MM/YYYY').format("YYYYMMDD");
			}
     	 
	     var predevtype = $("#txtDeliveryType").val();
	     var txtTitle = $("#txtTitle").val();
	     var issn = $("#txtIsbn").val();
	     var author = $("#txtAuthor").val();
	     var placePub = $("#txtPlaceOfPublication").val();
	     var pub = $("#txtPublisher").val();
	     var yearPub = $("#txtYearOfPublication").val();
	     var edition = $("#txtEdition").val();
	     var remarks = $("#txtRequestorRemark").val();
	     var vol = $("#txtVolume").val();
	     var iss = $("#txtIssue").val();
	     var pageno = $("#txtPageNumber").val();
     	 var gsUserId = $("#liferayLogin").val();

    	//////////////////////SET TABLE ///////////////////////////////////////////
    	var t = $('#ReqCtrl_table').DataTable({
     		destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			columnDefs    : [
						{'data': 'No'},
						{'data': 'Reference No'},
						{'data': 'Requestor'},
						{'data': 'ISBN'},
						{'data': 'Title'},
						{'data': 'Date Request'},
						{'data': 'Status'},
						{'data': 'Action'},
			],
			drawCallback: function( settings ) {
          	  $('#ReqCtrl_table tbody tr').each(function() {
          		  var Cell = $(this).find('td:eq(6)');
          		  //alert(Cell.text());
          		  debugger;
          		  if((Cell.text() == 'USER REQUEST')){
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
    	var totalrow = t.row().count();
    	///////////////////// Set Current Date  ///////////////////////////////////
    	var today = new Date(); 
    	//var liferayLogin = $("#liferayLogin").val();
    	
     	///////////////////////////////////////////////////////////////////////////
    	switch(title){
    		case state = 'Add Request Control':
    			$('#formdataReqCtrl').data('bootstrapValidator').resetForm();
    			$("#formdataReqCtrl").trigger('reset');
    			$('.lblDateRequested').text(moment(today).format("DD/MM/YYYY"));
    			$(".lblStatus").text("New Request");
    			$(".lblPatronName, .lblRequestorName, .lblLibName").empty();
    			
    			$("#cboDocumentType").prop("selectedIndex",-1);
    			
    			$.post("AddReqCtrl",{
    				reqDate : reqDate,
    				reqID : reqID, 
    				lendLib : lendLib, 
    				doctype : doctype, 
    				dexpect : dexpect, 
    				predevtype : predevtype, 
    				title : txtTitle, 
    				issn : issn, 
    				author : author, 
    				placePub : placePub,
    				pub : pub, 
    				yearPub : yearPub, 
    				pub : pub, 
    				yearPub : yearPub, 
    				edition : edition,
    				remarks : remarks, 
    				vol : vol,
    				iss : iss, 
    				pageno : pageno,
    				gsUserId : gsUserId,
     				},
     				function(message){
     					var status = message.replace(/\s+/g, '');

     					$(".lblRequestNo").text(message);

     					if (message != "") {

     						$.get("ResultSearch",
									{input_criteria : message.trim(), search_type : "reqno", inputDate : "",
     							endinputDate : ""}
							,function(json){
								var return_data = new Array();
								//messageBox("005","Add","@action");
								swal("Request Number is " +message.trim());
								for(var i=0;i< json.length; i++){
									var stat = GetStatus(json[i].status);
									t.row.add( [
									            totalrow+1,
												json[i].requestNo,
												json[i].requestor, 
												json[i].isbn,
												json[i].title,
												json[i].dateReq,
												stat,
												'<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalReqCtrl" data-whatever="View Request Control" title="View Request Control" data-rowid="'+json[i].requestNo+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalReqCtrl" data-whatever="Edit Request Control" title="Edit Request Control" data-rowid="'+json[i].requestNo+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].requestNo+'"><span class="glyphicon glyphicon-trash" title="Delete Request Control" ></span></button>',
									            ] ).draw( false );
								}
							});
     					}else{
     						swal("Unsuccessfully");
     					}
     			});
    			break;
    		case state = 'Edit Request Control':
    			
    			$.post("EditReqCtrl",{
    				sRequestNo : sRequestNo,
    				reqDate : reqDate,
    				reqID : reqID, 
    				lendLib : lendLib, 
    				doctype : doctype, 
    				dexpect : dexpect, 
    				predevtype : predevtype, 
    				title : txtTitle, 
    				issn : issn, 
    				author : author, 
    				placePub : placePub,
    				pub : pub, 
    				yearPub : yearPub, 
    				pub : pub, 
    				yearPub : yearPub, 
    				edition : edition,
    				remarks : remarks, 
    				vol : vol,
    				iss : iss, 
    				pageno : pageno,
    				gsUserId : gsUserId,
     				},
     				function(message){
     					var status = message.replace(/\s+/g, '');

     					if (message == "ok") {
     						$('#ReqCtrl_table').dataTable().fnClearTable();
     						$.get("ResultSearch",
									{input_criteria : sRequestNo, search_type : "reqno", inputDate : "",
     							endinputDate : ""}
							,function(json){
								var return_data = new Array();
								messageBox("005","Edit","@action");
 			    			 	$("#closeModalAdd").click();
								for(var i=0;i< json.length; i++){
									var stat = GetStatus(json[i].status);
									var no = parseInt(i);
 			    	            	no = (no+1)
 			    	            	
									t.row.add( [
									            no.toString(),
												json[i].requestNo,
												json[i].requestor, 
												json[i].isbn,
												json[i].title,
												json[i].dateReq,
												stat,
												'<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalReqCtrl" data-whatever="View Request Control" title="View Request Control" data-rowid="'+json[i].requestNo+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalReqCtrl" data-whatever="Edit Request Control" title="Edit Request Control" data-rowid="'+json[i].requestNo+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].requestNo+'"><span class="glyphicon glyphicon-trash" title="Delete Request Control" ></span></button>',
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
	
	function GetStatus(vsStatus){

		var tempGetStatus;

		switch (vsStatus){
		case "00":
			tempGetStatus = "USER REQUEST";
			break;
		case "01":
			tempGetStatus = "REJECTED";
			break;
		case "02":
			tempGetStatus = "IN PROGRESS";
			break;
		case "03":
			tempGetStatus = "RECEIVED";
			break;
		case "04":
			tempGetStatus = "RETURNED";
			break;
		}

		return tempGetStatus;
	}
	
});