

$(document).ready(function() {
	/*swal({   
		text: 'Load First 100 record only.',
		type: 'warning',   showCancelButton: false,
		timer: 3000
		
		}).then(function(dismiss) {
			
			if (dismiss === 'cancel') {
			    swal(
			      'Cancelled',
			      'Your imaginary file is safe :)',
			      'error'
			    );
			  }
	});*/
	
	//Call error message page
	function messageBox(code, criteria, label){
	      var url = "Error_Page?GL79ERRCODE="+code+"" +
	      "&criteria=" + criteria + "&label="+label+"";
	      //alert(url);
	      $.ajax({
	            url: url,
	            success: function(result) {
	                  swal(result); //, 'warning' 
	            }
	      });
	}
	
	///msg after add and edit
	if($("#msgAdd").val() == "Success"){
		//alert("Success");
		//messageBox("005","Add","@action");
		swal("Successfully Add");
	}else if($("#msgAdd").val() == "Success Update"){
		swal("Successfully Update");
	}/*else{
		alert("xSuccess");
	}*/
	
	
	
	////  clear msgAdd filed
	/*$('#vendorModal, #addAccMaint, #viewModal, #editModal').on('show.bs.modal', function() {
		$("#msgAdd").val('');
	    // do something when the modal is shown
	});*/
});



	 