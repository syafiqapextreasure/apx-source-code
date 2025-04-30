$(document).ready(function(){
	
	var today = new Date(); 
	
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
	
	
	$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		return results[1] || 0;
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
		
		$("#namaAhli, #ic, #passport, #inputDOB, #alamat, #alamat2, #alamat3, #poskod, #phone, #warganegara").prop('disabled', true);
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
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///on change radio button

	function detailpsgnYes(){
		$(".labelquespsgnppj").hide();
		$("input[name=ppjpsgnRadio][value='N']").prop('checked', true);
		$('input[name=ppjpsgnRadio]').attr("disabled",true);
		//$(".getidpsgn").show();
		$(".parentbukanstaff, .parentstaff").hide();
	}

	function detailpsgnNo(){
		$(".labelquespsgnppj").show();
		$('input[name=ppjpsgnRadio]').prop('checked', false);
		$('input[name=ppjpsgnRadio]').attr("disabled",false);
		$(".parentbukanstaff, .parentstaff").hide();
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
						
		}
	});


	///psgn ppkp
	$('input[name=ppjpsgnRadio]').change(function(){
		var value = $( 'input[name=ppjpsgnRadio]:checked' ).val();
		
		if(value=='Y'){
			$(".picpasspekerjapsgn").show();
			$(".getidpsgn").show();

			detailOKUYes();
			detailparentYes();
			hidepicparentkakitgn();
			hidepicoku();

	
		}else{
			$('#paspekerjapsgn').val('');
			$(".picpasspekerjapsgn").hide();
			$(".getidpsgn").hide();

			detailparentNo();
			hidepicparentkakitgn();
			hidepicoku();


			
		}
	});


	///parent ppkp
	$('input[name=parentStaffRadio]').change(function(){
		var value = $( 'input[name=parentStaffRadio]:checked' ).val();
		
		if(value=='Y'){
			$(".picpasspekerjaparent").show();

			detailOKUYes();
			hidepicoku();
			$(".getidparentstaff").show();

	
		}else{
			$('#paspekerjaparent').val('');
			$(".picpasspekerjaparent").hide();
			$(".getidparentstaff").hide();

			detailOKUNo();

			


			
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
	
	function parseDate(dateStr) {
		  var dateParts = dateStr.split("/");
		  return new Date(dateParts[2], (dateParts[1] - 1), dateParts[0]);
	}
	
	var loginid = $("#liferayLogin").val();

	$.get('GetForPatronDetail', {
		        	patronid : decodeURI($.urlParam('patrid')),
				 	}, function(responseJson) {

						if(responseJson==''){
							
							messageBox("002","025","","");
							$(".content-header").empty();
							$("#boxy").empty();
							window.close();
									
					 	}else{					 	
							$.each(responseJson, function(key,value) {
								

								if(value['STAT'] == "17"){
									
									var ic = value['NEWIC'];
									//alert("dob"+dob);
							    	var year =  parseInt(ic.substring(0,2));
							    	var month =  ic.substring(2,4);
							    	var date =  ic.substring(4,6);
							    	
							    	
							    	if(year > 40){
										year = year+1900;
									}else {
										year = year+2000;
									}
									
									var fullDate =  moment(year + "-" + month + "-" + date, 'YYYY-MM-DD').format("DD/MM/YYYY");							
									
									var ages = calculateAge(parseDate(fullDate), new Date());
									
								
									$('#umur').val(ages);
									

									$('#email').val(value['IPADD']);
									$('#branch').val(value['BRNC']);
									$('#namaAhli').val(value['NAME']);
									$('#ic').val(value['NEWIC']);
									$('#inputDOB').val(value['DOB']);
									$('#alamat').val(value['ADD1']);
									$('#alamat2').val(value['ADD2']);
									$('#alamat3').val(value['ADD3']);
									$('#poskod').val(value['CODE']);
									$('#negeri').val(value['TOWN']);
									
									
									$('#alamatSurat').val(value['OFFADD1']);
									$('#alamatSurat2').val(value['OFFADD2']);
									$('#alamatSurat3').val(value['OFFADD3']);
									$('#poskodSurat').val(value['OFFCODE']);
									
									$('#negeriSurat').val(value['OFFTOWN']);
									$('#race').val(value['RACE']);
									
									$('#phone').val(value['MTEL']);
									
									$('.reason').text(value['REMARK']);
									
									
									$.get('GetFndPatronFileType', {
							        	patrid : value['PATR'],
									 	}, function(responseJson) {
										
										///alert("responseJson"+responseJson)
										
											if( responseJson == null){
												$("input[name=ppjRadio][value='N']").prop("checked",true);
												$("input[name=ppjpsgnRadio][value='N']").prop("checked",true);
												$("input[name=parentStaffRadio][value='N']").prop("checked",true);
												$("input[name=okuRadio][value='N']").prop("checked",true);
												
												$(".picpasspekerja").hide();
												$(".getidpsgn").hide();
												hidepicpsgnkakitgn();
												hidepicparentkakitgn();
												hidepicoku();

											}else{
												
												if(responseJson == 'Pas Pekerja'){
													
													$("input[name=ppjRadio][value='Y']").prop("checked",true);
													
													$(".picpasspekerja").show();
													$(".getidpsgn").hide();
													detailpsgnYes();
													detailparentYes();
													detailOKUYes();
													hidepicpsgnkakitgn();
													hidepicparentkakitgn();
													hidepicoku();
													
												}else if(responseJson == 'Kad OKU'){
													
													$("input[name=ppjRadio][value='N']").prop("checked",true);
													$("input[name=ppjpsgnRadio][value='N']").prop("checked",true);
													$("input[name=parentStaffRadio][value='N']").prop("checked",true);
													$("input[name=okuRadio][value='Y']").prop("checked",true);
													
													//$(".pickadoku").show();
													$(".picpasspekerja").hide();
													$(".getidpsgn").hide();
													hidepicpsgnkakitgn();
													hidepicparentkakitgn();
													
												}else if(responseJson == 'Pas Pekerja Pasangan'){
													$("input[name=ppjRadio][value='N']").prop("checked",true);
													$("input[name=ppjpsgnRadio][value='Y']").prop("checked",true);
													
													///$(".picpasspekerja").hide();
													//$(".picpasspekerjapsgn").show();
													//$(".getidpsgn").show();
													
													$(".picpasspekerjapsgn").show();
													$(".picpasspekerja").hide();
													$(".getidpsgn").show();
													$(".parentbukanstaff").hide();
													$(".divemailpatent, .textIdparent").hide();
													$(".parentbukanstaff, .parentstaff").hide();
					
													$(".labeltextIdparent").text("No K/P");
													$(".labeltextNamaparent").text("Nama");
													
										
													detailOKUYes();
													detailparentYes();
													hidepicparentkakitgn();
													hidepicoku();
													
													$('#idPsgn').val(value['RELID']);
													
													
												}else if(responseJson == 'Pas Pekerja Ibu/Bapa/Penjaga'){
													$("input[name=ppjRadio][value='N']").prop("checked",true);
													$("input[name=ppjpsgnRadio][value='N']").prop("checked",true);
													$("input[name=parentStaffRadio][value='Y']").prop("checked",true);
													
													$(".picpasspekerjaparent").show();
													$(".picpasspekerja").hide();
													$(".getidpsgn").hide();

													detailOKUYes();
													hidepicoku();
													hidepicpsgnkakitgn();
													
												}


												
											}
									});
									
									age = calculateAge(parseDate(fullDate), new Date());
								
								
								
									///alert(age+"cc");
									
										
									$("#umur").val(age);
									
									
									if(age < 12 ){
									
									if(age <= 4){
										}else{
											//alert("Pengguna dibawah umur sila masukkan ibu bapa/penjaga");
											///////////messageBox("002","001","","");
											
											///alert($("#parentStaffNo").is(":checked"));
											
											/*if ($("#parentStaffNo").is(":checked")) {
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
											}*/
			
										}
										
										
									}else{
										
										/*if ($("#parentStaffNo").is(":checked")) {
											   // do something
											  // alert("is checked");
										}else{
											//alert("not checking checked");
										}*/
	
										
										/*$("#emailParent").prop('disabled', true);
										$(".parentDetail").hide();
										$(".parentbukanstaff, .parentstaff").hide();
										$(".labelquesstaffppj").show();
										$(".labelquestparent").hide();*/
									}
									
									
									
									/*var category = value['CATE'];
									
									if(category == '06' || category == '05' || category == '04' || category == '17'){
										
										$("input[name=ppjRadio][value='N']").prop("checked",true);
										$("input[name=ppjpsgnRadio][value='N']").prop("checked",true);
										$("input[name=parentStaffRadio][value='N']").prop("checked",true);
										$("input[name=okuRadio][value='N']").prop("checked",true);
													
										if(category=='06'){
											
											
											
											
											$.get('ResultParentDetail', {
								        	patronid : decodeURI($.urlParam('patrid')),
										 	}, function(responseJson) {
												if(responseJson==''){
											 	}else{					 	
													$.each(responseJson, function(key,value) {
															$('#emailParent').val(value['EMAIL']);
															$('#idParent').val(value['PARENTID']);
															$('#namaPenjaga').val(value['NAME']);
															$('#alamatSuratParent').val(value['ADD1']);
															$('#alamatSuratParent2').val(value['ADD2']);
															$('#alamatSuratParent3').val(value['ADD3']);
															$('#poskodSuratParent').val(value['CODE']);
															$('#negeriSuratParent').val(value['TOWN']);
															$('#phoneParent').val(value['PHONE']);
													
														
													});
												}
										});
											
										}
									}else if(category == '14', '15'){
										
									}else if(category == 13){
										
									}else if(category == 12){
										
									}*/
									
									var relid = value['RELID'];
									if(relid !=""){
										$(".parentDetail").show();
										
										$.get('GetForPatronDetail', {
								        	patronid : relid,
										 	}, function(responseJson) {
											
												if(responseJson==''){//relid
													
													
													$.get('ResultParentDetail', {
											        	patrid : decodeURI($.urlParam('patrid')),
													 	}, function(responseJson) {
															if(responseJson==''){
														 	}else{					 	
																$.each(responseJson, function(key,value) {
																		$('#emailParent').val(value['EMAIL']);
																		$('#idParent').val(value['PARENTID']);
																		$('#namaPenjaga').val(value['NAME']);
																		$('#alamatSuratParent').val(value['ADD1']);
																		$('#alamatSuratParent2').val(value['ADD2']);
																		$('#alamatSuratParent3').val(value['ADD3']);
																		$('#poskodSuratParent').val(value['CODE']);
																		$('#negeriSuratParent').val(value['TOWN']);
																		$('#phoneParent').val(value['PHONE']);
																
																	
																});
															}
													});
													
													
											 	}else{					 	
													$.each(responseJson, function(key,value) {
															$('#emailParent').val(value['IPADD']);
															$('#idParent').val(value['NEWIC']);
															$('#namaPenjaga').val(value['NAME']);
															$('#alamatSuratParent').val(value['ADD1']);
															$('#alamatSuratParent2').val(value['ADD2']);
															$('#alamatSuratParent3').val(value['ADD3']);
															$('#poskodSuratParent').val(value['CODE']);
															$('#negeriSuratParent').val(value['TOWN']);
															$('#phoneParent').val(value['MTEL']);
													
														
													});
												}
										});
										
										
									}else{
										$(".parentDetail").hide();
									}
									
									
									
									
									
									var a= "PatronReviewPhoto?patrid="+decodeURI($.urlParam('patrid'));
										$.get(a,function(data){
										
											 if(data!=null && data!=""){
												 /*$("#imgtest3").attr("src", a);
											  	if(data == "undefined"){
													 var pathArray = window.location.pathname.split('/');
													 var newPathname = "";
													 newPathname += "/";
													 newPathname += pathArray[1];
													 $("#imgtest3").attr("src", newPathname+"/resources/image/user_default.png");
											 	}*/
											 	 $("#imgtest3").attr("src", a).on('load', function() {
										            // Once the image is loaded, set its width and height
										            $(this).css({
										                'width': '250px', // Adjust the width as needed
										                'height': '170px' // Adjust the height as needed
										            });
										        });
											}else{
												 var pathArray = window.location.pathname.split('/');
												 var newPathname = "";
												 newPathname += "/";
												 newPathname += pathArray[1];
												 $("#imgtest3").attr("src", newPathname+"/resources/image/user_default.png");
											 }
										});
									
									
									
									
								}else{
									//alert("anda tidak layak");
									 messageBox("002","025","","");
									$(".content-header").empty();
									$("#boxy").empty();
									window.close();
								}
								
								
								
							});
						}
		});
		
		
	/*function calculateAge (dateOfBirth, dateToCalculate) {


	    var calculateYear = dateToCalculate.getFullYear();
	    var calculateMonth = dateToCalculate.getMonth();
	    var calculateDay = dateToCalculate.getDate();

	    var birthYear = dateOfBirth.getFullYear();
	    var birthMonth = dateOfBirth.getMonth();
	    var birthDay = dateOfBirth.getDate();

	    var age = calculateYear - birthYear;
	    var ageMonth = calculateMonth - birthMonth;
	    var ageDay = calculateDay - birthDay;

	    if (ageMonth < 0 || (ageMonth == 0 && ageDay < 0)) {
	        age = parseInt(age) - 1;
	    }
	    return age;
	}*/
	
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
	
	
	$("#idPsgn").blur(function(e){
		
	    e.preventDefault();
		e.stopImmediatePropagation();
		
		var idPsgn = $("#idPsgn").val();
		
		$(".parentbukanstaff, .parentstaff").hide();
		
		$.get('GetPatronBasicInfo', {
        	patrid : idPsgn,
	 	}, function(responseJson) {
		if(responseJson != null){
			
			if(responseJson==''){
				 ///messageBox2("002","010","","");
				 messageBox("002","010","","");
				 setup();
				 $("#idPsgn").val("");
			}
			
			$.each(responseJson, function(key,value) {
				
				
				if( (value['Cate'] == '08') || (value['Cate'] == '09') || (value['Cate'] == '10')){
					$(".parentDetail").show();
					
					$("#emailParent").val(value['Email']);
					
					$(".divemailpatent, .textIdparent").hide();
					
					$(".labeltextIdparent").text("No K/P");
					$(".labeltextNamaparent").text("Nama");

					
			
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
					messageBox("002","026","","");
					$("#idPsgn").val("");
					//$(".parentDetail").hide();
				}
				
				//$("#inputDOB").val(newInputDOB);
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
				 setup();
			}
			
			$.each(responseJson, function(key,value) {
				//$("#inputDOB").val(newInputDOB);
				

				
				if( (value['Cate'] == '08') || (value['Cate'] == '09') || (value['Cate'] == '10')){
					//$(".parentDetail").show();
					$("#emailParent").val(value['Email']);
					$("#idparent").val(value['IcNo']);
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
					//$(".parentDetail").hide();
				}
				
				/*$("#emailParent").val(value['Email']);
				$("#idparent").val(value['IcNo']);
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
	
	/*$("#Register").on("click", function(){
     	$("#Register").prop("disabled", true);
    });*/
    
    
    $("#photo").on("change", function(){
		$("#changeimg").val("changed")
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
	
	var token = $("#tokenval").val();
	
	////on blur parent email
	$("#emailParent").blur(function(e){
		
		e.preventDefault();
		e.stopImmediatePropagation();
		
		var email = $("#emailParent").val();
		var emailAhli = $("#emailAhli").val();
		
		
		/*if(email == emailAhli){
			alert("1");
			$("#emailParent").val("");
			messageBox("002","028","","");
		}else{
			alert("2");*/
		
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
		
		//}
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
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
               					//type: 'image/jpg,image/png,application/pdf',
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
                        message: 'No Ibu/Bapa/Penjaga Kakitangan PPJ diperluakn'
                    }
                }
            }, 
			emailParent: {
	            validators: {
	               notEmpty: {
	                   message: 'Alamat e-mel Ibu/Bapa/Penjaga diperluakan'
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
			alamatSuratParent: {
                validators: {
                    notEmpty: {
                        message: 'Alamat Ibu/Bapa/Penjaga diperluakan'
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
            },
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
		 $("#Register").prop("disabled", true);
	
		 var emailAhli = $('input[name="email"]').val();
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
		 
		 var umur = $('input[name="umur"]').val();
		 
		 
	
		 var fd = new FormData();
         //var filespicImage = $('#picImage')[0].files[0];
         var filespicImage = $('#photo')[0].files[0];
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
		 fd.append('picImageVal',$("#photo").val()); 
	
	

			$.ajax({
	        url: "UpdatePatronKeahlian",
	        type: 'POST',
	        data: fd,
	        success: function (data) {
		

				
				$.post("SetMailOutputDftrAhli",{idsmstempt : "M002", namaAhli : namaAhli,
							ic : ic,
							emailAhli : emailAhli,  phone : phone, tarikh : moment(today).format("DD/MM/YYYY"),
							branch : branch, emailParent :emailParent},
						function(data){
							
							messageBox("002","011","","");
							//alert("Berjaya daftar"); add reload page
							$('#formdataregister').trigger("reset");
							$('#formdataregister')[0].reset();
							$('#formdataregister').data('bootstrapValidator').resetForm();
							$('#hreject td').empty();
							setup();
							$("#Register").prop("disabled", true);
							//$("#red").text("");
							//$('#photo').fileinput('clear');
							
							var pathArray = window.location.pathname.split('/');
							var newPathname = "";
							newPathname += "/";
							newPathname += pathArray[1];
							$("#imgtest3").attr("src", newPathname+"/resources/image/user_default.png");
							$(':input').val('');
							$("#m#red").html('');
                
			                // Remove any existing preview
			                /*$('.fileinput-preview').empty();
			                $(":input").prop("disabled", true)
			                
			                */
							
							///
							
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


	});
	
	
	
	
});