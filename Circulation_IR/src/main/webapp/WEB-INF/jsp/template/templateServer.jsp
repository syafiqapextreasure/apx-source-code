<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.*"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/AdminLTE.min.css">
 <link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/_all-skins.min.css">
 
 <link rel="stylesheet"
	href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
  <link href="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/plugin/jquery-2.1.4.min.js"></script> --%>

 <script src="${pageContext.request.contextPath}/themes/default_themes/bootstrap/js/bootstrap.min.js"></script>
<!--   <script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script> -->
  <script src="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
  <script src="${pageContext.request.contextPath}/plugin/sweetalert2/js/sweetalert2.min.js" ></script>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/sweetalert2/css/sweetalert2.min.css">

<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/font-awesome.min.css"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/AdminLTE.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/_all-skins.min.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	

<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<!-- <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.26.29/dist/sweetalert2.all.min.js "></script> -->

<script src="${pageContext.request.contextPath}/plugin/sweetalert2/js/sweetalert2.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/sweetalert2/css/sweetalert2.min.css">

<link id="ui-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link id="ui1-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.css" rel="stylesheet"> --%>
<script>
 
 $( document ).ready(function() {
	 
	var username = window.frameElement.getAttribute("name");
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
		if(action.equals("Item_Reassignment.jsp")){
			header = "Item Reassignment";
			 leftnav += "navigation.jsp";
			 programID = "AARM0650";
		}else if(action.equals("Item_Status_Maintenance.jsp")){
			header = "Item Status Maintenance";
			 leftnav += "navigation.jsp";
			 programID = "AARM0550";
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

<!-- <script language="JavaScript" src="js/util.js">

</script> -->


</head>

 <style>
 	body{
 		overflow: visible;
 	}
 	
 	
	#version{
		font-size:9pt;
	}
 </style>



<body leftmargin="0" topmargin="0">
   <div style="height:800px">
         	<section class="content-header">
		        <input type="hidden" id='liferayLogin'> 
		        <input type="hidden" id="programID" value="<%=programID%>">
		       <h3>
		          <i class="glyphicon glyphicon-list"></i>&nbsp;<%=header %>
				  <span id="version">(2019.1.03)</span>
				   <a class="btn btn-primary pull-right" target="_top">
		         	<span class="glyphicon glyphicon-home"></span>
		         </a>
		       </h3>
		       
		    </section>
            <section class="content">
				<div class="box">
           		 <%--  <jsp:include page="<%= content %>" /> style="display:none"--%>
           		 <jsp:include page="<%= content %>"></jsp:include>
           		</div>
           </section>
         </div>
</body>



</html>
