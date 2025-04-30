/**
 * 
 */

function messageBox(code, criteria, label){

		$.get("Global?type=Handler&name=Error_Page",{GL79ERRCODE : code,
			criteria : criteria,
		 	label : label},function(result){
				swal(result);
			
		});
} 

function deleteReserve(reqCtrl){
	var reserveno = $(reqCtrl).attr('data-reserve');

	var table = $('#rsvCollectionMaster_table').DataTable();
	
	var index = $('#rsvCollectionMaster_table').DataTable()
       .rows({ search: 'applied'})
       .nodes()
       .to$()
       .index($(reqCtrl).closest('tr'));//$(this).closest('tr').index();
	//alert(index);
	swal({
		text: "Are you sure want to delete?",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes",
        cancelButtonText: "No"
      }).then(
        function(isConfirm) {
          if (isConfirm) {
        	  
        	  $.ajax({
    			  method: "POST",
    			  url: "deleteReserveCollection",
    			  data: { reserveno: reserveno}
    			}).done(function( msg ) {
    			    messageBox("005"," Deleted","@action");
    		})
    //		alert(index);
    		 console.log('41 index: '+ index);

/*				$.each($('#rsvCollectionMaster_table tbody .rowNumber'),function(i,v){
					console.log('52 i: '+i);
					$(this).text(i+1);
				})*/
//        	  
//        	  $('#rsvCollectionMaster_table tbody tr').each(function(i) {
//        		  console.log('52 i: '+i);
//        		  $($(this).find('td')[0]).html(i);   
//        	  });
//        	  
        	  $.each($('#rsvCollectionMaster_table tbody tr'),function(i,v){
					console.log('52 i: '+i);
					$($(this).find('td')[0]).html(i); 
        	  });
        	  
        	  setTimeout(function(){
					$('#rsvCollectionMaster_table').DataTable().row(index).remove().draw();
				}, 100);
    		
          }
     });
}


$(document).ready(function(){
	console.log('validate_AAVM150.js docuemnt.ready()');
	var count = 1;
	
$('#formreservecollection').bootstrapValidator({
	
	   feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            semMasterId: {
                validators: {
                	callback: {
                        message: 'Please select semester',
                        callback: function(value, validator) {
                            // Get the selected options
                        	var options = validator.getFieldElements('semMasterId').val();
                            return (options != null && options!=0);
                        }
                    }
                }
            },
            subj: {
                validators: {
                	callback: {
                        message: 'Please select subject',
                        callback: function(value, validator) {
                            // Get the selected options
                        	var options = validator.getFieldElements('subj').val();
                            return (options != null && options!=0);
                        }
                    }
                }
            },
        }
    })
     .on('success.form.bv', function (e) {
    	 console.log('line 88 save');
    	// Prevent form submission
         e.preventDefault();

         // Get the form instance
         var $form = $(e.target);

         // Get the BootstrapValidator instance
         var bv = $form.data('bootstrapValidator');
         
         //get title

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
     	 
     	 var output = [];
     	   
		    var table = $('#rsvCollectionMaster_table').DataTable();
	
		    var lastIndex = table.row(':last', { order: 'applied' }).data();
		    //get title
	     	 var modaltitle = $(".modal-title").text();

		 switch(modaltitle){
		 	case state = "Add New Reserve Collection":

		 		var itemindb = "N";
		 		  if(!$("#addMandatoryCheck").is(':checked')){
		 			  itemindb = "Y";
		 		  }
		 	
		 	
		 		$.post("InsertReserveCollection", { 
		 			courseId: $('#course').val(), 
        			semesterId: $('#semMasterId1').val(), 
        			subjectId: $('#subj').val(), 
        			instructor: $('#requestorId').val(), 
        			controlNo: $('#input-contorlNo').val(), 
        			startDate: $('#startDate').val(), 
        			endDate: $('#endDate').val(), 
        			createdDate: $('#dateRecorded').text(), 
        			recordedBy: $('#recordedBy').text(), 
        			title: $('#titles').val(), 
        			callNo: $('#callNo').val(), 
        			publication: $('#publication').val(), 
        			author: $('#author').val(), 
        			docNo: $('#accessionNo').val(),
        			itemindb : itemindb,
		 			}, 
		 	        function(data,status){
		 				messageBox("005"," Add Reserve Collection","@action");
		 				
		 			  	
			 	    	$.get("DisplayReserveDetails?getChecked=reserveno",{getCriteria : data},function(json){
			 	    		var totalRowCount = table.rows().count();
			 	    		console.log('total table row count: '+ totalRowCount);
			    			 var return_data = new Array();
			    			 for(var i=0;i< json.length; i++){
			    				  /*if (! table.data().any()) {
			    					  count = 1;
								        
								    } else {
								        var lastIndex = table.row(':last', { order: 'applied' }).data();
								        
								        count = parseInt(lastIndex[0]);
								   
								    }*/
			    				    
							        table.row.add([
							        	totalRowCount+1,
							            json[i].GL12DESC,
							            json[i].GL60DESC,
							            json[i].GL54DESC,
							            json[i].GL14NAME,			            
							            json[i].RE01TITLE,
							            '<button id="Viewform" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalreservecollection" data-mode="3" title="View Reserve" data-rowid="'+json[i].RC01RESERVENO+'"><span class="glyphicon glyphicon-eye-open"></span></button> ' + 
							            '<button id="Editform" class="btn btn-info btn-sm Edit" data-toggle="modal" data-target="#modalreservecollection" data-mode="2" title="Edit Order" data-rowid="'+json[i].RC01RESERVENO+'"><span class="glyphicon glyphicon-pencil"></span></button> ' + 
							            '<button id="Deleteform" class="btn btn-dull btn-sm" data-reserve="'+json[i].RC01RESERVENO+'" onclick="deleteReserve(this)"><span class="glyphicon glyphicon-trash" title="Delete Reserve Collection" ></span></button>'
							        ]).draw(false);
							        count++;
			    	            }
			    		 });
		 	         
				
		 	//     alert("ssss");
		 	        }
	    		).fail(function(data){
	    		}).success(function(data){
	    		});
		 		break;
		 	case state = "Edit Reserve Collection":
		 
		 		///alert("ff" +JSON.stringify(output2));
		 		var reserveno = localStorage.getItem('rowid');
		 
			$.post("updateReserveCollection", { 
		 		startdate : $("#startDate").val(),
		 		enddate : $("#endDate").val(),
		 		reserveno : reserveno
 			}, 
		 	    function(data,status){
		 	    	messageBox("005","Edit Reserve Collection","@action");
		 	    	$("#closeModalAdd").click();
		 	    	$('#rsvCollectionMaster_table').dataTable().fnClearTable();
		 	    	
		 	    	$.get("DisplayReserveDetails?getChecked=reserveno",{getCriteria : reserveno},function(json){
	    			 var return_data = new Array();
	    			 localStorage.clear();
	    			 for(var i=0;i< json.length; i++){
	    	            	table.row.add( [
	    	            	            1,
	    	            	            json[i].GL12DESC,
							            json[i].GL60DESC,
							            json[i].GL54DESC,
							            json[i].GL14NAME,			            
							            json[i].RE01TITLE,
							            '<button id="Viewform" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalreservecollection" data-mode="3" title="View Reserve" data-rowid="'+json[i].RC01RESERVENO+'"><span class="glyphicon glyphicon-eye-open"></span></button> ' + 
							            '<button id="Editform" class="btn btn-info btn-sm Edit" data-toggle="modal" data-target="#modalreservecollection" data-mode="2" title="Edit Order" data-rowid="'+json[i].RC01RESERVENO+'"><span class="glyphicon glyphicon-pencil"></span></button> ' + 
							            '<button id="Deleteform" class="btn btn-dull btn-sm" data-reserve="'+json[i].RC01RESERVENO+'" onclick="deleteReserve(this)"><span class="glyphicon glyphicon-trash" title="Delete Reserve Collection" ></span></button>'
	    	    			        ] ).draw( false );
	    	            }
	    		 });
		 	    	

		 	 
		 	    }).fail(function(data){
					swal("fail");
				})
		 		break;
		 }
	});
});