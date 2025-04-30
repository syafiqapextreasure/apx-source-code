$(document).ready(function() {

	$("#datepicker").hide();
	$("#search_type, #search_order").prop("selectedIndex",-1);
	
	$('input[name=radioOption][value=order]').attr('checked', true); 
	$('#search_order').val("vendor");
	
	////////disable search if radio button not checked ///////////////////////////////////////
	$("#btn-submit-retrievemodal").click(function(){
		var getChecked = $('input[name=radioOption]:checked').val();
		var getCriteria = $("input[name=criteria]").val();
		var getSearch = $("#search_type option:selected").val();
		var getAcq = $("#search_order option:selected").val();
		var inputStartDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");//$("#input-startDate").val();
		var inputEndDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD"); //$("#input-endDate").val();

		if($("input[name=criteria]").is(":hidden") == false && getCriteria == ""){
				swal("Search value is blank");
		}else if(!getChecked){
	         swal("Please Choose Radio Button")
	    }else if(getChecked == 'selection'){
	    	if(getSearch == undefined){
	    		//messageBox("108","SMD","@field");
	    		swal("Please select first"); 
	    	}else{
	    		searchVal(getChecked, getCriteria, getSearch, getAcq, inputStartDate, inputEndDate);
	    	}
	    }else if(getChecked == 'order'){
	     	if(getAcq == undefined){
		    	//messageBox("108","SMD","@field");
	     		alert("Please select first"); 
	    	}else{
	    		searchVal(getChecked, getCriteria, getSearch, getAcq, inputStartDate, inputEndDate);
	    	}
	    }else{
	    	searchVal(getChecked, getCriteria, getSearch, getAcq, inputStartDate, inputEndDate);
	    }
	});
	
	/////////////////////////// on radio button change //////////////////////////////////////////
	$('input[name=radioOption]').change(function(){
	    var value = $( 'input[name=radioOption]:checked' ).val();
	    //alert(value);

	    $("input[name=criteria]").val('');
	    $("#search_type, #search_order").prop('checked', false); 
	    $("#search_type, #search_order").prop("selectedIndex",-1);
	    $("#datepicker").hide();
		$("input[name=criteria]").show(); 
	    
	    if(value == "controlNo"){
	    	$("input[name=criteria]").attr('maxlength','10');
	    	$("input[name=criteria]").focus();
	    }else if(value == "accno"){
	    	$("input[name=criteria]").attr('maxlength','10');
	    	$("input[name=criteria]").focus();
	    }else if(value == "selection"){
	    	$('#search_type').val("title");
	    }else if(value == "order"){
	    	$('#search_order').val("vendor");
	    }
	    else{
	    	$("input[name=criteria]").attr('maxlength','70');
	    }
	});
	
	/////////////////////////// on change fo Acquisitions ///////////////////////////////////
	$("#search_order").change(function(){
		 var choosenAcq = $("#search_order option:selected").val();
		
		 $(':radio').removeAttr('checked') 
		 $('input[name=radioOption][value=order]').prop('checked', true); 
			
		// $("#search_type").prop("selectedIndex",-1);
		 alert(choosenAcq);
		 //alert(choosenAcq);
		 if(choosenAcq == "orderdate"){
			 $("#datepicker").show();
			 $("input[name=criteria]").hide();
		 }else if (choosenAcq == "reviewlistno"){
			 $("input[name=criteria]").attr('maxlength','10');
		 }else if (choosenAcq == "orderno"){
			 $("input[name=criteria]").attr('maxlength','10');
			 $("#datepicker").hide();
			 $("input[name=criteria]").show(); 
		 }else{
			 $("#datepicker").hide();
			 $("input[name=criteria]").show(); 
		 }	 
	});
	
	/////////////////////////// on change fo Acquisitions ///////////////////////////////////
	$("#search_type").change(function(){
		var choosenCat = $("#search_type option:selected").val();
		
		$("#datepicker").hide();
		$("input[name=criteria]").show(); 
		$(':radio').removeAttr('checked') 
		$('input[name=radioOption][value=selection]').prop('checked', true); 
		
		$("#search_order").prop("selectedIndex",-1);
		
		
	});
	
	////////////////////////////////////////////
	
	
	///////////////////// input-startDate set Current Date  ///////////////////////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///////////////////// input-endDate set Current Date  ///////////////////////////////////
	
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///////////////////////////function pass value //////////////////////////////////////////
	function searchVal(getChecked, getCriteria, getSearch, getAcq, inputStartDate, inputEndDate){
		var typeSearch = "orderMaint";
		
		if(inputStartDate == 'Invalid date'){
			inputStartDate = "";
		}
		
		/*alert("inputStartDate" +inputStartDate);
		alert("inputEndDate" +inputEndDate);*/
		
		loader("#OdrMaint2");
		$("#closeSearch").click();
		$.get('Acquisition?type=Maintenance&module=AAAM0250&name=Handler_OrderMaint', {getChecked:getChecked, getCriteria:getCriteria, 
			getSearch:getSearch,typeSearch:typeSearch, getAcq:getAcq,
			inputStartDate:inputStartDate, inputEndDate:inputEndDate}, function(responseText) {
			//alert("test 3");
			$("#display_OdrMaint").html(responseText);
			
			var table = $('#OdrMaint2').DataTable({
				//responsive: true,
				scrollx: true,
				info: false,
				paging: true,
				ordering: false,
				destroy: true,
				searching:false,
				info: true,
				autoWidth: false,/*
				deferRender: true,
				bSortClasses: false,*/
				lengthChange: false,
				/*columnDefs: [
					{
				    	"targets": [ 4 ],
				       	"visible": false,
				        "searchable": false,
				    }
				],*/
				drawCallback: function( settings ) {
					
					$('#OdrMaint2 tbody tr').each(function() {
				          var Cell = $(this).find('td:eq(4)');
				          //alert(Cell.text());

						/* var bbb = $(this).find('td:eq(8)');
							
							alert($(this).find('td:eq(8)'));
					
							alert(bbb.attr('data-status'));*/
				          debugger;


				          //if (Cell.text() == 'READY TO ORDER') {
				          if((Cell.text().trim() == 'APPROVED FOR ORDER') || (Cell.text().trim() == 'READY TO ORDER') || (Cell.text().trim() == 'PROFORMA INVOICE RECEIVED')){
				        	  $(this).find('#Edit').prop("disabled", false);
				        	  $(this).find('#Delete').prop("disabled", false);
				          }else{
				        	  $(this).find('#Edit').prop("disabled", true);
							  //$(this).find('tr').find('.Edit').prop("disabled", true);
				        	  $(this).find('#Delete').prop("disabled", true);
				          }
				        });
			    }
			});
			
			
			 /*$('#OdrMaint3 tbody').on('click', 'tr', function () {
			        var data = table.row( this ).data();
			        alert( 'You clicked on '+data[5]+'\'s row' );
			    } );*/
			
		});
		
		/*setTimeout(function(){
			$("#closeSearch").click();
		}, 2000);*/
	}	
	
	//clear all input fields in bootstrap modal when close modal 
	$('.modal').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $("#search_type, #search_order").prop("selectedIndex",-1);
	    $('input[name=radioOption][value=order]').attr('checked', true); 
		$('#search_order').val("vendor");
		//$(this).removeData('bs.modal');
	});
	 

});

