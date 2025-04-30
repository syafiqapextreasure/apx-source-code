
		
		
		//Delete General Subject
			function deleteUser(username){
			
			swal({   
				//title: 'Do you wish to delete this record?',
				text: 'Do you wish to delete this record?',
				type: 'warning',   showCancelButton: true,
				confirmButtonColor: '#3085d6', 
				cancelButtonColor: '#d33',
				confirmButtonText: 'Yes',
				cancelButtonText: 'No',
				confirmButtonClass: 'confirm-class',
				cancelButtonClass: 'cancel-class',
				closeOnConfirm: false,
				closeOnCancel: false 
				}).then(function() {
					 var deleteUrl = "Handler_DeleteUser?username="+username;
					  
				      $.ajax({
				 			url: deleteUrl,
				 			success: function(result) {
				 				var status = result.replace(/\s+/g, '');
				 				if(status == "ok"){
				 					  swal(
										'Successfully Deleted!',
										'The record has been successfully removed.',
										'success'
										);
				 					$('.swal2-confirm').click(function(){
					  					location.reload(); 
					   		        });
		
				 				}else{
				 				   swal(
										'Not Deleted',
										'This record cannot be deleted.',
										'error'
								  );
				 				}
				 			}
				 		});
							},function(dismiss) {
								  if (dismiss == 'cancel') {
								    swal(
								      '',
								      'Cancelled',
								      'error'
								    );
								     
								  }
								})
		}
	    	
		
			