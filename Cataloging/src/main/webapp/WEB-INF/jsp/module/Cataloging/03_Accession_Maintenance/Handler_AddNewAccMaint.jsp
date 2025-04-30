	<%@ page import="com.ilmu.cataloging.Acc_Maint.*, com.ilmu.foundation.*, java.util.List,com.ilmu.global.*" %>
	
	<%
	System.out.println("d");
	String CT03DOCNO = request.getParameter("CT03DOCNO");
	//String CT05SRAW = request.getParameter("CT05SRAW");
	String CT03MATNO = request.getParameter("CT03MATNO");
	String CT03LOCA = request.getParameter("CT03LOCA");
	//String frequency = request.getParameter("frequency");
	String CT03ICAT = request.getParameter("CT03ICAT");
	String CT03VEND = request.getParameter("CT03VEND");
	String CT03COND = request.getParameter("CT03COND");
	String CT03INVOICE = request.getParameter("CT03INVOICE");
	String CT03SMD = request.getParameter("CT03SMD");
	String CT03INVDATE = request.getParameter("CT03INVDATE");
	String currency = request.getParameter("currency");
	String CT03COPYNO = request.getParameter("CT03COPYNO");
	String CT03VOL = request.getParameter("CT03VOL");
	String CT03RATE = request.getParameter("CT03RATE");
	String copyType = request.getParameter("copyType");
	String onthefly = request.getParameter("onthefly");
	String foreignCost = request.getParameter("foreignCost");
	String localCost = request.getParameter("localCost");
	String sCost = request.getParameter("sCost");
	String hCost = request.getParameter("hCost");
	String officer = request.getParameter("officer");
	int acclength = Integer.parseInt(request.getParameter("maxlen"));

		boolean isDuplicate = CTRetrieve.accnoExist(CT03DOCNO);
		System.out.println("Dupllicate" + isDuplicate);
		if(isDuplicate)
		{	out.println("error");
		}else if(acclength>GlobalValidation.getAccessionMaxLength()|| acclength<GlobalValidation.getAccessionMinLength()){
			System.out.println("Nosss" + GlobalValidation.getAccessionMaxLength() + acclength);
			out.println("acclenwrg");
		}else
		{
			System.out.println(sCost + hCost);
			CTRetrieve.CT_AccMaint(CT03DOCNO, CT03MATNO, CT03LOCA, CT03ICAT, CT03VEND,CT03COND,CT03INVOICE,
			CT03SMD,CT03INVDATE,currency,CT03COPYNO,CT03VOL,CT03RATE,copyType,onthefly,
			foreignCost,localCost,sCost,hCost, officer);
			out.println("ok");
		}
	%>