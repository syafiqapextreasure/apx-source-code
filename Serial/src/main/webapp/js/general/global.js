/**
 * 
 */

$(document).ready(function() {
//Close patron search modal 
$(".closePatrModal").click(function(){
	$("#officerSearch").modal("hide");
});
 

});

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
		var matno = $(this).data("matno");
		var bufferno = $(this).data("bufferno");
		var action = $(this).attr("data-action");
		var searchtype = $(this).attr("data-search");
		var patrid = $(this).attr("data-patrid");
		
		if(pointer!=null){
			$("#search_title").collapse('hide');
			$("#result_title").collapse('show');
			loader("#display_title");
		$.get("ViewTitles",{pointer : pointer, action:action},function(data){
			$("#display_title").html(data);
			
		});	
			document.getElementById('controlNo').focus();
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
				$('#titleSearch').modal('hide');
				
				$(".CT03MATNO").val(matno);
				//document.getElementById("CT03MATNO").value = matno;
				$.get("SearchVendor",{criteria:matno,action:"keyupCtrlNo"},function(title){
					$(".title").html(title);
				   });	
			
			}else if(action=="5"){
			/*	$("#result").collapse('show');*/
				$('#titleSearch').modal('hide');
				
				$.get("Table_SerialMaster",{controlNoInput : matno},function(data){

					if(data.trim()=="empty"){
						swal("", "Record is not related to serial", "");
					}else{
					$("#panel_serial").html(data);
					
					}
				});
			}
			else{
			url = "Handler_BORecord?matno="+matno+"&action=IndexUnindex";
			$('.collapse').show();
			loader("#tags");
					$.get(url,function(data_title){
						//$('.modal').removeClass('show');
						
						$("#tags").html(data_title);
						 $("li").removeClass("unindexBO");
						 $("input").prop("disabled", true);
						
					});	
				
					//$('#titleSearch').modal('hide');
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
				swal('',result, 'info' );
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
	var startDate = $('#startDateInput').val();
	var stopDate =$('#stopDateInput').val();
	$.get("SearchPatron12",{criteria:criteria,search_type:searchType,cate_id:cate_id,startDate:startDate,stopDate:stopDate},function(data_vendor){
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
