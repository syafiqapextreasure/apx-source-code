<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, com.ilmu.utilities.query.*,
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>

<%

String[] tag = request.getParameterValues("tag[]");
String[] indi1 = request.getParameterValues("indi1[]");
String[] indi2 = request.getParameterValues("indi2[]");
String[] raw = request.getParameterValues("raw[]");
String matno = request.getParameter("matno");
String createby = request.getParameter("createby");
String creatime = request.getParameter("creatime");
String createdate = request.getParameter("createdate");
String modiby = request.getParameter("modiby");
String modidate = request.getParameter("modidate");
String moditime = request.getParameter("moditime");
int total = Integer.parseInt(request.getParameter("total"));
List<String> queryList = new ArrayList<String>();
String CT04SCONV = null;
if(Unindex.RcrdExist(Handler.replaceWithA(matno))){
	queryList.add(Handler_BO.Delete_CTWORK(Handler.replaceWithA(matno)));
}

String leader = Unindex.checkLeader(matno);

if(leader!=null){
	Glnumb CT04COUNTER = Glnumb.getGL98VALUE("BUFPOINT");
	int counter = CT04COUNTER.getGL98VALUE();
	CT04SCONV= Handler.convUpperCase(leader);
	queryList.add(Handler_BO.InsertBO(Handler.replaceWithA(matno), "000", "#", "#",leader, 
			counter, CT04SCONV.trim(), createdate, creatime, createby,
 			modiby, modidate, moditime,"A"));
}

for (int i = 0; i < total; i++) {
	
	 //Check if data has subfield. If yes remove subfield for CT05SCONV
		if(tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("005")||tag[i].equals("006")||
				tag[i].equals("007")|| tag[i].equals("008")){
			System.out.println("Tag" + tag[i]);
			CT04SCONV= Handler.convUpperCase(raw[i]);
		}else{
			System.out.println("Tags" + raw[i]);
			CT04SCONV= Handler.convUpperCase(Handler.rawToDisplayFormat(raw[i]));
		}
	//String CT04SCONV = Handler.convUpperCase(Handler.rawToDisplayFormat(raw[i]));
	if(CT04SCONV!=null && !CT04SCONV.trim().isEmpty()){
	Glnumb CT04COUNTER = Glnumb.getGL98VALUE("BUFPOINT");
	int counter = CT04COUNTER.getGL98VALUE();
	  
	queryList.add(Handler_BO.InsertBO(Handler.replaceWithA(matno), tag[i], indi1[i], indi2[i], raw[i], 
			counter, CT04SCONV.trim(), createdate, creatime, createby,
 			modiby, modidate, moditime,"A"));

	if(Unindex.BO_isExist(Handler.replaceWithA(matno),tag[i])){
		List<Unindex> retrieveRcrd = null;
		List<Unindex> searchResult = null;
		retrieveRcrd = Unindex.retrieveRcrd(matno, tag[i]);
		for(Unindex j: retrieveRcrd)
		{
				queryList.add(Unindex.updateHits(j.getCT06POINTER(),j.getGL17TABNAME()));

		}
	}
	}

}


queryList.add(Unindex.updateStat(matno, "T"));
//queryList = Unindex.BO_isDeletable(matno);
if(Unindex.BO_isDeletable(matno))
{
	queryList.add(Unindex.deleteCTPONT(matno));
}

boolean isSuccess = DBQuery.runBatchQuery(queryList);
if(isSuccess){
	out.println("Successful," + Handler.replaceWithA(matno));
}else{
	out.println("Fail");
}


%>

	
	