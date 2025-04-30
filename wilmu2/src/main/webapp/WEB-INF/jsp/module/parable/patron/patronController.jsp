<%@page import="java.util.List"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.patron.QueryPicklistCategory"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.patron.QueryPicklistDepartment"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.patron.QueryPicklistCourse"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.object.*"%>

<%

	List<patron_category>picklistCategory = null;
	QueryPicklistCategory queryPicklistCategory = new QueryPicklistCategory();
	picklistCategory = queryPicklistCategory.loadPicklistCategory();  
	
	List<department>picklistDepartment  = null;
	QueryPicklistDepartment queryPicklistDepartment = new QueryPicklistDepartment();
	picklistDepartment = queryPicklistDepartment.loadPicklistDepartment();
	
	List<course>picklistCourse  = null;
	QueryPicklistCourse queryPicklistCourse = new QueryPicklistCourse();
	picklistCourse = queryPicklistCourse.loadPicklistCourse();
%>