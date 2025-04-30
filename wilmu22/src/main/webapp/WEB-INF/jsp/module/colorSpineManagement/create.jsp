<html>

<head>
    <title>Add New Colour </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>

<body>

    <script>
        function search(ele) {
            var val = $("#abc2").val();
            if (/\d/.test(val) && (val.startsWith("r"))) {
                var r2 = val.split('r').pop().split('g')[0];
                var g2 = val.split('g').pop().split('b')[0];
                var b2 = val.split('b')[1];
                var rgb2 = "rgb(" + r2 + "," + g2 + "," + b2 + ")";
                $("#abc2").css('background-color', rgb2);
            } else {
                var _color = $("#abc2").val().toLowerCase();
                $("#abc2").css('background-color', _color)
            }
        }
    </script>

 <!--    <div class="jumbotron jumbotron-fluid text-center"> -->
    <div class="text-center">
        <h1> Add New Colour </h1>
    <!--     <h5><a href="main.jsp">Home</a></h5> -->
    </div>

    <form action="addColor" method="post">
        <div class="container" style="margin-top:40px">

            <!-- 	<div class="row"> -->
            <!-- 		<div class="col-sm-12"">	 -->
            <!-- 			<p>Colour Sequence: </p><input type="number" name="colseq" /> -->
            <!-- 			<div> -->
            <!-- 			<br><p>Colour Code:</p> <input type="text" id="abc2" name="colcode" class="search" /> <input type="button" value="View Colour" onclick="search(this)"> -->
            <!-- 			</div>	 -->
            <!-- 			<div><input type="submit" value="Create" /></div> -->
            <!-- 		</div>	 -->
            <%-- 		<div class="col-sm-12"><h5>${msg}</h5></div>	 --%>
            <!-- 	</div> -->
            
            <div class="row justify-content-md-center">
                <div class="col col-6">
                    <div class="input-group mb-4">
                        <strong><a href="${pageContext.request.contextPath}/colourSequence">To Colour Sequence Table</a></strong>
                    </div>
                </div>
            </div>

            <div class="row justify-content-md-center">
                <div class="col col-6">
                    <div class="input-group mb-4">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon3">Insert Colour Sequence:</span>
                        </div>
                        <input type="number" class="form-control" name="colseq" required>
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
                            <span class="input-group-text" id="basic-addon3">Insert Colour Code:</span>
                        </div>
                        <input type="text" class="form-control search" id="abc2" name="colcode" required>
                        <div class="input-group-append">
                            <input type="button" class="btn btn-outline-secondary" value="View Colour" onclick="search(this)" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col col-6">
                    <div class="input-group mb-4">
                        <input type="submit" class="btn btn-outline-secondary" value="Add Colour" />
                    </div>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col col-6">
                    <div class="input-group mb-2">
                        <h5>${msg}</h5>
                    </div>
                </div>
            </div>

        </div>
    </form>

</body>

</html>