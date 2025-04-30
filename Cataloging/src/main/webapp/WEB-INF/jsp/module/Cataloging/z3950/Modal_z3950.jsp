<!-- Modal content-->	  
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<!-- <h4 class="modal-title" id="myModalLabel">
				Add Tag
		</h4> -->
	</div>
	<div class="modal-body">
	<%
	 String pathName = "C:/Users/Suitha/Documents/workspace/z3950/WebContent/WEB-INF/jsp/module/z3950/zClient.jsp";
	System.out.println(pathName);
	
	%>	
		<jsp:include page="<%=pathName %>"/>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-info" id="convert">
			<span class="glyphicon glyphicon-save"></span> Add
		</button>
		<input type="button" name="cancel" value="Cancel" class="btn btn-info btn-cancel" data-dismiss="modal"/>
	</div>		