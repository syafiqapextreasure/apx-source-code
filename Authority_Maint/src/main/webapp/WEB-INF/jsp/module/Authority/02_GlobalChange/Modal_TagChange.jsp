<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.Authority.AuthorityGlobal.*,
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>

<script>
  	$(document).ready(function () {
  		$("[title]").tooltip();
	$('#tagtbl').DataTable({
	    responsive: true
	});

});
</script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Authority/Global_Change.js"></script>			
<%
String term = request.getParameter("term");
int pointer = Integer.parseInt(request.getParameter("pointer"));
%>
<div class="panel-body">
	<label>Data: <span><%=term%></span></label>
</div>
<div class="panel-body">
 <table class="table table-bordered" id="tagtbl">
	<thead>
		<tr>
			<th data-sortable="true" style="text-align: center;">No.</th>
			<th data-sortable="true" style="text-align: center;">Module</th>
			<th style="text-align: center;">Control No.</th>
			<th style="text-align: center;">Tag</th>
			<th style="text-align: center;">Indicator 1</th>
			<th style="text-align: center;">Indicator 2</th>
		</tr>
	</thead>
	<tbody>
		<%
			List<Global_Change> titleResult = null;
			int count = 0;
			titleResult = Global_Change.getTagDetails(pointer);
			for(Global_Change i: titleResult){
				count++;
		%>
		<tr>
			<td><%=count %></td>
			<td><%=i.gettype() %></td>
			<td><%=i.getmatno() %></td>
			<td><%=i.gettag() %></td>
			<td><%=i.getindi1() %></td>
			<td><%=i.getindi2() %></td>
		</tr>
		
		<%
			}
		%>
	</tbody>
</table>
</div>
<div class="modal-footer">
<div class="form-group">
	<div class="col-sm-4 col-md-4"></div>
		<div class="col-sm-8 col-md-8">
			<button type="button" class="btn btn-info acceptchg" id="acceptchg">
				<span class="glyphicon glyphicon-search"></span> Accept
			</button>
			<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
		</div>
	</div>
</div>