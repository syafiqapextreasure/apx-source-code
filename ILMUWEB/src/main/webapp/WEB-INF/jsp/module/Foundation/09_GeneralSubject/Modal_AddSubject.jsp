<%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.GeneralSubject.*, java.util.List" %>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/GeneralSubject.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->


<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> --%>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>

<script type="text/javascript">
        var specialKeys = new Array();
        specialKeys.push(8); //Backspace
        specialKeys.push(9); //Tab
        specialKeys.push(46); //Delete
        specialKeys.push(36); //Home
        specialKeys.push(35); //End
        specialKeys.push(37); //Left
        specialKeys.push(39); //Right
        function IsAlphaNumeric(e) {
            var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
            var ret = ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode));
            document.getElementById("error").style.display = ret ? "none" : "inline";
            return ret;
        }
    </script>

<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel" align="center">Add General Subject</h4>
</div>
	
<!-- <form role="form" class="form-horizontal" method="post"> -->
<form role="form" id="genSubForm" class="form-horizontal" action="${pageContext.request.contextPath}/AddGeneralSubj" method="post">
	<div class="modal-body" style="height:40%;overflow:auto">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-md-10">
			
			
            
                    <div class="form-group">
                      <label class="col-sm-3 "><strong>Subject Start</strong></label>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL54SUBJSTA" name="GL54SUBJSTA" onkeyup="toUppercase()" onkeypress="return IsAlphaNumeric(event);" ondrop="return false;"
                            onpaste="return false;" required>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <!-- <div id="ajaxResponse" style="color:red"></div> -->
                       <span id="error" style="color: Red; display: none">* Special Characters not allowed</span>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 "><strong>Subject End</strong></label>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL54SUBJEND" name="GL54SUBJEND" onkeyup="toUppercase()">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 control-label"></label>
                      <div class="col-sm-5 col-md-4">
                       <div id="ajaxResponse2" style="color:red"></div>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label  class="col-sm-3 "><strong>Join Character</strong></label>
                      <div class="col-sm-3 col-md-3">
                        <input type="text" class="form-control" id="GL54MARK" name="GL54MARK">
                      </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 "><strong>Description</strong></label>
                        <div class="col-sm-7">
                             <input type="text" class="form-control" id="GL54DESC" name="GL54DESC" onkeyup="toUppercase()">
                        </div>
                    </div>
                    
                    
		
			</div><!-- /.END COLUMN 13 -->
		 
	 	</div><!-- /.END ROW -->
		
	</div><!-- /.END PANEL BODY -->
	</div><!-- /.END MODAL BODY -->
	
	<div class="modal-footer">
		<!-- <button type="submit" class="btn btn-primary" id="" style="width:80px;" onclick="addGenSubj()">Save</button> -->
		<input type="submit" class="btn btn-primary" value="Save" id="btn-add" style="width:80px;">
		 <a href="template?MODULE=Foundation/09_GeneralSubject&ACTION=SubjTable.jsp">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
            </a>
	</div>			
	</form>
	

	