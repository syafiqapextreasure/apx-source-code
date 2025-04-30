/**
 * 
 */
	
		//Delete template name 
		function deletePlugin(patronID){
			/*var split = tplID.split(",");
			var tplID =split[0];
			var tplName = split[1];*/
			swal({   
				//title: 'Are you sure want to delete '+ tplName+'?',
				text: 'This record cannot be delete.',
				type: 'warning',   showCancelButton: false,
				//confirmButtonColor: '#3085d6', 
				//cancelButtonColor: '#d33',
				//confirmButtonText: 'Yes, delete it!',
				//cancelButtonText: 'No, cancel !',
				//confirmButtonClass: 'confirm-class',
				//cancelButtonClass: 'cancel-class',
				//closeOnConfirm: false,
				//closeOnCancel: false 
				}).then(function(dismiss) {
					
					if (dismiss === 'cancel') {
					    swal(
					      'Cancelled',
					      'Your imaginary file is safe :)',
					      'error'
					    );
					  }
					})
								
		}
			
	//Reload upon click o cancel button
	 $('#btn-cancel').click(function(){
		 history.go(0);
	 });
	
		