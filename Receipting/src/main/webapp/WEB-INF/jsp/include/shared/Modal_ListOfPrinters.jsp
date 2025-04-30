<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.print.*"%>
<script>
function changePrinter(){
	var radioValue = $("input[name='printer']:checked"). val();
	if(radioValue){
		var url = "Handler_DefaultPrinter?printer=" +   radioValue;
		alert(url);
		$.get(url,function(datas){
			alert(datas);
		});
	}
}
</script>
<div class="modal-body">
<table>
<%
PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
PrintService service = 
PrintServiceLookup.lookupDefaultPrintService();
String printServiceName = null;
if (service != null) {
   printServiceName = service.getName();
    System.out.println("Print Service Name is " + printServiceName);
} else {
    System.out.println("No default print service found");
}
for (PrintService printer : printServices){
%>
<tr>
	<td>
		<input type="radio" name= "printer" id="checkboxId" value="<%=printer.getName() %>" <%if(printServiceName.equals(printer.getName())){ %>checked<%} %>> &nbsp;
	</td>
	<td>
		 <span class="glyphicon glyphicon-print"></span> <%=printer.getName() %>
	</td>
 <%--  <a href="#" class="btn btn-success btn-lg">
    <span class="glyphicon glyphicon-print"></span> <%=printer.getName() %>
  </a> --%>
</tr>
<%
}
%>
</table>
</div>
<div class="modal-footer">

	<button class='btn btn-sm' id='deleteBO' onclick="changePrinter()" data-dismiss="modal">Delete</button>
	
	<button class='btn btn-sm' id='cancel' data-dismiss="modal">Cancel</button>
</div>


	