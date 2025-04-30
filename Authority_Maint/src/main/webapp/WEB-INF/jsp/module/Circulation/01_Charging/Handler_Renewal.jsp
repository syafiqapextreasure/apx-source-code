<%@page import="com.ilmu.circulation.Charging.*,
				java.util.*,com.ilmu.global.*"%>
<%



String CT03DOCNO = request.getParameter("CT03DOCNO");
String GL14PATR = request.getParameter("GL14PATR");
String Rdate = request.getParameter("Rdate");
String Rtime = request.getParameter("Rtime");
String username = (String)session.getAttribute("username");
System.out.println("CT03DOCNO"+CT03DOCNO);
System.out.println("GL14PATR"+GL14PATR);
/* if(GL14PATR=="")
{
	out.println("033");
}
else if(CT03DOCNO=="")
{
	out.println("020");
} */

Renewal rnw= null;
List<Circulation> details = null;
System.out.println("CT03DOCNO"+CT03DOCNO);
rnw= new Renewal();

boolean renewalfull=false;
renewalfull=rnw.renew(CT03DOCNO,GL14PATR, Rdate, Rtime, username);
System.out.println("R" + renewalfull);

String errmessage=rnw.getErrMessage();
System.out.println(errmessage);
if ( errmessage != null && !errmessage.isEmpty()) {
	System.out.println(errmessage);
	out.println(errmessage);
	System.out.println(errmessage);
}else{ 
System.out.println("acceess");
System.out.println("Renew" + rnw.getmsRawDateDue());
out.println(GeneralUtility.StrToDate(rnw.getmsRawDateDue())+ "," +GeneralUtility.StrToTime2(rnw.getmsRawTimeDue()));
/* details=Circulation.viewtable(GL14PATR, "C");
out.println(Circulation.g)
for(Circulation i: details){
out.println(GeneralUtility.StrToDate(i.getMsDocDate())+ "," + i.getMsDocTime()); */

%>
<%-- 


<table>


   


</table>

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
						
							</tr>
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
						</tr>
						<%
						}
						%>
						</tbody>
					</table> --%>
<%}//}%>

