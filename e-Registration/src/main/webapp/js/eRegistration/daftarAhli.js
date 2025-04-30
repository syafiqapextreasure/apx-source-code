$(document).ready(function(){
	
	$("#namaAhli").focus();
	$('input[type="text"]').attr('autocomplete', 'off');
	$('input[type="email"]').attr('autocomplete', 'off');
	
	//MAX LENGTH
	$("#namaAhli").attr('maxlength','50');
	$("#alamat, alamat2, alamat3, #alamatSurat, #alamatSurat2, #alamatSurat3").attr('maxlength','80');
	$("#poskod, #poskodSurat").attr('maxlength','5');
	$("#warganegara").attr('maxlength','20');
	
	$("#gelaran, #negeri, #negeriSurat, #race").prop("selectedIndex",-1); 
	
	////IC
	$("#ic").keypress(function (e) {
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
	
	///datepicker
	$('#inputDOB').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///input number only
	$("#poskod, #poskodSurat").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });
	
	//phone
	$("#phone").keypress(function (e) {
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
	
	//tel rumah
	$("#houseno").keypress(function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
		  var curchr = this.value.length;
        var curval = $(this).val();
        if (curchr == 2) {
            $(this).val(curval + "-");
        }
        $(this).attr('maxlength', '14');
	  });
	
	/////BUTTIN CANCEL CLICK
	$("#cancel").click(function(){
		//$('#formdataregister').data('bootstrapValidator').resetForm();
		$('#formdataregister').trigger('reset');
	});
	
	//focus out ic
	$("#ic").on('focusout',function () {
		
		var inputStr = $("#ic").val();
		
		var dob = inputStr.substring(0,6);
    	var year =  parseInt(dob.substring(0,2));
    	var month =  dob.substring(2,4);
    	var date =  dob.substring(4,6);
    	
    	if(year > 40){
			year = year+1900;
		}else {
			year = year+2000;
		}
    	
    	var fullDate = date + "/" + month + "/" + year;
    	$('#dob').datepicker('setDate', fullDate);
    	$("#inputDOB").val(fullDate);
		
	});
	
	//*******************************************************************************************//
	$('#formdataregister').bootstrapValidator({
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
            namaAhli: {
                validators: {
                    notEmpty: {
                        message: 'Nama/Name is required'
                    }
                }
            },
            ic: {
                validators: {
                    notEmpty: {
                        message: 'No. Kad Pengenalan/IC is required'
                    },
                    remote: {
                    	type: "GET",
                        url: 'CheckingPatDetails',
                        data: function(validator) {
                            return {
                            	ic: validator.getFieldElements('ic').val(),
                            };
                        },
                        message: 'No. Kad Pengenalan/IC already exist!',
                    }
                }
            },
            inputDOB: {
                validators: {
                    notEmpty: {
                        message: 'Tarikh Lahir/D.O.B is required'
                    }
                }
            },
            alamat: {
                validators: {
                    notEmpty: {
                        message: 'Alamat/Address is required'
                    }
                }
            },
            negeri: {
                validators: {
                    notEmpty: {
                        message: 'Please select Negeri/State'
                    }
                }
            },
            poskod: {
                validators: {
                    notEmpty: {
                        message: 'Poskod/PostalCode is required'
                    }
                }
            },
            alamatSurat: {
                validators: {
                    notEmpty: {
                        message: 'Alamat Surat-Menyurat is required'
                    }
                }
            },
            negeriSurat: {
                validators: {
                    notEmpty: {
                        message: 'Please select Negeri Surat-Menyurat'
                    }
                }
            },
            poskodSurat: {
                validators: {
                    notEmpty: {
                        message: 'Poskod Surat-Menyurat is required'
                    }
                }
            },
            warganegara: {
                validators: {
                    notEmpty: {
                        message: 'Warganegara/Nationality is required'
                    }
                }
            },
            phone: {
                validators: {
                    notEmpty: {
                        message: 'No. H/Phone is required'
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
         var today = new Date(); 
     	
     	 var namaAhli = $("#namaAhli").val().toUpperCase();
     	 //alert("namaAhli" +namaAhli);
     	 var gelaran = $("#gelaran").val();
     	 if(gelaran == null){
     		gelaran = "";
     	 }
     	 //alert("gelaran" +gelaran);
     	 var ic = $("#ic").val();   
     	 //alert("ic" +ic);
     	 var newICval = ic.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi,'').trim();
     	//alert("newICval" +newICval);
     	//alert($("#inputDOB").val());
     	 var inputDOB = moment($("#inputDOB").val(), 'DD/MM/YYYY').format("YYYYMMDD");
     	//alert("inputDOB" +inputDOB);
     	 var alamat = $("#alamat").val().toUpperCase();
     	//alert("alamat" +alamat);
     	 var alamat2 = $("#alamat2").val().toUpperCase();
     	 var alamat3 = $("#alamat3").val().toUpperCase();
     	 var negeri = $("#negeri").val();
     	 var poskod = $("#poskod").val().toUpperCase();;
     	 var alamatSurat = $("#alamatSurat").val().toUpperCase();
     	 var alamatSurat2 = $("#alamatSurat2").val().toUpperCase();
     	 var alamatSurat3 = $("#alamatSurat3").val().toUpperCase();
     	 var negeriSurat = $("#negeriSurat").val();
     	 var poskodSurat = $("#poskodSurat").val().toUpperCase();
     	 var warganegara = $("#warganegara").val().toUpperCase();
     	 var phone = $("#phone").val().toUpperCase();
     	 var houseno = $("#houseno").val().toUpperCase();;
     	 var kaum = $("#race").val();
     	 if(kaum == null){
     		 kaum = "";
     	 }

     	 var kad = $("input[name='kad']:checked").val();
     	 /*var emailAhli = $("#emailAhli").val().toUpperCase();*/
     	var emailAhli = $("#emailAhli").val().toUpperCase();
     	 
     	var sex = newICval.substring(11);
     	if (sex%2 == 0){
     		sex = "F";
    	} else{
    		sex = "M";
    	}
     	
     	alert()

     			$.get("Handler_Add",{
     				namaAhli : namaAhli, 
	     			gelaran : gelaran,
	     			newICval : newICval, 
	     			inputDOB : inputDOB,
	     			alamat : alamat, 
	     			alamat2 : alamat2,
	     			alamat3 : alamat3,
	     			negeri : negeri,
	     			poskod : poskod,
	     			alamatSurat : alamatSurat,
	     			alamatSurat2 : alamatSurat2,
	     			alamatSurat3 : alamatSurat3,
	     			negeriSurat : negeriSurat, 
	     			poskodSurat : poskodSurat,
	     			warganegara : warganegara,
	     			phone : phone,
	     			houseno : houseno,
	     			kaum : kaum,
	     			kad : kad,
	     			emailAhli : emailAhli,
	     			sex : sex
     				},
     				
     				function(message){
     					var status = message.replace(/\s+/g, '');
     					if (status == "ok") {
     						$('#formdataregister').data('bootstrapValidator').resetForm();
     						$('#formdataregister').trigger('reset');
     						$("#gelaran, #negeri, #negeriSurat, #race").prop("selectedIndex",-1); 
     						swal("Berjaya Daftar")
     					}else{
     						swal("Tidak Berjaya Daftar");
     					}
     			});

	});
	

	$('.input-group.date').datepicker({
	    format: "dd/mm/yyyy",
	    todayBtn: true,
	    autoclose: true,
	    todayHighlight: true
	}).on('changeDate', function(e) {
        // Revalidate the date field
        $('#formdataregister').bootstrapValidator('updateStatus', 'inputDOB', 'NOT_VALIDATED').bootstrapValidator('validateField', 'inputDOB');
    });
	
});