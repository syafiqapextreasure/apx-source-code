 
$(document).ready(function() {
	
    $("#receiver option:selected").text();

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

    $('#form-mail').bootstrapValidator({
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
    
    var table = $('#Email_table').DataTable({
        destroy: true,
        searching: false,
        bLengthChange: false,
        autoWidth: false,
        responsive: true,
        aaSorting: [],
        processing: true,
    });
    
    //click Refresh Subject
    $('button.refresh').click(function() {

        $("#form-mail").data('bootstrapValidator').resetForm();

        $('#Email_table tbody td').remove();
        $('.dataTables_info').hide();
        $('.dataTables_paginate.paging_simple_numbers').hide();

        $('#countMail').empty();
        var validator = $('#form-mail').data('bootstrapValidator');

        validator.validate();
        if (validator.isValid()) {
            $.ajax({
                url: 'MailBrowserSubject?startDate=' + $('#startDate input[type=text]').val() + '&endDate=' + $('#endDate input[type=text]').val() + '&receiver=' + $('#receiver').val() + '&sentMail=' + $('#sentMail').is(':checked'),
                beforeSend: function() {
                    $('#subject').empty();
                    $('#subject').append($("<option />").val('').text('Please select'));
                },
                success: function(data) {

                    if (data) {
                        $.each(data, function(key, entry) {
                            $('#subject').append($("<option />").val(entry).text(entry));
                            $('#countSubject').text(data.length);
                        })
                    } else if (!data) {
                        $('#countSubject').text(data.length);
                    }
                }
            });
        }
    });

    $('#subject').change(function() {

        var table = $('#Email_table').DataTable({
            destroy: true,
            searching: false,
            bLengthChange: false,
            autoWidth: false,
            responsive: true,
            processing: true,
            ajax: {
                url: "MailBrowserList",
                data: {
                    startDate: $('#startDate input[type=text]').val(),
                    endDate: $('#endDate input[type=text]').val(),
                    subject: $('#subject').val(),
                    sentMail: $('#sentMail').is(':checked')
                },
                type: "GET",
                dataSrc: function(json) {

                    $.fn.dataTable.ext.errMode = 'none';

                    var return_data = new Array();
                    var countEmail = Object.keys(json).length;
                    $('#countMail').text(countEmail);
                    for (var i = 0; i < json.length; i++) {
                        var stat = json[i].status;
                        return_data.push({
                            'No': (i + 1),
                            'Mail No': json[i].mailNo,
                            'Sender Id': json[i].sender,
                            'Send To': (json[i].sendToPatr),
                            'Email': json[i].email + " [" + json[i].receiverId + "]",
                            'Sent Date': json[i].sentDate + ",  " + json[i].sentTime,
                            'Action': '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalEmail" data-whatever="View Email" title="View Email" data-rowid="' + json[i].mailNo + '"><span class="glyphicon glyphicon-eye-open"></span></button></button> ' +
                                '<button id="Send" class="btn btn-primary btn-xs" data-mailNo="' + json[i].mailNo + '" data-email="' + json[i].email + '" title="Send Email" ><span class="glyphicon glyphicon-envelope"></span></button></button>&nbsp' +
                                '<button id="Delete" class="btn btn-dull btn-xs Delete" data-id="' + json[i].mailNo + '"><span class="glyphicon glyphicon-trash" title="Delete Email" ></span></button>',
                        })
                    }
                    return return_data;
                },
            },
            columns: [{
                    'data': 'No'
                },
                {
                    'data': 'Mail No'
                },
                {
                    'data': 'Sender Id'
                },
                {
                    'data': 'Send To'
                },
                {
                    'data': 'Email'
                },
                {
                    'data': 'Sent Date'
                },
                {
                    'data': 'Action'
                },
            ],
            'dom': 'Rlfrtip',
            'colReorder': {
                'allowReorder': false
            },
        });
        new $.fn.dataTable.ColReorder(table);
    });

    //Print Email into new tab browser
    $('#printBtn').click(function() {

        window.open("", "printForm", "width=500,height=300,toolbar=0").print();

    });

    //Save email into local
    $('#saveAs').click(function() {

        var mailNo = $('#modalView_mailNo').text();
        var message = '';
        // AJAX request
        $.ajax({
            url: 'SaveEmail?mailNo=' + mailNo,
            dataType: 'json',
            async: false,
            success: function(data) {
                message = data[0].message;
            }
        });

        var result = htmlToFormattedText(message);

        this.href = "data:text/plain;charset=UTF-8," + encodeURIComponent(result);

    });

    $('button.refresh').click(function() {

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
            swal("End date should be greater than Start date");
            document.getElementById("dateTo").value = "";
        }
    });


    //***************************************AREA FOR DELETE ********************************************//
    function messageBox(code, criteria, label) {
        var url = "Error_Message?GL79ERRCODE=" + code + "" +
            "&criteria=" + criteria + "&label=" + label + "";
        $.ajax({
            url: url,
            success: function(result) {
                swal(result);
            }
        });
    }

    $('#Email_table').on('click', '#Delete', function() {
        var deleteSelectedEmail = $(this).attr('data-id');

        var index = $('#Email_table').DataTable().rows({
                search: 'applied'
            })
            .nodes().to$().index($(this).closest('tr'));

        swal({
            text: "Are you sure want to delete?",
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
                    url: "Handler_DeleteEmail",
                    data: {
                        mailNo: deleteSelectedEmail
                    },
                    success: function(result) {
                        var status = result.replace(/\s+/g, '');
                        if (status == "ok") {
                            messageBox("005", "Deleted", "@action");
                            $('.swal2-confirm').click(
                                function() {
                                    $('#Email_table').DataTable().row(index).remove().draw();
                					
                			        $("#Email_table tbody .rowNumber").each(function (i){
                			               $(this).text(i+1);
                			            }); 
                                    
                                });
                            

                        } else {
                            swal("Unsuccessfully");
                        }
                    }
                });
            },
            function(dismiss) {
                if (dismiss == 'cancel') {
                    swal('', 'Cancelled');
                }
            })
    });
    //***********************************END AREA FOR DELETE ********************************************//   

    //***************************************SENT EMAIL NOTIFICATION ********************************************//
    function messageBox(code, criteria, label) {
        var url = "Error_Message?GL79ERRCODE=" + code + "" +
            "&criteria=" + criteria + "&label=" + label + "";
        $.ajax({
            url: url,
            success: function(result) {
                swal(result);
            }
        });
    }

    $('#Email_table').on('click', '#Send', function() {

        var sendSelectedMailNo = $(this).attr('data-mailNo');
        var sendSelectedEmail = $(this).attr('data-email');

        var index = $('#Email_table').DataTable().rows({
                search: 'applied'
            })
            .nodes().to$().index($(this).closest('tr'));

        swal({
            text: "Are you sure want to send this email?",
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
                    url: "Handler_SendEmail",
                    data: {
                        mailNo: sendSelectedMailNo,
                        email: sendSelectedEmail
                    },
                    success: function(result) {
                        var status = result.replace(/\s+/g, '');
                        if (status == "ok") {
                            messageBox("005", "Sent", "@action");
                            $('.swal2-confirm').click(
                                function() {
                                    $('#Email_table').DataTable().row(index).remove().draw();
                                });
                        } else {
                            swal("Unsuccessfully");
                        }
                    }
                });
            },
            function(dismiss) {
                if (dismiss == 'cancel') {
                    swal('', 'Cancelled');
                }
            })
    });
    //***********************************END AREA FOR DELETE ********************************************//   

    $("#print").click(function() {

        var mailNo = $('#modalView_mailNo').text();

        $.ajax({
            url: 'PrintEmail?mailNo=' + mailNo,
            dataType: 'json',
            success: function(data) {

                if (data != null) {

                    var str1 = data[0].message;
                    var str2 = "BEGIN";

                    if (str1.indexOf('html')) {

                        var w = window.open("", "printForm", "width=500,height=600,toolbar=0");
                        w.document.write("Mail No:  " + data[0].mailNo + "<br>" + "<br>");
                        w.document.write("Email:  " + data[0].receiverName + "<br>" + "<br>");
                        w.document.write("Subject:  " + data[0].subject + "<br>" + "<br>");

                        w.document.write(data[0].message.replace(/<\!--.*?-->/g, "").replace('href="<%= request.getContextPath() %>/css/receiptStyle.css"', ""));
                        w.document.close();
                        w.focus();
                        w.print();

                    } else {

                        var w = window.open("", "printForm", "width=500,height=600,toolbar=0");

                        var input = document.createElement('textarea');
                        input.cols = 170;
                        input.rows = 50;
                        input.style.fontSize = "16px";
                        input.style.border = "none";

                        w.document.write('');
                        w.document.write("Mail No:  " + data[0].mailNo + "<br>" + "<br>");
                        w.document.write("Email:  " + data[0].receiverName + "<br>" + "<br>");
                        w.document.write("Subject:  " + data[0].subject);
                        input.value = data[0].message

                        w.document.body.appendChild(input);
                        w.print();

                    }

                }

            }
        });

    })

});