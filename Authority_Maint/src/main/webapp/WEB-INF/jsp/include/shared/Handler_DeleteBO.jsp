<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,com.ilmu.circulation.Weeding.*,java.text.*,java.util.*"%>

	<%
	
		String action = request.getParameter("action");
		String ctrlno = request.getParameter("CT02MATNO");
		String bufferno = Handler.replaceWithA(ctrlno);
		
		
if(action.equals("checkRecord")){
	System.out.println("none");
	if(DeleteBO.isDeleteable(ctrlno)){
		System.out.println("none1");
		if(Weeding.rcrdExist_CTWORK(bufferno)){
			System.out.println("none2");
		if(Weeding.rcrdExist_CTMATM(ctrlno))
		{System.out.println("none3");
			if(DeleteBO.rcrdExist_ACORDD(ctrlno)){
				System.out.println("none4");
				if(DeleteBO.rcrdExist_SESERM(ctrlno)){
					System.out.println("none5");
					Weeding.updateCTMATM(ctrlno);
					DeleteBO.updateCTWORK(bufferno);
					out.println("ok");
				}else{
					out.println("decline");
				}
				
			}else{
				out.println("decline");
			}
		}else{
			System.out.println("exists");
			out.println("decline");
			
		}
		
		}
	}
	
	}

	%>
