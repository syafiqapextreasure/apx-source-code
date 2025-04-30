<%-- <%@page import="com.ilmu.cataloging.template.*"%> --%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.ilmu.circulation.Item_Status_Maintenance.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Selection List</title>
<%-- <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css"> --%>
<%--  <script src="${pageContext.request.contextPath}/plugin/sweetalert2/js/sweetalert2.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/sweetalert2/css/sweetalert2.min.css"> --%>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.16/css/jquery.dataTables.min.css" />
<%--  <script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Iteam_Status_Maintenance.js"></script> --%>
	<!-- <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> -->
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Release_For_CI.js"></script> --%>					

<script>
	
	//Call error message page
	function messageBox2(code, criteria, label){
	      var url = "Error_Page?GL79ERRCODE="+code+"" +
	      "&criteria=" + criteria + "&label="+label+"";
	      //alert(url);
	      $.ajax({
	           	url: url,
	            success: function(result) {
	            	setTimeout(function () {
	            	swal({   
	            		text: result,
	            		/* type: "warning",   
	            		showCancelButton: true,
	            		cancelButtonText: "Cancel",
	            		//timer: 3000
	            		confirmButtonText: "OK" */
	            		showCancelButton: true,
	            		confirmButtonColor: '#3085d6', 
	            		cancelButtonColor: '#d33',
	            		confirmButtonText: 'Yes',
	            		cancelButtonText: 'No',
	            		confirmButtonClass: 'confirm-class',
	            		cancelButtonClass: 'cancel-class',
	            		closeOnConfirm: false,
	            		closeOnCancel: false 
	            		
	            		}).then(function(isConfirm) {
	            			 if (isConfirm) {
	            				 var patrid = $("#patrid").val();
	            				 var pop;
	            				 var protocol =  window.location.protocol;
	            				 var host = protocol + "//" + window.location.hostname + ":" + location.port +  "/web/guest/maintenance1";
	            				 pop = window.open(host+ '?patrid='+ patrid, '_blank');
	            				    //window.location.href = "//google.com";
	            			 }
							
	            			/* var accno = $("#accno").val();
	            			 
	            			 $.get('getUpdateStatusRecall', {
	            				 accno : $("#accno").val()
	            			 	}, function(responseJson) {
	            				if(responseJson != null){
	            					$.each(responseJson, function(key,value) {
	            						$(".Status").text(value['DESC36']);
	            					});
	            				}
	            			}); */
	            	});}, 1000);
	            	 /* $.get('getUpdateStatusRecall', {
        				 accno : $("#accno").val()
        			 	}, function(responseJson) {
        				if(responseJson != null){
        					$.each(responseJson, function(key,value) {
        						$(".Status").text(value['DESC36']);
        					});
        				}
        			}); */
	                  //swal('Info!',result, 'warning' );
	                  //window.location.href = "//stackoverflow.com";
	            	//});
	            },
	            
	      });
	}

	////////////////////////////////////////ONCHANGE New Item Status //////////////////////////////////////
	$('#new').on('change', function() {
		//alert("masuk");
	    $(this).find(":selected").val();
	    //alert($(this).find(":selected").val() == 'L');
	    //alert($(this).find("option:selected").text());
	    if($(this).find(":selected").val() != ""){
	    	//alert("notnull");
	    	$("#btnOK").attr("disabled",false);
	    }else{
	    	//alert("null");
	    	$("#btnOK").attr("disabled","disabled");
	    }
	});
	
	///////////////////////click btnOK
	$("#btnOK").click(function(){		
		if($("#new").val() != 'L'){
			//swal($("#accno").val());
			$.post("modifynotlost", {
				accno: $("#accno").val(),
				newStat : $("#new").val(),
				loginid : $("#liferayLogin").val(),
			},function(data){
				messageBox("005","Update","@action");
				//swal("Successfully ");
				var accno = $("#accno").val();
   			 	//alert(accno)
	   			 $.get('getUpdateStatusRecall', {
	   				 accno : $("#accno").val()
	   			 	}, function(responseJson) {
	   				if(responseJson != null){
	   					$.each(responseJson, function(key,value) {
	   						$(".Status").text(value['DESC36']);
	   					});
	   				}
	   			 });
	   			 
				//$(".Status").text($("#new option:selected").text());
			}
			).fail(function(data){
				swal("error");
			}).success(function(data){
				/* swal("Successfully Update");
				$(".Status").text($("#new option:selected").text()); */
			});
		}else{ 
			$.get('getLcost', {
  				 accno : $("#accno").val()
  			 	}, function(responseJson) {
  				if(responseJson != null){
  					$.each(responseJson, function(key,value) {
  						if(value['DESC36'] == "0.0000"){
  							
  							if(($("#recepting").val() == null) || ($("#recepting").val() == " ") || ($("#recepting").val() == "")){

  				  			 }else{
  				  				messageBox("113","","");
  				  			 }
  							
  						}
  					});
  				}
  			});
			
			$.post("modifyItemStatus", {
				patrid : $("#patrid").val(),
				accno: $("#accno").val(),
				newStat : $("#new").val(),
				id : $("#liferayLogin").val()
			},function(data){
				var accno = $("#accno").val();
	  			 //alert("ll");
	  			 $.get('getUpdateStatusRecall', {
	  				 accno : $("#accno").val()
	  			 	}, function(responseJson) {
	  				if(responseJson != null){
	  					$.each(responseJson, function(key,value) {
	  						////alert(value['DESC36']);
	  						$(".Status").text(value['DESC36']);
	  					});
	  				}
	  			 });
	  			 
	  			 //alert("000"+$("#recepting").val()+"llll")
	  			 if(($("#recepting").val() == null) || ($("#recepting").val() == " ") || ($("#recepting").val() == "")){

	  			 }else{
	  				//alert("here");
	  				messageBox2("094","","");
	  			 }
				 ///messageBox2("094","","");
			}
			).fail(function(data){
				swal("error");
			}).success(function(data){   
				//messageBox("094","","");
			}); 
			
			
		}
		
		
		//alert($("#new option:selected").text());
		/* alert($("#new").val());
		alert($("#patrid").val());
		if($("#new").val() == 'L'){
			messageBox("094","","");
		}else{
		swal("Successfully Update");
		$(".Status").text($("#new option:selected").text());
		} */
		/* $.post("modifyItemStatus", {
			patrid : $("#patrid").val(),
			accno: $("#accno").val(),
			newStat : $("#new").val() 
		},function(data,status){
			if($("#new").val() == 'L'){
				messageBox("094","","");
			}else{
			swal("Successfully Update");
			$(".Status").text($("#new option:selected").text());
			}
		}); */
	});
	
	//////////////// select value emapty /////////////////////
	//alert( $('#new > option').length < 1);
	if($('#new > option').length == 1){
		//alert($("#status").val());
		var Status =$("#status").val();
		swal("Item is on " +Status+ " unable to modify");
		//messageBox("099","","");
		setTimeout(function(){
			//swal("Item unable to modify");
			$("#cancel").click();
		}, 3000); 
	}
	

</script>
</head>
<body>
      
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h4 class="modal-title">Modify Item Status</h4>
	</div>
	
	<div class="container"></div>
        <div class="modal-body" id="accSlctList" >
			<form role="form" class="form-horizontal">  <!--onsubmit="return send_vendor_info()" -->
			
				<div class="clearfix"></div>
				<div class="form-group" id="left">
					<div  class="col-lg-12" style="height:50%;">
				<%
     				String AccessionNO = request.getParameter("AccessionNO");
					List<SQLStatusMaintenance> listForModify = SQLStatusMaintenance.getMODIFY(AccessionNO); 
					for (SQLStatusMaintenance i: listForModify) {
						System.out.println(listForModify);
     			%>
     
      					<input type="hidden" id="AccessionNO" value="<%=AccessionNO%>">
      					<input type="hidden" id="patrid" value="<%=i.getPATR()%>">
      
						<div class="form-group">
					      <label class="control-label col-sm-3" for="accno">Accession No :</label>
					      <div class="col-sm-4">
					        <input type="text" class="form-control" id="accno" name="accno" value="<%=AccessionNO%>" readonly>
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="control-label col-sm-3" for="title">Title:</label>
					      <div class="col-sm-9"> 
					      	<textarea class="form-control" rows="4" id="title" readonly><%=i.getTITLE()%></textarea>         
					        <%-- <input type="text" class="form-control" id="title" name="title" value="<%=i.getTITLE()%>" readonly> --%>
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="control-label col-sm-3" for="status">Current Item Status:</label>
					      <div class="col-sm-5">          
					        <input type="text" class="form-control" id="status" name="status" value="<%=i.getDESC36()%>" readonly>
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="control-label col-sm-3" for="new">New Item Status:</label>
					      <div class="col-sm-5">  
					      	<select class="form-control" id="new">   
					      	<option value=""></option>     
					      	<% 
					      		String getNewVal = i.getCHGTO();
					      		String[] getNewValLen = getNewVal.split("");
					      		System.out.println("getNewVal "+getNewVal);
					      		System.out.print(getNewValLen.length + " lllllll");
					      		for (String idStat : getNewValLen) {
					      	      System.out.println(idStat+"istat");
					      	      List<SQLStatusMaintenance> getStatDESC3 = SQLStatusMaintenance.getStatDESC(idStat);
					      	    	for (SQLStatusMaintenance a: getStatDESC3) {
					      	   			System.out.println(a.getDESC36() + "val");  
					      	   		/* if(a.getGL36DESC().isEmpty()){
					      	   			System.out.pri("notValue");
					      	   		} */
					      	%>
					        
					        	<option value="<%=idStat%>"><%=a.getDESC36()%></option>
					        <%
					      	    		}
					      			} 
								}
					        %>
						  	</select>
					      </div>
					    </div>
					</div>
				</div>
			</form>
	</div>
	
	<div class="modal-footer">
        <button type="button" class="btn btn-info" id="btnOK" data-dismiss="modal">OK</button>
		<input type="button" id="cancel" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal">
	</div>
						
</body>
</html>