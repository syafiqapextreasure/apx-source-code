<%@page import="com.ilmu.circulation.Charging.*,
				java.util.*,com.ilmu.global.*"%>

<%



String CT03DOCNO = request.getParameter("CT03DOCNO");
String GL14PATR = request.getParameter("GL14PATR");
System.out.println("CT03DOCNO"+CT03DOCNO);
System.out.println("GL14PATR"+GL14PATR);
Discharging chr= null;
List<Circulation> details = null;

System.out.println("CT03DOCNO"+CT03DOCNO);

chr= new Discharging();

boolean dischargesuccessfull=false;
dischargesuccessfull=chr.discharge(CT03DOCNO,GL14PATR);
System.out.println("Message" + chr.getErrMessage());
out.println(chr.getErrMessage());
/* details=Circulation.viewtable(GL14PATR, "C");  */


%>