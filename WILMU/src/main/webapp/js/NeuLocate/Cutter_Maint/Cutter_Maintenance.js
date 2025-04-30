/**
 * 
 */

function getCutter(){
	$("#cuts").prop("checked", true)
	var cutters = $("#SL02CUTTERS").val();

	 $.get("Load_Cutters1",{box1:cutters
		},function(cutter){
			$(".box2").html(cutter);
			$("#SL02BOX1").val(cutters);
			$("#SL02BOX2").val(cutters);
			$("#SL02BOX2").prop('disabled', true);
		});
	
	
}


/*function cuttergrp(){
	alert("sss");
	$("input[name=selection][value='cutgrp']").prop("checked",true);
	$("#cuts").prop('disabled', true);
	$("#SL02CUTTER").prop('disabled', true);
}*/


function deletes(row){
	
	var cuttersgrp = $(row).data("cuttergrp");
	var cutters = $(row).data("cutter");
	var loca = $(row).data("loca");
	
	if(cutters!=cuttersgrp){
		  var i=row.parentNode.parentNode.rowIndex;
	        document.getElementById('listOfCutters').deleteRow(i);
	}else{
		$("table.listOfCutter> tbody > tr").each(function(Mindex, Mval) {

			if(($(this).find('td.slo2loca').text()==loca)&&($(this).find('td.sl02box').text()==cuttersgrp)){
					$("#" + loca+cuttersgrp).remove();
			/*	var i=row.parentNode.parentNode.rowIndex;
			        document.getElementById('listOfCutters').deleteRow(i);*/
			}
		});
	}
/*	var count = 0;
	$("table.listOfCutter> tbody > tr").each(function(Mindex, Mval) {
		alert("sdsd");
		var cuttergrp = $(Mval).find('td:eq(1)').text();
		 count++;
		if(count>1){
			alert("more than");
		}*/
     /*   var url = "Handler_AddCutter?SL02LOCA=" + $(Mval).find('td:eq(0)').text() + 
        	"&SL02BOX=" + $(Mval).find('td:eq(1)').text()+ 
			"&SL02CUTTER=" + $(Mval).find('td:eq(2)').text();
 
        $.get(url,function(cutterMsg){
     	  
        });*/
		
		
	//});
	   
	//var cuttergrp = $(row).data("cuttergrp");

	  /*$.get(url,function(cutterMsg){
    	  
      });
	*/
	
	  var rowCount = $('#listOfCutters >tbody >tr').length;
		
		if(rowCount>0){
			 $('#addValue').prop("disabled", false);
		}else{
			 $('#addValue').prop("disabled", true);
		}
}

function getLocaDesc(){
	document.getElementById("GL05DESC").value = document.getElementById("GL05LOCA").value;
}

function getLocaCode(){
	document.getElementById("GL05LOCA").value = document.getElementById("GL05DESC").value;
}

function getLocaDesc1(){
	document.getElementById("mainLocaDesc").value = document.getElementById("mainLocaCode").value;
}

function getLocaCode1(){
	document.getElementById("mainLocaCode").value = document.getElementById("mainLocaDesc").value;
}




function deleteCutter(cutter){
	var cuttergrp = $(cutter).data("cuttergrp");
	var location = $(cutter).data("loca");
	var cutter = $(cutter).attr("id");
	swal({   
		title: 'Are you sure want to delete?',
		text: 'The cutter will be removed',
		type: 'warning',   showCancelButton: true,
		confirmButtonColor: '#3085d6', 
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes, delete it !',
		cancelButtonText: 'No, cancel !',
		confirmButtonClass: 'confirm-class',
		cancelButtonClass: 'cancel-class',
		closeOnConfirm: false,
		closeOnCancel: false 
		}).then(function() {
			
			 var deleteUrl = "Handler_DeleteCutter?SL02CUTTER="+cutter + "&SL02BOX=" + cuttergrp + "&SL02LOCA=" + location ;
			  
		      $.ajax({
		 			url: deleteUrl,
		 			success: function(result) {
		 				var status = result.replace(/\s+/g, '');
		 				if(status == "ok"){
		 					if(cutter!=cuttergrp){
		 					  swal(
								'Deleted!',"Cutter " + cutter+ ' has been deleted.','success'
								);
		 					}else{
		 						swal(
										'Deleted! Cutter Group', cuttergrp+ ' with child has been deleted.','success'
										);
		 					}
		 					$('.swal2-confirm').click(function(){
			  					var SL02BOX1 = document.getElementById("SL02BOX1");
			  					var SL02BOX1Code = SL02BOX1.options[SL02BOX1.selectedIndex].value;
			  					var SL02BOX2 = document.getElementById("SL02BOX2");
			  					var SL02BOX2Code = SL02BOX2.options[SL02BOX2.selectedIndex].value;
			  					var GL05LOCA = document.getElementById("mainLocaCode");
			  					var GL05LOCAcode = GL05LOCA.options[GL05LOCA.selectedIndex].value;
			  					loader("#listOfCutter");
			  					 $.get("List_Cutters",{SL02BOX1:SL02BOX1Code, SL02BOX2:SL02BOX2Code, GL05LOCA:GL05LOCAcode
			  						 						},function(cutters){
			  						 $("#listOfCutter").html(cutters);
			  					 });
			   		        });

		 				}else{
		 				   swal(
								'Not Deleted',
								'Template name exist in other table',
								'error'
						  );
		 				}
		 			}
		 		});
					},function(dismiss) {
						  if (dismiss === 'cancel') {
						    swal(
						      'Cancelled',
						      'Your imaginary file is safe :)',
						      'error'
						    );
						  }
						})
}


function cuttergrp(){
	var box1 = $("#SL02BOX1 :selected").val();
	$("input[name=selection][value='cutgrp']").prop("checked",true);
		$("#SL02CUTTERS option[value='0']").prop('selected', true);
		$("#cuts").prop('disabled', false);
		$("#SL02CUTTERS").prop('disabled', true);
}


/*function deleteRow(row)
{
	
	$("table.listOfCutters > tbody > tr").each(function(Mindex, Mval) {
        var url = "Handler_AddCutter?SL02LOCA=" + $(Mval).find('td:eq(0)').text() + 
        	"&SL02BOX=" + $(Mval).find('td:eq(1)').text()+ 
			"&SL02CUTTER=" + $(Mval).find('td:eq(2)').text();
 
        $.get(url,function(cutterMsg){
     	  
        });
	});
	
	var cutter = $(row).attr('data-cutter');
	var cuttergrp = $(row).attr('data-cuttergrp');
	
	if(cutter==cuttergrp){
		
	}
    var i=row.parentNode.parentNode.rowIndex;
    document.getElementById('listOfCutters').deleteRow(i);
}
	*/

$(document).ready(function () {
	
/*	if($("#cuts").attr("checked") == true){
		$("#SL02CUTTERS").prop('disabled', false);
	}else if($("#cuts").attr("checked") == false){
		$("#SL02CUTTERS").prop('disabled', true);
	}
		*/

	var rowCount = $('#listOfCutters >tbody >tr').length;
	
	if(rowCount>1){
		 $('#addValue').prop("disabled", false);
	}else{
		 $('#addValue').prop("disabled", true);
	}
	
	$("#delPOIbutton").click(function(event){
		//alert("ddss");
	});
	/* 
	$("input[name=selection]").unbind().click(function(event){
		var values = $(this).val();
		
		if(values=="cutgrp"){
			$("#SL02CUTTERS option[value='0']").prop('selected', true);
			$("#cuts").prop('disabled', false);
			$("#SL02CUTTERS").prop('disabled', true);
			$("#SL02BOX1").prop('disabled', false);
			$("#SL02BOX2").prop('disabled', false);
		}else if(values=="cuts"){
			$("input[name=selection][value='cuts']").prop("checked",true);
			$("#SL02BOX1").prop('disabled', true);
			$("#SL02BOX2").prop('disabled', true);
		}
	}); */

	$('#addCutter').on('hidden.bs.modal', function () {
		 location.reload();
		})
		
		$('#cutter_search').unbind().click(function(event){
			var SL02BOX1 = document.getElementById("SL02BOX1");
			var SL02BOX1Code = SL02BOX1.options[SL02BOX1.selectedIndex].value;
			var SL02BOX2 = document.getElementById("SL02BOX2");
			var SL02BOX2Code = SL02BOX2.options[SL02BOX2.selectedIndex].value;
			var GL05LOCA = document.getElementById("mainLocaDesc");
			var GL05LOCAcode = GL05LOCA.options[GL05LOCA.selectedIndex].value;
	
			
			if(SL02BOX1Code=="0"||SL02BOX2Code=="0"||GL05LOCAcode=="0"){
				swal("Warning","Please fill in mandatory field", "warning" );
			}else{
				loader("#listOfCutter");
			 $.get("List_Cutters",{SL02BOX1:SL02BOX1Code, SL02BOX2:SL02BOX2Code, GL05LOCA:GL05LOCAcode
				 						},function(cutters){
				 $("#listOfCutter").html(cutters);
						/*$("#SL02CUTTERS option[value='0']").prop('selected', true);
						$("#SL02CUTTERS").prop('disabled', false);
						$("#SL02BOX1 option[value='0']").prop('selected', true);
						$("#SL02BOX2 option[value='0']").prop('selected', true);
						$("#mainLocaCode option[value='0']").prop('selected', true);
						$("#mainLocaDesc option[value='0']").prop('selected', true);*/
			 });
		}
		});
		
	//$('#addValue').click(function(event){
		/*var SL02LOCA = $("#GL05LOCA").val();
		var SL02BOX = $("#SL02BOX").val();
		var SL02CUTTER = $("#SL02CUTTER").val();
		if(SL02LOCA=="0"||SL02BOX==""||SL02CUTTER==""){
			swal("Warning","Mandatory fields are not filled in", "warning" );
		}else{
		 $.get("Handler_AddCutter",{SL02LOCA:SL02LOCA, SL02BOX:SL02BOX,SL02CUTTER:SL02CUTTER },function(cutterMsg){
			if(cutterMsg.trim()=="ok"){
				swal("Successful","The record is added", "success" );
				 $("#addCutter").modal("hide");
			}else{
				swal("Warning","Cutter already exist in different group", "warning" );
				$("#addCutter").modal("show");
			}
			});
		}*/
	//});
	
	$("#updateCutter").on("hidden.bs.modal", function(){
		 $(this).removeData();
	});
	
	$('#SL02BOX, #SL02CUTTER').unbind().keyup(function(){
	    this.value = this.value.toUpperCase();
	});
	
	/* $('#SL02BOX1').change(function(){
		alert("ssss");
		$("input[name=selection][value='cutgrp']").prop("checked",true);
		$("#SL02CUTTERS option[value='0']").prop('selected', true);
		$("#cuts").prop('disabled', false);
		$("#SL02CUTTERS").prop('disabled', true);
	});
	
	 */
	
	//Add list of cutter and cutter group 
	$('#addValue').unbind().click(function(){
		var SL02BOX1 = document.getElementById("SL02BOX1");
		var SL02BOX1Code = SL02BOX1.options[SL02BOX1.selectedIndex].value;
		var SL02BOX2 = document.getElementById("SL02BOX2");
		var SL02BOX2Code = SL02BOX2.options[SL02BOX2.selectedIndex].value;
		var GL05LOCA = document.getElementById("GL05LOCA");
		var GL05LOCAcode = GL05LOCA.options[GL05LOCA.selectedIndex].value;
		
	    $("table.listOfCutter > tbody > tr").each(function(Mindex, Mval) {
           var url = "Handler_AddCutter?SL02LOCA=" + $(Mval).find('td:eq(0)').text() + 
           	"&SL02BOX=" + $(Mval).find('td:eq(1)').text()+ 
  			"&SL02CUTTER=" + $(Mval).find('td:eq(2)').text();
           $.get(url,function(cutterMsg){
        	
           });
		});
			swal("Successful","The record is added", "success" );
			$("#addCutter").modal("hide");
			
		 $.get("List_Cutters",{SL02BOX1:SL02BOX1Code, SL02BOX2:SL02BOX2Code, GL05LOCA:GL05LOCAcode
					},function(cutters){
						$("#listOfCutter").html(cutters);
			});
	
	 });
	
	$("input[name=selection]").unbind().change(function(){
		if($(this).val()=="cuts"){
			$("#SL02CUTTERS").prop('disabled', false);
			$("#SL02BOX2").prop('disabled', true);
		}else if($(this).val()=="cutgrp"){
			$("#SL02CUTTERS option[value='0']").prop('selected', true);
			$("#cuts").prop('disabled', false);
			$("#SL02CUTTERS").prop('disabled', true);
			$("#SL02BOX1").prop('disabled', false);
			$("#SL02BOX2").prop('disabled', false);
		}
	});
		
	$("#mainLocaCode").unbind().change(function(){
		var location = $(this).val();
		
		 $.get("Load_CutterMaint",{SL02LOCA:location
			},function(cutters){
				$("#loadcutter").html(cutters);
	});
		 
		 $.get("Load_CutMaint",{SL02LOCA:location
			},function(cutters){
				$("#loadcut").html(cutters);
	});
		
	});
	/*
	$("#SL02CUTTER").change(function(){
		var cutters = $(this).val();
		alert(cutters);
		$("SL02BOX1").val(cutters);
		$("SL02BOX2").val(cutters);
		
	});*/
	
	$("#mainLocaDesc").unbind().change(function(){
		var location = $(this).val();
		
		 $.get("Load_CutterMaint",{SL02LOCA:location
			},function(cutters){
				$("#loadcutter").html(cutters);
			});
		 
		 $.get("Load_CutMaint",{SL02LOCA:location
			},function(cutters){
				$("#loadcut").html(cutters);
			});
		
	});
	
	//Append to add more tha one cutter and cutter group
	$("#SL02BOX1").unbind().change(function(){
		var box1 = $(this).val();
		 $.get("Load_Cutters1",{box1:box1
			},function(cutters){
				$(".box2").html(cutters);
			});
	});
	//Append to add more tha one cutter and cutter group
	$(".appendCutter").unbind().click(function(){
		
		var SL02LOCA = $("#GL05LOCA").val();
		var SL02BOX = $("#subj").val();
		var SL02CUTTER = $("#shelfno").val();

		if(SL02LOCA=="0"||SL02BOX==""||SL02CUTTER==""){
			swal("Warning","Mandatory fields are not filled in", "warning" );
		}else{
			
			
			 $.get("Handler_Validation",{SL02LOCA:SL02LOCA, SL02BOX:SL02BOX,SL02CUTTER:SL02CUTTER },function(cutterMsg){
				 if(cutterMsg.trim()=="ok"){
					 $("#collapseOne").collapse("show");
					
					 var status = "";
					 var cont = "";
					 $("#listOfCutters tbody").find("tr").each(function() { //get all rows in table

						    var cutter = $(this).find('td.sl02cutter').text(); 
						    var grp = $(this).find('td.sl02box').text(); 
						    var location = $(this).find("td.slo2loca").text();
						    if(SL02CUTTER==cutter && SL02LOCA==location){
								 cont = "false";
								 return false;
							 }else if(SL02CUTTER==grp){
								 cont = "false";
								 return false;
							 }else{
								 cont ="continue";
								 if(grp!=SL02BOX){
								    	status = "non";
								    }else if(grp==SL02BOX && SL02LOCA==location){
								    	status = "found";								  
								    }else if(grp==SL02BOX && SL02LOCA!=location){
								    	status = "non";
								    }
							 }
						    

						});
					 
					 if(cont=="false"){
						 swal("Warning","Cutter already exist", "warning" );
						 return false;
					 }else if(cont="continue"){
					 var table = document.getElementById("listOfCutters").getElementsByTagName('tbody')[0];
					 	if(status=="non"){
					 		if(SL02BOX!=SL02CUTTER){
					 		 var row = table.insertRow(table.rows.length);
					 		 	row.id = SL02LOCA + SL02BOX;
							    var cell1 = row.insertCell(0);
							    var cell2 = row.insertCell(1);
							    var cell3 = row.insertCell(2);
							    var cell4 = row.insertCell(3);
							    cell1.innerHTML = SL02LOCA
							    cell1.className = 'slo2loca';
							    cell2.innerHTML = SL02BOX
							    cell2.className = 'sl02box';
							    cell3.innerHTML = SL02BOX
							    cell3.className = 'sl02cutter';
							    cell4.innerHTML = '<button class="btn btn-dull" data-loca="'+SL02LOCA+'" data-cutter="'+SL02BOX+'" data-cuttergrp="'+SL02BOX+'" onclick="deletes(this)"><span class="glyphicon glyphicon-trash" id="delPOIbutton"></span></button>';
					    	
					 		}
					 		}
					 	
						
						 var row = table.insertRow(table.rows.length);
						 	row.id = SL02LOCA + SL02BOX;
						    var cell1 = row.insertCell(0);
						    var cell2 = row.insertCell(1);
						    var cell3 = row.insertCell(2);
						    var cell4 = row.insertCell(3);
						    cell1.innerHTML = SL02LOCA
						    cell1.className = 'slo2loca';
								cell2.innerHTML = SL02BOX
							    cell2.className = 'sl02box';
							    cell3.innerHTML = SL02CUTTER
							    cell3.className = 'sl02cutter';
							    cell4.innerHTML = '<button class="btn btn-dull" data-loca="'+SL02LOCA+'" data-cutter="'+SL02CUTTER+'" data-cuttergrp="'+SL02BOX+'" onclick="deletes(this)"><span class="glyphicon glyphicon-trash" id="delPOIbutton"></span></button>';
							    
							    var rowCount = $('#listOfCutters >tbody >tr').length;
								
								if(rowCount>1){
									 $('#addValue').prop("disabled", false);
								}else{
									 $('#addValue').prop("disabled", true);
								}
					 }
				 }else if(cutterMsg.trim()=="exist"){
					swal("Warning","Cutter already exist in different group", "warning" );
				 }
			 });
		}
   		 
   		
   
	});
	
	 //Update template name, status  
 	$('#update').unbind().click(function(){

 		var SL02LOCA = $("#GL05LOCA").val();
 		var oriLoca = $("#oriLoca").val();
 		var oriGrp = $("#oriGrp").val();
		var SL02BOX = $("#SL02BOX").val();
		var SL02CUTTER = $("#SL02CUTTER").val();
		
		if(SL02LOCA=="0"||SL02BOX==""||SL02CUTTER==""){
			swal("Warning","Mandatory fields are not filled in", "warning" );
		}else{
   		var url = "Handler_UpdateCutter?GL05LOCA=" + SL02LOCA +
   				"&SL02BOX=" + SL02BOX+ "&SL02CUTTER=" + SL02CUTTER +
   				"&oriLoca=" + oriLoca+ 
   				"&oriGrp=" + oriGrp+ 
   				"&oriCutter=" + $("#oriCutter").val();
		 
   		$.ajax({
   			url: url,
   			success: function(result) {
  			if(result.trim() == "ok"){
  					$('#updateCutter').modal('hide');
  					swal(   'Updated!',   'Record is updated' ,   'success' )
  				$('.swal2-confirm').click(function(){
  					var SL02BOX1 = document.getElementById("SL02BOX1");
  					var SL02BOX1Code = SL02BOX1.options[SL02BOX1.selectedIndex].value;
  					var SL02BOX2 = document.getElementById("SL02BOX2");
  					var SL02BOX2Code = SL02BOX2.options[SL02BOX2.selectedIndex].value;
  					var GL05LOCA = document.getElementById("mainLocaCode");
  					var GL05LOCAcode = GL05LOCA.options[GL05LOCA.selectedIndex].value;
  					loader("#listOfCutter");
  					 $.get("List_Cutters",{SL02BOX1:SL02BOX1Code, SL02BOX2:SL02BOX2Code, GL05LOCA:GL05LOCAcode
  						 						},function(cutters){
  						 $("#listOfCutter").html(cutters);
  					/*	$("#SL02CUTTERS option[value='0']").prop('selected', true);
  						$("#SL02BOX1 option[value='0']").prop('selected', true);
  						$("#SL02BOX2 option[value='0']").prop('selected', true);
  						$("#mainLocaCode option[value='0']").prop('selected', true);
  						$("#mainLocaDesc option[value='0']").prop('selected', true);
  						$("#SL02CUTTERS").prop('disabled', false);*/
  					 });
   		        });
  				}
  				else{
  					swal("Warning","Cutter already exist in different group", "warning" );
  					$("#updateCutter").modal("show");
  				}
  			}
   		});
		}
   	});

});