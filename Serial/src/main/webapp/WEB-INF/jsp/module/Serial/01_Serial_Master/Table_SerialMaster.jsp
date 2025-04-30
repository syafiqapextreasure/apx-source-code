<%@ page contentType="text/html; charset=UTF-8" %>
	<%@ page import="com.ilmu.global.connection.*, com.ilmu.serial.serial_master.*, java.util.List"%>
	<%
		String controlNoInput = request.getParameter("controlNoInput");
		
		List<Serial_Master> searchResult = null;
		
		searchResult = Serial_Master.SE01_SearchByMatno(controlNoInput);
		System.out.println("SS" + searchResult);
		if (searchResult.isEmpty()) {
			 out.println("Empty");
			}else{
	%>
 <script type="text/javascript">	

$(document).ready(function() {
	$('#tableSerial').DataTable({
	    responsive: true
	});
 });
</script>
	
	
	<table class="table table-bordered" id="tableSerial" style="font-size:10pt">
						<thead>
								<tr>
									<th>Control No.</th>
									<th>Title</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody id="serialdata">
			<% 
				
				for(Serial_Master i: searchResult)
				{
					String deleteFunc = "deleteRecord(&quot;" +i.getControlNo() + "&quot;)";
					out.println("<tr>");
					out.println("<td>" + i.getControlNo() + "</td>");
					out.println("<td>" + i.getTitle() + "</td>");
					out.println("<td><a type='button' title='View' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#viewPeriodicalsMaster' href='Modal_ViewSerialMaster?controlNo="+i.getControlNo()+"'><span class='glyphicon glyphicon-eye-open'></span></a>");
					out.println("<a type='button' title='Update' class='btn-info btn-sm' data-toggle='modal' data-target='#editPeriodicalsMaster' href='Modal_EditSerialMaster?controlNo="+i.getControlNo()+"'><span class='glyphicon glyphicon-pencil'></span></a>");
					out.println("<a type='button' title='Delete' class='btn btn-dull btn-sm' onclick=\'"+deleteFunc+"\'><span class='glyphicon glyphicon-trash'></span></a>");
				}
			%>
		</tbody>
	</table>
	<%
			}
	%>
	