	<%@ page import="java.text.SimpleDateFormat, java.text.DateFormat"%>
	<%@ page import="com.ilmu.cataloging.Acc_Maint.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	try {
		String CT03DOCNO = request.getParameter("CT03DOCNO");
		String oriCT03DOCNO = request.getParameter("oriCT03DOCNO");
		//String CT05SRAW = request.getParameter("CT05SRAW");
		String CT03MATNO = request.getParameter("CT03MATNO");
		String CT03LOCA = request.getParameter("CT03LOCA");
		//String frequency = request.getParameter("frequency");
		String CT03ICAT = request.getParameter("CT03ICAT");
		String CT03VEND = request.getParameter("CT03VEND");
		String CT03COND = request.getParameter("CT03COND");
		String CT03INVOICE = request.getParameter("CT03INVOICE");
		String CT03SMD = request.getParameter("CT03SMD");
		SimpleDateFormat formaterForOut = new SimpleDateFormat("dd-MM-yyyy");
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
		System.out.println("D" + CT03INVDATE);
		if(CT03INVDATE.trim() == null || CT03INVDATE.trim()==""){
			System.out.println("NULL");
		}
		boolean update = CTRetrieve.CT_editAccMaint(oriCT03DOCNO,CT03DOCNO, CT03MATNO, CT03LOCA, CT03ICAT, CT03VEND,CT03COND,CT03INVOICE,
				CT03SMD,CT03INVDATE,currency,CT03COPYNO,CT03VOL,CT03RATE,copyType,onthefly,
				foreignCost,localCost,sCost,hCost,officer);

		
		System.out.println(update);
		
		if(update)
		{
			out.println("ok");
		}else{
			out.println("error");
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>