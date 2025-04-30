<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.ilmu.foundation.CodeTable.SQLStatement"%>
<%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.* ,com.ilmu.global.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.ilmu.foundation.CodeTable.SQLStatement" %>



<%-- <% ResultSet resultset =null;%>
<% Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); %> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/CodeTable2.js"></script>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script> -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Delete.js"></script>					

 <!-- DataTables -->	
<%--  <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css"> --%>
 



<script>
      $(function () {
        $('#example1').DataTable({
          "paging": true,
          "lengthChange": false,
          "searching": false,
          "ordering": true,
          "info": true,
          "autoWidth": true,
          "columnDefs": [
          	{
            	"targets": [ 0 ],
            	"visible": false,
             	"searchable": false
          	}
          ]
        });
      });
	  
	  $(function () {
        $('#example3').DataTable({
          "paging": true,
          "lengthChange": false,
          "searching": false,
          "ordering": true,
          "info": true,
          "autoWidth": true,
          "columnDefs": [
         	{
            	"targets": [ 0 ],
             	"visible": false,
              	"searchable": false
          	}
        ]
        });
      });
</script>
 

</head>
		<%
			String value= request.getParameter("value");
			SQLStatement eb = new SQLStatement();
			List<Foundation> list = eb.getAllCodeStat(value);
			System.out.println(list + " list");
			for (Foundation a : list) {
		%>
	<body>

    <!-- MAIN CONTENT -->
    <div class="box box-default">
    <div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title pull-left"></h3>
        <button type="button" class="btn btn-primary pull-right" data-toggle='modal' data-target='#vendorModal' href='SearchCode?value=<%=value%>'><i class="glyphicon glyphicon-search" title="Find Code"></i></button>
        <button type="button" class="btn btn-primary pull-right" id="add" data-toggle='modal' data-target="#addModal" href="Modal_AddCode?value=<%=value%>">
        <i class="glyphicon glyphicon-plus" title="Add New Code"></i></button>
		<div class="clearfix"></div>
    </div>
    <div class="panel-body" id="display_vendor">
    
    <%
     	if(null != request.getAttribute("msg")){
     		String msg = (String)  request.getAttribute("msg");
     	
     %>
     <input type="hidden" id="msgAdd" value="<%=msg%>">
     
     <%
     	}
     %>
    
     <table id="example1" class="table table-bordered table-striped">
                    				
 					  <thead>
                      <tr>
                        <th>FCODE</th>
                        <th><%=a.getFCOLUMN1()%></th>
                        <th><%=a.getFCOLUMN2()%></th>
                        <% 
		   				} 
       	  				%>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
                    <%

                    		SQLStatement eb2 = new SQLStatement();
                			List<Foundation> list2 = eb2.getAllCode(value);
						
							for (Foundation e : list2) {
							
								String code = e.getCODE();
								//System.out.println(code+"code");
								
								String newConvertCode = code.replace(" ", "+");
							
						%>
						<tr>
							<td><%=e.getFCODE()%></td>
							<td><%=e.getCODE()%></td>
							<td><%=e.getDESCRIPTION()%></td>
							<td class=" last">
							<button class="btn btn-primary btn-xs" data-toggle='modal' data-target='#viewModal' href='Modal_ViewCode?value=<%=value%>&FCODE=<%=e.getFCODE()%>&CODE=<%=newConvertCode%>'><span class="glyphicon glyphicon-eye-open" title="View Record" ></span></button>
							<button class="btn btn-info btn-xs" data-toggle='modal' id="editCode" data-target='#editModal' data-value="<%=value%>" href="Modal_EditCode?value=<%=value%>&FCODE=<%=e.getFCODE()%>&CODE=<%=newConvertCode%>"><span class="glyphicon glyphicon-pencil" title="Edit Record" ></span></button>
							<button class="btn btn-dull btn-xs" onclick="deleteCode('<%=e.getCODE()%>','<%=value%>')"><span class="glyphicon glyphicon-trash" title="Delete Record" ></span></button>
   							</td>
							
							
   							<%
								}
							%> 
						
						</tr>
						
                    </tbody>
                    
                  </table>
    
                  
    </div>
</div>
</div>   

<!-- ./END MAIN CONTENT -->	



 	<!-- ADD MODAL -->  
		<div class="modal fade" id="addModal" role="dialog" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" style="width:60%;overflow:auto">
			<div class="modal-content">
				<!-- Remote content load here -->
			</div>
			</div>
		</div>
	<!-- Modal END -->
	
	<!-- VIEW MODAL -->
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
            <div class="modal-dialog" style="width:60%;overflow:auto">
                <div class="modal-content">
		 		 <!-- Remote content load here -->
		  		</div>
			</div>
	</div>
	
	<!-- Modal END -->
	
	<!-- EDIT MODAL -->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
            <div class="modal-dialog" style="width:60%;overflow:auto">
                <div class="modal-content" id="editContent">
		 		 <!-- Remote content load here -->
		  		</div>
			</div>
	</div>
	<!-- Modal END -->
 		
 	<!-- DELETE MODAL -->  
        
        <div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
            <div class="modal-dialog" style="width:400px;">
                <div class="modal-content">
		 		 <!-- Remote content load here -->
		  		</div>
			</div>
		</div>
 	<!-- Modal END -->
	

	
	<!-- Search Criteria Modal-->  
        
    <div class="modal fade" id="vendorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
         <div class="modal-dialog" style="width:60%;overflow:auto">
             <div class="modal-content">
		 	 <!-- Remote content load here -->
		  	 </div>
		</div>
	</div>
	<!-- ./End Search Criteria Modal -->
	
	
			
</body>

</html>
            