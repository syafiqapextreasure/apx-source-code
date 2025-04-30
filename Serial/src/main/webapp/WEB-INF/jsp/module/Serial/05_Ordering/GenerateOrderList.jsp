<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.ilmu.cataloging.PDF.*, 
				com.ilmu.cataloging.template.*,
				com.ilmu.serial.Ordering.*,com.ilmu.global.*"%>
				
				 <style>
   body {
  background: rgb(204,204,204); 
}

.center {
    margin: auto;
    padding: 10px;
    text-align:center;
     font-weight: bold;
}

.title {
    font-weight: bold;
    text-decoration: underline
}
page {
	padding:10mm;
  background: white;
  display: block;
  margin: 0 auto;
  margin-bottom: 0.5cm;
  box-shadow: 0 0 0.5cm rgba(0,0,0,0.5);
  font-size: 10pt
}
page[size="A4"] {  
 width: 210mm;
 min-height: 297mm;
 font-size:10pt
}

table{
font-size:10pt}

@media print {
  body, page {
    margin: 0;
    box-shadow: 0;
  }
}
    </style>

<%
	String text = "";
	String letterID = request.getParameter("letterId");
	String orderNo = request.getParameter("orderNo");
	String vendorcode = request.getParameter("vendor");
	String mode = "";
	System.out.println("Letter" + letterID);
	if(letterID.equals("EQ03")){
		System.out.println("Mode" + mode);
		mode = request.getParameter("mode");
	}
	if(letterID.equals("EQ40") || letterID.equals("EQ03")){
		String[] orderNos = orderNo.split(",");
		Ordering orderList;
		/* for(int i=0; i<orderNos.length;i++){
			}
		 */
		 Library vendor = Library.getVendorDetails(vendorcode);
		 Library library = Library.getContactsOnly();
		 List<Ordering> ordering = Ordering.getOrderingList(orderNo);
		 
		 %>
		 <body>
		 <page size="A4" layout="portrait">
		 <div class="center">
			 <div><%=library.getOrgName() %></div>
			 <div><%=library.getAddress1() %></div>
			 <div><%=library.getAddress2() %></div>
			 <div><%=library.getAddress3() %></div>
			<%--  <div><%=library.getPoscode() %> <%=library.getTown() %></div> --%>
		 </div>
		 <br/>
		 <br/>
		 <div>
			 <div><%=vendor.getGL39NAME() %></div>
			 <div><%=vendor.getGL39ADD1() %></div>
			 <div><%=vendor.getGL39ADD2() %></div>
			 <div><%=vendor.getGL39ADD3() %></div>
			 <div><%=vendor.getGL39PCODE() %></div>
		 </div>
		 <br/>
		 <div>Attention : </div>
		 <br/>
		 <br/>
		 <div class="title">
		 	<%
		 		if(mode!=null){
		 	%>
		 		 <div><%=mode %></div>
		 	<%
		 		}else{
		 	%>
			 	<div>Re:Ordering</div>
			 <%
		 		}
			 %>
		 </div>
		 <br/>
		 <table>
		 <tr>
		 	<th>Order#</th>
		 	<th>ISBN</th>
		 	<th>Description</th>
		 	<th>Edition</th>
		 	<th>Qty</th>
		 	<th>Unit Price</th>
		 	<th>Foreign Amount</th>
		 	<th>Amount</th>
		 	<th>Expected Date</th>
		 </tr>
		 
		 <%
		 int total = 0;
		 double totalForeign = 0;
		 double totalAmt = 0;
		 String quantity = null;
		 String foreignCurrency = null;
		 for(Ordering i: ordering){
			 if(i.getQuantity().contains("set")){
				 quantity = (i.getQuantity()).replace("set", "");
			 }else{
				 quantity = i.getQuantity();
			 }
			 total = Integer.parseInt(quantity) + total;
			 totalForeign = Double.parseDouble(i.getTotal() ) + totalForeign;
			 totalAmt = Double.parseDouble(i.getLocalTotal()) + totalAmt;
			 foreignCurrency = i.getSE03FOREX();
			%>
			
			<tr>
		 		<td><%=i.getSE03ORDER() %></td>
		 		<% if(i.getISBN().trim()==null || i.getISBN().trim().equals("null")){%>
		 		<td></td>
		 		<%
		 		}else{
		 		%>
		 		<td><%=i.getISBN() %></td>
		 		<%
		 		}
		 		%>
		 		<td><%=i.getTITLE() %></td>
		 		<td></td>
		 		<td><%=i.getQuantity()%></td>
		 		<td><%=i.getSE03FOREX()%> <%=i.getSE03FPRICE() %></td>
		 		<td><%=i.getSE03FOREX() %> <%=i.getTotal() %></td>
		 		<td>RM <%=i.getLocalTotal() %></td>
		 		<td><%=i.getSE03CRDATE() %></td>
		 	</tr>
			<%
		 }
		 	%>
		 	<tr>
		 		<td></td>
		 		<td></td>
		 		<td></td>
		 		<td><strong>Total :</strong></td>
		 		<td><strong><%=total %></strong></td>
		 		<td></td>
		 		<td><strong><%=foreignCurrency%> <%=Handler.decimalConversion(String.valueOf(totalForeign)) %></strong></td>
		 		<td><strong>RM <%=Handler.decimalConversion(String.valueOf(totalAmt)) %></strong></td>
		 		<td></td>
		 	</tr>
		 </table>
		 <p>Please  supply  the  item(s)  according  to  the terms  and  conditions  of  our  Library. </p>
		<p>Thank You.</p>
		<p>Yours sincerely, </p>
		<p>SYSADMIN </p>
		<p>Pensyarah</p>
		 </page>
		 </body>
		 <%
		 
		//orderList = Ordering.getOrderingList(orderNo);
		//text = Document.createOrderList(ordering);
		
	}
/* 	System.out.println("Order" + text);
	
	
	if (text!=null) {
		out.print(text);
	}
	 */
%>

