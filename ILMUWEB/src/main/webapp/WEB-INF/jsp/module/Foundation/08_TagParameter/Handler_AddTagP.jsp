	<%@ page import="com.ilmu.foundation.TagParameter.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	
	
	 System.out.println("Masuk Handler Add Status");
	
	
	 String GL17MARC= request.getParameter("GL17MARC");
	 System.out.println(GL17MARC);
	 String GL17TAG= request.getParameter("GL17TAG");
	 System.out.println(GL17TAG);
	 String GL17DESC= request.getParameter("GL17DESC");
	 String GL17TRUC= request.getParameter("GL17TRUC");
	 String GL17TABNAME= request.getParameter("GL17TABNAME");
	 String GL17ACRO = request.getParameter("GL17ACRO");
	 String GL17GRID = request.getParameter(" GL17GRID");
	 String GL17LEN = request.getParameter("GL17LEN");
	 String GL17IDXLANG = request.getParameter("GL17IDXLANG");
	 String GL17DEFAULT = request.getParameter("GL17DEFAULT");
	 String GL17REMARK = request.getParameter("GL17REMARK");
	 String GL17REPEAT = request.getParameter("GL17REPEAT");
	 String GL17AUTFLAG = request.getParameter("GL17AUTFLAG");
	 String GL17MANDA = request.getParameter("GL17MANDA");
	 String GL17CPFLAG = request.getParameter("GL17CPFLAG");
	 String GL17IDXFLAG = request.getParameter("GL17IDXFLAG");
	 String GL17PARAMIPS = request.getParameter("GL17PARAMIPS");
	 String GL17KEYWORD = request.getParameter("GL17KEYWORD");
	 String GL17MEDIA = request.getParameter("GL17MEDIA");
	 String GL17SPELL = request.getParameter("GL17SPELL"); 
	 /* String GL18INDILVL = request.getParameter("GL18INDILVL");
	 System.out.println(GL18INDILVL);
	 String GL18INDI = request.getParameter("GL18INDI");
	 System.out.println(GL18INDI); */
	 SQLStatement.AddTagP(GL17MARC, GL17TAG, GL17DESC,GL17TRUC,GL17TABNAME,GL17ACRO,GL17GRID,GL17LEN,GL17IDXLANG,GL17DEFAULT,GL17REMARK,GL17REPEAT,GL17AUTFLAG,GL17MANDA,GL17CPFLAG,GL17IDXFLAG,GL17PARAMIPS,GL17KEYWORD,GL17MEDIA,GL17SPELL);
	 
	 
	// SQLStatement.AddTagP(GL17MARC, GL17TAG, GL17DESC);
	
	
	%>