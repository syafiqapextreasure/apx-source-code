<%@ page import="com.ilmu.receipting.Receipting.ReceiptingService, java.util.List, com.ilmu.global.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.global.Glnumb, com.ilmu.utilities.query.*, 
					com.ilmu.global.*,java.text.*,java.util.*"%>

<%
/* String action = request.getParameter("action");
	
	boolean addoverride = false;
	if(action.equals("override")){
		String docno = request.getParameter("docno");
		String amt= request.getParameter("amt");
		String reference = request.getParameter("reference");
		String code = request.getParameter("code");
		String trxn= request.getParameter("trxn");
		String date= request.getParameter("date");
		String patrid= request.getParameter("patrid");
		 addoverride = ReceiptingService.addTransaction(action, trxn,code,date, amt, reference, patrid, "Sysadmin",
			docno, "Cash", "1");
	}else if(action.equals("misc")){
		String docno = request.getParameter("docno");
		String amt= request.getParameter("amt");
		String reference = request.getParameter("reference");
		String code = request.getParameter("code");
		String trxn= request.getParameter("trxn");
		String date= request.getParameter("date");
		String patrid= request.getParameter("patrid");
		 addoverride = ReceiptingService.addTransaction(action, trxn,code,date, amt, reference, patrid, "Sysadmin",
			docno, "Cash", "0");
	}else{
		String amt= request.getParameter("amt");
		String reference = request.getParameter("reference");--
		String code = request.getParameter("code");
		String trxn= request.getParameter("trxn");
		String patrid= request.getParameter("patrid");
		String paymode= request.getParameter("paymode");
		String docno= request.getParameter("docno");
		String receiptno = request.getParameter("receiptno");
		
		addoverride = ReceiptingService.addTransaction(action, trxn,code+"1",null, amt, receiptno, patrid, "Sysadmin",
				docno, paymode, "1");
	}
	if(addoverride){
		out.println("success");
	}  */
	String action = request.getParameter("action");
	List<String> queryList = new ArrayList<String>();
	
	if(action.equals("paidamt")){
		boolean insertAuditAddPayAmount = false;
		String[] trxn = request.getParameterValues("trxn[]");
		String[] code = request.getParameterValues("code[]");
		String[] docno = request.getParameterValues("docno[]");
		String[] raw = request.getParameterValues("rawdata[]");
		String[] amt = request.getParameterValues("amt[]");
		String patrid= request.getParameter("patrid");
		String paymode= request.getParameter("paymode");
		int total = Integer.parseInt(request.getParameter("total"));
		Glnumb CTRLNO = Glnumb.getGL98VALUE("RECEIPTNO");
		String receiptno = Handler.concatMatno(String.valueOf(CTRLNO.getGL98VALUE()));
		String reference = request.getParameter("reference");
		float amount=0;
	
		for (int i = 0; i < total; i++) {
		/* ORIGINAL CODE. ISSUE WITH AV REMOVE. 
			queryList.add(ReceiptingService.addPayment(action, trxn[i],code[i]+"1",null, amt[i], receiptno.trim(), patrid, (String)session.getAttribute("username"),
					docno[i].replaceAll("\\D+",""), paymode, "1", "0.00")); */
			queryList.add(ReceiptingService.addPayment(action, trxn[i],code[i]+"1",null, amt[i], receiptno.trim(), patrid, (String)session.getAttribute("username"),
					docno[i], paymode, "1", "0.00"));
			amount +=Float.parseFloat(amt[i]);
		}
		
		insertAuditAddPayAmount = ReceiptingService.insertAuditAddPayAmount(reference, patrid, (String)session.getAttribute("username"), amount, receiptno.trim());
		
		boolean isSuccess = DBQuery.runBatchQuery(queryList);
		
		if(isSuccess){
		 out.println("Successful," + receiptno.trim());
		}else{
			out.println("Fail");
		}
		
	}else if(action.equals("override")){
		boolean addoverride = false;
		boolean insertAuditOverride = false;
		String docno = request.getParameter("docno");
		String amt= request.getParameter("amt");
		String reference = request.getParameter("reference");
		String code = request.getParameter("code");
		String trxn= request.getParameter("trxn");
		String date= request.getParameter("date");
		String patrid= request.getParameter("patrid");
		
		addoverride = ReceiptingService.addTransaction(action, trxn,code,date, amt, reference, patrid,(String)session.getAttribute("username"),docno, "Cash", "1"); 
		insertAuditOverride = ReceiptingService.insertAuditAddTransaction(reference, patrid, (String)session.getAttribute("username"), docno, amt,code);
		if(addoverride){
			out.println("success");
		} 
	}else if(action.equals("misc")){
		boolean addoverride = false;
		boolean insertAuditAddTransaction = false;
		String docno = request.getParameter("docno");
		String amt= request.getParameter("amt");
		String reference = request.getParameter("reference");
		String code = request.getParameter("code");
		String trxn= request.getParameter("trxn");
		String date= request.getParameter("date");
		String patrid= request.getParameter("patrid");
		addoverride = ReceiptingService.addTransaction(action, trxn,code,date, amt, reference, patrid, (String)session.getAttribute("username"),docno, "Cash", "0");
		insertAuditAddTransaction = ReceiptingService.insertAuditAddTransaction(reference, patrid, (String)session.getAttribute("username"), docno, amt,code);
		 if(addoverride){
				out.println("success");
			} 
	}
	
	
%>