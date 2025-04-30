<%@ page import="java.util.*" %>
<%@page import="com.kmlink.ilmu.parable.parable_beta.object.*"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.QuerySMD"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.QueryPicklistItemCategory"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.QueryPicklistSMD"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.QueryPicklistBranch"%>
<%@ page import="com.kmlink.ilmu.parable.parable_beta.QueryAcession"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.object.book_spine"%>
<%@ include file="/include/header.jsp" %>

<%
	List<item_category>itemCategory = null;
	QueryPicklistItemCategory queryPicklistItemCategory = new QueryPicklistItemCategory();
	itemCategory = queryPicklistItemCategory.loadPicklistItemCategory();
	
	List<smd>picklistSMD  = null;
	QueryPicklistSMD queryPicklistSMD = new QueryPicklistSMD();
	picklistSMD = queryPicklistSMD.loadPicklistSMD();
	
	List<branch>picklistBranch  = null;
	QueryPicklistBranch queryPicklistBranch = new QueryPicklistBranch();
	picklistBranch = queryPicklistBranch.loadPicklistBranch();
	
	QueryAcession queryAcession= new QueryAcession();
	book_spine book_spine = new book_spine();
/* 	book_spine = queryAcession.loadAcession("0000000001");
	System.out.println("ASWAD" +new JSONObject(book_spine).toString()); */
%>