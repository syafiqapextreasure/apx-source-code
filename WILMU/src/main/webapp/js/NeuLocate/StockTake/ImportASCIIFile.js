/**
 * 
 */
$ (document).ready( function() {
	$ ("#datepickers").datepicker({
		autoclose: true,
		todayHighlight: true,
		 format: "dd/mm/yyyy"
	});
	

	//Upload file to validate and import to table
	$("#imports").click(function(event){
		   var form = $('.current_forms')[0];
		   var data = new FormData(form);
		   var deleteStat = false;
		   
		   if($("#thefile").val() == "" || $("#datepickers").val()==""|| $("#officerid").val()==""||$("#batchno").val()==""){
			      swal("", "Please fill in mandatory field", "");
			}else{
		   
		   if($('input[name="chkdelete"]').is(':checked')){
			   deleteStat = true;
			   data.append('deleteStat', deleteStat);
		   }else{
			   deleteStat = false;
			   data.append('deleteStat', deleteStat);
		   }
		   
		   $.ajax({
	           type: "POST",
	           enctype: 'multipart/form-data',
	           url: "ImportFile",
	           data: data,
	           processData: false,
	           contentType: false,
	           cache: false,
	           success: function (data) {
	        	  var splitData = data.split("/n");
	        	   if(splitData[0]=="invalid_length"){
	        		   swal('','Invalid length of accession number','');
	        	   }else{
	        		   $("#inventoryList tbody").html(splitData[0]);
	        		  /* $("#inventoryList tbody").html(data);
	        		   $('#checkInventory').prop('disabled', false);*/
	        		   swal("", splitData[1] + "record successfully insert into database", "");
	        		   $("#recordRetrieve").modal("hide");
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
			}
	});
	
});

