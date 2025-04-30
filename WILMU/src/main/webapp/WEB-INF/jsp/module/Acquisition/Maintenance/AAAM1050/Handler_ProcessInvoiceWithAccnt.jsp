
<%@page import="java.util.List, com.ilmu.acquisition.invoiceEntry.SaveInvoice"%>
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

	String bUseNote2 = request.getParameter("bUseNote2");
	System.out.println("bUseNote2 >  "+bUseNote2);	
	
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

    /* String sDesc = request.getParameter("title");
    System.out.println("title >  "+sDesc); */
    String[] sDesc = request.getParameterValues("title[]");
    System.out.println("title >  "+sDesc);
    
    String iQuantity = request.getParameter("qty");
    System.out.println("qty >  "+iQuantity);

    String sForex = request.getParameter("currency");
    System.out.println("currency >  "+sForex);
    
    String cUnitPrice = request.getParameter("unitprice");
    System.out.println("unitprice >  "+cUnitPrice);
    
    String dblExchangeRate = request.getParameter("excrate");
    System.out.println("dblExchangeRate >  "+dblExchangeRate);
    
    String cLocalPrice = request.getParameter("localprice");
    System.out.println("localprice >  "+cLocalPrice);
    
    String sNoteNum = request.getParameter("getnotenoCDNote");
    System.out.println("sNoteNum >  "+sNoteNum); 

    String totalOrder = request.getParameter("total");
    System.out.println("total >  "+totalOrder); 
    
    /* String sOrderNo = request.getParameter("ordernoInvoice");
    System.out.println("orderno >  "+sOrderNo); */
    String[] sOrderNo = request.getParameterValues("ordernoInvoice[]");
    System.out.println("title >  "+sOrderNo);

    /* String msReferenceNo = request.getParameter("referenceNo");
    System.out.println("refno >  "+msReferenceNo); */  
    String[] msReferenceNo = request.getParameterValues("referenceNo[]");
    System.out.println("refno >  "+msReferenceNo);

    int row = Integer.parseInt(request.getParameter("totalInvlength"));
    System.out.println("row > " +row);
    
    String ordernoupdAcc = request.getParameter("ordernoupdAcc");
    System.out.println("ordernoupdAcc >  "+ordernoupdAcc);
    
    String accnoupdAcc = request.getParameter("accnoupdAcc");
    System.out.println("accnoupdAcc >  "+accnoupdAcc);
    
    String updAccStatus = request.getParameter("updAccStatus");
    System.out.println("updAccStatus >  "+updAccStatus);

    int totalupdAccStatuslength = Integer.parseInt(request.getParameter("totalupdAccStatuslength"));
    System.out.println("totalupdAccStatuslength > " +totalupdAccStatuslength);

    String officeId = request.getParameter("officeId");
    System.out.println("officeId >  "+officeId); 
	
    String account = request.getParameter("account");
    System.out.println("account >  "+account); 

    String sCommittedAmount = request.getParameter("CommittedAmount");
    System.out.println("CommittedAmount >  "+sCommittedAmount);  
    if(sCommittedAmount.equals("") || sCommittedAmount.equals(null)){
    	sCommittedAmount = "0";
    }	
    
    System.out.println("CommittedAmount2 >  "+sCommittedAmount);  
    
    String scurrentAmount = request.getParameter("currentAmount");
    System.out.println("currentAmount >  "+scurrentAmount);
    
    String sFundCode = request.getParameter("FundCode");
    if(sFundCode.equals("Please select")){
    	sFundCode = "";
    }
    System.out.println("FundCode >  "+sFundCode); 

    String gettotaldiscount = request.getParameter("gettotaldiscount");
    System.out.println("gettotaldiscount >  "+gettotaldiscount); 
    
    String getFundCodediscount = request.getParameter("getFundCodediscount");
    if(getFundCodediscount.equals("Please select")){
    	getFundCodediscount = "";
    }
    System.out.println("getFundCodediscount >  "+getFundCodediscount); 
    
    String gettotalService = request.getParameter("gettotalService");
    System.out.println("gettotalService >  "+gettotalService); 
    
    String getFundCodeSV = request.getParameter("getFundCodeSV");
    if(getFundCodeSV.equals("Please select")){
    	getFundCodeSV = "";
    }
    System.out.println("getFundCodeSV >  "+getFundCodeSV); 
    
    String gettotalHC = request.getParameter("gettotalHC");
    System.out.println("gettotalHC >  "+gettotalHC); 
    
    String getFundCodeHC = request.getParameter("getFundCodeHC");
    if(getFundCodeHC.equals("Please select")){
    	getFundCodeHC = "";
    }
    System.out.println("getFundCodeHC >  "+getFundCodeHC); 
    
    String getcrditnote = request.getParameter("getcrditnote");
    System.out.println("getcrditnote >  "+getcrditnote);

    String getdebitnote = request.getParameter("getdebitnote");
    System.out.println("getdebitnote >  "+getdebitnote); 
    
    String getcrditnoteFund = request.getParameter("getcrditnoteFund");
    if(getcrditnoteFund.equals("Please select")){
    	getcrditnoteFund = "";
    }
    System.out.println("getcrditnoteFund >  "+getcrditnoteFund);


    String getdebitnoteFund = request.getParameter("getdebitnoteFund");
    if(getdebitnoteFund.equals("Please select")){
    	getdebitnoteFund = "";
    }
    System.out.println("getdebitnoteFund >  "+getdebitnoteFund);

    int funcoderow = Integer.parseInt(request.getParameter("funcoderow"));
    System.out.println("funcoderow >  "+funcoderow);
    
    /* String totalOrder = request.getParameter("totalOrder");
    System.out.println("totalOrder >  "+totalOrder); */     

   List<String> queryList = new ArrayList<String>();
	
	boolean bSuccessful;
	
	String[] newsForex = sForex.split(",");
	String[] newiQuantity = iQuantity.split(",");
	String[] newdblExchangeRate = dblExchangeRate.split(",");
	String[] newcUnitPrice = cUnitPrice.split(",");
	String[] newcLocalPrice = cLocalPrice.split(",");
	
	String[] newordernoupdAcc = ordernoupdAcc.split(","); 
	String[] newaccnoupdAcc = accnoupdAcc.split(",");
	
	int countTotalOrder = sOrderNo.length;
	System.out.println("countTotalOrder " +countTotalOrder);
	

	
	for (int i = 0; i < row; i++) {
		
		//System.out.println("title :" +sDesc[i]);
		
		bSuccessful = SaveInvoice.SaveInvoiceDetails(msVendor, sInvoiceNo, sInvoiceRawDate, sOrderNo[i], msReferenceNo[i], 
				newsForex[i], newiQuantity[i], newdblExchangeRate[i], newcUnitPrice[i], 
				newcLocalPrice[i], sDesc[i].replace("'", "''"), vsCurrentTime);  
		 
		 if(bShowAccession.equals("YES")){
			 System.out.println("bShowAccession == YES");
	    	 for (int j = 0; j < totalupdAccStatuslength; j++) {
	    		 System.out.println("ppp");
	    		 SaveInvoice.SaveAllAccession(sInvoiceNo, sInvoiceRawDate, 
	    				 newordernoupdAcc[j], newaccnoupdAcc[j]);
	    	}
	 	 } 
		 
		 if(bUseNote2.equals("YES")){
				bSuccessful = SaveInvoice.SaveCDDetails(sInvoiceNo, msReferenceNo[i], newdblExchangeRate[i], sInvoiceRawDate,
						newsForex[i], newiQuantity[i], sDesc[i].replace("'", "''"), vsCurrentTime, getcdtypeCDNote, sNoteNum, 
						 msVendor, sInvoiceRawDate, TotalCreditNote, TotalDebitNote);
	    } 

	   // UPDATE 05052019
		/* bSuccessful = SaveInvoice.SaveDiscount(msVendor, sInvoiceNo, msReferenceNo[i], newdblExchangeRate[i],
				    sInvoiceRawDate, newsForex[i], newiQuantity[i], sDesc[row-1], vsCurrentTime, discountpercentage, totalDiscount);
			     
		bSuccessful = SaveInvoice.SaveServiceCharge(msVendor, sInvoiceNo, msReferenceNo[i], newdblExchangeRate[i],
		            sInvoiceRawDate, newsForex[i], newiQuantity[i], sDesc[row-1], vsCurrentTime, serviceCharges);
				 
		bSuccessful = SaveInvoice.SaveHandlingCharge(msVendor, sInvoiceNo, msReferenceNo[i], newdblExchangeRate[i],
		             sInvoiceRawDate, newsForex[i], newiQuantity[i], sDesc[row-1], vsCurrentTime, handlingCharges); */
	 } 
	
	////UPDATE 06082019
	bSuccessful = SaveInvoice.SaveDiscount(msVendor, sInvoiceNo, msReferenceNo[0], newdblExchangeRate[0],
				    sInvoiceRawDate, newsForex[0], newiQuantity[0], sDesc[row-1].replace("'", "''"), vsCurrentTime, discountpercentage, totalDiscount);
			     
		bSuccessful = SaveInvoice.SaveServiceCharge(msVendor, sInvoiceNo, msReferenceNo[0], newdblExchangeRate[0],
		            sInvoiceRawDate, newsForex[0], newiQuantity[0], sDesc[row-1].replace("'", "''"), vsCurrentTime, serviceCharges);
				 
		bSuccessful = SaveInvoice.SaveHandlingCharge(msVendor, sInvoiceNo, msReferenceNo[0], newdblExchangeRate[0],
		             sInvoiceRawDate, newsForex[0], newiQuantity[0], sDesc[row-1].replace("'", "''"), vsCurrentTime, handlingCharges);
	
	if(account.equals("YES")){
			String CommitmentCode = "200";
			String UnderCommitmentCode = "210";
			String OverCommitmentCode = "220";
			String DiscountCode = "230";
			String ServiceChargeCode = "240";
			String HandlingChargeCode = "250";
			String DebitNote = "260";
			String CreditNote = "280";
			String RealisedCommitment = "400";
			
			String[] newsCommittedAmount = sCommittedAmount.split(",");
			String[] newscurrentAmount = scurrentAmount.split(",");
			String[] newsFundCode = sFundCode.split(",");
			
			for (int k = 0; k < funcoderow; k++) {
				/* System.out.println("newsCommittedAmount[k]" +newsCommittedAmount[k]);
				System.out.println("newsFundCode[k]" +newsFundCode[k]);
				System.out.println("newscurrentAmount[k]" +newscurrentAmount[k]);
				System.out.println("sOrderNo[k]" +sOrderNo[k]); */
				
				//29052019
				if(countTotalOrder == 1){
					/* SaveInvoice.SaveNetLiability(newscurrentAmount[k], gettotaldiscount, gettotalService, gettotalHC,
							getcrditnote, getdebitnote, newsFundCode[k], RealisedCommitment, sInvoiceRawDate, sInvoiceNo,
							officeId, msVendor, sOrderNo[0], newsCommittedAmount[k]);  */
					///27102020
					String dis, hc, sc;
					System.out.println("getFundCodediscount.equals(newsFundCode[k])" +getFundCodediscount.equals(newsFundCode[k]));
					if(getFundCodediscount.equals(newsFundCode[k])){
						dis = gettotaldiscount;
					}else{
						dis = "0";
					}
					System.out.println("dis" +dis);
					
					if(getFundCodeHC.equals(newsFundCode[k])){
						hc = gettotalHC;
					}else{
						hc = "0";
					}
					
					if(getFundCodeSV.equals(newsFundCode[k])){
						sc = gettotalService;
					}else{
						sc = "0";
					}
					SaveInvoice.SaveNetLiability(newscurrentAmount[k], dis, sc, hc,
							getcrditnote, getdebitnote, newsFundCode[k], RealisedCommitment, sInvoiceRawDate, sInvoiceNo,
							officeId, msVendor, sOrderNo[0], newsCommittedAmount[k]); 
				}else{
					/* SaveInvoice.SaveNetLiability(newscurrentAmount[k], gettotaldiscount, gettotalService, gettotalHC,
							getcrditnote, getdebitnote, newsFundCode[k], RealisedCommitment, sInvoiceRawDate, sInvoiceNo,
							officeId, msVendor, sOrderNo[k], newsCommittedAmount[k]); */
					
					
							///27102020
							String dis, hc, sc;
							System.out.println("getFundCodediscount.equals(newsFundCode[k])" +getFundCodediscount.equals(newsFundCode[k]));
							if(getFundCodediscount.equals(newsFundCode[k])){
								dis = gettotaldiscount;
							}else{
								dis = "0";
							}
							System.out.println("dis" +dis);
							
							if(getFundCodeHC.equals(newsFundCode[k])){
								hc = gettotalHC;
							}else{
								hc = "0";
							}
							
							if(getFundCodeSV.equals(newsFundCode[k])){
								sc = gettotalService;
							}else{
								sc = "0";
							}
							
							SaveInvoice.SaveNetLiability(newscurrentAmount[k], dis, sc, hc,
									getcrditnote, getdebitnote, newsFundCode[k], RealisedCommitment, sInvoiceRawDate, sInvoiceNo,
									officeId, msVendor, sOrderNo[k], newsCommittedAmount[k]);
							
				}
				
			}
			
			
			///21102020
			SaveInvoice.SaveOrder(sCommittedAmount, totalOrder, sInvoiceRawDate, sFundCode, 
					UnderCommitmentCode, OverCommitmentCode, msReferenceNo[0], officeId, 
					msVendor, sOrderNo[0]);
			///05082019
			SaveInvoice.SaveDiscount(getFundCodediscount, DiscountCode, sInvoiceRawDate,
						gettotaldiscount, msReferenceNo[0], officeId, msVendor, sOrderNo[0]);
		
			SaveInvoice.SaveServiceCharges(getFundCodeSV, ServiceChargeCode, sInvoiceRawDate, 
						gettotalService, msReferenceNo[0], officeId, msVendor, sOrderNo[0]);
								
			SaveInvoice.SaveHandlingCharges(getFundCodeHC, HandlingChargeCode, sInvoiceRawDate, 
						gettotalHC, msReferenceNo[0], officeId, msVendor, sOrderNo[0]);
						
			SaveInvoice.SaveCreditNote(getcrditnoteFund, CreditNote, sInvoiceRawDate, 
						getcrditnote, sInvoiceNo, officeId, msVendor, sOrderNo[0]);
				
			SaveInvoice.SaveDebitNote(getdebitnoteFund, DebitNote, sInvoiceRawDate, 
						getdebitnote, sInvoiceNo, officeId, msVendor, sOrderNo[0]); 
			
			
			/* if(countTotalOrder == 1){
				System.out.println("onlyyyyy1");
				SaveInvoice.SaveDiscount(getFundCodediscount, DiscountCode, sInvoiceRawDate,
						gettotaldiscount, msReferenceNo[0], officeId, msVendor, sOrderNo[0]);
		
				SaveInvoice.SaveServiceCharges(getFundCodeSV, ServiceChargeCode, sInvoiceRawDate, 
						gettotalService, msReferenceNo[0], officeId, msVendor, sOrderNo[0]);
								
				SaveInvoice.SaveHandlingCharges(getFundCodeHC, HandlingChargeCode, sInvoiceRawDate, 
						gettotalHC, msReferenceNo[0], officeId, msVendor, sOrderNo[0]);
						
				////26072019
				SaveInvoice.SaveCreditNote(getcrditnoteFund, CreditNote, sInvoiceRawDate, 
						getcrditnote, msReferenceNo[0], officeId, msVendor, sOrderNo[0]);
				
				SaveInvoice.SaveDebitNote(getdebitnoteFund, DebitNote, sInvoiceRawDate, 
						getdebitnote, msReferenceNo[0], officeId, msVendor, sOrderNo[0]); 
			}else{
				System.out.println("morre than 1");
				for (int v = 0; v < countTotalOrder; v++) {
					SaveInvoice.SaveDiscount(getFundCodediscount, DiscountCode, sInvoiceRawDate,
							gettotaldiscount, msReferenceNo[0], officeId, msVendor, sOrderNo[v]);
			
					SaveInvoice.SaveServiceCharges(getFundCodeSV, ServiceChargeCode, sInvoiceRawDate, 
							gettotalService, msReferenceNo[0], officeId, msVendor, sOrderNo[v]);
									
					SaveInvoice.SaveHandlingCharges(getFundCodeHC, HandlingChargeCode, sInvoiceRawDate, 
							gettotalHC, msReferenceNo[0], officeId, msVendor, sOrderNo[v]);
							
					////26072019
					SaveInvoice.SaveCreditNote(getcrditnoteFund, CreditNote, sInvoiceRawDate, 
							getcrditnote, msReferenceNo[0], officeId, msVendor, sOrderNo[v]);
					
					SaveInvoice.SaveDebitNote(getdebitnoteFund, DebitNote, sInvoiceRawDate, 
							getdebitnote, msReferenceNo[0], officeId, msVendor, sOrderNo[v]);
				}
			} */
			
			/* SaveInvoice.SaveDiscount(getFundCodediscount, DiscountCode, sInvoiceRawDate,
					gettotaldiscount, msReferenceNo[0], officeId, msVendor, sOrderNo[0]);
	
			SaveInvoice.SaveServiceCharges(getFundCodeSV, ServiceChargeCode, sInvoiceRawDate, 
					gettotalService, msReferenceNo[0], officeId, msVendor, sOrderNo[0]);
							
			SaveInvoice.SaveHandlingCharges(getFundCodeHC, HandlingChargeCode, sInvoiceRawDate, 
					gettotalHC, msReferenceNo[0], officeId, msVendor, sOrderNo[0]);
					
	
			SaveInvoice.SaveCreditNote(getcrditnoteFund, CreditNote, sInvoiceRawDate, 
					getcrditnote, sInvoiceNo, officeId, msVendor, sOrderNo[0]);
			
			SaveInvoice.SaveDebitNote(getdebitnoteFund, DebitNote, sInvoiceRawDate, 
					getdebitnote, sInvoiceNo, officeId, msVendor, sOrderNo[0]); */
	}	

	boolean isSuccess = DBQuery.runBatchQuery(queryList);
	
	if(isSuccess){
	 out.println("Successful");
	}else{
		out.println("Fail");
	} 


	

%>