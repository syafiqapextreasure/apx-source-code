<%@page import="com.ilmu.circulation.Charging.*,
				java.util.*,com.ilmu.global.*"%>
				
<script>
$(document).ready(function() {
	 var table =  $('#table-lateHistory').DataTable({
	       responsive: true
	   });
});
</script>
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal">&times;</button>
Late Return History
</div>
<div class="modal-body" style="height:70%;width:auto;overflow:auto">
<table class="table table-striped" id="table-lateHistory">
    <thead>
      <tr>
        <th>Accession No</th>
        <th>Call No</th>
        <th>Title</th>
        <th>Discharged Date</th>
        <th>Due Date</th>
        <th>Due Time</th>
        <th>Discharged Time</th>
        <th>Status</th>
      </tr>
    </thead>
		<%
		System.out.println("In Late");
			String GL14PATR = request.getParameter("GL14PATR");
			List<Charging> lateReturn = null;
			lateReturn = Charging.LateReturnHistory(GL14PATR);
		%>
		<%
        	for (Charging i: lateReturn) {
        %>
        <tr>
        	<td>
        		<%=i.getCT02DOCNO() %>
        	</td>
         	<td> <%=i.getCallNo() %></td>
         	<td> <%=i.getTitle() %></td>
         	<td> <%=i.getCI02DIDATE() %></td>
         	<td> <%=i.getCI02DDATE() %></td>
         	<td> <%=i.getCI02DTIME() %></td>
         	<td> <%=i.getCI02DITIME() %></td>
         	<td> <%=i.getstatus() %></td>
         </tr>
         <%
        	}
         %>
</table>
</div>