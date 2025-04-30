<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%>
<%@ page import="com.ilmu.global.connection.DBConnection, com.ilmu.foundation.CodeTable.*, java.util.List"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/CodeTable.js"></script>

	<%
		String CODE = request.getParameter("CODE");
		String FCODE = request.getParameter("FCODE");
		String titlecategory = request.getParameter("value");
		SQLStatement eb = new SQLStatement();
		Foundation e = eb.getCodeById(CODE, titlecategory);
	%>

<%-- <% ResultSet resultset =null;%>
<% Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); %>

<% 
    		
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://dev.paradigm.com.my;databaseName=ILMUWEB", "ilmuweb", "ilmuweb"); 
			Statement statement = connection.createStatement();
			String titlecategory = request.getParameter("value");   
			resultset = statement.executeQuery("SELECT FNDSYSTEM.FCODE, FNDSYSTEM.FNDNAME, FNDSYSTEM.FCOLUMN1, FNDSYSTEM.FCOLUMN2, FNDCODE.FNAME FROM FNDSYSTEM INNER JOIN FNDCODE ON FNDSYSTEM.FCODE=FNDCODE.FCODE WHERE FNDSYSTEM.FCODE='" + titlecategory + "'") ; 
			
            if(resultset.next()) {
%> --%>
	
		<%
			String value= request.getParameter("value");
			SQLStatement eb2 = new SQLStatement();
			List<Foundation> list = eb2.getAllCodeStat(value);
			System.out.println(list + " list");
			for (Foundation a : list) {
		%>
	
	<script>
	
	
	
	
	// Update placeholder for vendor after changes in select
    function updatePlaceholder() {
    	//document.getElementById('criteria').placeholder = document.getElementById('search-type').text;
    	$("#criteria").attr("placeholder", $("#search-type option:selected").text());
    	//document.getElementById('criteria'). = "";
    	$("#criteria").val("");
    }
	
	function getValue(cate_type){
		
		var str = cate_type.options[cate_type.selectedIndex].getAttribute('value');
		var e = document.getElementById("cate_type");
		var strUser = e.options[e.selectedIndex].value;
		document.getElementById("cate-id").value=strUser;
		
		
 	}
	function resetForm() {
	    document.getElementById("current_form").reset();
	}
	
	// Send vendor info
	function send_vendor_info() {	
		// Hide the search form
		$('#search_vendor').collapse("hide");
		// Show the result form
		$('#result_vendor').collapse("show").height("auto");
		return false;
	}
	
	// Search vendor result from SearchVendor.jsp
	$(document).ready(function(){
		//Div collapse
		$("#search").click(function(){
			$("#search_vendor").collapse("show");
			$("#result_vendor").collapse("hide");
		});
		
		$("#result").click(function(){
			$("#search_vendor").collapse("hide");
			$("#result_vendor").collapse("show");
		});
		
		$('#btn_submit').click(function(){
			//alert("test");
			var vendor_details = $('#criteria').val();
			var cate_id = $('#cate-id').val();
			var fcode = $('#FCODE').val();
			var fname = $('#FNAME').val();
			
			if (document.getElementById('code').checked) {
				var searchType = "CODE";
			}else if (document.getElementById('description').checked) {
				var searchType = "DESCRIPTION";
			}
			
			
			//alert(vendor_details);
			//alert(searchType);
			$.get("CodeSearch?value=<%=titlecategory%>",{criteria:vendor_details,searchType:searchType,cate_id:cate_id, fcode:fcode, fname:fname},function(data_vendor){
				//alert("TEST 1");

				$("#display_vendor").html(data_vendor);
			   });
		})
	});
	</script>

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" align="center">Search Criteria</h4>
                    </div>

	<div class="modal-body">
		<div class="panel panel-default" id="form_parent">
			<div class="panel-group">
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#search_vendor" id="search">Search</a>
						</h4>
					</div>
					<div id="search_vendor" class="panel-collapse collapse in">
						<div class="panel-body">
							<form role="form" class="form-horizontal" id="current_form" name="current_form" onsubmit="return send_vendor_info()">
							
							<div class="form-group">
                   				<input type="hidden" class="form-control" id="FCODE" name="FCODE" value="<%=a.getFCODE()%>">
                    			<input type="hidden" class="form-control" id="FNAME" name="FNAME" value="<%=a.getFNDNAME()%>">
                     
                			</div>
                
                
            				<div class="form-group">
            				<div class="radio">
            					<div class="col-sm-5 col-md-5">
  								<label>
    							<input type="radio" class="minimal" name="choice" id="code"  checked>
    					 		<%=a.getFCOLUMN1()%>
  								</label>
  								</div>
  								<div class="col-sm-5 col-md-5">
  								<label>
    							<input type="radio" class="minimal" name="choice" id="description" >
    					 		<%=a.getFCOLUMN2()%>
  								</label>
					  			</div>
					  			<div class="clearfix"></div>
					  			
					  			</div>
					  		</div>
            				
            				<div class="clearfix"></div>
            				
            				<%
								}
							%>
            				<div class="form-group">
            					<div class="col-sm-5 col-md-5">
            					<!-- <input type="text" class="form-control" name="criteria" id="criteria"> -->
            						<textarea rows="5" cols="90" id="criteria" name="criteria"></textarea>
								</div>
							</div>
                            
							<div class="form-group">
								<div class="col-sm-4 col-md-3"></div>
								<div class="col-sm-8 col-md-8">
								<button type="button" class="btn btn-info" id="btn_submit" onclick="send()" data-dismiss="modal">
										<span class="glyphicon glyphicon-search"></span> Find
								</button>
								<input type="button" class="btn btn-default" style="width: 70px" value="Reset" onclick="resetForm()" id="btn-resetForm">
								<input type="button" name="cancel" value="Cancel" class="btn btn-default" data-dismiss="modal"/>
							</div>
							</div>
							</form>
						</div>
					</div>
				</div>
			
				
				
			</div>
		</div>
	</div>
	
	