<%@page import="java.net.*,java.io.*, com.ilmu.serial.Ordering.*,com.ilmu.serial.Renewal.*, java.util.*,com.ilmu.utilities.query.*"%>

<%
String orderno = request.getParameter("orderno");
String[]  array = orderno.split(",");
List<String> queryList = new ArrayList<String>();
 for(int i=0;i<array.length;i++)
 {
	 queryList.add(Renewal.DeleteIssue(array[i]));
	 queryList.add(Renewal.DeleteOrder(array[i]));
 }
 
 boolean isSuccess = DBQuery.runBatchQuery(queryList);
 if(isSuccess){
 	out.println("Successful");
 }else{
 	out.println("Fail");
 }

%>