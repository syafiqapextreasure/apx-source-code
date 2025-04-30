
<script>
function login(){
	var username = $("#loginId").val();
	var password = $("#password").val();
	
	 $.get("CheckLoginIn",{patronid:username,password:password}, function(data){
		
		 if(data.trim()=="true"){
			 $("#userLogIn").modal("hide");
			 $("#overrideModal").modal("show");
			 $(".errorMessage").html("");
		 }else if(data.trim()=="false"){
			 $(".errorMessage").html("You have entered an invalid username or password");
		 }
	 })
	
	
}
</script>

<%
	String program = request.getParameter("program");
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	Override User
</div>
	<div class="modal-body">
	<form id="receiptingOverrideForm" class="form-horizontal ">
				<div class="row modal-size">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">


						<div class="form-group">
							<label class="col-sm-3 control-label">Patron Id &nbsp;:</label>
							<div class="col-sm-6">
								<input class="form-control" type="text" id="loginId"
									name="PatronId"	>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">Password &nbsp;:</label>
							<div class="col-sm-4">
								<input class="form-control" type="password" id="password" >
							</div>
						</div>
						<input type="hidden" id="program" value="<%=program%>">
						
						<div class="form-group">
							<label class="col-sm-6 control-label errorMessage" style="color:red"></label>
						</div>

					</div>
				</div>
				</form>
			</div>
			<div class="modal-footer">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-info" onclick="login()">Log In</button>
						<button type="button" class="btn btn-info" data-dismiss="modal">
							<span aria-hidden="true">Cancel</span>
						</button>
					</div>
				</div>
			</div>