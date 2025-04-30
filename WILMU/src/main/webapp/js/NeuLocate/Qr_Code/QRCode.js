/**
 * 
 */
	function submit(){
	
		 $(".message").html("");
	
		var SL02BOX1 = document.getElementById("SL02BOX1");
		var SL02BOX1Code = SL02BOX1.options[SL02BOX1.selectedIndex].value;
		var SL02BOX2 = document.getElementById("SL02BOX2");
		var SL02BOX2Code = SL02BOX2.options[SL02BOX2.selectedIndex].value;
		var location = document.getElementById("GL05LOCA");
		var locationCode = location.options[location.selectedIndex].value;
		var smd = $('#CT03SMD :selected').val();
		if(smd=="COM"){
			if(SL02BOX1Code=="0"||SL02BOX2Code=="0"||smd=="0"||locationCode=="0"){
				swal("Warning","Please fill in mandatory field", "warning" );
			}else{
				loader(".qrcodelist");
			 $.get("List_QR_CODE",{SL02BOX1:SL02BOX1Code, SL02BOX2:SL02BOX2Code, locationCode:locationCode,
				 					CT03SMD:smd},function(qrcode){
				 $(".qrcodelist").html(qrcode);
			 });
			}
		}else{
	
			if(SL02BOX1Code=="0"||SL02BOX2Code=="0"||smd=="0"||locationCode=="0"){
				swal("Warning","Please fill in mandatory field", "warning" );
			}else{
				loader(".qrcodelist");
			 $.get("List_QR_CODE",{SL02BOX1:SL02BOX1Code, SL02BOX2:SL02BOX2Code, locationCode:locationCode,
				 					CT03SMD:smd},function(qrcode){
				 $(".qrcodelist").html(qrcode);
			 });
			}
		}
	}

function getLocaDesc(){
	document.getElementById("GL05DESC").value = document.getElementById("GL05LOCA").value;
}

function getLocaCode(){
	document.getElementById("GL05LOCA").value = document.getElementById("GL05DESC").value;
}

function loadCutter(location){
	//alert(location);
}
function getValue(){
	$(".message").html($('.qrchk:checked').length + " enteries selected ");
}

$(document).ready(function () {
	

	//To load cutter group and cutters based on smd selection
	$('#CT03SMD').unbind().change(function(event){
		var GL05LOCA = $('#GL05LOCA :selected').val();
		var CT03SMD = $(this).val();
	if(CT03SMD=="COM"){
		$.get("LoadQRCutters",{GL05LOCA:GL05LOCA,CT03SMD:CT03SMD, action:"cutter"},function(cutterList){
			$(".loadcutter").html(cutterList);
		});

		}else{
			$.get("LoadQRCutters",{GL05LOCA:GL05LOCA,CT03SMD:CT03SMD, action:"CallNo"},function(cutterList){
				$(".loadcutter").html(cutterList);
				 return false;
				});
		
		}
	
	});
	
	//Append to add more tha one cutter and cutter group
	$("#SL02BOX1").change(function(){
		var GL05LOCA = $('#GL05LOCA :selected').val();
		var CT03SMD = $('#CT03SMD :selected').val();
		var box1 = $(this).val();
		if(CT03SMD=="COM"){
		 $.get("Load_QRCutters1",{GL05LOCA:GL05LOCA, CT03SMD:CT03SMD, box1:box1
			},function(cutters){
				$(".box2").html(cutters);
			});
		}else{
			 $.get("LoadQRShelf",{GL05LOCA:GL05LOCA, CT03SMD:CT03SMD, box1:box1
				},function(cutters){
					$(".box2").html(cutters);
				});
		}
	});
	
	$(".submit1").click(function(){

		 $(".message").html("");
	
		var SL02BOX1 = document.getElementById("SL02BOX1");
		var SL02BOX1Code = SL02BOX1.options[SL02BOX1.selectedIndex].value;
		var location = document.getElementById("GL05LOCA");
		var locationCode = location.options[location.selectedIndex].value;
		var smd = $('#CT03SMD :selected').val();
		if(smd=="COM"){
			var SL02BOX2 = document.getElementById("SL02BOX2");
			var SL02BOX2Code = SL02BOX2.options[SL02BOX2.selectedIndex].value;
			if(SL02BOX1Code=="0"||SL02BOX2Code=="0"||smd=="0"||locationCode=="0"){
				swal("Warning","Please fill in mandatory field", "warning" );
			}else{
				loader(".qrcodelist");
			 $.get("List_QR_CODE",{SL02BOX1:SL02BOX1Code, SL02BOX2:SL02BOX2Code, locationCode:locationCode,
				 					CT03SMD:smd},function(qrcode){
				 $(".qrcodelist").html(qrcode);
			 });
			}
		}else{
		
			if(SL02BOX1Code=="0"||smd=="0"||locationCode=="0"){
				swal("Warning","Please fill in mandatory field", "warning" );
			}else{
				loader(".qrcodelist");
			 $.get("List_QR_CODE",{SL02BOX1:SL02BOX1Code, locationCode:locationCode,
				 					CT03SMD:smd},function(qrcode){
				 $(".qrcodelist").html(qrcode);
			 });
			}
		}
	});
	

	
	$("#print").click(function(){
		window.open('', 'TheWindow');
		$("#selectedValue").submit();
	})
	/*var URL=[];
	$("#print").click(function(){
		
		var SL02BOX1 = document.getElementById("SL02BOX1");
		var SL02BOX1Code = SL02BOX1.options[SL02BOX1.selectedIndex].value;
		var SL02BOX2 = document.getElementById("SL02BOX2");
		var SL02BOX2Code = SL02BOX2.options[SL02BOX2.selectedIndex].value;
		$('input[name="selectedValue[]"]:checked').each(function() {
			URL.push(this.value);
			});

		 $.get("Print_QR_Code",{selectedValue:URL.toString()},function(qrcode){

			 							//alert(qrcode);
			 //$(".qrcodelist").html(qrcode);
		 });
	});*/
	
	$(".checkAll").change(function () {
	    $("input:checkbox").prop('checked', $(this).prop("checked"));
	    $(".message").html($('.qrchk:checked').length + " enteries selected ");
	});
	
	
});