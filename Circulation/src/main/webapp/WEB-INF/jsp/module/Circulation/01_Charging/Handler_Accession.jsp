
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ilmu.circulation.Charging.*,java.util.*,com.ilmu.global.*,
				java.util.*"%>
    
<%

String CT03DOCNO = request.getParameter("CT03DOCNO");

boolean isAcessionNo =false;

AccessionDetails accdetails = new AccessionDetails(CT03DOCNO);
isAcessionNo=accdetails.RetrieveAccessionDetail(CT03DOCNO);

if(isAcessionNo)
{
	
}




//details=Circulation.viewtable(GL14PATR, "C");



%>