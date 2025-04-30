$(document).ready(function(){
	
	function messageBox(langcode, code, criteria, label){
			$.get("Global?type=Handler&name=Error_Page",{
				GL79LANGCODE : langcode,
				GL79ERRCODE : code,
				criteria : criteria,
			 	label : label},function(result){
			 		//alert(result);
					//swal('',result, 'info' );
					swal(result);
			});
			
			
	} 
	
	
	
	//table
	//////table setup
	$('#reviewTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	$("input[name=radiostat][value='15']").prop('checked', true);
	$("#branch").prop("selectedIndex",-1); 
	
	$("#GL14STAT").prop('disabled', 'disabled');

	
	
	$.get('GetFndPatron', {
        	userID : $("#liferayLogin").val()
	 	}, function(responseJson) {
		if(responseJson != null){
			
			/*if(responseJson==''){
			}*/
			
			$.each(responseJson, function(key,value) {
				
			$("#branch option[value="+value['Branch']).attr('selected','selected');
				
			});
		}
	});
	
	$('input[name=radiostat]').change(function () {
		$('#reviewTable').DataTable().clear().draw();
	});
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		
		var radiostat = $("input[name='radiostat']:checked").val();
		var branch = $( "#branch" ).val();
		
		
		
			
		var t = $('#reviewTable').DataTable( {
			//dom: 'Bfrtip',
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "ResultFormReviewPatron",
		        data : {radiostat :  radiostat, branch : branch},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'Patron Id' : json[i].PatronId,
		                'Patron Name' : json[i].PatronName,
		                'Branch' : json[i].BranchID +" : "+json[i].BranchName,
		                'Action' : '<button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalpatronpreview" data-whatever="Edit" title="Preview Patron" data-rowid="'+json[i].PatronId+'"><span class="glyphicon glyphicon-pencil"></span></button>',
						//'Action' : '<button class="btn btn-info btn-xs" data-toggle='modal' data-target='#editModal' href='Modal_EditPatron?GL14PATR=<%=newConvertId%>'><span class="glyphicon glyphicon-pencil" title="Edit Record" onclick="noteCheck('<%=newConvertId%>', 1000)" ></span></button>'
										
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Patron Id'},
					{'data': 'Patron Name',},
					{'data': 'Branch'},
					{'data': 'Action'},
				],
    	});


		//////
		if(radiostat == "14" || radiostat == "16"){
			t.column(4).visible(false);
		}else{
			t.column(4).visible(true);
		}
		
	
		

	});
	
	////////////////
	//****************************************** AREA FOR MODAL *****************************************//
	///$("#modalpatronpreview input[name=GL14NAMETITLE]").prop("selectedIndex",-1);
	///$("#plkupDetails(5)").prop("selectedIndex",-1); 
	$('input[name=GL14SEX]').attr("disabled",true);
	$('#GL14TOWN, #GL14OFFTOWN').prop("disabled", true);
	
	var today = new Date();  
	
	$('#GL14MEMDATE').val(moment(today).format("DD/MM/YYYY"));
	
	
	$('#GL14MEMDATE').datepicker({
		
		//dateFormat : "dd/mm/yyyy",
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,	
		endDate: new Date(),
		/*}).on('changeDate', function (selected) {
                    var minDate = new Date(selected.date);
                    minDate.setDate(minDate.getDate() + 1);
                    $('#GL14EXPDATE2').datepicker('setStartDate', minDate);*/
	});
	
	///////////////////// GL14EXPDATE2 set Current Date  ////////////////////
	
	$('#GL14EXPDATE2').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
     	startDate: new Date(),
		/* }).on('changeDate', function (selected) {
                    var minDate = new Date(selected.date);
                    minDate.setDate(minDate.getDate() - 1);
                    $('#GL14MEMDATE').datepicker('setEndDate', minDate);*/
    });
	
	$('#modalpatronpreview').on('show.bs.modal', function (event) {
		
		$(this).find('.nav a:first').tab('show');
		
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');

		modal.find('.modal-title').text(recipient);
		
		//$('.picdmge').prop('src', $('.picdmge').prop("src").replace("=?patrids?", ""));
		///$('.picdmge').attr('src', $('.picdmge').attr("src").replace("?patrid?", value['PATR']));
		
		switch(recipient){
		case state = 'Edit':
			modal.find('.modal-title').text("Review Patron Detail");

			
			///$("#plkupDetails(5)").prop("selectedIndex",-1);
			///Display Pic
			var a= "PatronReviewPhoto?patrid="+rowid;
			$.get(a,function(data){
			
				 if(data!=null && data!=""){
					 $("#imgtest3").attr("src", a);
				  	if(data == "undefined"){
						 var pathArray = window.location.pathname.split('/');
						 var newPathname = "";
						 newPathname += "/";
						 newPathname += pathArray[1];
						 $("#imgtest3").attr("src", newPathname+"/resources/image/user_default.png");
				 	}
				}else{
					 var pathArray = window.location.pathname.split('/');
					 var newPathname = "";
					 newPathname += "/";
					 newPathname += pathArray[1];
					 $("#imgtest3").attr("src", newPathname+"/resources/image/user_default.png");
				 }
			});
			

			var s =  $('.picdmge2').prop('src');
			s = s.substring(0, s.indexOf('='));
			var buktiimg = s+"="+rowid
			$(".picdmge2").prop("src",buktiimg);

				
			$.get('GetForPatronDetail', {
		        	patronid : rowid,
				 	}, function(responseJson) {
						if(responseJson==''){
					 	}else{					 	
							$.each(responseJson, function(key,value) {
								
								

								$("input[name=patronid]").val(value['PATR'])
								$(".GL14PATR").text(value['PATR']);
								$('.GL14NAME').text(value['NAME']);
								
								$('#GL14GRID').val(value['GRID']);
								$('#GL14CATE').val(value['CATE']);
								$('#GL14STAT').val(value['STAT']);
								$('#GL14BRNC').val(value['BRNC']);
								$('#txtDetails19').val(value['MEMDATE']);
								//$('#txtDetails20').val(value['EXPDATE']);
								
								
								$('.GL14NEWIC').text(value['NEWIC']);
								$('.dob').text(value['DOB']);
								
								$("input[name=GL14SEX][value=" + value['SEX'] + "]").prop('checked', true);
								
								$('.GL14IPADD').text(value['IPADD']);

								$('#GL14DEPOSIT').val(value['DEPOSIT']);
								$('#GL14MEMFEE').val(value['MEMFE']);
								$('#GL14RECEIPT').val(value['RECEIPT']);
								
								$('.GL14ADD1').text(value['ADD1']);
								$('.GL14ADD2').text(value['ADD2']);
								$('.GL14ADD3').text(value['ADD3']);
								$('.GL14CODE').text(value['CODE']);
								$('#GL14TOWN').val(value['TOWN']);
								$('.GL14HTEL').text(value['HTEL']);
								$('.GL14MTEL').text(value['MTEL']);
								
								
								$('.OFFADD1').text(value['OFFADD1']);
								$('.OFFADD2').text(value['OFFADD2']);
								$('.OFFADD3').text(value['OFFADD3']);
								$('.GL14OFFCODE').text(value['OFFCODE']);
								$('#GL14OFFTOWN').val(value['OFFTOWN']);
								$('.GL14OTEL').text(value['OTEL']);
								$('.GL14FAX').text(value['FAX']);
								
								$.get('ServeletGetFndPatronCategoryAddRenewgrc', {
						        	cate : value['CATE'],
								 	}, function(responseJson) {
									
										/*var futureMonth = moment(today).add(responseJson, 'Y');

										//alert(moment(futureMonth).format("DD/MM/YYYY")+"futureMonth");
										$('#GL14EXPDATE2').val(moment(futureMonth).format("DD/MM/YYYY"));
										$('#GL14EXPDATE2').datepicker("setDate", moment(futureMonth).format("DD/MM/YYYY"));*/
										
										
									var fees = $('.patrfee').text();
									
									if(fees == "0" || fees == "0.00" || fees == "0.0000"){
									
											var futureMonth = moment(today).add('1', 'Y');
											$('#GL14EXPDATE2').val(moment(futureMonth).format("DD/MM/YYYY"));
											$('#GL14EXPDATE2').datepicker("setDate", moment(futureMonth).format("DD/MM/YYYY"));
									}else{
											$('#GL14EXPDATE2').val(moment(today).format("DD/MM/YYYY"));
											$('#GL14EXPDATE2').datepicker("setDate", moment(today).format("DD/MM/YYYY"));
									}
										

								});
								
								
								$.get('ServeletGetFndPatronCategoryRenewfee', {
						        	cate : value['CATE'],
								 	}, function(responseJson) {
									
									var fees = parseFloat(responseJson);
									fees = fees.toFixed(2);
									
							

									$('.patrfee').text(fees);
									
									if(fees == "0" || fees == "0.00" || fees == "0.0000"){
										
											var futureMonth = moment(today).add('1', 'Y');
											$('#GL14EXPDATE2').val(moment(futureMonth).format("DD/MM/YYYY"));
											$('#GL14EXPDATE2').datepicker("setDate", moment(futureMonth).format("DD/MM/YYYY"));
									}else{
										
											$('#GL14EXPDATE2').val(moment(today).format("DD/MM/YYYY"));
											$('#GL14EXPDATE2').datepicker("setDate", moment(today).format("DD/MM/YYYY"));
									}
										
										/*if(fees == "0" || fees == "0.00" || fees == "0.0000"){
											$(".lblhide").hide();
										}else{
											$(".lblhide").show();
										}*/

								});
								
								

								$.get('GetFndPatronFileType', {
						        	patrid : value['PATR'],
								 	}, function(responseJson) {
									
										if( responseJson == null){
											$('[href="#8"]').closest('li').hide();
										}else{
											$(".filenamedesc").text(responseJson);
											$('[href="#8"]').closest('li').show();
										}
									
										

								});
								
							
								//$('.picdmge').prop('src', $('.picdmge').prop("src").replace("patrid=", "patrids="+value['PATR']));
								
								///$('.picdmge').attr('src', $('.picdmge').attr("src").replace("?patrid?", value['PATR']));
								////setTimeout(function(){
								//////	$('.picdmge').prop('src', $('.picdmge').prop("src").replace("patrid=", "patrid="+""));
								
								  ////$(".picdmge").attr("src", newPathname+"/resources/image/user_default.png");
								//}, 2000);
								
							});
						}
				});
				

				
	  		break;
		}
	
	});
	
	
	///////
	$('#GL14CATE').on('change', function() {
		var value = $("#GL14CATE").val();
		$.get('ServeletGetFndPatronCategoryRenewfee', {
			cate : value,
		}, function(responseJson) {
			var fees = parseFloat(responseJson);
			$('.patrfee').text(fees.toFixed(2));
			
			var fees = $('.patrfee').text();
	
			if(fees == "0" || fees == "0.00" || fees == "0.0000"){
				alert("sfsfs");
				var futureMonth = moment(today).add('1', 'Y');
				$('#GL14EXPDATE2').val(moment(futureMonth).format("DD/MM/YYYY"));
				$('#GL14EXPDATE2').datepicker("setDate", moment(futureMonth).format("DD/MM/YYYY"));
			}else{
				$('#GL14EXPDATE2').val(moment(today).format("DD/MM/YYYY"));
				$('#GL14EXPDATE2').datepicker("setDate", moment(today).format("DD/MM/YYYY"));
			}
		});
	});
	
	
	////click
	
	$('#approveApplication').click(function(){
		
		 $('#approveApplication').prop('disabled', true);

		if( !$('#GL14GRID').val() ) { 
			messageBox("001", "181","","");
		}else{
			var patrid = $('.GL14PATR').text();
			var grid = $('#GL14GRID').val();
			var cate = $('#GL14CATE').val();
			var fee = $('.patrfee').text();
			var stat = $('#GL14STAT').val();
			var brnc = $('#GL14BRNC').val(); 
			var memdate =  moment($("#GL14MEMDATE").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			var expdate =  moment($("#GL14EXPDATE2").val(), 'DD/MM/YYYY').format("YYYYMMDD");		
			var idlogin = $("#liferayLogin").val();
			var tarikh = moment(today).format("DD/MM/YYYY");
			var patrname = $(".GL14NAME").text();
			
			
			$.post("Handle_UserUpdateOnlineAppProcess", { 
	    	 	patrid : patrid, grid : grid, cate : cate,  fee : fee, stat : stat,
						brnc : brnc, memdate : memdate, expdate : expdate,
						idlogin : idlogin
			 }, 
			 	function(data){
				//alert("data"+data)
				
				
					data = data.trim();																
					var getresultSucess = data.split('=');
	
				 	if(getresultSucess[0] == "Success"){
				 		
				 		if(fee == "0" || fee == "0.00" || fee == "0.0000"){
							
							
							
							$.post("SetMailOutputDaftarAhliReject",{idsmstempt : "M012",
									patrname :patrname,
									patrid :patrid,
									idlogin : idlogin, 
									tarikh : tarikh,
									expdate : expdate,
									reason : ""},
									
								function(data){
									if(data.trim() == "Success"){
										messageBox("001", "005","Register","@action");
										$("#closeModalAdd").click();
										$('#Reterive').click();
										$('#approveApplication').prop('disabled', false);
										///alert(data+"notification sudah di hantar");
										////messageBox("002","007","","");
										
									}
								}
								).fail(function(data){
									swal(data+"error");
									
								}).success(function(data){   
									swal(data+"success");
							 });
						}else{
						//////////////////////////////////belum lagi
							$.post("SetMailOutputDaftarAhliPay",{idsmstempt : "M013",
									patrid : patrid,
									idlogin : idlogin, 
									tarikh : tarikh,
									patrname :patrname,
									expdate : expdate,
									fee : fee,
									cate : cate,
									billno : getresultSucess[1]},
								function(data){
									if(data.trim() == "Success"){
										messageBox("001", "005","Register","@action");
										$("#closeModalAdd").click();
										$('#Reterive').click();
										$('#approveApplication').prop('disabled', false);
										///alert(data+"notification sudah di hantar");
										////messageBox("002","007","","");
										
									}
								}
								).fail(function(data){
									swal(data+"error");
									
								}).success(function(data){   
									swal(data+"success");
							 });	
						}
				 	}
	
				
			
			 	}).fail(function(data){
		    	 swal("fail");
		     	}).success(function(data){
			
		 	 });
		}
			

	
	});
	
	
	function messageBox2(langcode, code, criteria, label){
			$.get("Global?type=Handler&name=Error_Page",{
				GL79LANGCODE : langcode,
				GL79ERRCODE : code,
				criteria : criteria,
			 	label : label},function(result){
			 		//alert(result);
					//swal('',result, 'info' );
					swal(result);
			});
			
			
	} 
	
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};

	///// reject button
	$('#rejectApplication').click(function(){
		///alert("reject");
		 $('#rejectApplication').prop('disabled', true);
		var patrid = $('.GL14PATR').text();
		var idlogin = $("#liferayLogin").val();
		
		var tarikh = moment(today).format("DD/MM/YYYY");
		var patrname = $(".GL14NAME").text();
		
		
		
		
		swal({
		  title: "Please state reason",
		  ///text: "Press CANCEL, please!",
		  type: "input",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "CONFIRM",
		  cancelButtonText: "CANCEL",
		  closeOnConfirm: true,
		  closeOnCancel: true
		},
		function(inputValue){
		  //Use the "Strict Equality Comparison" to accept the user's input "false" as string)
		  if (inputValue===false) {

		  } else {
			///swal("Oh no...","press CANCEL please!");
		    ///console.log("The user says: ", inputValue);
		    	$(".cancel").prop('disabled', true);
		    	$(".confirm").prop('disabled', true);
		    	$.post("Handle_UserRejectOnlineAppProcess", { 
	    	 	patrid : patrid, 
				idlogin : idlogin,
				reason : inputValue,
			 }, 
			 	function(data){
				//alert("data"+data)
	
				 	if(data.trim() == "Success"){
				 		
						
						
						$.post("SetMailOutputDaftarAhliReject",{idsmstempt : "M014",
								patrid : patrid,
								idlogin : idlogin, 
								tarikh : tarikh,
								patrname :patrname, 
								expdate : "",
								reason : inputValue,},
							function(data){
								
								if(data.trim() == "Success"){
									messageBox("001", "005","Reject","@action");
									$("#closeModalAdd").click();
									$('#Reterive').click();
									$('#rejectApplication').prop('disabled', false);
									///alert(data+"notification sudah di hantar");
									////messageBox("002","007","","");
									$(".confirm").prop('disabled', false);
									
								}
								
				
							}
							).fail(function(data){
								swal(data+"error");
								
							}).success(function(data){   
								swal(data+"success");
						 });
				 	}
	
				
			
			 	}).fail(function(data){
		    	 swal("fail");
		     	}).success(function(data){
			
		 	 });
		    
		    
		  }
		});
		
		
		
				
		
	
		
		/**/
	});



		

	
			
	
});