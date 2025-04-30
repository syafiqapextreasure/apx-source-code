<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script>
//Validate BO -- In use 
function validateBO(){

	var tags = [];
	var indi1s = [];
	var indi2s = [];
	var subf = [];
	var validate = false;
	 var dfrd1 = $.Deferred();

	 $('#BO_Record > tbody  > tr').each(function() {
		 var tag  = ($(this).find(".tagValue").text());
		 var indi1  = ($(this).find(".indi1").val());
		 var indi2  = ($(this).find(".indi2").val());
		 var subfield  = ($(this).find(".data").val());

		/* if(subfield.trim()!="" && subfield.trim().includes("|")){
			 subfield = subfield.split("|")[1].substring(0,1);
		 }else{
			 subfield = " ";
		 }
		 */
		 tags.push(tag.trim());
		 indi1s.push(indi1.trim());
		 indi2s.push(indi2.trim());
		 subf.push(subfield);		
		 
		 FORM_HAS_CHANGED = false;
	 });

	 
	 $.get("ValidateBO",{tag:tags, indi1:indi1s, indi2:indi2s, subf:subf, total:tags.length},function(data){

		if(data.trim().includes('No_MandatoryTag')){
			var mandaTag = data.trim().split(',');
			alertMessage("", "info", "011", mandaTag[1], "@tag");
		}else if(data.trim()==""){
			validate = true;
			dfrd1.resolve(validate);
		}else{
			swal("", data.trim(), "");
		}
	});
	 return dfrd1.promise();
}
$("#duplicateCopy").click(function(){
	var type = $(".typecode").val();
	
	var matno ="";
	var createby = "";
	var createdate = "";
	var creatime = "";
	var modiby ="";
	var modidate = "";
	var moditime = "";
	
	$('#rcrddetails').each(function(index){
		
		matno = $(this).find("span.Cmatno").text().trim();
		createby = $(this).find("span.Cuser").text().trim();
		createdate = $(this).find("span.Cdate").text().trim();
		creatime = $(this).find("span.Ctime").text().trim();
		modiby =$(this).find("span.Muser").text().trim();
		modidate = $(this).find("span.Mdate").text().trim();
		moditime = $(this).find("span.Mtime").text().trim();
	}); 

	$("#rcrddetails").find("tr").remove();
	$.get("Duplicate_Copy",{action:"Duplicate Copy", type:type, matno:matno,createby:createby, createdate:createdate, creatime:creatime, 
		  					  modiby:modiby, modidate:modidate, moditime:moditime},function(data_title){
	$(".cancelcpy").css("display", "none");
	$(".duplicatecpy").css("display", "inline-block");
/* $("#duplicateCopy").click(function(){
	var type = $(".typecode").val();
	$("#rcrddetails").find("tr").remove();
	  $.get("Duplicate_Copy",{action:"Duplicate Copy", type:type},function(data_title){ */
		 // $('#rcrddetails').find('tbody').append(data_title);
		  $('#rcrddetails').html("");
		  $('#rcrddetails').html(data_title);
		  $(".rawTag, .indi1, .indi2, .editdata, #retrieveLinks, .delete, .linkage ").removeAttr("disabled");
			 $("#duplicateCopy").addClass("isDisabled");
				$("#addTag").removeClass("isDisabled");
				$("#searchAccNo").addClass("isDisabled");
				$("#addBORcrd").removeClass("isDisabled");
				$("#indexBO").addClass("isDisabled");
				$("#saveindex").removeClass("isDisabled");
				$("#unindex").addClass("isDisabled");
				$("#delete").addClass("isDisabled");
				$("#modify").addClass("isDisabled");
		 // $('#BO_Record').find('input').prop("disabled", true);
		   /* $("li").removeClass("saveIndexBO");
		    $("li").removeClass("saveBO");
		    $("li").removeClass("addTagBO");*/
	  }); 
});


//Index record --In use
$("a#indexBO").click(function() {
	validateBO().done(function(validate){
		
		if(validate==true){
			disableAllAction();
$('span.Cstatus').html("");

var matno ="";
var createby = "";
var createdate = "";
var creatime = "";
var CT04MATNO = "";
var type="";
var indi1 = [];
var indi2 = [];
var values = [];
var raw = [];
var manda = [];
var tag006 = "";
var tag007 = "";
var tag008="";

$('#rcrddetails').each(function(index){
	
	matno = $(this).find("span.Cmatno").text().trim();
	createby = $(this).find("span.Cuser").text().trim();
	createdate = $(this).find("span.Cdate").text().trim();
	creatime = $(this).find("span.Ctime").text().trim();
	modiby = $(this).find("#newUser").val().trim();
	modidate = $(this).find("span.Mdate").text().trim();
	moditime = $(this).find("span.Mtime").text().trim();
	type=$(this).find(".typecode").val().trim();
}); 


 $('#BO_Record').find('tbody tr').each(function () {

	 var CT04TAG = $(this).find("td.tagValue").text();
	 var CT04INDI1  = ($(this).find(".indi1").val());
	 var CT04INDI2  = ($(this).find(".indi2").val());
	 var CT04RAW = $(this).find(".data").val();
	 
	 if(CT04TAG.trim()=="006"){
		 tag006 = $(this).find(".editdata").attr("data-template");
	 }else if(CT04TAG.trim()=="007"){
		 tag007 = $(this).find(".editdata").attr("data-template");
	 }else if(CT04TAG.trim()=="008"){
		 tag008 = $(this).find(".editdata").attr("data-template");
	 }
	 
	 indi1.push(CT04INDI1);
	 indi2.push(CT04INDI2);
	 values.push(CT04TAG.trim());
	 raw.push(CT04RAW);

	 if($(this).find(".data").data("manda")=="Y" && ($(this).find(".data").val()).substring(2,3)=="|" || 
			 $(this).find(".data").data("manda")=="Y" && ($(this).find(".data").val()).substring(2,3)==""){

		 manda.push($(this).find("td.tagValue").text());
	 }
 });

if(manda.length<=0){
	 indexing(matno,values,indi1,indi2,raw,raw.length,createby,creatime,
				createdate, modiby,modidate, moditime,type,"DuplicateChk", tag006, tag007, tag008);

}else{
	swalMessage("", "info", "115", null, null);
 }

    //$('#tpl option[value="0"]').attr("selected",true);
	$('#tpl').val("0");
		}
		});
});


//Original indexing --In use
function indexing(matno,values,indi1,indi2,raw,total,createby,creatime,
		createdate, modiby,modidate, moditime, type, action, tag006, tag007, tag008){

	$.get("Handler_Indexing",{matno:matno, tag:values,indi1:indi1,
		indi2:indi2,rawdata:raw, total:total, createby:createby, creatime:creatime,
		createdate:createdate, modiby:modiby, modidate:modidate, moditime:moditime, type:type, action:action,
		tag006:tag006, tag007:tag007, tag008:tag008},
			function(message){

				if(message.trim().includes('No_MandatoryTag')){
					var mandaTag = message.trim().split(',');
					swalMessage("", "info", "011", mandaTag[1], "@tag");
				}else if (message.trim().includes("Successful")){
					var bufferno = message.trim().split(',');
					$("span.Muser").html($("#newUser").val());
					$("span.Mtime").html($(".newModitime").val());
					$("span.Mdate").html($(".newModidate").val());
					$("#duplicateCopy").removeClass("isDisabled");
					$("#addTag").addClass("isDisabled");
					$("#searchAccNo").removeClass("isDisabled");
					$("#addBORcrd").addClass("isDisabled");
					$("#indexBO").addClass("isDisabled");
					$("#saveindex").addClass("isDisabled");
					$("#unindex").removeClass("isDisabled");
					$("#delete").addClass("isDisabled");
					$("#modify").addClass("isDisabled");

					//$(".delete, .btn-raised, .subfButton").attr("disabled","disabled");
					$("input, .editdata, #retrieveLinks, .delete, .linkage ").attr("disabled","disabled");
					$('span.Cstatus').html('');
			  		$('span.Cstatus').append('Record Indexed');
					$('td.orimatno').html("");
			  		$('td.orimatno').html('Control No:<span class="Cmatno">'+bufferno[1] + '</span>');
			  		swalMessage("Successful", "success", "007", null, null); 
			  		 $("#unindex").removeClass("isDisabled");
				}else if (message.trim() == "Fail"){
					$("#indexBO").removeClass("isDisabled");
					swal("", "Record fail to index", "");
				}else if (message.trim().includes("true")){

					swal({
						  title: 'This record maybe duplicated.',
						  text: "Do you wish to proceed?",
						  type: 'warning',
						  showCancelButton: true,
						  confirmButtonColor: '#3085d6',
						  cancelButtonColor: '#d33',
						  confirmButtonText: 'Yes'
						}).then(function() {
							 indexing(matno,values,indi1,indi2,raw,raw.length,createby,creatime,
										createdate, modiby,modidate, moditime,type,"skipDuplicateChk", tag006, tag007, tag008);
						},function(dismiss) {
						  if (dismiss === 'cancel') {
								//next = false;
						  }
						})
				}
	}); 
}

//Unindex record --In use
$('#unindex').click(function() {

	  $("#addTag").removeAttr("disabled");
	
	  var bufferno ="";
	  var matno ="";
	  var creteby = "";
	  var createdate = "";
	  var creatime = "";
	  var modiby = "";
	  var modidate = "";
	  var moditime = "";
	  var type="";
	  var values = [];
	  var raw = [];
	  var indi1 = [];
	  var indi2 = [];
	  var matno = $("span.Cmatno").text();

	  
	  $('#rcrddetails').each(function(index){
			
			matno = $(this).find("span.Cmatno").text().trim();
			createby = $(this).find("span.Cuser").text().trim();
			createdate = $(this).find("span.Cdate").text().trim();
			creatime = $(this).find("span.Ctime").text().trim();
			modiby = $(this).find("#newUser").val().trim();
			modidate = $(this).find("span.Mdate").text().trim();
			moditime = $(this).find("span.Mtime").text().trim();
			type=$(this).find(".typecode").val().trim();
		}); 

		 $('#BO_Record').find('tbody tr').each(function () {
		
			 var CT04TAG = $(this).find("td.tagValue").text();
			 var CT04INDI1  = ($(this).find(".indi1").val());
			 var CT04INDI2  = ($(this).find(".indi2").val());
			 var CT04RAW = $(this).find(".data").val();
			 indi1.push(CT04INDI1);
			 indi2.push(CT04INDI2);
			 values.push(CT04TAG.trim());
			 raw.push(CT04RAW);

			 if($(this).find(".data").data("manda")=="Y" && ($(this).find(".data").val()).substring(2,3)=="|" || 
					 $(this).find(".data").data("manda")=="Y" && ($(this).find(".data").val()).substring(2,3)==""){

				 manda.push($(this).find("td.tagValue").text());
			 }
		 });

	  $.get("Handler_Unindex",{matno:matno,tag:values,indi1:indi1,
			indi2:indi2,raw:raw,createby:createby,createdate:createdate,creatime:creatime,
			modiby:modiby,modidate:modidate,moditime:moditime, total:raw.length, type:type, action:"Unindex"},
			function(message){
				if(message.trim().includes('No_MandatoryTag')){
					var mandaTag = message.trim().split(',');
					alertMessage("", "info", "011", mandaTag[1], "@tag");
				}else if (message.trim().includes("Successful")){
					var bufferno = message.trim().split(',');
						$("span.Muser").html($("#newUser").val());
						$("span.Mtime").html($(".newModitime").val());
						$("span.Mdate").html($(".newModidate").val());
					  $("td.orimatno").html("Buffer No.: <span class='Cmatno'>" + bufferno[1] + "</span>");
					  $('span.Cstatus').html('');
					  $('span.Cstatus').append('Record Unindexed');
					 /* $("li").removeClass("saveBO");
					  $("li").removeClass("indexBO");
					  $("li").removeClass("saveIndexBO");
					  $("li").removeClass("deleteBO");
					  $("li").removeClass("addTagBO");*/
					  
						$("#duplicateCopy").addClass("isDisabled");
						$("#addTag").removeClass("isDisabled");
						$("#searchAccNo").addClass("isDisabled");
						$("#addBORcrd").removeClass("isDisabled");
						$("#indexBO").removeClass("isDisabled");
						$("#saveindex").removeClass("isDisabled");
						$("#unindex").addClass("isDisabled");
						$("#delete").removeClass("isDisabled");
						$("#modify").addClass("isDisabled");
						//$("input, .editdata, #retrieveLink, .delete ").attr("disabled","disabled");
					  $('#BO_Record').find('textarea, button, select, a, .rawtag, .indi1, .indi2').removeAttr("disabled")
					  swalMessage("Successful", "success", "014", null, null);
					  //$('#tpl option[value="0"]').attr("selected",true);
					  $('#tpl').val("0");
				}else if (message.trim() == "Fail"){
					swal("", "Record fail to unindex", "");
				}
	});
});


//Save and index - In use
$('#saveindex').click(function() {
	validateBO().done(function(validate){
		
		if(validate==true){
			disableAllAction();
			var matno ="";
			var createby = "";
			var createdate = "";
			var creatime = "";
			var modiby = "";
			var modidate = "";
			var moditime = "";
			var type= "";
			var exist = true;
			var values = [];
			var indi1 = [];
			var indi2 = [];
			var raw = [];
			var manda = [];
			var ctrltags= [];
			var tag006 = "";
			var tag007 = "";
			var tag008 = "";
			
			
			$('#rcrddetails').each(function(index){
				
				matno = $(this).find("span.Cmatno").text().trim();
				createby = $(this).find("span.Cuser").text().trim();
				createdate = $(this).find("span.Cdate").text().trim();
				creatime = $(this).find("span.Ctime").text().trim();
				modiby = $(this).find("#newUser").val().trim();
				modidate = $(this).find("span.Mdate").text().trim();
				moditime = $(this).find("span.Mtime").text().trim();
				type=$(this).find(".typecode").val().trim();
			}); 
			 $('#BO_Record').find('tbody tr').each(function () {
			
				 var CT04TAG = $(this).find("td.tagValue").text();
				 var CT04INDI1  = ($(this).find(".indi1").val());
				 var CT04INDI2  = ($(this).find(".indi2").val());
				 var CT04RAW = $(this).find(".data").val();
				 var template = $(this).find(".editdata").attr("data-template");
				 indi1.push(CT04INDI1);
				 indi2.push(CT04INDI2);
				 values.push(CT04TAG.trim());
				 raw.push(CT04RAW);
				 ctrltags.push(template);
				 
				 if(CT04TAG.trim()=="006"){
					 tag006 = $(this).find(".editdata").attr("data-template");
				 }else if(CT04TAG.trim()=="007"){
					 tag007 = $(this).find(".editdata").attr("data-template");
				 }else if(CT04TAG.trim()=="008"){
					 tag008 = $(this).find(".editdata").attr("data-template");
				 }

				 if($(this).find(".data").data("manda")=="Y" && ($(this).find(".data").val()).substring(2,3)=="|" || 
						 $(this).find(".data").data("manda")=="Y" && ($(this).find(".data").val()).substring(2,3)==""){

					 manda.push($(this).find("td.tagValue").text());
				 }
			 });
			 if(manda.length<=0){
				
				$.get("Handler_AddBO",{matno:matno, tag:values,indi1:indi1,
							indi2:indi2,rawdata:raw, total:raw.length, createby:createby, creatime:creatime,
							createdate:createdate, modiby:modiby, modidate:modidate, moditime:moditime, type:type,
							ctrltag:ctrltags},
							function(message){
							/*alert("Save" + message);
							if(message.trim().includes('No_MandatoryTag')){
								var mandaTag = message.trim().split(',');
								alertMessage("", "info", "011", mandaTag[1], "@tag");
							}else*/ if (message.trim().includes("Successful")){
								var bufferno = message.trim().split(',');
								var bufferno = message.trim().split(',');
								
								indexing(bufferno[1], values,indi1,indi2,raw, raw.length, createby, 
											creatime,createdate, modiby, modidate, moditime, type, "DuplicateChk",tag006, tag007, tag008); 
								document.getElementById("indexBO").style.cursor = "pointer";
							}else if (message.trim() == "Fail"){
								swal("", "Record fail to save", "");
							}

					}); 
			 }else{
				 swalMessage("", "info", "115", null, null);
			 }
			 
			// $('#tpl option[value="0"]').attr("selected",true);
			 $('#tpl').val("0");
		}
	 });

});


//Save BO --In use
$('#addBORcrd').click(function(){
	validateBO().done(function(validate){
	
		if(validate==true){
			var matno ="";
			var createby = "";
			var createdate = "";
			var creatime = "";
			var modiby = "";
			var modidate = "";
			var moditime = "";
			var type="";
			var exist = true;
			var values = [];
			var indi1 = [];
			var indi2 = [];
			var raw = [];
			var manda = [];
			var ctrltags= [];
			
			
			$('#rcrddetails').each(function(index){
				
				matno = $(this).find("span.Cmatno").text().trim();
				createby = $(this).find("span.Cuser").text().trim();
				createdate = $(this).find("span.Cdate").text().trim();
				creatime = $(this).find("span.Ctime").text().trim();
				modiby = $(this).find("#newUser").val().trim();
				modidate = $(this).find("span.Mdate").text().trim();
				moditime = $(this).find("span.Mtime").text().trim();
				type=$(this).find(".typecode").val().trim();
			}); 
			
			var table = document.getElementById('BO_Record');
			var targetTDs = table.querySelectorAll('tbody>tr > td:first-child');


			var hasLooped = false;
			  if(!hasLooped){
				$('.BO_Record tbody').find('tr').each(function () {
					 var CT04TAG = $(this).find("td.tagValue").text();
					 var template = $(this).find(".editdata").attr("data-template");
					 var CT04INDI1  = ($(this).find(".indi1").val());
					 var CT04INDI2  = ($(this).find(".indi2").val());
					 var CT04RAW = $(this).find(".data").val();
					 indi1.push(CT04INDI1);
					 indi2.push(CT04INDI2);
					 values.push(CT04TAG.trim());

					 ctrltags.push(template);
					 raw.push(CT04RAW);
					 if($(this).find(".data").data("manda")=="Y" && ($(this).find(".data").val()).substring(2,3)=="|" || 
							 $(this).find(".data").data("manda")=="Y" && ($(this).find(".data").val()).substring(2,3)==""){

						 manda.push($(this).find("td.tagValue").text());
					 }
				 });
				 hasLooped = true;
			  }
			if(manda.length<=0){
				//alert(raw.length);
				
				
				$.get("Handler_AddBO",{matno:matno, tag:values,indi1:indi1,
							indi2:indi2,rawdata:raw, total:raw.length, createby:createby, creatime:creatime,
							createdate:createdate, modiby:modiby, modidate:modidate, moditime:moditime, type:type,
							ctrltag:ctrltags},
							function(message){
							if(message.trim().includes('No_MandatoryTag')){
								var mandaTag = message.trim().split(',');
								alertMessage("", "info", "011", mandaTag[1], "@tag");
							}else if (message.trim().includes("Successful")){
								var bufferno = message.trim().split(',');
								$("span.Muser").html($("#newUser").val());
								$("span.Mtime").html($(".newModitime").val());
								$("span.Mdate").html($(".newModidate").val());
								$("#duplicateCopy").addClass("isDisabled");
								$("#addTag").addClass("isDisabled");
								$("#searchAccNo").addClass("isDisabled");
								$("#addBORcrd").addClass("isDisabled");
								$("#indexBO").removeClass("isDisabled");
								$("#saveindex").addClass("isDisabled");
								$("#unindex").addClass("isDisabled");
								$("#delete").removeClass("isDisabled");
								$("#modify").removeClass("isDisabled");
								//$("li").removeClass("modifyBO");
				 			 	$('#modify').attr('onClick','modifyRcrd()');
					   			$("input, .editdata, #retrieveLinks, .delete, .linkage ").attr("disabled","disabled");
								$('span.Cmatno').html("");
								//$('#tpl option[value="0"]').attr("selected",true);
								 $('#tpl').val("0");
								$('td.orimatno').html("");
						  		$('td.orimatno').html('Buffer No:<span class="Cmatno">'+bufferno[1] + '</span>');
								$('span.Cstatus').html('Record Saved');
								swalMessage("Successful", "success", "005","added", "@action");
								$("li").removeClass("indexBO");
								document.getElementById("indexBO").style.cursor = "pointer";
								 $("#unindex").addClass("isDisabled");
								 $("#indexBO").removeClass("isDisabled");
							}else if (message.trim() == "Fail"){
								swal("", "Record fail to save", "");
							}

					}); 
			 }else{
				 swalMessage("", "info", "115", null, null);
			 }
			 
			// $('.tpl option[value="0"]').attr("selected",true);
			  $('#tpl').val("0");
		}
	 });
})

</script>
</body>
</html>