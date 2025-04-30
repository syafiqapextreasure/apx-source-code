<%@ page import="com.ilmu.circulation.resv.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<%
String CT03DOCNO = request.getParameter("CT03DOCNO");
String CT03MATNO = request.getParameter("CT03MATNO");

Reservation resv = new Reservation();
resv.checkAccession(CT03DOCNO,CT03MATNO);
String errmessage=resv.getErrMessage();
System.out.println("Err" + resv.getErrMessage());
List<Reservation> getInfo = null;
List<Reservation> getCallNo = null;

System.out.println("Err" + errmessage);
if(errmessage!=null && errmessage!=""){
	out.println(errmessage);
}else{
	String action = request.getParameter("action");
	System.out.println(CT03MATNO + action);
	if(action.equals("accession")){
		getInfo = Reservation.getItemInfo(CT03DOCNO, action);
		getCallNo = Reservation.getCallNo(CT03DOCNO, action);
	}else if(action.equals("ctrlno")){
		System.out.println(CT03MATNO + action);
		getInfo = Reservation.getItemInfo(CT03MATNO, action);
		getCallNo = Reservation.getCallNo(CT03MATNO, action);
	}
/* 
	String action = request.getParameter("action");
List<Reservation> getInfo = null;
List<Reservation> getCallNo = null;
	if(action.equals("accession")){
		String CT03DOCNO = request.getParameter("CT03DOCNO");
		boolean isAvailable = Reservation.chkItemAvailable(CT03DOCNO);
				if(isAvailable){
					String status = Reservation.getItemStatus(CT03DOCNO);
					System.out.println("Testing23");
					System.out.println("Testing" + status);
					out.println("049," + status);
				}else{
				
					getInfo = Reservation.getItemInfo(CT03DOCNO, action);
					getCallNo = Reservation.getCallNo(CT03DOCNO, action);
				}
		}else if(action.equals("ctrlno")){
			String CT03MATNO = request.getParameter("CT03MATNO");
			boolean chkAccessionList = Reservation.chkAccessionList(CT03MATNO);
			
			if(!chkAccessionList){
				getInfo = Reservation.getItemInfo(CT03MATNO, action);
				getCallNo = Reservation.getCallNo(CT03MATNO, action);
			}else{
				out.println("070");
			}

		} */
	
	if(getInfo!=null||getCallNo!=null){
			%>
				<div class="panel panel-default">
				  <div class="panel-body" style="width:90%;margin:auto">
				  	<table>
				  	<col width="7%">
				  	<col width="20%">
				  	<col width="7%">
				  	<col width="20%">
				  <%
				  	for (Reservation i: getInfo) {
				  %>
				  		<tr>
							<td>
								<label for="staticEmail" class="col-form-label">Item Location :</label>
							</td>
							<%
							String locadesc = "";
							
							if((i.getGL05DESC())==null){
								locadesc = "";
							}else{
								locadesc = i.getGL05DESC();
							}
							%>
							<td class="itemloca"><%=locadesc%></td>
							<td>
								<label for="staticEmail" class="col-form-label">Location :</label>
							</td>
							<%
							String locacode = "";
							if((i.getGL05LOCA())==null){
								locacode = "";
							}else{
								locacode = i.getGL05LOCA();
							}
							%>
							<td class="loca"><%=locacode%></td>
						</tr>
						<tr>
						<td>
							<label for="staticEmail" class="col-form-label">Title :</label>
						</td>
						<td><%=i.getCT05SRAW() %></td>
					
					<%
						}	for (Reservation i: getCallNo) {
					%>
						<td>	
							<label for="staticEmail" class="col-form-label">Call No. :</label>
						</td>
						<td><%=i.getCT05SRAW() %></td>
						</tr>
					<%
						}
					%>
					</table>
				  </div>
				</div>
		<%
	}}
		%>