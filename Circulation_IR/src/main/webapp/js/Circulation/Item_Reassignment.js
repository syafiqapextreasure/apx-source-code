/**
 * 
 */

$(document).ready(function() {
	
	//////////////focus //////////////////////////////////////////////////////////////////////
	$("#GL14PATR" ).focus();
	
	$("#CI07SICAT").show();
	$("#LOCA03").hide();
	$("#BRNC71").hide();
	
	var addDate = $("#addDate").val(); ///get FLAG2 FUNC99 ='REASSIGNEDGRACE' val
	var setReassignmentEnd = moment().add(addDate, 'day').format("DD/MM/YYYY");
	//alert(moment().add(addDate, 'day').format("DD/MM/YYYY"));
	//moment().format('L');
	
	///////////////////// GET TODAY DATE ///////////////////////////////////////////////////////
	var today = new Date();           
    var formattedtoday = today.getDate() + '/' + (today.getMonth() + 1) + '/' + today.getFullYear();
	
	/////////// GET actiontype first time //////////////////////////////////////////////////////
	var action = $('input[name=actiontype]:checked').val();
	categoryDiv();
	
	/////////// GET actiontype ONCHANGE ///////////////////////////////////////////////////////
	$('input[name=actiontype]').change(function(){
		
		$('#AccessionNo, #icat, #rStops, #PLOCA13').val('');
		$('#CallNo, #Title, #DESC10, #Starts, #rOn, #oriItemCat, #after').empty();
		$("#AccessionNo").prop("readonly", false);
		$("#AccessionNo" ).focus();
		$('.input-group-addon').attr("disabled", false);
		$('#DESC05, #LOCA03, #DESC71, #BRNC71, #ReassignedBranch').empty();
		
		var action = $('input[name=actiontype]:checked' ).val();
		
		switch(action) {
			case "category":
				$("#GL14PATR").focus();
				clear();
				
				categoryDiv();
				break;
			case "branch":
				$("#GL14PATR").focus();
				clear();
				
				branchDiv();
				break;
			default:
				//reload(action);
		}
	});
	
	//////////////////  ONCLICK DIV CATEGORY /////////////////////////////////////////////////
	function categoryDiv(){
		$('#GL14PATR').val('');
		$("#GL14PATR" ).focus();
		$("#locaInfo, #locaInfo2").hide();
		$("#itemInfo3").show();
		$("#AccessionNo").prop("readonly", true);
		$("#rStops").attr("disabled", true);
		$("#oriItemCat, #reassign, #unassign").hide();
		$('.input-group-addon').attr("disabled", true);
		$('#icat').prop('disabled', 'disabled');
		$("#icat2, #locaInfo, #locaInfo2").hide();
	}
	
	//////////////////ONCLICK DIV BRANCH /////////////////////////////////////////////////////
	function branchDiv(){
		$('#GL14PATR').val('');
		$("#GL14PATR" ).focus();
		$("#locaInfo, #locaInfo2").show();
		$("#itemInfo3, #reassign, #unassign").hide();
		$("#AccessionNo").prop("readonly", true);
		$("#rStops").attr("disabled", true);
		$('#PLOCA13').prop('disabled', 'disabled');
		$('.input-group-addon').attr("disabled", true);
		$("#PLOCA132").hide();
	}
	
	///////////////////////// TAB GL14PATR ///////////////////////////////////////////////////
	//keyboard tab
	$("#GL14PATR").keydown(function(e){
		
		var code = e.keyCode || e.which;
		
		if(code == '9' || code == '13'){
			 $.get('searchPatrBYTab', {
				 GL14PATR : $("#GL14PATR").val()
			 	}, function(responseJson) {
				if(responseJson != null){
					if(responseJson==''){
						//alert("Patron Id is Invalid");
						//swal ("Patron Id is Invalid");
						messageBox("036","",""); 
						clear();
						$('#GL14PATR').val('');
						$("#AccessionNo").prop("readonly", true);
						$('.input-group-addon').attr("disabled", true);
						setTimeout(function(){
					        $('#GL14PATR')[0].focus();
					    }, 3000);
						//$("#GL14PATR")[0].focus();
						/*$(".swal-button swal-button--confirm").click(function(){
							alert("dsds");
						});*/
						
					}
					else{
					$.each(responseJson, function(key,value) {
						//alert(value['action01'])
						var EXPDATE =  moment(value['EXPDATE']).format("DD/MM/YYYY"); //moment(value['EXPDATE'],'DD/MM/YYYY').format('YYYY-MM-DD');
						if(moment(today).format("YYYY-MM-DD") > moment(value['EXPDATE']).format("YYYY-MM-DD")){
							//swal("Patron membership has expired");
							messageBox("032","","");
							setTimeout(function(){
						        $('#GL14PATR')[0].focus();
						    }, 3000);
							//$("#GL14PATR" ).focus();
							$("#GL14PATR" ).val("");
							$("#AccessionNo").prop("readonly", true);
							$("#rStops").attr("disabled", true);
							$(".Name, .Status, .PatronCategory, .GroupId, .ExpiringDate").empty();
						}else if(value['action01'].trim()==""){
							
							messageBox("112",value['DESC08'],"@status");
							setTimeout(function(){
						        $('#GL14PATR')[0].focus();
						    }, 3000);
							//$("#GL14PATR" ).focus();
							$("#GL14PATR" ).val("");
							$("#AccessionNo").prop("readonly", true);
							$("#rStops").attr("disabled", true);
							$(".Name, .Status, .PatronCategory, .GroupId, .ExpiringDate").empty();
						}					
						/*else if(value['DESC08'] != "SATISFACTORY"){
							messageBox("112","","");
							setTimeout(function(){
						        $('#GL14PATR')[0].focus();
						    }, 3000);
							//$("#GL14PATR" ).focus();
							$("#GL14PATR" ).val("");
							$("#AccessionNo").prop("readonly", true);
							$("#rStops").attr("disabled", true);
							$(".Name, .Status, .PatronCategory, .GroupId, .ExpiringDate").empty();
							//alert("ONLY SATISFACTORY");
						}*/else{
							$('.Name').text(value['NAME14']);
							$('.Status').text(value['DESC08']);
							$('.PatronCategory').text(value['DESC07']);
							$('.GroupId').text(value['NAME02']);
							$('.ExpiringDate').text(moment(value['EXPDATE']).format("DD/MM/YYYY"));
							$("#AccessionNo").prop("readonly", false);
							$("#AccessionNo" ).focus();
							$('.input-group-addon').attr("disabled", false);
						}
					});
					}
				}
			});
			 event.preventDefault();}
		else if(code == '8'){
			clear();
		}
     });
	
	//////////////////////////////////////// TAB AccessionNo //////////////////////////////////////////// 
	///search tab DOCNO
	$("#AccessionNo").keydown(function(e){
		$("#after").empty();
		var action = $('input[name=actiontype]:checked' ).val();
		var code = e.keyCode || e.which;
		if((code == '9' || code == '13')) {
			if(action == "category"){
				$.get('searchAccessionNoBYTab', {
					AccessionNo : $("#AccessionNo").val(),
					id :$("#liferayLogin").val()
				 	}, function(responseJson) {
					if(responseJson != null){
						if(responseJson==''){
							//alert("The item is either in circulation or unavailable for reassignment");
							//swal ("The item is either in circulation or unavailable for reassignment");
							messageBox("090","","");
							setTimeout(function(){
						        $('#AccessionNo')[0].focus();
						    }, 3000);
							$("#reassign, #unassign").hide();
							$("#AccessionNo, #icat2input, #rStops").val('');
							$('#CallNo, #Title, #oriItemCat, #Starts, #rOn, #DESC10, #after').empty(); //, #CI07SICAT
							$('#icat2').hide();
							$('#icat').show();
							$('#icat').prop('disabled', 'disabled');
							//$('.input-group-addon').attr("disabled", true);
							$("#rStops").prop("readonly", true);
							//$("#CT03DOCNO" ).focus();
						}
						$.each(responseJson, function(key,value) {
							//alert((value['CT03ICAT']));
							if(value['ACCESSION07'] != null){
								
								/*alert("Please click the Unassign button to " +
										"restore the original item category.");*/
								messageBox("091","","");
								$('#CallNo').text(value['CALLNUMBER']);
								$('#Title').text((value['TITLE']));
								$('#oriItemCat').text(value['SICAT']);
								$('#DESC10').text(value['SICATDESC']); //SICATDESC
								$("#icat").hide();
								$("#icat2").show();
								$("#CI07SICAT").text(value['SICAT']);
								$('#icat2input').val(value['PICATDESC']);//PICATDESC   
								$("#reassign").hide();
								$("#unassign").show();
								$('#Starts').text(moment(value['DTSTART07']).format("DD/MM/YYYY"));  
								$('#rStops').val(moment(value['DTEND07']).format("DD/MM/YYYY"));
								$("#rStops").prop("readonly", true);//formattedtoday
								$('#rOn').text(moment(today).format("DD/MM/YYYY")+" by " + value['loginmane'] + ", " + $("#liferayLogin").val());
								$('#after').text($("#AccessionNo").val() + " has been unassigned from '" 
										+ $("#icat2input").val()+"' to it original '"+$("#DESC10").text()+"' item category");
							}else{
								//$("#reassign").show();
								$("#unassign").hide();
								$("#icat2").hide();
								$("#icat").show();
								$("#rStops").prop("readonly", false);
								$('#dateStop').attr('readonly',false).datepicker({
									startDate : today,
									format: "dd/mm/yyyy",
									todayBtn: true,
									autoclose: true,
									todayHighlight: true,
								});
								
								$('#icat').prop('disabled', false);
								$('#oriItemCat').text(value['ICAT']);
								$('#DESC10').text(value['DESC10']);
								$('#Title').text(value['TITLE']);
								$('#CallNo').text(value['CALLNUMBER']);
								$('#Starts').text(moment(today).format("DD/MM/YYYY"));
								$("#dateStop").datepicker("setDate", setReassignmentEnd);
								//$('#rStops').val(formattedtoday);
								///$('#rOn').text(moment(today).format("DD/MM/YYYY")+" by " + $(".Name").text() + ", " + $("#GL14PATR").val());
								$("#rStops").attr("disabled", false);
							}
						});
					}
				});
			}else if(action == "branch"){
				$.get('searchAccessionNoBYTabBranch', {
					AccessionNo : $("#AccessionNo").val()
				 	}, function(responseJson) {
					if(responseJson != null){
						if(responseJson==''){
							//alert("The item is either in circulation or unavailable for reassignment");
							//swal ("The item is either in circulation or unavailable for reassignment");
							messageBox("090","","");
							setTimeout(function(){
						        $('#AccessionNo')[0].focus();
						    }, 3000);
							$("#reassign, #unassign, #PLOCA132").hide();
							$("#PLOCA13").show();
							$("#AccessionNo, #rStops, #DESC71, #PLOCA13").val('');
							$('#CallNo, #Title, #Starts, #rStops, #after, #DESC71, #BRNC71').empty();
							$('#ReassignedBranch, #DESC05').empty();
							$('#PLOCA13').prop('disabled', 'disabled');
							$('.input-group-addon').attr("disabled", true);  
							$("#rStops").attr("disabled", true);
							//$("#CT03DOCNO" ).focus();
						}
						$.each(responseJson, function(key,value) {
							if(value['ACCESSION13'] != null){
								/*alert("You are only allowed to restorre the item's original item category as " +
										"this already been reassigned. Please click the Unassign button to " +
										"restore the original item category.");*/
								messageBox("091","","");
								$("#PLOCA13").hide();
								$("#PLOCA132").show();
								$("#reassign").hide();
								$("#unassign").show();
								$('#CallNo').text(value['CALLNUMBER']);  //CALL NUMBER
								$('#Title').text(value['TITLE']); //TITLE
								$('#DESC05').text(value['SLOCADESC']); // Original Location  //SLOCADESC
								$('#LOCA03').text(value['SLOCA']); //Original Location id
								$('#DESC71').text(value['ORIBRANCH']);  //ORIBRANCH
								$('#PLOCA132input').val(value['PLOCADESC']);   // PLOCADESC
								$('#ReassignedBranch').text(value['ReassignedBRANCH']);  // ReassignedBRANCH
								$('#Starts').text(moment(value['DTSTART13']).format("DD/MM/YYYY"));
								$('#rStops').val(moment(today).format("DD/MM/YYYY"));
								$("#rStops").attr("disabled", true);
								$('#after').text($("#AccessionNo").val() + " has been reassigned from its original '" 
										+ $("#DESC05").text()+"' to '"+$( "#PLOCA132input" ).val()+"' and stop on "+$("#rStops").val());
							}else{
								$("#unassign").hide();
								$("#rStops").attr("disabled", false);
								$('#dateStop').attr('readonly',false).datepicker({
									startDate : today, 
									format: "dd/mm/yyyy",
									todayBtn: true,
									autoclose: true,
									todayHighlight: true,
								});
								$("#rStops").prop("readonly", false);
								//$("#dateStop").datepicker("setDate", setReassignmentEnd);
								$('#PLOCA13').prop('disabled', false);
								$('#Title').text(value['TITLE']);
								$('#CallNo').text(value['CALLNUMBER']);
								$('#LOCA03').text(value['LOCA03']);
								$('#DESC05').text(value['DESC05']);
								$("#DESC71").text(value['DESC71']);
								$("#BRNC71").text(value['BRNC71']);
								$('#Starts').text(moment(today).format("DD/MM/YYYY"));
								$("#dateStop").datepicker("setDate", setReassignmentEnd);
								//$('#rStops').val(formattedtoday);
							}
						});
					}
				});
			}
		event.preventDefault();
		}else if(code == '8'){
			$('#CallNo, #Title, #Starts, #after, #DESC71, #BRNC71, #DESC10, #rOn, #DESC05, #ReassignedBranch').empty();
			$("#icat2input").val();
			$("#rStops").val('');
			$('#icat2').hide();
			$('#icat').show();
			$('#icat').prop('disabled', 'disabled');
			$("#icat").val();
			$('#PLOCA13').prop('disabled', true);
			$("#PLOCA13").val("");
			$("#reassign").hide();
			$("#unassign").hide();
			$("#rStops").attr("disabled", true);
			$("#PLOCA13").show();
			$("#PLOCA132").hide();
			
		}
	});		
	
	///////////////////////// WHEN REFRESH ////////////////////////////////////////////////////// 
	$("#refresh").click(function(){
		clear();
		$('#GL14PATR').val('');
	});
	
	////////////////////////////////// FUNCTION CLEAR ///////////////////////////////////////////
	function clear(){
		$('#icat').prop('disabled', 'disabled');
		$("#icat").show();
		$("#icat2").hide();
		$("#PLOCA13").show();
		$("#PLOCA132").hide();
		$('.Name, .Status, .GroupId, .PatronCategory, .ExpiringDate').empty(); 
		$(".image--cover").attr('src','/Circulation_IR/resources/image/user_default.png');
		$('#AccessionNo, #icat, #rStops, #icat2input, #PLOCA132').val('');
		$("#AccessionNo").prop("readonly", true);
		$('.input-group-addon').attr("disabled", true);
		$("#rStops").attr("disabled", true);
		$('#CallNo, #Title, #Starts, #rStops, #DESC71, #BRNC71, #DESC10, #rOn, #after, #DESC05').empty();
		$('#ReassignedBranch, #DESC05').empty();
		$("#GL14PATR" ).focus();
	}

	
	////////////////////////////////////  FOR DATEPICKER ///////////////////////////////////////
	$('#dateStop').datepicker({
		startDate : today, 
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
		//enableOnReadonly: false
	});
	
	$('#dateStop').attr('readonly',true).datepicker("destroy");
	
	//////////////////////////// BUTTON REASSIGN WHEN CLICK /////////////////////////////////////
	$("#reassign").click(function(){
		//alert($("#GL14PATR").val());
		var action = $('input[name=actiontype]:checked' ).val();
		if(action == "category"){
			$.post("reassign", {
				AccessionNo: $("#AccessionNo").val(),
				icat : $("#icat").val() ,
				oriItemCat : $("#oriItemCat").text(),
				Starts : $("#Starts").text(),
				rStops : $("#rStops").val(),
				GL14PATR : $("#GL14PATR").val(),
				CREBY : $("#liferayLogin").val()
			},function(data,status){
				
				//alert("Data: " + data + "\nStatus: " + status);	
			});
			//messageBox("005","","");
			messageBox("005",'Reassigned', "@action");
			$("#reassign").hide();
			$("#unassign").hide();
			$('#AccessionNo, #rStops').val('');
			$('#CallNo, #Title, #DESC10, #Starts, #rOn, #oriItemCat, #after').empty();
			$("#rStops").attr("disabled", true);
			$('#icat').val('');
			$('#icat').prop('disabled', 'disabled');
			$("#AccessionNo" ).focus();
			
		}else if(action == "branch"){
			
			$.post("reassignBranch", {
				AccessionNo: $("#AccessionNo").val(),
				LOCA03 : $("#LOCA03").text() ,
				PLOCA13 : $("#PLOCA13").val(),
				Starts : $("#Starts").text(),
				rStops : $("#rStops").val(),
				GL14PATR : $("#GL14PATR").val(),
				CREBY : $("#liferayLogin").val()
			},function(data,status){
				
				//alert("Data: " + data + "\nStatus: " + status);	
			});
			//messageBox("005","","");
			messageBox("005",'Reassigned', "@action");
			$("#reassign").hide();
			$("#unassign").hide();
			$('#AccessionNo, #rStops, #PLOCA13').val('');
			$('#CallNo, #Title, #DESC10, #Starts, #rOn, #ReassignedBranch, #DESC05, #LOCA03, #after').empty();
			$("#rStops").attr("disabled", true);
			$('#PLOCA13').prop('disabled', 'disabled');
			$('#PLOCA132input').val('');
			$('#PLOCA132input').prop('disabled', 'disabled'); 
			$('#DESC71').empty();
			$("#AccessionNo" ).focus();
		}else{
			
		}
	});
	
//////////////////////////////// /BUTTON UNASSIGN WHEN CLICK /////////////////////////////////////
	$("#unassign").click(function(){
		var action = $('input[name=actiontype]:checked' ).val();
		if(action == "category"){
			//alert($("#oriItemCat").text());
			$.post("unassign", {
				AccessionNo: $("#AccessionNo").val(),
				oriItemCat : $("#oriItemCat").text() 
			},function(data,status){
				$("#reassign").hide();
				$("#unassign").hide();
				$('#AccessionNo, #rStops').val('');
				$('#CallNo, #Title, #DESC10, #Starts, #rOn, #oriItemCat, #after').empty();
				$("#rStops").prop("readonly", true);
				$('#icat2input').val('');
				$('#icat').prop('disabled', 'disabled');
				$("#AccessionNo" ).focus();
				//messageBox("005","","");
				messageBox("005",'Unassigned', "@action");
			});
		}else if(action == "branch"){
			$.post("unassignBranch", {
				AccessionNo: $("#AccessionNo").val(),
				LOCA03 : $("#LOCA03").text() 
			},function(data,status){
				$("#reassign").hide();
				$("#unassign").hide();
				$('#AccessionNo, #rStops, #PLOCA13').val('');
				$('#CallNo, #Title, #DESC10, #Starts, #rOn, #DESC05, #DESC71, #ReassignedBranch, #after').empty();
				$("#rStops").prop("readonly", true);	
				$('#PLOCA132input').val('');
				$("#AccessionNo" ).focus();
				//messageBox("005","","");
				messageBox("005",'Unassigned', "@action");
			})
		}else{
		}
	});
	
	//////////////////////////////////////// ONCHANGE icat //////////////////////////////////////////
	$('#icat').on('change', function() {
	    $(this).find(":selected").val();
	    
	    if($(this).find(":selected").val()== $("#oriItemCat").text()){
	    	//swal("Reassigned item category is the same as the present item category.");
	    	messageBox("097","","");
	    	$("#reassign").hide();
	    }else{
	    	$('#after').text($("#AccessionNo").val() + " has been reassigned from its original '" 
					+ $("#oriItemCat").text()+"' to '"+$("#icat").val()+"' and stop on "+$("#rStops").val());
	    	$("#reassign").show();
	    }
	    
	});
	
	////////////////////////////////////////ONCHANGE DATE //////////////////////////////////////////
	$('#dateStop').change(function(){
		var action = $('input[name=actiontype]:checked' ).val();
		//alert(action);
		if(action == "category"){
			$('#after').text($("#AccessionNo").val() + " has been reassigned from its original '" 
					+ $("#oriItemCat").text()+"' to '"+$("#icat").val()+"' and stop on "+$("#rStops").val());
		}else if (action == "branch"){
			if($( "#PLOCA13 option:selected" ).text()== "Please select"){
				$('#after').text($("#AccessionNo").val() + " has been reassigned from its original '" 
				+ $("#DESC05").text()+"' to ' ' and stop on "+$("#rStops").val());
			}else{
			$('#after').text($("#AccessionNo").val() + " has been reassigned from its original '" 
					+ $("#DESC05").text()+"' to '"+$( "#PLOCA13 option:selected" ).text()+"' and stop on "+$("#rStops").val());
			}
		}
        //alert($('#rStops').val());
    });
	
	////////////////////////////////////////ONCHANGE PLOCA13 //////////////////////////////////////////
	$('#PLOCA13').on('change', function() {
		//$("#PLOCA13").find("option").eq(0).remove();
	    $(this).find(":selected").val();
	    
	    if($(this).find(":selected").val()== $("#LOCA03").text()){
	    	//swal("Reassigned item location is the same as the present item location.");
	    	messageBox("098","","");
	    	$("#reassign").hide();
	    }else if($(this).find(":selected").val()== ""){
	    	$('#after').text($("#AccessionNo").val() + " has been reassigned from its original '" 
					+ $("#DESC05").text()+"' to ' ' and stop on "+$("#rStops").val());
	    	$("#ReassignedBranch").empty();
	    	$("#reassign").hide();
	    }else{
	    	$('#after').text($("#AccessionNo").val() + " has been reassigned from its original '" 
					+ $("#DESC05").text()+"' to '"+$( "#PLOCA13 option:selected" ).text()+"' and stop on "+$("#rStops").val());
	    	/*$('#after').text($("#AccessionNo").val() + " has been unassigned from '" 
					+ $("#PLOCA132input").val()+"' to it original '"+$("#DESC05").text()+"' item location");*/
	    	$("#reassign").show();
	    }
	    //$("#reassign").show();
	    $.get('getBranch', {
			 id : $(this).find(":selected").val()
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$("#ReassignedBranch").text(value['DESC10']);
				});
			}
		});
	});
	
	////////////////////////////////////////ONCLICK VIEW //////////////////////////////////////////	
	$("#view").click(function(){
		var action = $( 'input[name=actiontype]:checked' ).val();
		
		//alert(action);
		$.get("view?action=" + action,function(data){
			// action : action
			$("#searchView").html(data);
		});
	});
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	$(".search").click(function(){
		/*var action = $( 'input[name=actiontype]:checked' ).val();

		var url = "Modal_PatrSearch";
		$.get("SearchPatron",function(data){
			$("#searchPatrContent").html(data);
		});*/
		/* var action = $( 'input[name=actiontype]:checked' ).val();*/

		   var url = "Modal_PatrSearch?action=charge";
		 $.get(url,function(data){
			
			 $("#searchPatrContent").html(data);
		   });
	});
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	$("#btn_submit").click(function(){
		//alert("btn_submit");
	});
		
	
	
/*	$("#showModelSearchAN2").on("show.bs.modal", function(){
		 $(this).removeData();
	});*/
	
	
});