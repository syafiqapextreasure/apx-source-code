<%@page import="java.util.*,javax.sql.*"%>
<html style = "background-color: #B6B6B6">
	<head>
		<title>Parable Beta</title>
	<link href="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
 	
	<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/navigation/_all-skins.min.css">
	
 	<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
	<link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/sweetalert2/css/sweetalert2.min.css"rel="stylesheet">

<%-- 	<link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/font-awesome.min.css"/> --%>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/themes/default_themes/bootstrap/css/bootstrap.css" rel="stylesheet">
	
	
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
    <script src="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
  
 	<script src="${pageContext.request.contextPath}/themes/default_themes/bootstrap/js/bootstrap.min.js"></script>

 	<script src="${pageContext.request.contextPath}/plugins/sweetalert2/js/sweetalert2.min.js" ></script>


	<%-- 	<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">	
	
			<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css"/>	
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap-theme.min.css"/>	
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/datepicker.css"/>	
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>
		 <script type="text/javascript" src="<%= request.getContextPath() %>/css/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/css/datatables/dataTables.bootstrap.min.js"></script>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/datatables/dataTables.bootstrap.css">
  <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	 --%>
	</head>		
<!-- 	<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">
        <img alt="Brand" src="...">
      </a>
    </div>
    <ul class="nav navbar-nav">
        <li class=""><a href="/module/homepage.jsp" >Home</a></li>
        <li class=""><a href="/module/book/book_spine.jsp" >Book Label</a></li>'
        <li class=""><a href="/module/spine/book_spine.jsp" >Spine Label</a></li>
        <li class=""><a href="/module/patron/patron.jsp" >Patron Label</a></li>
    </ul>
  </div>
</nav> -->
