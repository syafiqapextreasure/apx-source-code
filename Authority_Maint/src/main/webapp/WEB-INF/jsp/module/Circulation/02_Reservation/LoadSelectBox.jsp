<%@ page import="com.ilmu.circulation.resv.*, com.ilmu.circulation.Charging.*" %>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Reservation.js"></script>					
<%
	String action = request.getParameter("action");
	String CT03MATNO = request.getParameter("CT03MATNO");
	
	System.out.println(action);
	if(action.equals("getLoca")){
%>
<div class="form-group row" id="location">
	<label for="inputPassword" class="col-sm-2 col-form-label">Location :</label>
<%-- 	 <div class="col-sm-3">
		<select class="form-control" id="GL05LOCA">
			<option>Any Location </option>
			<%
				List<Reservation> branch = null;
				branch = Reservation.retrieveLoca(CT03MATNO);
		       	for (Reservation i: branch) {
			%>
			<option><%=i.getGL05LOCA() %></option>
			<%
			  	}
			%>
		</select>
	</div> --%>
	<div class="col-sm-5">
		<select class="form-control" id="GL05LOCA">
			<option>Any Location </option>
			<%
				List<Reservation> branch2 = null;
				branch2 = Reservation.retrieveLoca(CT03MATNO);
		       	for (Reservation i: branch2) {
			%>
			<option value="<%=i.getGL05LOCA() %>" data-text="<%=i.getGL05DESC() %>"><%=i.getGL05LOCA() %>-<%=i.getGL05DESC() %></option>
			<%
			  	}
			%>
		</select>
	</div>
</div>
<div id="accession">
  <div class="form-group row"> 
		<label for="inputPassword" class="col-sm-2 col-form-label">Accession No. :</label>
			<div class="col-sm-5">
				<select class="form-control selectCT03MATNO" id="">
					<option value="0">Any</option>
					<%
						List<Reservation> accession = null;
						accession = Reservation.retrieveAcc(CT03MATNO, "0");
				       	for (Reservation i: accession) {
					%>
					<option value="<%=i.getCT05SRAW() %>"><%=i.getCT05SRAW() %></option>
					<%
					  	}
					%>
				</select>
			 </div>
		</div>
	</div>
<%
	}else if (action.equals("getAcc")){
		String CT03LOCA = request.getParameter("CT03LOCA");
%>
	<div class="form-group row" id="accession"> 
		<label for="inputPassword" class="col-sm-2 col-form-label">Accession No. :</label>
			<div class="col-sm-5">
				<select class="form-control" id="sel1">
					<option>Any</option>
					<%
						List<Reservation> accession = null;
						accession = Reservation.retrieveAcc(CT03MATNO, CT03LOCA);
				       	for (Reservation i: accession) {
					%>
					<option value="<%=i.getCT05SRAW() %>"><%=i.getCT05SRAW() %></option>
					<%
					  	}
					%>
				</select>
			 </div>
	</div>
<%
	}
%>