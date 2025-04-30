<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.utilities.query.*, 
					com.ilmu.global.*,java.text.*,java.util.*"%>

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
	 int total = Integer.parseInt(request.getParameter("total"));
	 String[] ctrltag = request.getParameterValues("ctrltag[]");
	 String table =  Handler.tblName(module);
	 
	 String CT04SCONV = null;
	 boolean next = false;
	 
	if(matno==null || matno.trim().equals("")){
		Glnumb BUFFERNO = Glnumb.getGL98VALUE("AUTBUFFERNO");
		matno = Handler.concatMatno(String.valueOf(BUFFERNO.getGL98VALUE()));
	}
	
	List<String> queryList = new ArrayList<String>();
	
	if(BO_Record.matno_exist(matno, table)){
		queryList.add(Indexing.deleteCTWORK(matno, table));
	}
		
	for (int i = 0; i < total; i++) {
		
		if(tag[i].equals("000")||tag[i].equals("006")||tag[i].equals("007")||tag[i].equals("008")||
				tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("004")||
				tag[i].equals("005")||tag[i].equals("009")){
					CT04SCONV= Handler.convUpperCase((raw[i]));
			}else{
				CT04SCONV= Handler.convUpperCase(Handler.removeNumberSubf(raw[i]));
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
					 
		Glnumb CT04COUNTER = Glnumb.getGL98VALUE("AUTBUFPOINT");
		int counter = CT04COUNTER.getGL98VALUE();
		Map<String, String> conditionMap = new HashMap<String, String>();
		Map<String, String> valueStr = new HashMap<String, String>();
		Map<String, Integer> valueInt = new HashMap<String, Integer>();
					
		raw[i] = raw[i].replace("'","''");
		CT04SCONV = Handler.convUpperCase(Handler.removeSpecialChar(CT04SCONV));
		if(CT04SCONV.length() > 255 ){
			CT04SCONV = CT04SCONV.substring(0, 255);
		}
		
			valueStr.put("AU04MATNO",matno);
			valueStr.put("AU04MARC","Y");
			valueStr.put("AU04TAG",tag[i]);
			valueStr.put("AU04INDI1",indicator1);
			valueStr.put("AU04INDI2",indicator2);
			
			if(tag[i].equals("000")||tag[i].equals("006")||tag[i].equals("007")||tag[i].equals("008")||
					tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("004")||
					tag[i].equals("005")||tag[i].equals("009")){
				valueStr.put("AU04RAW",raw[i]);
			}else{
				valueStr.put("AU04RAW",GlobalValidation.removeSubfWithNoRecord(raw[i]));
			}
			if(creatime.trim().isEmpty()){
				valueStr.put("AU04CRETIME","");
			}else{
				valueStr.put("AU04CRETIME",DateTime.timeToDBFormat(creatime));
			}
				valueStr.put("AU04CREDATE",DateTime.displayToDBFormat(createdate));
				valueStr.put("AU04CREBY",createby);
				valueStr.put("AU04STATUS","A");
				valueStr.put("AU04SCONV",CT04SCONV);
				valueInt.put("AU04COUNTER",counter);
					
				queryList.add(QueryBuilder.createInsertQuery("AUWORK", valueStr, valueInt, null));
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