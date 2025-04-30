<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap, 
	com.ilmu.acquisition.orderManagement.detailClaim"%>
	
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/SearchOrder.js"></script>	
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

<!-- <style>
	.big-col {
  		width: 400px !important;
	}
</style> -->

<%
	// Prepare the variables
	String btnClick = request.getParameter("btnClick");
	System.out.println(btnClick + " btnClick");	

	String vendorCode = request.getParameter("vendorCode");
	System.out.println(vendorCode + " vendorCode");
	
	String orderNo = request.getParameter("orderNo");
	
	String referenceNo = request.getParameter("referenceNo");
	
	String startDate = request.getParameter("startDate");
	
	String endDate = request.getParameter("endDate");
%>

<%
	// Perform Search Execution

	HashMap<String, String> attributes = new HashMap<String, String>();

	if (vendorCode != null)
		attributes.put("vendorCode", vendorCode);
	if (orderNo != null)
		attributes.put("orderNo", orderNo);
	if (referenceNo != null)
		attributes.put("referenceNo", referenceNo);
	if (startDate != null)
		attributes.put("startDate", startDate);
	if (endDate != null)
		attributes.put("endDate", endDate);

	List<detailClaim> orderList = detailClaim.getSearchDetailClaim(btnClick, attributes);
%>

<%
	if (orderList.size() == 0) {
		out.print("<p>There is no data based on the search criteria!</p>");
	} else {
		
%>
<%
	if(orderList.size() != 0) {
%>


<%
	}
%>

	<div align="right" class="btn-group">
		<span class="text-nowrap">
			
			<button type="button" data-placement="bottom" class="button-vendorFeedback btn btn-info" id="vendorFeedback" data-toggle='modal' data-target="#vendorFeedbackSearch"> 
        		<i class="glyphicon glyphicon-user" title="Vendor Feedback"></i>
        	</button>
        	
        	<button type="button" class="button-errorInSupply btn btn-warning" id="errorInSupply" data-toggle='modal' data-target="#ErrorInSupplySearch"> 
        		<i class="glyphicon glyphicon-exclamation-sign" title="Error In Supply"></i>
        	</button>
        	
        	<button type="button" class="button-claim btn btn-success" id="Claim" data-toggle='modal'> 
        		<i class="glyphicon glyphicon-envelope" title="Claim"></i>
        	</button>
        	
        	<button type="button" class="button-cancellation btn btn-danger" id="Cancellation " data-toggle='modal'> 
        		<i class="glyphicon glyphicon-remove-sign" title="Cancellation"></i>
        	</button>
		</span>
	</div> 

	<input type="hidden" id="btnClick" value="<%=btnClick%>">
	
 	<table id="tableOrder" class="table table-bordered table-striped">
		<thead>
			<tr>
				<th><input name="select_all" value="1" id="example-select-all" type="checkbox" /></th>
				<!-- <th class="big-col"></th> -->
				<th>Order No</th>
				<th>Vendor Feedback</th>
				<th>Ref. No</th>
				<th>Vendor</th>
				<th>Order Date</th>
				<th>Title</th>
				<th>Order Type</th>
				<th>Order Status</th>
				<th>Invoice Status</th>
				<th>Copies Sets</th>
				<th>Received</th>
			</tr>
		</thead>
		<tbody>
	
<%
		for(detailClaim o : orderList) {
			
			int iCopies = Integer.parseInt(o.getcopies());
			int iSet = Integer.parseInt(o.getsets());
			String iOrderQty, sReceived;
			
			if( iSet > 0 ){
				int iReceived = Integer.parseInt(o.getsetreceived());
				iOrderQty = Integer.toString(iSet);
				sReceived = Integer.toString(iReceived);
				
				if(iSet>1){
					iOrderQty = iSet + " sets";
				}else{
					iOrderQty = iSet + " set";
				}
				
				if(iReceived > 1 ){
					sReceived = iReceived + " sets";
				}else{
					sReceived = iReceived + " set";
				}
			}else{
				int iReceived = Integer.parseInt(o.getreceived());
				iOrderQty = Integer.toString(iCopies);
				sReceived = Integer.toString(iReceived);
				
				if(iCopies > 1){
					iOrderQty = iCopies + " copies";
				}else{
					iOrderQty = iCopies + " copy";
				}
				
				if(iReceived > 1){
					sReceived = iReceived + " copies";
				}else{
					sReceived = iReceived + " copy";
				}
			}
			
			int sStatus = Integer.parseInt(o.getodertype());
			String sCategory = null;
			if(sStatus >= 80){
				sCategory = "Blanket Order";
			}else if(sStatus >= 60){
				sCategory = "Gift";
			}else if(sStatus >= 40){
				sCategory = "Profoma Order";
			}else if(sStatus >= 20){
				sCategory = "Local Order";
			}else{
				sCategory = "";
			}
			
	
			/* String set, received = null;
			
			if(o.getsets().equals("0")){
				int getcopies = Integer.parseInt(o.getcopies());
				if(getcopies > 1 ){
					set = getcopies + " copies";
				}else {
					set = getcopies + " copy";
				}
				
				int getreceived = Integer.parseInt(o.getreceived());
				if(getreceived > 1 ){
					received = getreceived + " copies";
				}else {
					received = getreceived + " copy";
				}
			}else {
				System.out.println(o.getorderNo() + " o.getorderNo()");
				System.out.println(o.getsets() + " o.getsets()");
				System.out.println(o.getsetreceived() + " o.getsetreceived()");
				int getsets = Integer.parseInt(o.getsets());
				if(getsets > 1 ){
					set = getsets + " sets";
				}else {
					set = getsets + " set";
				}
		
				int getsetreceived = Integer.parseInt(o.getsetreceived());
				if(getsetreceived > 1 ){
					received = o.getsetreceived() + " sets";
				}else {
					received = o.getsetreceived() + " set";
				}   
			}
			System.out.println(set); */
			
%>
			<tr>
				<%-- <td><i order-select-icon class="padding-5 fa fa-fw fa-square-o"  order-number="<%= o.getorderNo() %>" vendor="<%= o.getvendorCode()%>"></i></td> --%>
				<td><input type="checkbox" id="example-select-all" name="id[]" order-number="<%= o.getorderNo() %>" vendor="<%= o.getvendorCode()%>"></td>
				<td class="orderno"><%= o.getorderNo() %></td>
				<td><%= o.getfeedstat() %></td>
				<td><%= o.getreferenceNo() %></td>
				<td><%= o.getvendorName() %>
				<input type="hidden" class="vendorCode" value="<%= o.getvendorCode() %>">
				</td>
				<td><%= o.getorderDate() %></td>
				<td><%= o.gettitle() %></td>
				<%-- <td><%= o.getodertype() %></td> --%>
				<td><%= sCategory %></td>
				<td><%= o.getorderStatus() %></td>
				<td><%= o.getinvoiceStatus() %></td>
				<td><%= iOrderQty %></td>
				<td><%= sReceived %></td>
			</tr>
<%
		}
	
	}
%>
</tbody>
	</table>
	
	<!-- MODAL WHEN CLICK ErrorInSupply -->
    <div class="modal fade" id="ErrorInSupplySearch" tabindex="-1" role="dialog" aria-labelledby="ErrorInSupplySearch" aria-hidden="true" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" role="document" style="width:55%;overflow:auto">
			    <div class="modal-content" id="ErrorInSupplySearchContent">
				  <!-- Remote content load here  -->
			  	</div>
		</div>
	</div>
	
	<!-- MODAL WHEN CLICK vendorFeedback -->
	<div class="modal fade" id="vendorFeedbackSearch" tabindex="-1" role="dialog" aria-labelledby="vendorFeedbackSearch" aria-hidden="true" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" role="document" style="width:55%;overflow:auto">
			<div class="modal-content" id="vendorFeedbackSearchContent">
				  <!-- Remote content load here  -->
			</div>
		</div>
	</div>
	
	<!-- MODAL FUND WITHDRAWAL -->
		<div class="modal fade" id="fundWithdrawal" role="dialog"  data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content" id="withdrawalContent">
					<!-- Remote content load here -->
				</div>
			</div>
		</div>
	<!-- MODAL  END  FUND WITHDRAWAL -->