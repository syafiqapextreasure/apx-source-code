$(document).ready(function() {

	$("#stopDate2").prop('disabled', 'disabled');
	
	setTimeout(function(){
		$.get('GetFreqType', {
			  freq : $("#frequency1").val(),
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						$("#freqTime").val(value['freqTime']);
						$("#freqType").val(value['freqType']);
					});
				}
			});
	}, 1000);
	
	$('#startDate2').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	}).on('change', function(e) {

        // Revalidate the date field
		setTimeout(function(){
			var startDate = $("#startDate2").val();
			var qty = parseInt($("#numberOfIssues2").val());
			var issue = parseInt($("#copies2").val());
			var freqType = $("#freqType").val();
			var freqTime = parseInt($("#freqTime").val());
			
			endDate(startDate, qty, issue, freqType, freqTime);
			generateTableCardex();

		}, 1000);
    });
	
	//In use frequency
	$("#frequency").change(function(){
		
		setTimeout(function(){
			var startDate = $("#startDate2").val();
			var qty = parseInt($("#numberOfIssues2").val());
			var issue = parseInt($("#copies2").val());
			var freqType = $("#freqType").val();
			var freqTime = parseInt($("#freqTime").val());
			
			endDate(startDate, qty, issue, freqType, freqTime);
			generateTableCardex();

		}, 1000);
	})
	
	//Number of Copies
	$("#copies2").blur(function(){
		setTimeout(function(){
			var startDate = $("#startDate2").val();
			var qty = parseInt($("#numberOfIssues2").val());
			var issue = parseInt($("#copies2").val());
			var freqType = $("#freqType").val();
			var freqTime = parseInt($("#freqTime").val());
			
			endDate(startDate, qty, issue, freqType, freqTime);
			generateTableCardex();

		}, 1000);
	});
	
	//Number of Issues
	$("#numberOfIssues2").blur(function(){
		setTimeout(function(){
			var startDate = $("#startDate2").val();
			var qty = parseInt($("#numberOfIssues2").val());
			var issue = parseInt($("#copies2").val());
			var freqType = $("#freqType").val();
			var freqTime = parseInt($("#freqTime").val());
			
			endDate(startDate, qty, issue, freqType, freqTime);
			generateTableCardex();

		}, 1000);
	});
	
	
	////onchange select frequency
	$('#frequency1').on('change', function(event, obj) {
		 var freq = $("#frequency1").val();

		  //get frequency detail
		  $.get('GetFreqType', {
			  freq : freq
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						$("#freqTime").val(value['freqTime']);
						$("#freqType").val(value['freqType']);
					});
				}
			});
	});
	
	///////////////FUNCTION CALCULATE END DATE
	function endDate(startDate, qty, issue, freqType, freqTime){
		
		var enddate;
		var timestotal;
		var finalDate;
		
		issue = parseInt(issue);
		
		if(freqType == "M"){
			timestotal = ((issue-1)* (freqTime));
			//alert("timestotal" +timestotal);
		}else if(freqType == 'YYYY' || freqType == 'Y'){
			if(freqTime=='1'){
				timestotal = issue * freqTime;
				timestotal = timestotal-1;
			}else if(freqTime > 1){
				timestotal = ((issue-1)* (freqTime));
			}
		}else if(freqType == "WW"){
			//alert(freqTime +"freqTime");
			if(freqTime=='1'){
				timestotal = issue * freqTime;
				timestotal = timestotal-1;
			}else if(freqTime > 1){
				if(freqTime == '200'){
					issue = issue;
					//timestotal = issue-1; 
				}else{
					timestotal = ((issue-1) * (freqTime));
					//timestotal = timestotal-1;
					timestotal = timestotal;
				}
				
			}
		}else if(freqType == "D" || freqType == "M"){
			if(freqTime=='1'){
				timestotal = ((issue-1) * (freqTime));
			}else if(freqTime > 1){
				timestotal = ((issue-1) * (freqTime));
			}
		}

		var totaltable = qty * issue;

		//alert("freqType "+freqType);
		if(freqType == 'YYYY' || freqType == 'Y'){
			enddate = moment(startDate, "DD-MM-YYYY").add('years', timestotal);
			//alert("enddate" +enddate.format('YYYY'));
			var day = enddate.format('DD');
			var month = enddate.format('MM');
			var year = enddate.format('YYYY');
			$("#stopDate2").val(day + '/' + month + '/' + year);
			//alert(day + '/' + month + '/' + year);
		}else if(freqType == 'M'){
			enddate = moment(startDate, "DD-MM-YYYY").add('month', timestotal);
			var day = enddate.format('DD');
			var month = enddate.format('MM');
			var year = enddate.format('YYYY');
			$("#stopDate2").val(day + '/' + month + '/' + year);
			//alert(day + '/' + month + '/' + year);
		}else if(freqType == 'WW'){
			
	
			if(freqTime == '200'){
				//alert( startDate.format('DD') + "userChosenDate");
				var userChosenDate = moment(startDate, "DD/MM/YYYY").format("DD"),
			    yourChosenPaymentDate = (Math.min(userChosenDate, 28) + 15) % 28,
			    firstPayment = moment(startDate, "DD/MM/YYYY").date(Math.min(userChosenDate, yourChosenPaymentDate)),
			    secondPayment = moment(startDate, "DD/MM/YYYY").date(Math.max(userChosenDate, yourChosenPaymentDate)),
			    format = "DD/MM/YYYY";
				
			    
			    var addMonth = issue - (Math.floor(issue/2)+ 1);
			    
			    if(issue % 2 == 0){
			    	enddate = secondPayment.add('month', addMonth).format(format);
			    	//alert("1," +secondPayment.add('month', addMonth).format(format));
			    }else{
			    	enddate = firstPayment.add('month', addMonth).format(format);
			    	//alert("2," +firstPayment.add('month',addMonth).format(format));
			    }
			    
				$("#stopDate2").val(enddate);
			    
			}else{
				enddate = moment(startDate, "DD-MM-YYYY").add('weeks', timestotal);
				var day = enddate.format('DD');
				var month = enddate.format('MM');
				var year = enddate.format('YYYY');
				$("#stopDate2").val(day + '/' + month + '/' + year);
			}
			
			//alert(day + '/' + month + '/' + year);
		}else if(freqType == 'D'){
			enddate = moment(startDate, "DD-MM-YYYY").add('day', timestotal);
			var day = enddate.format('DD');
			var month = enddate.format('MM');
			var year = enddate.format('YYYY');
			$("#stopDate2").val(day + '/' + month + '/' + year);
			//alert(day + '/' + month + '/' + year);
		} 
	}
	
	function generateTableCardex(){
		//alert("here");
		var firstVolume = $("#firstVolume2").val();
		var quantity = $("#numberOfIssues2").val();
		var noOfIssues = $("#copies2").val();
		var startDate = $("#startDate2").val();
		var pattern = $("#hidden-patternID").val();
		var firstPattern = $("#hidden-firstPattern").val();
		var freq = $('#frequency1').val();
		var freqType = $('#freqType').val();
		var freqTime = $('#freqTime').val();
		
		//var freqTime = parseInt($("#freqTime").val());
		
		//aleret(freqTime);
		
		var url = "Table_GenerateIssuesList?patternID=" +pattern+"&firstPattern=" +firstPattern+"&startDate="+startDate;
		url += "&quantity="+quantity+"&noOfIssues=" + noOfIssues+"&firstVolume="+firstVolume+"&freq="+freq+"&freqType="+freqType+"&freqTime="+freqTime;
		
		////alert("url" +url);
		
		/*var result = "NewFolder333".match(/[^\d]+|\d+/g);
		alert(result);*/
		
		////05082019
		//if(firstVolume == "" || quantity == "" || noOfIssues == "" || startDate== ""|| pattern=="" || firstPattern == "")
		if($("#firstVolume2").val()==""){
			//alert($("#volisfrom").val() +"kl");
			alert("Please fill in necessary details.");
		}else
		{
			////alert("111");
			var url = "Table_GenerateIssuesList?patternID=" +pattern+"&firstPattern=" +firstPattern+"&startDate="+startDate;
			url += "&quantity="+quantity+"&noOfIssues=" + noOfIssues+"&firstVolume="+firstVolume+"&freq="+freq+"&freqType="+freqType+"&freqTime="+freqTime;
			//alert("222");
	   		$.ajax({
	   			// Title
	   			url: url,
	   			success: function(result) {
	   			
	   				$("#table-cardex").html(result);
	   				
	   				/*$.get('GetDisplayLooseSheets', {
						  matno : $("#input-contorlNo").val()
						 	}, function(responseJson) {
							if(responseJson != null){
								$.each(responseJson, function(key,value) {
									for(var i=1;i<=quantity; i++){
										if(value['CUMIDX'] == "Y"){
											$('#table-cardex').append('<tr><td>'+i+'</td><td>Cum. Index</td><td>Cum. Index</td><td>'+$("#startDate").val()+'</td></tr>');
										}
										
										if(value['TITLPG'] == "Y"){
											$('#table-cardex').append('<tr><td>'+i+'</td><td>Title Page</td><td>Title Page</td><td>'+$("#startDate").val()+'</td></tr>');
										}
										
										if(value['CONTPG'] == "Y"){
											$('#table-cardex').append('<tr><td>'+i+'</td><td>Cont. Page</td><td>Cont. Page</td><td>'+$("#startDate").val()+'</td></tr>');
										}
									}
									
								});
							}
					});*/
	   				
	   			}
	   		});
	   		///alert("333");
		}
	}
});