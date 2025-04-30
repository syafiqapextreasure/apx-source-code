<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@page import="com.ilmu.cataloging.Paramips.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/BO.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Template_Maintenance.js"></script>						
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	

<style>
#tagTable td:nth-child(1) { display: none;}
</style>
<style>
#myProgress {
  width: 100%;
  background-color: #ddd;
}

#myBar {
  width: 10%;
  height: 30px;
  background-color: #4CAF50;
  text-align: center;
  line-height: 30px;
  color: white;
}

#myProgress1 {
  width: 878px;
  background-color: #ddd;
  padding-left:0px;
}

#myBar1 {
  width: 0%;
  height: 30px;
  background-color: #4CAF50;
  text-align: center;
  line-height: 30px;
  color: black;
}

 a[disabled="disabled"] {
        pointer-events: none;
    }
</style>

<script>
function reset() {
	document.getElementById("current_form").reset();
	$('input[value=MARC]').prop('checked', true);
	$('a[id="z3950"]').attr('disabled','disabled');
	 $('input[type="file"]').removeAttr('disabled');
	 $("#status").html('<span class="label label-warning">No record</span>');
	 $(".tableList").html("");
	 $("#myProgress1").html("<div id='myBar1' class='myBar1'>0%</div>")

} 
$("input[name='modal_MARC']").change(function(){
	if($(this).val()=="MARC"){
		 $('a[id="z3950"]').attr('disabled','disabled');
		 $('input[type="file"]').removeAttr('disabled');
	}else if($(this).val()=="Z3950"){
		$('input[type="file"]').attr('disabled',true);
		 $('a[id="z3950"]').removeAttr('disabled');
	}
});
</script>

<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<!-- <h4 class="modal-title" id="myModalLabel">
				Add Tag
		</h4> -->
		<%String file = request.getParameter("filename"); %>
	</div>
	<div class="modal-body">

 	<form role="form" class="form-horizontal current_form"  enctype="multipart/form-data" >
 		<div class="form-group" id="div_Z390" style="display:none">
    			<label class="col-sm-3">
    				 <input type="radio" class="form-check-input" name='modal_MARC' value="MARC" disabled>&nbsp;Select Marc File
    			</label>
    			<input type="hidden" class="file filename2" value="" disabled>
    			<span class="filename2" id="filename2"></span>
    	</div>
    	
		<div class="form-group" id="div_MARC">
    			<label class="col-sm-3">
    				 <input type="radio" class="form-check-input" name='modal_MARC' value="MARC">&nbsp;Select Marc File
    			</label>
    			<input type="file" class="file" name="thefile" id="thefile" onchange="alertFilename()" disabled/>
    			<input type="hidden" name="action" value="1">
    	</div>
  		<div class="form-group">
    		<label class="col-sm-3">
    			<input type="radio" class="form-check-input" name='modal_MARC' value="Z3950">&nbsp;Z39.50
    		</label>
    		<div class="col-sm-3">
    		<a class="btn btn-primary" id='z3950' data-toggle='modal' data-target='#Z3950' href='Z3950' title="z3950" disabled="disabled"><span class="glyphicon glyphicon-globe" style="color:white"></span></a>
    		<input type="hidden" class="z3950Value" name="z3950Value"  >
    		<span id="status" style="font-size:12pt"><span class="label label-warning">No record</span></span>
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-3">
    			Script File 
    		</label>
    		<div class="col-sm-5">
    			<select class="form-control" id="script" name="script">
    			<!-- 	<option value="0">&nbsp;Please select&nbsp;</option> -->
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
		
<!-- 		<div id="myProgress">
		  <div id="myBar" class="myBar">0%</div>
		</div> -->
		<div id="myProgress1">
		  <div id="myBar1" class="myBar1">0%</div>
		</div>
		<div class='tableList'></div>
	</div>

	<div class="modal-footer">
		<button type="submit" class="btn btn-info" id="convert">
			<span class="glyphicon glyphicon-save"></span> Add
		</button>
		<button type="reset" class="btn btn-info" onClick="reset()">
			<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
		</button>
		<input type="button" name="cancel" value="Cancel" class="btn btn-info btn-cancel" data-dismiss="modal"/>
	</div>		
	

	