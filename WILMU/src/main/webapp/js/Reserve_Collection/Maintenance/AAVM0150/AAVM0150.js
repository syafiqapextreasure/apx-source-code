$(document).ready(function() {
	
	var todayDate = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	console.log('liferayLogin: '+ liferayLogin);
	
	$('#dateRecorded').text(moment(todayDate).format("DD/MM/YYYY"));
	$("#recordedBy").text(liferayLogin);
	$(".CT03MATNO").attr('maxlength','10');
	
	var tableRCMaster = $('#rsvCollectionMaster_table').DataTable();
	
	var action = $('#action').val();
	
	///////////////////// input-startDate set Current Date  ///////////////////////////////////
	$('#startDate').datepicker({
		//startDate : today,
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
	
	function remainData(){

		var element;
		$('input:checkbox[name=checkedValue]').each(function() 
				{    
				    if(!$(this).is(':checked')){
				    	var element = "#" + $(this).val();
						    
						   /* if(element==".CT03INVDATE"){
						    	$('#CT03INVDATE').val("").datepicker("update");
						    	
						    }*/
						    if($(element).is("select")) {
						    	$(element).prop('selectedIndex',0);
						    	var element1 = element + "1";
						    	$(element1).prop('selectedIndex',0);
						    }
						    
						    
						    if($(element).is("input[type=text]")) {
						    	$(element).val('');
						    	
						    	if(element == "#requestorId"){
						    		$("#div-reqname").html("");
						    	}
						    	return true;
						    }
				    }
				});
	}
	
	$("#addMandatoryCheck").change(function(){
		var chkItem = $("#addMandatoryCheck");
		if($(chkItem).is(":checked"))
	    {
			$("#input-contorlNo").attr("disabled", "disabled");
			$("#accessionNo").attr("disabled", "disabled");
			$("#callNo").removeAttr("disabled");
	    	$("#titles").removeAttr("disabled");
	    	$("#subject").removeAttr("disabled");
	    	$("#author").removeAttr("disabled");
	    	$("#publication").removeAttr("disabled");
	    	$(".remove").hide();
	    }else{
	    	$("#input-contorlNo").removeAttr("disabled");
	    	$("#accessionNo").removeAttr("disabled");
	    	$("#callNo").attr("disabled", "disabled");
			$("#titles").attr("disabled", "disabled");
			$("#subject").attr("disabled", "disabled");
			$("#author").attr("disabled", "disabled");
			$("#publication").attr("disabled", "disabled");
			$(".remove").show();
	    }
	})
	$("#requestorId").blur(function(e){

		var patronid = $("#requestorId").val().toUpperCase();

		$('#requestorId').val($('#requestorId').val().toUpperCase());
		
		$("#div-reqname").empty();
		if(patronid == ""){
			$("#okBtn").attr('disabled',false);
			$("#requestorId").css("border", "");
		}else{
		$.get('GetPatronName', {
			id : patronid
		 	}, function(data) {
		 		$("#div-reqname").text(data[0].Name);
		});
		}
	});
	
	$("#input-contorlNo").blur(function(){
	
		  $.ajax({
	            url: "RetrieveAccessionNo",
	            data: {
	                controlNo: $(this).val()
	            },
	            type: "GET",
	            success: function(data) {
	            	console.log('111 AAVM0150.js return data: '+ JSON.stringify(data));
	            	$("#accessionNo").empty();
	                $.each(data, function(key, entry) {
	                    $('#accessionNo').append($("<option />").val(entry).text(entry));
	                });
	            }
	        });
		  
		   $.ajax({
	            url: "RetrieveCatBibByMatno",
	            data: {
	                controlNo: $(this).val()
	            },
	            type: "GET",
	            success: function(data) {
	                $('#callNo').val(data[0].callNo);
	                $('#titles').val(data[0].title);
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
	                url: "RetrieveAccessionDetails",
	                data: {
	                    controlNo: $(this).val()
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

		/*   $.ajax({
	            url: "RetrieveCatBibByMatno",
	            data: {
	                controlNo: $(this).val()
	            },
	            type: "GET",
	            success: function(data) {
	            
	            	alert("assas");
	            	var json = JSON.parse(data);
alert(json.publication);
	                $('#callNo').val(data.callNo);
	                $('#title').val(data.title);
	                $('#author').val(data.author);
	                $('#publication').val(data[0].publication);
	                $('#location').val(data[0].location);
	                $('#branch').val(data[0].branch);
	            }
	        });*/
	});
	
	

	$.ajax({
		url : 'GetCourseCodeId',
		beforeSend : function() {
		},
		success : function(data) {
			if (data) {
				$.each(data, function (_0, entry) {
					$('#courseCodeId1').append($("<option />").val(entry).text(entry));
				});
				$('#courseCodeId1').val('');
				
				if (action == 'edit') {
					
					console.log('edit course id: '+ selectedRowValue[1]);
				    console.log('edit 7: '+ selectedRowValue[7]);
				    console.log('edit 8: '+ selectedRowValue[8]);
				    console.log('edit 9: '+ selectedRowValue[9]);
				    console.log('edit 10: '+ selectedRowValue[10]);
				    console.log('edit 11: '+ selectedRowValue[11]);
				    
					   $.ajax({
				            url: "GetStartDateEndDateInEdit",
				            data: {
				                reserveNo: selectedRowValue[11]
				            },
				            
				            type: "GET",
				            success: function(data) {

				                console.log('success get startDate endDate in edit: ' + JSON.stringify(data));
				                console.log(data[0].startDate);
				                console.log(data[0].endDate);
				             
								$('#startDate').val(data[0].startDate);
								$('#endDate').val(data[0].endDate);

				            }
				        });				    
					 
					$('#courseCodeId1').val(selectedRowValue[1]);
			        $('#instructorTF').val(selectedRowValue[7]);
			        $('.ctrlno').val(selectedRowValue[8]);
					$('#accessionNo').val(selectedRowValue[9]);

					console.log('control no for edit: '+ selectedRowValue[8]);
					console.log('reserve no for edit: '+ selectedRowValue[11]);
					
			        $.ajax({
			            url: "GetAccessionNoByReserveId",
			            data: {reserveNo: selectedRowValue[11]},
			            type: "GET",
			            success: function(data) {

			            	console.log('line 97');
			                console.log('success get reserve no: ' + data);
			                
			                if (data) {
		            			$.each(data, function (key, entry) {
		            				$('#accessionNo').append($("<option />").val(entry).text(entry));
		            			})
			                }
			            }
			        });
			        
					/*   $.ajax({
				            url: "GetBookDetail",
				            data: {
				                controlNo: selectedRowValue[8]
				            },
				            type: "GET",
				            success: function(data) {

				                console.log('success get book detail function: ' + JSON.stringify(data));
				                console.log('data call no: ' + data[0].callNo)

				                $('#callNo').val(data[0].callNo);
				                $('#title').val(data[0].title);
				                $('#author').val(data[0].author);
				                $('#publication').val(data[0].publication);
				                $('#location').val(data[0].location);
				                $('#branch').val(data[0].branch);
				            }
				        });*/

				      /*  var tableViewItemDetail = $('#viewItemTable').DataTable({
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
*/
					
				}else if (action =='delete'){
					
					console.log('delete course id: '+ selectedRowValue[1]);
				    console.log('delete 7: '+ selectedRowValue[7]);
				    console.log('delete 8: '+ selectedRowValue[8]);
				    console.log('delete 9: '+ selectedRowValue[9]);
				    console.log('delete 10: '+ selectedRowValue[10]);

/*			        $.ajax({
			            url: "GetAccessionNo",
			            data: {
			                controlNo: $('#controlNo').val()
			            },
			            type: "GET",
			            success: function(data) {

			                console.log('success get book detail function: ' + JSON.stringify(data));

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

			            	console.log('line 97');
			                console.log('success get reserve no: ' + data);
			                
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

				                console.log('success get startDate endDate in edit: ' + JSON.stringify(data));
				                console.log(data[0].startDate);
				                console.log(data[0].endDate);
				             
								$('#startDate').val(data[0].startDate);
								$('#endDate').val(data[0].endDate);

				            }
				        });		
				    
					$('#courseCodeId1').val(selectedRowValue[1]);
			        $('#instructorTF').val(selectedRowValue[7]);
					$('#controlNo').val(selectedRowValue[8]);
					$('#accessionNo').val(selectedRowValue[9]);
						
					console.log('control no for delete: '+ selectedRowValue[8]);
					
					   $.ajax({
				            url: "GetBookDetail",
				            data: {
				                controlNo: selectedRowValue[8]
				            },
				            type: "GET",
				            success: function(data) {

				                console.log('success get book detail function: ' + JSON.stringify(data));
				                console.log('data call no: ' + data[0].callNo)

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
					
					console.log('view course id: '+ selectedRowValue[1]);
				    console.log('view 7: '+ selectedRowValue[7]);
				    console.log('view 8: '+ selectedRowValue[8]);
				    console.log('view 9: '+ selectedRowValue[9]);
				    console.log('view 10: '+ selectedRowValue[10]);

//			        $.ajax({
//			            url: "GetAccessionNo",
//			            data: {
//			                controlNo: $('#controlNo').val()
//			            },
//			            type: "GET",
//			            success: function(data) {
//
//			                console.log('success get book detail function: ' + JSON.stringify(data));
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

			            	console.log('line 97');
			                console.log('success get reserve no: ' + data);
			                
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

				                console.log('success get startDate endDate in edit: ' + JSON.stringify(data));
				                console.log(data[0].startDate);
				                console.log(data[0].endDate);
				             
								$('#startDate').val(data[0].startDate);
								$('#endDate').val(data[0].endDate);

				            }
				        });		
				    
					$('#courseCodeId1').val(selectedRowValue[1]);
			        $('#instructorTF').val(selectedRowValue[7]);
					$('#controlNo').val(selectedRowValue[8]);
					$('#accessionNo').val(selectedRowValue[9]);
				
					console.log('control no for view: '+ selectedRowValue[8]);
					
					   $.ajax({
				            url: "GetBookDetail",
				            data: {
				                controlNo: selectedRowValue[8]
				            },
				            type: "GET",
				            success: function(data) {

				                console.log('success get book detail function: ' + JSON.stringify(data));
				                console.log('data call no: ' + data[0].callNo)

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
								console.log('course desc: '+ selectedRowValue[2]);
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

/*
	$.ajax({
	    url: 'GetSemMasterId',
	    success: function(data) {
	        if (data) {
	            $.each(data, function(_0, entry) {
	                $('#semMasterId1').append($("<option />").val(entry).text(entry));
	            });
	            $('#semMasterId1').val('');
				if (action == 'edit') {
					console.log('semester id: '+ selectedRowValue[3]);
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
	    					console.log('semester desc: '+ selectedRowValue[4]);
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
	});*/

	$.ajax({
	    url: 'GetSubjectId',
	    success: function(data) {
	        if (data) {

	            $.each(data, function(key, entry) {
	                $('#subjectId1').append($("<option />").val(entry).text(entry));
	            });
	            $('#subjectId1').val('');
				if (action == 'edit') {
					console.log('subject id: '+ selectedRowValue[5]);
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
	        					console.log('subject desc: '+ selectedRowValue[6]);
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



	


/*		  $("#searchISSN").focus(function() {
		        $(this).on("click", toggle2);
		    });
		    
		    $("#searchISSN").blur(function() {
		        $(this).off("click", toggle1);

		    });*/
		   

	
	$('#modal_patrSearchMono, #titleSearch').on('hide.bs.modal', function (event) {
		console.log('817 clear modal');
		$(event.target).removeData("bs.modal").find(".modal-content").empty();
	});

	
	$('#findBtn').click(function(){
		var getChecked = $('input[name=retrieveRadios]:checked').val();
		var getCriteria = $("input[name=inputCriteria]").val();
		
		loader("#rsvCollectionMaster_table");
		$(".closePatrModal").click();
		
		$.get('Global?type=Table&name=Table_SearchReserveCol', {getChecked: getChecked, getCriteria: getCriteria}, function(responseText) {

			$("#display_reservecollection").html(responseText);
			
			var table = $('#rsvCollectionMaster_table').DataTable({
				destroy: true,
				searching: false,
				bLengthChange: false,
				autoWidth: false,
				responsive: true,
				aaSorting: [],
				processing: true
			});
		});
	})
	
	$('#searchModal').on('hidden.bs.modal', function () {
		$("#inputCriteria").val("");
	    $('input[name=retrieveRadios][value=subject]').prop('checked', true); 
	});
	
	function viewForm(){
		$("#formreservecollection input").attr('disabled','disabled');
		$("#formreservecollection input[type=checkbox]").attr('disabled', true);
		$('#formreservecollection select').prop('disabled', 'disabled');
		$("#okBtn").hide();
		$("#close").show();
	}
	
	function editForm(){
		$("#formreservecollection input").attr('disabled','disabled');
		$("#formreservecollection input[type=checkbox]").attr('disabled', true);
		$('#formreservecollection select').prop('disabled', 'disabled');
		$("#startDate").attr('disabled',false);
		$("#endDate").attr('disabled',false);
		$("#okBtn").show();
		$("#close").show();
	}

	function addForm(){
		$("#formreservecollection input").attr('disabled', false);
		$("#formreservecollection input[type=checkbox]").attr('disabled', false);
		$('#formreservecollection select').prop('disabled', false);
		$("#startDate").attr('disabled',false);
		$("#endDate").attr('disabled',false);
		$("#accessionNo").empty();
		$("#okBtn").show();
		$("#close").show();
		$("#div-reqname").empty();
		$('#formreservecollection').data('bootstrapValidator').resetForm();
		$('#formreservecollection').trigger("reset");
		$('#viewItemTable').dataTable().fnClearTable();
	}

	
	
	//****************************************** AREA FOR MODAL *****************************************//
	 
	 //////modal add, edit, view
	 var stored;
	 $('#modalreservecollection').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('mode'); // Extract info from data-* attributes

		var rowid = button.data('rowid');
		
		console.log('866 recipient: '+ recipient);
		
		switch(recipient){
			case state = 1:

				addForm();
				modal.find('.modal-title').text("Add New Reserve Collection");
				$("#okBtn").prop("disabled",false);
				$("select").prop("selectedIndex",0);
				$("input[type=text]").empty();
				
	    		stored = [];
		  		break;
			case state = 2:
				
				editForm();
			$("#okBtn").prop("disabled",false);
				modal.find('.modal-title').text("Edit Reserve Collection");
			$.get('DisplayReserveDetails?getChecked=reserveno', {
				getCriteria : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						localStorage.setItem('rowid', rowid)
						
						$("#course").val(value['RE01COURSE']);
						$("#semMasterId1").val(value['RE01SEMESTER']);
						//$("#title").text(value['title']);
						$("#subj").val(value['RE01SUBJ']);
						$("#requestorId").val(value['RE01INSTRUCTOR']);
						$("#input-contorlNo").val(value['RE01CTRLNO']);
						$("#accessionNo").val(value['RE01DOCNO']);
						$("#startDate").val(value['RE01DTSTART']);
						$("#endDate").val(value['RE01DTEND']);
						$("#callNo").val(value['RE01CALLNO']);
						$("#subject").val(value['RE01ISUBJ']);
						$("#titles").val(value['RE01TITLE']);
						$("#div-reqname").text(value['GL14NAME']);							
						$("#author").val(value['RE01AUTH']);
						$("#publication").val(value['RE01PUBL']);
						
						if(value['RC01ITEMINDB']=="Y"){
							
							$('#addMandatoryCheck').attr('checked', false); 
							   $('#location').show();
				                $('#branch').show();
				           	 $.ajax({
						            url: "RetrieveCatBibByMatno",
						            data: {
						                controlNo: $("#input-contorlNo").val()
						            },
						            type: "GET",
						            success: function(data) {
						                $('#location').val(data[0].location);
						                $('#branch').val(data[0].branch);
						            }
						        });
						}else {
							$('#addMandatoryCheck').attr('checked', true); 
							   $('#location').hide();
				                $('#branch').hide();
						}
						$.ajax({
				            url: "RetrieveAccessionNo",
				            data: {
				                controlNo:$("#input-contorlNo").val()
				            },
				            type: "GET",
				            success: function(data) {

				                $.each(data, function(key, entry) {
				                    $('#accessionNo').append($("<option />").val(entry).text(entry));
				                    $('#accessionNo').val(value['RE01DOCNO']);
				                });
				            }
				        });
						$(".dateRecorded").text(value['RE01CREDATE']);
						 
						 var tableViewItemDetail = $('#viewItemTable').DataTable({
					            destroy: true,
					            searching: false,
					            bLengthChange: false,
					            autoWidth: false,
					            responsive: true,
					            aaSorting: [],
					            processing: true,
					            ajax: {
					                url: "RetrieveAccessionDetails",
					                data: {
					                    controlNo: $("#input-contorlNo").val()
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
					});
				}
			});
			
	  			break;
			case state = 3:
				//$('#requestor').dataTable().fnClearTable();
				viewForm();
				modal.find('.modal-title').text("View Reserve Collection");
				$.get('DisplayReserveDetails?getChecked=reserveno', {
					getCriteria : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
						
							
							$("#course").val(value['RE01COURSE']);
							$("#semMasterId1").val(value['RE01SEMESTER']);
							//$("#title").text(value['title']);
							$("#subj").val(value['RE01SUBJ']);
							$("#requestorId").val(value['RE01INSTRUCTOR']);
							$("#input-contorlNo").val(value['RE01CTRLNO']);
							$("#accessionNo").val(value['RE01DOCNO']);
							$("#startDate").val(value['RE01DTSTART']);
							$("#endDate").val(value['RE01DTEND']);
							$("#callNo").val(value['RE01CALLNO']);
							$("#subject").val(value['RE01ISUBJ']);
							$("#titles").val(value['RE01TITLE']);
							$("#div-reqname").text(value['GL14NAME']);							
							$("#author").val(value['RE01AUTH']);
							$("#publication").val(value['RE01PUBL']);
							
							if(value['RC01ITEMINDB']=="Y"){
								
								$('#addMandatoryCheck').attr('checked', false); 
								   $('#location').show();
					                $('#branch').show();
					           	 $.ajax({
							            url: "RetrieveCatBibByMatno",
							            data: {
							                controlNo: $("#input-contorlNo").val()
							            },
							            type: "GET",
							            success: function(data) {
							                $('#location').val(data[0].location);
							                $('#branch').val(data[0].branch);
							            }
							        });
							}else {
								$('#addMandatoryCheck').attr('checked', true); 
								   $('#location').hide();
					                $('#branch').hide();
							}
							$.ajax({
					            url: "RetrieveAccessionNo",
					            data: {
					                controlNo:$("#input-contorlNo").val()
					            },
					            type: "GET",
					            success: function(data) {

					                $.each(data, function(key, entry) {
					                    $('#accessionNo').append($("<option />").val(entry).text(entry));
					                    $('#accessionNo').val(value['RE01DOCNO']);
					                });
					            }
					        });
							$(".dateRecorded").text(value['RE01CREDATE']);
							 
							 var tableViewItemDetail = $('#viewItemTable').DataTable({
						            destroy: true,
						            searching: false,
						            bLengthChange: false,
						            autoWidth: false,
						            responsive: true,
						            aaSorting: [],
						            processing: true,
						            ajax: {
						                url: "RetrieveAccessionDetails",
						                data: {
						                    controlNo: $("#input-contorlNo").val()
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
						});
					}
				});
			
				break;
		}
		
	 });

});

