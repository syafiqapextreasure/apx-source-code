$(document).ready(function() {
	
	$("#brncCode, #town, #marctype, #libweekend, #irstype").prop("selectedIndex",-1);	
	
	/*$("input").on("blur", function(e) {
	    if (e.which === 32 && !this.value.length)
	        e.preventDefault();
	        alert("fs")
	});*/
	
	//////////////////////////////Max Length /////////////////////////////////
	
	$("#orderspan, #opacmsgtime").attr('maxlength','2');
	$("#timescost").attr('maxlength','4');
	$("#postcode, #gp1,#gp2, #gp3, #itt, #resvtime, #opaclimit, #sercHistory").attr('maxlength','5');
	$("#fund").attr('maxlength','6');
	$("#headLibExt, #profficerExt, #acqext, #catext, #cirext, #serext, #irsext, #finext").attr('maxlength','10');
	$("#headLib, #profficer, #logo, #acqhead, #cathead, #cirhead, #serHead, #irsHead, #finHead").attr('maxlength','12');
	$("#tel, #fax").attr('maxlength','14');
	$("#libName, #orgName, #libSymbol, #add1, #add2, #add3").attr('maxlength','50');
	
	//////////////key in number only//////////////////////////////////////
	$("#gp1,#gp2, #gp3, #orderspan, #itt, #resvtime, #timescost, #opaclimit, #opacmsgtime, #sercHistory, #daydate")
	.on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });
	
	///control input amount
	$("#rcost").on("keypress keyup blur",function (event) {
        //this.value = this.value.replace(/[^0-9\.]/g,'');
		 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
	         if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
	             event.preventDefault();
	         }
	         
	         //06042020
	         var number = ($(this).val().split('.'));
	         if (number[1].length > 2)
	         {
	             var salary = parseFloat($("#rcost").val());
	             $("#rcost").val( salary.toFixed(4));
	         }
	         /*if(($(this).val().indexOf('.') != -1) && ($(this).val().substring($(this).val().indexOf('.'),$(this).val().indexOf('.').length).length>4 )){
			     if (event.keyCode !== 8 && event.keyCode !== 46 ){ //exception
			         event.preventDefault();
			     }
	         }*/
    });
	
	/////////////////////////////MAIN/////////////////////////////////////////
	$.get('GetLibDetail', {
	 	}, function(responseJson) {
		if(responseJson != null){

			$.each(responseJson, function(key,value) {
				$("#action").val("edit");
				$("#libName").prop("disabled", true); 
				$("#orgName").prop("disabled", true); 
				$("#libName").val(value['name']);
				$("#orgName").val(value['orgname']);
				$("#libSymbol").val(value['libsymbol']);
				$("#brncCode").val(value['brnc']);
				$("#add1").val(value['add1']);
				$("#add2").val(value['add2']);
				$("#add3").val(value['add3']);
				$("#postcode").val(value['poscode']);
				$("#town").val(value['town']);
				$("#tel").val(value['tel']);
				$("#fax").val(value['fax']);
				
				$("#headLib").val(value['libhead']);
				setTimeout(function(){ 
		  				$.get('GetoffiName', {
		  					code : value['libhead']
		  			 		}, function(responseJson) {
		  			 			if(responseJson != null){
		  			 				$.each(responseJson, function(key,value) {
		  			 					$(".div-headLibname").text(value['patrName']);
		  			 				});
		  			 			}
		  			 	});
				}, 1000);
				
				$("#headLibExt").val(value['libheadext']);
				
				$("#profficer").val(value['prohead']);
				setTimeout(function(){ 
	  				$.get('GetoffiName', {
	  					code : value['prohead']
	  			 		}, function(responseJson) {
	  			 			if(responseJson != null){
	  			 				$.each(responseJson, function(key,value) {
	  			 					$(".div-profficername").text(value['patrName']);
	  			 				});
	  			 			}
	  			 	});
				}, 1000);
				$("#profficerExt").val(value['proext']);
				$("#logo").val(value['logo']);
				
				$("#acqhead").val(value['acqhead']);
				setTimeout(function(){ 
	  				$.get('GetoffiName', {
	  					code : value['acqhead']
	  			 		}, function(responseJson) {
	  			 			if(responseJson != null){
	  			 				$.each(responseJson, function(key,value) {
	  			 					$(".div-acqheadname").text(value['patrName']);
	  			 				});
	  			 			}
	  			 	});
				}, 1000);
				
				$("#acqext").val(value['acqext']);
				$("#gp1").val(value['acqclaims1']);
				$("#gp2").val(value['acqclaim2']);
				$("#gp3").val(value['acqclaim3']);
				$("#orderspan").val(value['orderspan']);
				
				var acqbprint = value['acqbprint'];
				switch(acqbprint){
			  		case state = 'Y':
			  			 $("#acqprint").prop('checked', true);
			  		break;
			  		case state = 'N':
			  			 $("#acqprint").prop('checked', false);
			  		break;
				}
				
				$("#cathead").val(value['cathead']);
				setTimeout(function(){ 
	  				$.get('GetoffiName', {
	  					code : value['cathead']
	  			 		}, function(responseJson) {
	  			 			if(responseJson != null){
	  			 				$.each(responseJson, function(key,value) {
	  			 					$(".div-catheadname").text(value['patrName']);
	  			 				});
	  			 			}
	  			 	});
				}, 1000);
				
				$("#catext").val(value['catext']);
				$("#marctype").val(value['marctype']);
				$("#itt").val(value['inxtrash']);
				
				$("#cirhead").val(value['cirhead']);
				setTimeout(function(){ 
	  				$.get('GetoffiName', {
	  					code : value['cirhead']
	  			 		}, function(responseJson) {
	  			 			if(responseJson != null){
	  			 				$.each(responseJson, function(key,value) {
	  			 					$(".div-cirheadname").text(value['patrName']);
	  			 				});
	  			 			}
	  			 	});
				}, 1000);
				
				$("#cirext").val(value['cirext']);
				$("#libweekend").val(value['libweekend']);
				$("#resvtime").val(value['resvtime']);
				$("#rcost").val(value['rcost']);
				$("#timecost").val(value['timescost']);
				
				var circmode = value['circmode'];
				switch(circmode){
					case state = 'I':
						$("input[name=circmode][value='I']").prop("checked",true);
	        	  		break;
	        	  	case state = 'E':
	        	  		$("input[name=circmode][value='E']").prop("checked",true);
	        	  		break;
				}
				
				$("#opaclimit").val(value['opaclimit']);
				
				var loadall = value['loadall'];
				switch(loadall){
					case state = 'Y':
						$("input[name=loadAll][value='Y']").prop("checked",true);
	        	  		break;
	        	  	case state = 'N':
	        	  		$("input[name=loadAll][value='N']").prop("checked",true);
	        	  		break;
				}
				
				var newaudate = value['newaudate'];
				switch(newaudate){
			  		case state = 'Y':
			  			 $("#newEnable").prop('checked', true);
			  		break;
			  		case state = 'N':
			  			 $("#newEnable").prop('checked', false);
			  		break;
				}
				
				var newalcrmat = value['newalcrmat'];
				switch(newalcrmat){
			  		case state = 'Y':
			  			 $("#newInc").prop('checked', true);
			  		break;
			  		case state = 'N':
			  			 $("#newInc").prop('checked', false);
			  		break;
				}
				
				$("#opacmsgtime").val(value['msgtimeout']);
				$("#sercHistory").val(value['opachistory']);
				
				$("#DateConfiguration").val(value['newaddate']);
				$("#Locationlist").val(value['newaloca']);
				$("#ItemCategorylist").val(value['newaicat']);
				$("#Conditionlist").val(value['newacond']);
				$("#SMDlist").val(value['newasmd']);
				$("#Statuslist").val(value['newadocs']);
				
				
				$("#serHead").val(value['serhead']);
				setTimeout(function(){ 
	  				$.get('GetoffiName', {
	  					code : value['serhead']
	  			 		}, function(responseJson) {
	  			 			if(responseJson != null){
	  			 				$.each(responseJson, function(key,value) {
	  			 					$(".div-serHeadname").text(value['patrName']);
	  			 				});
	  			 			}
	  			 	});
				}, 1000);
				
				$("#serext").val(value['serext']);
				
				var serbprint = value['serbprint'];
				switch(serbprint){
			  		case state = 'Y':
			  			 $("#serbprint").prop('checked', true);
			  		break;
			  		case state = 'N':
			  			 $("#serbprint").prop('checked', false);
			  		break;
				}
				
				$("#irsHead").val(value['irshead']);
				setTimeout(function(){ 
	  				$.get('GetoffiName', {
	  					code : value['irshead']
	  			 		}, function(responseJson) {
	  			 			if(responseJson != null){
	  			 				$.each(responseJson, function(key,value) {
	  			 					$(".div-irsHeadname").text(value['patrName']);
	  			 				});
	  			 			}
	  			 	});
				}, 1000);
				
				$("#irsext").val(value['irsext']);
				$("#irstype").val(value['irsmarctype']);
				
				var irsflag = value['irsflag'];
				switch(irsflag){
					case state = 'Y':
						$("input[name=irsflag][value='Y']").prop("checked",true);
	        	  		break;
	        	  	case state = 'N':
	        	  		$("input[name=irsflag][value='N']").prop("checked",true);
	        	  		break;
				}
				
				$("#finHead").val(value['finhead']);
				setTimeout(function(){ 
	  				$.get('GetoffiName', {
	  					code : value['finhead']
	  			 		}, function(responseJson) {
	  			 			if(responseJson != null){
	  			 				$.each(responseJson, function(key,value) {
	  			 					$(".div-finHeadname").text(value['patrName']);
	  			 				});
	  			 			}
	  			 	});
				}, 1000);
				
				$("#finext").val(value['finext']);
				$("#fund").val(value['fund']);
				
				
			});
		}else{
			$("#libName").prop("disabled", false); 
			$("#orgName").prop("disabled", false); 
			$("#brncCode, #town, #marctype, #libweekend, #irstype").prop("selectedIndex",-1);	
			$("#action").val("add");
		}
	});
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	//1) Head Librarian 
	// blur
	$("#headLib").blur(function(e){
		var headLib = $("#headLib").val();

		$(".div-headLibname").empty();
		////display officer name
		$.get('GetoffiName', {
        	code : headLib
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		swal("invalid");
		 		$("#headLib").val("");
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-headLibname").text(value['patrName']);
				});
			}
		});
	});
	//backspace
	$("#headLib").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-headLibname").empty();
		}
	});
	
	//2) PR Officer
	// blur
	$("#profficer").blur(function(e){
		var profficer = $("#profficer").val();

		$(".div-profficername").empty();
		////display officer name
		$.get('GetoffiName', {
        	code : profficer
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		swal("invalid");
		 		$("#profficer").val("");
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-profficername").text(value['patrName']);
				});
			}
		});
	});
	//backspace
	$("#profficer").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-profficername").empty();
		}
	});
	
	//3) Acquisition Head
	// blur
	$("#acqhead").blur(function(e){
		var acqhead = $("#acqhead").val();

		$(".div-acqheadname").empty();
		////display officer name
		$.get('GetoffiName', {
        	code : acqhead
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		swal("invalid");
		 		$("#acqhead").val("");
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-acqheadname").text(value['patrName']);
				});
			}
		});
	});
	//backspace
	$("#acqhead").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-acqheadname").empty();
		}
	});
	
	//4) Catalog Head
	// blur
	$("#cathead").blur(function(e){
		var cathead = $("#cathead").val();

		$(".div-catheadname").empty();
		////display officer name
		$.get('GetoffiName', {
        	code : cathead
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		swal("invalid");
		 		$("#cathead").val("");
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-catheadname").text(value['patrName']);
				});
			}
		});
	});
	//backspace
	$("#cathead").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-catheadname").empty();
		}
	});
	
	//5) Circ. Head
	// blur
	$("#cirhead").blur(function(e){
		var cirhead = $("#cirhead").val();

		$(".div-cirheadname").empty();
		////display officer name
		$.get('GetoffiName', {
        	code : cirhead
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		swal("invalid");
		 		$("#cirhead").val("");
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-cirheadname").text(value['patrName']);
				});
			}
		});
	});
	//backspace
	$("#cirhead").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-cirheadname").empty();
		}
	});
	
	//6) Serials Head
	// blur
	$("#serHead").blur(function(e){
		var serHead = $("#serHead").val();

		$(".div-serHeadname").empty();
		////display officer name
		$.get('GetoffiName', {
        	code : serHead
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		swal("invalid");
		 		$("#serHead").val("");
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-serHeadname").text(value['patrName']);
				});
			}
		});
	});
	//backspace
	$("#serHead").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-serHeadname").empty();
		}
	});
	
	//7) IRS Head
	// blur
	$("#irsHead").blur(function(e){
		var irsHead = $("#irsHead").val();

		$(".div-irsHeadname").empty();
		////display officer name
		$.get('GetoffiName', {
        	code : irsHead
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		swal("invalid");
		 		$("#irsHead").val("");
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-irsHeadname").text(value['patrName']);
				});
			}
		});
	});
	//backspace
	$("#irsHead").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-irsHeadname").empty();
		}
	});
	
	//8) Finance Controller
	// blur
	$("#finHead").blur(function(e){
		var finHead = $("#finHead").val();

		$(".div-finHeadname").empty();
		////display officer name
		$.get('GetoffiName', {
        	code : finHead
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		swal("invalid");
		 		$("#finHead").val("");
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-finHeadname").text(value['patrName']);
				});
			}
		});
	});
	//backspace
	$("#finHead").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-finHeadname").empty();
		}
	});
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//VALIDATE
	////////////
	$('#formdatalibInfo').bootstrapValidator({
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
        	libSymbol: {
                validators: {
                    notEmpty: {
                        message: 'Library Symbol is required'
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

     	var libName = $("#libName").val();
     	var orgName = $("#orgName").val();
     	var libSymbol = $("#libSymbol").val();
     	var brncCode = $("#brncCode").val();
     	var add1 = $("#add1").val();
     	var add2 = $("#add2").val();
     	var add3 = $("#add3").val();
     	var postcode = $("#postcode").val();
     	var town = $("#town").val();
     	var tel = $("#tel").val();
     	var fax = $("#fax").val();
     	var headLib = $("#headLib").val();
     	var headLibExt = $("#headLibExt").val();
     	var profficer = $("#profficer").val();
     	var profficerExt = $("#profficerExt").val();
     	
     	//acq
     	var acqhead = $("#acqhead").val();
     	var acqext = $("#acqext").val();
     	var gp1 = $("#gp1").val();
     	var gp2 = $("#gp2").val();
     	var gp3 = $("#gp3").val();
     	var orderspan = $("#orderspan").val();
     	
     	var acqprint = $('#acqprint').is(":checked");
     	if(acqprint == true){
     		acqprint = "Y";
    	}else{
    		acqprint = "N";
    	}
     	
     	//cat
     	var cathead = $("#cathead").val();
     	var catext = $("#catext").val();
     	var marctype = $("#marctype").val();
     	var itt = $("#itt").val();
     	
     	//cir
     	var cirhead = $("#cirhead").val();
     	var cirext = $("#cirext").val();
     	var libweekend = $("#libweekend").val();
     	var resvtime = $("#resvtime").val();
     	var rcost = $("#rcost").val();
     	var timecost = $("#timecost").val();
     	var circmode = $("input[name='circmode']:checked").val();
     	
     	//infotrack
     	var opaclimit = $("#opaclimit").val();
     	var loadAll = $("input[name='loadAll']:checked").val();
     	
     	var newEnable = $('#newEnable').is(":checked");
     	if(newEnable == true){
     		newEnable = "Y";
    	}else{
    		newEnable = "N";
    	}
     	
     	var newInc = $('#newInc').is(":checked");
     	if(newInc == true){
     		newInc = "Y";
    	}else{
    		newInc = "N";
    	}
     	
     	var opacmsgtime = $("#opacmsgtime").val();
     	var sercHistory = $("#sercHistory").val();
     	
     	//ser
     	var serHead = $("#serHead").val();
     	var serext = $("#serext").val();
     	
     	var serbprint = $('#serbprint').is(":checked");
     	if(serbprint == true){
     		serbprint = "Y";
    	}else{
    		serbprint = "N";
    	}

     	//irs
     	var irsHead = $("#irsHead").val();
     	var irsext = $("#irsext").val();
     	var irstype = $("#irstype").val();
     	var irsflag = $("input[name='irsflag']:checked").val();
     	
     	//fund
     	var finHead = $("#finHead").val();
     	var finext = $("#finext").val();
     	var fund = $("#fund").val();
     	
     	var action = $("#action").val();
     	
     	var DateConfiguration = $("#DateConfiguration").val();
     	var Locationlist = $("#Locationlist").val();
     	var ItemCategorylist = $("#ItemCategorylist").val();
     	var Conditionlist = $("#Conditionlist").val();
     	var SMDlist = $("#SMDlist").val();
     	var Statuslist = $("#Statuslist").val();
     	
     	var logo = $("#logoLib").val();
     	
     	if(action=="add"){
     		$.get("Handler_LibInfo",{
 				libName : libName, 
 				orgName : orgName,
 				libSymbol : libSymbol,
 				brncCode : brncCode,
 				add1 : add1,
 				add2 : add2,
 				add3 : add3,
 				postcode : postcode,
 				town : town,
 				tel : tel,
 				fax : fax,
 				headLib : headLib,
 				headLibExt : headLibExt,
 				profficer : profficer,
 				profficerExt : profficerExt,
 				acqhead : acqhead, 
 				acqext : acqext,
 				gp1 : gp1,
 				gp2 : gp2,
 				gp3 : gp3,
 				orderspan : orderspan,
 				acqprint : acqprint,
 				cathead : cathead,
 				catext : catext,
 				marctype : marctype,
 				itt : itt,
 				cirhead : cirhead,
 				cirext : cirext,
 				libweekend : libweekend,
 				resvtime : resvtime, 
 				rcost : rcost,
 				timecost : timecost,
 				circmode : circmode,
 				opaclimit : opaclimit,
 				loadAll : loadAll,
 				newEnable : newEnable,
 				newInc : newInc,
 				opacmsgtime : opacmsgtime, 
 				sercHistory : sercHistory,
 				serHead : serHead,
 				serext : serext,
 				serbprint : serbprint,
 				irsHead : irsHead,
 				irsext : irsext,
 				irstype : irstype,
 				irsflag : irsflag,
 				finHead : finHead,
 				finext : finext, 
 				fund : fund,
	  			action : "add",
	  			DateConfiguration : DateConfiguration,
	  			Locationlist: Locationlist,
	  			ItemCategorylist : ItemCategorylist,
	  			Conditionlist : Conditionlist,
	  			SMDlist : SMDlist,
	  			Statuslist : Statuslist,
	  			logo : logo
				},
				function(message){
					var status = message.replace(/\s+/g, '');
					if (status != "") {
						$('#formdatalibInfo').data('bootstrapValidator').resetForm();
						swal("Successfully");
						/*setTimeout(function(){ 
							 $('#formdatalibInfo a[href="tab_1"]').tab('show');
						}, 1000);*/
						
						//$("#tab_1").tab('show');
		     			//$('#formdatalibInfo').trigger('reset');
					}else{
						swal("Unsuccessfully");
					}
			});
     	}else if(action == "edit"){
     		$.get("Handler_LibInfo",{
     				libName : libName, 
     				orgName : orgName,
     				libSymbol : libSymbol,
     				brncCode : brncCode,
     				add1 : add1,
     				add2 : add2,
     				add3 : add3,
     				postcode : postcode,
     				town : town,
     				tel : tel,
     				fax : fax,
     				headLib : headLib,
     				headLibExt : headLibExt,
     				profficer : profficer,
     				profficerExt : profficerExt,
     				acqhead : acqhead, 
     				acqext : acqext,
     				gp1 : gp1,
     				gp2 : gp2,
     				gp3 : gp3,
     				orderspan : orderspan,
     				acqprint : acqprint,
     				cathead : cathead,
     				catext : catext,
     				marctype : marctype,
     				itt : itt,
     				cirhead : cirhead,
     				cirext : cirext,
     				libweekend : libweekend,
     				resvtime : resvtime, 
     				rcost : rcost,
     				timecost : timecost,
     				circmode : circmode,
     				opaclimit : opaclimit,
     				loadAll : loadAll,
     				newEnable : newEnable,
     				newInc : newInc,
     				opacmsgtime : opacmsgtime, 
     				sercHistory : sercHistory,
     				serHead : serHead,
     				serext : serext,
     				serbprint : serbprint,
     				irsHead : irsHead,
     				irsext : irsext,
     				irstype : irstype,
     				irsflag : irsflag,
     				finHead : finHead,
     				finext : finext, 
     				fund : fund,
		  			action : "edit",
		  			DateConfiguration : DateConfiguration,
		  			Locationlist: Locationlist,
		  			ItemCategorylist : ItemCategorylist,
		  			Conditionlist : Conditionlist,
		  			SMDlist : SMDlist,
		  			Statuslist : Statuslist,
		  			logo : logo
 				},
 				function(message){
 					var status = message.replace(/\s+/g, '');
 					if (status != "") {
 						$('#formdatalibInfo').data('bootstrapValidator').resetForm();
 						swal("Successfully");
 						setTimeout(function(){ 
 							$("#tab_1").click();
							// $('#formdatalibInfo a[href="tab_1"]').tab('show');
						}, 1000);
 		     			//
 					}else{
 						swal("Unsuccessfully");
 					}
 			});
     	}
     	
     	///kiv n new arrival
     	//var logo = $("#logo").val();
    
	});
	
	////////////////////////////////////////////////////////////////////////////////////////////
	// NEW ARRIVAL
	
	 $('#newArrival').on('show.bs.modal', function (event) {
		 
		//** Cuurent date
		var today = new Date();           
		var formattedtoday = moment(today).format("DD/MM/YYYY");
		$(".currentdate").text("pior to " +formattedtoday);
			
		//date configuration
		var dateconfig = $("#DateConfiguration").val();
		var arr = dateconfig.split('-');
		$("#daydate").val(arr[0]);
		$("#dwm").val(arr[1]);
		
		
		////// LOCATION
		$('#locaInfo').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
		    ajax: {
		        url: "GetLoca",
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            //SMD
		        	var lisLOCA = $("#Locationlist").val();
		    		var arr = lisLOCA.split(',');
		    		setTimeout(function(){ 
		    		$.each(arr, function(i, val){
		    				 var rows = $('#locaInfo').DataTable().rows({ 'search': 'applied' }).nodes();
		    				 $('input:checkbox[name="id[]"][value="'+val+'"]', rows).prop('checked', true);

		    			});
		    		});
		            for(var i=0;i< json.length; i++){
		              return_data.push({
		                'Location Code': '<input type="checkbox" name="id[]" value="'+"'"+json[i].Code+"'"+'">' +json[i].Code,
		                'Description' : json[i].Desc, 
		                'Branch Code' : json[i].brnc, 
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'Location Code'},
					{'data': 'Description'},
					{'data': 'Branch Code'},
				],
		});


		////// ITEM CATEGORY
		$('#icat').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
		    ajax: {
		        url: "GetItemCat",
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		        	var lisicat = $("#ItemCategorylist").val();
		    		var arr2 = lisicat.split(',');
		    		setTimeout(function(){ 
		    		$.each(arr2, function(i, val){
		    				 var rows = $('#icat').DataTable().rows({ 'search': 'applied' }).nodes();
		    				 $('input:checkbox[name="id2[]"][value="'+val+'"]', rows).prop('checked', true);

		    			});
		    		});
		           
		            for(var i=0;i< json.length; i++){
		              return_data.push({
		                'Code': '<input type="checkbox" name="id2[]" value="'+"'"+json[i].Code+"'"+'">' +json[i].Code,
		                'Description' : json[i].Desc, 
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'Code'},
					{'data': 'Description'},
				]
		});
		
		////// CONDITION
		$('#cond').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
		    ajax: {
		        url: "GetCond",
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		        	var listcon = $("#Conditionlist").val();
		    		var arr3 = listcon.split(',');
		    		setTimeout(function(){ 
		    		$.each(arr3, function(i, val){
		    				 var rows = $('#cond').DataTable().rows({ 'search': 'applied' }).nodes();
		    				 $('input:checkbox[name="id3[]"][value="'+val+'"]', rows).prop('checked', true);

		    			});
		    		});
		           
		            for(var i=0;i< json.length; i++){
		              return_data.push({
		                'Code': '<input type="checkbox" name="id3[]" value="'+"'"+json[i].Code+"'"+'">' +json[i].Code,
		                'Description' : json[i].Desc, 
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'Code'},
					{'data': 'Description'},
				]
		});
		
		////// SMD
		$('#SMD').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
		    ajax: {
		        url: "GetSmd",
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            //SMD
		        	var lisSMD = $("#SMDlist").val();
		    		var arr4 = lisSMD.split(',');
		    		setTimeout(function(){ 
		    		$.each(arr4, function(i, val){
		    				 var rows = $('#SMD').DataTable().rows({ 'search': 'applied' }).nodes();
		    				 $('input:checkbox[name="id4[]"][value="'+val+'"]', rows).prop('checked', true);

		    			});
		    		});
		            for(var i=0;i< json.length; i++){
		              return_data.push({
		                'Code': '<input type="checkbox" name="id4[]" value="'+"'"+json[i].Code+"'"+'">' +json[i].Code,
		                'Description' : json[i].Desc, 
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'Code'},
					{'data': 'Description'},
				],
		});
		
		////// STATUS
		$('#stat').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
		    ajax: {
		        url: "GetStat",
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            var liststat = $("#Statuslist").val();
		    		var arr5 = liststat.split(',');
		    		setTimeout(function(){ 
		    		$.each(arr5, function(i, val){
		    				 var rows = $('#stat').DataTable().rows({ 'search': 'applied' }).nodes();
		    				 $('input:checkbox[name="id5[]"][value="'+val+'"]', rows).prop('checked', true);

		    			});
		    		});
		           
		            for(var i=0;i< json.length; i++){
		              return_data.push({
		                'Code': '<input type="checkbox" name="id5[]" value="'+"'"+json[i].Code+"'"+'">' +json[i].Code,
		                'Description' : json[i].Desc, 
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'Code'},
					{'data': 'Description'},
				]
		});
		
	 })
	
	 
	 //////////////save arrival click
	 $('#savearr').on('click', function(){	
		 
		 $('#newArrival').modal('hide');
		 
		 var daydate = $("#daydate").val();
		 var dwm = $("#dwm").val();
		 $("#DateConfiguration").val(daydate +"-"+dwm);
		 
		 
		//ITEM CATEGORY
		 var arritemcate = [];
		 
		 $('#icat').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
				var itemcate = $(this).closest('tr').find('td:eq(0)').text();
				arritemcate.push(itemcate);
		 });
		 $("#ItemCategorylist").val(arritemcate.toString());
		 
		 //SMD
		 var arrsmd = [];
		 
		 //CONDITION
		 var arrcond = [];
		 
		 $('#cond').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
				var condition = $(this).closest('tr').find('td:eq(0)').text();
				arrcond.push(condition);
		 });
		 $("#Conditionlist").val(arrcond.toString());
		 
		 //SMD
		 var arrsmd = [];
		 
		 $('#SMD').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
				var smd = $(this).closest('tr').find('td:eq(0)').text();
				arrsmd.push(smd);
		 });
		 $("#SMDlist").val(arrsmd.toString());
		 
		 ///stat
		 var arrstat = [];
		 
		 $('#stat').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
				var status = $(this).closest('tr').find('td:eq(0)').text();
				arrstat.push(status);
		 });
		 $("#Statuslist").val(arrstat.toString());
		 
		 
	 });
	
});