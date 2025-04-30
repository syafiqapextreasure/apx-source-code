<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ilmu.circulation.Charging.*,
				java.util.*,com.ilmu.global.*"%>

<%

Charging chr= null;
List<Patron> details = null;
System.out.println("From Charging");
String GL14PATR = request.getParameter("GL14PATR");
System.out.println(GL14PATR);
Patron patr = new Patron(GL14PATR);
chr = new Charging();



%>
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal">&times;</button>
Patron's Status Enquiry 
</div>
<div class="modal-body" style="max-height:80%;min-width:50%;overflow:auto">	
	<div id="exTab2" class="container" style="width:100%">	
			<ul class="nav nav-tabs">
				<li class="active">
	        		<a  href="#1" data-toggle="tab">Other Information</a>
				</li>
				<li><a href="#2" data-toggle="tab">Related Patron</a>
				</li>
				<li><a href="#3" data-toggle="tab">Request Status</a>
				</li>
				<li><a href="#4" data-toggle="tab">Personal Information</a>
				</li>
				<li><a href="#5" data-toggle="tab">Miscellaneous</a>
				</li>
				
			</ul>

			<div class="tab-content " style="height:60%;overflow-y:auto">
			  <div class="tab-pane active" id="1">
          			<%
						List<Patron> otherinfo = null;
          			otherinfo = Patron.PatrDetails(GL14PATR);
					%>
					<table style="margin-top:40px">
					<col width="15%">	
					<col width="10%">
					<col width="15%">	
					<col width="10%">		
						<%
							chr.getPatrOtherInfo(GL14PATR);
						%>
         				<tr>
         					<td>
         						<label>Items Charged-To-Date:</label>
         					</td>
         					<td> <%=chr.getmsCharged()%>
         					</td>
         					<td>
         						<label>Last Charged Date:</label>
         					</td>
         					<td> <%=chr.getmsLastBor()%>
         					</td>
         				</tr>
         				<tr>
         					<td>
         						<label>Late Returns To Date:</label>
         					</td>
         					<td> <%=chr.getmsLateReturn() %>
         					</td>
         					<td>
         						<label>Last Return Date:</label>
         					</td>
         					<td> <%=chr.getmsLastRet()%>
         					</td>
         				</tr>
         					<tr>
         					<td>
         						<label>No. of Lost Items</label>
         					</td>
         					<td> 
         						<%=chr.getmsLostBok() %>
         					</td>
         					<td>
         						<label>Last Renew Date:</label>
         					</td>
         					<td> <%=chr.getmsRenewDate()%>
         					</td>
         				</tr>
         					<tr>
         					<td>
         						<label>No. of Suspension</label>
         					</td>
         					<td> 
         						<%=chr.getmsSuspension() %>
         					</td>
         					<td>
         						<label>Amount Outstanding:</label>
         					</td>
         					<td> <%=patr.getMsGetTotalFinesOutstanding() %>
         					</td>
         				</tr>
         					<tr>
         					<td>
         						<label>Item Charged YTD:</label>
         					</td>
         					<td> <%=chr.getmsBorrowedToYear() %>
         					</td>
         					<td>
         						<label>Amount Paid:</label>
         					</td>
         					<td> <%=patr.getMsGetTotalPaid()%>
         					</td>
         				</tr>
         					<tr>
         					<td>
         						<label>Late Returns YTD:</label>
         					</td>
         					<td> <%=chr.getmsLateReturnToYear() %>
         					</td>
         				</tr>
					 
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
					<div>
						<div class="radio">
					      <label><input type="radio" name="optradio" value="accReq" onchange="getValue()">Accession Request</label>
					    </div>
					    <div class="radio">
					      <label><input type="radio" name="optradio" value="seReq" onchange="getValue()">Serial Request</label>
					    </div>
					    <input type="hidden" id="GL14PATRS" value="<%=GL14PATR%>">
				    </div>
				    <div id="table_patrreq"></div>
         		
				</div>
				<div class="tab-pane" id="4">
					<%
						List<Patron> personalDetails = null;
						personalDetails = Patron.PatrDetails(GL14PATR);
					%>
					<table style="margin-top:40px">
					<col width="10%">					
					 	<%
         					for (Patron i: personalDetails) {
         				%>
         				<tr>
         					<td>
         						<label>Group ID</label>
         					</td>
         					<td> <label>:</label>&nbsp;<%=i.getGL02NAME() %>
         					</td>
         				</tr>
         				<tr>
         					<td>
         						<label>Status</label>
         					</td>
         					<td><label>:</label>&nbsp; <%=i.getGL08DESC() %>
         					</td>
         				</tr>
         					<tr>
         					<td>
         						<label>Date of Birth</label>
         					</td>
         					<td> <label>:</label>&nbsp;
         						<% if((i.getGL14DOB())!=null){%>
         						<%=i.getGL14DOB() %>
         						<%} %>
         					</td>
         				</tr>
         					<tr>
         					<td>
         						<label>Department</label>
         					</td>
         					<td><label>:</label>&nbsp; <%=i.getGL11NAME() %>
         					</td>
         				</tr>
         					<tr>
         					<td>
         						<label>Category</label>
         					</td>
         					<td> <label>:</label>&nbsp;<%=i.getGL07DESC() %>
         					</td>
         				</tr>
         					<tr>
         					<td>
         						<label>Gender</label>
         					</td>
         					<td> <label>:</label>&nbsp;<%=i.getGL14SEX() %>
         					</td>
         				</tr>
					    <%
         					}
					    %>
         			</table>
				</div>
					<div class="tab-pane" id="5">
					<%
						List<Patron> misc = null;
						misc = Patron.patrMisc(GL14PATR);
					%>
					Patron Miscellaneous Attributes
         			<table class="table table-bordered table-condensed" style="font-size:10pt">
         				 <thead>
					      <tr>
					        <th>Description</th>
					        <th>Value</th>
					      </tr>
					    </thead>
					    <tbody>
         				<%
         					for (Patron i: misc) {
         				%>
         				<tr>
         					<td><%=i.getGL66DESC()%></td>
         					<td><%=i.getGL65VALUE()%></td>
         				</tr>
         				<%
         					}
         				%>
         				</tbody>
         			</table>
				</div>
			</div>
  </div>
</div>