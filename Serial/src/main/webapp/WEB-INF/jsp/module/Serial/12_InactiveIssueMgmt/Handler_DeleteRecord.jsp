<%@ page import="java.text.*,java.util.*, com.ilmu.serial.InactiveIssueMgmt.*,
				com.ilmu.utilities.query.*"%>
<%
String[] orderno = request.getParameterValues("orderno[]");
String[] copyno = request.getParameterValues("copyno[]");
String[] issueno = request.getParameterValues("issueno[]");
int total = Integer.parseInt(request.getParameter("total"));
List<String> queryList = new ArrayList<String>();

for (int i = 0; i < total; i++) {
	queryList.add(Inactive_IssueMgmt.purgeRecord(orderno[i],copyno[i],issueno[i]));
}

boolean isSuccess = DBQuery.runBatchQuery(queryList);

if(isSuccess){
 out.println("Successful");
}else{
	out.println("Fail");
} 

/* String order = request.getParameter("orderno");
String ctrlno = Cancel_Order.getCtrlNo(order);
String query = Cancel_Order.deleteACORDD(order);
boolean isSuccess = DBQuery.runQuery(query);
if(isSuccess){
	
	 out.println("Successful," + ctrlno);
	}else{
		out.println("Fail");
	} */
%>