<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<div class="modal-content">
	<!-- Modal content-->
	<div class="modal-header"
		style="display: flex; align-items: center; justify-content: space-between;">
		<h5 class="modal-title" align="center"
			style="text-align: center; flex-grow: 1;">Search Criteria</h5>
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close" style="margin-left: auto;">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<form role="form" class="form-horizontal">
		<br>
		<div class="form-group" id="">
			<div class="col-sm-1"></div>
			<div class="row form-group">
				<div class="col-sm-3">
					<label><input type="checkbox" id="marcSearchCriteria">
						MARC</label>
				</div>
				<div class="col-sm-4">
					<select class="form-control" id="marcSearchSelect"
						name="marcSearchSelect" aria-label="Default select example">
						<jsp:include page="../../../../module/Select_Fndcode.jsp">
							<jsp:param name="fcode" value="H" />
						</jsp:include>
					</select>
				</div>
			</div>

			<div class="col-sm-1"></div>
			<div class="row form-group">
				<div class="col-sm-3">
					<label> <input type="radio" name="choice" value="tag"
						id="tagNoSearchCriteria" checked> Tag
					</label> <br> <label> <input type="radio" name="choice" value="description"
						id="descriptionSearchCriteria"> Description
					</label>
				</div>
				<div class="col-sm-4">
					<input type="text" class="form-control" style="height: 8vh;"
						id="tagNdescription">
				</div>
			</div>
		</div>
		<div class="modal-footer form-horizontal">
			<button type="button" class="btn btn-primary" id="btn_find"
				data-dismiss="modal">
				<span class="glyphicon glyphicon-search"></span> Find
			</button>
			<button type="button" id="close" class="btn btn-default"
				data-dismiss="modal">Cancel</button>
		</div>
	</form>
</div>
<!-- END Modal content-->