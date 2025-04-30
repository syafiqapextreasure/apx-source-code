$(document).ready(function(){
	   $('#accMatNo').keypress(function(event){
           var keycode = (event.keyCode ? event.keyCode : event.which);
           if(keycode == '13'){
        	  if($("#fastRelease").prop("checked") == true){
               	
        		  	var docNo = $('#accMatNo').val();
		          	var code;
		    		var criteria;
		    		var label;
   		      		var data = 'docNo='+ docNo;
			   	       $.get('ReleaseForCI', {
			        	   accMatNo : docNo
			           }, function(responseText) {
			       
			        	   $.ajax({
				                  type: "GET",
				                  url: "ReleaseForCI",
				                  data: data,
				                  success: function(msg){
				                	  if(msg=="A"){
				                		code = "001";
				                		criteria = docNo;
				                		label = "@docNo";
				                	  }
				                	  else{
				                		  code = "002";
					                		criteria = docNo;
					                		label = "@docNo";
				                	  }
					              	  $.get("Error_Message?GL79ERRCODE="+code+"" + 
				               				"&criteria=" + criteria + "&label="+label+"",function(msg){
							        	   var lines = responseText.split( "<br>" );
							        	   $('#releaseList').append("<tr><td>" + docNo + "</td>" + 
								   			            "<td>" + msg + "</td>" + 
								   				  		"<td>" + lines[0] + "</td>" + 
								   				  		"<td>" + lines[1] + "</td></tr>");
							                   //document.getElementById("testvalue").value =  document.getElementById("accMatNo").value;
							                   $('#ReleaseForm')[0].reset();
							                   $("#accMatNo").focus(); 
					              	   });
				                  	}
			        	   		});
			           		});
        	               }
				               else{
				               	
				               }
				              
				               return false;
				           }
				           event.stopPropagation();
				       });
	   $('#releaseBtn').click(function() {

		  	var docNo = $('#accMatNo').val();
          	var code;
    		var criteria;
    		var label;
	      		var data = 'docNo='+ docNo;
	   	       $.get('ReleaseForCI', {
	        	   accMatNo : docNo
	           }, function(responseText) {
	       
	        	   $.ajax({
		                  type: "GET",
		                  url: "ReleaseForCI",
		                  data: data,
		                  success: function(msg){
		                	  if(msg=="A"){
		                		code = "001";
		                		criteria = docNo;
		                		label = "@docNo";
		                	  }
		                	  else{
		                		  code = "002";
			                		criteria = docNo;
			                		label = "@docNo";
		                	  }
			              	  $.get("Error_Message?GL79ERRCODE="+code+"" + 
		               				"&criteria=" + criteria + "&label="+label+"",function(msg){
					        	   var lines = responseText.split( "<br>" );
					        	   $('#releaseList').append("<tr><td>" + docNo + "</td>" + 
						   			            "<td>" + msg + "</td>" + 
						   				  		"<td>" + lines[0] + "</td>" + 
						   				  		"<td>" + lines[1] + "</td></tr>");
					                   //document.getElementById("testvalue").value =  document.getElementById("accMatNo").value;
					                   $('#ReleaseForm')[0].reset();
					                   $("#accMatNo").focus(); 
			              	   });
		                  	}
	        	   		});
	           		});
      		  });
	   
	   $('#releaseBtn').click(function() {
			var accNo = $('#accMatNo').val();
			var print = $('#print').is(':checked');
			
			var reserve = $('#reserve').is(':checked');
			
			if(reserve){
				var accMatNo = $('#accMatNo').val();
				
				var matNo = $('#accNo_value').val();
					
				     var data = 'matNo='+ matNo+ '&docNo=' + accMatNo;

		               $.ajax({
		                  type: "POST",
		                  data:data,
		                  url: "ReleaseForCI",
		                  success: function(msg){
		                	  	
		                  }
		    	    });
				} 
				if (print){
				var accMatNo = $('#accMatNo_value').val();
				var accTitle = $('#accTitl_value').val();
				var accCallNo = $('#accCallNo_value').val();
				
				var menuID= "CT";
				// Parameters
				var url = "GeneratePreviewDocument?accMatNo=" + accMatNo
							+ "&accTitl=" + accTitle
							+ "&accCallNo=" + accCallNo
							+ "&print=" + print
							+ "&menuID=" + menuID;
			alert(url);
				
				window.open(url);
				
				$.ajax({
					// Title
					url: 'CreateMail.jsp' + params,
					success: function(result) {
						//alert("success");
							
							showPopupMsg('Successfully updated payment reference number', 'success');
	
					}
			
				});
				
				}
				else{
					//alert("Print checkbox is not checked");
				}

			});
	   
	   $('#reserve').click(function(event){
		   if($("#reserve").prop("checked") == true){
			   document.getElementById("print").disabled = '';
		   }
		  else{
		   document.getElementById("print").disabled = 'disabled';
		  }
		});
	   
	   $('#reserve').on('click', function() {
			
           var name = $('#accMatNo').val();
           var reserve = $('#reserve').is(':checked');
           if(reserve){
           $.get('ReleaseForCI', {
        	   matNos : name
           }, function(responseText) {
        	   $('#accNo_value').val(responseText);
           });
           }
   });
	   
	   $('#printBtn').click(function() {
			 
		   var docNo = $('#accMatNo').val();
		
		   //var errorID="005";
			  //var getDocNo =  $("#getDocNo").attr("value");
	          var data = 'docNo='+ docNo;

	               $.ajax({
	                  type: "POST",
	                  data:docNo,
	                  url: "Print",
	                  success: function(msg){
					 
	                  }
	    	    });
	             
		  });
	   
	   $('#print').on('click', function() {
			
			 document.getElementById("accMatNo_value").value = document.getElementById("accMatNo").value;
	});
	   
	   $('#accMatNo').focus(function(event) {
			
		      var name = $('#accMatNo').val();
		      if(name!=null){
		      $.get('ReleaseForCI', {
		   	   accMatNo : name
		      }, function(responseText) {
		   	   var lines = responseText.split( "<br>" );
		   	 document.getElementById("accTitl_value").value =lines[0] ;
			  document.getElementById("accCallNo_value").value = lines[1];
		   	   		document.getElementById("testvalue").value =  document.getElementById("accMatNo").value;
		              
		      });
		      }
		      
		});
});
 