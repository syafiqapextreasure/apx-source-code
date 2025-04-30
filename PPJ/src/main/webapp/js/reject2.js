$(document).ready(function(){
	
	var today = new Date(); 
	
	$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		return results[1] || 0;
	}

	
	 if(window.location.href.indexOf("patrid") != -1) // This doesn't work, any suggestions?
    {
		if(decodeURI($.urlParam('patrid')) == "0"){
			
			$("#divbody").hide();
		}else{
			$("#divbody").show();
		}
     
    }else{
	
		$("#divbody").hide();
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

	
		}else{
			$('#paspekerjaparent').val('');
			$(".picpasspekerjaparent").hide();

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



	
	$.get('GetForPatronDetail', {
		        	patronid : decodeURI($.urlParam('patrid')),
				 	}, function(responseJson) {
						if(responseJson==''){
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
									
									
									var relid = value['RELID'];
									if(relid !=""){
										$(".parentDetail").show();
										
										$.get('GetForPatronDetail', {
								        	patronid : relid,
										 	}, function(responseJson) {
												if(responseJson==''){relid
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
									
									
									$.get('GetFndPatronFileType', {
							        	patrid : value['PATR'],
									 	}, function(responseJson) {
										
										//alert("responseJson"+responseJson)
										
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
													
													$(".picpasspekerja").hide();
													$(".picpasspekerjapsgn").show();
													$(".getidpsgn").show();
										
													detailOKUYes();
													detailparentYes();
													hidepicparentkakitgn();
													hidepicoku();
													
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

	    if (ageMonth < 0 || (ageMonth == 0 && ageDay < 0)) {
	        age = parseInt(age) - 1;
	    }
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
		
		
		$.get('GetPatronBasicInfo', {
        	patrid : idPsgn,
	 	}, function(responseJson) {
		if(responseJson != null){
			
			if(responseJson==''){
				 ///messageBox2("002","010","","");
				 messageBox("002","010","","");
				 setup();
			}
			
			$.each(responseJson, function(key,value) {
				//$("#inputDOB").val(newInputDOB);
				$(".parentDetail").show();
			
				$("#emailParent").val(value['Email']);
				$("#idParent").val(value['IcNo']);
				$("#namaPenjaga").val(value['Name']);
				$("#alamatSuratParent").val(value['Add1']);
				$("#alamatSuratParent2").val(value['Add2']);
				$("#alamatSuratParent3").val(value['Add3']);
				$("#poskodSuratParent").val(value['Postcode']);
				$("#negeriSuratParent").val(value['Town']);
				$("#phoneParent").val(value['PhoneNo']);
			
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
                                 extension: 'jpg,png,pdf',  ///.jpg, .png, .pdf
               					///type: 'image/jpg,image/png,application/pdf',
                                maxSize:  3048*2048, // 5 MB
                                message: 'The selected file is not valid, it should be (jpg,png,pdf) and size not more than 6MB'
                            }
                        }
            },	
			paspekerjakakitgn: {
                        validators: {
	 					notEmpty: {
                       	 	message: 'kad kakitangan anda diperlukan'
                    	},
                            file: {
                                extension: 'jpg,png,pdf',  ///.jpg, .png, .pdf
               					///type: 'image/jpg,image/png,application/pdf',
                                maxSize:  3048*2048, // 5 MB
                                message: 'The selected file is not valid, it should be (jpg,png,pdf) and size not more than 6MB'
                            }
                        }
            },	
			paspekerjapsgn: {
                        validators: {
	 					notEmpty: {
                       	 	message: 'kad kakitangan Suami/Isteri anda diperlukan'
                    	},
                            file: {
                                extension: 'jpg,png,pdf',  ///.jpg, .png, .pdf
               					//type: 'image/jpg,image/png,application/pdf',
                                maxSize:  3048*2048, // 5 MB
                                message: 'The selected file is not valid, it should be (jpg,png,pdf) and size not more than 6MB'
                            }
                        }
            },
			paspekerjaparent: {
                        validators: {
	 					notEmpty: {
                       	 	message: 'kad Ibu/Bapa/Penjaga anda diperlukan'
                    	},
                            file: {
                                extension: 'jpg,png,pdf',  ///.jpg, .png, .pdf
               					///type: 'image/jpg,image/png,application/pdf',
                                maxSize:  3048*2048, // 5 MB
                                message: 'The selected file is not valid, it should be (jpg,png,pdf) and size not more than 6MB'
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
	                   message: 'Alamat e-mel Ibu/Bapa/Penjaga diperluakn'
	               },
	               person_email: {
	                   message: 'Input bukan alamat e-mel yang sah'
	               }
	           }
	        },
			idparent: {
                validators: {
                    notEmpty: {
                        message: 'No Ibu/Bapa/Penjaga diperluakn'
                    }
                }
            },
            idPsgn: {
                validators: {
                    notEmpty: {
                        message: 'No kakitangan kakitangan Suami/Isteriiperluakn'
                    }
                }
            },
			namaPenjaga: {
                validators: {
                    notEmpty: {
                        message: 'Nama Ibu/Bapa/Penjaga diperluakn'
                    }
                }
            },
			alamatSuratParent: {
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
            },
			phoneParent: {
				trigger: 'blur, focusout',
                validators: {
                    notEmpty: {
                        message: 'No Telefon/ HP Ibu/Bapa/Penjaga diperluakn'
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
	     


		////update 28022022
		if( (radioPPJStaff=="Y") && (radioPasanganStaff == "N") && (radioParentStaff == "N") && (radioStatusOKU == "N") ){
			statusUser = 'STAFF';
		}else if( (radioPPJStaff=="N") && (radioPasanganStaff == "Y") && (radioParentStaff == "N") && (radioStatusOKU == "N")){
			statusUser = 'PASANGAN';
		}else if( (radioPPJStaff=="N") && (radioPasanganStaff == "N") && (radioParentStaff == "Y") && (radioStatusOKU == "N") ){
			statusUser = 'PARENT';
		}else if(  (radioPPJStaff=="N") && (radioPasanganStaff == "N") && (radioParentStaff == "N") && (radioStatusOKU == "Y") ){
			statusUser = 'OKU';
		}else if(  (radioPPJStaff=="N") && (radioPasanganStaff == "N") && (radioParentStaff == "N") && (radioStatusOKU == "N") ){
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
	
	

			$.ajax({
	        url: "UpdatePatronKeahlian",
	        type: 'POST',
	        data: fd,
	        success: function (data) {
		
			//alert(data+"data")

	           messageBox("002","011","","");
				//alert("Berjaya daftar"); add reload page
				$('#formdataregister').trigger("reset");
				$('#formdataregister').data('bootstrapValidator').resetForm();
				setup();
				
				$.post("SetMailOutputDftrAhli",{idsmstempt : "M002", namaAhli : namaAhli,
							ic : ic,
							emailAhli : emailAhli,  phone : phone, tarikh : moment(today).format("DD/MM/YYYY"),
							branch : branch},
						function(data){
							
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