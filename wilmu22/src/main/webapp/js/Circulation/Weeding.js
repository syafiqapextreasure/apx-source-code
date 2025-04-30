/**
 * 
 */
	function submitWeeding(){
		var criteria = $('input[name=criteria]:checked' ).val();
		
		var search = $('#search').val();
		var search1 = $('#search1').val();
		$.get('Handler_SearchWeed',{criteria :criteria, search:search, search1:search1
	 	},function(responseJson) {
	 		$(".weddingList").html(responseJson);
		});
	}
	
$(document).ready(function() {
	$("[title]").tooltip();
	 var table = $('#weeding_enquiry').DataTable({
	       responsive: true
	   });
	 
	
	//Call error message page
	function messageBox(code, criteria, label){
	      var url = "Error_Page?GL79ERRCODE="+code+"" +
	      "&criteria=" + criteria + "&label="+label+"";
	      //alert(url);
	      $.ajax({
	            url: url,
	            success: function(result) {
	                  swal('',result, '' );
	            }
	      });
	}
	
	/////////// GET actiontype first time //////////////////////////////////////////////////////
	var action = $('input[name=actiontype]:checked').val();
	itemStatus();
	
	$("#modifybtn").hide();
	
	/////////// GET actiontype ONCHANGE ///////////////////////////////////////////////////////
	$('input[name=actiontype]').change(function(){
		
		var action = $('input[name=actiontype]:checked' ).val();
		
		switch(action) {
			case "maintenance":
				itemStatus();
				clear();
				$("#CT03DOCNO").val('');
				break;
			case "enquiry":
				recall();
				$('#weeding_enquiry').dataTable().fnClearTable();
				break;
			default:
		}
	});

	
	//////////////////  function itemStatus /////////////////////////////////////////////////////
	function itemStatus(){
		$("#statusCombineDetail").show();
		$("#CT03DOCNO" ).focus();
		$("#modifybtn").hide();
		$("#bib").hide();
		$("#itemrecall").hide();
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
		$("#controlNo").prop("readonly", true);
		$('#circulationBtn').attr('disabled', 'disabled');
		$('#patrstatusBtn').attr('disabled', 'disabled');
		$('#recallBranch, #accessionNo').prop('disabled', 'disabled');
		$('#searchcontrolNoBtn').prop('disabled', 'disabled');
	}
	
	///////////////function Clear //////////////////////////////////////////////////////////
	function clear(){
		$("#reason").val("");
		$(".Status, .Currency, .ItemCategory, .ForeignCost, .SMD, .LocalCost, .Condition").empty();
		$(".CirculatedHits, .CopyNo, .Location, .Volume, .Branch, .ControlNo, .CreatedDate").empty();
		$(".ISBN, .ReleasedDate, .Publisher, .CallNumber, .Title, .Author").empty();
		$('#table-circulation').dataTable().fnClearTable();
		$('#table-eligibility').dataTable().fnClearTable();
		$('#table-related').dataTable().fnClearTable();
		
		$('#controlNo, #recallBranch, #accessionNo').val(''); //GL14PATR
		$('.GL14NAME').empty(); 
		$(".image--cover").attr('src','/Circulation_IR/resources/image/user_default.png');
		$(".borrowedBy").empty();
		$('#recallBranch, #accessionNo').prop('disabled', 'disabled');
		$('#searchcontrolNoBtn').prop('disabled', 'disabled');
	}
	
	$("#searchWeedEnquiry").click(function(e){ 
		$.get('Modal_SearchWeedEnquiry',function(responseJson) {
			$("#searchWeedContent").html(responseJson);
		});
	});
	
	/*$("#searchWeedSubmit").click(function(e){ 
		alert(search + search1);
		var criteria = $('input[name=criteria]:checked' ).val();
		
		var search = $('#search').val();
		var search1 = $('#search1').val();
	alert(search + search1);
		$.get('Handler_SearchWeed',{criteria :criteria, search:search, search1:search1
	 	},function(responseJson) {
	 		$(".weddingList").html(responseJson);
		});
	});
	*/
	

	////////////////////////////////////////TAB Accession No AND BACKSPACE ////////////////////////////////////////////
	$("#CT03DOCNO").keydown(function(e){ 
		var action = $('input[name=actiontype]:checked' ).val();
		var code = e.keyCode || e.which;
		if(code == '9'){
			$('#table-circulation').dataTable().fnClearTable();
			$('#table-eligibility').dataTable().fnClearTable();
			$('#table-related').dataTable().fnClearTable();
			var CT03DOCNO = $("#CT03DOCNO").val();
			$.get('searchAccessionAtStatusMaint', {
				CT03DOCNO : $(this).val()
			 	}, function(responseJson) {
				if(responseJson != null){
					
					if(responseJson==''){
						//swal ("Invalid accession number");
						messageBox("020","","");
						$("#modifybtn").hide();
						$("#bib").hide();
						$("#CT03DOCNO").val('');
						setTimeout(function(){
					        $('#CT03DOCNO')[0].focus();
					    }, 3000);
					}else{
						if(action == 'maintenance'){
							//$("#modifybtn").show();
							$("#bib").show();
						}else if(action == 'enquiry'){
							$("#modifybtn").hide();
							$("#bib").hide();
						}
					}
					$.each(responseJson, function(key,value) {
						if(value['GL36DESC']=="095"){
							messageBox("095","","");
							$("#modifybtn").hide();
						}else{
							$("#modifybtn").show();
						$(".Status").text(value['GL36DESC']);
						$(".Currency").text(value['GL24DESC']);
						$(".ItemCategory").text(value['GL10DESC']); 
						$(".ForeignCost").text((Number(value['CT03FCOST'])).toFixed(2));  
						$(".SMD").text(value['GL47DESC']); 
						$(".LocalCost").text((Number(value['CT03LCOST'])).toFixed(2)); 
						$(".Condition").text(value['GL06DESC']);
						$(".CirculatedHits").text(value['CT03CIRHITS']);  
						$(".CopyNo").text(value['CT03COPYNO']);  
						$(".Location").text(value['GL05DESC']);
						$(".Volume").text(value['CT03VOLUME']); 
						$(".Branch").text(value['GL71DESC']);  
						$(".ControlNo").text(value['CT03MATNO']);
						$(".CreatedDate").text(value['CT03CRDATE']);
						$(".ISBN").text(value['ISBN']);
						
						$(".Author").text(value['AUTHOR']);
						$(".ReleasedDate").text(value['CT03RFCDATE']); 
						$(".Publisher").text(value['PUBLISHER']);
						$(".CallNumber").text(value['CALLNUMBER']);
						$(".Title").text(value['TITLE']);
						//$("#title").text(value['TITLE']);
						$(".Collation").text(value['COLLATION']);
						}
					});
					
					$.get('searchAccessionAtStatusMaint2', {
						CT03DOCNO : $("#CT03DOCNO").val()
					 	}, function(responseJson) {
						if(responseJson != null){
							$.each(responseJson, function(key,value) {
								$("#table-circulation tbody").prepend("<tr>" +
										"<td>"+ value.CI02PATR + "-" +value.GL14NAME+ "</td>" +
										"<td>" + value.CI02CDATE + "</td>" +
										"<td>" + value.CI02CTIME + "</td>" +
										"<td>" + value.CI02OFFI + "</td> "+
										"<td>" + value.CI02DDATE + "</td>" +
										"<td>" + value.CI02DTIME + "</td>" +
										"<td>" + value.CI02DIDATE + "</td>" +
										"<td>" + value.CI02DITIME + "</td>" +
										"<td>" + value.CI02DIOFFI + "</td>" +
										"<td>" + value.CI02RENEW + "</td>" +
										"<td>" + value.CI02ROFFID + "</td>" +
										"<td>" + value.CI02RDATE + "</td>" +
										"<td>" + value.CI02RTIME + "</td>" +
										"<td>" + value.CI02FLAG + "</td></tr>");

								$('#table-circulation tr.odd').remove();
							});
						}
					});
					
					$.get('searchAccessionAtStatusMaint3', {
						CT03DOCNO : $("#CT03DOCNO").val()
					 	}, function(responseJson) {
						if(responseJson != null){
							$.each(responseJson, function(key,value) {
								$("#table-related tbody").prepend("<tr>" +
										"<td>" + value.CT03DOCNO + "</td>" +
										"<td>" + value.CT03COPYNO + "</td>" +
										"<td>" + value.STATDESC + "</td> "+
										"<td>" + value.BRANCH + "</td>" +
										"<td>" + value.LOCATION + "</td>" +
										"<td>" + value.ITEMCATEGORY + "</td>" +
										"<td>" + value.DESCSMD + "</td>" +
										"<td>" + value.CT03VOLUME + "</td></tr>");

								$('#table-related tr.odd').remove();
							});
						}
					});
					
					$.get('searchAccessionAtStatusMaint4', {
						CT03DOCNO : $("#CT03DOCNO").val()
					 	}, function(responseJson) {
						if(responseJson != null){
							$.each(responseJson, function(key,value) {
								$("#table-eligibility tbody").append("<tr>" +
										"<td>"+ value.DESCCATE + "</td>" +
										"<td>" + value.DESCICATE + "</td>" +
										"<td>" + value.DESCSMD + "</td>" +
										"<td>" + value.DESCBRNC + "</td>" +
										"<td>" + value.GL27PLOAN + "</td>" +
										"<td>" + value.GL27ELIG + "</td>" +
										"<td>" + value.GL27PTYPE + "</td>" +
										"<td>" + value.GL27RENEW + "</td></tr>");

								$('#table-eligibility tr.odd').remove();
							});
						}
					});
				}
			});
			
		}else if(code == '8'){
			clear();
			$(".Status, .Currency, .ItemCategory, .ForeignCost, .SMD, .LocalCost, .Condition").empty();
			$(".CirculatedHits, .CopyNo, .Location, .Volume, .Branch, .ControlNo, .CreatedDate").empty();
			$(".ISBN, .ReleasedDate, .Publisher, .CallNumber, .Title, .Author").empty();
			$('#table-circulation').dataTable().fnClearTable();
			$('#table-eligibility').dataTable().fnClearTable();
			$('#table-related').dataTable().fnClearTable();
		}
	});
	
	////////////////////////////////////////// TABEL table-circulation ////////////////////////////////////////////
	var table1 = $('#table-circulation').DataTable({
		//scrollx: true,
		responsive: true,
		//scrollX: true,
		info: false,
		paging: false,
		select: true,
		columns: [
			{ data: "CI02PATR"}, 
			{ data: "CI02CDATE"}, 
		],
	      ajax: {
	          url: "searchAccessionAtStatusMaint",
	          //dataSrc: 'allk'
	      },
	});
	
	//////////////////////////////////////////TABEL table-related ////////////////////////////////////////////
	var table2 = $('#table-related').DataTable({
		//scrollx: true,
		responsive: true,
		scrollx: true,
		info: false,
		paging: false,
		select: true,
	});
	
	//////////////////////////////////////////TABEL table-eligibility ////////////////////////////////////////////
	var table3 = $('#table-eligibility').DataTable({
		//scrollx: true,
		responsive: true,
		scrollx: true,
		info: false,
		paging: false,
		order: [[ 2, "desc" ]]
	});
	
	///////////////////////////// CLICK TAB CIR ////////////////////////
	$('a[data-toggle=tab]').click(function(){
	});
	//alert($('.nav-tabs .active').text());
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		
		  var target = $(e.target).attr("href")
		  switch(target) {
		    case "#circulation":
		    	var CT03DOCNO = $("#CT03DOCNO").val();
		    	$('#CT03DOCNOCir').val(CT03DOCNO);
		    	$.get("Item_Status_Maintenance?CT03DOCNO=" + CT03DOCNO,function(data){
					// action : action
					$("#table-circulation").html(data);
				});
		        break;
		    case "#related":
		        break;
		    default:
		}
		});
	
    $("#CT03DOCNOCir").bind("change paste keyup click", function() {

     });
	
	/////////////////////////CLICK BTN MODIFY ///////////////////////////////////////////////////
	
	function getAlertMessage(code, criteria, label){
		var message = "";

		    var url = "Error_Page?GL79ERRCODE="+code+"" +
		    "&criteria=" + criteria + "&label="+label+"";
		    $.ajax({
		        async: false,
		        url: url,
		        success: function(result) {
		             message = result.trim();
		        }});
		  
		        return message;
		}

	
	$("#modifybtn").click(function(){
	
		var message = getAlertMessage("096", "", "");
		
		var reason = $("#reason").val();
		
		if(reason!=""){

		swal({   
			title: message,
			showCancelButton: true,
			confirmButtonColor: '#3085d6', 
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes',
			cancelButtonText: 'No',
			confirmButtonClass: 'confirm-class',
			cancelButtonClass: 'cancel-class',
			closeOnConfirm: false,
			closeOnCancel: false 
			}).then(function() {

				var CT03DOCNO = $("#CT03DOCNO").val();
				var reason = $("#reason").val();
				
			
				$.get("Handler_Weeding",{CT03DOCNO:CT03DOCNO, reason:reason},function(list){
					JSON.parse('""');
					var array={CT03DOCNO:CT03DOCNO};
					localStorage.setItem('array', JSON.stringify(array));
					if(list.trim()=="OK"){
					 swal("", "Record is successfully weeded", "success");
					}else{
						swal({   
							title: "Do you like to delete BO?",
							showCancelButton: true,
							confirmButtonColor: '#3085d6', 
							cancelButtonColor: '#d33',
							confirmButtonText: 'Yes',
							cancelButtonText: 'No',
							confirmButtonClass: 'confirm-class',
							cancelButtonClass: 'cancel-class',
							closeOnConfirm: false,
							closeOnCancel: false 
							}).then(function() {
									   var details =JSON.parse(localStorage.getItem('array'));
									$("#displayBO").modal("show");
						
								$.get("Modal_BORecord",{CT03DOCNO:details.CT03DOCNO,action:"deleteBO"},function(list){
									
									$("#BORecord").html(list);
									if(list.trim()=="OK"){
									 swal("", "Record is successfully weeded", "success");
									}else{
										
									}
									  
								  });
										},function(dismiss) {
											  if (dismiss === 'cancel') {
												  $("#CT03DOCNO").val("");
												  return false;
											  }
											})
					}
					  
				  });
				itemStatus();
				clear();
				$("#CT03DOCNO").val('');
		    			
						},function(dismiss) {
							  if (dismiss === 'cancel') {
								  $("#CT03DOCNO").val("");
								  return false;
							  }
							})
		//var action = $( 'input[name=actiontype]:checked' ).val();
		var CT03DOCNO = $("#CT03DOCNO").val();
		var GL36DESC = $(".Status").text();
		//$.get("view?action=" + action,function(data){
		//if(action == 'modify'){
			$.get("modify?CT03DOCNO=" + CT03DOCNO,function(data){
				GL36DESC:GL36DESC;
				//CT03DOCNO: CT03DOCNO,
				// action : action
				$("#modifyContent").html(data);
				$("#btnOK").attr("disabled","disabled");
				
			});
		//}
		}else{
			swal("", "Please insert reason for weeding", "warning");
		}
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
		if(code == '9'){
			$.get('searchPatrBYTab', {
				 GL14PATR : $("#GL14PATR").val()
		 	}, function(responseJson) {
			if(responseJson != null){
				if(responseJson==''){
					messageBox("036","",""); 
					clear();
					$('#GL14PATR').val('');
					$("#controlNo").prop("readonly", true);
					setTimeout(function(){
				        $('#GL14PATR')[0].focus();
				    }, 3000);					
				}
			}//else{
				$.each(responseJson, function(key,value) {
					$('.GL14NAME').text(value['GL14NAME']);
					$('#circulationBtn').attr('disabled', false);
					$('#patrstatusBtn').attr('disabled', false);
					$("#controlNo").prop("readonly", false);
					$("#controlNo").focus();
					$('#searchcontrolNoBtn').prop('disabled', false);
					$('#recallBranch').prop('disabled', false);
					//$('#recallBranch, #accessionNo').prop('disabled', false);
				});
			//}
			});
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
	
	///////////////////////// TAB Control No ///////////////////////////////////////////////////
	$("#controlNo").keydown(function(e){
		var code = e.keyCode || e.which;
		if(code == '9'){
			$.get('recall', {
				controlNo : $("#controlNo").val()
		 	}, function(responseJson) {
		 		if(responseJson != null){
					if(responseJson==''){
						messageBox("093","",""); 
						setTimeout(function(){
					        $('#controlNo')[0].focus();
					    }, 3000);					
					}else{
						$('#accessionNo').prop('disabled', false);
						$.each(responseJson, function(key,value) {
							 $("#accessionNo").append("<option value='"+value.GL36DESC+"'>" + value.GL36DESC + "</option>");
						});
					}
				}
		 	});
		}else if(code == '8'){
			//$("#accessionNo").val('');
			$('option:gt(0)', '#accessionNo').remove();
			$('#accessionNo').prop('disabled', 'disabled');
			$("#recallBranch").val('');
			$('.title').empty();
			$('.borrowedBy').empty();
		}
	});
	
	////////////////////////////////////////ONCHANGE accessionNo //////////////////////////////////////
	$('#accessionNo').on('change', function() {
	    $(this).find(":selected").val();
	    
	    $.get('detailRecall', {
			CT03MATNO : $("#controlNo").val(),
			CT03DOCNO : $(this).find(":selected").val()
	 	}, function(responseJson) {
	 		$.each(responseJson, function(key,value) {
	 			//alert((value.GL08ACTION8 == 'Y' && value.totalElig > 0))
	 			$('.title').text(value['TITLE']);
	 			$('.borrowedBy').text(value['GL14NAME'] +" , "+ value['CT03PATR']);
	 			if((value.GL08ACTION8 == 'Y' && value.totalElig > 0)){
	 				$('.recallStatus').text("Patron is not eligible to recall this title")
	 			}else{
	 				$('.recallStatus').text("Patron is eligible to recall this title")
	 			}
	 		});
	 	});
	});
	
	
	
	 //$("#CT03DOCNO").val("0000000300");
	
});

