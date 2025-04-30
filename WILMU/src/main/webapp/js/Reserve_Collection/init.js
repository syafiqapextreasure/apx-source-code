
var selectedRowValue = [];

$(document).ready(function() {
	
	var table = $('#rsvCollectionMaster_table').DataTable({
        destroy: true,
        searching: false,
        bLengthChange: false,
        autoWidth: false,
        responsive: true,
        aaSorting: [],
        processing: true,
    });
    
/*	
	var tableRCMaster = $('#rsvCollectionMaster_table').DataTable({
		select : false,
		destroy : true,
		searching : false,
		lengthChange: false,
		bInfo: true,
		info: true,
		paging: true,
		columnDefs : [{
            "targets": -1,
            "data": null,
            "defaultContent": "<a id='viewReserve' href='view-reserve' class='btn btn-info' role='button' data-toggle='modal' data-target='#viewModal' data-action='view'><span class='glyphicon glyphicon-eye-open'></span></a>&nbsp"+
             				"<a id='editReserve' href='edit-reserve' class='btn btn-info' role='button' data-toggle='modal' data-target='#editModal' data-action='edit'><span class='glyphicon glyphicon-edit'></span></a>&nbsp"+
             				"<a id='deleteReserve' href='delete-reserve' class='btn btn-info' role='button' data-toggle='modal' data-target='#deleteModal' data-action='delete'><span class='glyphicon glyphicon-trash'></span></a>"
		},
		{
            "targets": [2],
            "visible": false,
            "searchable": false
        },
        {
            "targets": [4],
            "visible": false,
            "searchable": false
        },
        {
            "targets": [5],
            "visible": false,
            "searchable": false
        },
        {
            "targets": [8],
            "visible": false,
            "searchable": false
        },
        {
            "targets": [9],
            "visible": false,
            "searchable": false
        },
        {
            "targets": [11],
            "visible": false,
            "searchable": false
        }
		],
//		"dom": '<"top"iflp<"clear">>rt<"bottom"iflp<"clear">>',
        'colReorder': {
            'allowReorder': true
        },
	});
    */
    $('#rsvCollectionMaster_table tbody').on( 'click', 'a', function () {
    	
    	selectedRowValue = tableRCMaster.row( $(this).parents('tr') ).data();
    	
    	row = tableRCMaster.row( $(this).parents('tr') );

    	console.log('init.js selected value: '+selectedRowValue);

    } );

});