<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.kmlink.ilmu.shared.foundation.global.*"%>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script> 
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/general/patronSearch.js"></script> --%>
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
						<div class="col-xs-10 col-sm-6 col-md-6 col-lg-6">
							<div class="input-group" id="patronSearchMode">
								<div class="input-group-btn search-panel">
									<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
				                    	<span class="text-value" data-span="patronId">ID</span> <span class="caret"></span>
				                    </button>
				                    <ul class="dropdown-menu" role="menu">
				                     	<li value="patronId" text="ID" class="pointer"><a data-value="patronId" >ID</a></li>
										<li value="patronName" text="Name" class="pointer"><a data-value="patronName" >Name</a></li>
										<li value="patrnic" text="New IC" class="pointer"><a data-value="patrnic">New IC</a></li>
										<li value="patonic" text="Old IC" class="pointer"><a data-value="patonic">Old IC</a></li>
				                    </ul>
								</div>
								<input field-input="" class="form-control" id="patronSearchInput" option="Id" old-value="Id==" type="text">
								<span class="input-group-btn">
									<button class="btn btn-primary btn-md" id="btn_submit" title="Search">
										<i class="glyphicon glyphicon-search"></i>&nbsp;
									</button>
								</span>
							<!-- 	<span class="input-group-btn">
									<button class="btn btn-primary" type="button" action="search" id="btn_submit" title="Search">
										<span class="glyphicon glyphicon-search"></span></button>
								</span> -->
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
										</tr><tr class="tables">
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
			<button type="button" class="btn btn-default" data-dismiss="modal">
				<span aria-hidden="true"> <i class="fa fa-close"></i> Close
				</span>
			</button>
		</div>

	</div>
	</div>
	