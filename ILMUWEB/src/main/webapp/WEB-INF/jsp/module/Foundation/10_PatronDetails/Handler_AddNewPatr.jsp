	<%@ page import="com.ilmu.cataloging.Acc_Maint.*,com.ilmu.foundation.*, java.util.List" %>
	
	<%
		System.out.println("d");
	
		 String GL14PATR = request.getParameter("GL14PATR"); 
		 String GL14PASSWORD = request.getParameter("GL14PASSWORD");
		 //String GL14PASW = request.getParameter("GL14PASW");
		 String GL14NAMETITLE=request.getParameter("GL14NAMETITLE");
		 String GL14NAME = request.getParameter("GL14NAME");
		 String GL14GRID=request.getParameter("GL14GRID");
		 String GL14CATE=request.getParameter("GL14CATE");
		 String GL14STAT=request.getParameter("GL14STAT");
		 String GL14BRNC=request.getParameter("GL14BRNC");
		 String GL14MEMDATE=request.getParameter("GL14MEMDATE");
		 String GL14EXPDATE=request.getParameter("GL14EXPDATE");
		 String GL14RACE=request.getParameter("GL14RACE");
		 String GL14RELIGION=request.getParameter("GL14RELIGION");
		 String GL14NEWIC=request.getParameter("GL14NEWIC");
		 String GL14OLDIC=request.getParameter("GL14OLDIC");
		 String GL14COLOR=request.getParameter("GL14COLOR");
		 String GL14RELID=request.getParameter("GL14RELID");
		 String GL14PARENTID=request.getParameter("GL14PARENTID");
		 String GL14IPADD=request.getParameter("GL14IPADD");
		 String GL14SEX=request.getParameter("GL14SEX");
		 String GL14DOB=request.getParameter("GL14DOB");
		 String GL14DEPOSIT=request.getParameter("GL14DEPOSIT");
		 String GL14MEMFEE=request.getParameter("GL14MEMFEE");
		 String GL14RECEIPT=request.getParameter("GL14RECEIPT");
		 String GL14EMPLOYEE=request.getParameter("GL14EMPLOYEE");
		 String GL14DESC=request.getParameter("GL14DESC");
		 String GL14DEPT=request.getParameter("GL14DEPT");
		 String GL14COURSE=request.getParameter("GL14COURSE");
		 String GL14REMARK=request.getParameter("GL14REMARK");
		 String GL14DATEJOIN=request.getParameter("GL14DATEJOIN");
		 String GL14REGISTER=request.getParameter("GL14REGISTER");
		 String GL14SUPERVISOR=request.getParameter("GL14SUPERVISOR");
		 String GL14STAFFLEVEL=request.getParameter("GL14STAFFLEVEL");
		 String GL14ADD1=request.getParameter("GL14ADD1");
		 String GL14ADD2=request.getParameter("GL14ADD2");
		 String GL14ADD3=request.getParameter("GL14ADD3");
		 String GL14CODE=request.getParameter("GL14CODE");
		 String GL14TOWN=request.getParameter("GL14TOWN");
		 String GL14HTEL=request.getParameter("GL14HTEL");
		 String GL14HTELX=request.getParameter("GL14HTELX");
		 String GL14MTEL=request.getParameter("GL14MTEL");
		 String GL14ADD21=request.getParameter("GL14ADD21");
		 String GL14ADD22=request.getParameter("GL14ADD22");
		 String GL14ADD23=request.getParameter("GL14ADD23");
		 String GL14CODE2=request.getParameter("GL14CODE2");
		 String GL14TOWN2=request.getParameter("GL14TOWN2");
		 String GL14HTEL2=request.getParameter("GL14HTEL2");
		 String GL14OFFADD1=request.getParameter("GL14OFFADD1");
		 String GL14OFFADD2=request.getParameter("GL14OFFADD2");
		 String GL14OFFADD3=request.getParameter("GL14OFFADD3");
		 String GL14OFFCODE=request.getParameter("GL14OFFCODE");
		 String GL14OFFTOWN=request.getParameter("GL14OFFTOWN");
		 String GL14OTEL=request.getParameter("GL14OTEL");
		 String GL14FAX=request.getParameter("GL14FAX");
		 
		 String GL14ABBR=request.getParameter("GL14ABBR");
		 String GL14MAILFLAG=request.getParameter("GL14MAILFLAG");
		 /*String GL14FINEOUT=request.getParameter("GL14FINEOUT");
		 String GL14FINECOL=request.getParameter("GL14FINECOL");
		 String GL14LOSTBOK=request.getParameter("GL14LOSTBOK");
		 String GL14SUSPEND=request.getParameter("GL14SUSPEND");
		 String GL14BORDATE=request.getParameter("GL14BORDATE");
		 String GL14BORYEAR=request.getParameter("GL14BORYEAR");
		 String GL14LTDATE=request.getParameter("GL14LTDATE");
		 String GL14LTYEAR=request.getParameter("GL14LTYEAR");
		 String GL14LASTBOR=request.getParameter("GL14LASTBOR");
		 String GL14LASTRET=request.getParameter("GL14LASTRET");
		 String GL14LOGIN=request.getParameter("GL14LOGIN");
		 String GL14USID=request.getParameter("GL14USID");
		 String GL14DUEF=request.getParameter("GL14DUEF");
		 String GL14BPRINT=request.getParameter("GL14BPRINT");
		 String GL14SECURE=request.getParameter("GL14SECURE");
		 String GL14PBAR=request.getParameter("GL14PBAR");
		 String GL14SNOTICE=request.getParameter("GL14SNOTICE");
		 String GL14RMVD=request.getParameter("GL14RMVD");
		 String GL14DEFMODE=request.getParameter("GL14DEFMODE");*/
		 String GL14IMAGE=request.getParameter("GL14IMAGE");
		 //String GL14IMG=request.getParameter("GL14IMG");

		out.println("ok1");
		boolean isDuplicate = CTRetrieve.isExistPatronID(GL14PATR);
		if(isDuplicate)
		{
			out.println("error");
		}else
		{
			/* CTRetrieve.CT_AccMaint(CT03DOCNO, CT03MATNO, CT03LOCA, CT03ICAT, CT03VEND,CT03COND,CT03INVOICE,
			CT03SMD,CT03INVDATE,currency,CT03COPYNO,CT03VOL,CT03RATE,copyType,onthefly,
			foreignCost,localCost,sCost,hCost); */
			out.println("ok");
		}
	%>