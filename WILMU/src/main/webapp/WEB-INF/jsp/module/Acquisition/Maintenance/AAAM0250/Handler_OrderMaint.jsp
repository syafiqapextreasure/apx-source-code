<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.wilmu.acquisition.global.*,
				 java.util.List.*,
				 java.util.*"%>
	

<html>
	<head>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/delete.js"></script>
		<!-- <script>
			$(document).ready(function(){
				
			});
		</script> -->
	</head>
	<body>
		<table id="OdrMaint2" class="table table-bordered table-striped" style="width:100%">
			<thead>
				<tr>
					<th>Order No.</th>
					<th>Control No.</th>
					<th width="40%">Title</th>
					<th>Vendor</th>
					<!-- <th>Id Status</th> -->
					<th>Status</th>
					<th>Reference</th>
					<th>Order Date</th>
					<th>Amount</th>
					<th>Action </th>
				</tr>
			</thead>
			<tbody>
				<% 
					System.out.println("Start Seaarch Order Maint");
				
					String getChecked = request.getParameter("getChecked");
					System.out.println("getChecked : " + getChecked);
					
					String getCriteria = request.getParameter("getCriteria");
					System.out.println("getCriteria : " + getCriteria);
					
					String newGetCriteria = getCriteria.replaceAll("\\-", " ");
					System.out.println("newGetCriteria : " + newGetCriteria);
					
					String getSearch = request.getParameter("getSearch");
					System.out.println("getSearch : " + getSearch);
					
					String typeSearch = request.getParameter("typeSearch");
					System.out.println("typeSearch : " + typeSearch);  
					
					String getAcq = request.getParameter("getAcq");
					System.out.println("getAcq : " + getAcq);
					
					String inputStartDate = request.getParameter("inputStartDate");
					/* 
					
					if(inputStartDate.equals("Invalid date")){
						inputStartDate = "";
					} */
					
					System.out.println("inputStartDate : " + inputStartDate);
					
					String inputEndDate = request.getParameter("inputEndDate");
					System.out.println("inputEndDate : " + inputEndDate);
					
					System.out.println(getSearch == "");
					
					List<GetAcqSearch> search2 = null;
					
					if(getSearch != ""&&getSearch !=null){
						System.out.println("getSearch not null");
						if(getSearch.equals("title")){
							search2 = GetAcqSearch.searchByTitle(newGetCriteria, typeSearch, getSearch);
						}else if (getSearch.equals("name")){
							search2 = GetAcqSearch.searchByName(newGetCriteria, typeSearch, getSearch);
						}else if (getSearch.equals("subject")){
							search2 = GetAcqSearch.searchBySubject(newGetCriteria, typeSearch, getSearch);
						}else if(getSearch.equals("isbn")){
							search2 = GetAcqSearch.searchByIndx(newGetCriteria, typeSearch, getSearch);
						}else if(getSearch.equals("issn")){
							search2 = GetAcqSearch.searchByIndx(newGetCriteria, typeSearch, getSearch);
						}else if(getSearch.equals("publisher")){
							search2 = GetAcqSearch.searchByPubl(newGetCriteria, typeSearch, getSearch);
						}
					}else if(getAcq != ""&&getAcq !=null){
						/* if(getAcq == "reviewlistno"){
							System.out.println("reviewlistno");
						}else{
							System.out.println("getAcq not null"); */
							search2 = GetAcqSearch.getAllBy2(getChecked, newGetCriteria, typeSearch, getAcq, inputStartDate, inputEndDate);
					}else{
						if(getChecked.equals("controlNo")){
							search2 =GetAcqSearch.getAllBy3(getChecked, newGetCriteria, typeSearch);
							System.out.println("controlNo here");
						}else if(getChecked.equals("accno")){
							search2 =GetAcqSearch.getAllBy3(getChecked, newGetCriteria, typeSearch);
							System.out.println("accno here");
						}
						
					}
					////////System.out.println("DISPLAYYYYYYY");
					for(GetAcqSearch getSearchOdrMaint: search2){
						///////System.out.println("resultttttttttt : " +getSearchOdrMaint.getstatusdesc());  
				%>
				<tr>
					<td><%=getSearchOdrMaint.getorder()%></td>
					<td><%=getSearchOdrMaint.getctrlno()%></td>
					<td><%=getSearchOdrMaint.getTitle()%></td>
					<td><%=getSearchOdrMaint.getvenddesc()%></td>
					<%-- <td><%=getSearchOdrMaint.getstatus()%></td> --%>
					<td><%=getSearchOdrMaint.getstatusdesc()%></td>
					<td><%=getSearchOdrMaint.getrefno()%></td>
					<td><%=getSearchOdrMaint.getordate()%></td> 
					<td><%=getSearchOdrMaint.getamount()%></td> 
					<td class="last">
						<button id="View" class="btn btn-primary btn-sm" data-toggle='modal' data-target="#modalOdrMaint" data-mode="3" title="View Order" data-rowid="<%=getSearchOdrMaint.getorder()%>"><span class="glyphicon glyphicon-eye-open"></span></button>
						<button id="Edit" class="btn btn-info btn-sm Edit" data-toggle='modal' data-status="<%=getSearchOdrMaint.getstatusdesc()%>" data-target="#modalOdrMaint" data-mode="2" title="Edit Order" data-rowid="<%=getSearchOdrMaint.getorder()%>"><span class="glyphicon glyphicon-pencil"></span></button>
		   				<button id="Delete" class="btn btn-dull btn-sm DeleteOrder" data-OrderNo="<%=getSearchOdrMaint.getorder()%>" <%-- data-Status="<%=getSearch.getStatus()%>" --%>><span class="glyphicon glyphicon-trash" title="Delete Oder" ></span></button>
					</td>
				</tr>
				<%
					}
					System.out.println("ENDDDD"); 
				%>
			</tbody>
		</table>
			
	</body>
</html>


