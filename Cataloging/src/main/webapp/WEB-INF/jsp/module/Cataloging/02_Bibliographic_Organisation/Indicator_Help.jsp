<%@page import="com.ilmu.cataloging.Template_Maint.*,
				java.util.*, com.ilmu.global.Handler"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/BO.js"></script>	
	<%
	String GL18TAG = request.getParameter("GL18TAG");
	String module = request.getParameter("module");
	String marc = Handler.marcType(module);
	%>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Indicators List - Tag <%=GL18TAG %></h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<div class="col-sm-2">
				<label>Indicators:</label>
			</div>
			<div class="col-sm-5 col-md-5">
				<%	
					int count = 0;
					
					List<Indicator_Handler> IndiInfo1 = null;
					IndiInfo1 = Indicator_Handler.getIndi1(GL18TAG, marc);
				%>
				<select class="form-control indi indi1Help" id="indi1" name="indi1" onchange="loadXMLDoc();addVal()">
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
						<option value="NoIndicator">No Indicator</option>
						<%	} %>
				</select>
			</div>
			<div class="col-sm-5 col-md-5">
				<select class="form-control indi indi2Help" id="indi2" name="indi2" onchange="getSubfields()">
					<%	
					List<Indicator_Handler> IndiInfo2 = null;
					IndiInfo2 = Indicator_Handler.getIndi2(GL18TAG, marc);
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
	</div>
	<div class="modal-footer">
		<button class='btn btn-info replaceIndi' data-dismiss="modal">Replace</button>
	</div>