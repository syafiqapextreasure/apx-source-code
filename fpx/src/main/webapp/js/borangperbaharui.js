$(document).ready(function(){
	
	$(".toremove").remove();
	var today = new Date();  

	$(".div1patrid").removeClass( "col-sm-3 col-md-3" ).addClass( "col-sm-2 col-md-2" );
	
	$("#renewMembershipBtn").prop("disabled", true);
	
	
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
	
	function messageBox3(langcode, code, criteria, label,billno){
			$.get("Global?type=Handler&name=Error_Page",{
				GL79LANGCODE : langcode,
				GL79ERRCODE : code,
				criteria : criteria,
			 	label : label},function(result){
			 		//alert(result);
					//swal('',result, 'info' );
					///swal(result);
					swal({
							  //title: 'Are you sure?',
							  text: result,
							  ///position: 'top-end',
							  //type: 'warning',
							  showCancelButton: true,
							  confirmButtonColor: '#3085d6',
							  cancelButtonColor: '#d33',
							  confirmButtonText: 'Ya',
		            		  cancelButtonText: 'Tidak',
		            		  confirmButtonClass: 'confirm-class',
		            		  cancelButtonClass: 'cancel-class',
		            		  closeOnConfirm: false,
		            		  closeOnCancel: false 
							}).then(function(isConfirm){
								
								if(isConfirm){
									//alert("testing here");
									///window.location.replace(billno);
									//window.location.href = billno;
									window.open(billno, '_blank');
									$("#renewMembershipBtn").hide();
								}
								
							   // function when confirm button clicked
							}, function(dismiss){
							   if(dismiss == 'cancel'){
									//reload();
									//$('#formdataregister').trigger("reset");
									//$('#formdataregister').data('bootstrapValidator').resetForm();
									//setup();
									$("#renewMembershipBtn").hide();
							   }
							})
						});
			
			
	}
	
	//$(".divexpdate").hide(); 
	
	var userID = $("#liferayLogin").val();
	$("#lblPatronID").val(userID);
	reload()

	function clearOrEmpty(){
		
		$(".lblName").empty();
		$("#txtAddress").empty();
		$(".lblDateEnrolled").empty();
		$(".lblExpiryDate").empty();
		$(".lblContactNo").empty();
		$(".lblIPAddress").empty();

		$(".lblItemsChargedTD").empty();
		$(".lblLastChargeDate").empty();
		$(".lblLateReturnsTD").empty();
		$(".lblLastReturnDate").empty();
		$(".lblNumLostItems").empty();
		$(".lblLastRenewDate").empty();
		$(".lblNumSuspension").empty();
							

		$(".lblFinesOutstanding").empty();
		$(".lblItemsChargedYTD").empty();
		$(".lblFinesPaid").empty();
		$(".lblLateReturnsYTD").empty();					

		$(".lblGroupID").empty();
		$(".lblDepartment").empty();
		$(".lblStatus").empty();
		$(".lblCategory").empty();
		$(".lblDOB").empty();
		$(".lblGender").empty();

	}
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	function reload(){
	$(".status-rectangle").hide();
	
	setTimeout(function() { 
		var PatronID = $("#lblPatronID").val();

		
        $.get('GetRenewalMembershipData', {
	        	id : PatronID
			 	}, function(responseJson) {

				if(responseJson != null){
					if(responseJson==''){

						//messageBox("001","036","",""); 
						messageBox("002","010","",""); 
						

						$("#lblPatronID").val("");
						setTimeout(function(){
					        //$('#lblPatronID')[0].focus();
							$("#renewMembershipBtn").prop("disabled", true);
					    }, 3000);
						clearOrEmpty();
					}else{
						$.each(responseJson, function(key,value) {
					
							
							if(value['CodeStatus'] != '01'){
								
							
								messageBox("002","029","",""); 
									//messageBox("002","022","","");
									$("#renewMembershipBtn").hide();
								/*if(value['CodeStatus']  == '16'){
									messageBox("002","030","",""); 
									$(".status-rectangle").show();
									$("#renewMembershipBtn").hide();
									
									
									
									--$.get('ResultGlflag2', {
										id : "RENEWALMONTH",
										}, function(responseJson) {
																	
										 $(".status-link").attr("href", newURL);
								
									});--
									
									
								}else{
									messageBox("002","029","",""); 
									//messageBox("002","022","","");
									$("#renewMembershipBtn").hide();
								}*/
								
							}else{
									if(value['PatronID'] == ""){
									var pathArray = window.location.pathname.split('/');
									 var newPathname = "";
									 newPathname += "/";
									 newPathname += pathArray[1];
									 $("#imgtest3").attr("src", newPathname+"/resources/image/user_default.png");
									 
									 clearOrEmpty();
								}else{
									$("#renewMembershipBtn").show();
									$("#renewMembershipBtn").prop("disabled", false);
							
									$(".lblName").text(value['Name']);
									$("#txtAddress").text(value['Address']);
									$(".lblDateEnrolled").text(value['DateEnrolled']);
									$(".lblExpiryDate").text(value['ExpiryDate']);
									$(".lblContactNo").text(value['ContactNo']);
									$(".lblIPAddress").text(value['Email']);
	
									$(".lblItemsChargedTD").text(value['ItemsChargedTD']);
									$(".lblLastChargeDate").text(value['LastChargeDate']);
									$(".lblLateReturnsTD").text(value['LateReturnsTD']);
									$(".lblLastReturnDate").text(value['LastReturnDate']);
									$(".lblNumLostItems").text(value['LostItems']);
									$(".lblLastRenewDate").text(value['LastRenewDate']);
									$(".lblNumSuspension").text(value['Suspension']);
								
	
									$(".lblFinesOutstanding").text("RM " +value['outstanding'].toFixed(2));
									$(".lblItemsChargedYTD").text(value['ItemsChargedYTD']);
									$(".lblFinesPaid").text("RM " +value['FinesPaid']);
									$(".lblLateReturnsYTD").text(value['LateReturnsYTD']);
	
	
								
	
									$(".lblGroupID").text(value['GroupID']);
									$(".lblDepartment").text(value['Department']);
									$(".lblStatus").text(value['Status']);
									$(".lblCategory").text(value['Category']);
									$(".lblDOB").text(value['DOB']);
									$(".lblGender").text(value['Gender']);
								}
							}


							
							
					
						});
						
						
					}
				}
			});
			
			///Display Pic
			var a= "PatronReviewPhoto?patrid="+PatronID;
			$.get(a,function(data){
				 if(data!=null && data!=""){
					 $("#imgtest3").attr("src", a);
				 }else{
					 var pathArray = window.location.pathname.split('/');
					 var newPathname = "";
					 newPathname += "/";
					 newPathname += pathArray[1];
					 $("#imgtest3").attr("src", newPathname+"/resources/image/user_default.png");
				 }
			});
			
    }, 500);
    }
	
	$("#lblPatronID").on("blur",function (e) {
		
		e.preventDefault();	
		
		var PatronID = $("#lblPatronID").val();
		
		/*var code = e.keyCode || e.which;
		if(code == '9' ||code == '13' ){*/
			
			$.get('GetRenewalMembershipData', {
	        	id : PatronID
			 	}, function(responseJson) {

				if(responseJson != null){
					if(responseJson==''){

						messageBox("001","036","",""); 

						$("#lblPatronID").val("");
						setTimeout(function(){
					        //$('#lblPatronID')[0].focus();
							$("#renewMembershipBtn").prop("disabled", true);
					    }, 3000);
						clearOrEmpty();
					}else{
						$.each(responseJson, function(key,value) {
							
							if(value['CodeStatus'] != '01'){
								
								messageBox("001","180","",""); 
								
							}else{
									if(value['PatronID'] == ""){
									var pathArray = window.location.pathname.split('/');
									 var newPathname = "";
									 newPathname += "/";
									 newPathname += pathArray[1];
									 $("#imgtest3").attr("src", newPathname+"/resources/image/user_default.png");
									 
									 clearOrEmpty();
								}else{
									
									$("#renewMembershipBtn").prop("disabled", false);
							
									$(".lblName").text(value['Name']);
									$("#txtAddress").text(value['Address']);
									$(".lblDateEnrolled").text(value['DateEnrolled']);
									$(".lblExpiryDate").text(value['ExpiryDate']);
									$(".lblContactNo").text(value['ContactNo']);
									$(".lblIPAddress").text(value['Email']);
	
									$(".lblItemsChargedTD").text(value['ItemsChargedTD']);
									$(".lblLastChargeDate").text(value['LastChargeDate']);
									$(".lblLateReturnsTD").text(value['LateReturnsTD']);
									$(".lblLastReturnDate").text(value['LastReturnDate']);
									$(".lblNumLostItems").text(value['LostItems']);
									$(".lblLastRenewDate").text(value['LastRenewDate']);
									$(".lblNumSuspension").text(value['Suspension']);
								
	
									$(".lblFinesOutstanding").text("RM " +value['outstanding'].toFixed(2));
									$(".lblItemsChargedYTD").text(value['ItemsChargedYTD']);
									$(".lblFinesPaid").text("RM " +value['FinesPaid']);
									$(".lblLateReturnsYTD").text(value['LateReturnsYTD']);
	
	
								
	
									$(".lblGroupID").text(value['GroupID']);
									$(".lblDepartment").text(value['Department']);
									$(".lblStatus").text(value['Status']);
									$(".lblCategory").text(value['Category']);
									$(".lblDOB").text(value['DOB']);
									$(".lblGender").text(value['Gender']);
								}
							}


							
							
					
						});
						
						
					}
				}
			});
			
			///Display Pic
			var a= "PatronReviewPhoto?patrid="+PatronID;
			$.get(a,function(data){
				 if(data!=null && data!=""){
					 $("#imgtest3").attr("src", a);
				 }else{
					 var pathArray = window.location.pathname.split('/');
					 var newPathname = "";
					 newPathname += "/";
					 newPathname += pathArray[1];
					 $("#imgtest3").attr("src", newPathname+"/resources/image/user_default.png");
				 }
			});
			
			     
			
			
		/*}else if(code == '8'){
			 var pathArray = window.location.pathname.split('/');
			 var newPathname = "";
			 newPathname += "/";
			 newPathname += pathArray[1];
			 $("#imgtest3").attr("src", newPathname+"/resources/image/user_default.png");
			 
			 clearOrEmpty();
			//$('#review').dataTable().fnClearTable();	
		}*/
	});
	
	
	
	
	//clear paron id keydown backspace
	$("#lblPatronID").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#renewMembershipBtn").prop("disabled", true);
			 var pathArray = window.location.pathname.split('/');
			 var newPathname = "";
			 newPathname += "/";
			 newPathname += pathArray[1];
			 $("#imgtest3").attr("src", newPathname+"/resources/image/user_default.png");

			clearOrEmpty();
		}
		
		if(code == 13) {
		    e.preventDefault();
		    return false;
		 }
	});


	//////////////////
	$.get('ResultGlflag2', {
		id : "RENEWALMONTH",
		}, function(responseJson) {
									
		$(".renewmonth").val(responseJson);

	});
	
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Renew Membership Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#renewMembershipBtn').click(function(){

		$("#Renew").prop("disabled", false);
		
		///$(".yeartorenewinput").hide();
		var minrenew = $(".renewmonth").val();
		//alert("minrenew"+minrenew);
		
		
		var startDate = moment(today, 'DD/MM/YYYY');//moment('02/01/2021', 'DD/MM/YYYY');
		///alert("startDate"+startDate);
		
    	var endDate = $(".lblExpiryDate").text();
		//alert("endDate1"+endDate);
		endDate = moment(endDate, 'DD/MM/YYYY');  //moment('02/05/2022', 'DD/MM/YYYY');
		//alert("endDate"+endDate);


	  
	    var monthDiff = endDate.diff(startDate, 'months');

		///alert("monthDiff"+monthDiff);


	    if(monthDiff < minrenew){

			$('#modalrenewmember').on('show.bs.modal', function (event) {

				$.get('GetRenewFee', {
		        	patronid : $("#lblPatronID").val(),
				 	}, function(responseJson) {
						if(responseJson==''){
					 	}else{					 	
							$.each(responseJson, function(key,value) {


								var fee = value['Renwefee'];
								fee =  parseFloat(fee);
								$("#renewfee").val(fee.toFixed(2));

								var year;

								if(fee == "0" || fee == "0.00" || fee == "0.0000"){
									$(".yeartorenew").hide();
									$(".yeartorenewinput").show();
									$("#renewyearinput").val(value['RenweGrc']);
									year = value['RenweGrc'];
									$('#2year').attr("disabled",true);
									$('#3year').attr("disabled",true);
									$(".divexpdate").show(); 
								}else{
									year = $('input[name="renewyear"]:checked').val();
									$(".yeartorenew").show();
									$(".yeartorenewinput").hide();
									$(".renewyearinput").val("0")
									$('#2year').attr("disabled",false);
									$('#3year').attr("disabled",false);
									$(".divexpdate").hide(); 
								}	

								var totalpay = 	parseFloat(0);
								totalpay = 	fee *  	parseFloat(year);	
								

								$("#renewpay").val(totalpay.toFixed(2));	
								
								///var futureMonth = moment(today).add(year, 'Y');
								var futureMonth;
								
	
							    var dDiff = endDate.diff(startDate);
							    ///alert(dDiff+"dDiff");
							    if (dDiff > 0) {
							        //alert('Date is not past');
							        futureMonth = moment(endDate).add(year, 'Y');
							        $('#renewexpdate').val(moment(futureMonth).format("DD/MM/YYYY"));	
							        //alert(moment(futureMonth).format("DD/MM/YYYY")+"futureMonth not pass");
							    }else{
							        //alert('Date is past');
							        futureMonth = moment(today).add(year, 'Y');
							        $('#renewexpdate').val(moment(futureMonth).format("DD/MM/YYYY"));	
							        //alert(moment(futureMonth).format("DD/MM/YYYY")+"futureMonth pass");
							    }
								
								
								$('#renewexpdate').val(moment(futureMonth).format("DD/MM/YYYY"));	


								
							});
						}
			});

			});
	    }else{
			$('#modalrenewmember').modal('toggle');
	    	//messageBox("001","179", minrenew, "@month");
	    	messageBox("002","023", minrenew, "@month");
	    }
		
		///var futureMonth = moment(today).add(2, 'M');
		
	});
	
	
	/////
	$('input[name=renewyear]').change(function(){

		var value = $( 'input[name=renewyear]:checked' ).val();

		var totalpay = 	parseFloat(0);
								

		/*if (value == '1') {
	        alert("1");
	    }
	    else if (value == '2') {
	        alert("2");
	    }else if (value == '3') {
	        alert("3");
	    }*/

	    totalpay = 	parseFloat($("#renewfee").val()) *  parseFloat(value);	

		$("#renewpay").val(totalpay.toFixed(2));
		
		var futureMonth = moment(today).add(value, 'Y');
		$('#renewexpdate').val(moment(futureMonth).format("DD/MM/YYYY"));	

	});



	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn renewMembershipBtn Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Renew').click(function(){

		$("#Renew").prop("disabled", true);
		var patronId = $("#lblPatronID").val();
		var expDate = moment($(".lblExpiryDate").text(), 'DD/MM/YYYY').format("YYYYMMDD");///$(".lblExpiryDate").text();
		var newExpDate = moment($("#renewexpdate").val(), 'DD/MM/YYYY').format("YYYYMMDD");///$("#renewexpdate").val();
		var recordedBy = $("#liferayLogin").val();
		var fee = $("#renewpay").val();
		var year = $('input[name="renewyear"]:checked').val();
		
		//alert(newExpDate)

		
		$.post("Handle_RenewalMembership", 
					{patronId : patronId, expDate : expDate,  
					newExpDate : newExpDate, recordedBy : recordedBy,
					fee : fee, year : year}, function(result){
	
				/////alert(result.trim()+"result.trim()")
				var resultsplit = result.trim().split(',');
				/////alert(resultsplit[1]+"resultsplit0")
				///alert(resultsplit[2]+"resultsplit1")
				
				if(result.trim() != "fail"){
					
					
					if(fee == "0" || fee == "0.00" || fee == "0.0000"){
						
						$.post("SetMailOutputPerbaharuiKeahlian",{idsmstempt : "M015", 
							id : patronId,  tarikh : moment(today).format("DD/MM/YYYY"), officerid : recordedBy,
							expDate : expDate, newExpDate : newExpDate, fee : fee, billno : resultsplit[1]},
							function(data){
								
								
								if(data.trim() == "Success"){
									$("#renewMembershipBtn").prop("disabled", true);
									//messageBox("002","005","Renew","@action");
									///messageBox("001", "005","Renew","@action");
									messageBox("002", "032","","");
									$("#closeModalAdd").click();	
									clearOrEmpty();
									//$('#lblPatronID').get(0).focus();
									$('#lblPatronID').val("");
								}else{
									swal("something wrong");
								}
								
								
								
														
							}).fail(function(data){
								swal(data+"error");
																		
							}).success(function(data){   
								swal(data+"success");
						});
						
					}else{
						
						var patraddress = $("#txtAddress").text();
									
						
						
						$.post("SetMailOutputPerbaharuiKeahlian",{idsmstempt : "M017", 
							id : patronId,  tarikh : moment(today).format("DD/MM/YYYY"), officerid : recordedBy,
							expDate : expDate, newExpDate : newExpDate, fee : fee,
							 patraddress:patraddress, billno : resultsplit[1]},
							function(data){
								
								
								if(data.trim() == "Success"){
									//messageBox("002","005","Renew","@action");
									////messageBox("001", "005","Renew","@action");
									messageBox3("002", "019","","", resultsplit[2]);
									$("#closeModalAdd").click();	
									clearOrEmpty();
									//$('#lblPatronID').get(0).focus();
									$('#lblPatronID').val("");
								}else{
									swal("something wrong");
								}
								
								
								
														
							}).fail(function(data){
								swal(data+"error");
																		
							}).success(function(data){   
								swal(data+"success");
						});
						
					}
		


				}
  		});
		
		
		

		
		


		/*$.post("Handle_RenewalMembership", 
					{patronId : patronId, expDate : expDate,  
					newExpDate : newExpDate, recordedBy : recordedBy,
					fee : fee}, function(result){

				if(result.trim() =="Success"){*/
					
					
					/*$.post("SetMailOutputPerbaharuiKeahlian",{idsmstempt : "M006", 
						id : patronId,  tarikh : moment(today).format("DD/MM/YYYY"), officerid : recordedBy,
						expDate : expDate, newExpDate : newExpDate, fee : fee},
						function(data){
							
							
							if(data.trim() == "Success"){
								//messageBox("002","005","Renew","@action");
								messageBox("001", "005","Renew","@action");
								$("#closeModalAdd").click();	
								clearOrEmpty();
								$('#lblPatronID').get(0).focus();
							}else{
								swal("something wrong");
							}
							
							
							
													
						}).fail(function(data){
							swal(data+"error");
																	
						}).success(function(data){   
							swal(data+"success");
					});*/



				//}
  		//});

	});
	
	
	
	
	
	
	

});