/**
 * 
 */

$(document).on("click", "#btn_submit", function(event){
	 
	var criteria = $('#patronSearchInput').val();
	var searchType =$(".text-value").data('span');
	var cate_id = $('#cate_type').val();
	var action = $("#action").val();
	var sort = $("#patronSortedBy").val();
	loader("#display_vendor");
	$.get("SearchPatron",{criteria:criteria,search_type:searchType,cate_id:cate_id, action:action, sort:sort},function(data_vendor){
	/*	$("#result_vendor").collapse('show');
		$("#search_vendor").collapse('hide');*/
		
		$("#display_vendor").html(data_vendor);
		$('#patr_result').DataTable();
	   });
});

$(document).ready(function() {
	 $('#patr_result').DataTable();

	 $('body').on('shown.bs.modal', '.modal', function () {
		 /////alert("close");
		  $(this).removeData('bs.modal');
		}); 
//Close patron search modal 
$(".closePatrModal").click(function(){
	$("#officerSearch").modal("hide");
});


$(".dropdown-menu li a").click(function(){
	$('#searchPatron').removeData();
	//alert("sd");
	  $(this).parents(".input-group-btn").find('.btn').html('<span class="text-value" data-span="'+$(this).data('value')+'">' + $(this).text() + '</span> <span class="caret"></span>');
		});




});

//$('#view-result12').find('td,th').last().hide();
///////////$("#view-result12 th:last-child, #view-result12 td:last-child").remove();

function appendData(GL14PATR){
	
	//20092019
	$(".idOfficer").val(GL14PATR);
	$(".idOfficer").focus();
	setTimeout(function(){ 
			$.get('GetoffiName', {
	        	code : GL14PATR
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						$(".div-officername").text(value['patrName']);
					});
				}
		});
	}, 1000);
	
	//$("#GL14PATR").val(GL14PATR);
	///$("#requestorId").val(GL14PATR);
	
	/*$.get('getRequestor', {
		patronID : GL14PATR
 	}, function(responseJson) {
	if(responseJson != null){
		if(responseJson==''){
			messageBox("036","",""); 
		}
		$.each(responseJson, function(key,value) {
			if(moment(today).format("YYYY-MM-DD") > moment(value['expdate']).format("YYYY-MM-DD")){
				messageBox("032","","");
			}else if(value['CHARGING'] == 'N'){
				messageBox("118",value['STATDESC'], "@action");
			}else{
				$.get('getPatrNameDept', {
					code : reqid
				 	}, function(responseJson) {
				 	if(responseJson != null){
				 	
						$.each(responseJson, function(key,value) {
							$("#div-reqname").text(value['patrname']);
							$("#div-reqdept").text(value['patrdept'])
						});
					}
				});
			}
		});
	}
	});*/
	
	/*$.get('getPatrNameDept', {
    	code : GL14PATR
	 	}, function(responseJson) {
		if(responseJson != null){
			$.each(responseJson, function(key,value) {
				$("#div-reqname").text(value['patrname']);
			});
		}
	});*/

	//$(".patrid").val(GL14PATR);
	 /*access(GL14PATR);
		var url = "Handler_PatronDetails?action=charge&GL14PATR=" + GL14PATR;
		
		$.get(url,function(data)
				{
				var message = (data.trim()).split(",");
				alert(data);
			if(data.trim()== "033"){
				messageBox("033","","");
				reload("charge");
				}else if(data.trim()== "036"){
				messageBox("036","","");
				reload("charge");
				}else if(message[0]== "080"){
				messageBox("080",message[1], "@status");
				reload("charge");
				}else if(data.trim()== "082"){
				messageBox("082","", "");
				reload("charge");
				}else if(data.trim()== "050"){
				messageBox("050","","");
				reload("charge");
				}else if(data.trim()== "032"){
					messageBox("032","","");
					reload("charge");
				}else if(data.trim()== "040"){
					messageBox("040","","");
					reload("charge");
				}else if(data.trim()== "046"){
					messageBox("046","","");
					reload("charge");
				}else{
					reload("charge");
					var details = data.trim().split("/n");
					
					alert(details[0]);
					$("#div-reqname").text(details[0]);

					$(".Name").append(' <span>'+details[0]+'</span>');
					$(".GL14CATE").append(' <span>'+details[1]+'</span>');
					$(".Status").append(' <span>'+details[2]+'</span>');
					$(".GL14MEMDATE").append(' <span>'+details[3]+'</span>');
					$(".GL14EXPDATE").append(' <span>'+details[4]+'</span>');
					$(".GL14BRANCH").append(' <span>'+details[10]+'</span>');
					$("#onloan").text(details[5]);
					$("#itemoverdue").text(details[6]);
					$("#itemreserved").text(details[7]);
					$("#fines").text(details[8]);
					//$("#totalfines").text(details[9]);
			}		
	
		});*/	
}

function getDischargeItem(discharge){
	var items = discharge.split(",");

	var url = "Handler_PatronDetails?action=discharge&CT03DOCNO=" + items[1];
		
	$.get(url,function(data)
	{
		$("#patrDet").html(data);

		  access(items[0]);
		  if(!items[1] )
			  {
			  messageBox("020","","");
			  }
		
		  else
		  {

			  $.get("AccessionDischarge?name=" + items[1] + "&patronid=" + items[0],function(data)
		    			{ 
				
				  var message = data.split(",");
				  if(data.trim()=="020"){
					  messageBox("020","","");
				}else if(data.trim()== "081"){
					messageBox("081","","");
				}else if(data.trim()=="071"){
					  messageBox("071","","");
				}else if(data.trim()=="070"){
					  messageBox("070","","");
				}else if(data.trim()=="073"){
					  messageBox("073","","");
				}else if((message[0]).trim()=="064"){
					printResvNotification(message[1]);
				}else if((message[0]).trim()=="resvFine"){
					resvFine(message[1]);
					//printResvNotification(message[1]);
				}else if(data.trim()=="074"){
					  repayment();
				}else if(message[0].trim()=="066"){
					  messageBox("066",message[1],"@status");
				}else{
						var datas =data.split("\n");
					$('#table-charging tr.status').remove();
						$(".hide").css('display', '');
						 $("th").removeClass("hide");
			
						 $('#table-charging tbody').prepend('<tr style="background-color:white" class="status"><td class="disCT03DOCNO">'+datas[0]+'</td>'+
									'<td>'+datas[1]+'</td><td class="Cdate">'+datas[2]+'</td>'+
									'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
									'<td class="Dtime">'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td>' + 
									'<td class="lateby">'+datas[7]+'</td><td class="fines">'+datas[8]+'</td></tr>');
				
						//charging(new_id);
						$('#table-charging tr.odd').remove();
						
				}
				  //$("#CT03DOCNO").val("");
		    			});

		  }
		  $('#searchPatron').modal('hide');
	});
	
}

function getResvDetails(CI03PATR){

    
    var CT03MATNO = $("#CT03MATNO").val();
	 var CI03DOCNO = "";
	
	 if(CT03MATNO.length!=0 && CT03MATNO!=null){
		 CI03DOCNO = $(".selectCT03MATNO").find(':selected').val();
	 }else{
		 CT03MATNO = "0";
		 CI03DOCNO = $("#CT03DOCNO").val();
	 }
	
	$.get("Handler_AddResv",{CI03PATR:CI03PATR, CI03DOCNO:CI03DOCNO, CI03MATNO:CT03MATNO},function(result){
		var message = (result.trim()).split(",");
		alert(result.trim());
			if(result.trim()=="ok"){
				setTimeout(function(){swal("Successful", "Reservation successfully added"); }, 1000);
				 
					var url = "List_Resv?CI03DOCNO="+CI03DOCNO + "&CI03MATNO=" + CT03MATNO;
					$.get(url,function(data)
							{
						if ($('.ackLetter').is(":checked"))
 						{
 							var url = "GeneratePreviewDocument?docno="+CI03DOCNO+"&action=acknowledgement";

 								window.open(url,"", "");
 								
 						}	
								$(".resv_list").html(data);
							});
			}else if(result.trim()=="050"){
				messageBox("050","","");
			}else if(result.trim()=="032"){
				messageBox("032","","");
			}else if(result.trim()=="060"){
				messageBox("060","","");
			}else if(result.trim()=="077"){
				messageBox("077","","");
			}else if(result.trim()=="078"){
				messageBox("078","","");
			}else if(result.trim()=="079"){
				messageBox("079","","");
			}else if(message[0]=="076"){
				messageBox("076",message[1].trim(), "@no");
			}else if(message[0].trim()=="075"){
				messageBox("075",message[1].trim(),"@id");
			}
	   });
}


function getDetails(details){

	//When action chosen is 7(From List of Bibliography)
	var newSearch = $("input[name='searchSelection']:checked").val();
	 var checkedValue  = $("input[name='searchin']:checked").val(); 
	 var action = $(details).attr("data-action");
	 var title = $('.criteria').val();
	 if(checkedValue=="index"){
		 if(newSearch=="selection"){
			var title = $('#criteria').val();
			var searchType = $('#search_type').val();
		
			$("#search_title").collapse('hide');
			$("#result_title").collapse('show');
			loader("#display_title");
			$.get("RetrieveSearchTitle",{criteria:title,search_type:searchType,action:action},function(data_title){
				$("#display_title").html(data_title);
			   });	
			}
			else if(newSearch=="ctrlNo"){
				$("#search_title").collapse('hide');
				$("#result_title").collapse('show');
				loader("#display_title");
				var url = "RetrieveSearchTitle?criteria="+title+"&search_type="+newSearch+"&action="+action;
				$.get(url,function(data_title){
					$("#display_title").html(data_title);
				});
			   }
			else if(newSearch == "tag"){
				 var tag = $(".tags").val();
			$("#search_title").collapse('hide');
			$("#result_title").collapse('show');
			loader("#display_title");
			$.get("RetrieveSearchTitle",{criteria:title,search_type:newSearch,tag:tag,action:action},function(data_title){
				$("#display_title").html(data_title);
			});
			}
			else if(newSearch == "officerID"){
				 var searchtype = $("#officerValue").val();
				 var startDateInput = $("#startDateInput").val();
				 var stopDateInput = $("#stopDateInput").val();
				 
			$.get("Search_OfficerID",{criteria:searchtype,search_type:newSearch,
									  action:action, startDateInput: startDateInput,
									  stopDateInput:stopDateInput},function(data_title){
				$("#officerSearch").modal('show');
				$("#officerResult").html(data_title);
				// $(data_title).appendTo('.modal-content').modal();
			});
			}
	 	}else{
			var buffer = "bufferno";
		   if(newSearch=="bufferNo"){
				var title = $('#criteria').val();
				$("#search_title").collapse('hide');
				$("#result_title").collapse('show');
				loader("#display_title");
				$.get("RetrieveSearchTitle",{criteria:title,search_type:newSearch, buffer:buffer, action:action},function(data_title){
					$("#display_title").html(data_title);
				   });	
				}
		   else if(newSearch=="tag"){
			  
				var title = $("#criteria").val();
				var tag = $('.tags :selected').val()
				$("#search_title").collapse('hide');
				$("#result_title").collapse('show');
				loader("#display_title");
				$.get("RetrieveSearchTitle",{criteria:title,search_type:newSearch, buffer:buffer, tag:tag, action:action},function(data_title){
					$("#display_title").html(data_title);
				   });	
				}	else if(newSearch == "officerID"){
						 var searchtype = $("#officerValue").val();
						 var startDateInput = $("#startDateInput").val();
						 var stopDateInput = $("#stopDateInput").val();

					$.get("Search_OfficerID",{criteria:searchtype,search_type:newSearch,
											  action:action, startDateInput: startDateInput,
											  stopDateInput:stopDateInput, buffer:"bufferNo"},function(data_title){
						$("#officerSearch").modal('show');
						$("#officerResult").html(data_title);
						// $(data_title).appendTo('.modal-content').modal();
					});
				}
	   }
}
	$('.collapse').on('shown.bs.collapse', function(){
		$(this).parent().find(".glyphicon-chevron-up").removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
		}).on('hidden.bs.collapse', function(){
		$(this).parent().find(".glyphicon-chevron-down").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
		});
	
	$('body').on('hidden.bs.modal', '#titleSearch', function () {
		  $(this).removeData('bs.modal');
		});
	
 	$('#titleSearch').on('shown.bs.modal', function () {
	//Depends on the value checked in search in 
 	$('#retrieveBO input').on('change', function() {
		   var checkedValue  = $('input[name=searchin]:checked', '#retrieveBO').val();
		   $("input[type=radio][value=selection]").attr("disabled", false);
	
		   if(checkedValue=="index"){
			   $("#buffer").hide();
			   $("#index").show();
		   }
		   else if(checkedValue=="buffer"){
		
			   document.getElementById("search_type").disabled = true;
			   $("input[type=radio][value=selection]").attr("disabled", true);
			   $("#buffer").show();
			   $("#index").hide();
			  
		   }
		});
 	

 		
 		


 

	});

function loader(target_id) {
	$(target_id).load('Loading');
}

 



$('.clickable-row').click(function() {
	
		var pointer = $(this).attr("data-value");
		var title = $(this).find(".title").text();
		var newTitle = $(this).find(".newTitle").text(); 
		var matno = $(this).data("matno");
		var bufferno = $(this).data("bufferno");
		var action = $(this).attr("data-action");
		var searchtype = $(this).attr("data-search");
		var patrid = $(this).attr("data-patrid");
		var docno = $(this).data("docno");
		
		if(pointer!=null){
			$("#search_title").collapse('hide');
			$("#result_title").collapse('show');
			loader("#display_title");
		$.get("ViewTitles",{pointer : pointer, action:action},function(data){
			$("#display_title").html(data);
		});	
			//document.getElementById('controlNo').focus();
		}else if(patrid!=null && bufferno=="bufferNo"){
			var startDate = $(this).attr("data-startDate");
			var endDate = $(this).attr("data-endDate");
			var patrid = $(this).attr("data-patrid");
			var criteria = $(this).attr("data-criteria");
	
			$.get("RetrieveSearchTitle",{search_type:searchtype,patrid:patrid,startDate:startDate
										,action:action,endDate:endDate,criteria:criteria, buffer:"bufferNo"},function(data_title){
				$("#search_title").collapse('hide');
				$("#result_title").collapse('show');
				$("#display_title").html(data_title);
				$("#officerSearch").modal("hide");
			});
		}else if(patrid!=null && bufferno!="bufferNo"){
			var startDate = $(this).attr("data-startDate");
			var endDate = $(this).attr("data-endDate");
			var patrid = $(this).attr("data-patrid");
			var criteria = $(this).attr("data-criteria");
	
			$.get("RetrieveSearchTitle",{search_type:searchtype,patrid:patrid,startDate:startDate
										,action:action,endDate:endDate,criteria:criteria},function(data_title){
				$("#search_title").collapse('hide');
				$("#result_title").collapse('show');
				$("#display_title").html(data_title);
				$("#officerSearch").modal("hide");
			});
		}
		else if(matno!=null){
			if(action=="8"){
				//document.getElementById("title").innerHTML = title;
				document.getElementById("CT03MATNO").value = matno;
				$.get("SearchVendor",{criteria:matno,action:"keyupCtrlNo"},function(title){
					$(".title").html(title);
					$(".close").click();
				   });	
				$('#titleSearch').modal('hide');
			}else if(action=="5"){
			/*	$("#result").collapse('show');*/
				$("#input-contorlNo").val(matno);
				$("#div-ctrolNo").text(newTitle);
				//invoice
				//alert($(this).find(".newTitle").text());
				$("#input-title").val($(this).find(".newTitle").text());
				$("#input-titleCtrlno").val(matno);
				$('#titleSearch').modal('hide');
				
				$.get("Table_CTResultMaster",{controlNoInput : matno},function(data){
					$(".accession_list").html(data);
				});
			}else if(action=="15"){
				if(docno == null){
					messageBox("103","","");
					$("#titleSearch .close").click();
					$("#accessionNo").focus();
				}else{
					$("#accessionNo").val(docno);
					$("#titleSearch .close").click();
					$("#accessionNo").focus();
					//21092019
					/*$("#controlNo, #editissuevolDes, #spineTitle").val("");
			 		$(".callNumber, .div-officername").empty();
					setTimeout(function(){ 
					////display officer name
						$.get('GetAccession', {
				        	code : docno
						 	}, function(responseJson) {
						 		
						 	if(responseJson == ""){
						 		$("#controlNo, #editissuevolDes, #spineTitle").val("");
						 		$(".callNumber, .div-officername").empty();
						 	}
							if(responseJson != null){
								$.each(responseJson, function(key,value) {
									$("#controlNo").val(value['ctrlno']);
									$("#editissuevolDes").val(value['title']);
									$(".isbn").text(value['issn']);
									$("#spineTitle").val(value['title']);
									$(".callNumber").text(value['callno']);
								});
							}
						});
					}, 1000);*/
				}
			}else if(action=="20"){
				//AT BINDING 
				///alert("matno" +matno);
				$(".ctrlno").val(matno);
				$("#titleSearch .close").click();
				$(".ctrlno").focus();
				$('#checkbox-controlNo').prop('checked', true);
			}else{
			url = "Handler_BORecord?matno="+matno+"&action=IndexUnindex";
			//loader("#tags");
					$.get(url,function(data_title){
						$('#titleSearch').modal('hide');
						$('.collapse').show();
						$("#tags").html(data_title);
						 $("li").removeClass("unindexBO");
						 $("input").prop("disabled", true);
						
					});	
			}
		}else if (bufferno!=null){
			url = "Handler_BufferTable?matno="+bufferno;
						
			$.get(url,function(data_title){
				$('#titleSearch').modal('hide');
				$('.collapse').show();
				$("#tags").html(data_title);
				$("li").removeClass("deleteBO");
			});	
		}else if(searchtype=="officerID"){
			alert("in");
				var startDate = $(this).attr("data-startDate");
				var endDate = $(this).attr("data-endDate");
				var patrid = $(this).attr("data-patrid");
				var criteria = $(this).attr("data-criteria");
				
				$.get("RetrieveSearchTitle",{search_type:searchtype,patrid:patrid,startDate:startDate
											,action:action,endDate:endDate,criteria:criteria},function(data_title){
					$("#search_title").collapse('hide');
					$("#result_title").collapse('show');
					$("#display_title").html(data_title);
					$("#officerSearch").modal("hide");
				});
		}
		
		$('body').removeClass('modal-open');
		$('.modal-backdrop').remove();
		});


function messageBox(code, criteria, label){
	var url = "Error_Message?GL79ERRCODE="+code+"" + 
		"&criteria=" + criteria + "&label="+label+"";
	$.ajax({
			url: url,
			success: function(result) {
				//swal('',result, 'info' );
				swal(result);
			}
		});
}

function deletedMessage(message, info, code, criteria, label){
	var url = "Error_Message?GL79ERRCODE="+code+"" + 
		"&criteria=" + criteria + "&label="+label+"";
	$.ajax({
			url: url,
			success: function(result) {
				swal(message,result, info );
			}
		});
}

function alertMessage(message, info, code, criteria, label){
	var url = "Error_Message?GL79ERRCODE="+code+"" + 
		"&criteria=" + criteria + "&label="+label+"";
	$.ajax({
			url: url,
			success: function(result) {
				//swal(message,result, info );
				//swal(message,result, info );
				swal(result.trim());
			}
		});
}

function unsavedForm(){
	
				swal({
					  title: 'Are you sure?',
					  text: "You won't be able to revert this!",
					  type: 'warning',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: 'Yes, delete it!',
					  cancelButtonText: 'No, cancel!',
					  confirmButtonClass: 'btn btn-success',
					  cancelButtonClass: 'btn btn-danger',
					  buttonsStyling: false
					}).then(function() {
					  swal(
					    'Deleted!',
					    'Your file has been deleted.',
					    'success'
					  );
					}, function(dismiss) {
					  // dismiss can be 'cancel', 'overlay', 'close', 'timer'
					  if (dismiss === 'cancel') {
					    swal(
					      'Cancelled',
					      'Your imaginary file is safe :)',
					      'error'
					    );
					  }
					})
		
}

function indiMessage(code, indi, indilvl, tag){
	var url = "Error_Message?GL79ERRCODE="+code+"" + 
		"&indi=" + indi + "&indilvl="+indilvl+"&tag=" +tag;
	$.ajax({
			url: url,
			success: function(result) {
				$(".errorMessage").html(result);
				//swal(message,result, info );
			}
		});
}

function saveMessage(code, criteria, label){
	var url = "Error_Message?GL79ERRCODE="+code+"" + 
		"&criteria=" + criteria + "&label="+label+"";
	$.ajax({
			url: url,
			success: function(result) {
				swal('Cancelled!',result, 'error' );
			}
		});
}


$('#startDateInput').datepicker({
	format : "d/m/yyyy",
	autoclose : true
});	
$('#stopDateInput').datepicker({
	format : "d/m/yyyy",
	autoclose : true
});

// Update placeholder for textbox
function updatePlaceholder() {
	if($(".title option:selected").text()!=null){
		$("#criteria").attr("placeholder", $(".title option:selected").text());
	}else{
		$("#criteria").attr("placeholder", $(".tags option:selected").text());
	}
}


//Display value after selecting dropdown in officer modal 
function getValue(cate_type){
	
	var str = cate_type.options[cate_type.selectedIndex].getAttribute('value');
	var e = document.getElementById("cate_type");
	var strUser = e.options[e.selectedIndex].value;
	document.getElementById("cate-id").value=strUser;
}

//Submit to get search value in officer modal 

$('#btn_submit').click(function(){
	
	var criteria = $('#patronText').val();
	var searchType = $('#search-type').val();
	var cate_id = $('#cate-id').val();
	$.get("SearchPatron12",{criteria:criteria,search_type:searchType,cate_id:cate_id},function(data_vendor){
		$("#display_vendor").html(data_vendor);
	   });
})

	$(document).ready(function(){
	
		initDate();
		defaultDate();
		init();
		
	
	});
	function init(){
		initDate();
		defaultDate();
	}
	
	
	//Datatable plugin
/*	$(window).load(function(){
		$(document).ready(function () {
		   $('#title_result').DataTable({
		       responsive: true
		   });
	
		});
	});

	$('.panel-collapse').on('shown.bs.collapse', function (e) {
	   cpnsole.log($(e.currentTarget).find('.panel-body').find('.table')[0].id);
	    var tableIdToUpdate = $(e.currentTarget).find('.panel-body').find('.table')[0].id;
	    $('#' + tableIdToUpdate).DataTable().columns.adjust().responsive.recalc();
	});
	*/
	$('.multiselect').multiselect({
		includeSelectAllOption: true
	});
	
	/*  $('.plugintables').DataTable({
	       responsive: true
	   });
	  
*/
	
	function confirmationMessage(title, text, type, confirmButtonText){
		swal({   
			title: title,
			text: text,
			type: type,   
			showCancelButton: true,
			confirmButtonColor: '#3085d6', 
			cancelButtonColor: '#d33',
			confirmButtonText: confirmButtonText,
			cancelButtonText: 'No, cancel !',
			confirmButtonClass: 'confirm-class',
			cancelButtonClass: 'cancel-class',
			closeOnConfirm: false,
			closeOnCancel: false 
			})
	}
