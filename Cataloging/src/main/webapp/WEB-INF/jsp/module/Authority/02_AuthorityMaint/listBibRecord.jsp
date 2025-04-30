
<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.ilmu.cataloging.Template_Maint.SQLStatement,
				com.ilmu.cataloging.Template_Maint.Cataloging,
				java.util.*, com.ilmu.global.*" %>
<html>

<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/themes/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/BO.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/BO.js"></script>
<style>
.panel-group .panel {
  overflow: visible;
}
</style>
</head>
<body>

				<div class="panel panel-default" id="form_parent"> 
					<div class="panel-group">
						<div class="panel panel-default">
						  <div class="panel-heading clearfix">
							<h4 class="panel-title pull-left" style="padding-top: 7.5px;">
								<a class="btn btn-primary" id='enableSelect' title="Add new record"><span class="glyphicon glyphicon-plus" style="color:white"></span></a>
								<a class="btn btn-primary" id='retrieveLink' data-toggle='modal' data-target='#titleSearch' href='RetrieveTitleModal?action=7&module=Authority' title="Catalog Search"><span class="glyphicon glyphicon-search" style="color:white"></span></a>
								<a class="btn btn-primary" id='paramips' data-toggle='modal' data-target='#paraZ39' href='ParamipsZ39' title="Marc Uploader"><span class="glyphicon glyphicon-cloud-upload" style="color:white"></span></a>
							</h4>
								<a data-toggle="collapse" data-parent="#form_parent" href="#search">
									<i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
								</a>
							</div>
							<div id="search" class="panel-collapse collapse in">
								<div class="panel-body">
									<input type="hidden" id="module" value="Authority">
									<select class="form-control tpl" id="tpl" name="tpl" onchange="getTplDetails()" disabled>
										<option value="0">&nbsp;Please select&nbsp;</option>
											<%
												SQLStatement tpl = new SQLStatement();
												String marc = Handler.marcType("Authority");
												List<Cataloging> tpllist = tpl.getTemplate(marc);
												for (Cataloging e : tpllist) {
											%>
										<option value="<%=e.getTempId()%>" data-type="<%=e.getTempType()%>"><%=e.getTempName()%></option>
											<%
												}
											%>
									</select>
									<input type="hidden" name="empName" id="empName" class="inp-form"/>
									<input type="hidden" name="tplid" id="tplid" class="inp-form"/>
								</div>
							</div>
						</div>
					 
						<div class="panel panel-default">
							<div class="panel-heading clearfix">
								<div class='errorMessage'></div>

								<h4 class="panel-title">
									<i class="indicator glyphicon glyphicon-chevron-up pull-right"></i>
								</h4>
							</div>
							  <div id="demo" class="collapse">
										<div id="tags"></div>
							</div>

						</div>
					</div>
				</div>  

	<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch"  data-backdrop="static">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content title">
			<!-- Remote content load here -->
			</div>
		</div>
	</div>	
	
	<div class="modal fade" style="z-index:1050" id="tagSearch" tabindex="-1" role="dialog" aria-labelledby="tagSearch"  data-backdrop="static">
	    <div class="modal-dialog" style="width:900px;" role="document">
			  <div class="modal-content">
			 <div id='unindexResult'></div>
			  </div>
		</div>
	</div>
	
		<div class="modal fade" id="BOIndi" tabindex="-1" role="dialog" aria-labelledby="BOIndi"  data-backdrop="static">
		<div class="modal-dialog" role="document" style="width:900px;">
			<div class="modal-content">
			<div  id='indicator_help'></div>
			<!-- Remote content load here -->
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="paraZ39" tabindex="-1" role="dialog" aria-labelledby="paraZ39"  aria-hidden="true"  data-backdrop="static"> 
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content" id="content_para">
			<!-- Remote content load here -->
			</div>
		</div>
	</div>
	
		<div class="modal fade modal-child" id="Z3950" data-backdrop-limit="1" tabindex="-1" role="dialog" aria-labelledby="Z3950" aria-hidden="true" data-modal-parent="#Z3950"  data-backdrop="static">
		<div class="modal-dialog" role="document" style="width:900px;">
			<div class="modal-content" id="content_Z3950" >
				<div class="contents"></div>
			<!-- Remote content load here -->
			</div>
		</div>
	</div>	
			 <!-- CANCEL A PO MODAL -->
	 <div class="modal hide fade" id="error-dialog">
	   <div class="modal-header">
			<a class="close" data-dismiss="modal">x</a>
			<h3>Cancel Purchase Order?</h3>
	   </div>
	   <div class="modal-body">
	   </div>
	   <div class="modal-footer">
		   <a href="#" class="btn btn-danger btn-modal btn-cancel"  data-dismiss="modal">Yes, cancel it</a>
		   <a href="#" class="btn" data-dismiss="modal">Nevermind</a>
	   </div>
	 </div>

</body>

</html>
