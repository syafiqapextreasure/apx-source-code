<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/sweetalert2/css/sweetalert2.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/AdminLTE.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/_all-skins.min.css">
<link id="ui-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link id="ui1-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jasny-bootstrap.min.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/jquery-2.1.4.min.js"></script> 
<script type="text/javascript" src="<%= request.getContextPath() %>/themes/default_themes/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugin/sweetalert2/js/sweetalert2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jasny-bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<head>

<meta http-equiv="Pragma" content="no-cache">

<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<!-- <script language="JavaScript" src="js/util.js"> -->

<!-- </script> -->

</head>
<style>
	#version{
		font-size:9pt;
	}
</style>
<body leftmargin="0" topmargin="0" style="overflow: visible;">
<%

    String module = request.getParameter("MODULE");
	System.out.println(module + " = module");
    if (module == null || module.equals("")) {

        module = "ROOT";

    }

    String action = request.getParameter("ACTION");

    if (action == null || action.equals("")) {

        action = "home.jsp";

    }

    String content = "/WEB-INF/jsp/module/" + module + "/" + action;

    String leftnav = "/WEB-INF/jsp/include/";
    String header = "";
    String programID = "";
    if (module.equals("ROOT")) {

        if (action.equals("home.jsp")){

            leftnav += "navigation.jsp";

        } else {

            leftnav += "navigation.jsp";

        }

    } else if (module.equals("registration") || module.equals("hintPassword")){

        leftnav += "empty.jsp";

    }else if (module.equals("/Circulation/01_Charging")){
    	header = "Counter Services";
		 leftnav += "navigation.jsp";
		 programID = "AARM0150";

    }else if (module.equals("Circulation/02_Reservation")){

    	header = "Reservation";
		 leftnav += "navigation.jsp";
		 programID = "AARM0450";

    }else if (module.equals("Circulation/03_Reservation_Scrutiny")){

    	header = "Reservation Scrutiny";
		 leftnav += "navigation.jsp";
		 programID = "AARM1350";

    }else if (module.equals("Circulation/04_Weeding")){

    	header = "Weeding";
		 leftnav += "navigation.jsp";
		 programID = "AAWM0150";

    }
%>

<div class = "box2">
         	<section class="content-header">
         <%
         //String username = Handler.getCurrentUser("sysadmin");
       // String username = "000000000024";	
       // String username = "sysadmin";
         //HttpSession sessions = request.getSession();
         //sessions.setAttribute("username", username);
         %>
         <%-- 	 <%= request.getParameter("user") %>

         	<input type="text" value=" <%= request.getParameter("user") %>" id='liferayLogin'> --%>
		       <h3>
		          <i class="glyphicon glyphicon-list"></i>&nbsp;<%=header %><span id="version">(2023.2.1.18)</span>
		          <a class="btn btn-primary pull-right">
		         	<span class="glyphicon glyphicon-home"></span>
		         </a>
		           <a class="btn btn-primary pull-right" href="Modal_ListOfPrinter" data-toggle='modal' data-target='#modalPrinter'>
		         	<span class="glyphicon glyphicon-print"></span>
		         </a>
		       </h3>
		        <input type="hidden" id='liferayLogin' name="liferayLogin">
				<input type="hidden" id="programID" value="<%= programID %>">
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
	
	<script>
/* $( document ).ready(function() {
	$("#modalPrinter").modal("show");
	var url = "Modal_ListOfPrinter";
	$.get(url,function(data){
		$("#printerList").html(data);
	})
}); */
</script>
 <script>
 
$( document ).ready(function() {
	 

	var username = window.frameElement.getAttribute("name");
	//var username = "ilmuadmin";
/* 	var username ="sysadmin"; */
	//alert(username);
	//var loggedIn = "ILMUADMIN";
	/* var str_array = loggedIn.split('-');
	var username = "";

	for(var i = 0; i < str_array.length; i++) {
		 username += str_array[i];
	}
	 */
	 var currentURL = window.location.href;
	 var queryString = new URL(currentURL).search;
	 var searchParams = new URLSearchParams(queryString);
	 var moduleValue = searchParams.get("MODULE");

	 if (moduleValue === null || moduleValue.trim() === "") {
	   // The MODULE parameter is empty, proceed with your action here
	   console.log("MODULE is empty, proceed with the page.");
	 } else {
	   // The MODULE parameter has a value, handle it accordingly
	   //console.log("MODULE value:", moduleValue);
	 }


	 
	var programID = $("#programID").val();
	 $("#liferayLogin").val(username); 
//	$("#officer").text(username);
//	username = $("#officer").text();
	 $.get("SignedInUser",{username:username,programID:programID}, function(data){
			 if(data.trim()=="true"){
				 $(".box").show();
			 }else{
// 				 $(".box").remove();
// 				 $(".box2").remove();
// 				  swal({
// 					  title: 'Access is denied',
// 					  text: 'You do not have permission to view this resource using the credentials that you supplied.',
// 					  showCancelButton: false,
// 					  showConfirmButton: false,
// 						//allowOutsideClick: false
// 					}).then(
// 					  function () {},
// 					  // handling the promise rejection
// 					  function (dismiss) {
// 					    if (dismiss === 'timer') {
// 					      //console.log('I was closed by the timer')
// 					    }
// 					  }
// 					) 
			 }
		});
}); 
 </script>

</body>
</html>
