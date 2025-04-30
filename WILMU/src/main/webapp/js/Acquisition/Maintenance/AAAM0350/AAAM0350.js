$(document).ready(function() {
	function messageBox(code, criteria, label){
		
		$.get("Global?type=Handler&name=Error_Page",{GL79ERRCODE : code,
			criteria : criteria,
		 	label : label},function(result){
				swal(result);
			
		});
} 
	////////////
	$('#modal_reviewlistno').on('hidden.bs.modal', function () {
		$('#search_reviewlist').collapse("show");
		$('#result_reviewlist').collapse("hide");
		$("#criteriaseachreviewlist").val("");	
		$("input[name='radioOptionseachreviewlist'][value='rListNo']").prop('checked', true);
		$('#table-reviewList').dataTable().fnClearTable();
		$("#criteriaseachreviewlist").show();
	});
	
	
	////////////////////today DATE //////////////////////////
	var today = new Date();  
	
	///maxlength
	$("#input-reqNo").attr('maxlength','10');
	$("#input-vendorCode").attr('maxlength','4');
	
	
	///////////////////// input-startDate set Current Date  ///////////////////////////////////
	$('#input-startDate').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///////////////////// input-endDate set Current Date  ///////////////////////////////////
	
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	//////datatable for new request
	$('#newreqtable').DataTable({
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
	
	//////datatable for review list
	$('#review').DataTable({
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
	
	/////////// GET actiontype first time //////////////////////////////////////////////////////
	var action = $('input[name=actiontype]:checked').val();
	newreq();
	
	/////////// GET actiontype ONCHANGE ///////////////////////////////////////////////////////
	$('input[name=actiontype]').change(function(){
		
		var action = $('input[name=actiontype]:checked' ).val();
		
		switch(action) {
			case "newreq":
				newreq();
				break;
			case "reviewList":
				reviewList();
				break;
			default:
		}
	});
	
	//////////////////function newreq /////////////////////////////////////////////////////
	function newreq(){
		$("#viewnewreq").show();
		$("#viewreviewlist").hide();
		$("#preview").attr('disabled',true);
		$("#reviewlist").attr('disabled',true);
		$('#newreqtable').dataTable().fnClearTable();
		$('#checkbox-date, #checkbox-reqNo, #checkbox-vendorCode').prop('checked',false);
		$("#input-vendorCode, #input-reqNo, #input-startDate").val('');
		$("#div-vendorName").empty();
		$('.checkAll').prop('checked', false);
	}
	
	//////////////////function newreq /////////////////////////////////////////////////////
	function reviewList(){
		$("#viewnewreq").hide();
		$("#viewreviewlist").show();
		$('#review').dataTable().fnClearTable();
		$("#reviewListNo").val('');
		$("#Reject, #Approve, #Merge").attr('disabled',true);
		$('.checkAll2').prop('checked', false);
		
	}

	//***********************************************************************************//
	/////FOR SEARCH NEW REQUEST
	
	//WHEN CLICK SEARCH 
	$("#button-retrievenewreq").click(function(){
		
		//var getChecked = $('input[name=searchMethod]:checked').val();
		var vendor = $("#input-vendorCode").val().trim();
		var reqno = $('#input-reqNo').val().trim();
		var inputStartDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var inputEndDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		
		var isvendorCheck  =  "";

   	 	if($("#checkbox-vendorCode").prop('checked') == true){
   	 		isvendorCheck = "vendorinput";
		}else{
			isvendorCheck = "";
		}
   	 	
   	 	var isreqnoCheck  =  "";

	 	if($("#checkbox-reqNo").prop('checked') == true){
	 		isreqnoCheck = "reqnoinput";
		}else{
			isreqnoCheck = "";
		}
	 	
	 	var isdateCheck  =  "";

	 	if($("#checkbox-date").prop('checked') == true){
	 		isdateCheck = "dateinput";
		}else{
			isdateCheck = "";
		}	
	 	
	 	searchVal(vendor, reqno, inputStartDate, inputEndDate, isvendorCheck, isreqnoCheck, isdateCheck);
	});
	
	///////////////pass value
	function searchVal(vendor, reqno, inputStartDate, inputEndDate, isvendorCheck, isreqnoCheck, isdateCheck){
		//loader("#newreqtable");  
		$('#newreqtable').DataTable( {
			responsive: true,
			scrollx: true,
			info: false,
			paging: true,
			ordering: false,
			destroy: true,
			searching:false,
			info: true,
			lengthChange: false,
			//oLanguage: {"sZeroRecords": "", "sEmptyTable": ""},
			processing: true,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
		    ajax: {
		        url: "GetNewList",
			    data:{
			    	vendor : vendor,
			    	reqno : reqno,
			    	inputStartDate : inputStartDate,
			    	inputEndDate : inputEndDate,
			    	isvendorCheck: isvendorCheck,
			        isreqnoCheck: isreqnoCheck,
			        isdateCheck: isdateCheck
			    },
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		           
		            for(var i=0;i< json.length; i++){
		              return_data.push({
		            	'' : '<input type = "checkbox" class="checkAll" id = "checkAll">',
		                'Request': json[i].reqno, 
		                'Author/Title' : json[i].title,  
		                'Quantity' : json[i].qty,
		                'Requestor' : json[i].requestor +", "+ json[i].patrname,
		                'Request Date' : json[i].datereq,
		                'Estimated Price': json[i].forex + " " +json[i].fprice,
		                'Local Price' : json[i].price
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
		     	    {'data': ''},
					{'data': 'Request'},
					{'data': 'Author/Title'},
					{'data': 'Quantity'},
					{'data': 'Requestor'},
					{'data': 'Request Date'},
					{'data': 'Estimated Price'},
					{'data': 'Local Price', render: $.fn.dataTable.render.number(',', '.', 2, '') },//{'data': 'Local Price'},
				],
		});
	}
	//***********************************************************************************//
	
	//clear vendor name when vendor code keydown backspace
	$("#input-vendorCode").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#div-vendorName").empty();
		}
	});


	///////////////////////////////////////////////////////////////////////////////////////
	///////////FOR PRINT PREVIEW checkbox
	// Handle click on "Select all" control
    $('#checkAll').on('click', function(){
    	/////////$("#process").show().prop('disabled', false);
       // Check/uncheck all checkboxes in the table
       var rows = $('#newreqtable').DataTable().rows({ 'search': 'applied' }).nodes();
       if(!this.checked){
    	   $('input[type="checkbox"]', rows).prop('checked', false);
    	   $("#preview").attr('disabled',true);
   		   $("#reviewlist").attr('disabled',true);
       }else{
    	   $('input[type="checkbox"]', rows).prop('checked', this.checked);
    	   $("#preview").attr('disabled',false);
    	   $("#reviewlist").attr('disabled',false);
       }
       
       /*if($('.newreqtable:checked').length>0){
    	   alert("fdfs");
    	   $("#preview").attr('disabled',false);
    	   $("#reviewlist").attr('disabled',false);
		}else{
			$("#preview").attr('disabled',true);
	   		   $("#reviewlist").attr('disabled',true);
		}*/
       /*$('input[type="checkbox"]', rows).prop('checked', this.checked);*/
    });

    // Handle click on checkbox to set state of "Select all" control
    $('#newreqtable tbody').on('change', 'input[type="checkbox"]', function(){
    	
    	 var totaltick = $('#newreqtable :input[type="checkbox"]:checked').length;
    	 //alert("totaltick"+totaltick);
    	 
    	 if(!this.checked){
    		 if(totaltick >= 1){
    			 $("#preview").attr('disabled',false);
    	   		 $("#reviewlist").attr('disabled',false);
    		 }else{
    			 $("#preview").attr('disabled',true);
  	   		 	 $("#reviewlist").attr('disabled',true);
    		 }
    	 }else{
    		 if(totaltick >= 1){
    			 $("#preview").attr('disabled',false);
    	   		 $("#reviewlist").attr('disabled',false);
		   }else{
			     $("#preview").attr('disabled',true);
	  	   		 $("#reviewlist").attr('disabled',true);
		   }
    	 }
    	 
       // If checkbox is not checked
       /*if(!this.checked){
    	   $("#preview").attr('disabled',true);
   		   $("#reviewlist").attr('disabled',true);
          var el = $('#checkAll').get(0);
          // If "Select all" control is checked and has 'indeterminate' property
          if(el && el.checked && ('indeterminate' in el)){
             // Set visual state of "Select all" control 
             // as 'indeterminate'
             el.indeterminate = true;
          }
       }else{
    	   $("#preview").attr('disabled',false);
    	   $("#reviewlist").attr('disabled',false);
       }*/
    });
    
    
    ////////////////BUTTON PREVIEW CLICK //////////////////////
    $('#preview').on('click', function () {	
    	var dataArr = [];
    	var id = $("#liferayLogin").val();

    	//alert($("#liferayLogin").val());
    	
    	$('#newreqtable').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
    		dataArr.push("'"+$(this).closest('tr').find('td:eq(1)').text()+"'");  
            //alert(dataArr);
        });
    	
    	///alert(dataArr);
    	var action = "preview";
	    var url = "Global?type=Letter&name=GenerateLetterAcq&reqno=" + dataArr
	    			+ "&id=" + id
	    			+ "&letterId=" + "EQ41"
	    			+ "&action=" + action;
		window.open(url).print();
		$('.checkAll').attr('checked',false);
    });
    
    ////BUTTON PRINT REVIEW LIST
    $('#reviewlist').on('click', function () {	
    	var dataArr = [];
    	$('#newreqtable').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
    		//dataArr.push($(this).closest('tr').find('td:eq(1)').text());
    		dataArr.push("'"+$(this).closest('tr').find('td:eq(1)').text()+"'");  
            //alert(dataArr);
        });
    	
    	$.get("Acquisition?type=Maintenance&module=AAAM0350&name=Modal_ReviewListNumber&reqno="+dataArr,function(data){
			// action : action
			$("#processContent").html(data);
		});
    });
    
    ///////////
    $("#reviewListNo").attr('maxlength','10');
    
    ////////////////////////////////////////TAB REVIEW LIST NO AND BACKSPACE ////////////////////////////////////////////
	$("#reviewListNo").keydown(function(e){ 
		var reviewlist = $("#reviewListNo").val();
		
		var code = e.keyCode || e.which;
		if(code == '9' ||code == '13' ){
			$('#review').DataTable( {
				responsive: true,
				scrollx: true,
				info: false,
				paging: true,
				ordering: false,
				destroy: true,
				searching:false,
				info: true,
				lengthChange: false,
				columnDefs: [
				 	{ className: "text-right", "targets": [3, 6,7] },
				 ],
			    ajax: {
			        url: "GettaborenterreviewlistAcq?reviewlist="+reviewlist,
			        type: "GET",
			        dataSrc: function (json) {
			            var return_data = new Array();
			           
			            for(var i=0;i< json.length; i++){
			              return_data.push({
			            	  '' : '<input type = "checkbox" class="checkAll2" id = "checkAll2">',
				          	  'Request': json[i].reqno, 
				              'Author/Title' : json[i].title,  
				              'Quantity' : json[i].qty,
				              'Requestor' : json[i].requestor +", "+ json[i].sRequestorName,
				              'Request Date' : json[i].datereq,
				              'Estimated Price': json[i].forex + " " +json[i].fprice,
				              'Local Price' : json[i].price,
				              'Review List No' : json[i].REVIEWLIST
			              })
			            }
			            return return_data;
			          },
			     },//This is the end of the AJAX object from the example above
			     	columns    : [
			     		{'data': ''},
			 			{'data': 'Request'},
			 			{'data': 'Author/Title'},
			 			{'data': 'Quantity'},
			 			{'data': 'Requestor'},
			 			{'data': 'Request Date'},
			 			{'data': 'Estimated Price'},
			 			{'data': 'Local Price', render: $.fn.dataTable.render.number(',', '.', 2, '') },
			 			{'data': 'Review List No'},
					]
			});
			event.preventDefault();	
		}else if(code == '8'){
			$('#review').dataTable().fnClearTable();
			
			$("#Reject, #Approve, #Merge").attr('disabled',true);	
		}
	});
    
	///////////////////////////////////////////////////////////////////////////////////////
	///////////FOR REVIEW LIST NO checkbox
	// Handle click on "Select all" control
    $('.checkAll2').on('click', function(){
    	/////////$("#process").show().prop('disabled', false);
       // Check/uncheck all checkboxes in the table
       var rows = $('#review').DataTable().rows({ 'search': 'applied' }).nodes();
       if(!this.checked){
    	   $('.checkAll2', rows).prop('checked', false);
    	   $("#Reject, #Approve, #Merge").attr('disabled',true);
       }else{
    	   $('.checkAll2', rows).prop('checked', this.checked);
    	   $("#Reject, #Approve, #Merge").attr('disabled',false);
       }
       /*$('input[type="checkbox"]', rows).prop('checked', this.checked);*/
    });

    // Handle click on checkbox to set state of "Select all" control
    $('#review tbody').on('change', '.checkAll2', function(){
    	
       // If checkbox is not checked
       if(!this.checked){
    	   $("#Reject, #Approve, #Merge").attr('disabled',true);
          var el = $('.checkAll2').get(0);
          // If "Select all" control is checked and has 'indeterminate' property
          if(el && el.checked && ('indeterminate' in el)){
             // Set visual state of "Select all" control 
             // as 'indeterminate'
             el.indeterminate = true;
          }
       }else{
    	   $("#Reject, #Approve, #Merge").attr('disabled',false);
       }
    });
    
    ////////////////BUTTON APPROVE CLICK //////////////////////////////////////////////////
    $('#Approve').on('click', function () {	
    	var dataArr = [];
    	var id = $("#liferayLogin").val();
    	//alert($("#liferayLogin").val());
    	
    	$('#review').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
    		var reqnumber = $(this).closest('tr').find('td:eq(1)').text();  
     		var indexval  = $('#review').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
     		$('#review').DataTable().row(indexval).remove().draw();
     		
     		var obj = {};
  	    	obj.reqnumber = reqnumber;
  	    	obj.indexval = indexval;
  	    	dataArr.push(obj);
        });
    	///alert(JSON.stringify(dataArr))
    	
    	////GET REVIEWLIST NUMBER
    	var reqnoArray = [];
    	for (var i = 0; i < dataArr.length; i++) {
 	 		var code = dataArr[i]['reqnumber'];
 	 		var obj = {};
  	    	obj.code = code;
  	    	reqnoArray.push(obj);
 	 	}
    	////alert(JSON.stringify(reviewArray));
    	
    	
    	$.post("ApproveReviewList", { 
    	 	reqno : JSON.stringify(reqnoArray),
    	 	userid : $("#liferayLogin").val()
		 }, 
		 function(data,status){
			////get INDEX
			 alert(reqno);
			 messageBox("005","Approved","@action");
			 $("#reviewListNo").val("");
			/*for (var i = 0; i < dataArr.length; i++) {
		 		var getindex = dataArr[i]['indexval'];
		 		//alert(getindex);
		 		$('#review').DataTable().row(getindex).remove().draw();
		 		$('.checkAll2').prop('checked', false);
			}*/
		 }
	     ).fail(function(data){
	    	 swal("fail");
	     }).success(function(data){
	 	 });
    });
    
    ////////////////BUTTON REJECT CLICK //////////////////////////////////////////////////
    $('#Reject').on('click', function () {	
    	var dataArr = [];
    	var indxArr = [];
    	var id = $("#liferayLogin").val();
    	
    	var dataArr = [];
    	 $('#review').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
    		
    		var req = "'"+$(this).closest('tr').find('td:eq(1)').text()+"'";//dataArr.push($(this).closest('tr').find('td:eq(1)').text());  
    		var indexval  = $('#review').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
    		//$('#review').DataTable().row(indexval).remove().draw();
    		
    		var obj = {};
 	    	obj.req = req;
 	    	obj.indexval = indexval;
 	    	dataArr.push(obj);
 	    	indxArr.push(indexval);
        });
    	 
	 	/////////////////////get reqno 
	 	var reqArray = [];
	 	var reqArray2 = [];
	 	   	
	 	for (var i = 0; i < dataArr.length; i++) {
	 		var code = dataArr[i]['req'];
	 		var obj = {};
 	    	obj.code = code;
 	    	reqArray.push(obj);
 	    	reqArray2.push(code);  
	 	}
	 	
	 	
	 
	 	
	 	swal({
			text: "Do you wish to reject the request(s) ?",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "Yes",
	        cancelButtonText: "No"
	      }).then(
	        function(isConfirm) {
	          if (isConfirm) {
	        	  $.post("rejectReviewList", { 
	          	 	reqno : JSON.stringify(reqArray),
	          	 	//reviewNo : reviewNo
	      		 }, 
	      			 function(data,status){
	      			messageBox("005","Rejected","@action");
	      			 var action = "reject";
	      			    var url = "Global?type=Letter&name=GenerateLetterAcq&reqno=" + reqArray2
	      			    			+ "&id=" + id
	      			    			+ "&action=" + action;
	      				window.open(url).print();
	      				
	      				/////////////////////get index 
	      			
	      				$("#reviewListNo").val("");
	      				$.each(indxArr, function(index, value){
	  	        		  $('#review').DataTable().row(value).remove().draw();
	      				});
	      				
	      			 }
	      		     ).fail(function(data){
	      		    	 swal("fail");
	      		     }).success(function(data){
	      	     });
	          }
	        }
	      );
	 	
	 	/*alert(JSON.stringify(reqArray));
	 	alert(reqArray2);*/
    });
    
    ////////////////BUTTON MERGE CLICK //////////////////////////////////////////////////
    $('#Merge').on('click', function () {	
    	var dataArr = [];
    	var id = $("#liferayLogin").val();
    	//alert($("#liferayLogin").val());
    	
    	$('#review').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
    		var reqnumber = $(this).closest('tr').find('td:eq(1)').text();  
     		var indexval  = $('#review').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
     		/////$('#review').DataTable().row(indexval).remove().draw();
     		
     		var obj = {};
  	    	obj.reqnumber = reqnumber;
  	    	obj.indexval = indexval;
  	    	dataArr.push(obj);
        });
    	///alert(JSON.stringify(dataArr))
    	
    	////GET REQ NUMBER
    	var reqnoArray = [];
    	for (var i = 0; i < dataArr.length; i++) {
 	 		var code = dataArr[i]['reqnumber'];
 	 		var obj = {};
  	    	obj.code = code;
  	    	reqnoArray.push(obj);
 	 	}
    	////alert(JSON.stringify(reviewArray));
    	
    	swal({
			text: "You are performing Merge Approval for the selected requests. Do you want to proceed?",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "Yes",
	        cancelButtonText: "No"
	      }).then(
	        function(isConfirm) {
	          if (isConfirm) {
	        	  //alert("comfirm");
	        	  $.post("MergeReviewList", { 
	          	 	reqno : JSON.stringify(reqnoArray),
	          	 	userid : $("#liferayLogin").val()
	      		 }, 
	      		 function(data,status){
	      			////get INDEX
	      			 messageBox("005","Merge","@action");
	      			for (var i = 0; i < dataArr.length; i++) {
	      		 		var getindex = dataArr[i]['indexval'];
	      		 		///alert(getindex);
	      		 		//$('#review').DataTable().row(getindex).remove().draw();
	      		 		$('.checkAll2').prop('checked', false);
	      		 		setTimeout(function(){
	    					$("#button-retrievereviewlistno").click();
	    				}, 1000);
	      			}
	      		 }
	      	     ).fail(function(data){
	      	    	 swal("fail");
	      	     }).success(function(data){
	      	 	 });
	          }
	          
	        },
	        function() {
	        	//alert("noooo");
	        }
	      );
    	
    	/**/
    });
    
    /////vendor code
	$("#input-vendorCode").blur(function(){
		$('#checkbox-vendorCode').prop('checked', true);
		var vendorCode = $("#input-vendorCode").val().toUpperCase();
		$('#input-vendorCode').val($('#input-vendorCode').val().toUpperCase());
		//alert("vendorCode " + vendorCode);
		$("#div-vendorName").empty();
		
		if(vendorCode == ""){
			$("#button-retrievenewreq").attr('disabled',false);
			$('#checkbox-vendorCode').prop('checked', false);
			$("#input-vendorCode").css("border", "");
		}else{
		////display vendor name
			$.get('Global?type=Handler&name=GetVendorName', {
        	code : vendorCode
		 	}, function(responseJson) {
		 	if(responseJson==''){
		 		$("#button-retrievenewreq").attr('disabled',true);
		 		$("#input-vendorCode").css("border", "solid red");
		 		$('#checkbox-vendorCode').prop('checked', false);
		 	}else{//(responseJson != null){
		 	
				$.each(responseJson, function(key,value) {
					$("#div-vendorName").text(value['vendorName']);
					$("#button-retrievenewreq").attr('disabled',false);
					$('#checkbox-vendorCode').prop('checked', true);
					$("#input-vendorCode").css("border", "");
				});
			}
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
			$('#checkbox-vendorCode').prop('checked', false);
		}
	});
	
	/////req no
	$("#input-reqNo").blur(function(e){
		if($(this).val().length >= 1) {
			$('#checkbox-reqNo').prop('checked', true);
	    } else {
	    	$('#checkbox-reqNo').prop('checked', false);
	    }
		
	});
	
	$("#input-reqNo").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$('#checkbox-reqNo').prop('checked', false);
		}
	});
	
	$("#input-reqNo").keyup(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$('#checkbox-reqNo').prop('checked', true);
		}
	});
	
	/////date
	$('#input-startDate').change(function(){
		$('#checkbox-date').prop('checked', true);
	});
	
	//******************************************************************************************//
	// retrieve review list click
	$("#button-retrievereviewlistno").click(function(){
		var reviewlist = $("#reviewListNo").val();
		
		$('#review').DataTable( {
			responsive: true,
			scrollx: true,
			info: false,
			paging: true,
			ordering: false,
			destroy: true,
			searching:false,
			info: true,
			lengthChange: false,
			columnDefs: [
			 	{ className: "text-right", "targets": [3, 6,7] },
			 ],
		    ajax: {
		        url: "GetReviewList?reviewlist="+reviewlist,
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		           
		            for(var i=0;i< json.length; i++){
		              return_data.push({
		            	  '' : '<input type = "checkbox" class="checkAll2" id = "checkAll2">',
			          	  'Request': json[i].reqno, 
			              'Author/Title' : json[i].title,  
			              'Quantity' : json[i].qty,
			              'Requestor' : json[i].requestor +", "+ json[i].sRequestorName,
			              'Request Date' : json[i].datereq,
			              'Estimated Price': json[i].forex + " " +json[i].fprice,
			              'Local Price' : json[i].price,
			              'Review List No' : json[i].REVIEWLIST
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
		     		{'data': ''},
		 			{'data': 'Request'},
		 			{'data': 'Author/Title'},
		 			{'data': 'Quantity'},
		 			{'data': 'Requestor'},
		 			{'data': 'Request Date'},
		 			{'data': 'Estimated Price'},
		 			{'data': 'Local Price', render: $.fn.dataTable.render.number(',', '.', 2, '') },
		 			{'data': 'Review List No'},
				]
		});
	});
	
	//////////////////////////////
	$("#checkbox-vendorCode").change(function() {
	    var ischecked= $(this).is(':checked');
	    if(!ischecked)
	    	$("#div-vendorName").empty();
	    	$("#input-vendorCode").css("border", "");
	    	$("#input-vendorCode").val("");
	});
	
	$("#checkbox-reqNo").change(function() {
	    var ischecked= $(this).is(':checked');
	    if(!ischecked)
	    	$("#input-reqNo").val("");
	});
	
	$("#checkbox-date").change(function() {
	    var ischecked= $(this).is(':checked');
	    if(!ischecked)
	    	$("#input-startDate").val("");
	});
	
	
	
	
});
