/**
 * 
 */

$(document).ready(function() {
	
	/////setting when open
	$("#datepicker").hide();
	//$("#searchStatus, #searchdept, #searchbranch").prop("selectedIndex",-1);
	
	$('input[name=radioOption][value=reqNo]').attr('checked', true); 
	$("#criteria").attr('maxlength','10');
	
	
	/////////////////////////// on radio button change //////////////////////////////////////////
	$('input[name=radioOption]').change(function(){
		var value = $( 'input[name=radioOption]:checked' ).val();
	    //alert(value);
		$("#criteria").val('');
		
	    if(value == "reqID"){
	    	$("#datepicker").hide();
			$("#criteria").show();
	    	$("#criteria").attr('maxlength','12');
	    }else if(value == "datereq"){
	    	$("#datepicker").show();
			$("#criteria").hide();
	    }else if(value == "reqNo"){
	    	$("#criteria").attr('maxlength','10');
	    }else{
	    	$("#datepicker").hide();
			$("#criteria").show();
			$("#criteria").attr('maxlength','255');
	    }
	});
	
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
	
	////////clear all input fields in bootstrap modal when close modal //////////////////////
	$('.modal').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $("#datepicker").hide();
		$("#criteria").show();
		$("#searchStatus, #searchdept, #searchbranch").prop("selectedIndex",-1);
		$('input[name=radioOption][value=reqNo]').attr('checked', true); 
		$("#criteria").attr('maxlength','10');
		$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
		$('#input-startDate').val("");
	});
	
	///////////////////////WHEN CLICK SEARCH //////////////////////////////////////////////
	$("#btn-submit-retrievemodal").click(function(){
		var getChecked = $('input[name=radioOption]:checked').val();
		var getCriteria = $("#criteria").val();
		var inputStartDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var inputEndDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD"); 
		var status = $("#searchStatus").val();
		var stausYN;
		var searchstatus;
		var dept = $("#searchdept").val();
		var deptYN;
		var searchdept;
		var branch = $("#searchbranch").val();
		var branchYN;
		var searchbranch;

		
		var checboxlist = [];
        $.each($("input[name='checkboxOption']:checked"), function(){            
        	checboxlist.push($(this).val());
        });
        var listcheckbox = checboxlist.join(", ");
        //alert(checboxlist.length);
     
  	    	if($("#criteria").is(":hidden") == false && getCriteria == ""){
  	    		swal("Search value is blank")
  	    	}else{
  	    		//searchVal(getChecked, getCriteria, JSON.stringify(output), inputStartDate, inputEndDate);
  	    		searchVal(getChecked, getCriteria, inputStartDate, inputEndDate,
  	    				stausYN, searchstatus, deptYN, searchdept, branchYN, searchbranch);
  	    	}
	});
	
	///////////////////////////function pass value //////////////////////////////////////////
	function searchVal(getChecked, getCriteria, inputStartDate, inputEndDate,
			stausYN, searchstatus, deptYN, searchdept, branchYN, searchbranch){
		loader("#reqCtrl");  
		$("#closeSearch").click();
		$.get('Global?type=Modal&name=Handler_SearchReqCtrl', {getChecked:getChecked, getCriteria:getCriteria, 
			inputStartDate:inputStartDate, inputEndDate:inputEndDate,
			stausYN:stausYN, searchstatus:searchstatus, deptYN:deptYN, searchdept:searchdept, 
			branchYN:branchYN, searchbranch:searchbranch}, function(responseText) {

			$("#display_reqCntrl").html(responseText);
			
			var table = $('#reqCtrl').DataTable({
				responsive: true,
				scrollx: true,
				info: false,
				paging: true,
				ordering: false,
				destroy: true,
				searching:false,
				info: true,
				lengthChange: false,
				drawCallback: function( settings ) {
					$('#reqCtrl tbody tr').each(function() {
				          var Cell = $(this).find('td:eq(5)');
				          //alert(Cell.text());
				          debugger;
				          if((Cell.text().toUpperCase().trim() == 'User Request'.toUpperCase()) || (Cell.text().trim().toUpperCase() == 'Under Review'.toUpperCase()) || (Cell.text().toUpperCase().trim() == 'Awaiting Review'.toUpperCase()) || (Cell.text().toUpperCase().trim() == 'REQUESTED'.toUpperCase())){
				        	  $(this).find('#Edit').prop("disabled", false);
				        	  $(this).find('#Delete').prop("disabled", false);
				          }else{
				        	  $(this).find('#Edit').prop("disabled", true);
				        	  $(this).find('#Delete').prop("disabled", true);
				          }
				        });
			    }
			});
		});
	}
	
	/////////////////////ON CHANGE //////////////////
	$("#searchStatus").change(function(){
		$("input[name='checkboxOption'][value='status']").prop('checked', true);
	});
	
	$("#searchdept").change(function(){
		$("input[name='checkboxOption'][value='dept']").prop('checked', true);
	});
	
	$("#searchbranch").change(function(){
		$("input[name='checkboxOption'][value='branch']").prop('checked', true);
	});
	
});