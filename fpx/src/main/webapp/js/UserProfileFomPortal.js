
$(document).ready(function() {
	
	$("#updUserProfile").attr('disabled',true);
	
	function messageBox(langcode, code, criteria, label){
			$.get("Global?type=Handler&name=Error_Page",{
				GL79LANGCODE : langcode,
				GL79ERRCODE : code,
				criteria : criteria,
			 	label : label},function(result){
			 		//alert(result);
					//swal('',result, 'info' );
					swal(result);
			});
			
			
	} 
	
	$(".statedropdown").hide();
	
	$("#lblPatronID").on("blur",function (e) {
		
		var id = $("#lblPatronID").val();
		$(".lblName").empty();

		e.preventDefault();
		e.stopImmediatePropagation();
		

		$.get('GetPatronName', {
        	id : id
	 	}, function(responseJson) {
		if(responseJson != null){
			
			$.each(responseJson, function(key,value) {

				if(value['Name']==''){	
					messageBox("001","036","","");	
	
					$(".wilmuname").empty();
					$(".wilmuemail").empty();
					$(".wilmuic").empty();
					$(".wilmudob").empty();
					$(".wilmugender").empty();
					$(".wilmuadd1").empty();
					$(".wilmuadd2").empty();
					$(".wilmuadd3").empty();
					$(".wilmupostcode").empty();
					$(".wilmustate").empty();
					$(".wilmucountry").empty();
					$(".wilmuphoneno").empty();
					$(".wilmuhomeno").empty();


					$(".portalname").empty();
					$(".portalemail").empty();
					$(".portalic").empty();
					$(".portaldob").empty();
					$(".portalgender").empty();
					$(".portaladd1").empty();
					$(".portaladd2").empty();
					$(".portaladd3").empty();
					$(".portalpostcode").empty();
					$(".portalstate").empty();
					$(".portalcountry").empty();
					$(".portalphoneno").empty();
					$(".portalhomeno").empty();
				}

				
				///$(".lblName").text(value['Name']);
				$('#Reterive').prop('disabled', false);
			});
		}
		});

		
		////display vendor name
		/*$.get('GetPatronName', {
        	id : id
		 	}, function(responseJson) {
			

			if(responseJson != null){
				
				if(responseJson == ""){
					messageBox("001","036","","");
				}

				$.each(responseJson, function(key,value) {
					

					$(".lblName").text(value['Name']);
					$('#Reterive').prop('disabled', false);
				});
			}
		});*/
		
		
		/////
		$.get('GetPatronBasicInfo', {
        	patrid : id
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					
					var gender = value['Gender'];
					if(gender == 'F'){
						gender = "Female";
					}if(gender == 'M'){
						gender = "Male";
					}
				
					
					$(".wilmuname").text(value['Name']);
					$(".wilmuemail").text(value['Email']);
					$(".wilmuic").text(value['IcNo']);
					$(".wilmudob").text(value['Dob']);
					$(".wilmugender").text(gender);
					$(".wilmuadd1").text(value['Add1']);
					$(".wilmuadd2").text(value['Add2']);
					$(".wilmuadd3").text(value['Add3']);
					$(".wilmupostcode").text(value['Postcode']);
					$(".wilmustate").text(value['TownDesc']);
					$(".wilmucountry").text(value['Nationality']);
					$(".wilmuphoneno").text(value['PhoneNo']);
					$(".wilmuhomeno").text(value['HouseNo']);
					
					
					var settings = {
					  "url": "https://api.ppj.gov.my/api/call/5?fid=52&token=VEcybXJ5OG5tVmNMdzhlY1RJRnJFVVo3eHk1TDE2V05uQzRpRDA2bw==&ref="+value['Email'],
					  "method": "GET",
					  "timeout": 0,
					  "headers": {
						"Cookie": "ppj_api_centre_session=2MpGfbzx4byDCPQDYVoWoXWhFKVNfqiFliPyLqgA"
					  },
					};
					
					
					$.ajax(settings).done(function (response) {
						
						console.log(response);
					
			
						if(response != null){	
							$.each(response, function(key,value) {
								
								//alert("dsf"+value['reference_id']);
								///alert($(".wilmuic").text() != value['reference_id'] )

								/*if($(".wilmuic").text() != value['reference_id'] ){						
									$("#updUserProfile").attr('disabled',true);
								}else{
									$("#updUserProfile").attr('disabled',false);
								}*/
								$("#updUserProfile").attr('disabled',false);
								
								
								var gender = value['gender'];
								if(gender == 'P'){
									gender = "Female";
								}if(gender == 'L'){
									gender = "Male";
								}
								
								$('#negeriUpd option:contains(' + value['city'] + ')').attr('selected', 'selected');
								
								var add3 = value['address3'];
								
								if(add3 = "null"){
									add3 = "";
								}
								
								$("#alamat3").val(add3 +value['city']);
								
								$(".portalname").text(value['name']);
								$(".portalemail").text(value['email']);
								$(".portalic").text(value['reference_id']);
								$(".portaldob").text(moment(value['birth_date'], 'YYYY-MM-DD').format("DD/MM/YYYY"));
								$(".portalgender").text(gender);
								$(".portaladd1").text(value['address1']);
								$(".portaladd2").text(value['address2']);
								$(".portaladd3").text(add3 +value['city']);
								$(".portalpostcode").text(value['postcode']);
								
								var state = value['fk_lkp_state'];
								
								if(state == "1"){
									state = "JOHOR";
								}else if(state == "2"){
									state = "KEDAH";
								}else if(state == "3"){
									state = "KELANTAN";
								}else if(state == "4"){
									state = "MELAKA";
								}else if(state == "5"){
									state = "NEGERI SEMBILAN";
								}else if(state == "6"){
									state = "PAHANG";
								}else if(state == "7"){
									state = "PERAK";
								}else if(state == "8"){
									state = "PERLIS";
								}else if(state == "9"){
									state = "PULAU PINANG";
								}else if(state == "10"){
									state = "SABAH";
								}else if(state == "11"){
									state = "SARAWAK";
								}else if(state == "12"){
									state = "SELANGOR";
								}else if(state == "13"){
									state = "TERENGGANU";
								}else if(state == "14"){
									state = "KUALA LUMPUR";
								}else if(state == "15"){
									state = "LABUAN";
								}else if(state == "16"){
									state = "PUTRAJAYA";
								}
								
								$(".portalstate").text(state);
								$(".portalcountry").text(value['nationality']);
								$(".portalphoneno").text(value['mobile_no']);
								$(".portalhomeno").text(value['home_no']);
								
							});
						}
			
					});
					

					
					
				});
			}
		});
		


		
	});
	
	
	
	//clear paron id keydown backspace
	$("#lblPatronID").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".lblName").empty();
			$(".wilmuname").empty();
			$(".wilmuemail").empty();
			$(".wilmuic").empty();
			$(".wilmudob").empty();
			$(".wilmugender").empty();
			$(".wilmuadd1").empty();
			$(".wilmuadd2").empty();
			$(".wilmuadd3").empty();
			$(".wilmupostcode").empty();
			$(".wilmustate").empty();
			$(".wilmucountry").empty();
			$(".wilmuphoneno").empty();
			$(".wilmuhomeno").empty();


			$(".portalname").empty();
			$(".portalemail").empty();
			$(".portalic").empty();
			$(".portaldob").empty();
			$(".portalgender").empty();
			$(".portaladd1").empty();
			$(".portaladd2").empty();
			$(".portaladd3").empty();
			$(".portalpostcode").empty();
			$(".portalstate").empty();
			$(".portalcountry").empty();
			$(".portalphoneno").empty();
			$(".portalhomeno").empty();
		}
		
		if(code == 13) {
		    e.preventDefault();
		    return false;
		 }
	});


	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Update Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#updUserProfile').click(function(){


		var id = $("#lblPatronID").val();
		var name = $(".portalname").text();
		var email = $(".portalemail").text();
		var kp = $(".portalic").text();
		var dob = moment($(".portaldob").text(), 'DD/MM/YYYY').format("YYYYMMDD");
		var gender = $(".portalgender").text();
		var add1 = $(".portaladd1").text();
		var add2 = $(".portaladd2").text();
		var add3 = $(".portaladd3").text();
		var postcode = $(".portalpostcode").text();
		///var state = $(".portalstate").text();
		var state = $("#negeriUpd").val();
		var national = $(".portalcountry").text();
		var mobileno = $(".portalphoneno").text();
		var houseno = $(".portalhomeno").text();
		var loginid = $("#liferayLogin").val();
		
		



		/*$.ajax({
	        url: "Handle_UserUpdatePortal",
	        type: 'POST',
	        data: { id : id, name : name, email : email,  kp : kp, dob : dob,
					gender : gender, add1 : add1, add2 : add2,
					add3 : add3, postcode : postcode, state : state, 
					national : national,
					mobileno : mobileno, houseno : houseno,
					loginid : loginid
				  },
	        success: function (data) {
		
				alert("dataaa"+data);
	          /// messageBox("002","004","","");
				//alert("Berjaya daftar");
				$('#formdataborangcadangan').trigger("reset");
				$('#formdataborangcadangan').data('bootstrapValidator').resetForm();
			
	        },
	        cache: false,
	        contentType: false,
	        processData: false
	    });*/



	    $.post("Handle_UserUpdatePortal", { 
    	 	id : id, name : name, email : email,  kp : kp, dob : dob,
					gender : gender, add1 : add1, add2 : add2,
					add3 : add3, postcode : postcode, state : state, 
					national : national,
					mobileno : mobileno, houseno : houseno,
					loginid : loginid
		 }, 
		 function(data,status){

		 	if(data.trim() == "Success"){
		 		messageBox("001", "005","Update","@action");
		 		$(".lblName").empty();
				$("#lblPatronID").val("");
				$(".wilmuname").empty();
				$(".wilmuemail").empty();
				$(".wilmuic").empty();
				$(".wilmudob").empty();
				$(".wilmugender").empty();
				$(".wilmuadd1").empty();
				$(".wilmuadd2").empty();
				$(".wilmuadd3").empty();
				$(".wilmupostcode").empty();
				$(".wilmustate").empty();
				$(".wilmucountry").empty();
				$(".wilmuphoneno").empty();
				$(".wilmuhomeno").empty();


				$(".portalname").empty();
				$(".portalemail").empty();
				$(".portalic").empty();
				$(".portaldob").empty();
				$(".portalgender").empty();
				$(".portaladd1").empty();
				$(".portaladd2").empty();
				$(".portaladd3").empty();
				$(".portalpostcode").empty();
				$(".portalstate").empty();
				$(".portalcountry").empty();
				$(".portalphoneno").empty();
				$(".portalhomeno").empty();
		 	}

			///alert("dataaa"+data);
			
		
		 }
	     ).fail(function(data){
	    	 swal("fail");
	     }).success(function(data){
	 	 });
		 });

	
	
	
});


	
	
	