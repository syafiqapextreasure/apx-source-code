<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, com.ilmu.circulation.Weeding.*, 
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/themes/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>
<script>
function deleteUnindex(){

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
	  var CT02MATNO ="";
	  
	  $('#BibRcrd').find('tbody tr').each(function(index){
			$('#viewBO tbody').each(function(index){
				createby = $(this).find("span.Cuser").text().trim();
				createdate = $(this).find("span.Cdate").text().trim();
				creatime = $(this).find("span.Ctime").text().trim();
				modidate = $(this).find("span.Mdate").text().trim();
				moditime = $(this).find("span.Mtime").text().trim();

			});
			  
			  var tag = $(this).find("td.tag").text().trim();
			  var indis1 = ($(this).find("td.indi1").text().trim());
			  var indis2 = ($(this).find("td.indi2").text().trim());
			  var rawdata = $(this).find("td.raw").text().trim();

			  values.push(tag);
			  indi1.push(indis1);
			  indi2.push(indis2);
			  raw.push(rawdata);
			  
	  });
	  
	  $.get("Handler_DeleteBO",{matno:matno,action:"checkRecord"},
				function(record_exist){
		  if(record_exist.trim()=="ok"){
			  swal({
				  title: 'Are you sure '+matno+'?',
				  text: "You won't be able to revert this!",
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Yes, delete it!'
				}).then(function(){
					$.get("Handler_Unindex",{matno:matno,tag:values,indi1:indi1,
						indi2:indi2,raw:raw,createby:createby,createdate:createdate,creatime:creatime,
						modiby:modiby,modidate:modidate,moditime:moditime, total:raw.length, action:"Unindex"},
						function(message){
							if (message.trim().includes("Successful")){
								var bufferno = message.trim().split(',');
								$('#viewBO tbody').each(function(index){
									CT02MATNO = $(this).find("span.Cmatno").text();
								});
								$.get("Handler_DeleteBO",{CT02MATNO:CT02MATNO,action:"afterUnindex"},
										function(updatedD){
									if(updatedD.trim()=="ok"){
										$.get("Handler_DeleteBO",{CT02MATNO:CT02MATNO,action:"confirmDelete"},
												function(confirmation){
											if(confirmation.trim()=="ok"){
												  swal(
														    'Deleted!',
														    'Your file has been deleted.',
														    'success'
														  );
												  $("#displayBO").modal("hide");
												  $(".Cstatus").html('Deleted');
												  document.getElementById("cancel").click();
											}else if(confirmation.trim()=="failCTMATMD"){
												alertMessage('info!', 'Fail to Delete. Error code 01CT2MATM.', '');
												document.getElementById("cancel").click();
											}else if(confirmation.trim()=="failCTWORKD"){
												alertMessage('info!', 'Fail to Delete. Error code 01CT2WORK.', '');
												document.getElementById("cancel").click();
											}else if(confirmation.trim()=="failCTPONT0"){
												alertMessage('info!', 'Fail to Delete. Error code 01CT2PONT.', '');
												document.getElementById("cancel").click();
											}else{
												alertMessage('info!', 'Unexpected error MoBO89.', '');
												document.getElementById("cancel").click();
											}
										});
									}else if(updatedD.trim()=="statusNotT"){
										alertMessage('info!', 'Status is not T.', '');
										document.getElementById("cancel").click();
									}else if(updatedD.trim()=="noMatnoCtwork"){
										alertMessage('info!', 'There is no buffer.', '');
										document.getElementById("cancel").click();
									}else{
										alertMessage('info!', 'Unexpected error MoBO97.', '');
										document.getElementById("cancel").click();
									}
								});
							}else{
								alertMessage('info!', 'Unexpected error MoBO101.', '');
								document.getElementById("cancel").click();
							}
						});
				});
		  }else if(record_exist.trim()=="declineOrder"){
			  alertMessage("", "info", "175", CT02MATNO, '@matno');
			  document.getElementById("cancel").click();
		  }else{
			  alertMessage("", "info", "015", CT02MATNO, '@matno');
			  document.getElementById("cancel").click();
		  }
	  });
};


</script> 
<div class="modal-body">
<%
	System.out.println("In");
	List<BO_Record> CT02LEADER = null;
	List<BO_Record> BORecord = null;
	System.out.println("Accession12"+request.getParameter("CT03DOCNO"));
	
	String CT03DOCNO = request.getParameter("CT03DOCNO");
	
		String controlNoInput = Weeding.WEmatno(CT03DOCNO);
		System.out.println("controlNoInput:"+controlNoInput);
		CT02LEADER = BO_Record.getLeader(controlNoInput);
		Weeding CTMATM = Weeding.getCTMATM(controlNoInput);
	
%>

<div class="displayBO" style="min-height:10%;max-height:80%;display:block;overflow-y:scroll">
<table id="viewBO">
	<tr>
		<td>Created By: <span class='Cuser'><%=CTMATM.getCT02CREBY()%></span></td>
	    <td>Created Date: <span class='Cdate'><%=CTMATM.getCT02CREDATE() %></span></td>
	    <td>Created Time: <span class='Ctime'><%=CTMATM.getCT02CRTIME() %></span></td>
	    <td>&nbsp;</td>
	</tr>
	<tr>
	 	<td>Modified By: <span class='Muser'><%=CTMATM.getCT02CREBY()%></span></td>
		<td>Modified Date: <span class='Mdate'><%=DateTime.getSysTodayDate2()%></span></td>
	    <td>Modified Time: <span class='Mtime'><%=DateTime.getSysTodayTime()%></span></td> 
	</tr>
	<tr>
	 <td class="orimatno">Control No: <span class='Cmatno'><%= CTMATM.getCT02MATNO()%></span></td>
	<%--  <td class="actionstatus">Status: <span class='Cstatus'><%=CTMATM.getCT02STATUS()%></span></td>
	 <td class="type">Type:<span class='Ctype'><%=CTMATM.getCT02TYPE() %></span></td> --%>
	</tr>
</table>
<table id="BibRcrd" class="table table-hover table-condensed table-striped table-fixed BibRcrd" data-url="data1.json" data-height="299" data-sort-name="name" data-sort-order="desc" style="border-collapse:collapse;color:black">
<col width='5%'><col width='5%'><col width='5%'>
<tbody>
<%request.setCharacterEncoding("UTF-8"); %>
	<%
	
		List<BO_Record> tableName = null;
		List<BO_Record> searchResult = null;
		
		tableName = BO_Record.getBORecord(controlNoInput);
		

		for(BO_Record j: tableName)
		{

			searchResult = BO_Record.getAllBy(controlNoInput, j.getCT06TAG(),j.getGL17TABNAME(), j.getCT06POINTER());
			for(BO_Record i: searchResult)
			{
				String authlink = null;
				authlink = BO_Record.authorityLink(j.getCT06TAG());
			%>
				<tr>
					<td class='tag'><%=i.getCT04TAG() %></td>
					<td class='indi1'><%=i.getCT04INDI1() %></td>
					<td class='indi2'><%=i.getCT04INDI2() %></td>
					<td class='raw'><%=i.getCT04RAW () %></td>
				</tr>
			
			<%
			}
		}
			%>
</tbody>
</table> 
</div>
</div>
<div class="modal-footer">
<%
	String action = request.getParameter("action");
	if(action.equals("deleteBO")){
%>
<button class='btn btn-sm' id='deleteBO' onclick="deleteUnindex()">Delete</button>
<%
	}
%>
<button class='btn btn-sm' id='cancel' data-dismiss="modal">Cancel</button>
</div>


	