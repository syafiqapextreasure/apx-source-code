<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>

	<%
	
		String action = request.getParameter("action");
		String bufferno = request.getParameter("CT02MATNO");
		String ctrlno = Handler.replaceWith0(bufferno);
		String status = request.getParameter("status");
		System.out.println("sdsdsd" + bufferno + action);
	
		
if(action.equals("checkRecord")){
	if((status.contains("Buffer")||status.contains("Modify Record")) && !bufferno.contains("A")){
		out.println("approve");
	}else{
	System.out.println("none");
	if(DeleteBO.isDeleteable(ctrlno)){
		System.out.println("none1");
		if(DeleteBO.rcrdExist_CTWORK(bufferno)){
			System.out.println("none2");
		/* if(DeleteBO.rcrdExist_CTMATM(ctrlno))
		{System.out.println("none3"); */
		if(DeleteBO.rcrdExist_CTMATM(Handler.replaceWith0(ctrlno))){
			if(DeleteBO.rcrdExist_ACORDD(ctrlno)){
				System.out.println("none4");
				if(DeleteBO.rcrdExist_SESERM(ctrlno)){
					System.out.println("none5");
					out.println("approve");
					/* DeleteBO.updateCTMATM(ctrlNo);
					DeleteBO.updateCTWORK(ctrlNo);
					out.println("ok"); */
				}else{
					out.println("declineACORDD");
				}
				
			}else{
				out.println("declineACORDD");
			}
		}else{
			out.println("decline");
		}
		/* }else{
			System.out.println("exists");
			out.println("decline");
			
		} */
		
		}
	}
	}
	
	}

if(action.equals("confirmDelete")){
	DeleteBO.updateCTMATM(ctrlno);
	DeleteBO.updateCTWORK(bufferno);
	AuditTrail.InsertAudit("CT", "CTU0006",  ctrlno , (String) session.getAttribute("username"));
	out.println("ok");
}
	%>
