<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<script>
function closeModal(){
	//$(".modal-backdrop.in").hide();
	$(".marcview").modal("hide");
}
</script>
<div class="modal-body">
<%
	System.out.println("In");
	List<BO_Record> CT02LEADER = null;
	List<BO_Record> BORecord = null;
	System.out.println("Accession12"+request.getParameter("ctrlno"));
	System.out.println("Accession"+request.getParameter("ctrlno"));
	String controlNoInput = request.getParameter("ctrlno");

	
%>

<div class="displayBO" style="min-height:10%;max-height:80%;display:block;overflow-y:scroll">

<table class="table table-hover table-condensed table-striped table-fixed BibRcrd" >
<col width='5%'><col width='5%'><col width='5%'>
<tbody>
<%request.setCharacterEncoding("UTF-8"); %>
	<%
	
		List<BO_Record> tableName = null;
		List<BO_Record> searchResult = null;
		
		tableName = BO_Record.getBORecord(controlNoInput);
		

		for(BO_Record j: tableName)
		{
			System.out.println("Testa" +j.getCT06TAG());
			searchResult = BO_Record.getAllBy(controlNoInput, j.getCT06TAG(),j.getGL17TABNAME(), j.getCT06POINTER());
			for(BO_Record i: searchResult)
			{
				System.out.println("Test" + i.getCT04TAG());
			%>
				<tr>
					<td class='tag'><%=i.getTAG() %></td>
					<td class='indi1'><%=i.getINDI1() %></td>
					<td class='indi2'><%=i.getINDI2() %></td>
					<td class='raw'><%=i.getCTTPLSUBF () %></td>
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
<input type="button" class='btn btn-sm closeMarc' value="Close" onclick="closeModal()">
</div>


	