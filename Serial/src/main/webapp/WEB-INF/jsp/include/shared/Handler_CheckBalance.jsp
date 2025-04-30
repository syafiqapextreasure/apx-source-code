<%@page import="com.ilmu.fundacc.FundAcc.*"%>
<%@page import="com.ilmu.global.*"%>
<%@page import="com.ilmu.utilities.query.*,java.util.*, java.math.BigDecimal, java.math.RoundingMode"%>
<%

String action = request.getParameter("action");

String balance = "";

	if(action.equals("check")){
	String code = request.getParameter("code");
	Double amt = Double.parseDouble(Handler.decimalConversion(request.getParameter("amount")));
		
	 balance = FundDistribution.getBalance(code);
	 
	 balance = Handler.decimalConversion(balance);
	 
	 if(amt > Double.parseDouble(balance)){
		 out.println("fail");
	 }
	 
	}else if(action.equals("insert")){
		int totalorder = Integer.parseInt(request.getParameter("totalorder"));
		String[] code = request.getParameterValues("code[]");
		String[] amt = request.getParameterValues("amount[]");
		String[] order = request.getParameterValues("orderno[]");
		String vendor = request.getParameter("vendor");
		String ref = request.getParameter("ref");
		String txcd = request.getParameter("txcd");
		int total = Integer.parseInt(request.getParameter("total"));
		String desc = request.getParameter("desc");
		List<String> queryList = new ArrayList<String>();
		double amount = 0.00;
		int txno = 0;
		
		BigDecimal amount2 = BigDecimal.ZERO;   
		BigDecimal totalorder2 = BigDecimal.ZERO; 
		
/* 
		 for (int i = 0; i < total; i++) {
			 System.out.println(FundDistribution.InsertFund(code[i], txcd, ref, vendor, amt[i], desc));
			 queryList.add(FundDistribution.InsertFund(code[i], txcd, ref, vendor, amt[i], desc));
		 } */
	

		 for (int i = 0; i < total; i++) {
			 Glnumb no = Glnumb.getGL98VALUE("ACCTXNO");
			 txno = no.getGL98VALUE();
			 
			 System.out.println(FundDistribution.InsertFund(code[i], txcd, ref, vendor, amt[i], desc, txno));
			 queryList.add(FundDistribution.InsertFund(code[i], txcd, ref, vendor, amt[i], desc, txno));
			 
			 //Insert into FAMSTR
			 boolean insert = false;
			 if(txcd.equals("200")){
				 insert = FundDistribution.insertComm(Double.parseDouble(amt[i]),code[i]);
			 }else if(txcd.equals("600")){
				 insert=   FundDistribution.FAPostTrx(code[i], "600", Double.parseDouble(amt[i]));
			 }
			 
			 if(txcd.equals("200")){
			 /* for (int j = 0; j < totalorder; j++) {
				 //Insert into ACDIST
				 amount =(Double.parseDouble(amt[i])/totalorder);
				 String query1 = FundDistribution.InsertSEDIST(ref, txcd, code[i], String.valueOf(amount), order[j], txno);
				 queryList.add(query1);
			 } */
				///21092020
				 amount2 = new BigDecimal(amt[i]); 
				 System.out.println("amount2 isssss : "+amount2);
				 
				 totalorder2= new BigDecimal(totalorder);
				 System.out.println("totalorder2 isssss : "+totalorder2);
				 
				 BigDecimal result = amount2.divide(totalorder2, 2, RoundingMode.HALF_UP);
				 System.out.println("result1111 isssss : "+result);

				 BigDecimal subValue = amount2; 
				 System.out.println("subValue isssss : "+subValue);
				 
				 for (int j = 0; j < totalorder; j++) {
					 
					 
					 
					 if(j == (totalorder-1)){
						 System.out.println("j == totalorder ");
						 System.out.println("subValue "+j+" isssss : "+subValue +"orderno " +order[j]); 
						 
						//Insert into ACDIST
						 String query1 = FundDistribution.InsertSEDIST(ref, txcd, code[i], String.format ("%.2f", subValue), order[j], txno);
						 queryList.add(query1); 
					 }else{
						 subValue = subValue.subtract(result);
						 System.out.println("subValue "+j+" isssss : "+result +"orderno " +order[j]);
						 
						//Insert into ACDIST
						 String query1 = FundDistribution.InsertSEDIST(ref, txcd, code[i], String.format ("%.2f", result), order[j], txno);
						 queryList.add(query1);
					 }
					 
					 
				 }
			 }
		 }
			boolean isSuccess = DBQuery.runBatchQuery(queryList);
			
			if(isSuccess){
			 out.println("success");
			}else{
				out.println("Fail");
			} 
		//out.println("success");
	}
%>