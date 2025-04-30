

$(document).ready(function () {
    $("#firstname").keyup(function (event) {
		
		var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
		var alphaExp = /^[A-Za-z\s@']+$/;
		
		

		if (key.match(alphaExp)) {
			return true;
		} else{
			event.preventDefault();
			return false;
		}
		
		CheckFirstName();

    });
});

function CheckFirstName(){
    var firstname = $("#firstname").val();

	/* if (!(/^\S{3,}$/.test(firstname))){
	    	 $('.sendButton').attr('disabled', true);
			 $("#nameResponse").html("Firstname cannot be empty.");
	}
    else */ if (firstname.trim() == "") 
    {

    	$("#nameResponse").html("Firstname cannot be empty. Please fill out this field.");
    	$('.editButton').attr('disabled', true);
        return false;
    }
    else{
			$("#nameResponse").html(" ");
			Validate();
			return true;
			
	}
	
}

$(document).ready(function () {
    $("#lastname").keydown(function (e) {

        var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
		var alphaExp = /^[A-Za-z\s@']+$/;
	
	
		if (key.match(alphaExp)) {
			return true;
		} else{
			event.preventDefault();
			return false;
		}
    });
});


function valUpEmail(emailField){
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	var oriEmail = $("#oriemail").val();

	if(oriEmail!=""){
		if(emailField.value==oriEmail){
			$("#emailResponse").html(" ");
			$('.editButton').attr('disabled', false);
			return true;
		}else{
			 if (reg.test(emailField.value) == false) 
				{
					$("#emailResponse").html("Invalid email address.");
					$('.editButton').attr('disabled', true);
					return false;
				}
				else{
					$("#emailResponse").html(" ");
					CheckEmail();
					return true;
				}
		}
		
	}
}

function validateEmail(emailField){
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;


    if (reg.test(emailField.value) == false) 
    {
    	$("#emailResponse").html("Invalid email address.");
    	$('.editButton').attr('disabled', true);
        return false;
    }
    else{
    	$("#emailResponse").html(" ");
    	CheckEmail();
    	return true;
    }
}

//Checking Email already exist

function CheckEmail(){
	
	
	var firstname = $("#firstname").val();		
	var email = $("#email").val();
	
	
	$.get('CheckingEmail', {
		email : email
	}, function(responseText) {
		
		//alert(responseText);
		
		$('#emailResponse').text(responseText);
		
		if(responseText.trim()=="Email already exist!"){
		 $('.editButton').attr('disabled', true);
		 
		}else if(firstname.length<=0||email.length<=0){
			 $('.editButton').attr('disabled', true);
		}
		else{
			if(($("#emailResponse").text()=="")&&($("#emailResponse").text()!="Email already exist!")&&($("#emailResponse").text()!="Invalid email address.")&&($("#nameResponse").text()!="Firstname cannot be empty. Please fill out this field.")){
	    		 $('.editButton').attr('disabled', false);
			 }
		
		}
		
	});

}





function Validate() {
	
	$("#firstname, #email, #lastname").blur(function(){
		
		var firstname = $("#firstname").val();		
		var email = $("#email").val();
		var lastname = $("#lastname").val();
	     
	     if(firstname.length<=0){
			 $("#nameResponse").html("Please fill out this field.");
			 $('.editButton').attr('disabled', true);
		 }else if(email.length<=0){
			 $("#emailResponse").html("Please fill out this field.");
			 $('.editButton').attr('disabled', true);
		 }else if(($("#nameResponse").text()=="Firstname cannot be empty. Please fill out this field.")&&($("#emailResponse").text()=="Invalid email address.")){
			 $('.editButton').attr('disabled', true);
		 
		 }
		 else if($("#emailResponse").text()=="Invalid email address."){
			 $('.editButton').attr('disabled', true);
		 }
		 else if($("#nameResponse").text()=="Firstname cannot be empty. Please fill out this field."){
			 $('.editButton').attr('disabled', true);
			 
		 }else if((lastname<=0)&&($("#emailResponse").text()=="")&&($("#nameResponse").text()!="Firstname cannot be empty.")&&($("#nameResponse").text()!="Firstname cannot be empty. Please fill out this field.")){
	    		 $('.editButton').attr('disabled', false);
				 
		}else{
			 
	    	 $('.editButton').attr('disabled', false);
			
	     }
		 
		
	});
  
}


function checkingedit(){
	
	var firstname = $("#firstname").val();		
	var email = $("#email").val();
	     
	     if(firstname.length<=0||email.length<=0){
	    	 $('.editButton').attr('disabled', true);
	     }else{
	    	 $('.editButton').attr('disabled', false);
			
	     }
	
}

function updateUser() {
	//alert("Masuk Javasript");
	
	var firstname = $("#firstname").val();
	var lastname = $("#lastname").val();
	
	var email = $("#email").val();
	var username = $("#username").val();
	
	
	
	 $.get("UpdateUser",{firstname:firstname, lastname:lastname, email:email, username:username},function(responseText){
		 swal("Successful","The record has been edited.", "success" );
			$('.swal2-confirm').click(function(){
				location.reload(); 
	        });
	 
	 $("#EditUser").modal("hide");
	 });
		
}

function resetPass() {
	//alert("Masuk reset");
	
	document.getElementById("oldpassword").value="";
}	

function passLength(){
    var inputStr = $("#password").val();
    if(inputStr.length<8){
    	$("#error_msg").html("Password must be at least 8 characters/numbers.");
       $("#ok_msg").html(" ");
	   $('.resetbtn').attr('disabled',true);
    }else{
    	$("#error_msg").html(" ");
    	document.getElementById("ok_msg").innerHTML = "<img src='dist/img/available.png' style='width:20px' class='mark'> Valid password.";
    	$('.resetbtn').attr('disabled',false);
    }
}

function validatePass(){
	
	var password = $("#password").val();
	
	if($("#error_msg").text()=="Password must be at least 8 characters/numbers."){
		$('.resetbtn').attr('disabled',true);
	}
	else{
		$('.resetbtn').attr('disabled',false);
	}
}

function newpass() {
	var username = $("#username").val();
	var password = $("#password").val();
	//alert(password);
	
	if(password.length<=0){
   	 $('.editButton').attr('disabled', true);
    }else{
   	 $('.editButton').attr('disabled', false);
   	 
   	loader(".list");
   	$.get("Reset",{password:password, username:username},function(responseText){
		 swal("Successful","The password has been reset.", "success" );
			$('.swal2-confirm').click(function(){
				location.reload(); 
	        });
	 
	 $("#Reset").modal("hide");
	 });
		
    }

}