<%@page import="java.net.*,java.io.*, com.wilmu.acquisition.ordering.data.*"%>

<%
String referenceNo = request.getParameter("referenceNo");
String action = request.getParameter("action");
String orderno = request.getParameter("orderno");
String ordermode = request.getParameter("ordermode");
System.out.println("Order" + orderno);
if(action.equals("IsUniqueReferenceNo")){
	 boolean exist = Ordering.IsUniqueReferenceNo(referenceNo);
	 
	 if(exist){
		 out.println("exist");
	 }else{
		 boolean update = Ordering.UpdateReference(referenceNo, orderno, ordermode);
			
			if(update){
				 boolean checkReq = Ordering.checkReq(orderno);
				if(checkReq){
					out.println("Req_Exist");
				}else{
					out.println("Success");
				}
			}
	 }
}else{
	boolean update = Ordering.UpdateReference(referenceNo, orderno, ordermode);
	
	if(update){
		 boolean checkReq = Ordering.checkReq(orderno);
			if(checkReq){
				out.println("Req_Exist");
			}else{
				out.println("Success");
			}
	}
}
%>