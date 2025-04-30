<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.serial.RecordForPayment.*, 
					java.util.List, com.ilmu.utilities.query.*, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<%
System.out.println("sdsdsdsds");
	//String[] print = request.getParameterValues("print[]");
	String criteria = request.getParameter("criteria");
	String vendor = Handler.convUpperCase(request.getParameter("vendor"));
	String text = request.getParameter("text");
	String invno = request.getParameter("voucherno");
	String chequeno = request.getParameter("chequeno");
	String invdate = request.getParameter("voucherdate");
	String chequedate = request.getParameter("chequedate");
	String invoice = request.getParameter("invoice");
	boolean bSuccessful = false;
/* 	boolean bSuccessful = false;
	//Check if can proceed to printing invoice
	if(print!=null){
		if(!Arrays.asList(print).contains("Y")){
			out.println("120");
			bSuccessful = false;
		}else{
			bSuccessful = true;
		}
	}else{
		bSuccessful = true;
	}
	 */
System.out.println("sdsdsdsds" + vendor);
		 if (RecordForPayment.UpdateInvoice(criteria,text,vendor,invno, chequeno,
					invdate, chequedate)){
			  if (RecordForPayment.UpdateOrders(invoice,vendor)){
				   bSuccessful = true;
			   }

		  }
		 
		   
		   if(bSuccessful){
			   out.println("success");
		   }
		   

				 
	//} 
%>