$(document).ready(function(){
	
	var today = new Date(); 
	
	
	var patronid = $("#liferayLogin").val();
	
	
	$.get('GetFndPatron', {
        	userID : patronid,
	 	}, function(responseJson) {
		if(responseJson != null){
			
			if(responseJson==''){

			}
			
			$.each(responseJson, function(key,value) {
				
				$(".lblpatronID").text($("#liferayLogin").val());
				$(".lblname").text(value['Name']);
				$(".lblcate").text(value['Cate']);
						
			});
		}
	});
	
	
	var t = $('#renewalTable').DataTable( {
			//dom: 'Bfrtip',
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
		    	url: "GetRenewalSql",
		        data : {patronid :  patronid},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
			
						var duedate = json[i].DueDate;
						var chkboxVal = "";
						
						var remarks = "";
						
				
				
						if(moment(moment(today, 'DD-MM-YYYY')).format('YYYY-MM-DD') > moment(moment(duedate, 'DD-MM-YYYY')).format('YYYY-MM-DD')){
							//chkboxVal = '<input type="checkbox" id="example-select-all" name="id[]" value=" '+json[i].AccNo+' " disabled="disabled" >';
							chkboxVal = '';
							remarks = "Overdue";
						}else{
							chkboxVal = '<input type="checkbox" id="example-select-all" name="id[]" value=" '+json[i].AccNo+'">';
							remarks = "";
						}
						
	
			
					

		              return_data.push({
		                'No': (i+1),
						'Accession No' : json[i].AccNo,
						'Title' : json[i].Title,
						'Borrow Date' : json[i].BorrowDate,
		                'Due Date' : duedate,
		                'New Date' : '',
						'Remarks' : remarks,
		                'Action' : chkboxVal,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Accession No'},
					{'data': 'Title'},
					{'data': 'Borrow Date'},
					{'data': 'Due Date'},
					{'data': 'New Date'},
					{'data': 'Remarks'},
					{'data': 'Action'},
				],
				rowCallback: function( row, data, index ) {
			
				  if ( data.Remarks == "Overdue" ) {
				    	/// $('td', row).css('color', 'Red')
						$('td:eq(6)', row).css('color', 'Red')
				  } 
				}
    	});


	$("#renewbtn").attr('disabled', 'disabled');

	///////////////////////////////////////////WHEN CLICK CHECK BOX////////////
	/// 1) FOR SELECT ALL
	$('#example-select-all').on('click', function(){
		// Check/uncheck all checkboxes in the table
		var rows = $('#renewalTable').DataTable().rows({ 'search': 'applied' }).nodes();
		if(!this.checked){
	    	$('#example-select-all', rows).prop('checked', false);
	    	$("#renewbtn").attr('disabled',true);
	   	}else{
	    	$('#example-select-all', rows).prop('checked', this.checked);
	    	$("#renewbtn").attr('disabled',false);
	    }
	});
	
	
	// Handle click on checkbox to set state of "Select all" control
    $('#renewalTable tbody').on('change', '#example-select-all', function(){
    	
       // If checkbox is not checked
       if(!this.checked){
    	  //$(".saveRetrieve").attr('disabled',true);
          var el = $('#example-select-all').get(0);
          var totaltick = $('#renewalTable :input[type="checkbox"]:checked').length
          if(totaltick == 0){
        	  $("#renewbtn").attr('disabled',true);  
          }else{
        	  $("#renewbtn").attr('disabled',false);  
          }
          
          // If "Select all" control is checked and has 'indeterminate' property
          if(el && el.checked && ('indeterminate' in el)){
             // Set visual state of "Select all" control 
             // as 'indeterminate'
             el.indeterminate = true;
          }
       }else{
    	   $("#renewbtn").attr('disabled',false);
       }
    });
    ///////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#renewbtn').click(function(){
		
		$("#renewbtn").attr('disabled', 'disabled');
		
		var output2 = [];
			$('#renewalTable').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
	     		var indexval  = $('#renewalTable').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
				var accnoobj = $(this).closest('tr').find('td:eq(1)').text();
				var deudateobj = $(this).closest('tr').find('td:eq(4)').text();
	
	     		var obj = {};
	     		obj.accnoobj = accnoobj;
	     		obj.indexval = indexval;
				obj.deudateobj = deudateobj;
	     		output2.push(obj);
		});
			
			
		var accnoArray = [];
		    for (var i = 0; i < output2.length; i++) {
		 		var code = output2[i]['accnoobj'];
		 		accnoArray.push(code);
		}
		
		
		var deudateArray = [];
		    for (var i = 0; i < output2.length; i++) {
		 		var code = output2[i]['deudateobj'];
		 		deudateArray.push(code);
		}
		
		
		var indexArray = [];
		    for (var i = 0; i < output2.length; i++) {
		 		var code = output2[i]['indexval'];
		 		indexArray.push(code);
		}
		
		//alert(indexArray.toString(+"dgdgfdgfdgfd"));
		
		indexArray.toString().split(',');
		
		///alert(indexArray[1]+"fddfd");
		

		
		$.post("Handler_Renewal",{
				accno : accnoArray.toString(), 
				patronid : patronid,
				duedate : deudateArray.toString(),
				totalrecord : accnoArray.length},
			function(data){
							
				$("#renewbtn").attr('disabled', false);
				//alert(data+"data");
				//data.replace('[','');
				//data.replace(']','');
				
				var output = data;
				output = output.replace('[','');
				output = output.replace(']','');
				///alert(output+"dsfs");
				
				var arr1 = output.split('*,');
				
				//alert(arr1.length+ "dsfs");
				
				
				for (let i = 0; i < arr1.length; i++){
					
					arr1[i] = arr1[i].replace('*','');
					//alert(arr1[i]+"qwerty");
					
					for(let y = 0; y < arr1.length; y++){
						
						var arr2 = arr1[i].split(', ');
						////alert("ff"+arr2[0].trim() +"ff");
						
						
						t.cell({row:indexArray[i], column:5}).data(arr2[1]);
						t.cell({row:indexArray[i], column:7}).data("");
						
					
						
						if(arr2[0].trim() == "023"){
							//alert("023");
							$.get("Global?type=Handler&name=Error_Page",{
								GL79LANGCODE : "001",
								GL79ERRCODE : "023",
								criteria : arr2[2],
							 	label : "@patron"},function(result){
							 		//alert(result);
									//swal('',result, 'info' );
									///swal(result);
									t.cell({row:indexArray[i], column:6}).data(result);
							});
						}else if(arr2[0].trim()  == "005"){
							$.get("Global?type=Handler&name=Error_Page",{
								GL79LANGCODE : "001",
								GL79ERRCODE : "005",
								criteria : "Renew",
							 	label : "@action"},function(result){
							 		//alert(result);
									//swal('',result, 'info' );
									///swal(result);
									t.cell({row:indexArray[i], column:6}).data(result);
							});
						}else{
							$.get("Global?type=Handler&name=Error_Page",{
								GL79LANGCODE : "001",
								GL79ERRCODE : arr2[0].trim() ,
								criteria : "",
							 	label : ""},function(result){
							 		//alert(result);
									//swal('',result, 'info' );
									///swal(result);
									t.cell({row:indexArray[i], column:6}).data(result);
							});
						}
						
						
						
						
						
					}
					
		

					
				}
				
														
			}
			).fail(function(data){
				swal(data+"error");
																	
			}).success(function(data){   
				swal(data+"success");
		});
		
	});
	
	
	
	
	
});