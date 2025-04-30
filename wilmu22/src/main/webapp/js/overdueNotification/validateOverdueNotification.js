$(document).ready(function() {
	var liferayLogin = $("#liferayLogin").val();
	
    $.urlParam = function(name) {
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
//        alert('line 5 result: '+ results);
//        alert('line 6 url: '+ window.location.href);
        return results[1] || 0;
    }

    $('.module').val(decodeURIComponent($.urlParam('value')));

//    alert('line 12 $.urlParam(): '+ $.urlParam('value'));
    
    if($.urlParam('value')=='on'){
    	$('.OrContainer').hide();
    	$('.overReminderTable').hide();
    	$('.reminderFooter').hide();
    }else if($.urlParam('value')=='or'){
    	$('.OnContainer').hide();
    	$('.overNotifyTable').hide();
    	$('.notificationFooter').hide();
    }
    
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
    
    $('#overReminderForm').bootstrapValidator({
        framework: 'bootstrap',
        excluded: [':disabled'],
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            dateFrom: {
                validators: {
                    notEmpty: {
                        message: 'Start Date is required'
                    }
                }
            },
            dateTo: {
                validators: {
                    notEmpty: {
                        message: 'End Date is required'
                    }
                }
            },
            patronCate: {
                validators: {
                    notEmpty: {
                        message: 'Patron Category is required'
                    }
                }
            },
            brch: {
                validators: {
                    notEmpty: {
                        message: 'Branch is required'
                    }
                }
            },
        }
    });
/*    
    if($("#patronCate").attr("selectedIndex") == 0) {
        alert("You haven't selected anything!");
       }
    */

    
/*
    $('#overReminderForm').bootstrapValidator({
        framework: 'bootstrap',
        excluded: [':disabled'],
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            dateFrom: {
                validators: {
                    notEmpty: {
                        message: 'Start Date is required'
                    }
                }
            },
            dateTo: {
                validators: {
                    notEmpty: {
                        message: 'End Date is required'
                    }
                }
            }
        }
    });
*/
    $('#submitButtonRedirectPage').click(function() {
        $('#countSubject').text('successfully pass value to jsp page from js');

        $('#new_window_parameter_1').val('value');
        $('#invisible_form').submit();

    })

    var table = $('#overdue_table').DataTable({
        destroy: true,
        searching: false,
        bLengthChange: false,
        autoWidth: false,
        responsive: true,
        aaSorting: [],
        processing: true,
    });
    
    var table = $('#overdueRmd_table').DataTable({
        destroy: true,
        searching: false,
        bLengthChange: false,
        autoWidth: false,
        responsive: true,
        aaSorting: [],
        processing: true,
    });

    var liferayLogin = $("#liferayLogin").val();

    $('#reloadPageBtn').click(function() {
        location.reload();
    });

    var patronCategoryIdCounter = 1;
    var patronCategoryDescCounter = 1;
    var branchIdCounter = 1;
    var branchDescCounter = 1;
    var patronStatusIdCounter = 1;
    var patronStatusDescCounter = 1;
    var countIfpatronCategoryIdCounter = 1;
    var countIfpatronCategoryDescCounter = 1;
    var pageLoad = 1;

    if (pageLoad == '1') {
        if ($('input[name=retrieveRadios]:checked', '#form-radioDate').val() == 'unprint') {

            $('.unprint').prop('disabled', true);

            var now = new Date();
            var dateNewFormat = fixDigit(now.getDate()) + '/' +
                fixDigit(now.getMonth() + 1) +
                '/' + now.getFullYear();

            $('.unprint').val(dateNewFormat);

        } else {
            $('.reprint').prop('disabled', true);
            $('.reprint').val("");
            $('.patron').prop('disabled', true);
            $('.patron').val("");
        };
        pageLoad++;
    }
    if (patronCategoryIdCounter <= '1' || patronCategoryDescCounter <= '1') {

        $.ajax({
            url: 'PatronCategoryId?unprintedDate=' +
                $('#forUnprint').val() +
                '&reprintDate=' +
                $('#forReprint').val() +
                '&inputPatronId=' +
                $('#forPatron').val() +
                '&reminder=' +
                $('input[name="reminderRadios"]:checked')
                .val() +
                '&address=' +
                $('input[name="addressRadios"]:checked')
                .val() + '&officerId=' +
                $('#officerId').val(),

            success: function(data) {
                if (data) {
                    var count = 0;

                    $.each(data, function(key, entry) {
                        $('#patronCategoryId1').append(
                            $("<option />").val(entry)
                            .text(entry));
                        count++;
                    })

                    $dropdown = $('#patronCategoryId1');
                    $dropdown[0].selectedIndex = -1;

                    $.ajax({

                        url: 'PatronCategoryDesc?unprintedDate=' +
                            $('#forUnprint').val() +
                            '&reprintDate=' +
                            $('#forReprint').val() +
                            '&inputPatronId=' +
                            $('#forPatron').val() +
                            '&reminder=' +
                            $('input[name="reminderRadios"]:checked')
                            .val() +
                            '&address=' +
                            $('input[name="addressRadios"]:checked')
                            .val() + '&officerId=' +
                            $('#officerId').val(),

                        success: function(data1) {
                            if (data1) {
                                var count1 = 0;

                                $.each(data1, function(key, entry) {
                                    $('#patronCategoryDesc1').append(
                                        $("<option />").val(entry)
                                        .text(entry));
                                    count1++;
                                })
                            }
                            $dropdown = $('#patronCategoryDesc1');
                            $dropdown[0].selectedIndex = -1;
                        }
                    });
                }
            }
        });

        patronCategoryIdCounter++;
        countIfpatronCategoryIdCounter++;
    }

    if (branchIdCounter <= '1' || branchDescCounter <= '1') {
        $.ajax({
            url: 'BranchId?unprintedDate=' + $('#forUnprint').val() +
                '&reprintDate=' + $('#forReprint').val() +
                '&inputPatronId=' + $('#forPatron').val() +
                '&reminder=' +
                $('input[name="reminderRadios"]:checked').val() +
                '&address=' +
                $('input[name="addressRadios"]:checked').val() +
                '&officerId=' + $('#officerId').val(),

            success: function(data) {
                if (data) {
                    var count = 0;
                    $.each(data, function(key, entry) {
                        $('#branchId2').append(
                            $("<option />").val(entry).text(entry));
                        count++;
                    })
                }
                $dropdown = $('#branchId2');
                $dropdown[0].selectedIndex = -1;
                $.ajax({

                    url: 'BranchDesc?unprintedDate=' + $('#forUnprint').val() +
                        '&reprintDate=' + $('#forReprint').val() +
                        '&inputPatronId=' + $('#forPatron').val() +
                        '&reminder=' +
                        $('input[name="reminderRadios"]:checked').val() +
                        '&address=' +
                        $('input[name="addressRadios"]:checked').val() +
                        '&officerId=' + $('#officerId').val(),

                    success: function(data1) {
                        if (data1) {
                            var count1 = 0;
                            $.each(data1, function(key, entry) {
                                $('#branchDesc2').append(
                                    $("<option />").val(entry).text(entry));
                                count1++;
                            })
                        }
                        $dropdown = $('#branchDesc2');
                        $dropdown[0].selectedIndex = -1;
                    }
                });

            }
        });
        branchIdCounter++;
    }

    if (patronStatusIdCounter <= '1' || patronStatusDescCounter <= '1') {
        $.ajax({

            url: 'PatronStatusId?unprintedDate=' + $('#forUnprint').val() +
                '&reprintDate=' + $('#forReprint').val() +
                '&inputPatronId=' + $('#forPatron').val() +
                '&reminder=' +
                $('input[name="reminderRadios"]:checked').val() +
                '&address=' +
                $('input[name="addressRadios"]:checked').val() +
                '&officerId=' + $('#officerId').val(),

            success: function(data) {
                if (data) {
                    var count = 0;
                    $.each(data, function(key, entry) {
                        $('#patronStatusId3').append(
                            $("<option />").val(entry).text(entry));
                        count++;
                    })
                }
                $dropdown = $('#patronStatusId3');
                $dropdown[0].selectedIndex = -1;
                $.ajax({

                    url: 'PatronStatusDesc?unprintedDate=' +
                        $('#forUnprint').val() + '&reprintDate=' +
                        $('#forReprint').val() + '&inputPatronId=' +
                        $('#forPatron').val() + '&reminder=' +
                        $('input[name="reminderRadios"]:checked').val() +
                        '&address=' +
                        $('input[name="addressRadios"]:checked').val() +
                        '&officerId=' + $('#officerId').val(),
    
                    success: function(data1) {
                        if (data1) {
                            var count1 = 0;
                            $.each(data1, function(key, entry) {
                                $('#patronStatusDesc3').append(
                                    $("<option />").val(entry).text(entry));
                                count++;
                            })
                        }
                        $dropdown = $('#patronStatusDesc3');
                        $dropdown[0].selectedIndex = -1;
                    }
                });
            }
        });
        patronStatusIdCounter++;
    }


    $(".patronCategoryId").change(function() {
        var id = $(this).data('id');
        var index = $(this).prop('selectedIndex');
        $("#patronCategoryDesc" + id).prop('selectedIndex', index);
    });

    $(".patronCategoryDesc").change(function() {
        var id = $(this).data('id');
        var index = $(this).prop('selectedIndex');
        $("#patronCategoryId" + id).prop('selectedIndex', index);
    });

    $(".branchId").change(function() {
        var id = $(this).data('id');
        var index = $(this).prop('selectedIndex');
        $("#branchDesc" + id).prop('selectedIndex', index);
    });

    $(".branchDesc").change(function() {
        var id = $(this).data('id');
        var index = $(this).prop('selectedIndex');
        $("#branchId" + id).prop('selectedIndex', index);
    });

    $(".patronStatusId").change(function() {
        var id = $(this).data('id');
        var index = $(this).prop('selectedIndex');
        $("#patronStatusDesc" + id).prop('selectedIndex', index);
    });

    $(".patronStatusDesc").change(function() {
        var id = $(this).data('id');
        var index = $(this).prop('selectedIndex');
        $("#patronStatusId" + id).prop('selectedIndex', index);
    });

    
    $('#retrieveBtn').click(function() {
	

    	if($.urlParam('value')=='on'){
				
				/*alert("1"+$("input[name='retrieveRadios']:checked").val());
    	        alert("2"+$('#forUnprint').val());
    	        alert("3"+$('#forReprint').val());
    	       alert("4"+ $('#forPatron').val());
    	        alert("5"+$('#patronCategoryId1').val());
    	        alert("6"+$('#branchId2').val());
    	       alert("7"+$("input[name='reminderRadiosName']:checked").val());
    	        alert("8"+ $("input[name='addressRadiosName']:checked").val());
    	        alert("9"+$('#officerCB').is(':checked'));
    	       alert("10"+$('#officerId').val());
    	        alert("11"+$('#patronStatusId3').val());*/
	
	
    	       var table = $('#overdue_table').DataTable({
					dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_OverdueNotification',
					        title: 'List Overdue Notification',
					                
					 	},
						/*{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_OverdueReminder',
							charset: "utf-8",
							bom : "true",
							orientation: 'portrait', //landscape
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
								//doc.defaultStyle.font = 'Arial';
								//doc.styles.tableHeader.alignment = 'left';
								
								var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][0].alignment = 'left';
										doc.content[1].table.body[i][1].alignment = 'left';
										doc.content[1].table.body[i][2].alignment = 'left';
										doc.content[1].table.body[i][3].alignment = 'left';
						           };
								
							 	doc.content[1].table.widths = [ '8%',  '12%', '60%', '20%']; 
							

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Group Listing'+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
						                        margin: [0, 0, 0, 12],
						                    });
					
											doc['footer']=(function(page, pages) {
												return {
													columns: [
														{
															alignment: 'left',
															text: ['\t\t\t\t  ', moment(today).format("DD/MM/YYYY") +'\t' +moment(today).format("hh:mm:ss A")]
														},
														{
															alignment: 'right',
															text: ['page ', { text: page.toString() },	' of ',	{ text: pages.toString() }]
														}
													],
													margin: 20
												}
											});
									}, 
					    },*/
					
               			{
		                	extend: 'csv',
		                	filename: 'WILMU_OverdueNotification',
		                	//title: 'Orders Report By ,
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										
										return "Overdue Notification" +"\n\n" + doc;
									 }
             			},
            ],
    	            destroy: true,
    	            searching: false,
    	            bLengthChange: false,
    	            autoWidth: false,
    	            //					responsive: true,
    	            aaSorting: [],
    	            processing: true,
    	            select: {
    	                style: 'multi'
    	            },
    	            
    	            ajax: {
    	                url: "OverdueNotificationList",
    	                data: {
    	                    retrieveType: $("input[name='retrieveRadios']:checked").val(),
    	                    unprintedDate: $('#forUnprint').val(),
    	                    reprintDate: $('#forReprint').val(),
    	                    inputPatronId: $('#forPatron').val(),
    	                    patronCategoryId: $('#patronCategoryId1').val(),
    	                    branchId: $('#branchId2').val(),
    	                    reminder: $("input[name='reminderRadiosName']:checked").val(),
    	                    address: $("input[name='addressRadiosName']:checked").val(),
    	                    officerIdStatus: $('#officerCB').is(':checked'),
    	                    officerId: $('#officerId').val(),
    	                    patronStatusId: $('#patronStatusId3').val()
    	                },

    	                dataSrc: function(json) {
    	                    var return_data = new Array();
    	                    $.fn.dataTable.ext.errMode = 'none';
    	                    for (var i = 0; i < json.length; i++) {
    	                        var stat = json[i].status;
    	                        return_data.push({
    	                            //							'checkAll': "<tr><td><input class='bookChk' onchange='getValue()'value='"+ json[i].accessionNo +"' type='checkbox' name = 'selectedValue[]' checked/></td>",
    	                            'Accession No': json[i].accessionNo,
    	                            'Patron': json[i].patron,
    	                            'Title': json[i].title,
    	                            'Call No': json[i].callNo,
    	                            'Due Date': json[i].dueDate,
    	                            'Reminder Type': json[i].reminderType,
    	                            'Late By': json[i].countLateBy,
    	                            'Fines': json[i].countFine,
    	                            'Email': json[i].email,
    	                            'Branch': json[i].branch,
    	                            'Location': json[i].location,
    	                        })
    	                    }
    	                    return return_data;
    	                },
    	            },
    	            columns: [
    	                //		     		{'data': 'checkAll'},
    	                {
    	                    'data': 'Accession No'
    	                },
    	                {
    	                    'data': 'Patron'
    	                },
    	                {
    	                    'data': 'Title'
    	                },
    	                {
    	                    'data': 'Call No'
    	                },
    	                {
    	                    'data': 'Due Date'
    	                },
    	                {
    	                    'data': 'Reminder Type'
    	                },
    	                {
    	                    'data': 'Late By'
    	                },
    	                {
    	                    'data': 'Fines'
    	                },
    	                {
    	                    'data': 'Email'
    	                },
    	                {
    	                    'data': 'Branch'
    	                },
    	                {
    	                    'data': 'Location'
    	                },
    	            ],
    	            ///'dom': 'Rlfrtip',
    	            ///'colReorder': {
    	                ///'allowReorder': false
    	           // },
    	        });
    	}else if($.urlParam('value')=='or'){

/*    	    if($('#patronCate option[value]:selected').text()==''){
    	    	alert("You haven't selected anything in Patron Category!");
    	    }else if($('#brch option[value]:selected').text()=='' && $('#loca option[value]:selected').text()=='' ){
    	    	alert("You haven't selected anything in Branch or Location!");
    	    		
    	    	}else{
*/
	            var startDate = document.getElementById("dateFrom").value;
	            var endDate = document.getElementById("dateTo").value;
	
	            var startDateSplit = startDate.split('/');
	            var endDateSplit = endDate.split('/');
	            // Month is zero-indexed so subtract one from the month inside the constructor
	            var formattedStartDate = new Date(startDateSplit[2], startDateSplit[1] - 1, startDateSplit[0]); //Y M D 
	            var formattedEndDate = new Date(endDateSplit[2], endDateSplit[1] - 1, endDateSplit[0]); //Y M D 
	
	            var startTimeStamp = formattedStartDate.getTime();
	            var endTimeStamp = formattedEndDate.getTime();
	
	            $("#overReminderForm").data('bootstrapValidator').resetForm();
	
	            var validator = $('#overReminderForm').data('bootstrapValidator');
	
	            validator.validate();
	    		
	            if (validator.isValid()) {
	            
	            	if(endTimeStamp < startTimeStamp){
	
	                    swal("End date should be greater than Start date");
	                    document.getElementById("dateTo").value = "";
	                }else{
	            	
			 	       var table = $('#overdueRmd_table').DataTable({
				dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_OverdueReminder',
					        title: 'List Overdue Reminder',
					                
					 	},
						/*{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_OverdueReminder',
							charset: "utf-8",
							bom : "true",
							orientation: 'portrait', //landscape
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
								//doc.defaultStyle.font = 'Arial';
								//doc.styles.tableHeader.alignment = 'left';
								
								var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][0].alignment = 'left';
										doc.content[1].table.body[i][1].alignment = 'left';
										doc.content[1].table.body[i][2].alignment = 'left';
										doc.content[1].table.body[i][3].alignment = 'left';
						           };
								
							 	doc.content[1].table.widths = [ '8%',  '12%', '60%', '20%']; 
							

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Group Listing'+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
						                        margin: [0, 0, 0, 12],
						                    });
					
											doc['footer']=(function(page, pages) {
												return {
													columns: [
														{
															alignment: 'left',
															text: ['\t\t\t\t  ', moment(today).format("DD/MM/YYYY") +'\t' +moment(today).format("hh:mm:ss A")]
														},
														{
															alignment: 'right',
															text: ['page ', { text: page.toString() },	' of ',	{ text: pages.toString() }]
														}
													],
													margin: 20
												}
											});
									}, 
					    },*/
					
               			{
		                	extend: 'csv',
		                	filename: 'WILMU_OverdueReminder',
		                	//title: 'Orders Report By ',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										
										return "Overdue Reminder" +"\n\n" + doc;
									 }
             			},
            ],
				            destroy: true,
				            searching: false,
				            bLengthChange: false,
				            autoWidth: false,
				            //responsive: true,
				            aaSorting: [],
				            processing: true,
				            select: {
				                style: 'multi'
				            },
				            
				      
							responsive: true,
							deferRender: true,
							footer: true,
				            
				            
				            
				            ajax: {
				                url: "OverdueReminderList", 
				                data: {
				                    startDate: $('#startDate input[type=text]').val(),
				                    endDate: $('#endDate input[type=text]').val(),
				                    patronCate: $('#patronCate').val(),
				                    branch: $('#brch').val(),
				                    loca: $('#loca').val(),
				                    address: $("input[name='addressRadiosName']:checked").val(),
				                },
			
				                dataSrc: function(json) {
				                    var return_data = new Array();
				                    $.fn.dataTable.ext.errMode = 'none';
				                    for (var i = 0; i < json.length; i++) {
				                        var stat = json[i].status;
				                        return_data.push({
				                            //'checkAll': "<tr><td><input class='bookChk' onchange='getValue()'value='"+ json[i].accessionNo +"' type='checkbox' name = 'selectedValue[]' checked/></td>",
				                            'Accession No': json[i].accessionNo,
				                            'Patron': json[i].patron,
				                            'Title': json[i].title,
				                            'Call No': json[i].callNo,
				                            'Due Date': json[i].dueDate,
				                            'Reminder Type': json[i].reminderType,
				                            'Email': json[i].email,
				                            'Branch': json[i].branch,
				                            'Location': json[i].location,
				                        })
				                    }
				                    return return_data;
				                },
				            },
				            columns: [
				                //		     		{'data': 'checkAll'},
				                {
				                    'data': 'Accession No'
				                },
				                {
				                    'data': 'Patron'
				                },
				                {
				                    'data': 'Title'
				                },
				                {
				                    'data': 'Call No'
				                },
				                {
				                    'data': 'Due Date'
				                },
				                {
				                    'data': 'Reminder Type'
				                },
				                {
				                    'data': 'Email'
				                },
				                {
				                    'data': 'Branch'
				                },
				                {
				                    'data': 'Location'
				                },
				            ],
				            //'dom': 'Rlfrtip',
				            //'colReorder': {
				                ///'allowReorder': false
				           // },
				        });
	                }
	            
	            }
	    	}
//    	}
    });

    $('input[type=radio][name=retrieveRadios]').click(
        function() {
            var related_class = $(this).val();
            $('.' + related_class).prop('disabled', false);

            if (related_class == "reprint") {
                $("#btnOpenReprintModal").attr("disabled", false);
            } else {
                $("#btnOpenReprintModal").attr("disabled", true);
            }

            $('input[type=radio][name=retrieveRadios]').not(
                ':checked').each(function() {
                var other_class = $(this).val();
                $('.' + other_class).prop('disabled', true);

                $('.' + other_class).val("");

            });

            if (related_class == "unprint") {

                var now = new Date();
                var currentDate = fixDigit(now.getDate()) + '/' +
                    fixDigit(now.getMonth() + 1) +
                    '/' + now.getFullYear();

                $('#forUnprint').val(currentDate);
                $("#forUnprint").prop('disabled', true);
            }


        });


    // Utility function to prepend zeros to single digits:
    function fixDigit(val) {
        return val.toString().length === 1 ? "0" + val : val;
    }

    $('input[type=radio][name=optradio]').click(
        function() {
            var related_class = $(this).val();
            $('.' + related_class).prop('disabled', false);

            $('input[type=radio][name=retrieveRadios]').not(
                ':checked').each(function() {
                var other_class = $(this).val();
                $('.' + other_class).prop('disabled', true);
                $('.' + other_class).val("");
            });
        });

    // disable patron status by radio button
    $('input[type=radio][name=patronStatusRadios]').change(function() {
        if ($(this).val() == 'change') {
            $('#patronStatusId3').attr('disabled', false);
            $('#patronStatusDesc3').attr('disabled', false);
        } else if ($(this).val() == 'unchange') {
            $('#patronStatusId3').attr('disabled', true);
            $('#patronStatusDesc3').attr('disabled', true);
        }
    });

    // disable officer id by radio button
    $('#officerCB').change(function() {
        $('#officerId').prop("disabled", !this.checked);
        $('#officerId').val("");
    });
    
    // disable OR officer id by radio button
    $('#orOfficerCB').change(function() {
        $('#orOfficerId').prop("disabled", !this.checked);
        $('#orOfficerId').val("");
    });

    $('#exampleModal').on('show.bs.modal', function(event) {

        $('#model_table_a').DataTable({
            destroy: true,
            searching: false,
            bLengthChange: false,
            autoWidth: false,
            responsive: true,
            processing: true,
            aaSorting: [],
            ajax: {
                url: "ReprintDate",
                type: "GET",
                dataSrc: function(json) {
                    var return_data = new Array();
                    var countEmail = Object.keys(json).length;
                    $('#countMail').text(countEmail);
                    for (var i = 0; i < json.length; i++) {
                        return_data.push({

                            'Printed Date': json[i],

                        })
                    }
                    return return_data;
                },
            },
            columns: [

                {
                    'data': 'Printed Date'
                },

            ],
        });

    });

    $('#exampleModal').on("hidden.bs.modal", function() {

        var table = $('#model_table_a').DataTable();

        var selectedRowValue = table.row('.selected').data();

        $("#forReprint").val(selectedRowValue["Printed Date"]);

    });

    $(document).on('click dblclick', '#model_table_a > tbody >tr', function() {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#model_table_a > tbody > tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }

        var getDateFromCell = ($(this).text());

        $('#model_table_b').DataTable({
            destroy: true,
            searching: false,
            bLengthChange: false,
            autoWidth: false,
            responsive: true,
            processing: true,
            ajax: {
                url: "ReprintNotice",
                data: {
                    "reprintDate": getDateFromCell
                },
                type: "GET",
                dataSrc: function(json) {
                    var return_data = new Array();
                    var countEmail = Object.keys(json).length;
                    $('#countMail').text(countEmail);
                    for (var i = 0; i < json.length; i++) {
                        var stat = json[i].status;
                        return_data.push({

                            'Accession No': json[i].accessionNo,
                            'Title': json[i].title,
                        })
                    }
                    return return_data;
                },
            },
            columns: [

                {
                    'data': 'Accession No'
                },
                {
                    'data': 'Title'
                },

            ],
        });
    });

    $('#overdue_table').on('select.dt deselect.dt', function() {
        $('#retrieveBtn').prop('disabled', true);
        $('#previewBtn').prop('disabled', false);
        $('#printOverdue').prop('disabled', false);
        $('#printOverdue').attr("href", "#nav-panel");
        $('#emailBtn').prop('disabled', false);

    })

        $('#overdueRmd_table').on('select.dt deselect.dt', function() {
        $('#retrieveBtn').prop('disabled', true);
        $('#previewBtn').prop('disabled', false);
        $('#printOverdue').prop('disabled', false);
        $('#printOverdue').attr("href", "#nav-panel");
        $('#emailBtn').prop('disabled', false);

    })

    $('#previewBtn').click(function() {

        if($.urlParam('value')=='on'){
            var table = $('#overdue_table').DataTable();
            var tableData = table.rows({
                selected: true
            }).data().toArray();

            var dataJSON = JSON.stringify(tableData);

            $.ajax({
                url: "GeneratePreviewDocument",
                dataType: 'json',
                data: {
                    json: JSON.stringify(tableData), 
                    reminderType: $("input[name='reminderRadiosName']:checked").val(),
                    liferayLogin: liferayLogin
                },
	            beforeSend: function() {
	            	swal({
	            	     title: 'Retrieving data from Database.'
	            	});
	            	swal.showLoading();
	            },
                success: function(data) {
                    if (data) {
                    	swal.close();
                        var winOpen = window.open("previewNotification");
                        winOpen.document.write(data);

                    } else if (!data) {
                    }
                }
            });

        }else if($.urlParam('value')=='or'){
            var table = $('#overdueRmd_table').DataTable();
            var tableData = table.rows({
                selected: true
            }).data().toArray();

            var dataJSON = JSON.stringify(tableData);

            $.ajax({
                url: "GeneratePreviewDocument",
                dataType: 'json',
                data: {
                    json: JSON.stringify(tableData)
                },
	            beforeSend: function() {
	            	swal({
	            	     title: 'Retrieving data from Database.'
	            	});
	            	swal.showLoading();
	            },
                success: function(data) {

                    if (data) {
                    	swal.close();
                        var winOpen = window.open("previewNotification");
                        winOpen.document.write(data);

                    } else if (!data) {
                    }
                }
            });
        }
        
 
    });


    //Print overdue into local
    $('#printOverdue').click(function() {

        if($.urlParam('value')=='on'){
	        var table = $('#overdue_table').DataTable();
	        var tableData = table.rows({
	            selected: true
	        }).data().toArray();
	
	        var dataJSON = JSON.stringify(tableData);
	
	        $.ajax({
	            url: "PrintDocument",
	            dataType: 'json',
	            data: {
	                json: JSON.stringify(tableData),
	                liferayLogin: liferayLogin,
	                reminderType: $("input[name='reminderRadiosName']:checked").val(),
	                overdueType: "notification"
	            },
	            beforeSend: function() {
	            	swal({
	            	     title: 'Retrieving data from Database.'
	            	});
	            	swal.showLoading();
	            },
	            success: function(data) {
	                if (data) {
	                	
	                    var w = window.open();
	                    swal.close();
	
	                    w.document.write(data.htmlLetter);
	                    w.print();
	
	                    for (var i = 0; i < data.insertedRow.length; i++) {
	                        var accNo = data.insertedRow[i];
	                        table.rows().nodes().each(function(a, b) {
	                            if ($(a).children().eq(0).text() == accNo) {
	                                table.rows(a).remove();
	
	                            }
	
	                            return false;
	                        });
	                        table.rows().invalidate();
	                        table.draw();
	                    }
	
	                } else if (!data) {
	                }
	            }
	        });
        }else if($.urlParam('value')=='or'){
        	
	        var table = $('#overdueRmd_table').DataTable();
	        var tableData = table.rows({
	            selected: true
	        }).data().toArray();
	
	        var dataJSON = JSON.stringify(tableData);
	
	        $.ajax({
	            url: "PrintDocument",
	            dataType: 'json',
	            data: {
	                json: JSON.stringify(tableData),
	                liferayLogin: liferayLogin,
	                overdueType: "reminder"
	            },
	            beforeSend: function() {
	            	swal({
	            	     title: 'Retrieving data from Database.'
	            	});
	            	swal.showLoading();
	            },
	            success: function(data) {
	                if (data) {
	                	
	                    var w = window.open();
	                    swal.close();
	
	                    w.document.write(data.htmlLetter);
	                    w.print();
/*	
	                    for (var i = 0; i < data.insertedRow.length; i++) {
	                        var accNo = data.insertedRow[i];
	                        table.rows().nodes().each(function(a, b) {
	                            if ($(a).children().eq(0).text() == accNo) {
	                                table.rows(a).remove();
	
	                            }
	
	                            return false;
	                        });
	                        table.rows().invalidate();
	                        table.draw();
	                    }
	*/
	                } else if (!data) {
	                }
	            }
	        });
        }
    });

    $('#emailBtn').click(function() {

        if($.urlParam('value')=='on'){
        
	        var table = $('#overdue_table').DataTable();
	        var tableData = table.rows({
	            selected: true
	        }).data().toArray();
	
	        var dataJSON = JSON.stringify(tableData);
	
	        swal({
	            text: "Are you sure to send notification?",
	            showCancelButton: true,
	            confirmButtonColor: '#3085d6',
	            cancelButtonColor: '#d33',
	            confirmButtonText: 'Yes',
	            cancelButtonText: 'No',
	            confirmButtonClass: 'confirm-class',
	            cancelButtonClass: 'cancel-class',
	            closeOnConfirm: false,
	            closeOnCancel: false
	        }).then(
	            function() {
	                $.ajax({
	                    url: "SentOverdueToMailBrowser",
	                    dataType: 'json',
	                    data: {
	                        json: JSON.stringify(tableData),
	                        sender: liferayLogin,
	                        reminderType: $("input[name='reminderRadiosName']:checked").val()
	                    },
	                    success: function(data) {
	
	                        for (var i = 0; i < data.insertedRow.length; i++) {
	                            var accNo = data.insertedRow[i];
	                            table.rows().nodes().each(function(a, b) {
	                                if ($(a).children().eq(0).text() == accNo) {
	                                    table.rows(a).remove();
	
	                                }
	
	                                return false;
	                            });
	                            table.rows().invalidate();
	                            table.draw();
	                        }
	                        swal("Overdue Notification has sent to Mail Browser successfully.");
	                    }
	                });
	            });
        	}else if($.urlParam('value')=='or'){
        		
    	        var table = $('#overdueRmd_table').DataTable();
    	        var tableData = table.rows({
    	            selected: true
    	        }).data().toArray();
    	
    	        var dataJSON = JSON.stringify(tableData);
    	
    	        swal({
    	            text: "Are you sure to send notification?",
    	            showCancelButton: true,
    	            confirmButtonColor: '#3085d6',
    	            cancelButtonColor: '#d33',
    	            confirmButtonText: 'Yes',
    	            cancelButtonText: 'No',
    	            confirmButtonClass: 'confirm-class',
    	            cancelButtonClass: 'cancel-class',
    	            closeOnConfirm: false,
    	            closeOnCancel: false
    	        }).then(
    	            function() {
    	                $.ajax({
    	                    url: "SentOverdueToMailBrowser",
    	                    dataType: 'json',
    	                    data: {
    	                        json: JSON.stringify(tableData),
    	                        sender: liferayLogin
    	                    },
    	                    success: function(data) {
/*    	
    	                        for (var i = 0; i < data.insertedRow.length; i++) {
    	                            var accNo = data.insertedRow[i];
    	                            table.rows().nodes().each(function(a, b) {
    	                                if ($(a).children().eq(0).text() == accNo) {
    	                                    table.rows(a).remove();
    	
    	                                }
    	
    	                                return false;
    	                            });
    	                            table.rows().invalidate();
    	                            table.draw();
    	                        }*/
    	                        swal("Overdue Notification has sent to Mail Browser successfully.");
    	                    }
    	                });
    	            });
        	}
    });

    function messageBox(code, criteria, label) {
        var url = "Error_Message?GL79ERRCODE=" + code + "" +
            "&criteria=" + criteria + "&label=" + label + "";
        $.ajax({
            url: url,
            success: function(result) {
                //swal('',result, 'info' );
                swal(result);
            }
        });
    }
    
    //////////////////////////////////
    $('#patronCate, #loca, #brch').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true,
   		numberDisplayed: 1,
	});

});