	<%@ page import="com.wilmu.global.patron.PatronSearch, java.util.List"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/patronSearch.js"></script>	

	<table class="table table-hover patr_result"  data-toggle="table" id="patr_result" >
	<%
		String action = request.getParameter("action");

		if(!action.equals("discharge")){
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
		System.out.println("sorttt " +sort);
		String patrid="";

		List<PatronSearch> patronResult = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}else
		{
			patronResult = PatronSearch.getpatrModalSearch(criteria, searchType, cate_id, sort);
		%>
		
		<%
			System.out.println(action + "kkjj");
			
		%>
		
		<%
			for(PatronSearch i: patronResult){
		%>
			<tr value="<%= i.getId() %>" class="patrid">
				<td>
					<%= i.getId() %>
					<input id="GL14PATR" type="hidden" value="<%= i.getId()%>">
				</td>
				<td><%= i.getName() %></td>
				<td>
					<button title="" onclick="appendData('<%= i.getId()%>','<%= i.getName()%>', '<%= action%>')" class="btn btn-sm btn-default" data-original-title="Select" data-dismiss="modal">
						<i class="fa fa-check"></i> Select
					</button>
				</td>
			</tr>
		<%
			}
		}
		%>
		</tbody>
	</table>