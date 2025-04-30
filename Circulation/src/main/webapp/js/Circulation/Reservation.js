/**
 * 
 */

//Call error message page


function messageBox(code, criteria, label){
      var url = "Error_Page?GL79ERRCODE="+code+"" +
      "&criteria=" + criteria + "&label="+label+"";
      $.ajax({
            url: url,
            success: function(result) {
                  swal(result);
            }
      });
}


$(document).ready(function() {
	
	var accLenght = "GetGlobalValidation";
					 $.get(accLenght,function(data){
							console.log(data + " apani ?");
							$("#CT03DOCNO,#accession").attr("maxlength", data);
							
							 $(window).bind('load', function() {
								   window.stop()
								});
					  });
	
	$("[title]").tooltip();
	 $("#CT03DOCNO, #CT03MATNO").keyup(function(){
	       if(!this.value){
	    	   location.reload();
	       }
	    });
	 
	 $('input[name=optradio]').change(function(){
		   var action = $( 'input[name=optradio]:checked' ).val();

		   if(action == "accession"){
			   $("#accession").prop('disabled',false);
			   $("#CI02NDATE").prop('disabled',true);
		   }
		   else if(action == "days"){
			   $("#accession").prop('disabled',true);
			   $("#CI02NDATE").prop('disabled',false);
		   }

		   event.preventDefault();
		 });

	 var activeTab = localStorage.getItem('activeTab');
	    if(activeTab){
	        $('.nav-tabs a[href="' + activeTab + '"]').tab('show');
	    }
	    
	
	$("#accession").on('keydown keypress',function(e) {

		var code = e.keyCode || e.which;
		
		   var action = $('input[name=optradio]:checked').val();
		   var accession;
		   var days;
		   accession = this.value;
		
					  if (code == '9'||code == '13') {
						  $.get("List_ResvScrutiny",{action:"accession",accession:accession},function(list){
							  $("#resv_scrutiny tbody").html(list);
							  
						  });
							event.preventDefault();
					  }
			
		});
	
	$("#CI02NDATE").on('keydown keypress',function(e) {
		var code = e.keyCode || e.which;

		   var days=this.value;
		
				  if (code == '9'||code == '13') {
					  $.get("List_ResvScrutiny",{action:"days",days:days},function(list){
						  $("#resv_scrutiny tbody").html(list);
						  
					  });
						e.preventDefault();
				  }
		});
	
	$(".dropdown-menu li a").click(function(){
		  $(this).parents(".input-group-btn").find('.btn').html('<span class="text-value" data-span="'+$(this).data('value')+'">' + $(this).text() + '</span> <span class="caret"></span>');
			});
	
	  $("#refresh").click(function(){
		location.reload();
	});
	  
	  $("#refresh").click(function(){
			location.reload();
		});
		  
	  
	$('#btn_submit').click(function(){
	
		var criteria = $('#patronSearchInput').val();
		var searchType =$(".text-value").data('span');
		var cate_id = $('#cate_type').val();
		
		$.get("SearchPatron",{criteria:criteria,search_type:searchType,cate_id:cate_id},function(data_vendor){
		/*	$("#result_vendor").collapse('show');
			$("#search_vendor").collapse('hide');*/
			$("#display_vendor").html(data_vendor);
		   });
	})
	
	$('#btn_sort').click(function(){
		$('#resvTable tr').each(function() {
		    var id = $(this).find(".id").html();   
		    var status = $(this).find(".status").html();    
		    var accession = $(this).find(".accession").html();  
		    
		    var row = $(this).closest('tr');
		    var prevHf=$(this).parents('tr').prev().find('.id').html();
		   /* if(status)
		    var url = "Handler_SortPriority?action=ctrlno&CT03MATNO="+CT03MATNO;
		    
		    $.get(url,function(data)
			{
		    	alert(data);
			});*/
		 });
	})


	function sortPriority(index) {
	
    try{
        var line = document.getElementById(index);
    } catch(e) {
        return; 
    }
    var tag = line.parentNode; 

    var subfields = tag.getElementsByTagName('tr');
    var subfieldsLength = subfields.length;

    if(subfieldsLength<=1) return; 

    for(var i=0;i<subfieldsLength;i++){
        if(subfields[i].getAttribute('id') == index){ 
            if(i==1){
                tag.appendChild(subfields[1]);
                return;
            } else {
                var lineAbove = subfields[i-1];
                tag.insertBefore(line,lineAbove);
                return;
            }
        }
    }
}

	
	$("#CT03MATNO").keydown(function(e)
	{
		var keycode = (e.keyCode ? e.keyCode : e.which);

		 if (keycode == '13'||keycode == '9') {
			 
			 var CT03MATNO = $(this).val();
			 var CI03DOCNO = $(".selectCT03MATNO").find(':selected').val();
			
				var url = "Handler_ResvAcc?action=ctrlno&CT03MATNO="+CT03MATNO;
				  
				$.get(url,function(data)
				{
					var message = data.split(",");
				
					if(message[0].trim()==="049"){
						messageBox("049",message[1],"@status");
						
						}else if(data.trim()==="070"){
							messageBox("070","","");
						
						}else if(data.trim()==="088"){
							messageBox("088","","");
						
						}/*else if(data.trim()==="088"){
							//messageBox("088","","");
							$("#error").html("There is no available copy of this title to be reserved")
						
						}*/else if(data.trim()==="020"){
							
							messageBox("020","","");
							}else{
								$("#itemInfo").html(data);
								var url = "List_Resv?CI03MATNO="+CT03MATNO + "&CI03DOCNO="+CI03DOCNO;
								$.get(url,function(data)
										{
											$(".resv_list").html(data);
											var url = "LoadSelectBox?action=getLoca&CT03MATNO="+CT03MATNO;
											  
											$.get(url,function(data)
											{
												$(".changeValue").html(data);
											});
										});
							}
				});
		 }
	});
	
	$("#retrieveRecord").click(function(e)
			{
		 var action = $( 'input[name=optradio]:checked' ).val();

		 if(action=="days"){

			   var days=$("#CI02NDATE").val();
						  $.get("List_ResvScrutiny",{action:"days",days:days},function(list){
							  $("#resv_scrutiny tbody").html(list);
							  
						  });
		 }else{
			   var accession = $("#accession").val();
			
					  $.get("List_ResvScrutiny",{action:"accession",accession:accession},function(list){
						 
						  $("#resv_scrutiny tbody").html(list);
						  
					  });
				 }
			});
	
	$("#GL05LOCA").change(function(){
		
		var GL05LOCA = $("#GL05LOCA").val();	
		var desc =  $(this).find(':selected').attr('data-text');
		var CT03MATNO = $("#CT03MATNO").val();
	
		var url = "LoadSelectBox?action=getAcc&CT03MATNO="+CT03MATNO + "&CT03LOCA=" + GL05LOCA;
		  
		$.get(url,function(data)
		{
			$("#accession").html(data);
			$(".itemloca").html(desc);
			$(".loca").html(GL05LOCA);
		});
	});
/*	
	$("#GL05LOCA").change(function(){
		
		var GL05LOCA = $(this).val();
		$("#GL05DESC").val(GL05LOCA);
		var desc = $("GL05DESC")
	});
	*/
	$("#CT03DOCNO").keydown(function(e)
	   {
		   var action = $( 'input[name=optradio]:checked' ).val();

		   if(action == "charge"){
			   reload(action);
		   }
		 var keycode = (e.keyCode ? e.keyCode : e.which);
		    if (keycode == '13'||keycode == '9') {
			var CT03DOCNO = $(this).val();
			var CT03MATNO = "0";
			var url = "Handler_ResvAcc?action=accession&CT03DOCNO="+CT03DOCNO;
		
			$.get(url,function(data)
					{
				var message = data.split(",");
				
						if(message[0].trim()=="049"){
							messageBox("049",message[1].trim(),"@status");
						}
					else{
					if(data.trim()==="020"){
						messageBox("020","","");
						}else{
							$("#itemInfo").html(data);
							var url = "List_Resv?CI03DOCNO="+CT03DOCNO + "&CI03MATNO=" +CT03MATNO ;
							$.get(url,function(data)
									{
										$(".resv_list").html(data);
									});
						}
					}
			});
		    }
			  
		});
});
	
//Delete template name 
function deleteScrutiny(patrId){

	 var CI03DOCNO = $("#accession").val();

	 var message = patrId.split(",");

	swal({   
		title: 'Remove '+ message[0]+' from reservation list?',
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
	
		/*	var url = "GeneratePreviewDocument?docno="+message[2]+"&patron="+message[0]+"&matno="+message[2]+"&action=cancellation";

			window.open(url,"", "");*/
			if ($('.cancellationLetter').is(":checked"))
			{
				var url = "GeneratePreviewDocument?docno="+message[3]+"&patron="+message[0]+"&action=cancellation";

					window.open(url,"", "");
					
			}	

			if(!CI03DOCNO){
				CI03DOCNO = message[3];
			}
			
			 var deleteUrl = "Handler_DeleteScrutiny?CI02PATR="+message[0] + "&CI03DOCNO="
			 				+message[3] + "&CI03MATNO="+message[2] + "&CI03NSTAT=" + message[1];
			 		$.get(deleteUrl,function(data)
						{
			 			if(data.trim() == "ok"){
		 					  swal(
								'Deleted!', message[0]+ ' has been deleted.','success'
								);
		 						if ($('.cancellationLetter').is(":checked"))
		 						{
		 							
		 							var url = "Print_CancellationLetter?CI03PATR=" + GL14PATR + "&CI03DOCNO="+CI03DOCNO+ "";
		 					
		 								window.open(url,"", "");
		 								
		 						}	
		 						$("#resv_scrutiny tbody").prepend("");
		 						  var action = $('input[name=optradio]:checked').val();
		 				
		 						if(action.trim()=="days"){
		 							var days = $("#CI02NDATE").val();
		 							 $.get("List_ResvScrutiny",{action:"days",days:days},function(list){
		 								  $("#resv_scrutiny tbody").html(list);
		 								  
		 							  });
		 						}else{
		 						 $.get("List_ResvScrutiny",{accession:CI03DOCNO,action:action},function(list){
		 	
		 							  $("#resv_scrutiny tbody").html(list);
		 							  $("#CI03DOCNO").val();
		 							  
		 						  });
		 						}
			 			

		 				}else{
		 				   swal(
								'Not Deleted',
								'Patron is still available for reservation',
								'error'
						  );
		 				}
						});
		      /*$.ajax({
		 			url: deleteUrl,
		 			success: function(result) {
		 			alert(result.trim());
		 				if(result.trim() == "ok"){
		 					  swal(
								'Deleted!', message[0]+ ' has been deleted.','success'
								);
		 						if ($('.cancellationLetter').is(":checked"))
		 						{
		 							
		 							var url = "Print_CancellationLetter?CI03PATR=" + GL14PATR + "&CI03DOCNO="+CI03DOCNO+ "";
		 					
		 								window.open(url,"", "");
		 								
		 						}	
		 						alert("errre");
		 						$("#resv_scrutiny tbody").prepend("");
		 						alert("errre11");
		 						  var action = $('input[name=optradio]:checked').val();
		 						if(action=="days"){
		 							var days = $("#CI02NDATE").val();
		 							 $.get("List_ResvScrutiny",{action:"days",days:days},function(list){
		 								  $("#resv_scrutiny tbody").html(list);
		 								  
		 							  });
		 						}else{
		 							alert("errre1177");
		 						 $.get("List_ResvScrutiny",{accession:CI03DOCNO,action:action},function(list){
		 	
		 							  $("#resv_scrutiny tbody").html(list);
		 							  
		 						  });
		 						}
			 			

		 				}else{
		 				   swal(
								'Not Deleted',
								'Patron is still available for reservation',
								'error'
						  );
		 				}
		 			}
		 		});*/
					},function(dismiss) {
						  if (dismiss === 'cancel') {
						    swal(
						      'Cancelled'
						    );
						  }
						})
}

//Delete template name 
function deleteReserv(patrId){

	 var CT03MATNO = $("#CT03MATNO").val();
	 var CI03DOCNO = "";
	 if(CT03MATNO.length != 0 && CT03MATNO != null){
		 CI03DOCNO = $(".selectCT03MATNO").find(':selected').val();
	 }else{
		 CT03MATNO = "0";
		 CI03DOCNO = $("#CT03DOCNO").val();
	 }
	
	 var message = patrId.split(",");

	swal({   
		title: 'Remove '+ message[0]+' from reservation list?',
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
			var row = $(".btn-dull").closest("tr");    // Find the row
		    var date = row.find("td:eq(2)").text(); // Find the text
			if ($('.cancellationLetter').is(":checked"))
				{
				 
					///var url = "GeneratePreviewDocument?docno="+CI03DOCNO+"&patron="+message[0]+"&matno="+CT03MATNO+"&rsvdate="+date+"&action=cancellation";
					//13112019
					var url = "GeneratePreview?docno="+CI03DOCNO+"&patron="+message[0]+"&matno="+CT03MATNO+"&rsvdate="+date+"&action=cancellation";
				
						window.open(url,"", "");
						
				}	
				
			 var deleteUrl = "Handler_DeleteResv?CI02PATR="+message[0] + "&CI03DOCNO="
			 				+CI03DOCNO + "&CI03MATNO=" + CT03MATNO + "&CI03NSTAT=" + message[1];
				// Put the object into storage
				var reserveDocno={CI03DOCNO:CI03DOCNO,acknowlege:$(".ackLetter:checked").val()};
				
				localStorage.setItem('reserveDocno', JSON.stringify(reserveDocno));
		      $.ajax({
		 			url: deleteUrl,
		 			success: function(result) {
		 			
		 				if(result.trim() == "ok"){
		 					  swal(
								'Deleted!', message[0]+ ' has been deleted.','success'
								);
		 					
		 					 var details =JSON.parse(localStorage.getItem('reserveDocno'));
		 					
		 						 var url = "List_Resv?CI03DOCNO="+CI03DOCNO + "&CI03MATNO=" + CT03MATNO;
		 						
									$.get(url,function(data)
											{
												$(".resv_list").html(data);

												if (details.acknowlege=="ackLetter"&&$('#resvTable tr:eq(1) td:eq(6)').text().trim()=="Awaiting for collection")
												{
													
													var url = "GeneratePreviewDocument?docno="+ $('#resvTable tr:eq(1) td:eq(7)').text()+"&action=reserved";
													
													window.open(url,"", "");

												}

											
											});
			 			

		 				}else{
		 				   swal(
								'Not Deleted',
								'Patron is still available for reservation',
								'error'
						  );
		 				}
		 			}
		 		});
					},function(dismiss) {
						  if (dismiss === 'cancel') {
						    swal(
						      'Cancelled'
						    );
						  }
						})
}

