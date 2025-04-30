<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/themes/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/BO.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/BO.js"></script>	

<script>
//save BO record 
$('#addBORcrd').click(function(){
	var CT04MATNO ="";
	var matno ="";
	var createby = "";
	var createdate = "";
	var cretime = "";
	var exist = true;
	var values = [];
	var indi1 = [];
	var indi2 = [];
	var raw = [];
	var manda = [];
	
	
	 $('#BibRcrd').find('tbody').each(function () {
		 
		 $('#rcrddetails tbody').each(function(index){
			matno = $(this).find("span.Cmatno").text().trim();
			createby = $(this).find("span.Cuser").text().trim();
			createdate = $(this).find("span.Cdate").text().trim();
			creatime = $(this).find("span.Ctime").text().trim();
			modiby = $(this).find("span.Muser").text().trim();
			modidate = $(this).find("span.Mdate").text().trim();
			moditime = $(this).find("span.Mtime").text().trim();
		});
		 
		 var CT04TAG = $(this).find("td.tagValue").text();
		 var CT04INDI1  = ($(this).find("input.indiValue1").val());
		 var CT04INDI2  = ($(this).find("input.indiValue2").val());
		 var CT04RAW = $(this).find("td.subfield").text();
		 indi1.push(CT04INDI1);
		 indi2.push(CT04INDI2);
		 values.push(CT04TAG.trim());
		 raw.push(CT04RAW);

		 if($(this).find("td.subfield").data("manda")=="Y" && ($(this).find("td.subfield").text()).substring(2,3)=="|" || 
				 $(this).find("td.subfield").data("manda")=="Y" && ($(this).find("td.subfield").text()).substring(2,3)==""){

			 manda.push($(this).find("td.tagValue").text());
			 alert($(this).find("td.tagValue").text());
		 }
	 });
	 alert(manda.length);
	 if(manda.length<=0){
		
			$.get("Handler_AddBO",{matno:matno, tag:values,indi1:indi1,
					indi2:indi2,rawdata:raw, total:raw.length, createby:createby, creatime:creatime,
					createdate:createdate, modiby:modiby, modidate:modidate, moditime:moditime},
					function(message){
					alert("Save" + message);
					if(message.trim().includes('No_MandatoryTag')){
						var mandaTag = message.trim().split(',');
						alertMessage("", "info", "011", mandaTag[1], "@tag");
					}else if (message.trim().includes("Successful")){
						var bufferno = message.trim().split(',');
						$("span.Muser").html($("#newUser").val());
						$("span.newModitime").html($("span.Mtime").text());
						$("span.newModidate").html($("span.Mdate").text());
						$("li").removeClass("modifyBO");
		 			 	$('#modify').attr('onClick','modifyRcrd()');
			   			$(".delete, .btn-raised, .subfButton").attr("disabled","disabled");
						$('span.Cmatno').html("");
						$('#tpl option[value="0"]').attr("selected",true);
						$('span.Cmatno').append(bufferno[1]);
						$('span.Cstatus').html('Record Saved');
						alertMessage("Successful", "success", "005","added", "@action");
						$("li").removeClass("indexBO");
						document.getElementById("indexBO").style.cursor = "pointer";
					}else if (message.trim() == "Fail"){
						swal("", "Record fail to save", "");
					}

			});
	 }else{
		 alertMessage("", "info", "109", null, null);
	 }
	 
	 $('#tpl option[value="0"]').attr("selected",true);
})


//Original indexing
function indexing(matno,values,indi1,indi2,raw,total,createby,creatime,
		createdate, modiby,modidate, moditime, action){
	alert("dd");
alert(action + "Action");
	$.get("Handler_Indexing",{matno:matno, tag:values,indi1:indi1,
		indi2:indi2,rawdata:raw, total:total, createby:createby, creatime:creatime,
		createdate:createdate, modiby:modiby, modidate:modidate, moditime:moditime, action:action},
			function(message){
				
				if(message.trim().includes('No_MandatoryTag')){
					var mandaTag = message.trim().split(',');
					alertMessage("", "info", "011", mandaTag[1], "@tag");
				}else if (message.trim().includes("Successful")){
					var bufferno = message.trim().split(',');
					$("span.Muser").html($("#newUser").val());
					$("span.newModitime").html($("span.Mtime").text());
					$("span.newModidate").html($("span.Mdate").text());
					$("li").removeClass("unindexBO");
					$(".delete, .btn-raised, .subfButton").attr("disabled","disabled");
			  		$('span.Cstatus').append('Record Indexed');
					$('td.orimatno').html("");
			  		$('td.orimatno').html('Control No:<span class="Cmatno">'+bufferno[1] + '</span>');
			  		alertMessage("Successful", "success", "007", null, null); 
				}else if (message.trim() == "Fail"){
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
										createdate, modiby,modidate, moditime, "skipDuplicateChk");
						},function(dismiss) {
						  if (dismiss === 'cancel') {
								//next = false;
						  }
						})
				}
	}); 
}

//Check duplicate record before indexing
$("a#indexBO").click(function() {
	$('span.Cstatus').html("");

	var matno ="";
	var createby = "";
	var createdate = "";
	var creatime = "";
	var CT04MATNO = "";
	var indi1 = [];
	var indi2 = [];
	var values = [];
	var raw = [];
	var manda = [];
	$('#BibRcrd tbody').each(function(index){
		
		$('#rcrddetails tbody').each(function(index){
			createby = $(this).find("span.Cuser").text().trim();
			createdate = $(this).find("span.Cdate").text().trim();
			creatime = $(this).find("span.Ctime").text().trim();
			modiby = $(this).find("span.Muser").text().trim();
			modidate = $(this).find("span.Mdate").text().trim();
			moditime = $(this).find("span.Mtime").text().trim();
			matno = $(this).find("span.Cmatno").text().trim();
		});
		  
		  var tag = $(this).find("td.tagValue").text().trim();
		  var indis1 = $(this).find("input.indiValue1").val().trim();
		  var indis2 = $(this).find("input.indiValue2").val().trim();
		  var rawdata;
	
		  if(tag=="000"){
			  rawdata= $(this).find("td.subfield").text();
		  }else{
			  rawdata=$(this).find("td.subfield").text().trim();
		  }
		  
		  
		 
		 indi1.push(indis1);
		 indi2.push(indis2);
		 values.push(tag.trim());
		 raw.push(rawdata);

		 if($(this).find("td.subfield").data("manda")=="Y" && ($(this).find("td.subfield").text()).substring(2,3)=="|" || 
				 $(this).find("td.subfield").data("manda")=="Y" && ($(this).find("td.subfield").text()).substring(2,3)==""){

			 manda.push($(this).find("td.tagValue").text());
		 }
		
});

	if(manda.length<=0){
				 
		 indexing(matno,values,indi1,indi2,raw,raw.length,createby,creatime,
					createdate, modiby,modidate, moditime,"DuplicateChk");
	
	}else{
		 alertMessage("", "info", "109", null, null);
	 }
	
	    $('#tpl option[value="0"]').attr("selected",true);
	
});

//Unindex record
$('#unindex').click(function() {

	  $("#addTag").removeAttr("disabled");
	  alert("unindex");
	  var bufferno ="";
	  var matno ="";
	  var creteby = "";
	  var createdate = "";
	  var creatime = "";
	  var modiby = "";
	  var modidate = "";
	  var moditime = "";
	  var values = [];
	  var raw = [];
	  var indi1 = [];
	  var indi2 = [];
	  var matno = $("span.Cmatno").text();

	  $('#BibRcrd').find('tbody').each(function(index){
			$('#rcrddetails tbody').each(function(index){
				matno = $(this).find("span.Cmatno").text().trim();
				bufferni = $(this).find("span.Cmatno").text().trim();
				createby = $(this).find("span.Cuser").text().trim();
				createdate = $(this).find("span.Cdate").text().trim();
				creatime = $(this).find("span.Ctime").text().trim();
				modiby = $(this).find("#newUser").val().trim();
				modidate = $(this).find("span.Mdate").text().trim();
				moditime = $(this).find("span.Mtime").text().trim();

			});
			  
			  var tag = $(this).find("td.tagValue").text();
			  var indis1 = ($(this).find("input.indiValue1").val());
			  var indis2 = ($(this).find("input.indiValue2").val());
			  var rawdata = $(this).find("td.subfield").text();

			  values.push(tag);
			  indi1.push(indis1);
			  indi2.push(indis2);
			  raw.push(rawdata);
			  
	  });
	  
	  $.get("Handler_Unindex",{matno:matno,tag:values,indi1:indi1,
			indi2:indi2,raw:raw,createby:createby,createdate:createdate,creatime:creatime,
			modiby:modiby,modidate:modidate,moditime:moditime, total:raw.length, action:"Unindex"},
			function(message){
				alert(message);
				if(message.trim().includes('No_MandatoryTag')){
					var mandaTag = message.trim().split(',');
					alertMessage("", "info", "011", mandaTag[1], "@tag");
				}else if (message.trim().includes("Successful")){
					var bufferno = message.trim().split(',');
						$("span.Muser").html($("#newUser").val());
						$("span.newModitime").html($("span.Mtime").text());
						$("span.newModidate").html($("span.Mdate").text());
					  $("td.orimatno").html("Buffer No.: <span class='Cmatno'>" + bufferno[1] + "</span>");
					  $('span.Cstatus').html('');
					  $('span.Cstatus').append('Record Unindexed');
					  $("li").removeClass("saveBO");
					  $("li").removeClass("indexBO");
					  $("li").removeClass("saveIndexBO");
					  $("li").removeClass("deleteBO");
					  $("li").removeClass("addTagBO");
					  $('#BibRcrd').find('textarea, button, select, a').removeAttr("disabled")
					  alertMessage("Successful", "success", "014", null, null);
					  $('#tpl option[value="0"]').attr("selected",true);
				}else if (message.trim() == "Fail"){
					swal("", "Record fail to unindex", "");
				}
	});
});

			
//Save and index
//Save and index
$('#saveindex').click(function() {
	alert("SaveIndex1");
	$('span.Cstatus').html("");
	var CT04MATNO ="";
	var matno ="";
	var createby = "";
	var createdate = "";
	var cretime = "";
	var exist = true;
	var values = [];
	var indi1 = [];
	var indi2 = [];
	var raw = [];
	var manda = [];
	var indexRaw = [];
	var done = false;
	
 $('#BibRcrd').find('tbody').each(function () {
		 
		 $('#rcrddetails tbody').each(function(index){
			matno = $(this).find("span.Cmatno").text().trim();
			createby = $(this).find("span.Cuser").text().trim();
			createdate = $(this).find("span.Cdate").text().trim();
			creatime = $(this).find("span.Ctime").text().trim();
			modiby = $(this).find("span.Muser").val().trim();
			modidate = $(this).find("span.Mdate").text().trim();
			moditime = $(this).find("span.Mtime").text().trim();
		});
		 
		 var CT04TAG = $(this).find("td.tagValue").text();
		 var CT04INDI1  = ($(this).find("input.indiValue1").val());
		 var CT04INDI2  = ($(this).find("input.indiValue2").val());
		 var CT04RAW = $(this).find("td.subfield").text();
		 
		 if(CT04TAG=="000"){
			  rawdata= $(this).find("td.subfield").text();
		  }else{
			  rawdata=$(this).find("td.subfield").text().trim();
		  }
		  
		 
		 indexRaw.push(rawdata);
		 indi1.push(CT04INDI1);
		 indi2.push(CT04INDI2);
		 values.push(CT04TAG.trim());
		 raw.push(CT04RAW);

		 if($(this).find("td.subfield").data("manda")=="Y" && ($(this).find("td.subfield").text()).substring(2,3)=="|" || 
				 $(this).find("td.subfield").data("manda")=="Y" && ($(this).find("td.subfield").text()).substring(2,3)==""){

			 manda.push($(this).find("td.tagValue").text());
		 }
	 });
 
 if(manda.length<=0){
	
		$.get("Handler_AddBO",{matno:matno, tag:values,indi1:indi1,
				indi2:indi2,rawdata:raw, total:raw.length, createby:createby, creatime:creatime,
				createdate:createdate, modiby:modiby, modidate:modidate, moditime:moditime},
				function(message){
					alert("SaveIndex2" + message);
					if(message.trim().includes('No_MandatoryTag')){
						var mandaTag = message.trim().split(',');
						alertMessage("", "info", "011", mandaTag[1], "@tag");
					}else if (message.trim().includes("Successful")){
						var bufferno = message.trim().split(',');
	
						 indexing(bufferno[1], values,indi1,indi2,indexRaw, indexRaw.length, createby, 
									creatime,createdate, modiby, modidate, moditime, "DuplicateChk");
					}else if (message.trim() == "Fail"){
						swal("", "Record fail to save", "");
					}
		});
		
}else{
	alertMessage("", "info", "109", null, null);
}
 $(".delete, .btn-raised, .btn btn-info btn-sm subfButton").prop("disabled",true);
 $('#tpl option[value="0"]').attr("selected",true);
});
 
//Delete BO 
$("a#delete").click(function() {
	var CT02MATNO = "";
	$('#rcrddetails tbody').each(function(index){
		CT02MATNO = $(this).find("span.Cmatno").text();
	});	 
	
	$.get("Handler_DeleteBO",{CT02MATNO:CT02MATNO,action:"checkRecord"},
			function(record_exist){
	alert(record_exist);
	if(record_exist.trim()=="approve"){
		swal({
			  title: 'Are you sure '+CT02MATNO+'?',
			  text: "You won't be able to revert this!",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, delete it!'
			}).then(function() {
				$.get("Handler_DeleteBO",{CT02MATNO:CT02MATNO,action:"confirmDelete"},
						function(confirmation){
					if(confirmation.trim()=="ok"){
						  swal(
								    'Deleted!',
								    'Your file has been deleted.',
								    'success'
								  );
						  $(".Cstatus").html('Deleted');
					}
				});
			})
	}else{
		 alertMessage("", "info", "015", CT02MATNO, '@matno');
	}
 });
});
 
 
</script>

<%
	String controlNoInput = request.getParameter("matno");
	String userName = (String) session.getAttribute("username");
	BO_Record CTMATM = BO_Record.getMatm(controlNoInput);
	List<BO_Record> CT02LEADER = BO_Record.getLeader(controlNoInput);
	List<BO_Record> BORecord = BO_Record.loadDefaultTpl();
%>
<div id='BOdetails'>
<table id='rcrddetails' class="table-hover table-condensed table-fixed" data-url="data1.json" data-height="299" data-sort-name="name" data-sort-order="desc" style="border-collapse:collapse;color:black;width:100%">
<%
%>
	<tr>
		<td class="creby">Created By: <span class='Cuser'><%=CTMATM.getCT02CREBY()%> <%-- <%=userName %>--%></span></td>
	    <td class="credate">Created Date: <span class='Cdate'><%=CTMATM.getCT02CREDATE()%></span></td>
	    <td class="cretime">Created Time: <span class='Ctime'><%=CTMATM.getCT02CRTIME()%></span></td>
	</tr>
	<tr>
	 	<td class="modiby">Modified By: <span class='Muser'><%=CTMATM.getCT02IDXBY() %></span><input type='hidden' id='newUser' value='<%=userName%> '></td>
	 	 <td>Modified Date: <label><span class="newModidate"><%=CTMATM.getCT02IDXDATE()%></span></label></td>
	    <td>Modified Time: <label><span class="newModitime"><%=CTMATM.getCT02IDXTIME()%></span></label></td>
	    <td style="display:none" class="modidate">Modified Date: <span class='Mdate'><%=DateTime.getTodayDate()%></span></td>
	    <td style="display:none" class="moditime">Modified Time: <span class='Mtime'><%=DateTime.getTodayTime()%></span></td>
	</tr>
	<tr>
	 <td class="orimatno">Control No: <span class='Cmatno'><%= CTMATM.getCT02MATNO()%></span></td>
	 <td class="actionstatus">Status: <span class='Cstatus'><%=CTMATM.getCT02STATUS()%></span></td>
	 <td class="type">Type:<span class='Ctype'><%=CTMATM.getCT02TYPE() %></span></td>
	</tr>
</table>
</div>
<%
	int inc = 0;
	
	
	out.println("<table class='table table-condensed table-fixed' id='header' style='border-collapse:collapse;color:black;width:100%'><thead>");
	out.println("<col width='5%'><col width='5%'><col width='5%'>");
	out.println("<tr class='titleTr'><th>Tag</th><th>Indi1</th><th>Indi2</th><th>Data</th>");
	out.println("<th><div class='btn-group pull-right'><button type='button' data-toggle='dropdown'");
	out.println(" class='btn btn-default dropdown-toggle'>Action <span class='caret'></span></button>");
	out.println("<ul class='dropdown-menu menu'>");
	out.println("<li class='duplicate'><a id='duplicateCopy' class='disableDuplicate' style='cursor:pointer'>Duplicate Copy</a></li>");
	out.println("<li class='addTagBO'><a id='addTag' data-toggle='modal' data-target='#addNewRecord'>Add Tag</a></li>");
	out.println("<li class='searchAccNo'><a id='searchAccNo' href='#'>Accession No</a></li>");
	out.println("<li class='divider'></li>");
	out.println("<li class='saveBO'><a id='addBORcrd' href='#' disabled>Save</a></li>");
	out.println("<li class='indexBO'><a id='indexBO' href='#' disabled>Index</a></li>");
	out.println("<li class='saveIndexBO'><a id='saveindex' href='#'disabled >Save and Index</a></li>");
	out.println("<li class='divider'></li>");
	out.println("<li  class='modifyBO'><a id='modify' href='#'>Modify Record</a></li>");
	out.println("<li  class='unindexBO'><a id='unindex' href='#' class='disableUnindex'>Unindex and Modify</a></li>");
	out.println("<li class='deleteBO'><a id='delete' href='#' class='disableDelete'>Delete</a></li>");
	out.println("</ul></div></th></th></tr>");
	out.println("</thead></table>");
%>
<div class="displayBO" style="min-height:10%;max-height:80%;display:block;overflow-y:scroll">
<table id="BibRcrd" class="table table-hover table-condensed table-striped table-fixed BibRcrd" data-url="data1.json" data-height="299" data-sort-name="name" data-sort-order="desc" style="border-collapse:collapse;color:black">
<col width='5%'><col width='5%'><col width='5%'>
<%request.setCharacterEncoding("UTF-8"); %>
	<%
	
		List<BO_Record> tableName = null;
		List<BO_Record> searchResult = null;
		List<String> tags = new ArrayList<String>();
		
		tableName = BO_Record.getBORecord(controlNoInput);
		
	
			for(BO_Record i: CT02LEADER)
			{System.out.println("Leader" + i.getCT02LEADER() + "ss");
				if( !(i.getCT02LEADER()).trim().isEmpty()){
					tags.add("000");
					out.println("<tbody>");
					out.println("<tr><td style='display:none'><input type='hidden' class='counter' value='+counter.getGL98VALUE()+'><input type='hidden' class='matno' value='"+CTMATM.getCT02MATNO()+"'></td><td class='tagValue'>000</td><td><input style='text-align:center;display:none' size='1' maxlength='1' type='text' class='indiValue1' data-tag='000' data-indilvl='1' value='#'></td><td><input style='text-align:center;display:none' size='1' maxlength='1' type='text' class='indiValue2' data-indilvl='2' data-tag='000' onkeyup='updateIndi(this)' value='#'></td><td class='tagdesc'>LEADER</td>");
					out.println("<td><a class='btn btn-dull btn-sm delete'title='Delete Field'><span class='glyphicon glyphicon-trash'></span></a></td><td></td></tr>");
					out.println("<tr><td></td><td colspan='3' style='max-width:700px;word-wrap:break-word;' class='subfield' id='tag_000"+inc+"' >" + i.getCT02LEADER() + "</td>");
					out.println("<td><a data-toggle='modal' href='#ctrlTagsForm' onclick='ctrlTags(this)' class='btn btn-circle btn-raised btn-primary btn-sm' title='Add Control Fields'  data-id='000"+inc+"' value='000'><span class='glyphicon glyphicon-edit'></span></a></td></tr>");
					out.println("</tbody>");
				}
			
			}

		for(BO_Record j: tableName)
		{
			if(!(j.getCT06TAG()).equals("000")){

			searchResult = BO_Record.getAllBy(controlNoInput, j.getCT06TAG(),j.getGL17TABNAME(), j.getCT06POINTER());
			for(BO_Record i: searchResult)
			{
				tags.add(i.getCT04TAG());
				String authlink = null;
				authlink = BO_Record.authorityLink(j.getCT06TAG());
				/* Glnumb counter = Glnumb.getGL98VALUE("BUFPOINT"); */
				out.println("<tbody>");
				out.println("<tr><td style='display:none'><input type='hidden' class='counter' value='+counter.getGL98VALUE()+'><input type='hidden' class='matno' value='"+CTMATM.getCT02MATNO()+"'></td><td class='tagValue'>"+ i.getCT04TAG() +"</td>");
				if(i.getCT04TAG().equals("000")||i.getCT04TAG().equals("001")||i.getCT04TAG().equals("002")||i.getCT04TAG().equals("003")){
					out.println("<td><input style='text-align:center;display:none' size='1' maxlength='1' type='text' class='indiValue1' data-tag='"+i.getCT04TAG()+"' data-indilvl='1' onkeyup='updateIndi(this)' id='indi1_NULL"+inc+"' value='#' disabled></td><td><input style='text-align:center;display:none' size='1' maxlength='1' type='text' class='indiValue2' data-indilvl='2' data-tag='"+i.getCT04TAG()+"' onkeyup='updateIndi(this)' id='indi2_NULL"+inc+"' value='#' disabled></td>");
				}else{
					out.println("<td><input style='text-align:center' size='1' maxlength='1' type='text' class='indiValue1' data-tag='"+i.getCT04TAG()+"' data-indilvl='1' onkeyup='updateIndi(this)' id='indi1_"+i.getCT04INDI1()+inc+"' value='" + i.getCT04INDI1() + "' disabled></td><td><input style='text-align:center' size='1' maxlength='1' type='text' class='indiValue2' data-indilvl='2' data-tag='"+i.getCT04TAG()+"' onkeyup='updateIndi(this)' id='indi2_"+i.getCT04INDI2()+inc+"' value='" + i.getCT04INDI2() + "' disabled></td>");
				}
				out.println("<td class='tagdesc'>"+i.getGL17DESC() +"</td>");
				out.println("<td class='creby' hidden>"+ CTMATM.getCT02CREBY() +"</td>");
				out.println("<td class='credate' hidden>"+ CTMATM.getCT02CREDATE() +"</td>");
				out.println("<td class='cretime' hidden>"+ CTMATM.getCT02CRTIME() +"</td>");
				out.println("<td class='status' hidden>"+ CTMATM.getCT02STATUS() +"</td>");
				out.println("<td><a class='btn btn-dull btn-sm delete' title='Delete Field'><span class='glyphicon glyphicon-trash'></span></a></td><td></td></tr>");
				out.println("<tr><td></td><td colspan='3' style='max-width:700px;word-wrap:break-word;' class='subfield' id='tag_"+i.getCT04TAG()+inc+"' data-manda='"+j.getGL17MANDA()+"'>" + i.getCT04RAW () + "</td>");
				if(i.getCT04TAG().equals("000")||i.getCT04TAG().equals("006")||i.getCT04TAG().equals("007")||i.getCT04TAG().equals("008")){
					out.println("<td><a data-toggle='modal' href='#ctrlTagsForm' onclick='ctrlTags(this)' class='btn btn-primary btn-raised btn-sm' title='Add Control Fields' data-autflag='"+i.getGL17AUTFLAG()+"' data-id='"+i.getCT04TAG()+inc+"' value='"+i.getCT04TAG()+"'><span class='glyphicon glyphicon-edit'></span></a></td></tr>");
				}
				else{
					out.println("<td><a data-toggle='modal' data-autflag='"+authlink+"' data-id='"+i.getCT04TAG()+inc+"' data-target='#viewSubfields'  data-indiid1='"+i.getCT04INDI1()+inc+"' data-indiid2='"+i.getCT04INDI2()+inc+"' data-indi1='"+i.getCT04INDI1()+"' data-indi2='"+i.getCT04INDI2()+"' data-tagdesc='"+i.getGL17DESC()+"' data-tag='"+i.getCT04TAG()+"' data-subf='"+i.getCT04RAW ()+"' onClick='openModal(this)' class='btn btn-info btn-sm subfButton'><span class='glyphicon glyphicon-pencil' title='View Subfields'></span></a></td></tr>");
				}
				out.println("</tbody>");	
				inc++;
				System.out.println(authlink);
				
			}
			}
		}
		
		for(BO_Record i: BORecord)
		{
			if(!tags.contains(i.getTAG())){
				
					out.println("<tbody>");
					out.println("<tr><td style='display:none'><input type='hidden' class='matno' value=''></td><td class='tagValue'>"+ i.getTAG() +"</td>");
					out.println("<td><input size='1' maxlength='1' type='text' style='text-align:center' class='indiValue1' data-tag='"+i.getTAG()+"' data-indilvl='1' onkeyup='updateIndi(this)' id='indi1_"+i.getINDI1()+inc+"' value='" + i.getINDI1() + "' disabled></td><td><input size='1' maxlength='1' style='text-align:center' type='text' class='indiValue2' data-indilvl='2' data-tag='"+i.getTAG()+"' onkeyup='updateIndi(this)' id='indi2_"+i.getINDI2()+inc+"' value='" + i.getINDI2() + "' disabled></td>");
					out.println("<td class='tagdesc'>"+i.getTAGDESC() +"</td>");
					out.println("<td><a class='btn btn-dull btn-sm delete' title='Delete Field'><span class='glyphicon glyphicon-trash'></span></a></td><td></td></tr>");
					if(i.getTAG().equals("000")||i.getTAG().equals("006")||i.getTAG().equals("007")||i.getTAG().equals("008")||
							i.getTAG().equals("001")||i.getTAG().equals("002")||i.getTAG().equals("003")||i.getTAG().equals("004")||
							i.getTAG().equals("005")||i.getTAG().equals("009")||i.getTAG().equals("010")){
					out.println("<tr><td></td><td colspan='3' style='max-width:700px;word-wrap:break-word;' class='subfield mandatory' id='tag_"+i.getTAG()+inc+"' data-manda='"+i.getGL17MANDA()+"'></td>");
					}else{
						out.println("<tr><td></td><td colspan='3' style='max-width:700px;word-wrap:break-word;' class='subfield' id='tag_"+i.getTAG()+inc+"' data-manda='"+i.getGL17MANDA()+"'>" + i.getCTTPLSUBF () + "</td>");
					}
					if(i.getTAG().equals("000")||i.getTAG().equals("006")||i.getTAG().equals("007")||i.getTAG().equals("008")){
						out.println("<td><a data-toggle='modal' onclick='ctrlTags(this)' data-target='#ctrlTagsForm' class='btn btn-primary btn-raised btn-sm' title='Add Control Fields' data-indiid1='"+i.getINDI1()+"' data-indiid2='"+i.getINDI2()+"' data-ctmstr='"+i.getCT16MSTR()+"' data-indi1='"+i.getINDI1()+inc+"' data-indi2='"+i.getINDI2()+inc+"' data-autflag='"+i.getGL17AUTFLAG()+"' data-tagdesc='"+i.getTAGDESC()+" 'data-tag='"+i.getTAG()+"' data-subf='"+i.getCTTPLSUBF ()+"' data-id='"+i.getTAG()+inc+"' value='"+i.getTAG()+"'><span class='glyphicon glyphicon-edit'></span></a></td></tr>"); 
					}else if(i.getTAG().equals("001")||i.getTAG().equals("002")||i.getTAG().equals("003")||i.getTAG().equals("004")||
							i.getTAG().equals("005")||i.getTAG().equals("009")||i.getTAG().equals("010")){
						out.println("<td><a data-toggle='modal' data-indiid1='"+i.getINDI1()+inc+"' data-indiid2='"+i.getINDI2()+inc+"' data-id='"+i.getTAG()+inc+"' data-ctmstr='"+i.getCT16MSTR()+"' data-target='#viewSubfields' data-indi1='"+i.getINDI1()+"' data-indi2='"+i.getINDI2()+"' data-tagdesc='"+i.getTAGDESC()+"' data-tag='"+i.getTAG()+"' data-subf='"+i.getCTTPLSUBF ()+"' onClick='openCtrlField(this)' class='btn btn-info btn-sm subfButton' ><span class='glyphicon glyphicon-pencil' title='View Subfields'></span></a></td></tr>");
					}
					else{
						out.println("<td><a data-toggle='modal' data-autflag='"+i.getGL17AUTFLAG()+"' data-indiid1='"+i.getINDI1()+inc+"' data-indiid2='"+i.getINDI2()+inc+"' data-id='"+i.getTAG()+inc+"' data-ctmstr='"+i.getCT16MSTR()+"' data-target='#viewSubfields' data-indi1='"+i.getINDI1()+"' data-indi2='"+i.getINDI2()+"' data-tagdesc='"+i.getTAGDESC()+"' data-tag='"+i.getTAG()+"' data-subf='"+i.getCTTPLSUBF ()+"' onClick='openModal(this)' class='btn btn-info btn-sm subfButton' ><span class='glyphicon glyphicon-pencil' title='View Subfields'></span></a></td></tr>");
					}
					out.println("</tbody>");	
					inc++;
				}
			}
	
%>

</table> 
</div>

 	<div class="modal fade" style="z-index:1050" id="viewSubfields" tabindex="-1" role="dialog" aria-labelledby="viewSubfields" data-backdrop="static">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content">
			  <div id="subfList"></div>
			  </div>
		</div>
	</div>
	
	<div class="modal fade" style="z-index:1050" id="ctrlTagsForm" tabindex="-1" role="dialog" aria-labelledby="viewSubfields" data-backdrop="static">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content">
			  <div id="ctrlTagDisplay"></div>
			  </div>
		</div>
	</div>
	
	<div class="modal fade" style="z-index:1050" id="addNewRecord" tabindex="-1" role="dialog" aria-labelledby="addNewRecord" data-backdrop="static">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content" id='modalTag'>
			  </div>
		</div>
	</div>
	
<!-- 	<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch">
		<div class="modal-dialog" role="document" style="width:900px;">
			<div class="modal-content">
			Remote content load here
			</div>
		</div>
	</div>	 -->
	
	
	
	