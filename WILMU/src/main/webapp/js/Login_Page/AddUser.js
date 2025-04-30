

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

    	$("#nameResponse").html("Firstname cannot be empty.");
    	$('.sendButton').attr('disabled', true);
        return false;
    }
    else{
			$("#nameResponse").html(" ");
			CheckUsername();
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



// validate firstname and lastname /[^a-zA-Z \n\r@']+/g

/* $(document).on('keypress', '#firstname', function (event) {
	
	var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	var alphaExp = /^[A-Za-z\s@']+$/;
	

	if (key.match(alphaExp)) {
		return true;
		} else{
			event.preventDefault();
		return false;
		}

}); */

/* $(document).on('keypress', '#lastname', function (event) {
	
	var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	var alphaExp = /^[A-Za-z\s@']+$/;
	

	if (key.match(alphaExp)) {
		return true;
		} else{
			event.preventDefault();
		return false;
		}

}); */

function validateEmail(emailField){
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

    if (reg.test(emailField.value) == false) 
    {
    	$("#emailResponse").html("Invalid email address.");
    	$('.sendButton').attr('disabled', true);
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
	
	var id = $('#username').val();
	var firstname = $("#firstname").val();		
	var email = $("#email").val();
	var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm_password").value;
	
	
	$.get('CheckingEmail', {
		email : email
	}, function(responseText) {
		
		$('#emailResponse').text(responseText);
		
		if(responseText.trim()=="Email already exist!"){
		 $('.sendButton').attr('disabled', true);
		}else if(id.length<=0||firstname.length<=0||email.length<=0||password.length<=0||confirmPassword.length<=0){
			 $('.sendButton').attr('disabled', true);
		}
		else{
			if(($("#emailResponse").text()=="")&&($("#userResponse").text()=="")&&($("#pass_error").text()!="Password must be at least 8 characters/numbers.")&&($("#nameResponse").text()!="Firstname cannot be empty.")){
	    		 $('.sendButton').attr('disabled', false);
			 }
		
		}
		
	});

}



//validate username 
function userLength(){
    var inputStr = $("#username").val();
    if(inputStr.length<6){
    	$("#userResponse").html("Username must be at least 6 characters.");
    	$('.sendButton').attr('disabled', true);
    	return false;
    }
	else{
		$("#userResponse").html(" ");
		CheckUsername();
		return true;
	}
}

function CheckUsername(){

	var id = $('#username').val();
	var firstname = $("#firstname").val();		
	var email = $("#email").val();
	var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm_password").value;
	
	
	$.get('CheckingIfExist', {
		username : id
	}, function(responseText) {
		
	$('#userResponse').text(responseText);
		
		
		if(responseText.trim()=="Username already exist!"){
		 $('.sendButton').attr('disabled', true);
		}else if(id.length<=0||firstname.length<=0||email.length<=0||password.length<=0||confirmPassword.length<=0){
			 $('.sendButton').attr('disabled', true);
		}
		else{
			if(($("#emailResponse").text()=="")&&($("#userResponse").text()=="")&&($("#pass_error").text()!="Password must be at least 8 characters/numbers.")&&($("#nameResponse").text()!="Firstname cannot be empty.")){
	    		 $('.sendButton').attr('disabled', false);
			 }
		
		}
		
	});

}

//Validate password

function passLength(){
    var inputStr = $("#password").val();
	var confirmPassword = $("#confirm_password").val();
    if(inputStr.length<8){
    	$("#pass_ok").html(" ");
    	$("#pass_error").html("Password must be at least 8 characters/numbers.");
		document.getElementById("confirm_error").innerHTML = " ";
    	$('.sendButton').attr('disabled', true);
        return false;
    }else{
    	$("#pass_error").html(" ");
    	document.getElementById("pass_ok").innerHTML = "Valid password."
		//<img src='dist/img/available.png' style='width:20px' class='mark'> 
    	return true;
    }
}


//Validate password

function Validate() {
	
	var firstname = document.getElementById("firstname").value;
	var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm_password").value;
	
	if((confirmPassword.length<=0) && ($("#pass_error").text()=="Password must be at least 8 characters/numbers.")){
		//alert("Test");
		document.getElementById("confirm_error").innerHTML = " ";
		document.getElementById("confirm_ok").innerHTML = " ";
	}
	else if((confirmPassword.length<=0) && ($("#pass_ok").text()=="Valid password.")){
		document.getElementById("confirm_error").innerHTML = "Please re-enter password to confirm.";
		document.getElementById("confirm_ok").innerHTML = " ";
	}
	else{
		
		if (password != confirmPassword) {
    	$("#confirm_ok").html(" ");
		document.getElementById("confirm_error").innerHTML = "<img src='dist/img/error.png' style='width:20px' class='mark'> Password does not match.";
    	$('.sendButton').attr('disabled', true);
        return false;
		}
		else{
    	
    	$("#confirm_error").html(" ");
		 document.getElementById("confirm_ok").innerHTML = "<img src='dist/img/available.png' style='width:20px' class='mark'> Valid password.";
    	
			if(($("#emailResponse").text()=="")&&($("#userResponse").text()=="")&&($("#pass_error").text()!="Password must be at least 8 characters/numbers.")&&(firstname !=" ")){
    		 checking();
			 //$('.sendButton').attr('disabled', false);
			}
		 
		}
		
	}
	
    
}

function focuspass(){
	
	var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm_password").value;
	
	if((confirmPassword.length<=0) && ($("#pass_error").text()=="Password must be at least 8 characters/numbers.")){
		//alert("Test");
		document.getElementById("confirm_error").innerHTML = " ";
		document.getElementById("confirm_ok").innerHTML = " ";
	}
	
    
	
}

function checking() {
	$("#firstname, #email, #username, #password, #confirm_password").blur(function(){
		var firstname = $("#firstname").val();		
		var email = $("#email").val();
		var username = $("#username").val();
		var password = document.getElementById("password").value;
	     var confirmPassword = document.getElementById("confirm_password").value;
	     
	     if(firstname.length<=0){
			 $("#nameResponse").html("Please fill out this field.");
			 $('.sendButton').attr('disabled', true);
		 }else if(email.length<=0){
			 $("#emailResponse").html("Please fill out this field.");
			 $('.sendButton').attr('disabled', true);
		 }else if(username.length<=0){
			 $("#userResponse").html("Please fill out this field.");
			 $('.sendButton').attr('disabled', true);
		 }else if(password.length<=0){
			 $("#pass_error").html("Please fill out this field.");
			 $('.sendButton').attr('disabled', true);
		 }else if(confirmPassword.length<=0){
			 $("#confirm_error").html("Please fill out this field.");
			 $('.sendButton').attr('disabled', true);
		 }else if(($("#emailResponse").text()=="")&&($("#userResponse").text()=="")&&($("#userResponse").text()!="Password must be at least 8 characters/numbers.")&&($("#nameResponse").text()!="Firstname cannot be empty.")){
	    		 $('.sendButton').attr('disabled', false);
		}else{
			 
	    	 //$('.sendButton').attr('disabled', false);
			
	     }
		
	});
}

//add function
function addUser() {
		
	var firstname = $("#firstname").val();
	var lastname = $("#lastname").val();
	
	var email = $("#email").val();
	var username = $("#username").val();
	
	var password = $("#password").val();

	loader(".list");
	 $.get("HandlerSignupPage",{firstname:firstname, lastname:lastname, email:email, username:username, password:password},function(responseText){
		 
		 swal("Successful","The record is added", "success" );
				$('.swal2-confirm').click(function(){
					location.reload(); 
		        });
		 
		 $("#AddUser").modal("hide");
		 				
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