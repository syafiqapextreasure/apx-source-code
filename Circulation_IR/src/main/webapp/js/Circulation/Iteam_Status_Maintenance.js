/**
 * 
 */
 
 	///////////////////////////////////////////FUNCTION getValue//////////////////////////////////////////////////
	
	function getValue(){
	var flag = $("input[name='optradio']:checked").val();
	var GL14PATR = $("#GL14PATRS").val();
	
	var url = "Table_PatrReqStat?GL14PATR=" + GL14PATR + "&FLAG=" + flag;
	
	 $.get(url,function(data){
			$("#table_patrreq").html(data);
	   });
}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

$(document).ready(function() {
	
	//Call error message page
	function messageBox(code, criteria, label){
	      var url = "Error_Page?GL79ERRCODE="+code+"" +
	      "&criteria=" + criteria + "&label="+label+"";
	      //alert(url);
	      $.ajax({
	            url: url,
	            success: function(result) {
	                  swal(result); //, 'warning' 
	            }
	      });
	}
	
	////////////////////today DATE //////////////////////////
	var today = new Date();  
	
	/////////// GET actiontype first time //////////////////////////////////////////////////////
	var action = $('input[name=actiontype]:checked').val();
	itemStatus();
	$("#forConly").hide();
	
	/*$("#modifybtn").hide();*/
	
	/////////// GET actiontype ONCHANGE ///////////////////////////////////////////////////////
	$('input[name=actiontype]').change(function(){
		
		var action = $('input[name=actiontype]:checked' ).val();
		
		switch(action) {
			case "itemStatus":
				itemStatus();
				clear();
				$("#AccessionNO").val('');
				break;
			case "recall":
				recall();
				clear();
				break;
			default:
		}
	});
	
	//////////////////  function itemStatus /////////////////////////////////////////////////////
	function itemStatus(){
		$("#statusCombineDetail").show();
		
		$("#modifybtn").hide();
		$("#bib").hide();
		$("#itemrecall").hide();
		$('#AccessionNO').attr('disabled', false);
		$("#AccessionNO").focus();
	}
	
	//////////////////function recall ///////////////////////////////////////////////////////
	function recall(){
		$('#GL14PATR').val('');
		setTimeout(function(){
	        $('#GL14PATR')[0].focus();
	    }, 1000);	
		$("#statusCombineDetail").hide();
		$("#modifybtn").hide();
		$("#bib").hide();
		$("#itemrecall").show();
		$("#CT03MATNO").prop("readonly", true);
		$('#circulationBtn, #recallBtn').attr('disabled', 'disabled');
		$('#patrstatusBtn, #patrLateReturn, #receipting').attr('disabled', 'disabled');
		$('#recallBranch, #accessionNo').prop('disabled', 'disabled');
		$('#searchcontrolNoBtn').prop('disabled', 'disabled');
	}
	
	///////////////function Clear //////////////////////////////////////////////////////////
	function clear(){
		$(".Status, .Currency, .ItemCategory, .ForeignCost, .SMD, .LocalCost, .Condition").empty();
		$(".CirculatedHits, .CopyNo, .Location, .Volume, .Branch, .ControlNo, .CreatedDate").empty();
		$(".ISBN, .ReleasedDate, .Publisher, .CallNumber, .Title, .Author").empty();
		$('#table-circulation').dataTable().fnClearTable();
		$('#table-eligibility').dataTable().fnClearTable();
		$('#table-related').dataTable().fnClearTable();
		
		$('#CT03MATNO, #recallBranch').val(''); //GL14PATR , #accessionNo
		$('.NAME').empty(); 
		$(".image--cover").attr('src','/Circulation_IR/resources/image/user_default.png');
		$(".borrowedBy, .recallStatus, .borrowedByC, .dueDate").empty();
		$('#recallBranch').prop('disabled', 'disabled');
		//$('#accessionNo').prop('disabled', 'disabled');
		$('#searchcontrolNoBtn').prop('disabled', 'disabled');
		$("#forConly").hide();
		$("#CT03MATNO").prop("readonly", true);
		$('option:gt(0)', '#accessionNo').remove();
		
		$('#circulationBtn').attr('disabled', 'disabled');
		$('#patrstatusBtn, #patrLateReturn, #receipting').attr('disabled', 'disabled');
		
		$("#modifybtn").hide();
		$("#bib").hide();
		
	}
	
	////////////////////////////////////////TAB Accession No AND BACKSPACE ////////////////////////////////////////////
	$("#AccessionNO").keydown(function(e){ 
		var action = $('input[name=actiontype]:checked' ).val();
		var code = e.keyCode || e.which;
		if(code == '9' ||code == '13' ){
			$('#table-circulation').dataTable().fnClearTable();
			$('#table-eligibility').dataTable().fnClearTable();
			$('#table-related').dataTable().fnClearTable();
			var AccessionNO = $("#AccessionNO").val();
			$.get('searchAccessionAtStatusMaint', {
				AccessionNO : $("#AccessionNO").val()
			 	}, function(responseJson) {
				if(responseJson != null){
					if(responseJson==''){
						//swal ("Invalid accession number");
						//swal("Invalid");
						messageBox("020","","");
						$("#modifybtn").hide();
						$("#bib").hide();
						$("#AccessionNO").val('');
						setTimeout(function(){
					        $('#AccessionNO')[0].focus();
					        clear();
					    }, 3000);
					}else{
						if(action == 'itemStatus'){
							$("#modifybtn").show();
							$("#bib").show();
						}else if(action == 'recall'){
							$("#modifybtn").hide();
							$("#bib").hide();
						}
					}
					$.each(responseJson, function(key,value) {
						if(value['DESC36'].toUpperCase() == 'CIRCULATED'.toUpperCase()){
							$("#forConly").show();
						}else if(value['DESC36'].toUpperCase() == 'ON LOAN'.toUpperCase()){
							$("#forConly").show();
						}else{
							$("#forConly").hide();
						}
						
						//release Date
						var outputRFCDATE;
						if(moment(value.RFCDATE).isValid() == false){
							outputRFCDATE = value.RFCDATE; 
						}else{
							outputRFCDATE = moment(value.RFCDATE).format("DD/MM/YYYY");
						}
					
						$(".Status").text(value['DESC36']);
						$(".Currency").text(value['DESC24']);
						$(".ItemCategory").text(value['DESC10']); 
						$(".ForeignCost").text((Number(value['FCOST'])).toFixed(2));  
						$(".SMD").text(value['DESC47']); 
						$(".LocalCost").text((Number(value['LCOST'])).toFixed(2)); 
						$(".Condition").text(value['DESC06']);
						$(".CirculatedHits").text(value['CIRHITS']);  
						$(".CopyNo").text(value['COPYNO']);  
						$(".Location").text(value['DESC05']);
						$(".Volume").text(value['VOLUME']); 
						$(".Branch").text(value['DESC71']);  
						$(".ControlNo").text(value['MATNO']);
						$(".CreatedDate").text(moment(value.CRDATE).format("DD/MM/YYYY"));
						$(".ISBN").text(value['ISBN']);
						$(".ReleasedDate").text(outputRFCDATE); 
						$(".Publisher").text(value['PUBLISHER']);
						$(".CallNumber").text(value['CALLNUMBER']);
						$(".Title").text(value['TITLE']);
						$(".borrowedByC").text(value['PATRNAME']);
						$(".dueDate").text(moment(value['DDATE03']).format("DD/MM/YYYY"));
					});
					
					var AccessionNO = $("#AccessionNO").val();
					//alert(AccessionNO);
					var cir = $('#table-circulation').DataTable( {
						destroy: true,
						searching: false,
						bLengthChange: false,
						columnDefs: [
							{
								"targets": [ 3 ],
								"visible": false,
								"searchable": false,
							},
							{
								"targets": [ 4 ],
								"visible": false,
								"searchable": false,
							},
							{
								"targets": [ 6 ],
								"visible": false,
								"searchable": false,
							},
							{
								"targets": [ 7 ],
								"visible": false,
								"searchable": false,
							},
							{
								"targets": [ 8 ],
								"visible": false,
								"searchable": false,
							},
							{
								"targets": [ 9 ],
								"visible": false,
								"searchable": false,
							},
							{
								"targets": [ 12 ],
								"visible": false,
								"searchable": false,
							},
							{
								"targets": [ 13 ],
								"visible": false,
								"searchable": false,
							}
						],
					    ajax: {
					        url: "searchAccessionAtStatusMaint2?AccessionNO="+AccessionNO,
					        type: "GET",
					        dataSrc: function (json) {
					            var return_data = new Array();
					            ///return date
				            	var outputRDATE;
				            	//return time 
								var outputRTIME;
					           
					            for(var i=0;i< json.length; i++){
									if(moment(json[i].RDATE).isValid() == false){
										outputRDATE = json[i].RDATE; 
									}else{
										outputRDATE = moment(json[i].RDATE).format("DD/MM/YYYY");
									} 
									
									if(moment(json[i].RTIME).isValid() == false){
										outputRTIME = json[i].RTIME; 
										//alert(outputRTIME + " outputRTIME 1");
									}else{
										outputRTIME = moment(json[i].RTIME, 'HH:mm:ss').format('HH:mm:ss');
										//alert(outputRTIME + " outputRTIME 3");
									}
									
					      
					              return_data.push({
					            	'No' : i+1,
					                'Borrower': json[i].PATR02 +' - '+ json[i].NAME,
					                'Charge Date' : moment(json[i].CDATE).format("DD/MM/YYYY"),  
					                'Charge Time' : moment(json[i].CTIME, 'HH:mm:ss').format('HH:mm:ss'), 
					                'Charge Staff' : json[i].OFFI +' - ' +json[i].OFFINAME,
					                'Due Date' : moment(json[i].DDATE02).format("DD/MM/YYYY"),   
					                'Due Time' : moment(json[i].DTIME, 'HH:mm:ss').format('HH:mm:ss'),
					                'Return Date' : moment(json[i].DIDATE).format("DD/MM/YYYY"),
					                'Return Time' : moment(json[i].DITIME, 'HH:mm:ss').format('HH:mm:ss'),
					                'Return Staff' : json[i].DIOFFI +' - '+ json[i].DIOFFINAME,
					                'Renew Date' : outputRDATE,
					                'Renew' : json[i].RENEW02,
					                'Renew Time' : outputRTIME,
					                'Renew Staff' : json[i].ROFFID +' - '+ json[i].ROFFIDNAME,
					                'Status' : json[i].FLAG,
					                'View Detail' : '<button type="button" class="btn btn-primary viewCir" id="viewCir" data-toggle="modal" data-target="#virCirDetail"><span class="fa fa-eye" style="color:white" title="View Details"></span></button>'
					              })
					             
					              
					            }
					            return return_data;
					            
					            
					          },
					     },//This is the end of the AJAX object from the example above
					     	columns    : [
					     	    {'data': 'No'},
								{'data': 'Borrower'},
								{'data': 'Charge Date'},
								{'data': 'Charge Time'},
								{'data': 'Charge Staff'},
								{'data': 'Due Date'},
								{'data': 'Due Time'},
								{'data': 'Return Date'},
								{'data': 'Return Time'},
								{'data': 'Return Staff'},
								{'data': 'Renew Date'},
								{'data': 'Renew'},
								{'data': 'Renew Time'},
								{'data': 'Renew Staff'},
								{'data': 'Status'},
								{'data': 'View Detail'}
							],
						
							
					});
					
					$('#table-circulation').on( 'click', '#viewCir', function () {
						
						e.preventDefault();  

					    var currentRow = $(this).closest("tr");

					    var data = $('#table-circulation').DataTable().row(currentRow).data();
					    
					    if(data["Status"] == "Charged"){
							 $(".returnDate").hide();
							 $(".returnTime").hide();
							 $(".returnStaff").hide();
							 $(".renewDate").hide();
							 $(".renewTime").hide();
							 $(".renewStaff").hide();
						 }else{
							 $(".returnDate").show();
							 $(".returnTime").show();
							 $(".returnStaff").show();
							 $(".renewDate").show();
							 $(".renewTime").show();
							 $(".renewStaff").show();
						 }
					    
					    
					    $(".chargeDate").text(data["Charge Date"]);
						 $(".chargeTime").text(data["Charge Time"]);
						 $(".chargeStaff").text(data["Charge Staff"]);
						 $(".returnDate").text(data["Return Date"]);
						 $(".returnTime").text(data["Return Time"]);
						 $(".returnStaff").text(data["Return Staff"]);
						 $(".renewDate").text(data["Renew Date"]);
						 $(".renewTime").text(data["Renew Time"]);
						 $(".renewStaff").text(data["Renew Staff"]);

					     $('#detailCirculationModal').modal('show'); 

						/*var data = cir.row($(this).closest('tr')).data();
						alert("2");
						//alert(data + "dsds")
					    //alert(data["Charge Date"]);
						
							
						alert("3");
						 $(".chargeDate").text(data["Charge Date"]);
						 alert("4");
						 $(".chargeTime").text(data["Charge Time"]);
						 alert("5");
						 $(".chargeStaff").text(data["Charge Staff"]);
						 alert("6");
						 $(".returnDate").text(data["Return Date"]);
						 alert("7");
						 $(".returnTime").text(data["Return Time"]);
						 alert("8");
						 $(".returnStaff").text(data["Return Staff"]);
						 alert("9");
						 $(".renewDate").text(data["Renew Date"]);
						 alert("10");
						 $(".renewTime").text(data["Renew Time"]);
						 alert("11");
						 $(".renewStaff").text(data["Renew Staff"]);
						 alert("12");
				        
						 $('#detailCirculationModal').modal('show'); 
						 alert("13");*/
				    } );
	
					$('#table-related').DataTable( {
						destroy: true,
						searching: false,
						bLengthChange: false,
					    ajax: {
					        url: "searchAccessionAtStatusMaint3?AccessionNO="+AccessionNO,
					        type: "GET",
					        dataSrc: function (json) {
					            var return_data = new Array();
					           
					            for(var i=0;i< json.length; i++){
					              return_data.push({
					            	'No' : i+1,
					                'Accession No': json[i].DOCNO,
					                'Copy No' : json[i].COPYNO, 
					                'Status' : json[i].STATDESC,
					                'Branch' : json[i].BRANCH,
					                'Location' : json[i].LOCATION,
					                'Item Category' : json[i].ITEMCATEGORY,
					                'SMD' : json[i].DESCSMD,
					                'Volume' : json[i].VOLUME
					              })
					            }
					            return return_data;
					          },
					     },//This is the end of the AJAX object from the example above
					     	columns    : [
					     	    {'data': 'No'},
								{'data': 'Accession No'},
								{'data': 'Copy No'},
								{'data': 'Status'},
								{'data': 'Branch'},
								{'data': 'Location'},
								{'data': 'Item Category'},
								{'data': 'SMD'},
								{'data': 'Volume'}
							]
					});
					
					
					$('#table-eligibility').DataTable( {
						destroy: true,
						searching: false,
						bLengthChange: false,
					    ajax: {
					        url: "searchAccessionAtStatusMaint4?AccessionNO="+AccessionNO,
					        type: "GET",
					        dataSrc: function (json) {
					            var return_data = new Array();
					           
					            for(var i=0;i< json.length; i++){
					              return_data.push({
					            	'No' : i+1,
					                'Patron Category': json[i].DESCCATE,
					                'Item Catgory' : json[i].DESCICATE, 
					                'SMD' : json[i].DESCSMD,
					                'Branch' : json[i].DESCBRNC,
					                'Loan Period' : json[i].PLOAN,
					                'Eligibility' : json[i].ELIG,
					                'Period Type' : json[i].PTYPE,
					                'Renew' : json[i].RENEW27
					              })
					            }
					            return return_data;
					          },
					     },//This is the end of the AJAX object from the example above
					     	columns    : [
					     	    {'data': 'No'},
								{'data': 'Patron Category'},
								{'data': 'Item Catgory'},
								{'data': 'SMD'},
								{'data': 'Branch'},
								{'data': 'Loan Period'},
								{'data': 'Eligibility'},
								{'data': 'Period Type'},
								{'data': 'Renew'}
							],
							
					});
					
					
				}
			});
			event.preventDefault();	
		}else if(code == '8'){
			clear();
		}
	});
	

	
	////////////////////////////////////////// TABEL table-circulation ////////////////////////////////////////////
	var table1 = $('#table-circulation').DataTable({
		responsive: true,
		info: false,
		paging: false,
		ordering: false,
	});

	
	//////////////////////////////////////////TABEL table-related ////////////////////////////////////////////
	var table2 = $('#table-related').DataTable({
		responsive: true,
		scrollx: true,
		info: false,
		paging: false,
		ordering: false,
	});
	
	//////////////////////////////////////////TABEL table-eligibility ////////////////////////////////////////////
	var table3 = $('#table-eligibility').DataTable({
		responsive: true,
		scrollx: true,
		info: false,
		paging: false,
		ordering: false,
	});
	
	///////////////////////////// CLICK TAB CIR ////////////////////////
	
	
	/////////////////////////CLICK BTN MODIFY ///////////////////////////////////////////////////
	
	$("#modifybtn").click(function(){
		//var action = $( 'input[name=actiontype]:checked' ).val();
		var AccessionNO = $("#AccessionNO").val();
		var Status = $(".Status").text();
		//$.get("view?action=" + action,function(data){
		//if(action == 'modify'){
			$.get("modify?AccessionNO=" +AccessionNO,function(data){
				Status:Status;
				$("#modifyContent").html(data);
				$("#btnOK").attr("disabled","disabled");
				
			});
		//}
	});
	
	/////////////////////////CLICK BTN BIB ///////////////////////////////////////////////////
	
	$("#bib").click(function(){
		//var action = $( 'input[name=actiontype]:checked' ).val();
		var ControlNo = $(".ControlNo").text();
		$.get("bib?ControlNo=" +ControlNo,function(data){
			// action : action
			$("#bibContent").html(data);
		});
	});
	
	///////////////////////// TAB GL14PATR ///////////////////////////////////////////////////
	$("#GL14PATR").keydown(function(e){
		var code = e.keyCode || e.which;
		if(code == '9' || code == '13'){
			$.get('searchPatrBYTab', {
				 GL14PATR : $("#GL14PATR").val()
		 	}, function(responseJson) {
			if(responseJson != null){
				if(responseJson==''){
					messageBox("036","",""); 
					clear();
					$('#GL14PATR').val('');
					$("#CT03MATNO").prop("readonly", true);
					setTimeout(function(){
				        $('#GL14PATR')[0].focus();
				    }, 3000);	
					
				}
				$.each(responseJson, function(key,value) {
					var EXPDATE =  moment(value['EXPDATE']).format("DD/MM/YYYY");
					var today2 = moment(today2).format("DD/MM/YYYY");
					var TotalFinesOutstanding = value['totalcharged'] - value['totalpaid'];
					var finelimit = value['FINELIMIT'];
					if(value['FINELIMIT'] == " "){
						finelimit = 0.00;
					}else{
						finelimit = value['FINELIMIT'];
					}
					
					/*alert("fndlimit" + finelimit);
					alert("totalcharged " + value['totalcharged']);
					alert("totalpaid " + value['totalpaid']);
					alert("TotalFinesOutstanding : " +TotalFinesOutstanding)
					alert(TotalFinesOutstanding >= finelimit && finelimit != 0.00);*/ 

					/*alert(EXPDATE);
					alert(today2);
					alert(moment(today).format("YYYY-MM-DD") > moment(value['EXPDATE']).format("YYYY-MM-DD"));*/
					if(moment(today).format("YYYY-MM-DD") > moment(value['EXPDATE']).format("YYYY-MM-DD")){
						
						messageBox("032","","");
						setTimeout(function(){
					        $('#GL14PATR')[0].focus();
					        $('.NAME').empty();
							$('#GL14PATR').val('');
							$("#CT03MATNO").prop("readonly", true);
							$('#searchcontrolNoBtn').prop('disabled', true);
							$(".image--cover").attr('src','/Circulation_IR/resources/image/user_default.png');
					    }, 1000);
						
					}else if(value['ACTION8RECALL'] == 'N' || value['ALLOWRSV'] == 'N'){
						messageBox("104","","");
						setTimeout(function(){
					        $('#GL14PATR')[0].focus();
					        $('.NAME').empty();
							$('#GL14PATR').val('');
							$("#CT03MATNO").prop("readonly", true);
							$('#searchcontrolNoBtn').prop('disabled', true);
							$(".image--cover").attr('src','/Circulation_IR/resources/image/user_default.png');
					    }, 1000);
					}else if(TotalFinesOutstanding >= finelimit && finelimit != 0.00){
						messageBox("082","","");
						setTimeout(function(){
					        $('#GL14PATR')[0].focus();
					        $('.NAME').empty();
							$('#GL14PATR').val('');
							$("#CT03MATNO").prop("readonly", true);
							$('#searchcontrolNoBtn').prop('disabled', true);
							$(".image--cover").attr('src','/Circulation_IR/resources/image/user_default.png');
					    }, 1000);
						
					}
					$('.NAME').text(value['NAME14']);
					$('#circulationBtn').attr('disabled', false);
					$('#patrstatusBtn, #patrLateReturn, #receipting').attr('disabled', false);
					$("#CT03MATNO").prop("readonly", false);
					$("#CT03MATNO").focus();
					$('#searchcontrolNoBtn').prop('disabled', false);
				});
			}
			});
			event.preventDefault();
		}else if(code == '8'){
			clear();
			
		}
	});
	
	/////////////////////////CLICK BTN circulation ///////////////////////////////////////////////////
	
	$("#circulationBtn").click(function(){
		var GL14PATR = $("#GL14PATR").val();
		$.get("circulationStatus?GL14PATR="+GL14PATR,function(data){
			// action : action
			$("#circulationContent").html(data);
		});
	});
	
/////////////////////////CLICK BTN receipting ///////////////////////////////////////////////////
	$("#receipting").click(function(){
	    var GL14PATR= document.getElementById("GL14PATR").value;
	    
	    /*if(!GL14PATR){
			 GL14PATR =$('#hiddenPatrId').val();
		 }*/
		var popup;
		var protocol =  window.location.protocol;
		//var url = $("#urlreceipt").attr('href');
		//var host2 = location.protocol + "//" + window.location.hostname + ":" + location.port + "/web/guest/maintenance1";
		//var host2 = location.protocol + "//" + window.location.hostname + ":" + location.port + "/Receipting/template?MODULE=Receipting&ACTION=main.jsp";
		//alert(host2);
		var host = protocol + "//" + window.location.host + "/web/guest/maintenance1";;
		/*popup = window.open(host+"?patrid="+ GL14PATR, '');*/
		popup = window.open(host + '?patrid='+ GL14PATR, '_blank');
   })
	
	/////////////////////////CLICK BTN LATE RETURN HISTORY ///////////////////////////////////////////////////
	
	$("#patrLateReturn").click(function(){
		 var GL14PATR= document.getElementById("GL14PATR").value;
		 //alert(GL14PATR);
		 
		/* if(!GL14PATR){
			 GL14PATR =$('#hiddenPatrId').val();
		 }*/
	
		 var url = "Modal_LateReturnHistory?GL14PATR=" + GL14PATR;
			 $.get(url,function(data){
				/// 	alert(data)
					$("#lateReturnTable").html(data);
		 });
	 });
	
	///////////////////////// TAB Control No ///////////////////////////////////////////////////
	$("#CT03MATNO").keydown(function(e){
		var code = e.keyCode || e.which;
		if(code == '9' || code == '13'){
			$('option:gt(0)', '#accessionNo').remove();
			$.get('recall', {
				controlNo : $("#CT03MATNO").val()
		 	}, function(responseJson) {
		 		if(responseJson != null){
					if(responseJson==''){
						messageBox("093","",""); 
						setTimeout(function(){
					        $('#CT03MATNO')[0].focus();
					        $('option:gt(0)', '#accessionNo').remove();
							$('#accessionNo').prop('disabled', 'disabled');
							$('#recallBtn').attr('disabled', 'disabled');
							$("#recallBranch").val('');
							$('.title, .recallStatus').empty();
							$('.borrowedBy').empty();
					    }, 1500);					
					}else{
						$('#accessionNo').prop('disabled', false);
						$('#recallBranch').prop('disabled', false);
						$.each(responseJson, function(key,value) {
							$('.title').text(value['LOAN']);
							 $("#accessionNo").append("<option value='"+value.LOST+"'>" + value.LOST + "</option>");
						});
					}
				}
		 	});
			event.preventDefault();
		}else if(code == '8'){
			//$("#accessionNo").val('');
			$('option:gt(0)', '#accessionNo', '#recallBranch').remove();
			$('#accessionNo, #recallBranch').prop('disabled', 'disabled');
			$("#recallBranch").val('');
			$('.title, .recallStatus').empty();
			$('.borrowedBy').empty();
			
			/*setTimeout(function(){
		        $('#CT03MATNO')[0].focus();
		    }, 1500);*/
		}
	});
	
	////////////////////////////////////////ONCHANGE accessionNo //////////////////////////////////////
	$('#accessionNo').on('change', function() {
	    $(this).find(":selected").val();
	    //alert($("#GL14PATR").val());
	    //alert($(this).find(":selected").val()=="Please Select");
	    /*alert($("#controlNo").val());*/
	    //alert($(this).find(":selected").val());
	    if($(this).find(":selected").val()=="Please Select"){
	    	$('#recallBtn').attr('disabled', 'disabled');
	    	$(".title, .borrowedBy, .recallStatus").empty();
	    }else{
	    $.get('detailRecall', {
	    	controlNo : $("#CT03MATNO").val(),
			accessionNo : $(this).find(":selected").val(),
			patr: $("#GL14PATR").val() 
	 	}, function(responseJson) {
	 		if(responseJson != null){
	 			
	 			
				if(responseJson==''){
					
					messageBox("100","",""); 
					//swal("testing");
					$('option:gt(0)', '#accessionNo').remove();
	 				$('#accessionNo').prop('disabled', 'disabled');
	 				$("#recallBranch").val('');
	 				$('.title, .recallStatus').empty();
	 				$('.borrowedBy').empty();	
	 				$('#recallBtn').attr('disabled', 'disabled');
				}/*else if(($("#GL14PATR").val()== value['PATR03'])){
					alert("value sama");
				}*/else{
					$.each(responseJson, function(key,value) {
						if(($("#GL14PATR").val()== value['PATR03'])){
							messageBox("109","",""); 
							//alert("An item of the same title has been changed to this patron");
							$("#CT03MATNO").val('');
							$('option:gt(0)', '#accessionNo').remove();
			 				$('#accessionNo').prop('disabled', 'disabled');
			 				$("#recallBranch").val('');
			 				$('.title, .recallStatus').empty();
			 				$('.borrowedBy').empty();	
			 				$('#recallBtn').attr('disabled', 'disabled');
						}else if(value['ALLOWRSV'] == 'N'){
							messageBox("100","",""); 
							//swal("testing");
							$('option:gt(0)', '#accessionNo').remove();
			 				$('#accessionNo').prop('disabled', 'disabled');
			 				$("#recallBranch").val('');
			 				$('.title, .recallStatus').empty();
			 				$('.borrowedBy').empty();	
			 				$('#recallBtn').attr('disabled', 'disabled');
						}else if(value['BRANCH'] != value['ITEMBRNC']){
							messageBox("110","",""); 
							//swal("Item location not matching the patron location");
							$('option:gt(0)', '#accessionNo').remove();
			 				$('#accessionNo').prop('disabled', 'disabled');
			 				$("#recallBranch").val('');
			 				$('.title, .recallStatus').empty();
			 				$('.borrowedBy').empty();	
			 				$('#recallBtn').attr('disabled', 'disabled');
						}else if(value['RESSTAT'] == 'X'){
							messageBox("111","",""); 
							//swal("Patron already reserved");
							$('option:gt(0)', '#accessionNo').remove();
			 				$('#accessionNo').prop('disabled', 'disabled');
			 				$("#recallBranch").val('');
			 				$('.title, .recallStatus').empty();
			 				$('.borrowedBy').empty();	
			 				$('#recallBtn').attr('disabled', 'disabled');
						}/*else if(){
						}*/else{
						//$('.title').text(value['TITLE']);
						$('.borrowedBy').text(value['PATRNAME'] +" , "+ value['PATR03']);
						$('#recallBtn').attr('disabled', false);
						}
					});
				}
			}
	 	});
	    }
	});
	
	/////////////////////////////// accessionNo  AND recallBranch ////////////////////////////////////
	/*if($(this).find(":selected").val()=="Please Select" && )*/
	
	
	/////////////////////////CLICK BTN PATR STATUS///////////////////////////////////////////////////
	
	$("#patrstatusBtn").click(function(){
		var GL14PATR = $("#GL14PATR").val();
		/*$("#patrStatusContent").html(data);*/
		$.get("PatrDetailsModal?GL14PATR="+GL14PATR,function(data){
			// action : action
			$("#patrStatusContent").html(data);
		});
	});
	
	//////////////////////////////////////////////////////////////////
	
	
	///////////////////// Click Btn RECALL recallBtn///////////////////////////////////////////
	$("#recallBtn").click(function(){
		//alert("recallBtn");
		var docno = $("#accessionNo option:selected" ).text();
		var patr = $("#GL14PATR").val();
		var matno = $("#CT03MATNO").val();
		var accessionNo = $("#accessionNo").val();
		var recallBranch = $("#recallBranch").val();
		var borrowedBy = $(".borrowedBy").text();
		var getPatr = borrowedBy.substr(borrowedBy.indexOf(",") + 1).trim();
		//alert("docno:"+docno + " /n patr="+patr+" /n matno="+matno +" /n accessionNo" + accessionNo + "/n recallBranch" +recallBranch);
		//alert("jj"+getPatr);
		
		$.post("updateRecall", {
			docno:docno,
			matno:matno,
			accessionNo:accessionNo,
			recallBranch:recallBranch,
			getPatr:patr,
			id : $("#liferayLogin").val()
		},function(data,status){
			
			$.get("print",function(data){
				messageBox("102","","");
				clear();
				window.open("GeneratePreviewDocument?docno="+docno+"&id="+$("#liferayLogin").val(), "", "");///
			});
			}
		).fail(function(data){
			swal("fail");
		}).success(function(data){
			/*$.get("print",function(data){
				messageBox("102","","");
				clear();
				window.open("recallLetter?docno="+docno, "", "");
			});*/
		});
		
		
	});
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	$(".search").click(function(){
		var url = "Modal_PatrSearch?action=charge";
		$.get(url,function(data){
			$("#searchPatrContent").html(data);
		});
	});
});