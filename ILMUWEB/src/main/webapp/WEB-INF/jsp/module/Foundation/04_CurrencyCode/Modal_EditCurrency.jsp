<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.CurrencyCode.*, java.util.List" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/CurrencyCode.js"></script>
<!-- Include Required Prerequisites -->
<%-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> --%>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>

<%
	String GL24FOREX = request.getParameter("GL24FOREX");
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getCurrency(GL24FOREX);
	System.out.println(e);
%>

<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Edit Currency Code</h4>
</div>
	
<!-- <form role="form" class="form-horizontal" method="post"> -->
<form role="form" id="currencyForm" class="form-horizontal" action="${pageContext.request.contextPath}/UpdateCurrency"  method="post">
	<div class="modal-body" style="height:40%;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 "><b>Currency Code</b></label>
                      <div class="col-sm-5 col-md-3">
                        <input type="text" class="form-control" id="GL24FOREX" name="GL24FOREX" onkeyup="toUppercase()" value="<%=e.getGL24FOREX()%>" disabled>
                      </div>
                      	<input type="hidden" id="Currency" name="Currency" class="form-control" value="<%=e.getGL24FOREX()%>">
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 "><strong>Description</strong></label>
                        <div class="col-sm-8">
                             <input type="text" class="form-control" id="GL24DESC" name="GL24DESC" value="<%=e.getGL24DESC()%>" onkeyup="toUppercase()">
                        </div>
                    </div>
                    
                    <div class="form-group">
                      	<label class="col-sm-3 "><b>Publisher's Rate</b></label>
                      	<div class="col-sm-3 col-md-3">
                        	<input type="text" class="form-control" id="GL24PRATE" name="GL24PRATE" value="<%=e.getGL24PRATE()%>">
                      	</div>
                    
                      	<div class="form-group">
                      	<label class="col-sm-3 ">Date Last Updated</label>
                    		<div class='col-sm-3'>
                      		<div class="input-group date">
  						 		<input type="text" class="form-control" id="GL24PDATE" name="GL24PDATE" value="<%=e.getGL24PDATE()%>" >
  						 		<span class="input-group-addon">
  								<i class="glyphicon glyphicon-calendar"></i></span>
							</div>
        			  		</div>
                  		</div>
                    </div>
                    
                    <div class="form-group">
                      	<label  class="col-sm-3 "><b>Bank's Rate</b></label>
                      	<div class="col-sm-2 col-md-3">
                        	<input type="text" class="form-control" id="GL24BRATE" name="GL24BRATE" value="<%=e.getGL24BRATE()%>" >
                      	</div>
                      	<div class="form-group">
                      	<label class="col-sm-3 ">Date Last Updated</label>
                      		<div class='col-sm-3'>
                      		<div class="input-group date">
  						 		<input type="text" class="form-control" id="GL24BDATE" name="GL24BDATE" value="<%=e.getGL24BDATE()%>" >
  						 		<span class="input-group-addon">
  								<i class="glyphicon glyphicon-calendar"></i></span>
							</div>
        			  		</div>
                  		</div>
                    </div>
                   
                   <!--  <div class="form-group">
                      <label class="col-sm-3 "><b>Unit Conversion</b></label>
                      <div class="col-md-4"> 
                      <div class="radio"> 
  						<label>
    					<input type="radio" class="minimal" name="GL10UNIT" id="D" value="D" checked >
    					 1 unit
  						</label>
  						</div>
                      <div class="radio"> 
  						<label>
    					<input type="radio" class="minimal" name="GL10UNIT" id="H" value="H" >
    					 100 units
  						</label>
					  </div>
					  </div>
			  	  	</div> -->
                    
                    
                    
                    
		
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		<!-- <button type="submit" class="btn btn-primary"  onclick="updateCurrency()">Save</button> -->
		<button type="submit" class="btn btn-primary" id="btn-save">Save</button>
		 <a href="template?MODULE=Foundation/04_CurrencyCode&ACTION=CurrencyTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
            </a>
	</div>			
	</form>
	
 <script type="text/javascript">
  $('.input-group.date').datepicker({
	    format: "dd/mm/yyyy",
	    todayBtn: true,
	    autoclose: true,
	    todayHighlight: true
	    
	    
	});
  </script> 
	