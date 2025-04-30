/**
 * 
 */

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
	
	function viewForm(){
		$("#formroutingmaster input").attr('disabled','disabled');
		$("#formroutingmaster input[type=checkbox]").attr('disabled', true);
		$('#formroutingmaster select').prop('disabled', 'disabled');
		$("#okBtn").hide();
		$("#close").show();
	}
	
	function editForm(){
		$("#formroutingmaster input").attr('disabled','disabled');
		$("#formroutingmaster input[type=checkbox]").attr('disabled', true);
		$('#formroutingmaster select').prop('disabled', 'disabled');
		$("#graceperiod").attr('disabled',false);
		$("#okBtn").show();
		$("#close").show();
	}
	
	
$(document).ready(function() {
	
	var todayDate = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	
	$('#dateRecorded').text(moment(todayDate).format("DD/MM/YYYY"));
	$("#recordedBy").text(liferayLogin);
	$(".CT03MATNO").attr('maxlength','10');
	
	var tableRCMaster = $('#routingTable').DataTable();
	
	$('#findBtn').click(function(){
		var getChecked = $('input[name=retrieveRadios]:checked').val();
		var getCriteria = $("input[name=inputCriteria]").val();
		var tableViewItemDetail = $('#routingTable').DataTable({
            destroy: true,
            searching: false,
            bLengthChange: false,
            autoWidth: false,
            responsive: true,
            aaSorting: [],
            processing: true,
            ajax: {
                url: "DisplayRoutingMaster",
                data: {
                    getChecked: getChecked,
                    getCriteria:getCriteria
                },
                type: "GET",
                dataSrc: function(data) {

                    var return_data = new Array();
                    $.fn.dataTable.ext.errMode = 'none';
                    	var count = 1;
                    for (var i = 0; i < data.length; i++) {
                        var stat = data[i].status;
                        return_data.push({
                            'No': count++,
                            'Title': data[i].TITLE,
                            'Patron Name': (data[i].NAME),
                            'Copy No': (data[i].SE15CPYNO),
                            'Priority': (data[i].SE15PRIORITY),
                            'Action':    '<button id="Viewform" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalroutingmaster" data-mode="3" title="View Routing" data-rowid="'+data[i].SE15ROUTINGNO+'"><span class="glyphicon glyphicon-eye-open"></span></button> ' + 
				            '<button id="Editform" class="btn btn-info btn-sm Edit" data-toggle="modal" data-target="#modalroutingmaster" data-mode="2" title="Edit Routing" data-rowid="'+data[i].SE15ROUTINGNO+'"><span class="glyphicon glyphicon-pencil"></span></button> ' + 
				            '<button id="Deleteform" class="btn btn-dull btn-sm" data-reserve="'+data[i].SE15ROUTINGNO+'" onclick="deleteRouting(this)"><span class="glyphicon glyphicon-trash" title="Delete Reserve Collection" ></span></button>',
                        });
                    }
                    return return_data;
                },
            },
            columns: [
            	{
                    'data': 'No'
                },
                {
                    'data': 'Title'
                },
                {
                    'data': 'Patron Name'
                },
                {
                    'data': 'Copy No'
                },
                {
                    'data': 'Prority'
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
	})
	
	$("#input-contorlNo").blur(function(e){
		
		var ctrlNo = $("#input-contorlNo").val();
		//alert("vendorCode " + vendorCode);
		$("#titles").val('');
		////display vendor name
		 $.ajax({
	            url: "RetrieveCatBibByMatno",
	            data: {
	                controlNo:ctrlNo
	            },
	            type: "GET",
	            success: function(data) {
	                $('#titles').val(data[0].title);
	            }
	        });
			$.ajax({
	            url: "RetrieveCopyNo",
	            data: {
	            	controlNo:ctrlNo
	            },
	            type: "GET",
	            success: function(data) {
	                $.each(data, function(key, entry) {
	                    $('#copySelect').append($("<option />").val(entry).text(entry));
	                });
	            }
	        });
	});
	
	
	$("#requestorId").blur(function(e){
		var reqid = $("#requestorId").val().trim();
		$.get('CheckRequestor', {
			patronID : reqid
	 	}, function(responseJson) {
		if(responseJson != null){
			if(responseJson==''){
				messageBox("036","",""); 
			}
			$.each(responseJson, function(key,value) {
					$.get('getPatronDept', {
						code : reqid
					 	}, function(responseJson) {
					 	if(responseJson != null){
					 	
							$.each(responseJson, function(key,value) {
								$("#div-reqname").text(value['Name']);
							});
						}
					});
				
			});
		}
		});
	});
	
	$("#copySelect").change(function(){
		var copy = $(this).val();
		$("#priority").val(copy);
	})
	
	//****************************************** AREA FOR MODAL *****************************************//
	 
	 //////modal add, edit, view
	 var stored;
	 $('#modalroutingmaster').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('mode'); // Extract info from data-* attributes

		var rowid = button.data('rowid');
		switch(recipient){
			case state = 1:

				modal.find('.modal-title').text("Add New Routing");
				$("#okBtn").prop("disabled",false);
				$("select").prop("selectedIndex",0);
				$("input[type=text]").empty();
				
	    		stored = [];
		  		break;
			case state = 2:
				
				editForm();
			$("#okBtn").prop("disabled",false);
				modal.find('.modal-title').text("Edit Routing");
				$.get("DisplayRoutingMaster?getChecked=routingno",{
				getCriteria : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						localStorage.setItem('rowid', rowid)
						
							$("#input-contorlNo").val(value['SE15MATNO']);
							$("#titles").val(value['TITLE']);
							//$("#title").text(value['title']);
							$("#requestorId").val(value['SE15PATR']);
							$("#div-reqname").text(value['NAME']);
							$("#copySelect").val(value['SE15CPYNO']);
							$("#graceperiod").val(value['SE15GRACE']);
							$("#priority").val(value['SE15PRIORITY']);
						
					});
				}
			});
			
	  			break;
			case state = 3:
				//$('#requestor').dataTable().fnClearTable();
				viewForm();
				modal.find('.modal-title').text("View Reserve Collection");
				$.get("DisplayRoutingMaster?getChecked=routingno",{
					getCriteria : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
						
							
							$("#input-contorlNo").val(value['SE15MATNO']);
							$("#titles").val(value['TITLE']);
							//$("#title").text(value['title']);
							$("#requestorId").val(value['SE15PATR']);
							$("#div-reqname").text(value['NAME']);
							$("#copySelect").val(value['SE15CPYNO']);
							$("#graceperiod").val(value['SE15GRACE']);
							$("#priority").val(value['SE15PRIORITY']);
							
						});
					}
				});
			
				break;
		}
		
	 });
	
});