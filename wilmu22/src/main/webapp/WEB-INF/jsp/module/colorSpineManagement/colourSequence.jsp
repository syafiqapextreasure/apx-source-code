<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title> Colour Sequence Table</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.1/css/buttons.dataTables.css" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
    <style type="text/css">
        @media print {
            #jtron {
                display: none;
            }

            #myTable2,
            #myTable2 * {
                visibility: visible;
            }

            #hide1,
            #hide2,
            #hide3 {
                display: none;
            }
        }
    </style>
</head>

<body>
<!--     <div id="jtron" class="jumbotron text-center"> -->
	<div class="text-center">
        <h1> Colour Sequence Table</h1>
<!--         <h5><a href="main.jsp">Home</a></h5> -->
<!-- 		<h5><a href="./colorSpineManagement">Home</a></h5> -->
    </div>

    <div class="container" style="margin-top:40px">
    
    	<div class="d-flex justify-content-between" id="hide3">
        	<!-- <p class="text-left"><strong><a href="create.jsp"> Add New Colour </a></strong></p> -->
        	<p class="text-left"><strong><a href="${pageContext.request.contextPath}/create"> Add New Colour </a></strong></p>
        	<p class="text-left"><strong><input type="button" onclick="window.print()" value="Print" /></strong></p>
        </div>

        <table border="1" id="myTable2" class="table">
            <thead class="thead-dark">
                <tr>
                    <th>Colour Code | Sequence</th>
                    <th>Colour</th>
                    <th id="hide1">Action</th>
                </tr>
            </thead>
            <c:forEach var="colour" items="${colourSequence}">
                <c:set var="test" value="${colour.colourCode}" />
                <%
                
					String str = (String)pageContext.getAttribute("test");
					if ((str.matches(".*\\d.*")) && str.startsWith("r")){	
						String a = StringUtils.substringBetween(str, "r", "g");
						String b = StringUtils.substringBetween(str, "g", "b");
						String[] arrstr = str.split("b"); 
						String rgb = "rgb("+a+","+b+","+arrstr[1]+")";
						pageContext.setAttribute("rgb", rgb);
					} 
					else
					{
					pageContext.setAttribute("rgb", str);
					}
				%>
                <tr>
                    <td style="width:20%">${colour.colourCode} | ${colour.colourSequence}</td>
                    <td style="width:50%; background-color:${rgb} !important"></td>

                    <td id="hide2"><a href="update/<c:out value='${colour.colourSequence}'/>">Update Colour Code</a> |
                        <a href="delete/<c:out value='${colour.colourSequence}'/>" onclick="return confirm('Are you sure you want to delete this colour?');">Delete Colour Code</a></td>
                    <!-- 				<a href="#ex1" rel="modal:open"> Delete Colour Code</a></td> -->
                    <%-- 	<a href="delete/<c:out value='${colour.colourSequence}'/>">Delete Colour Code</a></td> --%>

                </tr>

                <!-- 			<div id="ex1" class="modal"> -->
                <%-- 			  <p>Are you sure you want to delete colour: ${colour.colourSequence} | ${colour.colourCode} ?</p> --%>
                <%-- 			  <button  onclick="location.href='delete/<c:out value='${colour.colourSequence}'/>'" type="button"  >Delete</button> --%>
                <!-- 			  <button onclick="#" rel="modal:close">Cancel</button> -->
                <!-- 			</div> -->
            </c:forEach>
        </table>

    </div>
    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.print.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>

    <script>
        $(document).ready(function() {

            $('#myTable2').DataTable({
                "pagingType": "simple_numbers",
                "pageLength": 50
                //dom: 'Bfrtip',
                //buttons: ['print']
            });
        });
    </script>

</body>

</html>