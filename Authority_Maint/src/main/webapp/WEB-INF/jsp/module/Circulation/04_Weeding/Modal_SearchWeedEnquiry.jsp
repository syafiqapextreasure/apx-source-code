  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.foundation.global.*"%>
<script>
$(document).ready(function() {
	$(".date-picker").datepicker();
});
function change(){
	var action = $('input[name=criteria]:checked' ).val();
	
	switch(action) {
		case "title":
			$(".searchInput").html('<input type="text" class="form-control" id="search">')
			break;
		case "date":
			$(".searchInput").html('<div class="input-group">' + 
		    						'<input type="date" class="form-control" id="search" maxlength="10"/>'+
		    						'<span class="input-group-addon">to</span>'+
		    						'<input type="date" class="form-control" id="search1" maxlength="10"/>'+
									'</div>');
			/* $("input#search,input#search1").keyup(function(e){
			    var key=String.fromCharCode(e.keyCode);
			    if(!(key>=0&&key<=9))$(this).val($(this).val().substr(0,$(this).val().length-1));
			    var value=$(this).val();
			    if(value.length==2||value.length==5)$(this).val($(this).val()+'/');
			}); */
			//$(".searchInput").html('<input type="text" class="form-control" id="search">')
			break;
		case "accession":
			/* $(".searchInput").html('<div class="input-group">' + 
									'<input type="text" class="form-control" id="search" maxlength="10"/>'+
									'<span class="input-group-addon">to</span>'+
									'<input type="text" class="form-control" id="search1" maxlength="10"/>'+
									'</div>'); */
			$(".searchInput").html('<input type="text" class="form-control" id="search">')
			//recall();
			//$('#weeding_enquiry').dataTable().fnClearTable();
			break;
			
		default:
	}
}
</script>
  <div class="modal-header">
     <button type="button" class="close" data-dismiss="modal" aria-label="Close">
   	 <span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">Search Criteria</h4>
  </div>
  <div class="modal-body">
  	<div class="row">
         <div class="form-group row">
			<label for="Title" class="col-sm-2 col-form-label">
				 <input class="form-check-input" type="radio" name="criteria" id="inlineRadiocharge" value="accession" onchange="change()" checked>Accession No
			</label>
		</div>
	</div>
	<div class="row">
         <div class="form-group row">
			<label for="Date" class="col-sm-2 col-form-label">
				 <input class="form-check-input" type="radio" name="criteria" id="inlineRadiocharge" value="date" onchange="change()">Weed Date
			</label>
			<div class="col-sm-5 searchInput">
				<div class="input-group">
				    <input type="text" class="form-control" id="search" maxlength="10"/><!-- 
				    	<span class="input-group-addon">to</span>
				    <input type="text" class="form-control" id="search1" maxlength="10"/> -->
				</div>
			</div>
			
		</div>
	</div>
	<div class="row">
         <div class="form-group row">
			<label for="Title" class="col-sm-2 col-form-label">
				 <input class="form-check-input" type="radio" name="criteria" id="inlineRadiocharge" value="title" onchange="change()">Title
			</label>
<!-- 			<div class="col-sm-10">
				<label>:&nbsp;</label><input type="text" max-length="10" id="title">
			</div> -->
		</div>
	</div>
  </div>
  <div class="modal-footer">
  <button class="btn btn-sm btn-info" id="searchWeedSubmit" data-dismiss="modal" onclick="submitWeeding()">Search</button>
  <button class="btn btn-sm btn-info" data-dismiss="modal">Close</button>
  </div>