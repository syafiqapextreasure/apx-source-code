/**
 * 
 */

	//*********************AREA FOR DELETE FOR PURCHASE CONTROL************************************//
	function deleteRequest(reqCtrl){
		var deleteorder = $(reqCtrl).attr('data-reqno');

		var index = $('#reqCtrl').DataTable()
	       .rows({ search: 'applied'})
	       .nodes()
	       .to$()
	       .index($(reqCtrl).closest('tr'));//$(this).closest('tr').index();
		//alert(index);
		swal({
			text: "Are you sure want to delete?",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "Yes",
	        cancelButtonText: "No"
	      }).then(
	        function(isConfirm) {
	          if (isConfirm) {
	        	  
	        	  $.ajax({
	    			  method: "POST",
	    			  url: "deleteRequestCtrl",
	    			  data: { deleteorder: deleteorder}
	    			}).done(function( msg ) {
	    			    messageBox("005"," Deleted","@action");
	    		})
	        	  setTimeout(function(){
						$('#reqCtrl').DataTable().row(index).remove().draw();
					}, 1000);
	    		
	          }
	     });
	}
	
	//*******************************AREA FOR DELETE FOR ORDER MAINT************************************//
	$('#OdrMaint2 tbody').on( 'click', '#Delete', function () {
		var deleteorder = $(this).attr('data-OrderNo');
		//alert(deleteorder+"fdf");
		var index = $('#OdrMaint2').DataTable()
	       .rows({ search: 'applied'})
	       .nodes()
	       .to$()
	       .index($(this).closest('tr'));//$(this).closest('tr').index();

		///alert('Row index: ' + index);
		swal({
			text: "Are you sure want to delete?",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "Yes",
	        cancelButtonText: "No"
	      }).then(
	        function(isConfirm) {
	          if (isConfirm) {
	        	  
	        	  $.ajax({
	    			  method: "POST",
	    			  url: "deleteOrderMaint",
	    			  data: { deleteorder: deleteorder}
	    			}).done(function( msg ) {
	    			    messageBox("005","Deleted Record","@action");
						setTimeout(function(){
							$('#OdrMaint2').DataTable().row(index ).remove().draw();	
						}, 100);
	    		})
	        	  
	    		
	          }
	          /*setTimeout(function(){
	    			$('#OdrMaint2').DataTable().row(deleteorder ).remove().draw();
	    		},2000)*/
	        }
	      );
		//$('#OdrMaint2').DataTable().row( $(this).parents('tr') ).remove().draw(false);
		/*$.ajax({
			  method: "POST",
			  url: "deleteordermaint",
			  data: { deleteorder: deleteorder}
			}).done(function( msg ) {
			    messageBox("005","Deleted Record","@action");
		});
		
		$('#OdrMaint2').DataTable().row( $(this).parents('tr') ).remove().draw();*/
		
		/*swal({
			  //title: 'Are you sure?',
			  text: "Do you wish to delete this record?",
			  showCancelButton: true,
			  confirmButtonColor : '#3085d6',
			  cancelButtonColor : '#d33',
			  confirmButtonText: 'Yes'
			}).then(function() {
				setTimeout(function(){
					alert("jk");
					$('#OdrMaint2').DataTable().row( $(this).parents('tr') ).remove().draw();
				}, 1000);
				//$('#OdrMaint2').DataTable().row( $(this).parents('tr') ).remove().draw();
				$.ajax({
					  method: "POST",
					  url: "deleteordermaint",
					  data: { deleteorder: deleteorder}
					}).done(function( msg ) {
					    messageBox("005","Deletd Record","@action");
				});
				
				
			})*/
		
	} );
	/*$('#OdrMaint2').on("click", "#Delete", function(){
		alert("kl");
        alert($(this).parent());
        table.row($(this).parents('tr')).remove().draw(false);
  });*/
	/*$('.DeleteOrder').click(function(){
		var deleteorder = $(this).attr('data-OrderNo');
		
		$.post("deleteordermaint", {
			deleteorder:deleteorder,
		},function(data,status){
			messageBox("005","Deletd Record","@action");
			$('#OdrMaint2').DataTable().row('tr').remove().draw( false ); 
			// $('#OdrMaint2').DataTable().ajax.reload();

			}
		).fail(function(data){
			swal("Fail Delete");
		})/*.success(function(data){
		})*/;
	///});
	
	//***************************END AREA FOR DELETE FOR ORDER MAINT***********************************//