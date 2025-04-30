$(document).ready(function() {

	$("#datepicker").hide();
	$("#search_type, #search_order").prop("selectedIndex",-1);
	
	$('input[name=radioOption][value=order]').attr('checked', true); 
	$('#search_order').val("vendor");
	
	////////disable search if radio button not checked ///////////////////////////////////////
	$("#btn-submit-rec").click(function(){
		var getChecked = $('input[name=radioOption]:checked').val();
		var getCriteria = $("#criteria").val();
		var getSearch = $("#search_type").val();
		var getAcq = $("#search_order").val();
		var inputStartDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");//$("#input-startDate").val();
		var inputEndDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD"); //$("#input-endDate").val();
		
		//alert($("#criteria").is(":hidden"));
		
		if($("#criteria").is(":hidden") == false && getCriteria == ""){
				swal("Search value is blank");
		}else if(!getChecked){
	         swal("Please Choose Radio Button")
	    }else if(getChecked == 'selection'){
	    	if(getSearch == null){
	    		//messageBox("108","SMD","@field");
	    		swal("Please select first"); 
	    	}else{
	    		$("#search_title").collapse('hide');
				$("#result_title").collapse('show');
	    		searchVal(getChecked, getCriteria, getSearch, getAcq, inputStartDate, inputEndDate);
	    	}
	    }else if(getChecked == 'order'){
	     	if(getAcq == null){
		    	//messageBox("108","SMD","@field");
	     		////////alert("Please select first"); 
	    	}else{
	    		$("#search_title").collapse('hide');
				$("#result_title").collapse('show');
	    		searchVal(getChecked, getCriteria, getSearch, getAcq, inputStartDate, inputEndDate);
	    	}
	    }else{
	    	$("#search_title").collapse('hide');
			$("#result_title").collapse('show');
	    	searchVal(getChecked, getCriteria, getSearch, getAcq, inputStartDate, inputEndDate);
	    }
	});
	
	/////////////////////////// on radio button change //////////////////////////////////////////
	$('input[name=radioOption]').change(function(){
	    var value = $( 'input[name=radioOption]:checked' ).val();
	    //alert(value);

	    $("#criteria").val('');
	    $("#search_type, #search_order").prop('checked', false); 
	    $("#search_type, #search_order").prop("selectedIndex",-1);
	    $("#datepicker").hide();
		$("#criteria").show(); 
	    
	    if(value == "controlNo"){
	    	$("#criteria").attr('maxlength','10');
	    	$("#criteria").focus();
	    }else if(value == "accno"){
	    	$("#criteria").attr('maxlength','10');
	    	$("#criteria").focus();
	    }else if(value == "selection"){
			$("#criteria").attr('maxlength','70');
	    	$('#search_type').val("title2");
	    }else if(value == "order"){
	    	$('#search_order').val("vendor");
	    }
	    else{
	    	$("#criteria").attr('maxlength','70');
	    }
	});
	
	/////////////////////////// on change fo Acquisitions ///////////////////////////////////
	$("#search_order").change(function(){
		 var choosenAcq = $("#search_order option:selected").val();
		
		 $(':radio').removeAttr('checked') 
		 $('input[name=radioOption][value=order]').prop('checked', true); 
			
		 $("#search_type").prop("selectedIndex",-1);
		 //alert(choosenAcq);
		 if(choosenAcq == "orderdate"){
			 $("#datepicker").show();
			 $("#criteria").hide();
		 }else if (choosenAcq == "reviewlistno"){
			 $("#criteria").attr('maxlength','10');
		 }else if (choosenAcq == "orderno"){
			 $("#datepicker").hide();
			 $("#criteria").show(); 
			 $("#criteria").attr('maxlength','10');
		 }else{
			 $("#datepicker").hide();
			 $("#criteria").show(); 
		 }	 
	});
	
	/////////////////////////// on change fo Acquisitions ///////////////////////////////////
	$("#search_type").change(function(){
		var choosenCat = $("#search_type option:selected").val();
		
		$("#datepicker").hide();
		$("#criteria").show(); 
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
		var typeSearch = "searchREC";
		
		$('#searchReceiving').DataTable( {
			destroy: true,
			//searching: false,
			bLengthChange: false,
			//paging: false,
			info: false,
			//oLanguage: {"sZeroRecords": "", "sEmptyTable": ""},
		    ajax: {
		        /*url: "Getsearchrec?rowid="+rowid,*/
		    	url: "GetSearchReceiving",
			    data:{
			    	getChecked : getChecked,
			    	getCriteria : getCriteria,
			    	getSearch : getSearch,
			    	getAcq : getAcq,
			    	inputStartDate : inputStartDate,
			    	inputEndDate : inputEndDate,
			    	typeSearch : typeSearch
			    },
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		           
		            for(var i=0;i< json.length; i++){
		              return_data.push({
		                'Order No': json[i].order,
		                'Control No' : json[i].ctrlno, 
		                'Title' : json[i].title,
		                'Vendor' : json[i].venddesc,
		                'Mode' : json[i].mode,  
		                'Status': json[i].statusdesc,
		                'Reference' : json[i].refno,
		                'Order Date' : json[i].orderdate,
		                'Action' : '<button class="SelectOrderno btn btn-sm btn-default" data-original-title="Select" value="'+json[i].order+'" data-dismiss="modal"><i class="fa fa-check"></i> Select',
		                //data-dismiss="modal"  <input type="button" class="SelectOrderno btn btn-md btn-danger" value="Delete">
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'Order No'},
					{'data': 'Control No'},
					{'data': 'Title'},
					{'data': 'Vendor'},
					{'data': 'Mode'},
					{'data': 'Status'},
					{'data': 'Reference'},
					{'data': 'Order Date'},
					{'data': 'Action'}
				],
		});
		
		$('#searchReceiving tbody').on('click', '.SelectOrderno', function () {
			$("#OrderNO").val($(this).prop("value"));
			$("#OrderNO").focus();
		});
	}	

	//clear all input fields in bootstrap modal when close modal 
	$('.modal').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $("#search_type, #search_order").prop("selectedIndex",-1);
	    $('input[name=radioOption][value=order]').attr('checked', true); 
		$('#search_order').val("vendor");
		$("#search_title").collapse('show');
		$("#result_title").collapse('hide');
		$('#searchReceiving').dataTable().fnClearTable();
		//$(this).removeData('bs.modal');
	});
	
	////////	
	
	 

});

