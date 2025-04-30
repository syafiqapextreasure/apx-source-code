<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.wilmu.global.menu.GetGlmenu"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/general/global.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugin/sweetalert2/css/sweetalert2.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/themes/default_themes/navigation/AdminLTE.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/themes/default_themes/navigation/_all-skins.min.css">
<link id="ui-stylesheet"
	href="<%=request.getContextPath()%>/themes/default_themes/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link id="ui1-stylesheet"
	href="<%=request.getContextPath()%>/themes/default_themes/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/datatables/dataTables.bootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/resources/css/jasny-bootstrap.min.css">

<!-- 27032021 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/css/bootstrap-datepicker3.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/css/listree.min.css" />
<script src="<%=request.getContextPath()%>/plugin/js/listree.umd.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="${pageContext.request.contextPath}/plugin/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="<%=request.getContextPath()%>/plugin/js/tableHTMLExport.js"></script>
<script type="text/javascript" charset="utf8"
	src="<%=request.getContextPath()%>/themes/default_themes/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="<%=request.getContextPath()%>/plugin/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="<%=request.getContextPath()%>/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/plugin/sweetalert2/js/sweetalert2.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="${pageContext.request.contextPath}/plugin/resources/js/jasny-bootstrap.min.js"></script>

<!-- 27032021 -->
<script type="text/javascript" charset="utf8"
	src="${pageContext.request.contextPath}/plugin/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="${pageContext.request.contextPath}/plugin/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" charset="utf8"
	src="<%=request.getContextPath()%>/plugin/bootstrap-multiselect/js/bootstrap-multiselect.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/bootstrap-multiselect/css/bootstrap-multiselect.css" />
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/rowgroup/1.3.0/js/dataTables.rowGroup.min.js"></script>

<!-- 22072021 -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugin/moment.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/plug-ins/1.10.24/api/sum().js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/buttons/2.0.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.flash.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.print.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https:////cdn.datatables.net/plug-ins/1.10.25/sorting/date-uk.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/css/fieldset.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.rawgit.com/ashl1/datatables-rowsgroup/fbd569b8768155c7a9a62568e66a64115887d7d0/dataTables.rowsGroup.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/select/1.3.3/js/dataTables.select.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.4.1/jspdf.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/2.3.5/jspdf.plugin.autotable.min.js"></script>
<script>
    $(document)
	    .ready(
		    function() {
			var username = window.frameElement.getAttribute("name");
			$("#liferayLogin").val(username);
			var programID = $("#programID").val();
			// Make a GET request using $.get
			$.get("SignedInUser", {
				    username : username,
				    programID : programID
				}).then(
					function(data) {
					    // Process the response data
					    if (data.trim() == "true") {
						$(".box").show();
					    } else {
						$(".box").remove();
						swal(
							{
							    title : 'Access is denied',
							    text : 'You do not have permission to view this resource using the credentials that you supplied.',
							    showCancelButton : false,
							    showConfirmButton : false,
							    allowOutsideClick : false,
							    allowEscapeKey : false
							}).then(function() {
						},
						// handling the promise rejection
						function(dismiss) {
						    if (dismiss === 'timer') {
						    }
						})
					    }
					}).fail(
					function(error) {
					    // Handle errors, if any
					    $(".box").remove();
					    console.error(
						    "GET request failed:",
						    error);
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
String programID = "";
String version = "";
String menuAndVersion = "";
String[] action1 = action.split("\\?");
String[] action2 = null;

if (action1.length > 1) {
	action2 = action1[1].split("=");
}
if (module.equals("ROOT")) {
	if (action.equals("home.jsp")) {
		leftnav += "navigation.jsp";
	} else {
		leftnav += "navigation.jsp";
	}
} else if (module.equals("registration") || module.equals("hintPassword")) {
	leftnav += "empty.jsp";
} else if (module.equals("Acquisition/Report/AAAR0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAR0250", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAR0250";
} else if (module.equals("Acquisition/Enquiry/AAAE0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAE0250", "AC", "ENQUIRY");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAE0250";
} else if (module.equals("Acquisition/Enquiry/AAAE0350")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAE0350", "AC", "ENQUIRY");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAE0350";
} else if (module.equals("Report/AAAR0750")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAR0750", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAR0750";
} else if (module.equals("Acquisition/Report/AAAR0450")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAR0450", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAR0450";
} else if (module.equals("Acquisition/Listing/AAAL1050")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAL1050", "AC", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAL1050";
} else if (module.equals("Acquisition/Report/AAAR0550")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAR0550", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAR0550";
} else if (module.equals("Acquisition/Report/AAAR0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAR0150", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAR0150";
} else if (module.equals("Acquisition/Listing/AAAL0850")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAL0850", "AC", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAL0850";
} else if (module.equals("Acquisition/Report/AAAR0350")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAR0350", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAR0350";
} else if (module.equals("Acquisition/Listing/AAAL0450")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAL0450", "AC", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAL0450";
} else if (module.equals("Acquisition/Listing/AAAL0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAL0250", "AC", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAL0250";
} else if (module.equals("Acquisition/Report/KLAS0550")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLAS0550", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLAS0550";
} else if (module.equals("Acquisition/Report/AAAR0650")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAR0650", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAR0650";
} else if (module.equals("Acquisition/Listing/AAAL0550")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAL0550", "AC", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAL0550";
} else if (module.equals("Acquisition/Listing/AAAL0750")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAL0750", "AC", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAL0750";
} else if (module.equals("Acquisition/Listing/AAAL0350")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAL0350", "AC", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAL0350";
} else if (module.equals("Acquisition/Listing/AAAL1150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAL1150", "AC", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAL1150";
} else if (module.equals("Acquisition/Report/KLAS0650")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLAS0650", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLAS0650";
} else if (module.equals("Acquisition/Report/KLAS0550")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLAS0550", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLAS0550";
} else if (module.equals("Acquisition/Listing/AAAL0650")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAL0650", "AC", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAL0650";
} else if (module.equals("Acquisition/Report/KLAS0550")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLAS0550", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLAS0550";
} else if (module.equals("Acquisition/Report/KLAS0750")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLAS0750", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLAS0750";
} else if (module.equals("Serials/Report/AASR0450")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASR0450", "SE", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASR0450";
} else if (module.equals("Serials/Listing/AASL0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASL0150", "SE", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASL0150";
} else if (module.equals("Serials/Listing/AASL0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASL0250", "SE", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASL0250";
} else if (module.equals("Serials/Listing/AASL0350")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASL0350", "SE", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASL0350";
} else if (module.equals("Serials/Listing/AASL0450")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASL0450", "SE", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASL0450";
} else if (module.equals("Serials/Listing/AASL0550")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASL0550", "SE", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASL0550";
} else if (module.equals("Serials/Listing/AASL0650")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASL0650", "SE", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASL0650";
} else if (module.equals("Serials/Listing/AASL0750")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASL0750", "SE", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASL0750";
} else if (module.equals("Serials/Listing/AASL0950")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASL0950", "SE", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASL0950";
} else if (module.equals("Serials/Report/KLSS0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLSS0150", "SE", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLSS0150";
} else if (module.equals("Serials/Report/AASR0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASR0250", "SE", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASR0250";
} else if (module.equals("Serials/Report/AASR0350")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASR0350", "SE", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASR0350";
} else if (module.equals("Serials/Report/AASR0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASR0150", "SE", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASR0150";
} else if (module.equals("Serials/Report/AASR0550")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASR0550", "SE", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASR0550";
} else if (module.equals("Serials/Report/AASR0650")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASR0650", "SE", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASR0650";
} else if (module.equals("Serials/Report/AASR0850")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AASR0850", "SE", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AASR0850";
} else if (module.equals("Foundation/Listing/AAGL1150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL1150", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL1150";
} else if (module.equals("Foundation/Listing/AAGL1250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL1250", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL1250";
} else if (module.equals("Foundation/Listing/AAGL1450")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL1450", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL1450";
} else if (module.equals("Foundation/Listing/AAGL1550")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL1550", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL1550";
} else if (module.equals("Foundation/Listing/AAGL1650")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL1650", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL1650";
} else if (module.equals("Foundation/Listing/AAGL1750")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL1750", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL1750";
} else if (module.equals("Foundation/Listing/AAGL8650")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL8650", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL8650";
} else if (module.equals("Foundation/Listing/AAGL4450")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL4450", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL4450";
} else if (module.equals("Foundation/Listing/AAGL1950")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL1950", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL1950";
} else if (module.equals("Foundation/Listing/AAGL0850")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL0850", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL0850";
} else if (module.equals("Foundation/Listing/AAGL2750")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL2750", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL2750";
} else if (module.equals("Foundation/Listing/AAGL2050")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL2050", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL2050";
} else if (module.equals("Foundation/Listing/AAGL2150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL2150", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL2150";
} else if (module.equals("Foundation/Listing/AAGL2550")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL2550", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL2550";
} else if (module.equals("Foundation/Listing/AAGL3050")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL3050", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL3050";
} else if (module.equals("Foundation/Listing/AAGL1050")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL1050", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL1050";
} else if (module.equals("Foundation/Listing/AAGL0950")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL0950", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL0950";
} else if (module.equals("Foundation/Listing/AAGL0450")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL0450", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL0450";
} else if (module.equals("Foundation/Listing/AAGL2650")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL2650", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL2650";
} else if (module.equals("Foundation/Listing/AAGL2950")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL2950", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL2950";
} else if (module.equals("Foundation/Listing/AAGL1850")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL1850", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL1850";
} else if (module.equals("Foundation/Report/AAGR0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGR0250", "GL", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGR0250";
} else if (module.equals("Foundation/Report/AAGR0350")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGR0350", "GL", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGR0350";
} else if (module.equals("Foundation/Report/KLGS0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLGS0150", "GL", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLGS0150";
} else if (module.equals("Foundation/Report/KLGR0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLGR0150", "GL", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLGR0150";
} else if (module.equals("Foundation/Listing/AAGL3750")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGL3750", "GL", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGL3750";
} else if (module.equals("Report/AARL0950")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AARL0950", "CI", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AARL0950";
} else if (module.equals("Cataloging/Listing/AACL0350")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AACL0350", "CT", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AACL0350";
} else if (module.equals("Cataloging/Listing/AACL0550")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AACL0550", "CT", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AACL0550";
} else if (module.equals("Cataloging/Listing/AACL0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AACL0250", "CT", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AACL0250";
} else if (module.equals("Cataloging/Report/KLCS0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLCS0250", "CT", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLCS0250";
} else if (module.equals("Cataloging/Report/KLCS0450")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLCS0450", "CT", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLCS0450";
} else if (module.equals("Reserve_Collection/Maintenance/AAVM0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAVM0150", "RC", "MAINT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLCS0450";
} else if (module.equals("Cataloging/Report/KLCS0650")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLCS0650", "CT", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLCS0650";
} else if (module.equals("Foundation/Maintenance/AAIFN1500M60")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAIFN1500M60", "GL", "MAINT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAIFN1500M60";
} else if (module.equals("Foundation/Enquiry/AAGE0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAGE0150", "GL", "ENQUIRY");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAGE0150";
} else if (module.equals("Circulation/Listing/AARL0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AARL0150", "CI", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AARL0150";
} else if (module.equals("Circulation/Enquiry/AARE0750")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AARE0750", "CI", "ENQUIRY");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AARE0750";
} else if (module.equals("Circulation/Report/AARR0450")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AARR0450", "CI", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AARR0450";
} else if (module.equals("Circulation/Enquiry/AARE1250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AARE1250", "CI", "ENQUIRY");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AARE1250";
} else if (module.equals("Receipting/Listing/AAEL0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAEL0150", "RE", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAEL0150";
} else if (module.equals("Receipting/Listing/AAEL0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAEL0250", "RE", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAEL0250";
} else if (module.equals("Receipting/Report/KLRL0350")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLRL0350", "RE", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLRL0350";
} else if (module.equals("Receipting/Listing/AAEL0350")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAEL0350", "RE", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAEL0350";
} else if (module.equals("Receipting/Report/AAER0150")) { // NOT YET SIAP
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAER0150", "RE", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAER0150";
} else if (module.equals("Binding/Listing/AABL0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AABL0150", "BI", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AABL0150";
} else if (module.equals("Binding/Listing/AABL0250")) { // NOT YET SIAP
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AABL0250", "BI", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AABL0250";
} else if (module.equals("Binding/Report/AABR0250")) { // NOT YET SIAP
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AABR0250", "BI", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AABR0250";
} else if (module.equals("Foundation/Maintenance/AAIFN1900M60")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAIFN1900M60", "GL", "MAINT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAIFN1900M60";
} else if (module.equals("mrbs/Listing/AABL0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AABL0150", "MR", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AABL0150";
} else if (module.equals("mrbs/Listing/AABL0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AABL0250", "MR", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AABL0250";
} else if (module.equals("mrbs/Report/AABR0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AABR0150", "MR", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AABR0150";
} else if (module.equals("mrbs/Report/AABR0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AABR0250", "MR", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AABR0250";
} else if (module.equals("ILL_Incoming_Request/Report/AALR0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AALR0150", "LI", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AALR0150";
} else if (module.equals("ILL_Outgoing_Request/Report/AALR0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AALR0250", "LO", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AALR0250";
} else if (module.equals("Accounting/Listing/AAFL0350")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAFL0350", "FA", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAFL0350";
} else if (module.equals("Accounting/Listing/AAFL0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAFL0150", "FA", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAFL0150";
} else if (module.equals("Accounting/Listing/AAFL0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAFL0250", "FA", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAFL0250";
} else if (module.equals("Cataloging/Report/KLCS0750")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLCS0750", "CT", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLCS0750";
} else if (module.equals("Acquisition/Report/KLAS0850")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLAS0850", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLAS0850";
} else if (module.equals("Cataloging/Listing/AACL0850")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AACL0850", "CT", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AACL0850";
} else if (module.equals("NeuGuide/Listing/AAVL0150")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAVL0150", "RC", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAVL0150";
} else if (module.equals("IRS/Listing/AAIL0450")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAIL0450", "IR", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAIL0450";
} else if (module.equals("Foundation/Report/KLGS0250")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("KLGS0250", "GL", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "KLGS0250";
} else if (module.equals("Circulation/Report/AARR0350")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AARR0350", "CI", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AARR0350";
} else if (module.equals("Cataloging/Listing/AACL0950")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AACL0950", "CT", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AACL0950";
} else if (module.equals("Acquisition/Report/AAAR0950")) {
	menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAAR0950", "AC", "REPORT");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAAR0950";
}///else if (module.equals("Ppj/Bapi/Report/PPJR0150")) {
	else if (module.equals("Ppj/Bapi/Report/PPJR0150")) {
	/* menuAndVersion = GetGlmenu.getGlobalGlmenuVersionOrName("AAFL0150", "FA", "LISTING");
	String[] tempArray;
	tempArray = menuAndVersion.split("=");

	header = tempArray[1].trim();
	version = tempArray[2].trim();

	leftnav += "navigation.jsp";
	programID = "AAFL0150"; */
		header = "Bapi Log";
	version = "2024.1.0.0";
		 leftnav += "navigation.jsp";
		 programID = "AAFL0150";
} else {
	leftnav += "navigation.jsp";
}
%>

<html>
<title>ILMU</title>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<body leftmargin="0" topmargin="0">
	<div class="box" style="height: 1000px">
		<section class="content-header">
			<input type="hidden" id='liferayLogin'> <input type="hidden"
				id="programID" value="<%=programID%>">
			<h3>
				<i class="glyphicon glyphicon-list"></i>&nbsp;<%=header%>
				<span style='font-size: 8pt'>(Ver <%=version%>)
				</span> <a class="btn btn-primary pull-right"> <span
					class="glyphicon glyphicon-home"></span>
				</a>
			</h3>
		</section>
		<section class="content">
			<div class="box">
				<jsp:include page="<%=content%>" />
			</div>
		</section>
	</div>
</body>
</html>