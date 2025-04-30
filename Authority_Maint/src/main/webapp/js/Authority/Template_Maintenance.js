/**
 * 
 */
$("[title]").tooltip();
function addTplNTplInfo(tplID, tplName, status, type){
	var id ="";
	var tag = [];
	var subf = [];
	var indi1=[];
	var indi2=[];
	  var url = "Handler_AddTpl?CT15SEQNO=" + tplID + 
     	"&CT15TNAME=" + tplName+ 
		"&CT15STAT=" + status + 
		"&CT15TYPE=" + type;
	  
	  $.ajax({
			url: url,
			success: function(result) {
			
				//If template added successfully
			if(result.trim() == "ok"){
				$('#tagTable').find('tbody tr').each(function () {
					// $.get("Handler_BOValidation",{validation:"GetTplCounter"},function(counters){
				
						id =  $(this).find("td.tplId").text().trim();
						var tags  =  $(this).find("td.tag").text().trim();
						var subfs = $(this).find("td.subfields").text().trim();
						var indi1s = $(this).find("td.indi1").text().trim();
						var indi2s =  $(this).find("td.indi2").text().trim();
						indi1.push(indi1s);
						indi2.push(indi2s);
						tag.push(tags.trim());
						subf.push(subfs);
				});
				$.get("Handler_AddNewTplMaint",{id:id, tag:tag,indi1:indi1,
					indi2:indi2,subf:subf, row:tag.length, action:'appendExistTag'},
					function(message){
						if(message.trim()=="Successful"){
							alertMessage("Successful", "success", "005","added template", "@action");
							  var $container = $("#display_title");
							  $("#addRcrd").removeClass("in");
							  $(".modal-backdrop").remove();
							  $('body').removeClass('modal-open');
							  $('body').css('padding-right', '');
							  $("#addRcrd").hide();
							  $('.swal2-confirm').click(function(){
								  location.reload();
							  });
						}else{
							alertMessage("Waring", "Fail to save", "");
						}
			      
					   });

			}else{
				messageBox("008",tplName, "@TplName");
			}

		}
 });	
}

$(document).on('hide.bs.modal', '#addRcrd', function () {
	 $(this).removeData('bs.modal');
});

function addTplInfo(tplID){
	var id ="";
	var tag = [];
	var subf = [];
	var indi1=[];
	var indi2=[];
	var next = false;	 
	 var rowCount = $('#tagTable tbody tr').length;
		var done;
		 if(rowCount>0){

			$('#tagTable').find('tbody tr').each(function () {
					// $.get("Handler_BOValidation",{validation:"GetTplCounter"},function(counters){
				
						id =  $(this).find("td.tplId").text().trim();
						var tags  =  $(this).find("td.tag").text().trim();
						var subfs = $(this).find("td.subfields").text().trim();
						var indi1s = $(this).find("td.indi1").text().trim();
						var indi2s =  $(this).find("td.indi2").text().trim();
						indi1.push(indi1s);
						indi2.push(indi2s);
						tag.push(tags.trim());
						subf.push(subfs);
				});
			 $.get("Delete_TplDetails?CTMSTR="+tplID,function(status){
				if(status.trim()=="ok"){
					var tplName = $(".tplName").text().trim();
					$.get("Handler_AddNewTplMaint",{id:id, tag:tag,indi1:indi1,
						indi2:indi2,subf:subf, row:tag.length, action:'appendExistTag'},
						function(message){
							if(message.trim()=="Successful"){
								
								alertMessage("Successful", "success", "005","added template", "@action");
								  var $container = $("#display_title");
								  $("#addRcrd").removeClass("in");
								  $(".modal-backdrop").remove();
								  $('body').removeClass('modal-open');
								  $('body').css('padding-right', '');
								  $("#addRcrd").hide();
									var url = "ShowTplDetails?seqNo="+tplID + "&tplName="+encodeURI(tplName);
					
							        var refreshId = setTimeout(function()
							        {
							            $container.load(url);
							        }, 2000);
							}else{
								$('#addTplInfo').prop('disabled', false);
								alertMessage("Waring", "Fail to save", "");
							}
				      
						   });
				}
			 });
		}else{
		var tplName = $(".tplName").text().trim();
		$('#tagTable').find('tbody tr').each(function () {
			// $.get("Handler_BOValidation",{validation:"GetTplCounter"},function(counters){
		
				id =  $(this).find("td.tplId").text().trim();
				var tags  =  $(this).find("td.tag").text().trim();
				var subfs = $(this).find("td.subfields").text().trim();
				var indi1s = $(this).find("td.indi1").text().trim();
				var indi2s =  $(this).find("td.indi2").text().trim();
				indi1.push(indi1s);
				indi2.push(indi2s);
				tag.push(tags.trim());
				subf.push(subfs);
		});
		 $.get("Handler_AddNewTplMaint",{id:id, tag:tag,indi1:indi1,
				indi2:indi2,subf:subf, row:tag.length, action:'appendExistTag'},
				function(message){
					if(message.trim()=="Successful"){
						alertMessage("Successful", "success", "005","added template", "@action");
						  var $container = $("#display_title");
						  $("#addRcrd").removeClass("in");
						  $(".modal-backdrop").remove();
						  $('body').removeClass('modal-open');
						  $('body').css('padding-right', '');
						  $("#addRcrd").hide();
						  $(".swal2-confirm").click(function(){
							  	 var url = "ShowTplDetails?seqNo="+tplID + "&tplName="+encodeURI(tplName);
							  	
								$.get(url,function(data){
						
									$('#search').collapse('hide');
							
									$("#display_title").html(data);
									$('#result_tags').collapse("show").height("auto");
									
							   });	
						  })
					}else{
						alertMessage("Waring", "Fail to save", "");
					}
		    	  alertMessage("Successful", "success", "005","added template", "@action");

				});
		/* $("table.tagTable > tbody > tr").each(function(Mindex, Mval) {
			 $.get("Handler_BOValidation",{validation:"GetTplCounter"},function(counter){
		       var url = "Handler_AddNewTplMaint?CTMSTR=" + $(Mval).find('td:eq(0)').text() + 
		       			"&CTTPLTAG=" + $(Mval).find('td:eq(1)').text()+ 
			  			"&CTTPLSUBF=" + $(Mval).find('td:eq(4)').text() +
			  			"&CTTPLINDI1=" + encodeURIComponent( $(Mval).find('td:eq(2)').text() )+
			  			"&CTTPLINDI2=" + encodeURIComponent( $(Mval).find('td:eq(3)').text() )+
			  			"&counter=" + counter.trim() + 
			  			"&action=" + "appendExistTag";

			         $.get(url,function(data_title){
		
			      
					   });
			           });
		 });*/
				/*  alertMessage("Successful", "success", "005","added template", "@action");
				  $(".swal2-confirm").click(function(){
					  	 var url = "ShowTplDetails?seqNo="+tplID + "&tplName="+encodeURI(tplName);
					  	
						$.get(url,function(data){
				
							$('#search').collapse('hide');
					
							$("#display_title").html(data);
							$('#result_tags').collapse("show").height("auto");
							
					   });	
				  })*/
		}
	 
	
}
   
//Hide options from dropdown list
$(function(){
   $('#updatesubfields option:selected').hide(); //initialise
  
   $('#updatesubfields').change(function(){
     
     $('#updatesubfields option').show(200, function(){
       
         $('#updatesubfields option:selected').hide();
       
     });
     
   });
})

//Trim value 
function clearSpace(input){
	var output = input.replace(/\s+/g, '');
	return output;
}


//Collapse panel to show values for each template
		function myFunction(newID){
		
			var details = newID.split(",");
			var url = "ShowTplDetails?seqNo="+details[0] + "&tplName=" + details[1];
			$('#search').collapse('hide');
			$('#result_tags').collapse("show").height("auto");
			loader('#display_title');
			var tags = [];
				$.get(url,function(data_title)
						{
					 
							
							$("#display_title").html(data_title);
							 $('#showtpldetails').find('tbody tr').each(function(index){
								  var CT04TAG = $(this).find("td.GL17TAG").text();
				    			  tags.push(CT04TAG.trim());
				    		  });
							 $('#btn_addTplInfo').attr('href',"AddNewTplInfo?action=2&tplName="+encodeURI(details[1].trim())+
										"&seqNo="+details[0] + "&GL17TAG=" + tags.toString());
					
			   });			
		}
		
		//Delete template name 
		function deletePlugin(tplID){
			var split = tplID.split(",");
			var tplID =split[0];
			var tplName = split[1];
			swal({   
				title: 'Are you sure want to delete '+ tplName+'?',
				text: 'The status of template will be inactive',
				type: 'warning',   showCancelButton: true,
				confirmButtonColor: '#3085d6', 
				cancelButtonColor: '#d33',
				confirmButtonText: 'Yes, delete it!',
				cancelButtonText: 'No, cancel !',
				confirmButtonClass: 'confirm-class',
				cancelButtonClass: 'cancel-class',
				closeOnConfirm: false,
				closeOnCancel: false 
				}).then(function() {
					 var deleteUrl = "Handler_DeleteTplMaint?tplID="+tplID;
					  
				      $.ajax({
				 			url: deleteUrl,
				 			success: function(result) {
				 				var status = result.replace(/\s+/g, '');
				 				if(status == "ok"){
				 					  swal(
										'Deleted!', tplName+ ' template has been deleted.','success'
										);
				 					$('.swal2-confirm').click(function(){
					  					location.reload(); 
					   		        });
		
				 				}else{
				 				   swal(
										'Not Deleted',
										'Template name exist in other table',
										'error'
								  );
				 				}
				 			}
				 		});
							},function(dismiss) {
								  if (dismiss === 'cancel') {
								    swal(
								      'Cancelled',
								      'Your imaginary file is safe :)',
								      'error'
								    );
								  }
								})
		}
			
	//Reload upon click o cancel button
/*	 $('#btn-cancel').click(function(){
		 history.go(0);
	 });*/
	 
		$(".cancels").click(function(){
			location.reload();
		});
		
		 $("#TextAreaName").change(function() {
		        $(this).attr("data-changed", true);
		    });

		    $("#Save").click(function(event) {
		        event.preventDefault();

		        if ($("#TextAreaName").attr("data-changed") === "true") {
		            //Do your saving here

		            //Reset the state
		            $("#TextAreaName").attr("data-changed", false);
		        }
		    });
		    
		    function changeRecord(){
		    	var oriTpl = $("#hiddenTpl").val();
		    	var newTpl = $("#CT15TNAME").val();
		    	
		    	var rcrdChanged;
		    	if(oriTpl==newTpl){
		    		rcrdChanged = false;
		    	}else{
		    		rcrdChanged = true;
		    	}
		    	
		    	return rcrdChanged;
		    }
	 //Update template name, status  
	 	$('#btn-update').click(function(){
	
	 		var rcrdExist = changeRecord();

	   		var url = "Handler_EditTpl?CT15SEQNO=" + $('#CT15SEQNO').val() +
	   				"&CT15TNAME=" + $('#CT15TNAME').val() + "&CT15TYPE=" + $('#type').val() +"&action=" + rcrdExist;
	   				url += "&CT15STAT=";
					if ($('#CT15STAT').is(':checked')) {url += "A"}else{url += "I"};
			
	   		$.ajax({
	   			url: url,
	   			success: function(result) {
	    				var updateRecord = clearSpace(result);
	  				if(updateRecord == "ok"){
	  					$('#updateModal').modal('hide');
	  					swal(   'Successful!',   'Template '+$('#CT15TNAME').val()+' updated',   'success' )
	  				$('.swal2-confirm').click(function(){
	  					location.reload(); 
	   		        });
	  				}
	  				else{
	  					messageBox("008",$('#CT15TNAME').val(), "@TplName");
	  				}
	  			}
	   		});
	   	});
	 	
	 /*	$(".tags").change(function(){
	 		var tagOpList = [];
	 		var repeatable = $(this).find(':selected').attr('data-repeat');
	 		var tag = $(this).val().trim();
	 		
	 		var appendTag = $(".appendTag");

	 		if (appendTag.addEventListener) {
	 			appendTag.addEventListener("click", function() {
	 		        alert("clicked");
	 		    }, false);
	 		} else { 
	 			alert("none");
	 		}
	 		  //Push selected tags options 
	           $('#tagTable').find('tbody tr').each(function () {
	        	   tagOpList.push(tag);
	           });
	 		alert(tagOpList);
	          
        	   if(repeatable=="N"){
	        	  if(tagOpList.indexOf(tag) > 0){
	        		  alert("exist");
	        	  }else{
	        		  alert("none");
	        	  }
        	   }
	 	});*/
	 	
	 	//Keyin the template change to uppercase
	 	$('#tplName, #CT15TNAME').keyup(function(){
	 	    this.value = this.value.toUpperCase();
	 	});
	 	
	 $('.modal').on('hidden.bs.modal', function(e)
	 { 
	 	 $(this).removeData();
	 }) ;
	 	  
		 $('#addTplInfo').click(function(){
		
			  var tplID = $('#tplId').val();
			  var status = $('#status').val();
			  var name = $('#tplname').val();
			  var type = $('#type').val();
			  if(name.trim()==""){
				  swal("", "Please fill in the template name", "")
			  }else{
			  var selected = [];
			  var selectedtxt = [];
			  var splittxts = [];
			  var tags = [];
			  var exist = true;
			  //Push selected subfield options
	           $('#subfields option:selected').each(function() {
	               selected.push([$(this).val()]);
	               selectedtxt.push([$(this).text()]);
	           });
	           
	           //Push selected tags options 
	           $('#tagTable').find('tbody tr').each(function () {
	        	   var CT16TAG = $(this).find("td.tag").text();
	        	   tags.push(CT16TAG.trim());
	           });

	           var str="";
	           for (var i = 0; i < selected.length; i++) {
	        	   str+=selected[i];
	        	 }
	           
	           //Check if mandatory tag exist
	           $.get("Handler_BOValidation",{validation:"MandaTag"},function(tag){
	        	 if(tag.trim().length!=0){
	        	   var res = tag.trim().split("\n");
	        	   
	        	   $.each(res, function(k){
		 			  if(tags.indexOf(res[k].trim())>=0){
		 				  exist = true;
		 			  }else{
		 				  alertMessage("", "info", "011", res[k], "@tag ");
		 				  exist = false;
		 			  }
	 				
		 			  return exist;
		 			  
	            });
	        	 }

	        	   //If exist then add template and tags
	        	   if(exist==true&&name!=null){
	        		 var tplName = $('#tplname').val();
	        		 var action = $(".action").val();
	        	
	        		 if(action=="upAction"){
	        			 $('#addTplInfo').prop('disabled', true);
	        			 addTplInfo(tplID);
	        			 $("#addRcrd").modal("hide");
	        		 }else{
						addTplNTplInfo(tplID, tplName, status, type);
						$("#addRcrd").modal("hide");
	        		 }
	        	   }else if(exist==true){
	        		   var tplName = $('#tplname').val();
	        		  var action = $(".action").val();
	        		 if(action=="upAction"){
	        			 $('#addTplInfo').prop('disabled', true);
	        			 addTplInfo(tplID);
	        			 $("#addRcrd").modal("hide");
	        		 }else{
	        			 addTplNTplInfo(tplID, tplName, status, type);
	        			 $("#addRcrd").modal("hide");
	        		 }
	        	   }
	        	   
	           });
			  }
		 });
		 
		//Update indicators and subfields 
		$('#updateTplInfo').click(function(){
			var indiValue1 = $("#updateIndi1").val();
			var indiValue2 = $("#updateIndi2").val();
			var tplName = $(".tplname").val();

	 		  var selected = [];
			  var selectedtxt = [];
			  var splittxts = [];
	           $('#updatesubfields1 option:selected').each(function() {
	               selected.push([$(this).val()]);
	               selectedtxt.push([$(this).text()]);
	           });
	           var str="";
	           for (var i = 0; i < selected.length; i++) {
	        	   str+=selected[i];
	        	 }

	    
	   		var url = "Handler_EditTplInfo?CTTPLID=" + $('#empId').val()+
	   			"&CTTPLNAME=" + $('#tplName').val()+
   				"&CTTPLTAG=" + $('#name').val() +
   				"&CTTPLINDI1=" + encodeURIComponent(indiValue1)+
   				"&CTTPLINDI2=" + encodeURIComponent(indiValue2) +
   				"&CTTPLSUBF=" + str+
   				"&CTMSTR=" + $('#seqNo').val();
				
			$.ajax({
	   			url: url,
	   			success: function(result) {
	   				
	   				
	   				swal(   'Successful!',   'Record for '+$('#tplName').val()+' successfully updated',   'success' )
	   				$('.swal2-confirm ').click(function(){
  					$('#updateModal1').modal('hide');
	  					
	  					var url = "ShowTplDetails?seqNo="+$('#seqNo').val() + "&tplName="+ $('#tplName').val();
    					loader("#display_title");
	  					$.get(url,function(data_title){
	  			
	  						$('#search').collapse('hide');
	  				
	  						$("#display_title").html(data_title);
	  						$('#result_tags').collapse("show").height("auto");
	  						
	  				   });	
	   				});
	  				var updateRecord = result;
	  				if(updateRecord == "ok"){
	  					//showPopupMsg("Update successful.","success");
	
	  				}else{
	  					showPopupMsg("Please fill up the necessary details.","warning");
	  				}
	  			}
	   		});
	

	   	});
		
		//Modal_AddTplInfo.jsp
		/*To retrieve Indicators and Subfields*/
		 function getIndicators(){
			  var tag = $(".tags option:selected").val();
				var url = "Indicators_Handler?GL18TAG=" + tag;
				$.get(url,function(data){
				$("#indicator").html(data);
				});	
			}

	//Add new tag, indicators and subfields 
	
			
	
			//Call error message page 
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
			
			
				//Append to add more than one tag 
			var tagOpList = [];
				$(".appendTag").click(function(){

			 		var repeatable = $(".tags").find(':selected').attr('data-repeat');
			 		var tag = $(".tags").val().trim();
					  var selected = [];
					  var selectedtxt = [];
					  var splittxts = [];
					  //Push selected tags options 
			       
			        	tagOpList.push(tag);
			        				           
				     $('#subfields option:selected').each(function() {
			               selected.push([$(this).val()]);
			               selectedtxt.push([$(this).text()]);
			           });
			           var str="";
			           for (var i = 0; i < selected.length; i++) {
			        	   str+=selected[i];
			        	 }

			   		 
			   		
			   		 var table = document.getElementById("tagTable").getElementsByTagName('tbody')[0];
					    var row = table.insertRow(table.rows.length);
					    var cell1 = row.insertCell(0);
					    var cell2 = row.insertCell(1);
					    var cell3 = row.insertCell(2);
					    var cell4 = row.insertCell(3);
					    var cell5 = row.insertCell(4);
					    var cell6 = row.insertCell(5);
					    cell1.innerHTML = $('#tplId').val();
					    cell1.className = 'tplId';
					    cell2.innerHTML = tag;
					    cell2.className = 'tag';
					    cell3.innerHTML = $('#indi1').val();
					    cell3.className = 'indi1';
					    cell4.innerHTML = $('#indi2').val();
					    cell4.className = 'indi2';
					    cell5.innerHTML = str;
					    cell5.className = 'subfields';
					    cell6.innerHTML = '<button class="btn btn-dull" onclick="deleteRow(this)"><span class="glyphicon glyphicon-trash" id="delPOIbutton"></span></button>';
					    
					    if(repeatable=="N"){
				        	   if(tagOpList.indexOf(tag)>=0){
						 			document.getElementById(tag).disabled = true;
						 			  }
				        	   }
					    if($("#tplname").val()!= null && $("#tplname").val().length==0){
			    			$('#addTplInfo').prop('disabled', true);
				    	}else{
				    		  $(".addTplInfo").removeAttr("disabled");
				    	}
					  
				});
			    $("#customFields").on('click','.remCF',function(){
			        $(this).parent().parent().remove();
			    });

			    //Delete the append tags 
			    function deleteRow(row)
			    {
			        var i=row.parentNode.parentNode.rowIndex;
			        	$(".tagTable tr:eq("+i+")").each(function(){
			        		 var CT16TAG = $(this).find("td.tag").text();
			        			document.getElementById(CT16TAG).disabled = false;
			 			        document.getElementById('tagTable').deleteRow(i);
			        	});
			  
			    }

			    	//Reload the modal 
			    	$("#updateModal1").on("show.bs.modal", function(e) {
			    	    var link = $(e.relatedTarget);
			    	    $(this).find(".modal-content").load(link.attr("href"));
			    	});
			    /*
			    	$("#tplName").change(function(){
			    		var input = document.getElementById("tplName");
			    		if (input.val().length > 0) {
			    			$('#addTplInfo').prop('disabled', true);
			    		}
			        });*/
			    	
			    	$("#tag").change(function(){
			    		var e = document.getElementById("tag");
			    		var strUser = e.options[e.selectedIndex].value;
			    		
			    		 if(strUser=="0"){
			    			 //$('#addTplInfo').prop('disabled', true);
			    			 $('.appendTag').prop('disabled', true);
			    		 }
			    		 else if ($("#tag option:selected").length) {
			    				//$('#addTplInfo').prop('disabled', false);  
			                	$('.appendTag').prop('disabled', false);
					    }
			        });
	
			    	$("#tplname").keyup(function(){
			    		 if($("#tplname").val()== null || $("#tplname").val().length==0){
			    			 $('#addTplInfo').prop('disabled', true);
			    		 }else{
			    			 $('#addTplInfo').prop('disabled', false);
			    		 }
			    	});
			    	
			    	$("#addRcrd").on("shown.bs.modal", function(){
			    	
			    		 $(this).removeData();
			    		 if($(".tplName").text()!= null && $(".tplName").text()!= ''){
				    			$('#addTplInfo').prop('disabled', false);
					    	}
			    		 if($("#tplname").val()== null || $("#tplname").val().length==0){
				    			$('#addTplInfo').prop('disabled', true);
					    	}
			    		
			    		/*
			    		 if($("#tplName").val()!= null && $("#tplName").val()!= ''){
			    			 alert("in1");
				    			$('#addTplInfo').prop('disabled', false);
					    	}
			    		if($(".tplName").text()!= null && $(".tplName").text()!= ''){
			    			alert("in2");
			    			$('#addTplInfo').prop('disabled', false);
				    	}*/
			    		// $('#collapseOne').collapse("show");
			    	});
			    if (document.getElementById("tplName")) {
			    	 $("#tplName").keyup(function(){
			    		  var txt = $('#tplName');  
			                if (txt.val() != null && txt.val() != '') {  

			                	$('#addTplInfo').prop('disabled', false);  
			                	//$('.appendTag').prop('disabled', false);
			                } else {  

			                	$('#addTplInfo').prop('disabled', true);
			                	//$('.appendTag').prop('disabled', true);
			                }  
				    		
				        });
			    	}else{
			    		$('#addTplInfo').prop('disabled', false);  
			    	}
			
			    	function deleteTplPlugin(tplID){
			    		var split = tplID.split(",");
			    		var tplID =split[0];
			    		var tplName = split[1];
			    		var tag = split[2];
			    		var tpl = $(".tplname").val();
			    		swal({   
			    			title: 'Are you sure want to delete?',
			    			text: 'The record will be removed from the template',
			    			type: 'warning',   showCancelButton: true,
			    			confirmButtonColor: '#3085d6', 
			    			cancelButtonColor: '#d33',
			    			confirmButtonText: 'Yes, delete it!',
			    			cancelButtonText: 'No, cancel !',
			    			confirmButtonClass: 'confirm-class',
			    			cancelButtonClass: 'cancel-class',
			    			closeOnConfirm: false,
			    			closeOnCancel: true 
			    			}).then(function() {
			    				 var deleteUrl = "Handler_DeleteTplInfo?CTTPLID="+tplID;
			    					swal( 'Deleted!','Tag ' + tag+ ' has been deleted.','success');  
			    					loader("#display_title");
			    				      $.ajax({
			    				 			// Title
			    				 			url: deleteUrl,
			    				 			success: function(result) {
			    				 				var status = result.replace(/\s+/g, '');
			    				 				if(status == "ok"){
			    				 					//$("#div-result").html(" ");
			    				 					//$('#result').collapse("show");
			    				 					// location.reload();
			    				 					var url = "ShowTplDetails?seqNo="+tplName + "&tplName="+encodeURI(tpl);

			    				  					$.get(url,function(data_title){
			    				  			
			    				  						$('#search').collapse('hide');
			    				  				
			    				  						$("#display_title").html(data_title);
			    				  						$('#result_tags').collapse("show").height("auto");
			    				  						
			    				  				   });	
			    				 					showPopupMsg('Data deleted succesfully.', 'success');
			    				 				}else{
			    				 					 location.reload();
			    				 					showPopupMsg('Having issues deleting data. Please try again later.', 'warning');
			    				 				}
			    				 				//updateTableWithLastQuery();
			    				 			}
			    				 		});
										},function(dismiss) {
											  if (dismiss === 'cancel') {
											    swal(
											      'Cancelled',
											      'Your record is safe :)',
											      'error'
											    );
											  }
											})
			    
			    	}
			    	
			    
			    	
/*//Datatable plugin
$(window).load(function(){
	$(document).ready(function () {
	   $('#tplTable').DataTable({
	       responsive: true
	   });
	   
	 
	 //When retrieve template details 
	   $('#showtpldetails').DataTable({
		        responsive: true
		    });
	   
		  $(".collapse").on('shown.bs.collapse', function(){
	  	        alert('The collapsible content is now fully shown.');
	  	    });
	   
	   $.get("template",{MODULE:"Cataloging/01_Template_Maintenance",ACTION:"List_Of_Tpl.jsp"},function(data_title){
	   });
	});
});

$('.panel-collapse').on('shown.bs.collapse', function (e) {
    var tableIdToUpdate = $(e.currentTarget).find('.panel-body').find('.table')[0].id;
    alert(tableIdToUpdate);
    $('#' + tableIdToUpdate).DataTable().columns.adjust().responsive.recalc();
});*/
			    	
			    
			    	$(document).ready(function () {
			    		
			    		$("#tplName").focusout(function() {
			    			var tplName = $(this).val();
			    			
			    			if(tplName.trim().length==0){
			    				$("#addTplInfo").prop("disabled",true);
			    			}else{
			    				$("#addTplInfo").prop("disabled",false);
			    			}
			    		});
			    		
			    	    $.get("template",{MODULE:"Cataloging/01_Template_Maintenance",ACTION:"List_Of_Tpl.jsp"},function(data_title){
			    		   });
			    	});

			    /*	$('.panel-collapse').on('shown.bs.collapse', function (e) {
			    	    console.log($(e.currentTarget).find('.panel-body').find('.table')[0].id);
			    	    var tableIdToUpdate = $(e.currentTarget).find('.panel-body').find('.table')[0].id;
			    	    $('#' + tableIdToUpdate).DataTable().columns.adjust().responsive.recalc();
			    	});
	*/
$("#updateModal").on("show.bs.modal", function(){
	 $(this).removeData();
});

$("#updateModal1").on("show.bs.modal", function(){
	 $(this).removeData();
});

/*$("#addRcrd").on("hidden.bs.modal", function(){
	 $(this).removeData();
});
*/
