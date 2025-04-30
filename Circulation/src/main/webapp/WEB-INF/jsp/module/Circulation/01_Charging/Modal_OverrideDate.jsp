<%@page import="com.ilmu.circulation.Charging.*,
				java.util.*,com.ilmu.global.*"%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Circulation.js"></script>		
 --%>
 <script type="text/javascript">
 
 
 var defaults = {
			showClear: true,
			showClose: true,
			allowInputToggle: true,
			useCurrent: false,
			ignoreReadonly: true,
			//minDate: new Date(),
			locale:'en',
			toolbarPlacement: 'top',
			icons: {
				time: 'fa fa-clock-o',
				date: 'fa fa-calendar',
				up: 'fa fa-angle-up',
				down: 'fa fa-angle-down',
				previous: 'fa fa-angle-left',
				next: 'fa fa-angle-right',
				today: 'fa fa-dot-circle-o',
				clear: 'fa fa-trash',
				close: 'fa fa-times'
			}
		};

	$(function() {
			 var action = $( 'input[name=actiontype]:checked' ).val();
			 var dateToday = new Date();
			 if(action=="charge"){
				 var patron = $("#GL14PATR").val();
				   var url = "GetHoliday?patron=" + patron;

				   $.get(url,function(data){
					   var ar = data.split(','); // split string on comma space
						 $("#table-charging").find("tbody tr").each(function () {
							 var chargeDate = $(this).find('td:eq(2)').text();
					 		 var dueDate = $(this).find('td:eq(4)').text();
					 		 var dueTime = $(this).find('td:eq(5)').text();
					 		 var optionsDate = $.extend({}, defaults, {format:'DD/MM/YYYY',   
					 			 										disabledDates: ar});
					 		$(".date").val(dueDate);
					 		$(".time").val(dueTime);
					 		$('.datepicker').datetimepicker(optionsDate);
					 		var optionsTime = $.extend({}, defaults, {format:'HH:mm:ss'});
					 		$('.timepicker').datetimepicker(optionsTime);
				 			});
						});
					 }else if(action=="discharge"){
						 var patron = $("#GL14PATR").val();
						   var url = "GetHoliday?patron=" + patron;

						   $.get(url,function(data){
							   var ar = data.split(','); // split string on comma space
								 $("#table-charging").find("tbody tr").each(function () {
									// var chargeDate = $(this).find('td:eq(2)').text();
							 		 var dueDate = $(this).find('td:eq(9)').text();
							 		 var dueTime = $(this).find('td:eq(10)').text();
							 		 var optionsDate = $.extend({}, defaults, {format:'DD/MM/YYYY',   
							 			 										disabledDates: ar});
							 		$(".date").val(dueDate);
							 		$(".time").val(dueTime);
							 		$('.datepicker').datetimepicker(optionsDate);
							 		var optionsTime = $.extend({}, defaults, {format:'HH:mm:ss'});
							 		$('.timepicker').datetimepicker(optionsTime);
						 			});
								});
							 }else{
								 var patron = $("#GL14PATR").val();
								   var url = "GetHoliday?patron=" + patron;

								   $.get(url,function(data){
									   var ar = data.split(','); // split string on comma space
										 $("#table-charging").find("tbody tr").each(function () {
											 //var chargeDate = $(this).find('td:eq(2)').text();
									 		 var dueDate = $(this).find('td:eq(7)').text();
									 		 var dueTime = $(this).find('td:eq(8)').text();
									 		 var optionsDate = $.extend({}, defaults, {format:'DD/MM/YYYY',   
									 			 										disabledDates: ar});
									 		$(".date").val(dueDate);
									 		$(".time").val(dueTime);
									 		$('.datepicker').datetimepicker(optionsDate);
									 		var optionsTime = $.extend({}, defaults, {format:'HH:mm:ss'});
									 		$('.timepicker').datetimepicker(optionsTime);
								 			});
										});
									 }
			 
			  $("#overrideCurrDate").click(function(){
				   var action = $( 'input[name=actiontype]:checked' ).val();
				  var patron = null;
				  var dueDate =  $(".date").val();
				  var dueTime = $(".time").val();
				  if(action=="charge"){
					  patron = $("#GL14PATR").val();
					/*   dueDate = $(".date").val();
					  dueTime = $(".time").val(); */
					 // dueDetails = ($("#datetimepicker1").find("input").val()).split(" ");
				  }else if(action=="discharge"){
					  patron = $("#hiddenPatrId").val();
					//  dueDetails = ($("#datetimepicker2").find("input").val()).split(" ");
				  }else if(action=="renewal"){
					  patron = $("#hiddenPatrId").val();
					//  dueDetails = ($("#datetimepicker3").find("input").val()).split(" ");
				  }
			
				   var url = "Handler_OverrideDate?dueDate=" + dueDate + "&patron=" + patron;
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
								 $('#table-charging tr.status td:eq(9)').text(dueDate);
							       $('#table-charging tr.status td:eq(10)').text(dueTime);
							}else if(action=="charge"){
								 $('#table-charging tr.status td:eq(4)').text(dueDate);
							       $('#table-charging tr.status td:eq(5)').text(dueTime);
							}else{
							 $('#table-charging tr.status td:eq(7)').text(dueDate);
						       $('#table-charging tr.status td:eq(8)').text(dueTime);
							}
						 }
					   });
			     /*  $('#table-charging tr.status td:eq(4)').text(dueDetails[0]);
			       $('#table-charging tr.status td:eq(5)').text(dueDetails[1]);*/
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
        	<%-- <div class="row">
			    <div class="col-sm-3">
			     	<label><%=dueLbl %> Date/Time :</label>
			    </div>
			    <%
			    	if(action.equals("charge")){
			    %>
			     <div class="col-sm-5">
			     <div class='input-group date datepicker1' id='datepicker1'>
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
			  </div> --%>
	<%-- 		  <div class="row">
			    <div class="col-sm-3">
			     	<label><%=dueLbl %> Date/Time :</label>
			    </div>
			   	<div class="col-sm-5">
			     <div class='input-group date timepicker' id='timepicker'>
                    <input type='time' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-time"></span>
                    </span>
                </div>
			    </div>
			  </div> --%>
			  <div class="row">
				<div class="col-sm-3">
			     	<label><%=dueLbl %> Date :</label>
			    </div>
			   	<div class="col-sm-5">
					<div class="form-group">
						<div class="input-group datepicker">
							<input type="text" class="form-control date" readonly>
							<span class="input-group-addon">
								<span class="fa fa-calendar"></span>
							</span>
						</div>
					</div>
				</div>
			</div>
			 <div class="row">
				<div class="col-sm-3">
			     	<label><%=dueLbl %> Time :</label>
			    </div>
			   	<div class="col-sm-5">
					<div class="form-group">
						<div class="input-group timepicker">
							<input type="text" class="form-control time" readonly>
							<span class="input-group-addon">
								<span class="fa fa-clock-o"></span>
							</span>
						</div>
					</div>
				</div>
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