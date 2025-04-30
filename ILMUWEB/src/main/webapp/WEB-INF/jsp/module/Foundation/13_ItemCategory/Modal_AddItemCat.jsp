<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.PatronCategory.*, java.util.List" %>
<%@ page import="java.sql.*" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/ItemCat.js"></script>	
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script> -->



<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> --%>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>


<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Add Item Category</h4>
</div>
	
<!-- <form role="form" class="form-horizontal" method="post"> -->
<form role="form" id="icatForm" class="form-horizontal" action="${pageContext.request.contextPath}/AddItemCat" method="post">
	<div class="modal-body" style="height:45%;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 "><strong>Item Category</strong></label>
                      <div class="col-sm-3 col-md-2">
                        <input type="text" class="form-control" id="GL10ICAT" name="GL10ICAT" onkeyup="toUppercase()" required>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 "><strong>Description</strong></label>
                      <div class="col-sm-7">
                        <input type="text" class="form-control" id="GL10DESC" name="GL10DESC" onkeyup="toUppercase()" required>
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Display</label>
                      <div class="col-sm-7">
                        <input type="text" class="form-control" id="GL10DISPLAY" name="GL10DISPLAY" onkeyup="toUppercase()" >
                      </div>
                    </div>
                    
                    <div class="form-group" >
                      <label for="inputPassword3" class="col-sm-3 ">Maximum Loan</label>
                      <div class="col-sm-7">
                        <input type="text" class="form-control" id="GL10ELIG" name="GL10ELIG" onkeyup="toUppercase()">
                      </div>
                    </div>
                    
                     <div class="form-group" id ="selectRadio">
                     <label for="inputPassword3" class="col-sm-3 ">Borrowing Period</label>
                     <div class="radio">
      					<label class="col-sm-2 ">
        				<input type="radio" class="minimal" name=GL10UNIT id="day" value="D" checked> Day
        				</label>
      					
      					<label class="col-sm-2 ">
        				<input type="radio" class="minimal" name="GL10UNIT" id="hour" value="H"> Hour
        				</label>
    				 </div>
    				 </div>
                    
                     <div class="form-group" id ="selectCheckbox">
                     <div class="checkbox">
      					<label class="col-sm-7 ">
        				<input type="checkbox" class="minimal" name=GL10RESERVEC id="GL10RESERVEC" value="Y"> Item Category for Reserve Collection
        				</label>
    				 </div>
    				</div>
                    
                   
              
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		<!-- <button type="submit" class="btn btn-primary" id="btn-add" style="width:80px;" onclick="addItemCat()">Add</button> -->
		 <input type="submit" class="btn btn-primary" value="Save" id="btn-add" style="width:80px;">
		 <a href="template?MODULE=Foundation/13_ItemCategory&ACTION=ItemCatTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
            </a>
	</div>			
	</form>
	

	