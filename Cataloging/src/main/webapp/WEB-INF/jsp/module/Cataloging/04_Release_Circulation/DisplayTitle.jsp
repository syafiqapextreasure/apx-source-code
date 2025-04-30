<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<div class="form-group">
		<div class="col-sm-1 col-md-1">
			<label>Titles:</label>
		</div>
			<div class="col-sm-8 col-md-8">
			
			   <%
				String tite=request.getParameter("piList");
			   System.out.println("Title" + tite);
				 List accTitle=new ArrayList();
			   		accTitle=(ArrayList)request.getAttribute("piList");
				%>
				<%
					for(int i=0;i<accTitle.size();i++){
					 List title=(List)accTitle.get(i);
					 System.out.println("Title" + title.get(0));
				%>	
				<%=title.get(0)%>					
				<input type="hidden" id="accTitlValue" value="<%=title.get(0)%>">
				<input type="hidden" id="accNo" value="<%=title.get(1)%>">
				<input type="hidden" id="accMatNoValue" value="<%=title.get(2)%>">
				<%
					}
				%>
				
				   <%
				 List accCallNo=new ArrayList();
				   accCallNo=(ArrayList)request.getAttribute("piList1");
				%>
				<%
					for(int i=0;i<accCallNo.size();i++){
					 List callNo=(List)accCallNo.get(i);
				%>	
				<input type="hidden" id="accCallNoValue" value="<%=callNo.get(0)%>">
				<%
					}
				%>
			</div>
			
	</div>
