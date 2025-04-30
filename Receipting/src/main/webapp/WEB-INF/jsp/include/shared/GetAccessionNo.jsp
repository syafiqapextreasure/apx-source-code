<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ilmu.global.*, com.ilmu.cataloging.Acc_Maint.*" %>
<%
/* String action = request.getParameter("action");

if(action.equals("newAccNo")){
	 out.println(Glnumb.GetCurrentAccessionNo());
}else{ */
	/* String accNo = request.getParameter("accNo");
	System.out.println(accNo); */
	//boolean accnoExist = CTRetrieve.accnoExist(accNo);
	String accNo = Glnumb.GetCurrentAccessionNo();
	while(accNo!=null){
		String StringaccNo = accNo;
		int n = StringaccNo.length();
		int count = 10;
		for(int i=n;i<count;i++){
	    //$('#AccNo').val(accNo);
	    accNo= "0" + accNo;
	   
		}
		System.out.println("F" + accNo);
		boolean accnoExist = CTRetrieve.accnoExist(accNo);
		if(!accnoExist){
			break;
		}else{
			Glnumb CT03DOCNO = Glnumb.getGL98VALUE("ACCNO");
			accNo= String.valueOf(CT03DOCNO.getGL98VALUE());
			System.out.println("L" + accNo);
			continue;
		}
		
	} 
	
	out.println(accNo);
//}
/* String accNo = Glnumb.GetCurrentAccessionNo() ;
boolean accnoExist = CTRetrieve.accnoExist(accNo);
String newAccNo = "";
while(accnoExist){
	System.out.println(Glnumb.getGL98VALUE("ACCNO"));
} */

%>
<%-- <%= Glnumb.GetCurrentAccessionNo() %>
 --%>