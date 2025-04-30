/**
 * Function : To be used in order to load username from liferay and validate
 */

$( document ).ready(function() {

	//var username = window.frameElement.getAttribute("name");
	var username = "ilmuadmin";
	$("#liferayLogin").val(username);

	var programID = $("#programID").val();
	//alert(programID);
	 $.get("SignedInUser",{username:username,programID:programID}, function(data){
		//alert(data);
		 if(data.trim()=="true"){
			 $(".box").show();
		 }else{
			 swal({
				  title: 'Access is denied',
				  text: 'You do not have permission to view this resource using the credentials that you supplied.',
				  showCancelButton: false,
				  showConfirmButton: false,
				  //allowOutsideClick: false
				  allowOutsideClick: false,
				  allowEscapeKey: false
				}).then(
				  function () {},
				  function (dismiss) {
				    if (dismiss === 'timer') {
				    }
				  }
				)
		 } 
	});
	 
	 
	 $.get('ResultGlflag2', {
		 	id : "CURRENCYFORMAT",
		 	}, function(responseJson) {
			$("#setupCurrency").val(responseJson);
	 });
	 
});
