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


function deleteRouting(reqCtrl){
	var reserveno = $(reqCtrl).attr('data-reserve');

	var index = $('#routingTable').DataTable()
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
    			  url: "deleteRoutingMaster",
    			  data: { reserveno: reserveno}
    			}).done(function( msg ) {
    			    messageBox("005"," Deleted","@action");
    		})
    		
        	  setTimeout(function(){
					$('#routingTable').DataTable().row(index).remove().draw();
				}, 100);
    		
          }
     });
}


$(document).ready(function(){

	
$('#formroutingmaster').bootstrapValidator({
	
	   feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	inputControlNo: {
        		trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'The Contol Number is required'
                    }
                }
            },
            requestorId: {
        		trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'The Patron Id is required'
                    }
                }
            },
            copySelect: {
                validators: {
                	callback: {
                        message: 'Please select copy no',
                        callback: function(value, validator) {
                            // Get the selected options
                        	var options = validator.getFieldElements('copySelect').val();
                            return (options != null && options!=0);
                        }
                    }
                }
            },
        }
    })
     .on('success.form.bv', function (e) {
    	// Prevent form submission
         e.preventDefault();
         // Get the form instance
         var $form = $(e.target);
         // Get the BootstrapValidator instance
         var bv = $form.data('bootstrapValidator');
         
		    var controlno = $('.CT03MATNO').val();
		    var requestorid = $('.lblPatronID').val();
		    var title = $('#titles').val();
		    var copyno = $('#copySelect').val();
		    var grace = $('#graceperiod').val();
		    var priority = $('#priority').val();
		    var dateRecorded = $("#dateRecorded").text();
		    var recordedBy = $("#recordedBy").text();
     	 
		    var table = $('#routingTable').DataTable();
	
		    var lastIndex = table.row(':last', { order: 'applied' }).data();
		    //get title
	     	 var modaltitle = $(".modal-title").text();

		 switch(modaltitle){
		 	case state = "Add New Routing":
		 	
		 	
		 		$.post("InsertRoutingMaster", { 
		 			controlno:controlno, 
		 			patron: requestorid, 
		 			title: title, 
		 			copy: copyno, 
		 			prio:priority, 
        			graceperiod: grace, 
        			dateRecorded:dateRecorded,
        			recordedBy : recordedBy,
		 			}, 
		 	        function(data,status){
		 				
		 				messageBox("005"," Add Routing","@action");
		 				
		 			  	
			 	    	$.get("DisplayRoutingMaster?getChecked=routingno",{getCriteria : data},function(json){
			    			 var return_data = new Array();
			    	           var count = 1;
			    			 for(var i=0;i< json.length; i++){
			    			
			    				    
							        table.row.add([
							            count,
			                           json[i].TITLE,
			                            (json[i].NAME),
			                           json[i].SE15CPYNO,
			                           json[i].SE15PRIORITY,
			                            '<button id="Viewform" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalroutingmaster" data-mode="3" title="View Routing" data-rowid="'+json[i].SE15ROUTINGNO+'"><span class="glyphicon glyphicon-eye-open"></span></button> ' + 
							            '<button id="Editform" class="btn btn-info btn-sm Edit" data-toggle="modal" data-target="#modalroutingmaster" data-mode="2" title="Edit Routing" data-rowid="'+json[i].SE15ROUTINGNO+'"><span class="glyphicon glyphicon-pencil"></span></button> ' + 
							            '<button id="Deleteform" class="btn btn-dull btn-sm" data-reserve="'+json[i].SE15ROUTINGNO+'" onclick="deleteRouting(this)"><span class="glyphicon glyphicon-trash" title="Delete Reserve Collection" ></span></button>'
							        ]).draw(false);
			    	        
			    	            }
			    		 });
		 	         
		 	        }
	    		).fail(function(data){
	    		}).success(function(data){
	    		});
		 		break;
		 	case state = "Edit Routing":
		
		 		///alert("ff" +JSON.stringify(output2));
		 		var reserveno = localStorage.getItem('rowid');
		
			$.post("updateRoutingMaster", { 
		 		grace : $("#graceperiod").val(),
		 		routingno : reserveno
 			}, 
		 	    function(data,status){
		 	    	messageBox("005","Edit Routing","@action");
		 	    	$("#closeModalAdd").click();
		 	    	$('#rsvCollectionMaster_table').dataTable().fnClearTable();
		 	    	
		 	    	$.get("DisplayRoutingMaster?getChecked=routingno",{getCriteria : reserveno},function(json){
	    			 var return_data = new Array();
	    			 localStorage.clear();
	    			 for(var i=0;i< json.length; i++){
	    	            	table.row.add( [
	    	            	            1,
	    	            	            json[i].TITLE,
							            json[i].NAME,
							            json[i].SE15CPYNO,
							            json[i].SE15PRIORITY,	
							            '<button id="Viewform" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalroutingmaster" data-mode="3" title="View Routing" data-rowid="'+json[i].SE15ROUTINGNO+'"><span class="glyphicon glyphicon-eye-open"></span></button> ' + 
							            '<button id="Editform" class="btn btn-info btn-sm Edit" data-toggle="modal" data-target="#modalroutingmaster" data-mode="2" title="Edit Routing" data-rowid="'+json[i].SE15ROUTINGNO+'"><span class="glyphicon glyphicon-pencil"></span></button> ' + 
							            '<button id="Deleteform" class="btn btn-dull btn-sm" data-reserve="'+json[i].SE15ROUTINGNO+'" onclick="deleteRouting(this)"><span class="glyphicon glyphicon-trash" title="Delete Reserve Collection" ></span></button>'
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