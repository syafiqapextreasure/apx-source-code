<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.*"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/sweetalert2/css/sweetalert2.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/AdminLTE.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/_all-skins.min.css">
<link id="ui-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link id="ui1-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="<%= request.getContextPath() %>/themes/default_themes/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="${pageContext.request.contextPath}/plugin/sweetalert2/js/sweetalert2.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.0/css/bootstrap-datepicker3.css" integrity="sha256-kS81UmreG6WJA7BRAVmBSkuc0YldflRXJw66wiAF9a8=" crossorigin="anonymous" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.0/js/bootstrap-datepicker.js" integrity="sha256-4/8vOpKmglUDBGTR6LAubR1L6/0f9kvKVfWdNWcxEV8=" crossorigin="anonymous"></script>
<script>
$( document ).ready(function() {
	 
	var username = window.frameElement.getAttribute("name");
	//alert(username);
	//var username = "Sysadmin";//////
	////////alert(loggedIn);
	///var str_array = loggedIn.split('-');/////
	///var username = ""; 

	/* for(var i = 0; i < str_array.length; i++) {
		loggedIn += str_array[i];
	} */
	
	var programID = $("#programID").val();
	 $("#liferayLogin").val(username);
	//alert(username);
	 $.get("SignedInUser",{username:username,programID:programID}, function(data){
			//alert(data);
			 if(data.trim()=="true"){
				 $(".box").show();
			 }else{
				 swal({
					  title: 'Access is denied',
					  text: 'You do not have permission to view this resource using the credentials that you supplied.',
					  showCancelButton: false,
					  showConfirmButton: false,
					  allowOutsideClick: false
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
			 
			 /* var splitString = data.split(" ");
			 var word2 = splitString[0];
			 $("#liferayLogin").val(word2); */
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
    String version = "";

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
   <div style="height:950px">
         	<section class="content-header">
         	   <input type="hidden" id='liferayLogin'> 
		       <input type="hidden" id="programID" value="<%=programID%>">
		       <h3>
		          <i class="glyphicon glyphicon-list"></i>&nbsp;<%=header %>
		          <%-- <span style='font-size:8pt'>(Ver <%=version %>)</span> --%>
				   <a class="btn btn-primary pull-right" href="http://dev.paradigm.com.my:8080/web/guest/home" target="_top">
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
         </div>
</body>



</html>
