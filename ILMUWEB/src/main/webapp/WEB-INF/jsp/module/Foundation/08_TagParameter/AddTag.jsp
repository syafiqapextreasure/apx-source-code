<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="com.ilmu.foundation.TagParameter.SQLStatement"%>
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
            <i class="glyphicon glyphicon-education"></i>  Tag Parameter
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Foundation</a></li>
            <li><a href="#">Tag Parameter</a></li>
            <li><a href="Active">Add Tag Parameter</a></li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
        
        <div class="box box-default">
        <div class="box-header with-border">
          <h3 class="box-title">Add Tag Parameter</h3>
          
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
                  <label class="col-sm-3 control-label">MARC</label>
                  <div class="col-sm-5 col-md-3">
                  <select class="form-control" id="GL71BRNC" name="GL71BRNC" onchange="document.getElementById('GL71DESC').selectedIndex
                            						= document.getElementById('GL71BRNC').selectedIndex">
                      <option value="0">Please Select</option>
                      <%
                      GlobalSQLStatement brnchcode = new GlobalSQLStatement();
							List<Foundation> brnch = brnchcode.getBranch();
							for (Foundation list : brnch) {
					  %>
					  <option value="<%=list.getGL71BRNC()%>"><%=list.getGL71BRNC()%></option>
					  <%
						}
					  %>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-5">
                  <select class="form-control" id="GL71DESC" name="GL71DESC" onchange="document.getElementById('GL71BRNC').selectedIndex
                            						= document.getElementById('GL71DESC').selectedIndex">
                      <option value="0">Please Select</option>
                      <%
							GlobalSQLStatement brnchdesc = new GlobalSQLStatement();
							List<Foundation> brnchlist = brnchdesc.getBranch();
							for (Foundation list : brnchlist) {
					  %>
					  <option value="<%=list.getGL71DESC()%>"><%=list.getGL71DESC()%></option>
					  <%
						}
					  %>
				</select>
                </div>
                
                                
             </div>
    		
    		 <div class="form-group">
                  <label class="col-sm-3 control-label">Tag</label>
                  <div class="col-sm-2 col-md-3">
                     <input type="text" class="form-control" id="GL11DEPT" name="GL11DEPT" required>
                  </div>
             
                  <label class="col-sm-2 control-label">Revised from Tag</label>
                  <div class="col-sm-3">
                      <input type="text" class="form-control" id="GL11POSCODE" name="GL11POSCODE">
                  </div>
             </div>
             
             <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 control-label">Description</label>
                  <div class="col-sm-8">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL">
                  </div>
             </div>
                    
             <div class="form-group">
                  <label class="col-sm-3 control-label">Abbreviated Desc.</label>
                  <div class="col-sm-8">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL">
                  </div>
             </div> 
             
              <!-- START CUSTOM TABS -->
          <h2 class="page-header"></h2>
          <div class="row">
            <div class="col-md-12">
              <!-- Custom Tabs -->
              <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                  <li class="active"><a href="#tab_1" data-toggle="tab">Parameters</a></li>
                  <li><a href="#tab_2" data-toggle="tab">Indicator 1</a></li>
                  <li><a href="#tab_3" data-toggle="tab">Indicator 2</a></li>
                  <li><a href="#tab_4" data-toggle="tab">Subfields (a-j)</a></li>
                  <li><a href="#tab_5" data-toggle="tab">Subfields (k-t)</a></li>
                  <li><a href="#tab_6" data-toggle="tab">Subfields (u-z)</a></li>
                  <li><a href="#tab_7" data-toggle="tab">Subfields (0-9)</a></li>
                </ul>
                <div class="tab-content">
                  <div class="tab-pane active" id="tab_1">
                    <div class="form-group">
                  	<label class="col-sm-2 control-label"><b>Index Table</b></label>
                  	<div class="col-sm-4">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL">
                  	</div>
                  	
                  	<div class="col-md-6">
                      	<div class="checkbox">
      						<label class="col-sm-4 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> Repeatable
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
      						<label class="col-sm-5 ">
        					<input type="checkbox" class="minimal" name="GL39SUPPLIER" id="GL39SUPPLIER" value="Y" onclick = "change()"> Authority Flag
        					<input type="hidden" class="minimal" name="GL39SUPPLIER" id="Action1" value="N"> 
      						</label>
    					</div>
    				</div>
             		</div> 
             		
             		<div class="form-group">
                  	<label class="col-sm-2 control-label"><b>Acronym</b></label>
                  	<div class="col-sm-4">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL">
                  	</div>
                  	<div class="col-md-6">
                      	<div class="checkbox">
      						<label class="col-sm-4 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> Mandatory
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
      						<label class="col-sm-5 ">
        					<input type="checkbox" class="minimal" name="GL39SUPPLIER" id="GL39SUPPLIER" value="Y" onclick = "change()"> Copy and Paste
        					<input type="hidden" class="minimal" name="GL39SUPPLIER" id="Action1" value="N"> 
      						</label>
    					</div>
    				</div>
             		</div> 
             		
             		<div class="form-group">
                  	<label class="col-sm-2 control-label"><b>Authority Group</b></label>
                  	<div class="col-sm-4">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL">
                  	</div>
                  	<div class="col-md-6">
                      	<div class="checkbox">
      						<label class="col-sm-4 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> Index Flag
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
      						<label class="col-sm-5 ">
        					<input type="checkbox" class="minimal" name="GL39SUPPLIER" id="GL39SUPPLIER" value="Y" onclick = "change()"> PARAMIPS Link
        					<input type="hidden" class="minimal" name="GL39SUPPLIER" id="Action1" value="N"> 
      						</label>
    					</div>
    				</div>
             		</div> 
             		
             		<div class="form-group">
                  	<label class="col-sm-2 control-label"><b>Field Length</b></label>
                  	<div class="col-sm-4">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL">
                  	</div>
                  	<div class="col-md-6">
                      	<div class="checkbox">
      						<label class="col-sm-4 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> Keyword
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
      						<label class="col-sm-5 ">
        					<input type="checkbox" class="minimal" name="GL39SUPPLIER" id="GL39SUPPLIER" value="Y" onclick = "change()"> Multimedia Tag
        					<input type="hidden" class="minimal" name="GL39SUPPLIER" id="Action1" value="N"> 
      						</label>
    					</div>
    				</div>
             		</div> 
             		
             		<div class="form-group">
                  	<label class="col-sm-2 control-label"><b>Index Language</b></label>
                  	<div class="col-sm-4">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL">
                  	</div>
                  	<div class="col-md-6">
                      	<div class="checkbox">
      						<label class="col-sm-5 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> Duplicate Check
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
    					</div>
    				</div>
             		</div> 
             		
             		<div class="form-group">
                  <label class="col-sm-2 control-label">Default Indicator</label>
                  <label class="col-sm-2 control-label">Indicator 1 </label>
                  <div class="col-sm-5 col-md-1">
                  <select class="form-control" id="GL71BRNC" name="GL71BRNC" onchange="document.getElementById('GL71DESC').selectedIndex
                            						= document.getElementById('GL71BRNC').selectedIndex">
                      
					  <option value="">#</option>
					  <option value="">1</option>
					  <option value="">2</option>
					  <option value="">3</option>
					  <option value="">4</option>
					  <option value="">5</option>
					  
				</select>
                </div>
               <label class="col-sm-2 control-label">Indicator 2 </label>
                  <div class="col-sm-2 col-md-1">
                  <select class="form-control" id="GL71DESC" name="GL71DESC" onchange="document.getElementById('GL71BRNC').selectedIndex
                            						= document.getElementById('GL71DESC').selectedIndex">
                      
					  <option value="">#</option>
					  <option value="">1</option>
					  <option value="">2</option>
					  <option value="">3</option>
					  <option value="">4</option>
					  <option value="">5</option>
					  
				</select>
                </div>      
             </div>
             
             		<div class="form-group">
                  	<label class="col-sm-2 control-label"><b>Default Value</b></label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL">
                  	</div>
             		</div>
             		
             		<div class="form-group">
                  	<label class="col-sm-2 control-label"><b>Remark</b></label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL">
                  	</div>
             		</div>
             
             
                  </div><!-- /.tab-pane -->
                  <!-- Tab 2 -->
                  <div class="tab-pane" id="tab_2">
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> Undefined (#)
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 0
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 1
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 2
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 3
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 4
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 5
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 6
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 7
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 8
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 9
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  </div><!-- /.tab-pane -->
                  
                  <!-- Tab 3 -->
                  <div class="tab-pane" id="tab_3">
                   <div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> Undefined (#)
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 0
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 1
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 2
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 3
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 4
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 5
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 6
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 7
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 8
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 9
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  </div><!-- /.tab-pane -->
                  
                  <!-- Tab 4 -->
                  <div class="tab-pane" id="tab_4">
                   <div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> a
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> b
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> c
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> d
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> e
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> f
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> g
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> h
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> i
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> j
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  </div><!-- /.tab-pane -->
                  
                  <!-- Tab 5 -->
                  <div class="tab-pane" id="tab_5">
                   <div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> k
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> l
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> m
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> n
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> o
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> p
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> q
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> r
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> s
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> t
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  </div><!-- /.tab-pane -->
                  
                  <!-- Tab 6 -->
                  <div class="tab-pane" id="tab_6">
                   <div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> u
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> v
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> w
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> x
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> y
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> z
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  </div><!-- /.tab-pane -->
                  
                  <!-- Tab 7 -->
                  <div class="tab-pane" id="tab_7">
                   <div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 0
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 1
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 2
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 3
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 4
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 5
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 6
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 7
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 8
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  	<div class="form-group">
      						<label class="col-sm-2 " >
        					<input type="checkbox" class="minimal" name="GL39PUB" id="GL39PUB" value="Y" onclick = "change()"> 9
        					<input type="hidden" class="minimal" name="GL39PUB" id="Action1" value="N"> 
      						</label>
                  	<div class="col-sm-9">
                      <input type="text" class="form-control" id="GL11TEL" name="GL11TEL" readonly>
                  	</div>
                  	</div>
                  </div><!-- /.tab-pane -->
                </div><!-- /.tab-content -->
              </div><!-- nav-tabs-custom -->
            </div><!-- /.col -->
            </div>
              
       		</div><!-- /.col -->
       		</div> <!-- /.row -->
       		
       		<div class="modal-footer">
            <input type="submit" name="add" value="Add" style="width: 80px" class="btn btn-primary" id="btn_add"/>
            <a href="template?MODULE=Foundation/08_TagParameter&ACTION=TagTable.jsp">
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

