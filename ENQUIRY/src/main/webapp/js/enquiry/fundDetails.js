$(document).ready(function() {
	
	$("#view").prop('disabled', true);
	
	//////table setup
	$('#table-FundDetails').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	//////////////Rate key in number only//////////////////////////////////////
	$("#txtEntry").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
    });
	
	////////////change radio button
	$('input[name=radioOption]').change(function(){
		$("#view").prop('disabled', false);
		$("#txtEntry").focus();
	});
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn View All//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#viewAll').click(function(){

		$("#txtEntry").val("");
		$('input[name="radioOption"]').prop('checked', false);
		$("#view").prop('disabled', true);
		
		$('#table-FundDetails').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//info: false,
		    ajax: {
		    	url: "resultFundDetailList",
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){
		            	
		              return_data.push({
		            	'Fund' : json[i].Fund,
		            	'Description': json[i].Descrip,
		                'Allocation': json[i].Allo,
		                '+Increment' : json[i].Incr, 
		                '-Decrement' : json[i].Decr,
		                '-Commitment' : json[i].Comm, 
		                '-Payment' : json[i].Pay,
		                'Balance' : json[i].Balace,
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
		     	    {'data': 'Fund'},
					{'data': 'Description'},
					{'data': 'Allocation', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': '+Increment', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': '-Decrement', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': '-Commitment', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': '-Payment', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': 'Balance', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
				],
		});
	});
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn View All//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#view').click(function(){
		$('#table-FundDetails').dataTable().fnClearTable();
		
		var ssopt = $('input[name=radioOption]:checked').val();
		var txtEntry; 
		txtEntry = $("#txtEntry").val();
		if(txtEntry == ""){
			txtEntry = "0";
		}else{
			txtEntry = txtEntry;
		}
		
		$('#table-FundDetails').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//info: false,
		    ajax: {
		    	url: "resultFundDetailList2",
		    	data : {ssopt : ssopt, txtEntry : txtEntry},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){
		            	
		              return_data.push({
		            	'Fund' : json[i].Fund,
		            	'Description': json[i].Descrip,
		                'Allocation': json[i].Allo,
		                '+Increment' : json[i].Incr, 
		                '-Decrement' : json[i].Decr,
		                '-Commitment' : json[i].Comm, 
		                '-Payment' : json[i].Pay,
		                'Balance' : json[i].Balace,
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
		     	    {'data': 'Fund'},
					{'data': 'Description'},
					{'data': 'Allocation', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': '+Increment', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': '-Decrement', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': '-Commitment', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': '-Payment', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': 'Balance', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
				],
		});
	});
	
});