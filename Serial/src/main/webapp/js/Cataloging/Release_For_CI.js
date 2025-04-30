$(document).ready(function(){  
	$("[title]").tooltip();
	
	//enter accession no to release circulation (fast release)
	$('#accMatNo').unbind().keypress(function(event){
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode == '13'){
     	  if($("#fastRelease").prop("checked") == true){
            	
     		  	var docNo = $('#accMatNo').val();

     		  if(docNo.length > 0){
     		  	var print = $('#print').is(':checked');
     		 	
     		  	//$('#releaseBtn').prop('disabled', false);
			  	$('.errorMessage').html('');
			  	 $(".accTitle").html('');
			  	  //var getDocNo =  $("#getDocNo").attr("value");
			  	  $.get("Handler_Release",{CT03DOCNO:docNo},function(validate){
			  		  if(validate.trim()=="ok"){
			  			 $('#releaseBtn').prop('disabled', false);
    			$.get("ReleaseCirculation",{CT03DOCNO:docNo},function(status){
    				var splitStatus = status.trim().split("?");
    				if(splitStatus[0]=="error"){
    		  			var code = "001";
               		var criteria = docNo;
               		var label = "@docNo";
    		  			  $.get("Error_Message?GL79ERRCODE="+code+"" + 
    	               				"&criteria=" + criteria + "&label="+label+"",function(msg){
    				        
    				        	   $('#releaseList').append("<tr><td>" + docNo + "</td>" + 
    					   			            "<td>" + msg + "</td>" + 
    					   			         "<td>" + splitStatus[1] + "</td>" + 
    					   			      "<td>" + splitStatus[2] + "</td>" + 
    					   				  	"</tr>");
    				                   //document.getElementById("testvalue").value =  document.getElementById("accMatNo").value;
    				        	   		$('#accMatNo').val('');
    				                   $("#accMatNo").focus(); 
    		              	   });
    		  		  }else{
    		  			var code = "002";
               		var criteria = docNo;
               		var label = "@docNo";
    		  			  $.get("Error_Message?GL79ERRCODE="+code+"" + 
    	               				"&criteria=" + criteria + "&label="+label+"",function(msg){
    	
    				        	   $('#releaseList').append("<tr><td>" + docNo + "</td>" + 
    				   			            "<td>" + msg + "</td>" + 
    				   			         "<td>" + splitStatus[1] + "</td>" + 
    				   			      "<td>" + splitStatus[2] + "</td>" + 
    				   				  	"</tr>");
    				                   //document.getElementById("testvalue").value =  document.getElementById("accMatNo").value;
    				                   $('#accMatNo').val('');
    				                   $("#accMatNo").focus(); 
    		              	   });
    		  			  	if (print){
							
							$.get("Handler_CIRESV",{CT03DOCNO:docNo, CT03MATNO:splitStatus[3],action:"check"}
									,function(status){
							
						if(status.trim()=="ok"){
						var accMatNo = docNo;
						var accTitle = splitStatus[1];
						var accCallNo =splitStatus[2];
						var matno = splitStatus[3];
						
						var menuID= "CT";
						var url = "GeneratePreviewDocument?accMatNo=" + accMatNo
									+ "&accTitl=" + accTitle
									+ "&accCallNo=" + accCallNo
									+ "&matno=" + matno
									+ "&print=" + print
									+ "&menuID=" + menuID;
		
						window.open(url);
						
						/*  $.get("Handler_CIRESV",{CT03DOCNO:docNo, CT03MATNO:splitStatus[3],action:"update"}
						  	,function(msg){
						  });
			*/
						}
						});
						}
				           
    		  		  }
    					$('.errorMessage').html('');
    					
    				});
			  		 }else{
							if (docNo.length > 0) {
				  			$(".errorMessage").html(validate);
				  		    $('#releaseBtn').prop('disabled', true);
				  		    $(".accTitle").html('');
							}
				  		  }
				  	 });
     	          }else{
     	        	 $('#releaseBtn').prop('disabled', true);
     	        		$('.errorMessage').html('Insert accession no.');
     	          }
     	  		}
				  else{
					  var accNo =  $(this).val();
				  	  if(accNo.length > 0){
						  	  
						  	//$('#releaseBtn').prop('disabled', false);
						  	$('.errorMessage').html('');
						  	 $(".accTitle").html('');
						  	  //var getDocNo =  $("#getDocNo").attr("value");
						  	  $.get("Handler_Release",{CT03DOCNO:accNo},function(validate){
						  		  if(validate.trim()=="ok"){
						  			 $('#releaseBtn').prop('disabled', false);
						       var data = 'accNo='+ accNo;
						            $.ajax({
						               type: "GET",
						               url: "ReleaseForCI",
						               data: data,
						               success: function(msg){
						             	  //$("#ctrlTagsForm").show();
						             	   $(".accTitle").html(msg);
					
						               }
						  	    });
						  		  }else{
									if (accNo.length > 0) {
						  			$(".errorMessage").html(validate);
						  		    $('#releaseBtn').prop('disabled', true);
						  		    $(".accTitle").html('');
									}
						  		  }
						  	 });
						        }else{
						        	 $('#releaseBtn').prop('disabled', true);
						        	$('.errorMessage').html('Insert accession no.');
						        }
				  
				  }
				              
				               return false;
				           }
				           event.stopPropagation();
				       });
	   
	//enter to display invalid accession no / title (scan barcode or key in barcode)
/*	   $('#accMatNo').on('keypress', function(event) {
		  
		   var keycode = (event.keyCode ? event.keyCode : event.which);
	        if(keycode == '13'){
	  	  var accNo =  $(this).val();
	  
	  	  if(accNo.length > 0){
			  	   $('#releaseBtn').prop('disabled', true);
			  	$('#releaseBtn').prop('disabled', false);
			  	$('.errorMessage').html('');
			  	 $(".accTitle").html('');
			  	  //var getDocNo =  $("#getDocNo").attr("value");
			  	  $.get("Handler_Release",{CT03DOCNO:accNo},function(validate){
			  		  if(validate.trim()=="ok"){
			       var data = 'accNo='+ accNo;
			            $.ajax({
			               type: "GET",
			               url: "ReleaseForCI",
			               data: data,
			               success: function(msg){
			             	  //$("#ctrlTagsForm").show();
			             	   $(".accTitle").html(msg);
		
			               }
			  	    });
			  		  }else{
						if (accNo.length > 0) {
						
			  			$(".errorMessage").html(validate);
			  		    $('#releaseBtn').prop('disabled', true);
			  		    $(".accTitle").html('');
						}
			  		  }
			  	 });
			        }else{
			        	$('.errorMessage').html('Insert accession no.');
			        }
	        }
	   });*/
	   
	   
	 //Display list of accession no in modal
	   $('.accList tr.accValues').click(function() {
	  		  var accNo =  $(this).attr("value");
	
	  		  $('table tr.accValues').css('background','#ffffff');
	  	         $(this).css('background','grey');  
	  		  //var getDocNo =  $("#getDocNo").attr("value");
	            var data = 'accNo='+ accNo;
	                 $.ajax({
	                    type: "GET",
	                    url: "ReleaseForCI",
	                    data: data,
	                    success: function(msg){
	                  	  //$("#ctrlTagsForm").show();
	                  	   $("#accTitle").html(msg);

	                    }
	      	    });
	  	  });
	   
	   //Refresh modal when open the modal
	   $("#accModal").on("show.bs.modal", function(){
			 $(this).removeData();
		});
	   
	   //Release record
	   $('#releaseBtn').unbind().click(function() {
		  
			var docNo = $('#accMatNo').val();
			
			if(docNo.length > 0){
			var print = $('#print').is(':checked');
			$.get("ReleaseCirculation",{CT03DOCNO:docNo},function(status){
				var splitStatus = status.trim().split("?");
				if(splitStatus[0]=="error"){
		  			var code = "001";
           		var criteria = docNo;
           		var label = "@docNo";
		  			  $.get("Error_Message?GL79ERRCODE="+code+"" + 
	               				"&criteria=" + criteria + "&label="+label+"",function(msg){
				        		if(splitStatus[1]==undefined){
									 	   $('#releaseList').append("<tr><td>" + docNo + "</td>" + 
					   			            "<td>" + msg + "</td>" +
   					   			         "<td>" + splitStatus[1] + "</td>" + 
   					   			      "<td>" + splitStatus[2] + "</td>" + 
					   				  	"</tr>");
								}else{
									 	   $('#releaseList').append("<tr><td>" + docNo + "</td>" + 
					   			            "<td>" + msg + "</td>" + 
					   			         "<td>" + splitStatus[1] + "</td>" + 
					   			      "<td>" + splitStatus[2] + "</td>" + 
					   				  	"</tr>");
								}
				       
				                   //document.getElementById("testvalue").value =  document.getElementById("accMatNo").value;
									$("span").html("");
								  $('#ReleaseForm')[0].reset();
				                   $("#accMatNo").focus();
				                   $(".accTitle").html('');
								  
		              	   });
		  		  }else{
		  			var code = "002";
	           		var criteria = docNo;
	           		var label = "@docNo";
		  			  $.get("Error_Message?GL79ERRCODE="+code+"" + 
	               				"&criteria=" + criteria + "&label="+label+"",function(msg){
	
				        	   $('#releaseList').append("<tr><td>" + docNo + "</td>" + 
				   			            "<td>" + msg + "</td>" + 
				   			         "<td>" + splitStatus[1] + "</td>" + 
				   			      "<td>" + splitStatus[2] + "</td>" + 
				   				  	"</tr>");
						if (print){
							
							$.get("Handler_CIRESV",{CT03DOCNO:docNo, CT03MATNO:splitStatus[3],action:"check"}
									,function(status){
							
						if(status.trim()=="ok"){
						var accMatNo = docNo;
						var accTitle = splitStatus[1];
						var accCallNo =splitStatus[2];
						var matno = splitStatus[3];
						
						var menuID= "CT";
						var url = "GeneratePreviewDocument?accMatNo=" + accMatNo
									+ "&accTitl=" + accTitle
									+ "&accCallNo=" + accCallNo
									+ "&matno=" + matno
									+ "&print=" + print
									+ "&menuID=" + menuID;
		
						window.open(url);
						
						/*  $.get("Handler_CIRESV",{CT03DOCNO:docNo, CT03MATNO:splitStatus[3],action:"update"}
						  	,function(msg){
						  });
			*/
						}
						});
						}
				                   //document.getElementById("testvalue").value =  document.getElementById("accMatNo").value;
				                  $("span").html("");
								   $('#ReleaseForm')[0].reset();
				                   $("#accMatNo").focus(); 
								      
		              	   });
		  			
		  		  }
			});
			}else{
				$(".errorMessage").html("Please insert accession no.");
			}
			 
	   });
	   
	 //Click on the reserve checkbox (check if the reserve is checked)
	   $('#reserve').click(function(event){
	   	   if($("#reserve").prop("checked") == true){
	   		   document.getElementById("print").disabled = '';
	   	   }
	   	  else{
	   		$('#print').attr('checked', false); // Unchecks it
	   	   document.getElementById("print").disabled = 'disabled';
	   	  }
	   	});
	   
});

//Append textbox with accession & append new title 
function updateAccRcrd(){ 
	$('.errorMessage').html('');
    $('#releaseBtn').prop('disabled', false);
 	document.getElementById("accMatNo").value = document.getElementById("accMatNoValue").value;
 	var title = $("#accTitle").html();
 	$(".accTitle").html(title);
 	//document.getElementById("testvalue").value = document.getElementById("accMatNoValue").value;
 }
