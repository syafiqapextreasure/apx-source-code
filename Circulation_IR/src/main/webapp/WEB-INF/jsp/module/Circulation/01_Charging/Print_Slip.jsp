<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	
</script>
</head>
<body>
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
				getPatrInfo = Reservation.getPatrInfo(GL14PATR);
				getItemInfo = Reservation.getItemInfo(CT03DOCNO, "accession");
				
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
			<br/>
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
			DateFormat df = new SimpleDateFormat("dd/MM/yy");
		 	DateFormat fmt = new SimpleDateFormat("hh:mm:ss aa");
		   String currenttime = fmt.format(new Date());
	       Date dateobj = new Date();
	       System.out.println(df.format(dateobj));
	
    
		%>
		<div class="margin-btm-15">
			<div class="float-lefts">Date: <%=df.format(dateobj)%></div>
			<div class="float-rights">Time: <%=currenttime%></div>
			<div class="clear"></div>
		</div>
		
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
				<tr>
					<td>Membership Expiry Date </td>
					<td>: &nbsp;<%=GeneralUtility.StrToDate(i.getGL14EXPDATE()) %> </td>
				</tr>
			</table>

			<div class="clear"></div>
		</div>
		<%
			}
		%>
		
		
		<%
		System.out.println("Action" + action);
			if(action.equals("Charging")){
				System.out.println("Actions" + action);
				getCirculated = Reservation.getCirculatedInfo(CT03DOCNO);
		%>

		<div class="margin-btm-15">
		<table class="transaction-table">
			<tfoot>
			<%

				for (Reservation i: getItemInfo) {
					
			%>
			
				<tr>
					<td><label>Accession No</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=CT03DOCNO %></td>
				</tr>
				<tr>
					<td><label>Title</label></td>
					<td><label>: &nbsp;</label></td>
					<td><%=i.getCT05SRAW() %></td>
				</tr>
					<%
						} for (Reservation i: getCirculated){
					%>
				<tr>
					<td><label>Charge Date & Time</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=i.getCI02CDATE() %>&nbsp;<%=i.getCI02CTIME() %> </td>
				</tr>
				<tr>
					<td><label>Due Date</label></td>
					<td><label>:&nbsp;</label></td>
					<td> <%=i.getCI02DDATE() %> </td>
				</tr>
					<%
						}
					%>
					</tfoot>
			</table>

			<div class="clear"></div>
		</div>
		
		<%
			}else if (action.equals("Discharging")){
				
System.out.println("Discharging");
		%>
		<div class="margin-btm-15">
		<table class="transaction-table">
			<tfoot>
			<%

				for (Reservation i: getItemInfo) {
				
			%>
			
				<tr>
					<td><label>Accession No</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=CT03DOCNO %></td>
				</tr>
				<tr>
					<td><label>Title</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=i.getCT05SRAW() %></td>
				</tr>
					<%
						} 
					String cdate = request.getParameter("CI02CDATE");
					String ctime = request.getParameter("CI02CTIME");
					String ddate = request.getParameter("CI02DDATE");
					String dtime = request.getParameter("CI02DTIME");
					String lateby = request.getParameter("LateBy");
					String fines = request.getParameter("fines");
					getCirculated = Reservation.getDischargeInfo(CT03DOCNO, ddate, cdate, dtime, ctime);	
					for (Reservation i: getCirculated){
						
						
					%>
				<tr>
					<td><label>Charge Date & Time</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=i.getCI02CDATE() %>&nbsp;<%=i.getCI02CTIME() %> </td>
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
					<td><%=lateby%>&nbsp;days</td>
				</tr>
				<tr>
					<td><label>Fines</label></td>
					<td><label>:&nbsp</label>;</td>
					<td> <%=fines%></td>
				</tr>
					<%
						}
					%>
					</tfoot>
			</table>

			<div class="clear"></div>
		</div>
			<%
			}else if (action.equals("Renewal")){
				

		%>
		<div class="margin-btm-15">
		<table class="transaction-table">
			<tfoot>
			<%

				for (Reservation i: getItemInfo) {

			%>
			
				<tr>
					<td><label>Accession No</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=CT03DOCNO %></td>
				</tr>
				<tr>
					<td><label>Title</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=i.getCT05SRAW() %></td>
				</tr>
					<%
						} 
					String cdate = request.getParameter("CI02CDATE");
					String ctime = request.getParameter("CI02CTIME");
					String ddate = request.getParameter("NewDate");
					String dtime = request.getParameter("CI02DTIME");
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = new Date();
					getCirculated = Reservation.getRenewInfo(CT03DOCNO);
							for (Reservation i: getCirculated){
						
					%>
				<tr>
					<td><label>Original Due Date</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=i.getCI02DDATE() %></td>
				</tr>
				<tr>
					<td><label>Renew Date</label></td>
					<td><label>:&nbsp;</label></td>
					<td><%=dateFormat.format(date).toString()%> </td>
				</tr>
				<tr>
					<td><label>New Due Date</label></td>
					<td><label>:&nbsp;</label></td>
					<td> <%=i.getCI02DDATE()%> </td>
				</tr>
				<tr>
					<td><label>Late By</label></td>
					<td><label>:&nbsp;</label></td>
					<td>  days</td>
				</tr>
				<tr>
					<td class="label">Fines</td>
					<td class="label">:&nbsp;</td>
					<td>  </td>
				</tr>
					<%
						}
					%>
					</tfoot>
			</table>

			<div class="clear"></div>
		</div>
		<%
			}
		%>
	
		<div>
			<%
				for (Reservation i: getCirculated){
			%>
			<div class="float-left">Officer : <%=i.getCI02OFFI() %>,<%=i.getGL14NAME() %></div>
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

