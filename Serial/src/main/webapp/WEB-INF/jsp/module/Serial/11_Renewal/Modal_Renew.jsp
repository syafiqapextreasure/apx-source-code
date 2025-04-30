<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serial/Renewal.js"></script>					
<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ilmu.serial.Renewal.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.0/css/bootstrap-datepicker3.css" integrity="sha256-kS81UmreG6WJA7BRAVmBSkuc0YldflRXJw66wiAF9a8=" crossorigin="anonymous" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.0/js/bootstrap-datepicker.js" integrity="sha256-4/8vOpKmglUDBGTR6LAubR1L6/0f9kvKVfWdNWcxEV8=" crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serial/Renewal2.js"></script>	
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
<!-- <script type="text/javascript">
            $(function () {
            	/* alert("ssds");
            	$("#startDate").datepicker({
            		format: 'dd/mm/yyyy'
            	}); */

            });
        </script> -->
<div class="modal-body" >		
			<div class="panel panel-default">
	    	<div class="panel-heading"><b>Prediction Pattern</b></div>

			  <ul class="nav nav-tabs">
			    <li class="active"><a data-toggle="tab" href="#home">Details</a></li>
			    <li><a data-toggle="tab" href="#menu1">Cardex</a></li>
			  </ul>
			
			  <div class="tab-content">
			    <div id="home" class="tab-pane fade in active">
					      <div class="panel-body">
						<form role="form" class="form-horizontal" id="form_renew">
						<input type="hidden" value="" id="action">
						<%
							String orderno = request.getParameter("orderno");
							List<Renewal> renewal = Renewal.PredictionPattern(orderno.trim());
			
							for(Renewal i : renewal){
						%>
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>Title:</label></div>
								<div class="col-sm-8 col-md-8"><%=i.getTITLE() %><input type="hidden" id="matno" value="<%=i.getSE03MATNO()%>"></div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>ISSN:</label></div>
								<div class="col-sm-8 col-md-8"><%=i.getISBN() %></div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>Order No:</label></div>
								<div class="col-sm-8 col-md-8"><span><%=i.getSE07ORDER() %><input type="hidden" id="ordernoval" value="<%=i.getSE07ORDER() %>"></span></div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>Last Volume/Issue:</label></div>
								<div class="col-sm-8 col-md-8"><%=i.getSE03VOLFROM() %>/<%=i.getSE03ISSFROM() %></div>
								<input type="hidden" value="<%=i.getSE03VOLFROM() %>" id="volFrom">
								<input type="hidden" value="<%=i.getSE03ISSFROM() %>" id="issueFrom">
							</div>
							
							<input type="hidden" class="form-control" id="freqTime" name="freqTime">
							<input type="hidden" class="form-control" id="freqType" name="freqType">
							<input type="hidden" id="hidden-patternID" name="patternID"/>
							<input type="hidden" id="hidden-firstPattern" name="firstPattern"/>
							
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>Vendor:</label></div>
								<div class="col-sm-4 col-md-4">
								<div class="input-group">
										<input type="text" class="form-control CT03VEND" id="input-vendorCode" placeholder="Vendor" value="<%=i.getSE03SUPPLIER()%>">
										<a href="Modal_Vendor" id="searchvendor" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#modal_vendorSearch">
										<span class="glyphicon glyphicon-option-horizontal"></span></a>
									</div>	
								</div>
								<div class="col-sm-4 col-md-4"><div id="div-vendorName"><%=i.getVENDNAME() %></div></div>
							</div>
						
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>Frequency:</label></div>
									<div class="col-sm-4 col-md-4">
									<select class="form-control frequency" id="frequency1">
										<%
											List<Renewal> freq = Renewal.GLFREQ();
											for(Renewal j : freq){
								
										%>
											<option value="<%=j.getcode() %>"  <%if(i.getSE03FREQ().equals(j.getcode())){%>selected<%} %> data-pattern="<%=j.gettype()%>"><%=j.getdesc() %></option>
										<%
											}
										%>
									</select>
									</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>Number of Copies:</label></div>
								<div class="col-sm-2 col-md-2"><input type="text" class="form-control copies" id="copies2" value="<%=i.getSE07COPIES()%>"></div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>Number of Issues:</label></div>
								<div class="col-sm-2 col-md-2"><input type="text" class="form-control numberOfIssues" id="numberOfIssues2" value="<%=i.getSE03ISSUES()%>"></div>
							</div>		
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>Volume Start:</label></div>
								<div class="col-sm-4 col-md-4"><input class="form-control" id="firstVolume2" type="text" value="<%=i.getnewVolume() %>"></div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>First Issue Pattern:</label></div>
								<div class="col-sm-3 col-md-3"><input class="form-control" id="issuePattern" type="text" value="<%=i.getSE03ISSFROM() %>" disabled></div>
								<div class="col-sm-1 col-md-1"><a href="Modal_IssuePattern" id="searchpattern" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#modal_pattern">
										<span class="glyphicon glyphicon-option-horizontal"></span></a></div>
								<div class="col-sm-3 col-md-3 xxx"></div>		
							</div>
							
							<%-- 17062020 <div class="form-group">
								<div class="col-sm-4 col-md-4"><label>Start Date:</label></div>
								<div class="col-sm-4 searchInput">
								<input class="form-control startDate" id="startDate" type="text"  value="<%=i.getSE03DTSTART() %>"></div>
									<div class="input-daterange input-group" id="datepicker">
									<input type="text" class="form-control startDate" maxlength="10" id="startDate" value="<%=i.getSE03DTSTART() %>"/>
									</div>
							</div> --%>
							<div class="form-group">
								<div class="col-sm-4 col-md-4">
									<label for="foreign">Start Date</label>
								</div>
								<div class="col-sm-2">
									<input type="text" class="form-control startDate" id="startDate2" name="startDate2" autocomplete="off" value="<%=i.getSE03DTSTART() %>">
								</div>
								<div class="col-sm-2">
									<label for="foreign">Stop Date</label>
								</div>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="stopDate2" name="stopDate2">
								</div>
							</div>
										
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>Currency:</label></div>
									<div class="col-sm-4 col-md-4">
									<select class="form-control currency" id="currency">
										<%
											List<Renewal> forex = Renewal.GLFOREX();
											for(Renewal j : forex){
								
										%>
											<option value="<%=j.getcode() %>" <%if(i.getSE07FOREX().equals(j.getcode())){%>selected<%} %>><%=j.getdesc() %></option>
										<%
											}
										%>
									</select>
									</div>
								
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>Exchange Rate:</label></div>
								<div class="col-sm-8 col-md-8">
									<input class="form-control" id="exchangeRate" type="text" value="<%=i.getSE07PRATE()%>">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>Foreign cost:</label></div>
								<div class="col-sm-8 col-md-8">
									<input class="form-control" id="foreignCost" type="text" value="<%=i.getSE07FPRICE()%>">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-md-4"><label>Local cost:</label></div>
								<div class="col-sm-8 col-md-8">
									<input class="form-control" id="localCost" type="text" value="<%=i.getSE07LPRICE()%>">
								</div>
							</div>
							<input type="hidden" id="ordernoarray" value="">
							<%
							}
							%>
						</form>
					</div>	
			    </div>
			    <div id="menu1" class="tab-pane fade">
			      <div class="panel panel-default">
		  			<div class="panel-body">
		  			
					<table class="table table-bordered" id="patternList">
						<thead>
							<tr>
								<th>Copy</th>
								<th>Volume</th>
								<th>Issue</th>
								<th>Expected date</th>
							</tr>
						</thead>
						<tbody id="table-cardex"></tbody>
					</table>
					</div>
					</div>
			    </div>
			  </div>

		</div>
		<!-- Hidden Search parameters -->
		<input type="hidden" id="input-searchParams">

	<script type="text/javascript" src="js/09.js"></script>
	<script type="text/javascript" src="js/shared.js"></script>
</div>
<div class="modal-footer">
  	<button type="submit" class="btn btn-primary" onClick="processRenewal()">OK</button>
	<button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
</div>

	<div class="modal fade" id="modal_pattern" role="dialog"  data-backdrop="static">
				    <div class="modal-dialog" style="width:50%">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
				
				
					<div class="modal fade" id="fundDistribution" role="dialog"  data-backdrop="static">
				    <div class="modal-dialog modal-lg">
						  <div class="modal-content" id="fundContent">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
		
