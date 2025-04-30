<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, com.ilmu.circulation.Weeding.*, 
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/themes/style.css"/><%-- 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script> --%>
<script>
//Unindex record
$('#deleteBO').click(function() {
	alert("sdsds");
	var matno = $("span.Cmatno").text();
	var exist = true;
	$.get("Handler_BOValidation",{CT04MATNO:matno,validation:"GetCounters"},function(counters){
		if(counters.trim()=="exist"){
			alertMessage("", "info", "015", matno, '@matno');
			$("#displayBO").modal("hide");
			return false;
		}else{
	
	  $('#BibRcrd').find('tr').each(function(index){
			$('#viewBO').each(function(index){
				CTRLNO = $(this).find("span.Cmatno").text();
				CT02MATNO = $(this).find("span.Cmatno").text();
				CT02CREBY = $(this).find("span.Cuser").text();
				CT02CREDATE = $(this).find("span.Cdate").text();
				CT02CRETIME = $(this).find("span.Ctime").text();
				CT02MODIBY = $(this).find("span.Muser").text();
				CT02MODIDATE = $(this).find("span.Mdate").text();
				CT02MODITIME = $(this).find("span.Mtime").text();
			});
			  
			  var CT04TAG = $(this).find("td.tag").text();
			  var CT04INDI1 = ($(this).find("td.indi1").text());
			  var CT04INDI2 = ($(this).find("td.indi2").text());
			  var CT04RAW = $(this).find("td.raw").text();
			  if(!CT02CRETIME.trim()){
				  CT02CRETIME  = null;
			  }
				$.get("Handler_BOValidation",{validation:"GetCounter"},function(counter){
	
					$.get("Handler_Unindex",{CT02MATNO:CTRLNO,CT02TAG:CT04TAG,CT02INDI1:CT04INDI1,
											CT02INDI2:CT04INDI2,CT02RAW:CT04RAW, counter:counter.trim(),
											CT02CREBY:CT02CREBY,CT02CREDATE:CT02CREDATE,CT02CRETIME:CT02CRETIME,
											CT02MODIBY:CT02MODIBY,CT02MODIDATE:CT02MODIDATE,CT02MODITIME:CT02MODITIME},
											function(bufferno){
												var CT02MATNO = "";
												$('#viewBO').each(function(index){
													CT02MATNO = $(this).find("span.Cmatno").text();
												});	 
											 $.get("Handler_DeleteBO",{CT02MATNO:CT02MATNO,action:"checkRecord"},
														function(record_exist){
															
																if(record_exist.trim()=="ok"){
																	$('#displayBO').modal('hide');
																	  swal(
																			    'Deleted!',
																			    'Your record has been deleted.',
																			    'success'
																			  );
																	
																} else{
																	alertMessage("", "info", "015", CT02MATNO, '@matno');
																
																}

											});

					   });  

				});
	  }); 
	return true;
		}

    	
	});
});
	

</script>
<div class="modal-body">
<%
	System.out.println("In");
	List<BO_Record> CT02LEADER = null;
	List<BO_Record> BORecord = null;
	System.out.println("Accession12"+request.getParameter("CT03DOCNO"));
	System.out.println("Accession"+request.getParameter("CT03DOCNO"));
	String CT03DOCNO = request.getParameter("CT03DOCNO");
	
		String controlNoInput = Weeding.WEmatno(CT03DOCNO);
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
		<td>Modified Date: <span class='Mdate'><%=DateTime.getSysTodayDate()%></span></td>
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
<button class='btn btn-sm' id='deleteBO'>Delete</button>
<%
	}
%>
<button class='btn btn-sm' id='cancel' data-dismiss="modal">Cancel</button>
</div>


	