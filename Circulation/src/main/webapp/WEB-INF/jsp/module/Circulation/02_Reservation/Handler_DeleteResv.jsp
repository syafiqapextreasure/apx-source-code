<%@page import="com.ilmu.circulation.resv.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>

	<%
	try {
		String CI03PATR = request.getParameter("CI02PATR");
		String CI03DOCNO = request.getParameter("CI03DOCNO");
		String CI03MATNO = request.getParameter("CI03MATNO");
		String CI03NSTAT = request.getParameter("CI03NSTAT");
		String username = (String)session.getAttribute("username");
		boolean checkAwaiting = Reservation.checkAwaiting(CI03DOCNO, CI03MATNO);
		Reservation.deleteRsv(CI03PATR, CI03DOCNO, CI03MATNO, username);
		
		boolean deleted = Reservation.resvIsDeleted(CI03PATR, CI03DOCNO, CI03MATNO);
		
		if(deleted){
			List<Reservation> getPatrByOrder = null;
			getPatrByOrder = Reservation.getPatrByOrder(CI03DOCNO, CI03MATNO);
			
			int CI03PRIOR = 1;
			
			boolean ciresvExist = Reservation.ciresvExist(CI03DOCNO, CI03MATNO);
			
			for (Reservation i: getPatrByOrder) {
				Reservation.updatePriority(i.getGL05LOCA(), i.getGL05DESC(), CI03PRIOR++);
			}
			
			System.out.println("Status" + CI03NSTAT); 
		
			 if((CI03NSTAT.trim()).equals("A")){
				 System.out.println("sd");
				 
				 if(checkAwaiting){
					 System.out.println("Status" + CI03NSTAT);
					 Reservation.updateStatus(CI03DOCNO, CI03MATNO);
				 }else{
					 System.out.println("Statussw3" + CI03NSTAT);
					Reservation.updateCTDOCM(CI03MATNO, CI03DOCNO);
				 } 
			 }
			/* if(CI03NSTAT.equals("A")){
				for (Reservation i: getPatrByOrder) {
					Reservation.updatePriority(i.getGL05LOCA(), i.getGL05DESC(), CI03PRIOR++, CI03NSTAT);
				}
			}else{
				for (Reservation i: getPatrByOrder) {
					Reservation.updatePriority(i.getGL05LOCA(), i.getGL05DESC(), CI03PRIOR++, "X");
				}
			} */
			
			
		}

		out.println("ok");
		
	} catch (Exception e) {
		out.println("error");
	}
	%>
