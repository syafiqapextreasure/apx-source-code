<%-- <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/BO.js"></script>					

        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h3 class="modal-title">View BO Record</h3>
          <hr/>
          <table style="width:100%">
          	<%
    				String controlNoInput = request.getParameter("matno");
    			
    				BO_Record CTMATM = BO_Record.getCTMATM(controlNoInput);
	          		out.println("<tr>");
	    			out.println("<td class='creby'>Created By: "+ CTMATM.getCT02CREBY() +"</td>");
	    			out.println("<td class='credate'>Created Date: "+ CTMATM.getCT02CREDATE() +"</td>");
	    			out.println("<td class='cretime'>Created Time: "+ CTMATM.getCT02CRTIME() +"</td>");	    			
	    			out.println("</tr>");
	    			out.println("<tr>");
	    			out.println("<td class='modiby'>Modified By: "+ CTMATM.getCT02CREBY() +"</td>");
	    			out.println("<td class='modidate'>Modified Date: "+ CTMATM.getCT02CREDATE() +"</td>");
	    			out.println("<td class='moditime'>Modified Time: "+ CTMATM.getCT02CRTIME() +"</td>");
	    			out.println("</tr>");
	    			out.println("<tr>");
	    			out.println("<td class='orimatno'>Control No: "+ CTMATM.getCT02MATNO() +"</td>");
	    			out.println("<td class='orimatno'>Status: View Record/Unindex</td>");
	    			out.println("</tr>");
    			
          		%>
          </table>
        </div>
        <div class="modal-body"  style="height:320px;overflow-y:scroll;">
          <table class="table table-striped" id="tblGrid">
          	          <tr>
          	<th>
          		Tag
          	</th>
          	<th>
          		Indicator 1
          	</th>
          	<th>
          		Indicator 2
          	</th>
          	<th>
          		Raw Data
          	</th>
          </tr>
            <tbody>
			<% 
			List<BO_Record> tableName = null;
			List<BO_Record> searchResult = null;
			
			tableName = BO_Record.getBORecord(controlNoInput);

			//int value = value.getGL98VALUE();
			for(BO_Record j: tableName)
			{
				searchResult = BO_Record.getAllBy(controlNoInput, j.getCT06TAG(),j.getGL17TABNAME());
				for(BO_Record i: searchResult)
				{
					/* Glnumb value = Glnumb.getGL98VALUE("BUFPOINT");
					 Glnumb.incHits("BUFPOINT");  */
					
					out.println("<tr class='BORecord'>");
					//out.println("<td class='counter' hidden>"+value.getGL98VALUE()+"</td>");
					out.println("<td class='matno' hidden>"+ i.getCT04MATNO() +"</td>");
					out.println("<td class='orimatno' hidden>"+ CTMATM.getCT02MATNO() +"</td>");
					out.println("<td class='creby' hidden>"+ CTMATM.getCT02CREBY() +"</td>");
					out.println("<td class='credate' hidden>"+ CTMATM.getCT02CREDATE() +"</td>");
					out.println("<td class='cretime' hidden>"+ CTMATM.getCT02CRTIME() +"</td>");
					out.println("<td class='status' hidden>"+ CTMATM.getCT02STATUS() +"</td>");
					out.println("<td class='tag'>"+ i.getCT04TAG() +"</td>");
					out.println("<td class='indi1'>"+ i.getCT04INDI1() +"</td>");
					out.println("<td class='indi2'>"+ i.getCT04INDI2() +"</td>");
					out.println("<td class='raw'>"+ i.getCT04RAW() +"</td>");
					//out.println("<td class='conv' hidden>"+ i.getCT05SCONV() +"</td>");
					out.println("</tr>");

				}
			}
			%>
	 </tbody>
          </table>
		</div>
        <div class="modal-footer">
          <button type="button" class="cancel btn btn-default " data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary unindex" data-value="<%=controlNoInput%>">Unindex and Modify</button>
        </div>


	
	 --%>