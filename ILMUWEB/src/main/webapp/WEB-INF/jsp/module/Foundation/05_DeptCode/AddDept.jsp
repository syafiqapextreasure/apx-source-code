<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="com.ilmu.foundation.DepartmentCode.SQLStatement"%>
<%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
  <head>
   
    <!-- File Input -->
    <link rel="stylesheet" href="../../../../dist/css/fileinput/jasny-bootstrap.min.css">
   
	<script src="../../../../js/jquery-1.11.1.js" type="text/javascript"></script>
	<script type="text/javascript" src="../../../../js/ajax.js"></script>

  </head>
  
<!-- BODY -->

<body>

<!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            <i class="glyphicon glyphicon-education"></i>  Department Code
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Foundation</a></li>
            <li><a href="#">Department Code</a></li>
            <li><a href="Active">Add Department Code</a></li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
        
        <div class="box box-default">
        <div class="box-header with-border">
          <h3 class="box-title">Add Department Code</h3>
          
          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-remove"></i></button>
          </div>
        </div>
        <!-- /.box-header -->
        
        <!-- START FORM -->
        <form class="form-horizontal" action="CourseCode" method="post">
        <div class="box-body">
          <div class="row">
           <div class="col-md-1">
           </div>
            <div class="col-md-9">
            
             <div class="form-group">
                  <label for="inputEmail3" class="col-sm-3 control-label">Department Code</label>
                  <div class="col-sm-5 col-md-3">
                     <input type="text" class="form-control" id="GL11DEPT" name="GL11DEPT" required>
                  </div>
             </div>
                    
             <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label">Department Name</label>
                  <div class="col-sm-7">
                      <input type="text" class="form-control" id="GL11NAME" name="GL11NAME">
                  </div>
             </div>
             
             <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label">Address</label>
                  <div class="col-sm-7">
                      <input type="text" class="form-control" id="GL11ADD1" name="GL11ADD1">
                  </div>
             </div>
             <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label"></label>
                  <div class="col-sm-7">
                      <input type="text" class="form-control" id="GL11ADD2" name="GL11ADD2">
                  </div>
             </div>
             <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label"></label>
                  <div class="col-sm-7">
                      <input type="text" class="form-control" id="GL11ADD3" name="GL11ADD3">
                  </div>
             </div>
             
             <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label">Postcode</label>
                  <div class="col-sm-3">
                      <input type="text" class="form-control" id="GL11POSCODE" name="GL11POSCODE">
                  </div>
             </div>
             <!-- Town Code -->
             <div class="form-group">
                  <label class="col-sm-3 control-label">Town</label>
                  <div class="col-sm-5 col-md-3">
                  <select class="form-control" id="GL11TOWN" name="GL11TOWN" onchange="document.getElementById('GL15DESC').selectedIndex
                            						= document.getElementById('GL11TOWN').selectedIndex">
                      <option value="0">Please Select</option>
                      <%
							GlobalSQLStatement code = new GlobalSQLStatement();
							List<Foundation> codelist = code.getTown();
							for (Foundation e : codelist) {
					  %>
					  <option value="<%=e.getGL15TOWN()%>"><%=e.getGL15TOWN()%></option>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="GL15DESC" name="GL15DESC" onchange="document.getElementById('GL11TOWN').selectedIndex
                            						= document.getElementById('GL15DESC').selectedIndex">
                      <option value="0">Please Select</option>
                      <%
                      GlobalSQLStatement town = new GlobalSQLStatement();
							List<Foundation> townlist = town.getTown();
							for (Foundation e : townlist) {
					  %>
					  <option value="<%=e.getGL15DESC()%>"><%=e.getGL15DESC()%></option>
					  <%
						}
					  %>
				</select>
                </div>
                
                                
             </div>
             
             <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label">Telephone Number</label>
                  <div class="col-sm-3">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL">
                  </div>
             </div>
                    
             <div class="form-group">
                  <label class="col-sm-3 control-label">Head of Department</label>
                  <div class="col-sm-5 col-md-2">
                       <input type="text" class="form-control format-vendorCode validate-char" id="patronId" name="GL11HEAD"  name="patronId">
                  </div>
                  <button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="../../../include/shared/SearchModal.jsp">...</button>
                  <div class="col-sm-2 col-md-5">
                  <div class="col-sm-4"><div id="div-vendorName"></div></div>
                       <input type="text" class="form-control" id="title" name="title" disabled>
                  </div>
             </div> 
            
			<div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label">Number of Staff</label>
                  <div class="col-sm-3">
                      <input type="text" class="form-control" id="GL11STAFF" name="GL11STAFF">
                  </div>
             </div>
             
             <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label">Fax Number</label>
                  <div class="col-sm-3">
                      <input type="text" class="form-control" id="GL11FAX" name="GL11FAX">
                  </div>
             </div>
              
       		</div><!-- /.col -->
       		</div> <!-- /.row -->
       		
       		<div class="modal-footer">
            <input type="submit" name="add" value="Add" style="width: 80px" class="btn btn-primary" id="btn_add"/>
            <a href="template?MODULE=Foundation/05_DepartmentCode&ACTION=DeptCode.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
            </a>
        </div>
          
          
          </div><!-- /.box-body -->
          </form>
          
        </div>
        

        </section><!-- /.content -->
      
     

     

    <!-- jQuery 2.1.4 
    <script src="../../../../plugins/jQuery/jQuery-2.1.4.min.js"></script>-->
    <!-- Bootstrap 3.3.5 -->
    <script src="../../../../bootstrap/js/bootstrap.min.js"></script>
    <!-- DataTables -->
    <script src="../../../../plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="../../../../plugins/datatables/dataTables.bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="../../../../plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="../../../../plugins/fastclick/fastclick.min.js"></script>
    
    <!-- File Input -->
    <script src="../../../../js/fileinput/jasny-bootstrap.min.js"></script>
    
    
    <!-- AdminLTE App -->
    <script src="../../../../dist/js/app.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="../../../../dist/js/demo.js"></script>
    <!-- date-picker -->
    <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"></script> -->
    <script src="../../../../plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <!-- page script -->
   <!--  <script type="text/javascript">
    $('.datepicker input').datepicker({
    	todayBtn: true,
    	autoclose: true,
        todayHighlight: true
    });
    </script> -->
  <script type="text/javascript">
  $('.input-group.date').datepicker({
	    format: "dd/mm/yyyy",
	    todayBtn: true,
	    autoclose: true,
	    todayHighlight: true
	});
  </script>  

   
  </body>
</html>

