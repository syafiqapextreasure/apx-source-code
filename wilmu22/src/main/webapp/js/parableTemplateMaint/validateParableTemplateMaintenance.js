
$(document).ready(function() {
	
	
	var globalDataFromServer;
	var styleTable = $('#styleTable').DataTable({
		destroy: true,
		searching: false,
		bInfo : false,
		bPaginate: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		processing: true,	
//		columnDefs : [{
//            "targets": [1],
//            "visible": false,
//            "searchable": false
//        }]
	});
	

	$('#selectTemplate').change(function(){

		$('#parableTemplateMaintenance_table').DataTable().destroy();
		$('#parableTemplateMaintenance_table tbody').empty();
		$('#styleTable').DataTable().destroy();
		$('#styleTable tbody').empty();

		});
	
/*	if (typeof jQuery != 'undefined') {  
	    // jQuery is loaded => print the version
	    alert(jQuery.fn.jquery);
	}*/
	
	var globalSelected='';

    $("#button").click(function()
    	    {
    	        $("#attributeProperties_table").toggle();
    	    });
 
	$.ajax({

		url : 'SelectBranch',
		success : function(data1) {
			if (data1) {
				var count1 = 0;

				$.each(data1, function(key, entry) {
					$('#selectBranch').append(
							$("<option />").val(entry)
									.text(entry));
					count1++;
				})
			}
			$dropdown = $('#selectBranch');
			$dropdown[0].selectedIndex = -1;
		}
	});

	   
	$("#selectBranch").change(function () {
	//    $("#day").prop("disabled", !(["yes", "maybe"].indexOf(this.value) !== -1));
		$("#selectTemplate").prop('disabled', false);
		
		$("#selectTemplate").empty();
		
		$.ajax({

			url : 'SelectTemplate',
			data : {selectedBranch: $('#selectBranch').val()},
			success : function(data1) {
				if (data1) {
					var count1 = 0;
					
					$.each(data1, function (index, record) {
					    
					    $('<option>', {
					        value: record.value,
					        text: record.templateName
					    }).appendTo("#selectTemplate");
					});
					$dropdown = $('#selectTemplate');
					$dropdown[0].selectedIndex = -1;
				
				}
			}
		});
	    
	});
	
	
	$('#loadAttributeBtn').click(function(){
		
		$('#styleCommon_table').hide();
		$('#editSheet_table').hide();
		$('#editLabel_table').hide();
		
		$('#parableTemplateMaintenance_table').show();
		$('#styleTable').hide();
			
			var table = $('#parableTemplateMaintenance_table').DataTable({
				destroy: true,
				searching: false,
				bPaginate: false,
				bLengthChange: false,
				autoWidth: false,
				responsive: true,
				processing: true,
			    ajax: {
			    	url: "LoadAttribute",
			        data : {selectedTemplate: $('#selectTemplate').val(), selectedBranch: $('#selectBranch').val()},
			        type: "GET",
			        dataSrc: function (json) {
			        	
			        	$.fn.dataTable.ext.errMode = 'none';
			   	
			            var return_data = new Array();

			            for( var i=0;i< json.length; i++) {
			            	var stat = json[i].status;
			            	if(i<=1){
								return_data.push({
									'SEQ No': json[i].seqNo,   
									'Attributes' : json[i].attributes,
									'Action' :'<button id="styleBtn" style="background-color: #4CAF50" edit_attribute="'+json[i].attributes+'" >Edit</button></button>&nbsp',
								})
			            	}else if(i>1){
								return_data.push({
									'SEQ No': json[i].seqNo,   
									'Attributes' : json[i].attributes,
									'Action' : '<button id="styleBtn" style="background-color: #008CBA;" edit_attribute="'+json[i].attributes+'">Style</button></button>',
								})
			            	}

			            }
			            return return_data;
			          },
			     },
		     	columns    : [
					{'data': 'SEQ No'},
					{'data': 'Attributes'},
					{'data': 'Action'},
				],
		        'dom': 'Rlfrtip',
		        'colReorder': {
		            'allowReorder': false
		        },
			});

    });

	$('#parableTemplateMaintenance_table').on('click', '#styleBtn', function () {
		
		var selectedAttribute = $(this).attr('edit_attribute');
		
		if(selectedAttribute=='SHEET' || selectedAttribute=='LABEL'){
			
			$('#formCommon').trigger("reset");
			$('#formAttribute').trigger("reset");
			$('#formBarcode').trigger("reset");
			$('#formTitle').trigger("reset");
			$('#formEditSheet').trigger("reset");
			$('#formEditLabel').trigger("reset");
			$("#styleCommon_table").hide();
			$("#attributeProperties_table").hide();
			$("#barcode_table").hide();
			$("#title_table").hide();
			$("#editSheet_table").hide();
			$("#editLabel_table").hide();
			
			$("#styleTable").show();
			
		globalSelected= selectedAttribute;
			
			$.ajax({
					url: "GetAttributeComponents",
				    data : {selectedTemplate: $('#selectTemplate').val(), selectedBranch: $('#selectBranch').val(), selectedAttribute: selectedAttribute},					
			        type: "GET",
			        async: false,
			        success: function (data) {
			  
			        	globalDataFromServer = data;
			        	 
			          },

			     });
			
			var styleTable = $('#styleTable').DataTable({
				destroy: true,
				searching: false,
				bPaginate: false,
				bLengthChange: false,
				autoWidth: false,
				processing: true,
				data: globalDataFromServer,
				  columns: [{
				      data: 'name'
				    },
				    {
				      data: 'value',
				      render: function(data, type, row, meta) {
				        return '<input type="Text" value="' + data + '" name="Text123">';
				      }
				    },				    
				  ]
				});

	
		}else{
			
			$('#formCommon').trigger("reset");
			$('#formAttribute').trigger("reset");
			$('#formBarcode').trigger("reset");
			$('#formTitle').trigger("reset");
			$('#formEditSheet').trigger("reset");
			$('#formEditLabel').trigger("reset");
			$("#attributeProperties_table").hide();
			$("#barcode_table").hide();
			$("#title_table").hide();
			$("#styleSheetLable_table").hide();
			$("#editSheet_table").hide();
			$("#editLabel_table").hide();
			
//			$("#styleCommon_table").toggle();
			$("#styleTable").show();
			
			
			globalSelected= selectedAttribute;
			
			$.ajax({
					url: "GetStyleComponents",
				    data : {selectedTemplate: $('#selectTemplate').val(), selectedBranch: $('#selectBranch').val(), selectedAttribute: selectedAttribute},					
			        type: "GET",
			        async: false,
			        success: function (data) {
			      
			        	globalDataFromServer = data;
			        	 
			          },

			     });

			
			var styleTable = $('#styleTable').DataTable({
				destroy: true,
				searching: false,
				bPaginate: false,
				bLengthChange: false,
				autoWidth: false,
				processing: true,
				data: globalDataFromServer,
				  columns: [{
				      data: 'name',
				      render: function(data, type, row, meta) {
					        return data+'<input type="hidden" name="styleName" value="' + data + '">'; }
				    },
				    {
				      data: 'value',
				      render: function(data, type, row, meta) {
				        return '<input type="Text" value="' + data + '" name="Text">';
				      }
				    }
				  ]
				});

		};
		
		
		$('#previewBtn').unbind('click');
		$('#previewBtn').on('change');
		$('#previewBtn').click(function(){

			var sendSelectedAttribute = globalSelected;

				let alldata = [];

			styleTable.rows().every(function(index, element) {
			    alldata.push(this.data());
			});
						  
				$.ajax({
					url: "previewParableTempt",	
					data : {selectedTemplate: $('#selectTemplate').val(), selectedBranch: $('#selectBranch').val(), styleSelectedPrint: $('#styleSelectedPrint').val(), sendSelectedAttribute: sendSelectedAttribute, arrJSONStyle: JSON.stringify(alldata)},
					success : function(data) {
						
/*						var w = window.open('/module/parableTemplateMaint/book/parableMaint_printBookLabel','about:blank');*/
					    var w = window.open('about:blank');
					    w.document.open();
					    w.document.write(data);
					    w.document.close();
						
				/*		window.open("","printForm","width=500,height=300,toolbar=0");*/
						}
				});	

			});
		
		$('#styleTable').off('change');
		$('#styleTable').unbind('click');
		$('#styleTable').on('change');
		
		$('#styleTable').on('change', 'input', function() {
			  let cell = $(this).closest('td');
			  styleTable.cell($(cell)).data($(this).val());
			});
		

		$('#saveBtn').unbind('click');
		$('#saveBtn').on('change');		
		$('#saveBtn').click(function(){
				swal("Template Save!");

				var arrStyle=[];
				var sendSelectedAttribute = globalSelected;

				let alldata = [];

				styleTable.rows().every(function(index, element) {
				    alldata.push(this.data());
				});
				  
				$.ajax({
					url: "SaveAttribute",
					dataType: 'JSON',
					data: {selectedTemplate: $('#selectTemplate').val(), selectedBranch: $('#selectBranch').val(), sendSelectedAttribute: globalSelected,  arrJSONStyle: JSON.stringify(alldata)},
					success : function(data) {
					if (data) {
		          		
					} else if (!data){
						}
					}
				});

			});
    });



});

