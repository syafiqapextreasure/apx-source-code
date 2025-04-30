/**
 * 
 */
  function reprint(){
		 var action = $('input[name=actiontype]:checked' ).val();
		  // if(action=="charge"){
			   var GL14PATR= document.getElementById("GL14PATR").value;
			  // var accession = $(accessions).data("acc");
			   var rowInfo = new Array();
			   //rowInfo.push("'" + accession+ "'");
			   $("input[name='reprint']:checked").each(function() {
					  rowInfo.push("'" + $(this).val()+ "'");
		          });
			    var url = "GeneratePreview?action=Charging&GL14PATR=" + GL14PATR + "&CT03DOCNO="+rowInfo.toString();
			   	window.open(url,"", "width=283,height=500,toolbar=0");
			    window.onload = function() { window.print(); }
		  // }
	   }
	   
  function checkAllPStatus(){
	var action = $('input[name=checkAllPStatus]');  
	    if(action.prop('checked')) {
	        // Iterate each checkbox and set them to checked
	        $('input[name="reprint"]').each(function() {
	            this.checked = true;
	        });
	    } else {
	       // Iterate each checkbox and set them to checked
	        $('input[name="reprint"]').each(function() {
	            this.checked = false;
	        });
	    }
	   }
	   
	   function UrlExists(url, cb){
			    jQuery.ajax({
			        url:      url,
			        dataType: 'text',
			        type:     'GET',
			        async: false,
			        complete:  function(xhr){
			            if(typeof cb === 'function')
			               cb.apply(this, [xhr.status]);
			        }
			    });
			}

	  function printSlip(){
		 var action = $('input[name=actiontype]:checked' ).val()
		   if(action=="charge"){
			   var GL14PATR= document.getElementById("GL14PATR").value;
			   var rowInfo = new Array();
			   $("#table-charging").find("tbody tr").each(function () {
			        var ID = $(this).children(':first').text();
			        if($(this).find('td:eq(6)').text()=="Circulated"){
				            rowInfo.push("'" + $(this).find('td:eq(1)').text()+ "'");
			        }
			    });
			    //alert(rowInfo);
			   var url = "GeneratePreview?action=Charging&GL14PATR=" + GL14PATR + "&CT03DOCNO="+rowInfo.toString();
			   var myWindow = window.open(url,"", "width=283,height=500,toolbar=0");

			   myWindow.print();
			   
				
		   }else if(action=="discharge"){
			   //var GL14PATR= document.getElementById("hiddenPatrId").value
			   var array = new Array();
			   $("#table-charging").find("tbody tr").each(function () {
			        if($(this).find('td:eq(6)').text()=="Discharged"){
					array.push({gl14patr:$(this).find('td:eq(11)').text().trim(),
									array: $(this).find('td:eq(1)').text()+ "|" + 
			        				$(this).find('td:eq(4)').text()+ "|" +
			        				$(this).find('td:eq(5)').text()+ "|" +
			        				$(this).find('td:eq(2)').text() + "|" + 
			        				$(this).find('td:eq(3)').text() + "|" + 
			        				$(this).find('td:eq(7)').text() + "|" + 
			        				$(this).find('td:eq(8)').text()});
			        };
			    });
			    
			    var cars = array
			    	var result = new Array();
				    result = cars.reduce(function (r, a) {
				        r[a.gl14patr] = r[a.gl14patr] || [];
				        r[a.gl14patr].push(a);
				        return r;
				    }, Object.create(null));
				
				
				for(let y = 0; y < Object.keys(result).length; y++){
					var isyrArr = [];
					for(let z = 0; z < Object.values(result)[y].length; z++){
						var isyraftestlagi = Object.values(result)[y][z].array.toString();
						
						isyrArr.push(isyraftestlagi);
					}
						//var url = "GeneratePreview?GL14PATR=" + array[y].gl14patr + "&CT03DOCNO="+ array[y].array.toString() +"&action=Discharging";
						var url2 = "GeneratePreview?GL14PATR=" + Object.keys(result)[y] + "&CT03DOCNO="+ isyrArr +"&action=Discharging";
						
						UrlExists(url2, function(status){
							    if(status === 200){
								var myWindow = window.open(url2,"_blank", "width=1000,height=700,toolbar=0");
								//mywindow.print is for print to pop up after the window have been open.
						    	myWindow.print();
						    	//$('#table-charging tbody').empty();
						    	//onafterPrint is for detecting if the print pop up is press or not.
						    	/*function isyraf2Function() {
									var dalamOnafterPrint;
									 myWindow.onafterprint = function(){
								  		dalamOnafterPrint = true;
									 };
								 console.log("Hello Isyraf "+dalamOnafterPrint);
								  return dalamOnafterPrint
								};*/
								reload("discharge");
							    }else{
								swal('Info!',"Connection Error, Please print again.", '' );
							    }
							});
				}
				
				
			   /*var url = "GeneratePreview?GL14PATR=" + GL14PATR + "&CT03DOCNO="+array.toString()+"&action=Discharging";
			   var myWindow = window.open(url,"", "width=283,height=500,toolbar=0");
			   myWindow.print();*/

		   }else if(action=="renewal"){
			   var patrIDNew = document.getElementById("hiddenPatrId").value;
			   var renewDate = $(this).find("td:eq(7)");
			   var array = new Array();
			   
			   $("#table-charging tbody tr").each(function(i) {
			        if($(this).find('td:eq(6)').text()=="Renewed"){
					renewDate = $(this).find("td:eq(7)").text();
//						 array.push($(this).find("td:eq(1)").text().trim()+ "|" + 
//							 		$(this).find("td:eq(4)").text().trim()+ "|" +
//									$(this).find("td:eq(7)").text() + "|" + 
//									$(this).find("td:eq(9)").text() + "|" +  
//									$(this).find("td:eq(10)").text().trim());
									
						array.push({gl14patr:$(this).find('td:eq(11)').text().trim(),
									array: $(this).find('td:eq(1)').text()+ "|" + 
			        				$(this).find('td:eq(4)').text()+ "|" + 
			        				$(this).find('td:eq(7)').text() + "|" + 
			        				$(this).find("td:eq(9)").text() + "|" +
			        				$(this).find('td:eq(10)').text()});
			        }
			    });
			    
			    var rArray = array
			    	var result = new Array();
				    result = rArray.reduce(function (r, a) {
				        r[a.gl14patr] = r[a.gl14patr] || [];
				        r[a.gl14patr].push(a);
				        return r;
				    }, Object.create(null));
				    
				for(let y = 0; y < Object.keys(result).length; y++){
					var isyrArr = [];
					for(let z = 0; z < Object.values(result)[y].length; z++){
						var isyraftestlagi = Object.values(result)[y][z].array.toString();
						
						isyrArr.push(isyraftestlagi);
					}
						//var url = "GeneratePreview?GL14PATR=" + array[y].gl14patr + "&CT03DOCNO="+ array[y].array.toString() +"&action=Discharging";
						var url2 = "GeneratePreview?GL14PATR=" + Object.keys(result)[y] + "&CT03DOCNO="+ isyrArr +"&action=Renewal";
						
						UrlExists(url2, function(status){
							    if(status === 200){
								var myWindow = window.open(url2,"_blank", "width=1000,height=700,toolbar=0");
								//mywindow.print is for print to pop up after the window have been open.
						    	myWindow.print();
								reload("renewal");
							    }else{
								swal('Info!',"Connection Error, Please print again.", '' );
							    }
							});
				}
		   }else if(action=="batchrenewal"){
				var patron = $("#GL14PATR").val();
				var array = new Array();
				
				$("#table-charging tbody tr").each(function(i) {
					if ($(this).find(".isyraf").is(':checked')&& $(this).find("td:eq(12)").html().trim()=="") {
						var renewDate = $(this).find("td:eq(8)");
						var fine = $(this).find("td:eq(11)");
						var lateby = $(this).find("td:eq(10)");
						
						array.push($(this).find("td:eq(2)").text().trim()+ "|" + 
					 	$(this).find("td:eq(5)").text().trim()+ "|" +
						renewDate.text() + "|" + 
						lateby.text() + "|" + fine.text());
					}
				});
				
				var url = "GeneratePreview?GL14PATR=" + patron + "&CT03DOCNO="+array.toString()+"&action=Renewal";
				window.open(url,"", "width=283,height=500,toolbar=0").print();
		   }else{
			
			   var patrIDNew = document.getElementById("hiddenPatrId").value;
			   var array = new Array();
			   var renewDate = $(this).find("td:eq(7)");
			   var renewItems = [];
			   
			    $("#table-charging").find("tbody tr").each(function () {
					   if ($(this).find('td:eq(12)').text() == "") {
						   renewItems.push("NOERROR")
					   }else{
						   renewItems.push("ERROR")
					   }
				    });

			  $("#table-charging tbody tr").each(function(i) {
			        if($(this).find('td:eq(6)').text()=="Renewed"){
					renewDate = $(this).find("td:eq(7)").text();

						 array.push($(this).find("td:eq(1)").text().trim()+ "|" + 
							 		$(this).find("td:eq(4)").text().trim()+ "|" +
									$(this).find("td:eq(7)").text() + "|" + 
									$(this).find("td:eq(9)").text() + "|" +  
									$(this).find("td:eq(10)").text().trim());
			        }
			    });

//				 array.push($(this).find("td:eq(2)").text().trim()+ "|" + 
//					 $(this).find("td:eq(5)").text().trim()+ "|" +
//						renewDate.text() + "|" + 
//						lateby.text() + "|" + fine.text());

			   		var url = "GeneratePreview?GL14PATR=" + patrIDNew + "&CT03DOCNO="+array.toString().replace(/\|$/, '')+"&action=Renewal";
			   		var myWindow = window.open(url,"", "width=283,height=500,toolbar=0");
					myWindow.print();
		   }
	   }

   function dischargingCont(){
			var url2 = "Handler_PatronDetails?GL14PATR=" + $(".dischargeaction").text() + "&action=Discharging" ;
			$.get(url2,function(data)
					{ 
				var details = data.trim().split("/n");
				$("#onloan").text(details[5]);
				$("#itemoverdue").text(details[6]);
				$("#itemreserved").text(details[7]);
				$("#fines").text(details[8]);
				//$("#totalfines").text(details[9]);
				$(".statusid").html('<strong>Discharged</strong>');
					var status = $('.statusid');
					status.removeClass('statusid')
					var el = $('.status');
					el.addClass('newlist');
					el.removeClass('status');
					  $(".newlist").css('background', 'LightGreen');
					document.getElementById("CT03DOCNO").value='';
					var total = $(".total").text();
					total = parseInt(total) + 1;
					$(".total").text(total);
					$(".title").text("");
					
					var Cdate = $(".Cdate").text();
					var Ctime = $(".Ctime").text();
					var Ddate = $(".Ddate").text();
					var Dtime = $(".Dtime").text();
					var Lateby = $(".lateby").text();
					var fines = $(".fines").text();
					var CT03DOCNO =  $(".disCT03DOCNO").text();
					
					if ($('.printSlip').is(":checked"))
					{   var array = new Array();
					        	array.push(CT03DOCNO+ "|" + Ddate + "|" +
					        				Dtime + "|" + Cdate + "|" + Ctime + "|" + 
					        				Lateby + "|" + fines);
					    
					        var url = "GeneratePreview?GL14PATR=" + GL14PATR + "&CT03DOCNO="+array.toString()+"&action=Discharging";
							window.open(url,"", "width=283,height=500,toolbar=0").print();
					}	
					// Put the object into storage
					var dischargePrint={GL14PATR:GL14PATR,CT03DOCNO:CT03DOCNO,Cdate:Cdate,
							Ctime:Ctime,Ddate:Ddate,Dtime:Dtime,Lateby:Lateby,fines:fines};
					localStorage.setItem('dischargePrint', JSON.stringify(dischargePrint));

					$('td.Cdate').removeClass('Cdate');
					$('td.Ctime').removeClass('Ctime');
					$('td.Ddate').removeClass('Ddate');
					$('td.Dtime').removeClass('Dtime');
					$('.lateby').removeClass('lateby');
					$('.fines').removeClass('fines');
					$('td.disCT03DOCNO').removeClass('disCT03DOCNO');
					$("#CT03DOCNO").focus();  
			});	
	}

   function discharging(){
			  var GL14PATR= $('#hiddenPatrId').val();
			  var GL14PATR2= $('#disGL14PATR').text();
			  var CT03DOCNO =  $(".disCT03DOCNO").text();
			  var chargeDate = $('#table-charging').find("td").eq(2).text();
			  var chargeTime = $('#table-charging').find("td").eq(3).text();
			  var dischargeTime =  $('#table-charging tr:last').find("td").eq(10).text();
			  
			  if(!CT03DOCNO ){
					  messageBoxCir("020","","");
				}else{
				if(GL14PATR.trim() === "" || GL14PATR == null){
					GL14PATR = GL14PATR2;
				}
				var url = "Handler_Discharging?CT03DOCNO=" + CT03DOCNO +"&GL14PATR=" +GL14PATR+"&dischargeTime=" +dischargeTime;
				$.get(url,function(datas){
					var message = datas.split(",");
					if((message[0]).trim()=="064"){
						dischargingCont();
						printResvNotification(message[1]);
					}else if((message[0]).trim()=="resvFine"){
						dischargingCont();
						printResvNotificationResv(message[1]);
					}else if(datas.trim()=="074"){
						dischargingCont();
						repayment();
					}else if(datas.trim()=="191"){
						messageBoxJust(191,"","");
					}else if(datas.trim()=="191H"){
						messageBoxJust(192,"","");
					}else if(datas.trim()=="041"){
						messageBoxJust(041,"","");
					}else{
						dischargingCont();
					}
				});	
			  }
			}
   
var chckAccL;  
function getAccession(e, test){
			var code = e.keyCode || e.which;
		//	$("#table-charging thead tr th").index(":contains(no)");
			var number = $('#table-charging tr:last td:first').text()
			var overExpMem = test;
			
				if(!$.isNumeric(number)){
						number=0;
					}
			
			number= parseInt(number)+1;
						
			   var action = $('input[name=actiontype]:checked').val();
			   var accession = ($("#CT03DOCNO").val()).toUpperCase();
			   var total = $(".total").text();
			   total = parseInt(total) + 1;
			   if (code == '9'||code == '13') {
			 	e.preventDefault(); // Prevent default behavior (form submission, etc.)
				e.stopPropagation(); // Prevent event propagation
// AUTO ZERO
//					   if(chckAccL == "Y"){
//							var chckLaccC = accession.length;
//							var macAccLAttr = $("#CT03DOCNO").attr("maxlength");
//							var macAccLAttrMin = $("#CT03DOCNO").attr("maxlength");
//							if(chckLaccC < macAccLAttrMin){
//								var paddedAccession;
//								var regex = /[^0-9]/;
//								if(regex.test(accession)){
//									paddedAccession = accession.padEnd(macAccLAttrMin, '0');
//									$("#CT03DOCNO").val(paddedAccession);
//								}else{
//									paddedAccession = accession.padStart(macAccLAttrMin, '0');
//									$("#CT03DOCNO").val(paddedAccession);	
//								}
//							}
//							
//						}
					}
				  accession = ($("#CT03DOCNO").val()).toUpperCase();
				  if(action =="discharge" || action =="renewal"){
					  if (code == '9'||code == '13') {
						e.preventDefault(); // Prevent default behavior (form submission, etc.)
    					e.stopPropagation(); // Prevent event propagation
						$(".GL14PATR").val("");
						$('#hiddenPatrId').val("");
						//$(".GL14NAME").html("");
						  //localStorage.setItem("patrid", $('#disGL14PATR').text());
						  var url = "Handler_PatronDetails?action="+action+"&CT03DOCNO=" + accession;
						  
								$.get(url,function(data)
								{
									
									 var message = data.split(",");
									  if(!accession )
										  {
										  messageBoxCir("020","","");
										  $('#CT03DOCNO').val('');
										  }else if(message[0].trim()=="066"){
											  messageBoxCir("066",message[1],"@status");
											  $('#CT03DOCNO').val('');
											}
										  else if(message[0].trim()=="020"){
											  messageBoxCir("020","","");
											  document.getElementById("CT03DOCNO").value="";
											}
									  else
									  {
										
										$("#patrDet").html(data);
										var AN01ACAT = 'GL01'; 
									    var GL14PATR= $('#hiddenPatrId').val();
//									    
									    access(GL14PATR);
						 				localStorage.setItem("patrid", GL14PATR);
									     
									    var url = "";
									    if(action =="discharge"){
									      url="AccessionDischarge?name=" + accession + "&patronid=" + GL14PATR;
									    }else{
									      url="AccessionRenewals?name=" + accession + "&patronid=" + GL14PATR + "&overExp=" + overExpMem;
									    }
										$.get(url,function(data)
									    { 
											 $(".dischargeaction").show();
											  
											  if(action =="discharge"){
												  $("#discharging").show();
												  $("#renewal2").hide();
											  }else{
												  $("#discharging").hide();
												  $("#renewal2").show();
											  }
											  $("#charging").hide();
											  var message = data.split(",");
											  if(data.trim().includes("\n")){
												if (!$('input#fast').is(':checked')) {
												  checkNote(GL14PATR, AN01ACAT);
												}
												  var datas =data.split("\n");
													$('#table-charging tr.status').remove();
													
												if(action =="renewal"){
													if(accession==""){
														 $("#renewal2").prop("disabled",true);
														 $("#printSlip").prop("disabled",true);
														 document.getElementById("GL14PATR").focus();
													}else{
														 $("#renewal2").prop("disabled",false);
														 $("#printSlip").prop("disabled",false);
														document.getElementById("CT03DOCNO").focus();
													}
													$("th.returndate, th.returntime, th.remarks").css('display', 'none');
													$("th.ndate, th.ntime,th.late, th.fine").css('display', '');
												
													$('td.Rdate').removeClass('Rdate');
													$('td.Rtime').removeClass('Rtime');

													 $(".title").text(datas[1]);
													 var chargeDateComp = datas[2];
													 var dischargeDateComp = datas[7];
													 
													 
													 var patron = localStorage.getItem("patrid");
													 if(chargeDateComp != null && chargeDateComp == dischargeDateComp){
														 $("#renewal2").prop("disabled",true);
														 $("#printSlip").prop("disabled",true);
														
														$('#table-charging tbody').html('<tr style="background-color:white" class="status"><td>'+total+'</td><td class="disCT03DOCNO">'+datas[0]+'</td>'+
																		'<td class="Cdate">'+datas[2]+'</td>'+
																		'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
																		'<td>'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td><td class="Rdate">'+datas[7]+'<input type="hidden" class="renewDate" value='+datas[7]+'></td><td class="Rtime">'
																		+datas[8]+'<input type="hidden" class="renewTime" value='+datas[8]+'><td class="lateby">'+datas[9]+'<input type="hidden" class="late" value= "'+datas[9]+'"></td><td class="fines">'+datas[10]+
																		'<input type="hidden" class="fine" value= "'+datas[10]+'"></td></tr>');
														
														swal("", "<strong>New due date is same as charge date. This charging transaction is not allowed.<strong>", "");
														return;
													 }
													 
														if(patron.trim()!=""){
															if(patron.trim()!= GL14PATR){
																$(".total").text('0');
																$('#table-charging tbody').html('<tr style="background-color:white" class="status"><td>'+total+'</td><td class="disCT03DOCNO">'+datas[0]+'</td>'+
																		'<td class="Cdate">'+datas[2]+'</td>'+
																		'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
																		'<td>'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td><td class="Rdate">'+datas[7]+'<input type="hidden" class="renewDate" value='+datas[7]+'></td><td class="Rtime">'
																		+datas[8]+'<input type="hidden" class="renewTime" value='+datas[8]+'><td class="lateby">'+datas[9]+'<input type="hidden" class="late" value= "'+datas[9]+'"></td><td class="fines">'+datas[10]+
																		'<input type="hidden" class="fine" value= "'+datas[10]+'"></td></tr>');
															}else{
																$('#table-charging tbody').append('<tr style="background-color:white" class="status"><td>'+total+'</td>'+
																		'<td class="disCT03DOCNO">'+datas[0]+'</td>'+
																		'<td class="Cdate">'+datas[2]+'</td>'+
																		'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
																		'<td>'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td><td class="Rdate">'+datas[7]+'<input type="hidden" class="renewDate" value='+datas[7]+'></td><td class="Rtime">'
																		+datas[8]+'<input type="hidden" class="renewTime" value='+datas[8]+'><td class="lateby">'+datas[9]+'<input type="hidden" class="late" value= "'+datas[9]+'"></td><td class="fines">'+datas[10]+
																		'<input type="hidden" class="fine" value= "'+datas[10]+'"></td>'+
																		'<td style="display: none;">'+GL14PATR+'</td>'+'</tr>');
															}
														}else{
															$('#table-charging tbody').append('<tr style="background-color:white" class="status">'+
																	'<td>'+total+'</td>'+
																	'<td class="disCT03DOCNO">'+datas[0]+'</td>'+
																	'<td class="Cdate">'+datas[2]+'</td>'+
																	'<td class="Ctime">'+datas[3]+'</td>'+
																	'<td class="Ddate">'+datas[4]+'</td>'+
																	'<td>'+datas[5]+'</td>'+
																	'<td class="statusid">'+datas[6]+'</td>'+
																	'<td class="Rdate">'+datas[7]+'<input type="hidden" class="renewDate" value='+datas[7]+'></td>'+
																	'<td class="Rtime">'+datas[8]+'<input type="hidden" class="renewTime" value='+datas[8]+'>'+
																	'<td class="lateby">'+datas[9]+'<input type="hidden" class="late" value= "'+datas[9]+'"></td>'+
																	'<td class="fines">'+datas[10]+'<input type="hidden" class="fine" value= "'+datas[10]+'"></td></tr>');
														}
														$('#table-charging tr.odd').remove();
													 if ($('input#fast').is(':checked')) {
															renew()
														}
														swal.close();
												}else{
													if(accession==""){
														 $("#discharging").prop("disabled",true);
														 $("#printSlip").prop("disabled",true);
														 document.getElementById("GL14PATR").focus();
													}else{
														 $("#discharging").prop("disabled",false);
														 $("#printSlip").prop("disabled",false);
														document.getElementById("CT03DOCNO").focus();
													}
													$("th.late, th.fine, th.returndate, th.returntime").css('display', '');
													$("th.ndate, th.ntime, th.remarks").css('display', 'none');
													$('th:nth-child(15)').hide();
													 var currentDate = new Date();
													 var day = currentDate.getDate();
													 var month = currentDate.getMonth() + 1;
													 var year = currentDate.getFullYear();
													 var time = (currentDate.getHours() <10 ? '0' + currentDate.getHours() : currentDate.getHours())+":"+ (currentDate.getMinutes() <10 ? '0' + currentDate.getMinutes() : currentDate.getMinutes())+":" + (currentDate.getSeconds() <10 ? '0' + currentDate.getSeconds() : currentDate.getSeconds()); // => 51

													 $(".title").text(datas[1]);
													 var patron = localStorage.getItem("patrid");
														if(patron.trim()!=""){
															if(patron.trim()!= GL14PATR){
																$(".number").text('n4');
																 $('#table-charging tbody').append('<tr style="background-color:white" class="status"><td>'+total+'</td><td class="disCT03DOCNO">'+datas[0]+'</td>'+
																			'<td class="Cdate">'+datas[2]+'</td>'+
																			'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
																			'<td class="Dtime">'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td>' + 
																			'<td class="lateby">'+datas[7]+'</td><td class="fines">'+datas[8]+ '</td>' +
																			'<td class="Rdate">'+(day <= 9 ? '0' + day : day)  + "/" + (month <= 9 ? '0' + month : month) + "/" + year +'</td>' + 
																			'<td class="Rtime">'+time+'</td>'+
																			'<td style="display: none;">'+datas[9]+'</td>'+
																			'</tr>');
																
															}else{
																 $('#table-charging tbody').append('<tr style="background-color:white" class="status"><td>'+total+'</td><td class="disCT03DOCNO">'+datas[0]+'</td>'+
																			'<td class="Cdate">'+datas[2]+'</td>'+
																			'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
																			'<td class="Dtime">'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td>' + 
																			'<td class="lateby">'+datas[7]+'</td><td class="fines">'+datas[8]+'</td>' + 
																			'<td class="Rdate">'+(day <= 9 ? '0' + day : day)  + "/" + (month <= 9 ? '0' + month : month) + "/" + year +'</td>' + 
																			'<td class="Rtime">'+time+'</td>' + 
																			'<td style="display: none;">'+GL14PATR+'</td>'+
																			'</tr>');
																}
														}else{
														 $('#table-charging tbody').append('<tr style="background-color:white" class="status"><td>'+total+'</td><td class="disCT03DOCNO">'+datas[0]+'</td>'+
																	'<td class="Cdate">'+datas[2]+'</td>'+
																	'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
																	'<td class="Dtime">'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td>' + 
																	'<td class="lateby">'+datas[7]+'</td>' + 
																	'<td class="fines">'+datas[8]+'</td>' + 
																	'<td class="Rdate">'+(day <= 9 ? '0' + day : day)  + "/" + (month <= 9 ? '0' + month : month) + "/" + year +'</td>' + 
																	'<td class="Rtime">'+time+'</td>' +
																	'<td style="display: none;">'+datas[9]+'</td>'+ 
																	'</tr>');
														}
														$('#table-charging tr.odd').remove();
													   if ($('input#fast').is(':checked')) {
															discharging()
														}
												}
													//charging(new_id);
													   $("#patrstatus").prop("disabled",false);
													   $("#patrDetails").prop("disabled",false);
													   $("#patrLateReturn").prop("disabled",false);
													   $("#receipting").prop("disabled",false);
													   $("#override").prop("disabled",false);
												}else{
													if(action =="renewal"){
														 $("#renewal2").prop("disabled",true);
														 $("#printSlip").prop("disabled",true);
													  }else{
														 $("#discharging").prop("disabled",false);
														 $("#printSlip").prop("disabled",false);
													  }
													var message = data.split(",");
													if(data.trim()=="038"){
														messageBoxCir("038","","");
														renew = false;
													}else if(message[0].trim()=="107"){
														  messageBoxCir("107",message[1],"@patrid");
													}else if(data.trim()=="082"){
														 messageBoxCir("082","","");
														 rightData = false;
													}else if((message[0]).trim()=="064"){
														printResvNotification(message[1]);
													}else if((message[0]).trim()=="resvFine"){
														resvFine(message[1]);
														//printResvNotification(message[1]);
													}else if(data.trim()=="074"){
														  repayment();
													}else if(data.trim()== "068"){
															var urls = "OverideExpFlag";
															$.get(urls,function(flagExp){
																var ansFlagExp = flagExp;
																if(ansFlagExp == '1'){
																	if(action == "renewal"){
																		oerrideMemExp("068R", null);
																	}else{
																		oerrideMemExp(data.trim(), null);
																	}
																	
																}else if(ansFlagExp == '0'){
																	messageBoxJust("068","","");
																}else{
																	if(action =="renewal"){
																		getAccession({ keyCode: 9, which: 9 }, "OK");
																	}else{
																		executeCharge("083");
																	}
																}
															});
													}else if(message[1]!= ""&&message[1]!="undefined"){
														  messageBoxCir(message[1],message[0],message[2]);
													}else{
														messageBoxCir(message[0].trim(),"","");
														rightData = false;
													}
												}
											  //$("#CT03DOCNO").val("");
									    			});
									  }
								});
								event.preventDefault();
				    	}
				  	}else if(action=="charge"){
				  
				  		if (code == '9'||code == '13') {
							e.preventDefault(); // Prevent default behavior (form submission, etc.)
    						e.stopPropagation(); // Prevent event propagation
    						$("#charging").prop("disabled",true);
							$("#printSlip").prop("disabled",true);
							$("#CT03DOCNO").prop("disabled",true);
						swal({
							title: 'Processing',
							allowEscapeKey: false,
							allowOutsideClick: false,
							onOpen: () => {
								swal.showLoading();
							}
						});
				  			
				  		 var id = $("#GL14PATR").val();
				  		 var rightData = true;
				  		 var proceed = true;
				  		 
						$.get("AccessionServlet?name=" + accession + "&patronid=" + id + "&action=checkAll",function(data)
				    			{
							if(data.trim().includes("\n")){
								var datas =data.split("\n");
								$("th.late, th.fine, th.rdate, th.rtime,th.ndate, th.ntime, th.remarks").css('display', 'none');
								$('#table-charging tr.status').remove();
								// $("#title").html(datas[1]);
								 $(".title").text(datas[1]);
				
								 $('#table-charging tbody').append('<tr style="background-color:white" class="status"><td>' +total +'</td><td>'+datas[0]+'</td>'+
											'<td>'+datas[2]+'</td>'+
											'<td>'+datas[3]+'</td><td class="dischargeDate">'+datas[4]+'</td>'+
											'<td class="dischargeTime">'+datas[5]+'</td><td class="statusid">Available</td></tr>');
								//charging(new_id);
								$('#table-charging tr.odd').remove();
								
								swal.close();
								
								if ($('input#fast').is(':checked') && proceed == true) {
									$("#charging").prop("disabled",true);
									$("#printSlip").prop("disabled",true);
									$("#CT03DOCNO").prop("disabled",true);
									charging()
								}else{
									$("#charging").prop("disabled",false);
									$("#printSlip").prop("disabled",false);
									$("#CT03DOCNO").prop("disabled",false);
								}
								
							}else{
								var message = data.split(",");
								$("#charging").prop("disabled",true);
								$("#printSlip").prop("disabled",true);
							
								if(data.trim()=="038"){
									proceed = false;
									 overrideMaxLoan(data.trim());
								}else if(data.trim()=="026"){
									 overrideMaxLoan(data.trim());
								}else if(message[0].trim()=="023"){
									  //messageBoxCir("023",message[1] + "," + message[2],"@patron");
									  messageBoxCir("023",message[2],"@patron");
									  rightData = false;
								}else if(data.trim()== "068"){
										//swal.close();
										var urls = "OverideExpFlag";
										$.get(urls,function(flagExp){
											var ansFlagExp = flagExp;
											if(ansFlagExp == '1'){
												oerrideMemExp(data.trim(), null);
											}else if(ansFlagExp == '0'){
												messageBoxJust("068","","");
											}else{
												executeCharge("083");
											}
										});
								}else if(message[1]!= ""&&message[1]!="undefined"){
									proceed = false;
									$("#CT03DOCNO").prop("disabled",false);
									messageBoxJust(message[1],message[0],message[2]);
								}else{
									messageBoxJust(message[0].trim(),"","");
									rightData = false;
								}
								
							}
							
							if(!rightData){
								$("#CT03DOCNO").val("");
							}
//							if(accession==""){
//								 $("#charging").prop("disabled",true);
//								 $("#printSlip").prop("disabled",true);
//								 document.getElementById("GL14PATR").focus();
//							}else{
//								 $("#charging").prop("disabled",false);
//								 $("#printSlip").prop("disabled",false);
//								document.getElementById("CT03DOCNO").focus();
//							}
							
							
				    	});
				  	    }
				   	}
			}
			
			
			//}


function batchrenewal(){
	var patron = $("#GL14PATR").val();
	var docno = [];
	var renewdate = [];
	var renewtime = [];
	var renewItems = [];
	 var array = new Array();

	   $("#table-charging").find("tbody tr").each(function () {
		   if ($(this).find("td:eq(12)").html().trim()=="") {
			   renewItems.push("NOERROR")
		   }else{
			   renewItems.push("ERROR")
		   }
	    });

	if(renewItems.includes("NOERROR")){
	$("#table-charging tbody tr").each(function(i) {
		if ($(this).find(".isyraf").is(':checked')&& $(this).find("td:eq(12)").html().trim()=="") {
		
		var renewDate = $(this).find("td:eq(8)");
		var renewTime = $(this).find("td:eq(9)");
		var status = $(this).find("td:eq(7)");
		var row = $(this);
		var fine = $(this).find("td:eq(11)");
		var lateby = $(this).find("td:eq(10)");
		
		var renew = true;
		var url = "Handler_Renewal?CT03DOCNO=" + $(this).find("td:eq(2)").text().trim() +
				  "&GL14PATR=" +patron + "&Rdate="+ $(this).find("td:eq(8)").text()+
				  "&Rtime="+ $(this).find("td:eq(9)").text();
		var url2 = "Handler_PatronDetails?GL14PATR=" + patron +  "&action=charge" ;
		
		JSON.parse('""');
		
	    $.ajax({
	        async: false,
	        url: url,
	        success: function(data) {
				
				if(data.trim()=="041"){
					messageBoxCir(173,"","");
				}else{
					
					$(renewDate).html("<strong>" +$(renewDate).text()+ "</strong>");
					$(renewTime).html("<strong>" + $(renewTime).text() + "</strong>");
		        
		        
				    $.ajax({
				        async: false,
				        url: url2,
				        success: function(data) {
							var details = data.trim().split("/n");
							
							$("#onloan").text(details[5]);
							$("#itemoverdue").text(details[6]);
							$("#itemreserved").text(details[7]);
							$("#fines").text(details[8]);
				        }});
		        
					$(status).html('<strong>Renewed</strong>');
					$(row).css('background', 'LightGreen');
						
					if($(fine).text()!=0.00){
						var lateDetails={late:$(lateby).text(),fine:$(fine).text()};
						localStorage.setItem('lateDetails', JSON.stringify(lateDetails));
						var details =JSON.parse(localStorage.getItem('lateDetails'));
						repaymentBatchRenewal();
						$(lateby).html(0 + "<input type='hidden' class='late' value='"+details.late+"'>");
						$('.fines').html((0.00).toFixed(2) + "<input type='hidden' class='fine' value='"+details.fine+"'>");
					}
				}
			
	        
	        }});
		
		 if ($('.printSlip').is(":checked"))
			{
			 array.push($(this).find("td:eq(2)").text().trim()+ "|" + 
					 $(this).find("td:eq(5)").text().trim()+ "|" +
						renewDate.text() + "|" + 
						lateby.text() + "|" + fine.text());
		}	
		}
	});
	
	
	 if ($('.printSlip').is(":checked"))
		{
		 var url = "GeneratePreview?GL14PATR=" + patron + "&CT03DOCNO="+array.toString()+"&action=Renewal";
		 window.open(url,"", "width=283,height=500,toolbar=0").print();
		}	
	}else{
		 swal('Info!',"No items to be renewed", '' );
	}
}
function batchrenew(GL14PATR, ansFlagExp){
	swal({
			title: 'Gathering Batch',
			allowEscapeKey: false,
			allowOutsideClick: false,
			onOpen: () => {
				swal.showLoading();
			}
		});
	var GL14PATRfirst = GL14PATR;
	var OverExp = ansFlagExp;
	var rightData = true;
	var action = $('input[name=actiontype]:checked').val();
	  if(action =="batchrenewal"){
	
		var url = "AccessionBatchRenew?patronid=" + GL14PATR + "&overExp=" + OverExp;
		
		$.get(url,function(data)
			{ 
			if(data.trim()==""){
				   swal('Info!',"There's no circulated item", '' );
			}else if(data.trim()== "068"){
				  //messageBoxCir("032", "", "");
				  //rightData = true;
				  var urls = "OverideExpFlag";
					$.get(urls,function(flagExp){
						var ansFlagExp = flagExp;
						if(ansFlagExp == '1'){
							oerrideMemExp(data.trim(), GL14PATRfirst);
						}else if(ansFlagExp == '0'){
							rightData = false;
							messageBoxJust("068","","");
						}else{
							batchrenew(GL14PATRfirst, ansFlagExp);
						}
					});
				  //oerrideMemExp(data.trim());
			}else if(data.trim()== "032"){
				  messageBoxJust("032", "", "");
				  rightData = true;
				  //oerrideMemExp(data.trim());
			}else if(data.trim()=="030"){
				messageBoxJust("030","","");
			}else{
				swal.close();
				if(rightData == true){
					$('#table-charging tr.status').remove();
					$("th.returndate, th.returntime").css('display', 'none');
					$("th.checkboxs,th.ndate, th.ntime,th.late, th.fine, th.remarks").css('display', '');	
					$('td.Rdate').removeClass('Rdate');
					$('td.Rtime').removeClass('Rtime');
					var patron = localStorage.getItem("patrid");
					$('#table-charging tbody').html(data);
					$('[data-toggle="tooltip"]').tooltip();
				}else{
					
					$('#table-charging tr.status').remove();
					$("th.returndate, th.returntime").css('display', 'none');
					$("th.checkboxs,th.ndate, th.ntime,th.late, th.fine, th.remarks").css('display', '');	
					$('td.Rdate').removeClass('Rdate');
					$('td.Rtime').removeClass('Rtime');
					var patron = localStorage.getItem("patrid");
					$('#table-charging tbody').html(data);
					$('[data-toggle="tooltip"]').tooltip();
					//$("input[name=renew]").attr("checked","checked");
					//$('input[name="renew"]'). prop("checked", true);
					
					$('#table-charging tr.odd').remove();
											 
					$("#patrstatus").prop("disabled",false);
					$("#patrDetails").prop("disabled",false);
					$("#patrLateReturn").prop("disabled",false);
					$("#receipting").prop("disabled",false);
					$("#override").prop("disabled",false);
					}
				
			}
			
			});
			
			event.preventDefault();

	  	}
}


function renew(){

	
	  var CT03DOCNO =  $(".disCT03DOCNO").text();
			  var GL14PATR= $('#hiddenPatrId').val();
	
			  var renew = true;
				//var url = "Handler_Renewal?CT03DOCNO=" + CT03DOCNO +"&GL14PATR=" +GL14PATR + "&Rdate="+ $(".renewDate").val()+"&Rtime="+ $(".renewTime").val();
			  var url = "Handler_Renewal?CT03DOCNO=" + CT03DOCNO +"&GL14PATR=" +GL14PATR + "&Rdate="+ $(".Rdate").text()+"&Rtime="+ $(".Rtime").text();
	
				JSON.parse('""');
				var renewPrint={GL14PATR:GL14PATR,CT03DOCNO:CT03DOCNO, dueDate:$(".Ddate").text(),renewDate:$(".Rdate").text(),
							late:$(".lateby").text(),fine:$(".fines").text() };
				localStorage.setItem('renewPrint', JSON.stringify(renewPrint));
				
				$.get(url,function(data){
					
					if(data.trim()=="041"){
						messageBoxCir("173","","");
					}else{
						
						var details=data.split(",");

									 $.each($('#table-charging tbody tr'), function(i, v) {
									     if(!$(this).hasClass("newlist")) {
									       $(this).find("td:eq(7)").css("font-weight","bold");
									       $(this).find("td:eq(8)").css("font-weight","bold");
									     }
									  });

									var url2 = "Handler_PatronDetails?GL14PATR=" + GL14PATR +  "&action=charge" ;
									
									$.get(url2,function(data)
											{ 
										var details = data.trim().split("/n");
										for (var i = 0; i < details.length; i++) {
										}
										$("#onloan").text(details[5]);
										$("#itemoverdue").text(details[6]);
										$("#itemreserved").text(details[7]);
										$("#fines").text(details[8]);		
												 
									});	
												$(".statusid").html('<strong>Renewed</strong>');
												var status1 = $('.renewDate');
												status1.removeClass('renewDate');
												var status2 = $('.renewTime');
												status2.removeClass('renewTime');
												var status = $('.statusid');
												status.removeClass('statusid');	
												$('td.Ddate').removeClass('Ddate');
												$('td.disCT03DOCNO').removeClass('disCT03DOCNO')
												var el = $('.status');
												el.addClass('newlist');
												el.removeClass('status');
												$(".title").text("");
												$(".newlist").css('background', 'LightGreen');
												var total = $(".total").text();
												total = parseInt(total) + 1;
												$(".total").text(total);
												
												if($(".fines").text()!=0.00){
													var lateDetails={late:$('.lateby').text(),fine:$('.fines').text()};
													localStorage.setItem('lateDetails', JSON.stringify(lateDetails));
													var details =JSON.parse(localStorage.getItem('lateDetails'));
													repayment();
										//			$('.lateby').html(0 + "<input type='hidden' class='late' value='"+details.late+"'>");
													$('.lateby').html(0 + "<input type='hidden' class='late' value='testing123'>");
													$('.fines').html((0.00).toFixed(2) + "<input type='hidden' class='fine' value='"+details.fine+"'>");
												}
												
												 if ($('.printSlip').is(":checked"))
													{
													 var details =JSON.parse(localStorage.getItem('renewPrint'));
													 var array = new Array();
											
													 array.push(CT03DOCNO+ "|" + 
																details.dueDate+ "|" +
																details.renewDate + "|" + 
																details.late + "|" + details.fine);
													 	// it is checked
													 //var url = "Print_Slip?GL14PATR=" + GL14PATR + "&CT03DOCNO="+array.toString()+"&action=Renewal";
													 //12112019
													 var url = "GeneratePreview?GL14PATR=" + GL14PATR + "&CT03DOCNO="+array.toString()+"&action=Renewal";
													 
													 
													window.open(url,"", "width=283,height=500,toolbar=0").print();
												}	
												
												 $('.lateby').removeClass('lateby');
												 $('.fines').removeClass('fines');
											     document.getElementById("CT03DOCNO").value='';
											     $("#CT03DOCNO").focus();  
					}
					

					
				});	
				
				 
			}

function getValue(){
	var flag = $("input[name='optradio']:checked").val();
	var GL14PATR = $("#GL14PATRS").val();
	
	var url = "Table_PatrReqStat?GL14PATR=" + GL14PATR + "&FLAG=" + flag;
	
	 $.get(url,function(data){
			$("#table_patrreq").html(data);
	   });
}

function checkNote(GL14PATR, AN01ACAT) {
  return new Promise(function(resolve, reject) {
    var urls = "PatronNote?AN01ACAT=" + AN01ACAT + "&AN01REFKEY=" + GL14PATR;
    $.get(urls, function(note) {
      var Notails = note;
      var message = "Do you want to view the note?";
	
      if (Notails != 0 && GL14PATR != "") {
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
          document.getElementById("viewNoteASD").click();

          // Listen for the view modal close event
          $('#modalViewNote').on('hidden.bs.modal', function() {
            resolve(); // Resolve the promise when the view modal is closed
          });
        }, function(dismiss) {
          if (dismiss === 'cancel') {
            resolve();
          }
        });
      } else {
		swal.close();
        resolve(); // Resolve the promise if no note or GL14PATR is empty
      }
    });
  });
}





$(document).ready(function() {
	
//	var table_id = "table-charging"; // replace this with your table's id
//		  $(document).on("change", "#" + table_id + " input.isyraf", function() {
//		    console.log(this.id);
//		  });
	// Capture Enter keypress globally
	
	var accLenght = "GetGlobalValidation?action=maxL";
	
	$.get(accLenght, function(response) {
		chckAccL = response.chkAcc;
	    if(chckAccL == 'Y') {
			$("#CT03DOCNO").attr("maxlength", response.accLength);
		    $("#CT03DOCNO").attr("minlength", response.accLenghtMin);
		}else{
			console.log("Using DEFAULT MAX AND MIN LENGHT");
		}
	});
	   
	document.addEventListener('keydown', function(event) {
	    var key = event.key;
	    if (key === 'Tab' || key === 'Enter' || key === ' ') {
	        event.preventDefault();
	    }
	});
	
	// TO CHANGE ALPHABET TO UPPERCASE
	$('input.upCaseLetter').keyup(function() {
		this.value = this.value.toUpperCase();
	});

	$(document).on("change", "input.isyraf", function() {
    if ($("#" + this.id).is(":checked")) {
        $("#batchrenewbtn").prop('disabled', false);
    } else {
		if($("#checkAll").is(":checked") || $(".isyraf").is(":checked")){
			if($("#checkAll").is(":checked") && !$(".isyraf").is(":checked")){
				$("#batchrenewbtn").prop('disabled', true);
			}else{
				$("#batchrenewbtn").prop('disabled', false);
			}
		}else{
			$("#batchrenewbtn").prop('disabled', true);
		}
    }
});

	
	$("#GL14PATR").on('keydown', function(e)
	{ 	
		var code = e.keyCode || e.which;			

	  var action = $('input[name=actiontype]:checked').val();
	  var AN01ACAT = 'GL01';
	  var thrisError = 'Y'
	  
	  if(action =="charge" || action=="batchrenewal"){
			if (code == '9'||code == '13') {
				e.preventDefault(); // Prevent default behavior (form submission, etc.)
    			e.stopPropagation(); // Prevent event propagation
				$("#GL14PATR").blur();
				swal({
							title: 'Processing Patron Information',
							allowEscapeKey: false,
							allowOutsideClick: false,
							onOpen: () => {
								swal.showLoading();
							}
						});
				
				if(action =="charge"){
					reload("charge");
				}else{
					reload("batchrenewal");
				}
				var rightData = true;
				var enableCT03DOCNO = false;
				 var GL14PATR = this.value;
				 access(GL14PATR);
		  		var url = "Handler_PatronDetails?action="+action+"&GL14PATR=" + GL14PATR;
		  		
				$.get(url,function(data){
					
					if(action =="charge"){
						var details = data.trim().split("/n");
						
						if(details[0]== "033"){
							messageBoxCir("033","","");
							rightData = false;
							return;
						}
						
						var errCode = details[14].substring(0, details[14].indexOf(','));
						var errMsg = details[14].substr(details[14].indexOf(',')+1);
						
						
						if(data.indexOf("GL08ACTION1=N")!= -1){
							
							if(errCode != ""){
							messageBoxCir(errCode,errMsg,"@status");
							 $("#patrstatus").prop("disabled",true);
							   $("#patrDetails").prop("disabled",true);
							   $("#patrLateReturn").prop("disabled",true);
							   $("#receipting").prop("disabled",true);
							   $("#override").prop("disabled",true);
							   //$("#GL14PATR").val("")
							rightData = true;
							enableCT03DOCNO = true;
							}else{
								messageBoxCir(errMsg,errCode,"@status");
							 	$("#patrstatus").prop("disabled",true);
							    $("#patrDetails").prop("disabled",true);
							    $("#patrLateReturn").prop("disabled",true);
							    $("#receipting").prop("disabled",true);
							    $("#override").prop("disabled",true);
								 rightData = true;
								 enableCT03DOCNO = true;
							}
						}else if(details[14]== "033"){
							messageBoxCir("033","","");
							rightData = false;
						}else if(details[14]== "036"){
							$("#patrstatus").prop("disabled",true);
						    $("#override").prop("disabled",true);
						    $("#patrDetails").prop("disabled",true);
						    $("#patrLateReturn").prop("disabled",true);
						    $("#receipting").prop("disabled",true);
							$("#GL14PATR").val("");
							messageBoxCir("036","","");
							rightData = false;
						}else if(details[14]== "080"){
							messageBoxCir("080",details[14], "@status");
							rightData = false;
						}else if(details[14]== "082"){
							messageBoxCir("082","", "");
							rightData = true;
							enableCT03DOCNO = true;
						}else if(details[14]== "050"){
							messageBoxCir("050","","");
							rightData = false;
						}else if(details[14]== "032"){
							messageBoxCir("032","","");
							rightData = true;
							enableCT03DOCNO = true;
						}else if(details[14]== "040"){
							messageBoxCir("040","","");
							rightData = false;
						}else if(details[14]== "046"){
							messageBoxCir("046","","");
							rightData = true;
							enableCT03DOCNO = true;
						}else{
							rightData = true;
							checkNote(GL14PATR, AN01ACAT);
						}
						
					if(rightData==true){
						
							var details = data.trim().split("/n");
							if(enableCT03DOCNO==false){
								$("#CT03DOCNO").prop("disabled",false);
								$("#CT03DOCNO").focus();
							}else{
								$("#CT03DOCNO").prop("disabled",true);
							}
							$("#patrstatus").prop("disabled",false);
						    $("#override").prop("disabled",false);
						    $("#patrDetails").prop("disabled",false);
						    $("#patrLateReturn").prop("disabled",false);
						    $("#receipting").prop("disabled",false);
	
	
							$(".GL14NAME").append(' <span>'+details[0]+'</span>');
							$(".GL14CATE").append(' <span>'+details[1]+'</span>');
							$(".GL14STAT").append(' <span>'+details[2].substring(0, details[2].indexOf(','))+'</span>');
							$(".GL14MEMDATE").append(' <span>'+details[3]+'</span>');
							$(".GL14EXPDATE").append(' <span>'+details[4]+'</span>');
							$(".GL14BRANCH").append(' <span>'+details[10]+'</span>');
							$(".department").append(' <span>'+details[11]+'</span>');
	
							$("#onloan").text(details[5]);
							$("#itemoverdue").text(details[6]);
							$("#itemreserved").text(details[7]);
							$("#fines").text(details[8]);
							$("#CT03DOCNO").focus();
					}

					} else if(action =="batchrenewal"){
						
						var details = data.trim().split("/n");
						var errCode = details[14].substring(0, details[14].indexOf(','));
						var errMsg = details[14].substr(details[14].indexOf(',')+1);
						$(".GL14NAME").append(' <span>'+details[0]+'</span>');
						$(".GL14CATE").append(' <span>'+details[1]+'</span>');
						$(".GL14STAT").append(' <span>'+details[2].substring(0, details[2].indexOf(','))+'</span>');
						$(".GL14MEMDATE").append(' <span>'+details[3]+'</span>');
						$(".GL14EXPDATE").append(' <span>'+details[4]+'</span>');
						$(".GL14BRANCH").append(' <span>'+details[10]+'</span>');
						$(".department").append(' <span>'+details[11]+'</span>');

						$("#onloan").text(details[5]);
						$("#itemoverdue").text(details[6]);
						$("#itemreserved").text(details[7]);
						$("#fines").text(details[8]);
						document.getElementById("CT03DOCNO").focus();
						
						if(data.indexOf("GL08ACTION3=N")!= -1){
							messageBoxCir("028","","");
							 $("#patrstatus").prop("disabled",true);
							   $("#patrDetails").prop("disabled",true);
							   $("#patrLateReturn").prop("disabled",true);
							   $("#receipting").prop("disabled",true);
							   $("#override").prop("disabled",true);
							   $("#GL14PATR").val("")
							rightData = false;
						}else if(details[14]== "033" || errCode == "033"){
							messageBoxCir("033","","");
							rightData = false;
						}else if(details[14]== "036" || errCode == "036"){
							messageBoxCir("036","","");
							rightData = false;
						}else if(details[14]== "080" || errCode == "080"){
							messageBoxCir("080",errMsg, "@status");
							rightData = false;
						}else if(details[14]== "082" || errCode == "082"){
							messageBoxCir("082","", "");
							$("#CT03DOCNO").prop("disabled",true);
							rightData = false;
						}else if(details[14]== "050" || errCode == "050"){
							messageBoxCir("050","","");
							rightData = false;
						}else if(details[14]== "032" || errCode == "032"){
							messageBoxCir("032","","");
							$("#CT03DOCNO").prop("disabled",true);
							rightData = false;
						}else if(details[14]== "040" || errCode == "040"){
							messageBoxCir("040","","");
							rightData = false;
						}else if(details[14]== "046" || errCode == "046"){
							messageBoxCir("046","","");
							$("#CT03DOCNO").prop("disabled",true);
							rightData = true;
						}else{
							  //alert("masuk sebab ??");
						}
						
					if(rightData==true){
						var details = data.trim().split("/n");

						swal.close();
						checkNote(GL14PATR, AN01ACAT)
							  .then(function() {
							    batchrenew(GL14PATR, null);
							  })
							  .catch(function(error) {
							    console.error('Error in checkNote:', error);
							  });
						}
					}
					 
				});	
			}
	  }
	 
	});
	
	 $("[title]").tooltip();
	  $("#charging").show();
	  $("#discharging").hide();
	  $("#renewal2").hide();
	  //$(".dischargeaction").hide();

	  //Override date modal, refresh when close
	  //Date : 16/6/2020
	  $('#overrideModal').on('hidden.bs.modal', function(e)
	   { 
		 $(this).removeData();
		}) ;
	  
	  $('#overrideModal').on('shown.bs.modal', function (e) {
		   var action = $( 'input[name=actiontype]:checked' ).val();
		  if(action=="charge"){
			var dueDate = $('#table-charging tr.status td.dischargeDate').text();
			var dueTime =  $('#table-charging tr.status td:eq(5)').text();
			var date = dueDate.split("/"); 
			var output = date[2]+"-" +date[1]+ "-" +date[0] + " " + dueTime;
			$('#datetimepicker1').data('DateTimePicker').date(new Date(output));
		  }
		
			})
			
		$("#checkAll").change(function(){
			$(".isyraf").prop('checked', $(this).prop('checked'));
			
			if($(this). is(":checked")){
				$("#batchrenewbtn").prop('disabled', false);
				}
				else {
					$("#batchrenewbtn").prop('disabled', true);
				}
		});
		
		

		
		$("table").on("change", "input.checkAlls", function() {
		  console.log(this.id);
		});
				
		var checkboxes = $("input.checkAlls");
		checkboxes.each(function() {
		  // do something with each checkbox
		  console.log(this.id);
		});
	  
			$('#CT03DOCNO').focus(function() { 
				$('#title').html('');
			});

	
	  $("#refresh").click(function(){
		  var action = $( 'input[name=actiontype]:checked' ).val();
		reload(action);
		$('#GL14PATR').val('');
	});
	  
	   $("#patrstatus").click(function(){
		 var GL14PATR= document.getElementById("GL14PATR").value;
		 
		 if(!GL14PATR){
			 GL14PATR =$('#hiddenPatrId').val();
		 }
	
		   var url = "Handler_PatrStatus?GL14PATR=" + GL14PATR;
		 $.get(url,function(data){
				$("#patrDetailsTable").html(data);
		   });
	   });
	   
	   
	   
	   $("#patrDetails").click(function(){
			 var GL14PATR= document.getElementById("GL14PATR").value;
			 if(!GL14PATR){
				 GL14PATR =$('#hiddenPatrId').val();
			 }
		
			   var url = "PatrDetailsModal?GL14PATR=" + GL14PATR;
			 $.get(url,function(data){
					$("#patrDetailsTable").html(data);
			   });
		   });
	   
	   $(".search").click(function(){
		   var action = $( 'input[name=actiontype]:checked' ).val();

			   var url = "Modal_PatrSearch?action=" + action;
			
			 $.get(url,function(data){
				
				 $("#searchPatrContent").html(data);
			   });
		   });
	   
	   $("#override").click(function(){

		   var action = $( 'input[name=actiontype]:checked' ).val();
		
		   
		   var accession  = $('#table-charging tr.status td:eq(1)').text();
		   var chargeDate  = $('#table-charging tr.status td:eq(2)').text();
		   var chargeTime  = $('#table-charging tr.status td:eq(3)').text();
		   
		   var url = "Modal_OverrideDate?action=" + action + "&chargeDate=" + chargeDate + 
  			"&chargeTime=" + chargeTime;

		   $.get(url,function(data){
			   		$("#overrideModal").modal("show");
				 $(".override").html(data);
			   });
		  // return false;
		   });
	   
	/*   $("#override").click(function(){
		   var action = $( 'input[name=actiontype]:checked' ).val();
	
		   
			   var accession  = $('#table-charging tr.status td:eq(0)').text();
			   var chargeDate  = $('#table-charging tr.status td:eq(2)').text();
			   var chargeTime  = $('#table-charging tr.status td:eq(3)').text();

			   var url = "Modal_OverrideDate?action=" + action + "&chargeDate=" + chargeDate + 
			   			"&chargeTime=" + chargeTime;
			 $.get(url,function(data){
				 $("#overrideTable").html(data);
			   });
		   });
	   */
	   
	  
	   $("#receipting").click(function(){
		    var GL14PATR= document.getElementById("GL14PATR").value;
		    
		    if(!GL14PATR){
				 GL14PATR =$('#hiddenPatrId').val();
			 }
			 
			var protocol =  window.location.protocol;
			var popup;
			var host = protocol + "//" + window.location.host + "/web/guest/maintenance1";
			/*popup = window.open(host+"?patrid="+ GL14PATR, '');*/
			popup = window.open(host + '?patrid='+ GL14PATR);
	   })
	   
	   $("#patrLateReturn").click(function(){
			 var GL14PATR= document.getElementById("GL14PATR").value;
			 
			 if(!GL14PATR){
				 GL14PATR =$('#hiddenPatrId').val();
			 }
		
			   var url = "Modal_PatrLateReturnHistory?GL14PATR=" + GL14PATR;
			 $.get(url,function(data){
					$("#lateReturnTable").html(data);
			   });
		   });
	   
	   
	  $.get("template",{MODULE:"Circulation/01_Charging",ACTION:"Charging.jsp"},function(data_title){
	   });

	   
	   $("#button-generateAccession").click(
			   function(){
			$("td.GL14NAME").html("test");
			$("td.GL14CATE").html("test");
			$("td.GL14STAT").html("test");
			$("td.GL14MEMDATE").html("test");
			$("td.GL14EXPDATE").html("test");
	   });
	

	
	   var tbody = $('#table-charging').children('tbody');

	 //Then if no tbody just select your table 
	 var table = tbody.length ? tbody : $('#table-charging');
	 var ids = 0;

	   $("#patrStatusModal").appendTo("body");
	   $('input[name=actiontype]').change(function(){
		   var action = $( 'input[name=actiontype]:checked' ).val();
		   
		   if(action == "charge"){
			   reload(action);
		   }
		   else if(action == "renewal"){
			   reload(action);
		   }else if(action == "batchrenewal"){
			   reload(action);
		   }
		   else{
			   reload(action);
		   }
		   event.preventDefault();
		   });
 

//	$("#GL14PATR").on('keyup', function(e){
//			
//			  if (!this.value) {
//				
//			       reload("charge");
//			       access(this.value);
//			    }else{
//			    	   $("#patrstatus").prop("disabled",false);
//					   $("#patrDetails").prop("disabled",false);
//					   $("#patrLateReturn").prop("disabled",false);
//					   $("#receipting").prop("disabled",false);
//					   $("#override").prop("disabled",false);
//					   $("#CT03DOCNO").prop("disabled",false);
//			    }
//				
//			});
		
		
		
		$("#GL14PATR").focus(function(event){

			setTimeout(function(){ 
				var patron = $("#GL14PATR").val();
			 if (patron.length < 1){
	
				  		var url = "Handler_PatronDetails?action=smartcardreader";
				  		
						$.get(url,function(data)
						{
							if(data.trim()!="null"){
							$("#GL14PATR").val(data);
							$("#CT03DOCNO").prop("disabled",false);
							}
						});	
						event.preventDefault();
			 	}
			 }, 5000);
			});
	
	});

/*						
if(data.trim()== "032"){
		messageBoxCir("032","","");
		 $("#patrstatus").prop("disabled",true);
		   $("#patrDetails").prop("disabled",true);
		   $("#patrLateReturn").prop("disabled",true);
		   $("#receipting").prop("disabled",true);
		   $("#override").prop("disabled",true);
		   $("#GL14PATR").val("")
		rightData = false;
}else if(data.trim()== "036"){
	messageBoxCir("036","","");
	 $("#patrstatus").prop("disabled",true);
	   $("#patrDetails").prop("disabled",true);
	   $("#patrLateReturn").prop("disabled",true);
	   $("#receipting").prop("disabled",true);
	   $("#override").prop("disabled",true);
	   $("#GL14PATR").val("")
	rightData = false;
}else if(dataTrim.indexOf("080,SATISFACTORY")!= -1){
	messageBoxCir("080","SATISFACTORY","@status");
	 $("#patrstatus").prop("disabled",true);
	   $("#patrDetails").prop("disabled",true);
	   $("#patrLateReturn").prop("disabled",true);
	   $("#receipting").prop("disabled",true);
	   $("#override").prop("disabled",true);
	   $("#GL14PATR").val("")
	rightData = false;
}else if(dataTrim.indexOf("080,TERMINATED")!= -1){
	messageBoxCir("080","TERMINATED","@status");
	 $("#patrstatus").prop("disabled",true);
	   $("#patrDetails").prop("disabled",true);
	   $("#patrLateReturn").prop("disabled",true);
	   $("#receipting").prop("disabled",true);
	   $("#override").prop("disabled",true);
	   $("#GL14PATR").val("")
	rightData = false;
}else if(dataTrim.indexOf("080,BLACK LISTED")!= -1){
	messageBoxCir("080","BLACK LISTED","@status");
	 $("#patrstatus").prop("disabled",true);
	   $("#patrDetails").prop("disabled",true);
	   $("#patrLateReturn").prop("disabled",true);
	   $("#receipting").prop("disabled",true);
	   $("#override").prop("disabled",true);
	   $("#GL14PATR").val("")
	rightData = false;
}else if(data.trim()== "082"){
	messageBoxCir("082","","");
	 $("#patrstatus").prop("disabled",true);
	   $("#patrDetails").prop("disabled",true);
	   $("#patrLateReturn").prop("disabled",true);
	   $("#receipting").prop("disabled",true);
	   $("#override").prop("disabled",true);
	   $("#GL14PATR").val("")
	rightData = false;
}else{
	var details = data.trim().split("/n");
	var message = (details[12].trim()).split(",");

	if(details[14]== "033"){
		messageBoxCir("033","","");
		rightData = false;
	}else if(details[14]== "036"){
		messageBoxCir("036","","");
		rightData = false;
		}else if(details[14]== "080"){
		messageBoxCir("080",details[14], "@status");
		rightData = false;
		}else if(details[14]== "082"){
		messageBoxCir("082","", "");
		rightData = false;
		}else if(details[14]== "050"){
		messageBoxCir("050","","");
		rightData = false;
		}else if(details[14]== "032"){
			messageBoxCir("032","","");
			rightData = false;
		}else if(details[14]== "040"){
			messageBoxCir("040","","");
			rightData = false;
		}else if(details[14]== "046"){
			messageBoxCir("046","","");
			rightData = false;
		}
	var details = data.trim().split("/n");


	$(".GL14NAME").append(' <span>'+details[0]+'</span>');
	$(".GL14CATE").append(' <span>'+details[1]+'</span>');
	$(".GL14STAT").append(' <span>'+details[2]+'</span>');
	$(".GL14MEMDATE").append(' <span>'+details[3]+'</span>');
	$(".GL14EXPDATE").append(' <span>'+details[4]+'</span>');
	$(".GL14BRANCH").append(' <span>'+details[10]+'</span>');
	$(".department").append(' <span>'+details[11]+'</span>');

	$("#onloan").text(details[5]);
	$("#itemoverdue").text(details[6]);
	$("#itemreserved").text(details[7]);
	$("#fines").text(details[8]);
	
	  if (!$("#GL14PATR").val()) {
	       reload("charge");
	       access($("#GL14PATR").val());
	    }else{
	    	   $("#patrstatus").prop("disabled",false);
			   $("#patrDetails").prop("disabled",false);
			   $("#patrLateReturn").prop("disabled",false);
			   $("#receipting").prop("disabled",false);
			   $("#override").prop("disabled",false);
	    }
}*/
/*								if(!rightData){
			 $("#charging").prop("disabled",true);
			 $("#printSlip").prop("disabled",true);
			 $("#CT03DOCNO").prop("disabled",true);
			 document.getElementById("GL14PATR").focus();
		}else{
			 $("#charging").prop("disabled",false);
			 $("#printSlip").prop("disabled",false);
			 $("#CT03DOCNO").prop("disabled",false);
			document.getElementById("CT03DOCNO").focus();
		}
*/


function getMessage(code, criteria, label, message){
    var url = "Error_Page?GL79ERRCODE="+code+"" +
    "&criteria=" + criteria + "&label="+label+"";

    $.ajax({
          url: url,
          success: function(result) {
                swal('Info!',result.trim() + message, '' );
          }
    });
}

function getMessageResv(code, criteria, label, message){
    var url = "Error_Page?GL79ERRCODE="+code+"" +
    "&criteria=" + criteria + "&label="+label+"";

    $.ajax({
          url: url,
          success: function(result) {
                swal({
                title: 'Info!',
                text: result.trim() + message,
            }).then(function (isConfirmed) {
                if (isConfirmed) {
                    resvFine(message[1]);
                }
            });
          }
    });
}

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

function preventEnterDismiss(event) {
	var code = event.keyCode || event.which;
    if (code == '9'||code == '13'||code == '32') {
        event.preventDefault();
    }
}


function charging(){
	$("#charging").prop("disabled",true);
	$("#printSlip").prop("disabled",true);
	$("#CT03DOCNO").prop("disabled",true);
	var a = performance.now();
	 swal({
			title: 'Charging',
			allowEscapeKey: false,
			allowOutsideClick: false,
			onOpen: () => {
				swal.showLoading();
				
				// Add a keydown event listener to the document
				document.addEventListener("keydown", preventEnterDismiss);
			}
		});

	  //var CT03DOCNO = document.getElementById("CT03DOCNO").value;
	  var CT03DOCNO = $('#table-charging tr:last').find("td").eq(1).text();
	  var dischargeDate = $('#table-charging').find('td.dischargeDate').text();
	  var dischargeTime = $('#table-charging').find('td.dischargeTime').text();
	  var chargeDate = $('#table-charging tr:last').find("td").eq(2).text();
	  var chargeTime = $('#table-charging tr:last').find("td").eq(3).text();
	  
	  var GL14PATRR= document.getElementById("GL14PATR").value;
	  var GL14PATR = GL14PATRR.trim()
	  var Ddate = dischargeDate.split("/");
	  var date = Ddate[0];
	  var month = Ddate[1];
	  var year = Ddate[2];
	  var url = "Handler_Circulation?CT03DOCNO=" + CT03DOCNO +"&GL14PATR=" +GL14PATR + "&Date=" + year+month+date+"&Time=" + dischargeTime + 
	  			"&chargeDate=" +chargeDate +"&chargeTime=" + chargeTime ;
			
			if(GL14PATR == "" || GL14PATR == null || GL14PATR == "undefined") {
				swal.close();
				messageBoxJust("036","","");
				}else{
			$.get(url,function(patr){
			if(patr.trim()==="021"){
				messageBoxJust("021","","");
			}
			else if(patr.trim()=="022"){
				//messageBoxCir("022",CT03DOCNO,"@docno");
				messageBoxJust("022","","");
			}
			else if(patr.trim()=="027"){
				messageBoxJust("027","","");
			}
			else if(patr.trim()=="028"){
				messageBoxJust("028","","");
			}
			else if(patr.trim()=="029"){
				messageBoxJust("029","","");
			}
			else if(patr.trim()=="030"){
				messageBoxJust("030","","");
			}
			else if(patr.trim()=="031"){
				messageBoxJust("031","","");
			}
			else if(patr.trim()=="032"){
				messageBoxJust("032","","");
			}
			else if(patr.trim()=="032"||(patr.trim()=="046")){
				messageBoxCir(data.trim(),"","");
			}
			else if(patr.trim()=="fail"){
				messageBoxJust("190","","");
				$("#CT03DOCNO").prop("disabled",false);
			}
			else
				{

			var url2 = "Handler_PatronDetails?GL14PATR=" + GL14PATR + "&action='Charging'";
		
			$.get(url2,function(data)
					{
				swal.close();
				var details = data.trim().split("/n");
				$("#onloan").text(details[5]);
				$("#itemoverdue").text(details[6]);
				$("#itemreserved").text(details[7]);
				$("#fines").text(details[8]);
				//$("#totalfines").text(details[9]);
				//$('#table-charging tr:eq(0) > td:eq(6)').text("Circulated");
				
				//if(patr.trim()=="ok"){
				$(".statusid").html('<strong>Circulated</strong>');
				var status = $('.statusid');
				status.removeClass('statusid');
				$('td.dischargeDate').removeClass('dischargeDate');
				$('td.dischargeTime').removeClass('dischargeTime');
				
				var el = $('.status');
				el.addClass('newlist');
				el.removeClass('status');
				
				$(".newlist").css('background', 'LightGreen');
				document.getElementById("CT03DOCNO").value='';
				//$("#charging").prop("disabled",true);
				$("#printSlip").prop("disabled",false);
				$("#CT03DOCNO").prop("disabled",false);
				var total = $(".total").text();
				total = parseInt(total) + 1;
				$(".total").text(total);
				$(".title").text("");
				 if ($('.printSlip').is(":checked"))
					{

				  // it is checked
				//var url = "Print_Slip?GL14PATR=" + GL14PATR + "&CT03DOCNO="+CT03DOCNO+"&action=Charging";
				//12112019
				var url = "GeneratePreview?GL14PATR=" + GL14PATR + "&CT03DOCNO="+"'"+CT03DOCNO+"'&action=Charging";
				
					window.open(url,"", "width=283,height=500,toolbar=0").print();
				}	
				//}
				// Put the object into storage
				 	JSON.parse('""');
					var chargePrint={GL14PATR:GL14PATR,CT03DOCNO:CT03DOCNO};
					localStorage.setItem('chargePrint', JSON.stringify(chargePrint));
					 $("#CT03DOCNO").focus();  
				
				});	
					
				}
		});
	}
	
		
	
	  
	  var b = performance.now();
	  console.log(`Call to doSomething took ${b - a} milliseconds.`);
}

/*function access() {
	  var b = document.getElementById("GL14PATR").value;
	  var a= "${pageContext.servletContext.contextPath }/PhotoServlet?GL14PATR="+b;
	  $.get(a,function(data){
		  alert(data);
		 if(data!=null && data!=""){
			 document.getElementById("imgtest3").src=a;
		 }
		});
}*/

function reload(action){
	
	$('input.GL14PATR').keyup(function() {
	  this.value = this.value.toUpperCase();
	});

	access("");
	var tables =  $('#table-charging').DataTable();
	tables.rows().remove().draw();
	 $("#CT03DOCNO").val("");
		$(".GL14NAME").html("");
		$(".GL14CATE").html("");
		$(".GL14STAT").html("");
		$(".GL14MEMDATE").html("");
		$(".GL14EXPDATE").html("");
		$(".GL14BRANCH").html("");
		$(".department").html("");
		
		
		$("#onloan").text("0");
		$("#itemoverdue").text("0");
		$("#itemreserved").text("0");
		$("#itemonhold").text("0");
		$("#fines").text("0.00");
		$(".total").text('0');
		$('#title').html('');
		//$("#totalfines").text("0.00");

	if(action == "charge"){
		$(".title").text("");
		  $(".search").show();
		 // $(".no").hide();
		  $("#printSlip").show();
		  $("#CT03DOCNO").prop("disabled",true);
		  $(".checkboxs").hide();
		  $("#charging").show();
		  $("#discharging").hide();
		  $("#batchrenewbtn").hide();
		  $("#renewal2").hide();
		  $(".chargeaction").show();
		  $(".dischargeaction").hide();
		  $("#charging").prop("disabled",true);
		  $("#patrstatus").prop("disabled",true);
		  $("#override").prop("disabled",true);
		  $("#patrDetails").prop("disabled",true);
		  $("#patrLateReturn").prop("disabled",true);
		  $("#receipting").prop("disabled",true);
		  $("th.late, th.fine, th.returntime,  th.returndate, th.ndate, th.ntime, th.remarks").css('display', 'none');
	   }
	   else if(action == "renewal"){
		  // $(".no").hide();
	//	   $("#patrDet").remove();
		   $("#printSlip").show();
		   $("#CT03DOCNO").prop("disabled",false);
		   $(".checkboxs").hide();
		   $("#charging").hide();
		   $("#discharging").hide();
		   $("#batchrenewbtn").hide();
		   $("#renewal2").show();
		   $(".chargeaction").hide();
		   $(".dischargeaction").text("");
		   $(".dischargeaction").show();
		   $("#patrstatus").prop("disabled",true);
		   $("#patrDetails").prop("disabled",true);
		   $("#patrLateReturn").prop("disabled",true);
		   $("#receipting").prop("disabled",true);
		   $("#override").prop("disabled",true);
		   $(".search").show();
		   $("th.returntime,  th.returndate, th.remarks").css('display', 'none');
		   $("th.ndate, th.ntime,th.late, th.fine").css('display', '');
		   $(".title").text("");
	   } else if(action == "batchrenewal"){
		   //$(".no").show();
		   //$("#printSlip").hide();
		   $("#CT03DOCNO").prop("disabled",true);
		   $(".checkboxs").show();
		   $("#charging").hide();
		   $("#discharging").hide();
		   $("#batchrenewbtn").show();
		   $("#renewal2").hide();
		   $(".chargeaction").show();
		   $(".dischargeaction").text("");
		   $(".dischargeaction").hide();
		   $("#patrstatus").prop("disabled",true);
		   $("#patrDetails").prop("disabled",true);
		   $("#patrLateReturn").prop("disabled",true);
		   $("#receipting").prop("disabled",true);
		   $("#override").prop("disabled",true);
		   $(".search").show();
		   $("th.returntime,  th.returndate").css('display', 'none');
		   $("th.ndate, th.ntime,th.late, th.fine, th.remarks").css('display', '');
		   $(".title").text("");
	   }
	   else{
		   //$(".no").hide();
		   $("#printSlip").show();
		   $("#CT03DOCNO").prop("disabled",false);
		   $(".checkboxs").hide();
		   $(".search").show();
		   $("#charging").hide();
		   $("#discharging").show();
		   $("#batchrenewbtn").hide();
		   $("#renewal2").hide();
		   $(".chargeaction").hide();
		   $(".dischargeaction").show();
		   $(".dischargeaction").text("");
		   $("#patrstatus").prop("disabled",true);
		   $("#patrDetails").prop("disabled",true);
		   $("#patrLateReturn").prop("disabled",true);
		   $("#receipting").prop("disabled",true);
		   $("#override").prop("disabled",false);
		   $("th.late, th.fine, th.returntime,  th.returndate").css('display', '');
		   $("th.ndate, th.ntime, th.remarks").css('display', 'none');
		   $(".title").text("");
	   }
}

//Delete template name 
function printResvNotification(alertMessage){
	var message = getAlertMessage("063", "", "");
	var CT03DOCNO =  $(".disCT03DOCNO").text();
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
		
				getMessage("064", "", "",alertMessage);
				   var details =JSON.parse(localStorage.getItem('dischargePrint'));
						//var url = "GeneratePreviewDocument?docno="+details.CT03DOCNO+"&action=reserved";
				   		//05112019
				   		console.log(details);
				   		var url = "GeneratePreview?docno="+CT03DOCNO+
				   		"&action=reserved";	
				   		
				   		
							window.open(url,"", "").print();

					},function(dismiss) {
						  if (dismiss === 'cancel') {
								getMessage("064", "", "", alertMessage);
						  }
						})
				}
				
//other one, later FIX
function printResvNotificationResv(alertMessage){
	var message = getAlertMessage("063", "", "");
	var CT03DOCNO =  $(".disCT03DOCNO").text();
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
		
				getMessageResv("064", "", "",alertMessage);
				   var details =JSON.parse(localStorage.getItem('dischargePrint'));
						//var url = "GeneratePreviewDocument?docno="+details.CT03DOCNO+"&action=reserved";
				   		//05112019
				   		console.log(details);
				   		var url = "GeneratePreview?docno="+CT03DOCNO+
				   		"&action=reserved";	
				   		
				   		
							window.open(url,"", "").print();

					},function(dismiss) {
						  if (dismiss === 'cancel') {
								getMessageResv("064", "", "", alertMessage);
						  }
						})
}


function executeCharge(error){
	swal.close();
	swal({
			title: 'Overwrite Date',
			allowEscapeKey: false,
			allowOutsideClick: false,
			onOpen: () => {
				swal.showLoading();
			}
		});
	
	var accession = $("#CT03DOCNO").val();
	var id = "";
	if($("#GL14PATR").val() === ""){
		id = $("#hiddenPatrId").val();
	}else{
		id = $("#GL14PATR").val();
	}
	
	
	var url = "";
	var expDate = "";
	var proceed = true;
	if(error=="038"){
		url = "AccessionServlet?name=" + accession + "&patronid=" + id + "&action=chkPatrCateMaxLoan";
	}else if(error=="026"){
		url = "AccessionServlet?name=" + accession + "&patronid=" + id + "&action=chkItemCategoryMaxLoan"
	}else if(error=="083"){
		expDate = $(".GL14EXPDATE").text();
		url = "AccessionServlet?name=" + accession + "&patronid=" + id + "&action=overrideMemExp&expDate="+expDate;
	}else{
		url = "AccessionServlet?name=" + accession + "&patronid=" + id + "&action=chkPatrCateMaxLoan"
	}
	
	  var total = $(".total").text();
	   total = parseInt(total) + 1;

	$.get(url,function(data){	
		var datas =data.split("\n");
		var chargeDateComp = datas[2];
		var dischargeDateComp = datas[4];
		if(chargeDateComp != null && chargeDateComp == dischargeDateComp){
			
			$("th.late, th.fine, th.rdate, th.rtime,th.ndate, th.ntime, th.remarks").css('display', 'none');
			$('#table-charging tr.status').remove();
			$(".title").text(datas[1]);
			$("#table-charging tbody").append('<tr style="background-color:white" class="status"><td>'+total+'</td><td>'+datas[0]+'</td>'+
						'<td>'+datas[2]+'</td><td>'+datas[3]+'</td>'+
						'<td class="dischargeDate">'+datas[4]+'</td><td class="dischargeTime">'+datas[5]+'</td>'+
						'<td class="statusid">Available</td></tr></tr>');
						
			$('#table-charging tr.odd').remove();
			
			swal("", "<strong>New due date is same as charge date. This charging transaction is not allowed.<strong>", "");
			
			
		}else{
			if(data.trim().includes("\n")){
			swal.close();
			$("#charging").prop("disabled",false);
			$("#printSlip").prop("disabled",false);
			$("#CT03DOCNO").prop("disabled",false);
			
			$("th.late, th.fine, th.rdate, th.rtime,th.ndate, th.ntime, th.remarks").css('display', 'none');
			$('#table-charging tr.status').remove();
			$(".title").text(datas[1]);
			$("#table-charging tbody").append('<tr style="background-color:white" class="status"><td>'+total+'</td><td>'+datas[0]+'</td>'+
						'<td>'+datas[2]+'</td><td>'+datas[3]+'</td>'+
						'<td class="dischargeDate">'+datas[4]+'</td><td class="dischargeTime">'+datas[5]+'</td>'+
						'<td class="statusid">Available</td></tr></tr>');
						
			$('#table-charging tr.odd').remove();
	
			if ($('input#fast').is(':checked') && proceed == true) {
				$("#charging").prop("disabled",true);
				$("#printSlip").prop("disabled",true);
				$("#CT03DOCNO").prop("disabled",true);
				charging()
			}
			
			}else{
				swal.close();
				var message = data.split(",");
				if(data.trim()=="038"){
					proceed = false;
					 overrideMaxLoan(data.trim());
				}else if(data.trim()=="026"){
					 overrideMaxLoan(data.trim());
				}else if(message[0].trim()=="023"){
					  messageBoxCir("023",message[2],"@patron");
					  //messageBoxCir("023",message[1] + "," + message[2],"@patron");
					  rightData = false;
				}else if(data.trim()== "068"){
					//messageBoxCir("068","","");
					  oerrideMemExp(data.trim(), null);
				}else if(data.trim()== "178"){
					  messageBoxCir("178","","");
				}else if(message[1]!= ""&&message[1]!="undefined"){
					  messageBoxCir(message[1],message[0],message[2]);
				}else{
					messageBoxCir(message[0].trim(),"","");
					rightData = false;
				}
			};
		}
		
	
	});
}
//Override Transaction with Max Loan 
function overrideMaxLoan(error){
	var message = getAlertMessage(error, "", "");
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

			executeCharge(error);
			 return false;
					},function(dismiss) {
						  if (dismiss === 'cancel') {
							  $("#CT03DOCNO").val("");
							  return false;
						  }
						})
}

//Delete template name 
function repayment(){
	var patrid = $(".dischargeaction").text();
	var popup;
/*	$('.swal2-confirm').click(function () {
		var url = window.location.hostname+":" +window.location.port+"/receipt-maintenance?patrid=";
		popup = window.open(url, '_blank');
		//$('#embeddedIframe').attr('src', "http://dev.paradigm.com.my/Receipting/receipting/receipting?patrid="+ patrid); 
		 swal.close();
    	});*/
	swal({   
		title: "Record payment now?",
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
			var protocol =  window.location.protocol;
			var path = window.location.pathname.split('/');
			var host = protocol + "//" + window.location.hostname+":" +location.port+"/web/guest/maintenance1";
			window.open(host+'?patrid=' + patrid, '_blank');
			//window.open('http://dev.paradigm.com.my/web/guest/receipt-maintenance?GL14PATR='+patrid, '_blank');
			//$('#embeddedIframe').attr('src', "http://dev.paradigm.com.my/Receipting/receipting/receipting?patrid="+ patrid); 
					},function(dismiss) {
						  if (dismiss === 'cancel') {
							  $("#CT03DOCNO").val("");
						  }
						})
}

function repaymentBatchRenewal(){
	var patrid = $("#GL14PATR").val();

	var popup;

	swal({   
		title: "Record payment now?",
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
			var protocol =  window.location.protocol;
			var path = window.location.pathname.split('/');
			var host = protocol + "//" + window.location.hostname+":" +location.port+"/web/guest/maintenance1";
			window.open(host+'?patrid=' + patrid, '_blank');
					},function(dismiss) {
						  if (dismiss === 'cancel') {
							  $("#CT03DOCNO").val("");
						  }
						})
}

function exceed(){
	var patrid = $(".dischargeaction").text();
	var message = getAlertMessage("074", "", "");
	var popup;
	$('.swal2-confirm').click(function () {
	//	popup = window.open('http://dev.paradigm.com.my/web/guest/receipt-maintenance?patrid='+ patrid, '_blank');
		var host = "https://" + window.location.hostname+":" +location.port+"/web/guest/maintenance1";
		window.open(host+'?patrid=' + patrid, '_blank');
		 swal.close();
    	});
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
			var protocol =  window.location.protocol;
			var path = window.location.pathname.split('/');
	
			//window.open('http://dev.paradigm.com.my/web/guest/receipt-maintenance?GL14PATR='+patrid, '_blank');
			var host = protocol + "//" + window.location.hostname+":" +location.port+"/web/guest/maintenance1";
			window.open(host+'?patrid=' + patrid, '_blank');
					},function(dismiss) {
						  if (dismiss === 'cancel') {
						
						  }
						})
}

//override membership expiry date

function overrideMemExpAlert(){
	   messageBoxCir("068", "", "");
}


function oerrideMemExp(error, GL14PATR){
	$("#charging").prop("disabled",true);
	$("#printSlip").prop("disabled",true);
//	swal("", "<strong>The patron's membership will expire before the due date<strong>", "");
//      setTimeout(function(){
    	  	var exp =  $(".GL14EXPDATE").text();
    		var message = getAlertMessage("083", exp, "@expdate");
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
					 swal({
						title: 'Overriding',
						allowEscapeKey: false,
						allowOutsideClick: false,
						onOpen: () => {
							swal.showLoading();
						}
					});
					if(error == "068R"){
						getAccession({ keyCode: 9, which: 9 }, "OK");
					}else{
						if(GL14PATR != null){
							batchrenew(GL14PATR, error);
						}else{
							executeCharge("083");
						}
					}
     						},function(dismiss) {
     							  if (dismiss === 'cancel') {
     								$("#charging").prop("disabled",true);
     							  }
     							})
//	  }
//      ,3000); //second message will appear 3 seconds later
}

var repay = function () {
	var patrid = $(".dischargeaction").text();
	//var message = getAlertMessage("074", "", "");
	var popup;
/*	$('.swal2-confirm').click(function () {
		var url = window.location.hostname+":" +window.location.port+"/receipt-maintenance?patrid=";
		popup = window.open(url, '_blank');
		//$('#embeddedIframe').attr('src', "http://dev.paradigm.com.my/Receipting/receipting/receipting?patrid="+ patrid); 
		 swal.close();
    	});*/
	swal({   
		title: "Record payment now?",
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
			var protocol =  window.location.protocol;
			var path = window.location.pathname.split('/');
			/*var url = window.location.hostname+":" +window.location.port+"/receipt-maintenance";
			window.open('http://dev.paradigm.com.my/web/guest/receipt-maintenance?GL14PATR='+patrid, '_blank');*/
			var host = protocol + "//" + window.location.hostname+":" +location.port+"/web/guest/maintenance1";
			window.open(host+'?patrid=' + patrid, '_blank');
			//$('#embeddedIframe').attr('src', "http://dev.paradigm.com.my/Receipting/receipting/receipting?patrid="+ patrid); 
					},function(dismiss) {
						  if (dismiss === 'cancel') {
							  $("#CT03DOCNO").val("");
						  }
						})
}

/*var print= function (alertMessage) {
	var message = getAlertMessage("063", "", "");

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
		
				getMessage("064", "", "",alertMessage);
				   var details =JSON.parse(localStorage.getItem('dischargePrint'));
						var url = "GeneratePreview?docno="+details.CT03DOCNO+"&action=reserved";
							
							window.open(url,"", "").print();

					},function(dismiss) {
						  if (dismiss === 'cancel') {
								getMessage("064", "", "", alertMessage);
						  }
						})
};*/
//UPDATED 23/10/2023 - now after pressing the reserve pop up it will show payment
function resvFine(alertMessage){
//	var message = getAlertMessage("063", "", "");
//
//	swal({   
//		title: message,
//		showCancelButton: true,
//		confirmButtonColor: '#3085d6', 
//		cancelButtonColor: '#d33',
//		confirmButtonText: 'Yes',
//		cancelButtonText: 'No',
//		confirmButtonClass: 'confirm-class',
//		cancelButtonClass: 'cancel-class',
//		closeOnConfirm: false,
//		closeOnCancel: false 
//		}).then(function() {
//				   var details =JSON.parse(localStorage.getItem('dischargePrint'));
//						var url = "GeneratePreview?docno="+details.CT03DOCNO+"&action=reserved";
//							
//							window.open(url,"", "");
//							 setTimeout(function() { repayment(); },2000);
//							
//
//					},function(dismiss) {
//						  if (dismiss === 'cancel') {
//								setTimeout(function() { repayment(); },2000);
//						  }
//						})
	setTimeout(function() { repayment(); },2000);
}

//Delete template name 
function resvFine11(alertMessage){
	var message = getAlertMessage("063", "", "");
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
			   var url = "Error_Page?GL79ERRCODE="+"064"+"" +
			    "&criteria=" + "" + "&label="+"";

			    $.ajax({
			          url: url,
			          success: function(result) {
			                swal('Info!',result.trim() + alertMessage, '' );
			                $('.swal2-confirm').click(function () {
			                	repayment();
			                	});
			          }
			    })
					},function(dismiss) {
						  if (dismiss === 'cancel') {
							   var url = "Error_Page?GL79ERRCODE="+"064"+"" +
							    "&criteria=" + "" + "&label="+"";

							    $.ajax({
							          url: url,
							          success: function(result) {
							                swal('Info!',result.trim() + alertMessage, '' );
							                $('.swal2-confirm').click(function () {
							                	repayment();
							                	});
							          }
							    })
						  }
						})
}

function messageBoxJust(code, criteria, label){
	  var numericValueCR = criteria.match(/\d+\.\d+|\d+/g);

	  var url = "Error_Page?GL79ERRCODE=" + (numericValueCR ? criteria : code) +
	            "&criteria=" + (numericValueCR ? code : criteria) +
	            "&label=" + label;
	            
	    $.ajax({
	          url: url,
	          success: function(result) {
	        	  var boldResult = result.bold();
	        	  swal({   
	        		  	text: boldResult,
		     			closeOnConfirm: false
		     			})
	          }
	    });
	}
	



