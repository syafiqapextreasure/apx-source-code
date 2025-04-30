<%@ page import="com.ilmu.circulation.resv.*, com.ilmu.circulation.Charging.*" %>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<%


String CI03PATR = request.getParameter("CI03PATR");
String CI03DOCNO = request.getParameter("CI03DOCNO");
String CI03MATNO = request.getParameter("CI03MATNO");
String username = request.getParameter("username");

Reservation resv = new Reservation();
//String username = (String)session.getAttribute("screenname");
resv.executeReserve(CI03PATR, CI03DOCNO, CI03MATNO);
String errmessage=resv.getErrMessage();
if(errmessage!=null && !errmessage.isEmpty()){
	out.println(errmessage);
}else{
	Reservation.InsertResv(CI03PATR, CI03DOCNO, CI03MATNO, username);
	out.println("ok");
} 


/* boolean canResv = Reservation.canResv(CI03MATNO);
boolean itemAllowResv = Reservation.itemAllowResv(CI03MATNO);
boolean isExist = Reservation.checkIfPatronExist(CI03PATR, CI03DOCNO, CI03MATNO);
boolean expDate = Reservation.checkMemDate(CI03PATR);
boolean isElig = Reservation.checkPatrStat(CI03PATR, CI03DOCNO, CI03MATNO);
boolean isItemElig = Reservation.chkItemStat(CI03PATR, CI03DOCNO, CI03MATNO);


if(!itemAllowResv && (CI03DOCNO!="")){
	if(isExist){
		if(expDate){
			if(isElig){
				if(isItemElig){
					Reservation.InsertResv(CI03PATR, CI03DOCNO, CI03MATNO);
					out.println("ok");
				}else{
					out.println("062");
				}
			}else{
				out.println("061");
			}
		}else{
			out.println("032");
		}
	}else{
		out.println("060");
	}
}else{
	out.println("069");
} */
%>