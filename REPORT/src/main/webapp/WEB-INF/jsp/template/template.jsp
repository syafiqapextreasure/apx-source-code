<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.wilmu.global.*"%>
<%-- <%@page import="com.ilmu.foundation.CodeTable.SQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%> --%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	

<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/sweetalert2/css/sweetalert2.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/AdminLTE.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/_all-skins.min.css">
<link id="ui-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link id="ui1-stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/resources/css/jasny-bootstrap.min.css">

<!-- 27032021 -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/css/bootstrap-datepicker3.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/css/font-awesome.min.css">



<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/themes/default_themes/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugin/sweetalert2/js/sweetalert2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/resources/js/jasny-bootstrap.min.js"></script>

<!-- 27032021 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/js/bootstrap-datepicker.js"></script>

<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/bootstrap-multiselect/js/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-multiselect/css/bootstrap-multiselect.css">

<!-- 28042021 -->
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/buttons.print.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/js/dataTables.responsive.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/js/vfs_fonts.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/js/pdfmake.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/js/jszip.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/js/dataTables.buttons.min.js"></script>

<link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/css/responsive.dataTables.min.css">


<!-- 02052021 -->

<%-- <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/js/buttons.print.min.js"></script> --%>

<!-- <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.print.min.js"></script> -->




<script>

  $( document ).ready(function() {

	var username = window.frameElement.getAttribute("name");
	//alert(username);*/
	//var username = "ilmuadmin";
	$("#liferayLogin").val(username);

	/* for(var i = 0; i < str_array.length; i++) {
		 username += str_array[i];
	} */

	var programID = $("#programID").val();
	//alert(programID);
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
				  //allowOutsideClick: false
				  allowOutsideClick: false,
				  allowEscapeKey: false
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
	System.out.println(content);
    String leftnav = "/WEB-INF/jsp/include/";
    String header = "";
    String programID= "";
    String version = "";
    
    
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

    }else if (module.equals("ReportCir/1_CirActivity")){
    	 header = "Circulation Activity";
    	 version = Menu.getVersion("KLRL0950", "CI", "REPORT");
		 leftnav += "navigation.jsp";
		 programID = "KLRL0950";
    }else if (module.equals("ReportCir/1_CirActivity2")){
   	 header = "Circulation Activity";
   	 version = Menu.getVersion("KLRL0950", "CI", "REPORT");
		 leftnav += "navigation.jsp";
		 programID = "KLRL0950";
   }else if (module.equals("Enquiry/13_LoanHistory")){
   	 header = "Loan History Enquiry";
   	 version = Menu.getVersion("AARE0750", "CI", "ENQUIRY");
		 leftnav += "navigation.jsp";
		 programID = "AARE0750";
   }else if (module.equals("ReportFnd/1_MembershipRegReport")){
  	 header = "Membership Registration Report";
  	version = Menu.getVersion("KLGS0150", "GL", "REPORT");
		 leftnav += "navigation.jsp";
		 programID = "KLGS0150";
	}else if (module.equals("ReportCir/2_CirNotice")){
	  	 header = "Circulation Notice";
	   	version = Menu.getVersion("KLRS0250", "CI", "REPORT");
	 		 leftnav += "navigation.jsp";
	 		 programID = "KLRS0250";
	}else if (module.equals("ListingFnd/25_Currency")){
	  	 header = "Currency Listing";
	   	version = Menu.getVersion("AAGL2550", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL2550";
	}else if (module.equals("ListingFnd/10_Condition")){
	  	 header = "Condition Listing";
	   	version = Menu.getVersion("AAGL1050", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL1050";
	}else if (module.equals("ListingFnd/16_Race")){
	  	 header = "Race Listing";
	   	version = Menu.getVersion("AAGL1650", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL1650";
	}else if (module.equals("ListingFnd/11_PatronCategory")){
	  	 header = "Patron Category Listing";
	   	version = Menu.getVersion("AAGL1150", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL1150";
	}else if (module.equals("ListingFnd/44_ErrorInSupply")){
	  	 header = "Error In Supply Listing";
	   	version = Menu.getVersion("AAGL4450", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL4450";
	}else if (module.equals("ListingFnd/09_Location")){
	  	 header = "Location Listing";
	   	version = Menu.getVersion("AAGL0950", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL0950";	
	}else if (module.equals("ListingFnd/15_Course")){
	  	 header = "Course Listing";
	   	version = Menu.getVersion("AAGL1550", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL1550";
    }else if (module.equals("ListingFnd/86_Branch")){
	  	 header = "Branch Listing";
	   	version = Menu.getVersion("AAGL8650", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL8650";	
    }else if (module.equals("ListingFnd/17_Town")){
	  	 header = "Town Listing";
	   	version = Menu.getVersion("AAGL1750", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL1750";	
	}else if (module.equals("ListingFnd/12_PatronStatus")){
	  	 header = "Patron Status Listing";
	   	version = Menu.getVersion("AAGL1250", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL1250";			 
	}else if (module.equals("ListingFnd/04_Group")){
	  	 header = "Group Listing";
	   	version = Menu.getVersion("AAGL0450", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL0450";			 
	}else if (module.equals("ListingFnd/26_Marc")){
	  	 header = "MARC Listing";
	   	version = Menu.getVersion("AAGL2650", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL2650";			 
	}else if (module.equals("ListingFnd/14_Department")){
	  	 header = "Department Listing";
	   	version = Menu.getVersion("AAGL1450", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL1450";			 
	}else if (module.equals("ListingFnd/19_Tag")){
	  	 header = "Tag Listing";
	   	version = Menu.getVersion("AAGL1950", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL1950";			 
	}else if (module.equals("ListingFnd/20_Indicator")){
	  	 header = "Indicator Listing";
	   	version = Menu.getVersion("AAGL2050", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL2050";			 
	}else if (module.equals("ListingFnd/29_ItemCategory")){
	  	 header = "Item Category Listing";
	   	version = Menu.getVersion("AAGL2950", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL2950";			 
	}else if (module.equals("ListingFnd/08_Menu")){
	  	 header = "Menu Listing";
	   	version = Menu.getVersion("AAGL0850", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL0850";			 
	}else if (module.equals("ListingFnd/21_Subfields")){
	  	 header = "Subfields Listing";
	   	version = Menu.getVersion("AAGL2150", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL2150";			 
	}else if (module.equals("ListingFnd/27_MediaInformation")){
	  	 header = "Media Information Listing";
	   	version = Menu.getVersion("AAGL2750", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL2750";			 
	}else if (module.equals("ListingFnd/37_Vendor")){
	  	 header = "Vendor Listing";
	   	version = Menu.getVersion("AAGL3750", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL3750";			 
	}else if (module.equals("ReportCir/06_CirFreq")){
	  	 header = "Circulation Frequency Report";
	   	version = Menu.getVersion("AARR0650", "CI", "REPORT");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AARR0650";			 
	}else if (module.equals("ReportCir/04_ItemOnLoan")){
	  	 header = "Item On Loan Report";
	   	version = Menu.getVersion("KLRL0450", "CI", "REPORT");
	 		 leftnav += "navigation.jsp";
	 		 programID = "KLRL0450";			 
	}else if (module.equals("ListingCir/01_WeedingListing")){
	  	 header = "Weeding Listing";
	   	version = Menu.getVersion("AAWL0150", "CI", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAWL0150";			 
	}else if (module.equals("ReportCir/10_ItemRenewal")){
	  	 header = "Items Renewal Report";
	   	version = Menu.getVersion("KLRL1050", "CI", "REPORT");
	 		 leftnav += "navigation.jsp";
	 		 programID = "KLRL1050";			 
	}else if (module.equals("ListingCir/08_ExpiredReassignmentListing")){
	  	 header = "Expired Reassignment Items";
	   	version = Menu.getVersion("AARL0850", "CI", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AARL0850";			 
	}else if(action1.length>1){
		if(action.equals("AuditTrailListing.jsp?value=" + action2[1])){
			
			 header = "Audit Trail Listing "+ action2[1];
			 version = Menu.getVersion("AARL0950", "CI", "LISTING");
			 leftnav += "navigation.jsp";
			 programID = "AARL0950"; 
		}
	}else if (module.equals("ListingCir/10_NonCirculatedListing")){
	  	 header = "Non-Circulated Item Listing";
	   	version = Menu.getVersion("AARL1050", "CI", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AARL1050";			 
	}else if (module.equals("ReportCir/09_ItemDisRep")){
	  	 header = "Items Discharging Report";
	   	version = Menu.getVersion("AARR0950", "CI", "Report");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AARR0950";			 
	}else if (module.equals("ListingCir/02_OnLoanItemListing")){
	  	 header = "On-Loan Item Listing";
	   	version = Menu.getVersion("AARL0250", "CI", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AARL0250";			 
	}else if (module.equals("ListingCir/03_OnHoldItemListing")){
	  	 header = "On-Hold Item Listing";
	   	version = Menu.getVersion("AARL0350", "CI", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AARL0350";			 
	}else if (module.equals("ListingCir/04_OnReserveItemListing")){
	  	 header = "On-Reserve Item Listing";
	   	version = Menu.getVersion("AARL0450", "CI", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AARL0450";			 
	}else if (module.equals("ReportCir/11_LoanItemPatrRep")){
	  	 header = "Loan Items for Patron";
	   	version = Menu.getVersion("AARR1150", "CI", "Report");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AARR1150";			 
	}else if (module.equals("ReportCir/05_PatrHisRep")){
	  	 header = "Patron History Report";
	   	version = Menu.getVersion("AARR0550", "CI", "Report");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AARR0550";			 
	}else if (module.equals("ListingCir/01_DueItemListing")){
	  	 header = "Due Item Listing";
	   	version = Menu.getVersion("AARL0150", "CI", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AARL0150";			 
	}else if (module.equals("ReportCir/02_MissingLostItems")){
	  	 header = "Missing/Lost Item Report";
	   	version = Menu.getVersion("KLRL0250", "CI", "Report");
	 		 leftnav += "navigation.jsp";
	 		 programID = "KLRL0250";			 
	}else if (module.equals("ReportCir/01_LoanHistRep")){
	  	 header = "Loan History Report by Title";
	   	version = Menu.getVersion("KLRL0150 ", "CI", "Report");
	 		 leftnav += "navigation.jsp";
	 		 programID = "KLRL0150 ";			 
	}else if (module.equals("ListingCir/07_ItemReas")){
	  	 header = "Item Reassignment Listing";
	   	version = Menu.getVersion("AARL0750", "CI", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AARL0750";			 
	}else if (module.equals("ReportCir/3_ResvScrutiny")){
	  	 header = "Reservation Scrutiny Report";
	   	version = Menu.getVersion("AARR0350 ", "CI", "Report");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AARR0350 ";			 
	}else if (module.equals("ListingFnd/18_PatronDetails")){
	  	 header = "Patron Details Listing";
	   	version = Menu.getVersion("AAGL1850", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL1850";
	}else if (module.equals("ListingFnd/30_PatronEligibility")){
	  	 header = "Patron Eligibility Listing";
	   	version = Menu.getVersion("AAGL3050", "GL", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGL3050";
	}else if (module.equals("ReportCir/01_CirActBySubj")){
	  	 header = "Circulation Activity Report by Subject";
	   	version = Menu.getVersion("KLRS0150", "CI", "REPORT");
	 		 leftnav += "navigation.jsp";
	 		 programID = "KLRS0150";
	}else if (module.equals("ReportFnd/01_HourlyLibUse")){
	  	 header = "Hourly Library Use Report";
	   	version = Menu.getVersion("KLGR0150", "GL", "REPORT");
	 		 leftnav += "navigation.jsp";
	 		 programID = "KLGR0150";
	}else if (module.equals("ListingCat/04_TitleListing")){
	  	 header = "Title listing";
	   	version = Menu.getVersion("AACL0450", "CT", "LISTING");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AACL0450";
	}else if (module.equals("ReportFnd/03_VendorStatistics")){
	  	 header = "Vendor Statistic";
	   	version = Menu.getVersion("AAGR0350", "GL", "REPORT");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAGR0350";
	}else if (module.equals("ReportAcq/04_OrdersReport")){
	  	 header = "Orders Report";
	   	version = Menu.getVersion("AAAR0450", "AC", "REPORT");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAAR0450";
	}else if (module.equals("ReportAcq/07_PaymentHistoryReport")){
	  	 header = "Payment History Report";
	   	version = Menu.getVersion("AAAR0750", "AC", "REPORT");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AAAR0750";
	}else if (module.equals("ReportCir/LostItemNotification")){
	  	 header = "Generate Lost Item Notification";
	   	version = Menu.getVersion("AARR0250", "CI", "REPORT");
	 		 leftnav += "navigation.jsp";
	 		 programID = "AARR0250";
	}else if (module.equals("ListingAcq/OrderRequstList")){
   	    	System.out.println("line 255 module: "+ module);
 	    	 header = "Order Request List";
 	    	 version = Menu.getVersion("AAAL0150", "AC", "LISTING");
 	 		 leftnav += "navigation.jsp";
 	 		 programID = "AAAL0150";
 	}else{
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

<!-- <style>fcgf
body {
	overflow: visible;
}

#version {
	font-size: 9pt;
}
</style> -->



<body leftmargin="0" topmargin="0">
<div style="height:1000px">
	<section class="content-header">
		<input type="hidden" id='liferayLogin'  > 
		<input type="hidden" id="programID" value="<%=programID%>">
		<input type="hidden" id='recepting'> 

		<h3>
			<i class="glyphicon glyphicon-list"></i>&nbsp;<%=header %>
			<span style='font-size:8pt'>(Ver <%=version %>)</span>
			<a
				class="btn btn-primary pull-right"> <span
				class="glyphicon glyphicon-home"></span>
			</a>
		</h3>

	</section>
	<section class="content">

		<div class="box">
			<jsp:include page="<%= content %>" />
		</div>
	</section>

</div>
</body>



</html>