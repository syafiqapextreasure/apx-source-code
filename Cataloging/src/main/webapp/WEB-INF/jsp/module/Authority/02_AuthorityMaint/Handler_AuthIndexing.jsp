<!-- 03-7-2019 : Change duplication check for tags. Check all the tags that cannot be duplicated instead of looping the table and check if 
				tag is duplicated or not. This is to speed up duplication check. -->

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.global.Glnumb, com.ilmu.utilities.query.*, 
					com.ilmu.global.*,java.text.*,java.util.*, com.ilmu.authority.AutMaint.*"%>

<%

String[] tag = request.getParameterValues("tag[]");
String[] indi1 = request.getParameterValues("indi1[]");
String[] indi2 = request.getParameterValues("indi2[]");
String[] raw = request.getParameterValues("rawdata[]");
String matno = request.getParameter("matno");
String createby = request.getParameter("createby");
String creatime = request.getParameter("creatime");
String createdate = request.getParameter("createdate");
String modiby = request.getParameter("modiby");
String modidate = request.getParameter("modidate");
String moditime = request.getParameter("moditime");
String type = request.getParameter("type");
int total = Integer.parseInt(request.getParameter("total"));
String status ="A";
String CT04SCONV = null;
String userName = (String) session.getAttribute("username");
String bufferno = null;
boolean next = false;
String action = request.getParameter("action");
String tag006 = request.getParameter("tag006");
String tag007 = request.getParameter("tag007");
String tag008 = request.getParameter("tag008");
String module = request.getParameter("module");
System.out.println("008" + tag008);
//Check for madatory tag
List<BO_Validation> BO_Validations = null;
BO_Validations = BO_Validation.mandatoryTag(Handler.marcType(module));
String mandatory = null;

for(BO_Validation i: BO_Validations)
{
	if(Arrays.asList(tag).contains(i.getGL17TAG())){
		next = true;
	}else{
		out.println("No_MandatoryTag," + i.getGL17TAG());
		next = false;
	} 

} 


System.out.println("Dulicate" + next + action);
if(next && action.equals("DuplicateChk")){
	List<BO_Validation> duplicateTerm = BO_Validation.duplicateTerm(Handler.marcType(module));
	for(BO_Validation i: duplicateTerm)
	{
		if(Arrays.asList(tag).contains(i.getGL17TAG())){
			int position = Arrays.asList(tag).indexOf(i.getGL17TAG());
			System.out.println("Position" + position);
			List<String> list = Arrays.asList(raw); 
			List<String> taglist = Arrays.asList(tag); 
			boolean flag = list.get(position).substring(0, 1).equals("|");
			
			String data = "";
			
			if(flag==true){
				data= Handler.convUpperCase(GlobalValidation.removeSubfWithNoRecord(list.get(position)));
			}else{
				data = list.get(position);
			}
			
			boolean duplicate = false;
		 	
		 	//If duplicate yes, check if record exist
		 	if(data.trim().length()!=0){
		 		duplicate = Indexing.SCONVDuplicateChk(Handler.removeSubfield(data), taglist.get(position));
		 	}
		 	
		 	if(duplicate){
		 		out.println(duplicate);
		 		System.out.println("Test1");
		 		next = false;
		 		break;
		 	}else{
		 		next = true;
		 	}
			
			//next = false;
		}else{
			//out.println("No_MandatoryTag," + i.getGL17TAG());
			next = true;
		} 

	} 
}else{
	next = true;
}

if(matno.contains("A")){
	bufferno = matno;
	matno =  matno.replace('A', '0');
	
}else{
	bufferno = matno;
	Glnumb CTRLNO = Glnumb.getGL98VALUE("CATMATERIALNO");
	matno = Handler.concatMatno(String.valueOf(CTRLNO.getGL98VALUE()+1));
}

HashMap<String, String> valueStr = new HashMap<String, String>();
HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
List<String> insertPointer = new ArrayList<String>();
List<String> insertCttable = new ArrayList<String>();
List<String> queryList = new ArrayList<String>();
List<String> tablenames = new ArrayList<String>();
List<String> duplicateData = new ArrayList<String>();

boolean existMatno = Indexing.CT02MATNO_Exist(matno, Handler.tblName(module));

if(!Arrays.asList(tag).contains("000")){
	queryList.add(Indexing.insertLeader(matno, ""));
}

if(existMatno)
{
	queryList.add(Indexing.updateAuthCTMATM(matno, userName, DateTime.getTodaySystemDate(), DateTime.timeToDBFormat(DateTime.getTodayTime()), 
					type,tag006, tag007, tag008,Handler.tblName(module)));
	
}
else{
	
	queryList.add(Indexing.addAuthCTMATM(matno, createby, createdate, creatime, userName, DateTime.getTodaySystemDate(), 
									DateTime.timeToDBFormat(DateTime.getTodayTime()),type,tag006, tag007, tag008,Handler.tblName(module)));
	System.out.println("reindexnew");
} 

for (int i = 0; i < total; i++) {
	String indicator1 = "";
	String indicator2 = "";
	if((indi1[i].trim()).isEmpty()){
		indicator1 = "#";
	}else{
		indicator1 =indi1[i];
	}
	
	if((indi2[i].trim()).isEmpty()){
		indicator2 = "#";
	}else{
		indicator2 = indi2[i];
	}
	System.out.println("Raw" + raw[i] + "ss");
if(raw[i]!=null){
	 //Check if data has subfield. If yes remove subfield for CT05SCONV
	if(tag[i].equals("006")||tag[i].equals("007")||tag[i].equals("008")||
					tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("004")||
					tag[i].equals("005")||tag[i].equals("009")){
		CT04SCONV= Handler.convUpperCase(raw[i]);
	}else if(tag[i].trim().equals("000")){
		queryList.add(Indexing.insertLeader(matno, Handler.buildString(raw[i])));
	}else{
		CT04SCONV= Handler.convUpperCase(Handler.removeNumberSubf(raw[i]));
	}

	if(CT04SCONV!=null && !CT04SCONV.trim().isEmpty()){
		
	//Check if converted data exist
	 boolean existCT05SCONV = Indexing.CT05SCONV_Exist(CT04SCONV, tag[i]);
	
	 if(!tag[i].trim().equals("000")){
		if(existCT05SCONV)
		{
			//String rawdata = Indexing.getCT05SRAW(raw[i], tag[i]);
			
			List<Indexing> rawdata = Indexing.checkData(CT04SCONV, tag[i]);
			boolean continueloop = false;
			for(Indexing j: rawdata)
			{
				
				String compareStrg = "";
				//System.out.println("Update raw hit count" + j.getCT05SRAW() + GlobalValidation.removeSubfWithNoRecord(raw[i]));
				if(tag[i].equals("006")||tag[i].equals("007")||tag[i].equals("008")||
					tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("004")||
					tag[i].equals("005")||tag[i].equals("009")){
					compareStrg = raw[i];
				}else{
					compareStrg = GlobalValidation.removeSubfWithNoRecord(raw[i]);
				}
				if(j.getCT05SRAW().equals(compareStrg)){

					queryList.add(Indexing.updateCT05HITS(j.getCT05POINTER(),tag[i]));
					int totalcount = Indexing.TotalSCONVRcrd(CT04SCONV,tag[i]);
					//Indexing getPointer = Indexing.getPointer(j.getCT05SRAW(), tag[i]);
					queryList.add(Indexing.addCTPONT(j.getCT05POINTER(), matno, tag[i], indicator1, indicator2, status, Handler.marcType(module),
							Handler.tblName(module)));
					continueloop = false;
					break;
				}else{
					continueloop = true;
				}
			}
			
			if(continueloop){
				String columnName = "CATPOINTERNO";
				Glnumb pointer = Glnumb.getGL98VALUE(columnName);
				int totalcount = Indexing.TotalSCONVRcrd(CT04SCONV,tag[i]);
				//Glnumb.incHits(columnName);

				/* int totalcount = Indexing.TotalSCONVRcrd(CT04SCONV,tag[i]);
					System.out.println("Counts" + totalcount);	 */
				if(tag[i].equals("006")||tag[i].equals("007")||tag[i].equals("008")||
							tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("004")||
							tag[i].equals("005")||tag[i].equals("009")){
					queryList.add(Indexing.addCTTable(pointer.getGL98VALUE(), raw[i], Handler.convUpperCase(raw[i]), tag[i], totalcount, 
								  0,Aut_Record.chkusedterm(tag[i], Handler.marcType(module))));
				}else{
					queryList.add(Indexing.addCTTable(pointer.getGL98VALUE(), GlobalValidation.removeSubfWithNoRecord(raw[i]), 
								  Handler.convUpperCase(Handler.removeNumberSubf(raw[i])), tag[i], totalcount, 0,   
								  Aut_Record.chkusedterm(tag[i], Handler.marcType(module))));
				}

			}
		} else{
			String columnName = "CATPOINTERNO";
			Glnumb pointer = Glnumb.getGL98VALUE(columnName);
			
			int totalcount = Indexing.TotalSCONVRcrd(CT04SCONV,tag[i]);			
	    	if(tablenames.contains(Bibliography.getMarkTagTableName(tag[i])) && duplicateData.contains(CT04SCONV)){
	    		int countA=Collections.frequency(tablenames, Bibliography.getMarkTagTableName(tag[i]));
	    		totalcount = totalcount + countA;
	    	}
    		tablenames.add(Bibliography.getMarkTagTableName(tag[i]));
    		duplicateData.add(CT04SCONV);
			//Glnumb.incHits(columnName);
			//int totalcount = Indexing.TotalSCONVRcrd(CT04SCONV,tag[i]);
			
			if(tag[i].equals("006")||tag[i].equals("007")||tag[i].equals("008")||
						tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("004")||
						tag[i].equals("005")||tag[i].equals("009")){
				queryList.add(Indexing.addCTTable(pointer.getGL98VALUE(), raw[i], Handler.convUpperCase(raw[i]), tag[i], totalcount, 0, 
							  Aut_Record.chkusedterm(tag[i], Handler.marcType(module))));
			}else{
				queryList.add(Indexing.addCTTable(pointer.getGL98VALUE(), GlobalValidation.removeSubfWithNoRecord(raw[i]), 
							  Handler.convUpperCase(Handler.removeNumberSubf(raw[i])), tag[i], totalcount, 0,  
							  Aut_Record.chkusedterm(tag[i], Handler.marcType(module))));
			}
			queryList.add(Indexing.addCTPONT(pointer.getGL98VALUE(), matno, tag[i], indicator1, indicator2, status, Handler.marcType(module),
							Handler.tblName(module)));
		}
	 }/* else if(tag[i].equals("000")){
			queryList.add(Indexing.insertLeader(matno, Handler.buildString(raw[i])));
	 }  */
}
	}
}
queryList.add(Indexing.deleteCTWORK(bufferno, Handler.tblName(module)));
System.out.println("Next" + next);
if(next){
	boolean isSuccess = DBQuery.runBatchQuery(queryList);
	
	if(isSuccess){
	 out.println("Successful," + matno);
	}else{
		out.println("Fail");
	}
}



/* String CT04MATNO = request.getParameter("CT02MATNO");
String bufferno = request.getParameter("CT04MATNO");
String CT06TAG = request.getParameter("CT06TAG");
String CT06INDI1 = request.getParameter("CT06INDI1");
String CT06INDI2 = request.getParameter("CT06INDI2");
String CT05SRAW = request.getParameter("CT05SRAW");
String CT06STATUS ="A";
String CT02CREBY = request.getParameter("CT02CREBY");
String CT02CRDATE = request.getParameter("CT02CREDATE");
String CT02CRETIME = request.getParameter("CT02CRETIME");
String CT02MODIBY = request.getParameter("CT02MODIBY");
String CT02MODIDATE = request.getParameter("CT02MODIDATE");
String CT02MODITIME = request.getParameter("CT02MODITIME");
String subString = CT04MATNO.substring(0,1);
String CT02MATNO = null;
String columnName = null;
int CT06POINTER = 0;
String CT04SCONV = null;;
String userName = (String) session.getAttribute("username");

System.out.println("Matno" + CT04MATNO);
if(CT06TAG.equals("001")||CT06TAG.equals("002")||CT06TAG.equals("003")||CT06TAG.equals("005")||CT06TAG.equals("006")||
		CT06TAG.equals("007")|| CT06TAG.equals("008")){
	CT04SCONV= Handler.convUpperCase((CT05SRAW));
}else{
	CT04SCONV= Handler.convUpperCase(Handler.rawToDisplayFormat(CT05SRAW));
}

System.out.println("conv" + CT04SCONV);
if(subString.equals("A")){
	System.out.println("Replace Matno");
	CT02MATNO = Handler.replaceWith0(CT04MATNO);
}else{
	CT02MATNO = CT04MATNO;
} */

/* else{
	columnName = "CATMATERIALNO";
	Glnumb MATNO = Glnumb.getGL98VALUE(columnName);
	CT02MATNO = MATNO.toString();
	Glnumb.incHits(columnName);
}  */



/* System.out.println("Tag" + CT06TAG);
//if(!CT06TAG.equals("000")){
	if(CT04SCONV!=null && !CT04SCONV.trim().isEmpty()){
		System.out.println("Tag12" + CT06TAG);
boolean existCT05SCONV = Indexing.CT05SCONV_Exist(CT04SCONV, CT06TAG);
System.out.println("Tag1" + CT06TAG);
if(existCT05SCONV)
{
	Indexing.updateCT05COUNT(CT05SRAW,CT06TAG);
	System.out.println("reindexCONVExist");
}


boolean existCT05SRAW = Indexing.CT05SRAW_Exist(CT05SRAW, CT06TAG);
System.out.println("Tag2" + CT06TAG);
if(existCT05SRAW)
{
	System.out.println("Tag3" + CT06TAG);
	Indexing.updateCT05HITS(CT05SRAW,CT06TAG);
	Indexing getPointer = Indexing.getPointer(CT05SRAW, CT06TAG);
	System.out.println(getPointer.getCT05POINTER()+CT02MATNO+CT06TAG+CT06INDI1+CT06INDI2+CT06STATUS);
	//Indexing.addCTPONT(getPointer.getCT05POINTER(), CT02MATNO, CT06MARC, CT06TAG, CT06INDI1, CT06INDI2, CT06STATUS);
	Indexing.addCTPONT(getPointer.getCT05POINTER(), CT02MATNO, CT06TAG, CT06INDI1, CT06INDI2, CT06STATUS);
	System.out.println("reindexRAWExist");
}else{
	System.out.println("Tag4" + CT06TAG);
	columnName = "CATPOINTERNO";
	Glnumb pointer = Glnumb.getGL98VALUE(columnName);
	Glnumb.incHits(columnName);
	//System.out.println(pointer.getGL98VALUE()+CT05SRAW+Handler.convUpperCase(Handler.removeSubfield(CT05SRAW))+CT06TAG);
	if(CT06TAG.equals("001")||CT06TAG.equals("003")||CT06TAG.equals("005")||CT06TAG.equals("006")||
			CT06TAG.equals("007")|| CT06TAG.equals("008")){
		Indexing.addCTTable(pointer.getGL98VALUE(), CT05SRAW, Handler.convUpperCase(CT05SRAW), CT06TAG);
	}else{
		Indexing.addCTTable(pointer.getGL98VALUE(), CT05SRAW, Handler.convUpperCase(Handler.removeSubfield(CT05SRAW)), CT06TAG);
	}
	//Indexing.addCTPONT(CT06POINTER, CT02MATNO, CT06MARC, CT06TAG, CT06INDI1, CT06INDI2, CT06STATUS);
	Indexing.addCTPONT(pointer.getGL98VALUE(), CT02MATNO, CT06TAG, CT06INDI1, CT06INDI2, CT06STATUS);
	System.out.println("reindexRAWNo");
}
}
boolean existMatno = Indexing.CT02MATNO_Exist(CT02MATNO);

if(existMatno)
{
	System.out.println("Tag5" + CT06TAG);
	boolean update = Indexing.updateCTMATM(CT02MATNO, userName, CT02MODIDATE, CT02MODITIME);
	System.out.println("reindexupdate");
}
else{
	System.out.println("Tag6" + CT02MATNO);
	System.out.println("User" +CT02CREBY );
	System.out.println("User" +CT02CRDATE );
	System.out.println("User" +CT02CRETIME );
	System.out.println("User" +CT02MODIDATE );
	System.out.println("User" +CT02MODITIME );
	
	Indexing.addCTMATM(CT02MATNO, CT02CREBY, CT02CRDATE, CT02CRETIME, userName, CT02MODIDATE, CT02MODITIME);
	System.out.println("reindexnew");
} 

System.out.println("Tags" + CT06TAG);
if(CT06TAG.equals("000")){
	System.out.println("Tags11" + CT06TAG);
	//CT04SCONV = CT05SRAW;
	Indexing.insertLeader(CT02MATNO, Handler.buildString(CT05SRAW));
} */
//}
/* 
if(CT06TAG.equals("000")){
	System.out.println("Tag 000");
	Indexing.insertLeader(CT02MATNO, Handler.buildString(CT05SRAW));
}
System.out.println("cs" + bufferno); */
/* Indexing.deleteCTWORK(bufferno);
System.out.println("Tag7" + CT06TAG); */
 
%>


