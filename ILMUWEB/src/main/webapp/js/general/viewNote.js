$(document).ready(function() {
	//alert("iii");
	///FOUNDATION
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//get value
	var login = $("#liferayLogin").val();
	var idpatr = $("#GL14PATR").val();
	$('input[name=vendorView][value=Y]').attr('checked', true);
	
	///readonly input
	$('#notetitle').prop('disabled', true);
	$('#note').prop('disabled', true);
	$('#viewprm').prop('disabled', true);
	$('#editprm').prop('disabled', true);
	$('#vendorView').prop('disabled', true);
	$('input[name=vendorView]').attr("disabled",true);
	
	
	$('#table-note').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		processing: true,
		//info: false,
	    ajax: {
	    	url: "getViewNote",
	        data : {patrId : idpatr},
	        type: "GET",
	        dataSrc: function (json) {
	            var return_data = new Array();

	            for(var i=0;i< json.length; i++){
	            	
	            	var view = json[i].viewpermission;
	            	var edit = json[i].editpermission;

	            	switch(view){
	        	  	case state = '1':
	        	  		view = "1 - Public";
	        	  		break;
	        	  	case state = '2':
	        	  		view = "2 - Group";
	        	  		break;
	        	  	case state = '3':
	        	  		view = "3 - Personal";
	        	  		break;
	        		}
	            	
	            	switch(edit){
	        	  	case state = '1':
	        	  		edit = "1 - Public";
	        	  		break;
	        	  	case state = '2':
	        	  		edit = "2 - Group";
	        	  		break;
	        	  	case state = '3':
	        	  		edit = "3 - Personal";
	        	  		break;
	        		}
	            	
	              return_data.push({
	            	'ID': json[i].id,
	                'Date': json[i].date,
	                'Time' : json[i].time, 
	                'Title' : json[i].title,
	                'Author' : json[i].author,
	                'View Permission' : view,
	                'Edit Permission' : edit,
	                'Action' : '<button id="ViewNote" class="btn btn-primary btn-xs ViewNote" title="View" data-rowid="'+json[i].id+'" data-author="'+json[i].author+'" data-viewprm="'+json[i].viewpermission+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="EditNote" class="btn btn-info btn-xs EditNote" title="Edit" data-rowid="'+json[i].id+'" data-author="'+json[i].author+'" data-editprm="'+json[i].editpermission+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="DeleteNote" class="btn btn-dull btn-xs DeleteNote" data-id="'+json[i].id+'" data-author="'+json[i].author+'" data-editprm="'+json[i].editpermission+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
	              })
	            }
	            return return_data;
	          },
	     },//This is the end of the AJAX object from the example above
	     	columns    : [
				{'data': 'ID'},
				{'data': 'Date'},
				{'data': 'Time'},
				{'data': 'Title'},
				{'data': 'Author'},
				{'data': 'View Permission'},
				{'data': 'Edit Permission'},
				{'data': 'Action'},
			],
	});
	
	$("#saveNote, .cancelNote").show();
	$(".closeNote").hide();
	
	$("#viewprm, #editprm").prop("selectedIndex",-1);
	$("#notetitle").attr('maxlength','50');
	$("#note").attr('maxlength','255');
	
	/////////Add button click
	$('#addNote').click(function(){
		
		$('.formdataNote').trigger('reset');
	    $('.formdataNote').data('bootstrapValidator').resetForm();
	    $("#viewprm, #editprm").prop("selectedIndex",-1);
	    $("#saveNote, .cancelNote").show();
		$(".closeNote").hide();
		$(".formdataNote input").attr('disabled',false);
		$('#viewprm, #editprm').prop('disabled', false);
		$('#note').prop('disabled', false);
		$("#passval").val(idpatr);
		$("#idnote").val();
		
		/*$('#notetitle').prop('readonly', false);
		$('#note').prop('readonly', false);*/
	});
	
	////view///////////////////////////////////////////////
	$('#table-note tbody').on( 'click', '.ViewNote', function (e) {
		e.preventDefault();
		var getId = $(this).attr('data-rowid');
		var getauthor = $(this).attr('data-author');
		var viewprm = $(this).attr('data-viewprm');
		
		$(".formdataNote input").attr('disabled','disabled');
		$('#viewprm, #editprm').prop('disabled', 'disabled');
		$('#note').prop('disabled', true);
		$("#saveNote, .cancelNote").hide();
		$(".closeNote").show();
		
		
		switch(viewprm){
			case state = '1':				
				setTimeout(function(){
					$.get('getViewEditNote', {
						getId : getId
					 	}, function(responseJson) {
						if(responseJson != null){
							$.each(responseJson, function(key,value) {
								$("#idnote").val(value['id'])
								$("#notetitle").val(value['title']);
								$("#note").val(value['note']);
								$("#viewprm").val(value['viewpermission']);
								$("#editprm").val(value['editpermission']);
								
								
								setTimeout(function(){
									var printpms = value['printpms'];
									
									switch(printpms){
							        case state = 'Y':
							        	$('input[name=vendorView][value=Y]').attr('checked', true);
							        	$('input[name=vendorView][value=N]').attr('checked', false);
								  		break;
							        case state = 'N':
							        	$('input[name=vendorView][value=N]').attr('checked', true);
							        	$('input[name=vendorView][value=Y]').attr('checked', false);
								  		break;
								 }
								}, 1000);
								
							});
						}
				 	});
					}, 1000);
				break;
		  	case state = '2':
		  	$.get("Handler_compareGroup",{
     			login : login, 
     			getauthor : getauthor,
				},
				function(message){
					var status = message.replace(/\s+/g, '');
					if (status == "same") {
						setTimeout(function(){
							$.get('getViewEditNote', {
								getId : getId
							 	}, function(responseJson) {
								if(responseJson != null){
									$.each(responseJson, function(key,value) {
										$("#idnote").val(value['id'])
										$("#notetitle").val(value['title']);
										$("#note").val(value['note']);
										$("#viewprm").val(value['viewpermission']);
										$("#editprm").val(value['editpermission']);
										
										setTimeout(function(){
											var printpms = value['printpms'];
											
											switch(printpms){
									        case state = 'Y':
									        	$('input[name=vendorView][value=Y]').attr('checked', true);
									        	$('input[name=vendorView][value=N]').attr('checked', false);
										  		break;
									        case state = 'N':
									        	$('input[name=vendorView][value=N]').attr('checked', true);
									        	$('input[name=vendorView][value=Y]').attr('checked', false);
										  		break;
										 }
										}, 1000);
									});
								}
						 	});
						}, 1000);
					}else{
						setTimeout(function(){
			  				$("#viewprm, #editprm").prop("selectedIndex",-1);
			  				$('input[name=vendorView][value=Y]').attr('checked', true);
						}, 1000);
			  			messageBox("147","view","@action"); 
			  			$('.formdataNote').trigger('reset');
						$('.formdataNote').data('bootstrapValidator').resetForm();
					}				
				});
		  		break;
		  	case state = '3':
		  		if(login == getauthor){
		  			setTimeout(function(){
						$.get('getViewEditNote', {
							getId : getId
						 	}, function(responseJson) {
							if(responseJson != null){
								$.each(responseJson, function(key,value) {
									$("#idnote").val(value['id'])
									$("#notetitle").val(value['title']);
									$("#note").val(value['note']);
									$("#viewprm").val(value['viewpermission']);
									$("#editprm").val(value['editpermission']);
									
									setTimeout(function(){
										var printpms = value['printpms'];
										
										switch(printpms){
								        case state = 'Y':
								        	$('input[name=vendorView][value=Y]').attr('checked', true);
								        	$('input[name=vendorView][value=N]').attr('checked', false);
									  		break;
								        case state = 'N':
								        	$('input[name=vendorView][value=N]').attr('checked', true);
								        	$('input[name=vendorView][value=Y]').attr('checked', false);
									  		break;
									 }
									}, 1000);
								});
							}
					 	});
					}, 1000);
		  		}else{
		  			setTimeout(function(){
		  				$("#viewprm, #editprm").prop("selectedIndex",-1);
		  				$('input[name=vendorView][value=Y]').attr('checked', true);
					}, 1000);
		  			messageBox("147","view","@action"); 
		  			$('.formdataNote').trigger('reset');
					$('.formdataNote').data('bootstrapValidator').resetForm();
		  		}
		  		break;
		}
	});
	
	///edit/////////////////////////////////////////////////////////////////////////////////////////////
	$('#table-note tbody').on( 'click', '.EditNote', function (e) {
		e.preventDefault();
		
		var getId = $(this).attr('data-rowid');
		var getauthor = $(this).attr('data-author');
		var editprm = $(this).attr('data-editprm');
		$("#passval").val(idpatr);
		
		$(".formdataNote input").attr('disabled',false);
		$('#viewprm, #editprm').prop('disabled', false);
		$('#note').prop('disabled', false);
		$("#saveNote, .cancelNote").show();
		$(".closeNote").hide();
		
		
		switch(editprm){
			case state = '1':
				setTimeout(function(){
					$.get('getViewEditNote', {
						getId : getId
					 	}, function(responseJson) {
						if(responseJson != null){
							$.each(responseJson, function(key,value) {
								
								//alert(printpms);
								
								$("#idnote").val(value['id'])
								$("#notetitle").val(value['title']);
								$("#note").val(value['note']);
								$("#viewprm").val(value['viewpermission']);
								$("#editprm").val(value['editpermission']);
								
								setTimeout(function(){
									var printpms = value['printpms'];
									
									switch(printpms){
							        case state = 'Y':
							        	$('input[name=vendorView][value=Y]').attr('checked', true);
							        	$('input[name=vendorView][value=N]').attr('checked', false);
								  		break;
							        case state = 'N':
							        	$('input[name=vendorView][value=N]').attr('checked', true);
							        	$('input[name=vendorView][value=Y]').attr('checked', false);
								  		break;
								 }
								}, 1000);
								
							});
						}
				 	});
					}, 1000);
				break;
		  	case state = '2':
		  	$.get("Handler_compareGroup",{
     			login : login, 
     			getauthor : getauthor,
				},
				function(message){
					var status = message.replace(/\s+/g, '');
					if (status == "same") {
						setTimeout(function(){
							$.get('getViewEditNote', {
								getId : getId
							 	}, function(responseJson) {
								if(responseJson != null){
									$.each(responseJson, function(key,value) {
										//var printpms = value['printpms'];
										//alert(printpms);
										
										$("#idnote").val(value['id'])
										$("#notetitle").val(value['title']);
										$("#note").val(value['note']);
										$("#viewprm").val(value['viewpermission']);
										$("#editprm").val(value['editpermission']);
	
										setTimeout(function(){
											var printpms = value['printpms'];
											
											switch(printpms){
									        case state = 'Y':
									        	$('input[name=vendorView][value=Y]').attr('checked', true);
									        	$('input[name=vendorView][value=N]').attr('checked', false);
										  		break;
									        case state = 'N':
									        	$('input[name=vendorView][value=N]').attr('checked', true);
									        	$('input[name=vendorView][value=Y]').attr('checked', false);
										  		break;
										 }
										}, 1000);
										
									});
								}
						 	});
						}, 1000);
					}else{
						setTimeout(function(){
			  				$("#viewprm, #editprm").prop("selectedIndex",-1);
			  				$('input[name=vendorView][value=Y]').attr('checked', true);
						}, 1000);
			  			messageBox("147","edit","@action"); 
			  			$('.formdataNote').trigger('reset');
						$('.formdataNote').data('bootstrapValidator').resetForm();
						$(".formdataNote input").attr('disabled','disabled');
						$('#viewprm, #editprm').prop('disabled', 'disabled');
						$('#note').prop('disabled', true);
					}				
				});
		  		break;
		  	case state = '3':
		  		if(login == getauthor){
		  			setTimeout(function(){
						$.get('getViewEditNote', {
							getId : getId
						 	}, function(responseJson) {
							if(responseJson != null){
								$.each(responseJson, function(key,value) {
									$("#idnote").val(value['id'])
									$("#notetitle").val(value['title']);
									$("#note").val(value['note']);
									$("#viewprm").val(value['viewpermission']);
									$("#editprm").val(value['editpermission']);
									
									setTimeout(function(){
										var printpms = value['printpms'];
										
										switch(printpms){
								        case state = 'Y':
								        	$('input[name=vendorView][value=Y]').attr('checked', true);
								        	$('input[name=vendorView][value=N]').attr('checked', false);
									  		break;
								        case state = 'N':
								        	$('input[name=vendorView][value=N]').attr('checked', true);
								        	$('input[name=vendorView][value=Y]').attr('checked', false);
									  		break;
									 }
									}, 1000);
									
								});
							}
					 	});
					}, 1000);
		  		}else{
		  			setTimeout(function(){
		  				$("#viewprm, #editprm").prop("selectedIndex",-1);
		  				$('input[name=vendorView][value=Y]').attr('checked', true);
					}, 1000);
		  			messageBox("147","edit","@action"); 
		  			$('.formdataNote').trigger('reset');
					$('.formdataNote').data('bootstrapValidator').resetForm();
					$(".formdataNote input").attr('disabled','disabled');
					$('#viewprm, #editprm').prop('disabled', 'disabled');
					$('#note').prop('disabled', true);
		  		}
		  		break;
		}
	});
	
	///delete//////////////////////
	$('#table-note tbody').on( 'click', '.DeleteNote', function (e) {
		e.preventDefault();
		
		$(".formdataNote input").attr('disabled',true);
		$('#viewprm, #editprm').prop('disabled', true);
		$('#note').prop('disabled', true);
		$("#saveNote, .cancelNote").hide();
		$(".closeNote").show();
		
		var index = $('#table-note').DataTable().rows({ search: 'applied'})
		.nodes().to$().index($(this).closest('tr'));
		//alert(index);
		
		var deleteNote = $(this).attr('data-id');
		var getauthor = $(this).attr('data-author');
		var editprm = $(this).attr('data-editprm');	
		
		switch(editprm){
			case state = '1':
				swal({
					text: "Are you sure want to delete?",
					showCancelButton : true,
					confirmButtonColor : '#3085d6',
					cancelButtonColor : '#d33',
					confirmButtonText : 'Yes',
					cancelButtonText : 'No',
					confirmButtonClass : 'confirm-class',
					cancelButtonClass : 'cancel-class',
					closeOnConfirm : false,
					closeOnCancel : false
				}).then(
					function() {
						$.ajax({
							url : "Handler_DeleteNote",
							data: { id: deleteNote},
								success : function(result) {
									var status = result.replace(/\s+/g, '');
									if (status == "ok") {
										messageBox("005","Deleted","@action");
										$('.formdataNote').trigger('reset');
									    $('.formdataNote').data('bootstrapValidator').resetForm();
									    $("#viewprm, #editprm").prop("selectedIndex",-1);
									    $('#notetitle').prop('disabled', true);
										$('#note').prop('disabled', true);
										$('#viewprm').prop('disabled', true);
										$('#editprm').prop('disabled', true);
										$('#vendorView').prop('disabled', true);
										$('input[name=vendorView]').attr("disabled",true);
										/*swal('Successfully Deleted!',
											'The record has been successfully removed.');*/
										$('.swal2-confirm').click(
											function() {
												$('#table-note').DataTable().row(index).remove().draw();
												//location.reload();
										});
									}else{
			     						swal("Unsuccessfully");
			     					}
								}
							});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');
						}
				});
				
				break;
		  	case state = '2':
		  	$.get("Handler_compareGroup",{
     			login : login, 
     			getauthor : getauthor,
				},
				function(message){
					var status = message.replace(/\s+/g, '');
					if (status == "same") {
						setTimeout(function(){
							swal({
								text: "Are you sure want to delete?",
								showCancelButton : true,
								confirmButtonColor : '#3085d6',
								cancelButtonColor : '#d33',
								confirmButtonText : 'Yes',
								cancelButtonText : 'No',
								confirmButtonClass : 'confirm-class',
								cancelButtonClass : 'cancel-class',
								closeOnConfirm : false,
								closeOnCancel : false
							}).then(
								function() {
									$.ajax({
										url : "Handler_DeleteNote",
										data: { id: deleteNote},
											success : function(result) {
												var status = result.replace(/\s+/g, '');
												if (status == "ok") {
													messageBox("005","Deleted","@action");
													$('#notetitle').prop('disabled', true);
													$('#note').prop('disabled', true);
													$('#viewprm').prop('disabled', true);
													$('#editprm').prop('disabled', true);
													$('#vendorView').prop('disabled', true);
													$('input[name=vendorView]').attr("disabled",true);
													/*swal('Successfully Deleted!',
														'The record has been successfully removed.');*/
													$('.swal2-confirm').click(
														function() {
															$('#table-note').DataTable().row(index).remove().draw();
															//location.reload();
													});
												}else{
						     						swal("Unsuccessfully");
						     					}
											}
										});
								}, function(dismiss) {
									if (dismiss == 'cancel') {
										swal('', 'Cancelled');
									}
							});
						}, 1000);
					}else{
						setTimeout(function(){
			  				$("#viewprm, #editprm").prop("selectedIndex",-1);
						}, 1000);
			  			messageBox("147","delete","@action"); 
			  			$('.formdataNote').trigger('reset');
						$('.formdataNote').data('bootstrapValidator').resetForm();
						$(".formdataNote input").attr('disabled','disabled');
						$('#viewprm, #editprm').prop('disabled', 'disabled');
						$('#note').prop('disabled', true);
					}				
				});
		  		break;
		  	case state = '3':
		  		if(login == getauthor){
		  			setTimeout(function(){
		  				swal({
							text: "Are you sure want to delete?",
							showCancelButton : true,
							confirmButtonColor : '#3085d6',
							cancelButtonColor : '#d33',
							confirmButtonText : 'Yes',
							cancelButtonText : 'No',
							confirmButtonClass : 'confirm-class',
							cancelButtonClass : 'cancel-class',
							closeOnConfirm : false,
							closeOnCancel : false
						}).then(
							function() {
								$.ajax({
									url : "Handler_DeleteNote",
									data: { id: deleteNote},
										success : function(result) {
											var status = result.replace(/\s+/g, '');
											if (status == "ok") {
												messageBox("005","Deleted","@action");
												$('#notetitle').prop('disabled', true);
												$('#note').prop('disabled', true);
												$('#viewprm').prop('disabled', true);
												$('#editprm').prop('disabled', true);
												$('#vendorView').prop('disabled', true);
												$('input[name=vendorView]').attr("disabled",true);
												/*swal('Successfully Deleted!',
													'The record has been successfully removed.');*/
												$('.swal2-confirm').click(
													function() {
														$('#table-note').DataTable().row(index).remove().draw();
														//location.reload();
												});
											}else{
					     						swal("Unsuccessfully");
					     					}
										}
									});
							}, function(dismiss) {
								if (dismiss == 'cancel') {
									swal('', 'Cancelled');
								}
						});
					}, 1000);
		  		}else{
		  			setTimeout(function(){
		  				$("#viewprm, #editprm").prop("selectedIndex",-1);
					}, 1000);
		  			messageBox("147","edit","@action"); 
		  			$('.formdataNote').trigger('reset');
					$('.formdataNote').data('bootstrapValidator').resetForm();
					$(".formdataNote input").attr('disabled','disabled');
					$('#viewprm, #editprm').prop('disabled', 'disabled');
					$('#note').prop('disabled', true);
		  		}
		  		break;
		}
		
		/*swal({
			text: "Are you sure want to delete?",
			showCancelButton : true,
			confirmButtonColor : '#3085d6',
			cancelButtonColor : '#d33',
			confirmButtonText : 'Yes',
			cancelButtonText : 'No',
			confirmButtonClass : 'confirm-class',
			cancelButtonClass : 'cancel-class',
			closeOnConfirm : false,
			closeOnCancel : false
		}).then(
			function() {
				$.ajax({
					url : "Handler_DeleteMono",
					data: { refno: deleteMono,
							loginid : liferayLogin},
						success : function(result) {
							var status = result.replace(/\s+/g, '');
							if (status == "ok") {
								messageBox("005","Deleted","@action");
								swal('Successfully Deleted!',
									'The record has been successfully removed.');
								$('.swal2-confirm').click(
									function() {
										$('#Monograph_table').DataTable().row(index).remove().draw();
										//location.reload();
								});
							}else{
	     						swal("Unsuccessfully");
	     					}else {
								messageBox("131","","@action");
								swal(
									'Not Deleted','This record cannot be deleted.');
							}
						}
					});
			}, function(dismiss) {
				if (dismiss == 'cancel') {
					swal('', 'Cancelled');
				}
		})*/
	});
	
	/////close modal
	 $(".cancelNote, .closeModalNote, .closeNote").click(function () {
         //$("#modalViewNote").modal("hide");
		 $(".modalViewNote").modal('toggle');
     });
	 
	
	$('.modalViewNote').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $('.formdataNote').data('bootstrapValidator').resetForm();
	});
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
});