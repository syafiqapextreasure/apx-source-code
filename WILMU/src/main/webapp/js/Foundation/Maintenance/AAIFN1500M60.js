$(document).ready(function() {
	
	
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	$('input[type="text"]').attr('autocomplete', 'off');
	
	//MAX LENGTH
	$("#inputcoursecode").attr('maxlength','20');
	$("#inputcoursecode").attr('maxlength','100');
	//$("#inputAccessLevel").attr('maxlength','4');
	
	//$(".div1vend").removeClass( "col-sm-3 col-md-3" ).addClass( "col-sm-2 col-md-2" );
	
	
	var t = $('#fndMaintTable').DataTable( {
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
		    	url: "ResultFndCourseCodeDescTutor",
		        type: "GET",
				/*start_time: new Date().getTime(),
				    complete: function(data) {
				        alert('This request took '+(new Date().getTime() - this.start_time)+' ms');
				},*/
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'Course Code' : json[i].Code,
		                'Description' : json[i].Desc,
						'Tutor' : json[i].Tutor,
						'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalcorsecode" data-mode="3" title="View Group Id" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalcorsecode" data-mode="2" title="Edit Group Id" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].groupid+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Course Code'},
					{'data': 'Description'},
					{'data': 'Tutor'},
					{'data': 'Action'},
				],
    	});
    	
    	
    	
    //****************************************** AREA FOR MODAL *****************************************//
	$('#modalcorsecode').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('mode'); // Extract info from data-* attributes
		var rowid = button.data('rowid');

		///modal.find('.modal-title').text(recipient);
		
		alert("recipient"+recipient);

		switch(recipient){
	  	case state = 1:
	  		enable();
	  		$('.modal-title').text("Add Course Code");
	  		//$('.modal-title').attr('form', 'Add Course Code');
	  		//$("#inputgroupId").focus();
	  		$('.daterec').text(moment(today).format("DD/MM/YYYY"));
	  		$(".recby").text(liferayLogin);	
	  		break;
	  	case state = 2:
	  		enable();
	  		$("#inputgroupId").prop("disabled", true);
	  		$.get('ResultFndCourseDetail', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {	
						$("#inputgroupId").val(value['groupid']);
						$("#inputGroupName").val(value['groupname']);
						$("#inputAccessLevel").val(value['accesslevel']);
						/*$(".daterec").text(value['daterec']);
						$(".recby").text(value['recby']);*/						
					});
				}
			});
	  		break;
	  	case state = 3:
	  		disable();
		  	$.get('ResultFndCourseDetail', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {	
						$("#inputcoursecode").val(value['Code']);
						$("#inputcoursedescription").val(value['Desc']);
						$("#lblPatronID").val(value['Tutor']);
						$(".daterec").text(value['Daterec']);
						$(".recby").text(value['Recby']);						
					});
				}
			});
	  		break;
	  }
	});
	//***************************************END AREA FOR MODAL *****************************************//
	
	
	//***************************************AREA FOR FUNCTION *****************************************//
	///// function enable at form 
	function enable(){
		$("input").prop("disabled", false);
  		$("#save, #cancel").show();
  		$("#close").hide();
	}
	
	////////////function disable at form 
	function disable(){
		$("input").attr('disabled','disabled');
  		$("#save, #cancel").hide();
  		$("#close").show();
	}
	//***********************************END AREA FOR FUNCTION *****************************************//
	
	//////remove patron lable
	$('label[for=Parent]').remove();
	$("#labelPatrId label").remove();
});