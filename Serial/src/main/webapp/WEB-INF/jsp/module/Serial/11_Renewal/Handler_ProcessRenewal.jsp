<%@page import="java.net.*,java.io.*, com.ilmu.serial.Ordering.*,com.ilmu.serial.Renewal.*, java.util.*,com.ilmu.utilities.query.*"%>

<%
String referenceNo = request.getParameter("referenceNo");
String action = request.getParameter("action");
String orderno = request.getParameter("orderno");
String ordermode = request.getParameter("ordermode");
String currency = request.getParameter("currency");
String prate = request.getParameter("prate");
String fprice = request.getParameter("fprice");
String lprice = request.getParameter("lprice");
String vendor = request.getParameter("vendor");
String[] orderList;
System.out.println("Order" + orderno);
if(action.equals("AddRecord")){
	 boolean add = Renewal.CreateReference(orderno, ordermode,currency,prate,fprice,lprice, vendor);
	
	 if(add){
		 out.println("success");
	 }
}else if(action.equals("UpdateRef")){
	
	 String[]  array = orderno.split(",");
	 boolean update = false;
	  for(int i=0;i<array.length;i++)
      {
			 update = Renewal.updateRenewal(array[i], referenceNo);
      }
		
	 if(update){
		 String[] oldOrder = request.getParameterValues("oldOrder[]");
		 int total = Integer.parseInt(request.getParameter("total"));
		 //String query = "";
		 List<String> queryList = new ArrayList<String>();
		  for(int i=0;i<total;i++)
	      {
			 // update = Renewal.updatePreviousOrder(oldOrder[i]);
			  queryList.add(Renewal.updatePreviousOrder(oldOrder[i]));
	      }
		  
			boolean isSuccess = DBQuery.runBatchQuery(queryList);
			if(isSuccess){
				 out.println("Successful");
				}else{
					out.println("Fail");
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