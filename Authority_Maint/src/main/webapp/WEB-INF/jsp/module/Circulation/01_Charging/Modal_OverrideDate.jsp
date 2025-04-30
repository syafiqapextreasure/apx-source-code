<%@page import="com.ilmu.circulation.Charging.*,
				java.util.*,com.ilmu.global.*"%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Circulation.js"></script>		
 --%><script type="text/javascript">
$(document).ready(function() {
	  $("#overrideCurrDate").click(function(){
		   var action = $( 'input[name=actiontype]:checked' ).val();
		  var patron = null;
		  var dueDetails = null;
		  if(action=="charge"){
			  patron = $("#GL14PATR").val();
			  dueDetails = ($("#datetimepicker1").find("input").val()).split(" ");
		  }else if(action=="discharge"){
			  patron = $("#hiddenPatrId").val();
			  dueDetails = ($("#datetimepicker2").find("input").val()).split(" ");
		  }else if(action=="renewal"){
			  patron = $("#hiddenPatrId").val();
			  dueDetails = ($("#datetimepicker3").find("input").val()).split(" ");
		  }
	
		   var url = "Handler_OverrideDate?dueDate=" + dueDetails[0] + "&patron=" + patron;
			 $.get(url,function(data){
				 var message = data.split(",");
				 if(message[0].trim()=="092"){
					 setTimeout(function(){ 
						 messageBox(message[0].trim(),message[1], "@desc");
						 }, 100);
				 }else if(data.trim()== "068"){
						//messageBox("068","","");
					  oerrideMemExp(data.trim());
				}else{
					if(action=="discharge"){
						 $('#table-charging tr.status td:eq(9)').text(dueDetails[0]);
					       $('#table-charging tr.status td:eq(10)').text(dueDetails[1]);
					}else{
					 $('#table-charging tr.status td:eq(4)').text(dueDetails[0]);
				       $('#table-charging tr.status td:eq(5)').text(dueDetails[1]);
					}
				 }
			   });
	     /*  $('#table-charging tr.status td:eq(4)').text(dueDetails[0]);
	       $('#table-charging tr.status td:eq(5)').text(dueDetails[1]);*/
	  });
    $(function () {
    	
    	   var action = $( 'input[name=actiontype]:checked' ).val();
    	   var dateToday = new Date();
    	 if(action=="charge"){
    		var dueDate = $('#table-charging tr.status td.dischargeDate').text();
 			var dueTime =  $('#table-charging tr.status td:eq(5)').text();
 			var date = dueDate.split("/"); 
 			var output = date[2]+"-" +date[1]+ "-" +date[0] + " " + dueTime;
    	  $('#datetimepicker1').datetimepicker({ 
      		format : 'DD/MM/YYYY HH:mm:ss',
      	     defaultDate:output,
      	   	minDate:new Date()
      	});
    	 }else if(action=="discharge"){
   		 		var dueDate = $('#table-charging tr.status td.Rdate').text();
				var dueTime =  $('#table-charging tr.status td.Rtime').text();
				var date = dueDate.split("/"); 
				var output = date[2]+"-" +date[1]+ "-" +date[0] + " " + dueTime;
				
				var RDate = $('#table-charging tr.status td.Ddate').text();
				var rdate = RDate.split("/"); 
				var routput = rdate[2]+"-" +rdate[1]+ "-" +rdate[0] + " " + dueTime;
    	  		$('#datetimepicker2').datetimepicker({ 
        		format : 'DD/MM/YYYY HH:mm:ss',
        		defaultDate:output,
        		maxDate:output,
        		minDate:routput,
        	});
    	 }else if(action=="renewal"){
		 		 var dueDate = $('#table-charging tr.status td.Rdate').text();
					var dueTime =  $('#table-charging tr.status td.Rtime').text();
					var date = dueDate.split("/"); 
					var output = date[2]+"-" +date[1]+ "-" +date[0] + " " + dueTime;
	    	  		$('#datetimepicker3').datetimepicker({ 
	        		format : 'DD/MM/YYYY HH:mm:ss',
	        		defaultDate:output,
	        		 minDate:new Date()
	        	});
	    	 }
    	  
    /* 	  $('#datetimepicker2').datetimepicker().on('dp.change', function (e) {
    		 
				$('#datetimepicker2').data('DateTimePicker').date(new Date(output));
    	    });  */
 /*    $('#datetimepicker1').datetimepicker({ 
    		format : 'DD/MM/YYYY HH:mm:ss',
             minDate: moment()
    	});
    
    $('#datetimepicker1').datetimepicker().on('dp.change', function (e) {
       var dueDetails = ($("#datetimepicker1").find("input").val()).split(" ");
       $('#table-charging tr.status td:eq(4)').text(dueDetails[0]);
       $('#table-charging tr.status td:eq(5)').text(dueDetails[1]);

    }); */

    
});
});
</script>
<style>
.modal-body {
    overflow-x: visible !important;
    }
</style>
<%
	String action = request.getParameter("action");
	String chargeDate = request.getParameter("chargeDate");
	String chargeTime = request.getParameter("chargeTime");
%>
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal">&times;</button>
Override Date
</div>
<div class="modal-body" style="height:80%;width:auto;">
<div class="bs-example">
    <div class="panel panel-default">
        <div class="panel-heading">
        	<%
        		String borrowLbl = null;
        		if(action.equals("charge")){
        			borrowLbl = "Charge";
        		}else if(action.equals("discharge")){
        			borrowLbl = "Borrow";
        		}else if(action.equals("renewal")){
        			borrowLbl = "Due";
        		}
        	%>
        	<label><%=borrowLbl %> Date/Time</label>
		</div>
        <div class="panel-body">
        	<div class="row">
			    <div class="col-sm-3">
			     	<label><%=borrowLbl %> Date :</label>
			    </div>
			     <div class="col-sm-5 oriDate">
			     	<%=chargeDate %>
			    </div>
			  </div>
			  	<div class="row">
				    <div class="col-sm-3">
				     	<label><%=borrowLbl %> Time :</label>
				    </div>
				    <div class="col-sm-5 oriTime">
				     	<%=chargeTime %>
				    </div>
			  </div>
        </div>
    </div>
</div>

<div class="bs-example">
    <div class="panel panel-default">
        <div class="panel-heading">
        	<%
        		String dueLbl = null;
        		if(action.equals("charge")||action.equals("renewal")){
        			dueLbl = "Due";
        		}else if(action.equals("discharge")){
        			dueLbl = "Return";
        		}
        	%>
        	<label>New <%=dueLbl %> Date/Time</label>
		</div>
        <div class="panel-body">
        	<div class="row">
			    <div class="col-sm-3">
			     	<label><%=dueLbl %> Date/Time :</label>
			    </div>
			    <%
			    	if(action.equals("charge")){
			    %>
			     <div class="col-sm-5">
			     <div class='input-group date datetimepicker1' id='datetimepicker1'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
			    </div>
			    <%
			    	}else if(action.equals("renewal")){
			    %>
			    
			       <div class="col-sm-5">
			     <div class='input-group date datetimepicker1' id='datetimepicker3'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
			    </div>
			    
			    <%
			    	}else{
			    %>
			      <div class="col-sm-5">
			     <div class='input-group date datetimepicker1' id='datetimepicker2'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
			    </div>
			    <%
			    	}
			    %>
			  </div>
        </div>
    </div>
</div>
</div>
<div class="modal-footer">
		<button type="button" class="btn btn-info" id="overrideCurrDate" data-dismiss="modal">
			Replace
		</button>
		<input type="button" name="cancel" value="Close" class="btn btn-info" data-dismiss="modal"/>
	</div>	