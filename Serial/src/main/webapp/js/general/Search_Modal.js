/**
 * 
 */

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

/*
  $('input:radio').click(function(){
	  alert("sssss");
	        if ($(this).val()=="selection"){
	            $("#search_type").prop("disabled", false);
	            $("#select_tags").prop("disabled", true);
	            $("#officerValue").prop("disabled", true);
	            $("#criteria").attr('maxlength','');
	        } else if ($(this).val()=="tag"){
	        	  $("#search_type").prop("disabled", true);
	        	  $("#officerValue").prop("disabled", true);
	        	document.getElementById("select_tags").disabled = false;
	        	$("#criteria").attr('maxlength','');
	        }else if($(this).val()=="buffer"){
	       	 	$("#select_tags").prop("disabled", false);
	       	 	$("#select_tags").val("245");
	       	 	$('#tag').prop('checked', true);
	        }else if($(this).val()=="MatNo"|| $(this).val()=="ctrlNo"){
	        	$("#criteria").attr('maxlength','10');
	        }
	        else{
	        	 $("#search_type").prop("disabled", true);
	        	 $("#select_tags").prop("disabled", true);
		        	document.getElementById("officerValue").disabled = false;
		        	$("#criteria").attr('maxlength','');

	        }
	        
  });*/
		//Vendor modal submit button 
		$('.vendor_submit').click(function(){			
			var vendor_details = $('#criteria1').val();
			var searchType = $('#search-type').val();
			var type = $(".type").val();
			$("#search_vendor").collapse('hide');
			$("#result_vendor").collapse('show');
			loader("#display_vendor");
			$.get("SearchVendor",{criteria:vendor_details,search_type:searchType,action:"modalVendor", type:type},function(data_vendor){
				$("#display_vendor").html(data_vendor);
			   });			
		})
	
			$('.clickable-row1').click(function() {
				var type=$(".type").val();

				if(type=="vendor"){
					$(".CT03VEND").val( $(this).attr('data-value'));
					$(".CT03VEND").focus();
					$(".div-vendorName").html($(this).find(".vendorName").text());
				}else if(type=="binder"){
					$(".binder").val( $(this).attr('data-value'));
					$(".binder").focus();
					$(".div-binderName").html($(this).find(".vendorName").text());
				}else if(type=="publisher"){
					$(".publisher").val( $(this).attr('data-value'));
					$(".publisher").focus();
					$(".div-publisherName").html($(this).find(".vendorName").text());
				}
				$('#vendorSearch').modal("hide");
			/*	document.getElementById('CT03VEND').value = $(this).attr('data-value');
				document.getElementById('div-vendorName').innerHTML = $(this).find(".vendorName").text();
				$('#vendorSearch').modal("hide");*/
		
				
			});
		
		
		 $('#vendorModal').on('hidden.bs.modal', function(e)
				    { 

				        $(this).removeData();
				    }) ;
		//Update vendor modal textbox 
		function updateVenPlaceholder(){
		$("#criteria1").attr("placeholder", $("#search-type option:selected").text());
	}
		
		  
	
		function getURL(identifier){
			var searchType = $("input[name='searchSelection']:checked").val();
			var action = $(identifier).attr("data-action");
			var title = $('.criteria').val();
			 if(searchType=="selection"){
					var title = $('#criteria').val();
					var searchType = $('#search_type').val();
					/*loader("#div-result");*/
					$("#search_title").collapse('hide');
					$("#result_title").collapse('show');
					loader("#display_title");
					$.get("RetrieveSearchTitle",{criteria:title,search_type:searchType,action:action},function(data_title){
						$("#display_title").html(data_title);
					   });	
					}
			else if(searchType == "MatNo"){
				loader("#div-result");
				$.get("Table_SearchByAccNo",{txtMatNo : title},function(data){
					$(".accession_list").html(data);
				});
				$("#titleSearch").modal("hide");
		}
			else if(searchType == "ctrlNo"){
					var searchType = "ctrlNo";
				
				url = "RetrieveSearchTitle?criteria="+title+"&search_type="+searchType+"&action="+action;
				$("#search_title").collapse('hide');
				$("#result_title").collapse('show');
			/*	loader("#display_title");*/
				$.get(url,function(data_title){
					$("#display_title").html(data_title);
				});	
		}else if(searchType == "tag"){
					 var searchType ="tag";
					 var tag = $("#select_tags").val();
					 var title = $('#criteria').val();
				$("#search_title").collapse('hide');
				$("#result_title").collapse('show');
				loader("#display_title");	 
				$.get("RetrieveSearchTitle",{criteria:title,search_type:searchType,tag:tag,action:action},function(data_title){
					$("#display_title").html(data_title);
				});
		}else if(searchType == "officerID"){
			 var searchtype = $("#officerValue").val();
			 var startDateInput = $("#startDateInput").val();
			 var stopDateInput = $("#stopDateInput").val();

		/*$.get("Search_OfficerID",{criteria:searchType,search_type:searchtype,
								  action:action, startDateInput: startDateInput,
								  stopDateInput:stopDateInput, buffer:"bufferNo"},function(data_title){*/
				$.get("Search_OfficerID",{criteria:searchtype,search_type:searchType,
					  action:action, startDateInput: startDateInput,
					  stopDateInput:stopDateInput},function(data_title){		
			$("#officerSearch").modal('show');
			$("#officerResult").html(data_title);

			// $(data_title).appendTo('.modal-content').modal();
		});
	}
			document.getElementById("current_form").reset();

		}
		
		//Authority Link Modal 
		$('.auth-row').click(function(){
			var raw = $(this).data("raw");
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
						"<input class='concatData' name='"+name+"' id='|"+subfield+"' data-desc='|"+subfield+"' value='"+raw+"' onkeyup='getDesc(this);getData(this)' type='text' size='99'>"+
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
		
		