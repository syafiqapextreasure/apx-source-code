
<%@ page import="com.ilmu.global.*"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/datatables/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/datatables/dataTables.bootstrap.css">

	
<link id="stylesheet" href="<%=request.getContextPath()%>/themes/default_themes/bootstrap-3.1.1/css/bootstrap.min.css" rel="stylesheet">	

<script type="text/javascript" src="<%=request.getContextPath()%>/themes/default_themes/bootstrap-3.1.1/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/plugin/sweetalert2/js/sweetalert2.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/sweetalert2/css/sweetalert2.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/themes/default_themes/navigation/AdminLTE.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/themes/default_themes/navigation/_all-skins.min.css">
<link id="stylesheet" href="<%=request.getContextPath()%>/themes/default_themes/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link id="stylesheet" href="<%=request.getContextPath()%>/themes/default_themes/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/bootstrap-multiselect/js/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/bootstrap-multiselect/css/bootstrap-multiselect.css" type="text/css" />


<%-- 
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/sweetalert2/css/sweetalert2.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/themes/default_themes/navigation/AdminLTE.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/themes/default_themes/navigation/_all-skins.min.css">
<link id="stylesheet" href="<%=request.getContextPath()%>/themes/default_themes/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link id="stylesheet" href="<%=request.getContextPath()%>/themes/default_themes/bootstrap/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/bootstrap-3.3.7/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/bootstrap-multiselect/css/bootstrap-multiselect.css" type="text/css" />



<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/jquery 3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugin/sweetalert2/js/sweetalert2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/bootstrap-multiselect/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>
 --%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/plugin/jquery-2.1.4.min.js"></script> --%>

<style>
#version {
	font-size: 9pt;
}
</style>
<script>
	$(document)
			.ready(
					function() {
//						var username = window.frameElement.getAttribute("name");
						var username = "ilmuadmin";
						//var userLoginName = $("#userLoginName").val(username);
//						var username = "ilmuadmin";
						//alert(username);
/* 						var loggedIn = "ILMUADMIN";
						var str_array = loggedIn.split('-'); */
/* 						var username = "";

						for (var i = 0; i < str_array.length; i++) {
							username += str_array[i];
						} */

						var programID = $("#programID").val();
						//alert(username);
						$
								.get(
										"SignedInUser",
										{
											username : username,
											programID : programID
										},
										function(data) {
											if (data.trim() == "true") {
												$(".box").show();
											} else {
												swal(
														{
															title : 'Access is denied',
															text : 'You do not have permission to view this resource using the credentials that you supplied.',
															showCancelButton : false,
															showConfirmButton : false,
														//allowOutsideClick: false

														}).then(function() {
												},
												// handling the promise rejection
												function(dismiss) {
													if (dismiss === 'timer') {
														//console.log('I was closed by the timer')
													}
												})
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
	String programID = "";
	String content = "/WEB-INF/jsp/module/" + module + "/" + action;
	String leftnav = "/WEB-INF/jsp/include/";
	String header = "";
	String version = "";

	if (module.equals("ROOT")) {

		if (action.equals("home.jsp")) {

			leftnav += "navigation.jsp";

		} else {

			leftnav += "navigation.jsp";

		}

	} else if (module.equals("registration") || module.equals("hintPassword")) {

		leftnav += "empty.jsp";

	} else if (module.equals("OnlineReceipt") ) {

		header = "Online Payment";
		programID = "AAEM0251";

	} else if (module.equals("Receipting")) {

		header = "Receipting Maintenance";
		//version = Menu.getVersion("AAEM0251", "RE", "MAINT");
		programID = "AAEM0251";
		leftnav += "navigation.jsp";

	} else {
		if (action.equals("List_Of_Tpl.jsp")) {
			header = "Template Maintenance";
			leftnav += "navigation.jsp";
		} else if (action.equals("listBibRecord.jsp")) {
			header = "Bibliographic Organisation";
			leftnav += "navigation.jsp";
		} else if (action.equals("MainPage.jsp")) {
			header = "Release For Circulation";
			leftnav += "navigation.jsp";
		} else {
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
		%>
		<%-- 	 <%= request.getParameter("user") %>
			
         	<input type="text" value=" <%= request.getParameter("user") %>" id='userLoginName'> --%>
         	<input type="hidden" id='userLoginName'> 
         	<input type="hidden" id="programID" value="<%=programID%>">
		<h3>
			<i class="glyphicon glyphicon-list"></i>&nbsp;<%=header%><span
				id="version">(2023.2.0.0)</span> <a
				class="btn btn-primary pull-right"> <span
				class="glyphicon glyphicon-home"></span>
			</a> <a class="btn btn-primary pull-right" href="Modal_ListOfPrinter"
				data-toggle='modal' data-target='#modalPrinter'> <span
				class="glyphicon glyphicon-print"></span>
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

			<jsp:include page="<%=content%>" />
		</div>
	</section>

</body>



</html>

<div class="modal fade" id="modalPrinter" tabindex="-1" role="dialog"
	aria-labelledby="searchPatron" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog" style="width: 40%;">
		<div class="modal-content" id="printerList">
			<!-- Remote content load here -->
		</div>
	</div>
</div>
