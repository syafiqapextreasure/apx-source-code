<%@page import="com.ilmu.circulation.Charging.*,
				java.util.*,com.ilmu.global.*"%>
<head>

</head>
<%



String CT03DOCNO = request.getParameter("CT03DOCNO");
String GL14PATR = request.getParameter("GL14PATR");
System.out.println("CT03DOCNO"+CT03DOCNO);
System.out.println("GL14PATR"+GL14PATR);
Discharging chr= null;
List<Circulation> details = null;

System.out.println("CT03DOCNO"+CT03DOCNO);

chr= new Discharging();
details=Circulation.viewtableacc(GL14PATR, CT03DOCNO);



%>

					<table class="table table-bordered" id="table-charging">	
						<thead>
							<tr>
								<th data-sortable="true">Accession No.</th>
								<th data-sortable="true">Title</th>
								<th data-sortable="true">Charge Date</th>
								<th data-sortable="true">Charge Time</th>
								<th data-sortable="true">Due Date</th>
								<th data-sortable="true">Due Time</th>
								<th data-sortable="true">Status</th>
								<th data-sortable="true">Late By</th>
								<th data-sortable="true">Fines</th>
								
							</tr>
							<tr><td colspan="9"><%=chr.getPrintMessage()%></td></tr>
						</thead>
						<tbody>
						<%
						for(Circulation i: details){
						%>
						<tr>
							<td>
								<%=i.getMsDocNo() %>
							</td>
							<td>
								<%=i.getMsBookTitle() %>
							</td>
							<td>
						
								<%=GeneralUtility.StrToDate(i.getMsCircDate()) %>
							</td>
							<td>
								<%=DateTime.Time(i.getMsCircTime()) %>
							</td>
							<td>
								<%=GeneralUtility.StrToDate(i.getMsDocDate()) %>
							</td>
							<td>
								<%=DateTime.Time(i.getMsDocTime()) %>
							</td>
							<td>
								<%=i.getMsCircStatus() %>
							</td>
							<td>
								<%=i.getLateBy()%>
							</td>
							<td>
								<%=i.getMsFinesByItem()%>
							</td>
						</tr>
						<%
						}
						%>
						</tbody>
					</table>
