<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Spine Label</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.0/css/select.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.6/css/buttons.dataTables.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
    <%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/bootstrap-5.0.2-dist/bootstrap-5.0.2-dist/css/bootstrap.min.css"> --%>
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Custom styles for this template,minified -->
	<style>
	body{font-size:.875rem}.feather{width:16px;height:16px;vertical-align:text-bottom}.sidebar{position:fixed;top:0;bottom:0;left:0;z-index:100;padding:0;box-shadow:inset -1px 0 0 rgba(0,0,0,.1)}.sidebar-sticky{position:-webkit-sticky;position:sticky;top:48px;height:calc(100vh - 48px);padding-top:.5rem;overflow-x:hidden;overflow-y:auto}.sidebar .nav-link{font-weight:500;color:#333}.sidebar .nav-link .feather{margin-right:4px;color:#999}.sidebar .nav-link.active{color:#007bff}.sidebar .nav-link.active .feather,.sidebar .nav-link:hover .feather{color:inherit}.sidebar-heading{font-size:.75rem;text-transform:uppercase}.navbar-brand{padding-top:.75rem;padding-bottom:.75rem;font-size:1rem;background-color:rgba(0,0,0,.25);box-shadow:inset -1px 0 0 rgba(0,0,0,.25)}.navbar .form-control{padding:.75rem 1rem;border-width:0;border-radius:0}.form-control-dark{color:#fff;background-color:rgba(255,255,255,.1);border-color:rgba(255,255,255,.1)}.form-control-dark:focus{border-color:transparent;box-shadow:0 0 0 3px rgba(255,255,255,.25)}.border-top{border-top:1px solid #e5e5e5}.border-bottom{border-bottom:1px solid #e5e5e5}
	</style>
	
	</head>

  <body>
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Spine Label</a>
    </nav>

    <div class="container-fluid">
      <div class="row">
<!--         <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link" href="home" onclick="modal();">
                  <span data-feather="home"></span>
                  Home <span class="sr-only">(current)</span>
                </a>
              </li>
              <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>Search by:</span>
            </h6>
              <li class="nav-item">
                <a class="nav-link" href="byAccNum" onclick="modal();">
                  <span data-feather="search"></span>
                  Accession No
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="byAccRange" onclick="modal();">
                  <span data-feather="search"></span>
                  Accession Range
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="byCallNum" onclick="modal();">
                  <span data-feather="search"></span>
                  Call No
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="byAccDateRange" onclick="modal();">
                  <span data-feather="search"></span>
                  Accession Date Range
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="byControlNum" onclick="modal();">
                  <span data-feather="search"></span>
                  Control No
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="byItemCategory" onclick="modal();">
                  <span data-feather="search"></span>
                  Item Category
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="bySMD" onclick="modal();">
                  <span data-feather="search"></span>
                  SMD
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="byTitle" onclick="modal();">
                  <span data-feather="search"></span>
                  Title
                </a>
              </li>
            </ul>

          </div>
        </nav>
 -->
		<div class="modal fade" tabindex="-1" role="dialog" id="spinnerModal">
		    <div class="modal-dialog modal-dialog-centered text-center" role="document">
		        <span class="fa fa-spinner fa-spin fa-3x w-100"></span>
		    </div>
		</div>
		
        <!-- <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4" style="overflow-y: scroll;height:1500px"> -->
        <main role="main" class="col-md-12" style="overflow-y: scroll;height:1500px">
          <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
            <h1 class="h2">Search Results</h1>                   
          </div>
          
           
 <div class="container">
        <form method="POST" action="printParam">
            <div id="ex2" style="display:none;">
                <div class="row justify-content-md-start">
                    <div class="col col-6">
                        <label for="basic-url">
                            <h4>Print Options</h4>
                        </label>
                        <div class="input-group mb-2">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon3">Start Print From Position:</span>
                            </div>
                            <input type="number" class="form-control" id="startPrintFrom" min="1" max="10" value="1">
                            <div class="input-group-append">
                                <!-- <button class="btn btn-outline-secondary updateSelection" type="button">Change Start Position</button> -->
                                <input type="button" class="updateSelection btn btn-outline-secondary" value="Change Start Position" />
                            </div>
                        </div>
                    </div>
                </div>
                
				<div class="row justify-content-md-start">
                    <div class="col col-6">
                        <div class="input-group mb-2">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">Logo Link: </span>
                            </div>
                            <input type="text" class="form-control" id="logoLink" name="logoLink" value="https://pbs.twimg.com/profile_images/378800000169582286/7807cb00b75c135f624cc3b6604f43d1_400x400.png">
                        	<div class="input-group-append">
                                <input type="button" class="btn btn-outline-secondary" onclick="window.open(document.getElementById('logoLink').value, '_blank')" value="View Logo" />
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="row justify-content-md-start">
                    <div class="col col-6">
                        <div class="input-group mb-4">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">Label: </span>
                            </div>
                            <input type="text" class="form-control" id="textLabel" name="textLabel" value="PERPUSTAKAAN AWAM NEGERI TERENGGANU">
                        </div>
                    </div>
                </div>
            </div>

            <div id="paramTable" style="display:none;">

                <div class="form-group row justify-content-start">
                    <div class="col col-10">
                        <label for="basic-url">
                            <h4>Print Parameters (10 Book ID)</h4>
                        </label>
                        <div class="input-group mb-1">
                            <div class="input-group-prepend">
                            </div>
                        </div>
                    </div>
                    <div class="col col-md-5">
                        <div class="input-group mb-1">
                            <div class="input-group-prepend">
                                <span class="input-group-text">#01</span>
                            </div>
                            <input type="text" class="form-control" id="table1" name="table1">
                        </div>
                    </div>
                    <div class="col col-md-5">
                        <div class="input-group mb-1">
                            <div class="input-group-prepend">
                                <span class="input-group-text">#02</span>
                            </div>
                            <input type="text" class="form-control" id="table2" name="table2">
                        </div>
                    </div>
                    <div class="col col-md-5">
                        <div class="input-group mb-1">
                            <div class="input-group-prepend">
                                <span class="input-group-text">#03</span>
                            </div>
                            <input type="text" class="form-control" id="table3" name="table3">
                        </div>
                    </div>
                    <div class="col col-md-5">
                        <div class="input-group mb-1">
                            <div class="input-group-prepend">
                                <span class="input-group-text">#04</span>
                            </div>
                            <input type="text" class="form-control" id="table4" name="table4">
                        </div>
                    </div>
                    <div class="col col-md-5">
                        <div class="input-group mb-1">
                            <div class="input-group-prepend">
                                <span class="input-group-text">#05</span>
                            </div>
                            <input type="text" class="form-control" id="table5" name="table5">
                        </div>
                    </div>
                    <div class="col col-md-5">
                        <div class="input-group mb-1">
                            <div class="input-group-prepend">
                                <span class="input-group-text">#06</span>
                            </div>
                            <input type="text" class="form-control" id="table6" name="table6">
                        </div>
                    </div>
                    <div class="col col-md-5">
                        <div class="input-group mb-1">
                            <div class="input-group-prepend">
                                <span class="input-group-text">#07</span>
                            </div>
                            <input type="text" class="form-control" id="table7" name="table7">
                        </div>
                    </div>
                    <div class="col col-md-5">
                        <div class="input-group mb-1">
                            <div class="input-group-prepend">
                                <span class="input-group-text">#08</span>
                            </div>
                            <input type="text" class="form-control" id="table8" name="table8">
                        </div>
                    </div>
                    <div class="col col-md-5">
                        <div class="input-group mb-1">
                            <div class="input-group-prepend">
                                <span class="input-group-text">#09</span>
                            </div>
                            <input type="text" class="form-control" id="table9" name="table9">
                        </div>
                    </div>
                    <div class="col col-md-5">
                        <div class="input-group mb-1">
                            <div class="input-group-prepend">
                                <span class="input-group-text">#10</span>
                            </div>
                            <input type="text" class="form-control" id="table10" name="table10">
                        </div>
                    </div>

                </div>

                <div class="form-group row justify-content-center">
                    <div class="col col-3">
                        <button type="button" onclick="clearInputField()" class="btn btn-outline-secondary">Clear Selection</button>
                        <input type="submit" value="Print Preview" class="btn btn-outline-primary" />
                    </div>
                </div>

            </div>
        </form>

        <div class="mt-3"></div>


        <form:form action="input" method="post">
            <div class="row mb-2">
<!--                     <h4>Search Result</h4> -->
            </div>

        </form:form>
    
        <div class="row">
            <table border="1" id="input" class="table responsive">
                <thead class="thead-dark">
                    <tr>
                        <th>#</th>
                        <th><input type="checkbox" class="selectAll" value="Select All"></th>
                        <th>AccessionNo</th>
                        <th>Copy No</th>
                        <th>Call No</th>
                        <th>Title</th>
                        <th>Item Category</th>
                        <th>SMD</th>
                        <th>Location</th>
                        <th>Control No</th>
                        <th>Accession Date</th>

                    </tr>
                </thead>
                <c:forEach var="input" items="${listRecords}">
                    <c:set var="accdate" value="${input.accessionDate}" />
                    <%
							    
								String str = (String)pageContext.getAttribute("accdate");
									String year = StringUtils.substring(str, 0, 4);
									String month = StringUtils.substring(str, 4, 6);
									String day = StringUtils.substring(str, 6, 8);
									String accdate = day+"/"+month+"/"+year;
									pageContext.setAttribute("accdate", accdate);

							%>
                    <tr>
                        <td></td>
                        <td></td>
                        <td>${input.accessionNo}</td>
                        <td>${input.copyNo}</td>
                        <td>${input.callNo}</td>
                        <td>${input.title}</td>
                        <td>${input.itemCategory}</td>
                        <td>${input.SMDdesc}</td>
                        <td>${input.location}</td>
                        <td>${input.controlNo}</td>
                        <td>${accdate}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
          
        </main>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/select2@4.0.12/dist/js/select2.min.js" defer></script>	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/select/1.3.0/js/dataTables.select.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>


	<!-- Modal Spinner -->
	<script>
	function modal(){
	       $('.modal').modal('show');
	       setTimeout(function () {
	       	$('.modal').modal('hide');
	       }, 20000);
	    }
	</script>
	
	<script>
		$(document).ready( function () {
			$('.select-list').select2();	    
		} );
	</script>
  
  	
    <script>

        $(document).ready(function() {


            var table = $('#input').DataTable({
                columnDefs: [{
                        "searchable": false,
                        "orderable": false,
                        "targets": 0,
                        "defaultContent": "0"
                    },
                    {
                        orderable: false,
                        className: 'select-checkbox',
                        targets: 1
                    }
                ],
                "deferRender": true,
                "processing": true,
                "order": [
                    [2, 'asc']
                ], //
                dom: 'Bfrtip',
                select: {
                    style: 'multi',
                    selector: 'td:nth-child(2)'
                },
                buttons: [{
                    text: 'Print Selected Values',
                    action: function() {

                        var count = table.rows({
                            selected: true
                        }).count();
                        var oData = table.rows({
                            selected: true
                        }).data();

                        var plainArray = oData.toArray();

                        document.getElementById('paramTable').scrollIntoView();

                        var startVal = document.getElementById("startPrintFrom").value;
                        document.getElementById("ex2").style.display = "block";
                        document.getElementById("paramTable").style.display = "block";
                        clearInputField();

                        for (var i = 0; i < oData.length; i++) {
                            if (i < 10) {
                                var a = parseInt(startVal) + parseInt(i);

                                document.getElementById("table" + a).value = oData[i][2];
                            }
                        }

                        $(document).on("click", ".updateSelection", function() {
                            var startVal = document.getElementById("startPrintFrom").value;
                            document.getElementById("paramTable").style.display = "block";
                            clearInputField();

                            for (var i = 0; i < oData.length; i++) {
                                if (i >= 10) {
                                    alert("Only 10 selections are allowed. Id: " + oData[i][2] + " is not added to the print list.");
                                } else {
                                    var a = parseInt(startVal) + parseInt(i);
                                    document.getElementById("table" + a).value = oData[i][2];
                                }
                            }
                        });
                    }
                }]
            });

            table.on('order.dt search.dt', function() {
                table.column(0, {
                    search: 'applied',
                    order: 'applied'
                }).nodes().each(function(cell, i) {
                    cell.innerHTML = i + 1;
                });
            }).draw();

            $(".selectAll").on("click", function(e) {
                if ($(this).is(":checked")) {
                    table.rows({
                        page: 'current'
                    }).select();
                } else {
                    table.rows({
                        page: 'current'
                    }).deselect();
                }
            });

          //Prevent Enter key from triggered
            $(".form-control").keypress(function(e) {           	  
            	  if (e.which == 13) {
            	    return false;
            	  }
            	});
            
        });


        function clearInputField() {
            for (var i = 0; i < 10; i++) {
                document.getElementById("table" + (i + 1)).value = '';
            }
        };
                    
    </script>
  
  
  </body>
</html>
