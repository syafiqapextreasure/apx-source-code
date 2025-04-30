<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="com.kmlink.ilmu.parable.parable_beta.*"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONString"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.object.book_spine"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="org.json.JSONArray"%>

<%
	String number1 =null;
	String number2 = "";
	List<book_spine> listData= null;
	HashMap<String, Object> hashData = new HashMap<String,Object>();
	String action = request.getParameter("action");
	String unprint = request.getParameter("unprint");
	String selectedBranch = request.getParameter("selectedBranch");
	String selectedLocation = request.getParameter("selectedLocation");
	int unprintLbl = Integer.parseInt(request.getParameter("unprint"));
	if(action.equalsIgnoreCase("accession_no")){
		if (request.getParameter("first_number")!=null && !request.getParameter("first_number").isEmpty()){
			number1 = (String)request.getParameter("first_number");
		}
		if(request.getParameter("second_number") != null && !request.getParameter("second_number").isEmpty()){
			number2 = (String)request.getParameter("second_number");
		}

		QueryAcession queryAcession= new QueryAcession();
		hashData = queryAcession.loadbyAcession(number1, number2, unprintLbl, "CT03BLESTATUS", selectedBranch, selectedLocation);
		listData = (List<book_spine>)hashData.get("list");
		for(int j = 0; j < listData.size(); j++){
			book_spine book_spine = listData.get(j);
			if(book_spine.getCallNo().trim().toString() == null || book_spine.getCallNo().trim().toString() == ""){
			}
			GeneralUtil generalUtil = new GeneralUtil();
			List<String> LLC = new ArrayList<String>();

		}
		out.println(new JSONArray(listData));
	}
	
	if(action.equalsIgnoreCase("accession_no_range")){
		if (request.getParameter("first_number")!=null && !request.getParameter("first_number").isEmpty()){
			number1 = (String)request.getParameter("first_number");
		}
		QueryAcession queryAcession= new QueryAcession();
		hashData = queryAcession.loadbyAcessionRange(number1, unprintLbl, "CT03BLESTATUS", selectedBranch, selectedLocation);
		listData = (List<book_spine>)hashData.get("list");
		for(int j = 0; j < listData.size(); j++){
			book_spine book_spine = listData.get(j);
			if(book_spine.getCallNo().trim().toString() == null || book_spine.getCallNo().trim().toString() == ""){
			}
			GeneralUtil generalUtil = new GeneralUtil();
			List<String> LLC = new ArrayList<String>();

		}
		out.println(new JSONArray(listData));
	}
	if(action.equalsIgnoreCase("call_no")){
		String call_number1 =null;
		String call_number2 = "";
		if (request.getParameter("call_number1")!=null && !request.getParameter("call_number1").isEmpty()){
			call_number1  = (String)request.getParameter("call_number1");
		}
		if(request.getParameter("call_number2") != null && !request.getParameter("call_number2").isEmpty()){
			call_number2 = (String)request.getParameter("call_number2");
		}
		
		call_number1 = call_number1.replaceAll("\\.", " ");   
		call_number2 = call_number2.replaceAll("\\.", " ");   
		QueryCallNumber queryCallNumber = new QueryCallNumber();
		hashData = queryCallNumber.loadByCallNumber(call_number1,call_number2, unprintLbl, "CT03BLESTATUS", selectedBranch, selectedLocation);
		listData = (List<book_spine>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
	
	if(action.equalsIgnoreCase("catalog_date")){
		String catalog_date1 = request.getParameter("catalog_date1");
		String catalog_date2 = request.getParameter("catalog_date2");
		QueryCatalogDate queryCatalogDate = new QueryCatalogDate();
//		hashData = queryCatalogDate.loadByCatalogDate(catalog_date1, catalog_date2, unprintLbl, "CT03BLESTATUS");
hashData = queryCatalogDate.loadByCatalogDate(catalog_date1, catalog_date2, unprintLbl, "CT03SPINESTATUS", selectedBranch, selectedLocation);
		listData = (List<book_spine>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
	
	if(action.equalsIgnoreCase("control_no")){
		String control_number1 = request.getParameter("control_number1");
		String control_number2 = request.getParameter("control_number2");
		QueryControlNumber queryControlNumber = new QueryControlNumber();
		hashData = queryControlNumber.loadByControlNumber(control_number1,control_number2, unprintLbl, "CT03BLESTATUS", selectedBranch, selectedLocation);
		listData = (List<book_spine>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
	
	if(action.equalsIgnoreCase("smd")){
		String smd = request.getParameter("smd");
		String catalog_date1 = "";
		String catalog_date2 = "";
		if (request.getParameter("catalog_date1")!=null && request.getParameter("catalog_date2")!=null){
			catalog_date1 = request.getParameter("catalog_date1");
			catalog_date2 = request.getParameter("catalog_date2");
		}
		String index_date1 ="";
		String index_date2 ="";
		if (request.getParameter("index_date1")!=null && request.getParameter("index_date2")!=null){
			index_date1 = request.getParameter("index_date1");
			index_date2 = request.getParameter("index_date2");
		}
		QuerySMD querySMD = new QuerySMD();
		hashData = querySMD.loadBySMD(smd,catalog_date1,catalog_date2,index_date1,index_date2, unprintLbl, "CT03BLESTATUS", selectedBranch, selectedLocation);
		listData = (List<book_spine>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
	if(action.equalsIgnoreCase("item_cat")){
		String item_category = request.getParameter("item_cat");
		String catalog_date1 = "";
		String catalog_date2 = "";
		if (request.getParameter("catalog_date1")!=null && request.getParameter("catalog_date2")!=null){
			catalog_date1 = request.getParameter("catalog_date1");
			catalog_date2 = request.getParameter("catalog_date2");
		}
		String index_date1 ="";
		String index_date2 ="";
		if (request.getParameter("index_date1")!=null && request.getParameter("index_date2")!=null){
			index_date1 = request.getParameter("index_date1");
			index_date2 = request.getParameter("index_date2");
		}
		QueryItemCategory queryItemCategory = new QueryItemCategory();
	//	hashData = queryItemCategory.loadByItemCategory(item_category.trim(),catalog_date1,catalog_date2,index_date1,index_date2, unprintLbl, "CT03BLESTATUS");
		hashData = queryItemCategory.loadByItemCategory(item_category.trim(),catalog_date1,catalog_date2,index_date1,index_date2, unprintLbl, "CT03BLESTATUS", selectedBranch, selectedLocation);
		listData = (List<book_spine>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
	if(action.equalsIgnoreCase("branch")){
		String branch = request.getParameter("branch");
		String catalog_date1 = "";
		String catalog_date2 = "";
		if (request.getParameter("catalog_date1")!=null && request.getParameter("catalog_date2")!=null){
			catalog_date1 = request.getParameter("catalog_date1");
			catalog_date2 = request.getParameter("catalog_date2");
		}
		String index_date1 ="";
		String index_date2 ="";
		if (request.getParameter("index_date1")!=null && request.getParameter("index_date2")!=null){
			index_date1 = request.getParameter("index_date1");
			index_date2 = request.getParameter("index_date2");
		}
		QueryBranch queryBranch = new QueryBranch();
		hashData = queryBranch.loadByBranch(branch.trim(),catalog_date1,catalog_date2,index_date1,index_date2, unprintLbl, "CT03BLESTATUS");
	//	hashData = queryBranch.loadByBranch(branch.trim(),catalog_date1,catalog_date2,index_date1,index_date2, unprintLbl, "CT03BLESTATUS", selectedBranch, selectedLocation);
		listData = (List<book_spine>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
	if(action.equalsIgnoreCase("index_date")){
		String index_date1 = request.getParameter("index_date1");
		String index_date2 = request.getParameter("index_date2");
		QueryIndexDate queryIndexDate = new QueryIndexDate();
	//	hashData = queryIndexDate.loadByIndexDate(index_date1, index_date2, unprintLbl, "CT03BLESTATUS");
		hashData = queryIndexDate.loadByIndexDate(index_date1, index_date2, unprintLbl, "CT03BLESTATUS", selectedBranch, selectedLocation);
		listData = (List<book_spine>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
	if(action.equalsIgnoreCase("title")){
		String title = request.getParameter("title");
		QueryTitle queryTitle = new QueryTitle();
		hashData = queryTitle.loadByTitle(title, unprintLbl, "CT03BLESTATUS", selectedBranch, selectedLocation);
		listData = (List<book_spine>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
	
		
		if(action.equalsIgnoreCase("print")){
			HashMap<String, Object> result = new HashMap<String, Object>();
			String [] selectedValues = request.getParameterValues("accessionNo[]");
			result.put("status", "ok");
			for(int i = 0; i< selectedValues.length ;i++ ){
				BarcodeGenerator barcodeGenerator = new BarcodeGenerator();
				barcodeGenerator.generateBarcode(selectedValues[i]);
			}
			result.put("data", selectedValues);
			out.println(new JSONObject(result));
		}
		
%>