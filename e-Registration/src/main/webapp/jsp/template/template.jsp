<%@ page contentType="text/html; charset=UTF-8" %>
<%-- <%@ page import="com.ilmu.global.*"%> --%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	 --%>
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

    }else {
    	if(action.equals("daftraAhli.jsp")){
			header = "Daftar Ahli/ User Self-Registration";
			 leftnav += "navigation.jsp";
			 programID = "77";
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
   <div style="height:900px">
         	<section class="content-header">
         	   <%-- <input type="hidden" id='liferayLogin'> 
		       <input type="hidden" id="programID" value="<%=programID%>"> --%>
		        <center><h3><%=header %></h3></center>
		    </section>
            <section class="content">
           		  <jsp:include page="<%= content %>" />
           </section>
         </div>
</body>



</html>
