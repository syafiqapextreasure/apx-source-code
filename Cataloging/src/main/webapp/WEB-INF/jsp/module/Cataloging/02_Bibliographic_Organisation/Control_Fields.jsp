<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ilmu.cataloging.Bibliography.*, 
				com.ilmu.cataloging.Template_Maint.Cataloging, 
				java.util.*"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/BO.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/BO.js"></script>					 
<title>Insert title here</title>

</head>
<body>

	
  <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Control Tag</h4>
	</div>
	<div class="modal-body">
	<div class="panel panel-default" id="form_parent">
		<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent"
							href="#search"><b>Control Tags</b></a>
					</h4>
				</div>
				<%
				String ctrlTag = request.getParameter("ctrlTag");
				System.out.println(ctrlTag);
				String ctrlID = request.getParameter("ctrlID");
					List<Control_Fields> getMasterCode = null;
					getMasterCode = Control_Fields.getMasterCode(ctrlTag);
														%>
				<div id="search" class="panel-collapse collapse in">
					<div class="panel-body">
						<form role="form" class="form-horizontal" id="current_form" name="AddTplInfo">
							<input type="hidden" id="selectValue" value="">
							<%if(!ctrlTag.equals("000")){%>
							<div class="form-group">
								<div class="col-sm-1 col-md-1"></div>
								<div class="col-sm-2 col-md-2">
									<label>Type Of Material:</label>
								</div>
								<div class="col-sm-3">
								<input type="hidden" id="ctrlTagValue" value= "<%=ctrlID%>">
								<input type="hidden" class="tags" value= "<%=ctrlTag%>">
									<select id="ctrlTagSlct" class="form-control">
									<option value='0'>Please Select</option>
										<%
											for(Control_Fields i: getMasterCode)
											{
										%>
										 <option value="<%=i.getGL59MSTRCODE()%>"><%=i.getGL59DESC()%></option>
						
										<%
											}
										%>
									</select>
						
									</div>
							</div>
							<%}else{%>
								<div class="form-group">
								<div class="col-sm-1 col-md-1"></div>
								<div class="col-sm-2 col-md-2">
									<label>Type Of Material:</label>
								</div>
								<div class="col-sm-3"><label class="leader">Leader</label></div>
								<input type="hidden" id="ctrlTagValue" value= "<%=ctrlID%>">
								<input type="hidden" class="tags" value= "<%=ctrlTag%>">
							</div>
							<%} %>
							<div class="clearfix"></div>
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div id="ctrlTagCode" onload="loadMediList();">
									
								</div>
							</div>
							<div class="clearfix"></div>
							
							<div id="indiList"></div>
							<div class="clearfix"></div>

						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
		</div> 
	<div class="modal-footer">
					<input type="button" class="btn btn-primary" id="add" value="Save" onclick="report()" data-dismiss="modal">
					<input type="button" class="btn btn-primary" value="Close" data-dismiss="modal">
	</div>
</body>
</html>