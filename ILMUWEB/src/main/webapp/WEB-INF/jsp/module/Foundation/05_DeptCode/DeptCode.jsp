<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.ilmu.foundation.DepartmentCode.SQLStatement"%>
<%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Delete.js"></script>					

 <!-- DataTables -->	
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
 



<script>
      $(function () {
        $('#example1').DataTable({
          "paging": true,
          "lengthChange": false,
          "searching": false,
          "ordering": true,
          "info": true,
          "autoWidth": true
        });
      });
</script>
 

</head>

	<body>
	
	<section class="content-header">
       <h1>
          <i class="glyphicon glyphicon-list"></i>  Depatrment Code
       </h1>
       
    </section>
    
    
    
    <!-- MAIN CONTENT -->
    <div class="box box-default">
    <div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title pull-left"></h3>
        <button class="btn btn-primary pull-right" data-toggle='modal' data-target='#vendorModal' href='SearchCriteria'><i class="glyphicon glyphicon-search" title="Find Patron"></i></button>
        <button type="button" class="btn btn-primary pull-right" id="btn_add" data-toggle='modal' data-target="#addModal" href="Modal_AddCurrency">
        <i class="glyphicon glyphicon-plus" title="Add New Patron"></i></button>
		<div class="clearfix"></div>
    </div>
    <div class="panel-body" id="display_vendor">
    
     <table id="example1" class="table table-bordered table-striped">
                    				
 					  <thead>
                      <tr>
                         <th>Dept Code</th>
                        <th>Department Name</th>
                        <th>Head of Dept.</th>
                        <th>No. of Staff </th>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
                    	<%
                    		SQLStatement eb = new SQLStatement();
							List<Foundation> list = eb.getAllDeptCode();
				
							for (Foundation e : list) {
						%>
						<tr>
							<td><%=e.getGL11DEPT()%></td>
							<td><%=e.getGL11NAME()%></td>
							<td><%=e.getGL11HEAD()%></td>
							<td><%=e.getGL11STAFF()%></td>
							
							<td class=" last">
							<input type="hidden" value="A" name="fndcategories"/>
							
							<button class="btn btn-primary btn-xs" data-toggle='modal' data-target='#viewModal' href='Modal_ViewCurrency?GL14PATR=<%=e.getGL14PATR()%>'><span class="glyphicon glyphicon-eye-open" title="View Record" ></span></button>
							<button class="btn btn-info btn-xs" data-toggle='modal' data-target='#editModal' href='Modal_EditCurrency?GL14PATR=<%=e.getGL14PATR()%>'><span class="glyphicon glyphicon-pencil" title="Edit Record" ></span></button>
   							<button class="btn btn-dull btn-xs" onclick="deletePlugin(this.id)" id="<%=e.getGL14PATR()%>"><span class="glyphicon glyphicon-trash" title="Delete Record" ></span></button>
   							</td>
							
						</tr>
						<%
						} 
						%>
                    </tbody>
                    
                  </table>
    
                  
    </div>
</div>
</div>   

<!-- ./END MAIN CONTENT -->	
</section>

  
<!-- ADD MODAL -->  
		<div class="modal fade" id="addModal" role="dialog" >
			<div class="modal-dialog" style="width:900px;height:300px;">
			<div class="modal-content">
				<!-- Remote content load here -->
			</div>
			</div>
		</div>
	<!-- Modal END -->
	
	<!-- VIEW MODAL -->
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:900px;height:300px;">
                <div class="modal-content">
		 		 <!-- Remote content load here -->
		  		</div>
			</div>
	</div>
	
	<!-- Modal END -->
	
	<!-- EDIT MODAL -->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:900px;height:300px;">
                <div class="modal-content">
		 		 <!-- Remote content load here -->
		  		</div>
			</div>
	</div>
	<!-- Modal END -->
 		
 	<!-- DELETE MODAL -->  
        
        <div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:400px;">
                <div class="modal-content">
		 		 <!-- Remote content load here -->
		  		</div>
			</div>
		</div>
 	<!-- Modal END -->
	

				
	<!-- Bootstrap modal for vendor search -->
	<div class="modal fade" id="vendorSearch" tabindex="-1" role="dialog" aria-labelledby="vendorSearch">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
    <!-- Modal END -->

	   
	
	
	<!-- Search Criteria Modal-->  
        
    <div class="modal fade" id="vendorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
         <div class="modal-dialog" style="width:900px;">
             <div class="modal-content">
		 	 <!-- Remote content load here -->
		  	 </div>
		</div>
	</div>
	<!-- ./End Search Criteria Modal -->
			
</body>
</html>