<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					com.ilmu.cataloging.Template_Maint.*,
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<%
String tag = request.getParameter("tag");
String defaults = Tag_Handler.getDefaultTag(tag);


%>