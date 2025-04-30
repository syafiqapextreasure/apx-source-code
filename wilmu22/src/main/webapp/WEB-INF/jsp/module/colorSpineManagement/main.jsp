
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en"> 
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<title> Spine Colour </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<style>
.col-centered{
    float: none;
    margin: 0 auto;
    width: 40%;
}

.col-centered a {
  background-color: #eee;
  color: black;
  display: block;
  padding: 12px;
  text-decoration: none;
  block: center;
}

.col-centered a:hover {
  background-color: #bfbfbf;
}

.col-centered a.active {
  background-color: #007399;
  color: black;
}

h1 {
  text-align: center;
  font-size: 38px;
}
</style>
<body>
<div class="text-center">
  <h1> Spine Colour  </h1> 
</div>


<div class="col-centered text-center">
<!--   <a href="main.jsp" class="active">Home</a> -->
<!--   <a href="create.jsp"> Create </a> -->
   <h4><a href="./colourSequence"> Colour Sequence Table</a></h4>
   <h4><a href="./itemclass"> Item Class Listing </a></h4>
   <h4><a href="./location">Location Listing </a></h4>
</div>
<script>
	$(document).ready( function () {
	    $('#myTable').DataTable({
	        "pagingType": "simple_numbers"
	    } );
	} );
</script>	
</body>
</html>