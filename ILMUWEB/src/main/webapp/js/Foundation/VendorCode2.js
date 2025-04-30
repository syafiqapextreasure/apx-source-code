$(document).ready(function() {
	///msg after add and edit
	if($("#msgAdd").val() == "Success"){
		//alert("Success");
		//messageBox("005","Add","@action");
		swal("Successfully Add");
	}else if($("#msgAdd").val() == "Success Update"){
		swal("Successfully Update");
	}
});