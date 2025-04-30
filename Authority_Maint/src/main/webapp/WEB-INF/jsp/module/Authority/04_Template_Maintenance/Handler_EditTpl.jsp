<%@page import="com.ilmu.Authority.AuthorityMaint.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
	
	<%
	try {
		int CT15SEQNO = Integer.parseInt(request.getParameter("CT15SEQNO"));
		String CT15TNAME = request.getParameter("CT15TNAME");
		String CT15STAT = request.getParameter("CT15STAT");
		String CT15TYPE = request.getParameter("CT15TYPE");
		String action = request.getParameter("action");


		boolean rcrdExist=false;
		if(action.equals("true")){
			rcrdExist = Template_Maintenance.rcrdChk(CT15TNAME);
		}else{
			rcrdExist = false;
		}
		
		System.out.println(rcrdExist);
		if(!rcrdExist){
			boolean update = Template_Maintenance.CT_editTplMaint(CT15SEQNO, CT15TNAME, CT15STAT, CT15TYPE);
			
			if(update)
			{
				
				out.println("ok");
			}else{
				
				out.println("error");
			}
				}else{
					out.println("error");
				}

		
	} catch (Exception e) {
		out.println("error");
	}
	%>