<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.circulation.resv.*, java.text.DateFormat, java.text.SimpleDateFormat,
				java.time.LocalDateTime, java.time.format.DateTimeFormatter,java.util.Calendar,
				java.util.Date, com.ilmu.circulation.Charging.*, java.math.*" %>
<%@page import="java.util.List"%>
<%@ page import="java.util.*, com.ilmu.global.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>W-ILMU / Receipting / Print Receipt</title>
<link id="ui-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link id="ui1-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/transactionStyle.css" rel="stylesheet">
<script>
function printpage() {
	  window.print();
	  }
</script>
</head>
<body onload="printpage()">
	<page>		<%
				Discharging chr= null;
				chr= new Discharging();
				String action = request.getParameter("action");	
				String GL14PATR = request.getParameter("GL14PATR");
				String CT03DOCNO = request.getParameter("CT03DOCNO");
				System.out.println("Action" + action + GL14PATR);
				List<Reservation> getPatrInfo = null;
				List<Reservation> getItemInfo = null;
				List<Reservation> getCirculated = null;
				String[] accession;
				getPatrInfo = Reservation.getPatrInfo(GL14PATR);
				/* String[] accession = CT03DOCNO.split(",");
			    for(int x=0; x<accession.length; x++){
			    	getItemInfo = Reservation.getItemInfo(accession[x], "accession");
			    } */
				
			%>
	<div class="center padding-20 no-print" align="center">
		<button action="Print" title="Print" class="btn btn-primary" onClick="window.print()">
			Print <i class="glyphicon glyphicon-print"></i>
		</button>
	</div>
<br/>
	<div class="padding-20">
		<%
		for (Reservation i: getPatrInfo) {
		%>
		<div class="ta-center">
			<div class="ta-center"><%=i.getGL28ORGNAME() %></div>
			<div class="ta-center"><%=i.getGL28ADD1() %></div>
			<div class="ta-center"><%=i.getGL28ADD2() %></div>
			<div class="ta-center"><%=i.getGL28POSCODE() %></div>
			<br/>
			<div class="ta-center">
				<%=action %>
			Slip
			</div>
		</div>
		<%
		}
		%>
	
		<%
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		 	DateFormat fmt = new SimpleDateFormat("hh:mm:ss aa");
		   String currenttime = fmt.format(new Date());
	       Date dateobj = new Date();
	       System.out.println(df.format(dateobj));
	
    
		%>
		<%
		System.out.println("Action" + action);
			if(action.equals("Charging")){
				int counter = 1;
				System.out.println("Actions" + CT03DOCNO);
				accession = CT03DOCNO.split(",");
		%>
		<div class="margin-btm-15">
			<div class="float-lefts">Date: <%=df.format(dateobj)%> &nbsp; <%=currenttime %></div>
			<div class="clear"></div>
		</div>	
		<hr class="style3">
		  <table>
			<tr>
				<td>No.</td>
				<td>Accession No.</td>
				<td>Due Date & Time</td>
			</tr>
			<tr>
				<td>Title</td>
			</tr>
		  </table>	
		<hr class="style3">
		
		<%
			    for(int x=0; x<accession.length; x++){
			    	System.out.println("Actions23" + accession[x]);
			    	getItemInfo = Reservation.getItemInfo(accession[x], "accession");
					getCirculated = Reservation.getCirculatedInfo(accession[x]);
		%>
		<div class="margin-btm-15">
		<table>
			<col width="1%">
			<col width="1%">
			<col width="5%">
			<%

				for (Reservation i: getItemInfo) {
					System.out.println(accession[x]);
					System.out.println("nUMBER" + x);
					System.out.println(i.getCT05SRAW());
			%>
			
				<tr>
					<td><%=counter++ %></td>
					<td><%=accession[x] %></td>
					<%
					for (Reservation j: getCirculated){
						System.out.println("Due Date" + j.getCI02DDATE() );
					%>
					<td> <%=j.getCI02DDATE() %> </td>
					<%
					}
					%>
				</tr> 
				<tr>
					<td><label>&nbsp;</label></td>
					<td colspan="2"><%=i.getCT05SRAW() %></td>
				</tr>
					<%
						}
					%>
			</table>
			<hr class="style3">
			<div class="clear"></div>
		</div>
		
		<%}
			}else if (action.equals("Discharging")){
		%>
			 <div class="margin-btm-15">
				<div class="float-lefts">Date: Date</div>
				<div class="clear"></div>
			</div>	
			<hr class="style3">
				<table class="transaction-table">
					<tr>
						<td>Accession No.</td>
					</tr>
					<tr>
						<td>Title</td>
					</tr>
				</table>
			<hr class="style3"> 	
		<%	
			accession = CT03DOCNO.split(",");
	
		    for(int x=0; x<accession.length; x++){
		    	
		    	System.out.println("Actions23" + accession[x]);
		    %>
		    <table class="transaction-table">
		    <%
		    	String[] dischargeDetails = (accession[x]).split("\\|");

		    	System.out.println("Actions23" + dischargeDetails[0]);
		    	getItemInfo = Reservation.getItemInfo(dischargeDetails[0], "accession");
				for (Reservation i: getItemInfo) {
					
					System.out.println("Actions232323" + accession[x]);
				
			%>
				<tr>
					<td><%=df.format(dateobj) %> &nbsp; <%=currenttime %></td>
				</tr>
				<tr>
					<td><%=dischargeDetails[0] %></td>
				</tr>
				<tr>
					<td><%=i.getCT05SRAW() %></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
					<%
						} 
					/* String cdate = request.getParameter("CI02CDATE");
					String ctime = request.getParameter("CI02CTIME");
					String ddate = request.getParameter("CI02DDATE");
					String dtime = request.getParameter("CI02DTIME");
					String lateby = request.getParameter("LateBy");
					String fines = request.getParameter("fines"); */
					getCirculated = Reservation.getDischargeInfo(dischargeDetails[0], dischargeDetails[1], dischargeDetails[3], dischargeDetails[2], dischargeDetails[4]);	
					for (Reservation i: getCirculated){
						
						
					%>
				<tr>
					<td><label>Charge Date</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=i.getCI02CDATE() %></td>
				</tr>
				<tr>
					<td><label>Return Date</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=df.format(dateobj)%> </td>
				</tr>
				<tr>
					<td><label>Due Date</label></td>
					<td><label>:&nbsp;</label></td>
					<td> <%=i.getCI02DDATE() %> </td>
				</tr>
				<tr>
					<td><label>Late By</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=dischargeDetails[5]%>&nbsp;days</td>
				</tr>
				<tr>
					<td><label>Fines</label></td>
					<td><label>:&nbsp;</label></td>
					<td> <%=dischargeDetails[6]%></td>
				</tr>
					<%
						}
					%>
			</table>
			<hr class="style3">
			<div class="clear"></div>
			<%
		    	   }
			}else if (action.equals("Renewal")){
			%>
			 	<div class="margin-btm-15">
				<div class="float-lefts">Date: Date</div>
				<div class="clear"></div>
			</div>	
			<hr class="style3">
				<table class="transaction-table">
					<tr>
						<td>Accession No.</td>
					</tr>
					<tr>
						<td>Title</td>
					</tr>
				</table>
			<hr class="style3"> 
			<%
				accession = CT03DOCNO.split(",");
				
			    for(int x=0; x<accession.length; x++){
			    	System.out.println("Actions23" + accession[x]);
		%>
		<div class="margin-btm-15">
		<table class="transaction-table">
			<%
				String[] dischargeDetails = (accession[x]).split("\\|");
		    	getItemInfo = Reservation.getItemInfo(dischargeDetails[0], "accession");
				for (Reservation i: getItemInfo) {

			%>
				<tr>
					<td><%=df.format(dateobj) %> &nbsp; <%=currenttime %></td>
				</tr>
				<tr>
					<td><%=dischargeDetails[0] %></td>
				</tr>
				<tr>
					<td><%=i.getCT05SRAW() %></td>
				</tr>
					<%
						} 
					/* String cdate = request.getParameter("CI02CDATE");
					String ctime = request.getParameter("CI02CTIME");
					String ddate = request.getParameter("NewDate");
					String due = request.getParameter("DueDate");
					String dtime = request.getParameter("CI02DTIME"); */
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = new Date();
					getCirculated = Reservation.getRenewInfo(dischargeDetails[0]);
							for (Reservation i: getCirculated){
						
					%>
				<tr>
					<td><label>Original Due Date</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=dischargeDetails[1] %></td>
				</tr>
				<tr>
					<td><label>Renew Date</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=dateFormat.format(date).toString()%> </td>
				</tr>
				<tr>
					<td><label>New Due Date</label></td>
					<td><label>:&nbsp;</label></td>
					<td> <%=i.getCI03DDATE()%> </td>
				</tr>
				<tr>
					<td><label>Late By</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=dischargeDetails[3]%>&nbsp;days</td>
				</tr>
				<tr>
					<td><label>Fines</label></td>
					<td><label>:&nbsp;</label></td>
					<td> <%=dischargeDetails[4]%></td>
				</tr>
					<%
						}
					%>
			</table>
			<hr class="style3">
			<div class="clear"></div>
		</div>
		<%
			    }
			}
		%>
		
			<%
			String patron = "";
			for (Reservation i: getPatrInfo) {
			patron = GL14PATR;
		%>

		<div class="margin-btm-15">
			<table>
				<tr>
					<td>ID </td>
					<td>: &nbsp;<%=GL14PATR %> </td>
				</tr>
				<tr>
					<td>Name </td>
					<td>: &nbsp;<%=i.getGL14NAME() %></td>
				</tr>
				<tr>
					<td>Address </td>
					<td> : &nbsp;<%=i.getGL14ADD1() %> </td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;&nbsp;<%=i.getGL14ADD2() %></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;&nbsp;<%=i.getGL14ADD3() %></td>
				</tr>
			</table>
			<hr class="style3">
			<div class="clear"></div>
		</div>
		<%
			}
		%>
	
		<div>
			<%
				for (Reservation i: getCirculated){
					String username = (String)session.getAttribute("username");
					Patron patr = new Patron(username.toUpperCase());
			%>
			<div class="float-left">OFFICER : <%=username.toUpperCase() %>,<%=patr.getMsPatronName() %></div>
			<%
				}
			%>
		</div>

		<div class="clear"></div>
		<br/>
		<div align="center">THANK YOU</div>
	</div>
<br/>
	<div class="center padding-20 no-print" align="center">
		<button action="Print" title="Print" class="btn btn-primary" onClick="window.print()">
			Print <i class="glyphicon glyphicon-print"></i>
		</button>
	</div>
	</page>
</body>
</html>

