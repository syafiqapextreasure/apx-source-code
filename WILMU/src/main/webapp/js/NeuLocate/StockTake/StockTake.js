/**
 * 
 */

function getTypeOfSelection(){
	//$(".newInventory").load("Load_EmptyTable");
	//$('#inventoryList').DataTable().ajax.reload();
	//$('.search_criteria').load("Load_OriPanel");
	  if($("#chknow").prop("checked") == true){
		  //$('.cutter1').show();
		  $('.cutter2').show();
		  $('#cutter').hide();
		  //$("#checkInventory").prop("disabled",false);
		  $("#enquire").prop("disabled",true);	
		  $("#retrieveLink").attr("disabled",true);	
		  $(".form-control").prop("disabled",false);
		  $('.barcode').css('display','inline-block');
		  $("#checkInventory").prop("disabled",false);	
		  $(".flag").val("");
		  $("#enter").prop("disabled",false);	
	  }else if($("#LRecord").prop("checked") == true){
		  $("#checkInventory").prop("disabled",true);
		  $("#enquire").prop("disabled",true);	
		  $("#retrieveLink").attr("disabled",false);
		  $(".form-control").prop("disabled",true);
		  $('.barcode').css('display','inline-block');
		  $("#barcode").prop("disabled",true);	
		  $("#enter").prop("disabled",true);	
		  $(".flag").val("Load");
	  }
   }

$ (document).ready( function() {
$('.multiselect').multiselect({
	 includeSelectAllOption: true,
	 maxHeight: 200
});

//Click on check now button to check inventory
	$("#checkStock").click(function(event){

		var callno1 = $("#input-startDate").val();
		var callno2 = $("#input-endDate").val();
		
		if(callno1==""){
			swal("Warning","Please fill in mandatory field", "warning" );
		}else{
				var searchItem = $('input[value="itemcate"]').is(':checked');
				var searchSmd = $('input[value="smd"]').is(':checked');
				var searchLoca = $('input[value="location"]').is(':checked');
				var searchBrnch = $('input[value="branch"]').is(':checked');
				//var itemcate = "";
				var itemcateArray = [];
				var smdArray = [];
				var locationArray = [];
				var branchArray = [];
				
				if(searchItem){
					 $('#icat option:selected').each(function() {
						 itemcateArray.push("'" + [$(this).val()] + "'");
			         });
				}
				if(searchSmd){
					 $('#smd option:selected').each(function() {
						 smdArray.push("'" +[$(this).val()]+ "'");
			         });
				}
				
				if(searchLoca){
					 $('#location option:selected').each(function() {
						 locationArray.push("'" +[$(this).val()]+ "'");
			         });
				}
				
				if(searchBrnch){
					 $('#branch option:selected').each(function() {
						 branchArray.push("'" +[$(this).val()]+ "'");
			         });
				}
	
				var barcodeList = [];
				var barcodeList1 = [];
				var barcodeAudit = [];
				 $('.inventoryList').find('tbody tr').each(function () {
					 var Barcode = $(this).find("td.ListBarcode").text();
					 barcodeList.push(Barcode.trim());
					 barcodeAudit.push(Barcode.trim());
					 barcodeList1.push("'"+Barcode.trim()+"'");
				 });
				alert(itemcateArray.toString());
				 loader(".newInventory");
					
				 $.get("NeuLocate/StockTake",{callno1:callno1, callno2:callno2,barcodeList:barcodeList,branchArray:branchArray.toString(),
					 locationArray:locationArray.toString(),smdArray:smdArray.toString(), itemcateArray:itemcateArray.toString(),
					 actionID:"Handler_InventoryCheck"},function(inventoryList){
	
					 
					 $(".newInventory").html(inventoryList);
						swal(
								'',
								'Inventory successfully checked',
								'success'
							);

				
				 });
	
			   
			  
		}

		
		}); 
	
})		