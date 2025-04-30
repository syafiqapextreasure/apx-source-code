<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%>
<%@ page
	import="com.ilmu.global.connection.DBConnection, com.ilmu.foundation.PatronDetails.PatronSearch, java.util.List"%>
<%
	GlobalSQLStatement sql = new GlobalSQLStatement();
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title">Search Criteria</h4>
</div>

<div class="modal-body">
	<div class="panel panel-default" id="form_parent">
		<div class="panel-group">

			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent"
							href="#search_vendor" id="search">Search</a>
					</h4>
				</div>
				<div id="search_vendor" class="panel-collapse collapse in">
					<div class="panel-body">
						<form role="form" class="form-horizontal" id="current_form"
							name="current_form" onsubmit="return send_vendor_info()">

							<div class="form-group">
								<div class="radio">
									<div class="col-sm-5 col-md-5">
										<label> <input type="radio" class="minimal"
											name="criteriaOptions" id="category" value="cate" checked>
											Patron Category Code
										</label>
									</div>
									<div class="col-sm-5 col-md-5">
										<label> <input type="radio" class="minimal"
											name="criteriaOptions" id="item" value="icate"> Item
											Category Code
										</label>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-5 col-md-5">
										<label> <input type="radio" class="minimal"
											name="criteriaOptions" id="smd" value="smd"> SMD Code
										</label>
									</div>
									<div class="col-sm-5 col-md-5">
										<label> <input type="radio" class="minimal"
											name="criteriaOptions" id="branch" value="branch">
											Branch
										</label>
									</div>
								</div>
							</div>
							<div class="panel-group">
								<div class="panel panel-default search-panel">
									<div class="panel-heading">
										<strong>Patron Category Code</strong>
									</div>
									<div class="panel-body search">
										<table class="table">
											<tbody>
												<%
													List<Foundation> catlist = sql.getCate();

													for (Foundation e : catlist) {
												%>
												<tr>
													<td width="5%"><label><input type="checkbox"
															class="categories" value="<%=e.getGL07CATE()%>"></label></td>
													<td><%=e.getGL07CATE()%></td>
													<td><%=e.getGL07DESC()%></td>
												</tr>
												<%
													}
												%>
											</tbody>
										</table>
									</div>
								</div>
							</div>

							<div class="clearfix"></div>

							<!-- 	<div class="form-group">
								<div class="col-sm-5 col-md-5">
									<textarea rows="5" cols="90" id="criteria"></textarea>
								</div>
							</div> -->

							<div class="form-group">
								<div class="col-sm-4 col-md-3"></div>
								<div class="col-sm-8 col-md-8">
									<button type="submit" class="btn btn-info" id="btn_submit"
										data-dismiss="modal">
										<span class="glyphicon glyphicon-search"></span> Find
									</button>
									<input type="button" class="btn btn-default"
										style="width: 70px" value="Reset" onclick="resetForm()"
										id="btn-resetForm"> <input type="button" name="cancel"
										value="Cancel" class="btn btn-default" data-dismiss="modal" />
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
<!-- http://stackoverflow.com/questions/24639335/javascript-console-log-causes-error-synchronous-xmlhttprequest-on-the-main-thr -->
	$.getScript("<%=request.getContextPath()%>/resources/js/search-elig.js");
	$.getScript("<%=request.getContextPath()%>/resources/js/patron-elig.js");
</script>
