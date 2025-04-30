<%@ page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@ page import="com.ilmu.foundation.global.Foundation"%>
<%@ page import="java.util.List"%>

<%
	GlobalSQLStatement sql = new GlobalSQLStatement();
	String type = request.getParameter("type");
%>
<%
	if (type.equals("cate")) {
%>
<div class="panel-heading">
	<strong>Patron Category Code</strong>
</div>
<div class="panel-body search">
	<table class="table">
		<tbody>
			<%
				List<Foundation> catlist = sql.getCate();

					for (Foundation e : catlist) {
			%>
			<tr>
				<td width="5%"><label><input type="checkbox"
						class="categories" value="<%=e.getGL07CATE()%>"></label></td>
				<td><%=e.getGL07CATE()%></td>
				<td><%=e.getGL07DESC()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</div>
<%
	} else if (type.equals("icate")) {
%>
<div class="panel-heading">
	<strong>Item Category Code</strong>
</div>
<div class="panel-body search">
	<table class="table">
		<tbody>
			<%
				List<Foundation> icatlist = sql.getICat();

					for (Foundation e : icatlist) {
			%>
			<tr>
				<td width="5%"><input type="checkbox" class="items"
					value="<%=e.getGL10ICAT()%>"></td>
				<td><%=e.getGL10ICAT()%></td>
				<td><%=e.getGL10DESC()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</div>
<%
	} else if (type.equals("smd")) {
%>
<div class="panel-heading">
	<strong>SMD Code</strong>
</div>
<div class="panel-body search">
	<table class="table">
		<tbody>
			<%
				List<Foundation> smdlist = sql.getSMD();

					for (Foundation e : smdlist) {
			%>
			<tr>
				<td width="5%"><input type="checkbox" class="smds"
					value="<%=e.getGL47SMD()%>"></td>
				<td><%=e.getGL47SMD()%></td>
				<td><%=e.getGL47DESC()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</div>
<%
	} else if (type.equals("branch")) {
%>
<div class="panel-heading">
	<strong>Branch</strong>
</div>
<div class="panel-body search">
	<table class="table">
		<tbody>
			<%
				List<Foundation> brnclist = sql.getBranch();

					for (Foundation e : brnclist) {
			%>
			<tr>
				<td width="5%"><input type="checkbox" class="branchs"
					value="<%=e.getGL71BRNC()%>"></td>
				<td><%=e.getGL71BRNC()%></td>
				<td><%=e.getGL71DESC()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</div>
<%
	}
%>