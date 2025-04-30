<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,com.ilmu.circulation.Weeding.*,java.text.*,java.util.*"%>

	<%
	
	String action = request.getParameter("action");
	String ctrlno = request.getParameter("CT02MATNO");
	String matno = request.getParameter("matno");
	String bufferno = "";
	if (ctrlno != null && !ctrlno.isEmpty()) {
	    bufferno = Handler.replaceWithA(ctrlno);
	} else if (matno != null && !matno.isEmpty()) {
	    bufferno = Handler.replaceWithA(matno);
	}

		
		
		
if(action.equals("checkRecord")){
	System.out.println("none");
		if(Weeding.rcrdExist_CTMATM(matno)){
			System.out.println("none3");
			if(DeleteBO.rcrdExist_ACORDD(matno)){
				System.out.println("none4");
				if(DeleteBO.rcrdExist_SESERM(matno)){
					System.out.println("none5");
					out.println("ok");
				}else{
					out.println("decline");
				}
			}else{
				out.println("declineOrder");
			}
		}else{
			System.out.println("exists");
			out.println("decline");
		}
	}

if(action.equals("confirmDelete")){
	System.out.println("StartCheckWeedingD");
	if(Weeding.checkCTMATMD(ctrlno)){
		System.out.println("CheckCTMATMDsuccessful");
		if(DeleteBO.checkCTWORKD(bufferno)){
			System.out.println("CheckCTWORKDsuccessful");
			if(Weeding.checkCTPONTzero(ctrlno)){
				System.out.println("CheckCTPONT0successful");
				out.println("ok");
			}else{
				out.println("failCTPONT0");
			}
		}else{
			out.println("failCTWORKD");
		}
	}else{
		out.println("failCTMATMD");
	}
}

if(action.equals("afterUnindex")){
	if(DeleteBO.isDeleteable(ctrlno)){
		System.out.println("isDeleteable");
		if(Weeding.rcrdExist_CTWORK(bufferno)){
			System.out.println("rcrdExist_CTWORK");
			Weeding.updateCTMATM(ctrlno);
			DeleteBO.updateCTWORK(bufferno);
			out.println("ok");
		}else{
			out.println("noMatnoCtwork");
		}
	}else{
		out.println("statusNotT");
	}
}



	%>
