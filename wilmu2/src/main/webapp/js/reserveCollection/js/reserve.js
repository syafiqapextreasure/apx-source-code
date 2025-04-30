$(document).ready(function() {

	   $(".search").click(function(){
		   
		   var action = $( 'input[name=actiontype]:checked' ).val();
		   var charge ='charge';

			   var url = "Modal_PatrSearch?action=" + charge;
			
			 $.get(url,function(data){
				
				 $("#searchPatrContent").html(data);
			   });
		   });
	
	var todayDate = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	
	$('#dateRecorded').text(moment(todayDate).format("DD/MM/YYYY"));
	$("#recordedBy").text(liferayLogin);
	
	var tableRCMaster = $('#rsvCollectionMaster_table').DataTable();
	
	var action = $('#action').val();
	
    $('input[type="text"]').on('keyup', function () {

    	var instructorTF = $("#instructorTF").val();
        var controlNo = $('#controlNo').val();
		var startVal = $('#startDate').datepicker('getDate');
		 var endVal = $('#endDate').datepicker('getDate');
        
        if (courseCodeId1 != '' && instructorTF != '' && controlNo != '' && startVal !='') {
        	alert("testing1234");
            $('#okBtn').attr('disabled', false);
        }
    });

	$.ajax({
		url : 'GetCourseCodeId',

		success : function(data) {
			if (data) {
				$.each(data, function (_0, entry) {
					$('#courseCodeId1').append($("<option />").val(entry).text(entry));
				});
				$('#courseCodeId1').val('');
				
				if (action == 'edit') {
				    
					   $.ajax({
				            url: "GetStartDateEndDateInEdit",
				            data: {
				                reserveNo: selectedRowValue[11]
				            },
				            
				            type: "GET",
				            success: function(data) {
				             
								$('#startDate').val(data[0].startDate);
								$('#endDate').val(data[0].endDate);

				            }
				        });				    
					 
					$('#courseCodeId1').val(selectedRowValue[1]);
			        $('#instructorTF').val(selectedRowValue[7]);
			        $('.ctrlno').val(selectedRowValue[8]);
					$('#accessionNo').val(selectedRowValue[9]);
					
			        $.ajax({
			            url: "GetAccessionNoByReserveId",
			            data: {reserveNo: selectedRowValue[11]},
			            type: "GET",
			            success: function(data) {
			                
			                if (data) {
		            			$.each(data, function (key, entry) {
		            				$('#accessionNo').append($("<option />").val(entry).text(entry));
		            			})
			                }
			            }
			        });
			        
					   $.ajax({
				            url: "GetBookDetail",
				            data: {
				                controlNo: selectedRowValue[8]
				            },
				            type: "GET",
				            success: function(data) {

				                $('#callNo').val(data[0].callNo);
				                $('#title').val(data[0].title);
				                $('#author').val(data[0].author);
				                $('#publication').val(data[0].publication);
				                $('#location').val(data[0].location);
				                $('#branch').val(data[0].branch);
				            }
				        });

				        var tableViewItemDetail = $('#viewItemTable').DataTable({
				            destroy: true,
				            searching: false,
				            bLengthChange: false,
				            autoWidth: false,
				            responsive: true,
				            aaSorting: [],
				            processing: true,
				            ajax: {
				                url: "GetAllAccessionNo",
				                data: {
				                    controlNo: selectedRowValue[8]
				                },
				                type: "GET",
				                dataSrc: function(data) {

				                    var return_data = new Array();
				                    $.fn.dataTable.ext.errMode = 'none';

				                    for (var i = 0; i < data.length; i++) {
				                        var stat = data[i].status;
				                        return_data.push({
				                            'Accession No': data[i].accNo,
				                            'Location': data[i].location,
				                            'Branch': (data[i].branch),
				                            'Item Category': data[i].itemCat,
				                            'SMD': data[i].smd,
				                            'Status': (data[i].status),
				                            'Patron ID': (data[i].partonId),
				                        });
				                    }
				                    return return_data;
				                },
				            },
				            columns: [
				            	{
				                    'data': 'Accession No'
				                },
				                {
				                    'data': 'Location'
				                },
				                {
				                    'data': 'Branch'
				                },
				                {
				                    'data': 'Item Category'
				                },
				                {
				                    'data': 'SMD'
				                },
				                {
				                    'data': 'Status'
				                },
				                {
				                    'data': 'Patron ID'
				                },
				            ],
				            'dom': 'Rlfrtip',
				            'colReorder': {
				                'allowReorder': false
				            },
				        });

					
				}else if (action =='delete'){

/*			        $.ajax({
			            url: "GetAccessionNo",
			            data: {
			                controlNo: $('#controlNo').val()
			            },
			            type: "GET",
			            success: function(data) {

			                $.each(data, function(key, entry) {
			                    $('#accessionNo').append($("<option />").val(entry).text(entry));
			                });
			            }
			        });*/
				    
			        $.ajax({
			            url: "GetAccessionNoByReserveId",
			            data: {reserveNo: selectedRowValue[11]},
			            type: "GET",
			            success: function(data) {
			                
			                if (data) {
		            			$.each(data, function (key, entry) {
		            				$('#accessionNo').append($("<option />").val(entry).text(entry));
		            			})
			                }
			            }
			        });
			        
					   $.ajax({
				            url: "GetStartDateEndDateInEdit",
				            data: {
				                reserveNo: selectedRowValue[11]
				            },
				            type: "GET",
				            success: function(data) {
				             
								$('#startDate').val(data[0].startDate);
								$('#endDate').val(data[0].endDate);

				            }
				        });		
				    
					$('#courseCodeId1').val(selectedRowValue[1]);
			        $('#instructorTF').val(selectedRowValue[7]);
					$('#controlNo').val(selectedRowValue[8]);
					$('#accessionNo').val(selectedRowValue[9]);
						
					   $.ajax({
				            url: "GetBookDetail",
				            data: {
				                controlNo: selectedRowValue[8]
				            },
				            type: "GET",
				            success: function(data) {

				                $('#callNo').val(data[0].callNo);
				                $('#title').val(data[0].title);
				                $('#author').val(data[0].author);
				                $('#publication').val(data[0].publication);
				                $('#location').val(data[0].location);
				                $('#branch').val(data[0].branch);
				            }
				        });

				        var tableViewItemDetail = $('#viewItemTable').DataTable({
				            destroy: true,
				            searching: false,
				            bLengthChange: false,
				            autoWidth: false,
				            responsive: true,
				            aaSorting: [],
				            processing: true,
				            ajax: {
				                url: "GetAllAccessionNo",
				                data: {
				                    controlNo: selectedRowValue[8]
				                },
				                type: "GET",
				                dataSrc: function(data) {

				                    var return_data = new Array();
				                    $.fn.dataTable.ext.errMode = 'none';

				                    for (var i = 0; i < data.length; i++) {
				                        var stat = data[i].status;
				                        return_data.push({
				                            'Accession No': data[i].accNo,
				                            'Location': data[i].location,
				                            'Branch': (data[i].branch),
				                            'Item Category': data[i].itemCat,
				                            'SMD': data[i].smd,
				                            'Status': (data[i].status),
				                            'Patron ID': (data[i].partonId),
				                        });
				                    }
				                    return return_data;
				                },
				            },
				            columns: [
				            	{
				                    'data': 'Accession No'
				                },
				                {
				                    'data': 'Location'
				                },
				                {
				                    'data': 'Branch'
				                },
				                {
				                    'data': 'Item Category'
				                },
				                {
				                    'data': 'SMD'
				                },
				                {
				                    'data': 'Status'
				                },
				                {
				                    'data': 'Patron ID'
				                },
				            ],
				            'dom': 'Rlfrtip',
				            'colReorder': {
				                'allowReorder': false
				            },
				        });

				
					
				}else if (action =='view'){

//			        $.ajax({
//			            url: "GetAccessionNo",
//			            data: {
//			                controlNo: $('#controlNo').val()
//			            },
//			            type: "GET",
//			            success: function(data) {
//
//			                $.each(data, function(key, entry) {
//			                    $('#accessionNo').append($("<option />").val(entry).text(entry));
//			                });
//			            }
//			        });
				    
			        $.ajax({
			            url: "GetAccessionNoByReserveId",
			            data: {reserveNo: selectedRowValue[11]},
			            type: "GET",
			            success: function(data) {
			                
			                if (data) {
		            			$.each(data, function (key, entry) {
		            				$('#accessionNo').append($("<option />").val(entry).text(entry));
		            			})
			                }
			            }
			        });
				    
					   $.ajax({
				            url: "GetStartDateEndDateInEdit",
				            data: {
				                reserveNo: selectedRowValue[11]
				            },
				            type: "GET",
				            success: function(data) {
				             
								$('#startDate').val(data[0].startDate);
								$('#endDate').val(data[0].endDate);

				            }
				        });		
				    
					$('#courseCodeId1').val(selectedRowValue[1]);
			        $('#instructorTF').val(selectedRowValue[7]);
					$('#controlNo').val(selectedRowValue[8]);
					$('#accessionNo').val(selectedRowValue[9]);
				
					   $.ajax({
				            url: "GetBookDetail",
				            data: {
				                controlNo: selectedRowValue[8]
				            },
				            type: "GET",
				            success: function(data) {

				                $('#callNo').val(data[0].callNo);
				                $('#title').val(data[0].title);
				                $('#author').val(data[0].author);
				                $('#publication').val(data[0].publication);
				                $('#location').val(data[0].location);
				                $('#branch').val(data[0].branch);
				            }
				        });

				        var tableViewItemDetail = $('#viewItemTable').DataTable({
				            destroy: true,
				            searching: false,
				            bLengthChange: false,
				            autoWidth: false,
				            responsive: true,
				            aaSorting: [],
				            processing: true,
				            ajax: {
				                url: "GetAllAccessionNo",
				                data: {
				                    controlNo: selectedRowValue[8]
				                },
				                type: "GET",
				                dataSrc: function(data) {

				                    var return_data = new Array();
				                    $.fn.dataTable.ext.errMode = 'none';

				                    for (var i = 0; i < data.length; i++) {
				                        var stat = data[i].status;
				                        return_data.push({
				                            'Accession No': data[i].accNo,
				                            'Location': data[i].location,
				                            'Branch': (data[i].branch),
				                            'Item Category': data[i].itemCat,
				                            'SMD': data[i].smd,
				                            'Status': (data[i].status),
				                            'Patron ID': (data[i].partonId),
				                        });
				                    }
				                    return return_data;
				                },
				            },
				            columns: [
				            	{
				                    'data': 'Accession No'
				                },
				                {
				                    'data': 'Location'
				                },
				                {
				                    'data': 'Branch'
				                },
				                {
				                    'data': 'Item Category'
				                },
				                {
				                    'data': 'SMD'
				                },
				                {
				                    'data': 'Status'
				                },
				                {
				                    'data': 'Patron ID'
				                },
				            ],
				            'dom': 'Rlfrtip',
				            'colReorder': {
				                'allowReorder': false
				            },
				        });

				}
				
				
				$.ajax({
				    url: 'GetCourseCodeDesc',
				    success: function(data1) {
				        if (data1) {
				            $.each(data1, function(_1, entry) {
				                $('#courseCodeDesc1').append($("<option />").val(entry).text(entry));
				            });
				            $('#courseCodeDesc1').val('');
							if (action == 'edit') {
						        $('#courseCodeDesc1').val(selectedRowValue[2]);
							}else if (action == 'delete'){
								 $('#courseCodeDesc1').val(selectedRowValue[2]);
							}else if (action == 'view'){
								 $('#courseCodeDesc1').val(selectedRowValue[2]);
							}
				        };
				    }
				});
			}
		}
	});


	$.ajax({
	    url: 'GetSemMasterId',
	    success: function(data) {
	        if (data) {
	            $.each(data, function(_0, entry) {
	                $('#semMasterId1').append($("<option />").val(entry).text(entry));
	            });
	            $('#semMasterId1').val('');
				if (action == 'edit') {
			        $('#semMasterId1').val(selectedRowValue[3]);
				}else if (action == 'delete'){
					$('#semMasterId1').val(selectedRowValue[3]);
				}else if (action == 'view'){
					$('#semMasterId1').val(selectedRowValue[3]);
				}

	            $.ajax({
	                url: 'GetSemMasterDesc',
	                success: function(data1) {
	                    if (data1) {
	                        $.each(data1, function(_1, entry) {
	                            $('#semMasterDesc1').append($("<option />").val(entry).text(entry));
	                        });
	                    }
	                    $('#semMasterDesc1').val('');
	    				if (action == 'edit') {
	    			        $('#semMasterDesc1').val(selectedRowValue[4]);
	    				}else if (action == 'delete'){
	    					$('#semMasterDesc1').val(selectedRowValue[4]);
	    				}else if (action == 'view'){
	    					$('#semMasterDesc1').val(selectedRowValue[4]);
	    				}
	                }
	            });
	        }
	    }
	});

	$.ajax({
	    url: 'GetSubjectId',
	    success: function(data) {
	        if (data) {

	            $.each(data, function(key, entry) {
	                $('#subjectId1').append($("<option />").val(entry).text(entry));
	            });
	            $('#subjectId1').val('');
				if (action == 'edit') {
			        $('#subjectId1').val(selectedRowValue[5]);
				}else if (action == 'delete'){
					$('#subjectId1').val(selectedRowValue[5]);
				}else if (action == 'view'){
					$('#subjectId1').val(selectedRowValue[5]);
				}

	            $.ajax({
	                url: 'GetSubjectDesc',
	                success: function(data1) {
	                    if (data1) {
	                        $.each(data1, function(key, entry) {
	                            $('#subjectDesc1').append($("<option />").val(entry).text(entry));
	                        })
	                        $('#subjectDesc1').val('');
	        				if (action == 'edit') {
	        			        $('#subjectDesc1').val(selectedRowValue[6]);
	        				}else if (action == 'delete'){
	        					$('#subjectDesc1').val(selectedRowValue[6]);
	        				}else if (action == 'view'){
	        					$('#subjectDesc1').val(selectedRowValue[6]);
	        				}
	                    }
	                }
	            });
	        }
	    }
	});
	
	$(".courseCodeId").change(function() {
		var id = $(this).data('id');
		var index = $(this).prop('selectedIndex');
		$("#courseCodeDesc" + id).prop('selectedIndex', index);
	});

	$(".courseCodeDesc").change(function() {
		var id = $(this).data('id');
		var index = $(this).prop('selectedIndex');
		$("#courseCodeId" + id).prop('selectedIndex', index);
	});

	$(".semMasterId").change(function() {
		var id = $(this).data('id');
		var index = $(this).prop('selectedIndex');
		$("#semMasterDesc" + id).prop('selectedIndex', index);
	});

	$(".semMasterDesc").change(function() {
		var id = $(this).data('id');
		var index = $(this).prop('selectedIndex');
		$("#semMasterId" + id).prop('selectedIndex', index);
	});

	$(".subjectId").change(function() {
		var id = $(this).data('id');
		var index = $(this).prop('selectedIndex');
		$("#subjectDesc" + id).prop('selectedIndex', index);
	});

	$(".subjectDesc").change(function() {
		var id = $(this).data('id');
		var index = $(this).prop('selectedIndex');
		$("#subjectId" + id).prop('selectedIndex', index);
	});
	
	
	var toggle1=function() {
		
		$("#accessionNo option").remove();
		var accNoClickCount = 1;
		
		$('#accessionNo').click(function() {

		    if (accNoClickCount == 1) {

		        $.ajax({
		            url: "GetAccessionNo",
		            data: {
		                controlNo: $('#controlNo').val()
		            },
		            type: "GET",
		            success: function(data) {

		                $.each(data, function(key, entry) {
		                    $('#accessionNo').append($("<option />").val(entry).text(entry));
		                });
		            }
		        });

		        $.ajax({
		            url: "GetBookDetail",
		            data: {
		                controlNo: $('#controlNo').val()
		            },
		            type: "GET",
		            success: function(data) {

		                $('#callNo').val(data[0].callNo);
		                $('#title').val(data[0].title);
		                $('#author').val(data[0].author);
		                $('#publication').val(data[0].publication);
		                $('#location').val(data[0].location);
		                $('#branch').val(data[0].branch);
		            }
		        });

		        var tableViewItemDetail = $('#viewItemTable').DataTable({
		            destroy: true,
		            searching: false,
		            bLengthChange: false,
		            autoWidth: false,
		            responsive: true,
		            aaSorting: [],
		            processing: true,
		            ajax: {
		                url: "GetAllAccessionNo",
		                data: {
		                    controlNo: $('#controlNo').val()
		                },
		                type: "GET",
		                dataSrc: function(data) {

		                    var return_data = new Array();
		                    $.fn.dataTable.ext.errMode = 'none';

		                    for (var i = 0; i < data.length; i++) {
		                        var stat = data[i].status;
		                        return_data.push({
		                            'Accession No': data[i].accNo,
		                            'Location': data[i].location,
		                            'Branch': (data[i].branch),
		                            'Item Category': data[i].itemCat,
		                            'SMD': data[i].smd,
		                            'Status': (data[i].status),
		                            'Patron ID': (data[i].partonId),
		                        });
		                    }
		                    return return_data;
		                },
		            },
		            columns: [
		            	{
		                    'data': 'Accession No'
		                },
		                {
		                    'data': 'Location'
		                },
		                {
		                    'data': 'Branch'
		                },
		                {
		                    'data': 'Item Category'
		                },
		                {
		                    'data': 'SMD'
		                },
		                {
		                    'data': 'Status'
		                },
		                {
		                    'data': 'Patron ID'
		                },
		            ],
		            'dom': 'Rlfrtip',
		            'colReorder': {
		                'allowReorder': false
		            },
		        });
		        accNoClickCount++;
		    }
		});
	};
	

  
	  $("#controlNo").focus(function() {
	        $(this).on("click", toggle2);
	    });
	    
	    $("#controlNo").blur(function() {
	        $(this).off("click", toggle1);

	    });
	   
		var toggle2=function() {
			
			$("#accessionNo option").remove();
			var accNoClickCount = 1;
			
			$('#accessionNo').click(function() {

			    if (accNoClickCount == 1) {

			        $.ajax({
			            url: "GetAccessionNo",
			            data: {
			                controlNo: $('#controlNo').val()
			            },
			            type: "GET",
			            success: function(data) {

			                $.each(data, function(key, entry) {
			                    $('#accessionNo').append($("<option />").val(entry).text(entry));
			                });
			            }
			        });

			        $.ajax({
			            url: "GetBookDetail",
			            data: {
			                controlNo: $('#controlNo').val()
			            },
			            type: "GET",
			            success: function(data) {

			                $('#callNo').val(data[0].callNo);
			                $('#title').val(data[0].title);
			                $('#author').val(data[0].author);
			                $('#publication').val(data[0].publication);
			                $('#location').val(data[0].location);
			                $('#branch').val(data[0].branch);
			            }
			        });

			        var tableViewItemDetail = $('#viewItemTable').DataTable({
			            destroy: true,
			            searching: false,
			            bLengthChange: false,
			            autoWidth: false,
			            responsive: true,
			            aaSorting: [],
			            processing: true,
			            ajax: {
			                url: "GetAllAccessionNo",
			                data: {
			                    controlNo: $('#controlNo').val()
			                },
			                type: "GET",
			                dataSrc: function(data) {

			                    var return_data = new Array();
			                    $.fn.dataTable.ext.errMode = 'none';

			                    for (var i = 0; i < data.length; i++) {
			                        var stat = data[i].status;
			                        return_data.push({
			                            'Accession No': data[i].accNo,
			                            'Location': data[i].location,
			                            'Branch': (data[i].branch),
			                            'Item Category': data[i].itemCat,
			                            'SMD': data[i].smd,
			                            'Status': (data[i].status),
			                            'Patron ID': (data[i].partonId),
			                        });
			                    }
			                    return return_data;
			                },
			            },
			            columns: [
			            	{
			                    'data': 'Accession No'
			                },
			                {
			                    'data': 'Location'
			                },
			                {
			                    'data': 'Branch'
			                },
			                {
			                    'data': 'Item Category'
			                },
			                {
			                    'data': 'SMD'
			                },
			                {
			                    'data': 'Status'
			                },
			                {
			                    'data': 'Patron ID'
			                },
			            ],
			            'dom': 'Rlfrtip',
			            'colReorder': {
			                'allowReorder': false
			            },
			        });
			        accNoClickCount++;
			    }
			});
		};
		

/*		  $("#searchISSN").focus(function() {
		        $(this).on("click", toggle2);
		    });
		    
		    $("#searchISSN").blur(function() {
		        $(this).off("click", toggle1);

		    });*/
		   

	
	$('#okBtn').click(function() {

		var startDate = document.getElementById("startDate").value;
	  	var endDate = document.getElementById("endDate").value;
	  	
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

		    var courseId = $('#courseCodeId1').val();
		    var courseDesc = $('#courseCodeDesc1').val();
		    var semesterId = $('#semMasterId1').val();
		    var semesterDesc = $('#semMasterDesc1').val();
		    var subjectId = $('#subjectId1').val();
		    var subjectDesc = $('#subjectDesc1').val();
		    var instructor = $('#instructorTF').val();
		    var controlNo = $('#controlNo').val();
		    var accNo = $('#accessionNo').val();
		    var title = $('#title').val();
		    var startDate = $('#startDate').val();
		    var endDate = $('#endDate').val();
		    
		    var table = $('#rsvCollectionMaster_table').DataTable();
	
		    var lastIndex = table.row(':last', { order: 'applied' }).data();
		
			if(action== 'add'){
				  $.ajax({	            		  
		            	url: "AddReserve",
		            	data : {courseId: $('#courseCodeId1').val(), semesterId: $('#semMasterId1').val(), subjectId: $('#subjectId1').val(), instructor: $('#instructorTF').val(), controlNo: $('#controlNo').val(), startDate: $('#startDate').val(), endDate: $('#endDate').val(), startDate: $('#startDate').val(), createdDate: $('#dateRecorded').text(), recordedBy: $('#recordedBy').text(), title: $('#title').val(), callNo: $('#callNo').val(), publication: $('#publication').val(), author: $('#author').val(), docNo: $('#accessionNo').val()},   	
		            	type: "GET",
		            	async : false,
		            	success: function(data) {
		            		reserveNo=data;
		            	}
		            });
			    
			    if (! table.data().any()) {
			    	
			        tableRCMaster.row.add([
			            1,
			            courseId,
			            courseDesc,
			            semesterId,
			            semesterDesc,
			            subjectId,
			            subjectDesc,
			            instructor,		
			            controlNo,
			            accNo,			            
			            title,
			            reserveNo,
			            action
			        ]).draw(true);
			        
			    } else {
			        var lastIndex = table.row(':last', { order: 'applied' }).data();
			        
			        var lastIndexInNumber = parseInt(lastIndex[0]);
			        
			        tableRCMaster.row.add([
			            lastIndexInNumber + 1,
			            courseId,
			            courseDesc,
			            semesterId,
			            semesterDesc,
			            subjectId,
			            subjectDesc,
			            instructor,		
			            controlNo,
			            accNo,			            
			            title,
			            reserveNo,
			            action
			        ]).draw(true);
			    }
			    
		    

			    
			    if (!$('#courseCodeCB').is(":checked")) {
			        $("#courseCodeId1").val('');
			        $("#courseCodeDesc1").val('');
			    }
		
			    if (!$('#semesterMasterCB').is(":checked")) {
			        $("#semMasterId1").val('');
			        $("#semMasterDesc1").val('');
			    }
		
			    if (!$('#subjectCB').is(":checked")) {
			        $("#subjectId1").val('');
			        $("#subjectDesc1").val('');
			    }
		
			    if (!$('#instructorCB').is(":checked")) {
			        $('#instructorTF').val('');
			        $('#lblInstructor').val('');
			    }
		
			    if (!$('#controlNoCB').is(":checked")) {
			        $('#controlNo').val('');
			    }
		
			    if (!$('#AccNoCB').is(":checked")) {
			        $('#accessionNo').val('');
			    }
		
			    if (!$('#startDateCB').is(":checked")) {
			        $('#startDate').val('');
			    }
		
			    if (!$('#endDateCB').is(":checked")) {
			        $('#endDate').val('');
			    }
			    
                $('#callNo').val('');
                $('#title').val('');
                $('#author').val('');
                $('#publication').val('');
                $('#location').val('');
                $('#branch').val('');
		
			}else if(action == 'edit'){
				
				  $.ajax({	            		  
		            	url: "EditReserve",
		            	data : {reserveNo: selectedRowValue[11], startDate: $('#startDate').val(), endDate: $('#endDate').val()},   	
		            	type: "GET",
		            	success: function(data) {
		            		
		            	}
		            });
				  
			
			}else if(action == 'delete'){
				
				  $.ajax({	            		  
		            	url: "DeleteReserve",
		            	data : {reserveNo: selectedRowValue[11]},   	
		            	type: "GET",
		            	success: function(data) {
		            		
		            	}
		            });
  
				  row.remove().draw();
				  
//				
//				 e.preventDefault();
//				 
//			        editor.remove( $(this).closest('tr'), {
//			            title: 'Delete record',
//			            message: 'Are you sure you wish to remove this record?',
//			            buttons: 'Delete'
//			        } );

			}
	    }
	});
	
	$('#createModal, #editModal, #viewModal, #deleteModal').on('hide.bs.modal', function (event) {
		$(event.target).removeData("bs.modal").find(".modal-content").empty();
	});
	
	$('#startDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	$('#endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	$('#instructorTF, #controlNo').bind('keyup', function() {
	    if (allFilled()) $('#okBtn').removeAttr('disabled');
	});

	function allFilled() {
	    var filled = true;
	    $('body input').each(function() {
	        if ($(this).val() == '') filled = false;
	    });
	    return filled;
	}

	$('#instructorCB1').on('click', function() {
	    if ($('#instructorCB1').is(':checked')) {
	    }
	});

	
	$('#findBtn').click(function(){
		var table = $('#rsvCollectionMaster_table').DataTable({
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			aaSorting: [],
			processing: true,
		    ajax: {
		    	url: "EditReserve",
		    	data: {selectedRadio: optradio, inputCriteria: inputCriteria},
		        dataSrc: function (json) {
		            var return_data = new Array();
		            $.fn.dataTable.ext.errMode = 'none';
		            for( var i=0;i< json.length; i++) {
		            	var stat = json[i].status;
						return_data.push({
							'No': json[i].accessionNo,
							'Course': json[i].patron,
							'Course Desc' : json[i].title, 
							'Semester' : json[i].callNo,
							'Semester Desc' : json[i].dueDate,
							'Subject' : json[i].reminderType, 
							'Subject Desc' : json[i].countLateBy,
							'Instructor' : json[i].countFine,
							'Control No' : json[i].email,
							'Accession No' : json[i].branch,
							'Title' : json[i].branch,
							'Reserve No' : json[i].branch,
							'Action' : json[i].location,
						})
		            }
		            return return_data;
		          },
		     },
	     	columns    : [
				{'data': 'courseId'},
				{'data': 'Course'},
				{'data': 'Course Desc'},
				{'data': 'Semester'},
				{'data': 'Semester Desc'},
				{'data': 'Subject'},
				{'data': 'Semester Desc'},
				{'data': 'Subject'},
				{'data': 'Subject Desc'},
				{'data': 'Instructor'},
				{'data': 'Accession No'},
				{'data': 'Title'},
				{'data': 'Reserve No'},
				{'data': 'Action'},
			],
	        'dom': 'Rlfrtip',
	        'colReorder': {
	            'allowReorder': false
	        },
		});
			new $.fn.dataTable.ColReorder(table);
    });
	

});