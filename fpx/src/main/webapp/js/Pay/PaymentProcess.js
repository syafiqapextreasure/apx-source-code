$(document).ready(function(){
	
	var today = new Date();
	
	$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		return results[1] || 0;
	}
	
	var token =  $('input[name="csrfToken"]').attr('value')
	
	$.get('Getbills', {
	        	bil : decodeURI($.urlParam('bil'))
		 	}, function(responseJson) {
			if(responseJson != null){
				

				
				$.each(responseJson, function(key,value) {

					var subsysId = value['Subsysid'];
					var password = value['Subpassword'];
					var orderNo = value['Orderno'];
					var description = value['Description'];
					var txnTime = moment(today).format("YYYY-MM-DD HH:mm:ss");
					var amount = value['Amount'];
					var status = value['Status'];
					
					/*var subsysId = "PPK";
					var password = "pPk@2022WLm";
					var orderNo = "PPK0000000620";
					var description = "PPK202308030000000620";
					var txnTime = "2023-08-03 16:37:54";
					var amount = "2.00";
					var status = "";*/
					


					
					//var jsonString = JSON.stringify(json);
					
					var json = {"payload": {
							"subsysId": subsysId,
							"password": password,
							"orderNo": orderNo,
							"description": description,
							"txnTime": txnTime,
							"feeCode": "",
							"amount": amount
					}};
					
					
					var json2 = {
						"payload": {
							"subsysId": subsysId,
							"password": password,
							"orderNo": orderNo,
							"amount": amount
						}
					};
					
					
					if(status != 'SUCCESS'){
						
						
						//////check if pay cash or not
						$.get('GetUPDREFF', {
							orderNo: orderNo
						}, function(responseJson) {  
							if (responseJson != null) {
								
								
								var sVal = responseJson;
								var iNum = parseInt(sVal);
								
								if(iNum > 0 ){
									alert("Payment has been made");
									window.close();
								}else if(iNum == 0){
									
									$.get('PostBill', {
							 data: JSON.stringify(json),
					 	}, function(responseJson) {
							if(responseJson != null){
										
								var json = JSON.parse(responseJson); // create an object with the key of the array
								
								///alert(json+"json postbill")
								
								$.get('PostPayStatus', {
									pOrderNo: orderNo,
								}, function(responseJson) {
									
									////alert(responseJson+"responseJson PostPayStatus")
									
									if (responseJson != null) {
										
									}

								});
								
							       $( "input[name='token']" ).val(json.token);
								   $("#submitBtn").click(); 
								   
								   
								   
								   
								  var testTimer = json.expires_in;
							
								  var testTimerTotal = testTimer;
								  
								  
								  $.get('PayInquiry', {
											data: JSON.stringify(json2),
											testTimerTotal : testTimerTotal
								  }, function(responseJson) {  
										if (responseJson != null) {
											//alert("MASUK ENQUIRY (IF STATUS = PENDING terus tukar status to FAILED)");
										}

								  });
		
								  //var testTimerTotal = testTimer * 1000;
									
									/*let timer = setTimeout(function() {
										// code to be executed after a delay
										
										$.get('PayInquiry', {
											data: JSON.stringify(json2),
										}, function(responseJson) {
											if (responseJson != null) {
												alert("MASUK ENQUIRY (IF STATUS = PENDING terus tukar status to FAILED)");
											}

										});

									}, testTimerTotal); // 5000 milliseconds = 5 seconds

									function stopTimer() {
										clearTimeout(timer);
									}*/
	
							}
					 	
					 	});
								}
								
							}

						});

					}else{
						alert("Payment already Success");
						window.close();
					}
					
					
					
					
					


					
				});
			}
	});

	
	


					
});