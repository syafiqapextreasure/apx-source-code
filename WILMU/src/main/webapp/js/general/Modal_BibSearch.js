/**
 * 
 */

function closeAuthModal(){
	$("#authlink").modal("hide");
}

function radio(){
	var checkValue = $('input[name="searchSelection"]:checked').val();
	        if (checkValue=="selection"){
	            $("#search_type").prop("disabled", false);
	            $("#select_tags").prop("disabled", true);
	            $("#officerValue").prop("disabled", true);
	            $("#criteria").attr('maxlength','');
	        } else if (checkValue=="tag"){
	        	  $("#search_type").prop("disabled", true);
	        	  $("#officerValue").prop("disabled", true);
	        	document.getElementById("select_tags").disabled = false;
	        	$("#criteria").attr('maxlength','');
	        }else if(checkValue=="buffer"){
	       	 	$("#select_tags").prop("disabled", false);
	       	 	$("#select_tags").val("245");
	       	 	$('#tag').prop('checked', true);
	        }else if(checkValue=="MatNo"|| checkValue=="ctrlNo"){
	        	$("#criteria").attr('maxlength','10');
	        }
	        else{
	        	 $("#search_type").prop("disabled", true);
	        	 $("#select_tags").prop("disabled", true);
		        	document.getElementById("officerValue").disabled = false;
		        	$("#criteria").attr('maxlength','');

	        }
	        
  }

	
$('#vendor').click(function() {
	document.getElementById('vendor').value = $(this).attr('data-value');
	document.getElementById('div-vendorName').innerHTML = $(this).find(".vendorName").text();
});

$('#titleSearch').on('shown.bs.modal', function () {
	//By default index is checked
		$("input[type=radio][value=index]").prop("checked",true);
		
		//By default selection is checked
		$("input[type=radio][value=selection]").prop("checked",true);
	
});


		$('.vendor_submit').click(function(){			
			var vendor_details = $('#criteria1').val();
			var searchType = $('#search-type').val();
			$("#search_vendor").collapse('hide');
			$("#result_vendor").collapse('show');
			loader("#display_vendor");
			$.get("SearchVendor",{criteria:vendor_details,search_type:searchType,action:"modalVendor"},function(data_vendor){
				$("#display_vendor").html(data_vendor);
			   });			
		})
	
		$('.clickable-row1').click(function() {
			
				$(".CT03VEND").val( $(this).attr('data-value'));
				$(".div-vendorName").html($(this).find(".vendorName").text());
			
				//$('#vendorSearch').modal("hide");
				$('#vendorSearchss').modal("hide");
		
				//$('#vendorSearch').modal("hide");
				document.getElementById('CT03VEND').value = $(this).attr('data-value');
				document.getElementById('div-vendorName').innerHTML = $(this).find(".vendorName").text();
				$('#vendorSearch').modal("hide");
		
				
			});
		
		
		//Update vendor modal textbox 
		function updateVenPlaceholder(){
		$("#criteria1").attr("placeholder", $("#search-type option:selected").text());
	}
		
		  
	
		function getURL(identifier){

			var searchType = $("input[name='searchSelection']:checked").val();
			var action = $(identifier).attr("data-action");
			var title = $('.criteria').val();
			 var bibtype = "";
			 
			  if($("input[name=type]").is(":checked")){
				  bibtype = $(".tags").val();
		      }
			  
			  
			 if(searchType=="selection"){
			
					var title = $('#criteria').val();
					var searchType = $('#search_type').val();
	
					$("#search_title").collapse('hide');
					$("#result_title").collapse('show');
					loader("#display_title");
					$.get("Global?type=Table&name=Table_BibSearch",{criteria:title,search_type:searchType,action:action, bibtype:bibtype, actiontype:"Search"},function(data_title){
					
						$("#display_title").html(data_title);
					   });	
					}
			else if(searchType == "MatNo"){

				url = "Global?type=Table&name=Table_BibSearch&criteria="+title+"&search_type=MatNo&action="+action + "&bibtype=" + bibtype;
				$("#search_title").collapse('hide');
				$("#result_title").collapse('show');

				$.get(url,function(data_title){
					$("#display_title").html(data_title);
				});	
		}
			else if(searchType == "ctrlNo"){
					var searchType = "ctrlNo";
				
				url = "Global?type=Table&name=Table_BibSearch&criteria="+title+"&search_type="+searchType+"&action="+action + "&bibtype=" + bibtype+ "&actiontype=" + "Search";
				$("#search_title").collapse('hide');
				$("#result_title").collapse('show');
				
				$.get(url,function(data_title){
					$("#display_title").html(data_title);
					$("#input-contorlNo").val($(".hits").attr("data-value"));
					$("#input-contorlNo").text($(".hits").attr("data-value"));
				});	
				
		}else if(searchType == "tag"){
					 var searchType ="tag";
					 var tag = $("#select_tags").val();
					 var title = $('#criteria').val();
				$("#search_title").collapse('hide');
				$("#result_title").collapse('show');
				loader("#display_title");	 
				$.get("Global?type=Table&name=Table_BibSearch",{criteria:title,search_type:searchType,tag:tag,action:action},function(data_title){
					$("#display_title").html(data_title);
				});
		}else if(searchType == "officerID"){
			 var searchtype = $("#officerValue").val();
			 var startDateInput = $("#startDateInput").val();
			 var stopDateInput = $("#stopDateInput").val();

				$.get("Search_OfficerID",{criteria:searchtype,search_type:searchType,
					  action:action, startDateInput: startDateInput,
					  stopDateInput:stopDateInput},function(data_title){		
			$("#officerSearch").modal('show');
			$("#officerResult").html(data_title);

			// $(data_title).appendTo('.modal-content').modal();
		});
	}
			//document.getElementById("current_form").reset();
			$("#current_form").trigger('reset');

		}
		
		//Authority Link Modal 
		$('.auth-row').click(function(){
			var raw = $(this).data("raw");
			var action = $("#action").val();
			
			if(action=="EditTerm"){
			var splitSubf = raw.split("|");
			 var html = "";
				for(var i = 1; i < splitSubf.length; i++)
				{
					var subfield = splitSubf[i].substring(0,1);
					var raw = splitSubf[i].substring(1);
					   var name = $(".concatData").attr('name');

					    html += 
				    	"<tr class='primarySubf'>" + 
					"<td>" + 
						"<a class='btn btn-success btn-xs sort'>" + 
							"<span class='glyphicon glyphicon-sort'></span>"+
						"</a>" + 
					"</td>" + 
					"<td class='subf_"+subfield+"'>" + 
						subfield + 
					"</td>" + 
					"<td colspan='2'>" + 
						"<input class='concatData' name='"+name+"' id='|"+subfield+"' data-desc='|"+subfield+"' value='"+raw+"' onkeyup='getDesc(this);getData(this)' type='text' size='99' style='width:100%'>"+
					"</td>" +
					"<td>"  +
						"<a class='btn btn-dull btn-sm' onclick='removeSubf(this)'>" + 
							"<span class='glyphicon glyphicon-trash'></span>" + 
						"</a>&nbsp;" +
						"<a class='btn btn-default btn-sm duplicateSubf'>" + 
							"<span class='glyphicon glyphicon-duplicate'></span>"+
						"</a>" + 
					"</td>"+
				"</tr>";

			}
				$("tr.primarySubf").empty();
				$("tr.headingTr").after(html);
			}else{
				var tagid = $("#tagid").val();
				$("#BO_Record tbody tr").each(function() {
			        var newClass = $(this).attr('class');
			       if(newClass==tagid){
			    	   $(this).find(".data").val(raw.trim());
			       }
				});
			/*	var rows = document.getElementsByClassName(tagid);
				alert("sdsd");
				$('.' + rows).html(raw.trim());*/
			}
			$('#authlink').modal('hide');
		})
		
		$('#startDateInput').datepicker()
				.on('changeDate', function(ev){
					startDate=new Date(ev.date.getFullYear(),ev.date.getMonth(),ev.date.getDate(),0,0,0);
					if(endDate!=null&&endDate!='undefined'){
						if(endDate<startDate){
								alertMessage("Error", "error", "018", null, null);
								$("#startDateInput").val("");
						}
					}
				});
			$("#stopDateInput").datepicker()
				.on("changeDate", function(ev){
					endDate=new Date(ev.date.getFullYear(),ev.date.getMonth(),ev.date.getDate(),0,0,0);
					if(startDate!=null&&startDate!='undefined'){
						if(endDate<startDate){
							alertMessage("Error", "error", "018", null, null);
							$("#stopDateInput").val("");
						}
					}
				});
		
			
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
				$.get("Global?type=Table&name=Table_TitleHits",{pointer : pointer, action:action},function(data){
					$("#display_title").html(data);
					
				});	
					document.getElementById('controlNo').focus();
				}else if(patrid!=null && bufferno=="bufferNo"){

					var startDate = $(this).attr("data-startDate");
					var endDate = $(this).attr("data-endDate");
					var patrid = $(this).attr("data-patrid");
					var criteria = $(this).attr("data-criteria");
			
					$.get("Global?type=Table&name=Table_BibSearch",{search_type:searchtype,patrid:patrid,startDate:startDate
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
			
					$.get("Global?type=Table&name=Table_BibSearch",{search_type:searchtype,patrid:patrid,startDate:startDate
												,action:action,endDate:endDate,criteria:criteria},function(data_title){
						$("#search_title").collapse('hide');
						$("#result_title").collapse('show');
						$("#display_title").html(data_title);
						$("#officerSearch").modal("hide");
					});
				}
				else if(matno!=null){
					if(action=="8"){
						$('#titleSearch').modal('hide');
						$(".CT03MATNO").val(matno);
						$.get("Global?type=Table&name=Table_BibSearch",{criteria:matno,actiontype:"keyupCtrlNo"},function(title){
							
							$(".title").html(title);
							
						    if(document.getElementById("add-submit")){
						    	 $("#add-submit").prop('disabled', false);
						    }
						   });	
					
					}else if(action=="5"){
					/*	$("#result").collapse('show');*/
						$('#titleSearch').modal('hide');
						if(searchtype=="matno"){
							$.get("Table_SearchByAccNo",{txtMatNo : $(this).attr("data-accno")},function(data){
								$(".accessionList").html(data);
							});
						}else{
						$.get("Table_CTResultMaster",{controlNoInput : matno},function(data){
							$(".accessionList").html(data);
						});
						}
					}
					else{
						var type = $(this).attr("data-type");
				/*	
						if(type=="A"){
							//url = "Handler_BORecord?matno="+matno+"&action=retrieveTpl&TPLID=1";
							url = "Retrieve_Acq?matno="+matno+"&action=IndexUnindex&type=A&TPLID=1";
							$('.collapse').show();
							loader("#tags");
									$.get(url,function(data_title){
										//$('.modal').removeClass('show');
										$("#tags").html(data_title);
										 $("li").removeClass("unindexBO");
										 $("input").prop("disabled", true);
										
									});	
						}else{		
				*/
							url = "Handler_BORecord?matno="+matno+"&action=Index";
							$('.collapse').show();
							loader("#tags");
									$.get(url,function(data_title){
										//$('.modal').removeClass('show');
										$('#titleSearch').modal('hide');
										$('td.orimatno').html('Buffer No:<span class="Cmatno"></span>');
										$("#tags").html(data_title);
										$("#duplicateCopy").removeClass("isDisabled");
										$("#addTag").addClass("isDisabled");
										$("#searchAccNo").removeClass("isDisabled");
										$("#addBORcrd").addClass("isDisabled");
										$("#indexBO").addClass("isDisabled");
										$("#saveindex").addClass("isDisabled");
										$("#unindex").removeClass("isDisabled");
										$("#delete").addClass("isDisabled");
										$("#modify").addClass("isDisabled");
					
										//$(".delete, .btn-raised, .subfButton").attr("disabled","disabled");
										$("input, .editdata, #retrieveLinks, .delete, .linkage ").attr("disabled","disabled");
									});	
						}
						
							//$('#titleSearch').modal('hide');
					//}
				}else if (bufferno!=null){
					url = "Handler_BORecord?matno="+bufferno+"&action=Buffer";
					$('td.orimatno').html('Buffer No:<span class="Cmatno"></span>');

					$.get(url,function(data_title){
						if(action!="7"){
							$('#titleSearch').modal('hide');
						}else{
							$('#titleSearch').modal('hide');
						}
						
						$('.collapse').show();
						$("#tags").html(data_title);
						$("li").removeClass("deleteBO");
						$("input, .editdata, #retrieveLinks, .delete, .linkage ").attr("disabled","disabled");
						 $("#duplicateCopy").addClass("isDisabled");
							$("#addTag").addClass("isDisabled");
							$("#searchAccNo").addClass("isDisabled");
							$("#addBORcrd").addClass("isDisabled");
							$("#indexBO").removeClass("isDisabled");
							$("#saveindex").removeClass("isDisabled");
							$("#unindex").addClass("isDisabled");
							$("#delete").removeClass("isDisabled");
							$("#modify").removeClass("isDisabled");
							//$("li").removeClass("modifyBO");
			 			 	$('#modify').attr('onClick','modifyRcrd()');
					});	
				}else if(searchtype=="officerID"){
				
						var startDate = $(this).attr("data-startDate");
						var endDate = $(this).attr("data-endDate");
						var patrid = $(this).attr("data-patrid");
						var criteria = $(this).attr("data-criteria");
						
						$.get("Global?type=Table&name=Table_BibSearch",{search_type:searchtype,patrid:patrid,startDate:startDate
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
		