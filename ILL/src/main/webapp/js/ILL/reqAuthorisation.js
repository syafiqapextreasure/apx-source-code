$(document).ready(function() {
	
	////////////////////today DATE //////////////////////////
	var today = new Date();
	var liferayLogin = $("#liferayLogin").val();
	
	$("#button-retrievereq").attr('disabled', true);
	$("#Reject, #Approve").attr('disabled',true);
	
	///////////////////// input-startDate set Current Date  ///////////////////////////////////
	$('#input-startDate').datepicker({
                format: "dd/mm/yyyy",
                todayBtn: true,
                autoclose: true,
                todayHighlight: true
            }).on('changeDate', function(e) {
                var startdate = $(this).val();
                if(startdate.length >= 1){
                    $('#checkbox-date').prop('checked', true);
                    $("#button-retrievereq").attr('disabled', false);
                } else {
                    $('#checkbox-date').prop('checked', false);
                    $("#button-retrievereq").attr('disabled', true);
                }
     });
	
	///////////////////// input-endDate set Current Date  ///////////////////////////////////
	
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	$('#reqtable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	//****************************************** AREA FOR keyup, keydown blur****************************//
	////////Requestor ID////////////
	//keyup
	$("#txtRequestor").keyup(function() {

		var requestor = $("#txtRequestor").val();
		
		if(requestor.length >= 1){
			$('#checkbox-reqid').prop('checked', true);
		}else{
			$('#checkbox-reqid').prop('checked', false);
		}
	});
	
	//blur
	$("#txtRequestor").blur(function(e){
		var requestor = $("#txtRequestor").val();

	
		$(".lblRequestorName").empty();
		////display vendor name
		$.get('GetName', {
        	code : requestor
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		$("#txtRequestor").val("");
		 		$("#txtRequestor").css("border", "solid red");
		 		$("#button-retrievereq").attr('disabled', 'disabled');
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".lblRequestorName").text(value['Name']);
					$("#txtRequestor").css("border", "");
					$("#button-retrievereq").attr('disabled', false);
				});
			}
		});
	});
	
	//on checkbox checked or not
	$('#checkbox-reqid').change(function() {
    	if($(this).prop('checked')) {
   		} else {
        	$("#txtRequestor").val("");
        	$(".lblRequestorName").empty();
        	$("#button-retrievereq").attr('disabled', true);
     	}
     });
	
	/////////Lending Library ID////////////
	$("#txtLibraryId").keyup(function() {

		var requestor = $("#txtLibraryId").val();
		
		if(requestor.length >= 1){
			$('#checkbox-LibraryId').prop('checked', true);
		}else{
			$('#checkbox-LibraryId').prop('checked', false);
		}
	});
	
	//blur
	$("#txtLibraryId").blur(function(e){
		var libID = $("#txtLibraryId").val();
	
		$(".lblLibraryName").empty();
		////display vendor name
		$.get('GetName', {
        	code : libID
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		$("#txtLibraryId").val("");
		 		$("#txtLibraryId").css("border", "solid red");
		 		$("#button-retrievereq").attr('disabled', 'disabled');
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".lblLibraryName").text(value['Name']);
					$("#txtLibraryId").css("border", "");
					$("#button-retrievereq").attr('disabled', false);
				});
			}
		});
	});
	
	//on checkbox checked or not
	$('#checkbox-LibraryId').change(function() {
    	if($(this).prop('checked')) {
   		} else {
        	$("#txtLibraryId").val("");
        	$(".lblLibraryName").empty();
        	$("#button-retrievereq").attr('disabled', true);
     	}
     });
	
	/////////Date Requested////////////
	$("#input-startDate").on("input", function () {
                var startdate = $(this).val();
                if(startdate.length >= 1){
                    $('#checkbox-date').prop('checked', true);
                    $("#button-retrievereq").attr('disabled', false);
                } else {
                    $('#checkbox-date').prop('checked', false);
                    $("#button-retrievereq").attr('disabled', true);
                }
            });
	
	//////////////////////////////REFERSH//////////////////////////////////////
	$('#refresh').click(function(){
		$('#reqtable').dataTable().fnClearTable();
		$("#serachreqathour")[0].reset();
		$(".lblRequestorName, .lblLibraryName").empty();
		$("#txtRequestor, #txtLibraryId").css("border", "");
		$("#button-retrievereq").attr('disabled', true);
	});
	///////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////RETRIEVE BUTTON CLICK////////////////////////
	$('#button-retrievereq').click(function(){
		
		$('#reqtable').dataTable().fnClearTable();
		$("#Reject, #Approve").attr('disabled',false);
		
		var requestor = $("#txtRequestor").val().trim();
		var libID = $("#txtLibraryId").val().trim();
		var inputStartDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var inputEndDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		
		var isreqidCheck  =  "";

   	 	if($("#checkbox-reqid").prop('checked') == true){
   	 		isreqidCheck = "reqid";
		}else{
			isreqidCheck = "";
		}
   	 	
   	 	var islibidCheck  =  "";

	 	if($("#checkbox-LibraryId").prop('checked') == true){
	 		islibidCheck = "LibraryId";
		}else{
			islibidCheck = "";
		}
	 	
	 	var isdateCheck  =  "";

	 	if($("#checkbox-date").prop('checked') == true){
	 		isdateCheck = "dateReq";
		}else{
			isdateCheck = "";
		}
	 	
	 	searchVal(requestor, libID, inputStartDate, inputEndDate, isreqidCheck, islibidCheck, isdateCheck);
		
	});
	///////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////WHEN CLICK CHECK BOX////////////
	/// 1) FOR SELECT ALL
	$('#example-select-all').on('click', function(){
		// Check/uncheck all checkboxes in the table
		var rows = $('#reqtable').DataTable().rows({ 'search': 'applied' }).nodes();
		if(!this.checked){
	    	$('#example-select-all', rows).prop('checked', false);
	    	$("#Reject, #Approve").attr('disabled',true);
	   	}else{
	    	$('#example-select-all', rows).prop('checked', this.checked);
	    	$("#Reject, #Approve").attr('disabled',false);
	    }
	});
	
	// Handle click on checkbox to set state of "Select all" control
    $('#reqtable tbody').on('change', '#example-select-all', function(){
    	
       // If checkbox is not checked
       if(!this.checked){
    	  //$(".saveRetrieve").attr('disabled',true);
          var el = $('#example-select-all').get(0);
          var totaltick = $('#reqtable :input[type="checkbox"]:checked').length
          if(totaltick == 0){
        	  $("#Reject, #Approve").attr('disabled',true);  
          }else{
        	  $("#Reject, #Approve").attr('disabled',false);  
          }
          
          // If "Select all" control is checked and has 'indeterminate' property
          if(el && el.checked && ('indeterminate' in el)){
             // Set visual state of "Select all" control 
             // as 'indeterminate'
             el.indeterminate = true;
          }
       }else{
    	   $("#Reject, #Approve").attr('disabled',false);
       }
    });
    ///////////////////////////////////////////////////////////////////////////
	
    ///////////////pass value
	function searchVal(requestor, libID, inputStartDate, inputEndDate, isreqidCheck, islibidCheck, isdateCheck){
		//loader("#newreqtable");  

		$('#reqtable').DataTable( {
			responsive: true,
			scrollx: true,
			info: false,
			paging: true,
			ordering: false,
			destroy: true,
			searching:false,
			info: true,
			lengthChange: false,
		    ajax: {
		        url: "GetReq",
			    data:{
			    	requestor : requestor,
			    	libID : libID,
			    	inputStartDate : inputStartDate,
			    	inputEndDate : inputEndDate,
			    	isreqidCheck: isreqidCheck,
			    	islibidCheck: islibidCheck,
			        isdateCheck: isdateCheck
			    },
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		           
		            for(var i=0;i< json.length; i++){
		              return_data.push({
		            	'': '<input type="checkbox" id="example-select-all" name="id[]" value="'+json[i].reqno+'">',
		                'Request No': json[i].reqno, 
		                'Title/Author' : json[i].titleAuthor,  
		                'ISBN/ISSN' : json[i].isbnissn,
		                'Requestor' : json[i].requestorID +", "+ json[i].requestorName,
		                'Request Date' : json[i].reqDate
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
		     	    {'data': ''},
					{'data': 'Request No'},
					{'data': 'Title/Author'},
					{'data': 'ISBN/ISSN'},
					{'data': 'Requestor'},
					{'data': 'Request Date'},
				],
		});
	}
	//***********************************************************************************//
	
	
	////////////////BUTTON REJECT CLICK //////////////////////////////////////////////////
    $('#Reject').on('click', function () {	
    	var dataArr = [];
    	
    	
    	var dataArr = [];
    	 $('#reqtable').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
    		
    		var req = $(this).closest('tr').find('td:eq(1)').text();//dataArr.push($(this).closest('tr').find('td:eq(1)').text());  
    		var indexval  = $('#reqtable').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
    		//$('#reqtable').DataTable().row(indexval).remove().draw();
    		var obj = {};
 	    	obj.req = req;
 	    	obj.indexval = indexval;
 	    	dataArr.push(obj);
 	    	
 	    	setTimeout(function(){
 	    		$('#reqtable').DataTable().row(indexval).remove().draw();
			}, 1000); 
        });
    	 

	 	/////////////////////get reqno 
	 	//var reqArray = [];
	 	var reqArray2 = [];
	 	   	
	 	for (var i = 0; i < dataArr.length; i++) {
	 		var code = dataArr[i]['req'];
 	    	reqArray2.push(code);  
	 	}
	 	
	 	var total = reqArray2.length;
	 	
	 	$.post("Reject", {
			liferayLogin : liferayLogin,
			total : total,
			reqno : reqArray2.toString()

		}, function(result){
			if(result=="Done"){
				messageBox("005","Rejected","@action");
			}else{
				swal("Fail");
			}
		});
	 	
	 	
    });
    
    ////////////////BUTTON REJECT CLICK //////////////////////////////////////////////////
    $('#Approve').on('click', function () {	
    	var dataArr = [];
    	
    	
    	var dataArr = [];
    	 $('#reqtable').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
    		
    		var req = $(this).closest('tr').find('td:eq(1)').text();//dataArr.push($(this).closest('tr').find('td:eq(1)').text());  
    		var indexval  = $('#reqtable').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
    		//$('#reqtable').DataTable().row(indexval).remove().draw();
    		var obj = {};
 	    	obj.req = req;
 	    	obj.indexval = indexval;
 	    	dataArr.push(obj);
 	    	
 	    	setTimeout(function(){
 	    		$('#reqtable').DataTable().row(indexval).remove().draw();
			}, 1000); 
         });
    	 

	 	/////////////////////get reqno 
	 	//var reqArray = [];
	 	var reqArray2 = [];
	 	   	
	 	for (var i = 0; i < dataArr.length; i++) {
	 		var code = dataArr[i]['req'];
 	    	reqArray2.push(code);  
	 	}
	 	
	 	var total = reqArray2.length;
	 	
	 	$.post("Approve", {
			liferayLogin : liferayLogin,
			total : total,
			reqno : reqArray2.toString()

		}, function(result){
			if(result=="Done"){
				messageBox("005","Approve","@action");
			}else{
				swal("Fail");
			}
		});
	 	
	 	
    });
	
});