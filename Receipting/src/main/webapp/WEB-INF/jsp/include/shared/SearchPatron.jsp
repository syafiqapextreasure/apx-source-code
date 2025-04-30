	<%@ page contentType="text/html; charset=UTF-8" %>
	<%@ page import="com.ilmu.global.*, java.util.List"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	

	<table class="table table-hover table"  data-toggle="table" id="patr_result" data-click-to-select="true" >
	<%
		String action = request.getParameter("action");
		if(!action.equals("discharge")&&!action.equals("renewal")){
	%>
		<thead>
			<tr>
				<td>
					ID
				</td>
				<td>
					Name
				</td>
				<td>
					Action
				</td>
			</tr>
		</thead>
		<%
		}
		%>
		<tbody>
		<% 
		String searchType = request.getParameter("search_type");
		String criteria = request.getParameter("criteria");
		String cate_type = request.getParameter("cate_type");
		String cate_id = request.getParameter("cate_id");
		String sort = request.getParameter("sort");
		String patrid="";

		List<Patron> vendorResult = null;
		List<Patron> retrieveItem = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}else
		{
			if(action.equals("discharge") || action.equals("renewal")){
				vendorResult = Patron.retrievePatron(criteria, searchType, cate_id, sort);
				retrieveItem = Patron.retrieveDischarging(criteria, searchType, cate_id);
			}else{
				vendorResult = Patron.retrievePatron(criteria, searchType, cate_id, sort);
			}
		%>
		
		<%
			if(action.equals("discharge")||action.equals("renewal")){
				for(Patron i: vendorResult){
		%>
				<tr>
					<td colspan="2">
						<%= i.getGL66DESC() %>,<%= i.getGL65VALUE() %>
						<input type="hidden" class="GL14PATR" value="<%= i.getGL66DESC() %>">
					</td>
				</tr>
		<%
				patrid = i.getGL66DESC() ;
				}for(Patron i: retrieveItem){
		%>
				<tr>
					<td><%= i.getGL66DESC() %></td>
					<td><%= i.getGL65VALUE() %></td>
					
					<%
						if(action.equals("discharge")){
					%>
					<td><button title="" onclick="getDischargeItem('<%=patrid %>,<%= i.getGL66DESC() %>')" class="btn btn-sm btn-default" data-original-title="Select" data-dismiss="modal">
						<i class="fa fa-check"></i> Select
					</button></td>
					<%
						}else{
							%>
					<td><button title="" onclick="getRenewalItem('<%=patrid %>,<%= i.getGL66DESC() %>')" class="btn btn-sm btn-default" data-original-title="Select" data-dismiss="modal">
					<i class="fa fa-check"></i> Select
					</button></td>
					<%
						}
					%>
				</tr>
		<%
				}
			}else{
		%>
		<%
			for(Patron i: vendorResult){
		%>
			<tr value="<%= i.getGL66DESC()%>" class="patrid" data-dismiss="modal">
				<td>
					<%= i.getGL66DESC() %>
					<input id="GL14PATR" type="hidden" value="<%= i.getGL66DESC()%>">
				</td>
				<td><%= i.getGL65VALUE() %></td>
				<td>
					<%
						if(action.equals("addResv")){
					%>
					<button title="" onclick="getPatronReceipt('<%= i.getGL66DESC()%>')" class="btn btn-sm btn-default" data-original-title="Select" data-dismiss="modal">
						<i class="fa fa-check"></i> Select
					</button>
					<%
						}else if(action.equals("charge")){
					%>
					<button title="" onclick="appendData('<%= i.getGL66DESC()%>')" class="btn btn-sm btn-default" data-original-title="Select">
						<i class="fa fa-check"></i> Select
					</button>
					<%
						}
					%>
				</td>
			</tr>
		<%
			}
			}
		}
		%>
		</tbody>
	</table>