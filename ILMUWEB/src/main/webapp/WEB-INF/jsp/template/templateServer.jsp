<%@ page import="com.ilmu.global.*"%>
<%@page import="com.ilmu.foundation.CodeTable.SQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>


<script src="${pageContext.request.contextPath}/plugin/sweetalert2/js/sweetalert2.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/sweetalert2/css/sweetalert2.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/bootstrap-multiselect/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/bootstrap-multiselect/css/bootstrap-multiselect.css" type="text/css"/>
 
 <link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/AdminLTE.min.css">
 <link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/_all-skins.min.css">
<link id="ui-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link id="ui1-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.css" rel="stylesheet">

<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
 
 <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>

 <!-- DataTables -->	
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
 
 <!-- File Input -->
 <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jasny-bootstrap.min.js"></script>	
 <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jasny-bootstrap.min.css">
 
 <!-- datepicker --> 
	 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker3.css">
 	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

 
 	
<script type="text/javascript">

$(document).ready(function(){
	
	var username = window.frameElement.getAttribute("name");
	alert(username);
	/*var loggedIn = "sysadmin";
	var str_array = loggedIn.split('-');
	var username = "";

	for(var i = 0; i < str_array.length; i++) {
		 username += str_array[i];
	}*/
	
	var programID = $("#programID").val();
	alert(programID +"programID");
	 $("#liferayLogin").val(username);
	 

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
	
	$("#templates").change(function() {
	    var theme_value = $(this).val();
	    switch(theme_value){
	        case "default_themes":
	            alert("Setting dark theme");
	            $('#ui-stylesheet').attr("href","themes/default_themes/bootstrap/css/bootstrap.min.css");
	            $('#ui1-stylesheet').attr("href","themes/default_themes/bootstrap/css/bootstrap.css");
	            //$('#icon-stylesheet').attr(href,"css/dark/themes/jquery.mobile.icons.min.css");
	            break;
	        case "flatly_themes":
	            alert("Setting light theme");
	            $('#ui-stylesheet').attr("href","themes/flatly_themes/bootstrap/css/bootstrap.min.css");
	            $('#ui1-stylesheet').attr("href","themes/flatly_themes/bootstrap/css/bootstrap.css");
	            //$('#icon-stylesheet').attr(href,"css/light/themes/jquery.mobile.icons.min.css");   
	            break; 
	        case "superhero_themes":
	            alert("Setting light theme");
	            $('#ui-stylesheet').attr("href","themes/superhero_themes/bootstrap/css/bootstrap.min.css");
	            $('#ui1-stylesheet').attr("href","themes/superhero_themes/bootstrap/css/bootstrap.css");
	            //$('#icon-stylesheet').attr(href,"css/light/themes/jquery.mobile.icons.min.css");   
	            break; 
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
    String version = "";

    /* if (module.equals("ROOT")) {

        if (action.equals("home.jsp")){

            leftnav += "navigation.jsp";

        } else {

            leftnav += "navigation.jsp";

        }

    } else if (module.equals("registration") || module.equals("hintPassword")){

        leftnav += "empty.jsp";

    } else {

        leftnav += "navigation.jsp";

    } */
    

    String[] action1 = action.split("\\?");
    String[] action2 = null;
  
    if(action1.length>1){
    	action2 = action1[1].split("=");
    }
    if (module.equals("ROOT")) {

        if (action.equals("home.jsp")){

            leftnav += "navigation.jsp";

        } else {

            leftnav += "navigation.jsp";

        }

    } else if (module.equals("registration") || module.equals("hintPassword")){

        leftnav += "empty.jsp";

    }/* else if (module.equals("Cataloging/03_Accession_Maintenance")){

    	header = "Accession Maintenance";
		 leftnav += "navigation.jsp";

    } */else {
    	if(action.equals("BranchCode.jsp")){
			header = "Branch Code";
			version = Menu.getVersion("AAGM2950", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM2950";
		}else   if(action1.length>1){
			if(action.equals("CodeTable.jsp?value=" + action2[1])){
				System.out.println("jjjjjjj" + action2[1]);
				SQLStatement eb = new SQLStatement();
				List<Foundation> list = eb.getAllCodeStat(action2[1]);
				for (Foundation a : list) {
					System.out.println("fndname " + a.getFNDNAME());
					header = a.getFNDNAME();
					version = Menu.getVersion("AAGM4550", "GL", "MAINT");
					leftnav += "navigation.jsp";
					programID = "AAGM4550";
				}
			}
		}else if(action.equals("CourseTable.jsp")){
			header = "Course Code";
			version = Menu.getVersion("AAIFN1500M60", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAIFN1500M60";
		}else if(action.equals("CurrencyTable.jsp")){
			header = "Currency Code";
			version = Menu.getVersion("AAGM2550", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM2550";
		}else if(action.equals("DeptTable.jsp")){
			header = "Department Code";
			version = Menu.getVersion("AAIFN1400M60", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAIFN1400M60";
		}else if(action.equals("LocaTable.jsp")){
			header = "Location Code";
			version = Menu.getVersion("AAGM0950", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM0950";
		}else if(action.equals("VendorTable.jsp")){
			header = "Vendor Code";
			version = Menu.getVersion("AAGM3750", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM3750";
		}else if(action.equals("SubjTable.jsp")){
			header = "General Subject";
			version = Menu.getVersion("AAGM5250", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM5250";
		}else if(action.equals("PatronTable.jsp")){
			header = "Patron Details";
			version = Menu.getVersion("AAIFN1800M60", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAIFN1800M60";
		}else if(action.equals("LibraryCalendar.jsp")){
			header = "Library Calendar";
			version = Menu.getVersion("AAGM3250", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM3250";
		}else if(action.equals("ItemCatTable.jsp")){
			header = "Item Category";
			version = Menu.getVersion("AAGM2950", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM2950";
		}else if(action.equals("PatCategory.jsp")){
			header = "Patron Category";
			version = Menu.getVersion("AAGM1150", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM1150";
		}else if(action.equals("SMDTable.jsp")){
			header = "Specific Material Designation (SMD)";
			version = Menu.getVersion("AAGM4750", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM4750";
		}else if(action.equals("StatusTable.jsp")){
			header = "Patron Status";
			 leftnav += "navigation.jsp";
			 version = Menu.getVersion("AAGM1250", "GL", "MAINT");
			 programID = "AAGM1250";
		}else if(action.equals("SerialTable.jsp")){
			header = "Serial Frequency";
			version = Menu.getVersion("AAGM4650", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM4650";
		}else if(action.equals("MainPage.jsp")){
			header = "Patron Eligibility";
			version = Menu.getVersion("AAIFN3000M60", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAIFN3000M60";
		}else if(action.equals("DocumentStatusTable.jsp")){
			header = "Document Status";
			version = Menu.getVersion("AAGM5050", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM5050";
		}else if(action.equals("PatrMiscellaneous.jsp")){
			header = "Patron Miscellaneous Attributes";
			version = Menu.getVersion("AAGM8450", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM8450";
		}else if(action.equals("ModuleAccessLevel.jsp")){
			header = "Module Access Level";
			version = Menu.getVersion("AAGM7650", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM7650";
		}else if(action.equals("groupID.jsp")){
			header = "Group ID";
			version = Menu.getVersion("AAGM0450", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM0450";
		}else if(action.equals("VendorFeedback.jsp")){
			header = "Vendor Feedback ID";
			version = Menu.getVersion("AAGM3950", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM3950";
		}else if(action.equals("flag.jsp")){
			header = "Flag";
			version = Menu.getVersion("AAGM7750", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM7750";
		}else if(action.equals("Menu.jsp")){
			header = "Menu";
			version = Menu.getVersion("AAIFN0800M60", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAIFN0800M60";
		}else if(action.equals("main.jsp")){
			header = "Library Information";
			version = Menu.getVersion("AAGM2850", "GL", "MAINT");
			 leftnav += "navigation.jsp";
			 programID = "AAGM2850";
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

         	<%-- <input type="hidden" value=" <%= request.getParameter("user") %>" id='liferayLogin'> --%>
         	<input type="hidden" id='liferayLogin' name="liferayLogin">
         	<input type="hidden" id="programID" value="<%=programID%>">
         	
		       <h3>
		          <i class="glyphicon glyphicon-list"></i>&nbsp;<%=header %>
				  <span id="version">(2019.1.07)</span>
		          <!-- <span style='font-size:8pt'>(Ver <%=version %>)</span> -->
		          <a class="btn btn-primary pull-right">
		         	<span class="glyphicon glyphicon-home"></span>
		         </a>
		       </h3>
			
		</section>
		
		<section class="content">
			<div class="box"> 
				<jsp:include page="<%= content %>" />
			</div>
		</section>
	</div>
 
         	<%-- <section class="content-header">   style="display:none"
         <%
         String username = Handler.getCurrentUser("ilmuadmin");
       // String username = "000000000024";	
       // String username = "sysadmin";
         HttpSession sessions = request.getSession();
         sessions.setAttribute("username", username);
            
         %>

         	 <%= request.getParameter("user") %>

         	<input type="text" value=" <%= request.getParameter("user") %>" id='liferayLogin'>
		       <h3>
		          <i class="glyphicon glyphicon-list"></i>&nbsp;<%=header %>
		          <a class="btn btn-primary pull-right">
		         	<span class="glyphicon glyphicon-home"></span>
		         </a>
		       </h3>

		    </section>
            <section class="content">

				<div class="box">

           		  <jsp:include page="<%= content %>" />
           		</div>
           </section> --%>
   
</body>



</html>


