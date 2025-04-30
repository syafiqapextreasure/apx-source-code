$(document).ready(function(){
	
	var today = new Date(); 
	///getpatronid
	
	
	////LOAD TABLE 
	var t = $('.tblPinjaman').DataTable( {
			//dom: 'Bfrtip',
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "GetLoadtblPinjaman",
		        data : {idlogin : $("#liferayLogin").val()},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
			
					if(json[i].Status == ""){
						
					}

		              return_data.push({
		                'No': (i+1),
		                'No Perolehan' : json[i].NoPerolehan, 
	                	'Judul' : json[i].Judul,
	                	'Tarikh Pinjaman' : json[i].TarikhPinjam,
	                	'Tarikh Pulangan' : json[i].TarikhPulang,
						//'Status' : json[i].Status,
						'Tindakan' : '<button id="bahanhilang" class="btn btn-primary" data-toggle="modal" data-target="#modalbhnhilang" data-whatever="Laporan Bahan Hilang" title="Laporan Bahan Hilang" data-rowid="'+json[i].NoPerolehan+'" data-judul="'+json[i].Judul+'"><span class="fa fa-file-excel-o"></span></button>'
						//'<button class="btn btn-primary" id="bahanhilang"><span class="fa fa-file-excel-o" style="color:white" data-whatever="Bahan Hilang" title="Bahan Hilang" data-rowid="'+json[i].NoPerolehan+'" data-judul="'+json[i].Judul+'"></span></button>'
									+" "
									+'<button id="bahanrosak" class="btn btn-primary" data-toggle="modal" data-target="#modalbhnrosak" data-whatever="Laporan Bahan Rosak" title="Laporan Bahan Rosak" data-rowid="'+json[i].NoPerolehan+'" data-judul="'+json[i].Judul+'"><span class="fa fa-file-archive-o"></span></button>'
									
									//'<button  class="btn btn-primary" id="bahanrosak" data-toggle="modal" data-target="#modalbhnrosak" data-whatever="Bahan Rosak"><span class="fa fa-file-archive-o" style="color:white" title="Bahan Rosak" data-rowid="'+json[i].NoPerolehan+'"></span></button>',
		                //'Action' : '<button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalpatronpreview" data-whatever="Edit" title="Preview Patron" data-rowid="'+json[i].PatronId+'"><span class="glyphicon glyphicon-pencil"></span></button>',
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'No Perolehan'},
					{'data': 'Judul'},
					{'data': 'Tarikh Pinjaman'},
					{'data': 'Tarikh Pulangan'},
					//{'data': 'Status'},
					{'data': 'Tindakan'},
				],
				 columnDefs: [
		            /*{
		                "targets": [ 3 ],
		                "visible": false,
		            },*/
		        ],
				/*drawCallback: function( settings ) {
					$('.tblPinjaman tbody tr').each(function() {
				          var Cell = $(this).find('td:eq(3)');
				          debugger;

				         if((Cell.text() == '')){
				        	  $(this).find('#bahanhilang').prop("disabled", false);
				        	  $(this).find('#bahanrosak').prop("disabled", false);
				          }else{
				        	  $(this).find('#bahanhilang').prop("disabled", true);
				        	  $(this).find('#bahanrosak').prop("disabled", true);
				          }
				        });
			    }*/
    	});


		////table hilang 
		/*var t2 = $('.tblBahanHilang').DataTable( {
			//dom: 'Bfrtip',
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "GetLoadtblBahanHilang",
		        data : {idlogin : $("#liferayLogin").val(), action : 'lost'},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'No Perolehan' : json[i].NoPerolehan, 
	                	'Judul' : json[i].Judul,
						'Status' : json[i].Status,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'No Perolehan'},
					{'data': 'Judul'},
					{'data': 'Status'},
				],
    	});*/



		////table rosak 
		/*var t3 =$('.tblBahanRosak').DataTable( {
			//dom: 'Bfrtip',
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "GetLoadTableBhnRosak",
		        data : {idlogin : $("#liferayLogin").val(), action : 'damage'},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){


		              return_data.push({
		                'No': (i+1),
		                'No Perolehan' : json[i].NoPerolehan, 
	                	'Judul' : json[i].Judul,
						'Status' : json[i].Status,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'No Perolehan'},
					{'data': 'Judul'},
					{'data': 'Status'},
				],
    	});*/


		//////on click button bahanhilang
		$('#modalbhnhilang').on('show.bs.modal', function (event) {
			
			var button = $(event.relatedTarget) // Button that triggered the modal
			var modal = $(this);
			var recipient = button.data('whatever'); // Extract info from data-* attributes
			var rowid = button.data('rowid');
			var judul = button.data('judul');
			
			modal.find('.modal-title').text(recipient);
			
			switch(recipient){
				
		  		case state = 'Laporan Bahan Hilang':
				$("#hilangnoperolehan").val(rowid);
				$("#hilangjudul").val(judul);
				
				$.get('GetCirDateTime', {
		        	accno : rowid,
					idlogin : $("#liferayLogin").val()
				 	}, function(responseJson) {
						if(responseJson==''){
					 	}else{					 	
							$.each(responseJson, function(key,value) {
								
								$("#hilangtarikhpinjam").val(value['Bdate']);
								$("#hilangmasapinjam").val(value['Btime']);
								$("#hilangtarikhpulang").val(value['Ddate']);
								$("#hilangmasapulang").val(value['Dtime']);
								
								
								$.get('GetBookCost', {
									 	accno : rowid,
										idlogin : $("#liferayLogin").val()
									 	}, function(responseJson) {
										$("#hilanghargabuku").val(responseJson);
								});
								
								
							});
						}
				});
				
				
				
				
				
				$.get("Global?type=Handler&name=Error_Page",{
					GL79LANGCODE : "002",
					GL79ERRCODE : "012",
					criteria : "",
				 	label : ""},function(result){
				 		//alert(result);
						//swal('',result, 'info' );
						///swal(result);
						$(".errmsg").text(result);
				});
				
		  	}

		});
		
		
		function messageBox(langcode, code, criteria, label){
			$.get("Global?type=Handler&name=Error_Page",{
				GL79LANGCODE : langcode,
				GL79ERRCODE : code,
				criteria : criteria,
			 	label : label},function(result){
			 		//alert(result);
					//swal('',result, 'info' );
					///swal(result);
					swal({
							  //title: 'Are you sure?',
							  text: result,
							  //type: 'warning',
							  showCancelButton: false,
							  confirmButtonColor: '#3085d6',
							  confirmButtonText: 'Ok',
		            		  confirmButtonClass: 'confirm-class',
		            		  closeOnConfirm: false,
							}).then(function(isConfirm){

								if(isConfirm){

									$.post("SetMailOutputBahanRosak",{idsmstempt : "M003", 
															 tarikh : moment(today).format("DD/MM/YYYY"),
																	id : $("#liferayLogin").val(),
																	/*namaAhli : namaAhli,
																	ic : ic,
																	emailAhli : emailAhli,  phone : phone,
																	branch : branch*/},
																function(data){
																	
																	/*if(data.trim() == "Success"){
																		///alert(data+"notification sudah di hantar");
																		messageBox("002","007","","");
																	}*/
																	
													
																}
																).fail(function(data){
																	swal(data+"error");
																	
																}).success(function(data){   
																	swal(data+"success");
															 });
									/*if(code == "009"){
										 window.location.replace("https://user.ppj.gov.my/auth/register");
									}else if(code == "002"){
										 window.location.replace("https://cassso.ppj.gov.my/cas/login?service=https%3A%2F%2Fwww.ppj.gov.my%2Fauth%2Fcas%2Flogin");
									}*/
								}
								
							   // function when confirm button clicked
							}, function(dismiss){
							  /* if(dismiss == 'cancel'){
									$('#formdataregister').trigger("reset");
									$('#formdataregister').data('bootstrapValidator').resetForm();
									setup();
							   }*/
							})
						});
			
			
	}
		
		
		
		
		
		
		
		
		$( "#modalbhnhilang #save" ).click(function() {

			$.ajax({
	    			  method: "POST",
	    			  url: "SaveUSRR",
	    			  data: { noperolehan: $("#hilangnoperolehan").val(), action : "bahanhilang", idlogin : $("#liferayLogin").val()},
						success: function (data, status, xhr) { 
							
							if(status == "success"){
									$("#closeModalAddHilang").click();
									$('.tblPinjaman').DataTable().ajax.reload();
									$('.tblBahanHilang').DataTable().ajax.reload();
									$('.tblBahanRosak').DataTable().ajax.reload();
									//alert("jayanya");
									messageBox("002","013","","");
									///////tambah notofi kat sini
							}
						},
	    			}).done(function( msg ) {    			    
	    	})

			
		});

		
		
		//////on click button bahanrosak
		$('#modalbhnrosak').on('show.bs.modal', function (event) {
			
			var button = $(event.relatedTarget) // Button that triggered the modal
			var modal = $(this);
			var recipient = button.data('whatever'); // Extract info from data-* attributes
			var rowid = button.data('rowid');
			var judul = button.data('judul');
			
			modal.find('.modal-title').text(recipient);
			
			
			switch(recipient){
		  		case state = 'Laporan Bahan Rosak':
				$("#rosaknoperolehan").val(rowid);
				$("#rosakjudul").val(judul);
				
		  	}

		});
		
		
		////validation submit form
	$('#formbahanrosak').bootstrapValidator({
		framework: 'bootstrap',
		 excluded: [':disabled',':hidden'],
		 icon: {
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        //feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
			rosakcatatan: {
                validators: {
                    notEmpty: {
                        message: 'Butiran Kerosakan diperlukan'
                    }
                }
            },
			/*pickerosakan: {
                        validators: {
	 					notEmpty: {
                       	 	message: 'Bukti kerosakan diperlukan'
                    	},
                            file: {
                                extension: 'jpg,bmp',
               					type: 'image/jpeg,image/bmp',
                                maxSize: 5 * 1024 * 1024, // 5 MB
                                message: 'The selected file is not valid, it should be (jpg,bmp)'
                            }
                        }
            },*/
        }
    })
     .on('success.form.bv', function (e) {
	
    	// Prevent form submission
         e.preventDefault();

         // Get the form instance
         var $form = $(e.target);

         // Get the BootstrapValidator instance
         var bv = $form.data('bootstrapValidator');
         
         //get title
		 var inputForm = $form.serialize();
	
		 var noperolehan = $('input[name="rosaknoperolehan"]').val();
		 var rosakcatatan = $('#rosakcatatan').val();
		 var action = "bahanrosak";
		 var idlogin = $("#liferayLogin").val();
		
	
		 var fd = new FormData();
         var filespickerosakan = $('#pickerosakan')[0].files[0];



		 fd.append('noperolehan',noperolehan);
	 	 fd.append('rosakcatatan',rosakcatatan);
		 fd.append('action',action);	
         fd.append('filespickerosakan',filespickerosakan);
		 fd.append('idlogin',idlogin);
	
	

				$.ajax({
	    			  method: "POST",
	    			  url: "SaveUSRR",
	    			  data: fd,
					  cache: false,
	        		  contentType: false,
	        		  processData: false,
						success: function (data, status, xhr) { // after success your get data

						        if(status == "success"){
					
									$("#closeModalAdd").click();
									$('#formbahanrosak').trigger("reset");
									$('#formbahanrosak').data('bootstrapValidator').resetForm();
									
									///alert("Maklumat kehilangan/kerosakan telah direkordkan. Anda akan menerima notifikasi pengesahan laporan");
									messageBox("002","013","","");
									
									$('.tblPinjaman').DataTable().ajax.reload();
									$('.tblBahanHilang').DataTable().ajax.reload();
									$('.tblBahanRosak').DataTable().ajax.reload();
									
									
									/*$.get("Global?type=Handler&name=Error_Page",{
										GL79LANGCODE : "002",
										GL79ERRCODE : "013",
										criteria : "",
									 	label : ""},function(result){
											swal({
													  //title: 'Are you sure?',
													  text: result,
													  //type: 'warning',
													  showCancelButton: false,
													  confirmButtonColor: '#3085d6',
													 /// cancelButtonColor: '#d33',
													  confirmButtonText: 'Ok',
								            		 /// cancelButtonText: 'Tidak',
								            		  confirmButtonClass: 'confirm-class',
								            		 //// cancelButtonClass: 'cancel-class',
								            		  closeOnConfirm: false,
								            		  closeOnCancel: false 
													}).then(function(isConfirm){
														
														if(isConfirm){
															
															$.post("SetMailOutputBahanRosak",{idsmstempt : "M003", 
															 tarikh : moment(today).format("DD/MM/YYYY"),
																	id : $("#liferayLogin").val(),},
																function(data){
													
																}
																).fail(function(data){
																	swal(data+"error");
																	
																}).success(function(data){   
																	swal(data+"success");
															 });
														
														}
														
													   // function when confirm button clicked
													}, function(dismiss){
													  
													})
												});*/
									
									
									
									
								}
						    },
	    			}).done(function( msg ) {
		
						
	    			    
	    		});
	
	
	  

	});
		

	
});