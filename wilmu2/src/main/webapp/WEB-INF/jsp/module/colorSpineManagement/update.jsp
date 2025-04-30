<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Update Colour</title>
</head>

<body onload="viewcol1()">
    <div class="jumbotron jumbotron-fluid text-center">
        <h1> Update Colour </h1>
        <h5><a href="${pageContext.request.contextPath}/template?MODULE=colorSpineManagement&ACTION=main.jsp">Home</a></h5>
    </div>
    <div class="container" style="margin-top:40px">
        <form action="../update" method="post">
            <div class="row">
                <div class="col-sm-12">

                    <c:forEach var="colour" items="${listColour}">

                        <!-- 	<div> -->
                        <!-- 	<p>Current Colour Sequence: </p>  -->
                        <%-- 	<input type="text" name="id" value="${colour.colourSequence}" readonly />  --%>
                        <!-- 	</div> -->
                        <!-- 	<div> -->
                        <%-- 	<br><p>Current Colour Code: <strong>${colour.colourCode}</strong> </p>  --%>
                        <%-- 	<input type="text" name="id" id="currentcol" value="${colour.colourCode}" style="background-color:${colour.colourCode}" readonly />  --%>
                        <!-- 	</div> -->
                        <!-- 	<div> -->
                        <!-- 	<br><p>Insert New Colour Code: </p> -->
                        <%-- 	<input type="text" id="abc2" name="newColourCode" class="search" value="${colour.colourCode}" /> --%>
                        <!-- 	<input type="button" value="View Colour" onclick="search(this)">	 -->
                        <!-- 	</div>    		     -->
                        <!-- 	<div> -->
                        <!-- 	<br><input type="submit" value="Update" />	 -->
                        <!-- 	</div> -->

                        <div class="row justify-content-md-center">
                            <div class="col col-6">
                                <div class="input-group mb-4">
                                    <strong><a href="${pageContext.request.contextPath}/colourSequence">To Colour Sequence Table</a></strong></p>
                                </div>
                            </div>
                        </div>

                        <div class="row justify-content-md-center">
                            <div class="col col-6">
                                <div class="input-group mb-4">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon3">Current Colour Sequence:</span>
                                    </div>
                                    <input type="number" class="form-control" name="id" value="${colour.colourSequence}" readonly>
                                </div>
                            </div>
                        </div>
                        <div class="row justify-content-md-center">
                            <div class="col col-6">
                                <div class="input-group mb-4">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon3">Current Colour Code:</span>
                                    </div>
                                    <input type="text" class="form-control" id="currentcol" name="id2" value="${colour.colourCode}" style="color:${colour.colourCode}; background-color:${colour.colourCode}" readonly>
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon3">${colour.colourCode}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row justify-content-md-center">
                            <div class="col col-6">
                                <label for="basic-url">
                                    <h6>Colour code format guide: </h6>
                                    <p>1) Text: red,green <br> 2) RGB: r123g123b123 <br> 3) Hex: #A1B2C3</p>
                                </label>
                                <div class="input-group mb-4">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon3">Insert New Colour Code:</span>
                                    </div>
                                    <input type="text" class="form-control search" id="abc2" name="newColourCode" required>
                                    <div class="input-group-append">
                                        <input type="button" class="btn btn-outline-secondary" value="View Colour" onclick="search(this)" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row justify-content-md-center">
                            <div class="col col-6">
                                <div class="input-group mb-4">
                                    <input type="submit" class="btn btn-outline-secondary" value="Update Colour" />
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

        </form>

        <div class="row justify-content-md-center">
            <div class="col col-6">
                <div class="input-group mb-2">
                    <h5>${msg}</h5>
                </div>
            </div>
        </div>


    </div>

    <script>
        function viewcol1() {
            var val = $("#currentcol").val();
            console.log(val);
            var re = /^[A-Za-z]+$/;
            if (/\d/.test(val) && (val.startsWith("r"))) {
                var r1 = val.split('r').pop().split('g')[0];
                var g1 = val.split('g').pop().split('b')[0];
                var b1 = val.split('b')[1];
                var rgb1 = "rgb(" + r1 + "," + g1 + "," + b1 + ")";
                $("#currentcol").css('background-color', rgb1);
                console.log(rgb1);
            } else {
                var _color = $("#currentcol").val().toLowerCase();
                $("#currentcol").css('background-color', _color)
                console.log(_color);
            }
        }

        function search(ele) {
            var val = $("#abc2").val();
            var re = /^[A-Za-z]+$/;
            if (/\d/.test(val) && (val.startsWith("r"))) {
                var r2 = val.split('r').pop().split('g')[0];
                var g2 = val.split('g').pop().split('b')[0];
                var b2 = val.split('b')[1];
                var rgb2 = "rgb(" + r2 + "," + g2 + "," + b2 + ")";
                $("#abc2").css('background-color', rgb2);
                console.log(rgb2);
            } else {
                var _color = $("#abc2").val().toLowerCase();
                $("#abc2").css('background-color', _color)
                console.log(_color);
            }
        }
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>