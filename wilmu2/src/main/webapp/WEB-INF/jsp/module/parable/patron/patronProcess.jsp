<%@page import="com.kmlink.ilmu.parable.parable_beta.patron.QueryCourse"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.patron.QueryDepartment"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.patron.QueryCategory"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.patron.QueryName"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.patron.QueryDateRange"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.patron.QueryPatron"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONString"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.object.patron"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="org.json.JSONArray"%>

<%

	List<patron> listData= null;
	HashMap<String, Object> hashData = new HashMap<String,Object>();
	String action = request.getParameter("action");
	int unprint = Integer.parseInt(request.getParameter("unprint"));
	String selectedBranche= request.getParameter("selectedBranch");
	if(action.equalsIgnoreCase("patron_id")){
		String patron_id1 ="";
		String patron_id2 ="";
		if (request.getParameter("patron_id1")!=null && !request.getParameter("patron_id1").isEmpty()){
			patron_id1  = (String)request.getParameter("patron_id1");
		}
		if(request.getParameter("patron_id2") != null && !request.getParameter("patron_id2").isEmpty()){
			patron_id2 = (String)request.getParameter("patron_id2");
		}
		QueryPatron queryPatron= new QueryPatron();
		hashData = queryPatron.loadByPatron(patron_id1,patron_id2, unprint, request.getParameter("selectedBranch"));
		listData = (List<patron>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
	
	if(action.equalsIgnoreCase("date_range")){
		String date_range1 ="";
		String date_range2 ="";
		if (request.getParameter("date_range1")!=null){
			date_range1 = (String)request.getParameter("date_range1");
		}
		if (request.getParameter("date_range2")!=null){
			date_range2 = (String)request.getParameter("date_range2");
		}
		QueryDateRange queryDateRange = new QueryDateRange();
		hashData = queryDateRange.loadByDateRange(date_range1, date_range2, unprint);
		patron patron = new patron();
		listData = (List<patron>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
	
	if(action.equalsIgnoreCase("name")){
		String name ="";
		if (request.getParameter("name")!=null){
			name = (String)request.getParameter("name");
		}
		QueryName queryName = new QueryName();
		hashData = queryName.loadByName(name, unprint, request.getParameter("selectedBranch"));
		patron patron = new patron();
		listData = (List<patron>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
	
	if(action.equalsIgnoreCase("patron_cat")){
		String patron_cat ="";
		if (request.getParameter("patron_cat")!=null){
			patron_cat = (String)request.getParameter("patron_cat").toString();
		}
		System.out.println("category ! >>>> "  +patron_cat);
		QueryCategory queryCategory = new QueryCategory();
		hashData = queryCategory.loadByPatronCategory(patron_cat, unprint);
		patron patron = new patron();
		listData = (List<patron>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
	
	if(action.equalsIgnoreCase("department")){
		String department ="";
		if (request.getParameter("department")!=null){
			department = (String)request.getParameter("department").toString();
		}
		QueryDepartment queryDepartment = new QueryDepartment();
		hashData = queryDepartment.loadByDepartment(department, unprint);
		patron patron = new patron();
		listData = (List<patron>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
	
	if(action.equalsIgnoreCase("course")){
		String course ="";
		if (request.getParameter("course")!=null){
			course = (String)request.getParameter("course").toString();
		}
		QueryCourse queryCourse = new QueryCourse();
		hashData = queryCourse.loadyByCourse(course, unprint);
		patron patron = new patron();
		listData = (List<patron>)hashData.get("list");
		out.println(new JSONArray(listData));
	}
%>