 <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
	
 <%@ page import="com.ilmu.global.*"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/sweetalert2/css/sweetalert2.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/AdminLTE.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/_all-skins.min.css">
<link id="ui-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link id="ui1-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.0/css/bootstrap-datepicker3.css" integrity="sha256-kS81UmreG6WJA7BRAVmBSkuc0YldflRXJw66wiAF9a8=" crossorigin="anonymous" /> -->
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/resources/css/jasny-bootstrap.min.css"> --%>

<!-- 27032021 -->
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/css/bootstrap-datepicker3.css"> --%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/css/font-awesome.min.css"> --%>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/themes/default_themes/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script> -->
<script src="${pageContext.request.contextPath}/plugin/sweetalert2/js/sweetalert2.min.js"></script>
<!-- <script	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.0/js/bootstrap-datepicker.js" integrity="sha256-4/8vOpKmglUDBGTR6LAubR1L6/0f9kvKVfWdNWcxEV8=" crossorigin="anonymous"></script>-->
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/plugin/resources/js/jasny-bootstrap.min.js"></script> --%>

<!-- 27032021 -->
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/plugin/js/jquery.min.js"></script> --%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/plugin/js/bootstrapValidator.min.js"></script> --%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/plugin/js/bootstrap-datepicker.js"></script> --%>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>


 <script>
 
 $( document ).ready(function() {
	 
	var username = window.frameElement.getAttribute("name");
	//alert(username);
	//var username = "SYSADMIN";
	/* var str_array = loggedIn.split('-');
	var username = "";

	for(var i = 0; i < str_array.length; i++) {
		 username += str_array[i];
	}  */

	$("#officer").text(username);
	username = $("#officer").text();
	//username.setAttribute("user", "_self");
	var programID = $("#programID").val();

	//alert(username);
	 $.get("SignedInUser",{username:username,programID:programID}, function(data){
	
		 if(data.trim()=="true"){
				 $(".box").show();
			 }else{
				 swal({
					  title: 'Access is denied',
					  text: 'You do not have permission to view this resource using the credentials that you supplied.',
					  showCancelButton: false,
					  showConfirmButton: false,
					  allowOutsideClick: false,
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
 <style>
 	body{
 		overflow: visible;
 	}
 	
 	
	#version{
		font-size:9pt;
	}
 </style>
<%



    String module = request.getParameter("MODULE");

    if (module == null || module.equals("")) {

        module = "ROOT";

    }

    String action = request.getParameter("ACTION");

    if (action == null || action.equals("")) {

        action = "home.jsp";

    }
System.out.println("action is"+action +" module "+module);
    String content = "/WEB-INF/jsp/module/" + module + "/" + action;
System.out.println(content);
    String leftnav = "/WEB-INF/jsp/include/";
    String header = "";
    String programID= "";

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
		 programID = "AACM0650";

    }else {
    	System.out.println("ACtion" + action);
		if(action.equals("List_Of_Tpl.jsp")){
			System.out.println("Insert" + action);
			header = "Template Maintenance";
			 leftnav += "navigation.jsp";
			 programID = "AACM0450";
		}else if(action.equals("listBibRecord.jsp")){
			header = "Bibliographic Organisation";
			 leftnav += "navigation.jsp";
			 programID = "AACM0550";
		}else if(action.equals("MainPage.jsp")){
			header = "Release For Circulation";
			 leftnav += "navigation.jsp";
			 programID = "AACM0250";
			
		}else{
	        leftnav += "navigation.jsp";
		}

    }

%>
<html>
<title>ILMU</title>

<head>

<meta http-equiv="Pragma" content="no-cache">

<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<!-- <script language="JavaScript" src="js/util.js"> -->

</script>

</head>

<body leftmargin="0" topmargin="0" style="overflow: visible;">
         	<section class="content-header">
         <%
         //String username = Handler.getCurrentUser("sysadmin");
       // String username = "000000000024";	
       // String username = "sysadmin";
         //HttpSession sessions = request.getSession();
         //sessions.setAttribute("username", username);
            System.out.println("sdsd" + request.getAttribute("data"));
         %>

         <%-- 	 <%= request.getParameter("user") %>

         	<input type="text" value=" <%= request.getParameter("user") %>" id='liferayLogin'> --%>
		       <h3>
		          <i class="glyphicon glyphicon-list"></i>&nbsp;<%=header %><span id="version">(2019.2.03)</span><br><br>
		          <label style="font-size:18px">Officer Id : </label><span id="officer" style="font-size:18px"></span>
		          <a class="btn btn-primary pull-right">
		         	<span class="glyphicon glyphicon-home"></span>
		         </a>
		           <a class="btn btn-primary pull-right" href="Modal_ListOfPrinter" data-toggle='modal' data-target='#modalPrinter'>
		         	<span class="glyphicon glyphicon-print"></span>
		         </a>
		       </h3>
				<input type="hidden" id="programID" value="<%=programID%>">
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

</body>


</html>
