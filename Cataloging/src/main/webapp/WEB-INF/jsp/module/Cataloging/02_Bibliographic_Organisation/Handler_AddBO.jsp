<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.utilities.query.*, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<head>
<!-- <META http-equiv="Content-Type" content="text/html;charset=UTF-8"> -->
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />

</head>
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
	 String type= request.getParameter("type");
	 String module = request.getParameter("module");
	 String table =  Handler.tblName(module);
	 int total = Integer.parseInt(request.getParameter("total"));
	 String[] ctrltag = request.getParameterValues("ctrltag[]");
	 String CT04SCONV = null;
	 boolean next = false;
	 
	if(matno==null || matno.trim().equals("")){
		Glnumb BUFFERNO = Glnumb.getGL98VALUE("CATBUFFERNO");
		matno = Handler.concatMatno(String.valueOf(BUFFERNO.getGL98VALUE()));
	}
	
	List<String> queryList = new ArrayList<String>();
	
	if(BO_Record.matno_exist(matno, table)){
		queryList.add(Indexing.deleteCTWORK(matno, table));
		System.out.println("Query2" + queryList.toString());
	}
		
	for (int i = 0; i < total; i++) {
		
		System.out.println("line 48 handler_addbo : " + raw[i]);
		
		if(tag[i].equals("000")||tag[i].equals("006")||tag[i].equals("007")||tag[i].equals("008")||
				tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("004")||
				tag[i].equals("005")||tag[i].equals("009")){
					CT04SCONV= Handler.convUpperCase((raw[i]));
					System.out.println("line 54 handler_addbo : " + CT04SCONV);
			}else{
				CT04SCONV= Handler.convUpperCase(Handler.removeNumberSubf(raw[i]));
				System.out.println("line 57 handler_addbo : " + CT04SCONV);
			}
				
		
		if(CT04SCONV!=null && !CT04SCONV.trim().isEmpty()){
			 String indicator1 = "";
			String indicator2 = "";
			
			//Check if indicator is blank, automatically insert #
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
					 
		Glnumb CT04COUNTER = Glnumb.getGL98VALUE("BUFPOINT");
		int counter = CT04COUNTER.getGL98VALUE();
		Map<String, String> conditionMap = new HashMap<String, String>();
		Map<String, String> valueStr = new HashMap<String, String>();
		Map<String, Integer> valueInt = new HashMap<String, Integer>();
					
		raw[i] = raw[i].replace("'","''");
		CT04SCONV = Handler.convUpperCase(Handler.removeSpecialChar(CT04SCONV));
		if(CT04SCONV.length() > 255 ){
			CT04SCONV = CT04SCONV.substring(0, 255);
		}
		
			valueStr.put("CT04MATNO",matno);
			valueStr.put("CT04MARC","U");
			valueStr.put("CT04TAG",tag[i]);
			valueStr.put("CT04INDI1",indicator1);
			valueStr.put("CT04INDI2",indicator2);
			
			if(tag[i].equals("000")||tag[i].equals("006")||tag[i].equals("007")||tag[i].equals("008")||
					tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("004")||
					tag[i].equals("005")||tag[i].equals("009")){
				valueStr.put("CT04RAW",raw[i]);
			}else{
				valueStr.put("CT04RAW",GlobalValidation.removeSubfWithNoRecord(raw[i]));
			}
			if(creatime.trim().isEmpty()){
				valueStr.put("CT04CRETIME","");
			}else{
				valueStr.put("CT04CRETIME",DateTime.timeToDBFormat(creatime));
			}
				valueStr.put("CT04CREDATE",DateTime.displayToDBFormat(createdate));
				valueStr.put("CT04CREBY",createby);
				valueStr.put("CT04TYPE",type);
				valueStr.put("CT04MODIDATE",DateTime.displayToDBFormat(modidate));
				valueStr.put("CT04MODITIME",DateTime.timeToDBFormat(moditime));
				//valueStr.put("CT04MODIBY",(String)session.getAttribute("username"));
				valueStr.put("CT04MODIBY",modiby);
				valueStr.put("CT04STATUS","A");
				valueStr.put("CT04SCONV",CT04SCONV);
				valueInt.put("CT04COUNTER",counter);
				valueStr.put("CT04CTRLTAG",ctrltag[i]);
				queryList.add(QueryBuilder.createInsertQuery("CTWORK", valueStr, valueInt, null));
			}
				
		 }
			 
		//If record is ok, it will save record in batch 
	 	boolean isSuccess =  DBQuery.runBatchQuery(queryList);
		if(isSuccess){
			out.println("Successful," + matno);
		}else{
			out.println("Fail");
		}
		 
	 
	%>