$(document).ready(function() {
	
	var today = new Date();
	
	var allSelected = false;
	$('#branch').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true,
		numberDisplayed: 1,
		onSelectAll: function(){
		    allSelected = true;
		  },
		  onChange: function(){
		    allSelected = false;
		  }
	}); 
	
	
	//////Class No Range
	$("#chkBoxRangeClassno").on('change', function () {
		var self = $(this);
	    if (self.is(":checked")) {
			$("#startInputClassno, #endInputClassno").prop( "disabled", false);
			$('#Reterive').prop('disabled', true);
			
			
	    } else {
			$("#startInputClassno, #endInputClassno").prop( "disabled", true);
			$('#Reterive').prop('disabled', false);
			$("#startInputClassno, #endInputClassno").val("");
			$("#startInputClassno, #endInputClassno").css("border", "");
	    }
	});
	
	
	/////validate control number
	$("#startInputClassno, #endInputClassno").focusout(function(e){
		
		e.preventDefault();
		e.stopImmediatePropagation();

	   // if(parseFloat($("#startInputClassno").val()) > parseFloat($("#endInputClassno").val())){
		if($("#startInputClassno").val() > $("#endInputClassno").val()){
			$("#startInputClassno, #endInputClassno").css("border", "solid red");
	        $("#Reterive").prop('disabled',true);
	
			messageBox("172","","");
			$("#chkBoxRangeClassno").focus();
	    } else {
	        $(".error").css("display","none");
			$("#startInputClassno, #endInputClassno").css("border", "");
	        $("#Reterive").prop('disabled',false);        
	    }
	    
	});
	
	////////BRANCH
	$("#chkBoxBranch").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#branch').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#branch').change(function() {
				if($("#branch").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#Reterive').prop('disabled', true);
				}
			});	
	    } else {
			$('#branch').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#branch").multiselect("clearSelection")
 			$("#branch").multiselect( 'refresh' ); 
	    }
	});
	
	////////Location
	$("#chkBoxLoca").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#loca').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#loca').change(function() {
				if($("#loca").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#Reterive').prop('disabled', true);
				}
			});	
	    } else {
			$('#loca').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#loca").multiselect("clearSelection")
 			$("#loca").multiselect( 'refresh' ); 
	    }
	});
	
	////////Catalog Type
	$("#chkBoxCattype").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#cattype').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#cattype').change(function() {
				if($("#cattype").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#Reterive').prop('disabled', true);
				}
			});	
	    } else {
			$('#cattype').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#cattype").multiselect("clearSelection")
 			$("#cattype").multiselect( 'refresh' ); 
	    }
	});
	
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		
		$('#catreportTable').dataTable().fnClearTable();
		
		////view
		var view = $('input[name="dataView"]:checked').val();
		
		alert("view"+view);
		
	});
	
	
	
	
});