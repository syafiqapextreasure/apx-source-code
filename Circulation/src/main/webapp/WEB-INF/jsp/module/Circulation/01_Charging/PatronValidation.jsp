
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ilmu.circulation.Charging.*,
				java.util.*"%>
    
<%
String GL14PATR = request.getParameter("GL14PATR");
System.out.println("Test 1"+GL14PATR);
boolean isPatronvalid =false;
Patron patr = new Patron("GL14PATR");
if(patr.getMsPatronName("GL14PATR")!=null)
{
	if(patr.getMsPatronStatus("GL14PATR")!=null)
	{
		if (patr.getMsPatronElig().equals("Y"))
		{
			isPatronvalid=true;
			
			response.sendRedirect("Charging.jsp");
		}
		
	}
}

//System.out.println("Test 1"+GL14PATR);

//Patron patr = new Patron(GL14PATR);
//System.out.println("test2"+patr.getMsPatronName());

if(isPatronvalid)
{
out.println(patr.getMsPatronName());
System.out.println("test2"+patr.getMsPatronName());
}
/*List<PatronDetails> details = null;
details = PatronDetails.getPatDetail(GL14PATR);

for(PatronDetails i: details){
	System.out.println(i.getGL14PATR());
	out.println(i.getGL14PATR()+","+i.getGL14NAME()+","+i.getGL14CATE()+","+i.getGL14MEMDATE()
				+","+i.getGL14EXPDATE()+","+i.getGL14STAT());
}*/
%>