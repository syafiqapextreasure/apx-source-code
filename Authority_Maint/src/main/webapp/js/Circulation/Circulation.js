/**
 * 
 */

   
	  function printSlip(){
		 var action = $('input[name=actiontype]:checked' ).val();
		   if(action=="charge"){
			   var GL14PATR= document.getElementById("GL14PATR").value;
			   var rowInfo = new Array();
			   $("#table-charging").find("tbody tr").each(function () {
			        var ID = $(this).children(':first').text();
			        if($(this).find('td:eq(5)').text()=="Circulated"){
				            rowInfo.push($(this).find('td:eq(0)').text());
			        }
			    });

			   	var url = "Print_Slip?GL14PATR=" + GL14PATR + "&CT03DOCNO="+rowInfo.toString()+"&action=Charging";
			   	window.open(url,"", "width=283,height=500,toolbar=0");
				
		   }else if(action=="discharge"){

			   var GL14PATR= document.getElementById("hiddenPatrId").value;
			   var array = new Array();
	
			   $("#table-charging").find("tbody tr").each(function () {

			        if($(this).find('td:eq(5)').text()=="Discharged"){
			        	array.push($(this).find('td:eq(0)').text()+ "|" + 
			        				$(this).find('td:eq(3)').text()+ "|" +
			        				$(this).find('td:eq(4)').text()+ "|" +
			        				$(this).find('td:eq(1)').text() + "|" + 
			        				$(this).find('td:eq(2)').text() + "|" + 
			        				$(this).find('td:eq(6)').text() + "|" + 
			        				$(this).find('td:eq(7)').text());
			        }
			    });
			   
			   
			   var url = "Print_Slip?GL14PATR=" + GL14PATR + "&CT03DOCNO="+array.toString()+"&action=Discharging";
			   window.open(url,"", "width=283,height=500,toolbar=0");

		   }else{
			   var GL14PATR= document.getElementById("hiddenPatrId").value;
			   var array = new Array();
	
			   $("#table-charging").find("tbody tr").each(function () {

			        if($(this).find('td:eq(5)').text()=="Renewed"){
			        	array.push($(this).find('td:eq(0)').text()+ "|" + 
			        				$(this).find('td:eq(3)').text()+ "|" +
			        				$(this).find('td:eq(6)').text() + "|" + $(this).find('.late').val() + "|"
			        				+ $(this).find('.fine').val());
			        }
			    });
			   
					 var url = "Print_Slip?GL14PATR=" + GL14PATR + "&CT03DOCNO="+array.toString()+"&action=Renewal";

					window.open(url,"", "width=283,height=500,toolbar=0");
		   }

	   }
	   

   function discharging(){
	
			  var CT03DOCNO =  $(".disCT03DOCNO").text();
			  var GL14PATR= $('#hiddenPatrId').val();
			  var CT03DOCNO =  $(".disCT03DOCNO").text();
			  
			  if(!CT03DOCNO )
				  {
				  messageBox("020","","");
				  }
			
			  else
			  {
			
				var url = "Handler_Discharging?CT03DOCNO=" +   CT03DOCNO +"&GL14PATR=" +GL14PATR;
				$.get(url,function(datas){
				
					var message = datas.split(",");
					if((message[0]).trim()=="064"){
						printResvNotification(message[1]);
					}else if((message[0]).trim()=="resvFine"){
						resvFine(message[1]);
						//printResvNotification(message[1]);
					}else if(datas.trim()=="074"){
						  repayment();
					}
					
						
						var url2 = "Handler_PatronDetails?GL14PATR=" + $(".dischargeaction").text() + "&action=charge" ;
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
								$("#title").html("");
								
								var Cdate = $(".Cdate").text();
								var Ctime = $(".Ctime").text();
								var Ddate = $(".Ddate").text();
								var Dtime = $(".Dtime").text();
								var Lateby = $(".lateby").text();
								var fines = $(".fines").text();
								
								if ($('.printSlip').is(":checked"))
								{   var array = new Array();
					
								        	array.push(CT03DOCNO+ "|" + Ddate + "|" +
								        				Dtime + "|" + Cdate + "|" + Ctime + "|" + 
								        				Lateby + "|" + fines);
							
									var url = "Print_Slip?GL14PATR=" + GL14PATR + "&CT03DOCNO="+array.toString()+"&action=Discharging";

										window.open(url,"", "width=350,height=500,toolbar=0");
										
									
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
	
						
						});	
				
					});	
				
					
			  }
			 
			}
   
  
function getAccession(e){

			var code = e.keyCode || e.which;
						
			   var action = $('input[name=actiontype]:checked').val();
			   var accession = $("#CT03DOCNO").val();
			 
			   

				  if(action =="discharge" || action =="renewal"){
					  if (code == '9'||code == '13') {
					 localStorage.setItem("patrid", $('#disGL14PATR').text());
				    	var url = "Handler_PatronDetails?action="+action+"&CT03DOCNO=" + accession;
				  		
								$.get(url,function(data)
								{
									 var message = data.split(",");

									  if(!accession )
										  {
										  messageBox("020","","");
										  }else if(message[0].trim()=="066"){
											  messageBox("066",message[1],"@status");
											}
									  else
									  {
										
										$("#patrDet").html(data);
										 
									    	 var GL14PATR= $('#hiddenPatrId').val();
									    	  access(GL14PATR);
									    	 
									    	  var url = "";
									    	  if(action =="discharge"){
									    		  url="AccessionDischarge?name=" + accession + "&patronid=" + GL14PATR;
									    	  }else{
									    		  url="AccessionRenewals?name=" + accession + "&patronid=" + GL14PATR;
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
											  
											  if(data.trim()=="021"){
													messageBox("021","","");
												}
												else if(data.trim()=="022"){
													//messageBox("022",CT03DOCNO,"@docno");
													messageBox("022","","");
												}else if(data.trim()=="020"){
													//messageBox("022",CT03DOCNO,"@docno");
													messageBox("020","","");
												}else if(data.trim()=="032"){
													//messageBox("022",CT03DOCNO,"@docno");
													messageBox("032","","");
												}
												else if(data.trim()=="023"){
													//messageBox("022",CT03DOCNO,"@docno");
													messageBox("023","","");
												}
												else if(data.trim()=="027"){
													messageBox("027","","");
												}
												else if(data.trim()=="028"){
													messageBox("028","","");
												}
												else if(data.trim()=="029"){
													messageBox("029","","");
												}
												else if(data.trim()=="030"){
													messageBox("030","","");
												}
												else if(data.trim()=="033"){
													messageBox("033","","");
												}
												else if(data.trim()=="037"){
													messageBox("037","","");
												}
												else if(data.trim()=="038"){
													messageBox("038","","");
													renew = false;
												}
												else if(data.trim()=="039"){
													messageBox("039","","");
												}
												else if(data.trim()=="040"){
													messageBox("040","","");
												}
												else if(data.trim()=="041"){
													messageBox("041","","");
												}
												else if(data.trim()=="042"){
													messageBox("042","","");
												}
												else if(data.trim()=="044"){
													messageBox("044","","");
												}else if(data.trim()=="089"){
													messageBox("089","","");
												}else if(data.trim()=="020"){
												  messageBox("020","","");
											}else if(data.trim()== "081"){
												messageBox("081","","");
											}else if(data.trim()=="071"){
												  messageBox("071","","");
											}else if(message[0].trim()=="107"){
												  messageBox("107",message[1],"@patrid");
											}else if(data.trim()=="031"){
												  messageBox("031","","");
											}else if(data.trim()=="070"){
												  messageBox("070","","");
											}else if(data.trim()=="073"){
												  messageBox("073","","");
											}else if(data.trim()=="068"){
												  messageBox("068","","");
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
													
												if(action =="renewal"){

													$("th.returndate, th.returntime").css('display', 'none');
													$("th.ndate, th.ntime,th.late, th.fine").css('display', '');
												
													$('td.Rdate').removeClass('Rdate');
													$('td.Rtime').removeClass('Rtime');
													/*alert(datas[10].trim());
													if(datas[10]!=0.00){
														repayment();
													}*/
													 $("#title").html(datas[1]);
													 
													 var patron = localStorage.getItem("patrid");
														if(patron.trim()!=""){
															if(patron.trim()!= GL14PATR){
																$('#table-charging tbody').html('<tr style="background-color:white" class="status"><td class="disCT03DOCNO">'+datas[0]+'</td>'+
																		'<td class="Cdate">'+datas[2]+'</td>'+
																		'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
																		'<td>'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td><td class="Rdate">'+datas[7]+'<input type="hidden" class="renewDate" value='+datas[7]+'></td><td class="Rtime">'
																		+datas[8]+'<input type="hidden" class="renewTime" value='+datas[8]+'><td class="lateby">'+datas[9]+'<input type="hidden" class="late" value= "'+datas[9]+'"></td><td class="fines">'+datas[10]+
																		'<input type="hidden" class="fine" value= "'+datas[10]+'"></td></tr>');
															}else{
																$('#table-charging tbody').prepend('<tr style="background-color:white" class="status"><td class="disCT03DOCNO">'+datas[0]+'</td>'+
																		'<td class="Cdate">'+datas[2]+'</td>'+
																		'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
																		'<td>'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td><td class="Rdate">'+datas[7]+'<input type="hidden" class="renewDate" value='+datas[7]+'></td><td class="Rtime">'
																		+datas[8]+'<input type="hidden" class="renewTime" value='+datas[8]+'><td class="lateby">'+datas[9]+'<input type="hidden" class="late" value= "'+datas[9]+'"></td><td class="fines">'+datas[10]+
																		'<input type="hidden" class="fine" value= "'+datas[10]+'"></td></tr>');
													
															}
														}else{
															$('#table-charging tbody').prepend('<tr style="background-color:white" class="status"><td class="disCT03DOCNO">'+datas[0]+'</td>'+
																	'<td class="Cdate">'+datas[2]+'</td>'+
																	'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
																	'<td>'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td><td class="Rdate">'+datas[7]+'<input type="hidden" class="renewDate" value='+datas[7]+'></td><td class="Rtime">'
																	+datas[8]+'<input type="hidden" class="renewTime" value='+datas[8]+'><td class="lateby">'+datas[9]+'<input type="hidden" class="late" value= "'+datas[9]+'"></td><td class="fines">'+datas[10]+
																	'<input type="hidden" class="fine" value= "'+datas[10]+'"></td></tr>');
												
														}
													 
														$('#table-charging tr.odd').remove();
													 if ($('input#fast').is(':checked')) {
															renew()
														}
												}else{
													
													$("th.late, th.fine, th.returndate, th.returntime").css('display', '');
													$("th.ndate, th.ntime").css('display', 'none');
													 var currentDate = new Date();
													 var day = currentDate.getDate();
													 var month = currentDate.getMonth() + 1;
													 var year = currentDate.getFullYear();
													 var time = (currentDate.getHours() <10 ? '0' + currentDate.getHours() : currentDate.getHours())+":"+ (currentDate.getMinutes() <10 ? '0' + currentDate.getMinutes() : currentDate.getMinutes())+":" + (currentDate.getSeconds() <10 ? '0' + currentDate.getSeconds() : currentDate.getSeconds()); // => 51
													 $("#title").html(datas[1]);
													 /*	 $('#table-charging tbody').prepend('<tr style="background-color:white" class="status"><td class="disCT03DOCNO">'+datas[0]+'</td>'+
																'<td>'+datas[1]+'</td><td class="Cdate">'+datas[2]+'</td>'+
																'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
																'<td class="Dtime">'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td>' + 
																'<td class="lateby">'+datas[7]+'</td><td class="fines">'+datas[8]+'</td><td class="Rdate">'+(day <= 9 ? '0' + day : day)  + "/" + (month <= 9 ? '0' + month : month) + "/" + year +'</td><td class="Rtime">'+time+'</td></tr>');*/
													 
													 var patron = localStorage.getItem("patrid");
														if(patron.trim()!=""){
															if(patron.trim()!= GL14PATR){
																$('#table-charging tbody').html('<tr style="background-color:white" class="status"><td class="disCT03DOCNO">'+datas[0]+'</td>'+
																		'<td class="Cdate">'+datas[2]+'</td>'+
																		'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
																		'<td class="Dtime">'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td>' + 
																		'<td class="lateby">'+datas[7]+'</td><td class="fines">'+datas[8]+'</td><td class="Rdate">'+(day <= 9 ? '0' + day : day)  + "/" + (month <= 9 ? '0' + month : month) + "/" + year +'</td><td class="Rtime">'+time+'</td></tr>');
																
															}else{
																 $('#table-charging tbody').prepend('<tr style="background-color:white" class="status"><td class="disCT03DOCNO">'+datas[0]+'</td>'+
																			'<td class="Cdate">'+datas[2]+'</td>'+
																			'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
																			'<td class="Dtime">'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td>' + 
																			'<td class="lateby">'+datas[7]+'</td><td class="fines">'+datas[8]+'</td><td class="Rdate">'+(day <= 9 ? '0' + day : day)  + "/" + (month <= 9 ? '0' + month : month) + "/" + year +'</td><td class="Rtime">'+time+'</td></tr>');
																}
														}else{
														 $('#table-charging tbody').prepend('<tr style="background-color:white" class="status"><td class="disCT03DOCNO">'+datas[0]+'</td>'+
																	'<td class="Cdate">'+datas[2]+'</td>'+
																	'<td class="Ctime">'+datas[3]+'</td><td class="Ddate">'+datas[4]+'</td>'+
																	'<td class="Dtime">'+datas[5]+'</td><td class="statusid">'+datas[6]+'</td>' + 
																	'<td class="lateby">'+datas[7]+'</td><td class="fines">'+datas[8]+'</td><td class="Rdate">'+(day <= 9 ? '0' + day : day)  + "/" + (month <= 9 ? '0' + month : month) + "/" + year +'</td><td class="Rtime">'+time+'</td></tr>');
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
											}
											  //$("#CT03DOCNO").val("");
									    			});
					
									  }
									
								});
						
								event.preventDefault();

				    	}
				  	}else if(action=="charge"){
				  
				  		if (code == '9'||code == '13') {
				  			
				  		 var id = $("#GL14PATR").val();
				  		 var rightData = true;
				  		 
						$.get("AccessionServlet?name=" + accession + "&patronid=" + id + "&action=checkAll",function(data)
				    			{
							var message = data.split(",");
							if(message[0].trim()=="066"){
								  messageBox("066",message[1],"@status");
								  rightData = false;
							}else if(message[0].trim()=="085"){
								  messageBox("085","","");
								  rightData = false;
							}else if(data.trim()=="040"){
								  messageBox("040","","");
								  rightData = false;
							}else if(data.trim()=="082"){
								  messageBox("082","","");
								  rightData = false;
							}else if(data.trim()== "085"){
								messageBox("085","","");
								 rightData = false;
							}else if(message[0].trim()== "086"){
								 messageBox("086",message[1], "@patronName");
								 rightData = false;
							}else if(data.trim()=="038"){
								 overrideMaxLoan(data.trim());
							}else if(message[0].trim()=="087"){
								 messageBox("087",message[1], "@patronName");
								 rightData = false;
							}else if(message[0].trim()=="084"){
								 messageBox("084",message[1], "@itemcate");
								 rightData = false;
							}else if(data.trim()=="026"){
								 overrideMaxLoan(data.trim());
							}else if(data.trim()=="025"){
								  messageBox("025","","");
								  rightData = false;
							}else if(data.trim()=="039"){
								  messageBox("039","","");
							}else if(data.trim()=="032"){
								  messageBox("032","","");
								  rightData = false;
							}else if(data.trim()=="044"){
								  messageBox("044","","");
								  rightData = false;
							}else if(data.trim()=="047"){
								  messageBox("047","","");
								  rightData = false;
							}else if(data.trim()=="020"){
								  messageBox("020","","");
								  rightData = false;
							}else if(data.trim()=="021"){
								  messageBox("021","","");
								  rightData = false;
							}else if(message[0].trim()=="023"){
								  messageBox("023",message[1] + "," + message[2],"@patron");
								  rightData = false;
							}else if(data.trim()=="046"){
								  messageBox("046","","");
								  rightData = false;
							}else if(data.trim()=="067"){
								  messageBox("067","","");
								  rightData = false;
							}else if(data.trim()== "046"){
								messageBox("046","","");
								 rightData = false;
							}else if(data.trim()== "039"){
								messageBox("039","","");
								 rightData = false;
							}else if(data.trim()== "068"){
								//messageBox("068","","");
								  oerrideMemExp(data.trim());
							}
							else {
																var datas =data.split("\n");
								$("th.late, th.fine, th.rdate, th.rtime,th.ndate, th.ntime").css('display', 'none');
								$('#table-charging tr.status').remove();
								 $("#title").html(datas[1]);
								 $('#table-charging tbody').prepend('<tr style="background-color:white" class="status"><td>'+datas[0]+'</td>'+
											'<td>'+datas[2]+'</td>'+
											'<td>'+datas[3]+'</td><td class="dischargeDate">'+datas[4]+'</td>'+
											'<td>'+datas[5]+'</td><td class="statusid">Available</td></tr>');
								//charging(new_id);
								$('#table-charging tr.odd').remove();
							
								}
							
							if(!rightData){
								$("#CT03DOCNO").val("");
							}

							 //$("#CT03DOCNO").val("");
									if ($('input#fast').is(':checked')) {
										charging()
									}
				    			});
				    			
							/*	}
						});*/
						
				  	    }
				  
				  	}
			
}


function renew(){
		 
	  var CT03DOCNO =  $(".disCT03DOCNO").text();
			  var GL14PATR= $('#hiddenPatrId').val();
	
			  var renew = true;
				var url = "Handler_Renewal?CT03DOCNO=" + CT03DOCNO +"&GL14PATR=" +GL14PATR + "&Rdate="+ $(".renewDate").val()+"&Rtime="+ $(".renewTime").val();
				JSON.parse('""');
				var renewPrint={GL14PATR:GL14PATR,CT03DOCNO:CT03DOCNO, dueDate:$(".Ddate").text(),renewDate:$(".Rdate").text(),
							late:$(".lateby").text(),fine:$(".fines").text() };
				localStorage.setItem('renewPrint', JSON.stringify(renewPrint));
				$.get(url,function(data){
					var details=data.split(",");
					$(".Rdate").html("<strong>" + $(".Rdate").text()+ "</strong>");
					$(".Rtime").html("<strong>" + $(".Rtime").text() + "</strong>");
					var url2 = "Handler_PatronDetails?GL14PATR=" + GL14PATR + "&CT03DOCNO=" + CT03DOCNO + "&action=renewal";
					$.get(url2,function(data)
							{
						var details = data.trim().split(",");
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
								$("#title").html("");
								  $(".newlist").css('background', 'LightGreen');
								
								if($(".fines").text()!=0.00){
									var lateDetails={late:$('.lateby').text(),fine:$('.fines').text()};
									localStorage.setItem('lateDetails', JSON.stringify(lateDetails));
									var details =JSON.parse(localStorage.getItem('lateDetails'));
									repayment();
									$('.lateby').html(0 + "<input type='hidden' class='late' value='"+details.late+"'>");
									$('.fines').html((0.00).toFixed(2) + "<input type='hidden' class='fine' value='"+details.fine+"'>");
								}
								
								 if ($('.printSlip').is(":checked"))
									{
							//alert($(".late").val());
									 var details =JSON.parse(localStorage.getItem('renewPrint'));
									 var array = new Array();
									 array.push(CT03DOCNO+ "|" + 
												details.dueDate+ "|" +
												details.renewDate + "|" + 
												details.late + "|" + details.fine);
									 	// it is checked
									 var url = "Print_Slip?GL14PATR=" + GL14PATR + "&CT03DOCNO="+array.toString()+"&action=Renewal";

									window.open(url,"", "width=283,height=500,toolbar=0");
								}	
								
								 $('.lateby').removeClass('lateby');
								 $('.fines').removeClass('fines');
							     document.getElementById("CT03DOCNO").value='';
					
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

$(document).ready(function() {
	 $("[title]").tooltip();
	  $("#charging").show();
	  $("#discharging").hide();
	  $("#renewal2").hide();
	  //$(".dischargeaction").hide();

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
			
		   
		   var accession  = $('#table-charging tr.status td:eq(0)').text();
		   var chargeDate  = $('#table-charging tr.status td:eq(2)').text();
		   var chargeTime  = $('#table-charging tr.status td:eq(3)').text();
		   
		   var url = "Modal_OverrideDate?action=" + action + "&chargeDate=" + chargeDate + 
  			"&chargeTime=" + chargeTime;
		   $.get(url,function(data){
			
				 $(".override").html(data);
			   });
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
			var popup;
			var host = "http://" + window.location.hostname + "/web/guest/receipt-maintenance";
			/*popup = window.open(host+"?patrid="+ GL14PATR, '');*/
			popup = window.open(host + '?patrid='+ GL14PATR, '_blank');
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
		   }
		   else{
			   reload(action);
		   }
		   event.preventDefault();
		   });
 

	$("#GL14PATR").on('keyup', function(e){
			
			  if (!this.value) {
				
			       reload("charge");
			       access(this.value);
			    }else{
			    	   $("#patrstatus").prop("disabled",false);
					   $("#patrDetails").prop("disabled",false);
					   $("#patrLateReturn").prop("disabled",false);
					   $("#receipting").prop("disabled",false);
					   $("#override").prop("disabled",false);
			    }
				
			});
		
		
		
		$("#GL14PATR").focus(function(){

			setTimeout(function(){ 
				var patron = $("#GL14PATR").val();
			 if (patron.length < 1){
	
				  		var url = "Handler_PatronDetails?action=smartcardreader";
				  		
						$.get(url,function(data)
						{
							if(data.trim()!="null"){
							$("#GL14PATR").val(data);
							}
						});	
						event.preventDefault();
			 	}
			 }, 5000);
			});

		$("#GL14PATR").on('keydown', function(e)
		{ 	
			
			var code = e.keyCode || e.which;			

		  var action = $('input[name=actiontype]:checked').val();
		 
		  if(action =="charge"){
				if (code == '9'||code == '13') {
					reload("charge");
					var rightData = true;
					 var GL14PATR = this.value;
					 access(GL14PATR);
			  		var url = "Handler_PatronDetails?action="+action+"&GL14PATR=" + GL14PATR;
			  		
					$.get(url,function(data)
							{
						if(action =="charge"){
							var message = (data.trim()).split(",");
						if(data.trim()== "033"){
							messageBox("033","","");
							rightData = false;
							}else if(data.trim()== "036"){
							messageBox("036","","");
							rightData = false;
							}else if(message[0]== "080"){
							messageBox("080",message[1], "@status");
							}else if(data.trim()== "082"){
							messageBox("082","", "");
							rightData = false;
							}else if(data.trim()== "050"){
							messageBox("050","","");
							rightData = false;
							}else if(data.trim()== "032"){
								messageBox("032","","");
								rightData = false;
							}else if(data.trim()== "040"){
								messageBox("040","","");
								rightData = false;
							}else if(data.trim()== "046"){
								messageBox("046","","");
								rightData = false;
							}else{
								var details = data.trim().split("/n");

								$(".GL14NAME").append(' <span>'+details[0]+'</span>');
								$(".GL14CATE").append(' <span>'+details[1]+'</span>');
								$(".GL14STAT").append(' <span>'+details[2]+'</span>');
								$(".GL14MEMDATE").append(' <span>'+details[3]+'</span>');
								$(".GL14EXPDATE").append(' <span>'+details[4]+'</span>');
								$(".GL14BRANCH").append(' <span>'+details[10]+'</span>');
								$("#onloan").text(details[5]);
								$("#itemoverdue").text(details[6]);
								$("#itemreserved").text(details[7]);
								$("#fines").text(details[8]);
								//$("#totalfines").text(details[9]);

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
								document.getElementById("CT03DOCNO").focus();

						}
						if(!rightData){
							
							$("#GL14PATR").val("");
							access("");
						}
						}
					/*	if(data.trim()==="033" && action =="charge"){
							
							messageBox("033","","");
							}
						else if(data.trim()==="036"){
							
							messageBox("036","","");
							}
						else if(action == "charge")
							{
						var details = data.trim().split("/n");

						$(".GL14NAME").append(' <span>'+details[0]+'</span>');
						$(".GL14CATE").append(' <span>'+details[1]+'</span>');
						$(".GL14STAT").append(' <span>'+details[2]+'</span>');
						$(".GL14MEMDATE").append(' <span>'+details[3]+'</span>');
						$(".GL14EXPDATE").append(' <span>'+details[4]+'</span>');
						$(".GL14BRANCH").append(' <span>'+details[10]+'</span>');
						$("#onloan").text(details[5]);
						$("#itemoverdue").text(details[6]);
						$("#itemreserved").text(details[7]);
						$("#fines").text(details[8]);
						$("#totalfines").text(details[9]);*/
						
						
					/*	var url2 = "Handler_Charging?GL14PATR=" + GL14PATR;
						$.get(url2,function(data){
							$("#table-charging").html(data);
							
						
						});*/
							//}
						
				
					});	
					event.preventDefault();
				

				}

		  }
		  
		 
		});
	   
	  
});


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


function charging(){
	var a = performance.now();

	/*var row = $(this).closest('#table-charging').find(' tbody tr.status:eq(0)').attr('id');
	alert($('#table-charging tr:eq(0) > td:eq(6)').text());*/
	  var CT03DOCNO = document.getElementById("CT03DOCNO").value;
	  var dischargeDate = $('#table-charging').find('td.dischargeDate').text();
	
	  var GL14PATR= document.getElementById("GL14PATR").value;
	  var Ddate = dischargeDate.split("/");
	  var date = Ddate[0];
	  var month = Ddate[1];
	  var year = Ddate[2];
	  var url = "Handler_Circulation?CT03DOCNO=" + CT03DOCNO +"&GL14PATR=" +GL14PATR + "&Date=" + year+month+date;
		
		$.get(url,function(patr){
			
			if(patr.trim()==="021"){
				messageBox("021","","");
			}
			else if(patr.trim()=="022"){
				//messageBox("022",CT03DOCNO,"@docno");
				messageBox("022","","");
			}
			else if(patr.trim()=="027"){
				messageBox("027","","");
			}
			else if(patr.trim()=="028"){
				messageBox("028","","");
			}
			else if(patr.trim()=="029"){
				messageBox("029","","");
			}
			else if(patr.trim()=="030"){
				messageBox("030","","");
			}
			else if(patr.trim()=="031"){
				messageBox("031","","");
			}
			else if(patr.trim()=="032"){
				messageBox("032","","");
			}
			else if(patr.trim()=="032"||(patr.trim()=="046")){
				messageBox(data.trim(),"","");
			}
			else
				{
	

			var url2 = "Handler_PatronDetails?GL14PATR=" + GL14PATR + "&action='Charging'";
		
			$.get(url2,function(data)
					{
				
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
				
				var el = $('.status');
				el.addClass('newlist');
				el.removeClass('status');
				
				  $(".newlist").css('background', 'LightGreen');
				document.getElementById("CT03DOCNO").value='';
				$("#title").html("");
				 if ($('.printSlip').is(":checked"))
					{

				  // it is checked
				var url = "Print_Slip?GL14PATR=" + GL14PATR + "&CT03DOCNO="+CT03DOCNO+"&action=Charging";

					window.open(url,"", "width=283,height=500,toolbar=0");
				}	
				//}
				// Put the object into storage
				 	JSON.parse('""');
					var chargePrint={GL14PATR:GL14PATR,CT03DOCNO:CT03DOCNO};
					localStorage.setItem('chargePrint', JSON.stringify(chargePrint));
				
				});	
					
				}
		
			
		});	
	
	  
	  var b = performance.now();
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
		
		
		$("#onloan").text("0");
		$("#itemoverdue").text("0");
		$("#itemreserved").text("0");
		$("#itemonhold").text("0");
		$("#fines").text("0.00");
		$('#title').html('');
		//$("#totalfines").text("0.00");

	if(action == "charge"){

		  $(".search").show();
		  $("#charging").show();
		  $("#discharging").hide();
		  $("#renewal2").hide();
		  $(".chargeaction").show();
		  $(".dischargeaction").hide();
		  $("#patrstatus").prop("disabled",true);
		  $("#override").prop("disabled",true);
		  $("#patrDetails").prop("disabled",true);
		  $("#patrLateReturn").prop("disabled",true);
		  $("#receipting").prop("disabled",true);
		  $("th.late, th.fine, th.returntime,  th.returndate, th.ndate, th.ntime").css('display', 'none');
	   }
	   else if(action == "renewal"){
		   $("#charging").hide();
		   $("#discharging").hide();
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
		   $("th.returntime,  th.returndate").css('display', 'none');
		   $("th.ndate, th.ntime,th.late, th.fine").css('display', '');
	   }
	   else{
		   $(".search").show();
		   $("#charging").hide();
		   $("#discharging").show();
		   $("#renewal2").hide();
		   $(".chargeaction").hide();
		   $(".dischargeaction").show();
		   $(".dischargeaction").text("");
		   $("#patrstatus").prop("disabled",true);
		   $("#patrDetails").prop("disabled",true);
		   $("#patrLateReturn").prop("disabled",true);
		   $("#receipting").prop("disabled",true);
		   $("#override").prop("disabled",true);
		   $("th.late, th.fine, th.returntime,  th.returndate").css('display', '');
		   $("th.ndate, th.ntime").css('display', 'none');
	   }
	//event.preventDefault();

}

//Delete template name 
function printResvNotification(alertMessage){
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
						var url = "GeneratePreviewDocument?docno="+details.CT03DOCNO+"&action=reserved";
							
							window.open(url,"", "");

					},function(dismiss) {
						  if (dismiss === 'cancel') {
								getMessage("064", "", "", alertMessage);
						  }
						})
}


function executeCharge(error){

	var accession = $("#CT03DOCNO").val();
	var id = $("#GL14PATR").val();
	var url = "";
	var expDate = "";
	if(error=="038"){
		url = "AccessionServlet?name=" + accession + "&patronid=" + id + "&action=chkPatrCateMaxLoan";
	}else if(error=="026"){
		url = "AccessionServlet?name=" + accession + "&patronid=" + id + "&action=chkItemCategoryMaxLoan"
	}else if(error=="083"){
		expDate = $(".GL14EXPDATE").text();
		/*var from = expDate.split("/");
		var f = new Date(from[2],from[1],from[0]);
		var month = String(f.getMonth());
		var day = String(f.getDate()-1);
		var year = String(f.getFullYear());
		expDate = day+"/"+month+"/"+year;*/
		url = "AccessionServlet?name=" + accession + "&patronid=" + id + "&action=overrideMemExp&expDate="+expDate;
	}else{
		url = "AccessionServlet?name=" + accession + "&patronid=" + id + "&action=chkPatrCateMaxLoan"
	}

	$.get(url,function(data)
			{
		var message = data.split(",");
		
		if(message[0].trim()=="066"){
			  messageBox("066",message[1],"@status");
		}else if(data.trim()=="040"){
			  messageBox("040","","");
		}
		else if(data.trim()=="026"){
			overrideMaxLoan(data.trim());
		}else if(data.trim()=="025"){
			  messageBox("025","","");
		}else if(data.trim()=="039"){
			  messageBox("039","","");
		}else if(data.trim()=="032"){
			  messageBox("032","","");
		}else if(data.trim()=="068"){
			 oerrideMemExp(data.trim());	
		}else if(data.trim()=="044"){
			  messageBox("044","","");
		}else if(message[0].trim()=="084"){
			  messageBox("084",message[1],"@itemcate");
		}else if(data.trim()=="047"){
			  messageBox("047","","");
		}else if(data.trim()=="020"){
			  messageBox("020","","");
		}else if(data.trim()=="021"){
			  messageBox("021","","");
		}else if(data.trim()=="023"){
			  messageBox("023","","");
		}else if(data.trim()=="046"){
			  messageBox("046","","");
		}else if(data.trim()=="066"){
			  messageBox("066","","");
		}else if(data.trim()=="067"){
			  messageBox("067","","");
		}
		else {
		
			var datas =data.split("\n");
			$('#table-charging tr.status').remove();
		   $("#title").text(datas[1]);
			$("#table-charging tbody").prepend('<tr style="background-color:white" class="status"><td>'+datas[0]+'</td>'+
						'<td>'+datas[2]+'</td><td>'+datas[3]+'</td>'+
						'<td class="dischargeDate">'+datas[4]+'</td><td>'+datas[5]+'</td>'+
						'<td class="statusid">Available</td></tr></tr>');
	
			//charging(new_id);
			$('#table-charging tr.odd').remove();
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
		/*	 var table = tbody.length ? tbody : $('#table-charging');
			 alert("sd");*/
		
			executeCharge(error);
	    			
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
			var path = window.location.pathname.split('/');
			var host = "http://" + window.location.hostname+":" +location.port+"/web/guest/maintenance1";
			window.open(host+'?patrid=' + patrid, '_blank');
			//window.open('http://dev.paradigm.com.my/web/guest/receipt-maintenance?GL14PATR='+patrid, '_blank');
			//$('#embeddedIframe').attr('src', "http://dev.paradigm.com.my/Receipting/receipting/receipting?patrid="+ patrid); 
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
		var host = "http://" + window.location.hostname+":" +location.port+"/web/guest/maintenance1";
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
			var path = window.location.pathname.split('/');
	
			//window.open('http://dev.paradigm.com.my/web/guest/receipt-maintenance?GL14PATR='+patrid, '_blank');
			var host = "http://" + window.location.hostname+":" +location.port+"/web/guest/maintenance1";
			window.open(host+'?patrid=' + patrid, '_blank');
					},function(dismiss) {
						  if (dismiss === 'cancel') {
						
						  }
						})
}

//override membership expiry date

function overrideMemExpAlert(){
	   messageBox("068", "", "");
}


function oerrideMemExp(error){
	swal("", "The patron's membership will expire before the due date", "");
      setTimeout(function(){
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
     				executeCharge("083");
     						},function(dismiss) {
     							  if (dismiss === 'cancel') {
     							
     							  }
     							})
	  }
      ,3000); //second message will appear 3 seconds later
	
	

/*	overrideMemExpAlert().
	  then(function() {
		  alert("sddd");
			var message = getAlertMessage("083", "", "");
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
       				alert("eer");
       						},function(dismiss) {
       							  if (dismiss === 'cancel') {
       								alert("eer34");
       							
       							  }
       							})
	  })*/
/*	 var url = "Error_Page?GL79ERRCODE="+error+"" +
    "&criteria=" + "" + "&label="+""+"";
    $.ajax({
          url: url,
          success: function(result) {
       	   swal('',result.trim(), '' );
       	   $(".swal2-confirm").click(function(){
       		   var expDate = $(".GL14EXPDATE").text();
       			var message = getAlertMessage("083",expDate, "@expdate");
     
       			$('.swal2-confirm').click(function () {
       				var id = $("#GL14PATR").val();
       				var accession = $("#CT03DOCNO").val();
       				executeCharge("083");
       				
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
       						},function(dismiss) {
       							  if (dismiss === 'cancel') {
       							
       							  }
       							})
       	   })
          }
    });*/
	
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
			var path = window.location.pathname.split('/');
			/*var url = window.location.hostname+":" +window.location.port+"/receipt-maintenance";
			window.open('http://dev.paradigm.com.my/web/guest/receipt-maintenance?GL14PATR='+patrid, '_blank');*/
			var host = "http://" + window.location.hostname+":" +location.port+"/web/guest/maintenance1";
			window.open(host+'?patrid=' + patrid, '_blank');
			//$('#embeddedIframe').attr('src', "http://dev.paradigm.com.my/Receipting/receipting/receipting?patrid="+ patrid); 
					},function(dismiss) {
						  if (dismiss === 'cancel') {
							  $("#CT03DOCNO").val("");
						  }
						})
}

var print= function (alertMessage) {
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
						var url = "GeneratePreviewDocument?docno="+details.CT03DOCNO+"&action=reserved";
							
							window.open(url,"", "");

					},function(dismiss) {
						  if (dismiss === 'cancel') {
								getMessage("064", "", "", alertMessage);
						  }
						})
};
function resvFine(alertMessage){
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
						var url = "GeneratePreviewDocument?docno="+details.CT03DOCNO+"&action=reserved";
							
							window.open(url,"", "");
							 setTimeout(function() { repayment(); },2000);
							

					},function(dismiss) {
						  if (dismiss === 'cancel') {
								 setTimeout(function() { repayment(); },2000);
						  }
						})
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

