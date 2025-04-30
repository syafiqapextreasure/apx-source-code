<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.foundation.global.*"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
  <div class="modal-header">
     <button type="button" class="close" data-dismiss="modal" aria-label="Close">
   	 <span aria-hidden="true">&times;</span></button>
           <h4 class="modal-title">Patron Search</h4>
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
                				<label class="col-sm-3 control-label">Search By</label>
                				<div class="col-sm-5 col-md-5">
                				<select class="form-control" id="search-type" name="search-type" onchange="updatePlaceholder()">
  								<option value="select">Please Select</option>
 								<option value="patronId">Patron Id</option>
  								<option value="patronName">Name</option>
  								<option>New IC</option>
  								<option>Old IC</option>
								</select>
                				</div>
            				</div>
            				
            				<div class="clearfix"></div>
            				
            				<div class="form-group">
                				<label class="col-sm-3 control-label">Search Text</label>
                				<div class="col-sm-5 col-md-5">
                    				<input type="text" class="form-control" name="criteria" id="patronText" placeholder="Please Select">
                				</div>
            				</div>
            				
            				<div class="form-group">
                                <label class="col-sm-3 control-label">Filtered By</label>
                                <div class="col-sm-5 col-md-5">
                                     <select class="form-control" id="cate_type" name=" cate_type" onchange="getValue(this)">
                                     <option value="0">Please Select</option>
                                   <%
											SQLStatement cat = new SQLStatement();
											List<Foundation> tpllist = cat.getCategory();
											for (Foundation e : tpllist) {
										%>
										<option value="<%=e.getGL07CATE()%>"><%=e.getGL07DESC()%></option>
										<%
									}
								%> 
									 </select>
                                </div>
                         <!--        <div class="col-sm-5 col-md-2">
                                      <input type="text" class="form-control" id="cate-id" name="cate-id"  readonly>
                                </div>
                                 -->
                            </div>
                      <!--     	<div class="form-group">
								<label class="col-sm-3 control-label">Date Range:</label>
								<div class="col-sm-6">
									<div class="input-daterange input-group date" id="orderDate">
										<input type="text" class="form-control" readonly id="startDateInput"/>
										<span class="input-group-addon">to</span>
										<input type="text" class="form-control" readonly id="stopDateInput"/>
									</div>
								</div>
							</div> -->
							<div class="form-group">
								<div class="col-sm-4 col-md-3"></div>
								<div class="col-sm-8 col-md-8">
								<button type="button" class="btn btn-info" id="btn_submit" onclick="send()">
										<span class="glyphicon glyphicon-search"></span> Search
								</button>
								<input type="button" class="btn btn-default" style="width: 70px" value="Reset" onclick="resetForm()" id="btn-resetForm">
								<input type="button" name="cancel" value="Cancel" class="btn btn-default" data-dismiss="modal"/>
							</div>
							</div>
							</form>
						</div>
					</div>
				</div>
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#result_vendor" id="result">Result</a>
						</h4>
					</div>
					<div id="result_vendor" class="panel-collapse collapse">
						<div class="panel-body">
							<div id="display_vendor"  style="overflow:scroll; height:300px"></div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	