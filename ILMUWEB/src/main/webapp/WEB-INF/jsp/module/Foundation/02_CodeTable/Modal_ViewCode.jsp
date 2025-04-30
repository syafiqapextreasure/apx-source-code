<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.ilmu.foundation.CodeTable.SQLStatement"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

<%@ page import="com.ilmu.cataloging.Acc_Maint.*, com.ilmu.foundation.global.*,com.ilmu.foundation.CodeTable.*, java.util.List" %>
	

<%-- <% ResultSet resultset =null;%>
<% Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); %> --%>

					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Add.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/CodeTable.js"></script>	
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Checking.js"></script> --%>

<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> --%>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>

<%
	String CODE = request.getParameter("CODE");
	System.out.println(CODE + " CODE");
	String FCODE = request.getParameter("FCODE");
	String titlecategory = request.getParameter("value");
	SQLStatement eb = new SQLStatement();
	Foundation e = eb.getCodeById(CODE, titlecategory);
%>

		<%-- <% 
			/* Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ILMU", "paradigm", "paradigm"); */
		
        	Connection connection = DriverManager.getConnection("jdbc:sqlserver://dev.paradigm.com.my;databaseName=ILMUWEB", "ilmuweb", "ilmuweb"); 
			Statement statement = connection.createStatement();
			  
			System.out.println(titlecategory);
			resultset = statement.executeQuery("SELECT FNDSYSTEM.FCODE, FNDSYSTEM.FNDNAME, FNDSYSTEM.FCOLUMN1, FNDSYSTEM.FCOLUMN2, FNDCODE.FNAME FROM FNDSYSTEM INNER JOIN FNDCODE ON FNDSYSTEM.FCODE=FNDCODE.FCODE WHERE FNDSYSTEM.FCODE='" + titlecategory + "'") ; 
			
            if(!resultset.next()) {
                	out.println("Sorry, could not find that publisher. ");
           	} else {
        %> --%>
        
        <%
			String value= request.getParameter("value");
			SQLStatement eb2 = new SQLStatement();
			List<Foundation> list = eb2.getAllCodeStat(value);
			System.out.println(value + " value");
			for (Foundation a : list) {
		%>




<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">View <%=a.getFNDNAME() %></h4>
</div>
	<%-- <% 
		 } 
    %> --%>
<form role="form" class="form-horizontal" >
	<div class="modal-body" style="height:30%;;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
				<div class="form-group">
                   	<input type="hidden" class="form-control" id="FCODE" name="FCODE" value="<%=a.getFCODE()%>">
                    <input type="hidden" class="form-control" id="FNAME" name="FNAME" value="<%=a.getFNDNAME()%>">
                      
                    <label class="col-sm-3 "><strong><%=a.getFCOLUMN1()%></strong></label>
                    <div class="col-sm-5 col-md-5">
                        <input type="text" class="form-control" id="CODE" name="CODE" value="<%=e.getCODE()%>" readonly>
                    </div> 
                </div>
                    
                <div class="form-group">
                    <label class="col-sm-3 "><%=a.getFCOLUMN2()%></label>
                    <div class="col-sm-9 col-md-9">
                        <input type="text" class="form-control" id="DESCRIPTION" name="DESCRIPTION" value="<%=e.getDESCRIPTION()%>" readonly>
                    </div>
                </div>
                
                <% 
					} 
				%> 
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		 <a href="template?MODULE=Foundation/02_CodeTable&ACTION=CodeTable.jsp?value=<%=titlecategory%>">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Close</button>
         </a>
	</div>			
	</form>
	

	