<%@page import="com.ilmu.cataloging.Template_Maint.*,
				java.util.*"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/bootstrap-multiselect/js/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/bootstrap-multiselect/css/bootstrap-multiselect.css" type="text/css"/>

<script>
/* $('.multiselect').multiselect({
	 includeSelectAllOption: true,
	 minHeight     : 10,  
	 enableFiltering: true,
     maxHeight: 200,
     dropUp: true

}); */

$('.multiselect').multiselect({
	includeSelectAllOption: true, 
	 maxHeight: 300
});





</script>
<style>


</style>
<div class="form-group">
	<div class="col-sm-1"></div>
	<div class="col-sm-2">
		<label>Indicators:</label>
	</div>
	<div class="col-sm-3 col-md-3">
		<%	
			int count = 0;
			String GL18TAG = request.getParameter("GL18TAG");
			List<Indicator_Handler> IndiInfo1 = null;
			IndiInfo1 = Indicator_Handler.getIndi1(GL18TAG);
		%>
		<select class="form-control indi tplindi1" id="indi1" name="indi1" onchange="loadXMLDoc();addVal()">
				<!-- <option value="0">Please select</option> -->
				<%
					for(Indicator_Handler i: IndiInfo1){
						count++;
				%>
				<option value="<%=i.getGL18INDI()%>" data-indilvl="<%=i.getGL18INDILVL()%>"><%=i.getGL18INDI()%>-<%=i.getGL18DESC()%></option>

				<%										
					}
				
					if (count==0){
				%>
				<option value="#">#-Undefined</option>
				<%	} %>
		</select>
	</div>
	<div class="col-sm-3 col-md-3">
		<select class="form-control indi tplindi2" id="indi2" name="indi2" onchange="getSubfields()">
			<%	
			List<Indicator_Handler> IndiInfo2 = null;
			IndiInfo2 = Indicator_Handler.getIndi2(GL18TAG);
			%>
			<!-- <option value="0">Please select</option> -->
				<%
					for(Indicator_Handler i: IndiInfo2){
						count++;
				%>
				<option value="<%=i.getGL18INDI()%>" data-indilvl="<%=i.getGL18INDILVL()%>"><%=i.getGL18INDI()%>-<%=i.getGL18DESC()%></option>

				<%										
					}
				
					if (count==0){
				%>
					<option value="NoIndicator">No Indicator</option>
				<%	} %>
		</select> 
	</div>
</div>
<div></div>
<div class="form-group">
	<div class="col-sm-1"></div>
	<div class="col-sm-2">
		<label>Subfields:</label>
	</div>
	<div class="col-sm-6 col-md-6">
		<select style="z-index:2" class='multiselect form-control' id='subfields' name='subfields' multiple='multiple' required aria-required="true">
			<%
				List<Subfield_Handler> subfList = null;
				subfList = Subfield_Handler.getListOfSubf(GL18TAG);
				int counts = 0;
				for(Subfield_Handler i: subfList){
					counts++;
				if(counts==1){
			%>
				<option value='|<%=i.getGL23SUBF()%>'data-id='<%=i.getGL23REPEAT()%>' selected><%=i.getGL23SUBF()%>-<%=i.getGL23DESC()%></option>
			<%
				}else{
			%>
				<option value='|<%=i.getGL23SUBF()%>'data-id='<%=i.getGL23REPEAT()%>'><%=i.getGL23SUBF()%>-<%=i.getGL23DESC()%></option>
			<%}}%>
		</select>
	</div>
</div>