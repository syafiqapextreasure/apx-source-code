/**
 * 
 */

function getLocaDesc(){
	document.getElementById("GL05DESC").value = document.getElementById("GL05LOCA").value;
}

function getLocaCode(){
	document.getElementById("GL05LOCA").value = document.getElementById("GL05DESC").value;
}

function getValue(){
	$(".message").html($('.update:checked').length + " enteries selected ");
}

/*function getSMD(){
	var loca = $("#GL05LOCA").val();
	$.get("LOAD_SMDS",{GL05LOCA:loca},function(smd){
		alert(smd);
		 $(".smd").html(smd);
		});
}
*/

//Update template name, status  
	function update(){
		var GL10ICAT = $("#GL10ICAT option:selected").val();
		var GL05LOCA = $("#GL05LOCA option:selected").val();
		var SL02BOX1 = $("#SL02BOX1 option:selected").val();
		var SL02BOX2 = $("#SL02BOX2 option:selected").val();
	
		var CT03ICAT = $("#CT03ICAT").val();
		var checkedValue = [];
		$("input[name='selectedValue[]']").each( function () {
		    if($(this).prop('checked') == true){
		    	checkedValue.push(this.value);
		    }
		});


		$.get("Handler_UpdateBoxNo",{CT03DOCNO:checkedValue.toString(), CT03ICAT:CT03ICAT},function(result){
			//$(".newPanel").load("LoadEmptyDiv");
			swal("Successful","The record is updated", "success" );
			
			$('#updateBox').modal('hide');
			
			$.get("Handler_TableResult",{GL10ICAT:GL10ICAT, locationCode:GL05LOCA, SL02BOX1:SL02BOX1, SL02BOX2:SL02BOX2},function(result){
				$(".qrcodelist").html("");
				$(".qrcodelist").html(result);
			});
		});

	}
	
$(document).ready(function () {
	  $('#btn_update').prop("disabled", true);
	$("input[name='selectedValue[]']").change( function () {
		 if ($("input[name='selectedValue[]']:checked").length > 0){
			 $('#btn_update').prop("disabled", false);
			  }
			  else{
				  $('#btn_update').prop("disabled", true);
			  }
	});
	
/*	$("#GL05LOCA").change(function(){
		var GL05LOCA = $(this).val();
		alert("s");
	$.get("Load_Details",{GL05LOCA:GL05LOCA},function(smd){
		alert(smd);
		 $("#queryDetail").html(smd);
		});
	})*/
	
	//Append to add more tha one cutter and cutter group
	$("#SL02BOX1").unbind().change(function(){
		var box1 = $(this).val();
		 $.get("Load_UpCutters1",{box1:box1
			},function(cutters){
				$(".box2").html(cutters);
			});
	});
	
	$("#updateBox_search").click(function(){
		alert("1");
		var GL10ICAT = $("#GL10ICAT").val();
		alert("2");
		var location = document.getElementById("GL05LOCA");
		alert("3");
		var GLSMD = $("#CT03SMD").val();
		alert("4");
		var GLSUBJ = $("#GLSUBJ").val();
		alert("5");
		var GLSHLFNO = $("#GLSHLFNO").val();
		alert("6");
		var locationCode = location.options[location.selectedIndex].value;
		alert("7");
		if(locationCode=="0"||GLSMD=="0"||GLSUBJ=="0"||GL10ICAT=="0" ||GLSHLFNO=="0"){
			swal("Warning","Please fill in mandatory field", "warning" );
		}else{
		loader(".qrcodelist");
		$.get("Handler_TableResult",{GL10ICAT:GL10ICAT, locationCode:locationCode, GLSMD:GLSMD, GLSUBJ:GLSUBJ, GLSHLFNO:GLSHLFNO},function(result){
			alert("test");
			$(".qrcodelist").html(result);
			 $(".message").html("");
		});
		}
	});
	
	$(".checkAll").change(function () {
	    $("input:checkbox").prop('checked', $(this).prop("checked"));
	    if ($("input[name='selectedValue[]']:checked").length > 0){
			 $('#btn_update').prop("disabled", false);
			  }
			  else{
				  $('#btn_update').prop("disabled", true);
			  }
	    $(".message").html($('.update:checked').length + " enteries selected ");
	});
	

 	
 	  $('#updateBox').on('hidden.bs.modal', function(e)
 			    { 
 			        $(this).removeData();
 			   }) ;
 	  
		 
});