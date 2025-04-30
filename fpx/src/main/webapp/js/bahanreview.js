$(document).ready(function(){
	
	var today = new Date(); 
	
	//table
	//////table setup
	$('#reviewAccnoTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	$("input[name=radiostat][value='Y']").prop('checked', true);
	
	$('input[name=radiostat]').change(function () {
		//$('#reviewAccnoTable').DataTable().ajax.reload();
		$('#reviewAccnoTable').DataTable().clear().draw();
	});
	
	
	$.get('GetFndPatron', {
        	userID : $("#liferayLogin").val()
	 	}, function(responseJson) {
		if(responseJson != null){
			
			/*if(responseJson==''){
			}*/
			
			$.each(responseJson, function(key,value) {
				
			$("#branch option[value="+value['Branch']).attr('selected','selected');
				
			});
		}
	});
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		
		var radiostat = "Z";//$("input[name='radiostat']:checked").val();
		var actionButon;
		var branch = $( "#branch" ).val();
			
		var t = $('#reviewAccnoTable').DataTable( {
			//dom: 'Bfrtip',
			destroy: true,
			searching: true,
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
		    	url: "ResultFormLaporanBahan",
		        data : {radiostat :  radiostat, brnc : branch},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
			
					var reviewStat = json[i].ReviewStat;
					if(reviewStat == 'N'){
						reviewStat = "Pending Review";
					}else if(reviewStat == 'C'){
						reviewStat = "Complated";
					}
					
					/*if(radiostat == "Y"){
						actionButon = '<button id="ProcessLostAccept" class="btn btn-primary" data-whatever="Proces" title="Accept Report" data-rowid="'+json[i].UserRepNo+'"><span class="fa fa-check"></span></button>'
						+' '+'<button id="ProcessLostCacel" class="btn btn-danger" title="Cancel Report" data-rowid="'+json[i].UserRepNo+'"><span class="fa fa-times"></span></button>';
					}else*/ if(radiostat == "Z"){
						//actionButon = '<button id="ProcessDamage" class="btn btn-primary" data-toggle="modal" data-target="#modalreviewitem" data-whatever="Proces" title="Process" data-rowid="'+json[i].UserRepNo+'"><span class="fa fa-hourglass-half"></span></button>';
						actionButon = '<button id="ProcessDamageAccept" class="btn btn-primary" data-toggle="modal" data-target="#modalreviewitem" data-whatever="Proces" title="Accept Report" data-rowid="'+json[i].UserRepNo+'"><span class="fa fa-check"></span></button>'
						+' '+'<button id="ProcessDamageCacel" class="btn btn-danger" title="Cancel Report" data-rowid="'+json[i].UserRepNo+'"><span class="fa fa-times"></span></button>';
					}

		              return_data.push({
		                'No': (i+1),
						'User Report No' : json[i].UserRepNo,
		                'Acc No' : json[i].AccNo,
						'Title' : json[i].Title,
						'Patron Id' : json[i].Patron,
		                'Patron Name' : json[i].PatronName,
		                'Date Report' : json[i].DateReport,
						'Time Report' : json[i].TimeReport,
						//'Status' : reviewStat,
		                'Action' : actionButon,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data' : 'User Report No'},
					{'data': 'Acc No'},
					{'data': 'Title'},
					{'data': 'Patron Id'},
					{'data': 'Patron Name'},
					{'data': 'Date Report'},
					{'data': 'Time Report'},
					//{'data': 'Status'},
					{'data': 'Action'},
				],
    	});


		//////
		

	});
	
	
	////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	///on click button ProcessLostAccept
	
	function messageBox(langcode, code, criteria, label, reportno, patronid,
						noperolehan, datereport, timereport, datetimereport, buutonfunction, judul, patronname){

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
							  confirmButtonText: 'Yes',
		            		  cancelButtonText: 'No',
		            		  confirmButtonClass: 'confirm-class',
		            		  cancelButtonClass: 'cancel-class',
		            		  closeOnConfirm: false,
		            		  closeOnCancel: false 
							}).then(function(isConfirm){
								
								if(isConfirm){
									
									//alert("buutonfunction"+buutonfunction)

									if(buutonfunction == 'Accept'){

										$.post("ResultModifyItem", {
											reportno : reportno,
											patrid : patronid,
											accno: noperolehan,
											newStat : "L",
											id : $("#liferayLogin").val(),
											datereport : datereport,
											timereport : timereport,
											datetimereport : datetimereport,
											buutonfunction : buutonfunction
										},function(data){
											//alert(data.trim()+"data");
											//var accno = noPerolehan;
			
											//alert("berjaya accept report");
											
											$.get('GetDendaSumm', {
										        	typedenda : "lost",
													patrid : patronid,
													accno: noperolehan,
												 	}, function(responseJson) {
														if(responseJson==''){
													 	}else{					 	
															$.each(responseJson, function(key,value) {	
																
																var newbyrn = parseFloat(value['Bayaran']);
																
																
																								
																$('<tr class="bayarantabletrxn">').append(
														       // $('<td>').text(value['Code']),
																///$('<td>').text("Total"),
														       $('<td>').text(newbyrn.toFixed(2))).appendTo('.tabletrxn')
														       ///$('<td>').text(parseFloat(value['Bayaran']).toFixed(2)).appendTo('.tabletrxn')
														       
															});
														}
											});
											
											swal({
												 //title: '<strong>Maklumat</strong>',
												 //icon: 'info',
												  html:
														'<div class="col-md-12" style="margin-bottom: 5px;">'+
								                         ' <div class="col-md-3">'+
								                            '<label>Details</label>'+
								                            '</div>'+
								                        '</div>'+
								
								
														'<div class="col-md-12" style="margin-bottom: 5px;">'+
															'<div class="col-md-12" style="margin-bottom: 5px;">'+
																'<div class="col-md-12">'+
																	'<table class="table table-bordered table-striped table-hover table-condensed tabletrxn">'+
																		'<thead style="background-color:rgba(52, 73, 94, 0.94);color:#ecf0f1">'+
																			'<tr>'+
																				///'<th width=30%>No</th>'+
																				'<th width=55% align="right">Total</th>'+
																			'</tr>'+
																		'</thead>'+
																		'<tbody>'+
																		'</tbody>'+
																	'</table>'+
																'</div>'+
															'</div>'+
														'</div>',
												showCancelButton: false,
												confirmButtonColor: '#3085d6',
												cancelButtonColor: '#d33',
												confirmButtonText: 'Ok',
										        cancelButtonText: 'No',
										        confirmButtonClass: 'confirm-class',
										        cancelButtonClass: 'cancel-class',
										        closeOnConfirm: false,
										        closeOnCancel: false 
												}).then(function(isConfirm){
													
											
													if(isConfirm){
														///alert("comfirm");
														
														///alert("tajuk"+judul);
														
;
														
													//alert("hntr pay kepada user")
																 
														
																										
														$.post("SetMailOutputBahanRosak",{idsmstempt : "M011", 
																	id : patronid,  tarikh : moment(today).format("DD/MM/YYYY"), 
																	noPerolehan: noperolehan, 
																	rosakcatatan : "",
																	officerid : $("#liferayLogin").val(), tajuk : judul, 
																	typedenda : "lost",amt: "", billno : "", damagestafremarks: ""},
																	
																
	
																function(data){
																	
																	$('#Reterive').click();
													
																}
																).fail(function(data){
																	swal(data+"error");
																	
																}).success(function(data){   
																	swal(data+"success");
														});
													}
																
													 // function when confirm button clicked
												}, function(dismiss){
														alert("dismiss");  
											});													
											
											/*if(data.trim() == "Success"){
												messageBox("002","005","","@action");
												$('#formdataitemdamage').trigger("reset");
												$('#formdataitemdamage').data('bootstrapValidator').resetForm();
												setup();
												
												$.post("SetMailOutput",{idsmstempt : "M001", namapeminjam : namapeminjam,
														noahli : noahli,  title : title, tarikh : tarikh, noPerolehan: noPerolehan,
														phone : phone},
													function(data){
														
														if(data.trim() == "Success"){
															//alert(data+"notification sudah di hantar");
															messageBox("002","007","","");
														}
														
										
													}
													).fail(function(data){
														swal(data+"error");
														
													}).success(function(data){   
														swal(data+"success");
												 });
												
												
											}else{
												alert("fail");
											}*/
											
											
										}
										).fail(function(data){
											swal("error");
										}).success(function(data){   
										
										});

									}else if(buutonfunction == 'Cancel'){
										
										//alert(noperolehan+"dss");

										$.post("ResultCancelReport", {
											reportno : reportno,
											id : $("#liferayLogin").val(),
											buutonfunction : buutonfunction,
											accno: noperolehan,
										},function(data){
											//alert(data.trim()+"data");
											//var accno = noPerolehan;
											
											
											

											if(data.trim() == "Success"){

												messageBox2("001","005","Cancel Report","@action");
												$('#reviewAccnoTable').DataTable().ajax.reload();
												
												
												//alert("patronidpatronidpatronid"+patronid);
									
												
												$.post("SetMailOutputBahanRosakBatal",{idsmstempt : "M004", 
														id : patronid,  tarikh : moment(today).format("DD/MM/YYYY"), 
														noPerolehan: noperolehan, officerid : $("#liferayLogin").val(),
														tajuk : judul,  rosakcatatan : ""},
													function(data){
														
														//alert(data+"apa sini");
														$('#Reterive').click();
														/*if(data.trim() == "Success"){
															//alert(data+"notification sudah di hantar");
															messageBox("002","007","","");
														}*/
														
										
													}
													).fail(function(data){
														swal(data+"errorcancel");
														
													}).success(function(data){   
														swal(data+"success");
												 });
												
												
											}else{

											}
											
											
											/*if(data.trim() == "Success"){
												messageBox("002","005","","@action");
												$('#formdataitemdamage').trigger("reset");
												$('#formdataitemdamage').data('bootstrapValidator').resetForm();
												setup();
												
												$.post("SetMailOutput",{idsmstempt : "M001", namapeminjam : namapeminjam,
														noahli : noahli,  title : title, tarikh : tarikh, noPerolehan: noPerolehan,
														phone : phone},
													function(data){
														
														if(data.trim() == "Success"){
															//alert(data+"notification sudah di hantar");
															messageBox("002","007","","");
														}
														
										
													}
													).fail(function(data){
														swal(data+"error");
														
													}).success(function(data){   
														swal(data+"success");
												 });
												
												
											}else{
												alert("fail");
											}*/
											
											
										}
										).fail(function(data){
											swal("errorllllll");
										}).success(function(data){   
										
										});

									}
									

									
									
									/*if(code == "009"){
										 window.location.replace("https://user.ppj.gov.my/auth/register");
									}else if(code == "002"){
										 window.location.replace("https://cassso.ppj.gov.my/cas/login?service=https%3A%2F%2Fwww.ppj.gov.my%2Fauth%2Fcas%2Flogin");
									}*/
									
								
				
				
									/*if(data.trim() == "Success"){
										messageBox("002","005","","@action");
										$('#formdataitemdamage').trigger("reset");
										$('#formdataitemdamage').data('bootstrapValidator').resetForm();
										setup();
										
										$.post("SetMailOutput",{idsmstempt : "M001", namapeminjam : namapeminjam,
												noahli : noahli,  title : title, tarikh : tarikh, noPerolehan: noPerolehan,
												phone : phone},
											function(data){
												
												if(data.trim() == "Success"){
													//alert(data+"notification sudah di hantar");
													messageBox("002","007","","");
												}
												
								
											}
											).fail(function(data){
												swal(data+"error");
												
											}).success(function(data){   
												swal(data+"success");
										 });
										
										
									}else{
										alert("fail");
									}*/
									
									
								}
								
							   // function when confirm button clicked
							}, function(dismiss){
							   if(dismiss == 'cancel'){
									/*$('#formdataregister').trigger("reset");
									$('#formdataregister').data('bootstrapValidator').resetForm();
									setup();*/
							   }
							})
						});
	
	}
	
	
	function messageBox2(langcode, code, criteria, label){
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
	
	$('#reviewAccnoTable').on( 'click', '#ProcessLostAccept', function (event) {
		
		/*var button = $(event.relatedTarget) // Button that triggered the modal
				var rowid = button.data('rowid');
				alert(rowid+"dffsds");*/
		
		
		var currentRow=$(this).closest("tr"); 

		var reportno=currentRow.find("td:eq(1)").html(); 
		var noperolehan=currentRow.find("td:eq(2)").html(); 
		var judul=currentRow.find("td:eq(3)").html(); 
		var patronid=currentRow.find("td:eq(4)").html(); 
		var patronname=currentRow.find("td:eq(5)").html();
		var datereport=currentRow.find("td:eq(6)").html(); 
		datereport = moment(datereport, 'DD/MM/YYYY').format("YYYYMMDD");
		var timereport=currentRow.find("td:eq(7)").html(); 
		timereport = moment(timereport, 'HH:mm:ss').format("HHmmss");
		var datetimereport = "Y";
		


		///messageBox("001","176","","");
		messageBox("001","176","","", reportno, patronid,
						noperolehan, datereport, timereport, datetimereport, 'Accept', judul, patronname)
		


	});
	
	
	////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	///on click button ProcessLostCacel
	
	$('#reviewAccnoTable').on( 'click', '#ProcessLostCacel, #ProcessDamageCacel', function (event) {


		var currentRow=$(this).closest("tr"); 

		var reportno=currentRow.find("td:eq(1)").html(); 
		var noperolehan=currentRow.find("td:eq(2)").html(); 
		var judul=currentRow.find("td:eq(3)").html(); 
		var patronid=currentRow.find("td:eq(4)").html(); 
		var patronname=currentRow.find("td:eq(5)").html();
	

		messageBox("001","177","","", reportno, patronid,
						noperolehan, "", "", "", 'Cancel', judul, patronname)
	});



	////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	///on click button ProcessDamageCacel
	/*$('#reviewAccnoTable').on( 'click', '#ProcessDamageCacel', function (event) {


		var currentRow=$(this).closest("tr"); 

		var reportno=currentRow.find("td:eq(1)").html(); 
		var noperolehan=currentRow.find("td:eq(2)").html(); 
		var patronid=currentRow.find("td:eq(4)").html(); 
		var datereport=currentRow.find("td:eq(6)").html(); 
		datereport = moment(datereport, 'DD/MM/YYYY').format("YYYYMMDD");
		var timereport=currentRow.find("td:eq(7)").html(); 
		timereport = moment(timereport, 'HH:mm:ss').format("HHmmss");
		var datetimereport = "Y";


		messageBox("001","177","","", reportno, "",
						"", "", "", "", 'Cancel')
	});*/
	
	
	////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	///on click button ProcessDamage
	
	$('#reviewAccnoTable').on( 'click', '#ProcessDamageAccept', function (event) {

		$('.picdmge').prop('src', $('.picdmge').prop("src").replace("?repno", "?repno=?reportno"));
		var currentRow=$(this).closest("tr"); 
		var reportno=currentRow.find("td:eq(1)").html(); 
		var accno=currentRow.find("td:eq(2)").html(); 
		var title=currentRow.find("td:eq(3)").html(); 
		var patrid=currentRow.find("td:eq(4)").html(); 
		var patrname=currentRow.find("td:eq(5)").html(); 
		

		
		
		var datereport=currentRow.find("td:eq(6)").html(); 
		datereport = moment(datereport, 'DD/MM/YYYY').format("YYYYMMDD");
		var timereport=currentRow.find("td:eq(7)").html(); 
		timereport = moment(timereport, 'HH:mm:ss').format("HHmmss");		

		
		$("#reportnumber").val(reportno);
		$("#damageaccno").val(accno);
		$("#damagetitle").val(title);
		$("#damagepatronid").val(patrid);
		$("#damagepatronname").val(patrname);
		
		//console.log($('.picdmge').prop("src").replace("?reportno", reportno)+"reportno");
		///alert($('.picdmge img').attr('src'));
		
		
		$("<img>").attr('src',  $('.picdmge').prop("src").replace("?reportno", reportno))
    	.on('load', () => $(".pictures").show())
    	.on('error', () => $(".pictures").hide());
		
		
		$('.picdmge').prop('src', $('.picdmge').prop("src").replace("?reportno", reportno));
		
		
		$("#damagedatereport").val(datereport);
		$("#damagetimereport").val(timereport);
		
		
	
		

		
		
		$.get('GetDamageDetail', {
        	reportno : reportno,
	 	}, function(responseJson) {
		if(responseJson != null){
			
			
			
			/*if(responseJson==''){
			}*/
			
			$("#detaildamage").val(responseJson);
			
			
		}
	});


		///$("#detaildamage").val();
		
		
		/*$.ajax({
                url: 'ItemReviewPhoto',
                type: 'POST',
                data: {reportno: reportno
                   },
                success:function(data){
					alert(data+"data")
           		 $('#imgtest3').html('<img src="'+data+'"/>');	
                },
              ///  dataType: "text"
        });*/
		
		
		/*$.get('', {
        	reportno : reportno,
	 	}, function(responseJson) {
		if(responseJson != null){
			
			if(responseJson==''){
			
			}
			
			$.each(responseJson, function(key,value) {
				
				alert(value['Nama'])
				//$("#namaPenjaga").val(value['Nama']);

				
			});
		}
		});*/
		
		
		
	});

	////
	////
	$("#damageamount").on("keypress keyup blur",function (event) {
        //this.value = this.value.replace(/[^0-9\.]/g,'');
		 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
	         if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
	             event.preventDefault();
	         }
	         if(($(this).val().indexOf('.') != -1) && ($(this).val().substring($(this).val().indexOf('.'),$(this).val().indexOf('.').length).length>4 )){
			     /*if (event.keyCode !== 8 && event.keyCode !== 46 ){ //exception
			         event.preventDefault();
			     }*/
	         }
    });



	  $(".closebigimg").click(function () {
	    $('#imagemodal').modal('hide');   
	  });
	
	
	
		$('.picdmge').on('click', function() {
			var numberis = $("#reportnumber").val();
	
			///$('.imagepreview').attr("src").replace("?reportno", numberis);
			//$('.imagepreview').attr('src', $(this).find('img').attr('src'));
			//$('.picdmge').attr("src").replace("?reportno", reportno)
			//$('.imagepreview').attr('src', $(this).find('src').attr('src'));
			
			/*alert("ree"+$('.imagepreview2').attr("src").replace("?reportno", numberis));
			$('.imagepreview2').attr("src").replace("?reportno", numberis);*/
			
			$('.picdmge').attr('src', $('.picdmge').attr("src").replace("?reportno", numberis));
	
			$('#imagemodal').modal('show'); 
			//$('.imagepreview').attr('src', $(this).find('img').attr('src'));
	
		});	
		
		
		
		///////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////
		
		$('#formreviewitemdamage').bootstrapValidator({
			framework: 'bootstrap',
			 excluded: [':disabled'],
			 icon: {
	        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
	        //feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
				damageamount: {
		            validators: {
		               notEmpty: {
		                   message: 'Ammount is required'
		               },
		           }
		        },		 
	        }
	    })
	     .on('success.form.bv', function (e) {
	    	 
	    	// Prevent form submission
	        e.preventDefault();
	
	         // Get the form instance
	         var $form = $(e.target);
	
	         // Get the BootstrapValidator instance
	         var bv = $form.data('bootstrapValidator');

			
			var amt = $('input[name="damageamount"]').val();
			var accno = $('input[name="damageaccno"]').val();
			var patronid = $('input[name="damagepatronid"]').val();
			var refer = "Denda buku rosak";

			
			
			////
			var reportno = $("#reportnumber").val();
			var datetimereport = "N";
			var datereport = $("#damagedatereport").val(datereport);
			var timereport = $("#damagetimereport").val(timereport);
			var patronname = $("#damagepatronname").val();
			var officerid = $("#liferayLogin").val();
			var judul = $("#damagetitle").val();
			var damagedetail = $("#detaildamage").val();
			var damagestafremarks = $("#rosakcatatanstafremarks").val();
			
		   //alert("line amt"+damagestafremarks)

	
			$.post("Handle_OfficeAppoveBhnRosak", {reportno: reportno, amt : amt, accno : accno,  patronid : patronid, refer : refer,
						officerid : officerid, damagestafremarks:damagestafremarks}, function(result){
							
					var amt2 = amt;
					result = result.trim();
					
					///alert(amt2);
					
						
																
					var getresultSucess = result.split('=');
					
					//alert(getresultSucess[0]+"shouldbesucess");
			
	
					if(getresultSucess[0] =="Success"){
						
						
						$("#closeModalItemDamage").click();
						//messageBox2("002","004","","");
						///alert("Berjaya daftar");
						///messageBox2("001","005","Submit","@action");
						//messageBox("002","005","Simpan","@action");
						$('#formreviewitemdamage').trigger("reset");
						$('#formreviewitemdamage').data('bootstrapValidator').resetForm();
						
						//reload();
						
						$('#reviewAccnoTable').DataTable().ajax.reload();
						
						
						
						////messageBox("001","176","","", reportno, patronid,
						///accno, datereport, timereport, datetimereport, 'anyelse')
						
						$.get('GetDendaSumm', {
										        	typedenda : "damage",
													patrid : patronid,
													accno: accno,
												 	}, function(responseJson) {
														if(responseJson==''){
													 	}else{					 	
															$.each(responseJson, function(key,value) {		
																var newbyrn = parseFloat(value['Bayaran']);
																
														       							
																$('<tr>').append(
														       // $('<td>').text(value['Code']),
																///$('<td>').text("Total"),
														        //////////////$('<td>').text(value['Bayaran'])).appendTo('.tabletrxn')
														        $('<td>').text(newbyrn.toFixed(2))).appendTo('.tabletrxn')
															});
														}
											});
											
											swal({
												 //title: '<strong>Maklumat</strong>',
												 //icon: 'info',
												  html:
														'<div class="col-md-12" style="margin-bottom: 5px;">'+
								                         ' <div class="col-md-3">'+
								                            '<label>Details</label>'+
								                            '</div>'+
								                        '</div>'+
								
								
														'<div class="col-md-12" style="margin-bottom: 5px;">'+
															'<div class="col-md-12" style="margin-bottom: 5px;">'+
																'<div class="col-md-12">'+
																	'<table class="table table-bordered table-striped table-hover table-condensed tabletrxn">'+
																		'<thead style="background-color:rgba(52, 73, 94, 0.94);color:#ecf0f1">'+
																			'<tr>'+
																				//'<th width=55%>No</th>'+
																				'<th width=55% align="right">Total</th>'+
																			'</tr>'+
																		'</thead>'+
																		'<tbody>'+
																		'</tbody>'+
																	'</table>'+
																'</div>'+
															'</div>'+
														'</div>',
												showCancelButton: false,
												confirmButtonColor: '#3085d6',
												cancelButtonColor: '#d33',
												confirmButtonText: 'Ok',
										        cancelButtonText: 'No',
										        confirmButtonClass: 'confirm-class',
										        cancelButtonClass: 'cancel-class',
										        closeOnConfirm: false,
										        closeOnCancel: false 
												}).then(function(isConfirm){
								
													if(isConfirm){
														$.post("SetMailOutputBahanRosak",{idsmstempt : "M016", 
																	id : patronid,  tarikh : moment(today).format("DD/MM/YYYY"), 
																	noPerolehan: accno, officerid : $("#liferayLogin").val(),
																	tajuk : judul, patronname : patronname,
																	typedenda : "damage3", amt:amt2, rosakcatatan : damagedetail, billno : getresultSucess[1],
																	damagestafremarks : damagestafremarks 
																	},
																	
																	
																	
																	
																	
																function(data){
																	
																	messageBox2("001","005","Submit","@action");
																	$('#Reterive').click();
													
																}
																).fail(function(data){
																	swal(data+"error");
																	
																}).success(function(data){   
																	swal(data+"success");
														});
													}
																
													 // function when confirm button clicked
												}, function(dismiss){
														alert("dismiss");  
											});		
						
						
						/*$.post("SetMailOutputBahanRosak",{idsmstempt : "M005", 
														id : patronid,  tarikh : moment(today).format("DD/MM/YYYY"), noPerolehan: noperolehan, officerid : $("#liferayLogin").val()},
													function(data){
														
														
														
										
													}
													).fail(function(data){
														swal(data+"error");
														
													}).success(function(data){   
														swal(data+"success");
											});*/
						
				
						
						
						
						
						
						
					}
	  		});
	
		});
	

	
});