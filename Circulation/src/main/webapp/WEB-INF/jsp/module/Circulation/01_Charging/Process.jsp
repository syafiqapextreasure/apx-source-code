<%@ page import="java.util.*" %>
<%! 
    ResourceBundle bundle =null;
    public void jspInit() {
       bundle = ResourceBundle.getBundle("forms");
      }
%>
<jsp:useBean id="formHandler" class="com.ilmu.circulation.Charging.Patron" scope="request">
<jsp:setProperty name="formHandler" property="*"/>
</jsp:useBean>
<% 
  
%>
