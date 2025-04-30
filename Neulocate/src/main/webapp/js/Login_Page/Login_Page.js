function showPassword() {
    
    var key_attr = $('#key').attr('type');
    
    if(key_attr != 'text') {
        
        $('.checkbox').addClass('show');
        $('#key').attr('type', 'text');
        
    } else {
        
        $('.checkbox').removeClass('show');
        $('#key').attr('type', 'password');
        
    }
    
}

$(document).ready(function () {
	$('#btn-login').click(function(event){
		
		var GL14PATR = $("#username").val();
		var GL14PASW = $("#key").val();
	
		
		 $.get("Handler_Login_Page",{GL14PATR:GL14PATR, GL14PASW:GL14PASW},function(message){
			if(message.trim()=="error"){
				swal("Warning", "Invalid username or password", "warning");
			}else{
				 window.location="template?MODULE=01_Inventory_Check&ACTION=Main_Page.jsp";				
			}
		 });
	});
});
