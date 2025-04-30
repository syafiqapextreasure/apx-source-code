<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title> Item Class List </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">   
</head>

<body>
    <div class="text-center">
        <h1> Item Class Listing </h1>
<!--         <h5><a href="main.jsp">Home</a></h5> -->
    </div>

    <div class="container" style="margin-top:40px">
    
    	<div class="d-flex justify-content-between" >
	       <!--  <p class="text-left"><strong><a href="create.jsp"> Add New Colour </a></strong></p> -->
	       <p class="text-left"><strong><a href="${pageContext.request.contextPath}/create"> Add New Colour </a></strong></p>
	    </div>
	    
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
            <c:forEach var="itemclass" items="${listItemClass}">

                <c:set var="test" value="${itemclass.colourCode}" />
                <%

					String str = (String)pageContext.getAttribute("test");
					if ((str.matches(".*\\d.*")) && str.startsWith("r")){	
						String a = StringUtils.substringBetween(str, "r", "g");
				//		System.out.println(a);
						String b = StringUtils.substringBetween(str, "g", "b");
				//		System.out.println(b);
						String[] arrstr = str.split("b"); 
				//		System.out.println("rgb("+a+","+b+","+arrstr[1]+")");
						String rgb = "rgb("+a+","+b+","+arrstr[1]+")";
// 						System.out.println(rgb);
						pageContext.setAttribute("rgb", rgb);
					} 
					else
					{
// 						System.out.println(str); 
					pageContext.setAttribute("rgb", str);
					}
				%>
                <tr>
                    <td>${itemclass.ppatCode}</td>
                    <td>${itemclass.colourDescription}</td>
                    <td>${itemclass.colourSequence}</td>
                    <td style="background-color:${rgb}"></td>
                    <%-- 				<td>${location.colourCode}</td> --%>
                    <td><a href="update/<c:out value='${itemclass.colourSequence}'/>">Update Colour Code</a> | </br><a href="delete/<c:out value='${itemclass.colourSequence}'/>" onclick="return confirm('Are you sure you want to delete this colour?');">Delete Colour Code</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {

            $('#myTable2').DataTable({
                "pagingType": "simple_numbers",
                "defaultContent": "",
                "pageLength": 25
            });
        });
    </script>
</body>
</html>