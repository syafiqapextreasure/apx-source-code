<%@page import="com.ilmu.circulation.Charging.*,
				java.util.*,com.ilmu.global.*"%>

<%

Charging chr= null;
List<Circulation> details = null;
System.out.println("From Charging");
String GL14PATR = request.getParameter("GL14PATR");
System.out.println(GL14PATR);
Patron patr = new Patron(GL14PATR);




%>
<div class="modal-header">
Patron's Status Enquiry 
</div>
<div class="modal-body">	
	<div id="exTab2" class="container" style="width:100%">	
			<ul class="nav nav-tabs">
				<li class="active">
	        		<a  href="#1" data-toggle="tab">Onloan Items<span class="badge"><%=patr.getMsNoCircByPatron() %></span></a>
				</li>
				<li><a href="#2" data-toggle="tab">Overdue Items<span class="badge"><%=patr.getNoMsGetItemOverdue() %></span></a>
				</li>
				<li><a href="#3" data-toggle="tab">Reserved Items<span class="badge"><%=patr.getNoMsGetItemReserved() %></span></a>
				</li>
			</ul>

			<div class="tab-content " style="height:60%;overflow-y:auto">
			  <div class="tab-pane active" id="1">
          			<%
						List<Circulation> onloan = null;
						onloan = Circulation.patronStatus(GL14PATR, "C");
					%>
         			<table class="table table-bordered table-condensed" style="font-size:10pt">
         				 <thead>
					      <tr>
					        <th>Accession No</th>
					        <th>Title</th>
					        <th>Call No</th>
					        <th>Charge Date/Time</th>
					        <th>Due Date/Time</th>
					      </tr>
					    </thead>
					    <tbody>
         				<%
         					for (Circulation i: onloan) {
         				%>
         				<tr>
         					<td><%=i.getCI02DOCNO()%></td>
         					<td><%=i.getCT05TITLE()%></td>
         					<td><%=i.getCT05CALL()%></td>
         					<td><%=i.getCI02BDATE()%>, <%=i.getCI02BTIME()%></td>
         					<td><%=i.getCI02DDATE()%>, <%=i.getCI02DTIME()%></td>
         				</tr>
         				<%
         					}
         				%>
         				</tbody>
         			</table>
				</div>
				<div class="tab-pane" id="2">
					<%
						List<Circulation> overdue = null;
						overdue = Circulation.patronStatus(GL14PATR, "O");
					%>
         			<table class="table table-bordered table-condensed" style="font-size:10pt">
         				 <thead>
					      <tr>
					        <th>Accession No</th>
					        <th>Title</th>
					        <th>Call No</th>
					        <th>Charge Date/Time</th>
					        <th>Due Date/Time</th>
					      </tr>
					    </thead>
					    <tbody>
         				<%
         					for (Circulation i: overdue) {
         				%>
         				<tr>
         					<td><%=i.getCI02DOCNO()%></td>
         					<td><%=i.getCT05TITLE()%></td>
         					<td><%=i.getCT05CALL()%></td>
         					<td><%=i.getCI02BDATE()%>, <%=i.getCI02BTIME()%></td>
         					<td><%=i.getCI02DDATE()%>, <%=i.getCI02DTIME()%></td>
         				</tr>
         				<%
         					}
         				%>
         				</tbody>
         			</table>
				</div>
		        <div class="tab-pane" id="3">
		         	 <%
						List<Circulation> reservation = null;
			         	reservation = Circulation.patronStatus(GL14PATR, "R");
					%>
         			<table class="table table-bordered table-condensed" style="font-size:10pt">
         				 <thead>
					      <tr>
					        <th>Accession No</th>
					        <th>Title</th>
					        <th>Call No</th>
					        <th>Reserved Date</th>
					        <th>Reserved Time</th>
					      </tr>
					    </thead>
					    <tbody>
         				<%
         					for (Circulation i: reservation) {
         				%>
         				<tr>
         					<td><%=i.getCI02DOCNO()%></td>
         					<td><%=i.getCT05TITLE()%></td>
         					<td><%=i.getCT05CALL()%></td>
         					<td><%=i.getCI02BDATE()%></td>
         					<td><%=i.getCI02DDATE()%></td>
         				</tr>
         				<%
         					}
         				%>
         				</tbody>
         			</table>
				</div>
			</div>
  </div>
      <%--  <table class="table table-bordered" id="table-charging">	
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
</div>