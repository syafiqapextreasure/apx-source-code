<%@page import="com.ilmu.cataloging.Paramips.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<script>
function convert(){
	alert("sds");
	 var fileName =document.getElementById("file").innerHTML ;
		

	var scriptType = document.getElementById("scripts").value;
	
	 $.get("Handler_Paramips",{action:"1",fileName:fileName,script:scriptType},function(authlink){
		 alert(authlink);
		 if(authlink.trim()=="converted"){
			 var elem = document.getElementById("myBars"); 
			    var width = 33;
			    var id = setInterval(frame, 33);
			    function frame() {
			        if (width >= 100) {
			            clearInterval(id);
			        } else {
			        	
			        	if(width!=66){
			            width++; 
			            elem.style.width = width + '%'; 
			            elem.innerHTML = width * 1 + '%';
			        	}
			        }
			    }
				 $.get("Handler_Paramips",{action:"2",fileName:fileName,script:scriptType},function(authlink){
								$.get("Handler_ParamipsTable",function(list){
									  
									$(".tableList").html(list);
									 var elem = document.getElementById("myBars"); 
									    var width = 66;
									    var id = setInterval(frame, 66);
									    function frame() {
									        if (width >= 100) {
									            clearInterval(id);
									        } else {
									        	
									        	if(width!=100){
									            width++; 
									            elem.style.width = width + '%'; 
									            elem.innerHTML = width * 1 + '%';
									        	}
									        }
									    }
								});

				
				 });
			}

	 });
}
</script>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<style>
#tagTable td:nth-child(1) { display: none;}
</style>
<style>
#myProgress {
  width: 100%;
  background-color: #ddd;
}

#myBars {
  width: 10%;
  height: 30px;
  background-color: #4CAF50;
  text-align: center;
  line-height: 30px;
  color: white;
}
</style>

<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<!-- <h4 class="modal-title" id="myModalLabel">
				Add Tag
		</h4> -->
		<%String file = request.getParameter("filename"); %>
			<input type="text" class="test" id="item2" value="">
	</div>
	<div class="modal-body">		
 		<form role="form" class="form-horizontal" id="current_form">
			<div class="form-group">
    			<label class="col-sm-3">
    				 <input type="checkbox" class="form-check-input">&nbsp;Select Marc File
    			</label>
    			<%
    				String files = request.getParameter("filename");
    			%>
    				<div id="file"><%=files %></div>
    
    				 <!--  <input type="file" name="img[]" class="file">
  	 <div class="input-group col-xs-12">
      <span class="input-group-addon"><i class="glyphicon glyphicon-picture"></i></span>
      <input type="text" class="form-control input-lg" disabled placeholder="Upload Image">
      <span class="input-group-btn">
        <button class="browse btn btn-primary input-lg" type="button"><i class="glyphicon glyphicon-search"></i> Browse</button>
      </span>
    </div> -->
  			</div>
  		<div class="form-group">
    		<label class="col-sm-3">
    			<input type="checkbox" class="form-check-input">&nbsp;Z39.50
    		</label>
    		<div class="col-sm-3">
    			<a class="btn btn-primary" id='z3950' data-dismiss="modal" data-toggle='modal' data-target='#Z3950' href='Z3950' title="z3950"><span class="glyphicon glyphicon-cloud-upload" style="color:white"></span></a>
    		</div>
    	</div>
    	
    	<div class="form-group">
    		<label class="col-sm-3">
    			Script File 
    		</label>
    		<div class="col-sm-5">
    			<select class="form-control" id="scripts">
    				<option value="0">&nbsp;Please select&nbsp;</option>
						<%
							List<Paramips> templates = Paramips.LoadTemplate();
								for (Paramips i : templates) {
						%>
								<option value="<%=i.getPARA01TPLNAME()%>"><%=i.getPARA01TPLNAME()%></option>
						<%
							}
						%>
    			</select>
    		</div>
    	</div>
				
				<div class="clearfix"></div>
										
		</form>
		
		<div id="myProgress">
		  <div id="myBars">0%</div>
		</div>
		<div class='tableList'></div>
	</div>

	<div class="modal-footer">
		<button type="button" class="btn btn-info" onclick="convert()">
			<span class="glyphicon glyphicon-save"></span> Add
		</button>
		<input type="button" name="cancel" value="Cancel" class="btn btn-info btn-cancel" data-dismiss="modal"/>
	</div>		
	

	