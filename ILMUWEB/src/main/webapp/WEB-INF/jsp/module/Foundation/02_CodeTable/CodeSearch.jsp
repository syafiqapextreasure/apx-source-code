	<!-- Java class-->
	<%@ page import="com.ilmu.global.connection.DBConnection"%>
	<%@page import="com.ilmu.foundation.CodeTable.SQLStatement"%>
	<%@ page import="java.util.*" %>
	<%@ page import="java.sql.*" %>

	<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.CodeTable.CodeCriteria, java.util.List" %>
	
<%-- <% ResultSet resultset =null;%>
<% Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); %>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script> --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Delete.js"></script>
	
	 <!-- DataTables -->	
<%--  <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css"> --%>
	
	<script>
	// Code making table row clickable
	// Have to place here otherwise the code won't work
	$(document).ready(function() {
		$('#vendor_result').DataTable();
	
		$('.clickable-row').click(function() {
				$('#vendorModal').modal('hide');
				
				document.getElementById('code').value = $(this).attr('data-value');
				document.getElementById('description').value = $(this).attr('data-name');
				document.getElementById('code').focus();
 		
		});
	});
	</script>
	
	<script>
    var $table = $('#table');

    $(function () {
        $('#modalTable').on('shown.bs.modal', function () {
            $table.DataTable('resetView');
        });
    });
</script>

<%-- <% 
			/* Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ILMU", "paradigm", "paradigm"); */
		
        	Connection connection = DriverManager.getConnection("jdbc:sqlserver://dev.paradigm.com.my;databaseName=ILMUWEB", "ilmuweb", "ilmuweb"); 
			Statement statement = connection.createStatement();
			String titlecategory = request.getParameter("value");   
			System.out.println(titlecategory);
			resultset = statement.executeQuery("SELECT FNDSYSTEM.FCODE, FNDSYSTEM.FNDNAME, FNDSYSTEM.FCOLUMN1, FNDSYSTEM.FCOLUMN2, FNDCODE.FNAME FROM FNDSYSTEM INNER JOIN FNDCODE ON FNDSYSTEM.FCODE=FNDCODE.FCODE WHERE FNDSYSTEM.FCODE='" + titlecategory + "'") ; 
			
            if(resultset.next()) {
                	
        %> --%>
        
        <%
			String value= request.getParameter("value");
			SQLStatement eb = new SQLStatement();
			List<Foundation> list = eb.getAllCodeStat(value);
			System.out.println(list + " list");
			for (Foundation a : list) {
		%>
	
	<table class="table table-bordered table-striped" data-toggle="table" id="vendor_result" data-click-to-select="true" >
		<thead>
			<tr>
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
		String searchType = request.getParameter("searchType");
		String criteria = request.getParameter("criteria");
		String cate_type = request.getParameter("cate_type");
		String cate_id = request.getParameter("cate_id");
		String fcode = request.getParameter("fcode");
		
		List<CodeCriteria> vendorResult = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}
		if(searchType.equals("CODE")){
			vendorResult = CodeCriteria.searchCodeByCode(criteria,fcode);
		}else if (searchType.equals("DESCRIPTION")) {
			vendorResult = CodeCriteria.searchCodeByDesc(criteria,fcode);
		}
		%>
		<%
			for(CodeCriteria i: vendorResult){
			
				String code = i.getcode();
				//System.out.println(code+"code");
				
				String newConvertCode = code.replace(" ", "+");
		%>
	 
			<tr class="clickable-row" data-dismiss="modal" data-value="<%= i.getcode()%>" data-name="<%= i.getdesc()%>">
				<td><%= i.getcode() %></td>
				<td><%= i.getdesc() %></td>
				<td class=" last">
					<input type="hidden" value="A" name="fndcategories"/>
							
							<button class="btn btn-primary btn-xs" data-toggle='modal' data-target='#viewModal' href='Modal_ViewCode?value=<%=value%>&CODE=<%=newConvertCode%>'><span class="glyphicon glyphicon-eye-open" title="View Record" ></span></button>
							<button class="btn btn-info btn-xs" data-toggle='modal' data-target='#editModal' href='Modal_EditCode?value=<%=value%>&CODE=<%=newConvertCode%>'><span class="glyphicon glyphicon-pencil" title="Edit Record" ></span></button>
   							<button class="btn btn-dull btn-xs" onclick="deleteCode('<%=i.getcode()%>','<%=value%>')"><span class="glyphicon glyphicon-trash" title="Delete Record" ></span></button>
   				</td>
   				
			</tr>
		<%
			}
		
		%>
		</tbody>
	</table>
	


	
	