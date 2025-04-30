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
String type=request.getParameter("type");


int total = Integer.parseInt(request.getParameter("total"));
String module = request.getParameter("module");
List<String> queryList = new ArrayList<String>();
String CT04SCONV = null;
if(Unindex.RcrdExist(Handler.replaceWithA(matno), Handler.tblName(module))){
	queryList.add(Handler_BO.Delete_CTWORK(Handler.replaceWithA(matno), Handler.tblName(module)));
}

/* String leader = Unindex.checkLeader(matno);

if(leader!=null){
	Glnumb CT04COUNTER = Glnumb.getGL98VALUE("BUFPOINT");
	int counter = CT04COUNTER.getGL98VALUE();
	CT04SCONV= Handler.convUpperCase(leader);
	queryList.add(Handler_BO.InsertBO(Handler.replaceWithA(matno), "000", "#", "#",leader, 
			counter, CT04SCONV.trim(), createdate, creatime, createby,
 			modiby,DateTime.getTodaySystemDate(), DateTime.timeToDBFormat(DateTime.getTodayTime()),"A", type, Handler.tblName(module), Handler.marcType(module)));
} */
////--------
/* for (int i = 0; i < total; i++) {
	
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
 			modiby, DateTime.getTodaySystemDate(), DateTime.timeToDBFormat(DateTime.getTodayTime()),"A", type, 
 			Handler.tblName(module), Handler.marcType(module)));

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

} */

///////15112021 --> WNH CHANGE

	Set<String> newTag = new HashSet<String>();
	for (int a = 0; a < total; a++) {
		newTag.add(tag[a]);
	}

	String[] myArraynewTag = new String[newTag.size()];
	newTag.toArray(myArraynewTag);
	



for (int i = 0; i < total; i++) {
	
	System.out.println("isssssssssssssssss :"+i);
	
	
	 //Check if data has subfield. If yes remove subfield for CT05SCONV
		if(tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("005")||tag[i].equals("006")||
			tag[i].equals("007")|| tag[i].equals("008")){
			CT04SCONV= Handler.convUpperCase(raw[i]);
		}else{
			CT04SCONV= Handler.convUpperCase(Handler.rawToDisplayFormat(raw[i]));
		}
	//String CT04SCONV = Handler.convUpperCase(Handler.rawToDisplayFormat(raw[i]));
	if(CT04SCONV!=null && !CT04SCONV.trim().isEmpty()){
	Glnumb CT04COUNTER = Glnumb.getGL98VALUE("BUFPOINT");
	int counter = CT04COUNTER.getGL98VALUE();
	
	queryList.add(Handler_BO.InsertBO(Handler.replaceWithA(matno), tag[i], indi1[i], indi2[i], raw[i], 
			counter, CT04SCONV.trim(), createdate, creatime, createby,
 			modiby, DateTime.getTodaySystemDate(), DateTime.timeToDBFormat(DateTime.getTodayTime()),"A", type, 
 			Handler.tblName(module), Handler.marcType(module)));
	}

}

for (int b = 0; b < newTag.size(); b++) {
	if(Unindex.BO_isExist(Handler.replaceWithA(matno),myArraynewTag[b])){
		
		List<Unindex> retrieveRcrd = null;
		List<Unindex> searchResult = null;
		retrieveRcrd = Unindex.retrieveRcrd(matno, myArraynewTag[b]);
		
		Set<String> newretrieveRcrdPointer = new HashSet<String>();
		Set<String> newretrieveRcrdTable = new HashSet<String>();
		
		for (int a = 0; a < retrieveRcrd.size(); a++) {
			Unindex element2 = retrieveRcrd.get(a);
			
			newretrieveRcrdPointer.add(element2.getCT06POINTER());
			newretrieveRcrdTable.add(element2.getGL17TABNAME());
		}
		
		String[] pointer = new String[newretrieveRcrdPointer.size()];
		newretrieveRcrdPointer.toArray(pointer);
		
		String[] table = new String[newretrieveRcrdTable.size()];
		newretrieveRcrdTable.toArray(table);
		
		for (int c = 0; c < newretrieveRcrdPointer.size(); c++) {
			queryList.add(Unindex.updateHits(pointer[c],table[0]));
		}

		//System.out.println("GGGGGGGG ::"+pointer[0] + "," +newretrieveRcrdPointer.size() + ", "+table[0]);
		///queryList.add(Unindex.updateHits(newretrieveRcrdPointer,newretrieveRcrdTable));

	}
}


queryList.add(Unindex.updateStat(matno, "T", Handler.tblName(module)));
//queryList = Unindex.BO_isDeletable(matno);
if(Unindex.BO_isDeletable(matno,Handler.tblName(module)))
{
	queryList.add(Unindex.deleteCTPONT(matno, Handler.tblName(module)));
	queryList.add(Handler_BO.Delete_CTWORK(matno, Handler.tblName(module)));
}

boolean isSuccess = DBQuery.runBatchQuery(queryList);
if(isSuccess){
	AuditTrail.InsertAudit("CT", "CTD0002",  matno , modiby);
	out.println("Successful," + Handler.replaceWithA(matno));
}else{
	out.println("Fail");
}


%>

	
	