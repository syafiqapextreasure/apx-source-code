/**
 * 
 */
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

				$.get(url,function(data_title){
		
					$('#search').collapse('hide');
			
					$("#display_title").html(data_title);
					$('#result_tags').collapse("show").height("auto");
					
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
	 $('#btn-cancel').click(function(){
		 history.go(0);
	 });
	 
	 //Update template name, status  
	 	$('#btn-update').click(function(){
	
	   		var url = "Handler_EditTpl?CT15SEQNO=" + $('#CT15SEQNO').val() +
	   				"&CT15TNAME=" + $('#CT15TNAME').val();
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
	  					showPopupMsg("Please fill up the necessary details.","warning");
	  				}
	  			}
	   		});
	   	});
	 	
		//Update indicators and subfields 
		$('#updateTplInfo').click(function(){
			var indiValue1 = $("#updateIndi1").val();
			var indiValue2 = $("#updateIndi2").val();

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
	   				
	   				
	   				swal(   'Successful!',   'Record for '+$('#tplName').val()+' added',   'success' )
	   				$('.swal2-confirm ').click(function(){
  					$('#updateModal1').modal('hide');
	  					
	  					var url = "ShowTplDetails?seqNo="+$('#seqNo').val();

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
			  var tag = document.getElementById("tag").value;
				var url = "Indicators_Handler?GL18TAG=" + tag;
				$.get(url,function(data){
				$("#indicator").html(data);
				});	
			}

	//Add new tag, indicators and subfields 
		 $('#addTplInfo').click(function(){
			  var tplID = $('#tplId').val();
			  var tplName = $('#tplName').val();
			  var status = $('#status').val();
			  var selected = [];
			  var selectedtxt = [];
			  var splittxts = [];
	           $('#subfields option:selected').each(function() {
	               selected.push([$(this).val()]);
	               selectedtxt.push([$(this).text()]);
	           });
	           var str="";
	           for (var i = 0; i < selected.length; i++) {
	        	   str+=selected[i];
	        	 }
	           var url = "Handler_AddTpl?CT15SEQNO=" + tplID + 
		           	"&CT15TNAME=" + tplName+ 
		  			"&CT15STAT=" + status;
   		$.ajax({
	   			url: url,
	   			success: function(result) {
	   				var action = clearSpace(result);
	  				if(action == "ok"){
	  				    $("table.tagTable > tbody > tr").each(function(Mindex, Mval) {
	  			           var url = "Handler_AddNewTplMaint?CTMSTR=" + $(Mval).find('td:eq(0)').text() + 
	  			           	"&CTTPLTAG=" + $(Mval).find('td:eq(1)').text()+ 
	  			  			"&CTTPLSUBF=" + $(Mval).find('td:eq(4)').text() +
	  			  			"&CTTPLINDI1=" + encodeURIComponent( $(Mval).find('td:eq(2)').text() )+
	  			  			"&CTTPLINDI2=" + encodeURIComponent( $(Mval).find('td:eq(3)').text() );

	  			         $.get(url,function(data_title){
	  						//$("#display_title").html(data_title);
	  					   });
	  			           });
	  				}else{
	  					messageBox("008",tplName, "@TplName");
	  				}

	  			}
	   		});
	         
	          
		 });
			
	
			//Call error message page 
			function messageBox(code, criteria, label){
				var url = "Error_Message?GL79ERRCODE="+code+"" + 
   				"&criteria=" + criteria + "&label="+label+"";
				$.ajax({
		   			url: url,
		   			success: function(result) {
		   			
		   				swal('Info!',result, 'warning' );
		  			}
		   		});
			}
			
				//Append to add more than one tag 
				$(".appendTag").click(function(){
					  var selected = [];
					  var selectedtxt = [];
					  var splittxts = [];
				     $('#subfields option:selected').each(function() {
			               selected.push([$(this).val()]);
			               selectedtxt.push([$(this).text()]);
			           });
			           var str="";
			           for (var i = 0; i < selected.length; i++) {
			        	   str+=selected[i];
			        	 }

			   		  $('#collapseOne').collapse("show");
			   		
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
					    cell2.innerHTML = $('#tag').val();
					    cell2.className = 'tag';
					    cell3.innerHTML = $('#indi1').val();
					    cell3.className = 'indi1';
					    cell4.innerHTML = $('#indi2').val();
					    cell4.className = 'indi2';
					    cell5.innerHTML = str;
					    cell5.className = 'subfields';
					    cell6.innerHTML = '<button onclick="deleteRow(this)"><span class="glyphicon glyphicon-remove" id="delPOIbutton"></span></button>';
					    
				});
			    $("#customFields").on('click','.remCF',function(){
			        $(this).parent().parent().remove();
			    });

			    //Delete the append tags 
			    function deleteRow(row)
			    {
			        var i=row.parentNode.parentNode.rowIndex;
			        document.getElementById('tagTable').deleteRow(i);
			    }

			    	//Reload the modal 
			    	$("#updateModal1").on("show.bs.modal", function(e) {
			    	    var link = $(e.relatedTarget);
			    	    $(this).find(".modal-content").load(link.attr("href"));
			    	});
			    
			    	$("#tplName").change(function(){
			    		var input = document.getElementById("tplName");
			    		if (input.val().length > 0) {
			    			$('#addTplInfo').prop('disabled', true);
			    		}
			        });
			    	
			    	$("#tag").change(function(){
			    		var e = document.getElementById("tag");
			    		var strUser = e.options[e.selectedIndex].value;
			    		
			    		 if(strUser=="0"){
			    			 $('#addTplInfo').prop('disabled', true);
			    			 $('.appendTag').prop('disabled', true);
			    		 }
			    		 else if ($("#tag option:selected").length) {
			    				$('#addTplInfo').prop('disabled', false);  
			                	$('.appendTag').prop('disabled', false);
					    }
			        });
			    	
			    	 $("#tplName").keyup(function(){
			    		  var txt = $('#tplName');  
			                if (txt.val() != null && txt.val() != '') {  
			                	$('#addTplInfo').prop('disabled', false);  
			                	$('.appendTag').prop('disabled', false);
			                } else {  
			                	$('#addTplInfo').prop('disabled', true);
			                	$('.appendTag').prop('disabled', true);
			                }  
				    		
				        });
			    	
			    	function deleteTplPlugin(tplID){
			    		var split = tplID.split(",");
			    		var tplID =split[0];
			    		var tplName = split[1];

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
			    					swal( 'Deleted!', tplName+ ' template has been deleted.','success');  
			    				      $.ajax({
			    				 			// Title
			    				 			url: deleteUrl,
			    				 			success: function(result) {
			    				 				var status = result.replace(/\s+/g, '');
			    				 				if(status == "ok"){
			    				 					//$("#div-result").html(" ");
			    				 					//$('#result').collapse("show");
			    				 					// location.reload();
			    				 					var url = "ShowTplDetails?seqNo="+tplName;

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
											      'Your imaginary file is safe :)',
											      'error'
											    );
											  }
											})
			    
			    	}
			    	