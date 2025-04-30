<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Foundation/Maintenance/AAIFN1900M60.js">
</script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-heading">
					<button class="btn btn-primary pull-right" data-toggle='modal'
						data-target='#searchtagparameter' id="searchTagParameter">
						<i class="glyphicon glyphicon-search" title="Search"
							data-searchfor="tagparameter"></i>
					</button>
					<button type="button" class="btn btn-primary pull-right"
						id="addTagParameter" data-toggle='modal'
						data-target="#modaltagparameter" data-mode="1">
						<i class="glyphicon glyphicon-plus" title="Add Tag Parameter"></i>
					</button>
					<div class="clearfix"></div>
				</div>
			</div>

			<div class="panel-body" id="display_maintFnd">
				<table id="fndMMainttTable"
					class="table table-bordered table-striped display compact"
					width="100%">
					<thead>
						<tr>
							<th>MARC</th>
							<th>Tag</th>
							<th>Description</th>
							<th>Action</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->

	<!-- START MODAL FOR DELETE -->
	<div class="modal fade" id="deleteModalTagParameter" tabindex="-1"
		role="dialog" aria-labelledby="ModalDelete" aria-hidden="true"
		data-keyboard="false" data-backdrop="false">
	</div>
	<!-- END MODAL FOR DELETE -->

	<!-- START MODAL ADD, EDIT, VIEW -->
	<div class="modal fade" id="modaltagparameter" tabindex="-1"
		role="dialog" aria-labelledby="ModalAdd" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<!-- START Modal content-->
		<div class="modal-dialog" role="document" style="width: 70%">
			<jsp:include page="Add_AAIFN1900M60.jsp"></jsp:include>
		</div>
		<!-- END Modal content-->
	</div>
	<!-- END MODAL ADD, EDIT, VIEW -->
	
	<!-- START MODAL SEARCH -->
	<div class="modal fade" id="searchtagparameter" tabindex="-1"
		role="dialog" aria-labelledby="ModalSearch" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<!-- START Modal content-->
		<div class="modal-dialog" role="document" style="width: 50vw">
			<jsp:include page="SearchCriteriaTagParameter.jsp"></jsp:include>
		</div>
		<!-- END Modal content-->
	</div>
	<!-- END MODAL SEARCH -->
</body>
</html>