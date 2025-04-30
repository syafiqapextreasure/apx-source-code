<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<title> PPAT WEB PAGE </title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="jumbotron text-center">
  <h1> PPAT - ITEM CLASS LISTING </h1>
</div>
	<p><strong><a href="main.jsp"> Return </a></strong></p>
	<div class="container" style="margin-top:80px">
	<table border="1" id="myTable" class="table">
	<thead class="thead-dark">
		<tr>
			<th>PPAT Code</th>
			<th>Description</th>
			<th>Category</th>
			<th>Virtua Code</th>
			<th>Colour Code</th>
			<th>Colour</th>
			<th>Action</th>
		</tr>
	</thead>
		<c:forEach var="student" items="${listStudent}">
			<tr>
				<td>${student.id}</td>
				<td>${student.name}</td>
				<td>${student.email}</td>
				<td>${student.email}</td>
				<td>${student.course}</td>
				<td style="background-color:${student.course}"></td>
				<td><a href="update/<c:out value='${student.id}'/>">Update</a> | <a
					href="delete/<c:out value='${student.id}'/>">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	
		<table border="1" id="myTable2" class="table">
	<thead class="thead-dark">
		<tr>
			<th>PPAT Code</th>
			<th>Description</th>
			<th>Colour Code</th> 
			<th>Colour</th>
			<th>Action</th>
		</tr>
	</thead>
		<c:forEach var="location" items="${listLocation}">
			<tr>
				<td>${location.ppatCode}</td>
				<td>${location.colourDescription}</td>
				<td>${location.colourSequence}</td>
				<td style="background-color:${location.colourCode}"></td>
<%-- 				<td>${location.colourCode}</td> --%>
				<td><a href="update/<c:out value='${location.colourCode}'/>">Update</a> | <a
					href="delete/<c:out value='${location.colourCode}'/>">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script>
	$(document).ready( function () {
	    $('#myTable').DataTable({
	        "pagingType": "simple_numbers"
	    } );
	    
	    $('#myTable2').DataTable({
	        "pagingType": "simple_numbers"
	    } );
	} );
</script>	
	
</body>
</html>