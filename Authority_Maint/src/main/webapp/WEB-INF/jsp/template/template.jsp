 <%@ page
	import="com.ilmu.global.*"%>
 
 <link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/AdminLTE.min.css">
 <link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/_all-skins.min.css">
 
 <link rel="stylesheet"
 href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
 <link href="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="${pageContext.request.contextPath}/themes/default_themes/bootstrap/js/bootstrap.min.js"></script>
 <script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
 <script src="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
 <script
src="${pageContext.request.contextPath}/plugin/sweetalert2/js/sweetalert2.min.js" ></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/sweetalert2/css/sweetalert2.min.css"
rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/font-awesome.min.css"/>
<link rel="stylesheet"
href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.css" rel="stylesheet">
<style>
	#version{
		font-size:9pt;
	}
</style>
 <script>
 
 $( document ).ready(function() {
	 
	var username = window.frameElement.getAttribute("name");
	//alert(username);
// 	var loggedIn = "Sysadmin";
// 	var str_array = loggedIn.split('-');
// 	var username = "";

// 	for(var i = 0; i < str_array.length; i++) {
// 		 username += str_array[i];
// 	}
	
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
					  showConfirmButton: false
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
<%

    String module = request.getParameter("MODULE");

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
    String programID= "";
    if (module.equals("ROOT")) {

        if (action.equals("home.jsp")){

            leftnav += "navigation.jsp";

        } else {

            leftnav += "navigation.jsp";

        }

    } else if (module.equals("registration") || module.equals("hintPassword")){

        leftnav += "empty.jsp";

    }else if (module.equals("Circulation/01_Charging")){

    	header = "Counter Services";
		 leftnav += "navigation.jsp";
		 programID = "AACM0650";

    }else if (module.equals("Authority/02_GlobalChange")){

    	header = "Global Change";
		 leftnav += "navigation.jsp";

    }else if (module.equals("Authority/03_GlobalMerge")){

    	header = "Global Merge";
		 leftnav += "navigation.jsp";

    }else if (module.equals("Circulation/04_Weeding")){

    	header = "Weeding";
		 leftnav += "navigation.jsp";

    }

%>

<html>

<title>ILMU</title>

<head>

<meta http-equiv="Pragma" content="no-cache">

<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<script language="JavaScript" src="js/util.js">

</script>

</head>



<body leftmargin="0" topmargin="0">
 
         	<section class="content-header">
         <%
        /*  String username = Handler.getCurrentUser("sysadmin");
       // String username = "000000000024";	
       // String username = "sysadmin";
         HttpSession sessions = request.getSession();
         sessions.setAttribute("username", username); */
            
         %>

         <%-- 	 <%= request.getParameter("user") %>

         	<input type="text" value=" <%= request.getParameter("user") %>" id='liferayLogin'> --%>
		       <h3>
		          <i class="glyphicon glyphicon-list"></i>&nbsp;<%=header %>
		          <a class="btn btn-primary pull-right">
		         	<span class="glyphicon glyphicon-home"></span>
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
					<div class="box" >
					
           		  <jsp:include page="<%= content %>" />
           		</div>
           </section>

</body>



</html>
