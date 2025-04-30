$(document).ready(function() {

    $('#modalEmail').on('show.bs.modal', function(event) {

        var button = $(event.relatedTarget) // Button that triggered the modal
        var modal = $(this);
        var recipient = button.data('whatever'); // Extract info from data-* attributes
        var rowid = button.data('rowid');

        modal.find('.modal-title').text(recipient);

        switch (recipient) {
            case state = 'Add Email':
                break;
            case state = 'Edit Email':
                $.get('MailBrowserView', {
                        mailNo: rowid
                    },
                    function(data) {
                        if (data != null) {
                        }
                    }
                );
                break;
            case state = 'View Email':
                $.get('MailBrowserView', {
                        mailNo: rowid
                    },
                    function(data) {
                        if (data != null) {
                            $('#htmlContent').empty();
                            var str1 = data.message;
                            var str2 = "BEGIN";

                      //      if (str1.indexOf(str2) != -1) {
                            if (str1.indexOf('html')) {

                                $('#modalView_message').hide();

                                $('#htmlContent').show();
                                $('#modalView_mailNo').text(data.mailNo);
                                $('#modalView_receiver').text(data.email);
                                $('#modalView_subject').text(data.subject);
                                $('#htmlContent').append(data.message);
                                // 	$('.test').html(data.message);

                            } else {

                                $('#htmlContent').hide();
                                $('#modalView_message').show();
                                $('#modalView_mailNo').text(data.mailNo);
                                $('#modalView_receiver').text(data.email);
                                $('#modalView_subject').text(data.subject);
                                $('#modalView_message').text(data.message);
                            }
                        }
                    }
                );
                break;
            case state = 'Print Mail':
                $.get('MailBrowserView', {
                        mailNo: rowid
                    },
                    function(data) {
                        if (data != null) {
                            $('#htmlContent').empty();
                            var str1 = data.message;
                            var str2 = "BEGIN";

                        }
                    }
                );
                break;
        }

    });

});