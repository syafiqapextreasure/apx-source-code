<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ilmu.serial.Renewal.*, com.ilmu.global.serial.*,com.ilmu.global.*,com.ilmu.utilities.query.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<%

Glnumb CTRLNO = Glnumb.getGL98VALUE("SERORDNO");
String noOfIssues = request.getParameter("noOfIssues");
String quantity = request.getParameter("quantity");
String[] vollbl = request.getParameterValues("vollbl[]");
String[] issulbl = request.getParameterValues("issulbl[]");
String[] expdate = request.getParameterValues("expdate[]");
String matno = request.getParameter("matno");
String freq = request.getParameter("freq");
String order = Handler.concatMatno(String.valueOf(CTRLNO.getGL98VALUE()));
//String order = "saaa";
String stat = "O";
String invstat = "O";
int issueNo = 1;
int total = Integer.parseInt(request.getParameter("total"));
System.out.println("Ttoa" + total);
List<String> queryList = new ArrayList<String>();
for (int i=0;i<total;i++){
	  queryList.add( IssuesPattern.InsertIssuePattern(order,issueNo,quantity,vollbl[i],issulbl[i],expdate[i],matno,freq));
 
	issueNo = issueNo + 1;
	 if(issueNo>Integer.parseInt(noOfIssues)){
		break;
	}  
	/* boolean exist = IssuesPattern.PatternExist(order);

	if(exist){
		boolean deletePattern = IssuesPattern.deletePattern(order);
		
		if(deletePattern){
			boolean insert = IssuesPattern.InsertIssuePattern(order);
		}
	} */
}

 boolean isSuccess = DBQuery.runBatchQuery(queryList);
if(isSuccess){
	out.println(order);
	}else{
		out.println(order);;
	} 

//out.println(order);
%>	