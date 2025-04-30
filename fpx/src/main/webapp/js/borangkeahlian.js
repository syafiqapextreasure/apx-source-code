$(document).ready(function(){
	
	$(".toremove").remove();
	
	function messageBox(langcode, code, criteria, label){
			$.get("Global?type=Handler&name=Error_Page",{
				GL79LANGCODE : langcode,
				GL79ERRCODE : code,
				criteria : criteria,
			 	label : label},function(result){
			 		//alert(result);
					//swal('',result, 'info' );
					///swal(result);
					/*swal({
					  text: result,
					  position: 'top-end',
					});*/
					swal({
			            text: result,
			            position: 'top', // Set the position to top
			        });
			});
			
			
	} 
	
	
	function messageBox2(langcode, code, criteria, label){
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
							 //// position: 'top-end',
							  text: result,
							  //type: 'warning',
							  showCancelButton: true,
							  confirmButtonColor: '#3085d6',
							  cancelButtonColor: '#d33',
							  confirmButtonText: 'Ya',
		            		  cancelButtonText: 'Tidak',
		            		  confirmButtonClass: 'confirm-class',
		            		  cancelButtonClass: 'cancel-class',
		            		  closeOnConfirm: false,
		            		  closeOnCancel: false ,
							  
							}).then(function(isConfirm){
								
								if(isConfirm){
									if(code == "009"){
										 window.location.replace("https://user.ppj.gov.my/auth/register");
									}else if(code == "002"){
										 window.location.replace("https://cassso.ppj.gov.my/cas/login?service=https%3A%2F%2Fwww.ppj.gov.my%2Fauth%2Fcas%2Flogin");
									}
								}
								
							   // function when confirm button clicked
							}, function(dismiss){
							   if(dismiss == 'cancel'){
									$('#formdataregister').trigger("reset");
									$('#formdataregister').data('bootstrapValidator').resetForm();
									setup();
							   }
							})
						});
			
			
	}
	
	
	var today = new Date(); 
	var token = $("#tokenval").val();
	
	
	var userID = $("#liferayLogin").val();

	

    
    setTimeout(function(){
		var userEmail = $("#liferayLoginEmail").val();
		$("#emailAhli").val(userEmail);
		
		 var settings = {
		  "url": token+"&ref="+userEmail,
		  "method": "GET",
		  "timeout": 0,
		  "headers": {
			"Cookie": "ppj_api_centre_session=2MpGfbzx4byDCPQDYVoWoXWhFKVNfqiFliPyLqgA"
		  },
		};
		
		$.ajax(settings).done(function (response) {

			
			$.each(response, function(key,value) {
				
				
				//alert('The value at arr[' + key + '] is: ' + value);
				
				 if(key == "status"){
				
					if(value == 0){
						messageBox2("002","009","","");
				        setup();
					}
					
				  }else if((key == "user")){

					 //////want to check if user aleready register at wilmu or not
					 $.get( "CheckPatronAvailaleWithEmail", { id : value['reference_id'],
					 								email : value['email']
					 	} )
					  .done(function( data ) {
						
						if(data == true){
					    
					    	$.get( "GetPatronStatus", { id : value['reference_id'] } )
							 .done(function( data ) {
							    //alert( "Data Loaded: " + data );
							    if(data == "17"){
									messageBox("002","015","","");
									$('#Register').prop('disabled', true);
								}else if(data == "15"){
									messageBox("002","016","","");
									$('#Register').prop('disabled', true);
								}else if(data == "14"){
									messageBox("002","017","","");
									$('#Register').prop('disabled', true);
								}else{
									messageBox("002","002",value['reference_id'],"@id");
									$('#Register').prop('disabled', true);
								}
								
								$('#formdataregister').trigger("reset");
								setup();
								
							});
							
							
							
					    }else{
							 if(value['reference_id'] == ""){
								$("#emailAhli").val("");
								messageBox("002","018","","");
								$('#formdataregister').data('bootstrapValidator').resetForm();
								$('#formdataregister input[type="text"]').val('');
								setup();
							}else{
								
								var kplength = value['reference_id'].length;
								var warganegara = value['nationality'];
								
								$("#namaAhli").val(value['name']);
								$("#jantina").val(value['gender']);
								
								var newInputDOB = moment(value['birth_date'], 'YYYY-MM-DD').format("DD/MM/YYYY");
									
								///$("#inputDOB").val(newInputDOB);
								$("#alamat").val(value['address1']);
								$("#alamat2").val(value['address2']);
								
								
								var ic = value['reference_id'];
							    var year =  parseInt(ic.substring(0,2));
							    var month =  ic.substring(2,4);
							    var date =  ic.substring(4,6);	
							    if(year > 40){
									year = year+1900;
								}else {
									year = year+2000;
								}
									
								//var fullDate = year + "-" + month + "-" + date;
								var fullDate =  moment(year + "-" + month + "-" + date, 'YYYY-MM-DD').format("DD/MM/YYYY");
								$("#inputDOB").val(fullDate);
								
								var add3 = value['address3'];
								
								if(add3 = "null"){
									add3 = "";
								}
								
								$("#alamat3").val(add3 +value['city']);
								
									
								var getWarganegara = value['fk_lkp_country'];
									
								if(getWarganegara == "132"){
									$('input[name=warganegaraRadio][value=Y]').prop('checked', 'checked');
								}else{
									$('input[name=warganegaraRadio][value=N]').prop('checked', 'checked');
								}
		
								
								$("#ic").val(value['reference_id']);
		
								var state = value['fk_lkp_state'];
								
								/////////var state = value['fk_lkp_state'];
						
								if(state == "1"){
									state = "JOHOR";
								}else if(state == "2"){
									state = "KEDAH";
								}else if(state == "3"){
									state = "KELANTAN";
								}else if(state == "4"){
									state = "MELAKA";
								}else if(state == "5"){
									state = "NEGERI SEMBILAN";
								}else if(state == "6"){
									state = "PAHANG";
								}else if(state == "7"){
									state = "PERAK";
								}else if(state == "8"){
									state = "PERLIS";
								}else if(state == "9"){
									state = "PULAU PINANG";
								}else if(state == "10"){
									state = "SABAH";
								}else if(state == "11"){
									state = "SARAWAK";
								}else if(state == "12"){
									state = "SELANGOR";
								}else if(state == "13"){
									state = "TERENGGANU";
								}else if(state == "14"){
									state = "KUALA LUMPUR";
								}else if(state == "15"){
									state = "LABUAN";
								}else if(state == "16"){
									state = "PUTRAJAYA";
								}
								
								
								$('#negeri option:contains(' + state + ')').attr('selected', 'selected');
									
								
									
								$("#phone").val(value['mobile_no']);
									
								$("#poskod").val(value['postcode']);
									
								///age = calculateAge(parseDate(newInputDOB), new Date());
								age = calculateAge(parseDate(fullDate), new Date());
								
								
								
								///alert(age+"cc");
								
									
								$("#umur").val(age);
						
								///alert(age)
								if(age <= 12 ){
									
									if(age <= 4){
										//alert("bwh 5 tahun tak di benarkan berdafatar");
										messageBox("002","024","","");
										$('#formdataregister').trigger("reset");
										setup();
									}else{
										//alert("Pengguna dibawah umur sila masukkan ibu bapa/penjaga");
										messageBox("002","001","","");
										
										///alert($("#parentStaffNo").is(":checked"));
										
										if ($("#parentStaffNo").is(":checked")) {
										   // do something
										    ///alert("is checked");
										   	$("#emailParent").prop('disabled', false);
											$(".parentDetail").show();
											$(".parentbukanstaff, .parentstaff").hide();
											$(".labelquesstaffppj").hide();
											$(".labelquestparent").show();
										}else{
											//alert("not checking checked");
											$(".parentbukanstaff, .parentstaff").hide();
											$(".labelquesstaffppj").hide();
											$(".labelquestparent").show();
										}
										
										
										/*$("#emailParent").prop('disabled', false);
										$(".parentDetail").show();
										$(".parentbukanstaff, .parentstaff").hide();
										$(".labelquesstaffppj").hide();
										$(".labelquestparent").show();*/
									}
									
									
								}else{
									
									if ($("#parentStaffNo").is(":checked")) {
										   // do something
										  // alert("is checked");
									}else{
										//alert("not checking checked");
									}

									
									$("#emailParent").prop('disabled', true);
									$(".parentDetail").hide();
									$(".parentbukanstaff, .parentstaff").hide();
									$(".labelquesstaffppj").show();
									$(".labelquestparent").hide();
								}
								
								
								///if email ada ppj
								var oriemail = value['email'];
								
								if(oriemail.includes('@ppj.gov.my')){
									$("input[name=ppjRadio][value=Y]").prop('checked', true);
								}/*else{
									
								}*/
								

						
							}
						}
						
					  });
  

				  }
				
			});
			
			})
	}, 2000);
    
   
  


	
	/////function setupage when reload
	function disbaledallinput(){
		$("#formdataregister :input").prop("disabled", true);
		$('input[name=warganegaraRadio]').attr("disabled",false);
		$(":submit").attr("disabled", true);
	}
	
	
	function setup(){
		$('input[type="text"]').attr('autocomplete', 'off');
		$('input[type="email"]').attr('autocomplete', 'off');
		
		$("input[name=icOrPass][value='ic']").prop("checked",true);
		$("#passport").prop('disabled', true);
		$("#passport, #jantina").hide();
		
		
		$("#branch, #cate, #negeri, #negeriSurat, #race, #negeriSuratParent").prop("selectedIndex",-1); 
		
		$("#emailAhli, #namaAhli, #namaPenjaga, #emailParent").attr('maxlength','50');
		///$("#ic, #idParentStaff, #idParent").attr('maxlength','12');
		$("#ic, #idParent").attr('maxlength','12');
		$("#idParentStaff, #idPsgn").attr('maxlength','50');
		$("#passport").attr('maxlength','10');
		$("#alamat, #alamat2, #alamat3, #alamatSurat, #alamatSurat2, #alamatSurat3, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3").attr('maxlength','80');
		$("#poskod, #poskodSurat, #poskodSuratParent").attr('maxlength','5');
		$("#phone, #phoneParent").attr('maxlength','14');
		
		
		$("#namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent").prop('disabled', true);
		$("#negeriSuratParent, #phoneParent, #emailParent, #idparent").prop('disabled', true);
		
		$("#emailAhli, #namaAhli, #ic, #passport, #inputDOB, #alamat, #alamat2, #alamat3, #poskod, #phone, #warganegara").prop('disabled', true);
		$('input[name=icOrPass], #negeri').attr("disabled",true);
		
		$('#inputtarikh').val(moment(today).format("DD/MM/YYYY"));
		
		//////$(".pickadoku, .picpasspekerja, .getidparentstaff, .parentbukanstaff, .parentstaff, .parentDetail").hide();


		$(".picpasspekerja, .labelquespsgnppj, .picpasspekerjapsgn").hide();
		$(".labelquestparent, .picpasspekerjaparent, .getidparentstaff, .labelquestoku, .pickadoku, .getidpsgn").hide();
		$(".parentbukanstaff, .parentstaff, .parentDetail").hide();

	
		/*$("input[name=okuRadio][value='N']").prop('checked', true);
		$("input[name=ppjRadio][value='N']").prop('checked', true);
		$("input[name=parentStaffRadio][value='N']").prop('checked', true);*/
		
		////$(".labelquestparent").hide();
		
		$('input[name=warganegaraRadio]').attr("disabled",false);
	}
	
	
	setup();
	

		
	///radio button for warganegara
	$('input[name=warganegaraRadio]').change(function(){

		var value = $( 'input[name=warganegaraRadio]:checked' ).val();

		if(value=='Y'){
			$("#formdataregister :input").prop("disabled", false);
			$(":submit").removeAttr("disabled");
			setup();
			//$('#formdataregister').data('bootstrapValidator').resetForm();
			//$("#Register").prop("disabled",false);
			//$(":submit").removeAttr("disabled");
		}else if(value=='N'){		
			disbaledallinput();
			$('#formdataregister').data('bootstrapValidator').resetForm();
			//$(":submit").attr("disabled", true);
		}
	});
	
	///datepicker
	$('#inputDOB').datepicker({
		dateFormat : "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
		endDate: new Date(),
		 }).on('changeDate', function (selected) {
                    var minDate = new Date(selected.date);
                    minDate.setDate(minDate.getDate() - 1);
   
		/*onSelect: function(value, ui) {
                var today = new Date();
				alert(today);
				alert(today.getFullYear());
                var age = today.getFullYear() - ui.selectedYear;
                //$('#age').val(age);
				alert(age);
            },
            changeMonth: true,
            changeYear: true,
		    maxDate: '+0d',*/
	}).on('hide', function () {
	  if (!this.firstHide) {
	    if (!$(this).is(":focus")) {
	      this.firstHide = true;
	      // this will inadvertently call show (we're trying to hide!)
	      this.focus(); 
	    }
	  } else {
	    this.firstHide = false;
	  }
	}).on('show', function () {
	  if (this.firstHide) {
	    // careful, we have an infinite loop!
	    $(this).datepicker('hide'); 
	  }
	});
	
	
	var age
	/////validate range number   #startInput, #endInput
	$(".inputDOB").focusout(function(){

		var iDOB = $(".inputDOB").val();
	    

	    age = calculateAge(parseDate(iDOB), new Date());

		if(age < 12 ){
			$("#namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent").prop('disabled', false);
			$("#negeriSuratParent, #phoneParent, #emailParent, #idparent").prop('disabled', false);
		}else{
			$("#namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent").prop('disabled', true);
			$("#negeriSuratParent, #phoneParent, #emailParent, #idparent").prop('disabled', true);
		}



	    /*var difference=Date.now() - birthDate.getTime(); 
	 	 
		 var  ageDate = new Date(difference); 
		 calculatedAge =   Math.abs(ageDate.getUTCFullYear() - 1970);
		 alert(calculatedAge);*/
	    
	});
	
	
	//convert the date string in the format of dd/mm/yyyy into a JS date object
	function parseDate(dateStr) {
		  var dateParts = dateStr.split("/");
		  return new Date(dateParts[2], (dateParts[1] - 1), dateParts[0]);
	}

	function calculateAge (dateOfBirth, dateToCalculate) {


	    var calculateYear = dateToCalculate.getFullYear();
	    var calculateMonth = dateToCalculate.getMonth();
	    var calculateDay = dateToCalculate.getDate();

	    var birthYear = dateOfBirth.getFullYear();
	    var birthMonth = dateOfBirth.getMonth();
	    var birthDay = dateOfBirth.getDate();

	    var age = calculateYear - birthYear;
	    var ageMonth = calculateMonth - birthMonth;
	    var ageDay = calculateDay - birthDay;

	    /*if (ageMonth < 0 || (ageMonth == 0 && ageDay < 0)) {
	        age = parseInt(age) - 1;
	    }*/
	    return age;
	}
	
	
	$("#ict").keypress(function (e) {
	        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	            return false;
	        }
			var curchr = this.value.length;
	        var curval = $(this).val();
	        if (curchr == 6) {
	            $(this).val(curval + "-");
	        }else if (curchr == 9) {
	        	$(this).val(curval + "-");
	        }
	        $(this).attr('maxlength', '14');
	});
	
	///input number only
	$("#poskod, #poskodSurat, #poskodSuratParent, #idparent").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });

	//phone
	$("#phone, #phoneParent").keypress(function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
		  var curchr = this.value.length;
        var curval = $(this).val();
        if (curchr == 3) {
            $(this).val(curval + "-");
        }
        $(this).attr('maxlength', '13');
	});
	
		$('input[type=radio][name=icOrPass]').on('change', function() {

		$("#idtypeVal").val('');
		switch ($(this).val()) {
			case 'ic':
           		$("#passport").hide();
				$("#ic").show();
				$("#passport").prop('disabled', true);
				$("#ic").prop('disabled', false);	
           	break;
			case 'passport':
				$("#ic").hide();
           		$("#passport").show();
				$("#passport").prop('disabled', false);	
				$("#ic").prop('disabled', true);	
           	break;
		}
	});
	
	

	
	
	////on blur emailAhli
	$("#emailAhli").blur(function(e){
		
		e.preventDefault();
		e.stopImmediatePropagation();
		
		var email = $("#emailAhli").val();
		
		var settings = {
		  "url": token+"&ref="+email,
		  "method": "GET",
		  "timeout": 0,
		  "headers": {
			"Cookie": "ppj_api_centre_session=2MpGfbzx4byDCPQDYVoWoXWhFKVNfqiFliPyLqgA"
		  },
		};
		
		$.ajax(settings).done(function (response) {

			
			$.each(response, function(key,value) {
				
				
				//alert('The value at arr[' + key + '] is: ' + value);
				
				 if(key == "status"){
				
					if(value == 0){
						messageBox2("002","009","","");
				        setup();
					}
					
				  }else if((key == "user")){

					 //////want to check if user aleready register at wilmu or not
					 $.get( "CheckPatronAvailaleWithEmail", { id : value['reference_id'],
					 								email : value['email']
					 	} )
					  .done(function( data ) {
						
						if(data == true){
					    
					    	$.get( "GetPatronStatus", { id : value['reference_id'] } )
							 .done(function( data ) {
							    //alert( "Data Loaded: " + data );
							    if(data == "17"){
									messageBox("002","015","","");
								}else if(data == "15"){
									messageBox("002","016","","");
								}else if(data == "14"){
									messageBox("002","017","","");
								}else{
									messageBox2("002","002",value['reference_id'],"@id");
									$('#Register').prop('disabled', true);
								}
								
								$('#formdataregister').trigger("reset");
								setup();
								
							});
							
							
							
					    }else{
							 if(value['reference_id'] == ""){
								$("#emailAhli").val("");
								messageBox("002","018","","");
								$('#formdataregister').data('bootstrapValidator').resetForm();
								$('#formdataregister input[type="text"]').val('');
								setup();
							}else{
								
								var kplength = value['reference_id'].length;
								var warganegara = value['nationality'];
								
								$("#namaAhli").val(value['name']);
								$("#jantina").val(value['gender']);
								
								var newInputDOB = moment(value['birth_date'], 'YYYY-MM-DD').format("DD/MM/YYYY");
									
								//$("#inputDOB").val(newInputDOB);
								$("#alamat").val(value['address1']);
								$("#alamat2").val(value['address2']);
								
								
								var ic = value['reference_id'];
							    var year =  parseInt(ic.substring(0,2));
							    var month =  ic.substring(2,4);
							    var date =  ic.substring(4,6);	
							    if(year > 40){
									year = year+1900;
								}else {
									year = year+2000;
								}
									
								//var fullDate = year + "-" + month + "-" + date;
								var fullDate =  moment(year + "-" + month + "-" + date, 'YYYY-MM-DD').format("DD/MM/YYYY");
								$("#inputDOB").val(fullDate);
								
								var add3 = value['address3'];
								
								if(add3 = "null"){
									add3 = "";
								}
								
								$("#alamat3").val(add3 +value['city']);
								
									
								var getWarganegara = value['fk_lkp_country'];
									
								if(getWarganegara == "132"){
									$('input[name=warganegaraRadio][value=Y]').prop('checked', 'checked');
								}else{
									$('input[name=warganegaraRadio][value=N]').prop('checked', 'checked');
								}
		
								
								$("#ic").val(value['reference_id']);
		
								var state = value['fk_lkp_state'];
								
								var state = value['fk_lkp_state'];
						
								if(state == "1"){
									state = "JOHOR";
								}else if(state == "2"){
									state = "KEDAH";
								}else if(state == "3"){
									state = "KELANTAN";
								}else if(state == "4"){
									state = "MELAKA";
								}else if(state == "5"){
									state = "NEGERI SEMBILAN";
								}else if(state == "6"){
									state = "PAHANG";
								}else if(state == "7"){
									state = "PERAK";
								}else if(state == "8"){
									state = "PERLIS";
								}else if(state == "9"){
									state = "PULAU PINANG";
								}else if(state == "10"){
									state = "SABAH";
								}else if(state == "11"){
									state = "SARAWAK";
								}else if(state == "12"){
									state = "SELANGOR";
								}else if(state == "13"){
									state = "TERENGGANU";
								}else if(state == "14"){
									state = "KUALA LUMPUR";
								}else if(state == "15"){
									state = "LABUAN";
								}else if(state == "16"){
									state = "PUTRAJAYA";
								}
								
								
								$('#negeri option:contains(' + state + ')').attr('selected', 'selected');
									
								
									
								$("#phone").val(value['mobile_no']);
									
								$("#poskod").val(value['postcode']);
									
								///age = calculateAge(parseDate(newInputDOB), new Date());
								age = calculateAge(parseDate(fullDate), new Date());
								
								
								
								///alert(age+"cc");
								
									
								$("#umur").val(age);
						
								///alert(age)
								if(age < 12 ){
									
									if(age <= 4){
										//alert("bwh 5 tahun tak di benarkan berdafatar");
										messageBox("002","024","","");
										$('#formdataregister').trigger("reset");
										setup();
									}else{
										//alert("Pengguna dibawah umur sila masukkan ibu bapa/penjaga");
										messageBox("002","001","","");
										
										if ($("#parentStaffNo").is(":checked")) {
										   // do something
										   ///alert("is checked");
										   	$("#emailParent").prop('disabled', false);
											$(".parentDetail").show();
											$(".parentbukanstaff, .parentstaff").hide();
											$(".labelquesstaffppj").hide();
											$(".labelquestparent").show();
										}else{
											//alert("not checking checked");
											$(".parentbukanstaff, .parentstaff").hide();
											$(".labelquesstaffppj").hide();
											$(".labelquestparent").show();
										}
										
										
										/*$("#emailParent").prop('disabled', false);
										$(".parentDetail").show();
										$(".parentbukanstaff, .parentstaff").hide();
										$(".labelquesstaffppj").hide();
										$(".labelquestparent").show();*/
									}
									
									
								}else{
									
									if ($("#parentStaffNo").is(":checked")) {
										   // do something
										  // alert("is checked");
									}else{
										//alert("not checking checked");
									}

									
									$("#emailParent").prop('disabled', true);
									$(".parentDetail").hide();
									$(".parentbukanstaff, .parentstaff").hide();
									$(".labelquesstaffppj").show();
									$(".labelquestparent").hide();
								}
								
								
								///if email ada ppj
								var oriemail = value['email'];
								
								if(oriemail.includes('@ppj.gov.my')){
									$("input[name=ppjRadio][value=Y]").prop('checked', true);
								}/*else{
									
								}*/
								

						
							}
						}
						
					  });
  

				  }
				
			});
			
			})
			
		
	});
	
	
	$("#emailAhli").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			//$('#formdataregister').trigger("reset");
			$('#formdataregister').data('bootstrapValidator').resetForm();
			$("#emailParent, #idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent, #negeriSuratParent").val("");
		}
		
		if(code == 13) {
	      e.preventDefault();
	      return false;
	    }
	});
	
	////on blur parent email
	$("#emailParent").blur(function(e){
		
		e.preventDefault();
		e.stopImmediatePropagation();
		
		var email = $("#emailParent").val();
		var emailAhli = $("#emailAhli").val();
		
		if(email == emailAhli){
			$("#emailParent").val("");
			messageBox("002","028","","");
		}else{

		
			var settings = {
			  ///"url": "https://api.ppj.gov.my/api/call/5?fid=52&token=VEcybXJ5OG5tVmNMdzhlY1RJRnJFVVo3eHk1TDE2V05uQzRpRDA2bw==&ref="+email,
			  "url" : token+"&ref="+email,
			  "method": "GET",
			  "timeout": 0,
			  "headers": {
				"Cookie": "ppj_api_centre_session=2MpGfbzx4byDCPQDYVoWoXWhFKVNfqiFliPyLqgA"
			  },
			};
			
			$.ajax(settings).done(function (response) {
				
				$.each(response, function(key,value) {
					
					//alert('The value at arr[' + key + '] is: ' + value);
					
					 if(key == "status"){
					
						if(value == 0){
							//alert("xda data");
							////////////////////////nk add something kat sini
							$('#formdataregister').data('bootstrapValidator').resetForm();
							$("#idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent").val("");
							$("#negeriSuratParent").prop("selectedIndex",-1); 
							$("#emailParent, #idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent, #negeriSuratParent").prop('disabled', false);
							$("#idParent").focus();
							
							$(".labeltextIdparent").html("<b>No KP Ibu/Bapa/Penjaga</b>");
						}
						
					  }else if((key == "user")){
						$("#idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent, #negeriSuratParent").prop('disabled', true);
						
	
						$(".labeltextIdparent").html("<b>Id Ibu/Bapa/Penjaga</b>");
					
						$("#namaPenjaga").val(value['name']);
		
						$("#idParent").val(value['reference_id']);
						$("#alamatSuratParent").val(value['address1']);
						$("#alamatSuratParent2").val(value['address2']);
						$("#alamatSuratParent3").val(value['address3']+ value['city']);
						$("#poskodSuratParent").val(value['postcode']);
						
						$("#phoneParent").val(value['mobile_no']);
						
						var state = value['fk_lkp_state'];
								
								if(state == "1"){
									state = "JOHOR";
								}else if(state == "2"){
									state = "KEDAH";
								}else if(state == "3"){
									state = "KELANTAN";
								}else if(state == "4"){
									state = "MELAKA";
								}else if(state == "5"){
									state = "NEGERI SEMBILAN";
								}else if(state == "6"){
									state = "PAHANG";
								}else if(state == "7"){
									state = "PERAK";
								}else if(state == "8"){
									state = "PERLIS";
								}else if(state == "9"){
									state = "PULAU PINANG";
								}else if(state == "10"){
									state = "SABAH";
								}else if(state == "11"){
									state = "SARAWAK";
								}else if(state == "12"){
									state = "SELANGOR";
								}else if(state == "13"){
									state = "TERENGGANU";
								}else if(state == "14"){
									state = "KUALA LUMPUR";
								}else if(state == "15"){
									state = "LABUAN";
								}else if(state == "16"){
									state = "PUTRAJAYA";
								}
								
						$('#negeriSuratParent option:contains(' + state + ')').attr('selected', 'selected');
					
					  }
				});
			
		});
		
		}
	});
	

	
	$("#emailParent").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent, #negeriSuratParent").val("");
		}
		
		if(code == 13) {
	      e.preventDefault();
	      return false;
	    }
	});
	

	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///on change radio button

	function detailpsgnYes(){
		$(".labelquespsgnppj").hide();
		$("input[name=ppjpsgnRadio][value='N']").prop('checked', true);
		$('input[name=ppjpsgnRadio]').attr("disabled",true);
		//$(".getidpsgn").show();
	}

	function detailpsgnNo(){
		$(".labelquespsgnppj").show();
		$('input[name=ppjpsgnRadio]').prop('checked', false);
		$('input[name=ppjpsgnRadio]').attr("disabled",false);
		//$(".getidpsgn").hide();
		
		
	}

	function detailparentYes(){
		$(".labelquestparent").hide(); 
		$("input[name=parentStaffRadio][value='N']").prop('checked', true);
		$('input[name=parentStaffRadio]').attr("disabled",true);
	}

	function detailparentNo(){
		$(".labelquestparent").show();
		$('input[name=parentStaffRadio]').prop('checked', false);
		$('input[name=parentStaffRadio]').attr("disabled",false);
	}


	function detailOKUYes(){
		$(".labelquestoku").hide();
		$("input[name=okuRadio][value='N']").prop('checked', true);
		$('input[name=okuRadio]').attr("disabled",true);
	}

	function detailOKUNo(){
		$(".labelquestoku").show();
		$('input[name=okuRadio]').prop('checked', false);
		$('input[name=okuRadio]').attr("disabled",false);
	}

	function hidepicpsgnkakitgn(){
		$('#paspekerjapsgn').val('');
		$(".picpasspekerjapsgn").hide();
	}

	function hidepicparentkakitgn(){
		$('#idParentStaff, #paspekerjaparent').val('');
		$(".getidparentstaff, .picpasspekerjaparent").hide();
	}

	function hidepicoku(){
		$('#kadOKU').val('');
		$(".pickadoku").hide();
	}


	///pekeje ppkp
	$('input[name=ppjRadio]').change(function(){
		var value = $( 'input[name=ppjRadio]:checked' ).val();
		
		if(value=='Y'){
			$(".picpasspekerja").show();
			detailpsgnYes();
			detailparentYes();
			detailOKUYes();
			hidepicpsgnkakitgn();
			hidepicparentkakitgn();
			hidepicoku();
			
			
		}else{
			$('#paspekerjakakitgn').val('');
			$(".picpasspekerja").hide();
			hidepicpsgnkakitgn();
			hidepicparentkakitgn();
			hidepicoku();
			
			detailpsgnNo();
			
			$(".getidparentstaff").hide();
			$(".idParentStaff").val("");
			$('.form-group .getidpsgn').removeClass('has-success');
						
		}
	});


	///psgn ppkp
	$('input[name=ppjpsgnRadio]').change(function(){
		var value = $( 'input[name=ppjpsgnRadio]:checked' ).val();
		
		if(value=='Y'){
			$(".picpasspekerjapsgn").show();
			$(".getidpsgn").show();
			$(".parentbukanstaff").hide();

			detailOKUYes();
			detailparentYes();
			hidepicparentkakitgn();
			hidepicoku();

	
		}else{
			$('#paspekerjapsgn, #idPsgn').val('');
			$(".picpasspekerjapsgn").hide();
			$(".getidpsgn, .parentDetail").hide();

			detailparentNo();
			hidepicparentkakitgn();
			hidepicoku();
			
			("#idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent, #negeriSuratParent").val("");



			
		}
	});


	///parent ppkp
	$('input[name=parentStaffRadio]').change(function(){
		var value = $( 'input[name=parentStaffRadio]:checked' ).val();
		$('#formdataregister').bootstrapValidator('updateStatus', 'okuRadio', 'NOT_VALIDATED');
		  
		if(value=='Y'){
			$(".picpasspekerjaparent").show();

			detailOKUYes();
			hidepicoku();

	
		}else{
			var umurpatr = $("#umur").val();
			///alert(umurpatr+value)
			
			if(age < 12 ){
				if(age <= 4){

				}else{
										
					//alert("not checking checked");
					$(".parentbukanstaff, .parentstaff").hide();
					$(".labelquesstaffppj").hide();
					$(".labelquestparent").show();
										
				}
									
									
			}
			
			$('#paspekerjaparent').val('');
			$(".picpasspekerjaparent").hide();

			detailOKUNo();

			$("#idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent, #negeriSuratParent").val("");



			
			
			
			
			
		}
	});

	
	//kad oku radio
	$('input[name=okuRadio]').change(function(){
		var value = $( 'input[name=okuRadio]:checked' ).val();
		
		if(value=='Y'){
			$(".pickadoku").show();
		}else{
			$('#kadOKU').val('');
			$(".pickadoku").hide();
		}
	});
	
	

	
	
	function parentStaffRadioY(){
		$('#formdataregister').data('bootstrapValidator').resetForm();
		$(".getidparentstaff").show();
		///$('#idParentStaff').focus();
		$(".parentbukanstaff").hide();
		$(".parentstaff, .parentDetail").show();
		$("#emailParent, #idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent, #negeriSuratParent").attr('disabled', 'disabled');	
	}
	
	function parentStaffRadioN(){
		$('#idParentStaff').val('');
		$(".getidparentstaff").hide();
		$(".parentbukanstaff").show();
		$(".parentstaff").hide();
		


		var umur = $('input[name="umur"]').val();

		if(umur >= 5 && umur <= 12){
			$(".parentDetail").show();
			$(".labelquesstaffppj").hide();
		}else{
			$(".parentDetail").hide();
			$(".labelquesstaffppj").show();
		}
		
		
		$("#emailParent, #emailParent, #idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent").val("");
		$("#negeriSuratParent").prop("selectedIndex",-1); 
		
		$(".idParentStaff").hide();
		
		$("#emailParent").attr('disabled', false);
		
		
	}
	
	
	///parent staff ppj
	$('input[name=parentStaffRadio]').change(function(){
		var value = $( 'input[name=parentStaffRadio]:checked' ).val();

		if(value=='Y'){
			parentStaffRadioY();
		}else{
			parentStaffRadioN();
		}
	});
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//idParentStaff
	
	$("#idParentStaff").blur(function(e){
		
	    e.preventDefault();
		e.stopImmediatePropagation();
		
		var idParentStaff = $("#idParentStaff").val();
		
		
		$.get('GetPatronBasicInfo', {
        	patrid : idParentStaff,
	 	}, function(responseJson) {
		if(responseJson != null){
			
			if(responseJson==''){
				 //messageBox2("002","010","","");
				 messageBox("002","010","","");
				 $("#idParentStaff").val("");
				 $("#emailParent, #idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent, #negeriSuratParent").val("");

				 //setup();
			}
			
			$.each(responseJson, function(key,value) {
				//$("#inputDOB").val(newInputDOB);
				
	
				if( (value['Cate'] == '08') || (value['Cate'] == '09') || (value['Cate'] == '10')){
					$("#emailParent").val(value['Email']);
					$("#idParent").val(value['IcNo']);
					$("#namaPenjaga").val(value['Name']);
					$("#alamatSuratParent").val(value['Add1']);
					$("#alamatSuratParent2").val(value['Add2']);
					$("#alamatSuratParent3").val(value['Add3']);
					$("#poskodSuratParent").val(value['Postcode']);
					$("#negeriSuratParent").val(value['Town']);
					$("#phoneParent").val(value['PhoneNo']);
				}else{
					messageBox("002","027","","");
					$("#idParentStaff").val("");
					$("#emailParent, #idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent, #negeriSuratParent").val("");

				}
				
				
			
			});
		}
		});

		
	});
	
	$("#idPsgn, #idParentStaff").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent, #negeriSuratParent").val("");
		}
		
		if(code == 13) {
	      e.preventDefault();
	      return false;
	    }
	});
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//idPsgn
	
	$("#idPsgn").blur(function(e){
		
	    e.preventDefault();
		e.stopImmediatePropagation();
		
		$('#Register').prop('disabled', false);
		
		var idPsgn = $("#idPsgn").val();
		
		
		$.get('GetPatronBasicInfo', {
        	patrid : idPsgn,
	 	}, function(responseJson) {
		if(responseJson != null){
			
			if(responseJson==''){
				 ///messageBox2("002","010","","");
				 messageBox("002","010","","");
				 $("#emailParent, #idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent, #negeriSuratParent").val("");
					$('#Register').prop('disabled', true);
				 //setup();
			}
			
			$.each(responseJson, function(key,value) {
				//$("#inputDOB").val(newInputDOB);				
				if( (value['Cate'] == '08') || (value['Cate'] == '09') || (value['Cate'] == '10')){
					$(".parentDetail").show();

					
					$("#emailParent").val(value['Email']);
					
					$(".divemailpatent, .textIdparent").hide();
					
					$(".labeltextIdparent").text("No K/P");
					$(".labelnamaPenjaga").text("Nama");

					
					$("#idParent").val(value['IcNo']);
					$("#namaPenjaga").val(value['Name']);
					$("#alamatSuratParent").val(value['Add1']);
					$("#alamatSuratParent2").val(value['Add2']);
					$("#alamatSuratParent3").val(value['Add3']);
					$("#poskodSuratParent").val(value['Postcode']);
					$("#negeriSuratParent").val(value['Town']);
					$("#phoneParent").val(value['PhoneNo']);
				}else{
					$(".parentDetail").hide();
					messageBox("002","026","","");
					$("#idPsgn").val("");
					$("#emailParent, #idparent, #namaPenjaga, #alamatSuratParent, #alamatSuratParent2, #alamatSuratParent3, #poskodSuratParent, #phoneParent, #negeriSuratParent").val("");

				}
				/*$(".parentDetail").show();
			
				$("#emailParent").val(value['Email']);
				$("#idParent").val(value['IcNo']);
				$("#namaPenjaga").val(value['Name']);
				$("#alamatSuratParent").val(value['Add1']);
				$("#alamatSuratParent2").val(value['Add2']);
				$("#alamatSuratParent3").val(value['Add3']);
				$("#poskodSuratParent").val(value['Postcode']);
				$("#negeriSuratParent").val(value['Town']);
				$("#phoneParent").val(value['PhoneNo']);*/
			
			});
		}
		});

		
	});
	
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////validation submit form
	$('#formdataregister').bootstrapValidator({
		framework: 'bootstrap',
		 excluded: [':disabled',':hidden'],
		 icon: {
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        //feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
			emailAhli: {
	            validators: {
	               notEmpty: {
	                   message: 'Alamat e-mel diperlukan'
	               },
	               person_email: {
	                   message: 'Input bukan alamat e-mel yang sah'
	               }
	           }
	        },
			branch: {
                validators: {
                    notEmpty: {
                        message: 'Sila pilih cawangan'
                    }
                }
            },
			cate: {
                validators: {
                    notEmpty: {
                        message: 'Sila pilih kategori ahli'
                    }
                }
            },
			/*alamatSurat: {
                validators: {
                    notEmpty: {
                        message: 'Alamat Pejabat / Sekolah/ Fakulti diperlukan'
                    }
                }
            },
			poskodSurat: {
                validators: {
                    notEmpty: {
                        message: 'Poskod diperlukan'
                    }
                }
            },
			negeriSurat: {
                validators: {
                    notEmpty: {
                        message: 'Negeri/Bandar diperlukan'
                    }
                }
            },*/
			warganegaraRadio: {
				validators: {
			     	notEmpty: {
			         	message: 'required'
			    	},
			   }                
			},
			kadOKU: {
                        validators: {
	 					notEmpty: {
                       	 	message: 'Kad OKU diperlukan'
                    	},
                            file: {
                                 extension: 'jpg,png',  ///.jpg, .png, .pdf
               					///type: 'image/jpg,image/png,application/pdf',
                                maxSize:  3048*2048, // 5 MB
                                message: 'The selected file is not valid, it should be (jpg,png) and size not more than 6MB'
                            }
                        }
            },	
			paspekerjakakitgn: {
                        validators: {
	 					notEmpty: {
                       	 	message: 'kad kakitangan anda diperlukan'
                    	},
                            file: {
                                extension: 'jpg,png',  ///.jpg, .png, .pdf
               					///type: 'image/jpg,image/png,application/pdf',
                                maxSize:  3048*2048, // 5 MB
                                message: 'The selected file is not valid, it should be (jpg,png) and size not more than 6MB'
                            }
                        }
            },	
			paspekerjapsgn: {
                        validators: {
	 					notEmpty: {
                       	 	message: 'kad kakitangan Suami/Isteri anda diperlukan'
                    	},
                            file: {
                                 extension: 'jpg,png',  ///.jpg, .png, .pdf
               					///type: 'image/jpg,image/png,application/pdf',
                                maxSize:  3048*2048, // 5 MB
                                message: 'The selected file is not valid, it should be (jpg,png) and size not more than 6MB'
                            }
                        }
            },
			paspekerjaparent: {
                        validators: {
	 					notEmpty: {
                       	 	message: 'kad Ibu/Bapa/Penjaga anda diperlukan'
                    	},
                            file: {
                                extension: 'jpg,png',  ///.jpg, .png, .pdf
               					///type: 'image/jpg,image/png,application/pdf',
                                maxSize:  3048*2048, // 5 MB
                                message: 'The selected file is not valid, it should be (jpg,png) and size not more than 6MB'
                            }
                        }
            },
			idParentStaff: {
                validators: {
                    notEmpty: {
                        message: 'No Ibu/Bapa/Penjaga Kakitangan PPJ diperluakan'
                    }
                }
            }, 
			emailParent: {
	            validators: {
	               notEmpty: {
	                   message: 'Alamat e-mel Ibu/Bapa/Penjaga diperlukan'
	               },
	               person_email: {
	                   message: 'Input bukan alamat e-mel yang sah'
	               }
	           }
	        },
			idparent: {
                validators: {
                    notEmpty: {
                        message: 'No Ibu/Bapa/Penjaga diperluakan'
                    }
                }
            },
            idPsgn: {
                validators: {
                    notEmpty: {
                        message: 'No kakitangan kakitangan Suami/Isteriiperluakan'
                    }
                }
            },
			namaPenjaga: {
                validators: {
                    notEmpty: {
                        message: 'Nama Ibu/Bapa/Penjaga diperluakan'
                    }
                }
            },
			/*alamatSuratParent: {
                validators: {
                    notEmpty: {
                        message: 'Alamat Ibu/Bapa/Penjaga diperluakn'
                    }
                }
            },
			poskodSuratParent: {
				trigger: 'blur, focusout',
                validators: {
                    notEmpty: {
                        message: 'Poskod Ibu/Bapa/Penjaga diperlukan'
                    }
                }
            },
			negeriSuratParent: {
                validators: {
                    notEmpty: {
                        message: 'Negeri/Bandar Ibu/Bapa/Penjaga diperlukan'
                    }
                }
            },*/
			phoneParent: {
				trigger: 'blur, focusout',
                validators: {
                    notEmpty: {
                        message: 'No Telefon/ HP Ibu/Bapa/Penjaga diperluakan'
                    }
                }
            },
			okuRadio: {
				validators: {
			     	notEmpty: {
			         	message: 'required'
			    	},
			   }                
			},
			ppjRadio: {
				validators: {
			     	notEmpty: {
			         	message: 'required'
			    	},
			   }                
			},
			paspekerja: {
				validators: {
			     	notEmpty: {
			         	message: 'required'
			    	},
			   }                
			},
			parentStaffRadio: {
				validators: {
			     	notEmpty: {
			         	message: 'required'
			    	},
			   }                
			},
			ppjpsgnRadio: {
				validators: {
			     	notEmpty: {
			         	message: 'required'
			    	},
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
		 var inputForm = $form.serialize();
		 
		$('#Register').prop('disabled', true);
	
		 var emailAhli = $('input[name="emailAhli"]').val();
		 var jantina = $('input[name="jantina"]').val();
		 var branch = $("#branch").val();
		 //var cate = $("#cate").val();
		// var cate = "";
		 var namaAhli = $('input[name="namaAhli"]').val();
		 //var icOrPass = $('input[name="icOrPass"]:checked').val();
		 var ic = $('input[name="ic"]').val();
	     //var passport = $('input[name="passport"]').val();
		 var inputDOB = moment($("#inputDOB").val(), 'DD/MM/YYYY').format("YYYYMMDD");
	     var alamat = $('input[name="alamat"]').val();
		 var alamat2 = $('input[name="alamat2"]').val();
		 var alamat3 = $('input[name="alamat3"]').val();
		 var poskod = $('input[name="poskod"]').val();
		 var negeri = $("#negeri").val();
		 var alamatSurat = $('input[name="alamatSurat"]').val();
		 var alamatSurat2 = $('input[name="alamatSurat2"]').val();
		 var alamatSurat3 = $('input[name="alamatSurat3"]').val();
	     var poskodSurat = $('input[name="poskodSurat"]').val();
		 var negeriSurat = $("#negeriSurat").val();
		 var phone = $('input[name="phone"]').val();
	     var race = $("#race").val();
		 var idparent = $('input[name="idparent"]').val();
		 var idParentStaff = $('input[name="idParentStaff"]').val();
	
		 var fd = new FormData();
         var filespicImage = $('#picImage')[0].files[0];
		 var filespaspekerja = $('#paspekerjakakitgn')[0].files[0];
	     var fileskadOKU = $('#kadOKU')[0].files[0];
		 var fileskadpasangan = $('#paspekerjapsgn')[0].files[0];
		 var fileskadparent = $('#paspekerjaparent')[0].files[0];


		 var radioStatusOKU = $( 'input[name=okuRadio]:checked' ).val();
		 var radioPPJStaff = $( 'input[name=ppjRadio]:checked' ).val();
		 var radioParentStaff = $( 'input[name=parentStaffRadio]:checked' ).val();
		 var radioPasanganStaff = $( 'input[name=ppjpsgnRadio]:checked' ).val();
		 
		 
		
	

	
	      
	
	     var emailParent = $('input[name="emailParent"]').val();
         var namaPenjaga = $('input[name="namaPenjaga"]').val();
         var alamatSuratParent = $('input[name="alamatSuratParent"]').val();
         var alamatSuratParent2 = $('input[name="alamatSuratParent2"]').val();
         var alamatSuratParent3 = $('input[name="alamatSuratParent3"]').val();
         var poskodSuratParent = $('input[name="poskodSuratParent"]').val();
		 var negeriSuratParent = $("#negeriSuratParent").val();
		 var phoneParent = $('input[name="phoneParent"]').val();
		 var idPsgn = $('input[name="idPsgn"]').val();
	
	     var statusUser;

		 var warganegara = $( 'input[name=warganegaraRadio]:checked' ).val();
		 
		 var umur = $('input[name="umur"]').val();

		 
	     /*if(radioStatusOKU=="Y" && radioPPJStaff == 'N'){
			statusUser = 'OKU';
		 }else if(radioStatusOKU=="Y" && radioPPJStaff == 'Y'){
			statusUser = 'STAFF';
		 }else if(radioStatusOKU=="N" && radioPPJStaff == 'Y'){
			statusUser = 'STAFF';
		 }else{
			
			if(umur >= 60){
				statusUser = "Warga Emas";
			}else{
				statusUser = 'Normal User';
			}
		}*/
		////update 28022022

		 
		if( (radioPPJStaff=="Y") && (radioPasanganStaff == "N") && (radioParentStaff == "N") && (radioStatusOKU == "N") ){
			statusUser = 'STAFF';
		}else if( (radioPPJStaff=="N") && (radioPasanganStaff == "Y") && (radioParentStaff == "N") && (radioStatusOKU == "N")){
			statusUser = 'PASANGAN';
		}else if( (radioPPJStaff=="N") && (radioPasanganStaff == "N") && (radioParentStaff == "Y") && (radioStatusOKU == "N") ){
			statusUser = 'PARENT';
		}else if(  (radioPPJStaff=="N" || radioPPJStaff==undefined ) && (radioPasanganStaff == "N" || radioPasanganStaff == undefined) && (radioParentStaff == "N") && (radioStatusOKU == "Y") ){
			statusUser = 'OKU';
		}else if(  (radioPPJStaff==undefined ) && (radioPasanganStaff == undefined) && (radioParentStaff == "Y") && (radioStatusOKU == "N") ){
			statusUser = 'PARENT';
		}else if(  (radioPPJStaff=="N") && (radioPasanganStaff == "N") && (radioParentStaff == "N") && (radioStatusOKU == "N") ){
			if(umur >= 60){
				statusUser = "Warga Emas";
			}else{
				statusUser = 'Normal User';
			}
		}else if(  (radioPPJStaff==undefined) && (radioPasanganStaff == undefined) && (radioParentStaff == "N") && (radioStatusOKU == "N") ){
			if(umur >= 60){
				statusUser = "Warga Emas";
			}else{
				statusUser = 'Normal User';
			}
		}
		
		 /*alert("radioPPJStaff = "+radioPPJStaff + ", radioPasanganStaff"+radioPasanganStaff
			+" ,radioParentStaff"+radioParentStaff +",radioStatusOKU"+radioStatusOKU);*/
		


		
		var agecate;
		

		if(umur >= 5 && umur <= 12){
			agecate = "Kanak-kanak";
		}else if(umur >= 13 && umur <= 20){
			agecate = "Remaja";
		}else if(umur >= 21 && umur <= 59){
			agecate = "Dewasa";
		}else if(umur >= 60){
			agecate = "Warga Emas";
		}
		

		
		var finalcate;
		
		
		/* if(radioParentStaff == 'Y'){
			finalcate = "anak staff";
		}else{
			
			
			
			
			if(statusUser == 'STAFF'){
				finalcate = "staff";
			}else if(statusUser == 'OKU'){
				finalcate = "oku " + agecate;
			}else if(statusUser == 'Warga Emas'){
				finalcate = "Warga Emas";
			}else if(statusUser == 'Normal User'){
				finalcate = agecate;
			}
		}*/
		
		///28022022
		if(statusUser == 'STAFF'){
				finalcate = "staff";
		}if(statusUser == 'PASANGAN'){
				finalcate = "pasangan";
		}if(statusUser == 'PARENT'){
				finalcate = "parent";
		}else if(statusUser == 'OKU'){
				finalcate = "oku " + agecate;
		}else if(statusUser == 'Warga Emas'){
				finalcate = "Warga Emas";
		}else if(statusUser == 'Normal User'){
				finalcate = agecate;
		}
		
		//alert(finalcate+"finalcate");
		

		
		if(filespicImage == undefined){
			filespicImage == "";
		}


		fd.append('emailAhli',emailAhli);
	 	 fd.append('jantina',jantina);
		 fd.append('branch',branch);
	     //fd.append('cate',cate);
		 fd.append('namaAhli',namaAhli);
	     //fd.append('icOrPass',icOrPass);
	 	 fd.append('ic',ic);
		 //fd.append('passport',passport);
	     fd.append('inputDOB',inputDOB);
		 fd.append('alamat',alamat);
		 fd.append('alamat2',alamat2);
	     fd.append('alamat3',alamat3);
		 fd.append('poskod',poskod);
	     fd.append('negeri',negeri);
	 	 fd.append('alamatSurat',alamatSurat);
		 fd.append('alamatSurat2',alamatSurat2);
	     fd.append('alamatSurat3',alamatSurat3);
		 fd.append('poskodSurat',poskodSurat);
	     fd.append('negeriSurat',negeriSurat);
		 fd.append('phone',phone);
	     fd.append('race',race);	
		 fd.append('idparent',idparent);	
		 fd.append('idParentStaff',idParentStaff);
         fd.append('picImage',filespicImage);
		 fd.append('paspekerja',filespaspekerja);
	     fd.append('kadOKU',fileskadOKU);

		 fd.append('paspekerjapasangan',fileskadpasangan);
	     fd.append('paspekerjaparent',fileskadparent);



		 fd.append('radioParentStaff',radioParentStaff);
		 fd.append('radioPasanganStaff',radioPasanganStaff);
		 
	     fd.append('emailParent',emailParent);
		 fd.append('namaPenjaga',namaPenjaga);
	     fd.append('alamatSuratParent',alamatSuratParent);
		 fd.append('alamatSuratParent2',alamatSuratParent2);
	     fd.append('alamatSuratParent2',alamatSuratParent3);	
		 fd.append('poskodSuratParent',poskodSuratParent);	
         fd.append('negeriSuratParent',negeriSuratParent);
		 fd.append('phoneParent',phoneParent);
	     fd.append('statusUser',statusUser);
	     fd.append('agecate',agecate);
		 fd.append('finalcate',finalcate);
		 
		 fd.append('idPsgn',idPsgn);
		 fd.append('picImageVal',$("#picImage").val()); 
	
	
		///alert("warganegara"+warganegara);
	
		if(warganegara == "Y"){
			$.ajax({
	        url: "savePatronKeahlian",
	        type: 'POST',
	        data: fd,
	        success: function (data) {
		
			//alert(data+"data")

	          
				
				$.post("SetMailOutputDftrAhli",{idsmstempt : "M002", namaAhli : namaAhli,
							ic : ic,
							emailAhli : emailAhli,  phone : phone, tarikh : moment(today).format("DD/MM/YYYY"),
							branch : branch, emailParent :emailParent},
						function(data){
							 
							 messageBox("002","011","","");
							//alert("Berjaya daftar");
							$('#formdataregister').trigger("reset");
							$('#formdataregister').data('bootstrapValidator').resetForm();
							setup();
							$('#Register').prop('disabled', true);
							
							
							/*if(data.trim() == "Success"){
								///alert(data+"notification sudah di hantar");
								messageBox("002","007","","");
							}*/
							
			
						}
						).fail(function(data){
							swal(data+"error");
							
						}).success(function(data){   
							swal(data+"success");
					 });
				
	        },
	        cache: false,
	        contentType: false,
	        processData: false
	    });
		}else if(warganegara == "N"){
			alert("pandai nk hack ye");
		}



	     



	});
	
	
	////click cancel
	$("#cancel").click(function(){
		$('#formdataregister').trigger("reset");
		$('#formdataregister').data('bootstrapValidator').resetForm();
		setup();
	});
	
	
	 
	
	


});