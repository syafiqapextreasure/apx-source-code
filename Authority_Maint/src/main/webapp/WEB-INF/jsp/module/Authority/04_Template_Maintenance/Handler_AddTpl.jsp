<%@page import="com.ilmu.Authority.AuthorityMaint.*,com.ilmu.global.Glnumb"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<%
	
	String CT15SEQNO = request.getParameter("CT15SEQNO");
	String CT15TNAME = request.getParameter("CT15TNAME");
	String CT15STAT = request.getParameter("CT15STAT");
	String CT15TYPE = request.getParameter("CT15TYPE");
	System.out.println("Record" + CT15TNAME);
	boolean rcrdExist = Template_Maintenance.rcrdChk(CT15TNAME);
	System.out.println(rcrdExist);
	if(!rcrdExist){
				Template_Maintenance.CT_Tpl(CT15SEQNO, CT15TNAME, CT15STAT, CT15TYPE);
				Glnumb.incHits("CTMSTRSEQNO");
				out.println("ok");
			}else{
				out.println("error");
			}

	
%>