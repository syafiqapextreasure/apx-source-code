	<%@ page import="com.ilmu.foundation.PatronCategory.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	
	
	System.out.println("Masuk Handler Add Patron Category");
	
	 String GL07CATE = request.getParameter("GL07CATE");
   	 String GL07DESC = request.getParameter("GL07DESC");
   	 String GL07ELIG = request.getParameter("GL07ELIG");
   	 String GL07MAXFINE = request.getParameter("GL07MAXFINE");
   	 String GL07FINELIMIT = request.getParameter("GL07FINELIMIT");
   	 String GL07ILLOUT = request.getParameter("GL07ILLOUT");
   	 String GL07MAXRESV = request.getParameter("GL07MAXRESV");
  	 String GL07ALLOWOVD = request.getParameter("GL07ALLOWOVD");
  	 String GL07ALLOWRSV = request.getParameter("GL07ALLOWRSV");
  	 String GL07MAXACCT = request.getParameter("GL07MAXACCT");
  	 String GL07POPDB = request.getParameter("GL07POPDB");
  	 String GL07RATER = request.getParameter("GL07RATER");
  	 String GL07EMAIL = request.getParameter("GL07EMAIL");
 	 String GL07MODEM = request.getParameter("GL07MODEM");
 	 String GL07SCHAR = request.getParameter("GL07SCHAR");
 	 String GL07BRFORC = request.getParameter("GL07BRFORC");
 	 String GL07ARTXN = request.getParameter("GL07ARTXN");
 	 String GL07DCFORC = request.getParameter("GL07DCFORC");
 	 
 	 String GL07RENEWFEE = request.getParameter("GL07RENEWFEE");
 	 String GL07RENEWGRC = request.getParameter("GL07RENEWGRC");
   	
	
 	SQLStatement.AddPatCate(GL07CATE, GL07DESC, GL07ELIG,GL07MAXFINE,GL07FINELIMIT,GL07ILLOUT, GL07MAXRESV, GL07ALLOWOVD,GL07ALLOWRSV, GL07MAXACCT, GL07POPDB,GL07RATER, GL07EMAIL, GL07MODEM, GL07SCHAR,GL07BRFORC,GL07ARTXN,GL07DCFORC,GL07RENEWFEE,GL07RENEWGRC);
	
	%>