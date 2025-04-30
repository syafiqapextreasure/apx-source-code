<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.cataloging.Bibliography.*"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
  <div class="modal-header">
     <button type="button" class="close closePatrModal" aria-label="Close">
   	 <span aria-hidden="true">&times;</span></button>
           <h4 class="modal-title">Patron Search</h4>
  </div>
		<%
		int count=0;
		String stopDateInput = request.getParameter("stopDateInput");
		String searchType = request.getParameter("search_type");
		String startDateInput = request.getParameter("startDateInput");
		String buffer = request.getParameter("buffer");
		String action = request.getParameter("action");
		String criteria = request.getParameter("criteria");
		List<Bibliography> searchOfficerID = null;
	System.out.println(criteria + searchType);
		if(buffer!=null){
			if(searchType=="creator"){
				searchOfficerID = Bibliography.bufferCreator(startDateInput,stopDateInput);
			}else{
				searchOfficerID = Bibliography.bufferModifier(startDateInput,stopDateInput);
			}
		}else{
		if(searchType=="creator"){
			searchOfficerID = Bibliography.creator(startDateInput,stopDateInput);
		}else{
			searchOfficerID = Bibliography.modifier(startDateInput,stopDateInput);
		}
		}
		%>
	<div class="modal-body">
		<div class="panel panel-default" id="form_parent">
			<div class="panel-group">
			
				<div class="panel panel-default">
					<div class="panel-heading" style="height:3%">
						<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent" href="#result_title" id="result-title">
							<i class="indicator glyphicon glyphicon-chevron-down pull-right"></i>
						</a>
					</h4>
					</div>
					<div id="search_vendor" class="panel-collapse collapse in">
						<div class="panel-body" style="height:30%;overflow-y:scroll">
							<table class="table table-hover" data-toggle="table" id="title_result" data-click-to-select="true">
								<tr>
									<th>
										Patron ID
									</th>
									<th>
										Name
									</th>
								</tr>
								<%
								for(Bibliography i: searchOfficerID){
									count++;
								%>
								<tr style="cursor:pointer" class="clickable-row" data-startDate="<%=startDateInput%>" 
									data-endDate="<%=stopDateInput%>" data-patrid="<%=i.getPatrID()%>" 
									<%if(buffer!=null){ %>data-bufferno="<%=buffer%>"<%} %>
									data-search="<%=searchType%>" data-action="<%=action%>" data-criteria="<%=criteria%>">
									<td>
										<%=i.getPatrID() %>
									</td>
									<td>
										<%=i.getPatrName() %>
									</td>
								</tr>
								<%
								}	if (count==0){
									%>
									<tr class="clickable-row1">
											<td class="pointer" colspan="2">
												<div class="alert alert-info" align="center">
													<font style="color:black;">No record found</font>
												</div>
											</td>
										</tr>
									<%
									}
								%>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	