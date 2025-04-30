<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.foundation.global.*"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/viewNote.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/validateviewNote.js"></script>	
<style>
.modalViewNote {
	z-index: 1080 !important;
}
</style>

<script>
console.log('masuk viewnot x?');
$(document).ready(function() {
	
	/* $('#table-note').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	$("#viewprm, #editprm").prop("selectedIndex",-1); */
});

</script>

 	<div class="modal-header">
		<h5 class="modal-title" id="modalName" align="center">Annotation</h5>
			<button type="button" id="closeModalNote" class="close" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
	</div>
	<div class="modal-body">
		<form role="form" class="form-horizontal formdataNote" id="formdataNote" method="post">
			<table id="table-note" class="table table-hover table-note">
				<thead>
					<tr>
						<th>ID</th>
						<th>Date</th>
						<th>Time</th>
						<th>Title</th>
						<th>Author</th>
						<th>View Permission</th>
						<th>Edit Permission</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody></tbody>
				 </table>

			<br>
			<button type="button" id="addNote" class="btn btn-primary addNote">Add</button>
			<br><br>
			
				<input type="hidden" class="form-control passval" id="passval" name="passval">
				<input type="hidden" class="form-control idnote" id="idnote" name="idnote">
				 
				<div class="form-group">
					<div class="col-sm-2"><label>Title</label></div>
					<div class="col-sm-5">
						<input type="text" class="form-control notetitle" id="notetitle" name="notetitle">
					</div>
				</div>
							
				<div class="form-group">
					<div class="col-sm-2"><label>Annotation</label></div>
						<div class="col-sm-8">
							<textarea class="form-control note" rows="2" id="note" name="note"></textarea>
						</div>
					</div>
							
					<div class="form-group">
						<div class="col-sm-2"><label>View Permission </label></div>
						<div class="col-sm-3">
							<select class="form-control viewprm" id="viewprm" name="viewprm">
								<option value="1">1 - Public</option>
								<option value="2">2 - Group</option>
								<option value="3">3 - Personal</option>
							</select>
						</div>
					</div>
							
					<div class="form-group">
						<div class="col-sm-2"><label>Edit Permission </label></div>
							<div class="col-sm-3">
								<select class="form-control editprm" id="editprm" name="editprm">
									<option value="1">1 - Public</option>
									<option value="2">2 - Group</option>
									<option value="3">3 - Personal</option>
								</select>
							</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-2"><label>Allow vendor to view </label></div>
							<div class="col-sm-6">
								<label class="radio-inline">
						       		<input id="vendorView" name="vendorView" type="radio" value="Y">Yes
						       </label>
						       <label class="radio-inline">
						       <input id="vendorView" name="vendorView" type="radio" value="N">No
						        </label>
							</div>
					</div>

					<div class="form-group modal-footer">
						<button type="submit" id="saveNote" class="btn btn-primary saveNote" disabled>Save</button>
						<button type="button" id="cancelNote" class="btn btn-secondary cancelNote" data-dismiss="modal">Cancel</button>
					     <button type="button" id="closeNote" class="btn btn-default closeNote" data-dismiss="modal">Close</button>    
					</div>
		</form>
	</div>
