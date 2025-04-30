<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.serial.RequestForPayment.*, 
					java.util.List, com.ilmu.utilities.query.*, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<%
	String[] print = request.getParameterValues("print[]");
	String vendor = request.getParameter("vendor");
	String payment = request.getParameter("payment");
	String grpList = request.getParameter("grpList");
	String msOrderNoList = request.getParameter("orderno");

	boolean bSuccessful = false;
	
	System.out.println("Print" + print);
	//Check if can proceed to printing invoice
	if(print!=null){
		if(!Arrays.asList(print).contains("Y")){
			out.println("120");
			bSuccessful = false;
		}else{
			System.out.println("Print`1`1" + print);
			bSuccessful = true;
		}
	}else{
		bSuccessful = true;
	}
	
	if(bSuccessful){
		 if (RequestForPayment.UpdateReferNo(msOrderNoList, vendor,payment,grpList)){
			 bSuccessful = true;
		  }
		 
		 System.out.println("Order" +msOrderNoList+vendor +grpList);
		   if (RequestForPayment.UpdateOrders(msOrderNoList, vendor,grpList)){
			   bSuccessful = true;
		   }
		   
		   if(bSuccessful){
			   out.println("success");
		   }
		
		 }
		   

				 
	//} 
%>