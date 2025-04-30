<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.wilmu.foundation.vendor.data.*,java.util.*"%>
    
<%
String code = request.getParameter("code");
String vendorName = GetFndVendor.getVendorNameByVendorCode(code);

out.println(vendorName);

%>