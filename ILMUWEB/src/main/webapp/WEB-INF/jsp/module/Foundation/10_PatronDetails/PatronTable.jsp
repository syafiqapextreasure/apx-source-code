<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="com.ilmu.foundation.PatronDetails.SQLStatement"%>
<%@page import="com.ilmu.foundation.PatronDetails.Foundation"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*, com.ilmu.global.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script> -->
<%--  <script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/PatronDetails2.js"></script> --%>					
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/PatronDetails2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Delete.js"></script>					

 <!-- DataTables -->	
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
 



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
        
        var loginid = $("#liferayLogin").val();
    	$("#loginID").val(loginid);

      });
</script>
 

</head>

	<body>
	
	<!-- <section class="content-header">
       <h1>
          <i class="glyphicon glyphicon-list"></i>  Patron Details
       </h1>
       
    </section> -->
    
    <%
    
    %>
    
    <!-- MAIN CONTENT -->
    <div class="box box-default">
    <div class="panel panel-default">
    <div class="panel-heading">
    <input type="hidden" id=loginID value="" disabled>
   <%--  <%= request.getParameter("loginID")%>
    <% System.out.println(System.getenv("liferayLogin") +"fff"); %> --%>
    	<%-- <%String tty = (String) request.getAttribute("loginID"); 
    	System.out.println("ggg" +tty); %> --%>
        <h3 class="panel-title pull-left"></h3>
        <button class="btn btn-primary pull-right" data-toggle='modal' data-target='#vendorModal' href='SearchCriteria'><i class="glyphicon glyphicon-search" title="Find Patron"></i></button>
        <button type="button" class="btn btn-primary pull-right" id="btn_add" data-toggle='modal' data-target="#addAccMaint" href="Modal_AddPatron"> <%-- ?GL14PATR=<%=newConvertId%>' --%>
        <i class="glyphicon glyphicon-plus" title="Add New Patron"></i></button>
		<div class="clearfix"></div>
    </div>
    <div class="panel-body" id="display_vendor">
    
     <%
     	if(null != request.getAttribute("msg")){
     		String msg = (String) request.getAttribute("msg");
     	
     %>
     <input type="hidden" id="msgAdd" value="<%=msg%>">
     
     <%
     	}
     %>
     
     <table id="example1" class="table table-bordered table-striped">
                    				
 					  <thead>
                      <tr>
                        <th>Patron ID</th>
                        <th>Name</th>
                        <th>Abbreviation</th>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
                   <%--  <%
							SQLStatement eb = new SQLStatement();
							List<Foundation> list = eb.getAllPatronDetails();
						
							for (Foundation e : list) {
						%>
						<tr>
							<td><%=e.getGL14PATR()%></td>
							<td><%=e.getGL14NAME()%></td>
							<td><%=e.getGL14ABBR()%></td>
							
							<td class=" last">
							<input type="hidden" value="A" name="fndcategories"/>
							
							<button class="btn btn-primary btn-xs" data-toggle='modal' data-target='#viewModal' href='Modal_ViewPatron?GL14PATR=<%=e.getGL14PATR()%>'><span class="glyphicon glyphicon-eye-open" title="View Record" ></span></button>
							<button class="btn btn-info btn-xs" data-toggle='modal' id="edit" data-target='#editModal' href='Modal_EditPatron?GL14PATR=<%=e.getGL14PATR()%>'><span class="glyphicon glyphicon-pencil" title="Edit Record" ></span></button>
   							<button class="btn btn-dull btn-xs" onclick="deletePatDetail(this.id)" id="<%=e.getGL14PATR()%>"><span class="glyphicon glyphicon-trash" title="Delete Record" ></span></button>
   							</td>
							
							
						
						</tr>
						<%
						} 
						%> --%>
                    </tbody>
                    
                  </table>
    
    </div>
</div>
</div>   

<!-- ./END MAIN CONTENT -->	


	
	<div class="modal lg fade" id="accModal" role= "dialog">
	<div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title">Selection List</h4>
        </div><div class="container"></div>
        <div class="modal-body" id="accSlctList" >
    		
        </div>
        <div class="modal-footer">
          <a href="#" data-dismiss="modal" class="btn">Close</a>
          <a href="#" id="subfSave" class="btn btn-primary">Save changes</a>
        </div>
      </div>
    </div>
</div>


 </section>

	<!-- Bootstrap modal for selection search -->
	<div class="modal fade" id="selectionSearch" tabindex="-1" role="dialog" aria-labelledby="selectionSearch">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
    <!-- Modal END -->
    
    <!-- Bootstrap modal for control no search -->
	<div class="modal fade" id="ctrlSearch" tabindex="-1" role="dialog" aria-labelledby="ctrlSearch">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
    <!-- Modal END -->
    
 
	<!-- Bootstrap modal for control no search -->
	<div class="modal fade" id="tagSearch" tabindex="-1" role="dialog" aria-labelledby="tagSearch">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
    <!-- Modal END -->
    

      
      	<div class="modal fade" id="MatNoSearch" tabindex="-1" role="dialog" aria-labelledby="MatNoSearch">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
	
		<!-- Bootstrap modal for selection search -->
	<div class="modal fade" id="officerSearch" tabindex="-1" role="dialog" aria-labelledby="officerSearch">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
    <!-- Modal END -->
    
    	<!-- Bootstrap modal for selection search -->
	<div class="modal fade" id="officerSearch" tabindex="-1" role="dialog" aria-labelledby="officerSearch">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
	<!-- Modal HTML -->  
				<div class="modal fade" id="updateModal" role="dialog" >
				    <div class="modal-dialog" style="width:60%;overflow:auto;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
			    <!-- Modal END -->
			    
			    		<!-- Modal HTML View Accession Maint-->  
				<div class="modal fade" id="viewAccMaint" role="dialog" >
				    <div class="modal-dialog" style="width:70%;overflow:auto;height:900px;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
			    <!-- Modal END -->
			    
			      
			    		<!-- Modal HTML Add Accession Maint-->  
			<!-- 	<div class="modal fade" id="addAccMaint" role="dialog" >
				    <div class="modal-dialog" style="width:1100px;height:900px;">
						  <div class="modal-content">
						  Remote content load here
						  </div>
					</div>
				</div> -->
				
				<div class="modal fade" id="addAccMaint" tabindex="-1" role="dialog" aria-labelledby="addAccMaint" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" role="document" style="width:80%;overflow:auto;">
			<div class="modal-content">
			<!-- Remote content load here -->
			</div>
		</div>
	</div>	
				
			    <!-- Modal END -->
				<!-- Modal HTML Add Accession Maint-->
				
					<!-- Bootstrap modal for vendor search -->
	<div class="modal fade" id="vendorSearch" tabindex="-1" role="dialog" aria-labelledby="vendorSearch">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
    <!-- Modal END -->

	   
				
				<div class="modal fade" id="editAccMaint" tabindex="-1" role="dialog" aria-labelledby="editAccMaint">
		<div class="modal-dialog" role="document" style="width:60%;overflow:auto;">
			<div class="modal-content">
			<!-- Remote content load here -->
			</div>
		</div>
	</div>	
	<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch">
		<div class="modal-dialog" role="document" style="width:60%;overflow:auto;">
			<div class="modal-content">
			<!-- Remote content load here -->
			</div>
		</div>
	</div>	
	
	<!-- Search Criteria Modal-->  
        
    <div class="modal fade" id="vendorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
         <div class="modal-dialog" style="width:50%;overflow:auto;">
             <div class="modal-content">
		 	 <!-- Remote content load here -->
		  	 </div>
		</div>
	</div>
	<!-- ./End Search Criteria Modal -->
	
	<!-- View Modal-->
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
            <div class="modal-dialog" style="width:70%;overflow:auto;;">
                <div class="modal-content">
		 		 <!-- Remote content load here -->
		  		</div>
			</div>
	</div>
	
	<!-- Edit Modal-->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
            <div class="modal-dialog" style="width:80%;overflow:auto;">
                <div class="modal-content">
		 		 <!-- Remote content load here -->
		  		</div>
			</div>
	</div>
 		
 	<!-- Modal HTML for DELETE-->  
        
        <div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:400px;">
                <div class="modal-content">
		 		 <!-- Remote content load here -->
		  		</div>
			</div>
		</div>
 	<!-- Modal END -->
 	
 	
			
</body>
</html>