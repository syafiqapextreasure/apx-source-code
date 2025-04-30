<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
					
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/Search_Modal.js"></script>	

<div class="modal-header">
	<h4 class="modal-title" id="myModalLabel">Authority Link</h4>
</div>
<div class="modal-body" style='height:50%;overflow:auto;'>					
<%
	String tag = request.getParameter("tag");
	String rawData = request.getParameter("rawData");
	  if (rawData.contains("|")) {
		  rawData = Handler.removeSubfield(rawData);
	  }
	System.out.println("Rawwwwwwq" + tag + "ssa" + rawData + "sss");
	Autlink getAU06MATNO = null;
	List<Autlink> getAU06POINTER = null;
	List<Autlink> getAUPONT = null;
	getAU06MATNO = Autlink.getAU06MATNO(tag, rawData);
	String pointerArray = "";
	String action = request.getParameter("action");

	getAU06POINTER = Autlink.getAU06POINTER(getAU06MATNO.getAU06MATNO());
	StringBuilder AU06POINTER = new StringBuilder();
	StringBuilder GL17TRUC = new StringBuilder();
	for(Autlink i: getAU06POINTER)
	{
		AU06POINTER.append(i.getAU06POINTER());
		AU06POINTER.append(",");
	
	}
	AU06POINTER.setLength(AU06POINTER.length() - 1);
	String pointer = AU06POINTER.toString();
	getAUPONT = Autlink.getAUPONT(tag, pointer);
	
	if(!action.equals("EditTerm")){
		String tagid = request.getParameter("tagid");

%>
	<input type = "hidden" value="<%=tagid %>" id="tagid">
<%
	}
%>
	<table class="table table-hover">
	    <thead>
	      <tr>
	        <th>Authority No.</th>
	        <th>Description</th>
	        <th>Term</th>
	      </tr>
	    </thead>
	    <tbody>
<%
	for(Autlink i: getAUPONT)
	{	
		for(Autlink j: getAU06POINTER)
		{
			if(j.getAU06POINTER()==i.getCT05POINTER()){
%>
	<tr  <%if((i.getCT05AUTLINK()).equals("T")){%>style="cursor:pointer"<%}else{ %>style="cursor:not-allowed;pointer-events:none;color:grey"<%} %> class="auth-row" data-raw="<%=i.getCT05SRAW() %>">
		<td>
			<%=getAU06MATNO.getAU06MATNO() %><input type="hidden" id="action" value="<%=action%>">
		</td>
		<td>
			<%=j.getGL17TRUC() %>
		</td>
		<td>
			<%=Handler.removeSubfield(i.getCT05SRAW()) %>
		</td>
	</tr>
<%
			}
		}
	}
%>
	  </tbody>
  </table>
</div>
<div class="modal-footer">
	<input type="button" name="cancel" value="Cancel" onclick="closeAuthModal()" class="btn btn-info btn-cancel"/>
</div>	
