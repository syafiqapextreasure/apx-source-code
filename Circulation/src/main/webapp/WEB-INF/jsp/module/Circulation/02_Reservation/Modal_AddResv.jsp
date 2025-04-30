<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.foundation.global.*"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Reservation.js"></script>	
  <div class="modal-header">
     <button type="button" class="close" data-dismiss="modal" aria-label="Close">
   	 <span aria-hidden="true">&times;</span></button>
           <h4 class="modal-title">Patron Search</h4>
  </div>
   <%
     	String action = request.getParameter("action");
     %>
     <input type="hidden" id="action" value="<%=action%>">
	<div class="modal-body">
	<div class="padding-15" id="patronSearchPanel">
		<div class="row pwidth-100">
			<div class="col-xs-12">
				<div class="margin-btm-5"></div>

				<div class="form-horizontal">

					<div class="form-group">
						<label for="patronSearchMode" class="col-sm-2 control-label">Search</label>
						<div class="col-xs-10 col-sm-8 col-md-6 col-lg-4">
							<div class="input-group" id="patronSearchMode">
								<div class="input-group-btn">
									<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										<span class="text-value" data-span="patronId">ID</span> <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li value="patronId" text="ID" class="pointer"><a data-value="patronId" >ID</a></li>

										<li value="patronName" text="Name" class="pointer"><a data-value="patronName" >Name</a></li>

										<li value="patrnic" text="New IC" class="pointer"><a data-value="patrnic">New IC</a></li>

										<li value="patonic" text="Old IC" class="pointer"><a data-value="patonic">Old IC</a></li>
									</ul>
								</div>
								<input field-input="" class="form-control" id="patronSearchInput" option="Id" old-value="Id==" placeholder="ID" type="text">
								<span class="input-group-btn">
									<button class="btn btn-default" type="button" action="search" id="btn_submit">
										Search</button>
								</span>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="FilteredBy" class="col-sm-2 control-label">Filtered
							by</label>
						<div class="col-xs-10 col-sm-8 col-md-6 col-lg-4">
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
					</div>

					<div class="form-group">
						<label for="SortedBy" class="col-sm-2 control-label">Sorted
							By</label>
						<div class="col-xs-10 col-sm-8 col-md-6 col-lg-4">
							<select field-input="" class="form-control" id="patronSortedBy" name="SortedBy" default="Id">
								<option value="Id">ID</option>
								<option value="Name">Name</option>
							</select>

						</div>
					</div>
				</div>

			</div>
		</div>
		<div class="margin-btm-15"></div>
		<div class="row pwidth-100">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="patronListingContainer" action-url="/Acquisition/acquisition/patron/search-patron/submit-ajax" action-search-mode="Id" action-search-filtered-by="" action-search-sorted-by="Id" action-search-text="">

				<div class="panel panel-default">
					<div class="panel-heading">Patron Listing</div>
					<div class="panel-body">
						<div class="pwidth-100 overflow-auto float-left" style="min-height: 378px;">
							<div class="table-responsive" id="display_vendor">
								<table class=" table table-striped table-hover table-condensed">
									<thead>
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th width="130px">Actions</th>
										</tr>
									</thead>
									<tbody class="font-size-13">
										<tr>
										</tr><tr class="">
											<td colspan="6">
												<div class="padding-5">
													<i class="glyphicon glyphicon-exclamation-sign"></i> No
													data found.
												</div>
											</td>
										</tr><tr>
										</tr><tr>
									</tr></tbody>
								</table>
							</div>

						</div>
					</div>
					<div class="panel-footer">
						<div paging-pagination-entry="patronListingContainer" class="paging"></div>

						<div paging-pagination="patronListingContainer" class="paging"></div>
					</div>
				</div>

			</div>
		</div>
		<div class="float-right margin-top-15">
			<button type="button" class="btn btn-info" data-dismiss="modal">
				<span aria-hidden="true"> <i class="fa fa-close"></i> Cancel
				</span>
			</button>
		</div>

	</div>
		<%-- <div class="panel panel-default" id="form_parent">
			<div class="panel-group">
			
				<div class="panel panel-default">
			<div class="panel-heading">Search Patron</div>
			<div class="panel-body">

				<form id="advanceSearchPatronForm" class="form-horizontal"
					method="POST">

					<div class="form-group">
						<label for="Description" class="col-sm-2 control-label">Search
							Text</label>
						<div class="col-sm-10">
							<input field-input type="text" class="form-control"
								id="SearchText" name="SearchText" placeholder="Search">

						</div>
					</div>


					<div class="form-group">
						<label for="Code" class="col-sm-2 control-label">Search By</label>
						<div class="col-sm-10">
							<select field-input class="form-control" id="SearchBy"
								name="SearchBy" default="Id">
								<option value="Id">Patron ID</option>
								<option value="Name">Name</option>
								<option value="NewIc">New IC</option>
								<option value="OldIc">Old IC</option>
							</select>

						</div>
					</div>

					<div class="form-group">
						<label for="Code" class="col-sm-2 control-label">Filtered
							By</label>
						<div class="col-sm-10">
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
					</div>

					<div class="form-group">
						<label for="Code" class="col-sm-2 control-label">Sorted By</label>
						<div class="col-sm-10">
							<select field-input class="form-control" id="SortedBy"
								name="SortedBy" default="Id">
								<option value="Id">ID</option>
								<option value="Name">Name</option>
							</select>

						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">
								<i class="fa fa-search"></i> Search
							</button>
						</div>
					</div>
				</form>
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
		</div> --%>
	</div>
	