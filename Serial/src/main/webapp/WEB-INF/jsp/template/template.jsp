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
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Weeding.js"></script>	

	

<script>
 
  $( document ).ready(function() {
	 
	//var username = window.frameElement.getAttribute("name");
	//alert(username);
	var loggedIn = "SYSADMIN";
	var str_array = loggedIn.split('-');
	var username = "";
	$("#liferayLogin").val(loggedIn);

	for(var i = 0; i < str_array.length; i++) {
		 username += str_array[i];
	}
	
	var programID = $("#programID").val();
	//alert(username);
	 $.get("SignedInUser",{username:username,programID:"AACM0550"}, function(data){
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
System.out.println(content);
    String leftnav = "/WEB-INF/jsp/include/";
    String header = "";

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
		if(action.equals("List_Of_Tpl.jsp")){
			header = "Template Maintenance";
			 leftnav += "navigation.jsp";
		}else if(action.equals("listBibRecord.jsp")){
			header = "Bibliographic Organisation";
			 leftnav += "navigation.jsp";
		}else if(action.equals("MainPage.jsp")){
			header = "Release For Circulation";
			 leftnav += "navigation.jsp";
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

<script language="JavaScript" src="js/util.js">

</script>

</head>



<body leftmargin="0" topmargin="0">

         	<section class="content-header">
         	<input type="hidden" id='liferayLogin'> 
         	<%
         	  //String username = Handler.getCurrentUser("ilmuadmin");
            // String username = "000000000024";	
              //String username = "test";
              /* HttpSession sessions = request.getSession();
              sessions.setAttribute("username", username); */
         	%>
		       <h3>
		          <i class="glyphicon glyphicon-list"></i>&nbsp;<%=header %>
		          <a class="btn btn-primary pull-right">
		         	<span class="glyphicon glyphicon-home"></span>
		         </a>
		       </h3>

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