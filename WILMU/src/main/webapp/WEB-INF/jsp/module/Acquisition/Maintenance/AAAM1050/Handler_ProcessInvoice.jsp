
<%@page import="java.util.List, com.ilmu.acquisition.invoiceEntry.SaveInvoice "%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime" %>

<%

	String sInvoiceRawDate = DateTime.getTodaySystemDate();
	System.out.println("sInvoiceRawDate >  "+sInvoiceRawDate);
	
	String vsCurrentTime = DateTime.getTodayTime();
	System.out.println("vsCurrentTime >  "+vsCurrentTime);
	
	String msVendor = request.getParameter("vendcode");
	System.out.println("vendcode >  "+msVendor);
	
	String sInvoiceNo = request.getParameter("invno");
	System.out.println("invno >  "+sInvoiceNo);
	
	String bUseNote = request.getParameter("bUseNote");
	System.out.println("bUseNote >  "+bUseNote);
	
	String bShowAccession = request.getParameter("bShowAccession");
	System.out.println("bShowAccession >  "+bShowAccession);
	
	String getcdtypeCDNote = request.getParameter("getcdtypeCDNote");
	System.out.println("getcdtypeCDNote >  "+getcdtypeCDNote);
	
	String getlocalAmountCDNote = request.getParameter("getlocalAmountCDNote");
	System.out.println("getlocalAmountCDNote >  "+getlocalAmountCDNote);
	
	if(getlocalAmountCDNote.equals("null")){
		getlocalAmountCDNote = "0";
	}
	
	String TotalCreditNote = "", TotalDebitNote = "";
	
	if(getcdtypeCDNote.equals("Credit")){
		 	TotalCreditNote = getlocalAmountCDNote;
		 	TotalDebitNote = "0.00";
	}else if(getcdtypeCDNote.equals("Debit")){
		 	TotalDebitNote = getlocalAmountCDNote;
		 	TotalCreditNote = "0.00";
	}else if (getcdtypeCDNote.equals("")){
		TotalDebitNote = "0.00";
		 	TotalCreditNote = "0.00";
	}
	
	String discountpercentage = request.getParameter("discountpercentage");
	if(discountpercentage.equals("")){
		discountpercentage = "0";
	}
	System.out.println("discountpercentage >  "+discountpercentage);
	
	String totalDiscount = request.getParameter("totalDiscount");
	if(totalDiscount.equals("")){
		totalDiscount = "0";
	}
	System.out.println("totalDiscount >  "+totalDiscount);
	
	String serviceCharges = request.getParameter("serviceCharges");
	if(serviceCharges.equals("")){
		serviceCharges = "0";
	}
	System.out.println("serviceCharges >  "+serviceCharges);
	
	String handlingCharges = request.getParameter("handlingCharges");
	if(handlingCharges.equals("")){
		handlingCharges = "0";
	}
	System.out.println("handlingCharges >  "+handlingCharges);
	
	String[] sDesc = request.getParameterValues("title[]");
	System.out.println("title >  "+sDesc);
	
	String[] iQuantity = request.getParameterValues("qty[]");
	System.out.println("qty >  "+iQuantity);
	
	String[] sForex = request.getParameterValues("currency[]");
	System.out.println("currency >  "+sForex);
	
	String[] cUnitPrice = request.getParameterValues("unitprice[]");
	System.out.println("unitprice >  "+cUnitPrice);
	
	String[] dblExchangeRate = request.getParameterValues("excrate[]");
	System.out.println("dblExchangeRate >  "+dblExchangeRate);
	
	String[] cLocalPrice = request.getParameterValues("localprice[]");
	System.out.println("localprice >  "+cLocalPrice);
	
	String sNoteNum = request.getParameter("getnotenoCDNote");
	System.out.println("getnotenoCDNote >  "+sNoteNum);
	
	String total = request.getParameter("total");
	System.out.println("total >  "+total);
	
	String[] sOrderNo = request.getParameterValues("ordernoInvoice[]");
	System.out.println("sOrderNo >  "+sOrderNo);
	
	/* String referenceNo = request.getParameter("referenceNo");
	System.out.println("referenceNo >  "+referenceNo); */
	
	String[] referenceNo = request.getParameterValues("referenceNo[]");
	System.out.println("referenceNo >  "+referenceNo);
	
	int row = Integer.parseInt(request.getParameter("totalInvlength"));
	
	//String ordernoupdAcc = request.getParameter("ordernoupdAcc");
	/* String[] ordernoupdAcc = request.getParameterValues("ordernoupdAcc[]");
	System.out.println("ordernoupdAcc >  "+ordernoupdAcc);
	
	String[] accnoupdAcc = request.getParameterValues("accnoupdAcc[]");
	System.out.println("accnoupdAcc >  "+accnoupdAcc);
	
	String[] updAccStatus = request.getParameterValues("updAccStatus[]");
	System.out.println("updAccStatus >  "+updAccStatus); */
	
	String ordernoupdAcc = request.getParameter("ordernoupdAcc");
    System.out.println("ordernoupdAcc >  "+ordernoupdAcc);
    
    String accnoupdAcc = request.getParameter("accnoupdAcc");
    System.out.println("accnoupdAcc >  "+accnoupdAcc);
    
    String updAccStatus = request.getParameter("updAccStatus");
    System.out.println("updAccStatus >  "+updAccStatus);
    
    int totalupdAccStatuslength = Integer.parseInt(request.getParameter("totalupdAccStatuslength"));
    System.out.println("totalupdAccStatuslength > " +totalupdAccStatuslength);
	
	/* int rowAcc = Integer.parseInt(request.getParameter("totalupdAccStatuslength"));
	System.out.println("rowAcc >  "+rowAcc); */
	
	String officeId = request.getParameter("officeId");
	System.out.println("officeId >  "+officeId); 
	
	  List<String> queryList = new ArrayList<String>();
		
		boolean bSuccessful;
		
		/* String[] newsForex = sForex.split(",");
		String[] newiQuantity = iQuantity.split(",");
		String[] newdblExchangeRate = dblExchangeRate.split(",");
		String[] newcUnitPrice = cUnitPrice.split(",");
		String[] newcLocalPrice = cLocalPrice.split(","); */
		
		String[] newordernoupdAcc = ordernoupdAcc.split(","); 
		String[] newaccnoupdAcc = accnoupdAcc.split(",");
		
		for (int i = 0; i < row; i++) {
			
			bSuccessful = SaveInvoice.SaveInvoiceDetails(msVendor, sInvoiceNo, sInvoiceRawDate, sOrderNo[i], referenceNo[i], 
					sForex[i], iQuantity[i], dblExchangeRate[i], cUnitPrice[i], 
					cLocalPrice[i], sDesc[i], vsCurrentTime);  
			 
			 if(bShowAccession.equals("YES")){
				 System.out.println("bShowAccession == YES");
		    	 for (int j = 0; j < totalupdAccStatuslength; j++) {
		    		 System.out.println("ppp");
		    		 SaveInvoice.SaveAllAccession(sInvoiceNo, sInvoiceRawDate, 
		    				 newordernoupdAcc[j], newaccnoupdAcc[j]);
		    	}
		 	 } 
			 
			 if(bUseNote.equals("YES")){
					bSuccessful = SaveInvoice.SaveCDDetails(sInvoiceNo, referenceNo[i], dblExchangeRate[i], sInvoiceRawDate,
							sForex[i], iQuantity[i], sDesc[i], vsCurrentTime, getcdtypeCDNote, sNoteNum, 
							 msVendor, sInvoiceRawDate, TotalCreditNote, TotalDebitNote);
		    } 
			 
			bSuccessful = SaveInvoice.SaveDiscount(msVendor, sInvoiceNo, referenceNo[i], dblExchangeRate[i],
					    sInvoiceRawDate, sForex[i], iQuantity[i], sDesc[row-1], vsCurrentTime, discountpercentage, totalDiscount);
				     
			bSuccessful = SaveInvoice.SaveServiceCharge(msVendor, sInvoiceNo, referenceNo[i], dblExchangeRate[i],
			            sInvoiceRawDate, sForex[i], iQuantity[i], sDesc[row-1], vsCurrentTime, serviceCharges);
					 
			bSuccessful = SaveInvoice.SaveHandlingCharge(msVendor, sInvoiceNo, referenceNo[i], dblExchangeRate[i],
			             sInvoiceRawDate, sForex[i], iQuantity[i], sDesc[row-1], vsCurrentTime, handlingCharges);
		 }
		
		boolean isSuccess = DBQuery.runBatchQuery(queryList);
		
		if(isSuccess){
		 out.println("Successful");
		}else{
			out.println("Fail");
		} 
%>