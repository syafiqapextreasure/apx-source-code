<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.kmlink.ilmu.shared.global.*"%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/datatables/jquery.dataTables.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/bootstrapvalidator-0.5.3/bootstrapValidator.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/bootstrap-multiselect/css/bootstrap-multiselect.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/datepicker/bootstrap-datepicker.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/sweetalert2/css/sweetalert2.min.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-2.1.4.min.js"></script> 
<script type="text/javascript" src="<%= request.getContextPath() %>/themes/default_themes/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugins/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugins/datatables/dataTables.bootstrap.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrapvalidator-0.5.3/bootstrapValidator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-multiselect/js/bootstrap-multiselect.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/sweetalert2/js/sweetalert2.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	


 <script>
 
$( document ).ready(function() {

	//var username = window.frameElement.getAttribute("name");
	var username = "ilmuadmin";
	//var liferayLogin = $("#liferayLogin").val(username);
//	var loggedIn = "ILMUADMIN";
//	var str_array = loggedIn.split('-');
//	var username = "";
/* 
	for(var i = 0; i < str_array.length; i++) {
		 username += str_array[i];
	}
	 */
	$("#liferayLogin").val(username); 
	var programID = $("#programID").val();
	//alert(username);
	 $.get("SignedInUser",{username:username,programID:programID}, function(data){
		 $("#username").val(username);
			 if(data.trim()=="true"){
				 $(".box").show();
			 }else{
				 swal({
					 title: 'Access is denied',
				  text: 'You do not have permission to view this resource using the credentials that you supplied.',
				  showCancelButton: false,
				  showConfirmButton: false,
				  //allowOutsideClick: false
				  allowOutsideClick: false,
				  allowEscapeKey: false
					  
					}).then(
					  function () {},
					  // handling the promise rejection
					  function (dismiss) {
					    if (dismiss === 'timer') {
					      //console.log('I was closed by the timer')
					    }
					  }
					)
			 }
		});
}); 
</script>

<!--   <script>
 
$( document ).ready(function() {
	
//	var username = window.frameElement.getAttribute("name");
	var username='teesting123';
	alert('line 87 in template liferay login username: '+username);
	$("#liferayLogin").val(username);
					  
}); 
</script>
 
  -->
<%

    String module = request.getParameter("MODULE");
    
    String action = request.getParameter("ACTION");
    String content = "/WEB-INF/jsp/module/" + module + "/" + action;

    String leftnav = "/WEB-INF/jsp/include/";
    String header = "";
    String programID= "";
    String version = "";

   
    	    if (module == null || module.equals("")) {

    	        module = "ROOT";

    	    }

    	    

    	    if (action == null || action.equals("")) {

    	        action = "home.jsp";

    	    }
    	   

    	    if (module.equals("ROOT")) {

    	        if (action.equals("home.jsp")){

    	            leftnav += "navigation.jsp";

    	        } else {

    	            leftnav += "navigation.jsp";

    	        }

    	    } else if (module.equals("registration") || module.equals("hintPassword")){

    	        leftnav += "empty.jsp";

    	    }else if (module.equals("Cataloging/03_Accession_Maintenance")){

    	    	header = "Accession Maintenance";
    			 leftnav += "navigation.jsp";

    	    }else {
    	    	if(action.equals("monograph.jsp")){
    				header = "Monograph Binding Maintenance";
    				version = Menu.getVersion("AABM0450", "BI", "MAINT");
    				 leftnav += "navigation.jsp";
    				 programID = "AABM0450";
    			}else if(action.equals("serials.jsp")){
    				header = "Serials Binding Maintenance";
    				version = Menu.getVersion("AABM0150", "BI", "MAINT");
    				 leftnav += "navigation.jsp";
    				 programID = "AABM0150";
    			}else if(action.equals("binderyClaims.jsp")){
    				header = "Bindery Claims";
    				version = Menu.getVersion("AABM0250", "BI", "MAINT");
    				 leftnav += "navigation.jsp";
    				 programID = "AABM0250";
    			}else if(action.equals("bindCheckIn.jsp")){
    				header = "Binding Check-In";
    				version = Menu.getVersion("AABM0350", "BI", "MAINT");
    				 leftnav += "navigation.jsp";
    				 programID = "AABM0350";
    			}else if(action.equals("bindingManagement.jsp")){
    				header = "Binding Management";
    				version = Menu.getVersion("AABM0550", "BI", "MAINT");
    				 leftnav += "navigation.jsp";
    				 programID = "AABM0550";
    			}else if(action.equals("email.jsp")){
    				header = "Mail Browser";
    				 version = Menu.getVersion("AAOE2751", "OP", "MAINT");
    				 leftnav += "navigation.jsp";
    				 programID = "AAOE2751"; 
    	    	}else if(action.equals("overdue.jsp?value=on")){
    				header = "Print Overdue Notification";
    				 version = Menu.getVersion("AARR0151", "CI", "REPORT");
    				 leftnav += "navigation.jsp?value=on";
    				 programID = "AARR0151"; 
    	    	}else if(action.equals("overdue.jsp?value=or")){
    				header = "Print Overdue Reminder";
    				 version = Menu.getVersion("AARR0750", "CI", "REPORT");
    				 leftnav += "navigation.jsp?value=on";
    				 programID = "AARR0750"; 
    	   		}else if(action.equals("reserve.jsp")){
    				header = "Reserve Collection";
    	 			version = Menu.getVersion("AAVM0150", "RC", "MAINT");
    				 leftnav += "navigation.jsp"; 
    				 programID = "AAVM0150"; 
    	    	}else if(action.equals("reserveCollectionManagement.jsp")){
    				header = "Reserve Collection";
    				version = Menu.getVersion("AAVM0250", "RC", "MAINT");
    				 leftnav += "navigation.jsp";
    				 programID = "AAVM0250"; 
    	    	}else if(action.equals("parableTemplateMaintenance.jsp")){
    				header = "Parable Template Maintenance";
    	 			version = Menu.getVersion("AABM0150", "BI", "MAINT");
    				 leftnav += "navigation.jsp";
    				 programID = "AABM0150";
    	    	}else if(action.equals("tagParameter.jsp")){
    				header = "Tag Parameter";
    				 version = Menu.getVersion("AAIFN1900M60", "GL", "MAINT");
    				 leftnav += "navigation.jsp";
    				 programID = "AABM0150"; 
    	     	}else if(action.equals("book_label.jsp")){
    				header = "Book Label";
    				 version = Menu.getVersion("AAIFN1900M60", "GL", "MAINT");
    				 leftnav += "navigation.jsp";
    				 programID = "AABM0150";
    	    	}else if(action.equals("patron.jsp")){
    				header = "Patron Label";
    				 version = Menu.getVersion("AAIFN1900M60", "GL", "MAINT");
    				 leftnav += "navigation.jsp";
    				 programID = "AABM0150"; 
    	     	}else if(action.equals("spine.jsp")){
    				header = "Spine Label";
    				 version = Menu.getVersion("AAIFN1900M60", "GL", "MAINT");
    				 leftnav += "navigation.jsp";
    				 programID = "AABM0150";
    	     	}else if(action.equals("home.jsp")){
    	/* 			header = "Colour Spine Label"; */
    			 	version = Menu.getVersion("AAIPB0400R60", "CT", "REPORT");
    			 	leftnav += "navigation.jsp";
    			 	programID = "AAIPB0400R60";
    	/*      	}else if(action.equals("main.jsp")){
    	 			header = "Colour Spine Label"; 
    			 	version = Menu.getVersion("AAIPB0400M60", "CT", "REPORT");
    			 	leftnav += "navigation.jsp";
    			 	programID = "AAIPB0400M60"; 
    			 	*/
    			}else if (module.equals("Circulation/01_Charging")){
    	   	    	header = "Counter Service";
//    	   	    	version = Menu.getVersion("AAIFN1900M60", "GL", "MAINT");
    	   			 leftnav += "navigation.jsp";
    	   			 programID = "AARM0150";
    	   		}else if(action.equals("membershipRenewal.jsp")){
    				header = "Membership Renewal";
    	 			version = Menu.getVersion("AAGM8850", "GL", "MAINT");
    				 leftnav += "navigation.jsp";
    				 programID = "AAGM8850"; 
    	   	    }else if (module.equals("Circulation/02_Reservation")){
    	   	    	header = "Reservation";
    	   	    	version = Menu.getVersion("AARM0450", "CI", "MAINT");
    	   			 leftnav += "navigation.jsp";
    	   			 programID = "AARM0450";
    	   	    }else if (module.equals("Enquiry/13_LoanHistory")){
    	   	    	 header = "Loan History Enquiry";
    	   	    	 version = Menu.getVersion("AARE0750", "CI", "ENQUIRY");
    	   	 		 leftnav += "navigation.jsp";
    	   	 		 programID = "KLRL0950";
    	   	    }else{
    	        leftnav += "navigation.jsp";
    			}
    	    	
    	    }


%>

<html>

<title>WILMU</title>

<head>

<meta http-equiv="Pragma" content="no-cache">

<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<!-- <script language="JavaScript" src="js/util.js"> -->


<%
		if(!programID.equals("AAIPB0400R60")){

%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/AdminLTE.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/_all-skins.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.css">

<%
		}
%>

</head>



<body leftmargin="0" topmargin="0">
   <div style="height:950px">
         	<section class="content-header">
         	   <input type="hidden" id='liferayLogin'> 
		       <input type="hidden" id="programID" value="<%=programID%>">
		       <%
					if(!programID.equals("AAIPB0400R60")){

		       %>
			       <h3>
			          <i class="glyphicon glyphicon-list"></i>&nbsp;<%=header %><span id="version"></span>
			          <a class="btn btn-primary pull-right">
			         	<span class="glyphicon glyphicon-home"></span>
			         </a>
			           <a class="btn btn-primary pull-right" href="Modal_ListOfPrinter" data-toggle='modal' data-target='#modalPrinter'>
			         	<span class="glyphicon glyphicon-print"></span>
			         </a>
			       </h3>
			   <%
					}
			   %>
		       <%-- 		       <h3>
		          <i class="glyphicon glyphicon-list"></i>&nbsp;<%=header %>
		           <span style='font-size:8pt'>(Ver <%=version %>)</span>
				   <a class="btn btn-primary pull-right" href="http://dev.paradigm.com.my:8080/web/guest/home" target="_top" style="background-color: #1ab394">
		         	<span class="glyphicon glyphicon-home"></span>
		         </a>
		         	           <a class="btn btn-primary pull-right" href="Modal_ListOfPrinter" data-toggle='modal' data-target='#modalPrinter' style="background-color: #1ab394">
		         	<span class="glyphicon glyphicon-print"></span>
		         </a>
		       </h3> --%>
		    </section>
            <section class="content">
  <!--          		<div>
						<select id='templates'>
							<option value='default_themes'>Default Template</option>
							<option value='flatly_themes'>Flatly Template</option>
							<option value='superhero_themes'>Superhero Template</option>
						</select>
					</div> -->
				<div class="box">
           		  <jsp:include page="<%= content %>" />
           		</div>
           </section>
         </div>
</body>



</html>


<div class="modal fade" id="modalPrinter" tabindex="-1" role="dialog" aria-labelledby="searchPatron"  aria-hidden="true" data-backdrop="static"> 
	<div class="modal-dialog" style="width:40%;">
		    <div class="modal-content" id="printerList">
			  <!-- Remote content load here -->
		  </div>
	</div>
</div>
