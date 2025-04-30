/**
 * 
 */

function getTypeOfSelection(){
	$(".newInventory").load("Load_EmptyTable");
	//$('#inventoryList').DataTable().ajax.reload();
	$('.search_criteria').load("Load_OriPanel");
	  if($("#chknow").prop("checked") == true){
		  //$('.cutter1').show();
		  $('.cutter2').show();
		  $('#cutter').hide();
		  //$("#checkInventory").prop("disabled",false);
		  $("#enquire").prop("disabled",true);	
		  $("#retrieveLink").attr("disabled",true);	
		  $(".form-control").prop("disabled",false);
		  $('.barcode').css('display','inline-block');
		  $("#checkInventory").prop("disabled",false);	
		  $(".flag").val("");
		  $("#enter").prop("disabled",false);	
	  }else if($("#enquiryInv").prop("checked") == true){
		  //$('.cutter1').hide();
		  $('.cutter2').show();
		  $("#checkInventory").prop("disabled",true);	
		  $(".enquire").prop("disabled",false);	
		  $("#retrieveLink").attr("disabled",true);
		  $(".form-control").prop("disabled",false);
		  $('.barcode').css('display','none');
		  $(".flag").val("");
	  }else if($("#LRecord").prop("checked") == true){
		  $("#checkInventory").prop("disabled",true);
		  $("#enquire").prop("disabled",true);	
		  $("#retrieveLink").attr("disabled",false);
		  $(".form-control").prop("disabled",true);
		  $('.barcode').css('display','inline-block');
		  $("#barcode").prop("disabled",true);	
		  $("#enter").prop("disabled",true);	
		  $(".flag").val("Load");
	  }
   }

/*function getShelfNo(CT05SRAW){
	var SL02LOCA = $('#GL05LOCA :selected').val();
	

	$.get("Load_ShelfNo",{CT05SRAW:CT05SRAW, SL02LOCA:SL02LOCA},function(shelfNo){
		$(".shelfNo").html(shelfNo);
		});
}*/
//Selection of cutter group to load cutters and box no
function getBoxNo1(SL02BOX){
var SL02LOCA = $('#GL05LOCA :selected').val();
var CT03SMD = $('#CT03SMD :selected').val();

//To load cutters or cutter group depending on the selection
if($("#cuttergroup").prop("checked") == true){
	$.get("NeuLocate/Inventory_Check",{GROUP:"G",SL02BOX:SL02BOX, SL02LOCA:SL02LOCA,actionID:"LoadCutters"},function(cutterList){
		$(".cutlist").html(cutterList);
	});
}else if($("#cutter").prop("checked") == true){
	$.get("NeuLocate/Inventory_Check",{GROUP:"C",SL02BOX:SL02BOX, SL02LOCA:SL02LOCA,actionID:"LoadCutters"},function(cutterList){
		$(".cutters").html(cutterList);
	});
}

	$.get("NeuLocate/Inventory_Check",{SL02BOX:SL02BOX, action:"CutterGroup", SL02LOCA:SL02LOCA, CT03SMD:CT03SMD,actionID:"LoadBoxNo"},function(cutterList){
		$(".box2").html(cutterList);
		});
}

//Load box no based on cutters selection
function getBoxNo2(SL02CUTTER){
	var SL02LOCA = $('#GL05LOCA :selected').val();
	var CT03SMD = $('#CT03SMD :selected').val();
		$.get("NeuLocate/Inventory_Check",{SL02CUTTER:SL02CUTTER, action:"Cutter", SL02LOCA:SL02LOCA,CT03SMD:CT03SMD,actionID:"LoadBoxNo"},function(cutterList){
			$(".box2").html(cutterList);
			});
	}

//Load SMD based on loaction class='smd'
function getLocaValue(GL05LOCA){
	$.get("NeuLocate/Inventory_Check",{GL05LOCA:GL05LOCA,actionID:"Load_SMD"},function(smd){
		 $(".smd").html(smd);
});
	
}

function getSMDValue(smd){
	var loca = $("#GL05LOCA").val();
	$.get("NeuLocate/Inventory_Check",{smd:smd, loca:loca,actionID:"Load_ICAT"},function(icat){
		 $(".icat").html(icat);
});
	
}

//Load cutter list or cutter group value based on radio button selection
function setValue(GL03LOCA, GL03CUTTER, GL03FLAG, boxno, SL03SMD){

		 if(GL03FLAG=="G"){
			 $.get("NeuLocate/Inventory_Check",{GL05LOCA:GL03LOCA, action:"CutterGroup",actionID:"Handler_LoadCutter"},function(cutterList){
				 $(".cutterGroup").html(cutterList);
				 $("#SL02BOX").val(GL03CUTTER);
				 $("#cuttergroup").prop("checked", true);
				 var SL02LOCA = $('#GL05LOCA :selected').val();
				 var SL02BOX = $('#SL02BOX :selected').val();
					$.get("NeuLocate/Inventory_Check",{SL02BOX:SL02BOX, action:"CutterGroup", SL02LOCA:SL02LOCA, CT03SMD:SL03SMD,actionID:"LoadBoxNo"},function(cutterList){
						$(".box2").html(""); 
						$(".box1").html(cutterList);
						 $("#boxNo").val(boxno);
					});
			});
				
				}else{
					 $.get("NeuLocate/Inventory_Check",{GL05LOCA:GL05LOCA, action:"Cutter",actionID:"Handler_LoadCutter"},function(cutterList){
						 $(".cutter").html(cutterList);
						 $("#SL02CUTTER").val(GL03CUTTER);
							$("#cutter").prop("checked", true);
						});
				}
}

//Load location desc based on location code
function getLocaDesc(){
	document.getElementById("GL05DESC").value = document.getElementById("GL05LOCA").value;
}

//Load location code based on location desc
function getLocaCode(){
	document.getElementById("GL05LOCA").value = document.getElementById("GL05DESC").value;
}
	
$(document).ready(function () {
	$('.multiselect').multiselect({
		 includeSelectAllOption: true,
		 maxHeight: 200
	});
	$("[title]").tooltip();
/*	if(("#barcode").val()!=null || ("#barcode").val().length==1){
		 $('#enter').prop('disabled', false);
	}*/
	var count = 1;
	$('#enter').unbind().click(function(event){
       
        	 $('#checkInventory').prop('disabled', false);
        	 var barcode = $("#barcode").val();
        	
        	 if(barcode.length <= 0){
        		 swal("Warning","Barcode value is empty", "warning" );
        		 $("#checkInventory").prop("disabled", true);
        	 }else{
        	 if ( barcodeList.indexOf(barcode) > -1 ){
        		 swal("Warning","Duplicate barcode", "warning" );
        	 }else{
        	 var newRowContent = "<tr>" +
        	 						"<td class='ListBarcode'>"+barcode+"</td>" +
        	 						"<td></td><td></td><td></td><td></td><td></td><td></td>" +
        	 						"</tr>";
        	 $(".counter").html(count++ + " enteries");
        	 if(barcodeList.length > 0){
        		 $("#inventoryList tbody").append(newRowContent);
        	 }else{
        	 $("#inventoryList tbody").html(newRowContent);
        	 }
           	 barcodeList.push(barcode);
        	 }
           	 $("#barcode").val("");
           	document.getElementById("barcode").focus();
        	 }
      
});
	var barcodeList = [];
	
	/* Append current table with new input barcode and check 
	 * if there is duplicate barcode in the list*/
	$('#barcode').unbind().keypress(function(event){
	        var keycode = (event.keyCode ? event.keyCode : event.which);
	      
	        if(keycode == '13'){
	        	 $('#checkInventory').prop('disabled', false);
	        	 var barcode = $(this).val();
	        	 if(barcode.length <= 0){
	        		 swal("Warning","Barcode value is empty", "warning" );
	        		 $("#checkInventory").prop("disabled", true);
	        	 }else{ 
		        	 if ( barcodeList.indexOf(barcode) > -1 ){
		        		 swal("Warning","Duplicate barcode", "warning" );
		        	 }else{
		        	 var newRowContent = "<tr>" +
		        	 						"<td class='ListBarcode'>"+barcode+"</td>" +
		        	 						"<td></td><td></td><td></td><td></td><td></td><td></td>" +
		        	 						"</tr>";
		        	 $(".counter").html(count++ + " enteries");
		        	 if(barcodeList.length > 0){
		        		 $("#inventoryList tbody").append(newRowContent);
		        	 }else{
		        	 $("#inventoryList tbody").html(newRowContent);
		        	 }
		           	 barcodeList.push(barcode);
		        	 }
		           	 $("#barcode").val("");
		        }
	        }
	      
	});
	
	//Refresh button to reload panel 2 and table
	$('input[name="optradio"]').change(function(){
	/*
		$(".newInventory").load("Load_EmptyTable");
		   if (this.value == 'enquiryInv') {
			   $('.search_criteria').load("Load_OriPanel");
			   $(".enquire").prop("disabled",false);	
			   alert("Transfer Thai Gayo1");
	        }
	        else if (this.value == 'LRecord') {
	            alert("Transfer Thai Gayo");
	        }*/
		/*27/02/2017
		 *$("#locations").html("");
		$("#boxs").html("");
		$("#cutters").html("");
		$("#groups").html("");
		$('#inventoryForm')[0].reset();*/
		/*$.get("Load_OriPanel",function(loadForm){
			$(".search_criteria").html("");
			$('.search_criteria').html(loadForm);
			
			if(checkedValue=="enquiryInv"){
				$(".enquire").prop("disabled",false);	
			}else if(checkedValue=="LRecord"){
				
			}
		});*/
		
	});
	
/*	//Check for file extension
	function alertFilename(){
		alert("sd");
	    var fileInput =  
            document.getElementById('thefile'); 
          
        var filePath = fileInput.value; 
      
        // Allowing file type 
        var allowedExtensions =  
                /(\.txt)$/i; 
          
        if (!allowedExtensions.exec(filePath)) { 
            alert('Invalid file type'); 
            fileInput.value = ''; 
            return false; 
        }  
	}*/
	
	//Upload file to validate and import to table
	$("#import").click(function(event){
		
		   var form = $('.current_form')[0];
		   var data = new FormData(form);
		   
		   $.ajax({
               type: "POST",
               enctype: 'multipart/form-data',
               url: "UploadFile",
               data: data,
               processData: false,
               contentType: false,
               cache: false,
               success: function (data) {
            	  
            	   if(data=="invalid_length"){
            		   swal('','Invalid length of accession number','');
            	   }else{
            		   $("#inventoryList tbody").html(data);
            		   $('#checkInventory').prop('disabled', false);
            	   }
      /*         	var value = data.split("&");

               	if(value[1].trim()=="done"){
               	
	                	$.get("Handler_ParamipsTable",{bufferno:value[0].toString(), total:value[2]},function(list){
	                		//alert("sd3");
	                					var $bar = $('#myBar1');
							            $bar.stop();
							       		clearInterval(timer);
							       		var $bar = $('#myBar1');
							    	   	Horloge(maxWidth);
							    	   	timer = setInterval('Horloge('+maxWidth+')', 1);
							       		$('#myBar1').animate({
							            width: "100%"
							          }, 2000, function() {
							              clearInterval(timer);
							          });
							        $(".tableList").html(list);

						});
               	}	*/
               },
               error: function (e) {

                   $("#result").text(e.responseText);
                   console.log("ERROR : ", e);
                   $("#btnSubmit").prop("disabled", false);

               }
           });
	/*	 $.get("UploadFile",{GL05LOCA:GL05LOCA, action:"Cutter"},function(list){
			 
		 });*/
	});
	//Click on check now button to check inventory
	$("#checkInventory").click(function(event){

			var barcodeList = [];
			var barcodeList1 = [];
			var barcodeAudit = [];
			 var status = [];
			 var invalid = [];
			var location = document.getElementById("GL05LOCA");
			var locationCode = location.options[location.selectedIndex].value;
		
			var smd = document.getElementById("CT03SMD");
			var smdCode = smd.options[smd.selectedIndex].value;

			if(locationCode=="0"||smdCode=="0"){
				swal("Warning","Please fill in mandatory field", "warning" );
			}else{
				$("#CT03SMD").prop("disabled", true);
				$("#CT03ICAT").prop("disabled", true);
				$("#GL05LOCA").prop("disabled", true);
				$("#GL05DESC").prop("disabled", true);
				$("#qrValue").prop("disabled", true);
			if(smdCode=="COM"){
				
				var checkedValue = $("input[name='selection']:checked").val();
				var cutter = "";
				var cutterno = "";
				
				
				if(checkedValue=="cuttergroup"){
					 cutter = document.getElementById("SL02BOX");
					 cutterno = cutter.options[cutter.selectedIndex].value;
				}else if(checkedValue=="cutter"){
					 cutter = document.getElementById("SL02CUTTER");
					 cutterno = cutter.options[cutter.selectedIndex].value;
				}else if(checkedValue=="qrcode"){
					 cutter = document.getElementById("SL02BOX");
					 cutterno = cutter.options[cutter.selectedIndex].value;
				}
				
				if(cutterno=="0"||cutterno==""){
					swal("Warning","Please fill in mandatory field", "warning" );
				}else{
					var box = document.getElementById("boxNo");
					var boxno = box.options[box.selectedIndex].value;
					
					if(boxno=="0"){
						swal("Warning","Please fill in mandatory field", "warning" );
					}else{
					$("#SL02BOX").prop("disabled", true);
					$("#SL02CUTTER").prop("disabled", true);
					$("#boxNo").prop("disabled", true);	
				 $('.inventoryList').find('tbody tr').each(function () {
					 var Barcode = $(this).find("td.ListBarcode").text();
					 barcodeList.push(Barcode.trim());
					 barcodeAudit.push(Barcode.trim());
					 barcodeList1.push("'"+Barcode.trim()+"'");
				 });
				 
				loader(".newInventory");
			
				 $.get("NeuLocate/Inventory_Check",{CT03SMD:smdCode, flag:checkedValue,barcode:barcodeList.toString(),barcodeArray:barcodeList1.toString(), 
					 							GL10BOXNO:boxno,SL02BOX:cutterno, GL03LOCA:locationCode,actionID:"Handler_InventoryCheck"},function(inventoryList){
	
					 
					 $(".newInventory").html(inventoryList);
						swal(
								'',
								'Inventory successfully checked',
								'success'
							);
					 barcodeList = [];
					 $('#newinventoryList').find('tbody tr').each(function () {
						 var statusValue = $(this).find("td.status").text();
						 var Barcode = $(this).find("td.ListBarcode").text();
						 if(statusValue!="Invalid"){
							 barcodeList.push(Barcode.trim());
						 }else if(statusValue=="Invalid"){
							 invalid.push(Barcode.trim());
						 }
					 });
					 
					 if($(".flag").val().length>0){	
							$.get("NeuLocate/Inventory_Check",{flag:checkedValue,barcode:barcodeAudit.toString(),barcodeArray:barcodeList1.toString(), 
								GL10BOXNO:boxno,SL02BOX:cutterno, GL03LOCA:locationCode, CT03SMD:smdCode,actionID:"Handler_InventoryAudit"},function(inventoryList){
									
						 });
					 }else{	
					if(invalid.length > 0){

						swal({   
							//title: 'Do you wish to delete this record?',
							text: 'There are ' + invalid.length + " invalid barcode/s scanned. Do you wish to rescan the barcode?",
							type: 'warning',   showCancelButton: true,
							confirmButtonColor: '#3085d6', 
							cancelButtonColor: '#d33',
							confirmButtonText: 'Yes',
							cancelButtonText: 'No',
							confirmButtonClass: 'confirm-class',
							cancelButtonClass: 'cancel-class',
							closeOnConfirm: false,
							closeOnCancel: false 
							}).then(function() {
							    swal(
						         	      '',
									      'Scanned barcode cleared',
									      'warning'
									    );
								  $(".counter").html("");
								    counter = 1;
								    $(".newInventory").load("Load_EmptyTable");
								
								},function(dismiss) {
								  if (dismiss == 'cancel') {
										$.get("NeuLocate/Inventory_Check",{flag:checkedValue,barcode:barcodeAudit.toString(),barcodeArray:barcodeList1.toString(), 
											GL10BOXNO:boxno,SL02BOX:cutterno, GL03LOCA:locationCode, CT03SMD:smdCode,actionID:"Handler_InventoryAudit"},function(inventoryList){
												 swal(
														'Successfully Saved!',
														'The record has been successfully saved.',
														'success'
													);
											});	     
									 }
								})
					}else{
						$.get("NeuLocate/Inventory_Check",{flag:checkedValue,barcode:barcodeAudit.toString(),barcodeArray:barcodeList1.toString(), 
								GL10BOXNO:boxno,SL02BOX:cutterno, GL03LOCA:locationCode, CT03SMD:smdCode,actionID:"Handler_InventoryAudit"},function(inventoryList){
									
						 });
					}
					 }
				 });
	
				 //$('#inventoryForm')[0].reset();
					}
				}
			}else{
				var box = document.getElementById("ShlfNo");
				var boxno = box.options[box.selectedIndex].value;
				$("#ShlfNo").prop("disabled", true);
				if(boxno=="0"||locationCode=="0"||smdCode=="0"){
					swal("Warning","Please fill in mandatory field", "warning" );
				}else{
	
				 $('.inventoryList').find('tbody tr').each(function () {
					 var Barcode = $(this).find("td.ListBarcode").text();
					 barcodeList.push(Barcode.trim());
					 barcodeAudit.push(Barcode.trim());
					 barcodeList1.push("'"+Barcode.trim()+"'");
				 });
		
				loader(".newInventory");

				 $.get("NeuLocate/Inventory_Check",{CT03SMD:smdCode, barcode:barcodeList.toString(),barcodeArray:barcodeList1.toString(), 
					 							GL10BOXNO:boxno, GL03LOCA:locationCode,actionID:"Handler_InventoryAudit"},function(inventoryList){
	
					 alert("3");
					 $(".newInventory").html(inventoryList);
					 barcodeList = [];
					 $('#newinventoryList').find('tbody tr').each(function () {
						 var statusValue = $(this).find("td.status").text();
						 var Barcode = $(this).find("td.ListBarcode").text();
						 if(statusValue!="Invalid"){
							 barcodeList.push(Barcode.trim());
						 }else if(statusValue=="Invalid"){
								 invalid.push(Barcode.trim());
							 }
					 });
					 
					 if($(".flag").val().length>0){	
							$.get("NeuLocate/Inventory_Check",{flag:"shelf",barcode:barcodeAudit.toString(),barcodeArray:barcodeList1.toString(), 
								GL10BOXNO:boxno,SL02BOX:cutterno, GL03LOCA:locationCode, CT03SMD:smdCode,actionID:"Handler_InventoryAudit"},function(inventoryList){
									
						 });
					 }else{	
					 if(invalid.length > 0){

							swal({   
								//title: 'Do you wish to delete this record?',
								text: 'There are ' + invalid.length + " invalid barcode/s scanned. Do you wish to rescan the barcode?",
								type: 'warning',   showCancelButton: true,
								confirmButtonColor: '#3085d6', 
								cancelButtonColor: '#d33',
								confirmButtonText: 'Yes',
								cancelButtonText: 'No',
								confirmButtonClass: 'confirm-class',
								cancelButtonClass: 'cancel-class',
								closeOnConfirm: false,
								closeOnCancel: false 
								}).then(function() {
									swal(
							         	      '',
										      'Scanned barcode cleared',
										      'warning'
										    );
									  $(".counter").html("");
									    counter = 1;
									    $(".newInventory").load("Load_EmptyTable");
							
									
									},function(dismiss) {
									  if (dismiss == 'cancel') {
										  $.get("NeuLocate/Inventory_Check",{flag:"shelf",barcode:barcodeAudit.toString(),barcodeArray:barcodeList1.toString(), 
												GL10BOXNO:boxno,SL02BOX:"000", GL03LOCA:locationCode, CT03SMD:smdCode,actionID:"Handler_InventoryAudit"},function(inventoryList){
													
													swal(
															'Successfully Saved!',
															'The record has been successfully saved.',
															'success'
														);
												});	     
										 }
									})
						}else{
							$.get("NeuLocate/Inventory_Check",{flag:"shelf",barcode:barcodeAudit.toString(),barcodeArray:barcodeList1.toString(), 
									GL10BOXNO:boxno,SL02BOX:"000", GL03LOCA:locationCode, CT03SMD:smdCode,actionID:"Handler_InventoryAudit"},function(inventoryList){
										
							 });		
						}
					 }
				 });
				 
				 //$('#inventoryForm')[0].reset();
				}
			}
			}
		}); 
		
	//Refresh button to reload panel 2 and table
	$('#refresh').click(function(event){
		$(".newInventory").load("Load_EmptyTable");
		$(".counter").html("");
		var checkbox = $('input[name="optradio"]:checked');
		 if(checkbox.val() == "chknow"){
			 $("#enter").prop("disabled",false);
		 }
		/*27/02/2017
		 *$("#locations").html("");
		$("#boxs").html("");
		$("#cutters").html("");
		$("#groups").html("");
		$('#inventoryForm')[0].reset();*/
	$.get("NeuLocate/Inventory_Check",{actionID:"Load_OriPanel"},function(loadForm){
			$(".search_criteria").html("");
			$('.search_criteria').html(loadForm);
		});
		
	});
	
	
	

	
	//To enquire the existing list
	$('#enquire').unbind().click(function(event){
		
		var barcodeList = [];
	
		var location = document.getElementById("GL05LOCA");
		var locationCode = location.options[location.selectedIndex].value;
		var smd = document.getElementById("CT03SMD");
		var smdCode = smd.options[smd.selectedIndex].value;
		var icat = document.getElementById("CT03ICAT");
		var icatCode = icat.options[icat.selectedIndex].value;
		var generalSubj = document.getElementById("cutterGroup");
		var generalSubjCode = generalSubj.options[generalSubj.selectedIndex].value;
		var generalSubj1 = document.getElementById("cutterGroup1");
		var generalSubjCode1 = generalSubj1.options[generalSubj1.selectedIndex].value;
		var cutter = "";
		var cutterno = "";
		
	if(smdCode=="COM"){
		var checkedValue = $("input[name='selection']:checked").val();

		if(locationCode=="0"||smdCode=="0"){
			swal("Warning","Please fill in mandatory field", "warning" );
		}else{
		if(checkedValue=="cuttergroup"){
			 cutter = document.getElementById("SL02BOX");
			 cutterno = cutter.options[cutter.selectedIndex].value;
		}else if(checkedValue=="cutter"){
			 cutter = document.getElementById("SL02CUTTER");
			 cutterno = cutter.options[cutter.selectedIndex].value;
		}else if(checkedValue=="qrcode"){
			 cutter = document.getElementById("SL02BOX");
			 cutterno = cutter.options[cutter.selectedIndex].value;
		}
			
		if(cutterno=="0"||cutterno==''){
			swal("Warning","Please fill in mandatory field", "warning" );
		}else{
			var box = document.getElementById("boxNo");
			var boxno = box.options[box.selectedIndex].value;
		if(boxno=="0"){
			swal("Warning","Please fill in mandatory field", "warning" );
		}else{
			loader(".newInventory");
		/*	 $.get("Handler_Enquire",{type:"Box",SL02SMD:smdCode,flag:checkedValue, GL10BOXNO:boxno,
				 					 SL02BOX:cutterno, GL03LOCA:locationCode},function(inventoryList){
	
				 $(".newInventory").html(inventoryList);
			 });*/
		}
		}
		}
	}else{
		if(locationCode=="0"||smdCode=="0"){

			swal("Warning","Please fill in mandatory field", "warning" );
		}else{
alert("1");
			var shelf = document.getElementById("ShlfNo");
			var shelfNo = shelf.options[shelf.selectedIndex].value;
		if(shelfNo=="0"){
			swal("Warning","Please fill in mandatory field", "warning" );
		}else{
			//loader(".newInventory");

			 $.get("NeuLocate/Inventory_Check",{type:"Shelf",SL02SMD:smdCode,GL10BOXNO:shelfNo,
				 					GL03LOCA:locationCode, CT03ICAT:icatCode, generalSubj:generalSubjCode,				 					
				 					generalSubj1:generalSubjCode1,actionID:"Handler_Enquire"},function(inventoryList){

				 $(".newInventory").html(inventoryList);
			 });
		}

		}
		
	}
	});
	
	$('#SL03LOCADROP').change(function(event){
		if($(this).val()!="0"){
			$("input[name=retrieve][value='SL02LOCA']").prop("checked",true);
		}else{
			$("input[name=retrieve][value='SL02LOCA']").prop("checked",false);
		}
	});
	
	$('#SL03SMDDROP').change(function(event){
		if($(this).val()!="0"){
			$("input[name=retrieve][value='SL03SMD']").prop("checked",true);
		}
	});
	
	$('#SL03CUTTERDROP').change(function(event){
		if($(this).val()!="0"){
			$("input[name=retrieve][value='SL02CUTTER']").prop("checked",true);
		}
	});
	
	$('#SL03BOXNODROP').change(function(event){
		if($(this).val()!="0"){
			$("input[name=retrieve][value='SL02BOXNO']").prop("checked",true);
		}
	});
	
	$('#SL03LOCADROP').change(function(event){
		$("input[name=retrieve][value='SL02LOCA']").prop("checked",true);
	});
	
	//Cutter group selection to load cutter list
	$('#SL02BOX').change(function(event){
		$("input[name=selection][value='cuttergroup']").prop("checked",true);
		//$("#SL02CUTTER").prop("disabled", true);
		
		var checkedValue = $("input[type='radio']:checked").val();
		/*var box = document.getElementById("GL10BOXNO");
		var boxno = box.options[box.selectedIndex].value;*/
		var location = document.getElementById("GL05LOCA");
		var locationCode = location.options[location.selectedIndex].value;
		var cutter = document.getElementById("SL02BOX");
		var cutterno = cutter.options[cutter.selectedIndex].value;
		$('#SL02CUTTER option[value="'+cutterno+'"]').attr("selected",true);
	/*	if(checkedValue=="cuttergroup"){
				 cutter = document.getElementById("SL02BOX");
				 cutterno = cutter.options[cutter.selectedIndex].value;
		
		}else if(checkedValue=="cutter"){
			
			cutter = document.getElementById("SL02CUTTER");
			cutterno = cutter.options[cutter.selectedIndex].value;
			
		}*/
		 $.get("NeuLocate/Inventory_Check",{GROUP:"G",SL02BOX:cutterno, SL02LOCA:locationCode,actionID:"LoadCutters"},function(cutterList){
			 $("input[name=selection][value='cutter']").attr("disabled", true);	
			 $(".cutlist").html(cutterList);
			});
		
		if(cutterno!="0"&&boxno!="0"&&locationCode!="0"){
			 $.get("NeuLocate/Inventory_Check",{flag:checkedValue, GL10BOXNO:boxno,
				 						  SL02BOX:cutterno, GL03LOCA:locationCode,actionID:"Handler_CheckingBox"},function(message){
				 $(".message").html("<span color='red'>"+message+"</span>")
			 });
			 
		}
	});
	
	$("input[type='radio']").change(function() {

	    if($(this).val()=="cutter") {
	    	$("#SL02CUTTER").prop("disabled", false);
	    	$('#SL02BOX').prop('selectedIndex',0);
	    	$("#SL02BOX").prop("disabled", true);
	    }else if($(this).val()=="cuttergroup") {
	    	$("#SL02BOX").prop("disabled", false);
	    	$('#SL02CUTTER').prop('selectedIndex',0);
	    	$("#SL02CUTTER").prop("disabled", true);
	    }
	});
	
	//Cutters selection to load value in cutter group 
	$('#SL02CUTTER').change(function(event){

		$("input[name=selection][value='cutter']").prop("checked",true);
		$("#SL02BOX").prop("disabled", true);
		
		var checkedValue = $("input[type='radio']:checked").val();
		/*var box = document.getElementById("GL10BOXNO");
		var boxno = box.options[box.selectedIndex].value;*/
		var location = document.getElementById("GL05LOCA");
		var locationCode = location.options[location.selectedIndex].value;
		var cutter = document.getElementById("SL02CUTTER");
		var cutterno = cutter.options[cutter.selectedIndex].value;
		/*if(checkedValue=="cuttergroup"){
				 cutter = document.getElementById("SL02BOX");
				 cutterno = cutter.options[cutter.selectedIndex].value;
		
		}else if(checkedValue=="cutter"){
			
			cutter = document.getElementById("SL02CUTTER");
			cutterno = cutter.options[cutter.selectedIndex].value;
			
		}
		*/
		 $.get("NeuLocate/Inventory_Check",{GROUP:"C",SL02CUTTER:cutterno, SL02LOCA:locationCode,actionID:"LoadCutters"},function(cutterList){
			 	$("input[name=selection][value='cuttergrp']").attr("disabled", true);
				$("input[name=selection][value='cuttergroup']").prop("checked",true);
				$(".cutterGrpList").html(cutterList);
				var cut = document.getElementById("SL02BOX");
				var cutCode = cut.options[cut.selectedIndex].value;
				$.get("NeuLocate/Inventory_Check",{GROUP:"G",SL02BOX:cutCode, SL02LOCA:locationCode},function(cutterList){
					$(".cutlist").html(cutterList);
				});
			
			});
		
		if(cutterno!="0"&&boxno!="0"&&locationCode!="0"){
			 $.get("NeuLocate/Inventory_Check",{flag:checkedValue, GL10BOXNO:boxno,
				 						  SL02BOX:cutterno, GL03LOCA:locationCode,actionID:"Handler_CheckingBox"},function(message){
				 $(".message").html("<span color='red'>"+message+"</span>")
			 });
		}
	});
	
	
	//Enable and disable radio to scan or load from file
	$("input[name='type']").change(function(){
		if($(this).val()=="scanBarcode"){
			// $('a[id="z3950"]').attr('disabled','disabled');
			$('input[id="barcode"]').removeAttr('disabled');
			$('input[type="file"]').attr('disabled',true);
			$('input[id="enter"]').removeAttr('disabled');
		}else if($(this).val()=="loadAccession"){
			$('input[id="barcode"]').attr('disabled','disabled');
			$('input[id="enter"]').attr('disabled','disabled');
			 $('input[type="file"]').removeAttr('disabled');
			//$('input[type="file"]').attr('disabled',true);
			 //$('a[id="z3950"]').removeAttr('disabled');
		}
	});
	
	//To load cutter group and cutters based on smd selection
	$('#CT03ICAT').unbind().change(function(event){
		var GL05LOCA = $("#GL05LOCA").val();
		var CT03SMD = $("#CT03SMD").val();
		var CT03ICAT = $(this).val();
		$('#CT03SMD').prop('disabled', true);
		/*document.getElementById("CT03SMD").style.cursor = "wait";
		$("#CT03SMD").css( 'pointer-events', 'none' );*/
		//document.getElementById("CT03SMD").style.pointer-events = "wait";

/*	if(CT03SMD=="COM"){
	$.get("Handler_LoadCutter",{GL05LOCA:GL05LOCA,CT03SMD:CT03SMD, action:"CutterGroup"},function(cutterList){
				 $(".cutterGroup").html(cutterList);
				});
			 $.get("Handler_LoadCutter",{GL05LOCA:GL05LOCA, CT03SMD:CT03SMD,action:"Cutter"},function(cutterList){
				 $(".cutters").html(cutterList);
				 return false;
				});
			 $('#CT03SMD').prop('disabled', false);

		}else{*/
			
			$.get("NeuLocate/Inventory_Check",{CT03SMD:CT03SMD, SL02LOCA:GL05LOCA, CT03ICAT:CT03ICAT,actionID:"Load_GeneralSubj"},function(shelfNo){
					/* $(".cutters").empty();
					 $(".cutlist").empty();
					 $(".box2").empty();*/
					$(".cutterGroup").html(shelfNo);
					 return false;
				});
			$('#CT03ICAT').prop('disabled', false);
			/*$.get("Handler_LoadCallNo",{GL05LOCA:GL05LOCA,CT03SMD:CT03SMD, action:"CallNo"},function(cutterList){
				 $(".cutters").empty();
				 $(".cutterGroup").html(cutterList);
				 return false;
				});
			 $('#CT03SMD').prop('disabled', false);*/
		
		//}
	
	});
	
	/*//Load shelf number based on location, smd, icat, gs
	$('#cutterGroup1').unbind().change(function(event){
		var GL05LOCA = $("#GL05LOCA").val();
		var CT03SMD = $("#CT03SMD").val();
		var CT03ICAT = $("#CT03ICAT").val();
		var cutter1 = $(this).val();
		var cutter = $("#cutterGroup").val();
		$('#CT03SMD').prop('disabled', true);

			
			$.get("NeuLocate/Inventory_Check",{CT03SMD:CT03SMD, SL02LOCA:GL05LOCA, CT03ICAT:CT03ICAT, 
				cutter:cutter,cutter1:cutter1,actionID:"Load_ShelfNo"},function(shelfNo){
				$(".shelf").html(shelfNo);
					 return false;
				});
			$('#CT03ICAT').prop('disabled', false);

	});
	*/
	$('#cutterGroup').unbind().change(function(event){
		var GL05LOCA = $("#GL05LOCA").val();
		var CT03SMD = $("#CT03SMD").val();
		var CT03ICAT = $("#CT03ICAT").val();
		var cutter = $(this).val();
		var cutter1 = $("#cutterGroup1").val();
		$('#CT03SMD').prop('disabled', true);

			
			$.get("NeuLocate/Inventory_Check",{CT03SMD:CT03SMD, SL02LOCA:GL05LOCA, CT03ICAT:CT03ICAT,
				cutter:cutter,cutter1:cutter1,actionID:"Load_ShelfNo"},function(shelfNo){
				$(".shelf").html(shelfNo);
					 return false;
				});
			$('#CT03ICAT').prop('disabled', false);

	});
	
	
	
	$('#retrieve_btn').unbind().click(function(event){
		var checkbox = $('input[name="optradio"]:checked');
		var checkboxArray = [];
		var SL02LOCA = "";
		var SL03SMD = "";
		var SL02CUTTER = "";
		var SL02BOXNO = "";
		var checkedValues = $("[name^='retrieve']:checked").map(function(){
			if($(this).val()=="SL02LOCA"){
				checkboxArray.push($(this).val());
				SL02LOCA = $("#SL03LOCADROP").val();
			}
			if($(this).val()=="SL02CUTTER"){
				checkboxArray.push($(this).val());
				SL02CUTTER=$("#SL03CUTTERDROP").val();
			}
			if($(this).val()=="SL02BOXNO"){
				checkboxArray.push($(this).val());
				SL02BOXNO=$("#SL03BOXNODROP").val();
			}
			
			if($(this).val()=="SL03SMD"){
				checkboxArray.push($(this).val());
				SL03SMD=$("#SL03SMDDROP").val();
			}
			
	    }).get();


		$.get("NeuLocate/Inventory_Check",{checkboxArray:checkboxArray.toString(),
										SL02LOCA:SL02LOCA,SL02CUTTER:SL02CUTTER,SL02BOXNO:SL02BOXNO, SL03SMD:SL03SMD,actionID:"Result_RecordRetrieve"},function(inventoryList){
					$('#searchcriteria').collapse('hide');
					$('#result_title1').collapse('show'); 
			/* 		$("#search_title1").collapse("hide");
					$("#result_title1").collapse("show"); */
					$("#display_title").html(inventoryList);
			});

		
	});
	$("#recordRetrieve").on("hidden.bs.modal", function(){
		 $(this).removeData();
	});
	
	$("#qrValue").keypress(function(event){
		 $("#qrcode").prop("checked", true); 
		 var keycode = (event.keyCode ? event.keyCode : event.which);
       var qrcode = $(this).val();
      
       if(keycode == '13'){
    	  if($("#qrcode").prop("checked") == true){

    		 var subString = qrcode.split('=')[1];
    		 var locationCode = subString.substring(0,4);
    		 var CT03SMD = subString.substring(4,7);
    		 
    		 //Load location
    		document.getElementById("GL05LOCA").value = locationCode;
    		document.getElementById("GL05DESC").value = document.getElementById("GL05LOCA").value;
    	if(CT03SMD=="COM"){
    		//Load SMD according to location 
    		$.get("NeuLocate/Inventory_Check",{GL05LOCA:locationCode,actionID:"Load_SMD"},function(smd){
    			 $(".smd").html(smd);
    			document.getElementById("CT03SMD").value = CT03SMD;
    		/*	$.get("Handler_LoadCutter",{GL05LOCA:locationCode, CT03SMD:CT03SMD,action:"Cutter"},function(cutterList){
   				 $(".cutters").html(cutterList);

   				});*/
    			//Load cutter group
    			$.get("NeuLocate/Inventory_Check",{GL05LOCA:locationCode,CT03SMD:CT03SMD, action:"CutterGroup",actionID:"Handler_LoadCutter"},function(cutterList){
    	   			 $(".cutterGroup").html(cutterList);
    	   			$.get("NeuLocate/Inventory_Check",{GL05LOCA:locationCode, CT03SMD:CT03SMD,action:"Cutter",actionID:"Handler_LoadCutter"},function(cutterList){
	    				 $(".cutters").html(cutterList);
    	   				var cuttergroup = subString.substring(7,10);
    	    		 	var boxNo = subString.substring(10,13);
    	     			document.getElementById("SL02BOX").value = cuttergroup;
    	     			document.getElementById("SL02CUTTER").value = cuttergroup;
    	     			$("input[name=selection][value='cuttergroup']").prop("checked",true);
    	     			
    	     				//Load Cutters
	     	     			$.get("NeuLocate/Inventory_Check",{GROUP:"G",SL02BOX:cuttergroup, SL02LOCA:locationCode,actionID:"LoadCutters"},function(cutterList){
	     	   					$(".cutlist").html(cutterList);
    	   			
		     	     			//Load cutters
		     	     			$.get("NeuLocate/Inventory_Check",{SL02BOX:cuttergroup, action:"CutterGroup", SL02LOCA:locationCode, CT03SMD:CT03SMD,actionID:"LoadBoxNo"},function(cutterList){
			     	   				//$(".box2").html(""); 
			     	   				$(".box2").html(cutterList);
			     	   				 $("#boxNo").val(boxNo);
			     	   				$("#GL05LOCA").prop("disabled", true);
			     	   				$("#GL05DESC").prop("disabled", true);
			     	   				$("#CT03SMD").prop("disabled", true);
			     	   				$("#SL02BOX").prop("disabled", true);
			     	   				$("#boxNo").prop("disabled", true);
			     	   				$("#SL02CUTTER").prop("disabled", true);
		     	   				}); 
	     	     			});
    	   			});
    			});
    	});
				
    	  }else{alert("we");
    			$.get("NeuLocate/Inventory_Check",{GL05LOCA:locationCode,actionID:"Load_SMD"},function(smd){
    				$(".smd").html(smd);
       			document.getElementById("CT03SMD").value = CT03SMD;
       		/*	$.get("Handler_LoadCutter",{GL05LOCA:locationCode, CT03SMD:CT03SMD,action:"Cutter"},function(cutterList){
      				 $(".cutters").html(cutterList);

      				});*/
       			//Load cutter group
       			$.get("NeuLocate/Inventory_Check",{CT03SMD:CT03SMD, SL02LOCA:locationCode,actionID:"Load_ShelfNo"},function(shelfNo){
					 $(".cutters").empty();
					 $(".cutlist").empty();
					 $(".box2").empty();
					$(".cutterGroup").html(shelfNo);
					var cuttergroup = subString.substring(7,10);
   	    		 	var boxNo = subString.substring(10,13);
   	     			document.getElementById("ShlfNo").value = boxNo;
   	     			$("#GL05LOCA").prop("disabled", true);
	   				$("#GL05DESC").prop("disabled", true);
	   				$("#CT03SMD").prop("disabled", true);
	   				$("#ShlfNo").prop("disabled", true);
	   				//return false;
				});
    			});
   
   				
    		}
    	  }
    	 return false;
		}
       event.stopPropagation();
	});
	
	//Click data in modal -- Result_RecordRetrieve.jsp
	$('.clickable').click(function() {
		var loca =  $(this).find(".loca").text();
		var flag = $(this).find(".flag").text();
		var smd = $(this).find(".smd").text();
		var cutter = $(this).find(".cutters").text();
		var boxno = $(this).find(".boxno").text();
		
		$.get("NeuLocate/Inventory_Check",{loca:loca,flag:flag,cutter:cutter,boxno:boxno, smd:smd,actionID:"Handler_RetrieveRecord"},function(inventoryList){
			//$("#display_title").html(inventoryList);
			 $(".newInventory").html(inventoryList);
			 var counter = $('.counters').val();
			 $(".counter").html(counter + " enteries");
			 $("#GL05LOCA").val(loca);
			 $("#GL05DESC").val(loca);
			 
			 $.get("NeuLocate/Inventory_Check",{GL05LOCA:loca,actionID:"Load_SMD"},function(smdOptions){
				 $(".smd").html(smdOptions);
				 $("#CT03SMD").val(smd);
				 $("#CT03SMD").prop("disabled", true);
			 
			if(smd=="COM"){
				$.get("NeuLocate/Inventory_Check",{GL05LOCA:loca,CT03SMD:smd, action:"CutterGroup",actionID:"Handler_LoadCutter"},function(cutterList){
   	   			 $(".cutterGroup").html(cutterList);
   	   			 $("#SL02BOX").val(cutter);
   	   			
   	   			$.get("NeuLocate/Inventory_Check",{GL05LOCA:loca, CT03SMD:smd,action:"Cutter",actionID:"Handler_LoadCutter"},function(cutterList){
	    			$(".cutters").html(cutterList);
	    			 $("#SL02CUTTER").val(cutter);
   	     			$("input[name=selection][value='cuttergroup']").prop("checked",true);
   	     			
   	     				//Load Cutters
	     	     			$.get("NeuLocate/Inventory_Check",{GROUP:"G",SL02BOX:cutter, SL02LOCA:loca,actionID:"LoadCutters"},function(cutterList){
	     	   					$(".cutlist").html(cutterList);
   	   			
		     	     			//Load cutters
		     	     			$.get("NeuLocate/Inventory_Check",{SL02BOX:cutter, action:"CutterGroup", SL02LOCA:loca, CT03SMD:smd,actionID:"LoadBoxNo"},function(cutterList){
			     	   				//$(".box2").html(""); 
			     	   				$(".box2").html(cutterList);
			     	   			    $("#boxNo").val(boxno);
			     	   			  
			     	   				$("#SL02BOX").prop("disabled", true);
			     	   				$("#boxNo").prop("disabled", true);
			     	   				$("#SL02CUTTER").prop("disabled", true);
		     	   				}); 
	     	     			});
   	   			});
   			});
				
					}else{
			
		       			//Load cutter group
		       			$.get("NeuLocate/Inventory_Check",{CT03SMD:smd, SL02LOCA:loca,actionID:"Load_ShelfNo"},function(shelfNo){
							 $(".cutters").empty();
							$(".cutterGroup").html(shelfNo);
		   	     			document.getElementById("ShlfNo").value = boxno;
		   	     			$("#ShlfNo").prop("disabled", true);
		    			});
		   
					}


			 $("#checkInventory").prop("disabled",false);
			 $("#chknow").prop("checked",true);
		/*	 $("#SL02BOX").val(cutter);
			 if(flag=="G"){
				 $("#SL02BOX").val(cutter);
			 }else{
				 $("#SL02CUTTER").val(cutter);
			 }*/
			});
		
		$("#recordRetrieve").modal("hide");
		});
	});
	
	/* OnFocus barcode textbox to display values from panel above
	 * 
	 * $("#barcode").focus(function(){
		 $(".form-control").prop("disabled",true);
		var location = document.getElementById("GL05DESC");
		var locationCode = location.options[location.selectedIndex].text;
		var box = document.getElementById("boxNo");
		var boxno = box.options[box.selectedIndex].value;
		var checkedValue = $("input[name='selection']:checked").val();
		
		if(checkedValue=="cuttergroup"){
			 cutter = document.getElementById("SL02BOX");
			 cutterno = cutter.options[cutter.selectedIndex].value;
			    $("#groups").html("<div class='col-sm-5'><label> Cutter Group :</label></div><div class='col-sm-7'> " + cutterno + "</div>");
			    $("#cutters").html("<div class='col-sm-5'><label> Cutter : </label></div><div class='col-sm-7'>" + $(".cutter").text()) + "</div>";
		}else if(checkedValue=="cutter"){
			 cutter = document.getElementById("SL02CUTTER");
			 cutterno = cutter.options[cutter.selectedIndex].value;
			    $("#cutters").html("<div class='col-sm-5'><label> Cutter : </label></div><div class='col-sm-7'> " + cutterno + "</div>");
		}else if(checkedValue=="qrcode"){
			 cutter = document.getElementById("SL02BOX");
			 cutterno = cutter.options[cutter.selectedIndex].value;
			    $("#groups").html("<div class='col-sm-5'><label> Cutter Group :</label></div><div class='col-sm-7'> " + cutterno + "</div>");
			    $("#cutters").html("<div class='col-sm-5'><label> Cutter : </label></div><div class='col-sm-7'>" + $(".cuttergrp").text() + "</div>");
		}
		
	    $("#locations").html("<div class='col-sm-5'><label> Location : </label></div><div class='col-sm-7'>" + locationCode + "</div>");
	    $("#boxs").html("<div class='col-sm-5'><label> Box : </label></div><div class='col-sm-7'>" + boxno + "</div>");
	});
	*/
});

/*$('#barcode').unbind().focus(function(event){
	alert("dsdsds");
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode == '13'){
        	var barcode = $(this).val();
        	alert(barcode);
        }
});*/