	<%@page import="com.ilmu.circulation.Charging.*,
				java.util.*,com.ilmu.global.*"%>
	 <%
	 		String GL14PATR = request.getParameter("GL14PATR");
	 		String FLAG = request.getParameter("FLAG");
			List<Patron> reqStat = null;
	  		reqStat = Patron.reqStat(GL14PATR, FLAG);
	%>
	<table class="table table-bordered table-condensed" style="font-size:10pt">
         				 <thead>
					      <tr>
					        <th>Request Date</th>
					        <th>Request No</th>
					        <th>Title</th>
					        <th>Request Status</th>
					      </tr>
					    </thead>
					    <tbody>
         				<%
         					for (Patron i: reqStat) {
         				%>
         				<tr>
         					<td><%=i.getREQSTATDATE() %></td>
         					<td><%=i.getREQSTATNO() %></td>
         					<td><%=i.getREQSTATTITLE() %></td>
         					<td><%=i.getREQSTAT() %></td>
         				</tr>
       
         				<%
         					}
         				%>
         				</tbody>
         			</table>