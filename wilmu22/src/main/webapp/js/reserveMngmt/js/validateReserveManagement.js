$(document).ready(
		function() {	
			
			var table = $('#RCtable').DataTable({
				destroy: true,
				searching: false,
				bLengthChange: false,
				autoWidth: false,
				responsive: true,
				aaSorting: [],
				processing: true,
			});

			  var enableDisableSubmitBtn = function() {
				    var startVal = $('#startDate').datepicker('getDate');
				    var endVal = $('#endDate').datepicker('getDate');

				    var disableBtn = startVal == null || startVal.length == 0 || endVal == null || endVal.length == 0;
				    $('#retrieveBtn').attr('disabled', disableBtn);
				  }

				  $('#startDate').datepicker({
				    format: "dd/mm/yyyy",
				    todayBtn: true,
				    autoclose: true,
				    todayHighlight: true
				  });


				  $('#endDate').datepicker({
				    format: "dd/mm/yyyy",
				    todayBtn: true,
				    autoclose: true,
				    todayHighlight: true

				  });

				  $('#startDate').on('changeDate', function(event) {
				
				    enableDisableSubmitBtn();
				  })

				  $('#endDate').on('changeDate', function(event) {
			
				    enableDisableSubmitBtn();
				  })
			
			$('#userDefineRB').click(function()
					{
					  $('#dateFrom').removeAttr("disabled");
					  $('#dateTo').removeAttr("disabled");
					  $('#fetchListDD').prop('disabled', true);
					});
			
			$('#fetchListRB').click(function()
					{
					  $('#retrieveBtn').removeAttr("disabled");
					  $('#dateFrom').prop('disabled', true);
					  $('#dateTo').prop('disabled', true);
					  $('#fetchListDD').removeAttr("disabled");
					});


			$('#retrieveBtn').click(function(){ 
				
				var startDate = document.getElementById("dateFrom").value;
				var endDate = document.getElementById("dateTo").value;
		  	
			  	var startDateSplit = startDate.split('/');
			  	var endDateSplit = endDate.split('/');
			 // Month is zero-indexed so subtract one from the month inside the constructor
				 var formattedStartDate = new Date(startDateSplit[2], startDateSplit[1] - 1, startDateSplit[0]); //Y M D 
				 var formattedEndDate = new Date(endDateSplit[2], endDateSplit[1] - 1, endDateSplit[0]); //Y M D 
			 
				 var startTimeStamp = formattedStartDate.getTime();
				 var endTimeStamp = formattedEndDate.getTime();
		 
			    if (endTimeStamp < startTimeStamp) {
				      document.getElementById("dateTo").value = "";
			    }else{
		    		var table = $('#RCtable').DataTable( {
						destroy: true,
						searching: false,
						bLengthChange: false,
						autoWidth: false,
						responsive: true,
						aaSorting: [],
						processing: true,
		    		    ajax: {
		    		    	url: "RetrieveRC",
		    		        data : { userDefineRB : $('#userDefineRB').is(":checked"), fetchListRB : $('#fetchListRB').is(":checked"), dateFrom : $('#startDate input[type=text]').val(), dateTo : $('#endDate input[type=text]').val(), fetchList : $('#fetchListDD :selected').val() },
		    		        type: "GET",
		    		        dataSrc: function (data) {
		    		        	
		    		        	var return_data = new Array();
		    		        	$.fn.dataTable.ext.errMode = 'none';
	
		    		            for( var i=0;i< data.length; i++) {		    		            
		    		            	var stat = data[i].status;
		    						return_data.push({
		    							'Course': data[i].course,   
		    							'Semester' : data[i].semester,           
		    							'Subject' : data[i].subject,
		    							'Instructor' : data[i].instructor,
		    							'Control No': data[i].controlNo,   
		    							'Title' : data[i].title,           
		    							'Start Date' : data[i].startDate,	
		    							'End Date' : data[i].endDate,
		    							'Reserve No' : data[i].reserveNo,
		    							'Action' : '<button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+data[i].reserveNo+'"><span class="glyphicon glyphicon-trash" title="Delete Reservation" ></span></button>',
		    						})
		    		            }
		    		            return return_data;
		    		          },
		    		     },
		    	     	columns    : [
		    				{'data': 'Course'},
		    				{'data': 'Semester'},
		    				{'data': 'Subject'},
		    				{'data': 'Instructor'},
		    				{'data': 'Control No'},
		    				{'data': 'Title'},
		    				{'data': 'Start Date'},
		    				{'data': 'End Date'},
		    				{'data': 'Reserve No'},
		    				{'data': 'Action'},
		    			],
		    	        'dom': 'Rlfrtip',
		    	        'colReorder': {
		    	            'allowReorder': false
		    	        },
		    		});
			    
		//    		new $.fn.dataTable.ColReorder(table);
			    }
			});
				
				
			var count1 = 0;
			$('#fetchListDD').click(function(){ 	
				if(count1==0){
	
					$.ajax({		
						url : 'FetchList',
						success : function(data1) {
							if (data1) {
										
								$.each(data1, function(key, entry) {
									$('#fetchListDD').append(
										$("<option />").val(entry)
											.text(entry));
											
									})
								}
							}
						});
						count1++;
						}
					})
					
					
			$('#RCtable').on('click', '#Delete', function () {
				
				var deleteSelectedRsrv = $(this).attr('data-id');
				
				var index = $('#RCtable').DataTable().rows({ search: 'applied'})
				.nodes().to$().index($(this).closest('tr'));
				
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
							url : "Handler_DeleteRsrv",
							data: { reserveNo: deleteSelectedRsrv},
								success : function(result) {
									var status = result.replace(/\s+/g, '');
									if (status == "true") {
										messageBox("005","Deleted","@action");
										$('.swal2-confirm').click(
											function() {
												$('#RCtable').DataTable().row(index).remove().draw();
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
				})
			});
	//***********************************END AREA FOR DELETE ********************************************// 
					
			$('#newBtn').click(function(){ 
				 $('#dateFrom').val(""); 
				 $('#dateTo').val(""); 
				 $('#RCtable').dataTable().fnClearTable();
				
			})
			
		});
