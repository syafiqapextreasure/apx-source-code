 <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, com.ilmu.utilities.query.*,
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>

<%

String tag = request.getParameter("CT02TAG");
String indi1 = request.getParameter("CT02INDI1");
String indi2 = request.getParameter("CT02INDI2");
String raw = request.getParameter("CT02RAW");
String matno = request.getParameter("CT02MATNO");
String createby = request.getParameter("CT02CREBY");
String creatime = request.getParameter("CT02CRETIME");
String createdate = request.getParameter("CT02CREDATE");
String modiby = request.getParameter("CT02MODIBY");
String modidate = request.getParameter("CT02MODIDATE");
String moditime = request.getParameter("CT02MODITIME");
/* int total = Integer.parseInt(request.getParameter("total")); */
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


	
	 //Check if data has subfield. If yes remove subfield for CT05SCONV
		if(tag.equals("001")||tag.equals("002")||tag.equals("003")||tag.equals("005")||tag.equals("006")||
				tag.equals("007")|| tag.equals("008")){
			System.out.println("Tag" + tag);
			CT04SCONV= Handler.convUpperCase(raw);
		}else{
			System.out.println("Tags" + raw);
			CT04SCONV= Handler.convUpperCase(Handler.rawToDisplayFormat(raw));
		}
	//String CT04SCONV = Handler.convUpperCase(Handler.rawToDisplayFormat(raw));
	if(CT04SCONV!=null && !CT04SCONV.trim().isEmpty()){
	Glnumb CT04COUNTER = Glnumb.getGL98VALUE("BUFPOINT");
	int counter = CT04COUNTER.getGL98VALUE();
	  
	queryList.add(Handler_BO.InsertBO(Handler.replaceWithA(matno), tag, indi1, indi2, raw, 
			counter, CT04SCONV.trim(), createdate, creatime, createby,
 			modiby, modidate, moditime,"A"));

	if(Unindex.BO_isExist(Handler.replaceWithA(matno),tag)){
		List<Unindex> retrieveRcrd = null;
		List<Unindex> searchResult = null;
		retrieveRcrd = Unindex.retrieveRcrd(matno, tag);
		for(Unindex j: retrieveRcrd)
		{
				queryList.add(Unindex.updateHits(j.getCT06POINTER(),j.getGL17TABNAME()));

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
