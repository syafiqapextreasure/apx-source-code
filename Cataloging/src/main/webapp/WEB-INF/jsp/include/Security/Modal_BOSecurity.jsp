<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					com.ilmu.cataloging.Template_Maint.*,
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/BO.js"></script>	
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	<h4 class="modal-title" id="myModalLabel">Security</h4>
</div>
<div class="modal-body">
<%
	String ctrlno = request.getParameter("ctrlno");
	String status = Bibliography.getPrivate(ctrlno);
	String checked = "";
	System.out.println("Status" + status);
	if(status.equals("Y")){
		checked = "checked";
	}else{
		checked = "";
	}
%>
	<input type="checkbox" id="staff" name="vehicle1" value="Y" <%=checked%>>
	<label for="vehicle1"> Private Record (Staff view)</label><br>
</div>
<div class="modal-footer">
	<button id="savePrivate" onclick="savePrivate()" class="btn btn-info" data-dismiss="modal">Save</button>
</div>