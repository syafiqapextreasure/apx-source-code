<%@ page import="com.ilmu.circulation.resv.*, com.ilmu.circulation.Charging.*" %>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>


		<%
			List<Reservation> getListResvScrutiny = null;
			String action = request.getParameter("action");
			
			if(action.equals("accession")){
				String CI03DOCNO = request.getParameter("accession");
				getListResvScrutiny = Reservation.getListResvScrutiny(CI03DOCNO);
			}else if (action.equals("days")){
				String CI02NDATE = request.getParameter("days");
				System.out.println("D"  + CI02NDATE);
				getListResvScrutiny = Reservation.getListResvScrutinyByDays(CI02NDATE);
			}
		
			int count = 1;

			for (Reservation i: getListResvScrutiny) {
		%>
		
		<tr>
			<td><%=count++ %></td>
			<td><%=i.getCI03NDATE() %></td>
			<td><%=i.getCI03PATR() %></td>
			<td><%=i.getGL14NAME() %></td>
			<td><%=i.getCT05SRAW() %></td>
			<td><%=i.getCI03PRIOR() %></td>
			<td><%=i.getCI03MATNO() %></td>
			<td><%=i.getCI03DOCNO() %></td>
			<td>
				<button type="button"onclick="deleteScrutiny('<%=i.getCI03PATR() %>, A,<%=i.getCI03MATNO() %>,<%=i.getCI03DOCNO() %>')" class="btn btn-dull btn-sm"><span class="glyphicon glyphicon-trash" title="Delete Reservation"></span></button>
			</td>
		</tr>
		<%
			}
		%>
