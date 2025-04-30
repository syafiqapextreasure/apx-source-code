<%@page import="com.ilmu.cataloging.Release_Circulation.*,com.ilmu.global.Handler"%>
<%@page import="com.ilmu.cataloging.Template_Maint.Cataloging"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<%@ page language="java" 
contentType="text/html; 
charset=utf-8" 
pageEncoding="utf-8"
import="java.sql.*"
%>
<%
String CT03DOCNO = request.getParameter("CT03DOCNO");
System.out.println("Docno" + CT03DOCNO);
//Not using the code below
//boolean statAvailable = ReleaseForCirculation.statAvailable(CT03DOCNO);
String status = ReleaseForCirculation.getRecordStat(CT03DOCNO);
String statusdesc = ReleaseForCirculation.getRecordStatdesc(status);
boolean TitleExist = ReleaseForCirculation.TitleExist(CT03DOCNO);
boolean CallNoExist = ReleaseForCirculation.CallNoExist(CT03DOCNO);
List<ReleaseForCirculation> Title = null;
List<ReleaseForCirculation> Matno = null;
List<ReleaseForCirculation> CallNo = null;
Matno = ReleaseForCirculation.Matno(CT03DOCNO);
String matno = null;
String title = null;
String callno = null;

for(ReleaseForCirculation m: Matno)
{
	matno = m.getCT03MATNO();
	if(TitleExist){
		Title = ReleaseForCirculation.Title(CT03DOCNO);
		for(ReleaseForCirculation i: Title)
		{
			title = i.getCT05SRAW();
			System.out.println("36" + i.getCT05SRAW());
			if(CallNoExist){
				CallNo = ReleaseForCirculation.CallNo(CT03DOCNO);
				for(ReleaseForCirculation j: CallNo)
				{
					callno = j.getCT05SRAW();
				}
			}
		}
	}
}

/* System.out.println("wwweew" + matno);
if(status.equals("A")){
	out.println("error"+ "?" +Handler.ifIsNull(title) + "?" + Handler.ifIsNull(callno) + "?" + matno);
}else if(status.equals("H")){
	String patron = ReleaseForCirculation.getReservedPatron(matno);
	out.println("error1"+ "?" +Handler.ifIsNull(title) + "?" + Handler.ifIsNull(callno) + "?" + matno+ "?" + patron);
}else{
	boolean update = ReleaseForCirculation.updateRelease(CT03DOCNO);
	out.println("ok"+ "?" +Handler.ifIsNull(title)+ "?" + Handler.ifIsNull(callno)+ "?" + matno+"?");
} */

////29112021 WNH UPDATE
System.out.println("wwweew" + matno);
if(!status.equals("F")){
	out.println("error"+ "?" +Handler.ifIsNull(title) + "?" + Handler.ifIsNull(callno) + "?" + matno +"?" +statusdesc+"**");
}else if(status.equals("H")){
	String patron = ReleaseForCirculation.getReservedPatron(matno);
	out.println("error1"+ "?" +Handler.ifIsNull(title) + "?" + Handler.ifIsNull(callno) + "?" + matno+ "?" + patron);
}else{
	boolean update = ReleaseForCirculation.updateRelease(CT03DOCNO);
	out.println("ok"+ "?" +Handler.ifIsNull(title)+ "?" + Handler.ifIsNull(callno)+ "?" + matno+"?");
}



%>