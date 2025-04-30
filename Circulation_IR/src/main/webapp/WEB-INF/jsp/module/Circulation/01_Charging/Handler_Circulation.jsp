<%@page import="com.ilmu.circulation.Charging.*,
				java.util.*,com.ilmu.global.*"%>

<%

Charging chr= null;
List<Circulation> details = null;
String CT03DOCNO = request.getParameter("CT03DOCNO");
String GL14PATR = request.getParameter("GL14PATR");

if(GL14PATR=="")
{
	out.println("033");
}
else if(CT03DOCNO=="")
{
	out.println("020");
}


chr= new Charging();
//chr.charge(CT03DOCNO,GL14PATR);
chr.getErrMessage();
String errmessage=chr.getErrMessage();

if(errmessage.equals("OK")){
	out.println("ok");
}else{
	out.println("fail");
}
//System.out.println(errmessage);
/* if ( errmessage != "") { */
	//out.print(errmessage);
/* }else{
	System.out.println("In2");
details=Circulation.viewtable(GL14PATR, "C"); */



%>

<%-- 

       <table class="table table-bordered" id="table-charging">	
			<thead>
				<tr>
					<th data-sortable="true">Accession No</th>
					<th data-sortable="true">Title</th>
					<th data-sortable="true">Charge Date</th>
					<th data-sortable="true">Charge Time</th>
					<th data-sortable="true">Due Date</th>
					<th data-sortable="true">Due Time</th>
					<th data-sortable="true">Status</th>
					<th data-sortable="true">Late By</th>
					<th data-sortable="true">Fines</th>
				</tr>
			
						<tr><td colspan="9"><%="ErrorCode "+chr.getErrMessage()+":"+chr.getPrintMessage()%></td></tr>
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
							<td data-sortable="true">
						
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
				
					</table> --%>

<%-- <%
}
%> --%>
