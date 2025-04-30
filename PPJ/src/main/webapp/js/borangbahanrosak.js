$(document).ready(function(){
	
	$(".toremove").remove();
	var today = new Date(); 
	var idlogin = $("#liferayLogin").val();
	
	///getpatronid
	
	$.fn.dataTable.ext.errMode = 'throw';
	////LOAD TABLE 
	//console.log(t);
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
		            	//console.log(return_data);
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
		            });
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

		//$.fn.dataTable.ext.errMode = 'throw';
		////table hilang 
		var t2 = $('.tblBahanHilang').DataTable( {
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
				//timeout: 3000,
		        data : {idlogin : idlogin, action : 'lost'},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
			

		              return_data.push({
		                'No': (i+1),
		                'No Perolehan' : json[i].NoPerolehan, 
	                	'Judul' : json[i].Judul,
						'Status' : json[i].Status,
		            });
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
    	});

		//$.fn.dataTable.ext.errMode = 'throw';

		////table rosak 
		var t3 =$('.tblBahanRosak').DataTable( {
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
				//timeout: 5000,
		        data : {idlogin : idlogin, action : 'damage'},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'No Perolehan' : json[i].NoPerolehan, 
	                	'Judul' : json[i].Judul,
						'Status' : json[i].Status,
		            });
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
    	});


		//////on click button bahanhilang
		$('#modalbhnhilang').on('show.bs.modal', function (event) {
			
			var button = $(event.relatedTarget); // Button that triggered the modal
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
										var newamount = parseFloat(responseJson);
										$("#hilanghargabuku").val(newamount.toFixed(2));  
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
		
		
		function messageBox2(langcode, code, criteria, label){
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
							  showCancelButton: true,
							  confirmButtonColor: '#3085d6',
							  confirmButtonText: 'Ya',
		            		  confirmButtonClass: 'confirm-class',
		            		  cancelButtonText: "Tidak",
		            		  closeOnConfirm: false,
							}).then(function(isConfirm){

								if(isConfirm){

									///alert("zzzzz");
									
									var accno = $("#hilangnoperolehan").val();
									var titlelost = $("#hilangjudul").val();
									var dateborrowlost = $('#hilangtarikhpinjam').val();
									var timeborrowlost = $("#hilangmasapinjam").val();
									var duedate = $("#hilangtarikhpulang").val();
									var costlost =$("#hilanghargabuku").val();
									
									
							
									
									$.ajax({
							    			  method: "POST",
							    			  url: "SaveUSRR",
							    			  data: { noperolehan: accno, action : "bahanhilang", idlogin : idlogin},
												success: function (data, status, xhr) { 
													
													if(status == "success"){
														   /* $("#modalbhnhilang #save").prop("disabled", false);
															$("#closeModalAddHilang").click();
															$('.tblPinjaman').DataTable().ajax.reload();
															$('.tblBahanHilang').DataTable().ajax.reload();
															$('.tblBahanRosak').DataTable().ajax.reload();*/
															//alert("jayanya");
															///messageBox("002","013","","");
															
															$.post("ResultModifyItem", {
																patrid : idlogin,
																accno: accno,
																newStat : "L",
																id : $("#liferayLogin").val(),
																datetimereport : 'Y'
															},function(data){
																
																data = data.trim();
																
																//alert(data);
						
																
																var getbillno = data.split(',');
																//alert(getbillno);
																//alert(getbillno[1]);
																//alert(getbillno[2]);
																
																//var accno = noPerolehan;
								
																//alert("berjaya accept report");
																
																//alert(titlelost+"titlelost");
																
																
																//////pending email tak sent lagi
																$.post(/*"SetMailOutputBahanHilang",{idsmstempt : "M005", idlogin : idlogin,
																		accnolost : accno,
																		titlelost : titlelost,  dateborrowlost : dateborrowlost, timeborrowlost : timeborrowlost,
																		duedate : duedate, costlost : costlost*/
																		"SetMailOutputBahanRosak",{idsmstempt : "M011", 
																	id : idlogin,  tarikh : moment(today).format("DD/MM/YYYY"), 
																	noPerolehan: accno, 
																	officerid : $("#liferayLogin").val(), tajuk : titlelost, //patronname : idlogin,
																	typedenda : "lost",amt: "",  rosakcatatan : "", billno : getbillno[1], damagestafremarks: ""},
																	function(data){
																		
																		if(data.trim() == "Success"){
																			///alert(data+"notification sudah di hantar");
																			///messageBox("002","007","","");
																			messageBox3("002", "031","","", getbillno[2]);
																		}else{
																			swal("something wrong");
																		}
											 					});	
											 					
											 					$("#modalbhnhilang #save").prop("disabled", false);
																$("#closeModalAddHilang").click();
																$('.tblPinjaman').DataTable().ajax.reload();
																$('.tblBahanHilang').DataTable().ajax.reload();
																$('.tblBahanRosak').DataTable().ajax.reload();										
																
																
															}
															).fail(function(data){
																swal("error");
															}).success(function(data){   
															
															});
															///////tambah notofi kat sini
															
															
															
															
													}
												},
							    			}).done(function( msg ) {    			    
							    	});
								
								}
								
							   // function when confirm button clicked
							}, function(dismiss){
		
									//alert("not zzzzz");
									$("#modalbhnhilang #save").prop("disabled", false);

							});
						});
			
			
		}
		
		
		/*function messageBox(langcode, code, criteria, label){
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
							});
						});
			
			
	}*/
	
	
	function messageBox(langcode, code, criteria, label){
			$.get("Global?type=Handler&name=Error_Page",{
				GL79LANGCODE : langcode,
				GL79ERRCODE : code,
				criteria : criteria,
			 	label : label},function(result){
			 		//alert(result);
					//swal('',result, 'info' );
					swal(result);
			});
			
			
	}
	
	function messageBox3(langcode, code, criteria, label,billno){
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
							  showCancelButton: true,
							  confirmButtonColor: '#3085d6',
							  cancelButtonColor: '#d33',
							  confirmButtonText: 'Ya',
		            		  cancelButtonText: 'Tidak',
		            		  confirmButtonClass: 'confirm-class',
		            		  cancelButtonClass: 'cancel-class',
		            		  closeOnConfirm: false,
		            		  closeOnCancel: false 
							}).then(function(isConfirm){
								
								if(isConfirm){
									//alert("testing here");
									///window.location.replace(billno);
									//window.location.href = billno;
									window.open(billno, '_blank');
									///$("#renewMembershipBtn").hide();
								}
								
							   // function when confirm button clicked
							}, function(dismiss){
							   if(dismiss == 'cancel'){
									//reload();
									//$('#formdataregister').trigger("reset");
									//$('#formdataregister').data('bootstrapValidator').resetForm();
									//setup();
									//$("#renewMembershipBtn").hide();
							   }
							})
						});
			
			
	}
		
		
		
		
		
		
		
		
		$( "#modalbhnhilang #save" ).click(function() {
			
			$("#modalbhnhilang #save").prop("disabled", true);
			
			var accnolost = $("#hilangnoperolehan").val();
			var titlelost = $("#hilangjudul").val();
			var dateborrowlost = $('#hilangtarikhpinjam').val();
			var timeborrowlost = $("#hilangmasapinjam").val();
			var duedate = $("#hilangtarikhpulang").val();
			var costlost =$("#hilanghargabuku").val();
			//idlogin
			
			messageBox2("002","021","","");
			
			/*$.ajax({
	    			  method: "POST",
	    			  url: "SaveUSRR",
	    			  data: { noperolehan: $("#hilangnoperolehan").val(), action : "bahanhilang", idlogin : idlogin},
						success: function (data, status, xhr) { 
							
							if(status == "success"){
								    $("#modalbhnhilang #save").prop("disabled", false);
									$("#closeModalAddHilang").click();
									$('.tblPinjaman').DataTable().ajax.reload();
									$('.tblBahanHilang').DataTable().ajax.reload();
									$('.tblBahanRosak').DataTable().ajax.reload();
									//alert("jayanya");
									messageBox("002","013","","");
									
									///////tambah notofi kat sini
									
									$.post("SetMailOutputBahanHilang",{idsmstempt : "M005", idlogin : idlogin,
											accnolost : accnolost,
											titlelost : titlelost,  dateborrowlost : dateborrowlost, timeborrowlost : timeborrowlost,
											duedate : duedate, costlost : costlost},
										function(data){
											
											if(data.trim() == "Success"){
												///alert(data+"notification sudah di hantar");
												////messageBox("002","007","","");
											}else{
												swal("something wrong");
											}
				 					});
									
									
							}
						},
	    			}).done(function( msg ) {    			    
	    	});*/
			
			
			
			
		});

		
		
		//////on click button bahanrosak
		$('#modalbhnrosak').on('show.bs.modal', function (event) {
			
			var button = $(event.relatedTarget); // Button that triggered the modal
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
		 var title =$("#rosakjudul").val();

	
		 var fd = new FormData();
         /*var filespickerosakan = $('#pickerosakan')[0].files[0];*/



		 fd.append('noperolehan',noperolehan);
	 	 fd.append('rosakcatatan',rosakcatatan);
		 fd.append('action',action);	
         ////fd.append('filespickerosakan',filespickerosakan);
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
									
									
									///alert(title+$("#rosakjudul").val());

									$.post("SetMailOutputBahanRosak",{idsmstempt : "M007", 
																	id : idlogin,  tarikh : moment(today).format("DD/MM/YYYY"), 
																	noPerolehan: noperolehan, 
																	officerid : $("#liferayLogin").val(), tajuk : title, //patronname : idlogin,
																	typedenda : "damage2",amt: "",  rosakcatatan : rosakcatatan, billno : "", damagestafremarks : ""},
											
								

										function(data){
											
											if(data.trim() == "Success"){
												///alert(data+"notification sudah di hantar");
												////messageBox("002","007","","");
											}else{
												swal("something wrong");
											}
				 					});
									
									
									
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